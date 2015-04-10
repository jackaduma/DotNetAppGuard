package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * ENCLog; RID Type: 28; Token Type: None; MD Stream: #-
 */
public class ENCLog implements IMetaDataTables {
	private String TableTag = "ENCLog";
	
	private int Token;  //ULONG
	private int FuncCode;   //ULONG
	
	public String getTableTag() {
		return this.TableTag;
	}
	
	public ENCLog(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setToken(dr.readDoubleWord());
		this.setFuncCode(dr.readDoubleWord());		
	}
	
	public int getToken() {
		return this.Token;
	}
	
	public int getFuncCode() {
		return this.FuncCode;
	}
	
	public void setToken(int token) {
		this.Token = token;
	}
	
	public void setFuncCode(int funcCode) {
		this.FuncCode = funcCode;
	}
}
