package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.CLI.MetaDataTables.constant.CodedTokenType;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * MemberRef; RID Type: 10; Token Type: 0x0A000000; MD Streams: #~, #-
 */
public class MemberRef implements IMetaDataTables {
	private static final String TAG = "MemberRef Table";
	
	private int Class; //MemberRefParent   ====Cannot be TypeDef
	private int Name; //STRING         =====No longer than 1,023 bytes
	private int Signature;     //BLOB       ======Cannot be 0
	
	private String refTableName;
	private int refTableIndex;
	
	private String className;
	private String memberName;
	private String sigName;
	
	private MetaDataTablesHeader tablesHeader;
	
	public MemberRef(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.tablesHeader = tablesHeader;
		
		this.setClass(dr, tablesHeader);
		
		if (4==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readWord());
		}
		
		if (4==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setSignature(dr.readDoubleWord());
		}
		else if (2==tablesHeader.getWideOfBlobStreamIndex()) {
			this.setSignature(dr.readWord());
		}		
	}
	
	public int getSelfClass() {
		return this.Class;
	}
	
	public int getName() {
		return this.Name;
	}
 	
	public int getSignature() {
		return this.Signature;
	}
	
	public String getRefTableName() {
		return this.refTableName;
	}
	
	public int getRefTableIndex() {
		return this.refTableIndex;
	}
	
	public String getClassName() {
		return this.className;
	}
	
	public String getMemberName() {
		return this.memberName;
	}
	
	public String getSigName() {
		return this.sigName;
	}
	
	
	
	public void setClass(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.Class = dr.readWord();
		
		int bitTag = CodedTokenType.TYPEMAP.get("MemberRefParent").get("TagBits");
		int maxMask = (int) (Math.pow(2, bitTag) - 1);  // this is important
		int index = this.Class & maxMask;
		int tableIndex = this.Class >> bitTag;
		
		Iterator<Entry<String, Integer>> iter = CodedTokenType.TYPEMAP.get("MemberRefParent").entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Integer> entry = (Entry<String, Integer>) iter.next();
			String tableName = entry.getKey();
			int bitFlag = entry.getValue();
			if (bitFlag==index && tableName!="TagBits") {
				this.refTableName = tableName;
				break;
			}	
		}
		
		int recordNum = tablesHeader.getRecordOfEachTable().get(this.refTableName);
		if (recordNum > (0xffff - maxMask)) {
			int extra = dr.readWord();
			this.refTableIndex = (extra << (8-bitTag)) + tableIndex;
			this.Class += (extra << 8);
		}
		else {
			this.refTableIndex = tableIndex;
		}	

	}
	
	public void setName(int name) {
		this.Name = name;
	}
	
	public void setSignature(int signature) {
		this.Signature = signature;
	}
	
	public void ReferenceHandler() throws IOException {
		this.memberName = tablesHeader.getStringsHeap().getUTF8String(this.Name);
		this.sigName = tablesHeader.getBlobHeap().getSignature(this.Signature);
		
		if (this.refTableName.equals("TypeDef")) {
			TypeDef table = (TypeDef) tablesHeader.getTablesData().get(this.refTableName).get(this.refTableIndex-1);
//			throw new IOException();
		}
		else if (this.refTableName.equals("TypeRef")) {
			TypeRef table = (TypeRef) tablesHeader.getTablesData().get(this.refTableName).get(this.refTableIndex-1);
			table.ReferenceHandler();
			this.className = String.format("%s-%s.%s", table.getScopeName(), table.getTypeNameSpace(), table.getTypeName());
		}
		else if (this.refTableName.equals("ModuleRef")) {
			ModuleRef table = (ModuleRef) tablesHeader.getTablesData().get(this.refTableName).get(this.refTableIndex-1);
		}
		else if (this.refTableName.equals("Method")) {
			Method table = (Method) tablesHeader.getTablesData().get(this.refTableName).get(this.refTableIndex-1);
		}
		else if (this.refTableName.equals("TypeSpec")) {
			TypeSpec table = (TypeSpec) tablesHeader.getTablesData().get(this.refTableName).get(this.refTableIndex-1);
			this.className = "<TypeSpec: Signature-"+table.getSigName()+">";
		}
		else {
			throw new IOException();
		}
	}
}
