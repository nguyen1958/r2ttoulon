package fr.analogon.r2t.pojo;

public class Communique 
{
	String idOuvrage ;
	String adresseEmplacement ;
	String dateDeControle ;
	String idRedevable ;
	String idEmplacement ;
	String type ="";
	String quartier="";
	String numeroEF="";
	String codeSecteur="";
	
	public String getAdresseEmplacement() {
		return adresseEmplacement;
	}
	public void setAdresseEmplacement(String adresseEmplacement) {
		this.adresseEmplacement = adresseEmplacement;
	}
	public String getDateDeControle() {
		return dateDeControle;
	}
	public void setDateDeControle(String dateDeControle) {
		this.dateDeControle = dateDeControle;
	}
	public String getIdEmplacement() {
		return idEmplacement;
	}
	public void setIdEmplacement(String idEmplacement) {
		this.idEmplacement = idEmplacement;
	}
	public String getIdOuvrage() {
		return idOuvrage;
	}
	public void setIdOuvrage(String idOuvrage) {
		this.idOuvrage = idOuvrage;
	}
	public String getIdRedevable() {
		return idRedevable;
	}
	public void setIdRedevable(String idRedevable) {
		this.idRedevable = idRedevable;
	}
	/**
	 * @return the quartier
	 */
	public final String getQuartier() {
		return quartier;
	}
	/**
	 * @param quartier the quartier to set
	 */
	public final void setQuartier(String quartier) {
		this.quartier = quartier;
	}
	/**
	 * @return the type
	 */
	public final String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public final void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the codeSecteur
	 */
	public final String getCodeSecteur() {
		return codeSecteur;
	}
	/**
	 * @param codeSecteur the codeSecteur to set
	 */
	public final void setCodeSecteur(String codeSecteur) {
		this.codeSecteur = codeSecteur;
	}
	/**
	 * @return the numeroEF
	 */
	public final String getNumeroEF() {
		return numeroEF;
	}
	/**
	 * @param numeroEF the numeroEF to set
	 */
	public final void setNumeroEF(String numeroEF) {
		this.numeroEF = numeroEF;
	}
	

}
