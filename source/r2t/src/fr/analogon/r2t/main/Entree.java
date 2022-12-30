package fr.analogon.r2t.main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.util.web.StaticManipHtml;
import fr.analogon.r2t.util.web.StaticManipServlet;


/**
 * A finir, ne gere pas les droits!. La servlet Entree est le point d'entree de
 * toute requete sur le site. Elle verifie si la session est valide et si
 * l'utilisateur a les droits pour accéder é la page demandé Elle redirige vers
 * la page JSP ou la servlet voulu grace au RequestDispatcher. Pour savoir
 * quelle jsp ou servlet est demandé on se sert du parametre action passé dans
 * la request. . Sofien CHARFI
 * 
 * @version 1.0
 * @since 1.0
 */


public class Entree extends RacineServlet {

	
	StaticManipServlet manip = new StaticManipServlet();
	StaticManipHtml StaticManipHtml = new StaticManipHtml();
	
	public void init() throws ServletException 
	{		
		super.init();
	}
	
	public void doMain(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		ConfVariable conf = new ConfVariable();		
		String preJsp = "/jsp/";
		String preUrlJbin ="./";
		try 
		{
				System.out.println(">>> doMain");
				String typeUtilisateur=  (String) request.getSession().getAttribute("typeUtilisateur");
				String typeTaxeAutorise=  (String) request.getSession().getAttribute("typeTaxeAutorise");
				String chaineInterro = request.getQueryString();
				chaineInterro = "?" + chaineInterro;					
				//recupere la valeur d'action
				String action = request.getParameter(ConfVariable.varParametreAction);			
				// Si l'action est login.jsp, on ouvre directement login.jsp sans
				System.out.println(">>> "+preJsp+"|"+action+"|"+chaineInterro);
				// faire de vérification sur la session			
				if (action.equalsIgnoreCase(conf.pageJspLogin))		
				{
					System.out.println(">>> doMain conf.pageJspLogin");
					// Redirection vers la page jsp demandée login.jsp
					RequestDispatcher rd = request.getRequestDispatcher(preJsp+ action + chaineInterro);
					//verfie si la page login est suite a une expiration de session 
					//on fait un retour vers hytorique -2 , sinon on ;
					idUtilisateur =(String) request.getSession().getAttribute("idUtilisateur");
					debug.logger.debug("Deconnexion de l'utulisateur  " + idUtilisateur);	
					request.getSession().invalidate();
					//System.out.println("session invalidate ");
					rd.forward(request, response);					
				}
				//Rediredtion vers autre jsp ou servlet
				else 
				{
					System.out.println(">>> doMain Rediredtion vers autre jsp ou servlet");
					int tailleString = action.length();
					int debutSub = tailleString - 4;
					String extension = action.substring(debutSub);
					//if (true)
					if (typeUtilisateur != null)
					{
						debug.logger.debug("la session est correcte");					
						if (extension.equals(".jsp")) 
						{
							System.out.println(".jsp|"+preJsp +":"+ action+":"	+ chaineInterro);
							// redirection vers une jsp							
							RequestDispatcher rd = request.getRequestDispatcher(preJsp + action	+ chaineInterro);
							rd.forward(request, response);						
						}
						else
						{
							// redirection vers une servlet
							System.out.println("redir servlet|"+preJsp + action	+ chaineInterro);
							response.sendRedirect(preUrlJbin + action	+ chaineInterro);
						}
					}
					else 
					{
						debug.logger.debug("la session a expiré, reidection vers login.jsp");						
						//Redirection vers la page jsp demandée						
						RequestDispatcher rd = request.getRequestDispatcher(preJsp+"login.jsp?msg=sessionExpire" );
						////System.out.println(b);
						rd.forward(request, response);
					}
				}				
		}
		catch (Exception e) 
		{		
			
			new DebuggerLog4J().logger.fatal("Erreur dans la classe Entree..." + e.getMessage() +e.getMessage());
			e.printStackTrace();			
			debug.logger.fatal(e.getCause().toString());
			 
			RequestDispatcher rd = request.getRequestDispatcher("/entree?action=afficheErreur.jsp");
			rd.forward(request, response);			
		}
			
		
				
		//LanceGarbageCollector.gc();
	}
}



