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
public class BaffBatchRapportComptableMensuelle extends fr.analogon.r2t.main.RacineBean 
{
	
	private HttpServletRequest request;	
	private int numeroBatch;	
	private String dateDeCreation="";	
	String lienRapportVersementMensuelToulon;
	String lienRapportRecapitulatifComptesMensuelToulon;

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
				
				this.lienRapportVersementMensuelToulon = "<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportVersementMensuelToulon()+"VM-"+numeroBatch+".pdf'>Versement mensuel</a>";
								
				this.lienRapportRecapitulatifComptesMensuelToulon=
					"<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportRecapitulatifComptesMensuelToulon()+"CM-"+numeroBatch+".pdf'>Recapitulatif comptes mensuel</a>";
				
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



	public String getLienRapportRecapitulatifComptesMensuelToulon() {
		return lienRapportRecapitulatifComptesMensuelToulon;
	}



	public void setLienRapportRecapitulatifComptesMensuelToulon(
			String lienRapportRecapitulatifComptesMensuelToulon) {
		this.lienRapportRecapitulatifComptesMensuelToulon = lienRapportRecapitulatifComptesMensuelToulon;
	}



	public String getLienRapportVersementMensuelToulon() {
		return lienRapportVersementMensuelToulon;
	}



	public void setLienRapportVersementMensuelToulon(
			String lienRapportVersementMensuelToulon) {
		this.lienRapportVersementMensuelToulon = lienRapportVersementMensuelToulon;
	}
}


