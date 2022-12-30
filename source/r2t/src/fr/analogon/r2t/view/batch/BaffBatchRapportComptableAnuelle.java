package fr.analogon.r2t.view.batch;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.RaportComptable;
import fr.analogon.r2t.request.RequestRaportComptable;


/**
 * Bean d'affichage du batch Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0
 */
public class BaffBatchRapportComptableAnuelle extends fr.analogon.r2t.main.RacineBean 
{
	
	private HttpServletRequest request;	
	private int numeroBatch;	
	private String dateDeCreation="";
	
	String lienBalanceAnnuelToulon="";
	String lienListeDesAcomptesAnnuelToulon="";
	
	String lienListeDesAnnulationsAnnuelToulon="";
	String lienListeDesImpayesAnnuelEtalageToulon="";
	String lienListeDesImpayesAnnuelTLPEToulon="";
	String lienVentilationParBaremeAnnuelEtalageToulon="";
	String lienVentilationParBaremeAnnuelTLPEToulon="";
	String lienVentilationParEmplacementAnnuelEtalageToulon="";
	String lienVentilationParEmplacementAnnuelTLPEToulon="";
     
	
	public void setRequest(HttpServletRequest req)
	{
		try
		{	
			this.request = req;
			if (request.getParameter("numeroBatch") != null)
			{
				numeroBatch =Integer.parseInt(request.getParameter("numeroBatch"));
				RequestRaportComptable requestRaportComptable = new RequestRaportComptable();
				
				this.lienBalanceAnnuelToulon= "<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportBalanceAnnuelToulon()+"BA-"+numeroBatch+".pdf'>Balance annuelle </a>";
				this.lienListeDesAcomptesAnnuelToulon= "<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportListeDesAcomptesAnnuelToulon()+"LDAA-"+numeroBatch+".pdf'>Liste des acomptes annuel </a>";
				this.lienListeDesAnnulationsAnnuelToulon= "<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportListeDesAnnulationsAnnuelToulon()+"LDAnnulation-"+numeroBatch+".pdf'>Liste des annulations, remises et exonérations </a>";
				this.lienListeDesImpayesAnnuelEtalageToulon= "<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportListeDesImpayesAnnuelEtalageToulon()+"LDIAEtalage-"+numeroBatch+".pdf'>Liste des impayes etalage </a>";
				this.lienListeDesImpayesAnnuelTLPEToulon= "<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportListeDesImpayesAnnuelTLPEToulon()+"LDIATLPE-"+numeroBatch+".pdf'>Liste des impayes TLPE  </a>";
				this.lienVentilationParBaremeAnnuelEtalageToulon= "<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportVentilationParBaremeAnnuelEtalageToulon()+"VPBAE-"+numeroBatch+".pdf'>Ventilation par bareme etalage </a>";
				this.lienVentilationParBaremeAnnuelTLPEToulon= "<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportVentilationParBaremeAnnuelTLPEToulon()+"VPBATLPE-"+numeroBatch+".pdf'>Ventilation par bareme TLPE </a>";
				this.lienVentilationParEmplacementAnnuelEtalageToulon= "<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportVentilationParEmplacementAnnuelEtalageToulon()+"VPEAE-"+numeroBatch+".pdf'>Ventilation par emplacement etalage </a>";
				this.lienVentilationParEmplacementAnnuelTLPEToulon= "<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportVentilationParEmplacementAnnuelTLPEToulon()+"VPETLPE-"+numeroBatch+".pdf'>Ventilation par emplacement TLPE </a>";
				
				RaportComptable raportComptable = requestRaportComptable.getRapport(numeroBatch);
				dateDeCreation =raportComptable.getDateLancement();
			}
		}
		catch (Exception e) 
		{
			DebuggerLog4J.logger.fatal(e.getMessage());
		}
			
						
	}




	public int getNumeroBatch() {
		return numeroBatch;
	}
	
	public void setNumeroBatch(int numeroBatch) {
		this.numeroBatch = numeroBatch;
	}
	public String getDateDeCreation() {
		return dateDeCreation;
	}

	public void setDateDeCreation(String dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}
	
	/**
	 * @return the lienBalanceAnnuelToulon
	 */
	public String getLienBalanceAnnuelToulon() {
		return lienBalanceAnnuelToulon;
	}

	/**
	 * @param lienBalanceAnnuelToulon the lienBalanceAnnuelToulon to set
	 */
	public void setLienBalanceAnnuelToulon(String lienBalanceAnnuelToulon) {
		this.lienBalanceAnnuelToulon = lienBalanceAnnuelToulon;
	}




	/**
	 * @return the lienListeDesAcomptesAnnuelToulon
	 */
	public String getLienListeDesAcomptesAnnuelToulon() {
		return lienListeDesAcomptesAnnuelToulon;
	}




