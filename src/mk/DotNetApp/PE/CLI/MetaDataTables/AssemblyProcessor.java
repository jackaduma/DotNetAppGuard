package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * AssemblyProcessor; RID Type: 33; Token Type: None;Unused
 */
public class AssemblyProcessor implements IMetaDataTables {
	private String TableTag = "AssemblyProcessor";
	
	private int Processor;    //ULONG
	
	public String getTableTag() {
		return this.TableTag;
	}
	
	public AssemblyProcessor(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setProcessor(dr.readDoubleWord());
	}
	
	public int getProcessor() {
		return this.Processor;
	}
	
	public void setProcessor(int processor) {
		this.Processor = processor;
	}
}
