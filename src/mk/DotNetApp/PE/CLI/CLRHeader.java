package mk.DotNetApp.PE.CLI;

import java.util.HashMap;
import java.util.Map;

import mk.DotNetApp.PE.ImageData;
import mk.DotNetApp.PE.ImageDataDirectory;
import mk.DotNetApp.PE.RVAConverter;


/**
 * @author kun_ma
 *
 */
public class CLRHeader {
	private static final String TAG = "CLR 2.0 Header";
	
	// COM+ Header entry point flags.
	private static Map<Integer, String> flagMap= new HashMap<Integer, String>();
	static
	{
		// COM+ Header entry point flags.
		flagMap.put(0x00000001, "COMIMAGE_FLAGS_ILONLY");
		flagMap.put(0x00000002, "COMIMAGE_FLAGS_32BITREQUIRED");
		flagMap.put(0x00000004, "COMIMAGE_FLAGS_IL_LIBRARY");
		flagMap.put(0x00000008, "COMIMAGE_FLAGS_STRONGNAMESIGNED");
		flagMap.put(0x00010000, "COMIMAGE_FLAGS_TRACKDEBUGDATA");
		
		// Version flags for image.
		flagMap.put(2, "COR_VERSION_MAJOR_V2");
		flagMap.put(2, "COR_VERSION_MAJOR");
		flagMap.put(0, "COR_VERSION_MINOR");
		flagMap.put(8, "COR_DELETED_NAME_LENGTH");
		flagMap.put(8, "COR_VTABLEGAP_NAME_LENGTH");
		
		// DDBLD - Added Next Line - Still verifying general usage
		
		flagMap.put(0x00000010, "COMIMAGE_FLAGS_NATIVE_ENTRYPOINT");
		// DDBLD - End of Add
		
		flagMap.put(0x00010000, "COMIMAGE_FLAGS_TRACKDEBUGDATA");
		// Other kinds of flags follow
		
		// Maximum size of a NativeType descriptor.
		flagMap.put(1, "NATIVE_TYPE_MAX_CB");
		flagMap.put(0xFF, "COR_ILMETHOD_SECT_SMALL_MAX_DATASIZE");

	    // #defines for the MIH FLAGS
		flagMap.put(0x01, "IMAGE_COR_MIH_METHODRVA");
		flagMap.put(0x02, "IMAGE_COR_MIH_EHRVA");
		flagMap.put(0x08, "IMAGE_COR_MIH_BASICBLOCK");

	    // V-table constants
		flagMap.put(0x01, "COR_VTABLE_32BIT");         // V-table slots are 32-bits in size.
		flagMap.put(0x02, "COR_VTABLE_64BIT");         // V-table slots are 64-bits in size.
		flagMap.put(0x04, "COR_VTABLE_FROM_UNMANAGED");         // If set, transition from unmanaged.
		flagMap.put(0x08, "COR_VTABLE_FROM_UNMANAGED_RETAIN_APPDOMAIN"); // If set, transition from unmanaged with keeping the current appdomain.
		flagMap.put(0x10, "COR_VTABLE_CALL_MOST_DERIVED");         // Call most derived method described by

	    // EATJ constants
		flagMap.put(32, "IMAGE_COR_EATJ_THUNK_SIZE");           // Size of a jump thunk reserved range.

	    // Max name lengths
	    //@todo: Change to unlimited name lengths.
	    flagMap.put(1024, "MAX_CLASS_NAME");
	    flagMap.put(1024, "MAX_PACKAGE_NAME");
	};
	
	// data
	private byte[] data;
	
	// Header versioning
	private long size;
	private int MajorRuntimeVersion;
	private int MinorRuntimeVersion;
	// Symbol table and startup information
	private ImageDataDirectory MetaData;
	private long Flags;
	private long EntryPointToken;
	// Binding information
    private ImageDataDirectory Resources;  // A Data Directory for the resources. These resources are referenced in the MetaData.
    private ImageDataDirectory StrongNameSignature;
    // Regular fixup and binding information
    private ImageDataDirectory CodeManagerTable;   //Always 0
    private ImageDataDirectory VTableFixups;
    private ImageDataDirectory ExportAddressTableJumps;   //Always 0
    // Precompiled image info (internal use only - set to zero)
    private ImageDataDirectory ManagedNativeHeader;
    
    public byte[] getData() {
    	return this.data;
    }
    
    public void setData(byte[] data) {
    	this.data = data;
    }
    
    public long getSize() {
    	return this.size;
    }
    
    public int getMajorRuntimeVersion() {
    	return this.MajorRuntimeVersion;
    }
    
    public int getMinorRuntimeVersion() {
    	return this.MinorRuntimeVersion;
    }
    
    public ImageDataDirectory getMetaData() {
    	return this.MetaData;
    }
    
    public long getFlags() {
    	return this.Flags;
    }
    
    public long getEntryPointToken() {
    	return this.EntryPointToken;
    }
    
    public ImageDataDirectory getResources() {
    	return this.Resources;
    }
    
    public ImageDataDirectory getStrongNameSignature() {
    	return this.StrongNameSignature;
    }
    
    public ImageDataDirectory getCodeManagerTable() {
    	return this.CodeManagerTable;
    }
    
    public ImageDataDirectory getVTableFixups() {
    	return this.VTableFixups;
    }
    
    public ImageDataDirectory getExportAddressTableJumps() {
    	return this.ExportAddressTableJumps;
    }
    
    public ImageDataDirectory getManagedNativeHeader() {
    	return this.ManagedNativeHeader;
    }
    
    public void setSize(long size) {
    	this.size = size;
    }
    
    public void setMajorRuntimeVersion(int MajorRuntimeVersion) {
    	this.MajorRuntimeVersion = MajorRuntimeVersion;
    }
    
    public void setMinorRuntimeVersion(int MinorRuntimeVersion) {
    	this.MinorRuntimeVersion = MinorRuntimeVersion;
    }
    
    public void setMetaData(ImageDataDirectory MetaData) {
    	this.MetaData = MetaData;
    }
    
    public void setFlags(long Flags) {
    	this.Flags = Flags;
    }
    
    public void setEntryPointToken(long EntryPointToken) {
    	this.EntryPointToken = EntryPointToken;
    }
    
    public void setResources(ImageDataDirectory Resources) {
    	this.Resources = Resources;
    }
    
    public void setStrongNameSignature(ImageDataDirectory StrongNameSignature) {
    	this.StrongNameSignature = StrongNameSignature;
    }
    
    public void setCodeManagerTable(ImageDataDirectory CodeManagerTable) {
    	this.CodeManagerTable = CodeManagerTable;
    }
    
    public void setVTableFixups(ImageDataDirectory VTableFixups) {
    	this.VTableFixups = VTableFixups;
    }
    
    public void setExportAddressTableJumps(ImageDataDirectory ExportAddressTableJumps) {
    	this.ExportAddressTableJumps = ExportAddressTableJumps;
    }
    
    public void setManagedNativeHeader(ImageDataDirectory ManagedNativeHeader) {
    	this.ManagedNativeHeader = ManagedNativeHeader;
    }
}
