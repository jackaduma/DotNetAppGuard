package mk.DotNetApp.analyser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mk.DotNetApp.PE.PE;
import mk.DotNetApp.PE.CLI.TextSection;
import mk.DotNetApp.PE.CLI.MetaDataTables.Assembly;
import mk.DotNetApp.PE.CLI.MetaDataTables.IMetaDataTables;
import mk.DotNetApp.PE.CLI.MetaDataTables.Method;
import mk.DotNetApp.PE.CLI.MetaDataTables.TypeDef;
import mk.DotNetApp.PE.CLI.MetaDataTables.MethodHandler.AMethod;
import mk.DotNetApp.PE.CLI.io.TextSectionParser;
import mk.DotNetApp.PE.io.DataReader;
import mk.DotNetApp.PE.io.PEParser;
import mk.DotNetApp.analyser.common.MethodFlowPattern;

/**
 * @author kun_ma
 *
 */
public class APIGrep {
	
	private static void InitMethodFullName(Map<String, List<IMetaDataTables>> tables) {
		List<IMetaDataTables> assemblyList = tables.get("Assembly");
		Assembly assemblyTable = (Assembly) assemblyList.get(0);
		String assemblyName = assemblyTable.getAssemblyName();
		
		List<IMetaDataTables> typeDefList = tables.get("TypeDef");  // index with "i"
		List<IMetaDataTables> methodsList = tables.get("Method");   // index with "j"
		int totalMethodCount = methodsList.size();
		
		/*
		 * The first row of the TypeDef table represents the pseudo class 
		 * that acts as parent for functions and variables defined at module scope.
		 * so the index begins with 1
		 */
		for (int i=1; i<typeDefList.size()-1; i++) {
			TypeDef type = (TypeDef) typeDefList.get(i);
			TypeDef nextType = (TypeDef) typeDefList.get(i+1);
			String typeNameSpace = type.getTypeNameSpace();
			String typeName = type.getTypeName();
			int startIndex = type.getMethodList();
			int nextStartIndex =nextType.getMethodList();
			type.setMethodCount(nextStartIndex - startIndex);
			for (int j=startIndex; j<nextStartIndex; j++) {
				Method m = (Method) methodsList.get(j-1);
				StringBuilder fullName = new StringBuilder(String.format("%s-%s.%s-%s", assemblyName, typeNameSpace, typeName, m.getName()));
				m.setFullName(fullName.toString());
			}
		}
		// deal with the last record
		TypeDef type = (TypeDef) typeDefList.get(typeDefList.size()-1);
		String typeNameSpace = type.getTypeNameSpace();
		String typeName = type.getTypeName();
		int startIndex = type.getMethodList();
		type.setMethodCount(totalMethodCount - startIndex + 1);
		for (int j=startIndex; j<=totalMethodCount; j++) {
			Method m = (Method) methodsList.get(j-1);
			StringBuilder fullName = new StringBuilder(String.format("%s-%s.%s-%s", assemblyName, typeNameSpace, typeName, m.getName()));
			m.setFullName(fullName.toString());
		}
		
	}
	
	private static void InitMethodCallCollection(String peFilePath, Map<String, List<IMetaDataTables>> tables) throws Exception {
		List<IMetaDataTables> methodsList = tables.get("Method");
		Iterator<IMetaDataTables> it = methodsList.iterator();		
		while (it.hasNext()) {
			Method m = (Method) it.next();
			System.out.println("-----------------------"+ m.getFullName() +"-----------------------");
			File f = new File(peFilePath);
			FileInputStream fi = new FileInputStream(f);
			DataReader d = new DataReader(fi);
			AMethod e = new AMethod(d, m.getPointerToRawData(), tables);
			m.setCallMethodCollection(e.getMethodBody().getCallMethodCollection());
			fi.close();
			d.close();
		}
	}
	
	public static int Analyse(String peFilePath, Map<String, List<IMetaDataTables>> tables) throws Exception {
		InitMethodFullName(tables);
		
		InitMethodCallCollection(peFilePath, tables);
		
		List<IMetaDataTables> methodsList = tables.get("Method");
		
		List<String> suspiciousBehavior = new ArrayList<String>(); 
		
		List<String> sourceList = new ArrayList<String>();
		List<String> sinkList = new ArrayList<String>();
		
		for (String sink : MethodFlowPattern.getPatternList(2)) {    // sink pattern
			for (String source : MethodFlowPattern.getPatternList(1)) {   // source pattern
				Iterator<IMetaDataTables> it = methodsList.iterator();
				while (it.hasNext()) {
					Method m = (Method) it.next();
					
					if (m.getCallMethodCollection().contains(sink)) {
						String actionSink = MethodFlowPattern.getPatternKey(sink, 2);
						if (!sinkList.contains(actionSink)) {
							sinkList.add(actionSink);
						}						
					}
					
					if (m.getCallMethodCollection().contains(source)) {
						String actionSource = MethodFlowPattern.getPatternKey(source, 1);
						if (!sourceList.contains(actionSource)) {
							sourceList.add(actionSource);
						}
					}
				}
			}
		}
		
		int result = 0;
		for (String actionSource : sourceList) {
			for (String actionSink : sinkList) {
				String behavior = String.format("%s leaks by %s", actionSource, actionSink);
				suspiciousBehavior.add(behavior);
				
				result |= MethodFlowPattern.getActionCode(behavior);
			}
		}
		
		return result;
		
//		System.out.println("------------------API Grep Over ------------------------------");
//		for (String behavior : suspiciousBehavior) {
//			System.out.println(behavior);
//		}		
		
	}
	
	public static void main (String[] args) throws Exception {
		String peFilePath = "C:\\Users\\kun_ma\\Desktop\\PhoneAppTester.dll";		
		PE pe = PEParser.parse(peFilePath);
		
		TextSectionParser parser = new TextSectionParser(pe, peFilePath);
		TextSection textSecion = parser.parse(peFilePath);
		
		Map<String, List<IMetaDataTables>> tables = textSecion.getMetaDataTablesData().getTablesData();	
		
		Analyse(peFilePath, tables);
	}
	
}
