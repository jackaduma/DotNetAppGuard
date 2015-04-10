package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * NestedClass; RID Type: 41; Token Type: None; MD Streams: #~, #-
 */
public class NestedClass implements IMetaDataTables {
	private static final String TAG = "NestedClass Table";
	
	private int NestedClass;    //RID: TypeDef
	private int EnclosingClass;   //RID: TypeDef
	
	private String nClassRefTableName;
	private int nClassRefTableIndex;
	
	private String eClassRefTableName;
	private int eClassRefTableIndex;
	
	public NestedClass(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		if (tablesHeader.getRecordOfEachTable().get("TypeDef") > 0xFFFF) {
			this.setNestedClass(dr.readDoubleWord());
			this.setEnclosingClass(dr.readDoubleWord());
		}
		else {
			this.setNestedClass(dr.readWord());
			this.setEnclosingClass(dr.readWord());
		}
		
	}
	
	public int getNestedClass() {
		return this.NestedClass;
	}
	
	public String getNClassRefTableName() {
		return this.nClassRefTableName;
	}
	
	public int getNClassRefTableIndex() {
		return this.nClassRefTableIndex;
	}
	
	public void setNestedClass(int n) {
		this.NestedClass = n;
		this.nClassRefTableName = "TypeDef";
		this.nClassRefTableIndex = n;
	}
	
	public int getEnclosingClass() {
		return this.EnclosingClass;
	}
	
	public String getEClassRefTableName() {
		return this.eClassRefTableName;
	}
	
	public int getEClassRefTableIndex() {
		return this.eClassRefTableIndex;
	}
	
	public void setEnclosingClass(int e) {
		this.EnclosingClass = e;
		this.eClassRefTableName = "TypeDef";
		this.eClassRefTableIndex = e;
	}
}
