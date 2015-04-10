package mk.DotNetApp.PE.CLI.Heaps;

import java.util.HashMap;
import java.util.Map;

/**
 * @author makun
 * This kind of heap contains binary objects of arbitrary size. 
 * Each binary object is preceded by its length (in compressed form). 
 * Binary objects are aligned on 4-byte boundaries.
 */
public class BlobHeap {
	private static final String TAG = "Blob Heap";  //#Blob Stream

	private byte[] data;
	private int position;
	private int wideOfIndex = 2;	
	
	private static Map<Integer, Integer> lengthMap = new HashMap<Integer, Integer>();
	static {
		lengthMap.put(0, 1);   //  000
		lengthMap.put(2, 2);   //  010
		lengthMap.put(6, 4);   //  110
	}
	
	public BlobHeap(byte[] data) {
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
	
	public String getSignature(int index) {
		int length = (int)(this.data[index] & 0xff);
		StringBuilder signature = new StringBuilder();
		int[] sigs = new int[length];
		int cursor = 0;
		while (cursor<length) {
			sigs[cursor] = (int)(this.data[index+1+cursor] & 0xff);
			signature.append(String.format("%02X", sigs[cursor]));
			cursor += 1;
		}
		
		return signature.toString();
	}
	
	public char ReadByte() {
		return (char)(this.data[this.position++]&0xff);
	}
	
	public long ReadCompressedUInt32 (int index)  // unsigned int ==> long
	{
		this.position = index;
		char first = this.ReadByte ();  // unsigned byte ==> char
		if ((first & 0x80) == 0)
			return (long)(first);

		if ((first & 0x40) == 0)
			return ((long) (first & ~0x80) << 8)    // unsigned int ==> long
				| this.ReadByte ();

		return ((long) (first & ~0xc0) << 24)    // unsigned int ==> long
			| (long) ReadByte () << 16            // unsigned int ==> long
			| (long) ReadByte () << 8            // unsigned int ==> long
			| ReadByte ();
	}

	public int ReadCompressedInt32 (int index)
	{
		int value = (int) (ReadCompressedUInt32(index) >> 1);
		if ((value & 1) == 0)
			return value;
		if (value < 0x40)
			return value - 0x40;
		if (value < 0x2000)
			return value - 0x2000;
		if (value < 0x10000000)
			return value - 0x10000000;
		return value - 0x20000000;
	}
	
}
