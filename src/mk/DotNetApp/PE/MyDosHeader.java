package mk.DotNetApp.PE;

/**
 * @author makun
 *
 */
public class MyDosHeader {
	private static final String TAG = "DosHeader";
	
	private String dosMagicString = "MZ";
	private int offsetOfPEHeader = 0;
	private boolean dosFlag = false;
	
	public String getDosMagicString() {
		return this.dosMagicString;
	}
	
	public int getOffsetOfPEHeader() {
		return this.offsetOfPEHeader;
	}
	
	public boolean getDosFlag() {
		return this.dosFlag;
	}
	
	public void setDosMagicString(String data) {
		this.dosMagicString = data;
	}
	
	public void setOffsetOfPEHeader(int data) {
		this.offsetOfPEHeader = data;
	}
	
	public void setDosFlag() {
		if (this.dosMagicString.equals("MZ") && 0==this.offsetOfPEHeader)
			this.dosFlag = true;
	}
}
