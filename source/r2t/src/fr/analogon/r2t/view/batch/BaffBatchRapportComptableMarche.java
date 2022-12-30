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
public class BaffBatchRapportComptableMarche extends fr.analogon.r2t.main.RacineBean 
{
	
	private HttpServletRequest request;	
	private int numeroBatch;	
	private String dateDeCreation="";	
	String lienRapportEncaissementJour;
	String lienRapportSuiviPresenceRedevable;
	String lienRapportRecapMensuelParFamilleMarche;
	String lienRapportRecapMoisParJour;
	String lienRapportRecuAbonne;
	

	public void setRequest(HttpServletRequest req)
	{
		try
		{	
			this.request = req;
			if (request.getParameter("numeroBatch") != null)
			{
				numeroBatch =Integer.parseInt(request.getParameter("numeroBatch"));
				RequestRaportComptable requestRaportComptable = new RequestRaportComptable();
				this.lienRapportEncaissementJour=
				"<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportEncaissementJour()+"EJ-"+numeroBatch+".pdf'>Encaissement Jour</a>";
				
				this.lienRapportSuiviPresenceRedevable=
				"<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportSuiviPresenceRedevable()+"SPR-"+numeroBatch+".pdf'>Suivi presence redevable </a>";
				
				this.lienRapportRecapMensuelParFamilleMarche=
				"<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportRecapMensuelParFamilleMarche()+"RFM-"+numeroBatch+".pdf'>Recapitulatif par famille marche</a>";
				
				this.lienRapportRecapMoisParJour=
				"<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportRecapMoisParJour()+"RMJ-"+numeroBatch+".pdf'>Recapitulatif mois par jour</a>";
				
				this.lienRapportRecuAbonne=
					"<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportRecuAbonne()+"RA-"+numeroBatch+".pdf'>Liste des recus des abonnes</a>";
				
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



	public String getLienRapportEncaissementJour() {
		return lienRapportEncaissementJour;
	}



	public void setLienRapportEncaissementJour(String lienRapportEncaissementJour) {
		this.lienRapportEncaissementJour = lienRapportEncaissementJour;
	}



	public String getLienRapportSuiviPresenceRedevable() {
		return lienRapportSuiviPresenceRedevable;
	}



	public void setLienRapportSuiviPresenceRedevable(
			String lienRapportSuiviPresenceRedevable) {
		this.lienRapportSuiviPresenceRedevable = lienRapportSuiviPresenceRedevable;
	}



	public String getLienRapportRecapMensuelParFamilleMarche() {
		return lienRapportRecapMensuelParFamilleMarche;
	}



	public void setLienRapportRecapMensuelParFamilleMarche(
			String lienRapportRecapMensuelParFamilleMarche) {
		this.lienRapportRecapMensuelParFamilleMarche = lienRapportRecapMensuelParFamilleMarche;
	}



	public String getLienRapportRecapMoisParJour() {
		return lienRapportRecapMoisParJour;
	}



	public void setLienRapportRecapMoisParJour(String lienRapportRecapMoisParJour) {
		this.lienRapportRecapMoisParJour = lienRapportRecapMoisParJour;
	}



	public String getLienRapportRecuAbonne() {
		return lienRapportRecuAbonne;
	}



	public void setLienRapportRecuAbonne(String lienRapportRecuAbonne) {
		this.lienRapportRecuAbonne = lienRapportRecuAbonne;
	}



	public HttpServletRequest getRequest() {
		return request;
	}


}


