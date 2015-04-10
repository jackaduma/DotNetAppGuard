package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.CLI.MetaDataTables.constant.CodedTokenType;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author makun
 *
 */
public class TypeDef implements IMetaDataTables {
	private static final String TAG = "TypeDef Table";
	private int Flags; // ULONG: Unsigned 4-byte integer     --Validity mask: 0x001173DBF
	private int Name; //STRING
	private int Namespace; //STRING
	private int Extends; // TypeDefOrRef   --Base type
	private int FieldList; //RID: Field    RID: <table> Record index to <table>
	private int MethodList; //RID: Method
	
	private String extendsRefTableName;
	private int extendsRefTableIndex;
	
	private int methodCount;
	private String typeName;
	private String typeNameSpace;
	
	public String getTypeName() {
		return this.typeName;
	}
	
	public String getTypeNameSpace() {
		return this.typeNameSpace;
	}
	
	public int getMethodCount() {
		return this.methodCount;
	}
	
	public void setMethodCount(int count) {
		this.methodCount = count;
	}
	
	public TypeDef(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.setFlags(dr.readDoubleWord());
		
		if (2 == tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readWord());
			this.setNamespace(dr.readWord());
		}
		else if (4 == tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readDoubleWord());
			this.setNamespace(dr.readDoubleWord());
		}
		
		this.setExtends(dr, tablesHeader);
		
		if (tablesHeader.getRecordOfEachTable().get("Field")>0xFFFF) {
			this.setFieldList(dr.readDoubleWord());
		}
		else {
			this.setFieldList(dr.readWord());
		}
		
		if (tablesHeader.getRecordOfEachTable().get("Method")>0xFFFF) {
			this.setMethodList(dr.readDoubleWord());
		}
		else {
			this.setMethodList(dr.readWord());
		}
		
		this.typeNameSpace = tablesHeader.getStringsHeap().getUTF8String(this.Namespace);
		this.typeName = tablesHeader.getStringsHeap().getUTF8String(this.Name);
		
	}
	
	public int getFlags() {
		return this.Flags;
	}
	
	public void setFlags(int flags) {
		this.Flags = flags;
	}
	
	public int getName() {
		return this.Name;
	}
	
	public void setName(int name) {
		this.Name  = name;
	}
	
	public int getNamespace() {
		return this.Namespace;
	}
	
	public void setNamespace(int namespace) {
		this.Namespace = namespace;
	}
	
	public int getExtends() {
		return this.Extends;
	}
	
	public String getExtendsRefTableName() {
		return this.extendsRefTableName;
	}
	
	public int getExtendsRefTableIndex() {
		return this.extendsRefTableIndex;
	}
	
	public void setExtends(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.Extends = dr.readWord();
		
		int bitTag = CodedTokenType.TYPEMAP.get("TypeDefOrRef").get("TagBits");
		int maxMask = (int) (Math.pow(2, bitTag) - 1);  // this is important
		int index = this.Extends & maxMask;
		int tableIndex = this.Extends >> bitTag;
		
		Iterator<Entry<String, Integer>> iter = CodedTokenType.TYPEMAP.get("TypeDefOrRef").entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Integer> entry = (Entry<String, Integer>) iter.next();
			String tableName = entry.getKey();
			int bitFlag = entry.getValue();
			if (bitFlag==index && tableName!="TagBits") {
				this.extendsRefTableName = tableName;
				break;
			}	
		}
		
		int recordNum = tablesHeader.getRecordOfEachTable().get(this.extendsRefTableName);
		if (recordNum > (0xffff - maxMask)) {
			int extra = dr.readWord();
			this.extendsRefTableIndex = (extra << (8-bitTag)) + tableIndex;
			this.Extends += (extra << 8);
		}
		else {
			this.extendsRefTableIndex = tableIndex;
		}
	}
	
	public int getFieldList() {
		return this.FieldList;
	}
	
	public void setFieldList(int fieldList) {
		this.FieldList = fieldList;
	}
	
	public int getMethodList() {
		return this.MethodList;
	}
	
	public void setMethodList(int methodList) {
		this.MethodList = methodList;
	}

}
