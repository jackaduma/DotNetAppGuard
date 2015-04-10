package mk.DotNetApp.decompiler;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author makun
 *
 */
public class PEReader {
	
	private static final String TAG = "PEReader";
	
	private String peFilePath = null;

	
	/*
	 * typedef struct _IMAGE_NT_HEADERS {
	 * 		DWORD Signature;
	 * 		......
	 * 
	 * }
	 */
	
	public PEReader() {
		
	}
	
	public PEReader(String peFilePath) throws FileNotFoundException {
		this.peFilePath = peFilePath;
		
		// init byte array of reader
	}
	
	/*
	 * MS-DOS Stub, include MS-DOS MZ Header and Real-Code Stub Program
	 * return 
	 */
	private int parseDOSStub() throws IOException {
		FileInputStream fis = new FileInputStream(new File(peFilePath));
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		int offset = 0;
		byte[] b = new byte[64];
		int count = bis.read(b);
		System.out.println(String.format("%02x%02x", b[0], b[1]));
		System.out.println(Integer.parseInt("5a4d", 16));
		System.out.println(String.format("%02x%02x%02x%02x", b[60], b[61], b[62], b[63]));
		
		bis.read(b);
		bis.read(b);
		System.out.println(String.format("%02x%02x", b[0], b[1]));

		bis.close();
		fis.close();
		
		System.out.println(String.format("%s%s", TAG, "Read End!"));
		return offset;
	}
	
	private void parsePEHeader() {
		
	}
	
	public static void main(String[] args) {
		String peFilePath = "E:\\Projects\\DotNetAppGuard\\Win8Metro\\GoEverywhere_v1_2089\\SecurityPack.exe";
		try {
			PEReader peReader = new PEReader(peFilePath);
			peReader.parseDOSStub();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
