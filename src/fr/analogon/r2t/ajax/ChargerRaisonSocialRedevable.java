package fr.analogon.r2t.ajax;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.request.DataFromBD;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.request.RequestParametres;
import fr.analogon.r2t.util.web.StaticManipHtml;

public class ChargerRaisonSocialRedevable extends  fr.analogon.r2t.main.RacineServlet 
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
	        String njr= (String)paramsMap.get("njr");
	        String raisonSocialeRedevableok= (String)paramsMap.get("raisonSocialeRedevableok");
	        String res ="";
	        DataFromBD data = new DataFromBD();
	    	RequestParametres requestParametres = new RequestParametres();
	        String ville = requestParametres.getVille();
	        if(ville.equalsIgnoreCase("Toulon")) 
 			{
	            if(njr.startsWith("11"))
		        {
					Vector contenu2 = new Vector();
					contenu2.addElement("");
					contenu2.addElement("M");
					contenu2.addElement("M. MME");
					contenu2.addElement("ME");
					contenu2.addElement("MME");
		        	res= StaticManipHtml.genererListeDeroulanteApartirDeVacteur("raisonSocialeRedevable",
		        			raisonSocialeRedevableok, contenu2, 1, false);
		        }
		        else
		        {	
		        	Vector contenu2 = data.getTousLesRaisonSocial();
		        	contenu2.remove("M");
		        	contenu2.remove("MMES");
		        	contenu2.remove("M. MME");
		        	contenu2.remove("ME");
		        	contenu2.remove("MLLE");
		        	contenu2.remove("MME");
		        	contenu2.remove("M_ET_MME");
		        	res= StaticManipHtml.genererListeDeroulanteApartirDeVacteur("raisonSocialeRedevable",
		        			raisonSocialeRedevableok, contenu2, 1, false);	
		        }
 			}
	        else
	        {
	        	Vector contenu2 = data.getTousLesRaisonSocial();
	        	res= StaticManipHtml.genererListeDeroulanteApartirDeVacteur("raisonSocialeRedevable",
	        			raisonSocialeRedevableok, contenu2, 1, false);
	        }	
	        writer.println(res);	    
	        writer.close();	
	 }        
}
	
