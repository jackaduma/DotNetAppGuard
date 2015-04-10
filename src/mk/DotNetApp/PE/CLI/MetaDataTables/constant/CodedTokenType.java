package mk.DotNetApp.PE.CLI.MetaDataTables.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kun_ma
 *
 */
public class CodedTokenType {
	
	public static Map<String, Map<String, Integer>> TYPEMAP = new HashMap<String, Map<String, Integer>>();
	static {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("TypeDef", 0);
		map.put("TypeRef", 1);
		map.put("TypeSpec", 2);
		map.put("TagBits", 2);  // this is a common flag for get number bits to encode tag
		TYPEMAP.put("TypeDefOrRef", map);
		
		map = new HashMap<String, Integer>();
		map.put("Field", 0);
		map.put("Param", 1);
		map.put("Property", 2);
		map.put("TagBits", 2);  // this is a common flag for get number bits to encode tag
		TYPEMAP.put("HasConstant", map);
		
		map = new HashMap<String, Integer>();
		map.put("Method", 0);
		map.put("Field", 1);
		map.put("TypeRef", 2);
		map.put("TypeDef", 3);
		map.put("Param", 4);
		map.put("InterfaceImpl", 5);
		map.put("MemberRef", 6);
		map.put("Module", 7);
		map.put("Permission", 8);
		map.put("Property", 9);
		map.put("Event", 10);
		map.put("Signature", 11);
		map.put("ModuleRef", 12);
		map.put("TypeSpec", 13);
		map.put("Assembly", 14);
		map.put("AssemblyRef", 15);
		map.put("File", 16);
		map.put("ExportedType", 17);
		map.put("ManifestResource", 18);
		map.put("GenericParam", 19);
		map.put("GenericParamConstraint", 20);
		map.put("MethodSpec", 21);
		map.put("TagBits", 5);  // this is a common flag for get number bits to encode tag
		TYPEMAP.put("HasCustomAttribute", map);
		
		map = new HashMap<String, Integer>();
		map.put("Field", 0);
		map.put("Param", 1);
		map.put("TagBits", 1);  // this is a common flag for get number bits to encode tag
		TYPEMAP.put("HasFieldMarshal", map);
		
		map = new HashMap<String, Integer>();
		map.put("TypeDef", 0);
		map.put("Method", 1);
		map.put("Assembly", 2);
		map.put("TagBits", 2);  // this is a common flag for get number bits to encode tag
		TYPEMAP.put("HasDeclSecurity", map);
		
		map = new HashMap<String, Integer>();
		map.put("TypeDef", 0);
		map.put("TypeRef", 1);
		map.put("ModuleRef", 2);
		map.put("Method", 3);
		map.put("TypeSpec", 4);
		map.put("TagBits", 3);  // this is a common flag for get number bits to encode tag
		TYPEMAP.put("MemberRefParent", map);
		
		map = new HashMap<String, Integer>();
		map.put("Event", 0);
		map.put("Property", 1);
		map.put("TagBits", 1);  // this is a common flag for get number bits to encode tag
		TYPEMAP.put("HasSemantics", map);
		
		map = new HashMap<String, Integer>();
		map.put("Method", 0);
		map.put("MemberRef", 1);
		map.put("TagBits", 1);  // this is a common flag for get number bits to encode tag
		TYPEMAP.put("MethodDefOrRef", map);
		
		map = new HashMap<String, Integer>();
		map.put("Field", 0);
		map.put("Method", 1);
		map.put("TagBits", 1);  // this is a common flag for get number bits to encode tag
		TYPEMAP.put("MemberForwarded", map);
		
		map = new HashMap<String, Integer>();
		map.put("File", 0);
		map.put("AssemblyRef", 1);
		map.put("ExportedType", 2);
		map.put("TagBits", 2);  // this is a common flag for get number bits to encode tag
		TYPEMAP.put("Implementation", map);		
		
		map = new HashMap<String, Integer>();
		map.put("none", 0);
		map.put("none", 1);
		map.put("Method", 2);
		map.put("MemberRef", 3);
		map.put("none", 4);
		map.put("TagBits", 3);  // this is a common flag for get number bits to encode tag
		TYPEMAP.put("CustomAttributeType", map);
		
		map = new HashMap<String, Integer>();
		map.put("Module", 0);
		map.put("ModuleRef", 1);
		map.put("AssemblyRef", 2);
		map.put("TypeRef", 3);
		map.put("TagBits", 2);  // this is a common flag for get number bits to encode tag
		TYPEMAP.put("ResolutionScope", map);
		
		map = new HashMap<String, Integer>();
		map.put("TypeDef", 0);
		map.put("Method", 1);
		map.put("TagBits", 1);  // this is a common flag for get number bits to encode tag
		TYPEMAP.put("TypeOrMethodDef", map);
	}
	
	
}
