package fr.analogon.r2t.changementTransfert;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.pojo.Emplacement;
import fr.analogon.r2t.request.RequestEmplacement;


/**
 * Servlet qui permet de faire le transfert d'un emplacment vers un nouveau redevable 
 * Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0
 */
public class GestionTransfertEmplacement extends   fr.analogon.r2t.main.RacineServlet  {
	
	private String numEmplacementATransferer;	
	private String numNewRedevable;	
	//Paul 31/10/2017
	private String redirectTo;
	private HttpServletRequest request;
	
	
	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)
	  {
		RequestDispatcher rd;
		//System.out.println("Transfer d'emplacement.....................................");
		try 
		{			
			this.request =request;					
			getParametres();
			ServletContext sc = getServletConfig().getServletContext();	
			//RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=batch_divers.jsp&transfer=ok");
			//Paul 31/10/2017=> empl_resultat_recherche_alerte.jsp&numRedevable=-1&alerte=1
			if(redirectTo!=null){
				rd=sc.getRequestDispatcher("/entree?action="+redirectTo);
			}				
			else{
				rd= sc.getRequestDispatcher("/entree?action=ok.jsp&toRedirect=batch_divers.jsp");
			}			
			//System.out.println("Transfer d'emplacement..................................[OK]");
			rd.forward( request, response);			
		} 
		catch (Exception e) 
		{
			//System.out.println("Transfer d'emplacement.....................[Erreur]");
			debug.logger.fatal(e.getMessage());
		}
		
			
		//response.sendRedirect("./entree?action=empl_recherche_reclamations.jsp");		
		//response.getWriter().write("<script>history.go(-3);</script>");
		
	}
	
	
	
	

	public void getParametres()
	{	
		//System.out.println("Setting parameter in baff gestion trasnfert" );
		//this.trace.ecritDebug("setReq de gestion de transfert d'empalcment");
		////System.out.println("///////////////////////////////////2");
		if (request.getParameter("numEmplacementATransferer") != null) 
			{ this.numEmplacementATransferer = request.getParameter("numEmplacementATransferer");	}
		
		if (request.getParameter("numNewRedevable") != null) 
		{ this.numNewRedevable = request.getParameter("numNewRedevable");	}	
		
		//Paul 31/10/2017 
		this.redirectTo = request.getParameter("redirectTo");		

		System.out.println("redirectTo="+ redirectTo);
		////System.out.println("numEmplacementATransferer>>>> "+ numEmplacementATransferer);
		////////////////////////////////////////////////////////////////////////
	    //TRAITEMENT DES DONNNEE ET ACCES A LA BD
		//////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////
		RequestEmplacement req = new RequestEmplacement();
		Emplacement empl = req.getEmplacement(numEmplacementATransferer);
				
		
		req.transfererEmplacement(numEmplacementATransferer, numNewRedevable);
		
	}
	
	public void destroy() 
	{
		super.destroy();		
	}


	
}


