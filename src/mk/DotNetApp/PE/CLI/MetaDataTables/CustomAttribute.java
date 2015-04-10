package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.CLI.MetaDataTables.constant.CodedTokenType;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 *  CustomAttribute; RID Type: 12; Token Type: 0x0C000000; MD Streams: #~, #-
 */
public class CustomAttribute implements IMetaDataTables {
	private String TableTag = "CustomAttribute";
	
	private int Parent;    //HasCustomAttribute
	private int Type;     //CustomAttributeType
	private int Value;    //BLOB     ====Can be 0
	
	private String parentRefTableName;
	private int parentRefTableIndex;
	
	private String typeRefTableName;
	private int typeRefTableIndex;
	
	public String getTableTag() {
		return this.TableTag;
	}

	public CustomAttribute(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setParent(dr, tablesHeader);
		this.setType(dr, tablesHeader);
		
		if (4==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setValue(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setValue(dr.readWord());
		}
		
	}
	
	public int getParent() {
		return this.Parent;
	}
	
	public int getType() {
		return this.Type;
	}
	
	public int getValue() {
		return this.Value;
	}
	
	public String getParentRefTableName() {
		return this.parentRefTableName;
	}
	
	public int getParentRefTableIndex() {
		return this.parentRefTableIndex;
	}
	
	public String getTypeRefTableName() {
		return this.typeRefTableName;
	}
	
	public int getTypeRefTableIndex() {
		return this.typeRefTableIndex;
	}
	
	public void setParent(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.Parent = dr.readWord();
		
		int bitTag = CodedTokenType.TYPEMAP.get("HasCustomAttribute").get("TagBits");
		int maxMask = (int) (Math.pow(2, bitTag) - 1);  // this is important
		int index = this.Parent & maxMask;
		int tableIndex = this.Parent >> bitTag;
		
		Iterator<Entry<String, Integer>> iter = CodedTokenType.TYPEMAP.get("HasCustomAttribute").entrySet().iterator();
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
	
	public void setType(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.Type = dr.readWord();
		
		int bitTag = CodedTokenType.TYPEMAP.get("CustomAttributeType").get("TagBits");
		int maxMask = (int) (Math.pow(2, bitTag) - 1);  // this is important
		int index = this.Type & maxMask;
		int tableIndex = this.Type >> bitTag;
		
		Iterator<Entry<String, Integer>> iter = CodedTokenType.TYPEMAP.get("CustomAttributeType").entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Integer> entry = (Entry<String, Integer>) iter.next();
			String tableName = entry.getKey();
			int bitFlag = entry.getValue();
			if (bitFlag==index && tableName!="TagBits") {
				this.typeRefTableName = tableName;
				break;
			}	
		}
		
		int recordNum = tablesHeader.getRecordOfEachTable().get(this.typeRefTableName);
		if (recordNum > (0xffff - maxMask)) {
			int extra = dr.readWord();
			this.typeRefTableIndex = (extra << (8-bitTag)) + tableIndex;
			this.Type += (extra << 8);
		}
		else {
			this.typeRefTableIndex = tableIndex;
		}
	}
	
	public void setValue(int value) {
		this.Value = value;
	}
}
