package fr.analogon.r2t.pojo;


/**
 * creer par   le 23/03/2006 pour ANALOGON.
 * Modifier par Sofien CHARFI le 11/11/2007 pour ANALOGON.
 */
public class FamilleMarche 
{
	private String codeFamilleMarche= "";
	private String libelleFamilleMarche= "";
	private String peutEtreSupprimer= "";
	
	public String getCodeFamilleMarche() {
		return codeFamilleMarche;
	}
	public void setCodeFamilleMarche(String codeFamilleMarche) {
		this.codeFamilleMarche = codeFamilleMarche;
	}
	public String getLibelleFamilleMarche() {
		return libelleFamilleMarche;
	}
	public void setLibelleFamilleMarche(String libelleFamilleMarche) {
		this.libelleFamilleMarche = libelleFamilleMarche;
	}
    	
	public String getPeutEtreSupprimer() {
		return peutEtreSupprimer;
	}
	public void setPeutEtreSupprimer(String peutEtreSupprimer) {
		this.peutEtreSupprimer = peutEtreSupprimer;
	}
	
}
