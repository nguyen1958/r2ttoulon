package fr.analogon.r2t.pojo;

import java.util.Date;



/**
 * creer  le 07/2017 pour ANALOGON.
 */
public class Commentaire 
{
	private String idCommentaire;
	private String numRedevable;
	private String auteur;
	private String commentaire;
	private Date date;
	private String nomAuteur;
	private String resultatvisite;

	
	public Commentaire(){
		
	}


	public String getIdCommentaire() {
		return idCommentaire;
	}


	public void setIdCommentaire(String idCommentaire) {
		this.idCommentaire = idCommentaire;
	}


	public String getNumRedevable() {
		return numRedevable;
	}


	public void setNumRedevable(String numRedevable) {
		this.numRedevable = numRedevable;
	}


	public String getAuteur() {
		return auteur;
	}


	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}


	public String getCommentaire() {
		return commentaire;
	}


	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getNomAuteur() {
		return nomAuteur;
	}


	public void setNomAuteur(String nomAuteur) {
		this.nomAuteur = nomAuteur;
	}


	public String getResultatvisite() {
		return resultatvisite;
	}


	public void setResultatvisite(String resultatvisite) {
		this.resultatvisite = resultatvisite;
	}

	
		
}
