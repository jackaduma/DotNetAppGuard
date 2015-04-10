package mk.DotNetApp.PE.CLI.MetaDataTables;

import java.io.IOException;

import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 *
 */
public class TableFactory {
	private static final String TAG = "Table Factory";
	
	private IDataReader dr;
	private MetaDataTablesHeader tablesHeader;
	
	public TableFactory(IDataReader dr, MetaDataTablesHeader tablesHeader) {
		this.dr = dr;
		this.tablesHeader = tablesHeader;		
	}
	
	public IMetaDataTables getTable(String tableName) throws IOException {
		IMetaDataTables table = null;
		if ("Module".equals(tableName)) {
			 table = new Module(this.dr, this.tablesHeader);
		}
		else if ("TypeRef".equals(tableName)) {
			table = new TypeRef(this.dr, this.tablesHeader);
		}
		else if ("TypeDef".equals(tableName)) {
			table = new TypeDef(this.dr, this.tablesHeader);
		}
		else if ("FieldPtr".equals(tableName)) {
			table = new FieldPtr(this.dr, this.tablesHeader);
		}
		else if ("Field".equals(tableName)) {
			table = new Field(this.dr, this.tablesHeader);
		}
		else if ("MethodPtr".equals(tableName)) {
			table = new MethodPtr(this.dr, this.tablesHeader);
		}
		else if ("Method".equals(tableName)) {
			table = new Method(this.dr, this.tablesHeader);
		}
		else if ("ParamPtr".equals(tableName)) {
			table = new ParamPtr(this.dr, this.tablesHeader);
		}
		else if ("Param".equals(tableName)) {
			table = new Param(this.dr, this.tablesHeader);
		}
		else if ("InterfaceImpl".equals(tableName)) {
			table = new InterfaceImpl(this.dr, this.tablesHeader);
		}
		else if ("MemberRef".equals(tableName)) {
			table = new MemberRef(this.dr, this.tablesHeader);
		}
		else if ("Constant".equals(tableName)) {
			table = new Constant(this.dr, this.tablesHeader);
		}
		else if ("CustomAttribute".equals(tableName)) {
			table = new CustomAttribute(this.dr, this.tablesHeader);
		}
		else if ("FieldMarshal".equals(tableName)) {
			table = new FieldMarshal(this.dr, this.tablesHeader);
		}
		else if ("DeclSecurity".equals(tableName)) {
			table = new DeclSecurity(this.dr, this.tablesHeader);
		}
		else if ("ClassLayout".equals(tableName)) {
			table = new ClassLayout(this.dr, this.tablesHeader);
		}
		else if ("FieldLayout".equals(tableName)) {
			table = new FieldLayout(this.dr, this.tablesHeader);
		}
		else if ("StandAloneSig".equals(tableName)) {
			table = new StandAloneSig(this.dr, this.tablesHeader);
		}
		else if ("EventMap".equals(tableName)) {
			table = new EventMap(this.dr, this.tablesHeader);
		}
		else if ("EventPtr".equals(tableName)) {
			table = new EventPtr(this.dr, this.tablesHeader);
		}
		else if ("Event".equals(tableName)) {
			table = new Event(this.dr, this.tablesHeader);
		}
		else if ("PropertyMap".equals(tableName)) {
			table = new PropertyMap(this.dr, this.tablesHeader);
		}
		else if ("PropertyPtr".equals(tableName)) {
			table = new PropertyPtr(this.dr, this.tablesHeader);
		}
		else if ("Property".equals(tableName)) {
			table = new Property(this.dr, this.tablesHeader);
		}
		else if ("MethodSemantics".equals(tableName)) {
			table = new MethodSemantics(this.dr, this.tablesHeader);
		}
		else if ("MethodImpl".equals(tableName)) {
			table = new MethodImpl(this.dr, this.tablesHeader);
		}
		else if ("ModuleRef".equals(tableName)) {
			table = new ModuleRef(this.dr, this.tablesHeader);
		}
		else if ("TypeSpec".equals(tableName)) {
			table = new TypeSpec(this.dr, this.tablesHeader);
		}
		else if ("ImplMap".equals(tableName)) {
			table = new ImplMap(this.dr, this.tablesHeader);
		}
		else if ("FieldRVA".equals(tableName)) {
			table = new FieldRVA(this.dr, this.tablesHeader);
		}
		else if ("ENCLog".equals(tableName)) {
			table = new ENCLog(this.dr, this.tablesHeader);
		}
		else if ("ENCMap".equals(tableName)) {
			table = new ENCMap(this.dr, this.tablesHeader);
		}
		else if ("Assembly".equals(tableName)) {
			table = new Assembly(this.dr, this.tablesHeader);
		}
		else if ("AssemblyProcessor".equals(tableName)) {
			table = new AssemblyProcessor(this.dr, this.tablesHeader);
		}
		else if ("AssemblyOS".equals(tableName)) {
			table = new AssemblyOS(this.dr, this.tablesHeader);
		}
		else if ("AssemblyRef".equals(tableName)) {
			table = new AssemblyRef(this.dr, this.tablesHeader);
		}
		else if ("AssemblyRefProcessor".equals(tableName)) {
			table = new AssemblyRefProcessor(this.dr, this.tablesHeader);
		}
		else if ("AssemblyRefOS".equals(tableName)) {
			table = new AssemblyRefOS(this.dr, this.tablesHeader);
		}
		else if ("File".equals(tableName)) {
			table = new File(this.dr, this.tablesHeader);
		}
		else if ("ExportedType".equals(tableName)) {
			table = new ExportedType(this.dr, this.tablesHeader);
		}
		else if ("ManifestResource".equals(tableName)) {
			table = new ManifestResource(this.dr, this.tablesHeader);
		}
		else if ("NestedClass".equals(tableName)) {
			table = new NestedClass(this.dr, this.tablesHeader);
		}
		else if ("GenericParam".equals(tableName)) {
			table = new GenericParam(this.dr, this.tablesHeader);
		}
		else if ("MethodSpec".equals(tableName)) {
			table = new MethodSpec(this.dr, this.tablesHeader);
		}
		else if ("GenericParamConstraint".equals(tableName)) {
			table = new GenericParamConstraint(this.dr, this.tablesHeader);
		}		
		
		return table;
	}

}