	/**
	 * @param lienListeDesAcomptesAnnuelToulon the lienListeDesAcomptesAnnuelToulon to set
	 */
	public void setLienListeDesAcomptesAnnuelToulon(
			String lienListeDesAcomptesAnnuelToulon) {
		this.lienListeDesAcomptesAnnuelToulon = lienListeDesAcomptesAnnuelToulon;
	}




	/**
	 * @return the lienListeDesAnnulationsAnnuelToulon
	 */
	public String getLienListeDesAnnulationsAnnuelToulon() {
		return lienListeDesAnnulationsAnnuelToulon;
	}




	/**
	 * @param lienListeDesAnnulationsAnnuelToulon the lienListeDesAnnulationsAnnuelToulon to set
	 */
	public void setLienListeDesAnnulationsAnnuelToulon(
			String lienListeDesAnnulationsAnnuelToulon) {
		this.lienListeDesAnnulationsAnnuelToulon = lienListeDesAnnulationsAnnuelToulon;
	}




	/**
	 * @return the lienListeDesImpayesAnnuelEtalageToulon
	 */
	public String getLienListeDesImpayesAnnuelEtalageToulon() {
		return lienListeDesImpayesAnnuelEtalageToulon;
	}




	/**
	 * @param lienListeDesImpayesAnnuelEtalageToulon the lienListeDesImpayesAnnuelEtalageToulon to set
	 */
	public void setLienListeDesImpayesAnnuelEtalageToulon(
			String lienListeDesImpayesAnnuelEtalageToulon) {
		this.lienListeDesImpayesAnnuelEtalageToulon = lienListeDesImpayesAnnuelEtalageToulon;
	}




	/**
	 * @return the lienListeDesImpayesAnnuelTLPEToulon
	 */
	public String getLienListeDesImpayesAnnuelTLPEToulon() {
		return lienListeDesImpayesAnnuelTLPEToulon;
	}




	/**
	 * @param lienListeDesImpayesAnnuelTLPEToulon the lienListeDesImpayesAnnuelTLPEToulon to set
	 */
	public void setLienListeDesImpayesAnnuelTLPEToulon(
			String lienListeDesImpayesAnnuelTLPEToulon) {
		this.lienListeDesImpayesAnnuelTLPEToulon = lienListeDesImpayesAnnuelTLPEToulon;
	}




	/**
	 * @return the lienVentilationParBaremeAnnuelEtalageToulon
	 */
	public String getLienVentilationParBaremeAnnuelEtalageToulon() {
		return lienVentilationParBaremeAnnuelEtalageToulon;
	}




	/**
	 * @param lienVentilationParBaremeAnnuelEtalageToulon the lienVentilationParBaremeAnnuelEtalageToulon to set
	 */
	public void setLienVentilationParBaremeAnnuelEtalageToulon(
			String lienVentilationParBaremeAnnuelEtalageToulon) {
		this.lienVentilationParBaremeAnnuelEtalageToulon = lienVentilationParBaremeAnnuelEtalageToulon;
	}




	/**
	 * @return the lienVentilationParBaremeAnnuelTLPEToulon
	 */
	public String getLienVentilationParBaremeAnnuelTLPEToulon() {
		return lienVentilationParBaremeAnnuelTLPEToulon;
	}




	/**
	 * @param lienVentilationParBaremeAnnuelTLPEToulon the lienVentilationParBaremeAnnuelTLPEToulon to set
	 */
	public void setLienVentilationParBaremeAnnuelTLPEToulon(
			String lienVentilationParBaremeAnnuelTLPEToulon) {
		this.lienVentilationParBaremeAnnuelTLPEToulon = lienVentilationParBaremeAnnuelTLPEToulon;
	}




	/**
	 * @return the lienVentilationParEmplacementAnnuelEtalageToulon
	 */
	public String getLienVentilationParEmplacementAnnuelEtalageToulon() {
		return lienVentilationParEmplacementAnnuelEtalageToulon;
	}




	/**
	 * @param lienVentilationParEmplacementAnnuelEtalageToulon the lienVentilationParEmplacementAnnuelEtalageToulon to set
	 */
	public void setLienVentilationParEmplacementAnnuelEtalageToulon(
			String lienVentilationParEmplacementAnnuelEtalageToulon) {
		this.lienVentilationParEmplacementAnnuelEtalageToulon = lienVentilationParEmplacementAnnuelEtalageToulon;
	}




	/**
	 * @return the lienVentilationParEmplacementAnnuelTLPEToulon
	 */
	public String getLienVentilationParEmplacementAnnuelTLPEToulon() {
		return lienVentilationParEmplacementAnnuelTLPEToulon;
	}




	/**
	 * @param lienVentilationParEmplacementAnnuelTLPEToulon the lienVentilationParEmplacementAnnuelTLPEToulon to set
	 */
	public void setLienVentilationParEmplacementAnnuelTLPEToulon(
			String lienVentilationParEmplacementAnnuelTLPEToulon) {
		this.lienVentilationParEmplacementAnnuelTLPEToulon = lienVentilationParEmplacementAnnuelTLPEToulon;
	}
}


