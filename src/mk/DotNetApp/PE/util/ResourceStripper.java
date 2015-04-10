/*******************************************************************************
 * This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     Peter Smith
 *******************************************************************************/
package mk.DotNetApp.PE.util;

import java.io.File;

import mk.DotNetApp.PE.PE;
import mk.DotNetApp.PE.io.PEParser;


public class ResourceStripper
{
    public static void remove(File pecoff, File output) throws Exception {
        PE pe = PEParser.parse(pecoff);
    }
}
