package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kun_ma
 *
 */
public class MetaDataTablesMap {
	private static final String TAG = "MetaData Tables Map";
	public static Map<Integer, String> TableMap = new HashMap<Integer, String>();
	static
	{
		TableMap.put(0, "Module");
		TableMap.put(1, "TypeRef");
		TableMap.put(2, "TypeDef");
		TableMap.put(3, "FieldPtr");  //A class-to-fields lookup table, which does not exist in optimized metadata (#~ stream)
		TableMap.put(4, "Field");
		TableMap.put(5, "MethodPtr");  //A class-to-methods lookup table, which does not exist in optimized metadata (#~ stream).
		TableMap.put(6, "Method");
		TableMap.put(7, "ParamPtr"); //A method-to-parameters lookup table, which does not exist in optimized metadata (#~ stream).
		TableMap.put(8, "Param");
		TableMap.put(9, "InterfaceImpl");
		TableMap.put(10, "MemberRef");
		TableMap.put(11, "Constant");
		TableMap.put(12, "CustomAttribute");
		TableMap.put(13, "FieldMarshal");
		TableMap.put(14, "DeclSecurity");
		TableMap.put(15, "ClassLayout");
		TableMap.put(16, "FieldLayout");
		TableMap.put(17, "StandAloneSig");
		TableMap.put(18, "EventMap");
		TableMap.put(19, "EventPtr"); //An event map每to每events lookup table, which does not exist in optimized metadata (#~ stream).
		TableMap.put(20, "Event");
		TableMap.put(21, "PropertyMap");
		TableMap.put(22, "PropertyPtr"); //A property map每to每properties lookup table, which does not exist in optimized metadata (#~ stream).
		TableMap.put(23, "Property");
		TableMap.put(24, "MethodSemantics");
		TableMap.put(25, "MethodImpl");
		TableMap.put(26, "ModuleRef");
		TableMap.put(27, "TypeSpec");
		TableMap.put(28, "ImplMap");
		TableMap.put(29, "FieldRVA");
		TableMap.put(30, "ENCLog"); //Edit-and-continue log descriptors that hold information about what changes have been made to specific metadata items during in-memory editing. This table does not exist in optimized metadata (#~ stream).
		TableMap.put(31, "ENCMap"); //Edit-and-continue mapping descriptors. This table does not exist in optimized metadata (#~ stream).
		TableMap.put(32, "Assembly");
		TableMap.put(33, "AssemblyProcessor");
		TableMap.put(34, "AssemblyOS");
		TableMap.put(35, "AssemblyRef");
		TableMap.put(36, "AssemblyRefProcessor");
		TableMap.put(37, "AssemblyRefOS");
		TableMap.put(38, "File");
		TableMap.put(39, "ExportedType");
		TableMap.put(40, "ManifestResource");
		TableMap.put(41, "NestedClass"); 
		TableMap.put(42, "GenericParam");  // added in CLR 2.0
		TableMap.put(43, "MethodSpec");  //added in CLR 2.0
		TableMap.put(44, "GenericParamConstraint");  // added in CLR 2.0
	}

}
