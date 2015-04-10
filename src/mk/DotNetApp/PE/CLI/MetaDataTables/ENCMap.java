package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * ENCMap; RID Type: 30; Token Type: None; MD Stream: #-
 */
public class ENCMap implements IMetaDataTables {
	private String TableTag = "ENCMap";
	
	private int Token;   //ULONG
	
	public String getTableTag() {
		return this.TableTag;
	}
	
	public ENCMap(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setToken(dr.readDoubleWord());
	}
	
	public int getToken() {
		return this.Token;
	}
	
	public void setToken(int token) {
		this.Token = token;
	}
}
