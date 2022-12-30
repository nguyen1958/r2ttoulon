package fr.analogon.r2t.facturation.scriptBatch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.Batch;
import fr.analogon.r2t.request.RequestBatch;
import fr.analogon.r2t.request.RequestFacture;
import fr.analogon.r2t.request.RequestPleiade;


/**	-----------------------------------------------------------------------<br>
* @author 	Sofien CHARFI
* 20/09/2008	16h10<br>
*-----------------------------------------------------------------------<br>
*Classe qui permet de lire le fichier envoyer de Pleaide et de maj le BD
*
*-----------------------------------------------------------------------<br>
*/
public class LectureFichierPleiade
{
	int numeroBatch;
	DebuggerLog4J debug = new DebuggerLog4J();
	public static void  lectureEtMajBD( String cheminFichierPleiade ) 
	{
		RequestFacture reqFacture = new RequestFacture();
		RequestPleiade requestPleiade  = new  RequestPleiade (); 
		try
		{
			////////////////////////////////////////
			//Recuperation du nom du fichier
			////////////////////////////////////////				
			DebuggerLog4J.logger.debug("cheminFichier"+cheminFichierPleiade);
			BufferedReader  in = new BufferedReader(new FileReader(cheminFichierPleiade));
			String line=in.readLine();			
			while (line !=null )
			{
				DebuggerLog4J.logger.debug("ligne="+line);
				//Lecture du fichier Pleaide et maj de la facture dans la BD 
				if(!line.startsWith("*"))
				{
					//DebuggerLog4J.logger.debug(line);
					String numeroPretitre=line.substring(33, 41); 
					String numeroTitre=line.substring(41, 49);
					String solde=line.substring(72, 85);
					DebuggerLog4J.logger.debug("numeroPretitre"+numeroPretitre);
					DebuggerLog4J.logger.debug("numeroTitre"+numeroTitre);
					DebuggerLog4J.logger.debug("solde"+solde);
					requestPleiade.majInfosFactureFromPleaide( numeroPretitre , numeroTitre , solde );
				}
				line = in.readLine();
			} 

			//////////////////////////////////////////////////////////////////////////
			//Recuperation des données a parir du nuemero de batch et MAJ de La BD
			/////////////////////////////////////////////////////////////////////////	    	    
			
		}
		catch (IOException e)
		{
			DebuggerLog4J.logger.fatal("Erreur , imposible de lire le fichier " + cheminFichierPleiade + e.getMessage()
					+  e.getMessage() + e.getCause());			
			
		} 
		catch (Exception e) 
		{			
			DebuggerLog4J.logger.fatal("Erreur  " +  e.getMessage()+  e.getMessage() + e.getCause());
			
		}
		
	}

	public LectureFichierPleiade( String cheminFichier) 
	{
		this.lectureEtMajBD(cheminFichier);
	}
	
	public LectureFichierPleiade( int numeroBatch) 
	{
		this.numeroBatch =numeroBatch;
		String cheminFichier;
		////////////////////////////////////////
		//Recuperation du nom du fichier
		////////////////////////////////////////
		//fromPleiade\2008-1302\toPleiade_DDV_2008_1302.txt
		
		RequestBatch reqBatch = new RequestBatch();				
		Batch batch = reqBatch.getBatch(numeroBatch);	
		FichierDeConfiguration  fichierDeConfiguration = new FichierDeConfiguration();
		//fichierDeConfiguration
		cheminFichier = fichierDeConfiguration.getCheminFichierFromPleiade() + batch.getAnneeExercice()+"-"+ numeroBatch +"/" +
						"fromPleiade_"+batch.getTypeEmplacement()+"_"+
						batch.getAnneeExercice()+"_"+numeroBatch+".txt";
		
		this.lectureEtMajBD(cheminFichier);
	}
	
	
	public static void main(String[] args) 
	{	
		DebuggerLog4J debug = new DebuggerLog4J();
		try 
		{
			if (args.length ==2 )
			{
				
				if (args[0].equalsIgnoreCase("B") )
				{
					
					//fichier source indiqué comme parametre d'entree
					LectureFichierPleiade c = new LectureFichierPleiade(Integer.valueOf(args[1]));
				}
				else if (args[0].equalsIgnoreCase("FP") )
				{
					
					//fichier source induqué comme parametre d'entree
					LectureFichierPleiade c = new LectureFichierPleiade(args[1]);
				}
				else
				{
					
					debug.logger.debug("L'appel de la methode doit etre :" +
							" o	LectureFichierPleiade  B   numeroBacth    \n" +
							" o	LectureFichierPleiade  FP  chemin vers fihcier fromPleiade.txt ");
				}
			}
			else
			{
					debug.logger.debug("Il faut 2 parametres d'entrees :" +
							" o	LectureFichierPleiade  B   numeroBacth    \n" +
							" o	LectureFichierPleiade  FP  chemin vers fihcier fromPleiade.txt ");
			}			
		} 
		catch (Exception e) 
		{
			debug.logger.fatal("Erreur "+  e.getStackTrace());	
			e.printStackTrace();
			System.out.println("-1");
		}
	}

	
}// Fin de la classe
