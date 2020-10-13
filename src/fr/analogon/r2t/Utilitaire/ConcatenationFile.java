package fr.analogon.r2t.Utilitaire; 

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PRAcroForm;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.SimpleBookmark;

import fr.analogon.r2t.main.DebuggerLog4J;

/** 
* Permet la concaténation de deux PDF en un. 
* 
* @author Johann Pons 
*/ 
public class ConcatenationFile 
{

public void concat(Vector listOfFile, String outFile) 
{ 
//int numBatch=499;
//args = new String[]{"D:\\1.pdf", "D:\\2.pdf", "D:\\out.pdf"}; 

try { 
int pageOffset = 0; 
ArrayList master = new ArrayList(); 
int f = 0; 
Document document = null; 
PdfCopy writer = null; 
while (f < listOfFile.size()) { 
PdfReader reader = new PdfReader((String)listOfFile.elementAt(f)); 
reader.consolidateNamedDestinations(); 
int n = reader.getNumberOfPages(); 
List bookmarks = SimpleBookmark.getBookmark(reader); 
if (bookmarks != null) { 
if (pageOffset != 0) { 
SimpleBookmark.shiftPageNumbers(bookmarks, pageOffset, null); 
} 
master.addAll(bookmarks); 
} 
pageOffset += n; 

if (f == 0) { 
document = new Document(reader.getPageSizeWithRotation(1)); 
writer = new PdfCopy(document, new FileOutputStream(outFile)); 
document.open(); 
} 
PdfImportedPage page; 
for (int i = 0; i < n;) { 
++i; 
page = writer.getImportedPage(reader, i); 
writer.addPage(page); 
} 
PRAcroForm form = reader.getAcroForm(); 
if (form != null) { 
writer.copyAcroForm(reader); 
} 
f++; 
} 
if (!master.isEmpty()) { 
writer.setOutlines(master); 
} 
document.close(); 
} catch (Exception e) { 
	DebuggerLog4J.logger.fatal(e.getMessage()); 
} 
} 
}

