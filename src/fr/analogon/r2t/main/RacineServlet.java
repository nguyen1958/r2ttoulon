package fr.analogon.r2t.main;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;


/**
 * Toutes les servlets heritent de cette classe. Ceci afin de gerer plus
 * efficacement la gestion des session et des ensembles d'utilisateurs. Ces
 * servlets traitent indifferaments les requetes post et les requetes get. .
 * Sofien CHARFI
 * 
 * @version 1.1
// * @since 1.0
 */

public abstract class RacineServlet extends HttpServlet  
{
	
	public static DebuggerLog4J debug = new DebuggerLog4J();
	public static FichierDeConfiguration  fichierDeConfiguration = InitialisationConnexionLectureConfiguration.fichierDeConfiguration;
	
	public static String idUtilisateur ;
	

	/** initalisation de la servlet RacineServletWeb */
	
	public void init() throws ServletException 
	{
		try
		{		
			//debug.logger.debug("Initialisation de session ........................");
			
			
		}
		catch (Exception e) 
		{	
			
			//debug.logger.debug("erreur dans RacineServlet " + e.getMessage());
		}
	}

	/**
	 * Cette methode contient le traitement principale de la servlets
	 * 
	 * @param request
	 *            la requete envoye a la servlet
	 * @param response
	 *            la reponse envoye par la servlet . Sofien CHARFI
	 * @since 1.0
	 */
	
	public abstract void doMain(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException 
	{			
			
			doMain(request, response);	
			idUtilisateur = (String)request.getSession().getAttribute("idUtilisateur");			
			////System.out.println("idUtilisateur="+idUtilisateur);		
	}
	
	/**
	 * Traiter la requéte HTTP Post, La requette post est redirigee sur la
	 * requette get . Sofien CHARFI
	 * 
	 * @since 2.0
	 */	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

