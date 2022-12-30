package fr.analogon.r2t.pojo;

public class Payement 
{
	String idPayement="";
	String datePayement="";
	String idTypePayement="";
	String idBanque="";
	String numeroCheque="";
	String montantPayement="";
	String numeroQuittance="";
	String nombreDeFacturePayee="";
	String numRedevable="";
	String typePayement="";
	String typeTaxe="";
	String etat="";
	String numeroTransaction="";
	
	
	public String getNumRedevable() {
		return numRedevable;
	}
	public void setNumRedevable(String numRedevable) {
		this.numRedevable = numRedevable;
	}
	public String getDatePayement() {
		return datePayement;
	}
	public void setDatePayement(String datePayement) {
		this.datePayement = datePayement;
	}
	public String getIdBanque() {
		return idBanque;
	}
	public void setIdBanque(String idBanque) {
		this.idBanque = idBanque;
	}
	public String getIdPayement() {
		return idPayement;
	}
	public void setIdPayement(String idPayement) {
		this.idPayement = idPayement;
	}
	public String getIdTypePayement() {
		return idTypePayement;
	}
	public void setIdTypePayement(String idTypePayement) {
		this.idTypePayement = idTypePayement;
	}
	public String getMontantPayement() {
		return montantPayement;
	}
	public void setMontantPayement(String montantPayement) {
		this.montantPayement = montantPayement;
	}
	public String getNombreDeFacturePayee() {
		return nombreDeFacturePayee;
	}
	public void setNombreDeFacturePayee(String nombreDeFacturePayee) {
		this.nombreDeFacturePayee = nombreDeFacturePayee;
	}
	public String getNumeroCheque() {
		return numeroCheque;
	}
	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}
	public String getNumeroQuittance() {
		return numeroQuittance;
	}
	public void setNumeroQuittance(String numeroQuittance) {
		this.numeroQuittance = numeroQuittance;
	}
	public String getTypePayement() {
		return typePayement;
	}
	public void setTypePayement(String typePayement) {
		this.typePayement = typePayement;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getTypeTaxe() {
		return typeTaxe;
	}
	public void setTypeTaxe(String typeTaxe) {
		this.typeTaxe = typeTaxe;
	}
	public String getNumeroTransaction() {
		return numeroTransaction;
	}
	public void setNumeroTransaction(String numeroTransaction) {
		this.numeroTransaction = numeroTransaction;
	}
	

}
