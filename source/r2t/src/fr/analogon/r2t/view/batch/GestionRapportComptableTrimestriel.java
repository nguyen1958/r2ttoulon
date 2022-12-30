package fr.analogon.r2t.view.batch;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.facturation.CreerRapportComptable;
import fr.analogon.r2t.request.RequestRaportComptable;


/**
 * Bean d'affichage du batch Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0

 */
 


public class GestionRapportComptableTrimestriel extends fr.analogon.r2t.main.RacineServlet 
{
	
	private String numeroTrimestre="";
	private String choix="";
	private HttpServletRequest request;	
	
	
	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{		
		debug.logger.debug("Batch raport comptable trimestriel............. ");
		this.request = request;		
		getParametres();		
		if(choix.equalsIgnoreCase("lancer"))
		{
			String dateLancement = GestionDate.getDateAujourdhuiString();
			String annee= dateLancement.substring(6);
			debug.logger.debug("lancement raport comptable trimestriel N° ="+numeroTrimestre);
			RequestRaportComptable requestRaportComptable = new RequestRaportComptable();
			int numeroBatch = requestRaportComptable.ajouterBatchRappportComptable( dateLancement,
					numeroTrimestre, "t", annee);
			//System.out.println(numeroBatch);			
			CreerRapportComptable gen = new CreerRapportComptable(""+numeroBatch);
			gen.lancerGenerationRapport();
			response.sendRedirect("./entree?action=gestionBatchRapportsComptableTrimestriel.jsp&numeroBatch="+numeroBatch);
			
		}
		//Consulttion
		else
		{
			debug.logger.debug("recuperation raport comptable");
			response.sendRedirect("./entree?action=gestionBatchRapportsComptableMensuel.jsp&numBatchRapport=" );
		}
	}
	
	private void getParametres() 
	{		
		
		if ((request.getParameter("numeroTrimestre") != null)) 
			this.numeroTrimestre = request.getParameter("numeroTrimestre");
		
		if ((request.getParameter("choix") != null)) 
			this.choix = request.getParameter("choix");
		
	}

}


