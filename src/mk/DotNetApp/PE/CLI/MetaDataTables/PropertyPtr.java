package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 *  PropertyPtr; RID Type: 22; Token Type: None; MD Stream: #-
 */
public class PropertyPtr implements IMetaDataTables {
	private static final String TAG = "PropertyPtr Table";
	
	private int Property;   //RID: Property
	
	private String refTableName;
	private int refTableIndex;
	
	public PropertyPtr(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		if (tablesHeader.getRecordOfEachTable().get("Property") > 0xFFFF) {
			this.setProperty(dr.readDoubleWord());
		}
		else {
			this.setProperty(dr.readWord());
		}
	}
	
	public int getProperty() {
		return this.Property;
	}
	
	public String getRefTableName() {
		return this.refTableName;
	}
	
	public int getRefTableIndex() {
		return this.refTableIndex;
	}
	
	public void setProperty(int property) {
		this.Property = property;
		this.refTableName = "Property";
		this.refTableIndex = property;
	}
}
