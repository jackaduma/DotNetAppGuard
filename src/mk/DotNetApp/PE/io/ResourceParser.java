/*******************************************************************************
 * This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     Peter Smith
 *******************************************************************************/
package mk.DotNetApp.PE.io;

import java.io.IOException;

import mk.DotNetApp.PE.resources.Bitmap;
import mk.DotNetApp.PE.resources.BitmapFileHeader;
import mk.DotNetApp.PE.resources.BitmapInfoHeader;
import mk.DotNetApp.PE.resources.FixedFileInfo;
import mk.DotNetApp.PE.resources.IconDirectory;
import mk.DotNetApp.PE.resources.IconDirectoryEntry;
import mk.DotNetApp.PE.resources.IconImage;
import mk.DotNetApp.PE.resources.Manifest;
import mk.DotNetApp.PE.resources.RGBQuad;
import mk.DotNetApp.PE.resources.StringFileInfo;
import mk.DotNetApp.PE.resources.StringPair;
import mk.DotNetApp.PE.resources.StringTable;
import mk.DotNetApp.PE.resources.VarFileInfo;
import mk.DotNetApp.PE.resources.VersionInfo;


public class ResourceParser
{
    public static Bitmap readBitmap(IDataReader dr) throws IOException {
        Bitmap bm = new Bitmap();
        bm.setFileHeader(readBitmapFileHeader(dr));
        bm.setInfoHeader(readBitmapInfoHeader(dr));

        return bm;
    }

    public static BitmapFileHeader readBitmapFileHeader(IDataReader dr)
            throws IOException {
        BitmapFileHeader bfh = new BitmapFileHeader();
        bfh.setType(dr.readWord());
        bfh.setSize(dr.readDoubleWord());
        bfh.setReserved1(dr.readWord());
        bfh.setReserved2(dr.readWord());
        bfh.setOffBits(dr.readDoubleWord());

        return bfh;
    }

    public static BitmapInfoHeader readBitmapInfoHeader(IDataReader dr)
            throws IOException {
        BitmapInfoHeader bh = new BitmapInfoHeader();
        bh.setSize(dr.readDoubleWord());
        bh.setWidth(dr.readDoubleWord());
        bh.setHeight(dr.readDoubleWord());
        bh.setPlanes(dr.readWord());
        bh.setBitCount(dr.readWord());
        bh.setCompression(dr.readDoubleWord());
        bh.setSizeImage(dr.readDoubleWord());
        bh.setXpelsPerMeter(dr.readDoubleWord());
        bh.setYpelsPerMeter(dr.readDoubleWord());
        bh.setClrUsed(dr.readDoubleWord());
        bh.setClrImportant(dr.readDoubleWord());

        return bh;
    }

    public static FixedFileInfo readFixedFileInfo(IDataReader dr)
            throws IOException {
        FixedFileInfo ffi = new FixedFileInfo();
        ffi.setSignature(dr.readDoubleWord());
        ffi.setStrucVersion(dr.readDoubleWord());
        ffi.setFileVersionMS(dr.readDoubleWord());
        ffi.setFileVersionLS(dr.readDoubleWord());
        ffi.setProductVersionMS(dr.readDoubleWord());
        ffi.setProductVersionLS(dr.readDoubleWord());
        ffi.setFileFlagMask(dr.readDoubleWord());
        ffi.setFileFlags(dr.readDoubleWord());
        ffi.setFileOS(dr.readDoubleWord());
        ffi.setFileType(dr.readDoubleWord());
        ffi.setFileSubtype(dr.readDoubleWord());
        ffi.setFileDateMS(dr.readDoubleWord());
        ffi.setFileDateLS(dr.readDoubleWord());
        return ffi;
    }

    public static IconImage readIconImage(IDataReader dr, int bytesInRes)
            throws IOException {
        IconImage ii = new IconImage();
        int quadSize = 0;
        ii.setHeader(readBitmapInfoHeader(dr));
        if (ii.getHeader().getClrUsed() != 0) {
            quadSize = ii.getHeader().getClrUsed();
        } else {
            if (ii.getHeader().getBitCount() <= 8) {
                quadSize = 1 << ii.getHeader().getBitCount();
            } else {
                quadSize = 0;
            }
        }

        int numBytesPerLine = ((((ii.getHeader().getWidth() *
                ii.getHeader().getPlanes() * ii.getHeader().getBitCount()) + 31) >> 5) << 2);
        int xorSize = numBytesPerLine * ii.getHeader().getHeight() / 2;
        int andSize = bytesInRes - (quadSize * 4) - ii.getHeader().getSize() -
                xorSize;

        if (quadSize > 0) {
            RGBQuad[] colors = new RGBQuad[quadSize];
            for (int i = 0; i < quadSize; i++) {
                colors[i] = readRGB(dr);
            }
            ii.setColors(colors);
        }

        byte[] xorMask = new byte[xorSize];
        dr.read(xorMask);
        ii.setXorMask(xorMask);

        byte[] andMask = new byte[andSize];
        dr.read(andMask);
        ii.setAndMask(andMask);

        return ii;
    }

