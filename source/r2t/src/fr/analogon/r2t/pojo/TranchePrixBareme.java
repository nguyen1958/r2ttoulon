package fr.analogon.r2t.pojo;

public class TranchePrixBareme
{
	private String idBareme;
	private String annee;
	private String surfaceMin;
	private String surfaceMax;
	private String prix;
	
	
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	public String getIdBareme() {
		return idBareme;
	}
	public void setIdBareme(String idBareme) {
		this.idBareme = idBareme;
	}
	public String getPrix() {
		return prix;
	}
	public void setPrix(String prix) {
		this.prix = prix;
	}
	public String getSurfaceMax() {
		return surfaceMax;
	}
	public void setSurfaceMax(String surfaceMax) {
		this.surfaceMax = surfaceMax;
	}
	public String getSurfaceMin() {
		return surfaceMin;
	}
	public void setSurfaceMin(String surfaceMin) {
		this.surfaceMin = surfaceMin;
	}
	

}
