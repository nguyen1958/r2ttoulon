package fr.analogon.r2t.main;


/**
 * Cette classe contient differentes variables de configuration, tel que des
 * variables de messages ou des nom de variables de session. . Sofien CHARFI
 *   fromPleiade
 * @version 1.1
 * @since 1.0
 */
public class ConfVariable 
{
	
	//TEST OF CONF
	//Paul 20-07-2017 impact du développement de r2tInspecteur pour tablette
	//Paul 07-07-2020 Relance facture [éditer total selon différent typepayement : remise,exonération et acompte]
	public static String codeDeVersionR2T = "V4.4.7_29062021"; // vVersion-SousVersion-Revision-DateRevision	
	
	public static String codeDeVersionRecenement = "V1.0.1"; // vVersion-SousVersion-Revision-DateRevision

	public static String nomAnalogon = "Analogon";

	public static String siteAnalogon = "www.analogon.fr";

	public static int maxAffichageReponse = 300;

	public static int maxEchecConnection = 20;

	public static String nomSessionNbEchecConsecutif = "NbEchecConsecutifConnection";

	// Liste des variables contenants les differents messages de l'application
	public static String messageVerifSession = "Votre session a expirée. Il faut vous reconnecter depuis la page d'accueil.";

	public static String messageVerifUtilisateur = "Vous n'avez pas les droits nécessaires pour accéder é cette page.";

	public static String messageExcDoMain = "Echec de l'application a cause d'une exception sur DoMain().";

	public static String messageEchecGeneral = "Echec géneral de l'application !!! .";

	public static String messageCIntegrite = "Une contrainte d'integrité n'est pas respectée.";

	public static String messageEchecEcritureBase = "Echec lors de l'écriture dans la base.";
	
	public static String messageClefPromaireDouble = "Ce code est déjé utilisé";	

	public static String messageEchecFormat = "Echec lors de la vérification des formats.";

	public static String messageEchecServlet = "Echec de la servlet.";

	public static String messageEchecLogin = "Votre login ou votre mot de passe est incorrect.";

	public static String messageEchecIdentification = "Vous devez vous identifier.";

	public static String messageEchecDateInvalide = "Une date n'est pas correctement remplie.";

	public static String messageLogin = "Login : ";

	public static String messageRaisonSociale = ", raison sociale : ";

	public static String messageEchecTypeFichierUpload = "Le type de fichier est incorrect.";

	public static String messageChampsManquants = "Vous n'avez pas saisi tous les champs obligatoires.";

	public static String messageImputationInexistante = "L'imputation-section que vous avez saisie n'existe pas.";

	public static String messageImputationExisntante = "L'imputation-section que vous voulez supprimer est encore utilisée.";

	public static String messageRueExistante = "La rue que vous voulez supprimer est encore utilisée.";

	public static String messageConfirmationSuppression = "Etes-vous sér de vouloir supprimer cet élément?";

	public static String messageEchecFormatDate = "La date saisie n'est pas valide, le format n'est pas de type JJ/MM/AAAA ou la date n'existe pas dans le calendrier gregorien (ex le 30/02/2003).";

	public static String messageEchecClef = "Attention, tentative de mettre une date Analogon en clef de la table dans Variable1[2][0].";

	public static String messageErreurCISuppressionRedevable = "La suppression de ce redevable ne respecte pas les contraintes d'intégrités.";

	public static String messageErreurCISuppressionEmplacement = "La suppression de cet emplacement ne respecte pas les contraintes d'intégrités.";

	public static String messageErreurCISuppressionsSerieTicket = "La suppression de cette serie ne respecte pas les contraintes d'intégrités.";

	public static String messageErreurArreteJournalier = "Il n'est pas possible de modifier ce paiement car l'arrété journalier a été fait.";

	public static String messageQuittanceExiste = "Ce numéro de quittance existe déja parmi les quittances créées aujourd'hui pour ce secteur.";

	public static String messageQuittanceExisteDeja = "Ce numéro de quittance existe déja parmi les quittances créées cette année mais pour un autre jour.";

	public static String messageTimbBarme = "Ce baréme existe déjé.";

	public static String messageTrensfertInterdit = "Ce transfert ne peut pas étre effectué car ce redevable pas soldée tous ses paiements.";

	public static String messageTransfertImpossibleDateActivite = "Impossible d'effectuer ce transfert, car un emplacement actif existe déja pour ce type d'emplacement, ce secteur et ce code d'emplacement.";

