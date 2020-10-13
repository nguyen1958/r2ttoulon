package fr.analogon.r2t.main;


import org.apache.log4j.Logger;

import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.request.Connecteur;


/**
 * Permet de debugger l'application .
 * Sofien CHARFI
 * 
 * @version 3
 * @since 3.0
 */

public final class InitialisationConnexionLectureConfiguration
{
    public static  Logger logger ;
    public static  int nombreDeConnexion=0  ;
    public static Connecteur connexion = null;
    public static FichierDeConfiguration fichierDeConfiguration =null;
  
	public InitialisationConnexionLectureConfiguration()
	{		
		//Conexion a la BD et lecture du fichier de configuration 
		try 
		{			
			//Lecture du fichier de configuration 
			if(fichierDeConfiguration == null)
			{
				//System.out.println(">>> new FichierDeConfiguration()");
				fichierDeConfiguration = new FichierDeConfiguration();								
			}
			
			try 
			{	
				//	Ouvrir une nouvelle connexion a la base de données	
				if( connexion == null || connexion.connexion == null || 
						connexion.connexion.isClosed() )
				{
					//nombreDeConnexion =nombreDeConnexion +1;
					//System.out.println(">>> new Connecteur()");
					connexion = new Connecteur();
					//System.out.println("Ouverture d'une nouvelle connexion a la BD");
					DebuggerLog4J.logger.debug("Ouverture d'une nouvelle connexion a la BD");				
					connexion.openConnection();
				}	
				
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				DebuggerLog4J.logger.fatal("Erreur " + e.getMessage() + e.getCause());
			}										
		}
		catch (Exception e) 
		{
			InitialisationConnexionLectureConfiguration.logger.fatal(e.getMessage() +  e.getMessage() + e.getCause());
			e.printStackTrace();
		}	
	}

	/**
	 * @return the fichierDeConfiguration
	 */
	public static final FichierDeConfiguration getFichierDeConfiguration() {
		return fichierDeConfiguration;
	}

	/**
	 * @return the connexion
	 */
	public static final Connecteur getConnexion()
	{
		try 
		{
			//System.out.println(">>>>>>connexion="+connexion);
			//System.out.println(">>>>>>connexion isClosed"+connexion.connexion.isClosed());
			//System.out.println(">>>>>>connexion isValid"+connexion.connexion.isValid(10));
			
		} 
		catch (Exception e) 
		{
			System.out.println(" ERRRORRRR"+e.getMessage());
		}
		
		return connexion;
	}
}

