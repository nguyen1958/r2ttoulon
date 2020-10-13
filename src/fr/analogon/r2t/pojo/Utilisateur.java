
package fr.analogon.r2t.pojo;


public class Utilisateur {

	private String id;
	private String login;
	private String password;
	private String valide;
	private String typeUtilisateur;
	private String listeDesTypesDeTaxeAutorise="";
	private String listeLibelleDesTypesDeTaxeAutorise="";
	private String nom;
	private String prenom;
	private String remarque;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getRemarque() {
		return remarque;
	}
	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}


	public String getTypeUtilisateur() {
		return typeUtilisateur;
	}
	public void setTypeUtilisateur(String typeUtilisateur) {
		this.typeUtilisateur = typeUtilisateur;
	}
	public String getValide() {
		return valide;
	}
	public void setValide(String valide) {
		this.valide = valide;
	}
	public String getListeDesTypesDeTaxeAutorise() {
		return listeDesTypesDeTaxeAutorise;
	}
	public void setListeDesTypesDeTaxeAutorise(String listeDesTypesDeTaxeAutorise) {
		this.listeDesTypesDeTaxeAutorise = listeDesTypesDeTaxeAutorise;
	}
	public String getListeLibelleDesTypesDeTaxeAutorise() {
		return listeLibelleDesTypesDeTaxeAutorise;
	}
	public void setListeLibelleDesTypesDeTaxeAutorise(
			String listeLibelleDesTypesDeTaxeAutorise) {
		this.listeLibelleDesTypesDeTaxeAutorise = listeLibelleDesTypesDeTaxeAutorise;
	}
}



