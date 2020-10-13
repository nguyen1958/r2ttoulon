package fr.analogon.r2t.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.reclamation.CreerFichierDataCourier;
import fr.analogon.r2t.request.RequestReclamation;

public class RecupererCourierReponse extends  fr.analogon.r2t.main.RacineServlet 
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
	        
	        Date d = new Date();
			debug.logger.debug("\n\n\n\n\n\n"+ d.toLocaleString()+"\n////////////////////////////////////////////////////");
			debug.logger.debug("////////////////////////////////////////////////////");
			debug.logger.debug("Demande de creation d un courier................. ");
			debug.logger.debug("////////////////////////////////////////////////////");
			debug.logger.debug("////////////////////////////////////////////////////");
			//get the idReclaamtion
	        String idReclamation= (String)paramsMap.get("idReclamation");
	        String idModeleCourier= (String)paramsMap.get("idModeleCourier");        
	        RequestReclamation requestReclamation= new RequestReclamation();
	        CreerFichierDataCourier c = new CreerFichierDataCourier( Integer.valueOf(idReclamation));
	        String lienCourier= c.getLienDataFile();
	        String cheminModele = requestReclamation.getModeleCourier(idModeleCourier).getCheminModele();
	        writer.println("<courier>");	             
	        writer.println("<lienCourier>"+lienCourier+"</lienCourier>");
	        writer.println("<cheminModele>"+cheminModele+"</cheminModele>");   
	        writer.println("</courier>");
	        writer.close();   
	        requestReclamation.ajouterCourierReponseReclamation(idReclamation, idModeleCourier);
	 }        
}
	
