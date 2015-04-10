package mk.DotNetApp.PE.CLI.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.LinkedHashMap;

import mk.DotNetApp.PE.PE;
import mk.DotNetApp.PE.CLI.CLRHeader;
import mk.DotNetApp.PE.CLI.MetaDataHeader;
import mk.DotNetApp.PE.CLI.MetaDataTablesData;
import mk.DotNetApp.PE.CLI.MetaDataTablesHeader;
import mk.DotNetApp.PE.CLI.StreamHeader;
import mk.DotNetApp.PE.CLI.TextSection;
import mk.DotNetApp.PE.CLI.Heaps.BlobHeap;
import mk.DotNetApp.PE.CLI.Heaps.GUIDHeap;
import mk.DotNetApp.PE.CLI.Heaps.StringsHeap;
import mk.DotNetApp.PE.CLI.Heaps.USHeap;
import mk.DotNetApp.PE.CLI.MetaDataTables.IMetaDataTables;
import mk.DotNetApp.PE.CLI.MetaDataTables.Method;
import mk.DotNetApp.PE.CLI.MetaDataTables.TableFactory;
import mk.DotNetApp.PE.CLI.MetaDataTables.MethodHandler.AMethod;
import mk.DotNetApp.PE.io.ByteArrayDataReader;
import mk.DotNetApp.PE.io.DataReader;
import mk.DotNetApp.PE.io.IDataReader;


/**
 * @author kun_ma
 *
 */
public class TextSectionParser {
	
	private PE pe;
	private String fileName;
	
	
	public TextSectionParser(PE pe, String filePath) {
		this.pe = pe;
		this.fileName = filePath;
	}
	
	public TextSection parse(InputStream is) throws IOException {
        return read(new DataReader(is));
    }

    public TextSection parse(String filename) throws IOException {
    	this.fileName = filename;
        return parse(new File(filename));
    }

    public TextSection parse(File file) throws IOException {
        return read(new DataReader(new FileInputStream(file)));
    }
    
