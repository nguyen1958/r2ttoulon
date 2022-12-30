package fr.analogon.r2t.reclamation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.request.RequestReclamation;


/**
 * Permet de gerer les gerer les modeles de reclamamtions .
 * Gestion des modeles de reclamations. . Sofien CHARFI
 * 
 * @since 1.0
 * @version 1.3
 */
public class GestionModeleCourier extends fr.analogon.r2t.main.RacineServlet 
{		
	public String choix="";		
	public String idModeleCourier="";
	public String nomModeleCourier="";
	public String cheminModeleCourier="";	
	private HttpServletRequest request;	
	RequestReclamation reqRecl= new RequestReclamation();
	
	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)
			
	{
		try 
		{
			this.request =request;
			getParametres();	
			ServletContext sc = getServletConfig().getServletContext();	
			if( ! choix.equalsIgnoreCase("supprimer"))
			{		
				RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=empl_gestion_ModeleCourier.jsp" +
	    			"&idModeleCourier="+idModeleCourier+"&choix="+choix);
				rd.forward(request, response);
			}
			else
			{				
				RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=liste_ModeleCourier.jsp" +
						           "&choix=chercher" );
				rd.forward(request, response);
			}
			
		} 
		catch (Exception e) 
		{
			//System.out.println("Gestion de reclamation.....................[Erreur]");
			debug.logger.fatal(e.getMessage());
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
		
		if ((request.getParameter("idModeleCourier") != null)	) 
			this.idModeleCourier = request.getParameter("idModeleCourier");	
		
		if ((request.getParameter("nomModeleCourier") != null)	) 
			this.nomModeleCourier= request.getParameter("nomModeleCourier");	
		
		if ((request.getParameter("cheminModeleCourier") != null)	)
		{
			cheminModeleCourier= request.getParameter("cheminModeleCourier");			
			cheminModeleCourier= cheminModeleCourier.replaceAll("\\\\", "\\\\\\\\");
			debug.logger.debug("cheminModeleCourier="+cheminModeleCourier);
		}
		
		//////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////
	    //TRAITEMENT DES DONNNEE ET ACCES A LA BD
		//////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////
		if(choix.equalsIgnoreCase("ajouter"))
		{			
			this.idModeleCourier = "" +reqRecl.ajouterModeleCourier(nomModeleCourier, cheminModeleCourier);
			debug.logger.debug("Ajouter modele courier ");
			debug.logger.debug("idModeleCourier="+idModeleCourier);
			choix= "modifier";
					
		}
		else if(choix.equalsIgnoreCase("modifier"))
		{			
			if(cheminModeleCourier.length()!=0)
			{
				reqRecl.majModeleCourier(""+idModeleCourier, nomModeleCourier, cheminModeleCourier);
				debug.logger.debug("Modifier modele courier ");
			}
		}	
		else if(choix.equalsIgnoreCase("supprimer"))
		{			
			debug.logger.debug("Supprimer modele courier ");
			reqRecl.supprimerModeleCourier(Integer.parseInt(idModeleCourier));			
		}			
	}	
}
