package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.CLI.MetaDataTables.constant.CodedTokenType;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * MethodSpec; RID Type: 43; Token Type: 0x2B000000; MD Streams: #~, #-
 */
public class MethodSpec implements IMetaDataTables {
	private static final String TAG = "MethodSpec Table";
	
	private int Method;     //MethodDefOrRef     ====Instantiated method
	private int Instantiation;   //BLOB         =====Instantiation signature
	
	private String methodRefTableName;
	private int methodRefTableIndex;
	
	private MetaDataTablesHeader tablesHeader;
	
	private String methodName;
	
	public String getMethodName() {
		return this.methodName;
	}

	public MethodSpec(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.tablesHeader = tablesHeader;
		
		this.setMethod(dr, tablesHeader);
		
		if (4==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setInstantiation(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setInstantiation(dr.readWord());
		}
	}
	
	public int getMethod() {
		return this.Method;
	}
	
	public String getMethodRefTableName() {
		return this.methodRefTableName;
	}
	
	public int getMethodRefTableIndex() {
		return this.methodRefTableIndex;
	}
	
	public void setMethod(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.Method = dr.readWord();
		
		int bitTag = CodedTokenType.TYPEMAP.get("MethodDefOrRef").get("TagBits");
		int maxMask = (int) (Math.pow(2, bitTag) - 1);  // this is important
		int index = this.Method & maxMask;
		int tableIndex = this.Method >> bitTag;
		
		Iterator<Entry<String, Integer>> iter = CodedTokenType.TYPEMAP.get("MethodDefOrRef").entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Integer> entry = (Entry<String, Integer>) iter.next();
			String tableName = entry.getKey();
			int bitFlag = entry.getValue();
			if (bitFlag==index && tableName!="TagBits") {
				this.methodRefTableName = tableName;
				break;
			}	
		}
		
		int recordNum = tablesHeader.getRecordOfEachTable().get(this.methodRefTableName);
		if (recordNum > (0xffff - maxMask)) {
			int extra = dr.readWord();
			this.methodRefTableIndex = (extra << (8-bitTag)) + tableIndex;
			this.Method += (extra << 8);
		}
		else {
			this.methodRefTableIndex = tableIndex;
		}
	}
	
	public int getInstantiation() {
		return this.Instantiation;
	}
	
	public void setInstantiation(int i) {
		this.Instantiation = i;
	}
	
	public void ReferenceHandler() throws IOException {
		if (this.methodRefTableName.equals("Method")) {
			Method table = (Method) tablesHeader.getTablesData().get(this.methodRefTableName).get(this.methodRefTableIndex-1);
			this.methodName = table.getFullName();
		}
		else if (this.methodRefTableName.equals("MemberRef")) {
			MemberRef table = (MemberRef) tablesHeader.getTablesData().get(this.methodRefTableName).get(this.methodRefTableIndex-1);
			table.ReferenceHandler();
			String className = table.getClassName();
			String memberName = table.getMemberName();
			String signature = table.getSigName();
			this.methodName = String.format("%s-%s", className, memberName);
		}
		
	}
}
