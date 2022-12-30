package fr.analogon.r2t.ajax;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.request.RequestEmplacement;
import fr.analogon.r2t.request.RequestRedevable;

public class VerifierUnciteSiretSiren extends  fr.analogon.r2t.main.RacineServlet 
{  
     
	public synchronized void doMain(HttpServletRequest requestObj, HttpServletResponse responseObj)                throws IOException
    {   	 
	    	String res ="";
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
	       	        
	        //Recuperation des parametres de la servlet 
	        String siret= (String)paramsMap.get("siret");
	        String siren= (String)paramsMap.get("siren");
	        String numRedevable= (String)paramsMap.get("numRedevable");	        
	        debug.logger.debug("siret="+siret);
	        debug.logger.debug("siren="+siren);
	        debug.logger.debug("numRedevable="+numRedevable);
	        RequestRedevable requestRedevable = new RequestRedevable();
	        
	        //Verfier si il y a un redevable avec le meme siret
	        String resSiretSiren = requestRedevable.rechercherRedevableBySiretSiren(numRedevable,siret, siren);
	        debug.logger.debug("resSiretSiren="+resSiretSiren);
	        if (resSiretSiren.equalsIgnoreCase("ok") &&  resSiretSiren.equalsIgnoreCase("ok")) 
	        	res = "ok";
	        else 
	        	res = "ko" ; 
	        
	        
	      
	        writer.println("<res>");	    
	        writer.println("<disponible>" + res+ "</disponible>");
	        writer.println("</res>");	
	        writer.close();	        
	 }        
}
	
