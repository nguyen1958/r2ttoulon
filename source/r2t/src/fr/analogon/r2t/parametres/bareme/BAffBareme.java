package fr.analogon.r2t.parametres.bareme;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Bareme;
import fr.analogon.r2t.pojo.Imputation;
import fr.analogon.r2t.request.DataFromBD;
import fr.analogon.r2t.request.RequestBareme;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de bareme  . Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0
 */
public class BAffBareme extends fr.analogon.r2t.main.RacineBean
{
	
	private HttpServletRequest request;	
	private String choix= "";	
	private boolean creation=true;
	private String codeBareme= "";
	private String anneeExercice= "";
	private String libelle= "";
	private String typeTaxe= "";
	private String typeTaxeSeulement= "";
	private String prixUnitaire= "";
	private String uniteDeTravail= "";
	private String uniteDeTemps= "";
	private String utiliserProrata= "NON";
	private String utiliserPeriodicite= "NON";
	private String typeArrondi= "";
	private String prixPeriode1= "";
	private String prixPeriode2= "";
	private String prixPeriode3= "";
	private String prixPeriode4= "";
	private String prixPeriode5= "";
	//private String listeBareme= "";
	private String listeTypeDeTaxeSansCode= "";
	private String listeDesTranchesDesPrix="";
	private String peutEtreSupprimer="";
	private String coefficientNumerique="";
	
	
	private String sectionBareme="";
	private String imputationBareme="";
	
	
	
	
	
	public String getSectionBareme() {
		return sectionBareme;
	}

	public void setSectionBareme(String sectionBareme) {
		this.sectionBareme = sectionBareme;
	}

	public String getImputationBareme() {
		return imputationBareme;
	}

	public void setImputationBareme(String imputationBareme) {
		this.imputationBareme = imputationBareme;
	}

	public String getCoefficientNumerique() {
		return coefficientNumerique;
	}

	public void setCoefficientNumerique(String coefficientNumerique) {
		this.coefficientNumerique = coefficientNumerique;
	}

	/**
	 * @param prixPeriode5 the prixPeriode5 to set
	 */
	public final void setPrixPeriode5(String prixPeriode5) {
		this.prixPeriode5 = prixPeriode5;
	}

	//Setting Request 
	public void setRequest(HttpServletRequest req)
	{	
			
		this.request = req;		
		if (request.getParameter("choix") != null && ! request.getParameter("choix").equalsIgnoreCase(" ") )
		{
			choix=request.getParameter("choix");			
			if(choix.equalsIgnoreCase("ajouter"))
			{
					this.setCreation(true);					
					anneeExercice = GestionDate.getAnneeCourante();
			}
			else if(choix.equalsIgnoreCase("modifier") )
			{
					this.setCreation(false);
					codeBareme = request.getParameter("codeBareme");
					anneeExercice = request.getParameter("anneeExercice");
					RequestBareme reqBareme= new RequestBareme();
					peutEtreSupprimer= reqBareme.peutEtreSuuprimer(codeBareme, anneeExercice);
					Bareme bareme = reqBareme.getBareme(codeBareme,anneeExercice);					
					codeBareme=bareme.getCode();
					anneeExercice=bareme.getAnExercice();										
					libelle=bareme.getLibelle();
					typeTaxe=bareme.getIdImputation()+"---"+bareme.getTypeTaxe();
					typeTaxeSeulement = bareme.getTypeTaxe();
					prixUnitaire=""+bareme.getPrixUnit();					
					uniteDeTravail=bareme.getUniteDeTravail();
					uniteDeTemps=bareme.getDureeUnitaire();					
					utiliserProrata= ""+bareme.getProrata();					
					utiliserPeriodicite= ""+bareme.getPeriodicite();					
					typeArrondi=bareme.getTypeArrondi();		
					
					prixPeriode1=""+bareme.getPeriode1();
					prixPeriode2=""+bareme.getPeriode2();
					prixPeriode3=""+bareme.getPeriode3();
					prixPeriode4=""+bareme.getPeriode4();
					prixPeriode5=""+bareme.getPeriode5();	
					
					imputationBareme = bareme.getImputationBareme();
					sectionBareme = bareme.getSectionBareme();
					
					listeDesTranchesDesPrix= bareme.getListeDesTranchesDesPrix();
					coefficientNumerique = bareme.getCoefficientNumerique();
					
					
			}
			else if (request.getParameter("choix").equalsIgnoreCase("chercher"))
			{
				codeBareme =request.getParameter("codeBareme");
				anneeExercice =request.getParameter("anneeExercice");
				typeTaxe =request.getParameter("typeTaxe");
				libelle =request.getParameter("libelle");							
			}
		}
	
	}

	/**
	 * @return the anneeExercice
	 */
	public final String getAnneeExercice()
	{	
		return anneeExercice;
	}

