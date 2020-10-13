package fr.analogon.r2t.administration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.request.RequestUtilisateur;


/**
 * Permet de gerer les utulisateur Sofien CHARFI
 * 
 * @since 1.0
 * @version 1.3
 */
public class GestionUtilisateur extends fr.analogon.r2t.main.RacineServlet 
{	
	private HttpServletRequest request;	
	private String choix= "";	
	private boolean creation=true;	
	private String codeUtilisateur="";
	private String nomUtilisateur="";
	private String prenomUtilisateur="";
	private String typeUtilisateur="";
	private String actifUtilisateur="OUI";
	private String typeTaxeUtilisateur="";	
	private String loginUtilisateur="";	
	private String motDePaseeUtilisateur="";
	private String  remarqueUtilisateur="";	
	private String  listeDesTypesDeTaxeAutorise="";
	

	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)			
	{
		try 
		{
			this.request = request;			
			getParametres();	
			ServletContext sc = getServletConfig().getServletContext();
			RequestDispatcher rd;
			if( choix.equalsIgnoreCase("Ajouter"))
			{
				debug.logger.debug("ajout d'un utulisateur");
				 rd = sc.getRequestDispatcher("/entree?action=empl_gestion_utilisateur.jsp" +
					"&choix=modifier&codeUtilisateur="+codeUtilisateur);
			}
			
			else if  (choix.equalsIgnoreCase("modifier"))
			{
				debug.logger.debug("modification de l utulisateur " + codeUtilisateur);					
				rd = sc.getRequestDispatcher("/entree?action=empl_gestion_utilisateur.jsp" +
					"&choix=modifier&codeUtilisateur="+codeUtilisateur);			
			}
			
			else
			{			
				rd = sc.getRequestDispatcher("/entree?action=liste_utilisateur.jsp");
			}
			////System.out.println("Code  utulisateur"+codeUtilisateur );
			rd.forward(request, response);
		} 
		catch (Exception e) 
		{			
			debug.logger.fatal("Gestion utulisateurs .....................[Erreur]"+e.getMessage());
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
		if (request.getParameter("codeUtilisateur") != null)	this.codeUtilisateur= request.getParameter("codeUtilisateur");
		if (request.getParameter("nomUtilisateur") != null)	this.nomUtilisateur= request.getParameter("nomUtilisateur");
		if (request.getParameter("prenomUtilisateur") != null)	this.prenomUtilisateur= request.getParameter("prenomUtilisateur");
		if (request.getParameter("loginUtilisateur") != null)	this.loginUtilisateur= request.getParameter("loginUtilisateur");
		if (request.getParameter("motDePaseeUtilisateur") != null)	this.motDePaseeUtilisateur= request.getParameter("motDePaseeUtilisateur");
		if (request.getParameter("actifUtilisateur") != null)	this.actifUtilisateur= request.getParameter("actifUtilisateur");
		if (request.getParameter("typeTaxeUtilisateur") != null)	this.typeTaxeUtilisateur= request.getParameter("typeTaxeUtilisateur");
		if (request.getParameter("typeUtilisateur") != null)	this.typeUtilisateur= request.getParameter("typeUtilisateur");
		if (request.getParameter("remarqueUtilisateur") != null)	this.remarqueUtilisateur= request.getParameter("remarqueUtilisateur");		
		if (request.getParameter("listeDesTypesDeTaxeAutorise") != null)	this.listeDesTypesDeTaxeAutorise= request.getParameter("listeDesTypesDeTaxeAutorise");
		
	
		
		if(choix.equalsIgnoreCase("ajouter"))
		{
			//System.out.println("Ajout d'un nouveau utulisateur ......");			
			RequestUtilisateur reqUtilisateur = new RequestUtilisateur();
			codeUtilisateur=  String.valueOf(reqUtilisateur.ajouterUtilisateur(nomUtilisateur,prenomUtilisateur,loginUtilisateur,
					motDePaseeUtilisateur,actifUtilisateur,	typeUtilisateur,remarqueUtilisateur,listeDesTypesDeTaxeAutorise));
		}
		else if(choix.equalsIgnoreCase("modifier"))
		{
			//System.out.println("Modifciation du profil utilisateur "+ codeUtilisateur);			
			RequestUtilisateur reqUtilisateur = new RequestUtilisateur();		
			reqUtilisateur.modifierUtilisateur( codeUtilisateur, nomUtilisateur,prenomUtilisateur,loginUtilisateur,
					motDePaseeUtilisateur,actifUtilisateur,	typeUtilisateur,remarqueUtilisateur,listeDesTypesDeTaxeAutorise);
			
		}	
		else if(choix.equalsIgnoreCase("supprimer"))
		{			
			//System.out.println("Sppreession de l'utilisateur"+ codeUtilisateur);
			RequestUtilisateur reqUtilisateur = new RequestUtilisateur();		
			reqUtilisateur.supprimerUtilisateur(codeUtilisateur);				
		}
	
	}

		
}
