package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * ParamPtr; RID Type: 07; Token Type: None; MD Stream: #-
 */
public class ParamPtr implements IMetaDataTables {
	private static final String TAG = "ParamPtr Table";
	
	private int Param;   // RID: Param
	
	private String refTableName;
	private int refTableIndex;
	
	public ParamPtr(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		if (tablesHeader.getRecordOfEachTable().get("Param")>0xFFFF) {
			this.setParam(dr.readDoubleWord());
		}
		else {
			this.setParam(dr.readWord());
		}
		
	}
	
	public int getParam() {
		return this.Param;
	}
	
	public String getRefTableName() {
		return this.refTableName;
	}
	
	public int getRefTableIndex() {
		return this.refTableIndex;
	}
	
	public void setParam(int param) {
		this.Param = param;
		this.refTableName = "Param";
		this.refTableIndex = param;
	}

}
