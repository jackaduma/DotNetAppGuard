package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * MethodPtr; RID Type: 05; Token Type: None; MD Stream: #-
 */
public class MethodPtr implements IMetaDataTables {
	private static final String TAG = "MethodPtr Table";
	
	private int Method;   //RID: Method
	
	private String refTableName;
	private int refTableIndex;
	
	public MethodPtr(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		if (tablesHeader.getRecordOfEachTable().get("Method")>0xFFFF) {
			this.setMethod(dr.readDoubleWord());
		}
		else {
			this.setMethod(dr.readWord());
		}
	}
	
	public int getMethod() {
		return this.Method;
	}
	
	public String getRefTableName() {
		return this.refTableName;
	}
	
	public int getRefTableIndex() {
		return this.refTableIndex;
	}
	
	public void setMethod(int method) {
		this.Method = method;
		this.refTableName = "Method";
		this.refTableIndex = method;
	}

}