	/**
	 * @param anneeExercice the anneeExercice to set
	 */
	public final void setAnneeExercice(String anneeExercice) {
		this.anneeExercice = anneeExercice;
	}

	/**
	 * @return the choix
	 */
	public final String getChoix() {
		return choix;
	}

	/**
	 * @param choix the choix to set
	 */
	public final void setChoix(String choix) {
		this.choix = choix;
	}

	/**
	 * @return the codeBareme
	 */
	public final String getCodeBareme() {
		return codeBareme;
	}

	/**
	 * @param codeBareme the codeBareme to set
	 */
	public final void setCodeBareme(String codeBareme) {
		this.codeBareme = codeBareme;
	}

	/**
	 * @return the creation
	 */
	public final boolean getCreation() {
		return creation;
	}

	/**
	 * @param creation the creation to set
	 */
	public final void setCreation(boolean creation) {
		this.creation = creation;
	}

	/**
	 * @return the libelle
	 */
	public final String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public final void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the prixPeriode1
	 */
	public final String getPrixPeriode1() {
		return prixPeriode1;
	}

	/**
	 * @param prixPeriode1 the prixPeriode1 to set
	 */
	public final void setPrixPeriode1(String prixPeriode1) {
		this.prixPeriode1 = prixPeriode1;
	}

	/**
	 * @return the prixPeriode2
	 */
	public final String getPrixPeriode2() {
		return prixPeriode2;
	}

	/**
	 * @param prixPeriode2 the prixPeriode2 to set
	 */
	public final void setPrixPeriode2(String prixPeriode2) {
		this.prixPeriode2 = prixPeriode2;
	}

	/**
	 * @return the prixPeriode3
	 */
	public final String getPrixPeriode3() {
		return prixPeriode3;
	}

	/**
	 * @param prixPeriode3 the prixPeriode3 to set
	 */
	public final void setPrixPeriode3(String prixPeriode3) {
		this.prixPeriode3 = prixPeriode3;
	}

	/**
	 * @return the prixPeriode4
	 */
	public final String getPrixPeriode4() {
		return prixPeriode4;
	}

	/**
	 * @param prixPeriode4 the prixPeriode4 to set
	 */
	public final void setPrixPeriode4(String prixPeriode4) {
		this.prixPeriode4 = prixPeriode4;
	}

	/**
	 * @return the prixPériode5
	 */
	public final String getPrixPeriode5() {
		return prixPeriode5;
	}

	

	/**
	 * @return the prixUnitaire
	 */
	public final String getPrixUnitaire() {
		return prixUnitaire;
	}

	/**
	 * @param prixUnitaire the prixUnitaire to set
	 */
	public final void setPrixUnitaire(String prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	/**
	 * @return the typeArrondi
	 */
	public final String getTypeArrondi() 
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();	
		DataFromBD df= new DataFromBD();
		Vector listDesTypeArrondis= df.getTousLesTypeArrondi();
		for (int i = 0; i < listDesTypeArrondis.size(); i++) 
		{
			contenu.addElement(listDesTypeArrondis.elementAt(i));
		}				
		res = StaticManipHtml.genererListeDeroulante("typeArrondi", 1,typeArrondi, contenu,false);	
		return res;	
	
	}

	/**
	 * @param typeArrondi the typeArrondi to set
	 */
	public final void setTypeArrondi(String typeArrondi) {
		this.typeArrondi = typeArrondi;
	}

	/**
	 * @return the typeTaxe
	 */
	public final String getTypeTaxe() 
	{	
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();	
		RequestImputation reqImputation = new RequestImputation();		
		Vector listDesTypeTaxe = reqImputation.getListeTypeDeTaxe(anneeExercice,"");
		for (int i = 0; i < listDesTypeTaxe.size(); i++) 
		{
			contenu.addElement(((Imputation)listDesTypeTaxe.elementAt(i)).getNumtypeTaxe() +"---"+
					((Imputation)listDesTypeTaxe.elementAt(i)).getLibelle());
		}				
		res = StaticManipHtml.genererListeDeroulanteChangementTypeTaxe("typeTaxe", 1,typeTaxe, contenu,false);
		//res= contenu.toString();
		return res;	
		
	}

	/**
	 * @param typeTaxe the typeTaxe to set
	 */
	public final void setTypeTaxe(String typeTaxe) {
		this.typeTaxe = typeTaxe;
	}

	/**
	 * @return the uniteDeTemps
	 */
	public final String getUniteDeTemps()
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();	
		DataFromBD df= new DataFromBD();
		Vector listDesUniteDeTemps= df.getTousLesUniteDeTemps();
		for (int i = 0; i < listDesUniteDeTemps.size(); i++) 
		{
			contenu.addElement(listDesUniteDeTemps.elementAt(i));
		}
			
