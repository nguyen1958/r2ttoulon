/**
 * 
 */
package fr.analogon.r2t.pojo;


/**
 * @author sofien
 *
 */
public class CourierReponse 
{
	String idHistoriqueCourier ="";
	String idReclamation  ="";
	String nomModele  ="";
	String dateCreation  ="";
	
	
	/**
	 * @return the dateCreation
	 */
	public final String getDateCreation() {
		return dateCreation;
	}
	/**
	 * @param dateCreation the dateCreation to set
	 */
	public final void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	/**
	 * @return the idHistoriqueCourier
	 */
	public final String getIdHistoriqueCourier() {
		return idHistoriqueCourier;
	}
	/**
	 * @param idHistoriqueCourier the idHistoriqueCourier to set
	 */
	public final void setIdHistoriqueCourier(String idHistoriqueCourier) {
		this.idHistoriqueCourier = idHistoriqueCourier;
	}
	/**
	 * @return the idModele
	 */
	public final String getNomModele() {
		return nomModele;
	}
	/**
	 * @param idModele the idModele to set
	 */
	public final void setNomModele(String nomModele) {
		this.nomModele = nomModele;
	}
	/**
	 * @return the idReclamation
	 */
	public final String getIdReclamation() {
		return idReclamation;
	}
	/**
	 * @param idReclamation the idReclamation to set
	 */
	public final void setIdReclamation(String idReclamation) {
		this.idReclamation = idReclamation;
	}
			 

}
