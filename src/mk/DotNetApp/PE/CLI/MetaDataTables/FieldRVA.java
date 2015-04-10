package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 *  FieldRVA; RID Type: 31; Token Type: None; MD Streams: #~, #-
 */
public class FieldRVA implements IMetaDataTables {
	private static final String TAG = "FieldRVA Table";
	
	private int RVA;   //ULONG
	private int Field;   //RID: Field
	
	private String refTableName;
	private int refTableIndex;
	
	public FieldRVA(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setRVA(dr.readDoubleWord());
		
		if (tablesHeader.getRecordOfEachTable().get("Field") > 0xFFFF) {
			this.setField(dr.readDoubleWord());
		}
		else {
			this.setField(dr.readWord());
		}
	}
	
	public int getRVA() {
		return this.RVA;
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
	
	public void setRVA(int rva) {
		this.RVA = rva;
	}
	
	public void setField(int field) {
		this.Field = field;
		this.refTableName = "Field";
		this.refTableIndex = field;
	}

}
