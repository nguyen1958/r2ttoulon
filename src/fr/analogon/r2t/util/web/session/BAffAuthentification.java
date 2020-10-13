package fr.analogon.r2t.util.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.request.RequestParametres;

/**
 * Cette classe permet d'afficher le code Html necessaire pour une
 * authentification avec une page html. Gere les messages d'erreur . David
 * Gimelle
 * 
 * @version 2.0
 * @since 2.0
 */
public class BAffAuthentification {
	
	public HttpServletRequest request;

	/** Message accompagnant la mire de login */
	//public  String messageSessionLogin="Veuillez taper votre login et votre mot de passe ";
	public  String messageSessionLogin="";
	
	public  String message="test";
	
	/* le nom de la variable html de login */
	private String varHtmlLogin;

	/* le nom de la varaible html de password */
	private String varHtmlPasswd;

	/*
	 * Le chemin vers la servlet d'authentification tel qu'il apparaitrea dans
	 * le formulaire
	 */
	private String formHtmlAction;

	/**
	 * permet d'initaliser le bean depuis une JSP. . Sofien CHARFI
	 * 
	 * @since 2.0
	 */
	public void setRequest(HttpServletRequest req) {
		this.request = req;
		debutSetReq();
		
		
		RequestParametres requestParametres = new RequestParametres();
		HttpSession session = request.getSession(true);		
		System.out.print(InitialisationConnexionLectureConfiguration.getConnexion().connexion);
		
		String ville = requestParametres.getVille();
		session.setAttribute("ville",ville);
			
		String siteWebVille = requestParametres.getSiteWebville();
		session.setAttribute("siteWebVille",siteWebVille);				
		
		message =request.getParameter("msg");		
		if(message !=null && message.equalsIgnoreCase("sessionExpire"))
			messageSessionLogin="La session a expiré ";
		else if (message !=null && message.equalsIgnoreCase("erreurParametres")) 
			messageSessionLogin="Paramètres d'authentification non valide !! ";
		else
			messageSessionLogin="";
		
		finSetReq();
	}
	

	/**
	 * Retourne le messageAccompagnant la mire de login. . Sofien CHARFI
	 * 
	 * @since 2.0
	 */
	public String getMessageSessionLogin() {
		return this.messageSessionLogin;
	}

	public String getVarHtmlLogin() {
		return this.varHtmlLogin;
	}

	public String getVarHtmlPasswd() {
		return this.varHtmlPasswd;
	}

	public String getFormHTMLAction() {
		return this.formHtmlAction;
	}

	/* Methode a redefinir */
	public void debutSetReq() {
	}

	/* Methode a redefinir */
	public void finSetReq() {
	}


	/**
	 * @return the message
	 */
	public final String getMessage() {
		return message;
	}


	/**
	 * @param message the message to set
	 */
	public final void setMessage(String message) {
		this.message = message;
	}



}