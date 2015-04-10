package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.CLI.Heaps.StringsHeap;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * Method; RID Type: 06; Token Type: 0x06000000; MD Streams: #~, #-
 */
public class Method implements IMetaDataTables {
	private String TableTAG = "Method";
	
	private int RVA;   //ULONG  ====Must be 0 or point at read-only section
	private int ImplFlags;  // USHORT    ====Validity mask: 0x10BF
	private int Flags;   // USHORT =====Validity mask: 0xFDF7
	private String Name;    //STRING     ====No longer than 1,023 bytes
	private int Signature;   //BLOB    ====Cannot be 0
	private int ParamList;  //RID: Param
	
	private String refTableName;
	private int refTableIndex;
	
	private int pointerToRawData;
	
	private String sigName;
	
	public String getSigName() {
		return this.sigName;
	}
	
	public String getTableTag() {
		return this.TableTAG;
	}
	
	private String fullName;
	
	public String getFullName() {
		return this.fullName;
	}
	
	public void setFullName(String name) {
		this.fullName = name;
	}
	
	//it provides the list of call methods to method flow analysis
	private List<String> callMethodCollection = new LinkedList<String>();
	
	public List<String> getCallMethodCollection() {
		return this.callMethodCollection;
	}
	
	public void setCallMethodCollection(List<String> collection) {
		this.callMethodCollection = collection;
	}
	
	public Method(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setRVA(dr.readDoubleWord());
		this.setImplFlags(dr.readWord());
		this.setFlags(dr.readWord());
		
		if (4==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readDoubleWord(), tablesHeader.getStringsHeap());
		}
		else if (2==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readWord(), tablesHeader.getStringsHeap());
		}
		
		if (4==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setSignature(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setSignature(dr.readWord());
		}
		
		if (tablesHeader.getRecordOfEachTable().get("Param")>0xFFFF) {
			this.setParamList(dr.readDoubleWord());
		}
		else {
			this.setParamList(dr.readWord());
		}
		
		/*
		 * If the RVA entry holds 0, it means this method is implemented somewhere else (imported from a COM library, 
		 * platform-invoked from an unmanaged DLL, or simply implemented by descendants of the class owning this method). 
		 * All these cases are described by special combinations of method flags and implementation flags.
		 */
		if (0==this.getRVA()) {
			this.setPointerToRawData(0);
			System.out.println("This Method will be implemented somewhere else!");
		}
		else {
			int offset = (this.getRVA() - tablesHeader.getSectionRVA()) + tablesHeader.getSectionPointerToRawData();
			this.setPointerToRawData(offset);
		}
		
		this.sigName = tablesHeader.getBlobHeap().getSignature(this.Signature);
		
	}
	
	public int getPointerToRawData() {
		return this.pointerToRawData;
	}
	
	public void setPointerToRawData(int offset) {
		this.pointerToRawData = offset;
	}
	
	public int getRVA() {
		return this.RVA;
	}
	
	public int getImplFlags() {
		return this.ImplFlags;
	}
	
	public int getFlags() {
		return this.Flags;
	}
	
	public String getName() {
		return this.Name;
	}
	
	public int getSignature() {
		return this.Signature;
	}
	
	public int getParamList() {
		return this.ParamList;
	}
	
	public String getRefTableName() {
		return this.refTableName;
	}
	
	public int getRefTableIndex() {
		return this.refTableIndex;
	}
	
	public void setRVA(int rva) {
		this.RVA = rva;
	}
	
	public void setImplFlags(int implFlags) {
		this.ImplFlags = implFlags;
	}
	
	public void setFlags(int flags) {
		this.Flags = flags;
	}
	
	public void setName(int index, StringsHeap s) {
		this.Name = s.getUTF8String(index);
//		System.out.println(this.Name);
//		System.out.println(this.RVA);
	}
	
	public void setSignature(int signature) {
		this.Signature = signature;
	}
	
	public void setParamList(int paramList) {
		this.ParamList = paramList;
		this.refTableName = "Param";
		this.refTableIndex = paramList;
	}
	
	public void ReferenceHandler() {
		
	}
}
