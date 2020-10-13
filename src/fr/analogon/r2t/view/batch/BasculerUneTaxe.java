package fr.analogon.r2t.view.batch;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.request.RequestBascule;



public class BasculerUneTaxe extends fr.analogon.r2t.main.RacineServlet 
{
	private HttpServletRequest request;	

	
	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)
	  {
			try 
		{			
			this.request =request;
			String typeDeTaxe= request.getParameter("typeDeTaxe").toString(); 
			getParametres(typeDeTaxe);
			ServletContext sc = getServletConfig().getServletContext();	
			RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=ok.jsp&toRedirect=batch_divers.jsp");
			rd.forward( request, response);	
		} 
		catch (Exception e) 
		{
			debug.logger.fatal(e.getMessage());
		}		
	}
	
	

	public void getParametres(String typeDeTaxe)
	{	
		RequestBascule req = new RequestBascule();				
		req.basculerUneTaxe(typeDeTaxe); 
		 
		
	}
	
	public void destroy() 
	{
		super.destroy();		
	}

}
