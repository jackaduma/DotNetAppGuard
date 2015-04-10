package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 *  Assembly; RID Type: 32; Token Type: 0x20000000; MD Streams: #~, #-
 */
public class Assembly implements IMetaDataTables {
	private String TableTag = "Assembly";
	
	private int HashAlgId;   //ULONG
	private int MajorVersion;   //USHORT
	private int MinorVersion;   //USHORT
	private int BuildNumber;    //USHORT
	private int RevisionNumber;   //USHORT
	private int Flags;    //ULONG            Validity mask: 0x0000C031
	private int PublicKey;    //BLOB
	private int Name;     //STRING No path, no extension
	private int Locale;    //STRING
	
	private String assemblyName;
	private String assemblyLocale;
	
	public String getTableTag() {
		return this.TableTag;
	}
	
	public Assembly(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setHashAlgId(dr.readDoubleWord());
		this.setMajorVersion(dr.readWord());
		this.setMinorVersion(dr.readWord());
		this.setBuildNumber(dr.readWord());
		this.setRevisionNumber(dr.readWord());		
		this.setFlags(dr.readDoubleWord());
		
		if (4==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setPublicKey(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setPublicKey(dr.readWord());
		}
		
		if (4==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readDoubleWord());
			this.setLocale(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readWord());
			this.setLocale(dr.readWord());
		}
		
		this.assemblyName = tablesHeader.getStringsHeap().getUTF8String(this.Name);
		this.assemblyLocale = tablesHeader.getStringsHeap().getUTF8String(this.Locale);
	}
	
	public String getAssemblyName() {
		return this.assemblyName;
	}
	
	public String getAssemblyLocale() {
		return this.assemblyLocale;
	}
	
	public int getHashAlgId() {
		return this.HashAlgId;
	}
	
	public void setHashAlgId(int hashAlgId) {
		this.HashAlgId = hashAlgId;
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
	
	public int getPublicKey() {
		return this.PublicKey;
	}
	
	public void setPublicKey(int key) {
		this.PublicKey = key;
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
}