	public static String messageErreurVoirie = "Choisir une des deux cases : Droit de premier établissement ou Taxe annuelle.";

	public static String messageEchecBeanAffichage = "Echec du bean d'affichage.";

	public static String messageImpossibleCreerEmplacementRapide = "Echec de la création rapide d'emplacement, veuillez utiliser la création standard d'emplacement.";

	public static String messageImpossibleSupprimerPaiementPonctuel = "Il n'est pas possible de supprimer ce paiement ponctuel.";

	// public static String tabloCivilite[] = { "", "M.", "Mme", "Mlle", "SARL",
	// "SA", "ASSO", "EURL", "Mmes", "Mrs", "M.Mme", "Me", "CIC", "Cie",
	// "GIE", "Etse", "SAS", "SCI", "SDF", "SEL", "Selarl", "SNC", "STE" };

	public static String tabloSecteurAfficheur[] = { "1", "50" };

	public static String tabloSecteurAmbulant[] = { "21", "22", "24" };

	public static String tabloSecteurAutobus[] = { "31" };

	public static String tabloSecteurConvention[] = { "41", "45", "48" };

	public static String tabloSecteurHalles[] = { "54" };

	// public static String tabloSecteurEtalage[] =
	// Config.tabloSecteurEtalage;//{"61","62","63","64"};
	// public static String tabloSecteurODP[] =
	// Config.tabloSecteurODP;//{"91","92","93"};
	// public static String tabloSecteurKiosque[] =
	// Config.tabloSecteurKiosque;//{"71","72","73","74"};
	public static String tabloSecteurMarche[] = { "80", "81", "82", "83", "84",
			"85", "86", "87", "88", "89" };

	public static String tabloSecteurTaxi[] = { "29" };

	public static String tabloSecteurVoirie[] = { "10" };

	// Liste des périodes
	public static String tabloPeriode[] = { "J", "S", "M", "A", };

	// Liste de type Timbre/Vignette
	public static String tabloTypeTimbre = "Timbre";

	public static String tabloTypeVignette = "Vignette";

	public static String tabloTypeTimbreVignette[] = { "Timbre", "Vignette" };

	// Liste de secteur pour serie de ticket
	public static String tablosecteurSerie[] = { "1", "2", "3", "4", "5", "6" };

	// Liste de code baréme pour unités de travail
	public static String tabloUniteDeTravail[] = { "UNITE","M","Mé","KG","FORFAIT" };

	public static String tabloDureeUnitaireBareme[] = { "AN", "TRIMESTRE",
			"MOIS", "QINZAINE", "SEMAINE", "WEEKEND", "JOUR", "12h", "-" };

	// Liste des roles
	// public static String tabloRole[] = Config.tabloRole;
	// {
	// /*"A",*/"B","C","D","E",/*"F",*/"G","H","J","M","O","P","Q","T","V","Y","Z"
	// };
	// public static String tabloRoleEmpl[] = Config.tabloRoleEmpl;
	// {
	// /*"A - Afficheur ",*/"B - Ambulant","C - Convention","D - Autobus","E -
	// Etalage",/*"F - Halle",*/"G - Kiosque","H - Vignette","J - Taxi","M -
	// Marche Halle","O - Ordre de recettes","P - ODP","Q - Ticket","T -
	// Timbre","V - Voirie","Y - Chapitre","Z - Transfert"
	// };

	// Liste des roles attribuent a un emplacments
	// public static String nomRoleEmplAfficheur="A";
	public static String nomRoleEmplAmbulant = "B";

	public static String nomRoleEmplAutobus = "D";

	public static String nomRoleEmplConvention = "C";

	public static String nomRoleEmplEtalage = "E";

	// public static String nomRoleEmplHalle="F";
	public static String nomRoleEmplHalle = "M";

	public static String nomRoleEmplKiosque = "G";

	public static String nomRoleEmplMarche = "M";

	public static String nomRoleEmplTaxi = "J";

	public static String nomRoleEmplVoirie = "V";

	public static String nomRoleEmplODP = "P";
	
	
	//Since 2.0
	public static String nomRoleEmplDDV = "DDV";
    //Since 2.0
	public static String nomRoleEmplTAV = "TAV";
    //Since 2.0
	public static String nomRoleEmplPUBLICITE = "PUBLICITE";
    //Since 2.0
	public static String nomRoleEmplINFRACTION = "P";	
	



	