		res = StaticManipHtml.genererListeDeroulante("uniteDeTemps",1,uniteDeTemps, contenu,false);		
		return res;	
	}

	/**
	 * @param uniteDeTemps the uniteDeTemps to set
	 */
	public final void setUniteDeTemps(String uniteDeTemps) {
		this.uniteDeTemps = uniteDeTemps;
	}

	/**
	 * @return the uniteDeTravail
	 */
	public final String getUniteDeTravail()
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();	
		RequestImputation reqImputation = new RequestImputation();
		Vector listDesUniteTravail= reqImputation.getTousLesUniteTravail();
		for (int i = 0; i < listDesUniteTravail.size(); i++) 
		{
			contenu.addElement(listDesUniteTravail.elementAt(i));
		}				
		res = StaticManipHtml.genererListeDeroulante("uniteDeTravail", 1,uniteDeTravail, contenu,false);
	
		return res;	
		
	}

	/**
	 * @param uniteDeTravail the uniteDeTravail to set
	 */
	public final void setUniteDeTravail(String uniteDeTravail) {
		this.uniteDeTravail = uniteDeTravail;
	}

	/**
	 * @return the utiliserPeriodicite
	 */
	public final String getUtiliserPeriodicite() 
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();		
		contenu.addElement("OUI");
		contenu.addElement("NON");
		res = StaticManipHtml.genererListeDeroulantePeriodicite("utiliserPeriodicite", 1,utiliserPeriodicite, contenu,false);
		return res;		
	}

	/**
	 * @param utiliserPeriodicite the utiliserPeriodicite to set
	 */
	public final void setUtiliserPeriodicite(String utiliserPeriodicite) {
		this.utiliserPeriodicite = utiliserPeriodicite;
	}

	/**
	 * @return the utiliserProrata
	 */
	public final String getUtiliserProrata() 
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();		
		contenu.addElement("OUI");
		contenu.addElement("NON");
		res = StaticManipHtml.genererListeDeroulante("utiliserProrata", 1,utiliserProrata, contenu,false);
		return res;
		
	}

	/**
	 * @param utiliserProrata the utiliserProrata to set
	 */
	public final void setUtiliserProrata(String utiliserProrata) {
		this.utiliserProrata = utiliserProrata;
	}

	/**
	 * @return the request
	 */
	public final HttpServletRequest getRequest() {
		return request;
	}
	
	
	/**
	 * @return the listeBareme
	 */
	public final String getListeBareme() 
	{
		String res="";
		StaticManipHtml mHtml = new StaticManipHtml();
		RequestBareme req = new RequestBareme();
		
		try
		{
			Vector contenu =req.getListeBareme(codeBareme,anneeExercice,libelle,typeTaxe);	
			res=mHtml.genererTableauListeBareme(contenu);		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(
		return res;
		
	}



	/**
	 * @return the listeTypeDeTaxeSansCode
	 */
	public final String getListeTypeDeTaxeSansCode()
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();	
		RequestImputation reqImputation = new RequestImputation();
		Vector listDesTypeTaxe = reqImputation.getLesDifferentTypesTaxe();
		for (int i = 0; i < listDesTypeTaxe.size(); i++) 
		{
			contenu.addElement(((Imputation)listDesTypeTaxe.elementAt(i)).getLibelle());
		}				
		res = StaticManipHtml.genererListeDeroulante("typeTaxe", 1,typeTaxe, contenu,true);
		//res= contenu.toString();
		return res;	
		
	}

	/**
	 * @param listeTypeDeTaxeSansCode the listeTypeDeTaxeSansCode to set
	 */
	public final void setListeTypeDeTaxeSansCode(String listeTypeDeTaxeSansCode) {
		this.listeTypeDeTaxeSansCode = listeTypeDeTaxeSansCode;
	}

	public String getPeutEtreSupprimer() {
		return peutEtreSupprimer;
	}

	public void setPeutEtreSupprimer(String peutEtreSupprimer) {
		this.peutEtreSupprimer = peutEtreSupprimer;
	}

	/**
	 * @return the typeTaxeSeulement
	 */
	public final String getTypeTaxeSeulement() {
		return typeTaxeSeulement;
	}

	/**
	 * @param typeTaxeSeulement the typeTaxeSeulement to set
	 */
	public final void setTypeTaxeSeulement(String typeTaxeSeulement) {
		this.typeTaxeSeulement = typeTaxeSeulement;
	}
	
	public boolean verfierAcces(String listeLibelleDesTypesDeTaxeAutorise) 
	{
		boolean res=false;	
		if(listeLibelleDesTypesDeTaxeAutorise.indexOf(typeTaxeSeulement) != -1)
			res= true; 

		return res;
	}

	public String getListeDesTranchesDesPrix() {
		return listeDesTranchesDesPrix;
	}

	public void setListeDesTranchesDesPrix(String listeDesTranchesDesPrix) {
		this.listeDesTranchesDesPrix = listeDesTranchesDesPrix;
	}


}