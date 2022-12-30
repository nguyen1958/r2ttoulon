package fr.analogon.r2t.pojo;

public class Remboursement 
{
	private String   id_alerte="";
	private String   idRemboursement ;
	private String   numeroFacture; 
	private String   montantTotal ;
	private String   idBatchTraitement ; 
	private String   typeTaxe ; 
	private String   idClient ;
	private String   anneeEx ;
	/**
	 * @return the id_alerte
	 */
	public String getId_alerte() {
		return id_alerte;
	}
	/**
	 * @param idAlerte the id_alerte to set
	 */
	public void setId_alerte(String idAlerte) {
		id_alerte = idAlerte;
	}
	/**
	 * @return the idRemboursement
	 */
	public String getIdRemboursement() {
		return idRemboursement;
	}
	/**
	 * @param idRemboursement the idRemboursement to set
	 */
	public void setIdRemboursement(String idRemboursement) {
		this.idRemboursement = idRemboursement;
	}
	/**
	 * @return the numeroFacture
	 */
	public String getNumeroFacture() {
		return numeroFacture;
	}
	/**
	 * @param numeroFacture the numeroFacture to set
	 */
	public void setNumeroFacture(String numeroFacture) {
		this.numeroFacture = numeroFacture;
	}
	/**
	 * @return the montantTotal
	 */
	public String getMontantTotal() {
		return montantTotal;
	}
	/**
	 * @param montantTotal the montantTotal to set
	 */
	public void setMontantTotal(String montantTotal) {
		this.montantTotal = montantTotal;
	}
	/**
	 * @return the idBatchTraitement
	 */
	public String getIdBatchTraitement() {
		return idBatchTraitement;
	}
	/**
	 * @param idBatchTraitement the idBatchTraitement to set
	 */
	public void setIdBatchTraitement(String idBatchTraitement) {
		this.idBatchTraitement = idBatchTraitement;
	}
	/**
	 * @return the typeTaxe
	 */
	public String getTypeTaxe() {
		return typeTaxe;
	}
	/**
	 * @param typeTaxe the typeTaxe to set
	 */
	public void setTypeTaxe(String typeTaxe) {
		this.typeTaxe = typeTaxe;
	}
	/**
	 * @return the idClient
	 */
	public String getIdClient() {
		return idClient;
	}
	/**
	 * @param idClient the idClient to set
	 */
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	/**
	 * @return the anneeEx
	 */
	public String getAnneeEx() {
		return anneeEx;
	}
	/**
	 * @param anneeEx the anneeEx to set
	 */
	public void setAnneeEx(String anneeEx) {
		this.anneeEx = anneeEx;
	}
	
	

}
