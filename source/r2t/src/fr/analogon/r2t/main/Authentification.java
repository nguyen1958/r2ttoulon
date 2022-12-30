   package fr.analogon.r2t.main;

 import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.analogon.r2t.pojo.Utilisateur;
import fr.analogon.r2t.request.RequestAdmin;
import fr.analogon.r2t.request.RequestParametres;
import fr.analogon.r2t.request.RequestUtilisateur;


 /**
  * Permet l'authetification Sofien CHARFI
  * 
  * @since 1.0
  * @version 1.3
  */
 public class Authentification extends fr.analogon.r2t.main.RacineServlet 
 {	
 	private HttpServletRequest request;	 	
 	private String loginUtilisateur="";	
 	private String motDePaseeUtilisateur=""; 
 	private String compteValideUtilisateur="";
 	private boolean verifierUser =false;
 	private String typeTaxeAutorise ="";
 	private String typeUtilisateur ="";
 	private String idUtilisateur ="";	
 	DebuggerLog4J debug = new DebuggerLog4J();
 	RequestUtilisateur reqUtilisateur = new RequestUtilisateur();
 	RequestParametres requestParametres = new RequestParametres();
 	RequestAdmin requestAdmin = new RequestAdmin();
 	HttpSession session ;
 	
 	public void doMain(HttpServletRequest request, HttpServletResponse response)			
 	{ 	
 		//Ecriture de le fichier trace		
 		try 
 		{
 					session = request.getSession(true);				
 					InitialisationConnexionLectureConfiguration c = new InitialisationConnexionLectureConfiguration();	
		 			this.request = request;			
		 			getParametres();	
		 			ServletContext sc = super.getServletContext();
		 			debug.logger.debug("Essai d'authentification ");		 			
		 			String typeApplication ="";
		 			if (request.getRequestURL().toString().contains("recensement"))
		 				typeApplication="recensement";
		 			else if (request.getRequestURL().toString().contains("r2t"))
		 				typeApplication="r2t";
		 			session.setAttribute("typeApplication", typeApplication);
		 			String msg = (String)request.getParameter("msg");
		 				 				
		 			if( verifierUser)
		 			{				
		 				session = request.getSession(true);	
		 				session.setAttribute("typeTaxeAutorise",typeTaxeAutorise);
		 				session.setAttribute("typeUtilisateur",typeUtilisateur);
		 				session.setAttribute("idUtilisateur",idUtilisateur);
		 				String regie= requestAdmin.getRegie();		 				
		 				String marche= requestAdmin.getMarche();
		 				String ville  =   requestParametres.getVille();
		 				session.setAttribute("ville",ville);
		 				
		 				if(ville.equalsIgnoreCase("Bordeaux")) 
		 				{
		 					//session.setAttribute("regie","false");
		 					//session.setAttribute("marche","false");
		 					session.setAttribute("regie",regie);
		 					if (marche !=null && marche.length()!=0 )
		 						session.setAttribute("marche",marche);
		 				}
		 				else
		 				{
		 					session.setAttribute("regie",regie);
		 					if (marche !=null && marche.length()!=0 )
		 						session.setAttribute("marche",marche);
		 				}
		 				
		 				
		 				debug.logger.debug(" Utilisateur connecté avec les parametres suivant : " +
		 						" login = "+ loginUtilisateur + " mot de passe ="+ motDePaseeUtilisateur); 
		 				
		 				
		 				if(msg.equalsIgnoreCase("sessionExpire"))
		 				{
		 					response.getWriter().write("<script>history.go(-2);</script>");		 					
		 				}
		 				else
		 				{		 					
		 					RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=index.jsp"); 				
			 				rd.forward(request, response);
		 				}
		 			}
		 			else
		 			{
		 				debug.logger.debug("Echec d'authentification  avec les parametres : login="
		 						 + loginUtilisateur + " et mot de passe: " + motDePaseeUtilisateur);
		 				RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=login.jsp&msg=erreurParametres");
		 				rd.forward(request, response);				
		 			}	
		 			
		 System.gc(); 
 		} 
 		catch (Exception e) 
 		{ 			
 			e.printStackTrace();
 			debug.logger.debug("Erreur...." + e.getMessage());					 
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
 		//////////////////////////////////////////////////////////////////////
 		////////////////////////////////////////////////////////////////////////
 	    //TRAITEMENT DES DONNNEE ET ACCES A LA BD
 		//////////////////////////////////////////////////////////////////////
 		//////////////////////////////////////////////////////////////////////
 		if (request.getParameter("login") != null)	
 			this.loginUtilisateur= request.getParameter("login");
 		if (request.getParameter("pass") != null)
 			this.motDePaseeUtilisateur= request.getParameter("pass"); 		
 				 
 		Utilisateur utilisateur =reqUtilisateur.getUtilisateur("",loginUtilisateur,motDePaseeUtilisateur);
 		
 		if(utilisateur == null || utilisateur.getLogin() ==null)
 			verifierUser= false;
 		else
 		{
 			compteValideUtilisateur = utilisateur.getValide();
 			if(compteValideUtilisateur.equalsIgnoreCase("oui"))
 			{
	 			verifierUser= true;	 			
	 			idUtilisateur = utilisateur.getId();		
	 			typeTaxeAutorise = utilisateur.getListeLibelleDesTypesDeTaxeAutorise();
	 			typeUtilisateur = utilisateur.getTypeUtilisateur();
	 			if(typeUtilisateur.equalsIgnoreCase("ADMINISTRATEUR"))
	 				typeUtilisateur="admin";
	 			if(typeUtilisateur.equalsIgnoreCase("GESTIONNAIRE"))
	 				typeUtilisateur="gest";
 			}
 			else
 				verifierUser= false;
	 			
 		}
 	} 		
 }
