package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 *  EventMap; RID Type: 18; Token Type: None; MD Streams: #~, #-
 */
public class EventMap implements IMetaDataTables {
	private String TableTag = "EventMap";
	
	private int Parent;      //RID: TypeDef
	private int EventList;   //RID: Event
	
	private String parentRefTableName;
	private int parentRefTableIndex;
	
	private String eventlistRefTableName;
	private int eventlistRefTableIndex;
	
	public String getTableTag() {
		return this.TableTag;
	}
	
	public EventMap(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		if (tablesHeader.getRecordOfEachTable().get("TypeDef") > 0xFFFF) {
			this.setParent(dr.readDoubleWord());
		}
		else {
			this.setParent(dr.readWord());
		}
		
		if (tablesHeader.getRecordOfEachTable().get("Event") > 0xFFFF) {
			this.setEventList(dr.readDoubleWord());
		}
		else {
			this.setEventList(dr.readWord());
		}
	}
	
	public int getParent() {
		return this.Parent;
	}
	
	public int getEventList() {
		return this.EventList;
	}
	
	public String getParentRefTableName() {
		return this.parentRefTableName;
	}
	
	public int getParentRefTableIndex() {
		return this.parentRefTableIndex;
	}
	
	public String getEventListRefTableName() {
		return this.eventlistRefTableName;
	}
	
	public int getEventListRefTableIndex() {
		return this.eventlistRefTableIndex;
	}
	
	public void setParent(int parent) {
		this.Parent = parent;
		this.parentRefTableName = "TypeDef";
		this.parentRefTableIndex = parent;
	}
	
	public void setEventList(int eventList) {
		this.EventList = eventList;
		this.eventlistRefTableName = "Event";
		this.eventlistRefTableIndex = eventList;
	}
}
