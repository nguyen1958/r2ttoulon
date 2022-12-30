package fr.analogon.r2t.facturation.pleiade;

import fr.analogon.r2t.facturation.scriptBatch.CreerDonneeFacture;
import fr.analogon.r2t.facturation.scriptBatch.LectureFichierPleiade;
import fr.analogon.r2t.main.DebuggerLog4J;

public class LancerPleiade
{	
	public LancerPleiade(CreerDonneeFacture cdf) 
	{
		int idBatchTraitement = cdf.getIdBatchTraitement(); 
		int nombreDeFactureCree = cdf.getNombreDeFactureCree();
		//////////////////////////////////////////
		//Traitement de Pleaide :
		//////////////////////////////////////////		
	    if (nombreDeFactureCree > 0 )
	    {	    		
			try
			{	
				DebuggerLog4J.logger.debug("Traitement pleiade ......................................................[ok]");							
			}
			catch (Exception e)
			{	
				DebuggerLog4J.logger.fatal(e.getMessage());
				DebuggerLog4J.logger.fatal("Traitement pleiade....................................................[Erreur]");
			}			
		}
	    
	    
	    //////////////////////////////////////////
		//MAJ de La table facture dans la BD, champs Titre et solde de la table facture:
		//////////////////////////////////////////		
	    if (nombreDeFactureCree > 0 )
	    {	    		
			try
			{				
				LectureFichierPleiade lfp = new LectureFichierPleiade(idBatchTraitement);
				DebuggerLog4J.logger.debug("Traitement du fichier recu de pleiade, MAJ DE LA BD ......................................................[ok]");
			}
			catch (Exception e)
			{	
				DebuggerLog4J.logger.fatal(e.getMessage());
				DebuggerLog4J.logger.fatal("Traitement du fichier recu de pleiade....................................................[Erreur]");
			}			
		}
	}
	
	
	public static void main(String[] args) 
	{		
		DebuggerLog4J.logger.debug("Date de lancment du batch="+args[0]);
		DebuggerLog4J.logger.debug("Type de Taxe="+args[1]);
		DebuggerLog4J.logger.debug("Numero de batch="+args[2]);				
		DebuggerLog4J.logger.debug("Traitment en cours , Emulation ....");
		try 
		{
			Thread.sleep(4000);
		}
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DebuggerLog4J.logger.debug("Termine pleaide terminé....[Ok]");
	}

	
	
}








