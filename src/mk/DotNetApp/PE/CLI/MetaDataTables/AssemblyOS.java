package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 *  AssemblyOS; RID Type: 34; Token Type: None;Unused
 */
public class AssemblyOS implements IMetaDataTables {
	private String TableTag = "AssemblyOS";
	
	private int OSPlatformID;    //ULONG
	private int OSMajorVersion;   //ULONG
	private int OSMinorVersion;   //ULONG
	
	public String getTableTag() {
		return this.TableTag;
	}
	
	public AssemblyOS(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setOSPlatformID(dr.readDoubleWord());
		this.setOSMajorVersion(dr.readDoubleWord());
		this.setOSMinorVersion(dr.readDoubleWord());
	}
	
	public int getOSPlatformID() {
		return this.OSPlatformID;
	}
	
	public void setOSPlatformID(int platform) {
		this.OSPlatformID = platform;
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

}
