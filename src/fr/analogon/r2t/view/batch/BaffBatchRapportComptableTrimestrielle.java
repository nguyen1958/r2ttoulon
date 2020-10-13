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
public class BaffBatchRapportComptableTrimestrielle extends fr.analogon.r2t.main.RacineBean 
{
	
	private HttpServletRequest request;	
	private int numeroBatch;	
	private String dateDeCreation="";	
	
	String lienBalanceTrimestrielToulon="";
	String lienVentilationParBaremeTrimestrielToulon="";
	String lienVentilationParEmplacementTrimestrielToulon="";
	
	String lienListeDesAcomptesTrimestrielToulon="";
	String lienListeDesAnnulationsTrimestrielToulon="";
	String lienListeDesImpayesTrimestrielToulon="";

	

	public void setRequest(HttpServletRequest req)
	{
		try
		{	
			this.request = req;
			if (request.getParameter("numeroBatch") != null)
			{
				numeroBatch =Integer.parseInt(request.getParameter("numeroBatch"));
				RequestRaportComptable requestRaportComptable = new RequestRaportComptable();
				DebuggerLog4J.logger.debug("LienRapportVersementMensuelToulon="+fichierDeConfiguration.getLienRapportVersementMensuelToulon());
				
				this.lienBalanceTrimestrielToulon=	"<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportBalanceTrimestrielToulon()+"BT-"+numeroBatch+".pdf'>Balance trimestrielle </a>";
				this.lienVentilationParBaremeTrimestrielToulon=	"<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportVentilationParBaremeTrimestrielToulon()+"VPBT-"+numeroBatch+".pdf'>Ventilation par bareme trimestrielle</a>";
				this.lienVentilationParEmplacementTrimestrielToulon=	"<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportVentilationParEmplacementTrimestrielToulon()+"VPET-"+numeroBatch+".pdf'>Ventilation par emplacement trimestrielle</a>";
	
	
				this.lienListeDesAcomptesTrimestrielToulon=	"<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportListeDesAcomptesTrimestrielToulon()+"LACT-"+numeroBatch+".pdf'>Liste des acomptes trimestriel </a>";
				this.lienListeDesAnnulationsTrimestrielToulon =	"<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportListeDesAnnulationsTrimestrielToulon()+"LANT-"+numeroBatch+".pdf'>Liste des annulations trimestriel</a>";
				this.lienListeDesImpayesTrimestrielToulon=	"<a target='_blank' href ='"+fichierDeConfiguration.getLienListeDesImpayesTrimestrielToulon()+"LIT-"+numeroBatch+".pdf'>Liste des impayes trimestriel</a>";
				RaportComptable raportComptable = requestRaportComptable.getRapport(numeroBatch);
				dateDeCreation =raportComptable.getDateLancement();
			}
		}
		catch (Exception e) 
		{
			DebuggerLog4J.logger.fatal(e.getMessage());
		}
							
	}



	public String getDateDeCreation() {
		return dateDeCreation;
	}



	public void setDateDeCreation(String dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}



	


	public int getNumeroBatch() {
		return numeroBatch;
	}



	public void setNumeroBatch(int numeroBatch) {
		this.numeroBatch = numeroBatch;
	}



	public String getLienBalanceTrimestrielToulon() {
		return lienBalanceTrimestrielToulon;
	}



	public void setLienBalanceTrimestrielToulon(String lienBalanceTrimestrielToulon) {
		this.lienBalanceTrimestrielToulon = lienBalanceTrimestrielToulon;
	}


	public String getLienVentilationParBaremeTrimestrielToulon() {
		return lienVentilationParBaremeTrimestrielToulon;
	}



	public void setLienVentilationParBaremeTrimestrielToulon(
			String lienVentilationParBaremeTrimestrielToulon) {
		this.lienVentilationParBaremeTrimestrielToulon = lienVentilationParBaremeTrimestrielToulon;
	}



	public String getLienVentilationParEmplacementTrimestrielToulon() {
		return lienVentilationParEmplacementTrimestrielToulon;
	}



	public void setLienVentilationParEmplacementTrimestrielToulon(
			String lienVentilationParEmplacementTrimestrielToulon) {
		this.lienVentilationParEmplacementTrimestrielToulon = lienVentilationParEmplacementTrimestrielToulon;
	}



	public String getLienListeDesAcomptesTrimestrielToulon() {
		return lienListeDesAcomptesTrimestrielToulon;
	}



	public void setLienListeDesAcomptesTrimestrielToulon(
			String lienListeDesAcomptesTrimestrielToulon) {
		this.lienListeDesAcomptesTrimestrielToulon = lienListeDesAcomptesTrimestrielToulon;
	}



	public String getLienListeDesAnnulationsTrimestrielToulon() {
		return lienListeDesAnnulationsTrimestrielToulon;
	}



	public void setLienListeDesAnnulationsTrimestrielToulon(
			String lienListeDesAnnulationsTrimestrielToulon) {
		this.lienListeDesAnnulationsTrimestrielToulon = lienListeDesAnnulationsTrimestrielToulon;
	}



	public String getLienListeDesImpayesTrimestrielToulon() {
		return lienListeDesImpayesTrimestrielToulon;
	}



	public void setLienListeDesImpayesTrimestrielToulon(
			String lienListeDesImpayesTrimestrielToulon) {
		this.lienListeDesImpayesTrimestrielToulon = lienListeDesImpayesTrimestrielToulon;
	}



	
}


