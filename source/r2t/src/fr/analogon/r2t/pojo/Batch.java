package fr.analogon.r2t.pojo;

public class Batch
{
	String numeroBatchTraitement;
	String dateExecution;
	String typeEmplacement;
	String valide;
	String etape;
	String nombreFactures;
	String nombreRemboursements;
	String nomFichierEnvoyeAPleiade;
	String listeFactures;
	String nomDossier;
	String anneeExercice;
	String aEditerDesFactureAnnulee="";
	String aEditerRoleChangementAdresseRedevable="";
	String aEditerDesRemboursement="";
	
	/**
	 * @return the aEditerDesRemboursement
	 */
	public String getaEditerDesRemboursement() {
		return aEditerDesRemboursement;
	}
	/**
	 * @param aEditerDesRemboursement the aEditerDesRemboursement to set
	 */
	public void setaEditerDesRemboursement(String aEditerDesRemboursement) {
		this.aEditerDesRemboursement = aEditerDesRemboursement;
	}
	public String getAnneeExercice() {
		return anneeExercice;
	}
	public void setAnneeExercice(String anneeExercice) {
		this.anneeExercice = anneeExercice;
	}
	/**
	 * @return the listeFactures
	 */
	public final String getListeFactures() {
		return listeFactures;
	}
	/**
	 * @param listeFactures the listeFactures to set
	 */
	public final void setListeFactures(String listeFactures) {
		this.listeFactures = listeFactures;
	}
	/**
	 * @return the nomFichierEnvoyeAPleiade
	 */
	public String getNomFichierEnvoyeAPleiade() {
		return nomFichierEnvoyeAPleiade;
	}
	/**
	 * @param nomFichierEnvoyeAPleiade the nomFichierEnvoyeAPleiade to set
	 */
	public void setNomFichierEnvoyeAPleiade(String nomFichierEnvoyeAPleiade) {
		this.nomFichierEnvoyeAPleiade = nomFichierEnvoyeAPleiade;
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
	 * @return the dateExecution
	 */
	public String getDateExecution() {
		return dateExecution;
	}
	/**
	 * @param dateExecution the dateExecution to set
	 */
	public void setDateExecution(String dateExecution) {
		this.dateExecution = dateExecution;
	}
	/**
	 * @return the numeroBatchTraitement
	 */
	public String getNumeroBatchTraitement() {
		return numeroBatchTraitement;
	}
	/**
	 * @param numeroBatchTraitement the numeroBatchTraitement to set
	 */
	public void setNumeroBatchTraitement(String numeroBatchTraitement) {
		this.numeroBatchTraitement = numeroBatchTraitement;
	}
	/**
	 * @return the typeEmplacement
	 */
	public String getTypeEmplacement() {
		return typeEmplacement;
	}
	/**
	 * @param typeEmplacement the typeEmplacement to set
	 */
	public void setTypeEmplacement(String typeEmplacement) {
		this.typeEmplacement = typeEmplacement;
	}
	/**
	 * @return the valide
	 */
	public String getValide() {
		return valide;
	}
	/**
	 * @param valide the valide to set
	 */
	public void setValide(String valide) {
		this.valide = valide;
	}
	/**
	 * @return the nomDossier
	 */
	public final String getNomDossier() {
		return nomDossier;
	}
	/**
	 * @param nomDossier the nomDossier to set
	 */
	public final void setNomDossier(String nomDossier) {
		this.nomDossier = nomDossier;
	}
	public String getAEditerDesFactureAnnulee() {
		return aEditerDesFactureAnnulee;
	}
	public void setAEditerDesFactureAnnulee(String editerDesFactureAnnulee) {
		aEditerDesFactureAnnulee = editerDesFactureAnnulee;
	}
	/**
	 * @return the aEditerRoleChangementAdresseRedevable
	 */
	public final String getAEditerRoleChangementAdresseRedevable() {
		return aEditerRoleChangementAdresseRedevable;
	}
	/**
	 * @param editerRoleChangementAdresseRedevable the aEditerRoleChangementAdresseRedevable to set
	 */
	public final void setAEditerRoleChangementAdresseRedevable(
			String editerRoleChangementAdresseRedevable) {
		aEditerRoleChangementAdresseRedevable = editerRoleChangementAdresseRedevable;
	}
	public String getEtape() {
		return etape;
	}
	public void setEtape(String etape) {
		this.etape = etape;
	}
	/**
	 * @return the nombreRemboursements
	 */
	public String getNombreRemboursements() {
		return nombreRemboursements;
	}
	/**
	 * @param nombreRemboursements the nombreRemboursements to set
	 */
	public void setNombreRemboursements(String nombreRemboursements) {
		this.nombreRemboursements = nombreRemboursements;
	}

	
}