    public TextSection read(IDataReader dr) throws IOException {
    	TextSection textSection = new TextSection();
    	
    	int textSectionPointerToRawData = pe.getSectionTable().findHeader(".text").getPointerToRawData();
    	textSection.setPointerToRawData(textSectionPointerToRawData);
    	
    	int textSectionRVA = pe.getSectionTable().findHeader(".text").getVirtualAddress();
    	textSection.setSectionRVA(textSectionRVA);
    	
    	// set CLR Header
    	CLRHeader clrHeader = this.pe.getImageData().getClrRuntimeHeader();
    	textSection.setCLRHeader(clrHeader);
    	
    	int metaDataRVA = clrHeader.getMetaData().getVirtualAddress();
    	int metaDataOffset = this.getDataOffset(pe, metaDataRVA);  // it's important
    	dr.jumpTo(metaDataOffset);
    	byte[] bMetaData = new byte[clrHeader.getMetaData().getSize()];
        dr.read(bMetaData);
        
        ByteArrayDataReader badr = new ByteArrayDataReader(bMetaData);
        
        // set Meta Data Header
        MetaDataHeader metaDataHeader = readMetaDataHeader(badr, metaDataOffset);
        textSection.setMetaDataHeader(metaDataHeader);
        
        // set Meta Data Tables Header, mean header of the stream #~ 
        MetaDataTablesHeader metaDataTablesHeader = readMetaDataTablesHeader(badr);
        textSection.setMetaDataTablesHeader(metaDataTablesHeader);
        
        textSection.getMetaDataTablesHeader().setSectionPointerToRawData(textSection.getPointerToRawData());
        textSection.getMetaDataTablesHeader().setSectionRVA(textSection.getSectionRVA());
        
        int curPosition = badr.getPosition();  // record current position and jump to deal with heap data
        
        // set Heap Data
        if (metaDataHeader.ExistBlobStream()) {
        	byte[] BlobStreamData = new byte[(int) metaDataHeader.getStreamHeader("#Blob").getStreamSize()];
        	int pointerToRawData = metaDataHeader.getStreamHeader("#Blob").getStreamDataPointerToRawData();
        	int offset = pointerToRawData - metaDataOffset;
        	badr.jumpTo(offset);
        	badr.read(BlobStreamData);
        	BlobHeap blobHeap = new BlobHeap(BlobStreamData);
        	blobHeap.setWideOfIndex(metaDataTablesHeader.getWideOfBlobStreamIndex());
            textSection.setBlobHeap(blobHeap);
            textSection.getMetaDataTablesHeader().setBlobHeap(blobHeap);
//            System.out.println(String.format("Blob Heap: %02X %02X %02X", blobHeap.getData()[0], blobHeap.getData()[1], blobHeap.getData()[2]));
        }
        
        if (metaDataHeader.ExistGUIDStream()) {
        	byte[] GUIDStreamData = new byte[(int) metaDataHeader.getStreamHeader("#GUID").getStreamSize()];
        	int pointerToRawData = metaDataHeader.getStreamHeader("#GUID").getStreamDataPointerToRawData();
        	int offset = pointerToRawData - metaDataOffset;
        	badr.jumpTo(offset);
        	badr.read(GUIDStreamData);
        	GUIDHeap guidHeap = new GUIDHeap(GUIDStreamData);
        	guidHeap.setWideOfIndex(metaDataTablesHeader.getWideOfGUIDStreamIndex());
        	textSection.setGUIDHeap(guidHeap);
        	textSection.getMetaDataTablesHeader().setGUIDHeap(guidHeap);
//        	System.out.println(String.format("GUID Heap: %02X %02X %02X", guidHeap.getData()[0], guidHeap.getData()[1], guidHeap.getData()[2]));
        }
        
        if (metaDataHeader.ExistStringStream()) {
        	byte[] StringsStreamData = new byte[(int) metaDataHeader.getStreamHeader("#Strings").getStreamSize()];
        	int pointerToRawData = metaDataHeader.getStreamHeader("#Strings").getStreamDataPointerToRawData();
        	int offset = pointerToRawData - metaDataOffset;
        	badr.jumpTo(offset);
        	badr.read(StringsStreamData);
        	StringsHeap stringsHeap = new StringsHeap(StringsStreamData);
        	stringsHeap.setWideOfIndex(metaDataTablesHeader.getWideOfStringStreamIndex());
        	textSection.setStringsHeap(stringsHeap);
        	textSection.getMetaDataTablesHeader().setStringsHeap(stringsHeap);
//        	System.out.println(String.format("Strings Heap: %02X %02X %02X", stringsHeap.getData()[0], stringsHeap.getData()[1], stringsHeap.getData()[2]));
        }
        
        if (metaDataHeader.ExistUSStream()) {
        	byte[] USStreamData = new byte[(int) metaDataHeader.getStreamHeader("#US").getStreamSize()];
        	int pointerToRawData = metaDataHeader.getStreamHeader("#US").getStreamDataPointerToRawData();
        	int offset = pointerToRawData - metaDataOffset;
        	badr.jumpTo(offset);
        	badr.read(USStreamData);
        	USHeap usHeap = new USHeap(USStreamData);
        	textSection.setUSHeap(usHeap);
        	textSection.getMetaDataTablesHeader().setUSHeap(usHeap);
//        	System.out.println(String.format("US Heap: %02X %02X %02X", usHeap.getData()[0], usHeap.getData()[1], usHeap.getData()[2]));
        }
        
        badr.jumpTo(curPosition);  // when heap data was done, then jump back to last position.
        
        textSection.setMetaDataTablesData(readMetaDataTablesData(badr, metaDataTablesHeader));
        textSection.getMetaDataTablesHeader().setTablesData(textSection.getMetaDataTablesData().getTablesData());
        
        // handle Reference in each MetaDataTables
        
        return textSection;
    }
    
    private int getDataOffset(PE pe, int dataRVA, String sectionName) {
    	int sectionRVA = pe.getSectionTable().findHeader(sectionName).getVirtualAddress();
    	int sectionOffset = pe.getSectionTable().findHeader(sectionName).getPointerToRawData();
    	int dataOffset = (dataRVA - sectionRVA) + sectionOffset;
    	return dataOffset;
    }
    
