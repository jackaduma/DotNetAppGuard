package mk.DotNetApp.PE.CLI.Heaps;

/**
 * @author makun
 * This kind of heap contains 16-byte binary objects, immediately following each other. 
 * The size of the binary objects is fixed, so length parameters or terminators are not needed.
 */
public class GUIDHeap {
	private static final String TAG = "GUID Heap";  //#GUID Stream

	private byte[] data;
	private int wideOfIndex = 2;
	
	public GUIDHeap(byte[] gUIDStreamData) {
		this.data = gUIDStreamData;
	}
	
	public byte[] getData() {
		return this.data;
	}
	
	public int getWideOfIndex() {
		return this.wideOfIndex;
	}
	
	public void setData(byte[] data) {
		this.data = data;
	}
	
	public void setWideOfIndex(int wide) {
		this.wideOfIndex = wide;
	}

}