	public static String nomRoleEmplOrdreRecette = "O";

	public static String nomRoleTimbre = "T";

	public static String nomRoleVignette = "H";

	public static String nomRoleTichet = "Q";

	public static String nomRoleTransfert = "Z";

	public static String tableauSecteur[] = { "-", "1", "2", "3", "4", "5", "6" };

	// Liste de secteur pour serie de ticket
	public static String tabloPeriodePaiement[] = { "0", "1", "2", "3", "4" };

	// Liste de secteur pour serie de ticket
	public static String tabloPeriodePaiemenAffichage[] = { "", "1", "2", "3",
			"4" };

	// Liste des variables html pour le moteur de recherchev des parametres
	public static String varHtmlSujetBanque = "banque";

	public static String varHtmlSujetBareme = "bareme";

	public static String varHtmlSujetActivite = "activiteprofession";

	public static String varHtmlSujetImputation = "imputation";

	public static String varHtmlCode = "code";

	public static String varHtmlLIbelle = "libelle";

	public static String varHtmlAnnee = "annee";

	public static String varHtmlSection = "section";

	// Liste de parametre Html divers
	public static String varHtmlTypeOperation = "typeOperation";

	// Liste des variables html pour les utilisateurs
	public static String varHtmlNumeroUser = "numerouser";

	public static String varHtmlLogin = "login";

	public static String varHtmlPassword = "password";

	public static String varHtmlEnsembleUtilisateur = "ensembleutilisateur";

	public static String varHtmlNom = "nom";

	public static String varHtmlPrenom = "prenom";

	public static String varHtmlValide = "valide";

	public static String varHtmlTempsInterSession = "tempsInterSession";

	public static String varHtmlDatePassword = "datePassword";

	public static String varHtmlDelaiPassword = "delaiPassword";

	public static String varHtmlRemarque = "remarque";

	// liste des variables html pour les emplacements
	public static String varHtmlNumRue = "numRue";

	public static String varHTMLNumeroEmplacementGeneral = "numEmplacment";

	// public static String varHTMLNumeroRedevable="numemplredevable";
	public static String varHTMLNumRedevable = "numRedevable";

	public static String varHTMLCodeTiersRedevable = "codetiersredevable";

	public static String varHTMLResponsableRedevable = "responsableredevable";

	public static String varHTMLDateFinActivite = "datefinactivite";

	public static String varHTMLNumeroEmplSpecialise = "numemplspecialise";

	public static String varHTMLNumeroEmplacementAutobus = "numeroEpmlAutobus";

	public static String varHTMLNumeroEmplacementMarche = "numeroEpmlMarche";

	public static String varHTMLNumeroEmplacementODP = "numeroemplodp";

	public static String varHTMLNumeroEmplacementAmbulant = "numeroEpmlAmbulant";

	public static String varHTMLNumeroEmplacementConvention = "numeroEpmlCnovention";

	public static String varHTMLNumeroEmplacementHalles = "numeroEpmlHalles";

	public static String varHTMLNumeroEmplacementKiosque = "numeroEpmlKiosque";

	public static String varHTMLNumeroEmplacementEtalage = "numeroEpmlEtalage";

	public static String varHTMLNumeroEmplacementTaxi = "numeroEpmlTaxi";

	public static String varHTMLNumeroEmplacementAfficheur = "numeroEpmlAfficheur";

	public static String varHTMLNumeroEmplacementVoirie = "numeroEpmlVoirie";

	public static String varHTMLNumeroEmplGeneral = "numeroEmplGeneral";

	public static String varHTMLCodeProfession = "codeProfession";

	public static String varHTMLRaisonSocial = "raisonSocial";

	public static String varHTMLCodeSecteur = "codeSecteur";

	public static String varHTMLCodeEmplacement = "codeEmplacement";

	public static String varHTMLCodeType = "codeType";

	public static String varHTMLCommentaire = "commentaire";

	public static String varHTMLCodeVoie = "codeVoie";

	public static String varHTMLAdresse1 = "adresse1";

	public static String varHTMLAdresse2 = "adresse2";

	public static String varHTMLCodePostal = "codePostal";

	public static String varHTMLVille = "ville";

	public static String varHTMLNumTel = "numTel";

	public static String varHTMLNumfax = "numfax";

	public static String varHTMLNumPortable = "numPortable";

	public static String varHTMLEmail = "email";

	public static String varHTMLDateDebutActivite = "dateDebutActivite";

