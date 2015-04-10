package mk.DotNetApp.PE.CLI.Heaps;

import java.io.IOException;

/**
 * @author kun_ma
 * A US heap containing user-defined strings. 
 * This stream contains string constants defined in the user code. 
 * The strings are kept in Unicode (UTF-16) encoding, with an additional trailing byte set to 1 or 0, 
 * indicating whether there are any characters with codes greater than 0x007F in the string. 
 * This trailing byte was added to streamline the encoding conversion operations on string objects produced from user-defined string constants. 
 * This stream¡¯s most interesting characteristic is that the user strings are never referenced from any metadata table 
 * but can be explicitly addressed by the IL code (with the ldstr instruction). 
 * In addition, being actually a blob heap, the #US heap can store not only Unicode strings but any binary object, 
 * which opens some intriguing possibilities.
 */
public class USHeap {	
	private static final String TAG = "US Heap";  //#US Stream

	private byte[] data;
	private int wideOfIndex = 2;
	
    private int position;
    private int offset;
		
	public USHeap(byte[] data) {
		this.data = data;
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
	
    public int readByte() throws IOException {
        if (offset + position >= data.length)
            return -1;
        return (char) (data[offset + position++] & 0xff);
    }
	
    public int readWord() throws IOException {
        return readByte() | readByte() << 8;
    }
	
	public String getUnicodeString(int index) throws IOException {
		this.offset = index;
		
		StringBuilder sb = new StringBuilder();
        char c = 0;
        while ((c = (char) readWord()) != 0) {
            sb.append(c);
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
	}
	
	public String getUnicodeString(int index, int size) throws IOException {
		this.offset = index;
		
		StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append((char) readWord());
        }
        return sb.toString();
	}
}
