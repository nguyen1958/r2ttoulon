package fr.analogon.r2t.facturation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;


public class JourneauxComptablesMensuel 
{
		//Declaration des variables de la classe
		private String numeroBatch;	
		private String numMois;				
		
		//template 
		String cheminRecapitulatifComptesMensuelToulon;
		String cheminVersementMensuelToulon;
		
		
		//fichier PDF	
		String cheminRapportRecapitulatifComptesMensuelToulon;
		String cheminRapportVersementMensuelToulon;
							
		private Connection connexionBD;		
		private boolean testRecapitulatifDesComptes;
		FichierDeConfiguration fichierDeConfiguration    =  InitialisationConnexionLectureConfiguration.getFichierDeConfiguration();
		
		
		public JourneauxComptablesMensuel(String Nbatch,String periode,
				String cheminTemplatesToulon, String cheminDataToulon,Connection connexionBD)
		{									
			this.connexionBD = connexionBD;
			
			//template
			this.cheminRecapitulatifComptesMensuelToulon=cheminTemplatesToulon+"/Mensuel/RecapitulatifComptesMensuelToulon/";
			this.cheminVersementMensuelToulon=cheminTemplatesToulon+"/Mensuel/VersementMensuelToulon/";
						
			//fichier pdf
			this.cheminRapportRecapitulatifComptesMensuelToulon =cheminDataToulon+"/RapportComptableMensuel/RecapitulatifComptesMensuelToulon/";					
			this.cheminRapportVersementMensuelToulon=cheminDataToulon+"/RapportComptableMensuel/VersementMensuelToulon/";
			this.numMois = periode;	
			numeroBatch = Nbatch;
			traitment();
		}
		
		public JourneauxComptablesMensuel(String Nbatch,String periode)
		{
		
			//template
			this.cheminRecapitulatifComptesMensuelToulon=fichierDeConfiguration.getCheminRecapitulatifComptesMensuelToulon();
			this.cheminVersementMensuelToulon=fichierDeConfiguration.getCheminVersementMensuelToulon();
						
			//fichier pdf
			this.cheminRapportRecapitulatifComptesMensuelToulon = fichierDeConfiguration.getCheminRapportRecapitulatifComptesMensuelToulon();					
			this.cheminRapportVersementMensuelToulon=fichierDeConfiguration.getCheminRapportVersementMensuelToulon();
			
			this.numMois = periode;			
			numeroBatch = Nbatch;
			connexionBD = InitialisationConnexionLectureConfiguration.connexion.connexion;			
			traitment();
		}
		
		
		public void traitment()
		{
				if ((this.numMois) != "10" &	(this.numMois) != "11" & (this.numMois) != "12")
				this.numMois = "0"+this.numMois;
				
			//Recapitulatif des comptes
			this.testRecapitulatifDesComptes = testRecapitulatifComptes();
			if (this.testRecapitulatifDesComptes)
			{
				try 
				{		
					
					//Rapport Recapitulatif des comptes Mensuel
					String s = cheminRecapitulatifComptesMensuelToulon+"RecapitulatifComptesMensuelToulon.jrxml";
					JasperDesign jasperDesign = JRXmlLoader.load(s);
					//Compilation du rapport
					System.out.print(s+" vient d'etre charge\n");
					JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
					System.out.print(s+" vient d'etre compile\n");
					Map parameters = new HashMap();
					parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
					parameters.put("par_CheminModele", cheminRecapitulatifComptesMensuelToulon);					
					JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);					
					String nomFichier="CM-"+numeroBatch+".pdf";					
					String cheminDuRapportPdf= cheminRapportRecapitulatifComptesMensuelToulon + nomFichier;					 
					JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
					System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");
									
					//Rapport Versement mensuel
					s = cheminVersementMensuelToulon+"VersementMensuelToulon.jrxml";					
					jasperDesign = JRXmlLoader.load(s);				
					System.out.print(s+" vient d'etre charge\n"); 
					jasperReport = JasperCompileManager.compileReport(jasperDesign);
					System.out.print(s+" vient d'etre compile\n");
					parameters = new HashMap();
					parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
					parameters.put("par_CheminModele", cheminVersementMensuelToulon);
					jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);
					nomFichier="VM-"+numeroBatch+".pdf";	
					cheminDuRapportPdf= cheminRapportVersementMensuelToulon + nomFichier;
					JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
					System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");
		
				} 
				catch (JRException e) 
				{			
					e.printStackTrace();
				}				
				System.out.print("Geration des rapports terminee\n");
			}
		}
		
		 boolean testRecapitulatifComptes() 
		 {
		 	 boolean bool = true;
			 try 
			 {
				 
				Statement select = connexionBD.createStatement();				
				String query = "select 1;";
				try 
				{
					ResultSet result = select.executeQuery(query);
				}
				catch (SQLException e) 
				{					
					e.printStackTrace();
				}finally{
					select.close();
				}
			}
			catch(SQLException e) 
			{
				System.out.println("Erreur dans la requete SQL");		
				e.printStackTrace();
			}		
			return bool;
		}
}