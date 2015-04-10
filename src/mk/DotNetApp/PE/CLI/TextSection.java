package mk.DotNetApp.PE.CLI;

import mk.DotNetApp.PE.CLI.Heaps.BlobHeap;
import mk.DotNetApp.PE.CLI.Heaps.GUIDHeap;
import mk.DotNetApp.PE.CLI.Heaps.StringsHeap;
import mk.DotNetApp.PE.CLI.Heaps.USHeap;

/**
 * @author kun_ma
 *
 */
public class TextSection {
	private static final String TAG = ".text section";
	
	private int pointerToRawData;
	private int sectionRVA;
	
	private CLRHeader clrHeader;
	private MetaDataHeader metaDataHeader;
	
	private MetaDataTablesHeader metaDataTablesHeader;	
	
	private BlobHeap blobHeap;
	private GUIDHeap guidHeap;
	private StringsHeap stringsHeap;
	private USHeap usHeap;
	
	private MetaDataTablesData metaDataTablesData;
	
	
	public int  getSectionRVA() {
		return this.sectionRVA;
	}
	
	public void setSectionRVA(int rva) {
		this.sectionRVA = rva;
	}
	
	public int getPointerToRawData() {
		return this.pointerToRawData;
	}
	
	public CLRHeader getCLRHeader() {
		return this.clrHeader;
	}
	
	public MetaDataHeader getMetaDataHeader() {
		return this.metaDataHeader;
	}
	
	public MetaDataTablesHeader getMetaDataTablesHeader() {
		return this.metaDataTablesHeader;
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
	
	public MetaDataTablesData getMetaDataTablesData() {
		return this.metaDataTablesData;
	}
	
	public void setPointerToRawData(int pointerToRawData) {
		this.pointerToRawData = pointerToRawData;
	}
	
	public void setCLRHeader(CLRHeader clrHeader) {
		this.clrHeader = clrHeader;
	}
	
	public void setMetaDataHeader(MetaDataHeader metaDataHeader) {
		this.metaDataHeader = metaDataHeader;
	}
	
	public void setMetaDataTablesHeader(MetaDataTablesHeader metaDataTablesHeader) {
		this.metaDataTablesHeader = metaDataTablesHeader;
	}
	
	public void setBlobHeap(BlobHeap blobHeap) {
		this.blobHeap = blobHeap;
	}
	
	public void setGUIDHeap(GUIDHeap guidHeap) {
		this.guidHeap = guidHeap;
	}
	
	public void setStringsHeap(StringsHeap stringsHeap) {
		this.stringsHeap = stringsHeap;
	}
	
	public void setUSHeap(USHeap usHeap) {
		this.usHeap = usHeap;
	}
	
	public void setMetaDataTablesData(MetaDataTablesData metaDataTablesData) {
		this.metaDataTablesData = metaDataTablesData;
	}

}
