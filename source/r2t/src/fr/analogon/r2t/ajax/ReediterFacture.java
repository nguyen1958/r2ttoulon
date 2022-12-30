package fr.analogon.r2t.ajax;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.facturation.CreerFactures;
import fr.analogon.r2t.pojo.Facture;
import fr.analogon.r2t.request.RequestFacture;
import fr.analogon.r2t.request.RequestImputation;

public class ReediterFacture extends  fr.analogon.r2t.main.RacineServlet 
{  
     
	public synchronized void doMain(HttpServletRequest requestObj, HttpServletResponse responseObj)                throws IOException
    {   	 
	    	debug.logger.debug("appel ajax reediter la facture");
	    	String res = "ko";
	    	//get the PrintWriter object to write the html page
	    	PrintWriter writer = responseObj.getWriter();
	    	try 
			{	 //set the content type
		        responseObj.setContentType("text/xml");
		        responseObj.setCharacterEncoding("utf-8");
		        responseObj.setHeader("Cache-Control", "no-cache");			   			    	 
		        //get parameters store into the hashmap
		        HashMap paramsMap = new HashMap();
		        Enumeration paramEnum = requestObj.getParameterNames();
		        while(paramEnum.hasMoreElements())
		        {
		            String paramName = (String)(paramEnum.nextElement());
		            paramsMap.put(paramName, requestObj.getParameter(paramName));
		        }
		       	        
		        //Recuperation des parametres de la servlet 
		        String numeroFacture= (String)paramsMap.get("numeroFacture");
		        RequestFacture requestFacture = new RequestFacture();
		        Facture facture = requestFacture.getFacture(numeroFacture);
		        int numeroBatch = Integer.parseInt(facture.getIdBatch());		        
				CreerFactures cf =new CreerFactures(numeroBatch,"reediter");
				RequestFacture rq = new RequestFacture();
				cf.LancerFactures(numeroBatch,  false);				
				debug.logger.debug(facture.getNumeroFacture());				
				cf.CreationFactures(
						Integer.parseInt(numeroFacture),
						210,		//numéro chrono non défini
						numeroBatch, 
						facture.getTypeTaxe(), 
						facture.getIdClient(), 
						Integer.parseInt(facture.getNumeroTitre()), "reedit");
				res = "ok";	        
		        	        
		        
				
			} catch (Exception e) 
			{
				e.printStackTrace();				
			}
			writer.println(res);
			writer.close();

		   	   
	 }        
}
	
