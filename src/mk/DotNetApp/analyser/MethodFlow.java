package mk.DotNetApp.analyser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mk.DotNetApp.PE.PE;
import mk.DotNetApp.PE.CLI.TextSection;
import mk.DotNetApp.PE.CLI.MetaDataTables.IMetaDataTables;
import mk.DotNetApp.PE.CLI.MetaDataTables.Assembly;
import mk.DotNetApp.PE.CLI.MetaDataTables.Method;
import mk.DotNetApp.PE.CLI.MetaDataTables.TypeDef;
import mk.DotNetApp.PE.CLI.MetaDataTables.MethodHandler.AMethod;
import mk.DotNetApp.PE.CLI.io.TextSectionParser;
import mk.DotNetApp.PE.io.DataReader;
import mk.DotNetApp.PE.io.PEParser;

import mk.DotNetApp.analyser.common.CallGraph;
import mk.DotNetApp.analyser.common.GraphNode;
import mk.DotNetApp.analyser.common.MethodFlowInfectionChain;
import mk.DotNetApp.analyser.common.MethodFlowPattern;

/**
 * @author kun_ma
 * 
 */
public class MethodFlow {
	private Map<String, List<String>> method = new HashMap<String, List<String>>();

	private LinkedList<Map<String, List<String>>> methodFlow = new LinkedList<Map<String, List<String>>>();

	public String peFilePath;
	public Map<String, List<IMetaDataTables>> dotNetTables = new HashMap<String, List<IMetaDataTables>>();

	public Set<GraphNode> graph = new HashSet<GraphNode>();

	public MethodFlow(String fileName, Map<String, List<IMetaDataTables>> tables)
			throws Exception {
		this.peFilePath = fileName;
		this.dotNetTables = tables;

		this.InitMethodFullName();
		this.InitMethodCallCollection();
	}

	private void InitMethodFullName() {
		List<IMetaDataTables> assemblyList = this.dotNetTables.get("Assembly");
		Assembly assemblyTable = (Assembly) assemblyList.get(0);
		String assemblyName = assemblyTable.getAssemblyName();

		List<IMetaDataTables> typeDefList = this.dotNetTables.get("TypeDef"); // index
																				// with
																				// "i"
		List<IMetaDataTables> methodsList = this.dotNetTables.get("Method"); // index
																				// with
																				// "j"
		int totalMethodCount = methodsList.size();

		/*
		 * The first row of the TypeDef table represents the pseudo class that
		 * acts as parent for functions and variables defined at module scope.
		 * so the index begins with 1
		 */
		for (int i = 1; i < typeDefList.size() - 1; i++) {
			TypeDef type = (TypeDef) typeDefList.get(i);
			TypeDef nextType = (TypeDef) typeDefList.get(i + 1);
			String typeNameSpace = type.getTypeNameSpace();
			String typeName = type.getTypeName();
			int startIndex = type.getMethodList();
			int nextStartIndex = nextType.getMethodList();
			type.setMethodCount(nextStartIndex - startIndex);
			for (int j = startIndex; j < nextStartIndex; j++) {
				Method m = (Method) methodsList.get(j - 1);
				StringBuilder fullName = new StringBuilder(String.format(
						"%s-%s.%s-%s", assemblyName, typeNameSpace, typeName,
						m.getName()));
				m.setFullName(fullName.toString());
			}
		}
		// deal with the last record
		TypeDef type = (TypeDef) typeDefList.get(typeDefList.size() - 1);
		String typeNameSpace = type.getTypeNameSpace();
		String typeName = type.getTypeName();
		int startIndex = type.getMethodList();
		type.setMethodCount(totalMethodCount - startIndex + 1);
		for (int j = startIndex; j <= totalMethodCount; j++) {
			Method m = (Method) methodsList.get(j - 1);
			StringBuilder fullName = new StringBuilder(String.format(
					"%s-%s.%s-%s", assemblyName, typeNameSpace, typeName,
					m.getName()));
			m.setFullName(fullName.toString());
		}

	}

	private GraphNode getGNByName(String name) {
		Iterator<GraphNode> it = this.graph.iterator();
		while (it.hasNext()) {
			GraphNode gn = it.next();
			if (gn.signature.equals(name)) {
				return gn;
			}
		}
		return null;
	}

