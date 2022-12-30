package fr.analogon.r2t.ajax;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.request.RequestImputation;

public class VerfierIntegrite extends  fr.analogon.r2t.main.RacineServlet 
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
	        String libelleImputation= (String)paramsMap.get("libelleImputation");	        
	        String anneeExercice= (String)paramsMap.get("anneeExercice");
	        
	        //Requete et accedcs a la BD 
	        RequestImputation reqImputation = new RequestImputation();	        	        
	        String idImputation="";    	        
	       if ( libelleImputation!=null  && anneeExercice!=null )
	        	idImputation =reqImputation.getIdImputationFromLibelle(libelleImputation, anneeExercice);
	        
	        writer.println("<res>");
	        if(idImputation.length() != 0)
	            writer.println("<idImputation>" +  idImputation+ "</idImputation>");
	        writer.println("</res>");	
	        writer.close();	   
	 }        
}
	
