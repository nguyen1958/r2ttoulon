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
import fr.analogon.r2t.pojo.Bareme;
import fr.analogon.r2t.request.RequestBareme;


public class RechercheBareme extends  fr.analogon.r2t.main.RacineServlet 
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
	            
	    String typeTaxe= (String)paramsMap.get("typeTaxe");	        
	    String anneeExercice= (String)paramsMap.get("anneeExercice");
	    if(anneeExercice==null) anneeExercice=GestionDate.getAnneeCourante();
	    //System.out.println(">>>"+typeTaxe);
	    //System.out.println(">>>"+anneeExercice);
	    RequestBareme reqBareme = new RequestBareme();
	    Vector contenu=  reqBareme.getListeBareme("",anneeExercice,"",typeTaxe);
	    writer.println("<listBareme>");	
	    for (int i = 0; i < contenu.size(); i++) 
	    {	    	
	    	Bareme bareme  = (Bareme)contenu.elementAt(i);
	    	writer.println("<ligne key='"+bareme.getCode()+"'>");	
	       	writer.println(bareme.getCode()+"-----");	       	
	       	writer.println(StringEscapeUtils.escapeXml(bareme.getLibelle())+"-----");
	       	if (! typeTaxe.equalsIgnoreCase("TLPE"))
	       		writer.println(bareme.getPrixUnit()+" euros-----");
	       	
	       	writer.print(bareme.getUniteDeTravail());
	       	if(!bareme.getDureeUnitaire().equalsIgnoreCase("NON-PRECISE"))
	       		writer.println("/"+bareme.getDureeUnitaire());	      			
	       	
	       	writer.println("-----Tarif("+anneeExercice+")");
	        writer.println("</ligne>");				
		}	       
	    writer.println("</listBareme>");
	    //System.out.println("retouyr"+writer.toString());
	    writer.close();	   
	 	}        
}
	
