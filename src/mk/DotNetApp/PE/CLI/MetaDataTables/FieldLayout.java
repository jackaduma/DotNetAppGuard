package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 *  FieldLayout; RID Type: 16; Token Type: None; MD Streams: #~, #-
 */
public class FieldLayout implements IMetaDataTables {
	private static final String TAG = "FieldLayout Table";
	
	private int OffSet;   //ULONG     =====Offset in bytes or ordinal
	private int Field;     //RID: Field
	
	private String refTableName;
	private int refTableIndex;
	
	public FieldLayout(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setOffset(dr.readDoubleWord());
		
		if (tablesHeader.getRecordOfEachTable().get("Field") > 0xFFFF) {
			this.setField(dr.readDoubleWord());
		}
		else {
			this.setField(dr.readWord());
		}
		
	}
	
	public int getOffset() {
		return this.OffSet;
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
	
	public void setOffset(int offset) {
		this.OffSet = offset;
	}
	
	public void setField(int field) {
		this.Field = field;
		this.refTableName = "Field";
		this.refTableIndex = field;
	}

}
