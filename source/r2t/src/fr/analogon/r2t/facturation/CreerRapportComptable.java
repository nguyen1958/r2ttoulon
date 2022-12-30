package fr.analogon.r2t.facturation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;

public class CreerRapportComptable 
{

	public String numeroBatch;
	private Connection connexionBD;
	String cheminDataToulon;
	String cheminTemplatesToulon;
	boolean test ;
	
	public CreerRapportComptable()
	{
		this.connexionBD = InitialisationConnexionLectureConfiguration.connexion.connexion;
		test =false;
	}
	
	public CreerRapportComptable(String numeroBatchatch)
	{
		this.connexionBD = InitialisationConnexionLectureConfiguration.connexion.connexion;
		this.numeroBatch = numeroBatchatch;
		test =false;
	}
	
	public CreerRapportComptable(String numeroBatchatch, Connection connexionBD,String cheminDataToulon, String cheminTemplatesToulon)
	{
		this.numeroBatch = numeroBatchatch;
		this.connexionBD = connexionBD;
		this.cheminDataToulon=cheminDataToulon;
		this.cheminTemplatesToulon=cheminTemplatesToulon;	
		test = true;
	}
	
public int lancerGenerationRapport()
{
	int res = 0;  
	try 
	{		
		Statement select = connexionBD.createStatement();		
		String query = "SELECT * FROM batchrapportcomptable WHERE idBatchRapportComptable = '"+numeroBatch+"';";
		try 
		{
			//On recupere les donnes liees a l'id batch
			ResultSet result = select.executeQuery(query);
			result.next();
			String dateLancement = result.getString(2);
			String periode = result.getString(3);
			String typeRapportComptable = result.getString(4);
			String annee = result.getString(5);
			
			//On affiche le type de rapport
			System.out.println("typeRapportComptable = "+typeRapportComptable);
			
			//Si on est dans le type de rapport journalier : 
			if (typeRapportComptable.equals("j"))
			{ 
				System.out.println("Debut Rapport Journaliers\nPour la date du "+dateLancement+"\n");
				if (test)
					new JourneauxComptablesJournaliers(numeroBatch,dateLancement,
						cheminTemplatesToulon,cheminDataToulon,connexionBD);
				else
					new JourneauxComptablesJournaliers(numeroBatch,dateLancement);
			}
			
			else if (typeRapportComptable.equals("m"))
			{ 
				System.out.println("Debut Rapport Mensuel\nPour le mois "+ periode+"\n");
				if (test)
					new JourneauxComptablesMensuel(numeroBatch,dateLancement,
						cheminTemplatesToulon,cheminDataToulon,connexionBD);
				else
					new JourneauxComptablesMensuel(numeroBatch,dateLancement);
			}			
			//trimestrielle 
			else if (typeRapportComptable.equals("t"))
			{ 
				System.out.println("Debut Rapport Trimestriel\nPour le trimestre N° "+periode+"\n");				
				if (test)
					new JourneauxComptablesTrimestriel(numeroBatch, periode,annee,
						cheminTemplatesToulon,cheminDataToulon,connexionBD);
				else
					new JourneauxComptablesTrimestriel(numeroBatch, periode, annee);
			}
			//annuelle
			else if (typeRapportComptable.equals("a"))
			{ 
				System.out.println("Debut Rapport Annuel \nPour l'anne "+periode+"\n");				
				
				if (test)
					new JourneauxComptablesAnnuel(numeroBatch, annee,
						cheminTemplatesToulon,cheminDataToulon,connexionBD);
				else
					new JourneauxComptablesAnnuel(numeroBatch,dateLancement);
			}
			//marche
			else if (typeRapportComptable.equals("marche"))
			{ 
				System.out.println("Debut Rapport marche pour le "+periode+"\n");				
				new JourneauxComptablesMarche(numeroBatch,dateLancement);
			}
		
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			res= -1;
		}finally{
			select.close();
		}
		
		} 
		catch(SQLException e) 
		{
			System.out.println("Erreur dans la requete SQL");
			e.printStackTrace();
			res = -1;
		}		
		return res;
	}
}
