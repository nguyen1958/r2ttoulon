package fr.analogon.r2t.facturation.pleiade;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.pojo.Batch;
import fr.analogon.r2t.request.RequestBatch;
import fr.analogon.r2t.request.RequestFacture;
import fr.analogon.r2t.request.RequestPleiade;


/**	-----------------------------------------------------------------------<br>
* @author 	Sofien CHARFI
* 20/09/2008	16h10<br>
*-----------------------------------------------------------------------<br>
*Classe qui permet de creer un fichier a envoyer a Pleaide
*
*-----------------------------------------------------------------------<br>
*/
public class PleaideEmulator
{
	int numeroBatch;
	public PleaideEmulator(int numeroBatch) 
	{
		this.numeroBatch = numeroBatch;
		String nomFichier="";
		FichierDeConfiguration  fichierDeConfiguration = InitialisationConnexionLectureConfiguration.fichierDeConfiguration;
		
		try
		{	
			RequestFacture req = new RequestFacture();
			RequestBatch reqbatch = new RequestBatch();
			RequestPleiade requestPleiade  = new  RequestPleiade (); 
			Batch batch = reqbatch.getBatch(numeroBatch);
			String typeDeTaxe = batch.getTypeEmplacement();
			String jour = String.valueOf(new Date().getDate());
			String mois = String.valueOf(new Date().getMonth());
			String annee = String.valueOf(new Date().getYear()+1900);
			String heure = String.valueOf(new Date().getHours());
			String minute = String.valueOf(new Date().getMinutes());
			if ( Integer.parseInt(heure) <10 ) heure = "0"+heure;
			if ( Integer.parseInt(minute) <10 ) minute = "0"+minute;
			if ( Integer.parseInt(jour) <10 ) jour = "0"+jour;
			if ( Integer.parseInt(mois) <10 ) mois = "0"+mois;		
			String cheminFichierFromPleiade= fichierDeConfiguration.getCheminFichierFromPleiade();
			nomFichier=  "fromPleiade_"+typeDeTaxe+"_"+ annee +"_"+ numeroBatch+".txt";
			String chemin = cheminFichierFromPleiade+"/"+annee+"-"+numeroBatch;
			File newRepertoire = new File(chemin );
			newRepertoire.mkdir();			
			FileWriter fw = new FileWriter( chemin +"/"+nomFichier);
		    BufferedWriter output = new BufferedWriter(fw);		    
		    //Recuperation des données a parir du nuemero de batch		    
		    Vector res =new Vector();				  
		    
		    //Creation du fichier Pleaide: 
		    for (int i = 0; i < res.size(); i++) 
		    {
		    	output.write((String)res.elementAt(i));		    	
		    	output.write("\n");
			}
		    output.write("**Ce fichier contient des informations sur le batch Numero "+numeroBatch+".\n");
		    output.write("**Le batch a cree " + res.size() +" factures.");		    
		    output.flush();
		    output.close();
			
		}		
		catch (Exception e) 
		{
			DebuggerLog4J.logger.fatal("Impossible de créer le fichier..............[Erreur]"+nomFichier
					+ e.getMessage() +  e.getMessage() + e.getCause());	
		}
	}

	public static void main(String[] args) 
	{
		PleaideEmulator c = new PleaideEmulator(753);
	}
	

}// Fin de la classe
