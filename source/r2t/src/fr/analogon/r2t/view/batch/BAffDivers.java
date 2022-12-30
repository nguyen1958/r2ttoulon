package fr.analogon.r2t.view.batch;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Bascule;
import fr.analogon.r2t.pojo.Imputation;
import fr.analogon.r2t.request.RequestBascule;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.request.RequestParametres;
import fr.analogon.r2t.util.web.StaticManipHtml;


public class BAffDivers extends fr.analogon.r2t.main.RacineBean
{

	private boolean basculeFaite = false;
	private String dateAjourdhui="";
	private String anneeExercice= "";
	private String anneeExerciceSuivante= "";
	private String dateBascule = "";
	private String pourcentage ="";
	private String ville ="";
	private String listeSecteur="";
	private String relance ="";
	private String siteWebVille ="";
	private String tousLesTypeDeTaxe ="";
	
	private String numEmplacement="";

	RequestParametres requestParametres = new RequestParametres();
	
	public String getTousLesTypeDeTaxe() 
	{					
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();	
		RequestImputation reqImputation = new RequestImputation();
		Vector listDesTypeTaxe = reqImputation.getLesDifferentTypesTaxe();
		for (int i = 0; i < listDesTypeTaxe.size(); i++) 
		{
			contenu.addElement(((Imputation)listDesTypeTaxe.elementAt(i)).getLibelle());
		}				
		tousLesTypeDeTaxe = StaticManipHtml.genererListeDeroulante("typeDeTaxe", 1,"", contenu,false);
		return tousLesTypeDeTaxe;
	}




	public void setTousLesTypeDeTaxe(String tousLesTypeDeTaxe) {
		this.tousLesTypeDeTaxe = tousLesTypeDeTaxe;
	}




	public void setRequest(HttpServletRequest req) 
	{
		//paul 31/10/2017
		if (req.getParameter("numEmplacement") != null) { this.numEmplacement = req.getParameter("numEmplacement");  }
		//System.out.println("Setting BaffDivers..............");
		anneeExercice = GestionDate.getAnneeCourante();
		anneeExerciceSuivante = String.valueOf(Integer.valueOf(GestionDate.getAnneeCourante())+1);
		//System.out.println(anneeExercice);
		RequestBascule reqBascule = new RequestBascule();
		Bascule bascule = reqBascule.getBascule(anneeExercice);
		this.dateBascule = bascule.getDateBascule();
		if(dateBascule.length()!=0)
			basculeFaite =true;
		else
			basculeFaite =false;
		this.pourcentage = bascule.getPourcentage();
	}
	
	public String getNumEmplacement() {
		return numEmplacement;
	}




	public void setNumEmplacement(String numEmplacement) {
		this.numEmplacement = numEmplacement;
	}




	public String getListeSecteur()
	{				
		Vector contenu3=requestParametres.getTousLesSecteurs();		
		listeSecteur = StaticManipHtml.genererListeDeroulante("numSecteur", 1,"", contenu3,true);		 
		return listeSecteur;
	}
	
	public final String getVille()
	{
		String ville ="";
		RequestParametres requestParametres = new RequestParametres();
		ville  =   requestParametres.getVille();
		return ville;
	}
	
	public final String getRelance()
	{
		String relance ="";
		RequestParametres requestParametres = new RequestParametres();
		relance  =   requestParametres.getRelance();
		return relance;
	}
	
	public String getSiteWebVille() 
	{
		String siteWebVille ="";	
		RequestParametres requestParametres = new RequestParametres();
		siteWebVille  =   requestParametres.getSiteWebville();	
		return siteWebVille;
	}

	
	
	/**
	 * @return the anneeExercice
	 */
	public final String getAnneeExercice() {
		return anneeExercice;
	}

	/**
	 * @param anneeExercice the anneeExercice to set
	 */
	public final void setAnneeExercice(String anneeExercice) {
		this.anneeExercice = anneeExercice;
	}

	/**
	 * @return the basculeFaite
	 */
	public final boolean getBasculeFaite() 
	{
		
		return basculeFaite;
	}

	/**
	 * @param basculeFaite the basculeFaite to set
	 */
	public final void setBasculeFaite(boolean basculeFaite) {
		this.basculeFaite = basculeFaite;
	}

	/**
	 * @return the dateBascule
	 */
	public final String getDateBascule() {
		return dateBascule;
	}

	/**
	 * @param dateBascule the dateBascule to set
	 */
	public final void setDateBascule(String dateBascule) {
		this.dateBascule = dateBascule;
	}

	/**
	 * @return the pourcentage
	 */
	public final String getPourcentage() {
		return pourcentage;
	}

	/**
	 * @param pourcentage the pourcentage to set
	 */
	public final void setPourcentage(String pourcentage) {
		this.pourcentage = pourcentage;
	}

	/**
	 * @return the anneeExerciceSuivante
	 */
	public final String getAnneeExerciceSuivante() {
		return anneeExerciceSuivante;
	}

	/**
	 * @param anneeExerciceSuivante the anneeExerciceSuivante to set
	 */
	public final void setAnneeExerciceSuivante(String anneeExerciceSuivante) {
		this.anneeExerciceSuivante = anneeExerciceSuivante;
	}

	/**
	 * @return the dateAjourdhui
	 */
	public final String getDateAjourdhui() 
	{
		return GestionDate.getDateAujourdhuiString();
	}


}