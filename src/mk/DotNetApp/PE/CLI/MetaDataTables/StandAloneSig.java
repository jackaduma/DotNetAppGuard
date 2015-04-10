package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 *  StandAloneSig; RID Type: 17; Token Type: 0x11000000; MD Streams: #~, #-
 */
public class StandAloneSig implements IMetaDataTables {
	private static final String TAG = "StandAloneSig Table";
	
	private int Signature;  // BLOB     ====Cannot be 0
	
	public StandAloneSig(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		if (4==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setSignature(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setSignature(dr.readWord());
		}
	}
	
	public int getSignature() {
		return this.Signature;
	}
	
	public void setSignature(int signature) {
		this.Signature = signature;
	}
}
