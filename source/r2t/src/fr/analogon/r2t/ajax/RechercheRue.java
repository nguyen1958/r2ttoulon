package fr.analogon.r2t.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.pojo.Rue;
import fr.analogon.r2t.request.RequestRue;

public class RechercheRue extends  fr.analogon.r2t.main.RacineServlet 
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
	        //get the author name passed
	        //String nomRue= (String)paramsMap.get("nomRue");
	        
	        
	        String nomRueRecherche= (String)paramsMap.get("nomRueRecherche");	        
	        String codeVoixRecherche= (String)paramsMap.get("codeVoixRecherche");
	        String numeroRue= (String)paramsMap.get("numeroRue");	        
	        RequestRue reqRue = new RequestRue();
	        Vector contenu= new Vector();
	       
	       if ( nomRueRecherche!=null   )
	        	contenu = reqRue.getListeRue(nomRueRecherche);
	        else if ( codeVoixRecherche!=null )
	        {
	         
	        	contenu = reqRue.getListeRueFromCodeVoieRedevable(codeVoixRecherche,numeroRue);
	        }
	        
	        writer.println("<list>");	
	        for (int i = 0; i < contenu.size(); i++) 
	        {
	        	Rue rue = (Rue)contenu.elementAt(i);
	        	String quartier = rue.getNomquartier();	        	
	        	String prefixe = rue.getPrefixe();
	        	String liaison = rue.getLiaison();
	        	String nomRue = rue.getNomrue(); 
	        	writer.println("<rue>");
	            writer.println("<code>" +  rue.getCodeVoie()+ "</code>");
	            if (liaison.length()==0)
	            	writer.println("<adresse>" + prefixe+" "+ nomRue +"</adresse>");
	            else
	            	writer.println("<adresse>" + prefixe+" "+liaison+" "+ nomRue +"</adresse>");
	            
	            writer.println("<quartier>" + quartier + "</quartier>");
	            writer.println("<codeRivolie>" +  rue.getCodeRivolie()+ "</codeRivolie>");
	            writer.println("<codePostal>" +  rue.getCodePostal()+ "</codePostal>");
	            writer.println("</rue>");      
	            
			}	       
	        writer.println("</list>");	
	        writer.close();	   
	 }        
}
	
