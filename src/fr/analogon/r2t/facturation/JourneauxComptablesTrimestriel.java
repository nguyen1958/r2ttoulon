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


public class JourneauxComptablesTrimestriel 
{
		//Declaration des variables de la classe
		private String numeroBatch;	
		private String NumeroTrimestre;
		private String annee;		

		//template 
		String cheminBalanceTrimestrielToulon;		
		String cheminVentilationParBaremeTrimestrielToulon;
		String cheminVentilationParEmplacementTrimestrielToulon;
		String cheminListeDesAnnulationsTrimestrielToulon;
		String cheminListeDesAcomptesTrimestrielToulon;
		String cheminListeDesImpayesTrimestrielToulon;
		
		
		
		
		//fichier PDF	
		String cheminRapportBalanceTrimestrielToulon;		
		String cheminRapportVentilationParBaremeTrimestrielToulon;
		String cheminRapportVentilationParEmplacementTrimestrielToulon;
		String cheminRapportListeDesAnnulationsTrimestrielToulon;
		String cheminRapportListeDesAcomptesTrimestrielToulon;
		String cheminRapportListeDesImpayesTrimestrielToulon;
		
		
							
		private Connection connexionBD;		
		private boolean testTrimestriel;
		FichierDeConfiguration fichierDeConfiguration = InitialisationConnexionLectureConfiguration.getFichierDeConfiguration();
		
		
		
		public JourneauxComptablesTrimestriel(String Nbatch,String numerotrimestre,String annee,
				String cheminTemplatesToulon, String cheminDataToulon,Connection connexionBD)
		{									
			this.connexionBD = connexionBD;
			this.NumeroTrimestre = numerotrimestre;
			this.annee = annee;
			//template
			this.cheminBalanceTrimestrielToulon = cheminTemplatesToulon+"/Trimestriel/BalanceTrimestrielToulon/";
			this.cheminVentilationParBaremeTrimestrielToulon = cheminTemplatesToulon+"/Trimestriel/VentilationParBaremeTrimestrielToulon/";
			this.cheminVentilationParEmplacementTrimestrielToulon = cheminTemplatesToulon+"/Trimestriel/VentilationParEmplacementTrimestrielToulon/";
			this.cheminListeDesImpayesTrimestrielToulon = cheminTemplatesToulon+"/Trimestriel/ListeDesImpayesTrimestrielToulon/";
			this.cheminVentilationParBaremeTrimestrielToulon = fichierDeConfiguration.getCheminVentilationParBaremeTrimestrielToulon();
			this.cheminVentilationParEmplacementTrimestrielToulon = fichierDeConfiguration.getCheminVentilationParEmplacementTrimestrielToulon();
		
			
			//fichier pdf
			this.cheminRapportBalanceTrimestrielToulon = cheminDataToulon+"/RapportComptableTrimestriel/BalanceTrimestrielToulon/";
			this.cheminRapportVentilationParBaremeTrimestrielToulon = cheminDataToulon+"/RapportComptableTrimestriel/VentilationParBaremeTrimestrielToulon/";
			this.cheminRapportVentilationParEmplacementTrimestrielToulon = cheminDataToulon+"/RapportComptableTrimestriel/VentilationParEmplacementTrimestrielToulon/";
			numeroBatch = Nbatch;
			traitment();
		}
		
