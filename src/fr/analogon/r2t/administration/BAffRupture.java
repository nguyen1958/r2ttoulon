package fr.analogon.r2t.administration;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.request.RequestOuvrage;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de Synchronisaiton
 * 
 * @version 1.0
 * @since 1.0
 */
public class BAffRupture extends fr.analogon.r2t.main.RacineBean {


	private String listeRupture="";
	private String nombreRupture="";
	private String  nomQuartier="";
	HttpServletRequest req;
	
	RequestOuvrage requestOuvrage =  new RequestOuvrage();
	
	public void setRequest(HttpServletRequest req) 
	{		
		this.req=req;
		String numOuvrage = req.getParameter("numOuvrage");
		nomQuartier = req.getParameter("nomQuartier");
		DebuggerLog4J.logger.debug("numOuvrage="+numOuvrage);
		
		if(numOuvrage !=null)	
			requestOuvrage.arrterFacturationOuvrae(numOuvrage);
		
		Vector tabListeRuptureControle= requestOuvrage.getListeRuptureControle(nomQuartier);		
		
		this.listeRupture=StaticManipHtml.genererTableauListeRuptureControle(tabListeRuptureControle);
		if(tabListeRuptureControle.size()==0) this.listeRupture ="Aucune rupture de controle pour le quartier " + nomQuartier;
		this.nombreRupture = ""+tabListeRuptureControle.size();
	}	



	/**
	 * @return the listeRupture
	 */
	public final String getListeRupture() {
		return listeRupture;
	}


	/**
	 * @param listeRupture the listeRupture to set
	 */
	public final void setListeRupture(String listeRupture) {
		this.listeRupture = listeRupture;
	}



	/**
	 * @return the nombreRupture
	 */
	public final String getNombreRupture() {
		return nombreRupture;
	}



	/**
	 * @param nombreRupture the nombreRupture to set
	 */
	public final void setNombreRupture(String nombreRupture) {
		this.nombreRupture = nombreRupture;
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

