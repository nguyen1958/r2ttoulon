package fr.analogon.r2t.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Ville;
import fr.analogon.r2t.request.RequestVille;
import fr.analogon.r2t.request.RequestVille;


public class RechercheCodeVille extends  fr.analogon.r2t.main.RacineServlet 
{  
     
	@SuppressWarnings("deprecation")
	public  void doMain(HttpServletRequest requestObj, HttpServletResponse responseObj)  throws IOException
    {

    	
	    String requestParameter= requestObj.getQueryString();
	    //System.out.println(requestParameter);
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
	            
	    String code = (String)paramsMap.get("code");	        
	    RequestVille reqVille = new RequestVille();
	    Vector contenu=  reqVille.getListeVille(code,"");
	    writer.println("<listVille>");	
	    for (int i = 0; i < contenu.size(); i++) 
	    {	    	
	    	Ville ville  = (Ville)contenu.elementAt(i);
	    	writer.println("<ligne>");	
	       	writer.println("<code>"+ville.getCode()+"</code>");	       	
	       	writer.println("<ville>"+ville.getNom()+"</ville>");	
	        writer.println("</ligne>");				
		}	       
	    writer.println("</listVille>");
	    //System.out.println("retouyr"+writer.toString());
	    writer.close();	   
	 	}        
}
	