    private int getDataOffset(PE pe, int dataRVA) {
    	return getDataOffset(pe, dataRVA, ".text");
    }
    
    private int getDataOffset(PE pe, int dataRVA, int sectionIndex) {
    	int sectionRVA = pe.getSectionTable().getHeader(sectionIndex).getVirtualAddress();
    	int sectionOffset = pe.getSectionTable().getHeader(sectionIndex).getPointerToRawData();
    	int dataOffset = (dataRVA - sectionRVA) + sectionOffset;
    	return dataOffset;    	
    }
    
    private MetaDataHeader readMetaDataHeader(IDataReader dr, int pointerToRawData) throws IOException {
    	MetaDataHeader metaDataHeader = new MetaDataHeader();
    	
    	metaDataHeader.setPointerToRawData(pointerToRawData);
        metaDataHeader.setSignature(dr.readDoubleWord());
        metaDataHeader.setMajorVersion(dr.readWord());
        metaDataHeader.setMinorVersion(dr.readWord());
        metaDataHeader.setReserved(dr.readDoubleWord());
        metaDataHeader.setVersionLength(dr.readDoubleWord());
        metaDataHeader.setVersionString(dr.readUtf((int) metaDataHeader.getVersionLength()));
        metaDataHeader.setFlags(dr.readWord());
        metaDataHeader.setNumberOfStreams(dr.readWord());
        
        StreamHeader[] streamHeaders = new StreamHeader[metaDataHeader.getNumberOfStreams()];
        StreamHeader streamHeader = null;
        for (int i=0; i<metaDataHeader.getNumberOfStreams(); i++) {
        	streamHeader = readStreamHeader(dr);
        	int addr = (int) (metaDataHeader.getPointerToRawData() + streamHeader.getOffset());
        	streamHeader.setStreamDataPointerToRawData(addr);
        	
        	String streamName = streamHeader.getName();
        	if (streamName.equals("#~")) {
        		metaDataHeader.setExistMetaDataTablesStream();
        	}
        	else if (streamName.equals("#Strings")) {
        		metaDataHeader.setExistStringStream();
        	}
        	else if (streamName.equals("#GUID")) {
        		metaDataHeader.setExistGUIDStream();
        	}
        	else if (streamName.equals("#US")) {
        		metaDataHeader.setExistUSStream();
        	}
        	else if (streamName.equals("#Blob")) {
        		metaDataHeader.setExistBlobStream();
        	}
        	else if (streamName.equals("#-")) {
        		metaDataHeader.setExistUnOptimizedStream();
        	}
        	
        	streamHeaders[i] = streamHeader;        	
        }
        
        metaDataHeader.setStreamHeaders(streamHeaders);
    	
    	return metaDataHeader;
    }
    
    private StreamHeader readStreamHeader(IDataReader dr) throws IOException {
    	StreamHeader streamHeader = new StreamHeader();
    	
    	streamHeader.setOffset(dr.readDoubleWord());
    	streamHeader.setStreamSize(dr.readDoubleWord());
    	
    	String name = null;
    	String c = dr.readUtf(4);
    	if (c.startsWith("#")) {    		
//    		System.out.println(c.getBytes().length);
//    		System.out.println(c.getBytes()[0]);
//    		System.out.println(c.getBytes()[1]);
//    		System.out.println(c.getBytes()[2]);
//    		System.out.println(c.getBytes()[3]);
    		name = c;
    		while (0!=c.getBytes()[3]) {
    			c = dr.readUtf(4);
    			name += c;
    		}
    	}
    	
    	if (name.startsWith("#~")){
    		name = "#~";
    	}
    	else if (name.startsWith("#-")) {
    		name = "#-";
    	}
    	else if (name.startsWith("#Strings")){
    		name = "#Strings";
    	}
    	else if (name.startsWith("#US")) {
    		name = "#US";
    	}
    	else if (name.startsWith("#GUID")) {
    		name = "#GUID";
    	}
    	else if (name.startsWith("#Blob")) {
    		name = "#Blob";
    	}
    	
    	streamHeader.setName(name);
    	
    	return streamHeader;
    }
    
