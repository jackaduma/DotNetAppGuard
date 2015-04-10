package mk.DotNetApp.PE.CLI;

public class StreamHeader {
	
	private static final String TAG = "MetaData Stream Header";
	
	private long offset;
	private long streamSize;
	private String name;
	
	private int streamDataPointerToRawData;
	private long headerSize;  //±£Áô×Ö¶Î
	
	public long getOffset() {
		return this.offset;
	}
	
	public long getStreamSize() {
		return this.streamSize;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getStreamDataPointerToRawData() {
		return this.streamDataPointerToRawData;
	}
	
	public void setOffset(long offset) {
		this.offset = offset;
	}
	
	public void setStreamSize(long size) {
		this.streamSize = size;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setStreamDataPointerToRawData(int streamDataPointerToRawData) {
		this.streamDataPointerToRawData = streamDataPointerToRawData;
	}
}
