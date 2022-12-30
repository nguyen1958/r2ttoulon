package fr.analogon.r2t.view.alertes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.request.RequestAlerte;


/**
 * Permet de gerer les alerets 
 * Sofien CHARFI
 * 
 * @since 1.0
 * @version 1.3
 */
public class Gestionalertes extends fr.analogon.r2t.main.RacineServlet 
{	
	public String numeroAlerte="";	
	public String actionAlerte="";	
	public String etatAlerte="";	
	public String remarqueAlerte="";	
	public String dateFinAlerte="";	
	public String textAlerte="";		
	public String idEmplacement="";	
	public String idAlerte="";
	public String imageASupprimer="";
	public String nomQuartier="";
	
	private HttpServletRequest request;	
	
	
	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{		
		this.request = request;		
		getParametres();	
		//System.out.println("idAlerte "+ idAlerte);
		String choix = request.getParameter("choix");
		if(actionAlerte.equalsIgnoreCase("modifierAlerte"))
			response.sendRedirect("./entree?action=empl_saisie_alerteSpantanne.jsp" +"&idAlerteSpontanne="+idAlerte );			
		else			
			response.sendRedirect("./entree?action=liste_alertes.jsp" );
	}
	
	public void destroy() {
		super.destroy();
		// mdBatchTraitement.closeConnexion();
	}

	/**
	 * Permet de recuperer les parametres html 
	 * 
	 * @since 1.0
	 */
	private void getParametres() 
	{		
		if ((request.getParameter("numeroAlerte") != null)) 
			this.numeroAlerte = request.getParameter("numeroAlerte");
		
		if ((request.getParameter("actionAlerte") != null)) 
			this.actionAlerte = request.getParameter("actionAlerte");

		if ((request.getParameter("etatAlerte") != null)) 
			this.etatAlerte = request.getParameter("etatAlerte");
			
		if ((request.getParameter("remarqueAlerte") != null)		)		
			this.remarqueAlerte = request.getParameter("remarqueAlerte");
		
		if ((request.getParameter("nomQuartier") != null))		
			this.nomQuartier = request.getParameter("nomQuartier");
		
		if ((request.getParameter("dateFinAlerte") != null))						
			this.dateFinAlerte = request.getParameter("dateFinAlerte");
		
		if ((request.getParameter("textAlerte") != null))					
			this.textAlerte = request.getParameter("textAlerte");
		
		
		if (request.getParameter("idEmplacement") != null)
			this.idEmplacement= request.getParameter("idEmplacement");
			
		if (request.getParameter("numeroAlerte") != null)
				this.idAlerte= request.getParameter("numeroAlerte");

		if(actionAlerte.equalsIgnoreCase("modifierAlerte") )
		{
			RequestAlerte req = new RequestAlerte();			
			req.majAlerte(Integer.parseInt(numeroAlerte), etatAlerte, remarqueAlerte
					,dateFinAlerte,textAlerte);
			if (request.getParameter("imageASupprimer") != null)
				this.imageASupprimer= request.getParameter("imageASupprimer");
			if(imageASupprimer.length() != 0)
			{
				//System.out.println("Suppreession nde photo");
				
				req.supprimerImageAlerte(imageASupprimer);		
				//reqEmplacement.supprimerImageEmplacementGeneral(nomImageASupprimer);
			}					
		}
		else if(actionAlerte.equalsIgnoreCase("supprimerAlerte"))
		{			
			RequestAlerte req = new RequestAlerte();					
			req.supprimerAlerte(Integer.parseInt(numeroAlerte));
			 
			
		}		
	}
}


