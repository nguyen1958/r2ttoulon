package fr.analogon.r2t.administration;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.request.DataFromBD;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de Synchronisaiton
 * 
 * @version 1.0
 * @since 1.0
 */
public class BAffSynchro extends fr.analogon.r2t.main.RacineBean {

	private String attribut;
	HttpServletRequest req;
	String nomQuartier="";
	String dateControle ="";
	String utuilsateur ="";

	
	public void setRequest(HttpServletRequest req) 
	{		
		this.req=req;
		this.nomQuartier = req.getParameter("nomQuartier");
		this.dateControle = req.getParameter("dateControle");
		this.utuilsateur = req.getParameter("utuilsateur");
	}
	

	/** Retourne l'attribut pour ordonner le tableau */
	public String getattribut() 
	{
		return attribut;
	}
	
	
	public String gethistoriqueSynchronisation()
	{	
		String res="";
		StaticManipHtml mHtml = new StaticManipHtml();
		DataFromBD req = new DataFromBD();
		Vector contenu =req.getSynchonisaltion(nomQuartier, dateControle , utuilsateur);
		res=StaticManipHtml.genererTableauHistoriqueSynchro(contenu);	
		//System.out.println(
		return res;
	}
}

