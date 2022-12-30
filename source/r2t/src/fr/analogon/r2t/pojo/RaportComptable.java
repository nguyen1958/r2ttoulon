package fr.analogon.r2t.pojo;

/**
 * Une rue. Sofien CHARFI 12 Avril 2006
 */
public class RaportComptable 
{
	
	private String idBatchRapportComptable="";
	private String dateLancement="";
	private String periode="";
	private String typeRaportComptable="";
	private String annee="";
	
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	public String getDateLancement() {
		return dateLancement;
	}
	public void setDateLancement(String dateLancement) {
		this.dateLancement = dateLancement;
	}
	public String getIdBatchRapportComptable() {
		return idBatchRapportComptable;
	}
	public void setIdBatchRapportComptable(String idBatchRapportComptable) {
		this.idBatchRapportComptable = idBatchRapportComptable;
	}
	public String getPeriode() {
		return periode;
	}
	public void setPeriode(String periode) {
		this.periode = periode;
	}
	public String getTypeRaportComptable() {
		return typeRaportComptable;
	}
	public void setTypeRaportComptable(String typeRaportComptable) {
		this.typeRaportComptable = typeRaportComptable;
	}
	
}
