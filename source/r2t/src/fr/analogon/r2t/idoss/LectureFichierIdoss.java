package fr.analogon.r2t.idoss;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.request.RequestIdoss;



/**	-----------------------------------------------------------------------<br>
* @author 	Sofien CHARFI
* 20/09/2008	16h10<br>
*-----------------------------------------------------------------------<br>
*Classe qui permet de lire le fichier idosss et mettrea a jour 
*le champs infosIdoss au niveau de l'empalcment 
*le champs dateFinAutorisation au niveau de l'ouvrage *
*-----------------------------------------------------------------------<br>
*/
public class LectureFichierIdoss
{
	
	public LectureFichierIdoss(int idBatchIdoss) 
	{
		String typeTaxe ="";
		String numeroIdoss ="";
		String numRue ="";
		String codeRivolie ="";
		String nomRedevable ="";
		String typeInfos ="";
		String infosIdoss ="";
		String dateDebut ="";
		String dateFin ="";
		RequestIdoss requestIdoss = new RequestIdoss();
		FichierDeConfiguration fichierDeConfiguration = new FichierDeConfiguration(); 
		String cheminFichierSource ="";
				
		try
		{
			////////////////////////////////////////
			//Recuperation du nom du fichier
			////////////////////////////////////////		
			//r2t\r2tData\data\idoss\idoss.txt
			cheminFichierSource = fichierDeConfiguration.getCheminIdoss()+ "idoss.txt";
			
			//cheminFichierSource ="";			
			//cheminFichierSource ="D:/idoss.txt";
			DebuggerLog4J.logger.debug("Fichier Idoss="+cheminFichierSource);
			BufferedReader  in = new BufferedReader(new FileReader(cheminFichierSource));			
			//DebuggerLog4J.logger.debug("cheminFichierSource Idoss="+cheminFichierSource);			
			//Suppresion des infos idoss au nivea de l'aempclament 			
			//requestPleiade1.suuprimerInofsIdoss();
			//requestPleiade1.suuprimerDteFinAutorisationIoss();
			
			
			String line=in.readLine();	
			boolean memeTraitment= true;
			while (line !=null && line.length() != 0 )
			{
				DebuggerLog4J.logger.debug("ligne="+line);
				//Lecture du fichier Idoss et maj de la facture dans la BD
				//System.out.println(line);
				StringTokenizer st = new StringTokenizer(line,"|");
				int i = 0;				
				while (st.hasMoreElements()) 
				{					
					String value= (String)st.nextElement();
					
					if(i==0) typeTaxe = value;
					else if(i==1) numeroIdoss = value;					
					else if(i==2) numRue = value;
					else if(i==3) codeRivolie = value;
					else if(i==4) nomRedevable = value;
					else if(i==5) typeInfos = value;									
					if(typeInfos.equalsIgnoreCase("INFO"))
					{					
						if(i==6) infosIdoss = value;
					}
					if(typeInfos.equalsIgnoreCase("DATE"))
					{
						if(i==6) dateDebut = value;
						else if(i==7) dateFin = value;
					}
					i++;
				}			
				/*
				System.out.println("-------------------MAJ BD-------------------");
				System.out.println("typeTaxe =" + typeTaxe);
				System.out.println(" numeroIdoss =" + numeroIdoss );
				System.out.println(" numRue =" + numRue );
				System.out.println(" codeRivolie ="+ codeRivolie );
				System.out.println(" nomRedevable ="+ nomRedevable );
				System.out.println(" flag ="+ typeInfos );
				System.out.println(" infos ="+ infosIdoss );
				System.out.println(" dateDebut ="+ dateDebut );
				System.out.println("dateFin ="+ dateFin );
				System.out.println("---------------------------------------------");
				*/
				requestIdoss.ajouterLigneRapprochemetnIdoss(idBatchIdoss,typeTaxe, numRue,
						codeRivolie, nomRedevable,
						typeInfos, infosIdoss, dateDebut, dateFin, line);
				/*
				//CAS D'UNE MAJ DU CHAMP Infos pour l'amplcement
				if(flag.equalsIgnoreCase("INFO"))
				{	
					requestIdoss.majInofsIdoss(typeTaxe, numRue, codeRivolie, nomRedevable, infos, memeTraitment);
					memeTraitment =false;					
				}
				//CAS MIS A JOUR DE LA DATE DE FIN D'AUTORISATION IDOSS
				else if(flag.equalsIgnoreCase("DATE"))
				{				
					
					requestIdoss.majInofsDate(typeTaxe, numRue, codeRivolie, 
							nomRedevable, dateDebut, dateFin);
					
				}
				*/
				line = in.readLine();
				typeTaxe ="";
				numeroIdoss ="";
				numRue ="";
				codeRivolie ="";
				nomRedevable ="";
				typeInfos ="";
				infosIdoss ="";
				dateDebut ="";
				dateFin ="";
			} 
			requestIdoss.majNombreLigne(""+idBatchIdoss);
			
			

			//////////////////////////////////////////////////////////////////////////
			//Recuperation des données a parir du nuemero de batch et MAJ de La BD
			/////////////////////////////////////////////////////////////////////////	    	    
			
		}
		catch (IOException e)
		{
			DebuggerLog4J.logger.fatal("Erreur , imposible de lire le fichier " + cheminFichierSource + e.getMessage()
					+  e.getMessage() + e.getCause());			
			
		} 
		catch (Exception e) 
		{			
			//DebuggerLog4J.logger.fatal("Erreur  " +  e.getMessage()+  e.getMessage() + e.getCause());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		LectureFichierIdoss c = new LectureFichierIdoss(0);
	}
}// Fin de la classe
