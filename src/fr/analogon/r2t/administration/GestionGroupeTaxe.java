package fr.analogon.r2t.administration;

import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.lang.StringUtils;

import fr.analogon.r2t.request.RequestGroupeTaxe;


/**
 * Permet de gerer les groupe de taxe Sofien CHARFI
 * 
 * @since 1.0
 * @version 1.3
 */
public class GestionGroupeTaxe extends fr.analogon.r2t.main.RacineServlet 
{	
	private HttpServletRequest request;	
	private String choix= "";	
	private boolean creation=true;	
	private String idGroupe="";
	private String libelle="";
	private String  listeDesTypesDeTaxeLiee="";
	

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
				debug.logger.debug("ajout d'un groupe de taxe");
				 rd = sc.getRequestDispatcher("/entree?action=empl_gestion_groupeTaxe.jsp" +
					"&choix=modifier&idGroupe="+idGroupe);
			}
			
			else if  (choix.equalsIgnoreCase("modifier"))
			{
				debug.logger.debug("modification du groupe de taxe " + libelle);					
				rd = sc.getRequestDispatcher("/entree?action=empl_gestion_groupeTaxe.jsp" +
					"&choix=modifier&idGroupe="+idGroupe);			
			}
			
			else
			{			
				rd = sc.getRequestDispatcher("/entree?action=liste_groupeTaxe.jsp");
			}
			////System.out.println("Code  groupe de taxe"+codeUtilisateur );
			rd.forward(request, response);
		} 
		catch (Exception e) 
		{			
			debug.logger.fatal("Gestion groupe de taxes .....................[Erreur]"+e.getMessage());
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
		/*Enumeration params = request.getParameterNames();
		while (params.hasMoreElements()){
			String par = (String) params.nextElement();
			System.out.println("param="+par+":"+request.getParameter(par));	
		}		
		String[] type = request.getParameterValues("taxeLiee");
		listeDesTypesDeTaxeLiee=StringUtils.join(type, ";");
		*/
		
		//////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////
	    //TRAITEMENT DES DONNNEE ET ACCES A LA BD
		//////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////
		if ((request.getParameter("choix") != null)) this.choix = request.getParameter("choix");		
		if (request.getParameter("idGroupe") != null)	this.idGroupe= request.getParameter("idGroupe");
		if (request.getParameter("libelle") != null)	this.libelle= request.getParameter("libelle");	
		
		String[] type = request.getParameterValues("taxeLiee");
		//if (type!=null)	this.listeDesTypesDeTaxeLiee=StringUtils.join(type, ";");
		
		System.out.println("listeDesTypesDeTaxeLiee>>>> "+listeDesTypesDeTaxeLiee);
	
		
		if(choix.equalsIgnoreCase("ajouter"))
		{
			//System.out.println("Ajout d'un nouveau groupe de taxe ......");			
			RequestGroupeTaxe reqGroupeTaxe = new RequestGroupeTaxe();
			idGroupe=  String.valueOf(reqGroupeTaxe.ajouterGroupeTaxe(libelle,listeDesTypesDeTaxeLiee));
		}
		else if(choix.equalsIgnoreCase("modifier"))
		{			
			RequestGroupeTaxe reqGroupeTaxe = new RequestGroupeTaxe();		
			reqGroupeTaxe.modifierGroupeTaxe(idGroupe, libelle, listeDesTypesDeTaxeLiee);
			
		}	
		else if(choix.equalsIgnoreCase("supprimer"))
		{			
			//System.out.println("Sppreession de l'utilisateur"+ codeUtilisateur);
			RequestGroupeTaxe reqGroupeTaxe = new RequestGroupeTaxe();		
			reqGroupeTaxe.supprimerGroupeTaxe(idGroupe);			
		}
	
	}

		
}