	public static String varHTMLEmplDateFinActivite = "dateFinActivite";

	public static String varHTMLCodeInscription = "codeInscription";

	public static String varHTMLNumInscription = "numInscription";

	public static String varHTMLDateInscription = "dateInscription";

	public static String varHTMLAdresse3 = "adresse3";

	public static String varHTMLCompagnie = "compagnie";

	public static String varHTMLNombredeCars = "nombredeCars";

	public static String varHTMLlingneDeCars = "lignesDeCars";

	public static String varHTMLNomTournee = "nomTournee";

	public static String varHTMLNumCatrePro = "numCatrePro";

	public static String varHTMLCertifHygiene = "numCertifHygiene";

	public static String varHTMLcertifPompier = "numCertifPompier";
	
	

	public static String varHTMLDateObtentionCertPro = "dateObtentionCertPro";

	public static String varHTMLDateObtentionCertHygiene = "dateObtentionCertHygiene";

	public static String varHTMLDateObtentionCertPompier = "dateObtentionCertPompier";

	public static String varHTMLMarqueVehicule = "marqueVehicule";

	public static String varHTMLTypeVehicule = "typeVehicule";

	public static String varHTMLImmatriculation = "immatriculationVehicule";

	public static String varHTMLCompagnieAssurance = "compagnieAssurance";

	public static String varHTMLTypeRecherche = "typeRecherche";

	public static String varHTMLOptionTransfert = "optionTransfert";

	public static String varHtmlPeriode = "periode";

	public static String varHtmlNbPlacesAssises = "nbPlacesAssises";

	public static String varHtmlNbPlacesDebouts = "nbPlacesDebouts";

	public static String varHtmlNumeroDeTaxi = "numDeTaxi";

	public static String varHtmlImmatriculationTaxi = "immatTaxi";

	public static String varHtmlNumCarteGrise = "numCarteGrise";

	public static String varHtmlNumAssurance = "numAssurance";

	public static String varHtmlDateAutorisationTravaux = "dateAutorisation";

	public static String varHtmlObjetTravaux = "objetTravaux";

	public static String varHtmlObjetAutorisationVoirie = "objAutorisationV";

	public static String varHtmlNatureVoirie = "natureVoirie";

	public static String varHtmlDateTransfert = "dateTransfert";

	// Liste des variables contenant le nom des variables de session
	public static String varSessionDerniereReq = "DerniereReq";

	public static String varSessionEtatSession = "etatSession";

	public static String varSessionLastSession = "lastSession";

	public static String varSessionTempsInterSession = "tis";

	public static String varSessionTypeUtilisateur = "typeUtilisateur";

	public static String varSessionNumeroUtilisateur = "numeroUtilisateur";

	public static String varSessionNomFichierUpload = "nomFichierUpload";

	public static String varSessionSupprimerForTransfert = "varSessionSupprimerFormTransfert";

	// Liste des variables contenant le nom des pages jsp,html,servlet
	public static String pageJspLogin = "login.jsp";

	public static String pageJspErreur = "afficheErreur.jsp";

	// public static String pageJspCreationUtilisateur = "creerLogin.jsp";
	public static String pageJspRapportActivite = "RapportActivite.jsp";

	public static String pageJspGestionUtilisateur = "GestionUtilisateur.jsp";

	public static String pageServletEntree = "entree";

	public static String pageServletRegieGestionDiverses = "regiegestiondiverses";

	public static String pageJspListeEmplacementRedevable = "listeEmplacementRedevable.jsp";

	public static String pageServletGestionEmplacement = "preparergestionemplacement";

	public static String pageServletListeUtilisateur = "listeUtilisateur.jsp";

	public static String pageJSPEmplGestionAfficheur = "empl_gestion_emplacementAfficheur.jsp";

	public static String pageJSPEmplGestionAmbulant = "empl_gestion_emplacementAmbulant.jsp";

	public static String pageJSPEmplGestionAutobus = "empl_gestion_emplacementAutobus.jsp";

	public static String pageJSPEmplGestionConvention = "empl_gestion_emplacementConvention.jsp";

	public static String pageJSPEmplGestionEtalage = "empl_gestion_emplacementEtalage.jsp";

	public static String pageJSPEmplGestionHalles = "empl_gestion_emplacementHalles.jsp";

	public static String pageJSPEmplGestionKiosque = "empl_gestion_emplacementKiosque.jsp";

	public static String pageJSPEmplGestionMarche = "empl_gestion_emplacementMarche.jsp";

