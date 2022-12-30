package fr.analogon.r2t.facturation.scriptBatch;

import fr.analogon.r2t.facturation.CreerFactures;
import fr.analogon.r2t.facturation.CreerRemboursement;
import fr.analogon.r2t.facturation.CreerRoleFacturation;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.Batch;
import fr.analogon.r2t.request.RequestBatch;
import fr.analogon.r2t.request.RequestCreationFactures;
import fr.analogon.r2t.request.RequestFacture;

public class ImprimerDonneeFacturePDF
{
	DebuggerLog4J debug = new DebuggerLog4J();
	public ImprimerDonneeFacturePDF(int idBatch) 
	{	
		RequestBatch reqBatch = new RequestBatch();
		Batch batch=reqBatch.getBatch(idBatch);		
		RequestFacture reqFacture = new RequestFacture();
		int nombreDeFactureCree = reqFacture.getNombreDeFactureCreeParUnBatch(idBatch);		
		debug.logger.debug("////////////////////////////////////////////");		
		debug.logger.debug("BATCH Numero ="+ idBatch + " NOMBRE DE FACTURE CREEES ="+ nombreDeFactureCree);
		debug.logger.debug("////////////////////////////////////////////");
		String anneeExercice= batch.getAnneeExercice();
		//////////////////////////////////////////
		//IMPRESSION DES FACTURES SUR LE FICHIERS PDF:
		//////////////////////////////////////////
	    if (nombreDeFactureCree > 0 )
	    {	    		
			try
			{	
				debug.logger.debug("\nImpression des factures PDF ......................................................");
				CreerFactures cf = new CreerFactures(idBatch,"normale");
				debug.logger.debug("Impression des factures PDF ......................................................[ok]");
				
			}
			catch (Exception e)
			{	
				debug.logger.fatal(e.getMessage());
				debug.logger.fatal("Impression des factures PDF......................................[Erreur]");
			}			
		}
	    
	    String Ra="false",Rar="false";
	    
	    debug.logger.debug("idBatch="+idBatch);
	    debug.logger.debug("anneeExercice="+anneeExercice);
	    debug.logger.debug("Type De taxe="+batch.getTypeEmplacement());
	    /////////////////////////////////////////////
		//CREATION DU CERTFICAT ANNULATION DE RECETTE:
		/////////////////////////////////////////////
	    try
		{
	    	debug.logger.debug("\nCreation du role certificat d'annulation de recette ......................................................");
			CreerRoleFacturation cr =  new CreerRoleFacturation();
			Ra= cr.lancerCreerRoleFacturation("RAR",idBatch,anneeExercice,"", batch.getTypeEmplacement(),"","","");
			debug.logger.debug("Creation du role certificat d'annulation ......................................................[ok]");
			
		}
		catch (Exception e)
		{	
			debug.logger.fatal(e.getMessage());
			debug.logger.fatal("Creation du role certificat d'annulation.......................................[Erreur]");
		}
	    
		 debug.logger.debug("idBatch="+idBatch);
		 debug.logger.debug("anneeExercice="+anneeExercice);
		 debug.logger.debug("Type De taxe="+batch.getTypeEmplacement());
	    /////////////////////////////////////////////
		//CREATION DU RAPPORT ANNULATION :
		/////////////////////////////////////////////
	    try
		{
			debug.logger.debug("\nCreation du role annulation de recette ......................................................");
			CreerRoleFacturation cr =  new CreerRoleFacturation();
			Rar = cr.lancerCreerRoleFacturation("RA",idBatch,anneeExercice,"", batch.getTypeEmplacement(),"","","");
			debug.logger.debug("Creation du role annulation de recette ......................................................[ok]");					
		}
		catch (Exception e)
		{	
			debug.logger.fatal(e.getMessage());
			debug.logger.fatal("Creation du role annulation de recette.......................................[Erreur]");
		}		
		
	    
		
	 
		
	  
		
		
		
		
			
		
		/////////////////////////////////////////////
		//CREATION DU RAPPORT FACTURATION:
		/////////////////////////////////////////////
	    try
		{
	    	debug.logger.debug("\nCreation du role facturation ......................................................");
			CreerRoleFacturation cr =  new CreerRoleFacturation();
			cr.lancerCreerRoleFacturation("RF",idBatch,anneeExercice,"","","","","");
			debug.logger.debug("Creation du role facturation ......................................................[ok]");
		}
		catch (Exception e)
		{	
			debug.logger.fatal(e.getMessage());
			debug.logger.fatal("Creation du role facturation.......................................[Erreur]");
		}
		
		////////////////////////////////////////////////
		//CREATION DU RAPPORT JUSTIFICATIF DE RECETTE :
		////////////////////////////////////////////////
	    try
		{
	    	debug.logger.debug("\nCreation du role justificatif de recette ......................................................");
			CreerRoleFacturation cr =  new CreerRoleFacturation();
			cr.lancerCreerRoleFacturation("REJR",idBatch,anneeExercice,"","","","","");
			debug.logger.debug("Creation du role justificatif de recette ......................................................[ok]");
		}
		catch (Exception e)
		{	
			debug.logger.fatal(e.getMessage());
			debug.logger.fatal("Creation du role justificatif de recette.......................................[Erreur]");
		} 
		
		
		
		////////////////////////////////////////////////
		//CREATION DU RAPPORT REMBOURSEMENT :
		////////////////////////////////////////////////
	    try
		{
	    	debug.logger.debug("\nCreation du rapport remboursement ......................................................");
			CreerRemboursement creerRemboursement =  new CreerRemboursement(idBatch);
			debug.logger.debug("Creation du rapport remboursement ......................................................[ok]");
			
			debug.logger.debug("\nCreation du rapport role remboursement ......................................................");
			CreerRoleFacturation cr2 =  new CreerRoleFacturation();
			cr2.lancerCreerRoleFacturation("RRem",idBatch,anneeExercice,"","","","","");
			debug.logger.debug("Creation du rapport role remboursement ......................................................[ok]");
		}
		catch (Exception e)
		{	
			debug.logger.fatal(e.getMessage());
			debug.logger.fatal("Creation de rapport remboursement.......................................[Erreur]");
			debug.logger.fatal("Creation du rapport role remboursement de recette.......................................[Erreur]");
		} 
	   
	    
	    //////////////////////////////////////////
		//VALIDATION DU BATCH
		//////////////////////////////////////////	
		try
		{
				debug.logger.debug("Validation du batch.......................................................") ;
			    RequestCreationFactures lesFactures= new RequestCreationFactures() ;	
				//lesFactures.validerBatch(idBatch) ; 
				if(Ra.equalsIgnoreCase("true") || Rar.equalsIgnoreCase("true") )
				{
					RequestBatch reqBatch1 = new RequestBatch();	
					reqBatch1.majBatchAEditerFactureAnnule(""+idBatch, Ra );
				}
				debug.logger.debug("Validation du batch.......................................................[ok]");
		}
		catch (Exception e)
		{	
				debug.logger.fatal(e.getMessage());
				debug.logger.fatal("Validation du batch...............................................................[Erreur]");
		}
		
	}
	
	
	
	public static void main(String[] args) 
	{	
		DebuggerLog4J debug = new DebuggerLog4J();
		try 
		{			
			if(args.length == 1)
			{			
				debug.logger.debug("Impression des factures crees par le batch numero: "+ args[0]); 
				System.out.println("TEST3");
				debug.logger.debug("Impression en cours ....");
				ImprimerDonneeFacturePDF im = new ImprimerDonneeFacturePDF(Integer.valueOf(args[0]));		
				debug.logger.debug("Validation du batch ....[OK]");					
			}
			else
			{
				debug.logger.debug("Il faut indiquer le numero de batch ");				
			}
		}
		catch (Exception e) 
		{
			System.out.println("-1");			
			e.printStackTrace();
			debug.logger.debug("Erreur " + e.getMessage());
			
		}
		
	}
	
	
	
}









