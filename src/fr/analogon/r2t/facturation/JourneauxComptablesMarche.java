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


public class JourneauxComptablesMarche 
{
		//Declaration des variables de la classe
		private String numeroBatch;	
		private String DateDuJour;
		private Connection connexionBD;		
		FichierDeConfiguration fichierDeConfiguration    =  InitialisationConnexionLectureConfiguration.getFichierDeConfiguration();
		
		//template
		String cheminEncaissementJour;
		String cheminSuiviPresenceRedevable;
		String cheminRecapMensuelParFamilleMarche;
		String cheminRecapMoisParJour;
		String cheminRecuAbonne;
		
		//fichier PDF
		String cheminRapportEncaissementJour;
		String cheminRapportSuiviPresenceRedevable;
		String cheminRapportRecapMensuelParFamilleMarche;
		String cheminRapportRecapMoisParJour;
		String cheminRapportRecuAbonne;
		
		public JourneauxComptablesMarche(String Nbatch,String DateJour)
		{
		
			//template
			this.cheminEncaissementJour=fichierDeConfiguration.getCheminEncaissementJour();
			this.cheminSuiviPresenceRedevable=fichierDeConfiguration.getCheminSuiviPresenceRedevable();
			this.cheminRecapMensuelParFamilleMarche=fichierDeConfiguration.getCheminRecapMensuelParFamilleMarche();
			this.cheminRecapMoisParJour=fichierDeConfiguration.getCheminRecapMoisParJour();
			this.cheminRecuAbonne=fichierDeConfiguration.getCheminRecuAbonne();
			
			
			//fichier pdf
			this.cheminRapportEncaissementJour =fichierDeConfiguration.getCheminRapportEncaissementJour();
			this.cheminRapportSuiviPresenceRedevable=fichierDeConfiguration.getCheminRapportSuiviPresenceRedevable();
			this.cheminRapportRecapMensuelParFamilleMarche=fichierDeConfiguration.getCheminRapportRecapMensuelParFamilleMarche();
			this.cheminRapportRecapMoisParJour=fichierDeConfiguration.getCheminRapportRecapMoisParJour();
			this.cheminRapportRecuAbonne=fichierDeConfiguration.getCheminRapportRecuAbonne();
			SetDateDuJour(DateJour);			
			numeroBatch = Nbatch;
			connexionBD = InitialisationConnexionLectureConfiguration.connexion.connexion;			
			traitment();
		}
		
		
		public void traitment()
		{
				//Rapport Encaissement Jour
				String s = "";
				JasperDesign jasperDesign  ;
				JasperReport jasperReport  ;
				Map parameters ;
				JasperPrint jasperprint ;
				String date= this.DateDuJour.substring(0,2)+"/"+this.DateDuJour.substring(3,5)+"/"+this.DateDuJour.substring(6,10);
				String nomFichier="";					
				String cheminDuRapportPdf="";
				
				try 
				{
					//Rapport Encaissement Jour
					s = cheminEncaissementJour+"ToulonMarcheEncaissementJour.jrxml";
					jasperDesign = JRXmlLoader.load(s);
					
					//Compilation du rapport
					System.out.print(s+" vient d'etre charge\n");
					jasperReport = JasperCompileManager.compileReport(jasperDesign);
					System.out.print(s+" vient d'etre compile\n");
					parameters = new HashMap();
					parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
					parameters.put("par_CheminModele", cheminEncaissementJour);					
					jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);					
					date= this.DateDuJour.substring(0,2)+"/"+this.DateDuJour.substring(3,5)+"/"+this.DateDuJour.substring(6,10);
					nomFichier="EJ-"+numeroBatch+".pdf";					
					cheminDuRapportPdf= cheminRapportEncaissementJour + nomFichier;					 
					JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
					System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");
				} 
				catch (JRException e) 
				{			
					e.printStackTrace();
				}
				
		
				try {
					
					//Rapport SuiviPresenceRedevable
					s = cheminSuiviPresenceRedevable+"ToulonMarcheMarcheSuiviPresenceRedevable.jrxml";
					jasperDesign = JRXmlLoader.load(s);
					//Compilation du rapport
					System.out.print(s+" vient d'etre charge\n");
					jasperReport = JasperCompileManager.compileReport(jasperDesign);
					System.out.print(s+" vient d'etre compile\n");
					parameters = new HashMap();
					parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
					parameters.put("par_CheminModele", cheminSuiviPresenceRedevable);					
					jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);					
					date= this.DateDuJour.substring(0,2)+"/"+this.DateDuJour.substring(3,5)+"/"+this.DateDuJour.substring(6,10);
					nomFichier="SPR-"+numeroBatch+".pdf";					
					cheminDuRapportPdf= cheminRapportSuiviPresenceRedevable + nomFichier;					 
					JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
					System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");
				} catch (Exception e) {
					// TODO: handle exception
				}	
				
				try {
					
				
					//Rapport Recap Mensuel Par Famille Marche
					s = cheminRecapMensuelParFamilleMarche+"ToulonMarcheRecapMensuelParMarche.jrxml";
					jasperDesign = JRXmlLoader.load(s);
					//Compilation du rapport
					System.out.print(s+" vient d'etre charge\n");
					jasperReport = JasperCompileManager.compileReport(jasperDesign);
					System.out.print(s+" vient d'etre compile\n");
					parameters = new HashMap();
					parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
					parameters.put("par_CheminModele", cheminRecapMensuelParFamilleMarche);					
					jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);					
					date= this.DateDuJour.substring(0,2)+"/"+this.DateDuJour.substring(3,5)+"/"+this.DateDuJour.substring(6,10);
					nomFichier="RFM-"+numeroBatch+".pdf";					
					cheminDuRapportPdf= cheminRapportRecapMensuelParFamilleMarche + nomFichier;					 
					JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
					System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
				
					
					//Rapport Recap Mois Par Jour
					s = cheminRecapMoisParJour+"ToulonMarcheRecapMoisParJour.jrxml";
					jasperDesign = JRXmlLoader.load(s);
					//Compilation du rapport
					System.out.print(s+" vient d'etre charge\n");
					jasperReport = JasperCompileManager.compileReport(jasperDesign);
					System.out.print(s+" vient d'etre compile\n");
					parameters = new HashMap();
					parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
					parameters.put("par_CheminModele", cheminRecapMoisParJour);					
					jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);					
					date= this.DateDuJour.substring(0,2)+"/"+this.DateDuJour.substring(3,5)+"/"+this.DateDuJour.substring(6,10);
					nomFichier="RMJ-"+numeroBatch+".pdf";					
					cheminDuRapportPdf= cheminRapportRecapMoisParJour + nomFichier;					 
					JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
					System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					
			
					
					//Rapport recu abonne
					s = cheminRecuAbonne+"ToulonMarcheRecuAbonne.jrxml";
					jasperDesign = JRXmlLoader.load(s);
					//Compilation du rapport
					System.out.print(s+" vient d'etre charge\n");
					jasperReport = JasperCompileManager.compileReport(jasperDesign);
					System.out.print(s+" vient d'etre compile\n");
					parameters = new HashMap();
					parameters.put("NumeroBatch",Integer.parseInt(numeroBatch));
					parameters.put("par_CheminModele", cheminRecuAbonne);					
					jasperprint = JasperFillManager.fillReport(jasperReport,parameters,connexionBD);					
					date= this.DateDuJour.substring(0,2)+"/"+this.DateDuJour.substring(3,5)+"/"+this.DateDuJour.substring(6,10);
					nomFichier="RA-"+numeroBatch+".pdf";					
					cheminDuRapportPdf= cheminRapportRecuAbonne + nomFichier;					 
					JasperExportManager.exportReportToPdfFile(jasperprint, cheminDuRapportPdf);
					System.out.print(cheminDuRapportPdf+" vient d'etre cree\n");
				} catch (Exception e) {
					// TODO: handle exception
				}	
		
								
				System.out.print("Geration des rapports terminee\n");
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
