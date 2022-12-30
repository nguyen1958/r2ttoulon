package fr.analogon.r2t.facturation;

import java.sql.Connection;
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


public class JourneauxComptablesAnnuel 
{
		//Declaration des variables de la classe
		private String numeroBatch;	
		private String annee;		
		
		//template et fichier PDF
		String cheminBalanceAnnuelToulon;
		String cheminRapportBalanceAnnuelToulon;
		
		String cheminListeDesAcomptesAnnuelToulon="";		
		String cheminListeDesAnnulationsAnnuelToulon="";
		String cheminListeDesImpayesAnnuelEtalageToulon="";
		String cheminListeDesImpayesAnnuelTLPEToulon="";
		String cheminVentilationParBaremeAnnuelEtalageToulon="";
		String cheminVentilationParBaremeAnnuelTLPEToulon="";
		String cheminVentilationParEmplacementAnnuelEtalageToulon="";
		String cheminVentilationParEmplacementAnnuelTLPEToulon="";
		
		String cheminRapportListeDesAcomptesAnnuelToulon="";		
		String cheminRapportListeDesAnnulationsAnnuelToulon="";
		String cheminRapportListeDesImpayesAnnuelEtalageToulon="";
		String cheminRapportListeDesImpayesAnnuelTLPEToulon="";
		String cheminRapportVentilationParBaremeAnnuelEtalageToulon="";
		String cheminRapportVentilationParBaremeAnnuelTLPEToulon="";
		String cheminRapportVentilationParEmplacementAnnuelEtalageToulon="";
		String cheminRapportVentilationParEmplacementAnnuelTLPEToulon="";
		
		
		
		
		
		
		private Connection connexionBD;		
		private boolean testAnnuel;
		FichierDeConfiguration fichierDeConfiguration = InitialisationConnexionLectureConfiguration.getFichierDeConfiguration();
		
		
		public JourneauxComptablesAnnuel(String Nbatch,String annee,
				String cheminTemplatesToulon, String cheminDataToulon,Connection connexionBD)
		{									
			this.connexionBD = connexionBD;	
			this.annee = annee ;
			//template	
			this.cheminBalanceAnnuelToulon = cheminTemplatesToulon+"/Annuel/BalanceAnnuelToulon/";
			System.out.println("Template="+cheminBalanceAnnuelToulon);
			//fichier pdf			
			this.cheminRapportBalanceAnnuelToulon = cheminDataToulon+"/BalanceAnnuelToulon/";
			System.out.println("facture="+cheminRapportBalanceAnnuelToulon);
			numeroBatch = Nbatch;
			traitment();
		}
		
