package mk.DotNetApp.PE.CLI.Heaps;

/**
 * @author makun
 * This kind of heap contains zero-terminated character strings,  encoded in UTF-8. The strings follow each other immediately.
 * The first byte of the heap is always 0, and as a result the first string in the heap is always an empty string.
 * The last byte of the heap must be 0 as well (in other words, the last string in the heap must be zero-terminated just like the others).
 */
public class StringsHeap {
	private static final String TAG = "Strings Heap";  // #Strings Stream
	
	private byte[] data;
	private int maxStringLength = 1024;
	private int wideOfIndex = 2;
	
	public StringsHeap(byte[] data) {
		this.data = data;
	}
	
	public String getUTF8String(int index) {
		StringBuilder sb = new StringBuilder();
        while (true) {
            char c = (char) (this.data[index++] & 0xff);
            if (c == 0) {
                break;
            }
            sb.append(c);
        }
        return sb.toString();
	}
	
	public int getMaxStringLength() {
		return this.maxStringLength;
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
