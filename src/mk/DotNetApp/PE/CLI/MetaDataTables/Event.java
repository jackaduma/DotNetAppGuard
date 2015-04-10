package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.CLI.MetaDataTables.constant.CodedTokenType;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * Event; RID Type: 20; Token Type: 0x14000000; MD Streams: #~, #-
 */
public class Event implements IMetaDataTables {
	private String TableTag = "Event";
	
	private int EventFlags;   //USHORT   ====0x0000, 0x0200 or 0x0600
	private int Name;    //STRING
	private int EventType;  //TypeDefOrRef
	
	private String refTableName;
	private int refTableIndex;
	
	public String getTableTag() {
		return this.TableTag;
	}
	
	public Event(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setEventFlags(dr.readWord());
		
		if (4==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readWord());
		}
		
		this.setEventType(dr, tablesHeader);
	}
	
	public int getEventFlags() {
		return this.EventFlags;
	}
	
	public int getName() {
		return this.Name;
	}
	
	public int getEventType() {
		return this.EventType;
	}
	
	public String getRefTableName() {
		return this.refTableName;
	}
	
	public int getRefTableIndex() {
		return this.refTableIndex;
	}
	
	public void setEventFlags(int eventFlags) {
		this.EventFlags = eventFlags;
	}
	
	public void setName(int name) {
		this.Name = name;
	}
	
	public void setEventType(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.EventType = dr.readWord();
		
		int bitTag = CodedTokenType.TYPEMAP.get("TypeDefOrRef").get("TagBits");
		int maxMask = (int) (Math.pow(2, bitTag) - 1);  // this is important
		int index = this.EventType & maxMask;
		int tableIndex = this.EventType >> bitTag;
		
		Iterator<Entry<String, Integer>> iter = CodedTokenType.TYPEMAP.get("TypeDefOrRef").entrySet().iterator();
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
			this.EventType += (extra << 8);
		}
		else {
			this.refTableIndex = tableIndex;
		}
	}
}
