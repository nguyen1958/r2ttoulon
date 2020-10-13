package fr.analogon.r2t.batch.facture;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.request.RequestRefacturation;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de liste de refacturation . Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0
 */
public class BAffListeRefacturation extends fr.analogon.r2t.main.RacineBean {

			
	private HttpServletRequest request;		
	private String listeRefacturation;
	private String anneeExercice="";	
	private String typeTaxe="";
	private String numeroFacture="";
	private String numRedevable="";
	private String dateRefacturation="";	 

	public void setRequest(HttpServletRequest req)
	{
		
		//System.out.println("set request Baff liste refacturation....");
		this.request = req;
		//System.out.println("anneeExercice="+request.getParameter("anneeExercice"));
		if (request.getParameter("anneeExercice") != null)	this.anneeExercice= request.getParameter("anneeExercice");
		if (request.getParameter("typeTaxe") != null)	this.typeTaxe= request.getParameter("typeTaxe");
		if (request.getParameter("numeroFacture") != null)	this.numeroFacture= request.getParameter("numeroFacture");
		if (request.getParameter("numRedevable") != null)	this.numRedevable= request.getParameter("numRedevable");
		if (request.getParameter("dateCreationRefacturation") != null)	this.dateRefacturation= request.getParameter("dateCreationRefacturation");

	}	
		
	
	public String getlisteRefacturation()
	{
		String res="";
		StaticManipHtml mHtml = new StaticManipHtml();
		RequestRefacturation reqRefacturation= new RequestRefacturation();
		Vector contenu =reqRefacturation.getListeRefacturation(anneeExercice,typeTaxe,
				numeroFacture,numRedevable,dateRefacturation);			
		res=mHtml.genererTableauListeRefacturation(contenu);
		//res="Sofien";
		//System.out.println(
		return res;
	}	

}