package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.CLI.MetaDataTables.constant.CodedTokenType;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 *  ImplMap; RID Type: 29; Token Type: None; MD Streams: #~, #-
 */
public class ImplMap implements IMetaDataTables {
	private String TableTag = "ImplMap";
	
	private int MappingFlags;   //USHORT                  Validity mask: 0x0747
	private int MemberForwarded;   //MemberForwarded      Method only
	private int ImportName;    //STRING                   Entry point name
	private int ImportScope;   //RID: ModuleRef           ModuleRef to unmanaged DLL
	
	private String memberRefTableName;
	private int memberRefTableIndex;
	
	private String scopeRefTableName;
	private int scopeRefTableIndex;
	
	public String getTableTag() {
		return this.TableTag;
	}
	
	public ImplMap(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setMappingFlags(dr.readWord());
		
		this.setMemberForwarded(dr, tablesHeader);
		
		if (4==tablesHeader.getWideOfStringStreamIndex()) {
			this.setImportName(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfStringStreamIndex()) {
			this.setImportName(dr.readWord());
		}
		
		if (tablesHeader.getRecordOfEachTable().get("ModuleRef") > 0xFFFF) {
			this.setImportScope(dr.readDoubleWord());
		}
		else {
			this.setImportScope(dr.readWord());
		}
	}
	
	public int getMappingFlags() {
		return this.MappingFlags;
	}
	
	public int getMemberForwarded() {
		return this.MemberForwarded;
	}
	
	public int getImportName() {
		return this.ImportName;
	}
	
	public int getImportScope() {
		return this.ImportScope;
	}
	
	public String getMemberRefTableName() {
		return this.memberRefTableName;
	}
	
	public int getMemberRefTableIndex() {
		return this.memberRefTableIndex;
	}
	
	public String getScopeRefTableName() {
		return this.scopeRefTableName;
	}
	
	public int getScopeRefTableIndex() {
		return this.scopeRefTableIndex;
	}
	
	public void setMappingFlags(int flags) {
		this.MappingFlags = flags;
	}
	
	public void setMemberForwarded(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.MemberForwarded = dr.readWord();
		
		int bitTag = CodedTokenType.TYPEMAP.get("MemberForwarded").get("TagBits");
		int maxMask = (int) (Math.pow(2, bitTag) - 1);  // this is important
		int index = this.MemberForwarded & maxMask;
		int tableIndex = this.MemberForwarded >> bitTag;
		
		Iterator<Entry<String, Integer>> iter = CodedTokenType.TYPEMAP.get("MemberForwarded").entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Integer> entry = (Entry<String, Integer>) iter.next();
			String tableName = entry.getKey();
			int bitFlag = entry.getValue();
			if (bitFlag==index && tableName!="TagBits") {
				this.memberRefTableName = tableName;
				break;
			}	
		}
		
		int recordNum = tablesHeader.getRecordOfEachTable().get(this.memberRefTableName);
		if (recordNum > (0xffff - maxMask)) {
			int extra = dr.readWord();
			this.memberRefTableIndex = (extra << (8-bitTag)) + tableIndex;
			this.MemberForwarded += (extra << 8);
		}
		else {
			this.memberRefTableIndex = tableIndex;
		}
	}
	
	public void setImportName(int name) {
		this.ImportName = name;
	}
	
	public void setImportScope(int scope) {
		this.ImportScope = scope;
		this.scopeRefTableName = "ModuleRef";
		this.scopeRefTableIndex = scope;
	}
}
