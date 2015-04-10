package mk.DotNetApp.Core.CIL;

import org.omg.CORBA.Object;

public class OpCode {
	
	public enum FlowControl {
		Branch, Break, Call, Cond_Branch, Meta, Next, Phi, Return, Throw;
		
		public static FlowControl convert(byte b) {
			return FlowControl.values()[0xff&b];
		}
	}

	public enum OpCodeType {
		Annotation, Macro, Nternal, Objmodel, Prefix, Primitive;
		
		public static OpCodeType convert(byte b) {
			return OpCodeType.values()[0xff&b];
		}
	}

	public enum OperandType {
		InlineBrTarget, InlineField, InlineI, InlineI8, InlineMethod, InlineNone, InlinePhi, InlineR, InlineSig, 
		InlineString, InlineSwitch, InlineTok, InlineType, InlineVar, InlineArg, ShortInlineBrTarget, ShortInlineI, 
		ShortInlineR, ShortInlineVar, ShortInlineArg;

		public static OperandType convert(byte b) {
			return OperandType.values()[0xff&b];
		}
	}

	public enum StackBehaviour {
		Pop0, Pop1, Pop1_pop1, Popi, Popi_pop1, Popi_popi, Popi_popi8, Popi_popi_popi, Popi_popr4, Popi_popr8,
		Popref, Popref_pop1, Popref_popi, Popref_popi_popi, Popref_popi_popi8, Popref_popi_popr4, Popref_popi_popr8,
		Popref_popi_popref, PopAll, Push0, Push1, Push1_push1, Pushi, Pushi8, Pushr4, Pushr8, Pushref, Varpop, Varpush;

		public static StackBehaviour convert(byte b) {
			return StackBehaviour.values()[0xff&b];
		}
	}

	public enum Code {
		Nop, Break, Ldarg_0, Ldarg_1, Ldarg_2, Ldarg_3, Ldloc_0, Ldloc_1, Ldloc_2, Ldloc_3, 
		Stloc_0, Stloc_1, Stloc_2, Stloc_3, Ldarg_S, Ldarga_S, Starg_S, Ldloc_S, Ldloca_S, Stloc_S,
		Ldnull, Ldc_I4_M1, Ldc_I4_0, Ldc_I4_1, Ldc_I4_2, Ldc_I4_3, Ldc_I4_4, Ldc_I4_5, Ldc_I4_6, Ldc_I4_7, Ldc_I4_8, 
		Ldc_I4_S, Ldc_I4, Ldc_I8, Ldc_R4, Ldc_R8, Dup, Pop, Jmp, Call, Calli, Ret, Br_S, Brfalse_S, Brtrue_S, 
		Beq_S, Bge_S, Bgt_S, Ble_S, Blt_S, Bne_Un_S, Bge_Un_S, Bgt_Un_S, Ble_Un_S, Blt_Un_S, Br, Brfalse, Brtrue, 
		Beq, Bge, Bgt, Ble, Blt, Bne_Un, Bge_Un, Bgt_Un, Ble_Un, Blt_Un, Switch, Ldind_I1, Ldind_U1, Ldind_I2, 
		Ldind_U2, Ldind_I4, Ldind_U4, Ldind_I8, Ldind_I, Ldind_R4, Ldind_R8, Ldind_Ref, Stind_Ref, Stind_I1, 
		Stind_I2, Stind_I4, Stind_I8, Stind_R4, Stind_R8, Add, Sub, Mul, Div, Div_Un, Rem, Rem_Un, And, Or, Xor, 
		Shl, Shr, Shr_Un, Neg, Not, Conv_I1, Conv_I2, Conv_I4, Conv_I8, Conv_R4, Conv_R8, Conv_U4, Conv_U8, Callvirt,
		Cpobj, Ldobj, Ldstr, Newobj, Castclass, Isinst, Conv_R_Un, Unbox, Throw, Ldfld, Ldflda, Stfld, Ldsfld, Ldsflda, 
		Stsfld, Stobj, Conv_Ovf_I1_Un, Conv_Ovf_I2_Un, Conv_Ovf_I4_Un, Conv_Ovf_I8_Un, Conv_Ovf_U1_Un, Conv_Ovf_U2_Un, 
		Conv_Ovf_U4_Un, Conv_Ovf_U8_Un, Conv_Ovf_I_Un, Conv_Ovf_U_Un, Box, Newarr, Ldlen, Ldelema, Ldelem_I1, Ldelem_U1, 
		Ldelem_I2, Ldelem_U2, Ldelem_I4, Ldelem_U4, Ldelem_I8, Ldelem_I, Ldelem_R4, Ldelem_R8, Ldelem_Ref, Stelem_I, 
		Stelem_I1, Stelem_I2, Stelem_I4, Stelem_I8, Stelem_R4, Stelem_R8, Stelem_Ref, Ldelem_Any, Stelem_Any, Unbox_Any, 
		Conv_Ovf_I1, Conv_Ovf_U1, Conv_Ovf_I2, Conv_Ovf_U2, Conv_Ovf_I4, Conv_Ovf_U4, Conv_Ovf_I8, Conv_Ovf_U8, Refanyval, 
		Ckfinite, Mkrefany, Ldtoken, Conv_U2, Conv_U1, Conv_I, Conv_Ovf_I, Conv_Ovf_U, Add_Ovf, Add_Ovf_Un, Mul_Ovf, 
		Mul_Ovf_Un, Sub_Ovf, Sub_Ovf_Un, Endfinally, Leave, Leave_S, Stind_I, Conv_U, Arglist, Ceq, Cgt, Cgt_Un, Clt,
		Clt_Un, Ldftn, Ldvirtftn, Ldarg, Ldarga, Starg, Ldloc, Ldloca, Stloc, Localloc, Endfilter, Unaligned, Volatile,
		Tail, Initobj, Constrained, Cpblk, Initblk, No, Rethrow, Sizeof, Refanytype, Readonly;

