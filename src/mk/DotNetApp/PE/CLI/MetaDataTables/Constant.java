package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.CLI.MetaDataTables.constant.CodedTokenType;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * Constant; RID Type: 11; Token Type: None; MD Streams: #~, #-
 */
public class Constant implements IMetaDataTables {
	private String TableTag = "Constant";
	
	private int Type;  //BYTE
	private int PaddingZero; // BYTE
	private int Parent;  // HasConstant
	private int Value;   // BLOB
	
	private String refTableName;
	private int refTableIndex;
	
	public String getTableTag() {
		return this.TableTag;
	}
	
	public Constant(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setType(dr.readByte());
		this.setPaddingZero(dr.readByte());
		
		this.setParent(dr, tablesHeader);
		
		if (4==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setValue(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setValue(dr.readWord());
		}
		
	}
	
	public int getType() {
		return this.Type;
	}
	
	public int getPaddingZero() {
		return this.PaddingZero;
	}
	
	public int getParent() {
		return this.Parent;
	}
	
	public int getValue() {
		return this.Value;
	}
	
	public String getRefTableName() {
		return this.refTableName;
	}
	
	public int getRefTableIndex() {
		return this.refTableIndex;
	}
	
	public void setType(int type) {
		this.Type = type;
	}
	
	public void setPaddingZero(int paddingZero) {
		this.PaddingZero = paddingZero;
	}
	
	public void setParent(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.Parent = dr.readWord();
		
		int bitTag = CodedTokenType.TYPEMAP.get("HasConstant").get("TagBits");
		int maxMask = (int) (Math.pow(2, bitTag) - 1);  // this is important
		int index = this.Parent & maxMask;
		int tableIndex = this.Parent >> bitTag;
		
		Iterator<Entry<String, Integer>> iter = CodedTokenType.TYPEMAP.get("HasConstant").entrySet().iterator();
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
			this.Parent += (extra << 8);
		}
		else {
			this.refTableIndex = tableIndex;
		}
	}
	
	public void setValue(int value) {
		this.Value = value;
	}
}
