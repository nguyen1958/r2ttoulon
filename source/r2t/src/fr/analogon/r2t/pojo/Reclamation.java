package fr.analogon.r2t.pojo;

public class Reclamation 
{
	String  dateReclamation;	
	String  listeOuvrage;	
	String  natureReclamation;	
	String typeReception;		
	String textReclamation;	
	String  etatReclamation;	
	int idRedevable;	
	String nomRedevable;	
	String prenomRedevable;	
	String civiliteRedevable;	
	int idFacture;		
	int idReclamation;	
	String typeTaxe;	
	String dateReponse;	
	String reponseReclamation;	
	String texteReponse;	
	String controleEffectue;	
	private String dateContole="";	
	private String nomContoleur="";	
	private String numContoleur="";
	private String motifReclamation="";	
	
	
	String listeCourierReponse="";
	

	/**
	 * @return the listeCourierReponse
	 */
	public final String getListeCourierReponse() {
		return listeCourierReponse;
	}

	/**
	 * @param listeCourierReponse the listeCourierReponse to set
	 */
	public final void setListeCourierReponse(String listeCourierReponse) {
		this.listeCourierReponse = listeCourierReponse;
	}

	/**
	 * @return the dateContole
	 */
	public String getDateContole() {
		return dateContole;
	}

	/**
	 * @param dateContole the dateContole to set
	 */
	public void setDateContole(String dateContole) {
		this.dateContole = dateContole;
	}

	/**
	 * @return the nomContoleur
	 */
	public String getNomContoleur() {
		return nomContoleur;
	}

	/**
	 * @param nomContoleur the nomContoleur to set
	 */
	public void setNomContoleur(String nomContoleur) {
		this.nomContoleur = nomContoleur;
	}
	
	
	
	
	public String getTexteReponse() {
		return texteReponse;
	}
	public void setTexteReponse(String texteReponse) {
		this.texteReponse = texteReponse;
	}
	public String getDateReponse() {
		return dateReponse;
	}
	public void setDateReponse(String dateReponse) {
		this.dateReponse = dateReponse;
	}
	public String getReponseReclamation() {
		return reponseReclamation;
	}
	public void setReponseReclamation(String reponseReclamation) {
		this.reponseReclamation = reponseReclamation;
	}
	public String getTypeTaxe() {
		return typeTaxe;
	}
	public void setTypeTaxe(String typeTaxe) {
		this.typeTaxe = typeTaxe;
	}
	public String getDateReclamation() {
		return dateReclamation;
	}
	public void setDateReclamation(String dateReclamation) {
		this.dateReclamation = dateReclamation;
	}
	public String getEtatReclamation() {
		return etatReclamation;
	}
	public void setEtatReclamation(String etatReclamation) {
		this.etatReclamation = etatReclamation;
	}
	public int getIdFacture() {
		return idFacture;
	}
	public void setIdFacture(int idFacture) {
		this.idFacture = idFacture;
	}
	public int getIdRedevable() {
		return idRedevable;
	}
	public void setIdRedevable(int idRedevable) {
		this.idRedevable = idRedevable;
	}

	public String getnatureReclamation() {
		return natureReclamation;
	}
	public void setnatureReclamation(String natureReclamation) {
		this.natureReclamation = natureReclamation;
	}
	public String getTextReclamation() {
		return textReclamation;
	}
	public void setTextReclamation(String textReclamation) {
		this.textReclamation = textReclamation;
	}
	public String getTypeReception() {
		return typeReception;
	}
	public void setTypeReception(String typeReception) {
		this.typeReception = typeReception;
	}
	public int getIdReclamation() {
		return idReclamation;
	}
	public void setIdReclamation(int idReclamation) {
		this.idReclamation = idReclamation;
	}
	/**
	 * @return the nomRedevable
	 */
	public String getNomRedevable() {
		return nomRedevable;
	}
	/**
	 * @param nomRedevable the nomRedevable to set
	 */
	public void setNomRedevable(String nomRedevable) {
		this.nomRedevable = nomRedevable;
	}
	/**
	 * @return the controleEffectue
	 */
	public String getControleEffectue() {
		return controleEffectue;
	}
	/**
	 * @param controleEffectue the controleEffectue to set
	 */
	public void setControleEffectue(String controleEffectue) {
		this.controleEffectue = controleEffectue;
	}

	/**
	 * @return the civiliteRedevable
	 */
	public String getCiviliteRedevable() {
		return civiliteRedevable;
	}

	/**
	 * @param civiliteRedevable the civiliteRedevable to set
	 */
	public void setCiviliteRedevable(String civiliteRedevable) {
		this.civiliteRedevable = civiliteRedevable;
	}

	/**
	 * @return the prenomRedevable
	 */
	public String getPrenomRedevable() {
		return prenomRedevable;
	}

	/**
	 * @param prenomRedevable the prenomRedevable to set
	 */
	public void setPrenomRedevable(String prenomRedevable) {
		this.prenomRedevable = prenomRedevable;
	}

	/**
	 * @return the listeOuvrage
	 */
	public String getListeOuvrage() {
		return listeOuvrage;
	}

	/**
	 * @param listeOuvrage the listeOuvrage to set
	 */
	public void setListeOuvrage(String listeOuvrage) {
		this.listeOuvrage = listeOuvrage;
	}

	/**
	 * @return the numContoleur
	 */
	public final String getNumContoleur() {
		return numContoleur;
	}

	/**
	 * @param numContoleur the numContoleur to set
	 */
	public final void setNumContoleur(String numContoleur) {
		this.numContoleur = numContoleur;
	}
	
	/**
	 * @return the motifReclamation
	 */
	public final String getMotifReclamation() {
		return motifReclamation;
	}

	/**
	 * @param numContoleur the motifReclamation to set
	 */
	public final void setMotifReclamation(String motifReclamation) {
		this.motifReclamation = motifReclamation;
	}
	
	
	

}
