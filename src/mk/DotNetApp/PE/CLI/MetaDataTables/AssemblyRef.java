package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 *  AssemblyRef; RID Type: 35; Token Type: 0x23000000; MD Streams: #~, #-
 */
public class AssemblyRef implements IMetaDataTables {
	private String TableTag = "AssemblyRef";
	
	private int MajorVersion;    //USHORT
	private int MinorVersion;    //USHORT
	private int BuildNumber;     //USHORT
	private int RevisionNumber;   //USHORT
	private int Flags;        //ULONG 0x00000000 or 0x00000001
	private int PublicKeyOrToken;    //BLOB
	private int Name;   //STRING   No path, no extension
	private int Locale;   //STRING
	private int HashValue;   //BLOB
	
	private String assemblyName;
	
	public String getAssemblyName() {
		return this.assemblyName;
	}
	
	public String getTableTag() {
		return this.TableTag;
	}
	
	public AssemblyRef(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setMajorVersion(dr.readWord());
		this.setMajorVersion(dr.readWord());
		this.setBuildNumber(dr.readWord());
		this.setRevisionNumber(dr.readWord());
		this.setFlags(dr.readDoubleWord());
		
		if (4==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setPublicKeyOrToken(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setPublicKeyOrToken(dr.readWord());
		}
		
		if (4==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readDoubleWord());
			this.setLocale(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readWord());
			this.setLocale(dr.readWord());
		}
		
		if (4==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setHashValue(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setHashValue(dr.readWord());
		}
		
		this.assemblyName = tablesHeader.getStringsHeap().getUTF8String(this.Name);
	}
	
	public int getMajorVersion() {
		return this.MajorVersion;
	}
	
	public void setMajorVersion(int major) {
		this.MajorVersion = major;
	}
	
	public int getMinorVersion() {
		return this.MinorVersion;
	}
	
	public void setMinorVersion(int minor) {
		this.MinorVersion = minor;
	}
	
	public int getBuildNumber() {
		return this.BuildNumber;
	}
	
	public void setBuildNumber(int build) {
		this.BuildNumber = build;
	}
	
	public int getRevisionNumber() {
		return this.RevisionNumber;
	}
	
	public void setRevisionNumber(int revision) {
		this.RevisionNumber = revision;
	}
	
	public int getFlags() {
		return this.Flags;
	}
	
	public void setFlags(int flags) {
		this.Flags = flags;
	}
	
	public int getPublicKeyOrToken() {
		return this.PublicKeyOrToken;
	}
	
	public void setPublicKeyOrToken(int key) {
		this.PublicKeyOrToken = key;
	}
	
	public int getName() {
		return this.Name;
	}
	
	public void setName(int name) {
		this.Name = name;
	}
	
	public int getLocale() {
		return this.Locale;
	}
	
	public void setLocale(int locale) {
		this.Locale = locale;
	}
	
	public int getHashValue() {
		return this.HashValue;
	}
	
	public void setHashValue(int value) {
		this.HashValue = value;
	}
	
}