    private MetaDataTablesHeader readMetaDataTablesHeader(IDataReader dr) throws IOException {
    	MetaDataTablesHeader metaDataTablesHeader = new MetaDataTablesHeader();
    	
    	metaDataTablesHeader.setReserved1(dr.readDoubleWord());
    	metaDataTablesHeader.setMajorVersion(dr.readByte());
    	metaDataTablesHeader.setMinorVersion(dr.readByte());
    	metaDataTablesHeader.setHeapOffsetSizes(dr.readByte());
    	metaDataTablesHeader.setReserved2(dr.readByte());
    	metaDataTablesHeader.setMaskValid(dr.readLong());
    	metaDataTablesHeader.setMaskSorted(dr.readLong());
    	
    	metaDataTablesHeader.setValidTables(metaDataTablesHeader.getMaskValid());
    	metaDataTablesHeader.setSortedTables(metaDataTablesHeader.getMaskSorted());
    	
    	int num = metaDataTablesHeader.getNumOfTables();
    	Map<String, Integer> recordOfEachTable = new LinkedHashMap<String, Integer>();
    	Map<String, Integer> wideOfIndexToTable = new LinkedHashMap<String, Integer>();
//    	System.out.println(num);    	
    	for (int i=0; i<num; i++) {
    		int record = dr.readDoubleWord();
    		String tableName = metaDataTablesHeader.getValidTables().get(i);
    		recordOfEachTable.put(tableName, record);
    		if (record>0xFFFF) {
    			wideOfIndexToTable.put(tableName, 4);
    		}
    		else {
    			wideOfIndexToTable.put(tableName, 2);
    		}
//    		System.out.println(String.format("%s:  %s", tableName, record));
    	}
    	metaDataTablesHeader.setRecordOfEachTable(recordOfEachTable);
    	metaDataTablesHeader.setWideOfIndexToTable(wideOfIndexToTable);
    	
    	return metaDataTablesHeader;
    }
    
    private MetaDataTablesData readMetaDataTablesData(IDataReader dr, MetaDataTablesHeader metaDataTablesHeader) throws IOException {
    	MetaDataTablesData metaDataTablesData = new MetaDataTablesData();
    	
    	Map<String, List<IMetaDataTables>> TablesData = new LinkedHashMap<String, List<IMetaDataTables>>();
    	
    	Map<String, Integer> recordOfEachTable = metaDataTablesHeader.getRecordOfEachTable();
    	Iterator<Entry<String, Integer>> iter = recordOfEachTable.entrySet().iterator();
		while(iter.hasNext()) {
			Entry<String, Integer> e = (Entry<String, Integer>) iter.next();
			String tableName = e.getKey();
			int recordNum = e.getValue();
			
			// get the Object of Table Entry Class
			List<IMetaDataTables> entryList = new ArrayList<IMetaDataTables>();
//			int methodIndex = 0;
			for (int i=0; i<recordNum; i++) {				
//				if (tableName.equals("Method")) {
//					methodIndex += 1;
//					System.out.println("--------<"+methodIndex+">--------");
//				}
				
				IMetaDataTables entry = readEntryByTableName(dr, tableName, metaDataTablesHeader);
				entryList.add(entry);
			}
			
			TablesData.put(tableName, entryList);
		}
		metaDataTablesData.setTablesData(TablesData);
		
		return metaDataTablesData;
    }
    

    private IMetaDataTables readEntryByTableName(IDataReader dr, String tableName, MetaDataTablesHeader metaDataTablesHeader) throws IOException {
    	TableFactory factory = new TableFactory(dr, metaDataTablesHeader);
    	IMetaDataTables table = factory.getTable(tableName);
    	
		if (tableName.equals("Method")) {
//			System.out.println("-----------------------"+((Method)table).getName()+"-----------------------");
			File f = new File(this.fileName);
			FileInputStream fi = new FileInputStream(f);
			DataReader d = new DataReader(fi);
//			AMethod e = new AMethod(d, ((Method)table).getPointerToRawData());
			fi.close();
			d.close();
		}
		
    	return table;
    }

}
