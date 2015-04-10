package mk.DotNetApp.Core.CIL;

import java.util.LinkedHashMap;
import java.util.Map;

public class InstructionsMap {
	
	public static Map<Integer, String> OneByteOpCode = new LinkedHashMap<Integer, String>();
	static
	{
		OneByteOpCode.put(0x58, "add");  //Add two values, returning a new value.
		OneByteOpCode.put(0xD6, "add.ovf");  //Add signed integer values with overflow check.
		OneByteOpCode.put(0xD7,"add.ovf.un");  //Add unsigned integer values with overflow check.
		OneByteOpCode.put(0x5F, "and");  //Bitwise AND of two integral values, returns an integral value.		
		OneByteOpCode.put(0x3B, "beq <int32 (target)>");  //Branch to target if equal.
		OneByteOpCode.put(0x2E, "beq.s <int8 (target)>");  //Branch to target if equal, short form.
		OneByteOpCode.put(0x3C, "bge <int32 (target)>");  //Branch to target if greater than or equal to. 
		OneByteOpCode.put(0x2F, "bge.s <int8 (target)>");  //Branch to target if greater than or equal to, short form.
		OneByteOpCode.put(0x41, "bge.un <int32 (target)>");  //Branch to target if greater than or equal to (unsigned or unordered).
		OneByteOpCode.put(0x34, "bge.un.s <int8 (target)>");  //Branch to target if greater than or equal to (unsigned or unordered), short form
		OneByteOpCode.put(0x3D, "bgt <int32 (target)>");  //Branch to target if greater than.
		OneByteOpCode.put(0x30, "bgt.s <int8 (target)>");  //Branch to target if greater than, short form.
		OneByteOpCode.put(0x42, "bgt.un <int32 (target)>");  //Branch to target if greater than (unsigned or unordered).
		OneByteOpCode.put(0x35, "bgt.un.s <int8 (target)>");  //Branch to target if greater than (unsigned or unordered), short form.
		OneByteOpCode.put(0x3E, "ble <int32 (target)>");  //Branch to target if less than or equal to.
		OneByteOpCode.put(0x31, "ble.s <int8 (target)>");  //Branch to target if less than or equal to, short form.
		OneByteOpCode.put(0x43, "ble.un <int32 (target)>");  //Branch to target if less than or equal to (unsigned or unordered).
		OneByteOpCode.put(0x36, "ble.un.s <int8 (target)>");  //Branch to target if less than or equal to (unsigned or unordered), short form
		OneByteOpCode.put(0x3F, "blt <int32 (target)>");  //Branch to target if less than.
		OneByteOpCode.put(0x32, "blt.s <int8 (target)>");  //Branch to target if less than, short form.
		OneByteOpCode.put(0x44, "blt.un <int32 (target)>");  //Branch to target if less than (unsigned or unordered).
		OneByteOpCode.put(0x37, "blt.un.s <int8 (target)>");  //Branch to target if less than (unsigned or unordered), short form.
		OneByteOpCode.put(0x40, "bne.un <int32 (target)>");  //Branch to target if unequal or unordered.
		OneByteOpCode.put(0x33, "bne.un.s <int8 (target)>");  //Branch to target if unequal or unordered, short form.
		OneByteOpCode.put(0x8C, "box <typeTok>");  //Convert a boxable value to its boxed form
		OneByteOpCode.put(0x38, "br <int32 (target)>");  //Branch to target.
		OneByteOpCode.put(0x2B, "br.s <int8 (target)>");  //Branch to target, short form.
		OneByteOpCode.put(0x01, "break");  //Inform a debugger that a breakpoint has been reached.
		OneByteOpCode.put(0x39, "brfalse <int32 (target)>");  //Branch to target if value is zero (false).
		OneByteOpCode.put(0x2C, "brfalse.s <int8 (target)>");  //Branch to target if value is zero (false), short form.
		OneByteOpCode.put(0x3A, "brinst <int32 (target)>");  //Branch to target if value is a non-null object reference (alias for brtrue).
		OneByteOpCode.put(0x2D, "brinst.s <int8 (target)>");  //Branch to target if value is a non-null object reference, short form (alias for brtrue.s).
		OneByteOpCode.put(0x39, "brnull <int32 (target)>");  //Branch to target if value is null (alias for brfalse).
		OneByteOpCode.put(0x2C, "brnull.s <int8 (target)>");  //Branch to target if value is null (alias for brfalse.s), short form.
		OneByteOpCode.put(0x3A, "brtrue <int32 (target)>");  //Branch to target if value is non-zero (true).
		OneByteOpCode.put(0x2D, "brtrue.s <int8 (target)>");  //Branch to target if value is non-zero (true), short form.
		OneByteOpCode.put(0x39, "brzero <int32 (target)>");  //Branch to target if value is zero (alias for brfalse).
		OneByteOpCode.put(0x2C, "brzero.s <int8 (target)>");  //Branch to target if value is zero (alias for brfalse.s), short form.
		OneByteOpCode.put(0x28, "call <method>");  //Call method described by method.
		OneByteOpCode.put(0x29, "calli <callsitedescr>");  //Call method indicated on the stack with arguments described by callsitedescr.
		OneByteOpCode.put(0x6F, "callvirt <method>");  //Call a method associated with an object.
		OneByteOpCode.put(0x74, "castclass <class>");  //Cast obj to class.		
		OneByteOpCode.put(0xC3, "ckfinite");  //Throw ArithmeticException if value is not a finite number.		
		OneByteOpCode.put(0xD3, "conv.i");  //Convert to native int, pushing native int on stack.
		OneByteOpCode.put(0x67, "conv.i1");  //Convert to int8, pushing int32 on stack.
		OneByteOpCode.put(0x68, "conv.i2");  //Convert to int16, pushing int32 on stack. 
		OneByteOpCode.put(0x69, "conv.i4");  //Convert to int32, pushing int32 on stack.
		OneByteOpCode.put(0x6A, "conv.i8");  //Convert to int64, pushing int64 on stack.
		OneByteOpCode.put(0xD4, "conv.ovf.i");  //Convert to a native int (on the stack as native int) and throw an exception on overflow.
		OneByteOpCode.put(0x8A, "conv.ovf.i.un");  //Convert unsigned to a native int (on the stack as native int) and throw an exception on overflow.
		OneByteOpCode.put(0xB3, "conv.ovf.i1");  //Convert to an int8 (on the stack as int32) and throw an exception on overflow.
		OneByteOpCode.put(0x82, "conv.ovf.i1.un");  //Convert unsigned to an int8 (on the stack as int32) and throw an exception on overflow.
		OneByteOpCode.put(0xB5, "conv.ovf.i2");  //Convert to an int16 (on the stack as int32) and throw an exception on overflow.
		OneByteOpCode.put(0x83, "conv.ovf.i2.un");  //Convert unsigned to an int16 (on the stack as int32) and throw an exception on overflow.
		OneByteOpCode.put(0xB7, "conv.ovf.i4");  //Convert to an int32 (on the stack as int32) and throw an exception on overflow.
		OneByteOpCode.put(0x84, "conv.ovf.i4.un");  //Convert unsigned to an int32 (on the stack as int32) and throw an exception on overflow.
		OneByteOpCode.put(0xB9, "conv.ovf.i8");  //Convert to an int64 (on the stack as int64) and throw an exception on overflow.
		OneByteOpCode.put(0x85, "conv.ovf.i8.un");  //Convert unsigned to an int64 (on the stack as int64) and throw an exception on overflow.
		OneByteOpCode.put(0xD5, "conv.ovf.u");  //Convert to a native unsigned int (on the stack as native int) and throw an exception on overflow.
		OneByteOpCode.put(0x8B, "conv.ovf.u.un");  //Convert unsigned to a native unsigned int (on the stack as native int) and throw an exception on overflow.
		OneByteOpCode.put(0xB4, "conv.ovf.u1");  //Convert to an unsigned int8 (on the stack as int32) and throw an exception on overflow.
		OneByteOpCode.put(0x86, "conv.ovf.u1.un");  //Convert unsigned to an unsigned int8 (on the stack as int32) and throw an exception on overflow.
		OneByteOpCode.put(0xB6, "conv.ovf.u2");  //Convert to an unsigned int16 (on the stack as int32) and throw an exception on overflow.
		OneByteOpCode.put(0x87, "conv.ovf.u2.un");  //Convert unsigned to an unsigned int16 (on the stack as int32) and throw an exception on overflow.
		OneByteOpCode.put(0xB8, "conv.ovf.u4");  //Convert to an unsigned int32 (on the stack as int32) and throw an exception on overflow.
		OneByteOpCode.put(0x88, "conv.ovf.u4.un");  //Convert unsigned to an unsigned int32 (on the stack as int32) and throw an exception on overflow.
		OneByteOpCode.put(0xBA, "conv.ovf.u8");  //Convert to an unsigned int64 (on the stack as int64) and throw an exception on overflow.
		OneByteOpCode.put(0x89, "conv.ovf.u8.un");  //Convert unsigned to an unsigned int64 (on the stack as int64) and throw an exception on overflow.
		OneByteOpCode.put(0x76, "conv.r.un");  //Convert unsigned integer to floating-point, pushing F on stack.
		OneByteOpCode.put(0x6B, "conv.r4");  //Convert to float32, pushing F on stack.
		OneByteOpCode.put(0x6C, "conv.r8");  //Convert to float64, pushing F on stack.
		OneByteOpCode.put(0xE0, "conv.u");  //Convert to native unsigned int, pushing native int on stack.
		OneByteOpCode.put(0xD2, "conv.u1");  //Convert to unsigned int8, pushing int32 on stack.
		OneByteOpCode.put(0xD1, "conv.u2");  //Convert to unsigned int16, pushing int32 on stack.
		OneByteOpCode.put(0x6D, "conv.u4");  //Convert to unsigned int32, pushing int32 on stack.
		OneByteOpCode.put(0x6E, "conv.u8");  // Convert to unsigned int64, pushing int64 on stack.		
		OneByteOpCode.put(0x70, "cpobj <typeTok>");  //Copy a value type from src to dest.
		OneByteOpCode.put(0x5B, "div");  //Divide two values to return a quotient or floating-point result.
		OneByteOpCode.put(0x5C, "div.un");  //Divide two values, unsigned, returning a quotient.
		OneByteOpCode.put(0x25, "dup");  //Duplicate the value on the top of the stack.
		OneByteOpCode.put(0xDC, "endfault");  //End fault clause of an exception block.		
		OneByteOpCode.put(0xDC, "endfinally");  //End finally clause of an exception block.
		OneByteOpCode.put(0x4C, "idind.u8");  //Indirect load value of type unsigned int64 as int64 on the stack (alias for ldind.i8).
		OneByteOpCode.put(0x75, "isinst <class>");  //Test if obj is an instance of class, returning null or an instance of that class or interface.
		OneByteOpCode.put(0x27, "jmp <method>");  //Exit current method and jump to the specified method.		
		OneByteOpCode.put(0x02, "ldarg.0");  //Load argument 0 onto the stack.
		OneByteOpCode.put(0x03, "ldarg.1");  //Load argument 1 onto the stack.
		OneByteOpCode.put(0x04, "ldarg.2");  //Load argument 2 onto the stack.
		OneByteOpCode.put(0x05, "ldarg.3");  //Load argument 3 onto the stack.
		OneByteOpCode.put(0x0E, "ldarg.s <uint8 (num)>");  //Load argument numbered num onto the stack, short form.		
		OneByteOpCode.put(0x0F, "ldarga.s <uint8 (argNum)>");  //Fetch the address of argument argNum, short form.
		OneByteOpCode.put(0x20, "ldc.i4 <int32 (num)>");  //Push num of type int32 onto the stack as int32.
		OneByteOpCode.put(0x16, "ldc.i4.0");  //Push 0 onto the stack as int32.
		OneByteOpCode.put(0x17, "ldc.i4.1");  //Push 1 onto the stack as int32.
		OneByteOpCode.put(0x18, "ldc.i4.2");  //Push 2 onto the stack as int32.
		OneByteOpCode.put(0x19, "ldc.i4.3");  //Push 3 onto the stack as int32.
		OneByteOpCode.put(0x1A, "ldc.i4.4");  //Push 4 onto the stack as int32.
		OneByteOpCode.put(0x1B, "ldc.i4.5");  //Push 5 onto the stack as int32.
		OneByteOpCode.put(0x1C, "ldc.i4.6");  //Push 6 onto the stack as int32.
		OneByteOpCode.put(0x1D, "ldc.i4.7");  //Push 7 onto the stack as int32.
		OneByteOpCode.put(0x1E, "ldc.i4.8");  //Push 8 onto the stack as int32.
		OneByteOpCode.put(0x15, "ldc.i4.m1");  //Push -1 onto the stack as int32.
		OneByteOpCode.put(0x15, "ldc.i4.M1");  //Push -1 of type int32 onto the stack as int32 (alias for ldc.i4.m1).
		OneByteOpCode.put(0x1F, "ldc.i4.s <int8 (num)>");  //Push num onto the stack as int32, short form.
		OneByteOpCode.put(0x21, "ldc.i8 <int64 (num)>");  //Push num of type int64 onto the stack as int64.
		OneByteOpCode.put(0x22, "ldc.r4 <float32 (num)>");  //Push num of type float32 onto the stack as F.
		OneByteOpCode.put(0x23, "ldc.r8 <float64 (num)>");  //Push num of type float64 onto the stack as F.
		OneByteOpCode.put(0xA3, "ldelem <typeTok>");  //Load the element at index onto the top of the stack.
		OneByteOpCode.put(0x97, "ldelem.i");  //Load the element with type native int at index onto the top of the stack as a native int.
		OneByteOpCode.put(0x90, "ldelem.i1");  //Load the element with type int8 at index onto the top of the stack as an int32.
		OneByteOpCode.put(0x92, "ldelem.i2");  //Load the element with type int16 at index onto the top of the stack as an int32.
		OneByteOpCode.put(0x94, "ldelem.i4");  //Load the element with type int32 at index onto the top of the stack as an int32.
		OneByteOpCode.put(0x96, "ldelem.i8");  //Load the element with type int64 at index onto the top of the stack as an int64.
		OneByteOpCode.put(0x98, "ldelem.r4");  //Load the element with type float32 at index onto the top of the stack as an F
		OneByteOpCode.put(0x99, "ldelem.r8");  //Load the element with type float64 at index onto the top of the stack as an F.
		OneByteOpCode.put(0x9A, "ldelem.ref");  //Load the element at index onto the top of the stack as an O. The type 
												//of the O is the same as the element type of the array pushed on the CIL stack.
		OneByteOpCode.put(0x91, "ldelem.u1");  //Load the element with type unsigned int8 at index onto the top of the stack as an int32.
		OneByteOpCode.put(0x93, "ldelem.u2");  //Load the element with type unsigned int16 at index onto the top of the stack as an int32.
		OneByteOpCode.put(0x95, "ldelem.u4");  //Load the element with type unsigned int32 at index onto the top of the stack as an int32.
		OneByteOpCode.put(0x96, "ldelem.u8");  //Load the element with type unsigned int64 at index onto the top of the stack as an int64 (alias for ldelem.i8).
		OneByteOpCode.put(0x8F, "ldelema <class>");  //Load the address of element at index onto the top of the stack.
		OneByteOpCode.put(0x7B, "ldfld <field>");  //Push the value of field of object (or value type) obj, onto the stack.
		OneByteOpCode.put(0x7C, "ldflda <field>");  //Push the address of field of object obj on the stack.		
		OneByteOpCode.put(0x4D, "ldind.i");  //Indirect load value of type native int as native int on the stack
		OneByteOpCode.put(0x46, "ldind.i1");  //Indirect load value of type int8 as int32 on the stack.
		OneByteOpCode.put(0x48, "ldind.i2");  //Indirect load value of type int16 as int32 on the stack.
		OneByteOpCode.put(0x4A, "ldind.i4");  //Indirect load value of type int32 as int32 on the stack.
		OneByteOpCode.put(0x4C, "ldind.i8");  //Indirect load value of type int64 as int64 on the stack.
		OneByteOpCode.put(0x4E, "ldind.r4");  //Indirect load value of type float32 as F on the stack.
		OneByteOpCode.put(0x4F, "ldind.r8");  //Indirect load value of type float64 as F on the stack.
		OneByteOpCode.put(0x50, "ldind.ref");  //Indirect load value of type object ref as O on the stack.
		OneByteOpCode.put(0x47, "ldind.u1");  //Indirect load value of type unsigned int8 as int32 on the stack
		OneByteOpCode.put(0x49, "ldind.u2");  //Indirect load value of type unsigned int16 as int32 on the stack
		OneByteOpCode.put(0x4B, "ldind.u4");  //Indirect load value of type unsigned int32 as int32 on the stack
		OneByteOpCode.put(0x8E, "ldlen");  //Push the length (of type native unsigned int) of array on the stack.		
		OneByteOpCode.put(0x06, "ldloc.0");  //Load local variable 0 onto stack.
		OneByteOpCode.put(0x07, "ldloc.1");  //Load local variable 1 onto stack.
		OneByteOpCode.put(0x08, "ldloc.2");  //Load local variable 2 onto stack.
		OneByteOpCode.put(0x09, "ldloc.3");  //Load local variable 3 onto stack.
		OneByteOpCode.put(0x11, "ldloc.s <uint8 (indx)>");  //Load local variable of index indx onto stack, short form.		
		OneByteOpCode.put(0x12, "ldloca.s <uint8 (indx)>");  //Load address of local variable with index indx, short form.
		OneByteOpCode.put(0x14, "ldnull");  //Push a null reference on the stack.
		OneByteOpCode.put(0x71, "ldobj <typeTok>");  //Copy the value stored at address src to the stack.
		OneByteOpCode.put(0x7E, "ldsfld <field>");  //Push the value of field on the stack.
		OneByteOpCode.put(0x7F, "ldsflda <field>");  //Push the address of the static field, field, on the stack.
		OneByteOpCode.put(0x72, "ldstr <string>");  //Push a string object for the literal string.
		OneByteOpCode.put(0xD0, "ldtoken <token>");  //Convert metadata token to its runtime representation.		
		OneByteOpCode.put(0xDD, "leave <int32 (target)>");  //Exit a protected region of code.
		OneByteOpCode.put(0xDE, "leave.s <int8 (target)>");  //Exit a protected region of code, short form.		
		OneByteOpCode.put(0xC6, "mkrefany <class>");  //Push a typed reference to ptr of type class onto the stack.
		OneByteOpCode.put(0x5A, "mul");  //Multiply values.
		OneByteOpCode.put(0xD8, "mul.ovf");  //Multiply signed integer values. Signed result shall fit in same size
		OneByteOpCode.put(0xD9, "mul.ovf.un");  //Multiply unsigned integer values. Unsigned result shall fit in same size
		OneByteOpCode.put(0x65, "neg");  //Negate value.
		OneByteOpCode.put(0x8D, "newarr <etype>");  //Create a new array with elements of type etype.
		OneByteOpCode.put(0x73, "newobj <ctor>");  //Allocate an uninitialized object or value type and call ctor.
		OneByteOpCode.put(0x00, "nop");  //Do nothing.
		OneByteOpCode.put(0x66, "not");  //Bitwise complement.
		OneByteOpCode.put(0x60, "or");  //Bitwise OR of two integer values, returns an integer.
		OneByteOpCode.put(0x26, "pop");  //Pop value from the stack.		
		OneByteOpCode.put(0xC2, "refanyval <type>");  //Push the address stored in a typed reference.
		OneByteOpCode.put(0x5D, "rem");  //Remainder when dividing one value by another.
		OneByteOpCode.put(0x5E, "rem.un");  //Remainder when dividing one unsigned value by another.
		OneByteOpCode.put(0x2A, "ret");  //Return from method, possibly with a value.		
		OneByteOpCode.put(0x62, "shl");  //Shift an integer left (shifting in zeros), return an integer.
		OneByteOpCode.put(0x63, "shr");  //Shift an integer right (shift in sign), return an integer.
		OneByteOpCode.put(0x64, "shr.un");  //Shift an integer right (shift in zero), return an integer.		
		OneByteOpCode.put(0x10, "starg.s <uint8 (num)>");  //Store value to the argument numbered num, short form.
		OneByteOpCode.put(0xA4, "stelem <typeTok>");  //Replace array element at index with the value on the stack
		OneByteOpCode.put(0x9B, "stelem.i");  //Replace array element at index with the i value on the stack.
		OneByteOpCode.put(0x9C, "stelem.i1");  //Replace array element at index with the int8 value on the stack.
		OneByteOpCode.put(0x9D, "stelem.i2");  //Replace array element at index with the int16 value on the stack.
		OneByteOpCode.put(0x9E, "stelem.i4");  //Replace array element at index with the int32 value on the stack.
		OneByteOpCode.put(0x9F, "stelem.i8");  //Replace array element at index with the int64 value on the stack.
		OneByteOpCode.put(0xA0, "stelem.r4");  //Replace array element at index with the float32 value on the stack.
		OneByteOpCode.put(0xA1, "stelem.r8");  //Replace array element at index with the float64 value on the stack.
		OneByteOpCode.put(0xA2, "stelem.ref");  //Replace array element at index with the ref value on the stack.
		OneByteOpCode.put(0x7D, "stfld <field>");  //Replace the value of field of the object obj with value.
		OneByteOpCode.put(0xDF, "stind.i"); //Store value of type native int into memory at address
		OneByteOpCode.put(0x52, "stind.i1");  //Store value of type int8 into memory at address
		OneByteOpCode.put(0x53, "stind.i2"); //Store value of type int16 into memory at address
		OneByteOpCode.put(0x54, "stind.i4"); //Store value of type int32 into memory at address
		OneByteOpCode.put(0x55, "stind.i8"); //Store value of type int64 into memory at address
		OneByteOpCode.put(0x56, "stind.r4"); //Store value of type float32 into memory at address
		OneByteOpCode.put(0x57, "stind.r8"); //Store value of type float64 into memory at address
		OneByteOpCode.put(0x51, "stind.ref"); //Store value of type object ref (type O) into memory at address		
		OneByteOpCode.put(0x0A, "stloc.0"); //Pop a value from stack into local variable 0.
		OneByteOpCode.put(0x0B, "stloc.1"); //Pop a value from stack into local variable 1.
		OneByteOpCode.put(0x0C, "stloc.2"); //Pop a value from stack into local variable 2.
		OneByteOpCode.put(0x0D, "stloc.3"); //Pop a value from stack into local variable 3.
		OneByteOpCode.put(0x13, "stloc.s <uint8 (indx)>");//Pop a value from stack into local variable indx, short form.
		OneByteOpCode.put(0x81, "stobj <typeTok>"); //Store a value of type typeTok at an address.
		OneByteOpCode.put(0x80,"stsfld <field>");//Replace the value of field with val.
		OneByteOpCode.put(0x59, "sub");//Subtract value2 from value1, returning a new value.
		OneByteOpCode.put(0xDA,"sub.ovf");//Subtract native int from a native int. Signed result shall fit in same size
		OneByteOpCode.put(0xDB,"sub.ovf.un");  //Subtract native unsigned int from a native unsigned int. Unsigned result shall fit in same size.
		OneByteOpCode.put(0x45, "switch <uint32, int32,int32 (t1..tN)>");  //Jump to one of n values.		
		OneByteOpCode.put(0x7A, "throw");  //Throw an exception.		
		OneByteOpCode.put(0x79, "unbox <valuetype>");  //Extract a value-type from obj, its boxed representation.
		OneByteOpCode.put(0xA5, "unbox.any <typeTok>");  //Extract a value-type from obj, its boxed representation		
		OneByteOpCode.put(0x61, "xor");  //Bitwise XOR of integer values, returns an integer.
	}
	
