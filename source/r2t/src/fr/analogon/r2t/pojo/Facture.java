/**
 * 
 */
package fr.analogon.r2t.pojo;

import java.util.Vector;

/**
 * @author sofien
 *
 */
public class Facture 
{
	int numeroFacture;	
	String numeroTitre="";
	String nomDossier="";	
	String typeTaxe="";
	Vector listeOuvrage= new Vector();	
	String dateCreationFacture="";	
	String etat="";	
	int idClient;	
	String nomPrenomClient="";	
	String envoyerALaTresorie="";
	public String getEnvoyerALaTresorie() {
		return envoyerALaTresorie;
	}
	public void setEnvoyerALaTresorie(String envoyerALaTresorie) {
		this.envoyerALaTresorie = envoyerALaTresorie;
	}
	int idFacture;
	String idReclamation="";	
	String idBatch="";
	String etatReclamation="";
	String montantTotalFacture="";	
	String soldeFacture="";	
	String motifAnnulationFacture="";	
	String anneeTitre="";
	String dateAnnulation="";	
	String  peutetreannule="";	
	
	
	public String getPeutetreannule() {
		return peutetreannule;
	}
	public void setPeutetreannule(String peutetreannule) {
		this.peutetreannule = peutetreannule;
	}
	/**
	 * @return the dateAnnulation
	 */
	public final String getDateAnnulation() {
		return dateAnnulation;
	}
	/**
	 * @param dateAnnulation the dateAnnulation to set
	 */
	public final void setDateAnnulation(String dateAnnulation) {
		this.dateAnnulation = dateAnnulation;
	}
	/**
	 * @return the montantTotalFacture
	 */
	public String getMontantTotalFacture() {
		return montantTotalFacture;
	}
	/**
	 * @param montantTotalFacture the montantTotalFacture to set
	 */
	public void setMontantTotalFacture(String montantTotalFacture) {
		this.montantTotalFacture = montantTotalFacture;
	}
	/**
	 * @return the soldeFacture
	 */
	public String getSoldeFacture() {
		return soldeFacture;
	}
	/**
	 * @param soldeFacture the soldeFacture to set
	 */
	public void setSoldeFacture(String soldeFacture) {
		this.soldeFacture = soldeFacture;
	}
	public String getNomDossier() {
		return nomDossier;
	}
	public void setNomDossier(String nomDossier) {
		this.nomDossier = nomDossier;
	}
	public int getNumeroFacture() {
		return numeroFacture;
	}
	public void setNumeroFacture(int numeroFacture) {
		this.numeroFacture = numeroFacture;
	}
	public String getDateCreationFacture() {
		return dateCreationFacture;
	}
	public void setDateCreationFacture(String dateCreationFacture) {
		this.dateCreationFacture = dateCreationFacture;
	}
	public String getTypeTaxe() {
		return typeTaxe;
	}
	public void setTypeTaxe(String typeTaxe) {
		this.typeTaxe = typeTaxe;
	}
	/**
	 * @return the idClient
	 */
	public int getIdClient() {
		return idClient;
	}
	/**
	 * @param idClient the idClient to set
	 */
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	/**
	 * @return the etat
	 */
	public String getEtat() {
		return etat;
	}
	/**
	 * @param etat the etat to set
	 */
	public void setEtat(String etat) {
		this.etat = etat;
	}
	/**
	 * @return the idFacture
	 */
	public int getIdFacture() {
		return idFacture;
	}
	/**
	 * @param idFacture the idFacture to set
	 */
	public void setIdFacture(int idFacture) {
		this.idFacture = idFacture;
	}
	/**
	 * @return the listeOuvrage
	 */
	public Vector getListeOuvrage() {
		return listeOuvrage;
	}
	/**
	 * @param listeOuvrage the listeOuvrage to set
	 */
	public void setListeOuvrage(Vector listeOuvrage) {
		this.listeOuvrage = listeOuvrage;
	}
	/**
	 * @return the nomPrenomClient
	 */
	public String getNomPrenomClient() {
		return nomPrenomClient;
	}
	/**
	 * @param nomPrenomClient the nomPrenomClient to set
	 */
	public void setNomPrenomClient(String nomPrenomClient) {
		this.nomPrenomClient = nomPrenomClient;
	}
	/**
	 * @return the idReclamation
	 */
	public String getIdReclamation() {
		return idReclamation;
	}
	/**
	 * @param idReclamation the idReclamation to set
	 */
	public void setIdReclamation(String idReclamation) {
		this.idReclamation = idReclamation;
	}
	/**
	 * @return the numeroTitre
	 */
	public String getNumeroTitre() {
		return numeroTitre;
	}
	/**
	 * @param numeroTitre the numeroTitre to set
	 */
	public void setNumeroTitre(String numeroTitre) {
		this.numeroTitre = numeroTitre;
	}
	/**
	 * @return the etatReclamation
	 */
	public String getEtatReclamation() {
		return etatReclamation;
	}
	/**
	 * @param etatReclamation the etatReclamation to set
	 */
	public void setEtatReclamation(String etatReclamation) {
		this.etatReclamation = etatReclamation;
	}
	public String getMotifAnnulationFacture() {
		return motifAnnulationFacture;
	}
	public void setMotifAnnulationFacture(String motifAnnulationFacture) {
		this.motifAnnulationFacture = motifAnnulationFacture;
	}
	/**
	 * @return the anneeTitre
	 */
	public final String getAnneeTitre() {
		return anneeTitre;
	}
	/**
	 * @param anneeTitre the anneeTitre to set
	 */
	public final void setAnneeTitre(String anneeTitre) {
		this.anneeTitre = anneeTitre;
	}
	/**
	 * @return the idBatch
	 */
	public final String getIdBatch() {
		return idBatch;
	}
	/**
	 * @param idBatch the idBatch to set
	 */
	public final void setIdBatch(String idBatch) {
		this.idBatch = idBatch;
	}
	

}
