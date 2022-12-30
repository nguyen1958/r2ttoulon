package fr.analogon.r2t.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import fr.analogon.r2t.util.date.LaDate;



/**
 * cree par CHARFI Sofien le 23/03/2008 pour ANALOGON.
 * 
 */
public class Emplacement {
	private String id;
	private String numeroTmp;	
	private String numeroAutorisation="";	
	private String observation="";
	private String nomCommercialEmplacment="";
	private String listeDesDocuements="";
	private String nombreOuvrageActif="";	
	private String idRedevableAutorise;
	private String codeProfeesion;
	private String libelleProfeesion;
	private String profeesion;
	private String etatEmplacement;
	private String nombreOuvrage;
	private String nombrePhoto;
	private String anneeExerciceImputation;
	
    private String raisonSocialeProprietaire;
	private String nomProprietaire;
	private String necessiteControleTerrain;
	private String prenomProprietaire;	
	private String numVoieProprietaire;
	private String codeVoixProprietaire;
	private String adressProprietaire;
	private String complement1AdressProprietaire;
	private String complement2AdressProprietaire;
	private String codePostaleProprietaire;
	private String villeeProprietaire;
	private String cedexProprietaire;
	private String numTelFixeProprietaire;
	private String numTelPortableProprietaire="";
	private String numTelFaxeProprietaire;
	private String emailProprietaire;
	
	private String redevableAdd1 = "";
	private String redevableAdd2 = "";
	private String redevableAdd3 = "";
	private String redevableVille = "";
	private String redevableCedex = "false";
	private String redevableCodePostal = "";
	private String redevableCodeVoie = "";
	private String NumRedevable;
	private String cedex;
	private String codePostal;
	private String infosIdoss;	
	private String complementNumeroRueEmpl="";	
	private String complementNumeroRueProprietaire="";	
	private String adresseComplete;	
	private Redevable redevable;
	private Profession profession;
	private LaDate dateDebutActivite;
	private String dateFinActivite;
	private String raisonSocial;
	private String codeSecteur;
	private String codeEmplacement;
	
	private String dateCreation="";
	
	private String libelleImputation;
	
	private String idImputation;

	private String type;

	private String enActivite;

	private String codeVoie;
	private String codeRivolie;

	private String numRue;

	private String adresse1;

	private String adresse2;

	private String adresse3;

	private String cp;

	private String ville;

	private String numTel;

	private String numFax;

	private String numPortable;

	private String email;

	private String codeInscription;

	private String numInscription;

	private String dateInscription;

	private String commentaire;



	private String idEmplOdp;

	private LaDate dateTransfert = new LaDate();

	private String texteAutorisationOccupation;

	private boolean prelevementAutomatique;

	private String codeBanquePrelev;

	
	private String quartier;
	
	private String nomCommercial;
	
	private String nomRedevable;
	
	private String prenomRedevable;
	
	private String numEmplacement;
	
	private String numeroRedevable;
	
	private String AnneeExercice;
	
	private String  rapprochementIdoss="";
	private String numeroEmplacementPersonalise="";
	private String dateReceptionDeclaration="";
	
	//besoin pour arreté d'autorisation
	private List listeOuvrage=new ArrayList<Article>();
	
	public String getNumeroEmplacementPersonalise() {
		return numeroEmplacementPersonalise;
	}

	public void setNumeroEmplacementPersonalise(String numeroEmplacementPersonalise) {
		this.numeroEmplacementPersonalise = numeroEmplacementPersonalise;
	}

	//AMBULANT (OK):
	String ambulantNomTournee ="";
	String ambulantCarteProfessionelleDateObtention="";
	String ambulantCarteProfessionelleNumero="";
	String ambulantCertficatHygieneDateObtention="";
	String ambulantCertficatHygieneNumero="";
	
	String ambulantCertficatPompierDateObtention="";
	String ambulantCertficatPompierNumero="";
	String ambulantVehiculeMarque="";
	String ambulantVehiculeModele="";
	String ambulantVehiculeImmatriculation="";
	String ambulantCompanieAssurance="";

	//BUS (OK) :
	String busCompanieAutoBus="";
	String busNombreCar="";
	String busLigneDeBus="";
	String busNombreDePlaceAssises="";
	String busNombreDePlaceDebout="";

	//TAXI  (OK) :
	String taxiNumeroTaxi="";
	String taxiNumeroAssurance="";
	String taxiImmatriculation="";
	String taxiNumeroDeCarteGrise="";

	//CONVENTION :
	String conventionDate ="";
	String conventionDureeAns ="";
	String conventionDureeMois ="";
	String conventionDateEffet ="";
	String conventionDatePremiereRevision ="";
	String conventionDureeDatePremiereRevision ="";
	String conventionObjet ="";
	String conventionLibelle ="";
	String conventionRenouvellement ="";
	String conventionMontantDuConditionnement ="";
	String conventionRecondiction ="";
	String conventionRecondictionNombreMaximum ="";


