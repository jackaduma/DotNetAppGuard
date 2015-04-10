package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 *  ClassLayout; RID Type: 15; Token Type: None; MD Streams: #~, #-
 */
public class ClassLayout implements IMetaDataTables {
	private String TableTag = "ClassLayout";
	
	private int PackingSize;    //USHORT    ====Power of 2, from 1 through 128
	private int ClassSize;     //ULONG
	private int Parent;     //RID: TypeDef
	
	private String refTableName;
	private int refTableIndex;
	
	public String getTableTag() {
		return this.TableTag;
	}
	
	public ClassLayout(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setPackingSize(dr.readWord());
		this.setClassSize(dr.readDoubleWord());
		
		if (tablesHeader.getRecordOfEachTable().get("TypeDef") > 0xFFFF) {
			this.setParent(dr.readDoubleWord());
		}
		else {
			this.setParent(dr.readWord());
		}
		
	}
	
	public int getPackingSize() {
		return this.PackingSize;
	}
	
	public int getClassSize() {
		return this.ClassSize;
	}
	
	public int getParent() {
		return this.Parent;
	}
	
	public String getRefTableName() {
		return this.refTableName;
	}
	
	public int getRefTableIndex() {
		return this.refTableIndex;
	}
	
	public void setPackingSize(int packingSize) {
		this.PackingSize = packingSize;
	}
	
	public void setClassSize(int classSize) {
		this.ClassSize = classSize;
	}
	
	public void setParent(int parent) {
		this.Parent = parent;
		this.refTableName = "TypeDef";
		this.refTableIndex = parent;
	}

}
