package fr.analogon.r2t.view.alertes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/** Servelt gerant les arrete */
public class ListeAlertes extends fr.analogon.r2t.main.RacineServlet 
{
	private String dateArrete;	

	
	public void init() throws ServletException 
	{		
		//System.out.println("INIT ALETES");
	}

	
	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		
		
		//fr.analogon.r2t.util.web.StaticManipHtml.afficheParametreHtml(request,System.out);		
		
		response.sendRedirect("<script><script>"); 
	}

	/** Recuperation des parametres html */
	private void RecuperationParamHtml(HttpServletRequest request)
	{
		//this.req = request;
		//this.choix = req.getParameter("choix");		
	}

	/** Generation des Alertes */
	private void genererAlertes()  
	{		
		//System.out.println(
	}

}
