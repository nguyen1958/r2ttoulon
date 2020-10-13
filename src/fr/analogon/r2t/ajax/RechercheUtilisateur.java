package fr.analogon.r2t.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.pojo.Utilisateur;
import fr.analogon.r2t.request.RequestUtilisateur;

public class RechercheUtilisateur extends  fr.analogon.r2t.main.RacineServlet 
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
	        
	        
	        String nomUtilisateur= (String)paramsMap.get("nomUtilisateur");
	        //System.out.println("nomUtilisateur "+nomUtilisateur);
	        RequestUtilisateur reqUtilisateur = new RequestUtilisateur();
	        Vector contenu= new Vector();
	       
	       if ( nomUtilisateur!=null   )
	        	contenu = reqUtilisateur.getListeUtilisateur("",nomUtilisateur,"","");	     
	        
	        writer.println("<list>");	
	        for (int i = 0; i < contenu.size(); i++) 
	        {
	        	Utilisateur utilisateur= (Utilisateur)contenu.elementAt(i);
	        	writer.println("<utilisateur>");
	            writer.println("<idUtilisateur>" +  utilisateur.getId()+ "</idUtilisateur>");
	            writer.println("<nomPrenomUtilisateur>" + utilisateur.getNom()+" "+ utilisateur.getPrenom() +"</nomPrenomUtilisateur>");
	            writer.println("</utilisateur>");				
			}	       
	        writer.println("</list>");	
	        writer.close();	   
	 }        
}
	
