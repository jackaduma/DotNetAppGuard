package mk.DotNetApp.TestSuite;
/**
 * @author makun
 *
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;
import java.util.Map.Entry;

import mk.DotNetApp.PE.PE;
import mk.DotNetApp.PE.CLI.TextSection;
import mk.DotNetApp.PE.CLI.MetaDataTables.IMetaDataTables;
import mk.DotNetApp.PE.CLI.MetaDataTables.Method;
import mk.DotNetApp.PE.CLI.MetaDataTables.TableFactory;
import mk.DotNetApp.PE.CLI.MetaDataTables.MethodHandler.AMethod;
import mk.DotNetApp.PE.CLI.io.TextSectionParser;
import mk.DotNetApp.PE.io.DataReader;
import mk.DotNetApp.PE.io.PEParser;


/**
 * An example demonstrating parsing an executable.
 */

public class Test_ParseExecutable {
	
	public static void testPE() throws Exception {
		String peFilePath = "c:\\windows\\system32\\notepad.exe";
		peFilePath = "C:\\Users\\kun_ma\\Desktop\\SecurityPack.exe";
//		peFilePath = "E:\\Projects\\DotNetAppGuard\\Win8Metro\\GoEverywhere_v1_2089\\SecurityPack.exe";
		PE pe = PEParser.parse(peFilePath);
		System.out.println(pe.getDosHeader().getMagic());
		System.out.println(pe.getSignature().isValid());
		System.out.println(pe.getOptionalHeader().getMagic());
		System.out.println(pe.getOptionalHeader().isValid());
		System.out.println(pe.getOptionalHeader().isPE32plus());
		System.out.println(pe.getOptionalHeader().getDataDirectoryCount());
		System.out.println("***********************************************");
		for (int i=0; i<16; i++) {
			System.out.println(pe.getOptionalHeader().getDataDirectory(i).getSize());
		}
		System.out.println("***********************************************");
		
		System.out.println(pe.getSectionTable().getNumberOfSections());
		for (int i=0; i<3; i++) {
			System.out.println(pe.getSectionTable().getSection(i).getData());
		}
		System.out.println("***********************************************");
		
		System.out.println(pe.getImageData().getClrRuntimeHeader().getSize());
		System.out.println(pe.getImageData().getClrRuntimeHeader().getMajorRuntimeVersion());
		System.out.println(pe.getImageData().getClrRuntimeHeader().getMinorRuntimeVersion());
		System.out.println(pe.getImageData().getClrRuntimeHeader().getMetaData().getVirtualAddress());		
	}
	
	public static void testTextSection() throws IOException {
		String peFilePath = "C:\\Users\\kun_ma\\Desktop\\SecurityPack.exe";
		peFilePath = "C:\\Users\\kun_ma\\Desktop\\Common.dll";
		peFilePath = "C:\\Users\\kun_ma\\Desktop\\SecurityPackLocalHttpServer.dll";
		peFilePath = "C:\\Users\\kun_ma\\Desktop\\SecurityPackModule.dll";		
//		peFilePath = "E:\\Projects\\DotNetAppGuard\\Win8Metro\\GoEverywhere_v1_2089\\SecurityPack.exe";
		PE pe = PEParser.parse(peFilePath);
		
		TextSectionParser parser = new TextSectionParser(pe, peFilePath);
		TextSection textSecion = parser.parse(peFilePath);
		
		Map<String, List<IMetaDataTables>> tables = textSecion.getMetaDataTablesData().getTablesData();		
		List<IMetaDataTables> methodsList = tables.get("Method");
		Iterator<IMetaDataTables> it = methodsList.iterator();		
		while (it.hasNext()) {
			Method m = (Method) it.next();
			System.out.println("-----------------------"+ m.getName() +"-----------------------");
			File f = new File(peFilePath);
			FileInputStream fi = new FileInputStream(f);
			DataReader d = new DataReader(fi);
			AMethod e = new AMethod(d, m.getPointerToRawData(), tables);
			fi.close();
			d.close();
		}		
	}
	
	public static void main(String[] args) throws Exception {
		testTextSection();
	}
}
