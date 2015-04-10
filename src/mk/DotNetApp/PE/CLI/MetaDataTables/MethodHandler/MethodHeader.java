package mk.DotNetApp.PE.CLI.MetaDataTables.MethodHandler;

import java.io.IOException;

import mk.DotNetApp.PE.io.IDataReader;

/**
 * @author kun_ma
 *
 */
public class MethodHeader {
	
	/*
	 * constant
	 */
	public static final int TinyFormat = 0x02;  //Method header is Tiny.
	public static final int FatFormat = 0x03;   //Method header is Fat.
	
	/*
	 * that is, an exception handling table is present 
	 */
	private int MoreSects = 0x08;   //More sections follow after this header.
	private boolean hasExceptonHandlingTable;
	/*
	 * which indicates that local variables will be initialized to 0 automatically on entry to method.
	 */
	private int InitLocals = 0x10;  //Call default constructor on all local variables.
	private boolean isInitLocals;
	
	private int format;
	private long codeSize;
	
	private int headerSize;
	/*
	 * MaxStack is the maximal evaluation stack depth in slots. Stack size in IL is measured
not in bytes but in slots, with each slot able to accept one item regardless of the item¡¯s size.
	 */
	private int maxStack;
	/*
	 * LocalVarSigTok is the token of the local variables signature (token type 0x11000000).
	 */
	private long LocalVarSigTok;
	
	public MethodHeader(IDataReader dr) throws IOException {
		int firstByte = dr.readByte();
		switch (firstByte&0x03) {
		case TinyFormat:
			this.format = TinyFormat;
			this.codeSize = firstByte>>2;
			this.maxStack = 8;  // default
			break;
		case FatFormat:
			this.format = FatFormat;
			if (MoreSects==(firstByte&MoreSects)) {
				this.hasExceptonHandlingTable = true;
			}
			else {
				this.hasExceptonHandlingTable = false;
			}
			
			if (InitLocals==(firstByte&InitLocals)) {
				this.isInitLocals = true;
			}
			else {
				this.isInitLocals = false;
			}
			
			int secondByte = dr.readByte();
			this.headerSize = secondByte >> 4;
			
			this.maxStack = dr.readWord();
			
			this.codeSize = dr.readDoubleWord();
			
			this.LocalVarSigTok = dr.readDoubleWord();
			break;

		default:
			throw new IOException();
		}
	}
	
	public int getFormat() {
		return this.format;
	}
	
	public int getMaxStack() {
		return this.maxStack;
	}
	
	public long getCodeSize() {
		return this.codeSize;
	}
	
	public int getHeaderSize() {
		return this.headerSize;
	}
	
	public long getLocalVarSigTok() {
		return this.LocalVarSigTok;
	}
	
	public boolean HasExceptonHandlingTable() {
		return this.hasExceptonHandlingTable;
	}
	
	public boolean IsInitLocals() {
		return this.isInitLocals;
	}
	
}