	/*
	 * these two bytes OpCode are start with "0xFE" as its first byte.
	 */
	public static Map<Integer, String> TwoBytesOpCode = new LinkedHashMap<Integer, String>();
	static
	{
		TwoBytesOpCode.put(0x00, "arglist");  //Return argument list handle for the current method.
		TwoBytesOpCode.put(0x01, "ceq");  //Push 1 (of type int32) if value1 equals value2, else push 0.
		TwoBytesOpCode.put(0x02, "cgt");  //Push 1 (of type int32) if value1 > value2, else push 0.
		TwoBytesOpCode.put(0x03, "cgt.un");  //Push 1 (of type int32) if value1 > value2, unsigned or unordered, else push 0.
		TwoBytesOpCode.put(0x04, "clt");  //Push 1 (of type int32) if value1 < value2, else push 0.
		TwoBytesOpCode.put(0x05, "clt.un");  //Push 1 (of type int32) if value1 < value2, unsigned or unordered, else push 0.
		TwoBytesOpCode.put(0x16, "constrained. <thisType> [prefix]");  //Call a virtual method on a type constrained to be type T
		TwoBytesOpCode.put(0x17, "cpblk");  //Copy data from memory to memory.
		TwoBytesOpCode.put(0x11, "endfilter");  //End an exception handling filter clause.
		TwoBytesOpCode.put(0x18, "initblk");  //Set all bytes in a block of memory to a given byte value.
		TwoBytesOpCode.put(0x15, "initobj <typeTok>");  //Initialize the value at address dest.
		TwoBytesOpCode.put(0x09, "ldarg <uint16 (num)>");  //Load argument numbered num onto the stack.
		TwoBytesOpCode.put(0x0A, "ldarga <uint16 (argNum)>");  //Fetch the address of argument argNum.
		TwoBytesOpCode.put(0x06, "ldftn <method>");  //Push a pointer to a method referenced by method, on the stack.
		TwoBytesOpCode.put(0x0C, "ldloc <uint16 (indx)>");  //Load local variable of index indx onto stack.
		TwoBytesOpCode.put(0x0D, "ldloca <uint16 (indx)>");  //Load address of local variable with index indx.
		TwoBytesOpCode.put(0x07, "ldvirtftn <method>");  //Push address of virtual method method on the stack.
		TwoBytesOpCode.put(0x0F, "localloc");  //Allocate space from the local memory pool.
		TwoBytesOpCode.put(0x19, "no. { typecheck, rangecheck, nullcheck } [prefix]"); //The specified fault check(s) normally performed 
																						//as part of the execution of the subsequent instruction can/shall be skipped.
		TwoBytesOpCode.put(0x1E, "readonly. [prefix]");  //Specify that the subsequent array address operation performs no 
															//type check at runtime, and that it returns a controlled-mutability managed pointer
		TwoBytesOpCode.put(0x1D, "refanytype");  //Push the type token stored in a typed reference.
		TwoBytesOpCode.put(0x1A, "rethrow");  //Rethrow the current exception.
		TwoBytesOpCode.put(0x1C, "sizeof <typeTok>");  //Push the size, in bytes, of a type as an unsigned int32.
		TwoBytesOpCode.put(0x0B, "starg <uint16 (num)>");  //Store value to the argument numbered num.
		TwoBytesOpCode.put(0x0E, "stloc <uint16 (indx)>"); //Pop a value from stack into local variable indx.
		TwoBytesOpCode.put(0x14, "tail. [prefix]");  //Subsequent call terminates current method
		TwoBytesOpCode.put(0x12, "unaligned. (alignment) [prefix]");  //Subsequent pointer instruction might be unaligned.
		TwoBytesOpCode.put(0x13, "volatile. [prefix]");  //Subsequent pointer reference is volatile.
	}
}