package mk.DotNetApp.PE.CLI;

import java.util.List;
import java.util.Map;

import mk.DotNetApp.PE.CLI.MetaDataTables.IMetaDataTables;

/**
 * @author kun_ma
 *
 */
public class MetaDataHeader {
	private static final String TAG = "MetaData Header";
	
	private int magic = 0x424A5342;    //ASCII code of string "BSJB"
	
	private long Signature;
	private int MajorVersion;
	private int MinorVersion;
	private long Reserved;
	private long VersionLength;
	private String VersionString;  // un sure length
	private int Flags;
	private int NumberOfStreams;
	private StreamHeader[] streamsHeaders;
	
	private int pointerToRawData;
	private int metaHeaderSize;  // ±£Áô×Ö¶Î
	
	private boolean existMetaDataTablesStream = false;
	private boolean existUnOptimizedStream = false;
	private boolean existStringStream = false;
	private boolean existUSStream = false;
	private boolean existGUIDStream = false;
	private boolean existBlobStream = false;
	
	
	public int getPointerToRawData() {
		return this.pointerToRawData;
	}
	
	public void setPointerToRawData(int pointerToRawData) {
		this.pointerToRawData = pointerToRawData;
	}
	
	public long getSignature() {
		return this.Signature;
	}
	
	public int getMajorVersion() {
		return this.MajorVersion;
	}
	
	public int getMinorVersion() {
		return this.MinorVersion;
	}
	
	public long getReserved() {
		return this.Reserved;
	}
	
	public long getVersionLength() {
		return this.VersionLength;
	}
	
	public String getVersionString() {
		return this.VersionString;
	}
	
	public int getFlags() {
		return this.Flags;
	}
	
	public int getNumberOfStreams() {
		return this.NumberOfStreams;
	}
	
	public void setSignature(long signature) {
		this.Signature = signature;
	}
	
	public void setMajorVersion(int majorVersion) {
		this.MajorVersion = majorVersion;
	}
	
	public void setMinorVersion(int minorVersion) {
		this.MinorVersion = minorVersion;
	}
	
	public void setReserved(long reserved) {
		this.Reserved = reserved;
	}
	
	public void setVersionLength(long versionLength) {
		this.VersionLength = versionLength;
	}
	
	public void setVersionString(String versionString) {
		this.VersionString = versionString;
	}
	
	public void setFlags(int flags) {
		this.Flags = flags;
	}
	
	public void setNumberOfStreams(int numberOfStreams) {
		this.NumberOfStreams = numberOfStreams;
	}
	
	public StreamHeader[] getStreamHeaders() {
		return this.streamsHeaders;
	}
	
	public StreamHeader getStreamHeader(int index) {
		return this.streamsHeaders[index];
	}
	
	public StreamHeader getStreamHeader(String name) {
		for (int i=0; i<this.NumberOfStreams; i++) {
			StreamHeader streamHeader = streamsHeaders[i];
			if (name.equals(streamHeader.getName())){
				return streamHeader;
			}
		}
		return null;
	}
	
	public void setStreamHeaders(StreamHeader[] streamsHeaders) {
		this.streamsHeaders = streamsHeaders;
	}
	
	public boolean ExistMetaDataTablesStream() {
		return this.existMetaDataTablesStream;
	}
	
	public boolean ExistUnOptimizedStream() {
		return this.existUnOptimizedStream;
	}
	
	public boolean ExistStringStream() {
		return this.existStringStream;
	}
	
	public boolean ExistUSStream() {
		return this.existUSStream;
	}
	
	public boolean ExistGUIDStream() {
		return this.existGUIDStream;
	}
	
 	public boolean ExistBlobStream() {
 		return this.existBlobStream;
 	}
 	
 	public void setExistMetaDataTablesStream(){
 		this.existMetaDataTablesStream = true;
 	}
 	
 	public void setExistUnOptimizedStream() {
 		this.existUnOptimizedStream = true;
 	}
 	
 	public void setExistStringStream() {
 		this.existStringStream = true;
 	}
 	
 	public void setExistUSStream() {
 		this.existUSStream = true;
 	}
 	
 	public void setExistGUIDStream() {
 		this.existGUIDStream = true;
 	}
 	
 	public void setExistBlobStream() {
 		this.existBlobStream = true;
 	}
}
