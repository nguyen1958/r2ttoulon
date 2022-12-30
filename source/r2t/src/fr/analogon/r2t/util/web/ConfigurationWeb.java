package fr.analogon.r2t.util.web;

/**
 * Objet utilisé pour configurer les objets de la bibliotheque, cet objet
 * utilise est configuré par defaut, . Pour pouvoir le parametre il faut le
 * faire herite par le fichier de configug du site et utilise celui-ci pour
 * changer les valeurs par defaut. . Sofien CHARFI
 * 
 * @version 2.1
 * @since 2.0
 */
public class ConfigurationWeb implements IConfig {

	// Les messages
	public static String messageVerifSession = ConstantesWeb.messageVerifSessionDefaut;

	public static String messageVerifUtilisateur = ConstantesWeb.messageVerifUtilisateurDefaut;

	public static String messageExcDoMain = ConstantesWeb.messageExcDoMainDefaut;

	public static String messageEchecGeneral = ConstantesWeb.messageEchecGeneralDefaut;

	public static String messageCIntegrite = ConstantesWeb.messageCIntegriteDefaut;

	public static String messageEchecEcritureBase = ConstantesWeb.messageEchecEcritureBaseDefaut;

	public static String messageEchecFormat = ConstantesWeb.messageEchecFormatDefaut;

	public static String messageEchecServlet = ConstantesWeb.messageEchecServletDefaut;

	public static String messageEchecLogin = ConstantesWeb.messageEchecLoginDefaut;

	public static String messageEchecIdentification = ConstantesWeb.messageEchecIdentificationDefaut;

	public static String messageEchecDateInvalide = ConstantesWeb.messageEchecDateInvalideDefaut;

	public static String messageEchecTypeFichierUpload = ConstantesWeb.messageEchecTypeFichierUploadDefaut;

	public static String messageAccueilLogin = ConstantesWeb.messageAccueilLoginDefaut;

	public static String messageLogin = ConstantesWeb.messageLoginDefaut;

	public static String messagePassword = ConstantesWeb.messagePasswordDefaut;

	//

	// debut page de LoginService
	public static String formLoginTarget = ConstantesWeb.formLoginTargetDefaut;

	public static String formLoginAction = ConstantesWeb.formLoginActionDefaut;

	public static String formLoginWidth = ConstantesWeb.formLoginWidthDefaut;

	public static String formLoginBorder = ConstantesWeb.formLoginBorderDefaut;

	public static String formLoginCellspacing = ConstantesWeb.formLoginCellspacingDefaut;

	public static String formLoginCellpadding = ConstantesWeb.formLoginCellpaddingDefaut;

	public static String formLoginAlign = ConstantesWeb.formLoginAlignDefaut;

	public static String formLoginTexteBouton = ConstantesWeb.formLoginTexteBoutonDefaut;

	public static String formLoginTexteLogin = ConstantesWeb.messageLoginDefaut;

	public static String formLoginTextePassword = ConstantesWeb.messagePasswordDefaut;

	public static String formLoginChampsCache = ConstantesWeb.formLoginChampsCacheDefaut;

	// fin page de login

	// Debut nom des variables html
	public static String varHtmlLogin = ConstantesWeb.varParametreLogin;

	public static String varHtmlPasswd = ConstantesWeb.varParametrePassword;

	// Fin nom des varaibles html

	//

	// debut fichier de trace et Exception
	/** True si l'application est sous Unix, False si elle est sous windows */
	public static boolean unix = ConstantesWeb.unixDefaut;

	/** Nom du fichier de trace avec extension pour windows */
	public static String nomFichierTraceWin = ConstantesWeb.nomFichierTraceWinDefaut;

	
	
	
	/** Nom du fichier de trace avec extension pour unix */
	public static String nomFichierTraceUnix = ConstantesWeb.nomFichierTraceUnixDefaut;

	/** Chemin absolu du fichier de trace sous unix */
	public static String pathHtmlUnixTrace = ConstantesWeb.pathHtmlUnixTraceDefaut;