	public static String pageJSPEmplGestionODP = "empl_gestion_emplacementodp.jsp";

	public static String pageJSPEmplGestionTaxi = "empl_gestion_emplacementTaxi.jsp";

	public static String pageJSPEmplGestionVoirie = "empl_gestion_emplacementVoirie.jsp";

	public static String pageJSPEmplGestionRedevable = "empl_gestion_redevable.jsp";

	public static String pageJSPEmplRechercheVoie = "empl_recherche_voie.jsp";

	public static String pageJSPGestionTransfert = "gestion_transfert.jsp";

	public static String pageJSPAcomptePaiement = "empl_acompte_paiement.jsp";

	public static String pageJSPTicketSecteur = "ticket_secteur.jsp";

	public static String pageJSPTicketAffectation = "ticket_affectation.jsp";

	public static String pageJSPChoixBatchTraitement = "empl_choix_batch.jsp";

	public static String pageJSPBatchTraitement = "empl_prefacturation.jsp";

	public static String pageJSPTicketPaiementTicket = "ticket_paiementTicket.jsp";

	public static String pageJSPTicketTimbreVignette = "ticket_timbre_vignette.jsp";

	public static String pageJSPRechercheTicketTimbreVignette = "ticket_recherche_timbre_vignette.jsp";

	public static String pageJSPModificationPaiement = "empl_modification_paiement.jsp";

	public static String pageJSPJourneaux = "batch_journeaux.jsp";

	public static String pageJSPExport = "batch_Excel_export.jsp";

	public static String pageJSPRecapElemFacturation = "empl_recap_element_facturation.jsp";

	public static String pageJSPEmplSaisieElemFacturation = "empl_saisie_element_facturation.jsp";

	public static String pageJSPResultatRechercheRedevable = "empl_resultat_recherche_redevable.jsp";

	public static String pageJSPBatchDivers = "batch_divers.jsp";

	// SousUri
	// public static String sousUriHtmlIndex_recherche =
	// Config.getPreUrlHtml()+"index_recherche.htm";
	// public static String sousUriJspMonStock =
	// Config.getPreUrlJbin()+ConfVariable.pageServletEntree+"?action=resultat_recherche_vehicule.jsp&mesVehicules=y&kiloDebut=&departement=&kiloFin=&prixDebut=&numVehicule=&energieVehicule=&anneeDebut=&numModele=&etatVehicule=&prixFin=&numMarque=&action=&ldCouleur=&tri=date&libModele=&anneeFin=&libMarque=&typeVehicule=";
	// public static String sousUriJspListeEnchere =
	// Config.getPreUrlJbin()+ConfVariable.pageServletEntree+"?action=liste_enchere.jsp&typeListeEnchere=client";
	public static String varHtmlPrefixVoie = "prefixe";

	public static String varHtmlChargeVariableHtml = fr.analogon.r2t.util.web.VariableHtml.NOMVARHTMLVARIABLEHTML;

	// Liste des variables contenant les quatre type d'utilisateur
	public static String typeUtilisateurAdmin = "ADMINISTRATEUR";

	public static String typeUtilisateurSecretaires = "Secrétaires";

	public static String typeUtilisateurInspecteurs = "Agents";

	public static String typeUtilisateurRegisseurs = "REGISSEUR";

	// public static String typeUtilisateurConsultation = "CONSULTATION";
	public static String tabloTypeUtilisateur[] = { typeUtilisateurSecretaires,
			typeUtilisateurInspecteurs, typeUtilisateurRegisseurs,
			typeUtilisateurAdmin };

