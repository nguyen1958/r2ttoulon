package fr.analogon.r2t.pojo;

public class Imputation
{
	private String numtypeTaxe= "";
	private String libelle= "";
	private String designation= "";
	private String codeBudget= "";
	private String code= "";
	private String section= "";
	private String codeFonction= "";
	private String libelleFonction="";
	private String codeCentreResponsable= "";
	private String libelleCentreResponsable= "";
	private String codeCentreExecution= "";
	private String libelleCentreExecution= "";
	private String necessiteControleTerrain= "";
	private String controleInduitFacturation= "";
	private String nomElu="";
	private String EluRenseignement1="";
	private String EluRenseignement2="";
	private String EluRenseignement3="";
	private String nomGestionnaire="";
	private String telGestionnaire="";
	private String faxGestionnaire="";
	
	private String minimumPerception="";
	private String minimumDeFacturation="";
	private String anneeExercice="";
	private String typeFacturation="";
	private String cycleFacturation ="";
	private String renouvellementAutomatique="";
	private String marche="";
	
	//Paul evolution eCadre Budgetaire
	private String chapitre="";
	private String nature="";
	private String fonction="";
	private String codeOpeEquipement="";
	private String typeMouvement="";
	private String sens="";
	private String codeSegStructurelle="";
	private String codeEleStructurelleGestionnaire="";
	private String codeEleStructurelleDestinataire="";
	private String codeSegment1="";
	private String codeEleSectoriel1="";
	private String codeSegment2="";
	private String codeEleSectoriel2="";
	private String codeSegment3="";
	private String codeEleSectoriel3="";
	private String codeSegment4="";
	private String codeEleSectoriel4="";
	private String codeSegment5="";
	private String codeEleSectoriel5="";
	private String codeSegOperationnel="";
	private String codeEleOperationnel="";
	private String codeSegStrategique="";
	private String codeEleStrategique="";
	
	
	public String getMarche() {
		return marche;
	}
	public void setMarche(String marche) {
		this.marche = marche;
	}
	public String getRenouvellementAutomatique() {
		return renouvellementAutomatique;
	}
	public void setRenouvellementAutomatique(String renouvellementAutomatique) {
		this.renouvellementAutomatique = renouvellementAutomatique;
	}
	public String getCycleFacturation() {
		return cycleFacturation;
	}
	public void setCycleFacturation(String cycleFacturation) {
		this.cycleFacturation = cycleFacturation;
	}
	/**
	 * @return the eluRenseignement1
	 */
	public final String getEluRenseignement1() {
		return EluRenseignement1;
	}
	/**
	 * @param eluRenseignement1 the eluRenseignement1 to set
	 */
	public final void setEluRenseignement1(String eluRenseignement1) {
		EluRenseignement1 = eluRenseignement1;
	}
	/**
	 * @return the eluRenseignement2
	 */
	public final String getEluRenseignement2() {
		return EluRenseignement2;
	}
	/**
	 * @param eluRenseignement2 the eluRenseignement2 to set
	 */
	public final void setEluRenseignement2(String eluRenseignement2) {
		EluRenseignement2 = eluRenseignement2;
	}
	/**
	 * @return the eluRenseignement3
	 */
	public final String getEluRenseignement3() {
		return EluRenseignement3;
	}
	/**
	 * @param eluRenseignement3 the eluRenseignement3 to set
	 */
	public final void setEluRenseignement3(String eluRenseignement3) {
		EluRenseignement3 = eluRenseignement3;
	}
	/**
	 * @return the faxGestionnaire
	 */
	public final String getFaxGestionnaire() {
		return faxGestionnaire;
	}
	/**
	 * @param faxGestionnaire the faxGestionnaire to set
	 */
	public final void setFaxGestionnaire(String faxGestionnaire) {
		this.faxGestionnaire = faxGestionnaire;
	}
	/**
	 * @return the nomElu
	 */
	public final String getNomElu() {
		return nomElu;
	}
	/**
	 * @param nomElu the nomElu to set
	 */
	public final void setNomElu(String nomElu) {
		this.nomElu = nomElu;
	}
	/**
	 * @return the nomGestionnaire
	 */
	public final String getNomGestionnaire() {
		return nomGestionnaire;
	}
	/**
	 * @param nomGestionnaire the nomGestionnaire to set
	 */
	public final void setNomGestionnaire(String nomGestionnaire) {
		this.nomGestionnaire = nomGestionnaire;
	}
	/**
	 * @return the telGestionnaire
	 */
	public final String getTelGestionnaire() {
		return telGestionnaire;
	}
	/**
	 * @param telGestionnaire the telGestionnaire to set
	 */
	public final void setTelGestionnaire(String telGestionnaire) {
		this.telGestionnaire = telGestionnaire;
	}
	/**
	 * @return the code
	 */
	public final String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public final void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the codeBudget
	 */
	public final String getCodeBudget() {
		return codeBudget;
	}
	/**
	 * @param codeBudget the codeBudget to set
	 */
	public final void setCodeBudget(String codeBudget) {
		this.codeBudget = codeBudget;
	}
	/**
	 * @return the codeCentreExecution
	 */
	public final String getCodeCentreExecution() {
		return codeCentreExecution;
	}
	/**
	 * @param codeCentreExecution the codeCentreExecution to set
	 */
	public final void setCodeCentreExecution(String codeCentreExecution) {
		this.codeCentreExecution = codeCentreExecution;
	}
	/**
	 * @return the codeCentreResponsable
	 */
	public final String getCodeCentreResponsable() {
		return codeCentreResponsable;
	}
	/**
	 * @param codeCentreResponsable the codeCentreResponsable to set
	 */
	public final void setCodeCentreResponsable(String codeCentreResponsable) {
		this.codeCentreResponsable = codeCentreResponsable;
	}
	/**
	 * @return the codeFonction
	 */
	public final String getCodeFonction() {
		return codeFonction;
	}
	/**
	 * @param codeFonction the codeFonction to set
	 */
	public final void setCodeFonction(String codeFonction) {
		this.codeFonction = codeFonction;
	}
	/**
	 * @return the controleInduitFacturation
	 */
	public final String getControleInduitFacturation() {
		return controleInduitFacturation;
	}
	/**
	 * @param controleInduitFacturation the controleInduitFacturation to set
	 */
	public final void setControleInduitFacturation(String controleInduitFacturation) {
		this.controleInduitFacturation = controleInduitFacturation;
	}
	/**
	 * @return the designation
	 */
	public final String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public final void setDesignation(String designation) {
		this.designation = designation;
	}
	
