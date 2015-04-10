package mk.DotNetApp.decompiler;

/**
 * @author makun
 *
 */
public class PEDecompiler {
	
	private static final String TAG = "PEDecompiler";
	
	private String peFilePath = null;
	
	public PEDecompiler() {
		
	}
	
	public PEDecompiler(String peFilePath) {
		this.peFilePath = peFilePath;
	}
	
	/*
	 * input format:
	 * java -j *.jar    -o []  [xap file]
	 */
	public static void main(String[] args) {
		
	}
}