	// Liste des variables des noms des emplacments
	/*
	 * public static String nomEmplAfficheur = Config.nomEmplAfficheur;//
	 * "Afficheur"; //trimestriel public static String nomEmplAmbulant =
	 * Config.nomEmplAmbulant;//"Ambulant"; //trimestriel public static String
	 * nomEmplAutobus = Config.nomEmplAutobus;//"Autobus"; // trimestriel public
	 * static String nomEmplConvention =
	 * Config.nomEmplConvention;//"Convention"; // trimestriel public static
	 * String nomEmplEtalage = Config.nomEmplEtalage;//"Etalage"; //annuel
	 * public static String nomEmplHalle = Config.nomEmplHalle;//"Halle";
	 * //trimestriel public static String nomEmplKiosque =
	 * Config.nomEmplKiosque;//"Kiosque"; //trimestriel public static String
	 * nomEmplMarche = Config.nomEmplMarche;//"Marche"; //trimestriel public
	 * static String nomEmplTaxi = Config.nomEmplTaxi;//"Taxi"; // trimestriel
	 * public static String nomEmplOccupationDuDomainePublique =
	 * Config.nomEmplOccupationDuDomainePublique;//"ODP"; //mensuel public
	 * static String nomEmplVoirie = Config.nomEmplVoirie;//"Voirie";
	 * //trimestriel public static String nomEmplOrdreRecette =
	 * Config.nomEmplOrdreRecette;//"Ordre de recette"; //ponctuel avec impayes
	 * trimestriel public static String nomEmplOrdreRecetteTransfert =
	 * Config.nomEmplOrdreRecetteTransfert;//"Transfert"; //ponctuel avec
	 * impayes trimestriel
	 */
	/** Vrai nom des emplacements trimestriel possible pour les factures */
	// public static String tabloNomEmplacementTrimestriel[]
	// =Config.tabloNomEmplacementTrimestriel;// {nomEmplAmbulant,
	// nomEmplAutobus, nomEmplConvention, nomEmplEtalage, nomEmplHalle,
	// nomEmplKiosque, nomEmplMarche, nomEmplTaxi, nomEmplVoirie};
	/*
	 * public static String tabloNomEmplacementLibelle[] =
	 * Config.tabloNomEmplacementLibelle;// {nomEmplAfficheur, nomEmplAmbulant,
	 * nomEmplAutobus, nomEmplConvention, nomEmplEtalage, nomEmplHalle,
	 * nomEmplKiosque, nomEmplMarche,
	 * nomEmplOccupationDuDomainePublique,nomEmplTaxi, nomEmplVoirie}; public
	 * static String tabloNomEmplacementNomReel[] =
	 * Config.tabloNomEmplacementNomReel;// {nomEmplAfficheur, nomEmplAmbulant,
	 * nomEmplAutobus, nomEmplConvention, nomEmplEtalage, nomEmplHalle,
	 * nomEmplKiosque, nomEmplMarche,
	 * nomEmplOccupationDuDomainePublique,nomEmplTaxi, nomEmplVoirie}; public
	 * static String tabloNomEmplacementPourElemFactLibelle[] =
	 * Config.tabloNomEmplacementPourElemFactLibelle;//
	 * {nomEmplOrdreRecette,nomEmplOrdreRecetteTransfert,nomEmplAmbulant,
	 * nomEmplAutobus, nomEmplConvention, nomEmplEtalage, nomEmplHalle,
	 * nomEmplKiosque, nomEmplMarche,
	 * nomEmplOccupationDuDomainePublique,nomEmplTaxi, nomEmplVoirie}; public
	 * static String tabloNomEmplacementPourElemFactNomReel[] =
	 * Config.tabloNomEmplacementPourElemFactNomReel;//
	 * {nomEmplOrdreRecette,nomEmplOrdreRecetteTransfert,nomEmplAmbulant,
	 * nomEmplAutobus, nomEmplConvention, nomEmplEtalage, nomEmplHalle,
	 * nomEmplKiosque, nomEmplMarche,
	 * nomEmplOccupationDuDomainePublique,nomEmplTaxi, nomEmplVoirie}; public
	 * static String tabloNomEmplacementPourRecherchePaiementLibelle[] =
	 * Config.tabloNomEmplacementPourRecherchePaiementLibelle;//
	 * {nomEmplOrdreRecette,nomEmplOrdreRecetteTransfert, nomEmplAfficheur,
	 * nomEmplAmbulant, nomEmplAutobus, nomEmplConvention, nomEmplEtalage,
	 * nomEmplHalle, nomEmplKiosque, nomEmplMarche, nomEmplTaxi, nomEmplVoirie};
	 * public static String tabloNomEmplacementPourRecherchePaiementNomReel[] =
	 * Config.tabloNomEmplacementPourRecherchePaiementNomReel;//
	 * {nomEmplOrdreRecette,nomEmplOrdreRecetteTransfert, nomEmplAfficheur,
	 * nomEmplAmbulant, nomEmplAutobus, nomEmplConvention, nomEmplEtalage,
	 * nomEmplHalle, nomEmplKiosque, nomEmplMarche, nomEmplTaxi, nomEmplVoirie};
	 * public static String tabloNomEmplacementPourRdOrigineLibelle[] =
	 * Config.tabloNomEmplacementPourRdOrigineLibelle;//
	 * {nomEmplAmbulant,nomEmplEtalage,nomEmplHalle, nomEmplKiosque,
	 * nomEmplMarche, nomEmplTaxi}; public static String
	 * tabloNomEmplacementPourRdOrigineNomReel[] =
	 * Config.tabloNomEmplacementPourRdOrigineNomReel;//
	 * {nomEmplAmbulant,nomEmplEtalage,nomEmplHalle, nomEmplKiosque,
	 * nomEmplMarche, nomEmplTaxi};
	 */

