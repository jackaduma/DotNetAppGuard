package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * Field; RID Type: 04; Token Type: 0x04000000; MD Streams: #~, #-
 */
public class Field implements IMetaDataTables {
	private static final String TAG = "Field Table";
	
	private int Flags;  // USHORT    ====Validity mask: 0xB7F7
	private int Name;  //STRING    ====No longer than 1023 bytes
	private int Signature;  // BLOB =====Cannot be 0
	
	public Field(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setFlags(dr.readWord());
		
		if (4==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readWord());
		}
		
		if (4==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setSignature(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setSignature(dr.readWord());
		}
	}
	
	public int getFlags() {
		return this.Flags;
	}
	
	public int getName() {
		return this.Name;
	}
	
	public int getSignature() {
		return this.Signature;
	}
	
	public void setFlags(int flags) {
		this.Flags = flags;
	}
	
	public void setName(int name) {
		this.Name = name;
	}
	
	public void setSignature(int signature) {
		this.Signature = signature;
	}

}
