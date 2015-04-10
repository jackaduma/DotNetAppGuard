package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.CLI.MetaDataTables.constant.CodedTokenType;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * InterfaceImpl; RID Type: 09; Token Type: 0x09000000; MD Streams: #~, #-
 */
public class InterfaceImpl implements IMetaDataTables {
	private static final String TAG = "InterfaceImpl Table";
	
	private int Class;   //RID: TypeDef    ====Class implementing the interface
	private int Interface;     //TypeDefOrRef     =====Implemented interface
	
	private String classRefTableName;
	private int classRefTableIndex;
	
	private String interfaceRefTableName;
	private int interfaceRefTableIndex;
	
	public InterfaceImpl(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		if (tablesHeader.getRecordOfEachTable().get("TypeDef")>0xFFFF) {
			this.setClass(dr.readDoubleWord());
		} else {
			this.setClass(dr.readWord());
		}
		
		this.setInterface(dr, tablesHeader);
	}
	
	public int getSelfClass() {
		return this.Class;
	}
	
	public int getInterface() {
		return this.Interface;
	}
	
	public String getClassRefTableName() {
		return this.classRefTableName;
	}
	
	public int getClassRefTableIndex() {
		return this.classRefTableIndex;
	}
	
	public String getInterfaceRefTableName() {
		return this.interfaceRefTableName;
	}
	
	public int getInterfaceRefTableIndex() {
		return this.interfaceRefTableIndex;
	}
	
	public void setClass(int Class) {
		this.Class = Class;
		this.classRefTableName = "TypeDef";
		this.classRefTableIndex = Class;
	}
	
	public void setInterface(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.Interface = dr.readWord();
		
		int bitTag = CodedTokenType.TYPEMAP.get("TypeDefOrRef").get("TagBits");
		int maxMask = (int) (Math.pow(2, bitTag) - 1);  // this is important
		int index = this.Interface & maxMask;
		int tableIndex = this.Interface >> bitTag;
		
		Iterator<Entry<String, Integer>> iter = CodedTokenType.TYPEMAP.get("TypeDefOrRef").entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Integer> entry = (Entry<String, Integer>) iter.next();
			String tableName = entry.getKey();
			int bitFlag = entry.getValue();
			if (bitFlag==index && tableName!="TagBits") {
				this.interfaceRefTableName = tableName;
				break;
			}	
		}
		
		int recordNum = tablesHeader.getRecordOfEachTable().get(this.interfaceRefTableName);
		if (recordNum > (0xffff - maxMask)) {
			int extra = dr.readWord();
			this.interfaceRefTableIndex = (extra << (8-bitTag)) + tableIndex;
			this.Interface += (extra << 8);
		}
		else {
			this.interfaceRefTableIndex = tableIndex;
		}
	}
}