	// Valeur possible de la variable html TypeRecherche
	public static String valTypeRecherchePaiementTimbreCreer = "creerPaiementTimbre";

	public static String valTypeRechercheCreerOrdreRecette = "creerOrdreRecette";

	public static String valTypeRechercheCreerOrdreRecetteTransfert = "creerOrdreRecetteTransfert";

	public static String valTypeRecherchePaiementVignetteCreer = "paiementVignetteCreer";

	public static String valTypeRecherchePaiementVignette = "paiementVignette";

	public static String valTypeRecherchePaiementElementFacturation = "elementFacturation";

	public static String valTypeRecherchePaiement = "paiement";

	public static String varChaineinterroEF = "chaineIntroPourEF";

	// Liste des variable y n
	public static String varValidY = "y";

	public static String varValidN = "n";

	public static String varValideTrue = "true";

	public static String varValideFalse = "false";

	// Liste des variables de parametre
	public static String varParametreChoix = "choix";

	public static String varParametreAction = "action";

	public static String varParametrePassword = "pass";

	public static String varParametreNumeroUser = "numeroUser";

	public static String varParametreLogin = "login";

	public static String varParametreNom = "nom";

	public static String varParametrePrenom = "prenom";

	public static String varParametreValide = "valide";

	public static String varParametre = "ensembleutilisateur";

	public static String varParametreTempsInterSession = "tempsInterSession";

	public static String varParametreDatePassword = "datePassword";

	public static String varParametreDelaiPassword = "delaiPassword";

	public static String varParametreRemarque = "remarque";

	public static String vartrensfertRdOrigine = "trensfertRdOrigine";

	public static String vartrensfertRdDestinataire = "trensfertRdDestinataire";

	public static String varRole = "role";

	public static String varOptionTrensfertOrigine = "origine";

	public static String varOptionTrensfertDistinataire = "destinataire";

	public static String varRecapitulationTransfert = "recapitulationTransfert";

	// Valeur pour la voirie
	public static String tableauValeurTypeVoirie[] = { "C", "E", "P" };

	// valeur pour la valeur leChoix
	public static String valVariableChoixModif = "modifier";

	public static String valVariableChoixAjou = "ajouter";

	public static String valVariableChoixCreer = "creer";

	public static String valVariableChoix = "choix";

	// Valeurs pour le moteur de recherche des redevable
	public static String varHtmlNomRedevable = "nomRedevable";

	public static String varHtmlPrenomRedevable = "prenom";

	public static String varHtmlCivilite = "civilite";

	public static String varHtmlNomVoie = "nom";

	public static String varHtmlNomVoie2 = "nomVoie2";

	// Variable pour le batch
	public static String varHtmlDateArrete = "dateArrete";

	// Variable pour les paiement
	public static String varHtmlNumPaiement = "numeroPaiement";

	// liste des variables pour les series
	public static String varHtmlNumSousSerieTicket = "numSousSerieTicket";

	public static String varHtmlNumeroSerie = "numeroSerie";

	public static String varHtmlCodeSerie = "codeSerie";

	public static String varHtmlExercice = "exercice";

	public static String varHtmlDateCreation = "dateCreation";

	public static String varHtmlCodeBareme = "codeBareme";

	public static String varHtmlDateSecteur = "dateSecteur";

	public static String varHtmlSecteur = "secteur";

	public static String varHtmlValeurTicket = "valeurTicket";

	// liste des variables pour les timbres et vignette
	public static String varHtmlNumTimbreVignette = "numTimbreVignette";

	public static String varHtmlAnExercice = "anExercice";

	public static String varHtmlTimbreCodeBareme = "codeBareme";

	public static String varHtmlQuantite = "quantite";

	public static String varHtmlTypeTV = "typeTV";

