package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * AssemblyRefProcessor; RID Type: 36; Token Type: None;Unused
 */
public class AssemblyRefProcessor implements IMetaDataTables {
	private String TableTag = "AssemblyRefProcessor";
			
	private int Processor;     //ULONG
	private int AssemblyRef;   //RID: AssemblyRef
	
	private String refTableName;
	private int refTableIndex;
	
	public String getTableTag() {
		return this.TableTag;
	}

	public AssemblyRefProcessor(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setProcessor(dr.readDoubleWord());
		if (tablesHeader.getRecordOfEachTable().get("AssemblyRef") > 0xFFFF) {
			this.setAssemblyRef(dr.readDoubleWord());
		}
		else {
			this.setAssemblyRef(dr.readWord());
		}
	}
	
	public int getProcessor() {
		return this.Processor;
	}
	
	public void setProcessor(int processor) {
		this.Processor = processor;
	}
	
	public int getAssemblyRef() {
		return this.AssemblyRef;
	}
	
	public String getRefTableName() {
		return this.refTableName;
	}
	
	public int getRefTableIndex() {
		return this.refTableIndex;
	}
	
	public void setAssemblyRef(int assemblyRef) {
		this.AssemblyRef = assemblyRef;
		this.refTableName = "AssemblyRef";
		this.refTableIndex = assemblyRef;
	}
}
