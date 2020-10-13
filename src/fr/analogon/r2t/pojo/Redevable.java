package fr.analogon.r2t.pojo;

import fr.analogon.r2t.util.date.LaDate;

/**
 * creer par Sofien CHARFI pour ANALOGON.
 */

public class Redevable 
{

	private String id;	
	private String adressEmplacment;
	private String natureJuridiqueRedevable;	
	private String  informationComplementaire ="";
	private String  commentaire ="" ;
	private String  siret ="" ;
	private String  siren ="" ;	
	private String numeroEmplacment;
	private String codeVoie;	
	private String numeroProfession;
	private String nom;
	private String responsable;
	private String codeTiers;
	private String nomFille;
	private String prenom;
	private String civilite;
	private LaDate dateNaissance;
	private String lieuNaissance;
	private String nationalite;
	private String numRue;
	private String adresse1;
	private String adresse2;
	private String adresse3;
	private String cp;
	private String ville;
	private String cedex;
	private String numTel;
	private String numPortable;
	private String numFax;
	private String email;
	private String type;	
	private String typeEmplacement;
	private String natureJuridique;	
	private String etatEmplacement;	
	private String redevableActif;
	private String complementNumeroRueRedevable="";	
	private String complementNumeroRueLiquidateur="";
	
	// Apartir de la version 2
    private String numRedevable;	
    private String raisonSocialeRedevable="";
	private String nomRedevable="";
	private String nomJeuneFilleRedevable="";
	private String prenomRedevable="";
	private String responsableRedevable="";
	private String dateNaissanceRedevable="";
	private String lieuNaissanceRedevable="";
	private String nationalliteRedevable="";
	private String numVoieRedevable="";
	private String codeVoixRedevable="";	
	private String codeRivolieRedevable="";	
	private String adressRedevable="";
	private String complement1AdressRedevable="";
	private String complement2AdressRedevable="";
	private String codePostaleRedevable="";
	private String villeeRedevable="";
	private String cedexRedevable="";
	private String numTelFixeRedevable="";
	private String numTelPortableRedevable="";
	private String numTelFaxeRedevable="";
	private String emailRedevable="";
	private String proffesionRedevable="";	
    private String raisonSocialeLiquidateur="";
	private String nomLiquidateur="";
	private String prenomLiquidateur="";	
	private String numVoieLiquidateur="";
	private String codeVoixLiquidateur="";
	private String adressLiquidateur="";
	private String complement1AdressLiquidateur="";
	private String complement2AdressLiquidateur="";
	private String codePostaleLiquidateur="";
	private String villeeLiquidateur="";
	private String cedexLiquidateur="";
	private String numTelFixeLiquidateur="";
	private String numTelPortableLiquidateur="";
	private String numTelFaxeLiquidateur="";
	private String emailLiquidateur="";
	private String immeubleResidenceRedevable="";
	
	/**
	 * @return the adressLiquidateur
	 */
	public String getAdressLiquidateur() {
		return adressLiquidateur;
	}

	/**
	 * @param adressLiquidateur the adressLiquidateur to set
	 */
	public void setAdressLiquidateur(String adressLiquidateur) {
		this.adressLiquidateur = adressLiquidateur;
	}

	/**
	 * @return the cedexLiquidateur
	 */
	public String getCedexLiquidateur() {
		return cedexLiquidateur;
	}

	/**
	 * @param cedexLiquidateur the cedexLiquidateur to set
	 */
	public void setCedexLiquidateur(String cedexLiquidateur) {
		this.cedexLiquidateur = cedexLiquidateur;
	}

	/**
	 * @return the codePostaleLiquidateur
	 */
	public String getCodePostaleLiquidateur() {
		return codePostaleLiquidateur;
	}

	/**
	 * @param codePostaleLiquidateur the codePostaleLiquidateur to set
	 */
	public void setCodePostaleLiquidateur(String codePostaleLiquidateur) {
		this.codePostaleLiquidateur = codePostaleLiquidateur;
	}

	/**
	 * @return the codeVoixLiquidateur
	 */
	public String getCodeVoixLiquidateur() {
		return codeVoixLiquidateur;
	}

	/**
	 * @param codeVoixLiquidateur the codeVoixLiquidateur to set
	 */
	public void setCodeVoixLiquidateur(String codeVoixLiquidateur) {
		this.codeVoixLiquidateur = codeVoixLiquidateur;
	}

