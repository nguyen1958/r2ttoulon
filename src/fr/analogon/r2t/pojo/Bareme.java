package fr.analogon.r2t.pojo;

import fr.analogon.r2t.Utilitaire.StaticNombres;

/**
 * creer par   le 23/03/2006 pour ANALOGON.
 * Modifier par Sofien CHARFI le 11/11/2007 pour ANALOGON.
 */
public class Bareme 
{
	private String code;
	private String anExercice;
	private String libelle;
	private String codeImputation;
	private String sectionImputation;
	private double prixUnit;
	private String uniteDeTravail;
	private boolean droitPremierEtablissemenet = false;
	private boolean tax_an = false;
	private String codeGenerationBareme;  
	private String dureeUnitaire;
	private String typeArrondi;
	private String typeTaxe;
	private String idImputation;
	private String listeDesTranchesDesPrix;
	private String coefficientNumerique;
	
	 //	since 2.0
	private double Periode1;
	 //	since 2.0
	private double Periode2;
	 //	since 2.0
	private double Periode3;
	 //	since 2.0
	private double Periode4;
	 //	since 2.0
	private double Periode5;
	 //	since 2.0
	private String Prorata="false";
	
	 //	since 2.0
	private String Periodicite="false";
	
	 //	since 2.0
	private String necessiteControleTerrain="";
	
	
	

	private String sectionBareme="";
	private String imputationBareme="";
	


	public String getSectionBareme() {
		return sectionBareme;
	}

	public void setSectionBareme(String sectionBareme) {
		this.sectionBareme = sectionBareme;
	}

	public String getImputationBareme() {
		return imputationBareme;
	}

	public void setImputationBareme(String imputationBareme) {
		this.imputationBareme = imputationBareme;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAnExercice() {
		return anExercice;
	}

	public void setAnExercice(String anExercice) {
		this.anExercice = anExercice;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getCodeImputation() {
		return codeImputation;
	}

	public void setCodeImputation(String codeImputation) {
		this.codeImputation = codeImputation;
	}

	public String getSectionImputation() {
		return sectionImputation;
	}

	public void setSectionImputation(String sectionImputation) {
		this.sectionImputation = sectionImputation;
	}

	public double getPrixUnit() {
		return prixUnit;
	}

	public void setPrixUnit(double prixUnit) {
		this.prixUnit = StaticNombres.arronDD2(prixUnit);
	}

	public String getUniteDeTravail() {
		return uniteDeTravail;
	}

	public void setUniteDeTravail(String uniteDeTravail) {
		this.uniteDeTravail = uniteDeTravail;
	}

	public boolean getDroitPremierEtablissemenet() {
		return droitPremierEtablissemenet;
	}

	public void setDroitPremierEtablissemenet(boolean droitPremierEtablissemenet) {
		this.droitPremierEtablissemenet = droitPremierEtablissemenet;
	}

	public boolean getTax_an() {
		return tax_an;
	}

	public void setTax_an(boolean tax_an) {
		this.tax_an = tax_an;
	}

	public String getCodeGenerationBareme() {
		return codeGenerationBareme;
	}

	public void setCodeGenerationBareme(String codeGenerationBareme) {
		this.codeGenerationBareme = codeGenerationBareme;
	}

	public String getDureeUnitaire() {
		return dureeUnitaire;
	}

	public void setDureeUnitaire(String dureeUnitaire) {
		this.dureeUnitaire = dureeUnitaire;
	}

	public double getPeriode1() {
		return Periode1;
	}

	public void setPeriode1(double periode1) {
		Periode1 = periode1;
	}

	public double getPeriode2() {
		return Periode2;
	}

	public void setPeriode2(double periode2) {
		Periode2 = periode2;
	}

	public double getPeriode3() {
		return Periode3;
	}

	public void setPeriode3(double periode3) {
		Periode3 = periode3;
	}

	public double getPeriode4() {
		return Periode4;
	}

	public void setPeriode4(double periode4) {
		Periode4 = periode4;
	}
	
	public double getPeriode5() {
		return Periode5;
	}
	

	public void setPeriode5(double periode5) {
		Periode5 = periode5;
	}
	

	public String getProrata() {
		return Prorata;
	}
	public void setProrata(String prorata) {
		Prorata = prorata;
	}
	

	
	public String getPeriodicite() {
		return Periodicite;
	}
	public void setPeriodicite(String Periodicite) {
		this.Periodicite = Periodicite;
	}

	/**
	 * @return the typeArrondi
	 */
	public String getTypeArrondi() {
		return typeArrondi;
	}

	/**
	 * @param typeArrondi the typeArrondi to set
	 */
	public void setTypeArrondi(String typeArrondi) {
		this.typeArrondi = typeArrondi;
	}

	/**
	 * @return the idImputation
	 */
	public final String getIdImputation() {
		return idImputation;
	}

	/**
	 * @param idImputation the idImputation to set
	 */
	public final void setIdImputation(String idImputation) {
		this.idImputation = idImputation;
	}

	/**
	 * @return the typeTaxe
	 */
	public final String getTypeTaxe() {
		return typeTaxe;
	}

	/**
	 * @param typeTaxe the typeTaxe to set
	 */
	public final void setTypeTaxe(String typeTaxe) {
		this.typeTaxe = typeTaxe;
	}

	public String getNecessiteControleTerrain() {
		return necessiteControleTerrain;
	}

	public void setNecessiteControleTerrain(String necessiteControleTerrain) {
		this.necessiteControleTerrain = necessiteControleTerrain;
	}

	public String getListeDesTranchesDesPrix() {
		return listeDesTranchesDesPrix;
	}

	public void setListeDesTranchesDesPrix(String listeDesTranchesDesPrix) {
		this.listeDesTranchesDesPrix = listeDesTranchesDesPrix;
	}

	public String getCoefficientNumerique() {
		return coefficientNumerique;
	}

	public void setCoefficientNumerique(String coefficientNumerique) {
		this.coefficientNumerique = coefficientNumerique;
	}


 
		
}
