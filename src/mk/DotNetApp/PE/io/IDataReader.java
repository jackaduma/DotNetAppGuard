package mk.DotNetApp.PE.io;

import java.io.IOException;

/**
 * @author makun
 *
 */
public interface IDataReader {
	
	public abstract int readByte() throws IOException;

    public abstract int readWord() throws IOException;

    public abstract int readDoubleWord() throws IOException;
    
    public abstract long readLong() throws IOException;

    public abstract int getPosition();

    public abstract void jumpTo(int location) throws IOException;

    public abstract void skipBytes(int numBytes) throws IOException;

    public abstract void close() throws IOException;

    public abstract void read(byte[] b) throws IOException;

    public abstract String readUtf(int size) throws IOException;

    public abstract String readUtf() throws IOException;

    public abstract String readUnicode() throws IOException;

    public abstract String readUnicode(int size) throws IOException;

}
