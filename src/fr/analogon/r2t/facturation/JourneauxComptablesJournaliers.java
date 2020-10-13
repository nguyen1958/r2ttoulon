package fr.analogon.r2t.facturation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;


public class JourneauxComptablesJournaliers 
{
		//Declaration des variables de la classe
		private String numeroBatch;	
		private String DateDuJour;				
		
		//template 
		String cheminListeQuittancesJournalierToulon;
		String cheminRecapitulatifComptesJournalierToulon;
		String cheminVersementChequesJournalierToulon;
		String cheminVersementChequesJournalierCompteRegieToulon;
		String cheminVersementJournalierToulon;
		String cheminModeleTLPEToulon;
		String cheminVersementCarteBancaireJournalierToulon;
		
		//fichier PDF			
		String cheminRapportJournalierToulon;
		String cheminRapportListeQuittancesJournalierToulon;
		String cheminRapportRecapitulatifComptesJournalierToulon;
		String cheminRapportVersementChequesJournalierToulon;
		String cheminRapportVersementChequesJournalierCompteRegieToulon;
		String cheminRapportVersementJournalierToulon;
		String cheminRapportVersementCarteBancaireJournalierToulon;
		
		
		String cheminFactureTLPEToulon;			
		private Connection connexionBD;		
		private boolean testRecapitulatifDesComptes;
		FichierDeConfiguration fichierDeConfiguration    =  InitialisationConnexionLectureConfiguration.getFichierDeConfiguration();
		
		
		public JourneauxComptablesJournaliers(String Nbatch,String DateJour,
				String cheminTemplatesToulon, String cheminDataToulon,Connection connexionBD)
		{									
			this.connexionBD = connexionBD;
			
			//template
			this.cheminListeQuittancesJournalierToulon=cheminTemplatesToulon+"/Journalier/ListeQuittancesJournalierToulon/";
			this.cheminRecapitulatifComptesJournalierToulon=cheminTemplatesToulon+"/Journalier/RecapitulatifComptesJournalierToulon/";
			this.cheminVersementChequesJournalierToulon=cheminTemplatesToulon+"/Journalier/VersementChequesJournalierToulon/";
			this.cheminVersementChequesJournalierCompteRegieToulon=cheminTemplatesToulon+"/Journalier/VersementChequesCompteRegieJournalierToulon/";
			this.cheminVersementJournalierToulon=cheminTemplatesToulon+"/Journalier/VersementJournalierToulon/";
			this.cheminModeleTLPEToulon=cheminTemplatesToulon+"/TemplateFactureTLPE/";
			this.cheminVersementCarteBancaireJournalierToulon=cheminTemplatesToulon+"/Journalier/VersementCarteBancaireJournalierToulon/";
			
			//fichier pdf
			this.cheminRapportJournalierToulon=cheminDataToulon+"/RapportComptableJournalier/";					
			this.cheminRapportVersementJournalierToulon=cheminRapportJournalierToulon+"/VersementJournalierToulon/";
			this.cheminRapportListeQuittancesJournalierToulon=cheminRapportJournalierToulon+"/ListeQuittancesJournalierToulon/";
			this.cheminRapportRecapitulatifComptesJournalierToulon=cheminRapportJournalierToulon+"/RecapitulatifComptesJournalierToulon/";
			this.cheminRapportVersementChequesJournalierToulon=cheminRapportJournalierToulon+"/VersementChequesJournalierToulon/";
			this.cheminRapportVersementChequesJournalierCompteRegieToulon=cheminRapportJournalierToulon+"/VersementChequesCompteRegieJournalierToulon/";
			this.cheminRapportVersementCarteBancaireJournalierToulon=cheminRapportJournalierToulon+"/VersementCarteBancaireJournalierToulon/";
			
			this.cheminFactureTLPEToulon=cheminDataToulon+"/FacturesTLPE/";
			//SetDateDuJour(DateJour);			
			numeroBatch = Nbatch;
			traitment();
		}
		
