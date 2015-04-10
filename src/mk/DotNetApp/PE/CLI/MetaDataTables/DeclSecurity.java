package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.CLI.MetaDataTables.constant.CodedTokenType;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 *  DeclSecurity; RID Type: 14; Token Type: 0x0E000000; MD Streams: #~, #-
 */
public class DeclSecurity implements IMetaDataTables {
	private String TableTag = "DeclSecurity";
	
	private int Action;   //SHORT
	private int Parent;     //HasDeclSecurity
	private int PermissionSet;   //BLOB      =====Cannot be 0
	
	private String parentRefTableName;
	private int parentRefTableIndex;
	
	public String getTableTag() {
		return this.TableTag;
	}
	
	public DeclSecurity(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setAction(dr.readWord());
		
		this.setParent(dr, tablesHeader);
		
		if (4==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setPermissionSet(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setPermissionSet(dr.readWord());
		}
	}
	
	public int getAction() {
		return this.Action;
	}
	
	public int getParent() {
		return this.Parent;
	}
	
	public int getPermissionSet() {
		return this.PermissionSet;
	}
	
	public String getParentRefTableName() {
		return this.parentRefTableName;
	}
	
	public int getParentRefTableIndex() {
		return this.parentRefTableIndex;
	}
	
	public void setAction(int action) {
		this.Action = action;
	}
	
	public void setParent(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.Parent = dr.readWord();
		
		int bitTag = CodedTokenType.TYPEMAP.get("HasDeclSecurity").get("TagBits");
		int maxMask = (int) (Math.pow(2, bitTag) - 1);  // this is important
		int index = this.Parent & maxMask;
		int tableIndex = this.Parent >> bitTag;
		
		Iterator<Entry<String, Integer>> iter = CodedTokenType.TYPEMAP.get("HasDeclSecurity").entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Integer> entry = (Entry<String, Integer>) iter.next();
			String tableName = entry.getKey();
			int bitFlag = entry.getValue();
			if (bitFlag==index && tableName!="TagBits") {
				this.parentRefTableName = tableName;
				break;
			}	
		}
		
		int recordNum = tablesHeader.getRecordOfEachTable().get(this.parentRefTableName);
		if (recordNum > (0xffff - maxMask)) {
			int extra = dr.readWord();
			this.parentRefTableIndex = (extra << (8-bitTag)) + tableIndex;
			this.Parent += (extra << 8);
		}
		else {
			this.parentRefTableIndex = tableIndex;
		}		
	}
	
	public void setPermissionSet(int permissionSet) {
		this.PermissionSet = permissionSet;
	}

}