	//DROIT DE VOIRIE :
	String droitDeVoirieAutorisationNumeroAutorisation="";
	String droitDeVoirieAutorisationDate="";
	String droitDeVoirieAutorisationType="";
	String droitDeVoirieTraveauxDureeMois="";
	String droitDeVoirieTraveauxDureeJour="";
	String droitDeVoirieTraveauxExecute="";
	String droitDeVoirieTraveauxConforme="";
	String droitDeVoirieNomInspecteur="";
	String droitDeVoirieNature="";
	String droitDeVoirieObjetDeTraveaux="";
	String droitDeVoirieObjetDeAutorisation="";


	//KIOSQUE :
	String kiosqueCategorie="";
	String kiosqueNatureVente="";

	public String getKiosqueCategorie() {
		return kiosqueCategorie;
	}

	public void setKiosqueCategorie(String kiosqueCategorie) {
		this.kiosqueCategorie = kiosqueCategorie;
	}

	public String getKiosqueNatureVente() {
		return kiosqueNatureVente;
	}

	public void setKiosqueNatureVente(String kiosqueNatureVente) {
		this.kiosqueNatureVente = kiosqueNatureVente;
	}

	/**
	 * @return the anneeExercice
	 */
	public String getAnneeExercice() {
		return AnneeExercice;
	}

	public String getConventionDate() {
		return conventionDate;
	}

	public void setConventionDate(String conventionDate) {
		this.conventionDate = conventionDate;
	}

	public String getConventionDureeAns() {
		return conventionDureeAns;
	}

	public void setConventionDureeAns(String conventionDureeAns) {
		this.conventionDureeAns = conventionDureeAns;
	}

	public String getConventionDureeMois() {
		return conventionDureeMois;
	}

	public void setConventionDureeMois(String conventionDureeMois) {
		this.conventionDureeMois = conventionDureeMois;
	}

	public String getConventionDateEffet() {
		return conventionDateEffet;
	}

	public void setConventionDateEffet(String conventionDateEffet) {
		this.conventionDateEffet = conventionDateEffet;
	}

	public String getConventionDatePremiereRevision() {
		return conventionDatePremiereRevision;
	}

	public void setConventionDatePremiereRevision(
			String conventionDatePremiereRevision) {
		this.conventionDatePremiereRevision = conventionDatePremiereRevision;
	}

	public String getConventionDureeDatePremiereRevision() {
		return conventionDureeDatePremiereRevision;
	}

	public void setConventionDureeDatePremiereRevision(
			String conventionDureeDatePremiereRevision) {
		this.conventionDureeDatePremiereRevision = conventionDureeDatePremiereRevision;
	}

	public String getConventionObjet() {
		return conventionObjet;
	}

	public void setConventionObjet(String conventionObjet) {
		this.conventionObjet = conventionObjet;
	}

	public String getConventionLibelle() {
		return conventionLibelle;
	}

	public void setConventionLibelle(String conventionLibelle) {
		this.conventionLibelle = conventionLibelle;
	}

	public String getConventionRenouvellement() {
		return conventionRenouvellement;
	}

	public void setConventionRenouvellement(String conventionRenouvellement) {
		this.conventionRenouvellement = conventionRenouvellement;
	}

	public String getConventionMontantDuConditionnement() {
		return conventionMontantDuConditionnement;
	}

	public void setConventionMontantDuConditionnement(
			String conventionMontantDuConditionnement) {
		this.conventionMontantDuConditionnement = conventionMontantDuConditionnement;
	}

	public String getConventionRecondiction() {
		return conventionRecondiction;
	}

	public void setConventionRecondiction(String conventionRecondiction) {
		this.conventionRecondiction = conventionRecondiction;
	}

	public String getConventionRecondictionNombreMaximum() {
		return conventionRecondictionNombreMaximum;
	}

	public void setConventionRecondictionNombreMaximum(
			String conventionRecondictionNombreMaximum) {
		this.conventionRecondictionNombreMaximum = conventionRecondictionNombreMaximum;
	}

	public String getDroitDeVoirieAutorisationNumeroAutorisation() {
		return droitDeVoirieAutorisationNumeroAutorisation;
	}

	public void setDroitDeVoirieAutorisationNumeroAutorisation(
			String droitDeVoirieAutorisationNumeroAutorisation) {
		this.droitDeVoirieAutorisationNumeroAutorisation = droitDeVoirieAutorisationNumeroAutorisation;
	}

	public String getDroitDeVoirieAutorisationDate() {
		return droitDeVoirieAutorisationDate;
	}

