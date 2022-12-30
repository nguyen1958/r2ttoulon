package fr.analogon.r2t.util.web;

/**
 * Cette classe contient differentes constantes de la bibliotheque WEB, tel que
 * des valeur de messages ou des nom de variables de session pour les packages
 * fr.analogon.r2t.util.web.*. . Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0
 */
public class ConstantesWeb {

	// Liste des variables contenants les differents messages de l'application
	public static String messageVerifSessionDefaut = "Votre session a expirée. Il faut vous reconnecter depuis la page d'accueil.";

	public static String messageVerifUtilisateurDefaut = "Vous n'avez pas les droits nécessaires pour accéder é cette page.";

	public static String messageExcDoMainDefaut = "Echec de l'application a cause d'une exception sur DoMain().";

	public static String messageEchecGeneralDefaut = "Echec géneral de l'application.";

	public static String messageCIntegriteDefaut = "Operation impossible car une contrainte d'integrité n'est pas respectée.";

	public static String messageEchecEcritureBaseDefaut = "Echec de l'écriture dans la base.";

	public static String messageEchecFormatDefaut = "Echec de la vérification des formats.";

	public static String messageEchecServletDefaut = "Echec de la servlet.";

	public static String messageEchecLoginDefaut = "Votre login ou votre mot de passe est incorrect.";

	public static String messageEchecIdentificationDefaut = "Vous devez vous identifier.";

	public static String messageEchecDateInvalideDefaut = "Une date n'est pas correctement remplie.";

	public static String messageEchecTypeFichierUploadDefaut = "Operation impossible car le type de fichier incorrect.";

	public static String messageEchecFormatDate = "La date saisie n'est pas valide, le format n'est pas de type JJ/MM/AAAA ou la date n'existe pas dans le calendrier gregorien (ex le 30/02/2003).";

	public static String messageAccueilLoginDefaut = "Veuillez taper votre login et votre mot de passe";

	public static String messageLoginDefaut = "LoginService :";

	public static String messagePasswordDefaut = "Mot de passe :";

	public static String messageEchecBeanAffichage = "Echec bean d'affichage.";

	// Variables par defaut pour le formulaire de login
	/** Parametre action utilisé par défaut par le formulaire de login */
	public static String formLoginActionDefaut = "./authentification";

	/** Texte par defaut utilisé pour le bouton du formuliare de login */
	public static String formLoginTexteBoutonDefaut = "Valider";

	public static String formLoginTargetDefaut = "";

	public static String formLoginWidthDefaut = "100%";

	public static String formLoginBorderDefaut = "0";

	public static String formLoginCellspacingDefaut = "0";

	public static String formLoginCellpaddingDefaut = "0";

	public static String formLoginAlignDefaut = "center";

	public static String formLoginChampsCacheDefaut = "";

	//

	// Liste des variables contenant le nom des variables de session
	public static String nomSessionMessLoginDefaut = "messLogin";

	public static String nomSessionMessageErreurDefaut = "messageErreur";

	public static String nomSessionUtilisateurDefaut = "utilisateur";

	// Liste des variables contenant le nom des pages jsp,html,servlet
	public static String pageJspLoginDefaut = "login.jsp";

	public static String pageJspErreurDefaut = "afficheErreur.jsp";

	public static String pageJspCreationUtilisateurDefaut = "GestionLogin.jsp";

	public static String pageServletEntreeDefaut = "entree";

	public static String pageJspIndexApplication = "index.jsp";

	// Liste des variables contenant les quatre type d'utilisateur
	public static String typeUtilisateurGestionnaire = "Gestionnaire";

	public static String typeUtilisateurConsultation = "Consultation";

	public static String tabloTypeUtilisateur[] = {
			typeUtilisateurGestionnaire, typeUtilisateurConsultation };

	// Liste des variable y n
	public static String valYes = "y";

	public static String valNo = "n";

	// Liste des variables de parametre HTMl
	public static String varParametreChoix = "choix";

	public static String varParametreAction = "action";

	public static String varParametreLogin = "login";

	public static String varParametrePassword = "pass";

	// valeur pour la valeur des parametres HTML de Choix
	public static String valVariableChoixModif = "modifier";

	public static String valVariableChoixAjout = "ajouter";