	/** Chemin absolu du fichier de trace sous unix */
	public static String pathHtmlWinTrace = ConstantesWeb.pathHtmlWinTraceDefaut;

	/** url des images */
	public static String urlImageJspHtml = ConstantesWeb.urlImageJspHtml;

	public static String urlImageServlet = ConstantesWeb.urlImageServlet;

	// fin fichier de trace et Exception

	// Debut variable pour IConfig
	/** Table d'acces en lecture des objet de session */
	public static String[][] tabAccesRead = ConstantesWeb.tabAccesReadDefaut;

	/** Table d'acces en ecriture des objet de session */
	public static String[][] tabAccesWrite = ConstantesWeb.tabAccesWriteDefaut;

	/** Table d'acces en des types d'utilisateur eu JSP et Servlet */
	public static String[][] tabAccesEnsemble = ConstantesWeb.tabAccesEnsembleDefaut;

	/** Liste des servlet et JSP accessible par tous */
	public static String[] tabAccesEnsemblePublic = ConstantesWeb.tabAccesEnsemblePublicDefaut;

	/** Host ou est la base de donnee Mysl */
	public static String host = ConstantesWeb.hostDefaut;

	/** User de connexion a la bse de données Mysql */
	public static String user = ConstantesWeb.userDefaut;

	/** Passwd de connexion a la base de donnee Mysql */
	public static String password = ConstantesWeb.passwordDefaut;

	/** Nom de la base de donnees Mysql */
	public static String dbName = ConstantesWeb.dbNameDefaut;

	/** Port de connexion a la base de donnees Mysql */
	public static String port = ConstantesWeb.portDefaut;

	/** Delais avant expiration du password propose lors de la creation d'un user */
	public static int delaisPassword = ConstantesWeb.delaisPasswordDefaut;

	/** Delais de temps inter session propose par lors de la creation d'un user */
	public static int tis = ConstantesWeb.tisDefaut;

	/**
	 * Valeur de la validite de l'utilisataur propose par dafaut lors de la
	 * creation d'un User, si false l'utilisateur ne paut pas se connecter.
	 */
	public static String valide = ConstantesWeb.valideDefaut;

	/** si true ecrit dans la fichier sinon dans la sortie standard */
	public static boolean ecrireDansFichierLog = ConstantesWeb.logFileFlagDefaut;

	/** si true : active le tracage sinon Trace ne fait rien */
	public static boolean fichierLogActif = ConstantesWeb.actifDefaut;

	/**
	 * Si true les exception sont ecrite dans la sortie standard et dans les
	 * traces
	 */
	public static boolean noteExceptionStdEtTrace = ConstantesWeb.noteExceptionDefaut;

	/** Si true les sortie de debugage sont ajoutées aux traces */
	public static boolean ecrireDebug = ConstantesWeb.ecrireDebugDefaut;

	// Fin variable pour IConfig

	// PreUrl
	public static String preUrlDataUnix = ConstantesWeb.preUrlDataUnixDefaut;

	public static String preUrlDataWindows = ConstantesWeb.preUrlDataWinDefaut;

	public static String preUrlHtmlUnix = ConstantesWeb.preUrlHtmlUnixDefaut;

	public static String preUrlHtmlWindows = ConstantesWeb.preUrlHtmlWindowsDefaut;

	public static String preUrlJbinWindows = ConstantesWeb.preUrlJbinWindowsDefaut;

	public static String preUrlJbinUnix = ConstantesWeb.preUrlJbinUnixDefaut;

	/**
	 * Les valeurs pour les requests dispacher. En général il n'y a pas de w3,
	 * car ce sont des chemins relatifs aux contextes des servlets.
	 */
	public static String preUrlJspReqDispacherWindows = ConstantesWeb.preUrlJspReqDispacherWindowsDefaut;

	public static String preUrlJspReqDispacherUnix = ConstantesWeb.preUrlJspReqDispacherUnixDefaut;

