package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 *  PropertyMap; RID Type: 21; Token Type: None; MD Streams: #~, #-
 */
public class PropertyMap implements IMetaDataTables {
	private static final String TAG = "PropertyMap Table";
	
	private int Parent;        //RID: TypeDef
	private int PropertyList;  //RID: Property
	
	private String parentRefTableName;
	private int parentRefTableIndex;
	
	private String propertylistRefTableName;
	private int propertylistRefTableIndex;
	
	public PropertyMap(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		if (tablesHeader.getRecordOfEachTable().get("TypeDef") > 0xFFFF) {
			this.setParent(dr.readDoubleWord());
		}
		else {
			this.setParent(dr.readWord());
		}
		
		if (tablesHeader.getRecordOfEachTable().get("Property") > 0xFFFF) {
			this.setPropertyList(dr.readDoubleWord());
		}
		else {
			this.setPropertyList(dr.readWord());
		}
	}
	
	public int getParent() {
		return this.Parent;
	}
	
	public int getPropertyList() {
		return this.PropertyList;
	}
	
	public String getParentRefTableName() {
		return this.parentRefTableName;
	}
	
	public int getParentRefTableIndex() {
		return this.parentRefTableIndex;
	}
	
	public String getPropertyListRefTableName() {
		return this.propertylistRefTableName;
	}
	
	public int getPropertyListRefTableIndex() {
		return this.propertylistRefTableIndex;
	}
	
	public void setParent(int parent) {
		this.Parent = parent;
		this.parentRefTableName = "TypeDef";
		this.parentRefTableIndex = parent;
	}
	
	public void setPropertyList(int propertyList) {
		this.PropertyList = propertyList;
		this.propertylistRefTableName = "Property";
		this.propertylistRefTableIndex = propertyList;
	}
}
