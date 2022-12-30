//			Classe Connecteur
// 
// Connexion é la base Mysql r2tBordeaux
//
// Modif Pascal 11/02/08
// retour de la connexion pour la méthode openConnection()

package fr.analogon.r2t.request;

import java.sql.Connection;
import java.sql.DriverManager;
import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;

public class Connecteur extends Request
{
	public String pilote = "com.mysql.jdbc.Driver";	
	public Connection connexion=null;
	
  public  Connection openConnection()
  {	  
	 try
	 {
			Class.forName(pilote);
			debug.logger.debug("after Class.forName");
			//connexion = DriverManager.getConnection("jdbc:mysql://localhost/macbordotierce","root","admin");
			FichierDeConfiguration  fichierDeConfiguration = InitialisationConnexionLectureConfiguration.fichierDeConfiguration;
			String host=fichierDeConfiguration.getHost();
			String port=fichierDeConfiguration.getPort();
			String dbName=fichierDeConfiguration.getDbName();			
			String user=fichierDeConfiguration.getUser();
			String password=fichierDeConfiguration.getPassword();
			debug.logger.debug("FichierDeConfiguration : "+host+":"+port+":"+dbName+":"+user+":"+password);
			connexion = DriverManager.getConnection ("jdbc:mysql://"+host+":"+port+"/"+dbName+"?autoReconnect=true",user,password);
			
	 }
	 catch (Exception e)
	 {
			debug.logger.debug("echec pilote : \n "+e);
			try 
			{
				//connexion.close();
			} 
			catch (Exception e2){e2.printStackTrace();}
	 }	
	 return connexion;
  }
 
  

 

}
