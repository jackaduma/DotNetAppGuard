package mk.DotNetApp.PE.CLI.MetaDataTables.MethodHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import mk.DotNetApp.PE.CLI.MetaDataTables.IMetaDataTables;
import mk.DotNetApp.PE.io.IDataReader;

/**
 * @author kun_ma
 *
 */
public class AMethod {
	private MethodHeader header;
	private MethodBody body;
	
	public AMethod(IDataReader dr, int position, Map<String, List<IMetaDataTables>> tables) throws IOException {
		System.out.println("Position is "+position);
		if (position<=0) {
			System.out.println();
			return;
		}
		dr.jumpTo(position);
		
		MethodHeader h = new MethodHeader(dr);
		this.setMethodHeader(h);
		
		System.out.println("Size is "+this.getMethodHeader().getCodeSize());
		
		byte[] b = new byte[(int) h.getCodeSize()];
		dr.read(b);
		this.body = new MethodBody(b, this.header, tables);
	}

	public MethodHeader getMethodHeader() {
		return this.header;
	}
	
	public void setMethodHeader(MethodHeader header) {
		this.header = header;
	}
	
	public MethodBody getMethodBody() {
		return this.body;
	}
	
	public void setMethodBody(MethodBody b) {
		this.body = b;
	}
}
