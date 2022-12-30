package fr.analogon.r2t.view.batch;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.facturation.CreerRoleFacturation;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.request.RequestBatch;
import fr.analogon.r2t.request.RequestReclamation;


/**
 * 
 * 
 * @since 1.0
 * @version 1.3
 */
public class GetRoleComnunique extends fr.analogon.r2t.main.RacineServlet 
{	
		
	RequestReclamation requestReclamation= new RequestReclamation();
	
	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)               throws IOException
    {	 
               
        response.setContentType("text/xml"); 
        response.setCharacterEncoding("utf-8");               
        PrintWriter writer = response.getWriter();        
        HashMap paramsMap = new HashMap();
        Enumeration paramEnum = request.getParameterNames();
        RequestBatch requBatch = new RequestBatch();
        
        while(paramEnum.hasMoreElements())
        {
            String paramName = (String)(paramEnum.nextElement());
            paramsMap.put(paramName, request.getParameter(paramName));
        }
        
    	CreerRoleFacturation Montest = new CreerRoleFacturation();
    	String dateJour =GestionDate.getDateAujourdhuiString();
    	String nomQuartier= (String)paramsMap.get("nomQuartier");
    	String numSecteur= (String)paramsMap.get("numSecteur");
    	String typeRapport = (String)paramsMap.get("typeRapport");
    	
    	
    	DebuggerLog4J.logger.debug("typeRapport="+typeRapport);
    	int numeroBatch= requBatch.ajouterLigneBatchListeCommunique(dateJour, nomQuartier,numSecteur);    	    	
    	
    	String nomFichier ="";
    	String codeRapport ="";
    	
    	//RCO_numeroBatch.pdf"; 
    	if (typeRapport.equalsIgnoreCase("generale"))
    	{
    		nomFichier = "RCO_"+numeroBatch+".pdf";
    		codeRapport ="RCO";
    	}
    	if (typeRapport.equalsIgnoreCase("detaille"))
    	{
    		nomFichier = "RCOD_"+numeroBatch+".pdf";
    		codeRapport ="RCOD";
    	}
    	
    	String res="false";		
		//System.out.println("codeRapport===="+codeRapport);
    	
		try {
			
			res = Montest.lancerCreerRoleFacturation(codeRapport,numeroBatch,"","","","",
					GestionDate.getDateAujourdhuiString(),nomQuartier);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//roleCommunique= "./"+fichierDeConfiguration.getLienRoleCommunique()+"RCO_1.pdf";
		String lienRoleCommunique ="-";
		if (typeRapport.equalsIgnoreCase("generale"))
    	{
			if(Boolean.valueOf(res))
			{
				//lienRoleCommunique= "./"+fichierDeConfiguration.getLienRoleCommunique()+nomFichier;
				lienRoleCommunique= "./r2tData/data/RoleCommuniqueOuvrage/RCO_"+numeroBatch +".pdf";
			}
    	}
    	if (typeRapport.equalsIgnoreCase("detaille"))
    	{    			
    			lienRoleCommunique= "./r2tData/data/RoleCommuniqueDetaille/RCOD_"+numeroBatch +".pdf";    		
    	}
					
	
        writer.println("<role>");	             
        writer.println("<lienRoleCommunique>"+lienRoleCommunique+"</lienRoleCommunique>");
        writer.println("</role>");
        writer.close();   
        
    }   
}
