package fr.analogon.r2t.view.batch;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.request.RequestBascule;


public class BasculerImputationBareme extends fr.analogon.r2t.main.RacineServlet 
{

	private String anneeExercice;
	private String poucentage;
	private HttpServletRequest request;
		

	
	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)
	  {
		//System.out.println("Baculer les type de taxe et les bareme .....................................");
		try 
		{			
			this.request =request;						
			getParametres();
			ServletContext sc = getServletConfig().getServletContext();	
			//RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=batch_divers.jsp&transfer=ok");
			RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=ok.jsp&toRedirect=batch_divers.jsp");
			rd.forward( request, response);	
			//System.out.println("Baculer les type de taxe et les bareme ..................................................[OK]");
		} 
		catch (Exception e) 
		{
			//System.out.println("Baculer les type de taxe et les bareme ...................................[Erreur]");
			debug.logger.fatal(e.getMessage());
		}		
	}
	
	

	public void getParametres()
	{
		anneeExercice= request.getParameter("anneeExercice");
		poucentage= request.getParameter("pourcentage");
		//System.out.println("Bascule avec un poucentage de "+poucentage+" %");		
		//System.out.println("poucentage double = "+Double.valueOf(poucentage));		
		//System.out.println("acces BD et transfert "+anneeExercice);
		RequestBascule req = new RequestBascule();
		//double poucentage =3.5 ;		
		req.basculerImputation(anneeExercice,Double.valueOf(poucentage));
		req.basculerBaeme(anneeExercice,Double.valueOf(poucentage));
		req.ecrireHistoriqueBascule(anneeExercice, GestionDate.getDateAujourdhuiString(),poucentage);		
		
	}
	
	public void destroy() 
	{
		super.destroy();		
	}

}
