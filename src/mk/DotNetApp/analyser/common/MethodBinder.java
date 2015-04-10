package mk.DotNetApp.analyser.common;

import java.util.LinkedList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author kun_ma
 *
 */
public class MethodBinder {
	
	/*
	 * some kinds of method need to be binding together
	 * 1. async methods
	 * 2. call back methods
	 * 3. thread start() and run()
	 */
	
	/*
	 * String.format("%s-%s.%s-%s", assemblyName, typeNameSpace, typeName, methodName)
	 */
	public static Set<List<String>> methodsGroupSet = new HashSet<List<String>> ();
	static {
		List<String> methodsGroup = new LinkedList<String>();
		methodsGroup.add("mscorlib-System.Threading.Thread-Thread");
		methodsGroup.add("mscorlib-System.Threading.ThreadStart-ThreadStart");
		methodsGroup.add(""); // dynamic add method by params
		methodsGroupSet.add(methodsGroup);
		
		methodsGroup = new LinkedList<String>();
	}
	
	public static Pattern asyncStructPattern = Pattern.compile("^._< >d__0$");
	
	public static void AsyncBinder() {
		
	}
	
	
	public static void Bind() {
		
	}

}
