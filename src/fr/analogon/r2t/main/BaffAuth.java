package fr.analogon.r2t.main;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 * @version 1.0 . Sofien CHARFI 
 * @since 1.0
 */
public class BaffAuth  extends	 fr.analogon.r2t.util.web.session.BAffAuthentification {

	private boolean nePlusAfficherFormulaire = false;

	private int nbEchecConsecutifConnection = 0;	

	private boolean afficheMessageDemo = false;

	public void setRequest(HttpServletRequest req) 
	{		
		super.setRequest(req);		
	}
	public void debutSetReq() 
	{
		nePlusAfficherFormulaire = false;
	}

	public boolean isNePlusAfficherFormulaire() {
		return nePlusAfficherFormulaire;
	}

	public int getNbEchecConsecutifConnection() {
		return nbEchecConsecutifConnection;
	}

	public boolean isAfficheMessageDemo() {
		return afficheMessageDemo;
	}

}
