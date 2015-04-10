package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.CLI.MetaDataTables.constant.CodedTokenType;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * ExportedType; RID Type: 39; Token Type: 0x27000000; MD Streams: #~, #-
 */
public class ExportedType implements IMetaDataTables {
	private static final String TAG = "ExportedType Table";
	
	private int Flags; //ULONG     ====Validity mask: 0x00200007
	private int TypeDefId; //ULONG    ====TypeDef token in another module
	private int TypeName; //STRING
	private int TypeNamespace; //STRING
	private int Implementation;   //Implementation    =====File, ExportedType, AssemblyRef
	
	private String refTableName;
	private int refTableIndex;
	
	public ExportedType(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setFlags(dr.readDoubleWord());
		this.setTypeDefId(dr.readDoubleWord());
		
		if (4==tablesHeader.getWideOfStringStreamIndex()) {
			this.setTypeName(dr.readDoubleWord());
			this.setTypeNamespace(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfStringStreamIndex()) {
			this.setTypeName(dr.readWord());
			this.setTypeNamespace(dr.readWord());
		}
		
		this.setImplementation(dr, tablesHeader);
	}
	
	public int getFlags() {
		return this.Flags;
	}
	
	public void setFlags(int flags) {
		this.Flags = flags;
	}
	
	public int getTypeDefId() {
		return this.TypeDefId;
	}
	
	public void setTypeDefId(int id) {
		this.TypeDefId = id;
	}
	
	public int getTypeName() {
		return this.TypeName;
	}
	
	public void setTypeName(int name) {
		this.TypeName = name;
	}
	
	public int getTypeNamespace() {
		return this.TypeNamespace;
	}
	
	public void setTypeNamespace(int space) {
		this.TypeNamespace = space;
	}
	
	public int getImplementation() {
		return this.Implementation;
	}
	
	public String getRefTableName() {
		return this.refTableName;
	}
	
	public int getRefTableIndex() {
		return this.refTableIndex;
	}
	
	public void setImplementation(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.Implementation = dr.readWord();
		
		int bitTag = CodedTokenType.TYPEMAP.get("Implementation").get("TagBits");
		int maxMask = (int) (Math.pow(2, bitTag) - 1);  // this is important
		int index = this.Implementation & maxMask;
		int tableIndex = this.Implementation >> bitTag;
		
		Iterator<Entry<String, Integer>> iter = CodedTokenType.TYPEMAP.get("Implementation").entrySet().iterator();
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
			this.Implementation += (extra << 8);
		}
		else {
			this.refTableIndex = tableIndex;
		}
	}
	
}
