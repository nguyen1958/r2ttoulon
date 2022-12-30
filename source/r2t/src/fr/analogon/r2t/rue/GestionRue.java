package fr.analogon.r2t.rue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.request.RequestRue;


/**
 * Permet gerer les Rues Sofien CHARFI
 * 
 * @since 1.0
 * @version 1.3
 */
public class GestionRue extends fr.analogon.r2t.main.RacineServlet 
{	
	private HttpServletRequest request;	
	private String choix= "";	
	private boolean creation=true;	
	private String codeRue="";
	private String codeRivolieRue="";
	private String adresseRue="";
	private String debutNumeroRue="";
	private String finNumeroRue="";
	private String quartierRue="";
	private String remarqueRue="";
	private String liaisonAdresseRue="";
	private String prefixeAdresseRue="";
	private String nomAdresseRue="";
	private String listeRue="";
	private String codePostal=""; 

	private String codeSecteur="";
	private String familleMarche="";

	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)			
	{
		try 
		{
			////System.out.println("**********************");
			//System.out.println("Gestion Rue.....................................");		
						
			
			this.request = request;			
			getParametres();	
			ServletContext sc = getServletConfig().getServletContext();
			RequestDispatcher rd;
			if( choix.equalsIgnoreCase("ajouter"))
			{
				//System.out.println("ajout d'une nouvelle rue");				
				rd = sc.getRequestDispatcher("/entree?action=empl_gestion_Rue.jsp" +
					"&choix=modifier&codeRue="+codeRue);				
			}
			
			else if  (choix.equalsIgnoreCase("modifier"))
			{
								
				rd = sc.getRequestDispatcher("/entree?action=empl_gestion_Rue.jsp" +
					"&choix=modifier&codeRue="+codeRue);			
			}
			
			else
			{			
				rd = sc.getRequestDispatcher("/entree?action=empl_recherche_Rue.jsp");
			}
			//System.out.println("Code  Rue"+codeRue );
			rd.forward(request, response);
		} 
		catch (Exception e) 
		{
			//System.out.println("Gestion Rues .....................[Erreur]");
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
		
		
		//////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////
	    //TRAITEMENT DES DONNNEE ET ACCES A LA BD
		//////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////
		if (request.getParameter("codeRue") != null)	this.codeRue= request.getParameter("codeRue");
		if (request.getParameter("prefixeAdresseRue") != null)	this.prefixeAdresseRue= request.getParameter("prefixeAdresseRue");
		if (request.getParameter("liaisonAdresseRue") != null)	this.liaisonAdresseRue= request.getParameter("liaisonAdresseRue");
		if (request.getParameter("nomAdresseRue") != null)	this.nomAdresseRue= request.getParameter("nomAdresseRue");
		if (request.getParameter("codeRivolieRue") != null)	this.codeRivolieRue= request.getParameter("codeRivolieRue");
		if (request.getParameter("debutNumeroRue") != null)	this.debutNumeroRue= request.getParameter("debutNumeroRue");
		if (request.getParameter("finNumeroRue") != null)	this.finNumeroRue= request.getParameter("finNumeroRue");
		if (request.getParameter("quartierRue") != null)	this.quartierRue= request.getParameter("quartierRue");
		if (request.getParameter("remarqueRue") != null)	this.remarqueRue= request.getParameter("remarqueRue");
		if (request.getParameter("codePostal") != null)	this.codePostal= request.getParameter("codePostal");
		
		if (request.getParameter("codeSecteur") != null)	this.codeSecteur= request.getParameter("codeSecteur");
		if (request.getParameter("familleMarche") != null)	this.familleMarche= request.getParameter("familleMarche");
		
		
		//System.out.println(prefixeAdresseRue);
		//System.out.println(liaisonAdresseRue);
		//System.out.println(nomAdresseRue);
		//System.out.println(codeRivolieRue);
		//System.out.println(debutNumeroRue);
		//System.out.println(finNumeroRue);
		//System.out.println(quartierRue);
		//System.out.println(remarqueRue);		
		
		if(choix.equalsIgnoreCase("ajouter"))
		{
			//System.out.println("Ajout d'une nouvelle Rue ......");	
			RequestRue 	requestRue = new RequestRue();
			codeRue = String.valueOf( requestRue.ajouterRue(prefixeAdresseRue,liaisonAdresseRue,
					nomAdresseRue,codeRivolieRue,debutNumeroRue,
					finNumeroRue,quartierRue,remarqueRue,codePostal,codeSecteur,familleMarche));			
		}
		
		else if(choix.equalsIgnoreCase("modifier"))
		{
			
			//System.out.println("Modifciation d'une Rue "+ codeRue);			
			RequestRue requestRue = new RequestRue();
			requestRue.modifierRue(codeRue ,prefixeAdresseRue,liaisonAdresseRue,
					nomAdresseRue,codeRivolieRue,debutNumeroRue,
					finNumeroRue,quartierRue,remarqueRue,codePostal,codeSecteur,familleMarche);
		}
		else if(choix.equalsIgnoreCase("supprimer"))
		{			
			//System.out.println("Sppreession de l'Rue"+ codeRue);
			RequestRue reqRue = new RequestRue();		
			reqRue.supprimerRue(codeRue);				
		}
			
	}		
}





