package fr.analogon.r2t.administration;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.request.RequestOuvrage;
import fr.analogon.r2t.request.RequestRue;
import fr.analogon.r2t.request.RequestUtilisateur;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de Synchronisaiton
 * 
 * @version 1.0
 * @since 1.0
 */
public class BAffListeContoleTerrain extends fr.analogon.r2t.main.RacineBean {


	private String listeControleTerrain="";
	private String listeUtilisateur="";
	private String listeQuartier="";	
	HttpServletRequest req;	
	RequestOuvrage requestOuvrage =  new RequestOuvrage();
	
	public void setRequest(HttpServletRequest req) 
	{		
		this.req=req;			
		String nomQuartier= req.getParameter("nomQuartier");
		String dateControle= req.getParameter("dateControle");
		String utuilsateur= req.getParameter("utuilsateur");
		String type= req.getParameter("type");		
		
		Vector tabQuartier= (new RequestRue()).getListeQuartiers("");
		this.listeQuartier = StaticManipHtml.genererListeDeroulante("nomQuartier", 1,"",tabQuartier,true);
		
		Vector tabUtilisateur= (new RequestUtilisateur()).getListeUtilisateur("", "","","");
		this.listeUtilisateur = StaticManipHtml.genererListeDeroulanteUser("utuilsateur",tabUtilisateur);
		
		
		if(type !=null )
		{
		  Vector tabListeControle= requestOuvrage.getListeControle( nomQuartier,dateControle, utuilsateur);	
		  this.listeControleTerrain=StaticManipHtml.genererTableauListeControle(tabListeControle);
		
		}
		
	}

	/**
	 * @return the listeControleTerrain
	 */
	public final String getListeControleTerrain() {
		return listeControleTerrain;
	}

	/**
	 * @param listeControleTerrain the listeControleTerrain to set
	 */
	public final void setListeControleTerrain(String listeControleTerrain) {
		this.listeControleTerrain = listeControleTerrain;
	}

	/**
	 * @return the listeUtilisateur
	 */
	public final String getListeUtilisateur() {
		return listeUtilisateur;
	}

	/**
	 * @param listeUtilisateur the listeUtilisateur to set
	 */
	public final void setListeUtilisateur(String listeUtilisateur) {
		this.listeUtilisateur = listeUtilisateur;
	}

	/**
	 * @return the listeQuartier
	 */
	public final String getListeQuartier() {
		return listeQuartier;
	}

	/**
	 * @param listeQuartier the listeQuartier to set
	 */
	public final void setListeQuartier(String listeQuartier) {
		this.listeQuartier = listeQuartier;
	}	


}

