package fr.analogon.r2t.facturation;

import java.sql.Connection;
import java.sql.DriverManager;

import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;

public class TestCreerRaportComptable 
{
	public static void main(String[] args) 
	{
		TestCreerRaportComptable test =new TestCreerRaportComptable();
		Connection connexionBD= test.getConnexionrBD();		
		String cheminDataToulon= "D:\\_workspaceDemoR2t\\r2t\\WebContent\\r2tData\\data\\toulon";		
		String cheminTemplatesToulon= "D:\\_workspaceDemoR2t\\r2t\\WebContent\\WEB-INF\\Templates\\TemplateRapport\\RapportToulon";		
		System.out.println("connexionBD"+connexionBD);
		CreerRapportComptable gen = new CreerRapportComptable("10147",connexionBD,cheminDataToulon,cheminTemplatesToulon);
		gen.lancerGenerationRapport();
	}
	
	public Connection getConnexionrBD()
	{
		Connection connexionBD= null;
		String url = new String("jdbc:mysql://localhost:3306/r2ttoulon");// URL de connection
		 try 
		 {
			 Class.forName("com.mysql.jdbc.Driver");
			 connexionBD = DriverManager.getConnection(url, "root", "");
		 }
		 catch(Exception e)
		 {
			 System.out.println("Driver introuvable");	
		 }
		 return connexionBD;
	}
	
}
