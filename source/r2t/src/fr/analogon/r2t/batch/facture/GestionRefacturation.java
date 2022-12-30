package fr.analogon.r2t.batch.facture;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Permet de gerer les refacturation . Sofien CHARFI
 * 
 * @since 1.0
 * @version 1.3
 */

public class GestionRefacturation extends  fr.analogon.r2t.main.RacineServlet 
{  
     
	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)  throws IOException
    {
    	//System.out.println("get request Refacturation.......... ");
	    String requestParameter= request.getQueryString();
	    System.out.println("///////////////////////////////////////////");
		System.out.println("Request = " + requestParameter);
		System.out.println("///////////////////////////////////////////");
	    //System.out.println("Parameter="+requestParameter);   
	    //get parameters store into the hashmap
        HashMap paramsMap = new HashMap();
        Enumeration paramEnum = request.getParameterNames();
        while(paramEnum.hasMoreElements())
        {
            String paramName = (String)(paramEnum.nextElement());
            paramsMap.put(paramName, request.getParameter(paramName));
        }
        //get the author name passed
        String etape= (String)paramsMap.get("etapeFacturation");
        //System.out.println("etapeFacturation="+etape); 
        ServletContext sc = getServletConfig().getServletContext();	
	    Refacturation refacturation = new Refacturation(requestParameter,etape);
	    RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=ok.jsp&toRedirect=refacturation.jsp");
		//System.out.println("Refacturation..................................[OK]");
		try {
			rd.forward( request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			debug.logger.fatal(e.getMessage());
		}	
	    
	 }        
}
	
