package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * Param; RID Type: 08; Token Type: 0x08000000; MD Streams: #~, #-
 */
public class Param implements IMetaDataTables {
	private static final String TAG = "Param Table";
	
	private int Flags;   //USHORT    ====Validity mask: 0x3013
	private int Sequence;   //USHORT   ====0 means return value
	private int Name;  // STRING
	
	public Param(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setFlags(dr.readWord());
		this.setSequence(dr.readWord());
		
		if (4==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readWord());
		}
	}
	
	public int getFlags() {
		return this.Flags;
	}
	
	public int getSequence() {
		return this.Sequence;
	}
	
	public int getName() {
		return this.Name;
	}
	
	public void setFlags(int flags) {
		this.Flags = flags;
	}
	
	public void setSequence(int sequence) {
		this.Sequence = sequence;
	}
	
	public void setName(int name) {
		this.Name = name;
	}
}
