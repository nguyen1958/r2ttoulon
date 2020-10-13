package fr.analogon.r2t.reclamation;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.request.RequestReclamation;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de List des reclamation . Sofien CHARFI
 * 
 * @version 1.0
 * @since 1.0
 */
public class BAffListeReclamation extends fr.analogon.r2t.main.RacineBean {

	
		
	private HttpServletRequest request;	
	private String etatReclamation= "";	
	private String numeroFacture="";	
	private String typeTaxe="";	
	private String numeroRedevable="";	
	private String dateCreationReclamation="";	
	private String controleEffectue="";	
	private String anneeTitre="";	
	private String numeroTitre="";	
	private String nomRedevable="";		 

	public void setRequest(HttpServletRequest req)
	{		
		//System.out.println("set request");
		this.request = req;
		
		if (request.getParameter("numeroFacture") != null)
			this.numeroFacture = request.getParameter("numeroFacture");
		
		if (request.getParameter("etatReclamation") != null)
			this.etatReclamation = request.getParameter("etatReclamation");
		
		if (request.getParameter("typeTaxe") != null)
			this.typeTaxe = request.getParameter("typeTaxe");
		
		if (request.getParameter("numeroRedevable") != null)
			this.numeroRedevable= request.getParameter("numeroRedevable");
		
		if (request.getParameter("nomRedevable") != null)
			this.nomRedevable= request.getParameter("nomRedevable");
		
		if (request.getParameter("dateCreationReclamation") != null)
			this.dateCreationReclamation = request.getParameter("dateCreationReclamation");
		
		if (request.getParameter("controleEffectue") != null)
			this.controleEffectue = request.getParameter("controleEffectue");
		
		if (request.getParameter("anneeTitre") != null)
			this.anneeTitre = request.getParameter("anneeTitre");
		
		if (request.getParameter("numeroTitre") != null)
			this.numeroTitre = request.getParameter("numeroTitre");
		
		etatReclamation = etatReclamation.toLowerCase().replaceAll(" ", "");
		if (etatReclamation.equalsIgnoreCase("clotures")) etatReclamation = "cloturer";
	}	
			
	
	public String getlisteReclamations()
	{
		String res="";
		StaticManipHtml mHtml = new StaticManipHtml();
		RequestReclamation reqReclam= new RequestReclamation();
		Vector contenu = reqReclam.getListeReclamations(etatReclamation,numeroFacture,
				typeTaxe,numeroRedevable, dateCreationReclamation,
				controleEffectue,nomRedevable);			
		res=mHtml.genererTableauListeReclamations(contenu);
		//System.out.println(
		return res;
	}

	public String getEtatReclamation() 
	{
		StaticManipHtml mHtml = new StaticManipHtml();
		RequestReclamation req = new RequestReclamation();
		Vector Contenu = req.getTousLesEtatsReclamation();		
		String res= StaticManipHtml.genererListeDeroulanteApartirDeVacteur("etatReclamation", 
				etatReclamation, Contenu, 1, false);
		return res;
	}
}




