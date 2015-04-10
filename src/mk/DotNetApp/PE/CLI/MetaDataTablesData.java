package mk.DotNetApp.PE.CLI;

import java.util.List;
import java.util.Map;

import mk.DotNetApp.PE.CLI.MetaDataTables.IMetaDataTables;


/**
 * @author kun_ma
 *
 */
public class MetaDataTablesData {
	private static final String TAG = "MetaData Tables Data";
	
	private Map<String, List<IMetaDataTables>> TablesData;	

	
	public Map<String, List<IMetaDataTables>> getTablesData() {
		return this.TablesData;
	}	
		
	public void setTablesData(Map<String, List<IMetaDataTables>> tablesData) {
		this.TablesData = tablesData;
	}	

}