	public void setDroitDeVoirieAutorisationDate(
			String droitDeVoirieAutorisationDate) {
		this.droitDeVoirieAutorisationDate = droitDeVoirieAutorisationDate;
	}

	public String getDroitDeVoirieAutorisationType() {
		return droitDeVoirieAutorisationType;
	}

	public void setDroitDeVoirieAutorisationType(
			String droitDeVoirieAutorisationType) {
		this.droitDeVoirieAutorisationType = droitDeVoirieAutorisationType;
	}

	public String getDroitDeVoirieTraveauxDureeMois() {
		return droitDeVoirieTraveauxDureeMois;
	}

	public void setDroitDeVoirieTraveauxDureeMois(
			String droitDeVoirieTraveauxDureeMois) {
		this.droitDeVoirieTraveauxDureeMois = droitDeVoirieTraveauxDureeMois;
	}

	public String getDroitDeVoirieTraveauxDureeJour() {
		return droitDeVoirieTraveauxDureeJour;
	}

	public void setDroitDeVoirieTraveauxDureeJour(
			String droitDeVoirieTraveauxDureeJour) {
		this.droitDeVoirieTraveauxDureeJour = droitDeVoirieTraveauxDureeJour;
	}

	public String getDroitDeVoirieTraveauxExecute() {
		return droitDeVoirieTraveauxExecute;
	}

	public void setDroitDeVoirieTraveauxExecute(String droitDeVoirieTraveauxExecute) {
		this.droitDeVoirieTraveauxExecute = droitDeVoirieTraveauxExecute;
	}

	public String getDroitDeVoirieTraveauxConforme() {
		return droitDeVoirieTraveauxConforme;
	}

	public void setDroitDeVoirieTraveauxConforme(
			String droitDeVoirieTraveauxConforme) {
		this.droitDeVoirieTraveauxConforme = droitDeVoirieTraveauxConforme;
	}

	public String getDroitDeVoirieNomInspecteur() {
		return droitDeVoirieNomInspecteur;
	}

	public void setDroitDeVoirieNomInspecteur(String droitDeVoirieNomInspecteur) {
		this.droitDeVoirieNomInspecteur = droitDeVoirieNomInspecteur;
	}

	public String getDroitDeVoirieNature() {
		return droitDeVoirieNature;
	}

	public void setDroitDeVoirieNature(String droitDeVoirieNature) {
		this.droitDeVoirieNature = droitDeVoirieNature;
	}

	public String getDroitDeVoirieObjetDeTraveaux() {
		return droitDeVoirieObjetDeTraveaux;
	}

	public void setDroitDeVoirieObjetDeTraveaux(String droitDeVoirieObjetDeTraveaux) {
		this.droitDeVoirieObjetDeTraveaux = droitDeVoirieObjetDeTraveaux;
	}

	public String getDroitDeVoirieObjetDeAutorisation() {
		return droitDeVoirieObjetDeAutorisation;
	}

	public void setDroitDeVoirieObjetDeAutorisation(
			String droitDeVoirieObjetDeAutorisation) {
		this.droitDeVoirieObjetDeAutorisation = droitDeVoirieObjetDeAutorisation;
	}


	public String getAmbulantNomTournee() {
		return ambulantNomTournee;
	}

	public void setAmbulantNomTournee(String ambulantNomTournee) {
		this.ambulantNomTournee = ambulantNomTournee;
	}

	public String getAmbulantCarteProfessionelleDateObtention() {
		return ambulantCarteProfessionelleDateObtention;
	}

	public void setAmbulantCarteProfessionelleDateObtention(
			String ambulantCarteProfessionelleDateObtention) {
		this.ambulantCarteProfessionelleDateObtention = ambulantCarteProfessionelleDateObtention;
	}

	public String getAmbulantCarteProfessionelleNumero() {
		return ambulantCarteProfessionelleNumero;
	}

	public void setAmbulantCarteProfessionelleNumero(
			String ambulantCarteProfessionelleNumero) {
		this.ambulantCarteProfessionelleNumero = ambulantCarteProfessionelleNumero;
	}

	public String getAmbulantCertficatHygieneDateObtention() {
		return ambulantCertficatHygieneDateObtention;
	}

	public void setAmbulantCertficatHygieneDateObtention(
			String ambulantCertficatHygieneDateObtention) {
		this.ambulantCertficatHygieneDateObtention = ambulantCertficatHygieneDateObtention;
	}

	public String getAmbulantCertficatHygieneNumero() {
		return ambulantCertficatHygieneNumero;
	}

	public void setAmbulantCertficatHygieneNumero(
			String ambulantCertficatHygieneNumero) {
		this.ambulantCertficatHygieneNumero = ambulantCertficatHygieneNumero;
	}

	public String getAmbulantCertficatPompierDateObtention() {
		return ambulantCertficatPompierDateObtention;
	}

