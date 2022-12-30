package fr.analogon.r2t.pojo;

import fr.analogon.r2t.Utilitaire.StaticNombres;

/**
 * creer  le 07/2013 pour ANALOGON.
 */
public class Autorisation 
{
	private String idAutorisation;
	private String numeroTmp;
	private String numRedevable;
	private String etat="";
	private String refDossier="";
	private String dateCreation="";
	private String dateModification="";
	private String createur="";
	private String adresseEmplacement="";
	private String nombreEmplacement="";
	private String cp="";
	private String ville="";
	
	public Autorisation(){
		
	}

	

	public String getIdAutorisation() {
		return idAutorisation;
	}

	public void setIdAutorisation(String idAutorisation) {
		this.idAutorisation = idAutorisation;
	}

	public String getNumeroTmp() {
		return numeroTmp;
	}

	public void setNumeroTmp(String numeroTmp) {
		this.numeroTmp = numeroTmp;
	}

	public String getNumRedevable() {
		return numRedevable;
	}

	public void setNumRedevable(String numRedevable) {
		this.numRedevable = numRedevable;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getRefDossier() {
		return refDossier;
	}

	public void setRefDossier(String refDossier) {
		this.refDossier = refDossier;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getDateModification() {
		return dateModification;
	}

	public void setDateModification(String dateModification) {
		this.dateModification = dateModification;
	}

	public String getCreateur() {
		return createur;
	}

	public void setCreateur(String createur) {
		this.createur = createur;
	}

	public String getAdresseEmplacement() {
		return adresseEmplacement;
	}

	public void setAdresseEmplacement(String adresseEmplacement) {
		this.adresseEmplacement = adresseEmplacement;
	}

	public String getNombreEmplacement() {
		return nombreEmplacement;
	}

	public void setNombreEmplacement(String nombreEmplacement) {
		this.nombreEmplacement = nombreEmplacement;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}

		
}
