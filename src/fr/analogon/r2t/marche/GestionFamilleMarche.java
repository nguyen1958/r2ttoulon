package fr.analogon.r2t.marche;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.request.RequestFamilleMarche;


/**
 * Permet de gerer les famille de marche .
 * Gestion des famille de marche. . Sofien CHARFI
 * 
 * @since 1.0
 * @version 1.3
 */
public class GestionFamilleMarche extends fr.analogon.r2t.main.RacineServlet 
{	
	private HttpServletRequest request;	
	private String choix= "";
	private boolean creation ;
	private String codeFamilleMarche= "";
	private String libelleFamilleMarche= "";
	
		
	public void doMain(HttpServletRequest request, HttpServletResponse response)			
	{
		try 
		{
			debug.logger.debug(" Gestion de famille marche " + choix);		
			this.request = request;			
			getParametres();	
			ServletContext sc = getServletConfig().getServletContext();	
			if( choix.equalsIgnoreCase("modifier") || choix.equalsIgnoreCase("Ajouter"))
			{
				//System.out.println();
				RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=empl_gestion_familleMarche.jsp" +
					"&choix=modifier&codeFamilleMarche="+codeFamilleMarche);
				rd.forward(request, response);			
			}
			else
			{			
				RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=liste_familleMarche.jsp");
				rd.forward(request, response);					
			}
			
		} 
		catch (Exception e) 
		{			
			debug.logger.fatal(e.getMessage() + " erreur FamilleMarche");
		}
	}

	
	public void destroy() 
	{
		super.destroy();		
	}

	/**
	 * Permet de recuperer les parametres html . Sofien CHARFI
	 * 
	 * @since 1.0
	 */
	private void getParametres() 
	{		
		
		////System.out.println("TEst"+request.getParameter("texteReponse"));
		if ((request.getParameter("choix") != null)	) 
			this.choix = request.getParameter("choix");	
		
		//////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////
	    //TRAITEMENT DES DONNNEE ET ACCES A LA BD
		//////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////
			
		if ((request.getParameter("choix") != null)) this.choix = request.getParameter("choix");		
		if ((request.getParameter("codeFamilleMarche") != null)) this. codeFamilleMarche = request.getParameter("codeFamilleMarche");
		if ((request.getParameter("libelleFamilleMarche") != null)) this. libelleFamilleMarche = request.getParameter("libelleFamilleMarche");
			
		if(choix.equalsIgnoreCase("ajouter"))
		{
			DebuggerLog4J.logger.debug("Ajout d'un nouveau d'une nouvelle famillle marche "+ libelleFamilleMarche+"......");			
			RequestFamilleMarche requestFamilleMarche = new RequestFamilleMarche();
			codeFamilleMarche = String.valueOf(requestFamilleMarche.ajouterFamilleMarche(libelleFamilleMarche));
		}
		else if(choix.equalsIgnoreCase("modifier"))
		{
			DebuggerLog4J.logger.debug("Modifciation 'd'une nouvelle famillle"+ codeFamilleMarche);			
			RequestFamilleMarche requestFamilleMarche = new RequestFamilleMarche();
			requestFamilleMarche.modifierFamilleMarche(codeFamilleMarche, libelleFamilleMarche) ;							
		}	
		else if(choix.equalsIgnoreCase("supprimer"))
		{
			DebuggerLog4J.logger.debug("Sppresion de famille de marche " + codeFamilleMarche);		
			RequestFamilleMarche requestFamilleMarche = new RequestFamilleMarche();
			requestFamilleMarche.supprimerFamilleMarche(codeFamilleMarche);
		}	
	}		
}

