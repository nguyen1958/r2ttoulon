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
public class GestionRapportComptableJournalier extends fr.analogon.r2t.main.RacineServlet 
{
	
	private String dateRaportJournalier="";
	private String choix="";
	private HttpServletRequest request;	
	
	
	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{		
		
		this.request = request;	
		
		getParametres();	
	
		if(choix.equalsIgnoreCase("lancer"))
		{
			String dateLancement = GestionDate.getDateAujourdhuiString();
			String annee= dateRaportJournalier.substring(6);
			System.out.println("lancement raport comptable="+dateRaportJournalier);
			RequestRaportComptable requestRaportComptable = new RequestRaportComptable();
			int numeroBatch = requestRaportComptable.ajouterBatchRappportComptable( dateLancement,
					dateRaportJournalier, "j", annee);
			//System.out.println(numeroBatch);
			response.sendRedirect("./entree?action=gestionBatchRapportsComptableJournalier.jsp&numeroBatch="+numeroBatch);
			CreerRapportComptable gen = new CreerRapportComptable(""+numeroBatch);
			gen.lancerGenerationRapport();			
			
		}
		else
		{
			System.out.println("recuperation raport comptable");
			response.sendRedirect("./entree?action=gestionBatchRapportsComptableJournalier.jsp&numBatchRapport=" );
		}
	}
	
	private void getParametres() 
	{		
		
		if ((request.getParameter("dateRaportJournalier") != null)) 
			this.dateRaportJournalier = request.getParameter("dateRaportJournalier");
		
		if ((request.getParameter("choix") != null)) 
			this.choix = request.getParameter("choix");
		
	}

}


