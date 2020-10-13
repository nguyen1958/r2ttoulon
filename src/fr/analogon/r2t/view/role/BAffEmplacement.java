package fr.analogon.r2t.view.role;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.main.RacineBean;
import fr.analogon.r2t.pojo.Emplacement;
import fr.analogon.r2t.pojo.Image;
import fr.analogon.r2t.pojo.Redevable;
import fr.analogon.r2t.request.DataFromBD;
import fr.analogon.r2t.request.RequestEmplacement;
import fr.analogon.r2t.request.RequestParametres;
import fr.analogon.r2t.request.RequestRedevable;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage pour l'emplacement general.
 * 
 * @version 1.1 . Sofien CHARFI
 * @since 1.0
 */
public class BAffEmplacement extends RacineBean
{
	private String lienListeOuvrage = "";
	private String listeDesDocuements ="";
	private String nomCommercialEmplacment = "";
	private String observation = "";
	private String numEmplacement = "";
	private String nombreOuvrageActif = "0";
	private String nombreOuvrage ;
	private String etatEmplacement = "";	
	private String numRedevable = "";	
	private String memeLien="";
	private String nombreImageEmplacement = "";
	private String raisonSocial="";
	private String numRue="";
	private String verifierConnexion="true";
	private String numeroEmplacementPersonalise="";
	private String dateReceptionDeclaration="";
	private String codeVoie="";
	private String codeRivolie="";
	private String leCodeSecteur="";
	private String nomQuartier="";
	private String adresse1="";
	private String adresse2="";
	private String adresse3="";
	private String cedex="";
	private String codePostal="";
	private String ville="";
	private String numTel="";
	private String numPortable="";
	private String numfax="";
	private String email="";
	private String codeInscription="";
	private String numInscription="";
	private String dateInscription="";
	private String dateDebutActivite="";
	private String dateFinActivite="";
	private String codeProfession="";
	private String codeSecteur="" ;
	private String codeEmplacement="";
	private String idImputation="";
	private String libelleImputation="";	
	private String anneeExerciceImputation="";	
	private String idRedevableAutorise="";	
	private String libelleProffesion="";	
	private String raisonSocialeProprietaire="";
	private String nomProprietaire="";
	private String prenomProprietaire="";	
	private String numVoieProprietaire="";	
	private String complementNumeroRueEmpl="";
	private String complementNumeroRueProprietaire="";
	private String codeVoixProprietaire="";
	private String adressProprietaire="";
	private String complement1AdressProprietaire="";
	private String complement2AdressProprietaire="";
	private String codePostaleProprietaire="";
	private String villeeProprietaire="";
	private String cedexProprietaire="";
	private String numTelFixeProprietaire="";
	private String numTelPortableProprietaire="";
	private String numTelFaxeProprietaire="";
	private String emailProprietaire="";	
	private String redevableAdd1 = "";
	private String redevableAdd2 = "";
	private String redevableAdd3 = "";
	private String redevableVille = "";
	private String redevableCedex = "false";
	private String redevableCodePostal = "";
	private String redevableCodeVoie = "";	
	private String redevableCodeRivolie ="";
	private HttpServletRequest request;
	private String nomRedevable = "";
	private String prenomRedevable = "";
	private String raisonSocialeRedevable = "";
	private String redevableNumRue = "";
	private String choix="";
	private String infosIdoss="";
	private String rapprochementIdoss="";
	private String nbreAlerteActif;
	private String peutEtreSupprimer ="ok";
	
	
	
	public String getPeutEtreSupprimer() {
		return peutEtreSupprimer;
	}



