package fr.analogon.r2t.view.ville;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.request.RequestVille;


/**
 * Permet de gerer les famille de marche .
 * Gestion des famille de marche. . Sofien CHARFI
 * 
 * @since 1.0
 * @version 1.3
 */
public class GestionVille extends fr.analogon.r2t.main.RacineServlet 
{	
	private HttpServletRequest request;	
	private String choix= "";
	private boolean creation ;
	private String id="";
	private String code= "";
	private String ville= "";
	
		
	public void doMain(HttpServletRequest request, HttpServletResponse response)			
	{
		try 
		{
			debug.logger.debug(" Gestion de ville " + choix);		
			this.request = request;			
			getParametres();	
			ServletContext sc = getServletConfig().getServletContext();	
			if( choix.equalsIgnoreCase("modifier") || choix.equalsIgnoreCase("Ajouter"))
			{
				//System.out.println();
				RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=empl_gestion_ville.jsp" +
					"&choix=modifier&id="+id);
				rd.forward(request, response);			
			}
			else
			{			
				RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=liste_ville.jsp");
				rd.forward(request, response);					
			}
			
		} 
		catch (Exception e) 
		{			
			debug.logger.fatal(e.getMessage() + " erreur Ville");
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
		if ((request.getParameter("id") != null)) this.id = request.getParameter("id");
		if ((request.getParameter("code") != null)) this. code = request.getParameter("code");
		if ((request.getParameter("ville") != null)) this. ville = request.getParameter("ville");
			
		if(choix.equalsIgnoreCase("ajouter"))
		{
			DebuggerLog4J.logger.debug("Ajout d'une nouvelle ville "+ ville+"......");			
			RequestVille requestVille = new RequestVille();
			id = String.valueOf(requestVille.ajouterVille(code,ville));
		}
		else if(choix.equalsIgnoreCase("modifier"))
		{
			DebuggerLog4J.logger.debug("Modifciation d'une nouvelle ville"+ ville);			
			RequestVille requestVille = new RequestVille();
			requestVille.modifierVille(id,code, ville) ;							
		}	
		else if(choix.equalsIgnoreCase("supprimer"))
		{
			DebuggerLog4J.logger.debug("Suppression de ville " + id);		
			RequestVille requestVille = new RequestVille();
			requestVille.supprimerVille(id);
		}	
	}		
}

