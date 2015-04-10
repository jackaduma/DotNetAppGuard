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

import java.util.ArrayList;
import java.util.List;

import mk.DotNetApp.PE.util.DataObject;


public class ResourceDirectory extends DataObject
{
    private ResourceDirectoryTable table;
    private List<ResourceEntry> entries = new ArrayList();

    public ResourceDirectoryTable getTable() {
        return table;
    }

    public void setTable(ResourceDirectoryTable table) {
        this.table = table;
    }

    public void add(ResourceEntry entry) {
        this.entries.add(entry);
    }

    public ResourceEntry get(int index) {
        return entries.get(index);
    }

    public int size() {
        return entries.size();
    }

    public ResourceEntry[] findResources(int type) {
        return findResources(type, -1 - 1);
    }

    public ResourceEntry[] findResources(int type, int name) {
        return findResources(type, name, -1);
    }

    public ResourceEntry[] findResources(int type, int name, int lang) {
        List<ResourceEntry> entries = new ArrayList();
        findResources(type, name, lang, entries);
        return entries.toArray(new ResourceEntry[0]);
    }

    private void findResources(int type, int name, int language,
            List<ResourceEntry> entries) {
        int id = type;
        if (id == -1)
            id = name;
        if (id == -1)
            id = language;
        for (ResourceEntry e : this.entries) {
            if (id == -1 || id == e.getId()) {
                if (e.getData() != null)
                    entries.add(e);
                else {
                    ResourceDirectory rd = e.getDirectory();
                    if (rd != null) {
                        if (type != -1)
                            type = -1;
                        else if (name != -1)
                            name = -1;
                        else
                            language = -1;
                        rd.findResources(type, name, language, entries);
                    }
                }
            }
        }
    }
}