		public JourneauxComptablesAnnuel(String Nbatch, String annee)
		{	
			
			//template et fichier pdf				
			this.cheminBalanceAnnuelToulon = fichierDeConfiguration.getCheminBalanceAnnuelToulon();			
			this.cheminRapportBalanceAnnuelToulon = fichierDeConfiguration.getCheminRapportBalanceAnnuelToulon();
			
			this.cheminListeDesAcomptesAnnuelToulon = fichierDeConfiguration.getCheminListeDesAcomptesAnnuelToulon();			
			this.cheminRapportListeDesAcomptesAnnuelToulon = fichierDeConfiguration.getCheminRapportListeDesAcomptesAnnuelToulon();
			
			this.cheminListeDesAnnulationsAnnuelToulon = fichierDeConfiguration.getCheminListeDesAnnulationsAnnuelToulon();			
			this.cheminRapportListeDesAnnulationsAnnuelToulon = fichierDeConfiguration.getCheminRapportListeDesAnnulationsAnnuelToulon();
			
			this.cheminListeDesImpayesAnnuelEtalageToulon = fichierDeConfiguration.getCheminListeDesImpayesAnnuelEtalageToulon();			
			this.cheminRapportListeDesImpayesAnnuelEtalageToulon = fichierDeConfiguration.getCheminRapportListeDesImpayesAnnuelEtalageToulon();
			
			this.cheminListeDesImpayesAnnuelTLPEToulon = fichierDeConfiguration.getCheminListeDesImpayesAnnuelTLPEToulon();			
			this.cheminRapportListeDesImpayesAnnuelTLPEToulon = fichierDeConfiguration.getCheminRapportListeDesImpayesAnnuelTLPEToulon();
			
			this.cheminVentilationParBaremeAnnuelEtalageToulon = fichierDeConfiguration.getCheminVentilationParBaremeAnnuelEtalageToulon();			
			this.cheminRapportVentilationParBaremeAnnuelEtalageToulon = fichierDeConfiguration.getCheminRapportVentilationParBaremeAnnuelEtalageToulon();
			
			this.cheminVentilationParBaremeAnnuelTLPEToulon = fichierDeConfiguration.getCheminVentilationParBaremeAnnuelTLPEToulon();			
			this.cheminRapportVentilationParBaremeAnnuelTLPEToulon = fichierDeConfiguration.getCheminRapportVentilationParBaremeAnnuelTLPEToulon();
			
			this.cheminVentilationParEmplacementAnnuelEtalageToulon = fichierDeConfiguration.getCheminVentilationParEmplacementAnnuelEtalageToulon();			
			this.cheminRapportVentilationParEmplacementAnnuelEtalageToulon = fichierDeConfiguration.getCheminRapportVentilationParEmplacementAnnuelEtalageToulon();
			
			this.cheminVentilationParEmplacementAnnuelTLPEToulon = fichierDeConfiguration.getCheminVentilationParEmplacementAnnuelTLPEToulon();			
			this.cheminRapportVentilationParEmplacementAnnuelTLPEToulon = fichierDeConfiguration.getCheminRapportVentilationParEmplacementAnnuelTLPEToulon();
			
			this.annee = annee ;
			numeroBatch = Nbatch;
			connexionBD = InitialisationConnexionLectureConfiguration.connexion.connexion;			
			traitment();
		}
		
		
		public void traitment()
		{	
			try 
			{				
				//BalanceAnnuelToulon
				String s = cheminBalanceAnnuelToulon+"BalanceAnnuelToulon.jrxml";
				JasperDesign jasperDesign = JRXmlLoader.load(s);				
				System.out.print(s+" vient d'etre charge\n");
				JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
				System.out.print(s+" vient d'etre compile\n");
				Map parameters = new HashMap();
				parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
				parameters.put("par_CheminModele", cheminBalanceAnnuelToulon);					
				JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);					
				String nomFichier="BA-"+numeroBatch+".pdf";					
				String cheminDuRapportPdf=  cheminRapportBalanceAnnuelToulon + nomFichier;					 
				JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
				System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");
	
				//ListeDesAcomptesAnnuelToulon
				s = cheminListeDesAcomptesAnnuelToulon+"ListeDesAcomptesAnnuelToulon.jrxml";
				jasperDesign = JRXmlLoader.load(s);				
				System.out.print(s+" vient d'etre charge\n");
				jasperReport = JasperCompileManager.compileReport(jasperDesign);
				System.out.print(s+" vient d'etre compile\n");
				parameters = new HashMap();
				parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
				parameters.put("par_CheminModele", cheminListeDesAcomptesAnnuelToulon);					
				jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);					
				nomFichier="LDAA-"+numeroBatch+".pdf";					
				cheminDuRapportPdf=  cheminRapportListeDesAcomptesAnnuelToulon + nomFichier;					 
				JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
				System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");