	/**
	 * @return the libelle
	 */
	public final String getLibelle() {
		return libelle;
	}
	/**
	 * @param libelle the libelle to set
	 */
	public final void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/**
	 * @return the libelleCentreExecution
	 */
	public final String getLibelleCentreExecution() {
		return libelleCentreExecution;
	}
	/**
	 * @param libelleCentreExecution the libelleCentreExecution to set
	 */
	public final void setLibelleCentreExecution(String libelleCentreExecution) {
		this.libelleCentreExecution = libelleCentreExecution;
	}
	/**
	 * @return the libelleCentreResponsable
	 */
	public final String getLibelleCentreResponsable() {
		return libelleCentreResponsable;
	}
	/**
	 * @param libelleCentreResponsable the libelleCentreResponsable to set
	 */
	public final void setLibelleCentreResponsable(String libelleCentreResponsable) {
		this.libelleCentreResponsable = libelleCentreResponsable;
	}
	/**
	 * @return the libelleFonction
	 */
	public final String getLibelleFonction() {
		return libelleFonction;
	}
	/**
	 * @param libelleFonction the libelleFonction to set
	 */
	public final void setLibelleFonction(String libelleFonction) {
		this.libelleFonction = libelleFonction;
	}
	/**
	 * @return the necessiteControleTerrain
	 */
	public final String getNecessiteControleTerrain() {
		return necessiteControleTerrain;
	}
	/**
	 * @param necessiteControleTerrain the necessiteControleTerrain to set
	 */
	public final void setNecessiteControleTerrain(String necessiteControleTerrain) {
		this.necessiteControleTerrain = necessiteControleTerrain;
	}
	/**
	 * @return the numtypeTaxe
	 */
	public final String getNumtypeTaxe() {
		return numtypeTaxe;
	}
	/**
	 * @param numtypeTaxe the numtypeTaxe to set
	 */
	public final void setNumtypeTaxe(String numtypeTaxe) {
		this.numtypeTaxe = numtypeTaxe;
	}
	/**
	 * @return the section
	 */
	public final String getSection() {
		return section;
	}
	/**
	 * @param section the section to set
	 */
	public final void setSection(String section) {
		this.section = section;
	}
	/**
	 * @return the anneeExercice
	 */
	public final String getAnneeExercice() {
		return anneeExercice;
	}
	/**
	 * @param anneeExercice the anneeExercice to set
	 */
	public final void setAnneeExercice(String anneeExercice) {
		this.anneeExercice = anneeExercice;
	}
	/**
	 * @return the minimumPerception
	 */
	public final String getMinimumPerception() {
		return minimumPerception;
	}
	/**
	 * @param minimumPerception the minimumPerception to set
	 */
	public final void setMinimumPerception(String minimumPerception) {
		this.minimumPerception = minimumPerception;
	}
	/**
	 * @return the typeFacturation
	 */
	public final String getTypeFacturation() {
		return typeFacturation;
	}
	/**
	 * @param typeFacturation the typeFacturation to set
	 */
	public final void setTypeFacturation(String typeFacturation) {
		this.typeFacturation = typeFacturation;
	}
	/**
	 * @return the minimumDeFacturation
	 */
	public String getMinimumDeFacturation() {
		return minimumDeFacturation;
	}
	/**
	 * @param minimumDeFacturation the minimumDeFacturation to set
	 */
	public void setMinimumDeFacturation(String minimumDeFacturation) {
		this.minimumDeFacturation = minimumDeFacturation;
	}
	public String getChapitre() {
		return chapitre;
	}
	public void setChapitre(String chapitre) {
		this.chapitre = chapitre;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public String getCodeOpeEquipement() {
		return codeOpeEquipement;
	}
	public void setCodeOpeEquipement(String codeOpeEquipement) {
		this.codeOpeEquipement = codeOpeEquipement;
	}
	public String getTypeMouvement() {
		return typeMouvement;
	}
	public void setTypeMouvement(String typeMouvement) {
		this.typeMouvement = typeMouvement;
	}
	public String getSens() {
		return sens;
	}
	public void setSens(String sens) {
		this.sens = sens;
	}
	public String getCodeSegStructurelle() {
		return codeSegStructurelle;
	}
	public void setCodeSegStructurelle(String codeSegStructurelle) {
		this.codeSegStructurelle = codeSegStructurelle;
	}
	public String getCodeEleStructurelleGestionnaire() {
		return codeEleStructurelleGestionnaire;
	}
	public void setCodeEleStructurelleGestionnaire(
			String codeEleStructurelleGestionnaire) {
		this.codeEleStructurelleGestionnaire = codeEleStructurelleGestionnaire;
	}
	public String getCodeEleStructurelleDestinataire() {
		return codeEleStructurelleDestinataire;
	}
	public void setCodeEleStructurelleDestinataire(
			String codeEleStructurelleDestinataire) {
		this.codeEleStructurelleDestinataire = codeEleStructurelleDestinataire;
	}
	public String getCodeSegment1() {
		return codeSegment1;
	}
	public void setCodeSegment1(String codeSegment1) {
		this.codeSegment1 = codeSegment1;
	}
	public String getCodeEleSectoriel1() {
		return codeEleSectoriel1;
	}
	public void setCodeEleSectoriel1(String codeEleSectoriel1) {
		this.codeEleSectoriel1 = codeEleSectoriel1;
	}
	public String getCodeSegment2() {
		return codeSegment2;
	}
	public void setCodeSegment2(String codeSegment2) {
		this.codeSegment2 = codeSegment2;
	}
	public String getCodeEleSectoriel2() {
		return codeEleSectoriel2;
	}
	public void setCodeEleSectoriel2(String codeEleSectoriel2) {
		this.codeEleSectoriel2 = codeEleSectoriel2;
	}
	public String getCodeSegment3() {
		return codeSegment3;
	}
	public void setCodeSegment3(String codeSegment3) {
		this.codeSegment3 = codeSegment3;
	}
	public String getCodeEleSectoriel3() {
		return codeEleSectoriel3;
	}
	public void setCodeEleSectoriel3(String codeEleSectoriel3) {
		this.codeEleSectoriel3 = codeEleSectoriel3;
	}
	public String getCodeSegment4() {
		return codeSegment4;
	}
	public void setCodeSegment4(String codeSegment4) {
		this.codeSegment4 = codeSegment4;
	}
	public String getCodeEleSectoriel4() {
		return codeEleSectoriel4;
	}
	public void setCodeEleSectoriel4(String codeEleSectoriel4) {
		this.codeEleSectoriel4 = codeEleSectoriel4;
	}
	public String getCodeSegment5() {
		return codeSegment5;
	}
	public void setCodeSegment5(String codeSegment5) {
		this.codeSegment5 = codeSegment5;
	}
	public String getCodeEleSectoriel5() {
		return codeEleSectoriel5;
	}
	public void setCodeEleSectoriel5(String codeEleSectoriel5) {
		this.codeEleSectoriel5 = codeEleSectoriel5;
	}
	public String getCodeSegOperationnel() {
		return codeSegOperationnel;
	}
	public void setCodeSegOperationnel(String codeSegOperationnel) {
		this.codeSegOperationnel = codeSegOperationnel;
	}
	public String getCodeEleOperationnel() {
		return codeEleOperationnel;
	}
	public void setCodeEleOperationnel(String codeEleOperationnel) {
		this.codeEleOperationnel = codeEleOperationnel;
	}
	public String getCodeSegStrategique() {
		return codeSegStrategique;
	}
	public void setCodeSegStrategique(String codeSegStrategique) {
		this.codeSegStrategique = codeSegStrategique;
	}
	public String getCodeEleStrategique() {
		return codeEleStrategique;
	}
	public void setCodeEleStrategique(String codeEleStrategique) {
		this.codeEleStrategique = codeEleStrategique;
	}
	@Override
	public String toString() {
		return "Imputation [numtypeTaxe=" + numtypeTaxe + ", libelle="
				+ libelle + ", designation=" + designation + ", codeBudget="
				+ codeBudget + ", code=" + code + ", section=" + section
				+ ", codeFonction=" + codeFonction + ", libelleFonction="
				+ libelleFonction + ", codeCentreResponsable="
				+ codeCentreResponsable + ", libelleCentreResponsable="
				+ libelleCentreResponsable + ", codeCentreExecution="
				+ codeCentreExecution + ", libelleCentreExecution="
				+ libelleCentreExecution + ", necessiteControleTerrain="
				+ necessiteControleTerrain + ", controleInduitFacturation="
				+ controleInduitFacturation + ", nomElu=" + nomElu
				+ ", EluRenseignement1=" + EluRenseignement1
				+ ", EluRenseignement2=" + EluRenseignement2
				+ ", EluRenseignement3=" + EluRenseignement3
				+ ", nomGestionnaire=" + nomGestionnaire + ", telGestionnaire="
				+ telGestionnaire + ", faxGestionnaire=" + faxGestionnaire
				+ ", minimumPerception=" + minimumPerception
				+ ", minimumDeFacturation=" + minimumDeFacturation
				+ ", anneeExercice=" + anneeExercice + ", typeFacturation="
				+ typeFacturation + ", cycleFacturation=" + cycleFacturation
				+ ", renouvellementAutomatique=" + renouvellementAutomatique
				+ ", marche=" + marche + ", chapitre=" + chapitre + ", nature="
				+ nature + ", fonction=" + fonction + ", codeOpeEquipement="
				+ codeOpeEquipement + ", typeMouvement=" + typeMouvement
				+ ", sens=" + sens + ", codeSegStructurelle="
				+ codeSegStructurelle + ", codeEleStructurelleGestionnaire="
				+ codeEleStructurelleGestionnaire
				+ ", codeEleStructurelleDestinataire="
				+ codeEleStructurelleDestinataire + ", codeSegment1="
				+ codeSegment1 + ", codeEleSectoriel1=" + codeEleSectoriel1
				+ ", codeSegment2=" + codeSegment2 + ", codeEleSectoriel2="
				+ codeEleSectoriel2 + ", codeSegment3=" + codeSegment3
				+ ", codeEleSectoriel3=" + codeEleSectoriel3
				+ ", codeSegment4=" + codeSegment4 + ", codeEleSectoriel4="
				+ codeEleSectoriel4 + ", codeSegment5=" + codeSegment5
				+ ", codeEleSectoriel5=" + codeEleSectoriel5
				+ ", codeSegOperationnel=" + codeSegOperationnel
				+ ", codeEleOperationnel=" + codeEleOperationnel
				+ ", codeSegStrategique=" + codeSegStrategique
				+ ", codeEleStrategique=" + codeEleStrategique + "]";
	}

	

	
}
