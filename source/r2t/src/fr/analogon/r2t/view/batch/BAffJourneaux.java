package fr.analogon.r2t.view.batch;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.request.RequestCreationFactures;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de batchJourneaux.jsp . Sofien CHARFI
 * 
 * @version 1.0
 * @since 1.0
 */
public class BAffJourneaux extends fr.analogon.r2t.main.RacineBean {

	private String attribut;
	HttpServletRequest req;
	String typeTaxe="";
	String date="";	
	String ACreeRapportsAnnulation="";
	String aCreeDesFactures="";
	String etatBatch="";
	public void setRequest(HttpServletRequest request) 
	{
		try 
		{
			
			
			this.req = request;
			etatBatch = req.getParameter("etatBatch");
			if(etatBatch != null)
			{
				typeTaxe= req.getParameter("typeTaxe");			
				date = req.getParameter("dateAction");	
				
				if(etatBatch != null && etatBatch.contains("Non"))
					etatBatch ="false";
				else
					etatBatch ="true";
			
				ACreeRapportsAnnulation= req.getParameter("ACreeRapportsAnnulation");
				aCreeDesFactures= req.getParameter("aCreeDesFactures");
			}
			
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		
	}
	

	/** Retourne l'attribut pour ordonner le tableau */
	public String getattribut() 
	{
		return attribut;
	}
	
	
	public String gethistoriquebatch()
	{		
		String res="";
		StaticManipHtml mHtml = new StaticManipHtml();
		RequestCreationFactures fac= new RequestCreationFactures();
		String attribut= "numeroBatchTraitement";	
		Vector contenu = fac.getHistoriqueBatch(attribut, date , typeTaxe, etatBatch,ACreeRapportsAnnulation,aCreeDesFactures);
		res=StaticManipHtml.genererTableauHistoriqueBatch(contenu);
	
		return res;
	}
	
	
	
	
	
	
	

}