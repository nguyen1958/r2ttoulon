package fr.analogon.r2t.changementTransfert;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.request.RequestRedevable;


/**
 * Servlet qui permet de changer une adresse d'un redevable 
 Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0
 */
public class GestionChangementAdresseRedevable extends   fr.analogon.r2t.main.RacineServlet  {
	
	
	private String numRedevableAChangerAdresse;
	private String numVoieRedevable;
	private String codeVoixRedevable;
	private String adressRedevable;
	private String complement1AdressRedevable;
	private String complement2AdressRedevable;
	private String codePostaleRedevable;
	private String villeeRedevable;
	private String cedexRedevable;
	
	private String complementNumeroRueRedevable;
	private String complementNumeroRueLiquidateur;
	
	private HttpServletRequest request;
	
	
	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)
	  {
		//System.out.println("Changement d'adresse de redevable....................................");
		//trace.ecritDebug("Changement d'adresse de redevable....................................");
		try 
		{			
			this.request =request;					
			getParametres();
			ServletContext sc = getServletConfig().getServletContext();	
			//RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=batch_divers.jsp&transfer=ok");
			RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=ok.jsp&toRedirect=batch_divers.jsp");
			//System.out.println("Changement d'adresse de redevable..................................[OK]");
			rd.forward( request, response);			
		} 
		catch (Exception e) 
		{
			//System.out.println("Changement d'adresse de redevable.....................[Erreur]");
			debug.logger.fatal(e.getMessage());
		}		
	}
	
	
	
	

	public void getParametres()
	{	
		//System.out.println("Setting parameter pour changement d'adresse du redvable " );	
		
		if (request.getParameter("numRedevableAChangerAdresse") != null) { this.numRedevableAChangerAdresse = request.getParameter("numRedevableAChangerAdresse");  }
		if (request.getParameter("numVoieRedevable") != null) { this.numVoieRedevable = request.getParameter("numVoieRedevable");  }
		if (request.getParameter("codeVoixRedevable") != null) { this.codeVoixRedevable = request.getParameter("codeVoixRedevable");  }
		if (request.getParameter("adressRedevable") != null) { this.adressRedevable = request.getParameter("adressRedevable");  }
		if (request.getParameter("complement1AdressRedevable") != null) { this.complement1AdressRedevable = request.getParameter("complement1AdressRedevable");  }
		if (request.getParameter("complement2AdressRedevable") != null) { this.complement2AdressRedevable = request.getParameter("complement2AdressRedevable");  }
		if (request.getParameter("codePostaleRedevable") != null) { this.codePostaleRedevable = request.getParameter("codePostaleRedevable");  }
		if (request.getParameter("villeeRedevable") != null) { this.villeeRedevable = request.getParameter("villeeRedevable");  }
		if (request.getParameter("cedexRedevable") != null) { this.cedexRedevable = request.getParameter("cedexRedevable");  }
			

		////System.out.println("numNewRedevable "+ numNewRedevable);
		////System.out.println("numEmplacementATransferer>>>> "+ numEmplacementATransferer);
		////////////////////////////////////////////////////////////////////////
	    //TRAITEMENT DES DONNNEE ET ACCES A LA BD
		//////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////
		RequestRedevable req = new RequestRedevable();
		req.changerAdresse(numRedevableAChangerAdresse,	numVoieRedevable,
				codeVoixRedevable,adressRedevable,complement1AdressRedevable,
				complement2AdressRedevable,	codePostaleRedevable,villeeRedevable,
				cedexRedevable,complementNumeroRueRedevable);
		
	}
	
	public void destroy() 
	{
		super.destroy();		
	}


	
}


