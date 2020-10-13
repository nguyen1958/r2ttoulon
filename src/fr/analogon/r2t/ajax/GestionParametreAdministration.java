package fr.analogon.r2t.ajax;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.request.RequestAdmin;
import fr.analogon.r2t.request.RequestParametres;

public class GestionParametreAdministration extends  fr.analogon.r2t.main.RacineServlet 
{  
     
	public synchronized void doMain(HttpServletRequest requestObj, HttpServletResponse responseObj)                throws IOException
    {   	 
	    	 //set the content type
		    requestObj.setCharacterEncoding("utf-8");
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
	        String type= (String)paramsMap.get("type");
	        String action= (String)paramsMap.get("action");
	        String oldValeur= (String)paramsMap.get("oldValeur");
	        String newValeur= (String)paramsMap.get("newValeur");
	        String niveauDebogageApplication= (String)paramsMap.get("niveauDebogageApplication");
	        String niveauDebogageR2TMobile= (String)paramsMap.get("niveauDebogageR2TMobile");
	        String SupprimerFichierTemporaire = (String)paramsMap.get("SupprimerFichierTemporaire");
	        //System.out.println("type"+type);
	        /*System.out.println("action"+action);
	        //System.out.println("old="+oldValeur);
	        //System.out.println("new="+newValeur);
	        */	        
	        
	        
	        //CAS DE MOTIF D'ANNULATION 
	        //DataFromBD df = new DataFromBD();
	        RequestParametres requestParametres = new RequestParametres();
	        if(action != null)
	        {	
	        	
		       if (type != null)
		       {
		        	if(type.equalsIgnoreCase("motifAnnulation"))
			       {
		        	if(action.equalsIgnoreCase("ajouterMotifAnnulation"))
			        	requestParametres.ajouterMotifAnnulation(newValeur);
			        if(action.equalsIgnoreCase("modifierMotifAnnulation"))
			        	requestParametres.modifierMotifAnnulation(oldValeur, newValeur);
			        if(action.equalsIgnoreCase("supprimerMotifAnnulation"))
			        	requestParametres.supprimerMotifAnnulation(oldValeur);
			       }
			        
		        	if(type.equalsIgnoreCase("RaisonSocial"))
		        	{
				        if(action.equalsIgnoreCase("ajouterRaisonSocial"))
				        	requestParametres.ajouterRaisonSocial(newValeur);
				        if(action.equalsIgnoreCase("modifierRaisonSocial"))
				        	requestParametres.modifierRaisonSocial(oldValeur, newValeur);
				        if(action.equalsIgnoreCase("supprimerRaisonSocial"))
				        	requestParametres.supprimerRaisonSocial(oldValeur);
		        	}
			        
		        	if(type.equalsIgnoreCase("Banque"))
		        	{	
				        if(action.equalsIgnoreCase("ajouterBanque"))
				        	requestParametres.ajouterBanque(newValeur);
				        if(action.equalsIgnoreCase("modifierBanque"))
				        	requestParametres.modifierBanque(oldValeur, newValeur);
				        if(action.equalsIgnoreCase("supprimerBanque"))
				        	requestParametres.supprimerBanque(oldValeur);
		        	}
		        	
		        	if(type.equalsIgnoreCase("Profesion"))
		        	{	
				        if(action.equalsIgnoreCase("ajouterProfesion"))
				        	requestParametres.ajouterProfesion(newValeur);
				        if(action.equalsIgnoreCase("modifierProfesion"))
				        	requestParametres.modifierProfesion(oldValeur, newValeur);
				        if(action.equalsIgnoreCase("supprimerProfesion"))
				        	requestParametres.supprimerProfesion(oldValeur);
		        	}
		       }
			        	
	        }
	        
	        
	        //Requete et accedcs a la BD 		        
	        writer.println("<list>");
	        	if (type != null)
		       {
		        
	        	if(type.equalsIgnoreCase("motifAnnulation"))
	        	{
	        		Vector contenuMotifAnnulation = requestParametres.getTousLesMotifsAnnulationFacture();
			        for (int i = 0; i < contenuMotifAnnulation.size(); i++)
			        {
			        	writer.println("<motifAnnulation>" +  contenuMotifAnnulation.elementAt(i)+ "</motifAnnulation>");	
					}
	        	}
	        	
	        	if(type.equalsIgnoreCase("Banque"))
	        	{
	        		Vector contenuBanques = requestParametres.getTousLesBanque();	        		
	    		     for (int i = 0; i < contenuBanques.size(); i++)
	    		     {
	    		      	String b = (String) contenuBanques.elementAt(i);
	    		      	b = b.replaceAll("<" , "inferieur "); 
	    		       writer.println("<banque>" + b + "</banque>");	    		        	
	    		     }
	        	}
	    	
	        	
	        	if(type.equalsIgnoreCase("RaisonSocial"))
	        	{	    	        
	    	        Vector contenuRaisonSocial = requestParametres.getTousLesRaisonSocial();
	    	        for (int i = 0; i < contenuRaisonSocial.size(); i++)
	    	        {	        	
			        	String s = contenuRaisonSocial.elementAt(i).toString();		        
			        	if(s.length()==0) s =".";
			        	writer.println("<raisonSocial>" +  s+ "</raisonSocial>");	
	    	        }
	        	}
	        	
	        	if(type.equalsIgnoreCase("Profesion"))
	        	{	    	        
	    	        
	        		Vector contenuProffesion = requestParametres.getTousLesProffesion();
	    	        for (int i = 0; i < contenuProffesion.size(); i++)
	    	        {	        	
			        	String s = contenuProffesion.elementAt(i).toString();		        
			        	if(s.length()==0) s =".";
			        	writer.println("<profesion>" +  s+ "</profesion>");	
	    	        }
	        	}

	        	
		       }
		        
		        
				
		        
		    if(niveauDebogageApplication != null && niveauDebogageApplication.length() !=0)
		    {
		    	RequestAdmin requestAdmin = new RequestAdmin();	
		    	requestAdmin.majNiveauDebogageApplication(niveauDebogageApplication);
		    }
		    
		    if(niveauDebogageR2TMobile != null && niveauDebogageR2TMobile.length() !=0)
		    {
		    	RequestAdmin requestAdmin = new RequestAdmin();	
		    	requestAdmin.majNiveauDebogageR2TMobile(niveauDebogageR2TMobile);
		    }
		    
		    if( SupprimerFichierTemporaire != null && SupprimerFichierTemporaire.length() !=0)
		    {
		    	RequestAdmin requestAdmin = new RequestAdmin();	
		    	requestAdmin.supprimerFichierTemporaire();
		    }
		    
		    
		    	
	        writer.println("</list>");  	       	
	        writer.close();	   
	   
	 }        
}
	
