package fr.analogon.r2t.main;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.SimpleLayout;

import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.request.RequestAdmin;


/**
 * Permet de debugger l'application .
 * Sofien CHARFI
 * 
 * @version 3
 * @since 3.0
 */

public final class DebuggerLog4J 
{
    public static  Logger logger ;
    public static int nombre =0;
    //0 aucun logging 
    //1 niveau erreur 
    //2 niveau debogae 
    
    
    public static int niveauDebogage =2;
    
  
    
    
	public DebuggerLog4J( )
	{		
		try 
		{			
			if(nombre>0)
			{
				RequestAdmin requestAdmin = new RequestAdmin();	
				if(requestAdmin.getNiveauDebogageApplication().length() !=0)
					niveauDebogage = Integer.valueOf(requestAdmin.getNiveauDebogageApplication());				
			}
			
			logger = Logger.getLogger("r2tDebug");
			InitialisationConnexionLectureConfiguration i = new InitialisationConnexionLectureConfiguration();
			FichierDeConfiguration  fichierDeConfiguration = 
				InitialisationConnexionLectureConfiguration.getFichierDeConfiguration();
			//PatternLayout layout = new PatternLayout("****%d{yyyy-MM-dd HH:mm:ss} -%C  -%M  -%F:%-4L\n%m%n");
			PatternLayout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm}||%m||-%M -%F:%-4L%n");
			//PatternLayout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm} --- %m%n -%C  -%M");			
			String cheminDossierLog =  fichierDeConfiguration.getCheminFichierLog();
			//String extension = new Date().getMonth()+1+ "_"+ Integer.valueOf(new Date().getYear()+1900)+".log";
			String extension =".log";			
			String cheminFichierLog = cheminDossierLog + "R2T_Trace" +extension ;
			
			if(niveauDebogage == 0)
			{
				logger.setLevel(Level.OFF);
			}
			else if(niveauDebogage == 1)
			{
				logger.setLevel(Level.FATAL);
			}
			else if(niveauDebogage == 2)
			{
				logger.setLevel(Level.ALL);
			}
				
			
				if( nombre ==0)
				{				
					//Sortie sur la console
					logger.addAppender(new ConsoleAppender(new SimpleLayout()));
					//Sortie dedebugage sur un fichier 			 
					//cheminFichierLog= "d://test.log";
					logger.addAppender(new FileAppender( layout, cheminFichierLog ) );
					System.out.println("Chemin Fichier log="+ cheminFichierLog);
				}
				nombre ++;
			
		}
		catch (Exception e) 
		{
			DebuggerLog4J.logger.fatal(e.getMessage() +  e.getMessage() + e.getCause());
			e.printStackTrace();
		}	
	}

}

