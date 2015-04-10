package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.CLI.MetaDataTables.constant.CodedTokenType;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * GenericParam; RID Type: 42; Token Type: 0x2A000000; MD Streams: #~, #-
 */
public class GenericParam implements IMetaDataTables {
	private static final String TAG = "GenericParam Table";
	
	private int Number;    //USHORT      ======Ordinal
	private int Flags;     //USHORT      =====Constraint flags
	private int Owner;      //TypeOrMethodDef    ====Generic type or method
	private int Name;       //STRING      ====Can be 0
	
	private String ownerRefTableName;
	private int ownerRefTableIndex;
	
	public GenericParam(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setNumber(dr.readWord());
		this.setFlags(dr.readWord());
		
		this.setOwner(dr, tablesHeader);
		
		if (4==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readWord());
		}
		
	}
	
	public int getNumber() {
		return this.Number;
	}
	
	public void setNumber(int n) {
		this.Number = n;
	}
	
	public int getFlags() {
		return this.Flags;
	}
	
	public void setFlags(int f) {
		this.Flags = f;
	}
	
	public int getOwner() {
		return this.Owner;
	}
	
	public String getOwnerRefTableName() {
		return this.ownerRefTableName;
	}
	
	public int getOwnerRefTableIndex() {
		return this.ownerRefTableIndex;
	}
	
	public void setOwner(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.Owner = dr.readWord();
		
		int bitTag = CodedTokenType.TYPEMAP.get("TypeOrMethodDef").get("TagBits");
		int maxMask = (int) (Math.pow(2, bitTag) - 1);  // this is important
		int index = this.Owner & maxMask;
		int tableIndex = this.Owner >> bitTag;
		
		Iterator<Entry<String, Integer>> iter = CodedTokenType.TYPEMAP.get("TypeOrMethodDef").entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Integer> entry = (Entry<String, Integer>) iter.next();
			String tableName = entry.getKey();
			int bitFlag = entry.getValue();
			if (bitFlag==index && tableName!="TagBits") {
				this.ownerRefTableName = tableName;
				break;
			}	
		}
		
		int recordNum = tablesHeader.getRecordOfEachTable().get(this.ownerRefTableName);
		if (recordNum > (0xffff - maxMask)) {
			int extra = dr.readWord();
			this.ownerRefTableIndex = (extra << (8-bitTag)) + tableIndex;
			this.Owner += (extra << 8);
		}
		else {
			this.ownerRefTableIndex = tableIndex;
		}
	}
	
	public int getName() {
		return this.Name;
	}
	
	public void setName(int n) {
		this.Name = n;
	}
}