	public static String tableauValeurTicket[] = {
			"5,00 - Tickets é 5 euro - 5000", "2,00 - Tickets é 2 euro - 5100",
			"1,00 - Tickets é 1 euro - 5200",
			"0,50 - Tickets é 50 cents - 5300",
			"0,20 - Tickets é 20 cents - 5350",
			"0,05 - Tickets é 5 cents - 5400", "0,01 - Tickets é 1 cent - 5500" };

	// liste des mode de paiement
	public static String modePaiementCBI = "CBI";

	public static String modePaiementCBS = "CBS";

	public static String BanqueTPREG = "TPREG";

	public static String sommeLimitePaiemnt = "5000";

	// Liste des noms des beans de session
	public static String beanSessionNomUnRedevable = "unRedevable";

	public static String beanSessionNomCRRedevable = "caracteristiqueRechercheRedevable";

	public static String beanSessionNomBDiversRemplRedevable = "bRemplaceRedevable";

	// Date des débuts et fins de période
	public static String tableauDateDebutPeriode[] = { "0101", "0401", "0701",
			"1001" };

	public static String tableauDateFinPeriode[] = { "0331", "0630", "0930",
			"1231" };

	// nom de repertoires
	public static String nomRepertoireFacture = "factures";

	public static String nomRepertoireJourneaux = "journeaux";

	public static String nomRepertoireExport = "exports";

	public static String nomRepertoireArretes = "arretes";

	public static String nomRepertoireWEBINFTemplatesApp = "templatesApp";

	public static String nomRepertoireZipArrete = "repzip";

	public static String nomRepertoirePhoto = "photo";

	public static String nomRepertoireWEBINFMiseAjourR2TMobile = "miseajourr2tmobile";

	public static String nomRepertoireImageApp = "imagesapp";

	// nom de fichiers
	public static String nomFichVentilationArticle = "ventilationBareme";

	public static String nomFichVentilationEmplacement = "ventilationEmplacement";

	public static String nomFichCompareVArtiVEmpl = "compareVBaremeVEmpl";

	public static String nomFichBordereaux = "bordereaux";

	public static String nomFichFacture = "facture";

	public static String nomFichAuthorisationODP = "autorisation_odp";

	public static String nomFichRelance1 = "relance1";

	public static String nomFichRelance2 = "relance2";

	public static String nomFichRelance3 = "relance3";

	public static String nomFichImpayes = "impayes";

	public static String nomFichFilien = "filien";

	public static String nomFichLogImp = "logImpaye";

	public static String nomFichAuthorisationFilienPlaineCo93Compta = "filienComptaPCo";

	public static String nomFichAuthorisationFilienPlaineCo93ComptaFactice = "filienComptaPCoFactice";

	public static String nomFichImpayesFactice = "impayesFactice";

	public static String nomFichFilienImpayesFactice = "filienFactice";

	public static String nomFichFilienFactureFactice = "filienFactureFactice";

	public static String nomFichLogFilienFactureFactice = "logFilienFactureFactice";

	public static String nomFichFilienFacture = "filienFacture";

	public static String nomFichLogFilienFacture = "logFilienFacture";

	public static String nomFichLogImpFactice = "logImpayeFactice";

	public static String nomFichFilienPrelevFactice = "filienPrelevFactice";

	public static String nomFichLogFilienPrelevFactice = "logFilienPrelevFactice";

	public static String nomFichFilienPrelev = "filienPrelev";

	public static String nomFichLogFilienPrelev = "logFilienPrelev";

	public static String nomFichEmplacementSansEF = "EmplSansEF";

	public static String nomFichEmplacementACommentaire = "EmplACommentaire";

	public static String nomFichEmplacementNonPayes = "EmplNonPayes";

	public static String nomFichFacturesFactices = "FactureFactice";

	public static String nomFichBordereauxFactices = "BordereauxFactice";

	public static String nomFichieExportXls = "exportr2t";

	public static String nomArchiveArreteZip = "ArchArreteODPReel";

	public static String nomArchiveArreteZipFactice = "ArchArreteODPFactice";

	public static String nomFichierArreteReelBatch = "arreteODPBatchReel";

	public static String nomFichierArreteFacticeBatch = "arreteODPBatchFactice";

	public static String nomFichierArreteReelPonctuel = "arreteODPReelPonctuel";

	public static String nomFichierArreteFacticePonctuel = "arreteODPFacticePonctuel";

	public static String nomFichierexportComptaPlaineCoSD93 = "comptapcosd93";

		public static String nomRepertoireHtml = "empl";

}