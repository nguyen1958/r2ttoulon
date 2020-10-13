package fr.analogon.r2t.view.batch;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.request.RequestBascule;



public class BasculerTLPE extends fr.analogon.r2t.main.RacineServlet 
{
	private HttpServletRequest request;	

	
	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)
	  {
		//System.out.println("Baculer les TLPE.....................................");
		try 
		{			
			this.request =request;						
			getParametres();
			ServletContext sc = getServletConfig().getServletContext();	
			//RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=batch_divers.jsp&transfer=ok");
			RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=ok.jsp&toRedirect=batch_divers.jsp");
			rd.forward( request, response);	
			//System.out.println("Baculer les TLPE...................................................[OK]");
		} 
		catch (Exception e) 
		{
			//System.out.println("Baculer les TLPE.....................................[Erreur]");
			debug.logger.fatal(e.getMessage());
		}		
	}
	
	

	public void getParametres()
	{	
		RequestBascule req = new RequestBascule();				
		req.basculerTLPE(GestionDate.getAnneeCourante());
		 
		
	}
	
	public void destroy() 
	{
		super.destroy();		
	}

}
