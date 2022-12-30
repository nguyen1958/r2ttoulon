package fr.analogon.r2t.pojo;

public class BatchRelance
{	
	String  idBatchRelance;
	String  annee;
	String  type;
	String	periode;
	String  dateLancement;
	int nombreFactureImpaye;

	public String getIdBatchRelance() {
		return idBatchRelance;
	}
	public void setIdBatchRelance(String idBatchRelance) {
		this.idBatchRelance = idBatchRelance;
	}
	public int getNombreFactureImpaye() {
		return nombreFactureImpaye;
	}
	public void setNombreFactureImpaye(int nombreFactureImpaye) {
		this.nombreFactureImpaye = nombreFactureImpaye;
	}
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
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
