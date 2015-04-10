package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * FieldPtr; RID Type: 03; Token Type: None; MD Stream: #-
 */
public class FieldPtr implements IMetaDataTables {
	private static final String TAG = "FieldPtr Table";
	
	private int Field; // RID: Field
	
	private String refTableName;
	private int refTableIndex;
	
	public FieldPtr(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		if (tablesHeader.getRecordOfEachTable().get("Field")>0xFFFF) {
			setField(dr.readDoubleWord());
		}
		else {
			setField(dr.readWord());
		}
	}
	
	public int getField() {
		return this.Field;
	}
	
	public String getRefTableName() {
		return this.refTableName;
	}
	
	public int getRefTableIndex() {
		return this.refTableIndex;
	}
	
	public void setField(int field) {
		this.Field = field;
		this.refTableName = "Field";
		this.refTableIndex = field;
	}
	
}