	public void setAmbulantCertficatPompierDateObtention(
			String ambulantCertficatPompierDateObtention) {
		this.ambulantCertficatPompierDateObtention = ambulantCertficatPompierDateObtention;
	}

	public String getAmbulantCertficatPompierNumero() {
		return ambulantCertficatPompierNumero;
	}

	public void setAmbulantCertficatPompierNumero(
			String ambulantCertficatPompierNumero) {
		this.ambulantCertficatPompierNumero = ambulantCertficatPompierNumero;
	}

	public String getAmbulantVehiculeMarque() {
		return ambulantVehiculeMarque;
	}

	public void setAmbulantVehiculeMarque(String ambulantVehiculeMarque) {
		this.ambulantVehiculeMarque = ambulantVehiculeMarque;
	}

	public String getAmbulantVehiculeModele() {
		return ambulantVehiculeModele;
	}

	public void setAmbulantVehiculeModele(String ambulantVehiculeModele) {
		this.ambulantVehiculeModele = ambulantVehiculeModele;
	}

	public String getAmbulantVehiculeImmatriculation() {
		return ambulantVehiculeImmatriculation;
	}

	public void setAmbulantVehiculeImmatriculation(
			String ambulantVehiculeImmatriculation) {
		this.ambulantVehiculeImmatriculation = ambulantVehiculeImmatriculation;
	}

	public String getAmbulantCompanieAssurance() {
		return ambulantCompanieAssurance;
	}

	public void setAmbulantCompanieAssurance(String ambulantCompanieAssurance) {
		this.ambulantCompanieAssurance = ambulantCompanieAssurance;
	}

	public String getBusCompanieAutoBus() {
		return busCompanieAutoBus;
	}

	public void setBusCompanieAutoBus(String busCompanieAutobus) {
		this.busCompanieAutoBus = busCompanieAutobus;
	}

	public String getBusNombreCar() {
		return busNombreCar;
	}

	public void setBusNombreCar(String busNombreCar) {
		this.busNombreCar = busNombreCar;
	}

	public String getBusLigneDeBus() {
		return busLigneDeBus;
	}

	public void setBusLigneDeBus(String busLigneDeBus) {
		this.busLigneDeBus = busLigneDeBus;
	}

	public String getBusNombreDePlaceAssises() {
		return busNombreDePlaceAssises;
	}

	public void setBusNombreDePlaceAssises(String busNombreDePlaceAssises) {
		this.busNombreDePlaceAssises = busNombreDePlaceAssises;
	}

	public String getBusNombreDePlaceDebout() {
		return busNombreDePlaceDebout;
	}

	public void setBusNombreDePlaceDebout(String busNombreDePlaceDebout) {
		this.busNombreDePlaceDebout = busNombreDePlaceDebout;
	}

	public String getTaxiNumeroTaxi() {
		return taxiNumeroTaxi;
	}

	public void setTaxiNumeroTaxi(String taxiNumeroTaxi) {
		this.taxiNumeroTaxi = taxiNumeroTaxi;
	}

	public String getTaxiNumeroAssurance() {
		return taxiNumeroAssurance;
	}

	public void setTaxiNumeroAssurance(String taxiNumeroAssurance) {
		this.taxiNumeroAssurance = taxiNumeroAssurance;
	}

	public String getTaxiImmatriculation() {
		return taxiImmatriculation;
	}

	public void setTaxiImmatriculation(String taxiImmatriculation) {
		this.taxiImmatriculation = taxiImmatriculation;
	}

	public String getTaxiNumeroDeCarteGrise() {
		return taxiNumeroDeCarteGrise;
	}

	public void setTaxiNumeroDeCarteGrise(String taxiNumeroDeCarteGrise) {
		this.taxiNumeroDeCarteGrise = taxiNumeroDeCarteGrise;
	}

	/**
	 * @param anneeExercice the anneeExercice to set
	 */
	public void setAnneeExercice(String anneeExercice) {
		AnneeExercice = anneeExercice;
	}

	/**
	 * @return the numeroRedevable
	 */
	public String getNumeroRedevable() {
		return numeroRedevable;
	}

	/**
	 * @param numeroRedevable the numeroRedevable to set
	 */
	public void setNumeroRedevable(String numeroRedevable) {
		this.numeroRedevable = numeroRedevable;
	}

	public Emplacement() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Redevable getRedevable() {
		return redevable;
	}

	public void setRedevable(Redevable redevable) {
		this.redevable = redevable;
	}

	public LaDate getDateDebutActivite() {
		return dateDebutActivite;
	}

	public void setDateDebutActivite(LaDate dateDebutActivite) {
		this.dateDebutActivite = dateDebutActivite;
	}

	public String getDateFinActivite() {
		return dateFinActivite;
	}

