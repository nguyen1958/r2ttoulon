package fr.analogon.r2t.fileupload;

import java.io.File;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import fr.analogon.r2t.main.DebuggerLog4J;

public class DataUpload {
	private HttpServletRequest  Request;
	private HttpServletResponse Response;
	private PrintWriter write ;
	private List items;
	//
	
	public DataUpload(HttpServletRequest  Request,HttpServletResponse Response) {
		Load(Request, Response);
	}
	public void Load(HttpServletRequest  Request,HttpServletResponse Response) {
		this.Request=Request;
		this.Response=Response;
	}
	public void Init() {
		try {
			if (ServletFileUpload.isMultipartContent(Request))
			{
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				items = upload.parseRequest(Request);
			}
		} catch (FileUploadException e) {
				// TODO Auto-generated catch block
			DebuggerLog4J.logger.fatal(e.getMessage());
		}
	}
	public String getValueFormName(String name) {
		Iterator iter = items.iterator();
		while (iter.hasNext()) {
		    FileItem item = (FileItem) iter.next();
		    if (item.isFormField()){
		    	String str= processFormField(item,name);
		    	if (str!=null) return str;
		    }
		    
		}
		return null;
	}
	public String getValueFileName(String name) {
		Iterator iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item= (FileItem) iter.next();
			if (!item.isFormField())
			{
				String str=processUploadedFile(item, name);
				if (str!=null) return str;
			}
		}
		return null;
	}
	
	public long getSizeFileName(String name) {
		Iterator iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item= (FileItem) iter.next();
			if (!item.isFormField())
			{
				if (item.getFieldName().equalsIgnoreCase(name))
					return item.getSize();
			}
		}
		return -1;
	}
	
	public long getUploadFileName(String name,String Filename) {		
		Iterator iter = items.iterator();

		while (iter.hasNext()) {
			try {
			FileItem item= (FileItem) iter.next();
			if (!item.isFormField())
			{
				if (item.getFieldName().equalsIgnoreCase(name))
				{
					File uplfile = new File(Filename);
					item.write(uplfile);
					System.out.println("Ecriture fichier");
				}
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				DebuggerLog4J.logger.fatal(e.getMessage());
			}
		}
		return -1;
	}
	
	private String processUploadedFile(FileItem item, String name) {
		// TODO Auto-generated method stub
		if (item.getFieldName().equalsIgnoreCase(name))
			return item.getName();
		return null;
		
	}
	private String processFormField(FileItem item,String name) {
		// TODO Auto-generated method stub
		if (item.getFieldName().equalsIgnoreCase(name))
			return item.getString();
		return null;
	}
	
	
	
}
