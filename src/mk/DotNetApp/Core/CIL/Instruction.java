package mk.DotNetApp.Core.CIL;

import mk.DotNetApp.Core.CIL.OpCode.Code;
import mk.DotNetApp.Core.CIL.OpCode.OperandType;

/**
 * @author kun_ma
 *
 */
public class Instruction {
	private int offset;
	public OpCode opcode;
	private Object operand;

	Instruction previous;
	Instruction next;

//	SequencePoint sequence_point;

	public int getOffset() {
		return this.offset;
	}	
	public void setOffset(int value) {
		this.offset = value;
	}

	public OpCode getOpCode() {
		 return this.opcode;
	}
	public void setOpCode(OpCode value) { 
		this.opcode = value;
	}
	

	public Object getOperand() {
		return this.operand;
	}
	public void setOperand(Object value) { 
		this.operand = value;
	}

	public Instruction getPrevious() {
		return this.previous;
	}
	public void	setPrevious(Instruction value) { 
		this.previous = value;
	}

	public Instruction getNext() {
		return this.next;
	}
	public void setNext(Instruction value) {
		this.next = value;
	}

//	public SequencePoint SequencePoint {
//		get { return sequence_point; }
//		set { sequence_point = value; }
//	}

	public Instruction (int offset, OpCode opCode)
	{
		this.offset = offset;
		this.opcode = opCode;
	}

	public Instruction (OpCode opcode, Object operand)
	{
		this.opcode = opcode;
		this.operand = operand;
	}

	public int GetSize ()
	{
		int size = opcode.getSize();

		switch (opcode.getOperandType()) {
		case InlineSwitch:
			return size + (1 + ((Instruction []) operand).length) * 4;
		case InlineI8:
		case InlineR:
			return size + 8;
		case InlineBrTarget:
		case InlineField:
		case InlineI:
		case InlineMethod:
		case InlineString:
		case InlineTok:
		case InlineType:
		case ShortInlineR:
		case InlineSig:
			return size + 4;
		case InlineArg:
		case InlineVar:
			return size + 2;
		case ShortInlineBrTarget:
		case ShortInlineI:
		case ShortInlineArg:
		case ShortInlineVar:
			return size + 1;
		default:
			return size;
		}
	}

	public String toString ()
	{
		StringBuilder instruction = new StringBuilder ();

		AppendLabel (instruction, this);
		instruction.append (':');
		instruction.append (' ');
		instruction.append (opcode.getName());

		if (operand == null)
			return instruction.toString ();

		instruction.append (' ');

		switch (opcode.getOperandType()) {
		case ShortInlineBrTarget:
		case InlineBrTarget:
			AppendLabel (instruction, (Instruction) operand);
			break;
		case InlineSwitch:
			Instruction[] labels = (Instruction []) operand;
			for (int i = 0; i < labels.length; i++) {
				if (i > 0)
					instruction.append (',');

				AppendLabel (instruction, labels [i]);
			}
			break;
		case InlineString:
			instruction.append ('\"');
			instruction.append (operand);
			instruction.append ('\"');
			break;
		default:
			instruction.append (operand);
			break;
		}

		return instruction.toString ();
	}

	static void AppendLabel (StringBuilder builder, Instruction instruction)
	{
		builder.append ("IL_");
		builder.append (String.format("%04X", instruction.getOffset()));
	}

	public static Instruction Create (OpCode opcode)
	{
		if (opcode.getOperandType() != OperandType.InlineNone)
			throw new IllegalArgumentException("opcode");

		return new Instruction (opcode, null);
	}

	public static Instruction Create (OpCode opcode, TypeReference type)
	{
		if (type == null)
			throw new IllegalArgumentException ("type");
		if (opcode.getOperandType() != OperandType.InlineType &&
			opcode.getOperandType() != OperandType.InlineTok)
			throw new IllegalArgumentException ("opcode");

		return new Instruction (opcode, type);
	}

	public static Instruction Create (OpCode opcode, EvaluationStack site)
	{
		if (site == null)
			throw new IllegalArgumentException ("site");
		if (opcode.getCode() != Code.Calli)
			throw new IllegalArgumentException ("code");

		return new Instruction (opcode, site);
	}

