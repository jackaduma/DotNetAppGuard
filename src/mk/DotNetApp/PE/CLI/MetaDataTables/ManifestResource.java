package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.CLI.MetaDataTables.constant.CodedTokenType;
import mk.DotNetApp.PE.io.IDataReader;

/**
 * @author kun_ma ManifestResource; RID Type: 40; Token Type: 0x28000000; MD
 *         Streams: #~, #-
 */
public class ManifestResource implements IMetaDataTables {
	private static final String TAG = "ManifestResource Table";

	private int Offset; // ULONG
	private int Flags; // ULONG ===0x000001 or 0x000002
	private int Name; // STRING
	private int Implementation; // Implementation ===0, File, AssemblyRef

	private String refTableName;
	private int refTableIndex;

	public ManifestResource(IDataReader dr, MetaDataTablesHeader tablesHeader)
			throws IOException {
		this.setOffset(dr.readDoubleWord());
		this.setFlags(dr.readDoubleWord());

		if (4 == tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readDoubleWord());
		} else if (2 == tablesHeader.getWideOfStringStreamIndex()) {
			this.setName(dr.readWord());
		}

		this.setImplementation(dr, tablesHeader);
	}

	public int getOffset() {
		return this.Offset;
	}

	public void setOffset(int offset) {
		this.Offset = offset;
	}

	public int getFlags() {
		return this.Flags;
	}

	public void setFlags(int f) {
		this.Flags = f;
	}

	public int getName() {
		return this.Name;
	}

	public void setName(int name) {
		this.Name = name;
	}

	public int getImplementation() {
		return this.Implementation;
	}

	public String getRefTableName() {
		return this.refTableName;
	}

	public int getRefTableIndex() {
		return this.refTableIndex;
	}

	public void setImplementation(IDataReader dr,
			MetaDataTablesHeader tablesHeader) throws IOException {
		this.Implementation = dr.readWord();

		int bitTag = CodedTokenType.TYPEMAP.get("Implementation")
				.get("TagBits");
		int maxMask = (int) (Math.pow(2, bitTag) - 1); // this is important
		int index = this.Implementation & maxMask;
		int tableIndex = this.Implementation >> bitTag;

		Iterator<Entry<String, Integer>> iter = CodedTokenType.TYPEMAP
				.get("Implementation").entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Integer> entry = (Entry<String, Integer>) iter.next();
			String tableName = entry.getKey();
			int bitFlag = entry.getValue();
			if (bitFlag == index && tableName != "TagBits") {
				this.refTableName = tableName;
				break;
			}
		}

		if (tablesHeader.getRecordOfEachTable().containsKey(this.refTableName)) {
			int recordNum = tablesHeader.getRecordOfEachTable().get(
					this.refTableName);
			if (recordNum > (0xffff - maxMask)) {
				int extra = dr.readWord();
				this.refTableIndex = (extra << (8 - bitTag)) + tableIndex;
				this.Implementation += (extra << 8);
			} else {
				this.refTableIndex = tableIndex;
			}
		}
	}
}
