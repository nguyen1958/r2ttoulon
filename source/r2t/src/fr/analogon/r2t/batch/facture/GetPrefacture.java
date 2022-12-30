package fr.analogon.r2t.batch.facture;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Permet de gerer de retrouner un lien vers le fichier de refacturation 
 *  Apercu de la refacturation  . Sofien CHARFI
 * 
 * @since 2
 * @version 2
 */
public class GetPrefacture extends   fr.analogon.r2t.main.RacineServlet 
{    
       
	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)               throws IOException
    {	 
        System.out.println("get request PreRefacturation......... ");       
        response.setContentType("text/xml"); 
        response.setCharacterEncoding("utf-8");               
        PrintWriter writer = response.getWriter();        
        HashMap paramsMap = new HashMap();
        Enumeration paramEnum = request.getParameterNames();
        while(paramEnum.hasMoreElements())
        {
            String paramName = (String)(paramEnum.nextElement());
            paramsMap.put(paramName, request.getParameter(paramName));
        }
        
        //get the etapeFacturation
        String etapeFacturation= (String)paramsMap.get("etapeFacturation");
	    String requestParameter= request.getQueryString();
	    //System.out.println("Parameter="+requestParameter);
        //System.out.println("typeFacture="+etapeFacturation);
        
	    //Creation des donnes de la refacturation :            
        Refacturation refacturation = new Refacturation(requestParameter,etapeFacturation);
        String lienFacture=refacturation.getLienFacture();
        
        writer.println("<facture>");	             
        writer.println("<lienFacture>"+lienFacture+"</lienFacture>");       
        writer.println("</facture>");
        writer.close();
   
    }        
}

