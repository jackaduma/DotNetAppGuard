package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * TypeSpec; RID Type: 27; Token Type: 0x1B000000; MD Streams: #~, #-
 */
public class TypeSpec implements IMetaDataTables {
	private static final String TAG = "TypeSpec Table";
	
	private int Signature;    //BLOB Cannot be 0
	
	private String sigName;
	
	public String getSigName() {
		return this.sigName;
	}
	
	public TypeSpec(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		if (4==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setSignatrue(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setSignatrue(dr.readWord());
		}
		
		this.sigName = tablesHeader.getBlobHeap().getSignature(this.Signature);
	}
	
	public int getSignature() {
		return this.Signature;
	}
	
	public void setSignatrue(int signature) {
		this.Signature = signature;
	}

}
