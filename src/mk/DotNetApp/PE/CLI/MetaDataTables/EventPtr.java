package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * EventPtr; RID Type: 19; Token Type: None; MD Stream: #-s
 */
public class EventPtr implements IMetaDataTables {
	private String TableTag = "EventPtr";
	
	private int Event;   //RID: Event
	
	private String refTableName;
	private int refTableIndex;
	
	public String getTableTag() {
		return this.TableTag;
	}
	
	public EventPtr(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		if (tablesHeader.getRecordOfEachTable().get("Event") > 0xFFFF) {
			this.setEvent(dr.readDoubleWord());
		}
		else {
			this.setEvent(dr.readWord());
		}
	}
	
	public int getEvent() {
		return this.Event;
	}
	
	public String getRefTableName() {
		return this.refTableName;
	}
	
	public int getRefTableIndex() {
		return this.refTableIndex;
	}
	
	public void setEvent(int event) {
		this.Event = event;
		this.refTableName = "Event";
		this.refTableIndex = event;
	}
}