	private void InitMethodCallCollection() throws Exception {
		List<IMetaDataTables> methodsList = this.dotNetTables.get("Method");
		Iterator<IMetaDataTables> it = methodsList.iterator();
		while (it.hasNext()) {
			Method m = (Method) it.next();

			/*
			 * new
			 */
			GraphNode gn = new GraphNode(m);
			if (this.graph.contains(gn)) {
				gn = this.getGNByName(m.getFullName());
			} else {
				this.graph.add(gn);
			}

			System.out.println("-----------------------" + m.getFullName()
					+ "-----------------------");
			File f = new File(this.peFilePath);
			FileInputStream fi = new FileInputStream(f);
			DataReader d = new DataReader(fi);
			AMethod e = new AMethod(d, m.getPointerToRawData(),
					this.dotNetTables);

			/*
			 * new
			 */
			List<String> callList = e.getMethodBody().getCallMethodCollection();
			for (String c : callList) {
				GraphNode cn = new GraphNode(c);
				if (this.graph.contains(cn)) {
					cn = this.getGNByName(c);
				} else {
					this.graph.add(cn);
				}
				
				gn.children.add(cn);
				cn.parents.add(gn);
				// if ("PhoneAppTester-PhoneAppTester.MainPage-AccessWebAsync"
				// .equals(cn.signature)) {
				// System.out
				// .println("Get U: PhoneAppTester-PhoneAppTester.MainPage-AccessWebAsync");
				// System.out.println("Get U: " + gn.signature);
				// }
			}

			m.setCallMethodCollection(e.getMethodBody()
					.getCallMethodCollection());

			fi.close();
			d.close();
		}
	}

	public void DemoAnalyse() {

		List<Method> infectionChain = new LinkedList<Method>();

		List<IMetaDataTables> methodsList = this.dotNetTables.get("Method");

		String startFlag = MethodFlowPattern.getPatternList(2).get(0); // sink
																		// pattern
		String endFlag = MethodFlowPattern.getPatternList(1).get(0); // source
																		// pattern
		boolean endState = false;
		while (true) {
			Iterator<IMetaDataTables> it = methodsList.iterator();
			int checkNum = 0;

			while (it.hasNext()) {
				Method m = (Method) it.next();

				if (m.getCallMethodCollection().contains(startFlag)) {
					infectionChain.add(m);
					startFlag = m.getFullName();

					if (m.getCallMethodCollection().contains(endFlag)) {
						endState = true;
					}
					break;
				} else {
					checkNum += 1;
				}
			}
			System.out.println(checkNum);
			if (endState || checkNum == methodsList.size()) {
				break;
			}
		}

		System.out.println("*****************Analyse Over*****************");

		System.out.println(MethodFlowPattern.getPatternList(2).get(0)
				+ "  <----");
		for (int i = 0; i < infectionChain.size(); i++) {
			System.out.println(String.format("%s  <-----", infectionChain
					.get(i).getFullName()));
		}
		System.out.println(endFlag);

	}

	/*
	 * multi-loop, each loop find a infection chain
	 */
	public void StupidAnalyse() {

		List<MethodFlowInfectionChain> infectionChainList = new LinkedList<MethodFlowInfectionChain>();

		List<IMetaDataTables> methodsList = this.dotNetTables.get("Method");

		for (String sink : MethodFlowPattern.getPatternList(2)) {
			String startFlag = sink;
			for (String source : MethodFlowPattern.getPatternList(1)) {
				String endFlag = source;
				boolean endState = false;

				MethodFlowInfectionChain infectionChain = new MethodFlowInfectionChain(
						sink);
				LinkedList<Method> chain = new LinkedList<Method>();
				infectionChain.setInfectionChain(chain);

				while (true) {
					Iterator<IMetaDataTables> it = methodsList.iterator();
					int checkNum = 0;

					while (it.hasNext()) {
						Method m = (Method) it.next();
						if (m.getCallMethodCollection().contains(startFlag)) {
							infectionChain.getInfectionChain().add(m);
							startFlag = m.getFullName();

							if (m.getCallMethodCollection().contains(endFlag)) {
								infectionChain.setSource(endFlag);
								endState = true;
							}
							break;
						} else {
							checkNum += 1;
						}
					}

					System.out.println(checkNum);
					if (endState || checkNum == methodsList.size()) {
						break;
					}
				}

				// if (infectionChain.getSink() != null) {
				infectionChainList.add(infectionChain);
				// }

				System.out
						.println("*****************Analyse Over*****************");
				for (MethodFlowInfectionChain c : infectionChainList) {
					System.out.println(c.getSink() + "  <----");
					List<Method> ic = c.getInfectionChain();
					for (int i = 0; i < ic.size(); i++) {
						System.out.println(String.format("%s  <-----", ic
								.get(i).getFullName()));
					}
					System.out.println(c.getSource());
				}
			}
		}
	}