	/**
	 * @return the complement1AdressLiquidateur
	 */
	public String getComplement1AdressLiquidateur() {
		return complement1AdressLiquidateur;
	}

	/**
	 * @param complement1AdressLiquidateur the complement1AdressLiquidateur to set
	 */
	public void setComplement1AdressLiquidateur(String complement1AdressLiquidateur) {
		this.complement1AdressLiquidateur = complement1AdressLiquidateur;
	}

	/**
	 * @return the complement2AdressLiquidateur
	 */
	public String getComplement2AdressLiquidateur() {
		return complement2AdressLiquidateur;
	}

	/**
	 * @param complement2AdressLiquidateur the complement2AdressLiquidateur to set
	 */
	public void setComplement2AdressLiquidateur(String complement2AdressLiquidateur) {
		this.complement2AdressLiquidateur = complement2AdressLiquidateur;
	}

	/**
	 * @return the emailLiquidateur
	 */
	public String getEmailLiquidateur() {
		return emailLiquidateur;
	}

	/**
	 * @param emailLiquidateur the emailLiquidateur to set
	 */
	public void setEmailLiquidateur(String emailLiquidateur) {
		this.emailLiquidateur = emailLiquidateur;
	}

	/**
	 * @return the nomLiquidateur
	 */
	public String getNomLiquidateur() {
		return nomLiquidateur;
	}

	/**
	 * @param nomLiquidateur the nomLiquidateur to set
	 */
	public void setNomLiquidateur(String nomLiquidateur) {
		this.nomLiquidateur = nomLiquidateur;
	}

	/**
	 * @return the numTelFaxeLiquidateur
	 */
	public String getNumTelFaxeLiquidateur() {
		return numTelFaxeLiquidateur;
	}

	/**
	 * @param numTelFaxeLiquidateur the numTelFaxeLiquidateur to set
	 */
	public void setNumTelFaxeLiquidateur(String numTelFaxeLiquidateur) {
		this.numTelFaxeLiquidateur = numTelFaxeLiquidateur;
	}

	/**
	 * @return the numTelFixeLiquidateur
	 */
	public String getNumTelFixeLiquidateur() {
		return numTelFixeLiquidateur;
	}

	/**
	 * @param numTelFixeLiquidateur the numTelFixeLiquidateur to set
	 */
	public void setNumTelFixeLiquidateur(String numTelFixeLiquidateur) {
		this.numTelFixeLiquidateur = numTelFixeLiquidateur;
	}

	/**
	 * @return the numTelPortableLiquidateur
	 */
	public String getNumTelPortableLiquidateur() {
		return numTelPortableLiquidateur;
	}

	/**
	 * @param numTelPortableLiquidateur the numTelPortableLiquidateur to set
	 */
	public void setNumTelPortableLiquidateur(String numTelPortableLiquidateur) {
		this.numTelPortableLiquidateur = numTelPortableLiquidateur;
	}

	/**
	 * @return the numVoieLiquidateur
	 */
	public String getNumVoieLiquidateur() {
		return numVoieLiquidateur;
	}

	/**
	 * @param numVoieLiquidateur the numVoieLiquidateur to set
	 */
	public void setNumVoieLiquidateur(String numVoieLiquidateur) {
		this.numVoieLiquidateur = numVoieLiquidateur;
	}

	/**
	 * @return the prenomLiquidateur
	 */
	public String getPrenomLiquidateur() {
		return prenomLiquidateur;
	}

	/**
	 * @param prenomLiquidateur the prenomLiquidateur to set
	 */
	public void setPrenomLiquidateur(String prenomLiquidateur) {
		this.prenomLiquidateur = prenomLiquidateur;
	}

	/**
	 * @return the raisonSocialeLiquidateur
	 */
	public String getRaisonSocialeLiquidateur() {
		return raisonSocialeLiquidateur;
	}

	/**
	 * @param raisonSocialeLiquidateur the raisonSocialeLiquidateur to set
	 */
	public void setRaisonSocialeLiquidateur(String raisonSocialeLiquidateur) {
		this.raisonSocialeLiquidateur = raisonSocialeLiquidateur;
	}

	/**
	 * @return the villeeLiquidateur
	 */
	public String getVilleeLiquidateur() {
		return villeeLiquidateur;
	}