		public JourneauxComptablesTrimestriel(String Nbatch,String numerotrimestre, String annee)
		{
		//template
			this.cheminBalanceTrimestrielToulon = fichierDeConfiguration.getCheminBalanceTrimestrielToulon();
			this.cheminVentilationParBaremeTrimestrielToulon = fichierDeConfiguration.getCheminVentilationParBaremeTrimestrielToulon();
			this.cheminVentilationParEmplacementTrimestrielToulon = fichierDeConfiguration.getCheminVentilationParEmplacementTrimestrielToulon();
			this.cheminListeDesAnnulationsTrimestrielToulon = fichierDeConfiguration.getCheminListeDesAnnulationsTrimestrielToulon();
			this.cheminListeDesAcomptesTrimestrielToulon = fichierDeConfiguration.getCheminListeDesAcomptesTrimestrielToulon();
			this.cheminListeDesImpayesTrimestrielToulon = fichierDeConfiguration.getCheminListeDesImpayesTrimestrielToulon();

			
			
			//fichier pdf
			this.cheminRapportBalanceTrimestrielToulon = fichierDeConfiguration.getCheminRapportBalanceTrimestrielToulon();
			this.cheminRapportVentilationParBaremeTrimestrielToulon = fichierDeConfiguration.getCheminRapportVentilationParBaremeTrimestrielToulon();
			this.cheminRapportVentilationParEmplacementTrimestrielToulon = fichierDeConfiguration.getCheminRapportVentilationParEmplacementTrimestrielToulon();
			this.cheminRapportListeDesAnnulationsTrimestrielToulon = fichierDeConfiguration.getCheminRapportListeDesAnnulationsTrimestrielToulon();
			this.cheminRapportListeDesAcomptesTrimestrielToulon = fichierDeConfiguration.getCheminRapportListeDesAcomptesTrimestrielToulon();
			this.cheminRapportListeDesImpayesTrimestrielToulon = fichierDeConfiguration.getCheminRapportListeDesImpayesTrimestrielToulon();

			
			this.NumeroTrimestre = numerotrimestre;
			this.annee = annee;			
			numeroBatch = Nbatch;
			connexionBD = InitialisationConnexionLectureConfiguration.connexion.connexion;			
			traitment();
		}
		
		
		public void traitment()
		{
					

			this.testTrimestriel = testTrimestriel();
			System.out.println("AVANT");
			if (this.testTrimestriel)
			{
				try 
				{		
					//Rapport Balance Trimestriel
					System.out.println("cheminBalanceTrimestrielToulon="+cheminBalanceTrimestrielToulon);
					String s = cheminBalanceTrimestrielToulon+"BalanceTrimestrielToulon.jrxml";
					JasperDesign jasperDesign = JRXmlLoader.load(s);
					//Compilation du rapport
					System.out.println(s+" vient d'etre charge\n");
					JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
					System.out.print(s+" vient d'etre compile\n");
					Map parameters = new HashMap();
					parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));					
					parameters.put("par_CheminModele", cheminBalanceTrimestrielToulon);					
					JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);					
					String nomFichier="BT-"+numeroBatch+".pdf";					
					String cheminDuRapportPdf= cheminRapportBalanceTrimestrielToulon + nomFichier;					 
					JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
					System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");

