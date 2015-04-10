package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * File; RID Type: 38; Token Type: 0x26000000; MD Streams: #~, #-
 */
public class File implements IMetaDataTables {
	private static final String TAG = "File Table";
	
	private int Flags;   //ULONG 0x00000000 or 0x00000001
	private int Name;    //STRING No path; only filename
	private int HashValue;   //BLOB
	
	public File(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setFlags(dr.readDoubleWord());
		
		if (4==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readWord());
		}
		
		if (4==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setHashValue(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setHashValue(dr.readWord());
		}
	}
	
	public int getFlags() {
		return this.Flags;
	}
	
	public void setFlags(int flags) {
		this.Flags = flags;
	}
	
	public int getName() {
		return this.Name;
	}
	
	public void setName(int name) {
		this.Name = name;
	}
	
	public int getHashValue() {
		return this.HashValue;
	}
	
	public void setHashValue(int value) {
		this.HashValue = value;
	}
}
