package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.CLI.MetaDataTables.constant.CodedTokenType;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 *  GenericParamConstraint; RID Type: 44; Token Type: 0x2C000000; MD Streams: #~, #-
 */
public class GenericParamConstraint implements IMetaDataTables {
	private static final String TAG = "GenericParamConstraint Table";
	
	private int Owner;   //RID: GenericParam           ====Constrained parameter
	private int Constraint;     //TypeDefOrRef      =====Type the parameter must extend or implement
	
	private String oRefTableName;
	private int oRefTableIndex;
	
	private String cRefTableName;
	private int cRefTableIndex;
	
	public GenericParamConstraint(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		if (tablesHeader.getRecordOfEachTable().get("GenericParam") > 0xFFFF) {
			this.setOwner(dr.readDoubleWord());
		}
		else {
			this.setOwner(dr.readWord());
		}
		
		this.setConstraint(dr, tablesHeader);
	}
	
	public int getOwner() {
		return this.Owner;
	}
	
	public String getOwnerRefTableName() {
		return this.oRefTableName;
	}
	
	public int getOwnerRefTableIndex() {
		return this.oRefTableIndex;
	}
	
	public void setOwner(int o) {
		this.Owner = o;
		this.oRefTableName = "GenericParam";
		this.oRefTableIndex = o;
	}
	
	public int getConstraint() {
		return this.Constraint;
	}
	
	public String getConstraintRefTableName() {
		return this.cRefTableName;
	}
	
	public int getConstraintRefTableIndex() {
		return this.cRefTableIndex;
	}
	
	public void setConstraint(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.Constraint = dr.readWord();
		
		int bitTag = CodedTokenType.TYPEMAP.get("TypeDefOrRef").get("TagBits");
		int maxMask = (int) (Math.pow(2, bitTag) - 1);  // this is important
		int index = this.Constraint & maxMask;
		int tableIndex = this.Constraint >> bitTag;
		
		Iterator<Entry<String, Integer>> iter = CodedTokenType.TYPEMAP.get("TypeDefOrRef").entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Integer> entry = (Entry<String, Integer>) iter.next();
			String tableName = entry.getKey();
			int bitFlag = entry.getValue();
			if (bitFlag==index && tableName!="TagBits") {
				this.cRefTableName = tableName;
				break;
			}	
		}
		
		int recordNum = tablesHeader.getRecordOfEachTable().get(this.cRefTableName);
		if (recordNum > (0xffff - maxMask)) {
			int extra = dr.readWord();
			this.cRefTableIndex = (extra << (8-bitTag)) + tableIndex;
			this.Constraint += (extra << 8);
		}
		else {
			this.cRefTableIndex = tableIndex;
		}
	}
}
