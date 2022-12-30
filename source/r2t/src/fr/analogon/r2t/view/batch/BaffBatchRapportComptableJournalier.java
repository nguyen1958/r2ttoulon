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
public class BaffBatchRapportComptableJournalier extends fr.analogon.r2t.main.RacineBean 
{
	
	private HttpServletRequest request;	
	private int numeroBatch;	
	private String dateDeCreation="";	
	String lienRapportListeQuittancesJournalierToulon;
	String lienRapportRecapitulatifComptesJournalierToulon;
	String lienRapportVersementChequesJournalierToulon;
	String lienRapportVersementJournalierToulon;
	String lienRapportVersementChequesJournalierCompteRegieToulon;
	String lienRapportVersementCarteBancaireJournalierToulon;

	public String getLienRapportVersementChequesJournalierCompteRegieToulon() {
		return lienRapportVersementChequesJournalierCompteRegieToulon;
	}



	public void setLienRapportVersementChequesJournalierCompteRegieToulon(
			String lienRapportVersementChequesJournalierCompteRegieToulon) {
		this.lienRapportVersementChequesJournalierCompteRegieToulon = lienRapportVersementChequesJournalierCompteRegieToulon;
	}



	public void setRequest(HttpServletRequest req)
	{
		try
		{	
			this.request = req;
			if (request.getParameter("numeroBatch") != null)
			{
				numeroBatch =Integer.parseInt(request.getParameter("numeroBatch"));
				RequestRaportComptable requestRaportComptable = new RequestRaportComptable();
				this.lienRapportVersementJournalierToulon=
				"<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportVersementJournalierToulon()+"VJ-"+numeroBatch+".pdf'>Versement journalier</a>";
				
				this.lienRapportListeQuittancesJournalierToulon=
				"<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportListeQuittancesJournalierToulon()+"LQJ-"+numeroBatch+".pdf'>Liste quittances journalier </a>";
				
				this.lienRapportRecapitulatifComptesJournalierToulon=
				"<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportRecapitulatifComptesJournalierToulon()+"CJ-"+numeroBatch+".pdf'>Recapitulatif comptes journalier</a>";
				
				this.lienRapportVersementChequesJournalierToulon=
				"<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportVersementChequesJournalierToulon()+"VCJ-"+numeroBatch+".pdf'>Versement cheques journalier</a>";
				
				
				this.lienRapportVersementChequesJournalierCompteRegieToulon=
					"<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportVersementChequesJournalierCompteRegieToulon()+"VCJCR-"+numeroBatch+".pdf'>Versement cheques journalier compte r�gie</a>";
				
				this.lienRapportVersementCarteBancaireJournalierToulon=
						"<a target='_blank' href ='"+fichierDeConfiguration.getLienRapportVersementCarteBancaireJournalierToulon()+"VCBJ-"+numeroBatch+".pdf'>Versement cartes bancaires journalier</a>";
						
					
				
				
				RaportComptable raportComptable = requestRaportComptable.getRapport(numeroBatch);
				dateDeCreation =raportComptable.getDateLancement();
			}
		}
		catch (Exception e) 
		{
			DebuggerLog4J.logger.fatal(e.getMessage());
		}
			
		/*
		try
		{	
		
		
		this.request = req;
		if (request.getParameter("numeroBatch") != null) 
		{ this.numeroBatch = Integer.parseInt(request.getParameter("numeroBatch"));	}
		
			RequestBatch reqBatch = new RequestBatch();
			Batch batch= reqBatch.getBatch(numeroBatch);
			this.anExercice = batch.getDateExecution();
			this.typeDeTaxe = batch.getTypeEmplacement();
			this.numeroBatch = Integer.parseInt(batch.getNumeroBatchTraitement());
			this.dateDeCreation = batch.getDateExecution();
			this.nombreFactures = batch.getNombreFactures();
			this.nomFichierToPleaide = batch.getNomFichierEnvoyeAPleiade();
			this.nomFichierFromPleaide = "fromPleiade_"+typeDeTaxe+"_"+batch.getAnneeExercice()+"_"+ numeroBatch+".txt";
			this.listeFactures= batch.getListeFactures();
			this.editerDesFactureAnnulee = batch.getAEditerDesFactureAnnulee();
			this.aEditerRoleChangementAdresseRedevable = batch.getAEditerRoleChangementAdresseRedevable(); 
			RequestBatch reqBatch1 = new RequestBatch();
			
			Batch batch1 = reqBatch1.getBatch(numeroBatch);
			
			
			
			String s= batch.getAnneeExercice()+"-"+batch.getNumeroBatchTraitement();			
			lienFichierToPleaide=lienFichierToPleaide+fichierDeConfiguration.getLienFichierToPleaide()+s+"/"+ nomFichierToPleaide;		
			
			lienFichierFromPleaide=lienFichierFromPleaide+fichierDeConfiguration.getLienFichierFromPleaide()+s+"/"+ nomFichierFromPleaide;		
			
			lienFichierDesFactures = lienFichierDesFactures +fichierDeConfiguration.getLienFichierDesFactures()  +batch.getNomDossier()+"/"+"BatchNumero"+numeroBatch+".pdf";
								
			lienCertificatAnnulationRecette=lienCertificatAnnulationRecette+fichierDeConfiguration.getLienCertificatAnnulationRecette() +"RAR_"+numeroBatch+".pdf";		
								
			lienRoleFacturation=lienRoleFacturation+fichierDeConfiguration.getLienRoleFacturation()+"RF_"+numeroBatch+".pdf";		
						
			lienRoleJustificatif=lienRoleJustificatif+fichierDeConfiguration.getLienRoleJustificatif()+"REJR_"+numeroBatch+".pdf";		
									
			lienRoleAnnulation=lienRoleAnnulation+fichierDeConfiguration.getLienRoleAnnulation()+"RA_"+numeroBatch+".pdf";		
	
			lienaRoleChangementAdresseRedevable= lienaRoleChangementAdresseRedevable +
				fichierDeConfiguration.getLienRoleChgtAdresseRedevable() +"RCAR_"+batch.getAEditerRoleChangementAdresseRedevable()+".pdf";
				
			
		}
		catch (Exception e) 
		{
			DebuggerLog4J.logger.fatal(e.getMessage());
		}
		*/			
				
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



	public String getLienRapportListeQuittancesJournalierToulon() {
		return lienRapportListeQuittancesJournalierToulon;
	}



	public void setLienRapportListeQuittancesJournalierToulon(
			String lienRapportListeQuittancesJournalierToulon) {
		this.lienRapportListeQuittancesJournalierToulon = lienRapportListeQuittancesJournalierToulon;
	}



	public String getLienRapportRecapitulatifComptesJournalierToulon() {
		return lienRapportRecapitulatifComptesJournalierToulon;
	}



	public void setLienRapportRecapitulatifComptesJournalierToulon(
			String lienRapportRecapitulatifComptesJournalierToulon) {
		this.lienRapportRecapitulatifComptesJournalierToulon = lienRapportRecapitulatifComptesJournalierToulon;
	}



	public String getLienRapportVersementChequesJournalierToulon() {
		return lienRapportVersementChequesJournalierToulon;
	}



	public void setLienRapportVersementChequesJournalierToulon(
			String lienRapportVersementChequesJournalierToulon) {
		this.lienRapportVersementChequesJournalierToulon = lienRapportVersementChequesJournalierToulon;
	}



	public String getLienRapportVersementJournalierToulon() {
		return lienRapportVersementJournalierToulon;
	}



	public void setLienRapportVersementJournalierToulon(
			String lienRapportVersementJournalierToulon) {
		this.lienRapportVersementJournalierToulon = lienRapportVersementJournalierToulon;
	}



	public String getLienRapportVersementCarteBancaireJournalierToulon() {
		return lienRapportVersementCarteBancaireJournalierToulon;
	}



	public void setLienRapportVersementCarteBancaireJournalierToulon(
			String lienRapportVersementCarteBancaireJournalierToulon) {
		this.lienRapportVersementCarteBancaireJournalierToulon = lienRapportVersementCarteBancaireJournalierToulon;
	}
	
	


}


