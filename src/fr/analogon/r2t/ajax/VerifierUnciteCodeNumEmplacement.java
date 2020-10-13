package fr.analogon.r2t.ajax;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.request.RequestEmplacement;

public class VerifierUnciteCodeNumEmplacement extends  fr.analogon.r2t.main.RacineServlet 
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
	       	        
	        //Recuperation des parametres de la servlet 
	        String codeSecteur= (String)paramsMap.get("codeSecteur");	        
	        String numeroEmplacementPersonalise= (String)paramsMap.get("numeroEmplacementPersonalise");
	        String numEmplacementR2T= (String)paramsMap.get("numEmplacementR2T");
	        
	        DebuggerLog4J.logger.debug("numEmplacementR2T="+numEmplacementR2T);
	        DebuggerLog4J.logger.debug("codeSecteur="+codeSecteur);
	       DebuggerLog4J.logger.debug("numeroEmplacementPersonalise="+numeroEmplacementPersonalise);
	        
	       
	        String res ;
	        //Verfier si il y a un emplacement qui a le meme codesecteur et le meme num Emplacement et que l'etat de l'emplacement="enActivite"
	        RequestEmplacement requestEmplacement = new RequestEmplacement();
	        String numEmplacement = requestEmplacement.rechercherEmplacementByNumByCode(codeSecteur,numeroEmplacementPersonalise,numEmplacementR2T);
	        if (numEmplacement.equalsIgnoreCase("-1"))
	        	res = "ok";
	        else
	        	res = "ko";
	      
	        writer.println("<res>");	    
	        writer.println("<disponible>" + res+ "</disponible>");
	        writer.println("</res>");	
	        writer.close();
	        
	 }        
}
	
