package mk.DotNetApp.PE.CLI.MetaDataTables.MethodHandler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mk.DotNetApp.Core.CIL.Instruction;
import mk.DotNetApp.Core.CIL.InstructionsMap;
import mk.DotNetApp.Core.CIL.OpCode;
import mk.DotNetApp.Core.CIL.OpCode.OperandType;
import mk.DotNetApp.Core.CIL.OpCodeNames;
import mk.DotNetApp.Core.CIL.OpCodes;
import mk.DotNetApp.PE.CLI.MetaDataTables.IMetaDataTables;
import mk.DotNetApp.PE.CLI.MetaDataTables.MemberRef;
import mk.DotNetApp.PE.CLI.MetaDataTables.MetaDataTablesMap;
import mk.DotNetApp.PE.CLI.MetaDataTables.Method;
import mk.DotNetApp.PE.CLI.MetaDataTables.MethodSpec;
import mk.DotNetApp.PE.CLI.MetaDataTables.TypeRef;
import mk.DotNetApp.PE.io.IDataReader;

/**
 * @author kun_ma
 *
 */
public class MethodBody {
	/*
	 * constant
	 */
	public static final int TinyFormat = 0x02;  //Method header is Tiny.
	public static final int FatFormat = 0x03;   //Method header is Fat.
	
	private byte[] data;
	private int position;  // the cursor
	
	private long codeSize;
	private int maxStackSize;  // slot numbers
	
	private Map<String, List<IMetaDataTables>> metadataTables;
	
	//it provides the list of call methods to method flow analysis
	private List<String> callMethodCollection = new LinkedList<String>();
	
	public List<String> getCallMethodCollection() {
		return this.callMethodCollection;
	}
	
	public MethodBody(IDataReader dr, int codeSize) {
		
	}
	
	public MethodBody(byte[] b, MethodHeader h, Map<String, List<IMetaDataTables>> tables) throws IOException {
		this.metadataTables = tables;
		
		this.data = b;
		this.position = 0;
		this.codeSize = h.getCodeSize();
		this.maxStackSize = h.getMaxStack();
		
		switch (h.getFormat()) {
		case TinyFormat:
			ReadCode();
			break;
		case FatFormat:
			ReadFatCode();
			break;
		default:
			break;
		}		
	}
	
	public void ReadFatCode() throws IOException {
		ReadCode();
	}
	
	public void ReadCode() throws IOException {
		while(this.position<this.codeSize) {
			System.out.print(AppendLabel());
			System.out.print("\t" + " | ");
			
			OpCode opCode = this.readOpCode();
			String opCodeName = opCode.getName();
			System.out.print(opCodeName);
			System.out.print("\t" + " | ");
			
			
			if (opCode.getOperandType() != OpCode.OperandType.InlineNone) {
				int oprandSize = this.GetOperandSize(opCode);
				System.out.println("\t"+oprandSize);
			}
			else {
				System.out.println("");
			}
		}
	}
	
