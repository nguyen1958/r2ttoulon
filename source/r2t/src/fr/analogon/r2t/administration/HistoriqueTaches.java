package fr.analogon.r2t.administration;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.request.RequestAdmin;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de Synchronisaiton
 * 
 * @version 1.0
 * @since 1.0
 */
public class HistoriqueTaches extends fr.analogon.r2t.main.RacineBean {

	private String historiqueTache;
	HttpServletRequest req;
	String date,actionRecherche,motClef,typeTaxe;
	public void setRequest(HttpServletRequest req) 
	{		
		this.req=req;
		this.date = req.getParameter("dateAction");
		this.actionRecherche = req.getParameter("actionRecherche");
		this.motClef = req.getParameter("motClef");
		this.typeTaxe = req.getParameter("typeTaxe");
		//System.out.println(date);
		//System.out.println(actionRecherche);
		//System.out.println(motClef);
		//System.out.println(typeTaxe);
	
	}
	
	public String getHistoriqueTache()
	{		
		String res="";
		StaticManipHtml mHtml = new StaticManipHtml();		
		RequestAdmin reqAdmin = new RequestAdmin();
		Vector contenu =reqAdmin.getHistoriquesTaches(date,actionRecherche,motClef,typeTaxe);
		res=StaticManipHtml.genererTableauHistoriqueTaches(contenu);
		//res="test";
		//System.out.println(
		return res;
	}
	
	
	
	
	
	
	

}