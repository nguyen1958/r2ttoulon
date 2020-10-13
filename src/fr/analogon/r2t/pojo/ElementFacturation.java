package fr.analogon.r2t.pojo;

import fr.analogon.r2t.Utilitaire.StaticNombres;
import fr.analogon.r2t.util.date.LaDate;


/**
 * creer par   le 23/03/2006 pour ANALOGON.
 */
public class ElementFacturation {

	private String id;

	private String idEmplacement;

	private String codeBareme;

	private String anExercice;

	private double nombreArticle;

	private int coefPrixUnitaire = 1;

	

	private String codeGenerationEf = "aucun";
	
	private LaDate dateCreation = new LaDate();
	
	private String dateDebutAutorisation  ;
	
	private String dateDernierControle  ;
	
	private String dateProchainControle  ;
	
	
	private String cheminPhotos = "aucun";
	
	
	private String test = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdEmplacement() {
		return idEmplacement;
	}

	public void setIdEmplacement(String idEmplacement) {
		this.idEmplacement = idEmplacement;
	}

	public String getCodeBareme() {
		return codeBareme;
	}

	public void setCodeBareme(String codeBareme) {
		this.codeBareme = codeBareme;
	}

	public String getAnExercice() {
		return anExercice;
	}

	public void setAnExercice(String anExercice) {
		this.anExercice = anExercice;
	}

	public double getNombreArticle() {
		return nombreArticle;
	}

	public void setNombreArticle(double nombreArticle) {
		this.nombreArticle = StaticNombres.arronDD2(nombreArticle);
	}

	public int getCoefPrixUnitaire() {
		return coefPrixUnitaire;
	}

	public void setCoefPrixUnitaire(int coefPrixUnitaire) {
		this.coefPrixUnitaire = coefPrixUnitaire;
	}

	public LaDate getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LaDate dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getCodeGenerationEf() {
		return codeGenerationEf;
	}

	public void setCodeGenerationEf(String codeGenerationEf) {
		this.codeGenerationEf = codeGenerationEf;
	}

	public String getCheminPhotos() {
		return cheminPhotos;
	}

	public void setCheminPhotos(String cheminPhotos) {
		this.cheminPhotos = cheminPhotos;
	}

	public String getDateDebutAutorisation() {
		return dateDebutAutorisation;
	}

	public void setDateDebutAutorisation(String dateDebutAutorisation) {
		this.dateDebutAutorisation = dateDebutAutorisation;
	}

	public String getdateDernierControle() {
		return dateDernierControle;
	}

	public void setdateDernierControle(String dateDernierControle) {
		this.dateDernierControle = dateDernierControle;
	}

	

	
	public String gettest() {
		return test;
	}

	public void settest(String test) {
		this.test = test;
	}

	public String getdateProchainControle() {
		return dateProchainControle;
	}

	public void setdateProchainControle(String dateProchainControle) {
		this.dateProchainControle = dateProchainControle;
	}

}