	// Liste des variables contenant le nom des pages jsp,html,servlet
	public static String pageJspLogin = ConstantesWeb.pageJspLoginDefaut;

	public static String pageJspErreur = ConstantesWeb.pageJspErreurDefaut;

	public static String pageJspCreationUtilisateur = ConstantesWeb.pageJspCreationUtilisateurDefaut;

	public static String pageServletEntree = ConstantesWeb.pageServletEntreeDefaut;

	public static String pageJspIndexApplication = ConstantesWeb.pageJspIndexApplication;

	// SousURI
	public String sousUriAccueilSite = getPreUrlHtml()
			+ ConstantesWeb.pageAccueilDefaut;

	// nom des variables de session
	public static String nomSessionMessLogin = ConstantesWeb.nomSessionMessLoginDefaut;

	public static String nomSessionMessageErreur = ConstantesWeb.nomSessionMessageErreurDefaut;

	public static String nomSessionUtilisateur = ConstantesWeb.nomSessionUtilisateurDefaut;

	public static String nomSessionValeurPreverif = "valeurPreVerifAuth";// true
																			// ou
																			// false

	/** La chaine par défaut utilisé pour remplir des champs obligatoires */
	public static String chaineParDefaut = ConstantesWeb.chaineParDefautDefaut;

	/**
	 * Nom des colonnes pour la table utilisateur du module de données
	 * d'authentification
	 */
	public static String cnUtilisateur_numeroUser = ConstantesWeb.cnUtilisateur_numeroUserDefaut;

	public static String scnUtilisateur_numeroUser = ConstantesWeb.scnUtilisateur_numeroUserDefaut;

	public static String cnUtilisateur_login = ConstantesWeb.cnUtilisateur_loginDefaut;

	public static String scnUtilisateur_login = ConstantesWeb.scnUtilisateur_loginDefaut;

	public static String cnUtilisateur_password = ConstantesWeb.cnUtilisateur_passwordDefaut;

	public static String scnUtilisateur_password = ConstantesWeb.scnUtilisateur_passwordDefaut;

	public static String cnUtilisateur_ensembleUtilisateur = ConstantesWeb.cnUtilisateur_ensembleUtilisateurDefaut;

	public static String scnUtilisateur_ensembleUtilisateur = ConstantesWeb.scnUtilisateur_ensembleUtilisateurDefaut;

	public static String cnUtilisateur_valide = ConstantesWeb.cnUtilisateur_valideDefaut;

	public static String scnUtilisateur_valide = ConstantesWeb.scnUtilisateur_valideDefaut;

	public static String cnUtilisateur_tempsInterSession = ConstantesWeb.cnUtilisateur_tempsInterSessionDefaut;

	public static String scnUtilisateur_tempsInterSession = ConstantesWeb.scnUtilisateur_tempsInterSessionDefaut;

	public static String cnUtilisateur_datePassword = ConstantesWeb.cnUtilisateur_datePasswordDefaut;

	public static String scnUtilisateur_datePassword = ConstantesWeb.scnUtilisateur_datePasswordDefaut;

	public static String cnUtilisateur_delaiPassword = ConstantesWeb.cnUtilisateur_delaiPasswordDefaut;

	public static String scnUtilisateur_delaiPassword = ConstantesWeb.scnUtilisateur_delaiPasswordDefaut;

	public static String cnUtilisateur_nom = ConstantesWeb.cnUtilisateur_nomDefaut;

	public static String scnUtilisateur_nom = ConstantesWeb.scnUtilisateur_nomDefaut;

	public static String cnUtilisateur_prenom = ConstantesWeb.cnUtilisateur_prenomDefaut;

	public static String scnUtilisateur_prenom = ConstantesWeb.scnUtilisateur_prenomDefaut;

	public static String cnUtilisateur_remarque = ConstantesWeb.cnUtilisateur_remarqueDefaut;

	public static String scnUtilisateur_remarque = ConstantesWeb.scnUtilisateur_remarqueDefaut;

