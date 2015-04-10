package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.CLI.MetaDataTables.constant.CodedTokenType;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 *  MethodSemantics; RID Type: 24; Token Type: None; MD Streams: #~, #-
 */
public class MethodSemantics implements IMetaDataTables {
	private static final String TAG = "MethodSemantics";
	
	private int Semantic;    //USHORT
	private int Method;     //RID: Method
	private int Association;  //HasSemantics
	
	private String methodRefTableName;
	private int methodRefTableIndex;
	
	private String associationRefTableName;
	private int associationRefTableIndex;

	public MethodSemantics(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setSemantic(dr.readWord());
		
		if (tablesHeader.getRecordOfEachTable().get("Method") > 0xFFFF) {
			this.setMethod(dr.readDoubleWord());
		}
		else {
			this.setMethod(dr.readWord());
		}
		
		this.setAssociation(dr, tablesHeader);		
	}
	
	public int getSemantic() {
		return this.Semantic;
	}
	
	public int getMethod() {
		return this.Method;
	}
	
	public int getAssociation() {
		return this.Association;
	}
	
	public String getMethodRefTableName() {
		return this.methodRefTableName;
	}
	
	public int getMethodRefTableIndex() {
		return this.methodRefTableIndex;
	}
	
	public String getAssociationRefTableName() {
		return this.associationRefTableName;
	}
	
	public int getAssociationRefTableIndex() {
		return this.associationRefTableIndex;
	}
	
	public void setSemantic(int semantic) {
		this.Semantic = semantic;
	}
	
	public void setMethod(int method) {
		this.Method = method;
		this.methodRefTableName = "Method";
		this.methodRefTableIndex = method;
	}
	
	public void setAssociation(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.Association = dr.readWord();
		
		int bitTag = CodedTokenType.TYPEMAP.get("HasSemantics").get("TagBits");
		int maxMask = (int) (Math.pow(2, bitTag) - 1);  // this is important
		int index = this.Association & maxMask;
		int tableIndex = this.Association >> bitTag;
		
		Iterator<Entry<String, Integer>> iter = CodedTokenType.TYPEMAP.get("HasSemantics").entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Integer> entry = (Entry<String, Integer>) iter.next();
			String tableName = entry.getKey();
			int bitFlag = entry.getValue();
			if (bitFlag==index && tableName!="TagBits") {
				this.associationRefTableName = tableName;
				break;
			}	
		}
		
		int recordNum = tablesHeader.getRecordOfEachTable().get(this.associationRefTableName);
		if (recordNum > (0xffff - maxMask)) {
			int extra = dr.readWord();
			this.associationRefTableIndex = (extra << (8-bitTag)) + tableIndex;
			this.Association += (extra << 8);
		}
		else {
			this.associationRefTableIndex = tableIndex;
		}
	}
}
