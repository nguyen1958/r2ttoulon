package fr.analogon.r2t.view.batch;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.request.RequestBatch;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de batchJourneaux.jsp . Sofien CHARFI
 * 
 * @version 1.0
 * @since 1.0
 */
public class BAffListeRapportSuivieFacturation extends fr.analogon.r2t.main.RacineBean {

	private String attribut;
	HttpServletRequest req;
	String listeRapportSuivieFacturation="";
	public void setRequest(HttpServletRequest req) 
	{		
	
	}
	

	/** Retourne l'attribut pour ordonner le tableau */
	public String getattribut() 
	{
		return attribut;
	}
	
	
	public String getListeRapportSuivieFacturation()
	{		
		String res="TEST";
		StaticManipHtml mHtml = new StaticManipHtml();		
		RequestBatch reqBatch = new RequestBatch();
		Vector contenu = reqBatch.getListeRapportSuivieFacturation();			
		res=StaticManipHtml.genererTableauListeRapportSuivieFacturation(contenu);		
		return res;
	}
	
	
	
	
	
	
	

}