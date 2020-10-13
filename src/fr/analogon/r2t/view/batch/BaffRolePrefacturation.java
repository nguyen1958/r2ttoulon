package fr.analogon.r2t.view.batch;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.GroupeTaxe;
import fr.analogon.r2t.pojo.Imputation;
import fr.analogon.r2t.request.RequestGroupeTaxe;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.util.web.StaticManipHtml;


/**
 * Bean d'affichage du batch Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0
 */
public class BaffRolePrefacturation extends fr.analogon.r2t.main.RacineBean {
	
	private int numeroBatch;
	private String tousTypeDeTaxe;
	private String nombreFactures;	
	private String anExercice = "";	
	private String typeDeTaxe="";
	private String dateDeCreation="";
	private String nomFichierToPleaide="";
	private String nomFichierFromPleaide="";
	private HttpServletRequest request;
	//nouveau
	private String tousGroupeDeTaxe;

	
	
	public BaffRolePrefacturation() 
	{
		try {
			//jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	

	public void setRequest(HttpServletRequest req)
	{	
		
		this.request = req;		
		////System.out.println("*******************************************");
		////System.out.println("*******************************************");
		if (request.getParameter("anExercice") != null) 
		{
			this.anExercice = request.getParameter("anExercice");
		}
		else
		{		
			////System.out.println("anExercice="+anExercice);
		}
		if (request.getParameter("typeDeTaxe") != null)
		{
			this.typeDeTaxe = request.getParameter("typeDeTaxe");
		}
		
			
	}


	/**
	 * @return the anExercice
	 */
	public String getAnExercice() 
	{
		this.anExercice = GestionDate.getDateAujourdhuiString();
		return anExercice;
	}


	/**
	 * @param anExercice the anExercice to set
	 */
	public void setAnExercice(String anExercice) {
		this.anExercice = anExercice;
	}


	/**
	 * @return the nombreFactures
	 */
	public String getNombreFactures() {
		return nombreFactures;
	}


	/**
	 * @param nombreFactures the nombreFactures to set
	 */
	public void setNombreFactures(String nombreFactures) {
		this.nombreFactures = nombreFactures;
	}


	/**
	 * @return the numeroBatch
	 */
	public int getNumeroBatch() {
		return numeroBatch;
	}


	/**
	 * @param numeroBatch the numeroBatch to set
	 */
	public void setNumeroBatch(int numeroBatch) {
		numeroBatch = numeroBatch;
	}


	/**
	 * @return the typeDeTaxe
	 */
	public String getTypeDeTaxe() {
		return typeDeTaxe;
	}


	/**
	 * @param typeDeTaxe the typeDeTaxe to set
	 */
	public void setTypeDeTaxe(String typeDeTaxe) {
		this.typeDeTaxe = typeDeTaxe;
	}


	/**
	 * @return the dateDeCreation
	 */
	public String getDateDeCreation() {
		return dateDeCreation;
	}


	/**
	 * @param dateDeCreation the dateDeCreation to set
	 */
	public void setDateDeCreation(String dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}


	/**
	 * @return the nomFichierFromPleaide
	 */
	public String getNomFichierFromPleaide() {
		return nomFichierFromPleaide;
	}


	/**
	 * @param nomFichierFromPleaide the nomFichierFromPleaide to set
	 */
	public void setNomFichierFromPleaide(String nomFichierFromPleaide) {
		this.nomFichierFromPleaide = nomFichierFromPleaide;
	}


	/**
	 * @return the nomFichierToPleaide
	 */
	public String getNomFichierToPleaide() {
		return nomFichierToPleaide;
	}


	/**
	 * @param nomFichierToPleaide the nomFichierToPleaide to set
	 */
	public void setNomFichierToPleaide(String nomFichierToPleaide) {
		this.nomFichierToPleaide = nomFichierToPleaide;
	}


	/**
	 * @return the tousTypeDeTaxe
	 */
	public String getTousTypeDeTaxe() 
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
		res = StaticManipHtml.genererListeDeroulanteTousLesTypesDeTaxeFacturation
				("typeDeTaxe", 1,"", contenu,false);
		//res= contenu.toString();
		return res;	
	}


	/**
	 * @param tousTypeDeTaxe the tousTypeDeTaxe to set
	 */
	public void setTousTypeDeTaxe(String tousTypeDeTaxe) {
		this.tousTypeDeTaxe = tousTypeDeTaxe;
	}


	public String getTousGroupeDeTaxe() {
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();	
		RequestGroupeTaxe reqGroupeTaxe = new RequestGroupeTaxe();
		Vector listDesGroupes = reqGroupeTaxe.getListeGroupeTaxe();
		for (int i = 0; i < listDesGroupes.size(); i++) 
		{
			contenu.addElement(((GroupeTaxe)listDesGroupes.elementAt(i)).getLibelle());
		}				
		res = StaticManipHtml.genererListeDeroulanteTousLesGroupesDeTaxeFacturation
				("groupeDeTaxe", 1,"", contenu,false);
		//res= contenu.toString();
		return res;	
	}


	public void setTousGroupeDeTaxe(String tousGroupeDeTaxe) {
		this.tousGroupeDeTaxe = tousGroupeDeTaxe;
	}
	
	//Paramètres définis dans fichier .properties
	public String getAnneeFacturationTLPE() {
		String[] res=fichierDeConfiguration.getAnneeFacturationTLPE().split(";");
		String contenu="<select name=\"anneeFacturationTLPE\" id=\"anneeFacturationTLPE\" size=\"1\">";
		for (String annee : res){
			contenu+=" <option value=\""+annee+"\">"+annee+"</option>";
		}
		contenu+="</select>";
		return contenu;	
	}

	
}