	public String getFormLoginAction() {
		return formLoginAction;
	}

	public String getFormLoginAlign() {
		return formLoginAlign;
	}

	public String getFormLoginBorder() {
		return formLoginBorder;
	}

	public String getFormLoginCellspacing() {
		return formLoginCellspacing;
	}

	public String getFormLoginChampsCache() {
		return formLoginChampsCache;
	}

	public String getFormLoginTarget() {
		return formLoginTarget;
	}

	public String getFormLoginTexteBouton() {
		return formLoginTexteBouton;
	}

	public String getFormLoginTexteLogin() {
		return formLoginTexteLogin;
	}

	public String getFormLoginTextePassword() {
		return formLoginTextePassword;
	}

	public String getFormLoginWidth() {
		return formLoginWidth;
	}

	public String getFormLoginCellpadding() {
		return formLoginCellpadding;
	}

	public boolean isFichierLogActif() {
		return fichierLogActif;
	}

	/**
	 * . Sofien CHARFI
	 * 
	 * @since 2.1
	 * @deprecated depuis 2.1
	 */
	/*
	 * public static String getDbName() { return dbName; }
	 */
	
	public static int getDelaisPassword() {
		return delaisPassword;
	}

	/**
	 * . Sofien CHARFI
	 * 
	 * @since 2.1
	 * @deprecated depuis 2.1
	 */
	/*
	 * public static String getHost() { return host; }
	 */
	
	public String getFichierLog() {
		if (unix)
			return pathHtmlUnixTrace + nomFichierTraceUnix;
		else
			return pathHtmlWinTrace + nomFichierTraceWin;
	}

	public boolean isEcrireDansFichierLog() {
		return ecrireDansFichierLog;
	}

	public boolean isNoteExceptionStdEtTrace() {
		return noteExceptionStdEtTrace;
	}

	public String[][] getTabAccesEnsemble() {
		return tabAccesEnsemble;
	}

	public String[] getTabAccesEnsemblePublic() {
		return tabAccesEnsemblePublic;
	}

	public String[][] getTabAccesRead() {
		return tabAccesRead;
	}

	public String[][] getTabAccesWrite() {
		return tabAccesWrite;
	}

	public int getTis() {
		return tis;
	}

	/**
	 * . Sofien CHARFI
	 * 
	 * @since 2.1
	 * @deprecated depuis 2.1
	 */
	/*
	 * public static String getUser() { return user; }
	 */
	
	public String getValide() {
		return valide;
	}

	public boolean isUnix() {
		return unix;
	}

	/**
	 * retourne la preUrlHtml en fonction du systeme d'exploitation
	 * 
	 * @return la preurl ex /w3/ ou /appli1/w3/ pour voir les fichier html sur
	 *         le serveur web.
	 * 
	 * @since 2.0
	 */
	public String getPreUrlHtml() {
		if (unix)
			return preUrlHtmlUnix;
		else
			return preUrlHtmlWindows;
	}

	/**
	 * retourne l'url vers les data. Il s'agis des fichiers telecharger par les
	 * utilisateurs
	 * 
	 * @since 1.8
	 */
	public String getPreUrlData() {
		if (unix)
			return preUrlDataUnix;
		else
			return preUrlDataWindows;
	}

	/**
	 * retourne la preUrljbin (pour l'execution des servlets en fonction du
	 * systeme d'exploitation
	 * 
	 * @return la preurl ex /jbin/servlet/ ou /appli1/ pour pouvoir executer les
	 *         servlets sur le serveur web.
	 * @since 2.0
	 */
	public String getPreUrlJbin() {
		if (unix)
			return preUrlJbinUnix;
		else
			return preUrlJbinWindows;
	}

	/**
	 * Retourne le pre url jsp é utiliser lors des requestDispacher de servlets
	 * vers des pages JSP.
	 * 
	 * @since 2.0
	 */
	public String getPreUrlJspReqDispacher() {
		if (unix)
			return preUrlJspReqDispacherUnix;
		else
			return preUrlJspReqDispacherWindows;
	}