	public OpCode readOpCode() {
		int ilOpCode = this.data[this.position]&0xff;
		
		try {
			if (ilOpCode != 0xFE)
			{
				OpCode code = OpCodes.OneByteOpCode[ilOpCode];
				this.position += 1;
				return code;
			}
			else {
				OpCode code = OpCodes.TwoBytesOpCode[this.data[this.position+1]];
				this.position += 2;
				return code;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object ReadOperand (OpCode opCode)
	{
		OperandType t = opCode.getOperandType();
		switch (t) {
		case InlineSwitch:
			int length = ReadInt32 ();
			int base_offset = this.position + (4 * length);
			int[] branches = new int[length];
			for (int i = 0; i < length; i++) {
				branches [i] = base_offset + ReadInt32 ();
			}
			return branches;
		case ShortInlineBrTarget:
			return ReadByte () + this.position;
		case InlineBrTarget:
			return ReadInt32 () + this.position;
		case ShortInlineI:
			if (opCode == OpCodes.Ldc_I4_S)
				return ReadByte ();

			return ReadUByte ();
		case InlineI:
			return ReadInt32 ();
		case ShortInlineR:
			this.position += 4;
			return "float";
//			return ReadSingle ();
		case InlineR:
			this.position += 8;
			return "double";
//			return ReadDouble ();
		case InlineI8:
			this.position += 8;
			return "Int 64";
//			return ReadInt64 ();
		case ShortInlineVar:
			this.position += 1;
			return "UByte";
//			return GetVariable (ReadUByte ());
		case InlineVar:
			this.position += 2;
			return "Int 16";
//			return GetVariable (ReadUInt16 ());
		case ShortInlineArg:
			this.position += 1;
			return "UByte";
//			return GetParameter (ReadUByte ());
		case InlineArg:
			this.position += 2;
			return "Int 16";
//			return GetParameter (ReadUInt16 ());
		case InlineSig:
			this.position += 4;
			return "Token";
//			return GetCallSite (ReadToken ());
		case InlineString:
			this.position += 4;
			return "Token";
//			return GetString (ReadToken ());
		case InlineTok:
		case InlineType:
		case InlineMethod:
		case InlineField:
			this.position += 4;
			return "Token";
//			return reader.LookupToken (ReadToken ());
		default:
			throw new UnsupportedOperationException ();
		}
	}
	
	public int GetOperandSize (OpCode opcode) throws IOException
	{
		int size = 0; //opcode.getSize();
		
		/*
		 *  for branch instructions
		 */
		int current_offset = 0;
		int offset = 0;
		int target_offset = 0;
		StringBuilder target = new StringBuilder("\tIL_");;

		OperandType t = opcode.getOperandType();
		switch (t) {
		case InlineSwitch:
			int length = ReadInt32 ();  // has move 4 byte
			int base_offset = this.position + (4 * length);
			int[] branches = new int[length];
			for (int i = 0; i < length; i++) {
				branches [i] = base_offset + ReadInt32 ();
				System.out.println("**** branch["+i+"] "+branches[i]);
			}			
//			this.position += length * 4;
			return size + (1 + length) * 4;
		case InlineI8:
		case InlineR:
			this.position += 8;
			return size + 8;
		case InlineMethod:
			int rid = (int) ((char) this.data[this.position+3] & 0xff);
			int index = (int) (((char) (this.data[this.position+2] & 0xff) << 16)
					| ((char) (this.data[this.position+1] & 0xff) << 8)
					| ((char) (this.data[this.position] & 0xff) << 0));
			
			System.out.print(String.format("\t(%02X)%06X\t|", rid, index));
			ReadToken(rid, index);
			this.position += 4;
			return size + 4;
		case InlineBrTarget:
			current_offset = this.position + 4;  // it means next instruction position 
			offset = ReadInt32();
			target_offset = current_offset + offset;			
			target.append(String.format("%04X", target_offset));
			System.out.print(target.toString());
//			this.position += 4;
			return size + 4;
		case InlineField:
		case InlineI:		
		case InlineString:
		case InlineTok:
		case InlineType:
		case ShortInlineR:
		case InlineSig:
			this.position += 4;
			return size + 4;
		case InlineArg:
		case InlineVar:
			this.position += 2;
			return size + 2;
		case ShortInlineBrTarget:
			current_offset = this.position + 1;
			offset = ReadByte();
			target_offset = current_offset + offset;
			target.append(String.format("%04X", target_offset));
			System.out.print(target.toString());
//			this.position += 1;
			return size + 1;
		case ShortInlineI:
		case ShortInlineArg:
		case ShortInlineVar:
			this.position += 1;
			return size + 1;
		default:
			return size;
		}
	}
	
	private int ReadInt32() {
		// C# byte stream is unsigned, so it need to transfer 
		int value = (int) ( (char)(data [position] & 0xff)
				| ((char) (data [position + 1] & 0xff) << 8)
				| ((char) (data [position + 2] & 0xff) << 16)
				| ((char) (data [position + 3] & 0xff) << 24));
		System.out.print("("+String.format("%08X", value)+")");
		this.position += 4;
		return value;		
	}
	
	private int ReadByte() {
		if (position >= data.length)
            return -1;
        return (char) (data[this.position++] & 0xff);
	}
	
	private int ReadUByte() {
		if (position >= data.length)
            return -1;
        return (char) (data[this.position++]);
	}
	
	private String AppendLabel() {
		StringBuilder label = new StringBuilder("IL_");
		label.append(String.format("%04X", this.position));
		return label.toString();
	}
	
	private String ReadToken(int rid, int index) throws IOException {
		StringBuilder token = new StringBuilder();
		String tableName = MetaDataTablesMap.TableMap.get(rid);
		System.out.print("\t(RID:"+tableName+", index:"+(index)+")");
		if (tableName.equals("MemberRef")) {
			MemberRef member = (MemberRef) this.metadataTables.get(tableName).get(index-1); // the index start with 1, but I construct begin 0
			member.ReferenceHandler();
			String refName = member.getRefTableName();
			int refIndex = member.getRefTableIndex();
			
			String className = member.getClassName();
			String memberName = member.getMemberName();
			String signature = member.getSigName();
			System.out.print("\t(Class:"+className+")");
			System.out.print("\t(Name:"+memberName+")");
			System.out.print("\t(Sig:"+signature+")");
			
			StringBuilder fullName = new StringBuilder(String.format("%s-%s", className, memberName));
			this.callMethodCollection.add(fullName.toString());
		}
		else if (tableName.equals("TypeDef")) {
			TypeRef type = (TypeRef) this.metadataTables.get(tableName).get(index-1);
		}
		else if (tableName.equals("Method")) {
			Method method = (Method) this.metadataTables.get(tableName).get(index-1);
			String name = method.getFullName();
			System.out.print("\t(Name:"+name+")");
			
			this.callMethodCollection.add(name);
		}
		else if (tableName.equals("MethodSpec")) {
			MethodSpec methodSpec = (MethodSpec) this.metadataTables.get(tableName).get(index-1);
			methodSpec.ReferenceHandler();
			
			String methodName = methodSpec.getMethodName();
			System.out.print("\t(Name:"+methodName+")");
		}
		
		
		return token.toString();
	}
}