	public void setDateFinActivite(String dateFinActivite) {
		this.dateFinActivite = dateFinActivite;
	}

	public String getRaisonSocial() {
		return raisonSocial;
	}

	public void setRaisonSocial(String raisonSocial) {
		this.raisonSocial = raisonSocial;
	}

	public String getCodeSecteur() {
		return codeSecteur;
	}

	public void setCodeSecteur(String codeSecteur) {
		this.codeSecteur = codeSecteur;
	}

	public String getCodeEmplacement() {
		return codeEmplacement;
	}

	public void setCodeEmplacement(String codeEmplacement) {
		this.codeEmplacement = codeEmplacement;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEnActivite() {
		return enActivite;
	}

	public void setEnActivite(String enActivite) {
		this.enActivite = enActivite;
	}

	public String getCodeVoie() {
		return codeVoie;
	}

	public void setCodeVoie(String codeVoie) {
		this.codeVoie = codeVoie;
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

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getNumFax() {
		return numFax;
	}

	public void setNumFax(String numFax) {
		this.numFax = numFax;
	}

	public String getNumPortable() {
		return numPortable;
	}

	public void setNumPortable(String numPortable) {
		this.numPortable = numPortable;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCodeInscription() {
		return codeInscription;
	}

	public void setCodeInscription(String codeInscription) {
		this.codeInscription = codeInscription;
	}

	public String getNumInscription() {
		return numInscription;
	}

	public void setNumInscription(String numInscription) {
		this.numInscription = numInscription;
	}

	public String getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(String dateInscription) {
		this.dateInscription = dateInscription;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	

	public String getIdEmplOdp() {
		return idEmplOdp;
	}

	public void setIdEmplOdp(String idEmplOdp) {
		this.idEmplOdp = idEmplOdp;
	}

	public LaDate getDateTransfert() {
		return dateTransfert;
	}

	public void setDateTransfert(LaDate dateTransfert) {
		this.dateTransfert = dateTransfert;
	}

	public String getTexteAutorisationOccupation() {
		return texteAutorisationOccupation;
	}

	public void setTexteAutorisationOccupation(
			String texteAutorisationOccupation) {
		this.texteAutorisationOccupation = texteAutorisationOccupation;
	}

	public boolean getPrelevementAutomatique() {
		return prelevementAutomatique;
	}

	public void setPrelevementAutomatique(boolean prelevementAutomatique) {
		this.prelevementAutomatique = prelevementAutomatique;
	}

	public String getCodeBanquePrelev() {
		return codeBanquePrelev;
	}

	public void setCodeBanquePrelev(String codeBanquePrelev) {
		this.codeBanquePrelev = codeBanquePrelev;
	}



	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public String getQuartier() {
		return quartier;
	}

	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}

	/**
	 * @return the adresseComplete
	 */
	public String getAdresseComplete() {
		return adresseComplete;
	}

	/**
	 * @param adresseComplete the adresseComplete to set
	 */
	public void setAdresseComplete(String adresseComplete) {
		this.adresseComplete = adresseComplete;
	}

	/**
	 * @return the nomCommercial
	 */
	public String getNomCommercial() {
		return nomCommercial;
	}

	/**
	 * @param nomCommercial the nomCommercial to set
	 */
	public void setNomCommercial(String nomCommercial) {
		this.nomCommercial = nomCommercial;
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
	 * @return the numEmplacement
	 */
	public String getNumEmplacement() {
		return numEmplacement;
	}

	/**
	 * @param numEmplacement the numEmplacement to set
	 */
	public void setNumEmplacement(String numEmplacement) {
		this.numEmplacement = numEmplacement;
	}

	/**
	 * @return the adressProprietaire
	 */
	public String getAdressProprietaire() {
		return adressProprietaire;
	}

	/**
	 * @param adressProprietaire the adressProprietaire to set
	 */
	public void setAdressProprietaire(String adressProprietaire) {
		this.adressProprietaire = adressProprietaire;
	}

	/**
	 * @return the cedexProprietaire
	 */
	public String getCedexProprietaire() {
		return cedexProprietaire;
	}

	/**
	 * @param cedexProprietaire the cedexProprietaire to set
	 */
	public void setCedexProprietaire(String cedexProprietaire) {
		this.cedexProprietaire = cedexProprietaire;
	}

	/**
	 * @return the codePostaleProprietaire
	 */
	public String getCodePostaleProprietaire() {
		return codePostaleProprietaire;
	}

	/**
	 * @param codePostaleProprietaire the codePostaleProprietaire to set
	 */
	public void setCodePostaleProprietaire(String codePostaleProprietaire) {
		this.codePostaleProprietaire = codePostaleProprietaire;
	}

	/**
	 * @return the codeVoixProprietaire
	 */
	public String getCodeVoixProprietaire() {
		return codeVoixProprietaire;
	}

	/**
	 * @param codeVoixProprietaire the codeVoixProprietaire to set
	 */
	public void setCodeVoixProprietaire(String codeVoixProprietaire) {
		this.codeVoixProprietaire = codeVoixProprietaire;
	}

	/**
	 * @return the complement1AdressProprietaire
	 */
	public String getComplement1AdressProprietaire() {
		return complement1AdressProprietaire;
	}

	/**
	 * @param complement1AdressProprietaire the complement1AdressProprietaire to set
	 */
	public void setComplement1AdressProprietaire(
			String complement1AdressProprietaire) {
		this.complement1AdressProprietaire = complement1AdressProprietaire;
	}

	/**
	 * @return the complement2AdressProprietaire
	 */
	public String getComplement2AdressProprietaire() {
		return complement2AdressProprietaire;
	}

	/**
	 * @param complement2AdressProprietaire the complement2AdressProprietaire to set
	 */
	public void setComplement2AdressProprietaire(
			String complement2AdressProprietaire) {
		this.complement2AdressProprietaire = complement2AdressProprietaire;
	}

	/**
	 * @return the emailProprietaire
	 */
	public String getEmailProprietaire() {
		return emailProprietaire;
	}

	/**
	 * @param emailProprietaire the emailProprietaire to set
	 */
	public void setEmailProprietaire(String emailProprietaire) {
		this.emailProprietaire = emailProprietaire;
	}

	/**
	 * @return the nomProprietaire
	 */
	public String getNomProprietaire() {
		return nomProprietaire;
	}

	/**
	 * @param nomProprietaire the nomProprietaire to set
	 */
	public void setNomProprietaire(String nomProprietaire) {
		this.nomProprietaire = nomProprietaire;
	}

	/**
	 * @return the numTelFaxeProprietaire
	 */
	public String getNumTelFaxeProprietaire() {
		return numTelFaxeProprietaire;
	}

	/**
	 * @param numTelFaxeProprietaire the numTelFaxeProprietaire to set
	 */
	public void setNumTelFaxeProprietaire(String numTelFaxeProprietaire) {
		this.numTelFaxeProprietaire = numTelFaxeProprietaire;
	}

	/**
	 * @return the numTelFixeProprietaire
	 */
	public String getNumTelFixeProprietaire() {
		return numTelFixeProprietaire;
	}

	/**
	 * @param numTelFixeProprietaire the numTelFixeProprietaire to set
	 */
	public void setNumTelFixeProprietaire(String numTelFixeProprietaire) {
		this.numTelFixeProprietaire = numTelFixeProprietaire;
	}

	/**
	 * @return the numTelPortableProprietaire
	 */
	public String getNumTelPortableProprietaire() {
		return numTelPortableProprietaire;
	}

	/**
	 * @param numTelPortableProprietaire the numTelPortableProprietaire to set
	 */
	public void setNumTelPortableProprietaire(String numTelPortableProprietaire) {
		this.numTelPortableProprietaire = numTelPortableProprietaire;
	}

	/**
	 * @return the numVoieProprietaire
	 */
	public String getNumVoieProprietaire() {
		return numVoieProprietaire;
	}

	/**
	 * @param numVoieProprietaire the numVoieProprietaire to set
	 */
	public void setNumVoieProprietaire(String numVoieProprietaire) {
		this.numVoieProprietaire = numVoieProprietaire;
	}

	/**
	 * @return the prenomProprietaire
	 */
	public String getPrenomProprietaire() {
		return prenomProprietaire;
	}

	/**
	 * @param prenomProprietaire the prenomProprietaire to set
	 */
	public void setPrenomProprietaire(String prenomProprietaire) {
		this.prenomProprietaire = prenomProprietaire;
	}

	/**
	 * @return the raisonSocialeProprietaire
	 */
	public String getRaisonSocialeProprietaire() {
		return raisonSocialeProprietaire;
	}

	/**
	 * @param raisonSocialeProprietaire the raisonSocialeProprietaire to set
	 */
	public void setRaisonSocialeProprietaire(String raisonSocialeProprietaire) {
		this.raisonSocialeProprietaire = raisonSocialeProprietaire;
	}

	/**
	 * @return the villeeProprietaire
	 */
	public String getVilleeProprietaire() {
		return villeeProprietaire;
	}

	/**
	 * @param villeeProprietaire the villeeProprietaire to set
	 */
	public void setVilleeProprietaire(String villeeProprietaire) {
		this.villeeProprietaire = villeeProprietaire;
	}

	/**
	 * @return the codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * @return the numRedevable
	 */
	public String getNumRedevable() {
		return NumRedevable;
	}

	/**
	 * @param numRedevable the numRedevable to set
	 */
	public void setNumRedevable(String numRedevable) {
		NumRedevable = numRedevable;
	}

	/**
	 * @return the cedex
	 */
	public String getCedex() {
		return cedex;
	}

	/**
	 * @param cedex the cedex to set
	 */
	public void setCedex(String cedex) {
		this.cedex = cedex;
	}

	/**
	 * @return the idRedevableAutorise
	 */
	public String getIdRedevableAutorise() {
		return idRedevableAutorise;
	}

	/**
	 * @param idRedevableAutorise the idRedevableAutorise to set
	 */
	public void setIdRedevableAutorise(String idRedevableAutorise) {
		this.idRedevableAutorise = idRedevableAutorise;
	}

	/**
	 * @return the codeProfeesion
	 */
	public String getCodeProfeesion() {
		return codeProfeesion;
	}

	/**
	 * @param codeProfeesion the codeProfeesion to set
	 */
	public void setCodeProfeesion(String codeProfeesion) {
		this.codeProfeesion = codeProfeesion;
	}

	/**
	 * @return the profeesion
	 */
	public String getProfeesion() {
		return profeesion;
	}

	/**
	 * @param profeesion the profeesion to set
	 */
	public void setProfeesion(String profeesion) {
		this.profeesion = profeesion;
	}

	/**
	 * @return the libelleProfeesion
	 */
	public String getLibelleProfeesion() {
		return libelleProfeesion;
	}

	/**
	 * @param libelleProfeesion the libelleProfeesion to set
	 */
	public void setLibelleProfeesion(String libelleProfeesion) {
		this.libelleProfeesion = libelleProfeesion;
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
	 * @return the nombreOuvrageActif
	 */
	public String getNombreOuvrageActif() {
		return nombreOuvrageActif;
	}

	/**
	 * @param nombreOuvrageActif the nombreOuvrageActif to set
	 */
	public void setNombreOuvrageActif(String nombreOuvrageActif) {
		this.nombreOuvrageActif = nombreOuvrageActif;
	}

	/**
	 * @return the redevableAdd1
	 */
	public String getRedevableAdd1() {
		return redevableAdd1;
	}

	/**
	 * @param redevableAdd1 the redevableAdd1 to set
	 */
	public void setRedevableAdd1(String redevableAdd1) {
		this.redevableAdd1 = redevableAdd1;
	}

	/**
	 * @return the redevableAdd2
	 */
	public String getRedevableAdd2() {
		return redevableAdd2;
	}

	/**
	 * @param redevableAdd2 the redevableAdd2 to set
	 */
	public void setRedevableAdd2(String redevableAdd2) {
		this.redevableAdd2 = redevableAdd2;
	}

	/**
	 * @return the redevableAdd3
	 */
	public String getRedevableAdd3() {
		return redevableAdd3;
	}

	/**
	 * @param redevableAdd3 the redevableAdd3 to set
	 */
	public void setRedevableAdd3(String redevableAdd3) {
		this.redevableAdd3 = redevableAdd3;
	}

	/**
	 * @return the redevableCedex
	 */
	public String getRedevableCedex() {
		return redevableCedex;
	}

	/**
	 * @param redevableCedex the redevableCedex to set
	 */
	public void setRedevableCedex(String redevableCedex) {
		this.redevableCedex = redevableCedex;
	}

	/**
	 * @return the redevableCodePostal
	 */
	public String getRedevableCodePostal() {
		return redevableCodePostal;
	}

	/**
	 * @param redevableCodePostal the redevableCodePostal to set
	 */
	public void setRedevableCodePostal(String redevableCodePostal) {
		this.redevableCodePostal = redevableCodePostal;
	}

	/**
	 * @return the redevableCodeVoie
	 */
	public String getRedevableCodeVoie() {
		return redevableCodeVoie;
	}

	/**
	 * @param redevableCodeVoie the redevableCodeVoie to set
	 */
	public void setRedevableCodeVoie(String redevableCodeVoie) {
		this.redevableCodeVoie = redevableCodeVoie;
	}

	/**
	 * @return the redevableVille
	 */
	public String getRedevableVille() {
		return redevableVille;
	}

	/**
	 * @param redevableVille the redevableVille to set
	 */
	public void setRedevableVille(String redevableVille) {
		this.redevableVille = redevableVille;
	}

	/**
	 * @return the nomCommercialEmplacment
	 */
	public String getNomCommercialEmplacment() {
		return nomCommercialEmplacment;
	}

	/**
	 * @param nomCommercialEmplacment the nomCommercialEmplacment to set
	 */
	public void setNomCommercialEmplacment(String nomCommercialEmplacment) {
		this.nomCommercialEmplacment = nomCommercialEmplacment;
	}

	/**
	 * @return the nombreOuvrage
	 */
	public String getNombreOuvrage() {
		return nombreOuvrage;
	}

	/**
	 * @param nombreOuvrage the nombreOuvrage to set
	 */
	public void setNombreOuvrage(String nombreOuvrage) {
		this.nombreOuvrage = nombreOuvrage;
	}

	
	

	/**
	 * @return the libelleImputation
	 */
	public final String getLibelleImputation() {
		return libelleImputation;
	}

	/**
	 * @param libelleImputation the libelleImputation to set
	 */
	public final void setLibelleImputation(String libelleImputation) {
		this.libelleImputation = libelleImputation;
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
	 * @return the anneeExerciceImputation
	 */
	public final String getAnneeExerciceImputation() {
		return anneeExerciceImputation;
	}

	/**
	 * @param anneeExerciceImputation the anneeExerciceImputation to set
	 */
	public final void setAnneeExerciceImputation(String anneeExerciceImputation) {
		this.anneeExerciceImputation = anneeExerciceImputation;
	}

	/**
	 * @return the numeroTmp
	 */
	public final String getNumeroTmp() {
		return numeroTmp;
	}

	/**
	 * @param numeroTmp the numeroTmp to set
	 */
	public final void setNumeroTmp(String numeroTmp) {
		this.numeroTmp = numeroTmp;
	}

	/**
	 * @return the codeRivolie
	 */
	public final String getCodeRivolie() {
		return codeRivolie;
	}

	/**
	 * @param codeRivolie the codeRivolie to set
	 */
	public final void setCodeRivolie(String codeRivolie) {
		this.codeRivolie = codeRivolie;
	}

	/**
	 * @return the infosIdoss
	 */
	public final String getInfosIdoss() {
		return infosIdoss;
	}

	/**
	 * @param infosIdoss the infosIdoss to set
	 */
	public final void setInfosIdoss(String infosIdoss) {
		this.infosIdoss = infosIdoss;
	}

	/**
	 * @return the rapprochementIdoss
	 */
	public final String getRapprochementIdoss() {
		return rapprochementIdoss;
	}

	/**
	 * @param rapprochementIdoss the rapprochementIdoss to set
	 */
	public final void setRapprochementIdoss(String rapprochementIdoss) {
		this.rapprochementIdoss = rapprochementIdoss;
	}

	/**
	 * @return the nombrePhoto
	 */
	public final String getNombrePhoto() {
		return nombrePhoto;
	}

	/**
	 * @param nombrePhoto the nombrePhoto to set
	 */
	public final void setNombrePhoto(String nombrePhoto) {
		this.nombrePhoto = nombrePhoto;
	}

	/**
	 * @return the complementNumeroRueEmpl
	 */
	public final String getComplementNumeroRueEmpl() {
		return complementNumeroRueEmpl;
	}

	/**
	 * @param complementNumeroRueEmpl the complementNumeroRueEmpl to set
	 */
	public final void setComplementNumeroRueEmpl(String complementNumeroRueEmpl) {
		this.complementNumeroRueEmpl = complementNumeroRueEmpl;
	}

	/**
	 * @return the complementNumeroRueProprietaire
	 */
	public final String getComplementNumeroRueProprietaire() {
		return complementNumeroRueProprietaire;
	}

	/**
	 * @param complementNumeroRueProprietaire the complementNumeroRueProprietaire to set
	 */
	public final void setComplementNumeroRueProprietaire(
			String complementNumeroRueProprietaire) {
		this.complementNumeroRueProprietaire = complementNumeroRueProprietaire;
	}
	
	 
	public String getNumeroAutorisation() {
		return numeroAutorisation;
	}

	public void setNumeroAutorisation(String numeroAutorisation) {
		this.numeroAutorisation = numeroAutorisation;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * @return the dateReceptionDeclaration
	 */
	public String getDateReceptionDeclaration() {
		return dateReceptionDeclaration;
	}

	/**
	 * @param dateReceptionDeclaration the dateReceptionDeclaration to set
	 */
	public void setDateReceptionDeclaration(String dateReceptionDeclaration) {
		this.dateReceptionDeclaration = dateReceptionDeclaration;
	}

	/**
	 * @return the necessiteControleTerrain
	 */
	public String getNecessiteControleTerrain() {
		return necessiteControleTerrain;
	}

	/**
	 * @param necessiteControleTerrain the necessiteControleTerrain to set
	 */
	public void setNecessiteControleTerrain(String necessiteControleTerrain) {
		this.necessiteControleTerrain = necessiteControleTerrain;
	}

	public String getListeDesDocuements() {
		return listeDesDocuements;
	}

	public void setListeDesDocuements(String listeDesDocuements) {
		this.listeDesDocuements = listeDesDocuements;
	}

	public List getListeOuvrage() {
		return listeOuvrage;
	}

	public void setListeOuvrage(List listeOuvrage) {
		this.listeOuvrage = listeOuvrage;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	
	
}