	/*
	 * Graph Traverse
	 */
	public void Analyse() {

		System.out.println("****************************************");
		System.out.println("Graph Node Count: " + this.graph.size());

		CallGraph cg = new CallGraph(this.graph, this.graph.size());
		cg.Backward(this.TaggingSink());

		Iterator<LinkedList<GraphNode>> sinkIt = cg.GetInfectionChainSet()
				.iterator();
		while (sinkIt.hasNext()) {
			LinkedList<GraphNode> lIt = sinkIt.next();
			System.out.println(lIt.toString());
		}

		cg = new CallGraph(this.graph, this.graph.size());
		cg.Backward(this.TaggingSource());

		Iterator<LinkedList<GraphNode>> sourceIt = cg.GetInfectionChainSet()
				.iterator();
		while (sourceIt.hasNext()) {
			LinkedList<GraphNode> lIt = sourceIt.next();
			System.out.println(lIt.toString());
		}

		List<MethodFlowInfectionChain> infectionChainList = new LinkedList<MethodFlowInfectionChain>();

		List<IMetaDataTables> methodsList = this.dotNetTables.get("Method");

		while (true) {
			Iterator<IMetaDataTables> it = methodsList.iterator();
			int checkNum = 0;
			int chainNum = 0;

			while (it.hasNext()) {
				Method m = (Method) it.next();

				for (String sink : MethodFlowPattern.getPatternList(2)) {
					if (m.getCallMethodCollection().contains(sink)) {
						MethodFlowInfectionChain methodFlowInfectionChain = new MethodFlowInfectionChain();

						methodFlowInfectionChain.setSink(sink);

						LinkedList<Method> infectionChain = new LinkedList<Method>();
						infectionChain.add(m);
						methodFlowInfectionChain
								.setInfectionChain(infectionChain);

						infectionChainList.add(methodFlowInfectionChain);
						chainNum += 1;

						break;
					}
				}

				boolean endState = false;
				for (MethodFlowInfectionChain chain : infectionChainList) {
					String startFlag = chain.getInfectionChain().getLast()
							.getFullName();
					if (m.getCallMethodCollection().contains(startFlag)) {
						chain.getInfectionChain().add(m);

						//
						for (String source : MethodFlowPattern
								.getPatternList(1)) {
							if (m.getCallMethodCollection().contains(source)) {
								chain.setSource(source);
								endState = true;
								break;
							}
						}
						if (endState) {
							continue;
						}
					} else {
						checkNum += 1;
					}
				}
			}
			System.out.println(checkNum);
			if (checkNum >= methodsList.size()) {
				break;
			}
		}

		System.out.println("*****************Analyse Over*****************");
		int index = 1;
		for (MethodFlowInfectionChain c : infectionChainList) {
			System.out.println(String.format(
					"********The %s infection chain: ***********", index));
			System.out.println(c.getSink() + "  <----");
			List<Method> ic = c.getInfectionChain();
			for (int i = 0; i < ic.size(); i++) {
				System.out.println(String.format("%s  <-----", ic.get(i)
						.getFullName()));
			}
			// System.out.println(c.getSource());
			index++;
		}

	}

	public GraphNode TaggingSource() {
		for (String sink : MethodFlowPattern.getPatternList(1)) {
			for (GraphNode n : this.graph) {
				if (sink.equals(n.toString())) {
					return n;
				}
			}
		}
		return null;
	}

	public GraphNode TaggingSink() {
		for (String sink : MethodFlowPattern.getPatternList(2)) {
			for (GraphNode n : this.graph) {
				if (sink.equals(n.toString())) {
					return n;
				}
			}
		}
		return null;
	}

	public GraphNode TaggingAction() {
		for (String sink : MethodFlowPattern.getPatternList(3)) {
			for (GraphNode n : this.graph) {
				if (sink.equals(n.toString())) {
					return n;
				}
			}
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		String peFilePath = "C:\\Users\\kun_ma\\Desktop\\PhoneAppTester.dll";
		PE pe = PEParser.parse(peFilePath);

		TextSectionParser parser = new TextSectionParser(pe, peFilePath);
		TextSection textSecion = parser.parse(peFilePath);

		Map<String, List<IMetaDataTables>> tables = textSecion
				.getMetaDataTablesData().getTablesData();

		MethodFlow mf = new MethodFlow(peFilePath, tables);
		mf.Analyse();
		// mf.StupidAnalyse();
		// mf.DemoAnalyse();
	}

}
