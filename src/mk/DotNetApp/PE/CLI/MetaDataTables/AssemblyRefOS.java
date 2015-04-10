package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 *  AssemblyRefOS; RID Type: 37; Token Type: None;Unused
 */
public class AssemblyRefOS implements IMetaDataTables {
	private String TableTag = "AssemblyRefOS";
	
	private int OSPlatformId;   //ULONG
	private int OSMajorVersion;   //ULONG
	private int OSMinorVersion;    //ULONG
	private int AssemblyRef;    //RID: AssemblyRef
	
	private String refTableName;
	private int refTableIndex;
	
	public String getTableTag() {
		return this.TableTag;
	}
	
	public AssemblyRefOS(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setOSPlatformId(dr.readDoubleWord());
		this.setOSMajorVersion(dr.readDoubleWord());
		this.setOSMinorVersion(dr.readDoubleWord());
		
		if (tablesHeader.getRecordOfEachTable().get("AssemblyRef") > 0xFFFF) {
			this.setAssemblyRef(dr.readDoubleWord());
		}
		else {
			this.setAssemblyRef(dr.readWord());
		}
	}
	
	public int getOSPlatformId() {
		return this.OSPlatformId;
	}
	
	public void setOSPlatformId(int id) {
		this.OSPlatformId = id;
	}
	
	public int getOSMajorVersion() {
		return this.OSMajorVersion;
	}
	
	public void setOSMajorVersion(int major) {
		this.OSMajorVersion = major;
	}
	
	public int getOSMinorVersion() {
		return this.OSMinorVersion;
	}
	
	public void setOSMinorVersion(int minor) {
		this.OSMinorVersion = minor;
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
	
	public void setAssemblyRef(int ref) {
		this.AssemblyRef = ref;
	}
}