    public static IconImage readPNG(byte[] data) {
        IconImage ii = new IconImage();
        ii.setPngData(data);
        return ii;
    }

    public static VersionInfo readVersionInfo(byte[] data) throws IOException {
        return readVersionInfo(new DataReader(data));
    }

    public static VersionInfo readVersionInfo(IDataReader dr)
            throws IOException {
        VersionInfo vi = new VersionInfo();
        vi.setLength(dr.readWord());
        vi.setValueLength(dr.readWord());
        vi.setType(dr.readWord());
        vi.setKey(dr.readUnicode());
        if (vi.getKey().length() % 2 == 1)
            dr.readWord(); // padding
        vi.setFixedFileInfo(ResourceParser.readFixedFileInfo(dr));

        int length = dr.readWord(); // length
        dr.readWord(); // value length
        dr.readWord(); // type
        vi.setStringFileInfo(readStringFileInfo(dr, length));

        return vi;
    }

    public static VarFileInfo readVarFileInfo(IDataReader dr)
            throws IOException {
        VarFileInfo vfi = new VarFileInfo();
        vfi.setKey(dr.readUnicode());
        String name = null;
        while ((name = dr.readUnicode()) != null) {
            if (name.length() % 2 == 1)
                dr.readWord();
            vfi.add(name, dr.readUnicode());
        }
        return vfi;
    }

    public static StringTable readStringTable(IDataReader dr)
            throws IOException {
        StringTable vfi = new StringTable();
        vfi.setLength(dr.readWord());
        if (vfi.getLength() == 0) {
            return null;
        }
        vfi.setValueLength(dr.readWord());
        vfi.setType(dr.readWord());
        vfi.setKey(dr.readUnicode());
        if (vfi.getKey().length() % 2 == 1) {
            dr.readWord(); // padding
            vfi.setPadding(2);
        }
        return vfi;
    }

    public static StringPair readStringPair(IDataReader dr) throws IOException {
        StringPair sp = new StringPair();
        sp.setLength(dr.readWord());
        sp.setValueLength(dr.readWord());
        sp.setType(dr.readWord());
        sp.setKey(dr.readUnicode());
        if (sp.getKey().length() % 2 == 0) {
            dr.readWord();
            sp.setPadding(2);
        }
        sp.setValue(dr.readUnicode());
        return sp;
    }

    public static Manifest readManifest(IDataReader dr, int length)
            throws IOException {
        Manifest mf = new Manifest();
        mf.set(dr.readUtf(length));
        return mf;
    }

    public static RGBQuad readRGB(IDataReader dr) throws IOException {
        RGBQuad r = new RGBQuad();
        r.setBlue(dr.readByte());
        r.setGreen(dr.readByte());
        r.setRed(dr.readByte());
        r.setReserved(dr.readByte());
        return r;
    }

    public static StringFileInfo readStringFileInfo(IDataReader dr, int length)
            throws IOException {
        StringFileInfo sfi = new StringFileInfo();

        return sfi;
    }

    public static IconDirectoryEntry readIconDirectoryEntry(IDataReader dr)
            throws IOException {
        IconDirectoryEntry ge = new IconDirectoryEntry();
        ge.setWidth(dr.readByte());
        ge.setHeight(dr.readByte());
        ge.setColorCount(dr.readByte());
        ge.setReserved(dr.readByte());
        ge.setPlanes(dr.readWord());
        ge.setBitCount(dr.readWord());
        ge.setBytesInRes(dr.readDoubleWord());
        ge.setOffset(dr.readDoubleWord());

        return ge;
    }

    public static IconDirectory readIconDirectory(IDataReader dr)
            throws IOException {
        IconDirectory gi = new IconDirectory();
        gi.setReserved(dr.readWord());
        gi.setType(dr.readWord());
        int count = dr.readWord();
        for (int i = 0; i < count; i++) {
            gi.add(readIconDirectoryEntry(dr));
        }

        return gi;
    }
}
