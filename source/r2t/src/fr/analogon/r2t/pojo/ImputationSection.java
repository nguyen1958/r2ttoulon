package fr.analogon.r2t.pojo;

/**
 * represente une imputation section. Sofien CHARFI 30 Mars 2006
 */
public class ImputationSection {
	private String codeImputation;
	
	private String idImputation;

	private String codeSection;

	private String libelle;

	private String periodicite;

	private String codeRole;

	public String getCodeImputation() {
		return codeImputation;
	}

	public void setCodeImputation(String codeImputation) {
		this.codeImputation = codeImputation;
	}

	public String getCodeSection() {
		return codeSection;
	}

	public void setCodeSection(String codeSection) {
		this.codeSection = codeSection;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getPeriodicite() {
		return periodicite;
	}

	public void setPeriodicite(String periodicite) {
		this.periodicite = periodicite;
	}

	public String getCodeRole() {
		return codeRole;
	}

	public void setCodeRole(String codeRole) {
		this.codeRole = codeRole;
	}

	public String getIdImputation() {
		return idImputation;
	}

	public void setIdImputation(String idImputation) {
		this.idImputation = idImputation;
	}
}