					//Rapport Ventilation par Bareme
					s = cheminVentilationParBaremeTrimestrielToulon+"VentilationParBaremeTrimestrielToulon.jrxml";
					jasperDesign = JRXmlLoader.load(s);
					//Compilation du rapport
					System.out.print(s+" vient d'etre charge\n");
					jasperReport = JasperCompileManager.compileReport(jasperDesign);
					System.out.print(s+" vient d'etre compile\n");
					parameters = new HashMap();
					parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
					parameters.put("par_CheminModele", cheminVentilationParBaremeTrimestrielToulon);					
					jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);					
					nomFichier="VPBT-"+numeroBatch+".pdf";					
					cheminDuRapportPdf= cheminRapportVentilationParBaremeTrimestrielToulon + nomFichier;					 
					JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
					System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");

					//Rapport Ventilation par Emplacement
					s = cheminVentilationParEmplacementTrimestrielToulon+"VentilationParEmplacementTrimestrielToulon.jrxml";
					jasperDesign = JRXmlLoader.load(s);
					//Compilation du rapport
					System.out.print(s+" vient d'etre charge\n");
					jasperReport = JasperCompileManager.compileReport(jasperDesign);
					System.out.print(s+" vient d'etre compile\n");
					parameters = new HashMap();					
					parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
					parameters.put("par_CheminModele", cheminVentilationParEmplacementTrimestrielToulon);					
					jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);					
					nomFichier="VPET-"+numeroBatch+".pdf";					
					cheminDuRapportPdf= cheminRapportVentilationParEmplacementTrimestrielToulon + nomFichier;					 
					JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
					System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");				
					
					
					//Rapport liste des annulations trimestriel Toulon
					s = cheminListeDesAnnulationsTrimestrielToulon+"ListeDesAnnulationsTrimestrielToulon.jrxml";
					jasperDesign = JRXmlLoader.load(s);
					//Compilation du rapport
					System.out.print(s+" vient d'etre charge\n");
					jasperReport = JasperCompileManager.compileReport(jasperDesign);
					System.out.print(s+" vient d'etre compile\n");
					parameters = new HashMap();					
					parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
					parameters.put("par_CheminModele", cheminListeDesAnnulationsTrimestrielToulon);					
					jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);					
					nomFichier="LANT-"+numeroBatch+".pdf";					
					cheminDuRapportPdf= cheminRapportListeDesAnnulationsTrimestrielToulon + nomFichier;					 
					JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
					System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");
					
					
					//Rapport Liste des acomptes trimestriel Toulon					
					s = cheminListeDesAcomptesTrimestrielToulon+"ListeDesAcomptesTrimestrielToulon.jrxml";
					jasperDesign = JRXmlLoader.load(s);
					//Compilation du rapport
					System.out.print(s+" vient d'etre charge\n");
					jasperReport = JasperCompileManager.compileReport(jasperDesign);
					System.out.print(s+" vient d'etre compile\n");
					parameters = new HashMap();					
					parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
					parameters.put("par_CheminModele", cheminListeDesAcomptesTrimestrielToulon);					
					jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);					
					nomFichier="LACT-"+numeroBatch+".pdf";					
					cheminDuRapportPdf= cheminRapportListeDesAcomptesTrimestrielToulon + nomFichier;					 
					JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
					System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");

					
					
					//Rapport Liste des impayes trimestriel Toulon				
					s = cheminListeDesImpayesTrimestrielToulon+"ListeDesImpayesTrimestrielToulon.jrxml";
					jasperDesign = JRXmlLoader.load(s);
					//Compilation du rapport
					System.out.print(s+" vient d'etre charge\n");
					jasperReport = JasperCompileManager.compileReport(jasperDesign);
					System.out.print(s+" vient d'etre compile\n");
					parameters = new HashMap();					
					parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
					parameters.put("par_CheminModele", cheminListeDesImpayesTrimestrielToulon);					
					jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);					
					nomFichier="LIT-"+numeroBatch+".pdf";					
					cheminDuRapportPdf= cheminRapportListeDesImpayesTrimestrielToulon + nomFichier;					 
					JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
					System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");									

				} 
				catch (JRException e) 
				{			
					e.printStackTrace();
				}				
				System.out.print("Gen�ration des rapports terminee\n");
			}
		}
		
		 boolean testTrimestriel() 
		 {
		 	 boolean bool = true;
			 try 
			 {

				String dateDebutTrimestre;
				String dateFinTrimestre;
				//int numtrimestre = Integer.parseInt(NumeroTrimestre);
				char numtrimestre = NumeroTrimestre.charAt(0); 
					 switch(numtrimestre)
		        {
		            case '1':
		            dateDebutTrimestre = "01/01/"+annee;
					dateFinTrimestre = "31/03/"+annee;
		            break;
		            case '2':
		            dateDebutTrimestre = "01/04/"+annee;
					dateFinTrimestre = "30/06/"+annee;
		            break;
		            case '3':
		            dateDebutTrimestre = "01/07/"+annee;
					dateFinTrimestre = "30/09/"+annee;
		            break;
					case '4':
		            dateDebutTrimestre = "01/10/"+annee;
					dateFinTrimestre = "31/12/"+annee;			
					break;
		            default:
		            dateDebutTrimestre = "01/01/"+annee;
					dateFinTrimestre = "31/03/"+annee;
					System.out.print("Il y a un probleme dans la recuperation du numero de trimestre");	
		            break;
		        }


			 
				Statement select = connexionBD.createStatement();				
				String query = "SELECT i.libelle AS typeTaxe, "+
				"(SELECT SUM(f.montantTotal) "+
				"FROM facture f "+
				"WHERE f.`typeTaxe`  = i.libelle "+
				"AND f.envoyerALaTresorie = \"false\" "+
				"AND STR_TO_DATE(f.`dateCreation`,'%d/%m/%Y')  "+
				"   	<=  "+
				"    STR_TO_DATE("+dateFinTrimestre+",'%d/%m/%Y') "+
				"AND STR_TO_DATE(f.`dateCreation`,'%d/%m/%Y')  "+
				"   	>=  "+
				"    STR_TO_DATE("+dateDebutTrimestre+",'%d/%m/%Y')) as constatation, "+
				"(SELECT SUM(l.montantPayement) "+
				"FROM facture f, "+
				"lignepayement l, "+
				"payement p "+
				"WHERE f.`typeTaxe`  = i.libelle "+
				"AND l.idFacture = f.idFacture "+
				"AND l.idPayement = p.idPayement "+
				"AND STR_TO_DATE(p.`datePayement`,'%d/%m/%Y')  "+
				"   	<=  "+
				"    STR_TO_DATE("+dateFinTrimestre+",'%d/%m/%Y') "+
				"AND STR_TO_DATE(p.`datePayement`,'%d/%m/%Y')  "+
				"   	>=  "+
				"    STR_TO_DATE("+dateDebutTrimestre+",'%d/%m/%Y')) as perception, "+
				"(SELECT SUM(f.montantTotal) "+
				"FROM facture f "+
				"WHERE f.`typeTaxe` = i.`libelle` "+
				"AND f.envoyerALaTresorie = \"true\" "+
				"AND STR_TO_DATE(f.`dateCreation`,'%d/%m/%Y')  "+
				"   	<=  "+
				"    STR_TO_DATE("+dateFinTrimestre+",'%d/%m/%Y') "+
				"AND STR_TO_DATE(f.`dateCreation`,'%d/%m/%Y')  "+
				"   	>=  "+
				"    STR_TO_DATE("+dateDebutTrimestre+",'%d/%m/%Y')) as poursuite1 "+
				"FROM imputation i "+
				"WHERE i.anneeExercice = "+annee+";";
				try 
				{
					ResultSet result = select.executeQuery(query);
				}
				catch (SQLException e) 
				{					
					e.printStackTrace();
				}finally {
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
