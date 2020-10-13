package fr.analogon.r2t.reclamation;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.pojo.ModeleCourier;
import fr.analogon.r2t.request.RequestReclamation;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage des urulisateurs  . Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0
 */
public class BAffModeleCourier extends fr.analogon.r2t.main.RacineBean
{
	
	private HttpServletRequest request;	
	private String choix= "";	
	private boolean creation=true;	
	private String idModeleCourier="";
	private String nomModeleCourier="";
	private String cheminModeleCourier="";
	private String listeModeleCouriers="";
	private boolean peutEtreSupprimer=true;
	
	RequestReclamation  requestReclamation = new RequestReclamation();
	
	public void setRequest(HttpServletRequest req)
	{	
				
		this.request = req;	
		if (request.getParameter("choix") != null && ! request.getParameter("choix").equalsIgnoreCase(" ") )
		{
			choix=request.getParameter("choix");
			
			if(choix.equalsIgnoreCase("ajouter"))
			{
				this.setCreation(true);								
			}
			if(choix.equalsIgnoreCase("modifier") )
			{
				 this.setCreation(false);	
				 idModeleCourier= req.getParameter("idModeleCourier");
				 ModeleCourier modeleCourier = requestReclamation.getModeleCourier(idModeleCourier);				 
				 this.nomModeleCourier = modeleCourier.getNomModele();
				 this.cheminModeleCourier = modeleCourier.getCheminModele();
				 peutEtreSupprimer = Boolean.valueOf(requestReclamation.ModelePeutEtreSuuprimer(idModeleCourier));
			}
			else if (request.getParameter("choix").equalsIgnoreCase("chercher"))
			{
				
				if (req.getParameter("nomModeleCourierCHERCHER") != null)
					nomModeleCourier= req.getParameter("nomModeleCourierCHERCHER");
				else
					nomModeleCourier="";
				Vector listeModele = requestReclamation.getListeDesModeleCourier(nomModeleCourier);
				this.listeModeleCouriers= StaticManipHtml.genererTableaulisteModeleCourier(listeModele);
				
			}
		}
	
	}

	/**
	 * @return the cheminModeleCourier
	 */
	public final String getCheminModeleCourier() {
		return cheminModeleCourier;
	}

	/**
	 * @param cheminModeleCourier the cheminModeleCourier to set
	 */
	public final void setCheminModeleCourier(String cheminModeleCourier) {
		this.cheminModeleCourier = cheminModeleCourier;
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
	 * @return the creation
	 */
	public final boolean getCreation() {
		return creation;
	}

	/**
	 * @param creation the creation to set
	 */
	public final void setCreation(boolean creation) {
		this.creation = creation;
	}

	/**
	 * @return the idModeleCourier
	 */
	public final String getIdModeleCourier() {
		return idModeleCourier;
	}

	/**
	 * @param idModeleCourier the idModeleCourier to set
	 */
	public final void setIdModeleCourier(String idModeleCourier) {
		this.idModeleCourier = idModeleCourier;
	}

	/**
	 * @return the nomModeleCourier
	 */
	public final String getNomModeleCourier() {
		return nomModeleCourier;
	}

	/**
	 * @param nomModeleCourier the nomModeleCourier to set
	 */
	public final void setNomModeleCourier(String nomModeleCourier) {
		this.nomModeleCourier = nomModeleCourier;
	}

	/**
	 * @return the listeModeleCouriers
	 */
	public final String getListeModeleCouriers() {
		return listeModeleCouriers;
	}

	/**
	 * @param listeModeleCouriers the listeModeleCouriers to set
	 */
	public final void setListeModeleCouriers(String listeModeleCouriers) {
		this.listeModeleCouriers = listeModeleCouriers;
	}

	/**
	 * @return the peutEtreSupprimer
	 */
	public final boolean getPeutEtreSupprimer() {
		return peutEtreSupprimer;
	}

	/**
	 * @param peutEtreSupprimer the peutEtreSupprimer to set
	 */
	public final void setPeutEtreSupprimer(boolean peutEtreSupprimer) {
		this.peutEtreSupprimer = peutEtreSupprimer;
	}


	
}



