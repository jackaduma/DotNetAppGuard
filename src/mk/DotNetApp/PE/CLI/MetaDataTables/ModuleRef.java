package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * ModuleRef; RID Type: 26; Token Type: 0x1A000000; MD Streams: #~, #-
 */
public class ModuleRef implements IMetaDataTables {
	private static final String TAG = "ModuleRef Table";
	
	private int Name;    //STRING   ====No longer than 512 bytes
	
	private String moduleName;
	
	public String getModuleName() {
		return this.moduleName;
	}
	
	public ModuleRef(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		if (4==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readWord());
		}
		
		this.moduleName = tablesHeader.getStringsHeap().getUTF8String(this.Name);
	}
	
	public int getName() {
		return this.Name;
	}
	
	public void setName(int name) {
		this.Name = name;
	}
}
