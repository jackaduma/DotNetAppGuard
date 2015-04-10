/*******************************************************************************
 * This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     Peter Smith
 *******************************************************************************/
package mk.DotNetApp.PE.resources;

public class StringTable
{
    private int length;
    private int valueLength;
    private int type;
    private String key;
    private int padding;

    public int getLength() {
        return length;
    }

    public int getValueLength() {
        return valueLength;
    }

    public int getType() {
        return type;
    }

    public int getPadding() {
        return padding;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setValueLength(int valueLength) {
        this.valueLength = valueLength;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }
}
