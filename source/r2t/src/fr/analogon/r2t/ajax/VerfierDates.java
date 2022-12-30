package fr.analogon.r2t.ajax;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.request.RequestImputation;

public class VerfierDates extends  fr.analogon.r2t.main.RacineServlet 
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
	        String dateDebutAutorisation= (String)paramsMap.get("dateDebutAutorisation");	        
	        String dateDernierControle= (String)paramsMap.get("dateDernierControle");
	        String periode= (String)paramsMap.get("periode");
	        String nbreDePeriode = (String)paramsMap.get("nbreDePeriode");
	        String dateVerif = "";
	        if(periode.equalsIgnoreCase("jour"))  
	           	dateVerif = GestionDate.addDays(dateDebutAutorisation, Integer.parseInt(nbreDePeriode)) ;	        
	        if(periode.equalsIgnoreCase("semaine"))  
	           	dateVerif = GestionDate.ajouterNombreSemaineComplet(dateDebutAutorisation, Integer.parseInt(nbreDePeriode)) ;
	        if(periode.equalsIgnoreCase("mois"))  
	           	dateVerif = GestionDate.ajouterNombreMoisComplet(dateDebutAutorisation, Integer.parseInt(nbreDePeriode)) ;	
	        if(periode.equalsIgnoreCase("an"))  
	           	dateVerif = GestionDate.ajouterNombreAnnees(dateDebutAutorisation, Integer.parseInt(nbreDePeriode)) ;	
	        //La date de dernier contrôle ne doit pas être inférieure à la date de mise en application complétée du nombre de périodes déjà facturées.    	
	        //System.out.println("Date autorisation= "+dateDebutAutorisation);
	        //System.out.println("Date caclcule= "+dateVerif);
	        //System.out.println("Date dernier controle= "+dateDernierControle);	        
	        int res = GestionDate.comparerDate(dateVerif,dateDernierControle);	        
	        writer.println(res);	        
	        writer.close();	   
	 }        
}
	
