package fr.analogon.r2t.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;

import fr.analogon.r2t.pojo.Redevable;
import fr.analogon.r2t.request.RequestRedevable;

public class RechercheRedevable extends fr.analogon.r2t.main.RacineServlet 
{  
	private String  escapeXML(String str){

	    str = str.replace("&","&amp;");
	    str = str.replace(">","&gt;");
	    str = str.replace("<","&lt;");
	    str = str.replace("\"","&quot;");
	    str = str.replace("'","&apos;");

	    return str;
	}
     
	//public synchronized void doMain(HttpServletRequest requestObj, HttpServletResponse responseObj)                throws IOException
	public  void doMain(HttpServletRequest requestObj, HttpServletResponse responseObj)  throws IOException
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
	        
	        //Recuperation des parametres : 	        
	        String numRedevable= (String)paramsMap.get("numRedevable");
	        String nomRedevable= (String)paramsMap.get("nomRedevable");
	        String adresseRedevable= (String)paramsMap.get("adresseRedevable");	        
	        ////System.out.println("numRedevable "+numRedevable);
	        ////System.out.println("nomRedevable "+nomRedevable);
	        ////System.out.println("adresseRedevable "+adresseRedevable);
	        
	        RequestRedevable reqRed = new RequestRedevable();        
	        Vector contenu= new Vector();
	        contenu = reqRed.getListeRedevable(numRedevable, nomRedevable, adresseRedevable);
	        
	     		
			
	        writer.println("<list>");	       
	        for (int i = 0; i < contenu.size(); i++) 
	        {
	        	
	        	Redevable redevable = (Redevable)contenu.elementAt(i);
	        	String raisonSocal = redevable.getRaisonSocialeRedevable();
	        	if (raisonSocal==null || raisonSocal.length()== 0 ) raisonSocal = "-"; 
	        	writer.println("<redevable>");
	        	writer.println("<numRedevable>" + redevable.getNumRedevable()+ "</numRedevable>");
	        	writer.println("<raisonSocialeRedevable>" + escapeXML(raisonSocal)+ "</raisonSocialeRedevable>");
	        	//debug.logger.debug("Nom Redevable="+ redevable.getNomRedevable());
	        	String nomPrenom= redevable.getNomRedevable()+ " " + redevable.getPrenomRedevable();
	        	//nomPrenom = nomPrenom.replaceAll("&", " ET");
	        	writer.println("<nomPrenomRedevable>" + escapeXML(nomPrenom) +"</nomPrenomRedevable>");
	            writer.println("<adresse>" + escapeXML(redevable.getAdressRedevable())+ "</adresse>");
	            writer.println("</redevable>");	
	            
			}	       
	        writer.println("</list>");	        
	        writer.close();	   
	 } 
	
	
}
	
