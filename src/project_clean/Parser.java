/* 
+++
    Copyright (C) 2012 Carlos Nasillo / me@carlosnasillo.com.
    
    Permission is hereby granted, free of charge, to any person obtaining a copy of 
    this software and associated documentation files (the "Software"), to deal in the 
    Software without restriction, including without limitation the rights to use, copy,
    modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
    and to permit persons to whom the Software is furnished to do so, subject to the 
    following conditions:
    
    The above copyright notice and this permission notice shall be included in all copies
    or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
    INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
    PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
    FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR 
    OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
    DEALINGS IN THE SOFTWARE.
---
*/

package project_clean;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Parser {

//------------------------------------------------------------------------------
    public String binaryFileToHexString(final String path)
        throws FileNotFoundException, IOException {
            final int bufferSize = 512;                                             
            final byte[] buffer = new byte[bufferSize];
            final StringBuilder FileHexFormat = new StringBuilder();
            FileInputStream stream = new FileInputStream(path);
            int bytesRead;

            while ((bytesRead = stream.read(buffer)) > 0) {
                for (int i = 0; i < bytesRead; i++) {
                    FileHexFormat.append(String.format("%02X", buffer[i]));
                }
            }
            stream.close();
            return FileHexFormat.toString();                                                       
    }

//------------------------------------------------------------------------------
    public String[] readVirusDatabase(final String filename) throws IOException {
        List<String> vListRaw = new ArrayList<String>();
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String vSignature = null;

        while ((vSignature = bufferedReader.readLine()) != null) {
            vListRaw.add(vSignature);
        }
        bufferedReader.close();
        String []definitionsListDirty = vListRaw.toArray(new String[vListRaw.size()]);
        return definitionsListDirty;
    }

    public String[] cleanDefinitionList(String rawDefinition){                                       
        String []cleaned = rawDefinition.split("=");
        return cleaned;
    }

//------------------------------------------------------------------------------
    public String removeSpaces(String dirtyStr) {
        StringTokenizer string = new StringTokenizer(dirtyStr," ",false);               
        String cleanStr="";
        while (string.hasMoreElements()) {
            cleanStr += string.nextElement();
        }
        return cleanStr;
    }
}
