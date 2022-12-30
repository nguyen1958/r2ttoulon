package fr.analogon.r2t.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.pojo.Emplacement;
import fr.analogon.r2t.request.RequestEmplacement;

public class RechercheEmplacement extends  fr.analogon.r2t.main.RacineServlet 
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
	        
	        //Recuperation des parametres : 	        
	        String numEmplacement= (String)paramsMap.get("numEmplacement");
	        String nomEmplacement= (String)paramsMap.get("nomEmplacement");
	        String adresseEmplacement= (String)paramsMap.get("adresseEmplacement");	        
	        //System.out.println("numEmplacement "+numEmplacement);
	        //System.out.println("nomEmplacement "+nomEmplacement);
	        //System.out.println("adresseEmplacement "+adresseEmplacement);
	        
	        RequestEmplacement reqEmpl = new RequestEmplacement();        
	        Vector contenu= new Vector();
	        contenu = reqEmpl.getListeEmplacement(numEmplacement, nomEmplacement, adresseEmplacement);
	        writer.println("<list>");	       
	        for (int i = 0; i < contenu.size(); i++) 
	        {
	        	
	        	Emplacement emplacment = (Emplacement)contenu.elementAt(i);
	        	String peutEtreSupprimer = reqEmpl.peutEtreSupprimer(emplacment.getNumEmplacement()) ;
	        	//System.out.println("peutEtreSupprimer"+peutEtreSupprimer);
	        	writer.println("<emplacement>");
	        	writer.println("<numEmplacement>" + emplacment.getNumEmplacement()+ "</numEmplacement>");
	            writer.println("<adresse>" + emplacment.getAdresseComplete()+ "</adresse>");
	            writer.println("<peutEtreSupprimer>" + peutEtreSupprimer+ "</peutEtreSupprimer>");
	            writer.println("</emplacement>");				
			}	       
	        writer.println("</list>");	      
	        writer.close();	   
	 }        
}
	