				//ListeDesAnnulationsAnnuelToulon
				s = cheminListeDesAnnulationsAnnuelToulon+"ListeDesAnnulationsAnnuelToulon.jrxml";
				jasperDesign = JRXmlLoader.load(s);				
				System.out.print(s+" vient d'etre charge\n");
				jasperReport = JasperCompileManager.compileReport(jasperDesign);
				System.out.print(s+" vient d'etre compile\n");
				parameters = new HashMap();	
				parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
				parameters.put("par_CheminModele", cheminListeDesAnnulationsAnnuelToulon);
				jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);
				nomFichier="LDAnnulation-"+numeroBatch+".pdf";					
				cheminDuRapportPdf=  cheminRapportListeDesAnnulationsAnnuelToulon + nomFichier;					 
				JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
				System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");
	
				//ListeDesImpayesAnnuelEtalageToulon
				s = cheminListeDesImpayesAnnuelEtalageToulon+"ListeDesImpayesAnnuelEtalageToulon.jrxml";
				jasperDesign = JRXmlLoader.load(s);				
				System.out.print(s+" vient d'etre charge\n");
				jasperReport = JasperCompileManager.compileReport(jasperDesign);
				System.out.print(s+" vient d'etre compile\n");
				parameters = new HashMap();
				parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
				parameters.put("par_CheminModele", cheminListeDesImpayesAnnuelEtalageToulon);					
				jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);					
				nomFichier="LDIAEtalage-"+numeroBatch+".pdf";					
				cheminDuRapportPdf=  cheminRapportListeDesImpayesAnnuelEtalageToulon + nomFichier;					 
				JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
				System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");
	
				//ListeDesImpayesAnnuelTLPEToulon
				s = cheminListeDesImpayesAnnuelTLPEToulon+"ListeDesImpayesAnnuelTLPEToulon.jrxml";
				jasperDesign = JRXmlLoader.load(s);				
				System.out.print(s+" vient d'etre charge\n");
				jasperReport = JasperCompileManager.compileReport(jasperDesign);
				System.out.print(s+" vient d'etre compile\n");
				parameters = new HashMap();
				parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
				parameters.put("par_CheminModele", cheminListeDesImpayesAnnuelTLPEToulon);					
				jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);					
				nomFichier="LDIATLPE-"+numeroBatch+".pdf";					
				cheminDuRapportPdf=  cheminRapportListeDesImpayesAnnuelTLPEToulon + nomFichier;					 
				JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
				System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");

				//VentilationParBaremeAnnuelEtalageToulon
				s = cheminVentilationParBaremeAnnuelEtalageToulon+"VentilationParBaremeAnnuelEtalageToulon.jrxml";
				jasperDesign = JRXmlLoader.load(s);				
				System.out.print(s+" vient d'etre charge\n");
				jasperReport = JasperCompileManager.compileReport(jasperDesign);
				System.out.print(s+" vient d'etre compile\n");
				parameters = new HashMap();
				parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
				parameters.put("par_CheminModele", cheminVentilationParBaremeAnnuelEtalageToulon);					
				jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);					
				nomFichier="VPBAE-"+numeroBatch+".pdf";					
				cheminDuRapportPdf=  cheminRapportVentilationParBaremeAnnuelEtalageToulon + nomFichier;					 
				JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
				System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");
				
				//VentilationParBaremeAnnuelTLPEToulon
				s = cheminVentilationParBaremeAnnuelTLPEToulon+"VentilationParBaremeAnnuelTLPEToulon.jrxml";
				jasperDesign = JRXmlLoader.load(s);				
				System.out.print(s+" vient d'etre charge\n");
				jasperReport = JasperCompileManager.compileReport(jasperDesign);
				System.out.print(s+" vient d'etre compile\n");
				parameters = new HashMap();
				parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
				parameters.put("par_CheminModele", cheminVentilationParBaremeAnnuelTLPEToulon);					
				jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);					
				nomFichier="VPBATLPE-"+numeroBatch+".pdf";					
				cheminDuRapportPdf=  cheminRapportVentilationParBaremeAnnuelTLPEToulon + nomFichier;					 
				JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
				System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");

				//VentilationParEmplacementAnnuelEtalageToulon
				s = cheminVentilationParEmplacementAnnuelEtalageToulon+"VentilationParEmplacementAnnuelEtalageToulon.jrxml";
				jasperDesign = JRXmlLoader.load(s);				
				System.out.print(s+" vient d'etre charge\n");
				jasperReport = JasperCompileManager.compileReport(jasperDesign);
				System.out.print(s+" vient d'etre compile\n");
				parameters = new HashMap();
				parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
				parameters.put("par_CheminModele", cheminVentilationParEmplacementAnnuelEtalageToulon);					
				jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);					
				nomFichier="VPEAE-"+numeroBatch+".pdf";					
				cheminDuRapportPdf=  cheminRapportVentilationParEmplacementAnnuelEtalageToulon + nomFichier;					 
				JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
				System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");

				//VentilationParEmplacementAnnuelTLPEToulon
				s = cheminVentilationParEmplacementAnnuelTLPEToulon+"VentilationParEmplacementAnnuelTLPEToulon.jrxml";
				jasperDesign = JRXmlLoader.load(s);				
				System.out.print(s+" vient d'etre charge\n");
				jasperReport = JasperCompileManager.compileReport(jasperDesign);
				System.out.print(s+" vient d'etre compile\n");
				parameters = new HashMap();	
				parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
				parameters.put("par_CheminModele", cheminVentilationParEmplacementAnnuelTLPEToulon);					
				jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);	
				nomFichier="VPETLPE-"+numeroBatch+".pdf";					
				cheminDuRapportPdf=  cheminRapportVentilationParEmplacementAnnuelTLPEToulon + nomFichier;					 
				JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
				System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");

			}
			catch (JRException e) {		
				e.printStackTrace();
			}				
			System.out.print("Genration des rapports terminee\n");			
		}
}