	// Liste des valeurs par defaut de Configuration web lie é IConfig
	public static boolean unixDefaut = false;

	public static String nomFichierTraceWinDefaut = "R2T_Trace.log";

	public static String nomFichierTraceUnixDefaut = "R2T_Trace.log";

	public static String pathHtmlUnixTraceDefaut = "/DISQUE2/WEB/temporaire/";

	public static String pathHtmlWinTraceDefaut = "D:\\jakarta-tomcat-5.0.28\\webapps\\r2t\\WEB-INF\\logs";

	// fin fichier de trace et Exception

	// Debut variable pour IConfig
	public static String[][] tabAccesReadDefaut = { {
			typeUtilisateurGestionnaire, "" } };

	public static String[][] tabAccesWriteDefaut = { {
			typeUtilisateurGestionnaire, "" } };

	public static String[][] tabAccesEnsembleDefaut = { {
			typeUtilisateurGestionnaire, "" } };

	public static String[] tabAccesEnsemblePublicDefaut = { typeUtilisateurGestionnaire };

	public static String hostDefaut = "";

	public static String userDefaut = "";

	public static String passwordDefaut = "";

	public static String dbNameDefaut = "";

	public static String portDefaut = "3306";

	public static int delaisPasswordDefaut = 7776000;

	public static int tisDefaut = 3600;

	public static String valideDefaut = "true";

	public static boolean logFileFlagDefaut = false;

	public static boolean actifDefaut = false;

	public static boolean noteExceptionDefaut = true;

	public static boolean ecrireDebugDefaut = true;

	// fin varaible pour Iconfig

	// preUrl
	public static String preUrlDataUnixDefaut = "./data/";

	public static String preUrlDataWinDefaut = "./data/";

	public static String preUrlHtmlUnixDefaut = "./";

	public static String preUrlHtmlWindowsDefaut = "./";

	public static String preUrlJbinWindowsDefaut = "./";

	public static String preUrlJbinUnixDefaut = "./";

	public static String preUrlJspReqDispacherWindowsDefaut = "/jsp/";

	public static String preUrlJspReqDispacherUnixDefaut = "/jsp/";

	/** chemin relatif depuis les jsp ou les html */
	public static String urlImageJspHtml = "./images/";

	/** chemin relatif depuis une servlet ou une jsp emise par une servlet. */
	public static String urlImageServlet = "./images/";

	// page
	public static String pageAccueilDefaut = "page_accueil.htm";

	/** La chaine par défaut utilisé pour remplir des champs obligatoires */
	public static String chaineParDefautDefaut = "-";

	// Nom des colonnes pour la table utilisateur du module de données
	// d'authentification
	public static String cnUtilisateur_numeroUserDefaut = "numeroUser";

	public static String scnUtilisateur_numeroUserDefaut = "numeroUser";

	public static String cnUtilisateur_loginDefaut = "login";

	public static String scnUtilisateur_loginDefaut = "login";

	public static String cnUtilisateur_passwordDefaut = "password";

	public static String scnUtilisateur_passwordDefaut = "password";

	public static String cnUtilisateur_ensembleUtilisateurDefaut = "ensembleUtilisateur";

	public static String scnUtilisateur_ensembleUtilisateurDefaut = "ensembleUtilisateur";

	public static String cnUtilisateur_valideDefaut = "valide";

	public static String scnUtilisateur_valideDefaut = "valide";

	public static String cnUtilisateur_tempsInterSessionDefaut = "tempsInterSession";

	public static String scnUtilisateur_tempsInterSessionDefaut = "tempsInterSession";

	public static String cnUtilisateur_datePasswordDefaut = "datePassword";

	public static String scnUtilisateur_datePasswordDefaut = "datePassword";

	public static String cnUtilisateur_delaiPasswordDefaut = "delaiPassword";

	public static String scnUtilisateur_delaiPasswordDefaut = "delaiPassword";

	public static String cnUtilisateur_nomDefaut = "nom";

	public static String scnUtilisateur_nomDefaut = "nom";

	public static String cnUtilisateur_prenomDefaut = "prenom";

	public static String scnUtilisateur_prenomDefaut = "prenom";

	public static String cnUtilisateur_remarqueDefaut = "remarque";

	public static String scnUtilisateur_remarqueDefaut = "remarque";
	//

}