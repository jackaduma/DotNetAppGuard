package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.CLI.MetaDataTables.constant.CodedTokenType;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 * MethodImpl; RID Type: 25; Token Type: None; MD Streams: #~, #-
 */
public class MethodImpl implements IMetaDataTables {
	private static final String TAG = "MethodImpl Table";
	
	private int Class;   //RID: TypeDef
	private int MethodBody;   //MethodDefOrRef           Overriding method
	private int MethodDeclaration;   //MethodDefOrRef    Overridden method
	
	private String classRefTableName;
	private int classRefTableIndex;
	
	private String bodyRefTableName;
	private int bodyRefTableIndex;
	
	private String declarationRefTableName;
	private int declarationRefTableIndex;
	
	public MethodImpl(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		if (tablesHeader.getRecordOfEachTable().get("TypeDef") > 0xFFFF) {
			this.setClass(dr.readDoubleWord());
		}
		else {
			this.setClass(dr.readWord());
		}
		
		this.setMethodBody(dr, tablesHeader);
		this.setMethodDeclaration(dr, tablesHeader);
	}
	
	public int getSelfClass() {
		return this.Class;
	}
	
	public int getMethodBody() {
		return this.MethodBody;
	}
	
	public int getMethodDeclaration() {
		return this.MethodDeclaration;
	}
	
	public String getClassRefTableName() {
		return this.classRefTableName;
	}
	
	public int getClassRefTableIndex() {
		return this.classRefTableIndex;
	}
	
	public String getBodyRefTableName() {
		return this.bodyRefTableName;
	}
	
	public int getBodyRefTableIndex() {
		return this.bodyRefTableIndex;
	}
	
	public String getDeclarationRefTableName() {
		return this.declarationRefTableName;
	}
	
	public int getDeclarationRefTableIndex() {
		return this.declarationRefTableIndex;
	}
	
	public void setClass(int Class) {
		this.Class = Class;
		this.classRefTableName = "TypeDef";
		this.classRefTableIndex = Class;
	}
	
	public void setMethodBody(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.MethodBody = dr.readWord();
		
		int bitTag = CodedTokenType.TYPEMAP.get("MethodDefOrRef").get("TagBits");
		int maxMask = (int) (Math.pow(2, bitTag) - 1);  // this is important
		int index = this.MethodBody & maxMask;
		int tableIndex = this.MethodBody >> bitTag;
		
		Iterator<Entry<String, Integer>> iter = CodedTokenType.TYPEMAP.get("MethodDefOrRef").entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Integer> entry = (Entry<String, Integer>) iter.next();
			String tableName = entry.getKey();
			int bitFlag = entry.getValue();
			if (bitFlag==index && tableName!="TagBits") {
				this.bodyRefTableName = tableName;
				break;
			}	
		}
		
		int recordNum = tablesHeader.getRecordOfEachTable().get(this.bodyRefTableName);
		if (recordNum > (0xffff - maxMask)) {
			int extra = dr.readWord();
			this.bodyRefTableIndex = (extra << (8-bitTag)) + tableIndex;
			this.MethodBody += (extra << 8);
		}
		else {
			this.bodyRefTableIndex = tableIndex;
		}
	}
	
	public void setMethodDeclaration(IDataReader dr, MetaDataTablesHeader tablesHeader) throws IOException {
		this.MethodDeclaration = dr.readWord();
		
		int bitTag = CodedTokenType.TYPEMAP.get("MethodDefOrRef").get("TagBits");
		int maxMask = (int) (Math.pow(2, bitTag) - 1);  // this is important
		int index = this.MethodDeclaration & maxMask;
		int tableIndex = this.MethodDeclaration >> bitTag;
		
		Iterator<Entry<String, Integer>> iter = CodedTokenType.TYPEMAP.get("MethodDefOrRef").entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Integer> entry = (Entry<String, Integer>) iter.next();
			String tableName = entry.getKey();
			int bitFlag = entry.getValue();
			if (bitFlag==index && tableName!="TagBits") {
				this.declarationRefTableName = tableName;
				break;
			}	
		}
		
		int recordNum = tablesHeader.getRecordOfEachTable().get(this.declarationRefTableName);
		if (recordNum > (0xffff - maxMask)) {
			int extra = dr.readWord();
			this.declarationRefTableIndex = (extra << (8-bitTag)) + tableIndex;
			this.MethodDeclaration += (extra << 8);
		}
		else {
			this.declarationRefTableIndex = tableIndex;
		}
	}
}