		public JourneauxComptablesJournaliers(String Nbatch,String DateJour)
		{
			//template
			this.cheminListeQuittancesJournalierToulon=fichierDeConfiguration.getCheminListeQuittancesJournalierToulon();
			this.cheminRecapitulatifComptesJournalierToulon=fichierDeConfiguration.getCheminRecapitulatifComptesJournalierToulon();
			this.cheminVersementChequesJournalierToulon=fichierDeConfiguration.getCheminVersementChequesJournalierToulon();
			this.cheminVersementChequesJournalierCompteRegieToulon=fichierDeConfiguration.getCheminVersementChequesJournalierComptesRegieToulon();
			this.cheminVersementCarteBancaireJournalierToulon=fichierDeConfiguration.getCheminVersementCarteBancaireJournalierToulon();
			
			
			this.cheminVersementJournalierToulon=fichierDeConfiguration.getCheminVersementJournalierToulon();
			
			this.cheminModeleTLPEToulon=fichierDeConfiguration.getCheminModeleTLPEToulon();
			
			//fichier pdf
			this.cheminRapportJournalierToulon=fichierDeConfiguration.getCheminRapportJournalierToulon();				
			this.cheminRapportVersementJournalierToulon=fichierDeConfiguration.getCheminRapportVersementJournalierToulon();
			this.cheminRapportListeQuittancesJournalierToulon=fichierDeConfiguration.getCheminRapportListeQuittancesJournalierToulon();
			this.cheminRapportRecapitulatifComptesJournalierToulon=fichierDeConfiguration.getCheminRapportRecapitulatifComptesJournalierToulon();
			this.cheminRapportVersementChequesJournalierToulon=fichierDeConfiguration.getCheminRapportVersementChequesJournalierToulon();
			this.cheminRapportVersementChequesJournalierCompteRegieToulon = fichierDeConfiguration.getCheminRapportVersementChequesJournalierCompteRegie();
			this.cheminRapportVersementCarteBancaireJournalierToulon=fichierDeConfiguration.getCheminRapportVersementCarteBancaireJournalierToulon();
			
			SetDateDuJour(DateJour);			
			numeroBatch = Nbatch;
			connexionBD = InitialisationConnexionLectureConfiguration.connexion.connexion;	
			traitment();
		}
		
		
		public void traitment()
		{
					
			//Recapitulatif des comptes
			this.testRecapitulatifDesComptes = testRecapitulatifComptes();
			if (this.testRecapitulatifDesComptes)
			{
				try 
				{	
					//Rapport Recapitulatif des comptes Journaliers
					String s = cheminRecapitulatifComptesJournalierToulon+"RecapitulatifComptesJournalierToulon.jrxml";
					JasperDesign jasperDesign = JRXmlLoader.load(s);
					//Compilation du rapport
					System.out.print(s+" vient d'etre charge\n");
					JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
					System.out.print(s+" vient d'etre compile\n");
					Map parameters = new HashMap();
					parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
					parameters.put("par_CheminModele", cheminRecapitulatifComptesJournalierToulon);	
					JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);
					String date= this.DateDuJour.substring(0,2)+"/"+this.DateDuJour.substring(3,5)+"/"+this.DateDuJour.substring(6,10);
					String nomFichier="CJ-"+numeroBatch+".pdf";					
					String cheminDuRapportPdf= cheminRapportRecapitulatifComptesJournalierToulon + nomFichier;					 
					JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
					System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");
				
					
					//Rapport Versement
					s = cheminVersementJournalierToulon+"VersementJournalierToulon.jrxml";					
					jasperDesign = JRXmlLoader.load(s);
					//Compilation du rapport
					System.out.print(s+" vient d'etre charge\n"); 
					jasperReport = JasperCompileManager.compileReport(jasperDesign);
					System.out.print(s+" vient d'etre compile\n");
					parameters = new HashMap();
					parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
					parameters.put("par_CheminModele", cheminVersementJournalierToulon);
					jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);
					nomFichier="VJ-"+numeroBatch+".pdf";	
					cheminDuRapportPdf= cheminRapportVersementJournalierToulon + nomFichier;
					JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
					System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");
						
					//Rapport Versement des Cheques
					s = cheminVersementChequesJournalierToulon+"VersementChequesJournalierToulon.jrxml";
					jasperDesign = JRXmlLoader.load(s);
					//Compilation du rapport
					System.out.print(s+" vient d'etre charge\n"); 
					jasperReport = JasperCompileManager.compileReport(jasperDesign);
					System.out.print(s+" vient d'etre compile\n");
					parameters = new HashMap();
					parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
					parameters.put("par_CheminModele", cheminVersementChequesJournalierToulon);
					jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);
					nomFichier="VCJ-"+numeroBatch+".pdf";
					cheminDuRapportPdf= cheminRapportVersementChequesJournalierToulon + nomFichier;
					JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);					
					System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");
									
					//Rapport Versement des Cheques compte Regie Toulon
					s = cheminVersementChequesJournalierCompteRegieToulon +"VersementChequesCompteRegieJournalierToulon.jrxml";
					jasperDesign = JRXmlLoader.load(s);
					//Compilation du rapport
					System.out.print(s+" vient d'etre charge\n"); 
					jasperReport = JasperCompileManager.compileReport(jasperDesign);
					System.out.print(s+" vient d'etre compile\n");
					parameters = new HashMap();
					parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
					parameters.put("par_CheminModele", cheminVersementChequesJournalierCompteRegieToulon);
					jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);
					nomFichier="VCJCR-"+numeroBatch+".pdf";
					cheminDuRapportPdf= cheminRapportVersementChequesJournalierCompteRegieToulon + nomFichier;
					JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);					
					System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");
					
					//Rapport Liste des Quittances
					s = cheminListeQuittancesJournalierToulon+"ListeQuittancesJournalierToulon.jrxml";
					jasperDesign = JRXmlLoader.load(s);
					//Compilation du rapport
					System.out.print(s+" vient d'etre charge\n"); 
					jasperReport = JasperCompileManager.compileReport(jasperDesign);
					System.out.print(s+" vient d'etre compile\n");
					parameters = new HashMap();
					parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
					parameters.put("par_CheminModele", cheminListeQuittancesJournalierToulon);
					jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);
					nomFichier="LQJ-"+numeroBatch+".pdf";
					cheminDuRapportPdf= cheminRapportListeQuittancesJournalierToulon + nomFichier;
					JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);	
					System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");
					
					//Rapport Versement des Cartes bancaires
					s = cheminVersementCarteBancaireJournalierToulon+"VersementCarteBancaireJournalierToulon.jrxml";
					jasperDesign = JRXmlLoader.load(s);
					//Compilation du rapport
					System.out.print(s+" vient d'etre charge\n"); 
					jasperReport = JasperCompileManager.compileReport(jasperDesign);
					System.out.print(s+" vient d'etre compile\n");
					parameters = new HashMap();
					parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
					parameters.put("par_CheminModele", cheminVersementCarteBancaireJournalierToulon);
					jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);
					nomFichier="VCBJ-"+numeroBatch+".pdf";
					cheminDuRapportPdf= cheminRapportVersementCarteBancaireJournalierToulon + nomFichier;
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
				String query = "SELECT DISTINCT"+
					"     i.`section`,"+
					"     i.`libelle`,"+
					"     i.`idImputation` AS imputation_idImputation,"+
					"                (SELECT SUM( l.`montantPayement`)"+
					"                FROM"+
					"                                `payement` p,"+
					"                                `lignepayement` l,"+
					"                                `facture` f"+
					"                WHERE"+
					"                                f.`idFacture` = l.`idFacture`"+
					"                                AND l.idPayement = p.idPayement"+
					"                                 AND p.datePayement = \""+this.DateDuJour+"\""+
					"                               AND f.typeTaxe = i.`libelle`"+
					"                ) AS versementPeriode,"+
					"                (SELECT"+
					"                                SUM(l.`montantPayement`)"+
					"                FROM"+
					"                                `payement` p,"+
					"                                `lignepayement` l,"+
					"                                `facture` f"+
					"                WHERE"+
					"                f.`idFacture` = l.`idFacture`"+
					"                AND l.idPayement = p.idPayement"+
					"                AND STR_TO_DATE(p.datePayement,'%d/%m/%Y')<STR_TO_DATE(\""+this.DateDuJour+"\",'%d/%m/%Y')"+
					"                AND STR_TO_DATE(p.datePayement,'%d/%m/%Y')>=STR_TO_DATE(CONCAT(\"01/01/\",substring(\""+this.DateDuJour+"\",7,4)),'%d/%m/%Y')"+
					"                AND f.typeTaxe = i.`libelle`"+
					"                ) AS versementAnterieur,"+
					"                (SELECT"+
					"                                SUM(l.`montantPayement`)"+
					"                FROM"+
					"                                `payement` p,"+
					"                                `lignepayement` l,"+
					"                                `facture` f"+
					"                WHERE"+
					"                f.`idFacture` = l.`idFacture`"+
					"                AND l.idPayement = p.idPayement"+
					"                AND STR_TO_DATE(p.datePayement,'%d/%m/%Y')<=STR_TO_DATE("+this.DateDuJour+",'%d/%m/%Y')"+
					"                AND STR_TO_DATE(p.datePayement,'%d/%m/%Y')>=STR_TO_DATE(CONCAT(\"01/01/\",substring("+this.DateDuJour+",7,4)),'%d/%m/%Y')"+
					"                AND f.typeTaxe = i.`libelle`"+
					"                ) AS totalVersement "+
					" FROM"+
					"     `imputation` i " +
					" WHERE"+
					"  i.anneeExercice LIKE substring(\""+this.DateDuJour+"\",7,4);";
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

		String GetDateDuJour()
		{
			return DateDuJour;
		}
		void SetDateDuJour(String d)
		{
			this.DateDuJour = d;
		}




}
