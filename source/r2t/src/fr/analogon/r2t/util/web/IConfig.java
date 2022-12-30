package fr.analogon.r2t.util.web;


//import com.borland.dx.sql.dataset.ConnectionDescriptor;

/**
 * Décrit les methodes que doivent implémenter un objet de configuration de site
 * web. . Vincent Jule
 * 
 * @version 2.6
 * @since 1.0
 */
public interface IConfig {

	/**
	 * Renvoit le tableau donnant les droits d'accés en lecture des servlets aux
	 * objets de la session. . Vincent Jule
	 * 
	 * @since 1.0
	 */
	public String[][] getTabAccesRead();

	/**
	 * Renvoit le tableau donnant les droits d'accés en écriture des servlets
	 * aux objets de la session. . Vincent Jule
	 * 
	 * @since 1.0
	 */
	public String[][] getTabAccesWrite();

	/**
	 * Renvoit le tableau donnant les droits d'accés aux servlets en fonction du
	 * groupe de l'utilisateur . Vincent Jule
	 * 
	 * @since 1.0
	 */
	public String[][] getTabAccesEnsemble();

	/**
	 * Renvoit le tableau donnant la liste des servlet et JSP accessible par
	 * tous. . Sofien CHARFI
	 * 
	 * @since 2.4
	 */
	public String[] getTabAccesEnsemblePublic();

	/**
	 * Retourne le host de la connexion é la base de données: ex : jaguar.apm.fr .
	 * Vincent Jule
	 * 
	 * @since 1.0
	 * @deprecated depuis 2.5
	 */
	// public String getHost();
	/**
	 * Retourne le user de la connexion é la base de données . Vincent Jule
	 * 
	 * @since 1.0
	 * @deprecated depuis 2.5
	 */
	// public String getUser();
	/**
	 * Retourne le mot de passe de la connexion é la base de données . David
	 * Gimelle
	 * 
	 * @since 1.0
	 * @deprecated depuis 2.5
	 */
	// public String getPassword();
	/**
	 * Retourne le nom de la base de données . Sofien CHARFI
	 * 
	 * @since 1.0
	 * @deprecated depuis 2.5
	 */
	// public String getDbName();
	/**
	 * Retourne le port de la base de données. . Sofien CHARFI
	 * 
	 * @since 1.0
	 * @deprecated depuis 2.5
	 */
	// public String getPort();
	/**
	 * retourne la duree de validité par défaut du mot de passe, utilisée lors
	 * de la creation d'utilisateur. . Sofien CHARFI
	 * 
	 * @since 1.0
	 * @deprecated depuis 2.5
	 */
	// public int getDelaisPassword();
	/**
	 * Retourne le temps maximal par défaut entre 2 servlets avant de se
	 * ré-identifier, utilisée lors de la creation d'utilisateur. . David
	 * Gimelle
	 * 
	 * @since 1.0
	 */
	public int getTis();

	/**
	 * Retourne la validité par défaut d'une session utilisée lors de la
	 * creation d'utilisateur. . Sofien CHARFI
	 * 
	 * @since 2.3
	 */
	public String getValide();

	/**
	 * Retourne le fichier dans lequel enregitrer les sorties exception . David
	 * Gimelle
	 * 
	 * @since 1.0
	 */
	public String getFichierLog();

	/**
	 * Retourne true : capture exception dans fichier activée sinon capture dans
	 * sortie standart . Sofien CHARFI
	 * 
	 * @since 1.0
	 */
	public boolean isEcrireDansFichierLog();

	/**
	 * true: le tracage par Trace est actif sinon ... il ne fait rien . David
	 * Gimelle
	 * 
	 * @since 1.0
	 */
	public boolean isFichierLogActif();

	/**
	 * Retourne la valeur de noteException
	 * 
	 * @return true si les exeptions sont ecrite la sortie standard et dans le
	 *         fichier de trace si celui-ci est actif . Sofien CHARFI
	 * @since 1.0
	 */
	public boolean isNoteExceptionStdEtTrace();

	/**
	 * Retourne le nb de mois apres lequel relancer la fiche d'intervention .
	 * Sofien CHARFI
	 * 
	 * @since 1.0
	 */
	// public int getRelanceMois();
	/**
	 * Retourne true si les sortie de debugage sont ecrite dans les traces de
	 * l'application . Sofien CHARFI
	 * 
	 * @since 2.6
	 */
	public boolean isEcrireDebug();

	/**
	 * retourne la preUrlHtml en fonction du systeme d'exploitation
	 * 
	 * @return la preurl ex /w3/ ou /appli1/w3/ pour voir les fichier html sur
	 *         le serveur web. . Sofien CHARFI
	 * @since 2.0
	 */
	public String getPreUrlHtml();

	/**
	 * Retourne le pre url jsp é utiliser lors des requestDispacher de servlets
	 * vers des pages JSP. . Sofien CHARFI
	 * 
	 * @since 2.0
	 */
	public String getPreUrlJspReqDispacher();

	/**
	 * retourne la preUrljbin (pour l'execution des servlets en fonction du
	 * systeme d'exploitation
	 * 
	 * @return la preurl ex /jbin/servlet/ ou /appli1/ pour pouvoir executer les
	 *         servlets sur le serveur web. . Sofien CHARFI
	 * @since 2.0
	 */
	public String getPreUrlJbin();

	public String getSousUriAccueilSite();

	public String getPageJspErreur();

	public String getPageServletEntree();

	public String getMessageExcDoMain();

	public String getMessageVerifUtilisateur();

	public String getMessageVerifSession();

	public String getNomSessionMessageErreur();

	/**
	 * Chaine par defaut, généralemnt utilisé pour empécher l'utilisateur de
	 * mettre une chaine vide dans un champs obligatoire qui autorise les
	 * chaines vides, souvent c'est le caractere -. En effet il est impossible
	 * de faire un lien HTML sur une chaine vide. . Sofien CHARFI
	 * 
	 * @since 2.0
	 */
	public String getChaineParDefaut();

	public String getNomSessionUtilisateur();

	public String getVarHtmlLogin();

	public String getVarHtmlPasswd();

	public String getPageJspIndexApplication();

	public String getCnUtilisateur_datePassword();

	public String getCnUtilisateur_delaiPassword();

	public String getCnUtilisateur_ensembleUtilisateur();

	public String getCnUtilisateur_login();

	public String getCnUtilisateur_nom();

	public String getCnUtilisateur_numeroUser();

	public String getCnUtilisateur_password();

	public String getCnUtilisateur_prenom();

	public String getCnUtilisateur_remarque();

	public String getCnUtilisateur_tempsInterSession();

	public String getCnUtilisateur_valide();

	public String getScnUtilisateur_datePassword();

	public String getScnUtilisateur_delaiPassword();

	public String getScnUtilisateur_ensembleUtilisateur();

	public String getScnUtilisateur_login();

	public String getNomSessionMessLogin();

	public String getMessageAccueilLogin();

	public String getNomSessionValeurPreverif();

	public String getPageJspCreationUtilisateur();

	public String getPageJspLogin();

	public String getMessageEchecLogin();

	public String getMessageEchecServlet();

	public String getSlash();

	public String getUrlImageServlet();

	public boolean isUnix();

	public String getFormLoginAction();

	public String getFormLoginAlign();

	public String getFormLoginBorder();

	public String getFormLoginCellspacing();

	public String getFormLoginChampsCache();

	public String getFormLoginTarget();

	public String getFormLoginTexteBouton();

	public String getFormLoginTexteLogin();

	public String getFormLoginTextePassword();

	public String getFormLoginWidth();

	public String getFormLoginCellpadding();

}