package fr.analogon.r2t.batch.facture;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.request.RequestFacture;


/**
 * Permet de generer tous les factrues . Sofien CHARFI
 * 
 * @since 1.0
 * @version 1.3
 */
public class GestionFactures extends fr.analogon.r2t.main.RacineServlet 
{	
		
	public String  actionFacture="";	
	public String  motifAnnulation="";	
	public String  numeroFacture="";	
	public String  numeroRedevable="";	
	public String  typeTaxe="";	
	public String  etatFacture="";	
	public String  dateCreationFacture="";	
	private HttpServletRequest request;
	
	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{		
		this.request =request;
		//System.out.println("*****GESTION facture ********");	
		getParametres();		
		response.sendRedirect("./entree?action=gestionFacture.jsp&numeroFacture="+numeroFacture );		
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
		if ((request.getParameter("numeroFacture") != null)
				&& (!request.getParameter("numeroFacture").equalsIgnoreCase(""))) 
			this.numeroFacture = request.getParameter("numeroFacture");		
		
		if ((request.getParameter("actionFacture") != null)
				&& (!request.getParameter("actionFacture").equalsIgnoreCase(""))) 
			this.actionFacture = request.getParameter("actionFacture");	
		
		if ((request.getParameter("motifAnnulation") != null)
				&& (!request.getParameter("motifAnnulation").equalsIgnoreCase(""))) 
		{
			this.motifAnnulation = request.getParameter("motifAnnulation");
			//System.out.println("motifAnnulation="+motifAnnulation);
		}			
					
		if (actionFacture.equalsIgnoreCase("ANNULEE"))
		{				
			RequestFacture req = new RequestFacture();			
			req.annulerFacture(numeroFacture,motifAnnulation);			
		}
	}

}