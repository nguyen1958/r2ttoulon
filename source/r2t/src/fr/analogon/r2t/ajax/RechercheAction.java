package fr.analogon.r2t.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.request.RequestAdmin;
import fr.analogon.r2t.request.RequestRue;
import fr.analogon.r2t.request.RequestVille;

public class RechercheAction extends  fr.analogon.r2t.main.RacineServlet 
{  
     
	public synchronized void doMain(HttpServletRequest requestObj, HttpServletResponse responseObj)                throws IOException
    {   	 
    	//set the content type
        responseObj.setContentType("application/json");
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
        String choix="";
        String action= "";
        String typeTaxe="";
        if (requestObj.getParameter("choix") != null) choix = requestObj.getParameter("choix");
        if (requestObj.getParameter("action") != null) action = FonctionCommun.ajouterAntislash(requestObj.getParameter("action"));
        if (requestObj.getParameter("typeTaxe") != null) typeTaxe = FonctionCommun.ajouterAntislash(requestObj.getParameter("typeTaxe"));
        System.out.println("parameters="+choix+":"+action+":"+typeTaxe);
        
        RequestAdmin req = new RequestAdmin();
        
        if (choix.equalsIgnoreCase("suppression")) {
        	req.removeAction(action, typeTaxe);
        	System.out.println("Suppession action");
        }
        else{//par defaut il s'agit d'une recherche
        	String res=req.getAction(action, typeTaxe);
            System.out.println(res);
            writer.println(res);
        }
        	      
        writer.close();	   
	 }        
}
	