		public static Code convert(byte value) {
			return Code.values()[0xff&value];
		}
	}

	final byte op1;
	final byte op2;
	final byte code;
	final byte flow_control;
	final byte opcode_type;
	final byte operand_type;
	final byte stack_behavior_pop;
	final byte stack_behavior_push;

	private String Name;
	public String getName() {
		return OpCodeNames.names.get((int)(0xff&code));
	}

//	private int Size;
	public int getSize() {
		return (op1&0xff) == 0xff ? 1 : 2;
	}

	public byte getOp1() {
		return op1;
	}

	public byte getOp2() {
		return op2;
	}

	public short getValue() {
		return (op1&0xff) == 0xff ? op2 : (short) ((op1 << 8) | op2);
	}

	public Code getCode() {
		return Code.convert(code);
	}

	public FlowControl getFlowControl() {
		return FlowControl.convert(flow_control);
	}

	public OpCodeType getOpCodeType() {
		return OpCodeType.convert(opcode_type);
	}

	public OperandType getOperandType() {
		return OperandType.convert(operand_type);
	}

	public StackBehaviour getStackBehaviourPop() {
		return StackBehaviour.convert(stack_behavior_pop);
	}

	public StackBehaviour getStackBehaviourPush() {
		return StackBehaviour.convert(stack_behavior_push);
	}

	public OpCode(int x, int y) {		
		
		this.op1 = (byte) ((x >> 0) & 0xff);
		this.op2 = (byte) ((x >> 8) & 0xff);
		this.code = (byte) ((x >> 16) & 0xff);
		this.flow_control = (byte) ((x >> 24) & 0xff);

		this.opcode_type = (byte) ((y >> 0) & 0xff);
		this.operand_type = (byte) ((y >> 8) & 0xff);
		this.stack_behavior_pop = (byte) ((y >> 16) & 0xff);
		this.stack_behavior_push = (byte) ((y >> 24) & 0xff);
		
		if ((op1&0xff) == 0xff)
			OpCodes.OneByteOpCode[op2&0xff] = this;
		else
			OpCodes.TwoBytesOpCode[op2&0xff] = this;
	}
	
	public boolean Equals(Object obj) {
		if (!(obj instanceof OpCode)) {
			return false;
		}

		OpCode opcode = (OpCode) obj;
		return op1 == opcode.op1 && op2 == opcode.op2;
	}

	public boolean Equals(OpCode opcode) {
		return op1 == opcode.op1 && op2 == opcode.op2;
	}

	public boolean EqualTo(OpCode one, OpCode other) {
		return one.op1 == other.op1 && one.op2 == other.op2;
	}

	public boolean NotEqualTo(OpCode one, OpCode other) {
		return one.op1 != other.op1 || one.op2 != other.op2;
	}

	public String ToString() {
		return this.Name;
	}
	



}
