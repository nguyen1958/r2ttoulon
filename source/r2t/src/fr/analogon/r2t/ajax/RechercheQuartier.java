package fr.analogon.r2t.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.request.RequestRue;

public class RechercheQuartier extends  fr.analogon.r2t.main.RacineServlet 
{  
     
	public synchronized void doMain(HttpServletRequest requestObj, HttpServletResponse responseObj)                throws IOException
    {

    	 
    	 //set the content type
        responseObj.setContentType("text/xml");
        responseObj.setCharacterEncoding("utf-8");
        responseObj.setHeader("Cache-Control", "no-cache");
	   //get the PrintWriter object to write the html page
	    PrintWriter writer = responseObj.getWriter();
	 
	        //get parameters store into the hashmap
	        HashMap paramsMap = new HashMap();
	        Enumeration paramEnum = requestObj.getParameterNames();
	        while(paramEnum.hasMoreElements())
	        {
	            String paramName = (String)(paramEnum.nextElement());
	            paramsMap.put(paramName, requestObj.getParameter(paramName));
	        }              
	        
	        String nomQuartier= (String)paramsMap.get("nomQuartier");
	        //System.out.println("nomQuartier "+nomQuartier);
	        RequestRue requestRue = new RequestRue();        
	        Vector contenu= new Vector();
	        contenu = requestRue.getListeQuartiers(nomQuartier);  	        
	        writer.println("<list>");	
	        for (int i = 0; i < contenu.size(); i++) 
	        {
	        	writer.println("<quartier>");
	            writer.println( (String)contenu.elementAt(i));	            
	            writer.println("</quartier>");				
			}	       
	        writer.println("</list>");	
	        writer.close();	   
	 }        
}
	
