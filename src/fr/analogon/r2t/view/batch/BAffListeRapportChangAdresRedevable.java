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
public class BAffListeRapportChangAdresRedevable extends fr.analogon.r2t.main.RacineBean {

	private String attribut;
	HttpServletRequest req;
	public void setRequest(HttpServletRequest req) 
	{		
	
	}
	

	/** Retourne l'attribut pour ordonner le tableau */
	public String getattribut() 
	{
		return attribut;
	}
	
	
	public String getlisteRapportChangementAdresse()
	{		
		String res="";
		StaticManipHtml mHtml = new StaticManipHtml();		
		RequestBatch reqBatch = new RequestBatch();
		Vector contenu = reqBatch.getListeRapportChangementAdresse();			
		res=StaticManipHtml.genererTableauListeRapportChangementAdresse(contenu);
		//System.out.println(
		return res;
	}
	
	
	
	
	
	
	

}