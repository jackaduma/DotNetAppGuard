package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 *  Property; RID Type: 23; Token Type: 0x17000000; MD Streams: #~, #-
 */
public class Property implements IMetaDataTables {
	private static final String TAG = "Property Table";
	
	private int PropFlags;   //USHORT   ====Validity mask: 0x1600
	private int Name;   //STRING
	private int Type;    //BLOB    ====Property signature
	
	public Property(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setPropFlags(dr.readWord());
		
		if (4==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readWord());
		}
		
		if (4==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setType(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setType(dr.readWord());
		}
	}
	
	public int getPropFlags() {
		return this.PropFlags;
	}
	
	public int getName() {
		return this.Name;
	}
	
	public int getType() {
		return this.Type;
	}
	
	public void setPropFlags(int propFlags) {
		this.PropFlags = propFlags;
	}
	
	public void setName(int name) {
		this.Name = name;
	}
	
	public void setType(int type) {
		this.Type = type;
	}
}