	/**
	 * Chaine par defaut, généralemnt utilisé pour empécher l'utilisateur de
	 * mettre une chaine vide dans un champs obligatoire qui autorise les
	 * chaines vides, souvent c'est le caractere -. En effet il est impossible
	 * de faire un lien HTML sur une chaine vide.
	 * 
	 * @since 2.0
	 */
	public String getChaineParDefaut() {
		return chaineParDefaut;
	}

	public String getSousUriAccueilSite() {
		return sousUriAccueilSite;
	}

	public static String getMessageCIntegrite() {
		return messageCIntegrite;
	}

	public static String getMessageEchecDateInvalide() {
		return messageEchecDateInvalide;
	}

	public static String getMessageEchecEcritureBase() {
		return messageEchecEcritureBase;
	}

	public static String getMessageEchecFormat() {
		return messageEchecFormat;
	}

	public static String getMessageEchecGeneral() {
		return messageEchecGeneral;
	}

	public static String getMessageEchecIdentification() {
		return messageEchecIdentification;
	}

	public String getMessageEchecLogin() {
		return messageEchecLogin;
	}

	public String getMessageEchecServlet() {
		return messageEchecServlet;
	}

	public String getMessageEchecTypeFichierUpload() {
		return messageEchecTypeFichierUpload;
	}

	public String getMessageExcDoMain() {
		return messageExcDoMain;
	}

	public static String getMessageLogin() {
		return messageLogin;
	}

	public static String getMessagePassword() {
		return messagePassword;
	}

	public String getMessageVerifSession() {
		return messageVerifSession;
	}

	public String getMessageVerifUtilisateur() {
		return messageVerifUtilisateur;
	}

	public String getPageJspCreationUtilisateur() {
		return pageJspCreationUtilisateur;
	}

	public String getPageJspErreur() {
		return pageJspErreur;
	}

	public String getPageJspLogin() {
		return pageJspLogin;
	}

	public String getPageServletEntree() {
		return pageServletEntree;
	}

	public String getNomSessionMessageErreur() {
		return nomSessionMessageErreur;
	}

	public String getNomSessionMessLogin() {
		return nomSessionMessLogin;
	}

	public String getMessageAccueilLogin() {
		return messageAccueilLogin;
	}

	public String getNomSessionUtilisateur() {
		return nomSessionUtilisateur;
	}

	public String getNomSessionValeurPreverif() {
		return nomSessionValeurPreverif;
	}

	public String getVarHtmlLogin() {
		return varHtmlLogin;
	}

	public String getVarHtmlPasswd() {
		return varHtmlPasswd;
	}

	public String getPageJspIndexApplication() {
		return pageJspIndexApplication;
	}

	public String getCnUtilisateur_datePassword() {
		return cnUtilisateur_datePassword;
	}

	public String getCnUtilisateur_delaiPassword() {
		return cnUtilisateur_delaiPassword;
	}

	public String getCnUtilisateur_ensembleUtilisateur() {
		return cnUtilisateur_ensembleUtilisateur;
	}

	public String getCnUtilisateur_login() {
		return cnUtilisateur_login;
	}

	public String getCnUtilisateur_nom() {
		return cnUtilisateur_nom;
	}

	public String getCnUtilisateur_numeroUser() {
		return cnUtilisateur_numeroUser;
	}

	public String getCnUtilisateur_password() {
		return cnUtilisateur_password;
	}

	public String getCnUtilisateur_prenom() {
		return cnUtilisateur_prenom;
	}

	public String getCnUtilisateur_remarque() {
		return cnUtilisateur_remarque;
	}

	public String getCnUtilisateur_tempsInterSession() {
		return cnUtilisateur_tempsInterSession;
	}

	public String getCnUtilisateur_valide() {
		return cnUtilisateur_valide;
	}

