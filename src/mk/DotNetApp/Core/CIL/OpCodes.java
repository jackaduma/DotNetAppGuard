package mk.DotNetApp.Core.CIL;

import mk.DotNetApp.Core.CIL.OpCode.Code;
import mk.DotNetApp.Core.CIL.OpCode.FlowControl;
import mk.DotNetApp.Core.CIL.OpCode.OpCodeType;
import mk.DotNetApp.Core.CIL.OpCode.OperandType;
import mk.DotNetApp.Core.CIL.OpCode.StackBehaviour;

public class OpCodes {
	public static final OpCode [] OneByteOpCode = new OpCode [0xe0 + 1];
	public static final OpCode [] TwoBytesOpCode = new OpCode [0x1e + 1];

	public static final OpCode Nop = new OpCode (
		0xff << 0 | 0x00 << 8 | (byte) Code.Nop.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Break = new OpCode (
		0xff << 0 | 0x01 << 8 | (byte) Code.Break.ordinal() << 16 | (byte) FlowControl.Break.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Ldarg_0 = new OpCode (
		0xff << 0 | 0x02 << 8 | (byte) Code.Ldarg_0.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Ldarg_1 = new OpCode (
		0xff << 0 | 0x03 << 8 | (byte) Code.Ldarg_1.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Ldarg_2 = new OpCode (
		0xff << 0 | 0x04 << 8 | (byte) Code.Ldarg_2.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Ldarg_3 = new OpCode (
		0xff << 0 | 0x05 << 8 | (byte) Code.Ldarg_3.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Ldloc_0 = new OpCode (
		0xff << 0 | 0x06 << 8 | (byte) Code.Ldloc_0.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Ldloc_1 = new OpCode (
		0xff << 0 | 0x07 << 8 | (byte) Code.Ldloc_1.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Ldloc_2 = new OpCode (
		0xff << 0 | 0x08 << 8 | (byte) Code.Ldloc_2.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Ldloc_3 = new OpCode (
		0xff << 0 | 0x09 << 8 | (byte) Code.Ldloc_3.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Stloc_0 = new OpCode (
		0xff << 0 | 0x0a << 8 | (byte) Code.Stloc_0.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Stloc_1 = new OpCode (
		0xff << 0 | 0x0b << 8 | (byte) Code.Stloc_1.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Stloc_2 = new OpCode (
		0xff << 0 | 0x0c << 8 | (byte) Code.Stloc_2.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Stloc_3 = new OpCode (
		0xff << 0 | 0x0d << 8 | (byte) Code.Stloc_3.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Ldarg_S = new OpCode (
		0xff << 0 | 0x0e << 8 | (byte) Code.Ldarg_S.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineArg.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Ldarga_S = new OpCode (
		0xff << 0 | 0x0f << 8 | (byte) Code.Ldarga_S.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineArg.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Starg_S = new OpCode (
		0xff << 0 | 0x10 << 8 | (byte) Code.Starg_S.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineArg.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Ldloc_S = new OpCode (
		0xff << 0 | 0x11 << 8 | (byte) Code.Ldloc_S.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineVar.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Ldloca_S = new OpCode (
		0xff << 0 | 0x12 << 8 | (byte) Code.Ldloca_S.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineVar.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Stloc_S = new OpCode (
		0xff << 0 | 0x13 << 8 | (byte) Code.Stloc_S.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineVar.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Ldnull = new OpCode (
		0xff << 0 | 0x14 << 8 | (byte) Code.Ldnull.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushref.ordinal() << 24);

	public static final OpCode Ldc_I4_M1 = new OpCode (
		0xff << 0 | 0x15 << 8 | (byte) Code.Ldc_I4_M1.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldc_I4_0 = new OpCode (
		0xff << 0 | 0x16 << 8 | (byte) Code.Ldc_I4_0.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldc_I4_1 = new OpCode (
		0xff << 0 | 0x17 << 8 | (byte) Code.Ldc_I4_1.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldc_I4_2 = new OpCode (
		0xff << 0 | 0x18 << 8 | (byte) Code.Ldc_I4_2.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldc_I4_3 = new OpCode (
		0xff << 0 | 0x19 << 8 | (byte) Code.Ldc_I4_3.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldc_I4_4 = new OpCode (
		0xff << 0 | 0x1a << 8 | (byte) Code.Ldc_I4_4.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldc_I4_5 = new OpCode (
		0xff << 0 | 0x1b << 8 | (byte) Code.Ldc_I4_5.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldc_I4_6 = new OpCode (
		0xff << 0 | 0x1c << 8 | (byte) Code.Ldc_I4_6.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldc_I4_7 = new OpCode (
		0xff << 0 | 0x1d << 8 | (byte) Code.Ldc_I4_7.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldc_I4_8 = new OpCode (
		0xff << 0 | 0x1e << 8 | (byte) Code.Ldc_I4_8.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldc_I4_S = new OpCode (
		0xff << 0 | 0x1f << 8 | (byte) Code.Ldc_I4_S.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineI.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldc_I4 = new OpCode (
		0xff << 0 | 0x20 << 8 | (byte) Code.Ldc_I4.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineI.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldc_I8 = new OpCode (
		0xff << 0 | 0x21 << 8 | (byte) Code.Ldc_I8.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineI8.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi8.ordinal() << 24);

	public static final OpCode Ldc_R4 = new OpCode (
		0xff << 0 | 0x22 << 8 | (byte) Code.Ldc_R4.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.ShortInlineR.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushr4.ordinal() << 24);

	public static final OpCode Ldc_R8 = new OpCode (
		0xff << 0 | 0x23 << 8 | (byte) Code.Ldc_R8.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineR.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushr8.ordinal() << 24);

	public static final OpCode Dup = new OpCode (
		0xff << 0 | 0x25 << 8 | (byte) Code.Dup.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Push1_push1.ordinal() << 24);

	public static final OpCode Pop = new OpCode (
		0xff << 0 | 0x26 << 8 | (byte) Code.Pop.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Jmp = new OpCode (
		0xff << 0 | 0x27 << 8 | (byte) Code.Jmp.ordinal() << 16 | (byte) FlowControl.Call.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineMethod.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Call = new OpCode (
		0xff << 0 | 0x28 << 8 | (byte) Code.Call.ordinal() << 16 | (byte) FlowControl.Call.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineMethod.ordinal() << 8 | (byte) StackBehaviour.Varpop.ordinal() << 16 | (byte) StackBehaviour.Varpush.ordinal() << 24);

	public static final OpCode Calli = new OpCode (
		0xff << 0 | 0x29 << 8 | (byte) Code.Calli.ordinal() << 16 | (byte) FlowControl.Call.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineSig.ordinal() << 8 | (byte) StackBehaviour.Varpop.ordinal() << 16 | (byte) StackBehaviour.Varpush.ordinal() << 24);

	public static final OpCode Ret = new OpCode (
		0xff << 0 | 0x2a << 8 | (byte) Code.Ret.ordinal() << 16 | (byte) FlowControl.Return.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Varpop.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Br_S = new OpCode (
		0xff << 0 | 0x2b << 8 | (byte) Code.Br_S.ordinal() << 16 | (byte) FlowControl.Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Brfalse_S = new OpCode (
		0xff << 0 | 0x2c << 8 | (byte) Code.Brfalse_S.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Brtrue_S = new OpCode (
		0xff << 0 | 0x2d << 8 | (byte) Code.Brtrue_S.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Beq_S = new OpCode (
		0xff << 0 | 0x2e << 8 | (byte) Code.Beq_S.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Bge_S = new OpCode (
		0xff << 0 | 0x2f << 8 | (byte) Code.Bge_S.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Bgt_S = new OpCode (
		0xff << 0 | 0x30 << 8 | (byte) Code.Bgt_S.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Ble_S = new OpCode (
		0xff << 0 | 0x31 << 8 | (byte) Code.Ble_S.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Blt_S = new OpCode (
		0xff << 0 | 0x32 << 8 | (byte) Code.Blt_S.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Bne_Un_S = new OpCode (
		0xff << 0 | 0x33 << 8 | (byte) Code.Bne_Un_S.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Bge_Un_S = new OpCode (
		0xff << 0 | 0x34 << 8 | (byte) Code.Bge_Un_S.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Bgt_Un_S = new OpCode (
		0xff << 0 | 0x35 << 8 | (byte) Code.Bgt_Un_S.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Ble_Un_S = new OpCode (
		0xff << 0 | 0x36 << 8 | (byte) Code.Ble_Un_S.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Blt_Un_S = new OpCode (
		0xff << 0 | 0x37 << 8 | (byte) Code.Blt_Un_S.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Br = new OpCode (
		0xff << 0 | 0x38 << 8 | (byte) Code.Br.ordinal() << 16 | (byte) FlowControl.Branch.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Brfalse = new OpCode (
		0xff << 0 | 0x39 << 8 | (byte) Code.Brfalse.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Brtrue = new OpCode (
		0xff << 0 | 0x3a << 8 | (byte) Code.Brtrue.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Beq = new OpCode (
		0xff << 0 | 0x3b << 8 | (byte) Code.Beq.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Bge = new OpCode (
		0xff << 0 | 0x3c << 8 | (byte) Code.Bge.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Bgt = new OpCode (
		0xff << 0 | 0x3d << 8 | (byte) Code.Bgt.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Ble = new OpCode (
		0xff << 0 | 0x3e << 8 | (byte) Code.Ble.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Blt = new OpCode (
		0xff << 0 | 0x3f << 8 | (byte) Code.Blt.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Bne_Un = new OpCode (
		0xff << 0 | 0x40 << 8 | (byte) Code.Bne_Un.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Bge_Un = new OpCode (
		0xff << 0 | 0x41 << 8 | (byte) Code.Bge_Un.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Bgt_Un = new OpCode (
		0xff << 0 | 0x42 << 8 | (byte) Code.Bgt_Un.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Ble_Un = new OpCode (
		0xff << 0 | 0x43 << 8 | (byte) Code.Ble_Un.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Blt_Un = new OpCode (
		0xff << 0 | 0x44 << 8 | (byte) Code.Blt_Un.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.InlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Switch = new OpCode (
		0xff << 0 | 0x45 << 8 | (byte) Code.Switch.ordinal() << 16 | (byte) FlowControl.Cond_Branch.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineSwitch.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Ldind_I1 = new OpCode (
		0xff << 0 | 0x46 << 8 | (byte) Code.Ldind_I1.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldind_U1 = new OpCode (
		0xff << 0 | 0x47 << 8 | (byte) Code.Ldind_U1.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldind_I2 = new OpCode (
		0xff << 0 | 0x48 << 8 | (byte) Code.Ldind_I2.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldind_U2 = new OpCode (
		0xff << 0 | 0x49 << 8 | (byte) Code.Ldind_U2.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldind_I4 = new OpCode (
		0xff << 0 | 0x4a << 8 | (byte) Code.Ldind_I4.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldind_U4 = new OpCode (
		0xff << 0 | 0x4b << 8 | (byte) Code.Ldind_U4.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldind_I8 = new OpCode (
		0xff << 0 | 0x4c << 8 | (byte) Code.Ldind_I8.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Pushi8.ordinal() << 24);

	public static final OpCode Ldind_I = new OpCode (
		0xff << 0 | 0x4d << 8 | (byte) Code.Ldind_I.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldind_R4 = new OpCode (
		0xff << 0 | 0x4e << 8 | (byte) Code.Ldind_R4.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Pushr4.ordinal() << 24);

	public static final OpCode Ldind_R8 = new OpCode (
		0xff << 0 | 0x4f << 8 | (byte) Code.Ldind_R8.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Pushr8.ordinal() << 24);

	public static final OpCode Ldind_Ref = new OpCode (
		0xff << 0 | 0x50 << 8 | (byte) Code.Ldind_Ref.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Pushref.ordinal() << 24);

	public static final OpCode Stind_Ref = new OpCode (
		0xff << 0 | 0x51 << 8 | (byte) Code.Stind_Ref.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi_popi.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Stind_I1 = new OpCode (
		0xff << 0 | 0x52 << 8 | (byte) Code.Stind_I1.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi_popi.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Stind_I2 = new OpCode (
		0xff << 0 | 0x53 << 8 | (byte) Code.Stind_I2.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi_popi.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Stind_I4 = new OpCode (
		0xff << 0 | 0x54 << 8 | (byte) Code.Stind_I4.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi_popi.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Stind_I8 = new OpCode (
		0xff << 0 | 0x55 << 8 | (byte) Code.Stind_I8.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi_popi8.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Stind_R4 = new OpCode (
		0xff << 0 | 0x56 << 8 | (byte) Code.Stind_R4.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi_popr4.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Stind_R8 = new OpCode (
		0xff << 0 | 0x57 << 8 | (byte) Code.Stind_R8.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi_popr8.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Add = new OpCode (
		0xff << 0 | 0x58 << 8 | (byte) Code.Add.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Sub = new OpCode (
		0xff << 0 | 0x59 << 8 | (byte) Code.Sub.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Mul = new OpCode (
		0xff << 0 | 0x5a << 8 | (byte) Code.Mul.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Div = new OpCode (
		0xff << 0 | 0x5b << 8 | (byte) Code.Div.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Div_Un = new OpCode (
		0xff << 0 | 0x5c << 8 | (byte) Code.Div_Un.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Rem = new OpCode (
		0xff << 0 | 0x5d << 8 | (byte) Code.Rem.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Rem_Un = new OpCode (
		0xff << 0 | 0x5e << 8 | (byte) Code.Rem_Un.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode And = new OpCode (
		0xff << 0 | 0x5f << 8 | (byte) Code.And.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Or = new OpCode (
		0xff << 0 | 0x60 << 8 | (byte) Code.Or.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Xor = new OpCode (
		0xff << 0 | 0x61 << 8 | (byte) Code.Xor.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Shl = new OpCode (
		0xff << 0 | 0x62 << 8 | (byte) Code.Shl.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Shr = new OpCode (
		0xff << 0 | 0x63 << 8 | (byte) Code.Shr.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Shr_Un = new OpCode (
		0xff << 0 | 0x64 << 8 | (byte) Code.Shr_Un.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Neg = new OpCode (
		0xff << 0 | 0x65 << 8 | (byte) Code.Neg.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Not = new OpCode (
		0xff << 0 | 0x66 << 8 | (byte) Code.Not.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Conv_I1 = new OpCode (
		0xff << 0 | 0x67 << 8 | (byte) Code.Conv_I1.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_I2 = new OpCode (
		0xff << 0 | 0x68 << 8 | (byte) Code.Conv_I2.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_I4 = new OpCode (
		0xff << 0 | 0x69 << 8 | (byte) Code.Conv_I4.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_I8 = new OpCode (
		0xff << 0 | 0x6a << 8 | (byte) Code.Conv_I8.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi8.ordinal() << 24);

	public static final OpCode Conv_R4 = new OpCode (
		0xff << 0 | 0x6b << 8 | (byte) Code.Conv_R4.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushr4.ordinal() << 24);

	public static final OpCode Conv_R8 = new OpCode (
		0xff << 0 | 0x6c << 8 | (byte) Code.Conv_R8.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushr8.ordinal() << 24);

	public static final OpCode Conv_U4 = new OpCode (
		0xff << 0 | 0x6d << 8 | (byte) Code.Conv_U4.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_U8 = new OpCode (
		0xff << 0 | 0x6e << 8 | (byte) Code.Conv_U8.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi8.ordinal() << 24);

	public static final OpCode Callvirt = new OpCode (
		0xff << 0 | 0x6f << 8 | (byte) Code.Callvirt.ordinal() << 16 | (byte) FlowControl.Call.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineMethod.ordinal() << 8 | (byte) StackBehaviour.Varpop.ordinal() << 16 | (byte) StackBehaviour.Varpush.ordinal() << 24);

	public static final OpCode Cpobj = new OpCode (
		0xff << 0 | 0x70 << 8 | (byte) Code.Cpobj.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineType.ordinal() << 8 | (byte) StackBehaviour.Popi_popi.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Ldobj = new OpCode (
		0xff << 0 | 0x71 << 8 | (byte) Code.Ldobj.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineType.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Ldstr = new OpCode (
		0xff << 0 | 0x72 << 8 | (byte) Code.Ldstr.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineString.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushref.ordinal() << 24);

	public static final OpCode Newobj = new OpCode (
		0xff << 0 | 0x73 << 8 | (byte) Code.Newobj.ordinal() << 16 | (byte) FlowControl.Call.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineMethod.ordinal() << 8 | (byte) StackBehaviour.Varpop.ordinal() << 16 | (byte) StackBehaviour.Pushref.ordinal() << 24);

	public static final OpCode Castclass = new OpCode (
		0xff << 0 | 0x74 << 8 | (byte) Code.Castclass.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineType.ordinal() << 8 | (byte) StackBehaviour.Popref.ordinal() << 16 | (byte) StackBehaviour.Pushref.ordinal() << 24);

	public static final OpCode Isinst = new OpCode (
		0xff << 0 | 0x75 << 8 | (byte) Code.Isinst.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineType.ordinal() << 8 | (byte) StackBehaviour.Popref.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_R_Un = new OpCode (
		0xff << 0 | 0x76 << 8 | (byte) Code.Conv_R_Un.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushr8.ordinal() << 24);

	public static final OpCode Unbox = new OpCode (
		0xff << 0 | 0x79 << 8 | (byte) Code.Unbox.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineType.ordinal() << 8 | (byte) StackBehaviour.Popref.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Throw = new OpCode (
		0xff << 0 | 0x7a << 8 | (byte) Code.Throw.ordinal() << 16 | (byte) FlowControl.Throw.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Ldfld = new OpCode (
		0xff << 0 | 0x7b << 8 | (byte) Code.Ldfld.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineField.ordinal() << 8 | (byte) StackBehaviour.Popref.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Ldflda = new OpCode (
		0xff << 0 | 0x7c << 8 | (byte) Code.Ldflda.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineField.ordinal() << 8 | (byte) StackBehaviour.Popref.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Stfld = new OpCode (
		0xff << 0 | 0x7d << 8 | (byte) Code.Stfld.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineField.ordinal() << 8 | (byte) StackBehaviour.Popref_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Ldsfld = new OpCode (
		0xff << 0 | 0x7e << 8 | (byte) Code.Ldsfld.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineField.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Ldsflda = new OpCode (
		0xff << 0 | 0x7f << 8 | (byte) Code.Ldsflda.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineField.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Stsfld = new OpCode (
		0xff << 0 | 0x80 << 8 | (byte) Code.Stsfld.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineField.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Stobj = new OpCode (
		0xff << 0 | 0x81 << 8 | (byte) Code.Stobj.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineType.ordinal() << 8 | (byte) StackBehaviour.Popi_pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Conv_Ovf_I1_Un = new OpCode (
		0xff << 0 | 0x82 << 8 | (byte) Code.Conv_Ovf_I1_Un.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_Ovf_I2_Un = new OpCode (
		0xff << 0 | 0x83 << 8 | (byte) Code.Conv_Ovf_I2_Un.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_Ovf_I4_Un = new OpCode (
		0xff << 0 | 0x84 << 8 | (byte) Code.Conv_Ovf_I4_Un.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_Ovf_I8_Un = new OpCode (
		0xff << 0 | 0x85 << 8 | (byte) Code.Conv_Ovf_I8_Un.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi8.ordinal() << 24);

	public static final OpCode Conv_Ovf_U1_Un = new OpCode (
		0xff << 0 | 0x86 << 8 | (byte) Code.Conv_Ovf_U1_Un.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_Ovf_U2_Un = new OpCode (
		0xff << 0 | 0x87 << 8 | (byte) Code.Conv_Ovf_U2_Un.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_Ovf_U4_Un = new OpCode (
		0xff << 0 | 0x88 << 8 | (byte) Code.Conv_Ovf_U4_Un.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_Ovf_U8_Un = new OpCode (
		0xff << 0 | 0x89 << 8 | (byte) Code.Conv_Ovf_U8_Un.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi8.ordinal() << 24);

	public static final OpCode Conv_Ovf_I_Un = new OpCode (
		0xff << 0 | 0x8a << 8 | (byte) Code.Conv_Ovf_I_Un.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_Ovf_U_Un = new OpCode (
		0xff << 0 | 0x8b << 8 | (byte) Code.Conv_Ovf_U_Un.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Box = new OpCode (
		0xff << 0 | 0x8c << 8 | (byte) Code.Box.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineType.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushref.ordinal() << 24);

	public static final OpCode Newarr = new OpCode (
		0xff << 0 | 0x8d << 8 | (byte) Code.Newarr.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineType.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Pushref.ordinal() << 24);

	public static final OpCode Ldlen = new OpCode (
		0xff << 0 | 0x8e << 8 | (byte) Code.Ldlen.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldelema = new OpCode (
		0xff << 0 | 0x8f << 8 | (byte) Code.Ldelema.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineType.ordinal() << 8 | (byte) StackBehaviour.Popref_popi.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldelem_I1 = new OpCode (
		0xff << 0 | 0x90 << 8 | (byte) Code.Ldelem_I1.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref_popi.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldelem_U1 = new OpCode (
		0xff << 0 | 0x91 << 8 | (byte) Code.Ldelem_U1.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref_popi.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldelem_I2 = new OpCode (
		0xff << 0 | 0x92 << 8 | (byte) Code.Ldelem_I2.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref_popi.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldelem_U2 = new OpCode (
		0xff << 0 | 0x93 << 8 | (byte) Code.Ldelem_U2.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref_popi.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldelem_I4 = new OpCode (
		0xff << 0 | 0x94 << 8 | (byte) Code.Ldelem_I4.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref_popi.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldelem_U4 = new OpCode (
		0xff << 0 | 0x95 << 8 | (byte) Code.Ldelem_U4.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref_popi.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldelem_I8 = new OpCode (
		0xff << 0 | 0x96 << 8 | (byte) Code.Ldelem_I8.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref_popi.ordinal() << 16 | (byte) StackBehaviour.Pushi8.ordinal() << 24);

	public static final OpCode Ldelem_I = new OpCode (
		0xff << 0 | 0x97 << 8 | (byte) Code.Ldelem_I.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref_popi.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldelem_R4 = new OpCode (
		0xff << 0 | 0x98 << 8 | (byte) Code.Ldelem_R4.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref_popi.ordinal() << 16 | (byte) StackBehaviour.Pushr4.ordinal() << 24);

	public static final OpCode Ldelem_R8 = new OpCode (
		0xff << 0 | 0x99 << 8 | (byte) Code.Ldelem_R8.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref_popi.ordinal() << 16 | (byte) StackBehaviour.Pushr8.ordinal() << 24);

	public static final OpCode Ldelem_Ref = new OpCode (
		0xff << 0 | 0x9a << 8 | (byte) Code.Ldelem_Ref.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref_popi.ordinal() << 16 | (byte) StackBehaviour.Pushref.ordinal() << 24);

	public static final OpCode Stelem_I = new OpCode (
		0xff << 0 | 0x9b << 8 | (byte) Code.Stelem_I.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref_popi_popi.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Stelem_I1 = new OpCode (
		0xff << 0 | 0x9c << 8 | (byte) Code.Stelem_I1.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref_popi_popi.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Stelem_I2 = new OpCode (
		0xff << 0 | 0x9d << 8 | (byte) Code.Stelem_I2.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref_popi_popi.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Stelem_I4 = new OpCode (
		0xff << 0 | 0x9e << 8 | (byte) Code.Stelem_I4.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref_popi_popi.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Stelem_I8 = new OpCode (
		0xff << 0 | 0x9f << 8 | (byte) Code.Stelem_I8.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref_popi_popi8.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Stelem_R4 = new OpCode (
		0xff << 0 | 0xa0 << 8 | (byte) Code.Stelem_R4.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref_popi_popr4.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Stelem_R8 = new OpCode (
		0xff << 0 | 0xa1 << 8 | (byte) Code.Stelem_R8.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref_popi_popr8.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Stelem_Ref = new OpCode (
		0xff << 0 | 0xa2 << 8 | (byte) Code.Stelem_Ref.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popref_popi_popref.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Ldelem_Any = new OpCode (
		0xff << 0 | 0xa3 << 8 | (byte) Code.Ldelem_Any.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineType.ordinal() << 8 | (byte) StackBehaviour.Popref_popi.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Stelem_Any = new OpCode (
		0xff << 0 | 0xa4 << 8 | (byte) Code.Stelem_Any.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineType.ordinal() << 8 | (byte) StackBehaviour.Popref_popi_popref.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Unbox_Any = new OpCode (
		0xff << 0 | 0xa5 << 8 | (byte) Code.Unbox_Any.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineType.ordinal() << 8 | (byte) StackBehaviour.Popref.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Conv_Ovf_I1 = new OpCode (
		0xff << 0 | 0xb3 << 8 | (byte) Code.Conv_Ovf_I1.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_Ovf_U1 = new OpCode (
		0xff << 0 | 0xb4 << 8 | (byte) Code.Conv_Ovf_U1.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_Ovf_I2 = new OpCode (
		0xff << 0 | 0xb5 << 8 | (byte) Code.Conv_Ovf_I2.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_Ovf_U2 = new OpCode (
		0xff << 0 | 0xb6 << 8 | (byte) Code.Conv_Ovf_U2.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_Ovf_I4 = new OpCode (
		0xff << 0 | 0xb7 << 8 | (byte) Code.Conv_Ovf_I4.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_Ovf_U4 = new OpCode (
		0xff << 0 | 0xb8 << 8 | (byte) Code.Conv_Ovf_U4.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_Ovf_I8 = new OpCode (
		0xff << 0 | 0xb9 << 8 | (byte) Code.Conv_Ovf_I8.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi8.ordinal() << 24);

	public static final OpCode Conv_Ovf_U8 = new OpCode (
		0xff << 0 | 0xba << 8 | (byte) Code.Conv_Ovf_U8.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi8.ordinal() << 24);

	public static final OpCode Refanyval = new OpCode (
		0xff << 0 | 0xc2 << 8 | (byte) Code.Refanyval.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineType.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ckfinite = new OpCode (
		0xff << 0 | 0xc3 << 8 | (byte) Code.Ckfinite.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushr8.ordinal() << 24);

	public static final OpCode Mkrefany = new OpCode (
		0xff << 0 | 0xc6 << 8 | (byte) Code.Mkrefany.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineType.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Ldtoken = new OpCode (
		0xff << 0 | 0xd0 << 8 | (byte) Code.Ldtoken.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineTok.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_U2 = new OpCode (
		0xff << 0 | 0xd1 << 8 | (byte) Code.Conv_U2.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_U1 = new OpCode (
		0xff << 0 | 0xd2 << 8 | (byte) Code.Conv_U1.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_I = new OpCode (
		0xff << 0 | 0xd3 << 8 | (byte) Code.Conv_I.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_Ovf_I = new OpCode (
		0xff << 0 | 0xd4 << 8 | (byte) Code.Conv_Ovf_I.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Conv_Ovf_U = new OpCode (
		0xff << 0 | 0xd5 << 8 | (byte) Code.Conv_Ovf_U.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Add_Ovf = new OpCode (
		0xff << 0 | 0xd6 << 8 | (byte) Code.Add_Ovf.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Add_Ovf_Un = new OpCode (
		0xff << 0 | 0xd7 << 8 | (byte) Code.Add_Ovf_Un.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Mul_Ovf = new OpCode (
		0xff << 0 | 0xd8 << 8 | (byte) Code.Mul_Ovf.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Mul_Ovf_Un = new OpCode (
		0xff << 0 | 0xd9 << 8 | (byte) Code.Mul_Ovf_Un.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Sub_Ovf = new OpCode (
		0xff << 0 | 0xda << 8 | (byte) Code.Sub_Ovf.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Sub_Ovf_Un = new OpCode (
		0xff << 0 | 0xdb << 8 | (byte) Code.Sub_Ovf_Un.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Endfinally = new OpCode (
		0xff << 0 | 0xdc << 8 | (byte) Code.Endfinally.ordinal() << 16 | (byte) FlowControl.Return.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Leave = new OpCode (
		0xff << 0 | 0xdd << 8 | (byte) Code.Leave.ordinal() << 16 | (byte) FlowControl.Branch.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.PopAll.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Leave_S = new OpCode (
		0xff << 0 | 0xde << 8 | (byte) Code.Leave_S.ordinal() << 16 | (byte) FlowControl.Branch.ordinal() << 24,
		(byte) OpCodeType.Macro.ordinal() << 0 | (byte) OperandType.ShortInlineBrTarget.ordinal() << 8 | (byte) StackBehaviour.PopAll.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Stind_I = new OpCode (
		0xff << 0 | 0xdf << 8 | (byte) Code.Stind_I.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi_popi.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Conv_U = new OpCode (
		0xff << 0 | 0xe0 << 8 | (byte) Code.Conv_U.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Arglist = new OpCode (
		0xfe << 0 | 0x00 << 8 | (byte) Code.Arglist.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ceq = new OpCode (
		0xfe << 0 | 0x01 << 8 | (byte) Code.Ceq.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Cgt = new OpCode (
		0xfe << 0 | 0x02 << 8 | (byte) Code.Cgt.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Cgt_Un = new OpCode (
		0xfe << 0 | 0x03 << 8 | (byte) Code.Cgt_Un.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Clt = new OpCode (
		0xfe << 0 | 0x04 << 8 | (byte) Code.Clt.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Clt_Un = new OpCode (
		0xfe << 0 | 0x05 << 8 | (byte) Code.Clt_Un.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1_pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldftn = new OpCode (
		0xfe << 0 | 0x06 << 8 | (byte) Code.Ldftn.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineMethod.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldvirtftn = new OpCode (
		0xfe << 0 | 0x07 << 8 | (byte) Code.Ldvirtftn.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineMethod.ordinal() << 8 | (byte) StackBehaviour.Popref.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Ldarg = new OpCode (
		0xfe << 0 | 0x09 << 8 | (byte) Code.Ldarg.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineArg.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Ldarga = new OpCode (
		0xfe << 0 | 0x0a << 8 | (byte) Code.Ldarga.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineArg.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Starg = new OpCode (
		0xfe << 0 | 0x0b << 8 | (byte) Code.Starg.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineArg.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Ldloc = new OpCode (
		0xfe << 0 | 0x0c << 8 | (byte) Code.Ldloc.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineVar.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push1.ordinal() << 24);

	public static final OpCode Ldloca = new OpCode (
		0xfe << 0 | 0x0d << 8 | (byte) Code.Ldloca.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineVar.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Stloc = new OpCode (
		0xfe << 0 | 0x0e << 8 | (byte) Code.Stloc.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineVar.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Localloc = new OpCode (
		0xfe << 0 | 0x0f << 8 | (byte) Code.Localloc.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Endfilter = new OpCode (
		0xfe << 0 | 0x11 << 8 | (byte) Code.Endfilter.ordinal() << 16 | (byte) FlowControl.Return.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Unaligned = new OpCode (
		0xfe << 0 | 0x12 << 8 | (byte) Code.Unaligned.ordinal() << 16 | (byte) FlowControl.Meta.ordinal() << 24,
		(byte) OpCodeType.Prefix.ordinal() << 0 | (byte) OperandType.ShortInlineI.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Volatile = new OpCode (
		0xfe << 0 | 0x13 << 8 | (byte) Code.Volatile.ordinal() << 16 | (byte) FlowControl.Meta.ordinal() << 24,
		(byte) OpCodeType.Prefix.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Tail = new OpCode (
		0xfe << 0 | 0x14 << 8 | (byte) Code.Tail.ordinal() << 16 | (byte) FlowControl.Meta.ordinal() << 24,
		(byte) OpCodeType.Prefix.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Initobj = new OpCode (
		0xfe << 0 | 0x15 << 8 | (byte) Code.Initobj.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineType.ordinal() << 8 | (byte) StackBehaviour.Popi.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Constrained = new OpCode (
		0xfe << 0 | 0x16 << 8 | (byte) Code.Constrained.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Prefix.ordinal() << 0 | (byte) OperandType.InlineType.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Cpblk = new OpCode (
		0xfe << 0 | 0x17 << 8 | (byte) Code.Cpblk.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi_popi_popi.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Initblk = new OpCode (
		0xfe << 0 | 0x18 << 8 | (byte) Code.Initblk.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Popi_popi_popi.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode No = new OpCode (
		0xfe << 0 | 0x19 << 8 | (byte) Code.No.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Prefix.ordinal() << 0 | (byte) OperandType.ShortInlineI.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Rethrow = new OpCode (
		0xfe << 0 | 0x1a << 8 | (byte) Code.Rethrow.ordinal() << 16 | (byte) FlowControl.Throw.ordinal() << 24,
		(byte) OpCodeType.Objmodel.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);

	public static final OpCode Sizeof = new OpCode (
		0xfe << 0 | 0x1c << 8 | (byte) Code.Sizeof.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineType.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Refanytype = new OpCode (
		0xfe << 0 | 0x1d << 8 | (byte) Code.Refanytype.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Primitive.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop1.ordinal() << 16 | (byte) StackBehaviour.Pushi.ordinal() << 24);

	public static final OpCode Readonly = new OpCode (
		0xfe << 0 | 0x1e << 8 | (byte) Code.Readonly.ordinal() << 16 | (byte) FlowControl.Next.ordinal() << 24,
		(byte) OpCodeType.Prefix.ordinal() << 0 | (byte) OperandType.InlineNone.ordinal() << 8 | (byte) StackBehaviour.Pop0.ordinal() << 16 | (byte) StackBehaviour.Push0.ordinal() << 24);
}
