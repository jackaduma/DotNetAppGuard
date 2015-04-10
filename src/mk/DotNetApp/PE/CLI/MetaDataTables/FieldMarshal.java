package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * FieldMarshal; RID Type: 13; Token Type: None; MD Streams: #~, #-
 */
public class FieldMarshal implements IMetaDataTables {
	private static final String TAG = "FieldMarshal Table";
	
	private int Parent;   //FieldMarshal  // this is a problem
	private int NativeType;   //BLOB Cannot be 0
	
	public FieldMarshal(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		if (tablesHeader.getRecordOfEachTable().get("FieldMarshal") > 0xFFFF) {
			this.setParent(dr.readDoubleWord());
		}
		else {
			this.setParent(dr.readWord());
		}
		
		if (4==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setNativeType(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setNativeType(dr.readWord());
		}
	}
	
	public int getParent() {
		return this.Parent;
	}
	
	public int getNativeType() {
		return this.NativeType;
	}
	
	public void setParent(int parent) {
		this.Parent = parent;
	}
	
	public void setNativeType(int nativeType) {
		this.NativeType = nativeType;
	}
}