	public String getScnUtilisateur_datePassword() {
		return scnUtilisateur_datePassword;
	}

	public String getScnUtilisateur_delaiPassword() {
		return scnUtilisateur_delaiPassword;
	}

	public String getScnUtilisateur_ensembleUtilisateur() {
		return scnUtilisateur_ensembleUtilisateur;
	}

	public String getScnUtilisateur_login() {
		return scnUtilisateur_login;
	}

	public static String getScnUtilisateur_nom() {
		return scnUtilisateur_nom;
	}

	public static String getScnUtilisateur_numeroUser() {
		return scnUtilisateur_numeroUser;
	}

	public static String getScnUtilisateur_password() {
		return scnUtilisateur_password;
	}

	public static String getScnUtilisateur_prenom() {
		return scnUtilisateur_prenom;
	}

	public static String getScnUtilisateur_remarque() {
		return scnUtilisateur_remarque;
	}

	public static String getScnUtilisateur_tempsInterSession() {
		return scnUtilisateur_tempsInterSession;
	}

	public static String getScnUtilisateur_valide() {
		return scnUtilisateur_valide;
	}

	/**
	 * retourne le separateur de fichier suivant que l'on soit sous windows ou
	 * unix.C a d un slash ou un anti slash.
	 */
	public static String Slash() {
		if (unix)
			return "/";
		else
			return "\\";
	}

	public boolean isEcrireDebug() {
		return ecrireDebug;
	}

	public static void setEcrireDansFichierLog(boolean ecrireDansFichierLog) {
		ecrireDansFichierLog = ecrireDansFichierLog;
	}

	public static void setEcrireDebug(boolean ecrireDebug) {
		ecrireDebug = ecrireDebug;
	}

	public static void setFichierLogActif(boolean fichierLogActif) {
		fichierLogActif = fichierLogActif;
	}

	public static void setNoteExceptionStdEtTrace(
			boolean noteExceptionStdEtTrace) {
		noteExceptionStdEtTrace = noteExceptionStdEtTrace;
	}

	public static void setUnix(boolean unix) {
		unix = unix;
	}

	public static void setFormLoginAction(String formLoginAction) {
		formLoginAction = formLoginAction;
	}

	public static void setPreUrlJbinUnix(String preUrlJbinUnix) {
		preUrlJbinUnix = preUrlJbinUnix;
	}

	public static void setPreUrlJbinWindows(String preUrlJbinWindows) {
		preUrlJbinWindows = preUrlJbinWindows;
	}

	public static void setPreUrlJspReqDispacherUnix(
			String preUrlJspReqDispacherUnix) {
		preUrlJspReqDispacherUnix = preUrlJspReqDispacherUnix;
	}

	public static void setPreUrlJspReqDispacherWindows(
			String preUrlJspReqDispacherWindows) {
		preUrlJspReqDispacherWindows = preUrlJspReqDispacherWindows;
	}

	public static void setPreUrlHtmlUnix(String preUrlHtmlUnix) {
		preUrlHtmlUnix = preUrlHtmlUnix;
	}

	public static void setPreUrlHtmlWindows(String preUrlHtmlWindows) {
		preUrlHtmlWindows = preUrlHtmlWindows;
	}

	public String getUrlImageJspHtml() {
		return urlImageJspHtml;
	}

	public String getUrlImageServlet() {
		return urlImageServlet;
	}

	public static void setUrlImageJspHtml(String urlImageJspHtml) {
		urlImageJspHtml = urlImageJspHtml;
	}

	public static void setUrlImageServlet(String urlImageServlet) {
		urlImageServlet = urlImageServlet;
	}

	public static void setPreUrlDataUnix(String preUrlDataUnix) {
		preUrlDataUnix = preUrlDataUnix;
	}

	public static void setPreUrlDataWindows(String preUrlDataWindows) {
		preUrlDataWindows = preUrlDataWindows;
	}

	public String getSlash() {
		if (unix)
			return "/";
		else
			return "\\";
	}

}