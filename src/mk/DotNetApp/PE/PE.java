/*******************************************************************************
 * This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     Peter Smith
 *******************************************************************************/
package mk.DotNetApp.PE;

import mk.DotNetApp.PE.CLI.CLRHeader;
import mk.DotNetApp.PE.CLI.TextSection;

/**
 * @author kun_ma
 *
 */
public class PE
{
    private DOSHeader dosHeader;
    private DOSStub stub;
    private PESignature signature;
    private COFFHeader coffHeader;
    private OptionalHeader optionalHeader;
    private ImageData imageData;
    private SectionTable sectionTable;
    
    
    public DOSHeader getDosHeader() {
        return dosHeader;
    }

    public DOSStub getStub() {
        return stub;
    }

    public PESignature getSignature() {
        return signature;
    }

    public COFFHeader getCoffHeader() {
        return coffHeader;
    }

    public OptionalHeader getOptionalHeader() {
        return optionalHeader;
    }

    public SectionTable getSectionTable() {
        return sectionTable;
    }
    
    public void setDosHeader(DOSHeader dosHeader) {
        this.dosHeader = dosHeader;
    }

    public void setStub(DOSStub stub) {
        this.stub = stub;
    }

    public void setSignature(PESignature signature) {
        this.signature = signature;
    }

    public void setCoffHeader(COFFHeader coffHeader) {
        this.coffHeader = coffHeader;
    }

    public void setOptionalHeader(OptionalHeader optionalHeader) {
        this.optionalHeader = optionalHeader;
    }

    public void setSectionTable(SectionTable sectionTable) {
        this.sectionTable = sectionTable;
    }

    public ImageData getImageData() {
        if (imageData == null)
            imageData = new ImageData();
        return imageData;
    }

    public void setImageData(ImageData imageData) {
        this.imageData = imageData;
    }
}