	public void setPeutEtreSupprimer(String peutEtreSupprimer) {
		this.peutEtreSupprimer = peutEtreSupprimer;
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

	//MARCHE :
	String marcheNomMarche="";


	//KIOSQUE
	String kiosqueCategorie="";
	String kiosqueNatureVente="";
	


	
	public String getNumeroEmplacementPersonalise() {
		return numeroEmplacementPersonalise;
	}



	public void setNumeroEmplacementPersonalise(String numeroEmplacementPersonalise) {
		this.numeroEmplacementPersonalise = numeroEmplacementPersonalise;
	}

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



	
	
	DataFromBD data = new DataFromBD();
		
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



	public String getConventionRecondiction() 
	{		
		Vector contenu  = new Vector();
		contenu.addElement("OUI");
		contenu.addElement("NON");
		conventionRecondiction=StaticManipHtml.genererListeDeroulante("conventionRecondiction", 1, conventionRecondiction, contenu, false);
		contenu = null;
		System.gc();
		
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



	public String getMarcheNomMarche() {
		return marcheNomMarche;
	}



	public void setMarcheNomMarche(String marcheNomMarche) {
		this.marcheNomMarche = marcheNomMarche;
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



	public void setBusCompanieAutoBus(String busCompanieAutoBus) {
		this.busCompanieAutoBus = busCompanieAutoBus;
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



	public void setRequest(HttpServletRequest req) 
	{
		try 
		{
			this.request = req;
			if (request.getParameter("choix")!= null )
				this.choix =request.getParameter("choix");
			
			if (request.getParameter("idImputation") != null )
				this.idImputation =request.getParameter("idImputation");
			
			if (request.getParameter("libelleImputation") != null )
				this.libelleImputation =request.getParameter("libelleImputation");
			
			if (request.getParameter("anneeExerciceImputation") != null )
				this.anneeExerciceImputation =request.getParameter("anneeExerciceImputation");	
			
			if (request.getParameter("numEmplacment") != null )
				this.numEmplacement =request.getParameter("numEmplacment");
			
			if (request.getParameter("numeroEmplacementPersonalise") != null )
				this.numeroEmplacementPersonalise =request.getParameter("numeroEmplacementPersonalise");

			
			if (request.getParameter("dateReceptionDeclaration") != null )
				this.dateReceptionDeclaration =request.getParameter("dateReceptionDeclaration");
			
			
			
			if (request.getParameter("codeSecteur") != null )
				this.leCodeSecteur =request.getParameter("codeSecteur");
			
			
			if (request.getParameter("observation") != null )
				this.observation =request.getParameter("observation");
			
			
			if (request.getParameter("numRedevable") != null )
				this.numRedevable = request.getParameter("numRedevable");
			
			//Cas de modification d'un emplacment
			if ( numEmplacement.length()> 0)
			{
				RequestEmplacement reqEmplacement = new RequestEmplacement();
				Emplacement emplacement=reqEmplacement.getEmplacement(numEmplacement);
				RequestEmplacement requEmplacement = new RequestEmplacement();
				nbreAlerteActif = requEmplacement.getNombreAlerte(emplacement.getNumEmplacement());
				 
				this.peutEtreSupprimer= reqEmplacement.peutEtreSupprimer(numEmplacement);
				this.raisonSocialeProprietaire=emplacement.getRaisonSocialeProprietaire();
				this.infosIdoss = emplacement.getInfosIdoss();
		    	this.nomProprietaire=emplacement.getNomProprietaire();	    	
		    	this.prenomProprietaire=emplacement.getPrenomProprietaire();
		    	this.numVoieProprietaire=emplacement.getNumVoieProprietaire();
		    	this.codeVoixProprietaire=emplacement.getCodeVoixProprietaire();
		    	this.adressProprietaire=emplacement.getAdressProprietaire();
		    	this.complement1AdressProprietaire=emplacement.getComplement1AdressProprietaire();
		    	this.complement2AdressProprietaire=emplacement.getComplement2AdressProprietaire();
		    	this.codePostaleProprietaire=emplacement.getCodePostaleProprietaire();
		    	this.villeeProprietaire=emplacement.getVilleeProprietaire();
		    	this.cedexProprietaire=emplacement.getCedexProprietaire();
		    	this.numTelFixeProprietaire=emplacement.getNumTelFixeProprietaire();
		    	this.numTelPortableProprietaire=emplacement.getNumTelPortableProprietaire();
		    	this.numTelFaxeProprietaire=emplacement.getNumTelFaxeProprietaire();
		    	this.emailProprietaire=emplacement.getEmailProprietaire();
		    	this.raisonSocial=emplacement.getRaisonSocial();    
		    	this.numRue=emplacement.getNumRue();
		    	this.codeRivolie=emplacement.getCodeRivolie();
		    	this.codeVoie = emplacement.getCodeVoie();
		    	this.codeSecteur=emplacement.getCodeSecteur();
		    	this.nomQuartier=emplacement.getQuartier();
		    	this.ville = emplacement.getVille();
		    	this.numeroEmplacementPersonalise = emplacement.getNumeroEmplacementPersonalise();
		    	this.dateReceptionDeclaration= emplacement.getDateReceptionDeclaration();
		    	this.etatEmplacement =emplacement.getEtatEmplacement();
		    	this.nomCommercialEmplacment=emplacement.getNomCommercialEmplacment();
		    	Vector liste = requEmplacement.getTableauDocumentEmplacement(numEmplacement);
		    	StaticManipHtml mHtml = new StaticManipHtml();
				RequestEmplacement reqEmpalcment = new RequestEmplacement();
				String lienRepertoire = "./r2tData/data/emplacements//"+numEmplacement+"//" ;	
		    	this.listeDesDocuements= mHtml.genererTableauListeDesDocuments(liste,lienRepertoire,numEmplacement);
		    	//this.listeDesDocuements= liste.toString();
		    	
		    	
		    	this.observation = emplacement.getObservation();
		    
		    	
		    	nomCommercialEmplacment= nomCommercialEmplacment.replaceAll("\"", "&quot;");
		    	this.nombreOuvrageActif = emplacement.getNombreOuvrageActif();
		    	this.nombreOuvrage = emplacement.getNombreOuvrage();
		    	this.complementNumeroRueProprietaire = emplacement.getComplementNumeroRueProprietaire();
		    	this.complementNumeroRueEmpl = emplacement.getComplementNumeroRueEmpl();
		    	if(numTelPortableProprietaire== null || numTelPortableProprietaire.equalsIgnoreCase("null") )
		    		numTelPortableProprietaire="";
		    	/*
		    	System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
		    	System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
		    	System.out.println("nombreOuvrage="+nombreOuvrage);
		    	System.out.println("nombreOuvrageActif="+nombreOuvrageActif);
		    	
		    	System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
		    	System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
		    	*/
		    	
		    	
		    	
		    	this.adresse1=emplacement.getAdresse1();
		    	this.adresse2=emplacement.getAdresse2();
		    	this.adresse3=emplacement.getAdresse3();    	
		    	this.codePostal=emplacement.getCodePostal();
		    	this.ville=emplacement.getVille();
		    	this.numTel=emplacement.getNumTel();
		    	this.numPortable =emplacement.getNumPortable();
		    	this.numfax =emplacement.getNumFax();
		    	this.email =emplacement.getEmail();
		    	this.codeInscription =emplacement.getCodeInscription();
		    	this.numInscription =emplacement.getNumInscription();
		    	this.numRedevable =emplacement.getNumRedevable();
		    	this.idImputation =emplacement.getIdImputation();
		    	this.libelleImputation =emplacement.getLibelleImputation();
		    	this.anneeExerciceImputation = emplacement.getAnneeExerciceImputation();
		    	this.cedex =emplacement.getCedex();
		    	this.idRedevableAutorise= emplacement.getIdRedevableAutorise(); 
		    	this.dateDebutActivite= emplacement.getDateDebutActivite().getLaDateString();
		    	this.dateFinActivite= emplacement.getDateFinActivite();
		    	this.dateInscription= emplacement.getDateInscription();
		    	this.codeProfession = emplacement.getCodeProfeesion();
		    	this.libelleProffesion = emplacement.getLibelleProfeesion();
		    	this.leCodeSecteur= emplacement.getCodeSecteur();
		    	this.libelleImputation = emplacement.getLibelleImputation();
		    	this.rapprochementIdoss = emplacement.getRapprochementIdoss();
		    			    	

		    	this.ambulantNomTournee= emplacement.getAmbulantNomTournee(); 
		    	this.ambulantCarteProfessionelleDateObtention= emplacement.getAmbulantCarteProfessionelleDateObtention();
		    	this.ambulantCarteProfessionelleNumero= emplacement.getAmbulantCarteProfessionelleNumero();
		    	this.ambulantCertficatHygieneDateObtention= emplacement.getAmbulantCertficatHygieneDateObtention();
		    	this.ambulantCertficatHygieneNumero= emplacement.getAmbulantCertficatHygieneNumero();
		    	this.ambulantCertficatPompierDateObtention= emplacement.getAmbulantCertficatPompierDateObtention();
		    	this.ambulantCertficatPompierNumero= emplacement.getAmbulantCertficatPompierNumero();
		    	this.ambulantVehiculeMarque= emplacement.getAmbulantVehiculeMarque();
		    	this.ambulantVehiculeModele= emplacement.getAmbulantVehiculeModele();
		    	this.ambulantVehiculeImmatriculation= emplacement.getAmbulantVehiculeImmatriculation();
		    	this.ambulantCompanieAssurance= emplacement.getAmbulantCompanieAssurance();

		    	//BUS (OK) :
		    	this.busCompanieAutoBus= emplacement.getBusCompanieAutoBus();
		    	this.busNombreCar= emplacement.getBusNombreCar();
		    	this.busLigneDeBus= emplacement.getBusLigneDeBus();
		    	this.busNombreDePlaceAssises= emplacement.getBusNombreDePlaceAssises();
		    	this.busNombreDePlaceDebout= emplacement.getBusNombreDePlaceDebout();
	    	
		    	
		    	//TAXI  (OK) :
		    	this.taxiNumeroTaxi= emplacement.getTaxiNumeroTaxi();
		    	this.taxiNumeroAssurance= emplacement.getTaxiNumeroAssurance();
		    	this.taxiImmatriculation= emplacement.getTaxiImmatriculation();
		    	this.taxiNumeroDeCarteGrise= emplacement.getTaxiNumeroDeCarteGrise();
		    	
		    	//CONVENTION :
		    	this.conventionDate = emplacement.getConventionDate();
		    	this.conventionDureeAns = emplacement.getConventionDureeAns();
		    	this.conventionDureeMois = emplacement.getConventionDureeMois();
		    	this.conventionDateEffet = emplacement.getConventionDateEffet();
		    	this.conventionDatePremiereRevision = emplacement.getConventionDatePremiereRevision();
		    	this.conventionDureeDatePremiereRevision = emplacement.getConventionDureeDatePremiereRevision();
		    	this.conventionObjet = emplacement.getConventionObjet();
		    	this.conventionLibelle = emplacement.getConventionLibelle();
		    	this.conventionRenouvellement = emplacement.getConventionRenouvellement();
		    	this.conventionMontantDuConditionnement = emplacement.getConventionMontantDuConditionnement();
		    	this.conventionRecondiction = emplacement.getConventionRecondiction();
		    	this.conventionRecondictionNombreMaximum = emplacement.getConventionRecondictionNombreMaximum();


		    	//DROIT DE VOIRIE :
		    	this.droitDeVoirieAutorisationNumeroAutorisation= emplacement.getDroitDeVoirieAutorisationNumeroAutorisation();
		    	this.droitDeVoirieAutorisationDate= emplacement.getDroitDeVoirieAutorisationDate();
		    	this.droitDeVoirieAutorisationType= emplacement.getDroitDeVoirieAutorisationType();
		    	this.droitDeVoirieTraveauxDureeMois= emplacement.getDroitDeVoirieTraveauxDureeMois();
		    	this.droitDeVoirieTraveauxDureeJour= emplacement.getDroitDeVoirieTraveauxDureeJour();
		    	this.droitDeVoirieTraveauxExecute= emplacement.getDroitDeVoirieTraveauxExecute();
		    	this.droitDeVoirieTraveauxConforme= emplacement.getDroitDeVoirieTraveauxConforme();
		    	this.droitDeVoirieNomInspecteur= emplacement.getDroitDeVoirieNomInspecteur();
		    	this.droitDeVoirieNature= emplacement.getDroitDeVoirieNature();
		    	this.droitDeVoirieObjetDeTraveaux= emplacement.getDroitDeVoirieObjetDeTraveaux();
		    	this.droitDeVoirieObjetDeAutorisation= emplacement.getDroitDeVoirieObjetDeAutorisation();

		    	
		    	//KIOSQUE :
		    	this.kiosqueCategorie  = emplacement.getKiosqueCategorie();
		    	this.kiosqueNatureVente= emplacement.getKiosqueNatureVente();
		    	
		    	
		    	
		    			    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	this.anneeExerciceImputation = emplacement.getAnneeExerciceImputation();
		    	this.lienListeOuvrage="./entree?action=regie_liste_element_facturation.jsp" +
				"&traitement=article&choix=modifier" +
				"&numEmplacement=" + this.numEmplacement ;
			}		
			//Cas de creation d'un emplacement
			else if (numRedevable.length() > 0)
			{		
				this.idRedevableAutorise = numRedevable;	
				RequestParametres requestParametres = new RequestParametres();
				this.ville = requestParametres.getVille();
				this.codePostal = requestParametres.getCodePotal();
			}
			if (numRedevable.length() > 0)
			{
				RequestRedevable req1 =new RequestRedevable();
				Redevable redevable = req1.getRedevable(numRedevable);			
				this.nomRedevable= redevable.getNomRedevable(); 
				this.prenomRedevable= redevable.getPrenomRedevable();
				this.raisonSocialeRedevable= redevable.getRaisonSocialeRedevable();
				this.redevableAdd1 = redevable.getAdressRedevable();	
		    	this.redevableAdd2 = redevable.getComplement1AdressRedevable();
		    	this.redevableAdd3 = redevable.getComplement2AdressRedevable();
		    	this.redevableVille = redevable.getVilleeRedevable();
		    	this.redevableCedex = redevable.getCedexRedevable();
		    	this.redevableCodePostal = redevable.getCodePostaleRedevable();
		    	this.redevableCodeVoie = redevable.getCodeVoixRedevable();
		    	this.redevableCodeRivolie = redevable.getCodeRivolieRedevable();
		    	this.redevableNumRue = redevable.getNumVoieRedevable();
		    	
			}
			
		}
		catch (Exception e) 
		{
			//System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRR");
			e.printStackTrace();
		}
		
		
	}
	
	
	
	/**
	 * @return the adresse1
	 */
	public final String getAdresse1() {
		return adresse1;
	}
	/**
	 * @param adresse1 the adresse1 to set
	 */
	public final void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}
	/**
	 * @return the adresse2
	 */
	public final String getAdresse2() {
		return adresse2;
	}
	/**
	 * @param adresse2 the adresse2 to set
	 */
	public final void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}
	/**
	 * @return the adresse3
	 */
	public final String getAdresse3() {
		return adresse3;
	}
	/**
	 * @param adresse3 the adresse3 to set
	 */
	public final void setAdresse3(String adresse3) {
		this.adresse3 = adresse3;
	}
	/**
	 * @return the adressProprietaire
	 */
	public final String getAdressProprietaire() {
		return adressProprietaire;
	}
	/**
	 * @param adressProprietaire the adressProprietaire to set
	 */
	public final void setAdressProprietaire(String adressProprietaire) {
		this.adressProprietaire = adressProprietaire;
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
	 * @return the cedex
	 */
	public final String getCedex() {
		return cedex;
	}
	/**
	 * @param cedex the cedex to set
	 */
	public final void setCedex(String cedex) {
		this.cedex = cedex;
	}
	/**
	 * @return the cedexProprietaire
	 */
	public final String getCedexProprietaire() {
		return cedexProprietaire;
	}
	/**
	 * @param cedexProprietaire the cedexProprietaire to set
	 */
	public final void setCedexProprietaire(String cedexProprietaire) {
		this.cedexProprietaire = cedexProprietaire;
	}
	/**
	 * @return the codeEmplacement
	 */
	public final String getCodeEmplacement() {
		return codeEmplacement;
	}
	/**
	 * @param codeEmplacement the codeEmplacement to set
	 */
	public final void setCodeEmplacement(String codeEmplacement) {
		this.codeEmplacement = codeEmplacement;
	}
	/**
	 * @return the codeInscription
	 */
	public final String getCodeInscription() {
		return codeInscription;
	}
	/**
	 * @param codeInscription the codeInscription to set
	 */
	public final void setCodeInscription(String codeInscription) {
		this.codeInscription = codeInscription;
	}
	/**
	 * @return the codePostal
	 */
	public final String getCodePostal() {
		return codePostal;
	}
	/**
	 * @param codePostal the codePostal to set
	 */
	public final void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	/**
	 * @return the codePostaleProprietaire
	 */
	public final String getCodePostaleProprietaire() {
		return codePostaleProprietaire;
	}
	/**
	 * @param codePostaleProprietaire the codePostaleProprietaire to set
	 */
	public final void setCodePostaleProprietaire(String codePostaleProprietaire) {
		this.codePostaleProprietaire = codePostaleProprietaire;
	}

	/**
	 * @return the codeProfession
	 */
	public String getCodeProfession()
	{
		String res="";
		StaticManipHtml mHtml = new StaticManipHtml();
		
		Vector contenu =data.getTousLesProffesions();
		////System.out.println("libelleProffesion"+libelleProffesion);
		if (codeProfession !=null && ! codeProfession.equalsIgnoreCase(""))
		{
			res= mHtml.genererListeDeroulanteApartirDeVacteur("codeProfession",
					libelleProffesion, contenu, 1, false);
		}
		else
		{			
			//Recuperation de l'activité du redevable 
			//valeur par defaut de l'emplacement
			//String stProffesionRedevable = (String)contenu.elementAt(0);
			RequestRedevable requestRedevable = new RequestRedevable();
			Redevable redevable = requestRedevable.getRedevable(numRedevable);
			String stProffesionRedevable= redevable.getProffesionRedevable();
			DataFromBD d = new DataFromBD();
			String libelleProfRed= d.getLibelleProffesion(Integer.parseInt(stProffesionRedevable));
			//Recupere le libelle de la prefesion du redevable
			//System.out.println("Proffession="+stProffesionRedevable );
			res= mHtml.genererListeDeroulanteApartirDeVacteur("codeProfession",
					libelleProfRed, contenu, 1, false);
			contenu = null;
			System.gc();
		}
		return res;			
	}
	
	
	/**
	 * @param codeProfession the codeProfession to set
	 */
	public final void setCodeProfession(String codeProfession) {
		this.codeProfession = codeProfession;
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
	 * @return the codeSecteur
	 */
	public String getCodeSecteur() 
	{
		String res="";
		StaticManipHtml mHtml = new StaticManipHtml();
		
		Vector contenu =data.getTousLesSecteurs();		
		if (leCodeSecteur !=null && ! leCodeSecteur.equalsIgnoreCase(""))
		{
			res= mHtml.genererListeDeroulanteApartirDeVacteur("leCodeSecteur",
					leCodeSecteur, contenu, 1, false);
		}
		else
		{			
			String stleCodeSecteur = (String)contenu.elementAt(0);			
			res= mHtml.genererListeDeroulanteApartirDeVacteur("leCodeSecteur",
					stleCodeSecteur, contenu, 1, false);
		}
		return res;	
	}

	
	
	
	
	/**
	 * @param codeSecteur the codeSecteur to set
	 */
	public final void setCodeSecteur(String codeSecteur) {
		this.codeSecteur = codeSecteur;
	}
	/**
	 * @return the codeVoie
	 */
	public final String getCodeVoie() {
		return codeVoie;
	}
	/**
	 * @param codeVoie the codeVoie to set
	 */
	public final void setCodeVoie(String codeVoie) {
		this.codeVoie = codeVoie;
	}
	/**
	 * @return the codeVoixProprietaire
	 */
	public final String getCodeVoixProprietaire() {
		return codeVoixProprietaire;
	}
	/**
	 * @param codeVoixProprietaire the codeVoixProprietaire to set
	 */
	public final void setCodeVoixProprietaire(String codeVoixProprietaire) {
		this.codeVoixProprietaire = codeVoixProprietaire;
	}
	/**
	 * @return the complement1AdressProprietaire
	 */
	public final String getComplement1AdressProprietaire() {
		return complement1AdressProprietaire;
	}
	/**
	 * @param complement1AdressProprietaire the complement1AdressProprietaire to set
	 */
	public final void setComplement1AdressProprietaire(
			String complement1AdressProprietaire) {
		this.complement1AdressProprietaire = complement1AdressProprietaire;
	}
	/**
	 * @return the complement2AdressProprietaire
	 */
	public final String getComplement2AdressProprietaire() {
		return complement2AdressProprietaire;
	}
	/**
	 * @param complement2AdressProprietaire the complement2AdressProprietaire to set
	 */
	public final void setComplement2AdressProprietaire(
			String complement2AdressProprietaire) {
		this.complement2AdressProprietaire = complement2AdressProprietaire;
	}
	/**
	 * @return the data
	 */
	public final DataFromBD getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public final void setData(DataFromBD data) {
		this.data = data;
	}
	/**
	 * @return the dateDebutActivite
	 */
	public final String getDateDebutActivite() {
		return dateDebutActivite;
	}
	/**
	 * @param dateDebutActivite the dateDebutActivite to set
	 */
	public final void setDateDebutActivite(String dateDebutActivite) {
		this.dateDebutActivite = dateDebutActivite;
	}
	/**
	 * @return the dateFinActivite
	 */
	public final String getDateFinActivite() {
		return dateFinActivite;
	}
	/**
	 * @param dateFinActivite the dateFinActivite to set
	 */
	public final void setDateFinActivite(String dateFinActivite) {
		this.dateFinActivite = dateFinActivite;
	}
	/**
	 * @return the dateInscription
	 */
	public final String getDateInscription() {
		return dateInscription;
	}
	/**
	 * @param dateInscription the dateInscription to set
	 */
	public final void setDateInscription(String dateInscription) {
		this.dateInscription = dateInscription;
	}
	/**
	 * @return the email
	 */
	public final String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public final void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the emailProprietaire
	 */
	public final String getEmailProprietaire() {
		return emailProprietaire;
	}
	/**
	 * @param emailProprietaire the emailProprietaire to set
	 */
	public final void setEmailProprietaire(String emailProprietaire) {
		this.emailProprietaire = emailProprietaire;
	}

	/**
	 * @return the etatEmplacement
	 */
	public String getEtatEmplacement() 
	{
		String res="";
		StaticManipHtml mHtml = new StaticManipHtml();		
		Vector contenu =data.getTousLesEtatsEmplacement();		
		if (etatEmplacement !=null && ! etatEmplacement.equalsIgnoreCase(""))
		{
			res= mHtml.genererListeDeroulanteApartirDeVacteur("etatEmplacement",
					etatEmplacement, contenu, 1, false);
		}
		else
		{			
			String stetatEmplacement = (String)contenu.elementAt(0);			
			res= mHtml.genererListeDeroulanteApartirDeVacteur("etatEmplacement",
					stetatEmplacement, contenu, 1, false);
		}
		return res;
	}
	
	
	
	/**
	 * @param etatEmplacement the etatEmplacement to set
	 */
	public final void setEtatEmplacement(String etatEmplacement) {
		this.etatEmplacement = etatEmplacement;
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
	 * @return the idRedevableAutorise
	 */
	public final String getIdRedevableAutorise() {
		return idRedevableAutorise;
	}
	/**
	 * @param idRedevableAutorise the idRedevableAutorise to set
	 */
	public final void setIdRedevableAutorise(String idRedevableAutorise) {
		this.idRedevableAutorise = idRedevableAutorise;
	}
	/**
	 * @return the leCodeSecteur
	 */
	public final String getLeCodeSecteur() {
		return leCodeSecteur;
	}
	/**
	 * @param leCodeSecteur the leCodeSecteur to set
	 */
	public final void setLeCodeSecteur(String leCodeSecteur) {
		this.leCodeSecteur = leCodeSecteur;
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
	 * @return the libelleProffesion
	 */
	public final String getLibelleProffesion() {
		return libelleProffesion;
	}
	/**
	 * @param libelleProffesion the libelleProffesion to set
	 */
	public final void setLibelleProffesion(String libelleProffesion) {
		this.libelleProffesion = libelleProffesion;
	}
	/**
	 * @return the lienListeOuvrage
	 */
	public final String getLienListeOuvrage() {
		return lienListeOuvrage;
	}
	/**
	 * @param lienListeOuvrage the lienListeOuvrage to set
	 */
	public final void setLienListeOuvrage(String lienListeOuvrage) {
		this.lienListeOuvrage = lienListeOuvrage;
	}
	/**
	 * @return the memeLien
	 */
	public final String getMemeLien() {
		return memeLien;
	}
	/**
	 * @param memeLien the memeLien to set
	 */
	public final void setMemeLien(String memeLien) {
		this.memeLien = memeLien;
	}
	/**
	 * @return the nombreImageEmplacement
	 */
	public String getNombreImageEmplacement()
	{
		String retour="0";
		if (numEmplacement.length() > 0 )
		{
			RequestEmplacement reqEmplacement = new RequestEmplacement();
			retour = String.valueOf(reqEmplacement.getNombreImagesEmplacement(numEmplacement));
			
			this.nombreImageEmplacement=retour;
		}
		return retour;
	}
	/**
	 * @param nombreImageEmplacement the nombreImageEmplacement to set
	 */
	public final void setNombreImageEmplacement(String nombreImageEmplacement) {
		this.nombreImageEmplacement = nombreImageEmplacement;
	}
	/**
	 * @return the nombreOuvrageActif
	 */
	public final String getNombreOuvrageActif() {
		return nombreOuvrageActif;
	}
	/**
	 * @param nombreOuvrageActif the nombreOuvrageActif to set
	 */
	public final void setNombreOuvrageActif(String nombreOuvrageActif) {
		this.nombreOuvrageActif = nombreOuvrageActif;
	}
	/**
	 * @return the nomCommercialEmplacment
	 */
	public final String getNomCommercialEmplacment() {
		return nomCommercialEmplacment;
	}
	/**
	 * @param nomCommercialEmplacment the nomCommercialEmplacment to set
	 */
	public final void setNomCommercialEmplacment(String nomCommercialEmplacment) {
		this.nomCommercialEmplacment = nomCommercialEmplacment;
	}
	/**
	 * @return the nomProprietaire
	 */
	public final String getNomProprietaire() {
		return nomProprietaire;
	}
	/**
	 * @param nomProprietaire the nomProprietaire to set
	 */
	public final void setNomProprietaire(String nomProprietaire) {
		this.nomProprietaire = nomProprietaire;
	}

	public String getNomQuartier()
	{
		String res="";
		/*
		StaticManipHtml mHtml = new StaticManipHtml();
		
		Vector contenu =data.getTousLesQuartiers();		
		if (nomQuartier !=null && ! nomQuartier.equalsIgnoreCase(""))
		{
			res= mHtml.genererListeDeroulanteApartirDeVacteur("NomQuartier",
					nomQuartier, contenu, 1, false);
		}
		else
		{			
			String stnomQuartier = (String)contenu.elementAt(0);			
			res= mHtml.genererListeDeroulanteApartirDeVacteur("NomQuartier",
					stnomQuartier, contenu, 1, false);
		}
		return res;
		*/		
		return nomQuartier;			
	}
	
	
	/**
	 * @param nomQuartier the nomQuartier to set
	 */
	public final void setNomQuartier(String nomQuartier) {
		this.nomQuartier = nomQuartier;
	}
	/**
	 * @return the numEmplacement
	 */
	public final String getNumEmplacement() {
		return numEmplacement;
	}
	/**
	 * @param numEmplacement the numEmplacement to set
	 */
	public final void setNumEmplacement(String numEmplacement) {
		this.numEmplacement = numEmplacement;
	}
	/**
	 * @return the numfax
	 */
	public final String getNumfax() {
		return numfax;
	}
	/**
	 * @param numfax the numfax to set
	 */
	public final void setNumfax(String numfax) {
		this.numfax = numfax;
	}
	/**
	 * @return the numInscription
	 */
	public final String getNumInscription() {
		return numInscription;
	}
	/**
	 * @param numInscription the numInscription to set
	 */
	public final void setNumInscription(String numInscription) {
		this.numInscription = numInscription;
	}
	/**
	 * @return the numPortable
	 */
	public final String getNumPortable() {
		return numPortable;
	}
	/**
	 * @param numPortable the numPortable to set
	 */
	public final void setNumPortable(String numPortable) {
		this.numPortable = numPortable;
	}
	/**
	 * @return the numRedevable
	 */
	public final String getNumRedevable() {
		return numRedevable;
	}
	/**
	 * @param numRedevable the numRedevable to set
	 */
	public final void setNumRedevable(String numRedevable) {
		this.numRedevable = numRedevable;
	}
	/**
	 * @return the numRue
	 */
	public final String getNumRue() 
	{
		
		return numRue;		
	}
	/**
	 * @param numRue the numRue to set
	 */
	public final void setNumRue(String numRue) {
		this.numRue = numRue;
	}
	/**
	 * @return the numTel
	 */
	public final String getNumTel() {
		return numTel;
	}
	/**
	 * @param numTel the numTel to set
	 */
	public final void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	/**
	 * @return the numTelFaxeProprietaire
	 */
	public final String getNumTelFaxeProprietaire() {
		return numTelFaxeProprietaire;
	}
	/**
	 * @param numTelFaxeProprietaire the numTelFaxeProprietaire to set
	 */
	public final void setNumTelFaxeProprietaire(String numTelFaxeProprietaire) {
		this.numTelFaxeProprietaire = numTelFaxeProprietaire;
	}
	/**
	 * @return the numTelFixeProprietaire
	 */
	public final String getNumTelFixeProprietaire() {
		return numTelFixeProprietaire;
	}
	/**
	 * @param numTelFixeProprietaire the numTelFixeProprietaire to set
	 */
	public final void setNumTelFixeProprietaire(String numTelFixeProprietaire) {
		this.numTelFixeProprietaire = numTelFixeProprietaire;
	}
	/**
	 * @return the numTelPortableProprietaire
	 */
	public final String getNumTelPortableProprietaire() {
		return numTelPortableProprietaire;
	}
	/**
	 * @param numTelPortableProprietaire the numTelPortableProprietaire to set
	 */
	public final void setNumTelPortableProprietaire(
			String numTelPortableProprietaire) {
		this.numTelPortableProprietaire = numTelPortableProprietaire;
	}
	/**
	 * @return the numVoieProprietaire
	 */
	public final String getNumVoieProprietaire() 
	{		
		return numVoieProprietaire;
	}
	/**
	 * @param numVoieProprietaire the numVoieProprietaire to set
	 */
	public final void setNumVoieProprietaire(String numVoieProprietaire) {
		this.numVoieProprietaire = numVoieProprietaire;
	}
	/**
	 * @return the prenomProprietaire
	 */
	public final String getPrenomProprietaire() {
		return prenomProprietaire;
	}
	/**
	 * @param prenomProprietaire the prenomProprietaire to set
	 */
	public final void setPrenomProprietaire(String prenomProprietaire) {
		this.prenomProprietaire = prenomProprietaire;
	}
	/**
	 * @return the raisonSocial
	 */
	public final String getRaisonSocial() {
		return raisonSocial;
	}
	/**
	 * @param raisonSocial the raisonSocial to set
	 */
	public final void setRaisonSocial(String raisonSocial) {
		this.raisonSocial = raisonSocial;
	}

	/**
	 * @return the raisonSocialeProprietaire
	 */
	public String getRaisonSocialeProprietaire() 
	{
		
		String res="";
		StaticManipHtml mHtml = new StaticManipHtml();
		//DataFromBD data = new DataFromBD();
		Vector contenu =data.getTousLesRaisonSocial();
		if (raisonSocialeProprietaire !=null )
		res= mHtml.genererListeDeroulanteApartirDeVacteur("raisonSocialeProprietaire",
				raisonSocialeProprietaire, contenu, 1, true);	
		else
		res= mHtml.genererListeDeroulanteApartirDeVacteur("raisonSocialeProprietaire",
					"", contenu, 1, false);	
		//System.out.println(
		return res;		
	}
	
	
	
	/**
	 * @param raisonSocialeProprietaire the raisonSocialeProprietaire to set
	 */
	public final void setRaisonSocialeProprietaire(String raisonSocialeProprietaire) {
		this.raisonSocialeProprietaire = raisonSocialeProprietaire;
	}
	/**
	 * @return the redevableAdd1
	 */
	public final String getRedevableAdd1() {
		return redevableAdd1;
	}
	/**
	 * @param redevableAdd1 the redevableAdd1 to set
	 */
	public final void setRedevableAdd1(String redevableAdd1) {
		this.redevableAdd1 = redevableAdd1;
	}
	/**
	 * @return the redevableAdd2
	 */
	public final String getRedevableAdd2() {
		return redevableAdd2;
	}
	/**
	 * @param redevableAdd2 the redevableAdd2 to set
	 */
	public final void setRedevableAdd2(String redevableAdd2) {
		this.redevableAdd2 = redevableAdd2;
	}
	/**
	 * @return the redevableAdd3
	 */
	public final String getRedevableAdd3() {
		return redevableAdd3;
	}
	/**
	 * @param redevableAdd3 the redevableAdd3 to set
	 */
	public final void setRedevableAdd3(String redevableAdd3) {
		this.redevableAdd3 = redevableAdd3;
	}
	/**
	 * @return the redevableCedex
	 */
	public final String getRedevableCedex() {
		return redevableCedex;
	}
	/**
	 * @param redevableCedex the redevableCedex to set
	 */
	public final void setRedevableCedex(String redevableCedex) {
		this.redevableCedex = redevableCedex;
	}
	/**
	 * @return the redevableCodePostal
	 */
	public final String getRedevableCodePostal() {
		return redevableCodePostal;
	}
	/**
	 * @param redevableCodePostal the redevableCodePostal to set
	 */
	public final void setRedevableCodePostal(String redevableCodePostal) {
		this.redevableCodePostal = redevableCodePostal;
	}
	/**
	 * @return the redevableCodeVoie
	 */
	public final String getRedevableCodeVoie() {
		return redevableCodeVoie;
	}
	/**
	 * @param redevableCodeVoie the redevableCodeVoie to set
	 */
	public final void setRedevableCodeVoie(String redevableCodeVoie) {
		this.redevableCodeVoie = redevableCodeVoie;
	}
	/**
	 * @return the redevableVille
	 */
	public final String getRedevableVille() {
		return redevableVille;
	}
	/**
	 * @param redevableVille the redevableVille to set
	 */
	public final void setRedevableVille(String redevableVille) {
		this.redevableVille = redevableVille;
	}
	/**
	 * @return the ville
	 */
	public final String getVille() {
		return ville;
	}
	/**
	 * @param ville the ville to set
	 */
	public final void setVille(String ville) {
		this.ville = ville;
	}
	/**
	 * @return the villeeProprietaire
	 */
	public final String getVilleeProprietaire() {
		return villeeProprietaire;
	}
	/**
	 * @param villeeProprietaire the villeeProprietaire to set
	 */
	public final void setVilleeProprietaire(String villeeProprietaire) {
		this.villeeProprietaire = villeeProprietaire;
	}












	/**
	 * @return the choix
	 */
	public final String getChoix() {
		return choix;
	}












	/**
	 * @param choix the choix to set
	 */
	public final void setChoix(String choix) {
		this.choix = choix;
	}












	/**
	 * @return the nomRedevable
	 */
	public final String getNomRedevable() {
		return nomRedevable;
	}












	/**
	 * @param nomRedevable the nomRedevable to set
	 */
	public final void setNomRedevable(String nomRedevable) {
		this.nomRedevable = nomRedevable;
	}












	/**
	 * @return the prenomRedevable
	 */
	public final String getPrenomRedevable() {
		return prenomRedevable;
	}












	/**
	 * @param prenomRedevable the prenomRedevable to set
	 */
	public final void setPrenomRedevable(String prenomRedevable) {
		this.prenomRedevable = prenomRedevable;
	}












	/**
	 * @return the raisonSocialeRedevable
	 */
	public final String getRaisonSocialeRedevable() {
		return raisonSocialeRedevable;
	}












	/**
	 * @param raisonSocialeRedevable the raisonSocialeRedevable to set
	 */
	public final void setRaisonSocialeRedevable(String raisonSocialeRedevable) {
		this.raisonSocialeRedevable = raisonSocialeRedevable;
	}












	/**
	 * @return the redevableNumRue
	 */
	public final String getRedevableNumRue() {
		return redevableNumRue;
	}

	/**
	 * @param redevableNumRue the redevableNumRue to set
	 */
	public final void setRedevableNumRue(String redevableNumRue) {
		this.redevableNumRue = redevableNumRue;
	}

	/**
	 * @return the request
	 */
	public final HttpServletRequest getRequest() {
		return request;
	}	
	
	public String getTableauImagesEmplacement()
	{
		String retour ="";
		try 
		{			
			if (numEmplacement != null && numEmplacement.length() != 0 )
			{
				this.memeLien="./preparergestionemplacement?optionTransfert=role"
					+"&numEmplacment="+this.numEmplacement
					+"&nomRedevable="+this.nomRedevable
					+"&prenom="+this.prenomRedevable;		
				retour="function getTableauImagesEmplacement() \n" +
						"{\n" +			
						" \nvar tableauImage=new Array;" ;				  
				  
				
				RequestEmplacement reqEmplacement = new RequestEmplacement();
				Vector contenu = reqEmplacement.getTableauImagesEmplacement(this.numEmplacement);				
				
				String cheminPhoto = fichierDeConfiguration.getLienPhotos();
				for (int i = 0; i < contenu.size(); i++) 
				{
					Image image = (Image)contenu.elementAt(i);				
					retour= retour+"\n var image=new Array;";
					retour= retour+"\n image[0]=\"./"+ cheminPhoto + image.getNomImage()+"\";";
					retour= retour+"\n image[1]=\""+image.getDateCreationImage()+"\";";
					retour= retour+"\n tableauImage["+i+"]=image;";	
					
				}
				retour= retour+"\n return tableauImage;";
				retour= retour+"\n}";				
			}
				
			
		}
		catch (Exception e) 
		{
			DebuggerLog4J.logger.fatal( e.getMessage() + e.getCause() + e.getMessage());
		}
		return retour;
		
		
	}
	
	public Vector getImagesEmplacement()
	{
		Vector retour = new Vector();
		try 
		{			
			if (numEmplacement != null && numEmplacement.length() != 0 )
			{
				RequestEmplacement reqEmplacement = new RequestEmplacement();
				Vector contenu = reqEmplacement.getTableauImagesEmplacement(this.numEmplacement);
				
				String cheminPhoto = fichierDeConfiguration.getLienPhotos();
				for (int i = 0; i < contenu.size(); i++) 
				{
					Image image = (Image)contenu.elementAt(i);				
					retour.addElement(image.getNomImage());
					//System.out.println(image.getNomImage());
					
				}					
			}		
			
		}
		catch (Exception e) 
		{
			DebuggerLog4J.logger.fatal( e.getMessage() + e.getCause() );
		}
		return retour;		
		
	}
	
	public int getNombreImagesEmplacement()
	{
		int nombreImage=0;
		if (numEmplacement != null && numEmplacement.length() != 0 )
		{
			RequestEmplacement reqEmplacement = new RequestEmplacement();
			nombreImage = reqEmplacement.getNombreImagesEmplacement(this.numEmplacement);		
		}
		return nombreImage;
	}



	/**
	 * @return the redevableCodeRivolie
	 */
	public final String getRedevableCodeRivolie() {
		return redevableCodeRivolie;
	}



	/**
	 * @param redevableCodeRivolie the redevableCodeRivolie to set
	 */
	public final void setRedevableCodeRivolie(String redevableCodeRivolie) {
		this.redevableCodeRivolie = redevableCodeRivolie;
	}



	/**
	 * @return the infosIdoss
	 */
	public final String getInfosIdoss() 
	{
		if (infosIdoss == null ||  infosIdoss.equalsIgnoreCase("null")) infosIdoss ="";
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
	public final String getRapprochementIdoss() 
	{
		if (rapprochementIdoss.equalsIgnoreCase("true")) 
			rapprochementIdoss ="OUI";
		else
			rapprochementIdoss ="NON";
		
		Vector contenu  = new Vector();
		contenu.addElement("OUI");
		contenu.addElement("NON");
		rapprochementIdoss=StaticManipHtml.genererListeDeroulante("rapprochementIdoss", 1, rapprochementIdoss, contenu, false);
		
		return rapprochementIdoss;
	}



	/**
	 * @param rapprochementIdoss the rapprochementIdoss to set
	 */
	public final void setRapprochementIdoss(String rapprochementIdoss) {
		this.rapprochementIdoss = rapprochementIdoss;
	}











	/**
	 * @return the nombreOuvrage
	 */
	public final String getNombreOuvrage() {
		return nombreOuvrage;
	}



	/**
	 * @param nombreOuvrage the nombreOuvrage to set
	 */
	public final void setNombreOuvrage(String nombreOuvrage) {
		this.nombreOuvrage = nombreOuvrage;
	}



	/**
	 * @return the complementNumeroRueEmpl
	 */
	public final String getComplementNumeroRueEmpl() 
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();		
		DataFromBD data = new DataFromBD();
		Vector contenu = new Vector();		
		contenu= data.getTousComplementNumRue();
		res = StaticManipHtml.genererListeDeroulante("complementNumeroRueEmpl", 1,complementNumeroRueEmpl, contenu,true);
		return res;
	}



	/**
	 * @param complementNumeroRueEmpl the complementNumeroRueEmpl to set
	 */
	public final void setComplementNumeroRueEmpl(String complementNumeroRueEmpl) 
	{
		this.complementNumeroRueEmpl = complementNumeroRueEmpl;
	}



	/**
	 * @return the complementNumeroRueProprietaire
	 */
	public final String getComplementNumeroRueProprietaire() 
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();		
		DataFromBD data = new DataFromBD();
		Vector contenu = new Vector();		
		contenu= data.getTousComplementNumRue();
		//System.out.println("complementNumeroRueProprietaire"+complementNumeroRueProprietaire);
		if(complementNumeroRueProprietaire==null ) complementNumeroRueProprietaire="";
		res = StaticManipHtml.genererListeDeroulante("complementNumeroRueProprietaire", 1,complementNumeroRueProprietaire, contenu,true);
		return res;
		
	}



	/**
	 * @param complementNumeroRueProprietaire the complementNumeroRueProprietaire to set
	 */
	public final void setComplementNumeroRueProprietaire(
			String complementNumeroRueProprietaire) {
		this.complementNumeroRueProprietaire = complementNumeroRueProprietaire;
	}
	
	public boolean verfierAcces(String listeLibelleDesTypesDeTaxeAutorise) 
	{
		boolean res=false;	
		if(listeLibelleDesTypesDeTaxeAutorise.indexOf(libelleImputation) != -1)
			res= true; 
		
		return res;
	}



	public String getVerifierConnexion() 
	{
		DebuggerLog4J.logger.debug("Getting verifierConnexion="+verifierConnexion);
		return verifierConnexion;
	}



	public void setVerifierConnexion(String verifierConnexion)
	{
		DebuggerLog4J.logger.debug("Setting verifierConnexion="+verifierConnexion);
		this.verifierConnexion = verifierConnexion;
	}



	public String getNbreAlerteActif() {
		return nbreAlerteActif;
	}



	public void setNbreAlerteActif(String nbreAlerteActif) {
		this.nbreAlerteActif = nbreAlerteActif;
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



	public String getListeDesDocuements() {
		return listeDesDocuements;
	}



	public void setListeDesDocuements(String listeDesDocuements) {
		this.listeDesDocuements = listeDesDocuements;
	}
	
	
	
}