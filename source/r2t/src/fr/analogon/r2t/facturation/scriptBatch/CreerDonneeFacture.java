package fr.analogon.r2t.facturation.scriptBatch;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.facturation.CreerRoleFacturation;
import fr.analogon.r2t.facturation.pleiade.CreerFichierPleiade;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.Batch;
import fr.analogon.r2t.request.RequestBatch;
import fr.analogon.r2t.request.RequestCreationFactures;
import fr.analogon.r2t.request.RequestFacture;
import fr.analogon.r2t.request.RequestParametres;

public class CreerDonneeFacture
{
	int idBatchTraitement =0;
	int nombreDeFactureCree;
	int nombreRemboursementsCree;
	String anneeExercice="";
	RequestCreationFactures lesFactures;
	String resRCAR ="false";
	int numeroBatchChangementAdresse =0;
	DebuggerLog4J debug = new DebuggerLog4J();
	public CreerDonneeFacture(String dateExecution, String typeTaxe, String etapeFacturation,
			String anneeFacturationTLPE) 
	{		
		/////////////////////////////////////////////
		//LANCEMENT DU RAPPORT CHANGEMENT ADRESSE:
		/////////////////////////////////////////////
	    try
		{
	    	RequestBatch reqBatch = new RequestBatch();
	    	CreerRoleFacturation cr =  new CreerRoleFacturation();
			numeroBatchChangementAdresse = reqBatch.ajouterLigneBatchChangementAdresse(dateExecution);
			resRCAR = cr.lancerCreerRoleFacturation("RCAR",numeroBatchChangementAdresse,"","","","","","");		
			debug.logger.debug("Creation du Role changement adresse redevable......................................................[ok]");
			if (resRCAR.equalsIgnoreCase("true"))
			{				
				reqBatch.majBatchChangementAdresse(""+numeroBatchChangementAdresse, "true");			
			}
			else
			{				
				reqBatch.majBatchChangementAdresse(""+numeroBatchChangementAdresse, "false");
			}
		}
		catch (Exception e)
		{	
			debug.logger.fatal("Erreur " +e.getMessage());
			debug.logger.fatal("Creation du role changement Adresse redevable.......................................[Erreur]");
		}	
		
		
		//////////////////////////////////////////
		//Creation des donnees des factures:
		//////////////////////////////////////////
		lesFactures= new RequestCreationFactures();	
		RequestFacture reqFacture = new RequestFacture();
		RequestBatch reqBatch = new RequestBatch();		
		try 
		{			
			debug.logger.debug("Infos : Type de taxe = "+ typeTaxe +",date lancement batch"+dateExecution+"..........."); 
			idBatchTraitement =lesFactures.generFacture(dateExecution, typeTaxe,etapeFacturation,"",
					GestionDate.getAnneeFromDateString(dateExecution),anneeFacturationTLPE);			
			debug.logger.debug("Creation de donnees des factures, batch numero "+ idBatchTraitement+" ......................................................[ok]");
		}
		catch (Exception e) 
		{
			debug.logger.debug("Creation de donnees des factures ......................................................[Erreur]"+e.getMessage());
			e.printStackTrace();
		}	
		Batch batch = reqBatch.getBatch(idBatchTraitement);
		nombreDeFactureCree=  reqFacture.getNombreDeFactureCreeParUnBatch(idBatchTraitement);
		nombreRemboursementsCree = Integer.valueOf(batch.getNombreRemboursements());
		
		anneeExercice=  batch.getAnneeExercice();
		if (resRCAR.equalsIgnoreCase("true"))
		{				
			reqBatch.majBatchAEditerRoleChangementAdresseRedevable(""+idBatchTraitement, ""+numeroBatchChangementAdresse);			
		}
		else
		{				
			reqBatch.majBatchAEditerRoleChangementAdresseRedevable(""+idBatchTraitement, "false");
		}
		
		
		
		
		
		
		//////////////////////////////////////////
		//Creation du fichier a envoyer a Pleaide:
		//////////////////////////////////////////		
	    if (nombreDeFactureCree > 0  || reqFacture.getNombreDeFactureAnnuleAEnvoyerToPleiade(typeTaxe )> 0 )  
	    {	    		
			try
			{	
				
				//Selon la ville
				RequestParametres requestParametres = new RequestParametres();
				String ville =  requestParametres.getVille();
				if(ville.equalsIgnoreCase("Bordeaux"))
				{					 
					debug.logger.debug("Creation de fichier a envoye a pleiade ......................................................[ok]");
					CreerFichierPleiade cfp = new CreerFichierPleiade(idBatchTraitement);
				}				
			}
			catch (Exception e)
			{	
				debug.logger.fatal("Creation de fichier a envoye a pleiade....................................................[Erreur]");
				debug.logger.fatal(e.getMessage()+ e.getMessage() + e.getCause());
				
			}			
		}		
	}
	
	

	/**
	 * @return the anneeExercice
	 */
	public final String getAnneeExercice() {
		return anneeExercice;
	}

	/**
	 * @return the idBatchTraitement
	 */
	public final int getIdBatchTraitement() {
		return idBatchTraitement;
	}

	/**
	 * @return the nombreDeFactureCree
	 */
	public final int getNombreDeFactureCree() {
		return nombreDeFactureCree;
	}

	/**
	 * @return the lesFactures
	 */
	public final RequestCreationFactures getLesFactures() {
		return lesFactures;
	}

	/**
	 * @return the nombreRemboursementsCree
	 */
	public int getNombreRemboursementsCree() {
		return nombreRemboursementsCree;
	}

	/**
	 * @param nombreRemboursementsCree the nombreRemboursementsCree to set
	 */
	public void setNombreRemboursementsCree(int nombreRemboursementsCree) {
		this.nombreRemboursementsCree = nombreRemboursementsCree;
	}

	

	public static void main(String[] args) 
	{
		DebuggerLog4J debug = new DebuggerLog4J();
		try 
		{
			System.out.println("\nParametres pour le script:  args1=dateLancementBatch ; args2=typeTaxe ; args3=anneeFacturationTLPE (que pour la TLPE)");
			String dateLancementBatch=args[0];
			String typeTaxe=args[1];			
			String anneeFacturationTLPE="";
			if(typeTaxe.equalsIgnoreCase("tlpe") && args[2].length() != 0)
				anneeFacturationTLPE=args[2];
			CreerDonneeFacture cdf = new CreerDonneeFacture(dateLancementBatch,typeTaxe,"facturation",anneeFacturationTLPE);
			//Retourner le numeo de batch
			System.out.println("\nNouveauNumeroBatch="+ cdf.idBatchTraitement);			
		}
		catch (Exception e) 
		{
			//En cas d'erreur l'application retourne -1
			System.out.println("\nNouveauNumeroBatch="+ "-1");	
		}
		
	}
	
	
}