	public static Instruction Create (OpCode opcode, MethodReference method)
	{
		if (method == null)
			throw new IllegalArgumentException ("method");
		if (opcode.getOperandType() != OperandType.InlineMethod &&
			opcode.getOperandType() != OperandType.InlineTok)
			throw new IllegalArgumentException ("opcode");

		return new Instruction (opcode, method);
	}

	public static Instruction Create (OpCode opcode, FieldReference field)
	{
		if (field == null)
			throw new IllegalArgumentException ("field");
		if (opcode.getOperandType() != OperandType.InlineField &&
			opcode.getOperandType() != OperandType.InlineTok)
			throw new IllegalArgumentException ("opcode");

		return new Instruction (opcode, field);
	}

	public static Instruction Create (OpCode opcode, String value)
	{
		if (value == null)
			throw new IllegalArgumentException ("value");
		if (opcode.getOperandType() != OperandType.InlineString)
			throw new IllegalArgumentException ("opcode");

		return new Instruction (opcode, value);
	}

	/*
	 * // C# sbyte : -128 ~ 127 signed byte
	 */
	public static Instruction Create (OpCode opcode, short value) {
		if (opcode.getOperandType() != OperandType.ShortInlineI &&
			opcode != OpCodes.Ldc_I4_S)
			throw new IllegalArgumentException ("opcode");

		return new Instruction (opcode, value);
	}

	public static Instruction Create (OpCode opcode, byte value) {
		if (opcode.getOperandType() != OperandType.ShortInlineI ||
			opcode == OpCodes.Ldc_I4_S)
			throw new IllegalArgumentException ("opcode");

		return new Instruction (opcode, value);
	}

	public static Instruction Create (OpCode opcode, int value)
	{
		if (opcode.getOperandType() != OperandType.InlineI)
			throw new IllegalArgumentException ("opcode");

		return new Instruction (opcode, value);
	}

	public static Instruction Create (OpCode opcode, long value)
	{
		if (opcode.getOperandType() != OperandType.InlineI8)
			throw new IllegalArgumentException ("opcode");

		return new Instruction (opcode, value);
	}

	public static Instruction Create (OpCode opcode, float value)
	{
		if (opcode.getOperandType() != OperandType.ShortInlineR)
			throw new IllegalArgumentException ("opcode");

		return new Instruction (opcode, value);
	}

	public static Instruction Create (OpCode opcode, double value)
	{
		if (opcode.getOperandType() != OperandType.InlineR)
			throw new IllegalArgumentException ("opcode");

		return new Instruction (opcode, value);
	}

	public static Instruction Create (OpCode opcode, Instruction target)
	{
		if (target == null)
			throw new IllegalArgumentException ("target");
		if (opcode.getOperandType() != OperandType.InlineBrTarget &&
			opcode.getOperandType() != OperandType.ShortInlineBrTarget)
			throw new IllegalArgumentException ("opcode");

		return new Instruction (opcode, target);
	}

	public static Instruction Create (OpCode opcode, Instruction [] targets)
	{
		if (targets == null)
			throw new IllegalArgumentException ("targets");
		if (opcode.getOperandType() != OperandType.InlineSwitch)
			throw new IllegalArgumentException ("opcode");

		return new Instruction (opcode, targets);
	}

	public static Instruction Create (OpCode opcode, VariableDefinition variable)
	{
		if (variable == null)
			throw new IllegalArgumentException ("variable");
		if (opcode.getOperandType() != OperandType.ShortInlineVar &&
			opcode.getOperandType() != OperandType.InlineVar)
			throw new IllegalArgumentException ("opcode");

		return new Instruction (opcode, variable);
	}

	public static Instruction Create (OpCode opcode, ParameterDefinition parameter)
	{
		if (parameter == null)
			throw new IllegalArgumentException ("parameter");
		if (opcode.getOperandType() != OperandType.ShortInlineArg &&
			opcode.getOperandType() != OperandType.InlineArg)
			throw new IllegalArgumentException ("opcode");

		return new Instruction (opcode, parameter);
	}
}
