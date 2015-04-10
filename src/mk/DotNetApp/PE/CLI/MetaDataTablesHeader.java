package mk.DotNetApp.PE.CLI;

import java.util.ArrayList;
import java.util.LinkedHashMap ;
import java.util.List;
import java.util.Map;

import mk.DotNetApp.PE.CLI.Heaps.BlobHeap;
import mk.DotNetApp.PE.CLI.Heaps.GUIDHeap;
import mk.DotNetApp.PE.CLI.Heaps.StringsHeap;
import mk.DotNetApp.PE.CLI.Heaps.USHeap;
import mk.DotNetApp.PE.CLI.MetaDataTables.IMetaDataTables;
import mk.DotNetApp.PE.CLI.MetaDataTables.MetaDataTablesMap;


/**
 * @author kun_ma
 *
 */
public class MetaDataTablesHeader {
	
	private static final String TAG = "MetaData Tables Header";
	
	private int Reserved1;  //DWORD, always be 0
	private int MajorVersion;   //Byte
	private int MinorVersion;   //Byte
	private int HeapOffsetSizes;  //Byte
	private int Reserved2;   //Byte, always be 1
	private long MaskValid;   //QWORD
	private long MaskSorted;  //QWORD
	
	private Map<String, Integer> recordOfEachTable = new LinkedHashMap<String, Integer>(); // an array of DWORD
	private Map<String, Integer> wideOfIndexToTable = new LinkedHashMap<String, Integer>();  // this is for read data of table data
	
	private int wideOfStringStreamIndex = 2; // 2 bytes = 16 bits
	private int wideOfGUIDStreamIndex = 2;
	private int wideOfBlobStreamIndex = 2;
	
	private List<String> validTables = new ArrayList<String>();
	private List<String> sortedTables = new ArrayList<String>();
	private int numOfTables=0;
	
	private BlobHeap blobHeap;
	private GUIDHeap guidHeap;
	private StringsHeap stringsHeap;
	private USHeap usHeap;
	
	private int sectionRVA;
	private int sectionPointerToRawData;
	
	private Map<String, List<IMetaDataTables>> tablesData;
	
	public Map<String, List<IMetaDataTables>> getTablesData() {
		return this.tablesData;
	}
	
	public void setTablesData(Map<String, List<IMetaDataTables>> data) {
		this.tablesData = data;
	}
	
	public int getSectionRVA() {
		return this.sectionRVA;
	}
	
	public int getSectionPointerToRawData() {
		return this.sectionPointerToRawData;
	}
	
	public void setSectionRVA(int sectionRVA) {
		this.sectionRVA = sectionRVA;
	}
	
	public void setSectionPointerToRawData(int sectionPointerToRawData) {
		this.sectionPointerToRawData = sectionPointerToRawData;
	}
	
	public BlobHeap getBlobHeap() {
		return this.blobHeap;
	}
	
	public GUIDHeap getGUIDHeap() {
		return this.guidHeap;
	}
	
	public StringsHeap getStringsHeap() {
		return this.stringsHeap;
	}
	
	public USHeap getUSHeap() {
		return this.usHeap;
	}
	
	public void setBlobHeap(BlobHeap b) {
		this.blobHeap = b;
	}
	
	public void setGUIDHeap(GUIDHeap g) {
		this.guidHeap = g;
	}
	
	public void setStringsHeap(StringsHeap s) {
		this.stringsHeap = s;
	}
	
	public void setUSHeap(USHeap u) {
		this.usHeap = u;
	}
	
	public int getReserved1() {
		return this.Reserved1;
	}
	
	public int getMajorVersion() {
		return this.MajorVersion;
	}
	
	public int getMinorVersion() {
		return this.MinorVersion;
	}
	
	public int getHeapOffsetSizes() {
		return this.HeapOffsetSizes;
	}
	
	public int getReserved2() {
		return this.Reserved2;
	}
	
	public long getMaskValid() {
		return this.MaskValid;
	}
	
	public long getMaskSorted() {
		return this.MaskSorted;
	}
	
	public int getWideOfStringStreamIndex() {
		return this.wideOfStringStreamIndex;
	}
	
	public int getWideOfGUIDStreamIndex() {
		return this.wideOfGUIDStreamIndex;
	}
	
	public int getWideOfBlobStreamIndex() {
		return this.wideOfBlobStreamIndex;
	}
	
	public List<String> getValidTables() {
		return this.validTables;
	}
	
	public List<String> getSortedTables() {
		return this.sortedTables;
	}
	
	public Map<String, Integer> getRecordOfEachTable() {
		return this.recordOfEachTable;
	}
	
	public Map<String, Integer> getWideOfIndexToTable() {
		return this.wideOfIndexToTable;
	}
	
	public int getNumOfTables() {
		return this.numOfTables;
	}
	
	public void setReserved1(int reserved) {
		this.Reserved1 = reserved;
	}
	
	public void setMajorVersion(int majorVersion) {
		this.MajorVersion = majorVersion;
	}
	
	public void setMinorVersion(int minorVersion) {
		this.MinorVersion = minorVersion;
	}
	
	public void setHeapOffsetSizes(int heapOffsetSizes) {
		this.HeapOffsetSizes = heapOffsetSizes;
		
		if ((heapOffsetSizes & 0x01) == 0x01) {
			this.wideOfStringStreamIndex = 4;  // 4 bytes = 32 bits
		}
		
		if ((heapOffsetSizes & 0x02) == 0x02) {
			this.wideOfGUIDStreamIndex = 4;
		}
		
		if ((heapOffsetSizes & 0x04) == 0x04) {
			this.wideOfBlobStreamIndex = 4;
		}
	}
	
	public void setReserved2(int reserved) {
		this.Reserved2 = reserved;
	}
	
	public void setMaskValid(long maskValid) {
		this.MaskValid = maskValid;
	}
	
	public void setMaskSorted(long maskSorted) {
		this.MaskSorted = maskSorted;
	}
	
	public void setValidTables() {
		this.setValidTables(this.MaskValid);
	}
	
	public void setValidTables(long maskValid) {
//		System.out.println("************Valid Tables List*************");
		for (int i=0; i<MetaDataTablesMap.TableMap.size(); i++) {
			long flag = (long)Math.pow((double)2, (double)i);
			if ((maskValid & flag) == flag) {
//				System.out.println(String.format("%s: %s", i, MetaDataTablesMap.TableMap.get(i)));
				this.validTables.add(MetaDataTablesMap.TableMap.get(i));
				this.numOfTables += 1;
			}
		}		
	}
	
	public void setSortedTables() {
		this.setSortedTables(this.MaskSorted);
	}
	
	public void setSortedTables(long maskSorted) {
//		System.out.println("****************Sorted Tables List******************");
		for (int i=0; i<MetaDataTablesMap.TableMap.size(); i++) {
			long flag = (long)Math.pow((double)2, (double)i);
			if ((maskSorted & flag) == flag) {
//				System.out.println(String.format("%s: %s", i, MetaDataTablesMap.TableMap.get(i)));
				this.sortedTables.add(MetaDataTablesMap.TableMap.get(i));
			}
		}
	}
	
	public void setRecordOfEachTable(Map<String, Integer> recordOfEachTable) {
		this.recordOfEachTable = recordOfEachTable;
	}
	
	public void setWideOfIndexToTable(Map<String, Integer> wideOfIndexToTable) {
		this.wideOfIndexToTable = wideOfIndexToTable;
	}
	
	public void setNumOfTables(int num) {
		this.numOfTables = num;
	}

}
