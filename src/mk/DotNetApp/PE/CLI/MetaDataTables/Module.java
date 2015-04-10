package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author makun
 * Module; RID Type: 00; Token Type: 0x00000000;Metadata (MD) Streams: #~, #-
 */
public class Module implements IMetaDataTables {
	private static final String TAG = "Module Table";
	
	private int Generation; //USHORT: Unsigned 2-byte integer     ====For edit-and-continue
	private int Name; //STRING: Offset in the #Strings stream     ====No longer than 512 bytes
	private int Mvid; //GUID: Offset in the #GUID stream          ====Generated automatically
	private int EncId; //GUID                                     ====For edit-and-continue
	private int EncBaseId; //GUID                                 ====For edit-and-continue
	
	public Module() {
		
	}
	
	public Module(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {		
		setGeneration(dr.readWord());
		
		if (4==tablesHeader.getWideOfStringStreamIndex()) {
			setName(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfStringStreamIndex()) {
			setName(dr.readWord());
		}
		
		if (4 == tablesHeader.getWideOfGUIDStreamIndex()) {
			setMvid(dr.readDoubleWord());
			setEncId(dr.readDoubleWord());
			setEncBaseId(dr.readDoubleWord());
		}
		else if (2 == tablesHeader.getWideOfGUIDStreamIndex()) {
			setMvid(dr.readWord());
			setEncId(dr.readWord());
			setEncBaseId(dr.readWord());
		}
	}
	
	public int getGeneration() {
		return this.Generation;
	}
	
	public int getName() {
		return this.Name;
	}
	
	public int getMvid() {
		return this.Mvid;
	}
	
	public int getEncId() {
		return this.EncId;
	}
	
	public int getEncBaseId() {
		return this.EncBaseId;
	}
	
	public void setGeneration(int generation) {
		this.Generation = generation;
	}
	
	public void setName(int name) {
		this.Name = name;
	}
	
	public void setMvid(int mvid) {
		this.Mvid = mvid;
	}
	
	public void setEncId(int encId) {
		this.EncId = encId;
	}
	
	public void setEncBaseId(int encBaseId) {
		this.EncBaseId = encBaseId;
	}
	
}
