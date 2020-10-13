package fr.analogon.r2t.pojo;

public class BatchFilien
{
	String  numeroBatchTraitement;
	String  idBatchFilien;
	String  annee;
	String  type;
	String	periode;
	String etatBatchFilien ="";
	String listeNumeroFacture ="";
	
	public String getListeNumeroFacture() {
		return listeNumeroFacture;
	}
	public void setListeNumeroFacture(String listeNumeroFacture) {
		this.listeNumeroFacture = listeNumeroFacture;
	}
	public String getEtatBatchFilien() {
		return etatBatchFilien;
	}
	public void setEtatBatchFilien(String etatBatchFilien) {
		this.etatBatchFilien = etatBatchFilien;
	}
	String  dateLancement;
	int nombreFactureImpaye;
	
	
	
	public int getNombreFactureImpaye() {
		return nombreFactureImpaye;
	}
	public void setNombreFactureImpaye(int nombreFactureImpaye) {
		this.nombreFactureImpaye = nombreFactureImpaye;
	}
	public String getNumeroBatchTraitement() {
		return numeroBatchTraitement;
	}
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	public void setNumeroBatchTraitement(String numeroBatchTraitement) {
		this.numeroBatchTraitement = numeroBatchTraitement;
	}
	public String getIdBatchFilien() {
		return idBatchFilien;
	}
	public void setIdBatchFilien(String idBatchFilien) {
		this.idBatchFilien = idBatchFilien;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPeriode() {
		return periode;
	}
	public void setPeriode(String periode) {
		this.periode = periode;
	}
	public String getDateLancement() {
		return dateLancement;
	}
	public void setDateLancement(String dateLancement) {
		this.dateLancement = dateLancement;
	}
	
	
}