	/**
	 * @param villeeLiquidateur the villeeLiquidateur to set
	 */
	public void setVilleeLiquidateur(String villeeLiquidateur) {
		this.villeeLiquidateur = villeeLiquidateur;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodeVoie() {
		return codeVoie;
	}

	public void setCodeVoie(String codeVoie) {
		this.codeVoie = codeVoie;
	}

	public String getNumeroProfession() {
		return numeroProfession;
	}

	public void setNumeroProfession(String numeroProfession) {
		this.numeroProfession = numeroProfession;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getCodeTiers() {
		return codeTiers;
	}

	public void setCodeTiers(String codeTiers) {
		this.codeTiers = codeTiers;
	}

	public String getNomFille() {
		return nomFille;
	}

	public void setNomFille(String nomFille) {
		this.nomFille = nomFille;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public LaDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LaDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) 
	
	{
		
		this.nationalite = nationalite;
	}

	public String getNumRue() {
		return numRue;
	}

	public void setNumRue(String numRue) {
		this.numRue = numRue;
	}

	public String getAdresse1() {
		return adresse1;
	}

	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	public String getAdresse3() {
		return adresse3;
	}

	public void setAdresse3(String adresse3) {
		this.adresse3 = adresse3;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCedex() {
		return cedex;
	}

	public void setCedex(String cedex) {
		this.cedex = cedex;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getNumPortable() {
		return numPortable;
	}

	public void setNumPortable(String numPortable) {
		this.numPortable = numPortable;
	}

	public String getNumFax() {
		return numFax;
	}

	public void setNumFax(String numFax) {
		this.numFax = numFax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setNatureJuridique(String natureJuridique) {
		this.natureJuridique = natureJuridique;
	}

	public String getNatureJuridique() {
		return natureJuridique;
	}

	/**
	 * @return the adressRedevable
	 */
	public String getAdressRedevable() {
		return adressRedevable;
	}

	/**
	 * @param adressRedevable the adressRedevable to set
	 */
	public void setAdressRedevable(String adressRedevable) {
		this.adressRedevable = adressRedevable;
	}

	/**
	 * @return the cedexRedevable
	 */
	public String getCedexRedevable() {
		return cedexRedevable;
	}

	/**
	 * @param cedexRedevable the cedexRedevable to set
	 */
	public void setCedexRedevable(String cedexRedevable) {
		this.cedexRedevable = cedexRedevable;
	}

	/**
	 * @return the codePostaleRedevable
	 */
	public String getCodePostaleRedevable() {
		return codePostaleRedevable;
	}

	/**
	 * @param codePostaleRedevable the codePostaleRedevable to set
	 */
	public void setCodePostaleRedevable(String codePostaleRedevable) {
		this.codePostaleRedevable = codePostaleRedevable;
	}

	/**
	 * @return the codeVoixRedevable
	 */
	public String getCodeVoixRedevable() {
		return codeVoixRedevable;
	}

	/**
	 * @param codeVoixRedevable the codeVoixRedevable to set
	 */
	public void setCodeVoixRedevable(String codeVoixRedevable) {
		this.codeVoixRedevable = codeVoixRedevable;
	}

	/**
	 * @return the complement1AdressRedevable
	 */
	public String getComplement1AdressRedevable() {
		return complement1AdressRedevable;
	}

	/**
	 * @param complement1AdressRedevable the complement1AdressRedevable to set
	 */
	public void setComplement1AdressRedevable(String complement1AdressRedevable) {
		this.complement1AdressRedevable = complement1AdressRedevable;
	}

	/**
	 * @return the complement2AdressRedevable
	 */
	public String getComplement2AdressRedevable() {
		return complement2AdressRedevable;
	}

	/**
	 * @param complement2AdressRedevable the complement2AdressRedevable to set
	 */
	public void setComplement2AdressRedevable(String complement2AdressRedevable) {
		this.complement2AdressRedevable = complement2AdressRedevable;
	}

	/**
	 * @return the dateNaissanceRedevable
	 */
	public String getDateNaissanceRedevable() {
		return dateNaissanceRedevable;
	}

	/**
	 * @param dateNaissanceRedevable the dateNaissanceRedevable to set
	 */
	public void setDateNaissanceRedevable(String dateNaissanceRedevable) {
		this.dateNaissanceRedevable = dateNaissanceRedevable;
	}

	/**
	 * @return the emailRedevable
	 */
	public String getEmailRedevable() {
		return emailRedevable;
	}

	/**
	 * @param emailRedevable the emailRedevable to set
	 */
	public void setEmailRedevable(String emailRedevable) {
		this.emailRedevable = emailRedevable;
	}

	/**
	 * @return the lieuNaissanceRedevable
	 */
	public String getLieuNaissanceRedevable() {
		return lieuNaissanceRedevable;
	}

	/**
	 * @param lieuNaissanceRedevable the lieuNaissanceRedevable to set
	 */
	public void setLieuNaissanceRedevable(String lieuNaissanceRedevable) {
		this.lieuNaissanceRedevable = lieuNaissanceRedevable;
	}

	/**
	 * @return the nationalliteRedevable
	 */
	public String getNationalliteRedevable() {
		return nationalliteRedevable;
	}

	/**
	 * @param nationalliteRedevable the nationalliteRedevable to set
	 */
	public void setNationalliteRedevable(String nationalliteRedevable) {
		this.nationalliteRedevable = nationalliteRedevable;
	}

	/**
	 * @return the nomJeuneFilleRedevable
	 */
	public String getNomJeuneFilleRedevable() {
		return nomJeuneFilleRedevable;
	}

	/**
	 * @param nomJeuneFilleRedevable the nomJeuneFilleRedevable to set
	 */
	public void setNomJeuneFilleRedevable(String nomJeuneFilleRedevable) {
		this.nomJeuneFilleRedevable = nomJeuneFilleRedevable;
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
	 * @return the numRedevable
	 */
	public String getNumRedevable() {
		return numRedevable;
	}

	/**
	 * @param numRedevable the numRedevable to set
	 */
	public void setNumRedevable(String numRedevable) {
		this.numRedevable = numRedevable;
	}

	/**
	 * @return the numTelFaxeRedevable
	 */
	public String getNumTelFaxeRedevable() {
		return numTelFaxeRedevable;
	}

	/**
	 * @param numTelFaxeRedevable the numTelFaxeRedevable to set
	 */
	public void setNumTelFaxeRedevable(String numTelFaxeRedevable) {
		this.numTelFaxeRedevable = numTelFaxeRedevable;
	}

	/**
	 * @return the numTelFixeRedevable
	 */
	public String getNumTelFixeRedevable() {
		return numTelFixeRedevable;
	}

	/**
	 * @param numTelFixeRedevable the numTelFixeRedevable to set
	 */
	public void setNumTelFixeRedevable(String numTelFixeRedevable) {
		this.numTelFixeRedevable = numTelFixeRedevable;
	}

	/**
	 * @return the numTelPortableRedevable
	 */
	public String getNumTelPortableRedevable() {
		return numTelPortableRedevable;
	}

	/**
	 * @param numTelPortableRedevable the numTelPortableRedevable to set
	 */
	public void setNumTelPortableRedevable(String numTelPortableRedevable) {
		this.numTelPortableRedevable = numTelPortableRedevable;
	}

	/**
	 * @return the numVoieRedevable
	 */
	public String getNumVoieRedevable() {
		return numVoieRedevable;
	}

	/**
	 * @param numVoieRedevable the numVoieRedevable to set
	 */
	public void setNumVoieRedevable(String numVoieRedevable) {
		this.numVoieRedevable = numVoieRedevable;
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
	 * @return the raisonSocialeRedevable
	 */
	public String getRaisonSocialeRedevable() {
		return raisonSocialeRedevable;
	}

	/**
	 * @param raisonSocialeRedevable the raisonSocialeRedevable to set
	 */
	public void setRaisonSocialeRedevable(String raisonSocialeRedevable) {
		this.raisonSocialeRedevable = raisonSocialeRedevable;
	}

	/**
	 * @return the responsableRedevable
	 */
	public String getResponsableRedevable() {
		return responsableRedevable;
	}

	/**
	 * @param responsableRedevable the responsableRedevable to set
	 */
	public void setResponsableRedevable(String responsableRedevable) {
		this.responsableRedevable = responsableRedevable;
	}

	/**
	 * @return the villeeRedevable
	 */
	public String getVilleeRedevable() {
		return villeeRedevable;
	}

	/**
	 * @param villeeRedevable the villeeRedevable to set
	 */
	public void setVilleeRedevable(String villeeRedevable) {
		this.villeeRedevable = villeeRedevable;
	}

	/**
	 * @return the proffesionRedevable
	 */
	public String getProffesionRedevable() {
		return proffesionRedevable;
	}

	/**
	 * @param proffesionRedevable the proffesionRedevable to set
	 */
	public void setProffesionRedevable(String proffesionRedevable) {
		this.proffesionRedevable = proffesionRedevable;
	}

	/**
	 * @return the etatEmplacement
	 */
	public String getEtatEmplacement() {
		return etatEmplacement;
	}

	/**
	 * @param etatEmplacement the etatEmplacement to set
	 */
	public void setEtatEmplacement(String etatEmplacement) {
		this.etatEmplacement = etatEmplacement;
	}

	/**
	 * @return the adressEmplacment
	 */
	public String getAdressEmplacment() {
		return adressEmplacment;
	}

	/**
	 * @param adressEmplacment the adressEmplacment to set
	 */
	public void setAdressEmplacment(String adressEmplacment) {
		this.adressEmplacment = adressEmplacment;
	}

	/**
	 * @return the numeroEmplacment
	 */
	public String getNumeroEmplacment() {
		return numeroEmplacment;
	}

	/**
	 * @param numeroEmplacment the numeroEmplacment to set
	 */
	public void setNumeroEmplacment(String numeroEmplacment) {
		this.numeroEmplacment = numeroEmplacment;
	}

	/**
	 * @return the codeRivolieRedevable
	 */
	public final String getCodeRivolieRedevable() {
		return codeRivolieRedevable;
	}

	/**
	 * @param codeRivolieRedevable the codeRivolieRedevable to set
	 */
	public final void setCodeRivolieRedevable(String codeRivolieRedevable) {
		this.codeRivolieRedevable = codeRivolieRedevable;
	}

	/**
	 * @return the redevableActif
	 */
	public final String getRedevableActif() {
		return redevableActif;
	}

	/**
	 * @param redevableActif the redevableActif to set
	 */
	public final void setRedevableActif(String redevableActif) {
		this.redevableActif = redevableActif;
	}

	/**
	 * @return the complementNumeroRueLiquidateur
	 */
	public final String getComplementNumeroRueLiquidateur() {
		return complementNumeroRueLiquidateur;
	}

	/**
	 * @param complementNumeroRueLiquidateur the complementNumeroRueLiquidateur to set
	 */
	public final void setComplementNumeroRueLiquidateur(
			String complementNumeroRueLiquidateur) {
		this.complementNumeroRueLiquidateur = complementNumeroRueLiquidateur;
	}

	/**
	 * @return the complementNumeroRueRedevable
	 */
	public final String getComplementNumeroRueRedevable() {
		return complementNumeroRueRedevable;
	}

	/**
	 * @param complementNumeroRueRedevable the complementNumeroRueRedevable to set
	 */
	public final void setComplementNumeroRueRedevable(
			String complementNumeroRueRedevable) {
		this.complementNumeroRueRedevable = complementNumeroRueRedevable;
	}
	
	public String getNatureJuridiqueRedevable() {
		return natureJuridiqueRedevable;
	}

	public void setNatureJuridiqueRedevable(String natureJuridiqueRedevable) {
		this.natureJuridiqueRedevable = natureJuridiqueRedevable;
	}

	/**
	 * @return the informationComplementaire
	 */
	public String getInformationComplementaire() {
		return informationComplementaire;
	}

	/**
	 * @param informationComplementaire the informationComplementaire to set
	 */
	public void setInformationComplementaire(String informationComplementaire) {
		this.informationComplementaire = informationComplementaire;
	}

	/**
	 * @return the commentaire
	 */
	public String getCommentaire() {
		return commentaire;
	}

	/**
	 * @param commentaire the commentaire to set
	 */
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	

	/**
	 * @return the siret
	 */
	public String getSiret() {
		return siret;
	}

	/**
	 * @param siret the siret to set
	 */
	public void setSiret(String siret) {
		this.siret = siret;
	}

	/**
	 * @return the siren
	 */
	public String getSiren() {
		return siren;
	}

	/**
	 * @param siren the siren to set
	 */
	public void setSiren(String siren) {
		this.siren = siren;
	}

	/**
	 * @return the immeubleResidenceRedevable
	 */
	public String getImmeubleResidenceRedevable() {
		return immeubleResidenceRedevable;
	}

	/**
	 * @param immeubleResidenceRedevable the immeubleResidenceRedevable to set
	 */
	public void setImmeubleResidenceRedevable(String immeubleResidenceRedevable) {
		this.immeubleResidenceRedevable = immeubleResidenceRedevable;
	}

	public String getTypeEmplacement() {
		return typeEmplacement;
	}

	public void setTypeEmplacement(String typeEmplacement) {
		this.typeEmplacement = typeEmplacement;
	}


}
