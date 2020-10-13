package fr.analogon.r2t.view.batch;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.request.RequestOuvrage;
import fr.analogon.r2t.request.RequestRue;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de batchJourneaux.jsp . Sofien CHARFI
 * 
 * @version 1.0
 * @since 1.0
 */
public class BAffListeCoomunique extends fr.analogon.r2t.main.RacineBean {

	private String attribut;	
	private String roleCommunique="";
	public String getNumSecteur() {
		return numSecteur;
	}


	public void setNumSecteur(String numSecteur) {
		this.numSecteur = numSecteur;
	}


	private String nomQuartier="";
	private String listeCommunique="";
	private String dateDuJour="";
	private String numSecteur="";
	
	private String controle="true";
	private String nbreOuvrageAControle="";
	private String listeQuartier="";
	private HttpServletRequest request;	
	RequestOuvrage reqOuvrage = new RequestOuvrage();	
	RequestRue requestRue = new RequestRue();
	
		
	public void setRequest(HttpServletRequest req) 
	{			
		StaticManipHtml mHtml = new StaticManipHtml();
		nomQuartier = req.getParameter("nomQuartier");
		numSecteur  = req.getParameter("numSecteur");
		//DebuggerLog4J.logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		//DebuggerLog4J.logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		DebuggerLog4J.logger.debug("quartier="+nomQuartier);
		DebuggerLog4J.logger.debug("numSecteur="+numSecteur);
		Vector contenu = reqOuvrage.getListeCommunique(GestionDate.getDateAujourdhuiString(),
				nomQuartier,numSecteur); 
		listeCommunique=StaticManipHtml.genererTableauListeCommunique(contenu);
		dateDuJour = GestionDate.getDateAujourdhuiString();
		nbreOuvrageAControle = ""+contenu.size();
		if(contenu.size()==0) controle ="false";
	}
	

	/** Retourne l'attribut pour ordonner le tableau */
	public String getattribut() 
	{
		return attribut;
	}
	



	/**
	 * @return the roleCommunique
	 */
	public final String getRoleCommunique() {
		return roleCommunique;
	}


	/**
	 * @param roleCommunique the roleCommunique to set
	 */
	public final void setRoleCommunique(String roleCommunique) {
		this.roleCommunique = roleCommunique;
	}


	/**
	 * @return the request
	 */
	public final HttpServletRequest getRequest() {
		return request;
	}


	/**
	 * @return the listeCommunique
	 */
	public final String getListeCommunique() {
		return listeCommunique;
	}


	/**
	 * @param listeCommunique the listeCommunique to set
	 */
	public final void setListeCommunique(String listeCommunique) {
		this.listeCommunique = listeCommunique;
	}


	/**
	 * @return the dateDuJour
	 */
	public final String getDateDuJour() {
		return dateDuJour;
	}


	/**
	 * @param dateDuJour the dateDuJour to set
	 */
	public final void setDateDuJour(String dateDuJour) {
		this.dateDuJour = dateDuJour;
	}


	/**
	 * @return the controle
	 */
	public final String getControle() {
		return controle;
	}


	/**
	 * @param controle the controle to set
	 */
	public final void setControle(String controle) {
		this.controle = controle;
	}


	/**
	 * @return the nbreOuvrageAControle
	 */
	public final String getNbreOuvrageAControle() {
		return nbreOuvrageAControle;
	}


	/**
	 * @param nbreOuvrageAControle the nbreOuvrageAControle to set
	 */
	public final void setNbreOuvrageAControle(String nbreOuvrageAControle) {
		this.nbreOuvrageAControle = nbreOuvrageAControle;
	}


	/**
	 * @return the listeQuartier
	 */
	public final String getListeQuartier() 
	{
		Vector tabQuartier= requestRue.getListeQuartiers("");
		listeQuartier = StaticManipHtml.genererListeDeroulante("nomQuartier", 1,"",tabQuartier,true);
		return listeQuartier;
	}


	/**
	 * @param listeQuartier the listeQuartier to set
	 */
	public final void setListeQuartier(String listeQuartier) {
		this.listeQuartier = listeQuartier;
	}


	/**
	 * @return the nomQuartier
	 */
	public final String getNomQuartier() {
		return nomQuartier;
	}


	/**
	 * @param nomQuartier the nomQuartier to set
	 */
	public final void setNomQuartier(String nomQuartier) {
		this.nomQuartier = nomQuartier;
	}
	
	
	
	
	
	
	

}