package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.CLI.MetaDataTables.constant.CodedTokenType;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author makun
 * TypeRef; RID Type: 01; Token Type: 0x01000000; MD Streams: #~, #-
 */
public class TypeRef implements IMetaDataTables {
	private static final String TAG = "TypeRef Table";
	
	private int ResolutionScope; //ResolutionScope:  <coded_token_type>
	private int Name;     //String
	private int Namespace; //String
	
	private String refTableName;
	private int refTableIndex;
	
	private MetaDataTablesHeader tablesHeader;
	private String typeName;
	private String typeNameSpace;
	
	private String scopeName;

	
	public TypeRef(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.tablesHeader = tablesHeader;
		
		setResolutionScope(dr, tablesHeader);		
		
		if (4 == tablesHeader.getWideOfStringStreamIndex()) {
			setName(dr.readDoubleWord());
			setNamespace(dr.readDoubleWord());
		}
		else if (2 == tablesHeader.getWideOfStringStreamIndex()) {
			setName(dr.readWord());
			setNamespace(dr.readWord());
		}
	}
	
	public String getScopeName() {
		return this.scopeName;
	}
	
	public void setScopeName(String scope) {
		this.scopeName = scope;
	}
	
	public String getTypeName() {
		return this.typeName;
	}
	
	public void setTypeName(String name) {
		this.typeName = name;
	}
	
	public String getTypeNameSpace() {
		return this.typeNameSpace;
	}
	
	public void setTypeNameSpace(String nameSpace) {
		this.typeNameSpace = nameSpace;
	}
	
	public int getResolutionScope() {
		return this.ResolutionScope;
	}
	
	public int getName() {
		return this.Name;
	}
	
	public int getNamespace() {
		return this.Namespace;
	}
	
	public String getRefTableName() {
		return this.refTableName;
	}
	
	public int getRefTableIndex() {
		return this.refTableIndex;
	}
	
	public void setResolutionScope(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.ResolutionScope = dr.readWord();
		
		int bitTag = CodedTokenType.TYPEMAP.get("ResolutionScope").get("TagBits");
		int maxMask = (int) (Math.pow(2, bitTag) - 1);  // this is important
		int index = this.ResolutionScope & maxMask;
		int tableIndex = this.ResolutionScope >> bitTag;
		
		Iterator<Entry<String, Integer>> iter = CodedTokenType.TYPEMAP.get("ResolutionScope").entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Integer> entry = (Entry<String, Integer>) iter.next();
			String tableName = entry.getKey();
			int bitFlag = entry.getValue();
			if (bitFlag==index && tableName!="TagBits") {
				this.refTableName = tableName;
				break;
			}	
		}
		int recordNum = tablesHeader.getRecordOfEachTable().get(this.refTableName);
		if (recordNum > (0xffff - maxMask)) {
			int extra = dr.readWord();
			this.refTableIndex = (extra << (8-bitTag)) + tableIndex;
			this.ResolutionScope += (extra << 8);
		}
		else {
			this.refTableIndex = tableIndex;
		}
	}
	
	public void setName(int name) {
		this.Name = name;
	}
	
	public void setNamespace(int namespace) {
		this.Namespace = namespace;
	}
	
	public void ReferenceHandler() throws IOException {
		this.typeName = this.tablesHeader.getStringsHeap().getUTF8String(this.Name);
		this.typeNameSpace = this.tablesHeader.getStringsHeap().getUTF8String(this.Namespace);
		
		if (this.refTableName.equals("Module")) {
			Module table = (Module) this.tablesHeader.getTablesData().get(this.refTableName).get(this.refTableIndex-1);
		}
		else if (this.refTableName.equals("ModuleRef")) {
			ModuleRef table = (ModuleRef) this.tablesHeader.getTablesData().get(this.refTableName).get(this.refTableIndex-1);
		}
		else if (this.refTableName.equals("AssemblyRef")) {
			AssemblyRef table = (AssemblyRef) this.tablesHeader.getTablesData().get(this.refTableName).get(this.refTableIndex-1);
			this.scopeName = table.getAssemblyName();
		}
		else if (this.refTableName.equals("TypeRef")) {
			TypeRef table = (TypeRef) this.tablesHeader.getTablesData().get(this.refTableName).get(this.refTableIndex-1);
		}
		else {
			throw new IOException();
		}
	}

}
