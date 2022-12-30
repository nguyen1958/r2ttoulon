package fr.analogon.r2t.Utilitaire;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServlet;

import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.request.RequestParametres;


public  class FichierDeConfiguration extends HttpServlet
{
	 static  String cheminFichierConfiguration;
	 static  String ville;
	 String cheminAbsoluTemplateRapport ="";	 
	 
	 public String getCheminAbsoluTemplateRapport() {
		return cheminAbsoluTemplateRapport;
	}

	public void setTemplateRapport(String cheminAbsoluTemplateRapport) {
		cheminAbsoluTemplateRapport = cheminAbsoluTemplateRapport;
	}

	 String cheminDossierTmp;
	 String cheminAbsoluApplication;	
	 String cheminFichierToPleiade;
	 String cheminFichierFromPleiade;
	 String cheminRapportJRXMLFacture;
	 String cheminRapportJRXMLRemboursement;
	 String cheminRapportJRXMLFactureTLPE;
	 String cheminRoleJRXML;
	 String cheminJRXMLCourrier;
	 String cheminRapportModele;
	 String cheminRapportModeleRemboursement;
	 String cheminRapportModeleTLPE;	 
	 
	 String cheminListeQuittancesJournalierToulon;
	 String cheminRecapitulatifComptesJournalierToulon;
	 String cheminVersementChequesJournalierToulon;
	 String cheminVersementChequesJournalierComptesRegieToulon;
	 String cheminVersementCarteBancaireJournalierToulon;
	 
	 
	 //Chemin vers les tempaltes des rapports marche
	 String cheminEncaissementJour;
	 String cheminSuiviPresenceRedevable;
	 String cheminRecapMensuelParFamilleMarche;
	 String cheminRecapMoisParJour;
	 String cheminRecuAbonne;

	 String cheminJRXMLExport;
	 
	 String dossierFacture;
		
		

	public String getCheminVersementChequesJournalierComptesRegieToulon() {
		return cheminVersementChequesJournalierComptesRegieToulon;
	}
	
	public void setCheminVersementChequesJournalierComptesRegieToulon(
			String cheminVersementChequesJournalierComptesRegieToulon) {
		this.cheminVersementChequesJournalierComptesRegieToulon = cheminVersementChequesJournalierComptesRegieToulon;
	}

	 String cheminVersementJournalierToulon;
	 String cheminRapportModeleTLPEToulon; 
	 String cheminRapportListeQuittancesJournalierToulon;
	 String cheminRapportRecapitulatifComptesJournalierToulon;
	 String cheminRapportVersementChequesJournalierToulon;
	 String cheminRapportVersementCarteBancaireJournalierToulon;
	 String cheminRapportVersementChequesJournalierCompteRegie;
	 String cheminRapportVersementJournalierToulon; 
	 String cheminRapportJRXMLFactureTLPEToulon;
	 String cheminModeleTLPEToulon;	 
	 String cheminRapportJournalierToulon;
	 String cheminRapportMarcheToulon;
	 String cheminRapportMensuelToulon;
	 String cheminRapportTrimestrielToulon;
	 String cheminFactureTLPEToulon;	
	 String cheminFichierFilien;
	 String cheminFichierExcelImpaye;
	 
	 
	 	String lienRapportEncaissementJour;
		String lienRapportSuiviPresenceRedevable;
		String lienRapportRecapMensuelParFamilleMarche;
		String lienRapportRecapMoisParJour;
		String lienRapportRecuAbonne;
		
		String cheminRapportEncaissementJour;
		String cheminRapportSuiviPresenceRedevable;
		String cheminRapportRecapMensuelParFamilleMarche;
		String cheminRapportRecapMoisParJour;
		String cheminRapportRecuAbonne;
	 
	 
	 
	 public String getCheminFichierExcelImpaye() {
		return cheminFichierExcelImpaye;
	}

	public void setCheminFichierExcelImpaye(String cheminFichierExcelImpaye) {
		this.cheminFichierExcelImpaye = cheminFichierExcelImpaye;
	}

	String cheminFactures;
	
	 String cheminIdoss;	 
	 String cheminCouriers;
	 String cheminData;
	 String cheminR2tData;
	 String nomFichierLogs;
	 String cheminFluxPleiade;
	 
	 
	 
	 
	 


	 	 
	 
	 
	 
	 
	 
	 
	 
	 //Type de taxe Pub
	 String cheminRapportModelePubToulon; 
	 String cheminRapportJRXMLFacturePubToulon;
	 
		
	 //Type de taxe par defaut de toulon
	 String cheminRapportModeleParDefautToulon; 
	 String cheminRapportJRXMLFactureParDefautToulon;
	 
	 
	 
	//Attribut dans le fichier de configuration
	 
	 String dbName="";
	 String host="";
	 String port="";
	 String user="";
	 String password="";
	 String tailleMaxImage="";
	 String tailleMaxDocument="";
	 String adresseServeur="";
	 
	 //Paul 16/10/2019 ajouter les parametres pour serveur sftp
	 String userftp="";
	 String portftp="";
	 String serveurftp="";
	 String passwordftp="";
	 String cheminfactureftp="";
		 
	 
	 String anneeFacturationTLPE="";
	 
	 public String getTailleMaxImage() {
		return tailleMaxImage;
	}

	public void setTailleMaxImage(String tailleMaxImage) {
		this.tailleMaxImage = tailleMaxImage;
	}

	public String getTailleMaxDocument() {
		return tailleMaxDocument;
	}

	public void setTailleMaxDocument(String tailleMaxDocument) {
		this.tailleMaxDocument = tailleMaxDocument;
	}

	//String configuration="";		 
	 String cheminRoleFacturation="";
	 String cheminRoleCommunique="";
	 String cheminRoleAnnulation="";
	 String cheminRoleCertificatAnnulationRecette="";
	 String cheminRoleEtatJustificatifRecette="";
	 String cheminRoleChgtAdresseRedevable="";
	 String cheminRoleSuivieFacturation="";
	 String cheminPhotosEmplacement="";
	 String cheminFichierLog="";	 
	 String cheminDataCourier="";
	 File fichierProperties ;
	 private String lienData="";
	 private String lienRoleAnnulation="";
	 private String lienRemboursement="";	 
	 private String lienRoleRemboursement="";
	 private String cheminRemboursement="";
	 private String cheminRoleRemboursement="";
	 private String lienDossierTmp="";
	 private String lienRoleCommunique="";
	 private String lienRoleJustificatif="";
	 private String lienRoleFacturation="";
	 private String lienCertificatAnnulationRecette="";
	 private String listeFactures="";
	 private String lienFichierDesFactures="";	
	 private String lienFichierToPleaide="";
	 private String lienFichierFromPleaide="";
	 private String lienFichierFilien="";
	 private String lienFichierExcelImpaye="";
	 private String lienSuivieDeFacturation="";
	 private String lienRoleChgtAdresseRedevable="";
	 private String lienPhotos="";
	 
	 String lienRapportListeQuittancesJournalierToulon;
	 String lienRapportRecapitulatifComptesJournalierToulon;
	 String lienRapportVersementChequesJournalierToulon;
     String lienRapportVersementChequesJournalierCompteRegieToulon;
     String lienRapportVersementCarteBancaireJournalierToulon;
	 String lienRapportVersementJournalierToulon;
	 String lienRapportVersementMensuelToulon;
	 String lienRapportRecapitulatifComptesMensuelToulon;
	 
	 
	 
	 
	 
	 
	 
	 
	 String cheminRapportVersementMensuelToulon;
	 String cheminRapportRecapitulatifComptesMensuelToulon;
	 
	 String cheminVersementMensuelToulon;
	 String cheminRecapitulatifComptesMensuelToulon;

	 
			
 
	   //template Trimestriel
		String cheminBalanceTrimestrielToulon;		
		String cheminVentilationParBaremeTrimestrielToulon;
		String cheminVentilationParEmplacementTrimestrielToulon;		
		String cheminListeDesAnnulationsTrimestrielToulon;
		String cheminListeDesAcomptesTrimestrielToulon;
		String cheminListeDesImpayesTrimestrielToulon;
		
		
		//fichier PDF Trimestriel	
		String cheminRapportBalanceTrimestrielToulon;		
		String cheminRapportVentilationParBaremeTrimestrielToulon;
		String cheminRapportVentilationParEmplacementTrimestrielToulon;		
		String cheminRapportListeDesAnnulationsTrimestrielToulon;		
		String cheminRapportListeDesAcomptesTrimestrielToulon;
		String cheminRapportListeDesImpayesTrimestrielToulon;		
		String lienRapportBalanceTrimestrielToulon;		
		String lienRapportVentilationParBaremeTrimestrielToulon;
		String lienRapportVentilationParEmplacementTrimestrielToulon;		
		String lienRapportListeDesAnnulationsTrimestrielToulon;
		String lienRapportListeDesAcomptesTrimestrielToulon;				
		String lienListeDesImpayesTrimestrielToulon;

		
		
		 /**
		 * @return the cheminBalanceAnnuelToulon
		 */
		public String getCheminBalanceAnnuelToulon() {
			return cheminBalanceAnnuelToulon;
		}

		/**
		 * @param cheminBalanceAnnuelToulon the cheminBalanceAnnuelToulon to set
		 */
		public void setCheminBalanceAnnuelToulon(String cheminBalanceAnnuelToulon) {
			this.cheminBalanceAnnuelToulon = cheminBalanceAnnuelToulon;
		}

		public String getLienFichierExcelImpaye() {
				return lienFichierExcelImpaye;
		}

		public void setLienFichierExcelImpaye(String lienFichierExcelImpaye) {
			this.lienFichierExcelImpaye = lienFichierExcelImpaye;
		}	

		public String getCheminRapportListeDesAcomptesTrimestrielToulon() {
			return cheminRapportListeDesAcomptesTrimestrielToulon;
		}

		public void setCheminRapportListeDesAcomptesTrimestrielToulon(
				String cheminRapportListeDesAcomptesTrimestrielToulon) {
			this.cheminRapportListeDesAcomptesTrimestrielToulon = cheminRapportListeDesAcomptesTrimestrielToulon;
		}

		public String getLienRapportListeDesAcomptesTrimestrielToulon() {
			return lienRapportListeDesAcomptesTrimestrielToulon;
		}

		public void setLienRapportListeDesAcomptesTrimestrielToulon(
				String lienRapportListeDesAcomptesTrimestrielToulon) {
			this.lienRapportListeDesAcomptesTrimestrielToulon = lienRapportListeDesAcomptesTrimestrielToulon;
		}

		public String getLienRapportListeDesAnnulationsTrimestrielToulon() {
			return lienRapportListeDesAnnulationsTrimestrielToulon;
		}

		public void setLienRapportListeDesAnnulationsTrimestrielToulon(
				String lienRapportListeDesAnnulationsTrimestrielToulon) {
			this.lienRapportListeDesAnnulationsTrimestrielToulon = lienRapportListeDesAnnulationsTrimestrielToulon;
		}
		
		public String getLienRapportBalanceTrimestrielToulon() {
			return lienRapportBalanceTrimestrielToulon;
		}

		public void setLienRapportBalanceTrimestrielToulon(
				String lienRapportBalanceTrimestrielToulon) {
			this.lienRapportBalanceTrimestrielToulon = lienRapportBalanceTrimestrielToulon;
		}

		public String getLienRapportVentilationParBaremeTrimestrielToulon() {
			return lienRapportVentilationParBaremeTrimestrielToulon;
		}

		public void setLienRapportVentilationParBaremeTrimestrielToulon(
				String lienRapportVentilationParBaremeTrimestrielToulon) {
			this.lienRapportVentilationParBaremeTrimestrielToulon = lienRapportVentilationParBaremeTrimestrielToulon;
		}

		public String getLienRapportVentilationParEmplacementTrimestrielToulon() {
			return lienRapportVentilationParEmplacementTrimestrielToulon;
		}

		public void setLienRapportVentilationParEmplacementTrimestrielToulon(
				String lienRapportVentilationParEmplacementTrimestrielToulon) {
			this.lienRapportVentilationParEmplacementTrimestrielToulon = lienRapportVentilationParEmplacementTrimestrielToulon;
		}

	
		/**
		 * @return the cheminRapportBalanceAnnuelToulon
		 */
		public String getCheminRapportBalanceAnnuelToulon() {
			return cheminRapportBalanceAnnuelToulon;
		}

		/**
		 * @param cheminRapportBalanceAnnuelToulon the cheminRapportBalanceAnnuelToulon to set
		 */
		public void setCheminRapportBalanceAnnuelToulon(
				String cheminRapportBalanceAnnuelToulon) {
			this.cheminRapportBalanceAnnuelToulon = cheminRapportBalanceAnnuelToulon;
		}


		String lienRoleSuivieFacturation="";
		

		///////////////////////////////
		//Rapport Comptable annuelle////
		///////////////////////////////
		String lienRapportBalanceAnnuelToulon;  //lien vers le fichier qui contient le template
		String cheminRapportBalanceAnnuelToulon ; // Chemin vers dossier du rapport generé : r2t\r2tData\data\toulon\RapportComptableAnnuel
		String cheminBalanceAnnuelToulon; //lien vers le dossier qui contient le template
		
		
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
		
		String lienRapportListeDesAcomptesAnnuelToulon="";		
		String lienRapportListeDesAnnulationsAnnuelToulon="";
		String lienRapportListeDesImpayesAnnuelEtalageToulon="";
		String lienRapportListeDesImpayesAnnuelTLPEToulon="";
		String lienRapportVentilationParBaremeAnnuelEtalageToulon="";
		String lienRapportVentilationParBaremeAnnuelTLPEToulon="";
		String lienRapportVentilationParEmplacementAnnuelEtalageToulon="";
		String lienRapportVentilationParEmplacementAnnuelTLPEToulon="";

		/**
		 * @return the lienRapportBalanceAnnuelToulon
		 */
		public String getLienRapportBalanceAnnuelToulon() {
			return lienRapportBalanceAnnuelToulon;
		}

		/**
		 * @param lienRapportBalanceAnnuelToulon the lienRapportBalanceAnnuelToulon to set
		 */
		public void setLienRapportBalanceAnnuelToulon(
				String lienRapportBalanceAnnuelToulon) {
			this.lienRapportBalanceAnnuelToulon = lienRapportBalanceAnnuelToulon;
		}

		public String getLink (String adresseServeur , String cheminAbsolu)
		{
			String res="" ;
			int index = cheminAbsolu.indexOf("r2tData");
			if(index != -1)
			{
				cheminAbsolu = cheminAbsolu.substring(index, cheminAbsolu.length());
				//cheminAbsolu = adresseServeur + "/" + cheminAbsolu;
				cheminAbsolu = ReglerCheminSelonOs.reglerCheminSelonOS(cheminAbsolu);
				////System.out.println(cheminAbsolu);
				
			}
			return cheminAbsolu;
		}
		
	/**
	 * @return the cheminRoleAnnulation
	 */
	public final String getCheminRoleAnnulation() {
		return cheminRoleAnnulation;
	}






	/**
	 * @param cheminRoleAnnulation the cheminRoleAnnulation to set
	 */
	public final void setCheminRoleAnnulation(String cheminRoleAnnulation) {
		this.cheminRoleAnnulation = cheminRoleAnnulation;
	}

	 public String getCheminRapportModeleParDefautToulon() {
			return cheminRapportModeleParDefautToulon;
		}

		public void setCheminRapportModeleParDefautToulon(
				String cheminRapportModeleParDefautToulon) {
			this.cheminRapportModeleParDefautToulon = cheminRapportModeleParDefautToulon;
		}

		public String getCheminRapportJRXMLFactureParDefautToulon() {
			return cheminRapportJRXMLFactureParDefautToulon;
		}

		public void setCheminRapportJRXMLFactureParDefautToulon(
				String cheminRapportJRXMLFactureParDefautToulon) {
			this.cheminRapportJRXMLFactureParDefautToulon = cheminRapportJRXMLFactureParDefautToulon;
		}



	/**
	 * @return the cheminRoleCertificatAnnulationRecette
	 */
	public final String getCheminRoleCertificatAnnulationRecette() {
		return cheminRoleCertificatAnnulationRecette;
	}





	/**
	 * @param cheminRoleCertificatAnnulationRecette the cheminRoleCertificatAnnulationRecette to set
	 */
	public final void setCheminRoleCertificatAnnulationRecette(
			String cheminRoleCertificatAnnulationRecette) {
		this.cheminRoleCertificatAnnulationRecette = cheminRoleCertificatAnnulationRecette;
	}






	public final String getCheminRoleEtatJustificatifRecette() {
		return cheminRoleEtatJustificatifRecette;
	}





	public final void setCheminRoleEtatJustificatifRecette(
			String cheminRoleEtatJustificatifRecette) {
		this.cheminRoleEtatJustificatifRecette = cheminRoleEtatJustificatifRecette;
	}
	





	/**
	 * @return the cheminRoleFacturation
	 */
	public final String getCheminRoleFacturation() {
		return cheminRoleFacturation;
	}





	/**
	 * @param cheminRoleFacturation the cheminRoleFacturation to set
	 */
	public final void setCheminRoleFacturation(String cheminRoleFacturation) {
		this.cheminRoleFacturation = cheminRoleFacturation;
	}

	public  FichierDeConfiguration()
	{
		try 
		{
			String urlTmp = FichierDeConfiguration.class.getResource("FichierDeConfiguration.class").toString();
			System.out.println("****************************************");
			System.out.println("CheminClassFC="+urlTmp);
		    urlTmp = urlTmp.replaceAll("file:","");
		    urlTmp = urlTmp.replaceAll("zip:","");			
			if (urlTmp.startsWith(":"))	urlTmp = urlTmp.replaceAll(":", "");
			System.out.println("Index WEB-INF="+urlTmp.indexOf("WEB-INF"));
			if(urlTmp.indexOf("WEB-INF") !=-1)
				urlTmp= urlTmp.substring(0,urlTmp.indexOf("WEB-INF"));
			else
				urlTmp= urlTmp.substring(0,urlTmp.indexOf("build"));
				//Paul pour Test2.java
				//urlTmp= urlTmp.substring(0,urlTmp.indexOf("build"))+"WebContent/";
			
			urlTmp = ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminAbsoluApplication =urlTmp;
			System.out.println("after reglerCheminSelonOS="+urlTmp);
			//Recuperation du fichier de configuration 
			urlTmp = cheminAbsoluApplication+"properties/application.properties";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminFichierConfiguration= urlTmp;			
					
			System.out.println("cheminFichierConfiguration="+cheminFichierConfiguration);
			try
			{				
				fichierProperties = new File(cheminFichierConfiguration);				
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				urlTmp="/data/domains/diiDomain/applications/r2t/";
				urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
				cheminAbsoluApplication =urlTmp;	
				
				urlTmp = cheminAbsoluApplication+"properties/application.properties";
				urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
				cheminFichierConfiguration= urlTmp;					
				fichierProperties = new File(urlTmp);			
			}
			Properties p = new Properties();
			//System.out.println(">>>Lecture du fichier de configuration : " + fichierProperties);
			FileInputStream propriete = new FileInputStream(fichierProperties);			
			p.load(propriete);
			//Chargement des variables a partir du fichier  
			host= p.getProperty("host");
			port= p.getProperty("port");
			dbName= p.getProperty("dbName");
			user= p.getProperty("user");			
			password= p.getProperty("password");
			tailleMaxImage= p.getProperty("tailleMaxImage");
			tailleMaxDocument= p.getProperty("tailleMaxDocument");
			anneeFacturationTLPE=p.getProperty("anneeFacturationTLPE");
			adresseServeur=p.getProperty("adresseServeur");
			//Paul 16/10/2019 ajouter les parametres su serveur ftp
			userftp=p.getProperty("sftp.user");
			passwordftp=p.getProperty("sftp.password");
			serveurftp=p.getProperty("sftp.serveur");
			portftp=p.getProperty("sftp.port");
			cheminfactureftp=p.getProperty("sftp.cheminfacture");
			
			System.out.println(host+":"+port+":"+dbName+":"+user+":"+password);
			
			cheminR2tData = p.getProperty("cheminR2tData");			
			cheminDataCourier =p.getProperty("cheminDataCourier");
			//configuration	=p.getProperty("configuration");	
			
			cheminData = cheminR2tData+"/data/";
			cheminData= ReglerCheminSelonOs.reglerCheminSelonOS(cheminData);
			
					
			cheminAbsoluTemplateRapport = cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/";
			
			//paul filien 263 
			dossierFacture= p.getProperty("dossierFacture");
			System.out.println("dossierFacture"+dossierFacture);
			
			//Les Fichiers utulise pour la facturation :
			 RequestParametres requestParametres = new RequestParametres();
				ville = requestParametres.getVille();
			
	
			DebuggerLog4J.logger.debug("DC, ville ="+ville);
			if (ville.equalsIgnoreCase("toulon"))			
			{
				//Template de toulon
				urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/TemplateFacture/Facturer2tToulonV1.jrxml";
			}
			else
			{
				//Template de bordeaux 
				urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/TemplateFacture/Facturer2tBordeauxV1.jrxml";	
			}
			
			DebuggerLog4J.logger.debug("Template utilise="+urlTmp);
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportJRXMLFacture =urlTmp;
			
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/TemplateFactureTLPE/FactureTLPEr2tBordeauxV1.jrxml";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportJRXMLFactureTLPE =urlTmp;
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/Remboursement/Remboursementr2tBordeauxV1.jrxml";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportJRXMLRemboursement = urlTmp;
			
			
			
			//toto
			DebuggerLog4J.logger.debug("ville="+ville);
			if (ville.equalsIgnoreCase("toulon"))			
			{
				//cheminRoleJRXML r2t\WEB-INF\Templates\TemplateRapport\RapportToulon
				urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/";
			}
			else
			{			
				urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/";
				
			}
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			DebuggerLog4J.logger.debug("cheminRoleJRXML="+cheminRoleJRXML);
			cheminRoleJRXML =urlTmp;
						
			//toto
			if (ville.equalsIgnoreCase("toulon"))			
			{
				//r2t\WEB-INF\Templates\TemplateRapport\RapportToulon
				urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/TemplateFactures/TemplateFacture/";
			}
			else
			{
				urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/TemplateFacture/";				
			}
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportModele =urlTmp;

			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/TemplateFactureTLPE/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportModeleTLPE =urlTmp;
			
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/Remboursement/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportModeleRemboursement =urlTmp;
		
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateCourrierReclamation/CourrierReclamation";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminJRXMLCourrier =urlTmp;
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateExport/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminJRXMLExport =urlTmp;
			
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/TemplateFactures/TemplateFactureTLPE/FactureTLPEr2tToulonV1.jrxml";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportJRXMLFactureTLPEToulon =urlTmp;
			
		
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/TemplateFactures/TemplateFactureTaxePublicite/FactureTaxePubliciter2tToulonV1.jrxml";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportJRXMLFacturePubToulon =urlTmp;
		
			//facture par defaut pour toulon
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/TemplateFactures/TemplateFacture/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportModeleParDefautToulon =urlTmp;
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/TemplateFactures/TemplateFacture/Facturer2tToulonV1.jrxml";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportJRXMLFactureParDefautToulon =urlTmp;
		
			//Chemin vers les templates des rapports de marche
			String utlToulon = cheminAbsoluApplication + "/WEB-INF/Templates/TemplateRapport/RapportToulon/";
			
			urlTmp= utlToulon +"TicketsMarche/ToulonMarcheEncaissementJour/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminEncaissementJour =urlTmp;
			
			urlTmp= utlToulon +"TicketsMarche/ToulonMarcheMarcheSuiviPresenceRedevable/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminSuiviPresenceRedevable =urlTmp;
			
			urlTmp= utlToulon +"TicketsMarche/ToulonMarcheRecapMensuelParMarche/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRecapMensuelParFamilleMarche =urlTmp;
			
			urlTmp= utlToulon +"TicketsMarche/ToulonMarcheRecapMoisParJour/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRecapMoisParJour =urlTmp;
			
			urlTmp= utlToulon +"TicketsMarche/ToulonMarcheRecuAbonne/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRecuAbonne =urlTmp;
			
			
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Journalier/ListeQuittancesJournalierToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminListeQuittancesJournalierToulon =urlTmp;
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Journalier/RecapitulatifComptesJournalierToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRecapitulatifComptesJournalierToulon =urlTmp;
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Journalier/VersementChequesJournalierToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminVersementChequesJournalierToulon =urlTmp;
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Journalier/VersementCarteBancaireJournalierToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminVersementCarteBancaireJournalierToulon =urlTmp;
			
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Journalier/VersementChequesCompteRegieJournalierToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminVersementChequesJournalierComptesRegieToulon =urlTmp;
			
			
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Journalier/VersementJournalierToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminVersementJournalierToulon =urlTmp;
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/TemplateFactures/TemplateFactureTLPE/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportModeleTLPEToulon =urlTmp;
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/TemplateFactures/TemplateFactureTaxePublicite/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportModelePubToulon =urlTmp;
			
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/TemplateFactures/TemplateFactureTLPE/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminModeleTLPEToulon =urlTmp;			
			
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Mensuel/RecapitulatifComptesMensuelToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRecapitulatifComptesMensuelToulon =urlTmp;			

			
						
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Mensuel/VersementMensuelToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminVersementMensuelToulon =urlTmp;
			
			//Chemin Rapports Trimestriel
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Trimestriel/BalanceTrimestrielToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);			
			cheminBalanceTrimestrielToulon =urlTmp;
			

			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Trimestriel/VentilationParBaremeTrimestrielToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminVentilationParBaremeTrimestrielToulon =urlTmp;			

			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Trimestriel/VentilationParEmplacementTrimestrielToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminVentilationParEmplacementTrimestrielToulon =urlTmp;
			
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Trimestriel/ListeDesAnnulationsTrimestrielToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminListeDesAnnulationsTrimestrielToulon =urlTmp;
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Trimestriel/ListeDesAcomptesTrimestrielToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminListeDesAcomptesTrimestrielToulon =urlTmp;
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Trimestriel/ListeDesImpayesTrimestrielToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminListeDesImpayesTrimestrielToulon =urlTmp;
			
			
			//Rapport Comptable annuelle 
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Annuel/BalanceAnnuelToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminBalanceAnnuelToulon =urlTmp;
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Annuel/ListeDesAcomptesAnnuelToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminListeDesAcomptesAnnuelToulon =urlTmp;
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Annuel/ListeDesAnnulationsAnnuelToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminListeDesAnnulationsAnnuelToulon =urlTmp;
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Annuel/ListeDesImpayesAnnuelEtalageToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminListeDesImpayesAnnuelEtalageToulon =urlTmp;
					
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Annuel/ListeDesImpayesAnnuelTLPEToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminListeDesImpayesAnnuelTLPEToulon =urlTmp;
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Annuel/VentilationParBaremeAnnuelEtalageToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminVentilationParBaremeAnnuelEtalageToulon =urlTmp;
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Annuel/VentilationParBaremeAnnuelTLPEToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminVentilationParBaremeAnnuelTLPEToulon =urlTmp;
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Annuel/VentilationParEmplacementAnnuelEtalageToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminVentilationParEmplacementAnnuelEtalageToulon =urlTmp;
			
			urlTmp= cheminAbsoluApplication +"/WEB-INF/Templates/TemplateRapport/RapportToulon/Annuel/VentilationParEmplacementAnnuelTLPEToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminVentilationParEmplacementAnnuelTLPEToulon =urlTmp;
			

			//Logs			
			urlTmp= cheminR2tData + "/logs/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminFichierLog=urlTmp;
						 
			//urlTmp= "r2t/"+cheminR2tData + "/photos/";
			urlTmp =cheminR2tData + "/photos/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminPhotosEmplacement=urlTmp;
			
			urlTmp= cheminR2tData + "/idoss/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminIdoss=urlTmp;
			
			urlTmp= cheminR2tData + "/tmp/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminDossierTmp=urlTmp;
			
			urlTmp= cheminR2tData + "/pleiade/toPleiade/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminFichierToPleiade=urlTmp;				
			 
			urlTmp= cheminR2tData + "/pleiade/fromPleiade/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminFichierFromPleiade=urlTmp;	
			
			urlTmp= cheminR2tData + "/filien/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminFichierFilien=urlTmp;
			
			urlTmp= cheminR2tData + "/ExcelImpaye/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminFichierExcelImpaye=urlTmp;
			
			
			 
			urlTmp= cheminData + "/factures/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminFactures=urlTmp;
			
			
			
			
			
			
			
			
			urlTmp= cheminData + "/courriers/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminCouriers=urlTmp;
			
			
						
			urlTmp= cheminData + "/RoleCommuniqueOuvrage/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRoleCommunique=urlTmp;
			
			urlTmp= cheminData + "/RoleFacturation/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRoleFacturation=urlTmp;	
			
			urlTmp= cheminData + "/RoleAnnulation/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRoleAnnulation=urlTmp;	
			
			urlTmp= cheminData + "/RoleCertificatAnnulationRecette/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRoleCertificatAnnulationRecette=urlTmp;
			
			
			urlTmp= cheminData + "/RoleEtatJustificatifRecette/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRoleEtatJustificatifRecette=urlTmp;
			
			

			urlTmp= cheminData +"/Remboursement/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRemboursement =urlTmp;	
			
			
			urlTmp= cheminData +"/RoleRemboursement/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRoleRemboursement =urlTmp;
						
			
			urlTmp= cheminData +"/RoleChgtAdresseRedevable/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRoleChgtAdresseRedevable=urlTmp;	
			
			urlTmp= cheminData +"/RoleSuiviFacturation/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRoleSuivieFacturation=urlTmp;			
			
			
			urlTmp= cheminData +"/RoleChgtAdresseRedevable/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRoleChgtAdresseRedevable=urlTmp;			
			
				
			urlTmp= cheminData +"/toulon/RapportComptableJournalier/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportJournalierToulon=urlTmp;
			
			urlTmp= cheminData +"/toulon/RapportTicketsMarche/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportMarcheToulon=urlTmp;
			
			
			urlTmp= cheminData +"/toulon/RapportComptableMensuel/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportMensuelToulon=urlTmp;
			
			urlTmp= cheminData +"/toulon/RapportComptableTrimestriel/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportTrimestrielToulon=urlTmp;			

			urlTmp= cheminRapportTrimestrielToulon +"/BalanceTrimestrielToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportBalanceTrimestrielToulon=urlTmp;
			
			
			
			urlTmp= cheminRapportTrimestrielToulon +"/VentilationParBaremeTrimestrielToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportVentilationParBaremeTrimestrielToulon=urlTmp;	
			
			urlTmp= cheminRapportTrimestrielToulon +"/VentilationParEmplacementTrimestrielToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportVentilationParEmplacementTrimestrielToulon=urlTmp;				
			
			
			
			
			
			urlTmp= cheminRapportTrimestrielToulon +"/ListeDesAnnulationsTrimestrielToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportListeDesAnnulationsTrimestrielToulon=urlTmp;
			
			urlTmp= cheminRapportTrimestrielToulon +"/ListeDesImpayesTrimestrielToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportListeDesImpayesTrimestrielToulon=urlTmp;
			
			
			urlTmp= cheminRapportTrimestrielToulon +"/ListeDesAcomptesTrimestrielToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportListeDesAcomptesTrimestrielToulon=urlTmp;
			
			
			
			
			
			urlTmp= cheminRapportMensuelToulon +"/RecapitulatifComptesMensuelToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportRecapitulatifComptesMensuelToulon=urlTmp;
			
			urlTmp= cheminRapportMensuelToulon +"/VersementMensuelToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportVersementMensuelToulon=urlTmp;

			
			
			urlTmp= cheminRapportJournalierToulon +"/ListeQuittancesJournalierToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportListeQuittancesJournalierToulon=urlTmp;
			
			urlTmp= cheminRapportJournalierToulon +"/RecapitulatifComptesJournalierToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportRecapitulatifComptesJournalierToulon=urlTmp;	

			urlTmp= cheminRapportJournalierToulon +"/VersementChequesJournalierToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportVersementChequesJournalierToulon=urlTmp;
			
			urlTmp= cheminRapportJournalierToulon +"/VersementCarteBancaireJournalierToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportVersementCarteBancaireJournalierToulon=urlTmp;
			
			
			
			urlTmp= cheminRapportJournalierToulon +"/VersementChequesCompteRegieJournalierToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportVersementChequesJournalierCompteRegie=urlTmp;
			
			
			urlTmp= cheminRapportJournalierToulon +"/VersementJournalierToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportVersementJournalierToulon=urlTmp;	
					
			
			urlTmp= cheminData +"/toulon/FacturesTLPE/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminFactureTLPEToulon=urlTmp;
			 
			
			
			
			//Rapport comptable marche :
			urlTmp= cheminRapportMarcheToulon +"/ToulonMarcheEncaissementJour/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportEncaissementJour=urlTmp;
			
			urlTmp= cheminRapportMarcheToulon +"/ToulonMarcheMarcheSuiviPresenceRedevable/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportSuiviPresenceRedevable=urlTmp;
			
			urlTmp= cheminRapportMarcheToulon +"/ToulonMarcheRecapMensuelParMarche/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportRecapMensuelParFamilleMarche=urlTmp;
			
			urlTmp= cheminRapportMarcheToulon +"/ToulonMarcheRecapMoisParJour/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportRecapMoisParJour=urlTmp;
			
			urlTmp= cheminRapportMarcheToulon +"/ToulonMarcheRecuAbonne/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportRecuAbonne=urlTmp;
			
			
			
			
			
			
			
			
			//Rapport comptable annuelle
			urlTmp= cheminData +"/toulon/RapportComptableAnnuel/BalanceAnnuelToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportBalanceAnnuelToulon=urlTmp;
			
			urlTmp= cheminData +"/toulon/RapportComptableAnnuel/ListeDesAcomptesAnnuelToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportListeDesAcomptesAnnuelToulon=urlTmp;
			
			urlTmp= cheminData +"/toulon/RapportComptableAnnuel/ListeDesAnnulationsAnnuelToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportListeDesAnnulationsAnnuelToulon=urlTmp;
			
			urlTmp= cheminData +"/toulon/RapportComptableAnnuel/ListeDesImpayesAnnuelEtalageToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportListeDesImpayesAnnuelEtalageToulon=urlTmp;
			
			urlTmp= cheminData +"/toulon/RapportComptableAnnuel/ListeDesImpayesAnnuelTLPEToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportListeDesImpayesAnnuelTLPEToulon=urlTmp;
			
			urlTmp= cheminData +"/toulon/RapportComptableAnnuel/VentilationParBaremeAnnuelEtalageToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportVentilationParBaremeAnnuelEtalageToulon=urlTmp;
			
			urlTmp= cheminData +"/toulon/RapportComptableAnnuel/VentilationParBaremeAnnuelTLPEToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportVentilationParBaremeAnnuelTLPEToulon=urlTmp;
			
			urlTmp= cheminData +"/toulon/RapportComptableAnnuel/VentilationParEmplacementAnnuelEtalageToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportVentilationParEmplacementAnnuelEtalageToulon=urlTmp;
			
			urlTmp= cheminData +"/toulon/RapportComptableAnnuel/VentilationParEmplacementAnnuelTLPEToulon/";
			urlTmp= ReglerCheminSelonOs.reglerCheminSelonOS(urlTmp);
			cheminRapportVentilationParEmplacementAnnuelTLPEToulon=urlTmp;

			
			
			
			
			
			
			
			
						
			String link="";
			////System.out.println("Adresse"+link);
			this.lienData=getLink(link,   cheminData);
			
			this.lienRoleAnnulation=getLink(link,   cheminRoleAnnulation);
			this.lienDossierTmp=getLink(link,   cheminDossierTmp);
			this.lienRoleCommunique=getLink(link,   cheminRoleCommunique);
			
			this.lienRoleJustificatif=getLink(link,  cheminRoleEtatJustificatifRecette);			
			this.lienRoleFacturation=getLink(link,  cheminRoleFacturation );
			this.lienCertificatAnnulationRecette=getLink(link,   cheminRoleCertificatAnnulationRecette);			
			this.lienFichierDesFactures	=getLink(link,   cheminFactures);
			this.lienFichierToPleaide=getLink(link,  cheminFichierToPleiade);
			this.lienFichierFromPleaide=getLink(link, cheminFichierFromPleiade);
			
			this.lienFichierFilien =getLink(link, cheminFichierFilien);
			this.lienFichierExcelImpaye = getLink(link, cheminFichierExcelImpaye);
			
			this.lienSuivieDeFacturation= getLink(link, cheminRoleSuivieFacturation);			
			this.lienRoleChgtAdresseRedevable  = getLink(link, cheminRoleChgtAdresseRedevable);
			this.lienRoleSuivieFacturation= getLink(link, cheminRoleSuivieFacturation);
			this.lienPhotos = getLink(link, cheminPhotosEmplacement);
						
			this.lienRapportListeQuittancesJournalierToulon=getLink(link, cheminRapportListeQuittancesJournalierToulon);
			this.lienRapportRecapitulatifComptesJournalierToulon=getLink(link, cheminRapportRecapitulatifComptesJournalierToulon);
			this.lienRapportVersementChequesJournalierToulon=getLink(link, cheminRapportVersementChequesJournalierToulon);
			this.lienRapportVersementCarteBancaireJournalierToulon=getLink(link, cheminRapportVersementCarteBancaireJournalierToulon);
			
			this.lienRapportVersementChequesJournalierCompteRegieToulon=getLink(link, cheminRapportVersementChequesJournalierCompteRegie);
			
			
			
			
			
			
			
			this.lienRapportBalanceTrimestrielToulon= getLink(link,   cheminRapportBalanceTrimestrielToulon);
			this.lienRapportVentilationParBaremeTrimestrielToulon= getLink(link,   cheminRapportVentilationParBaremeTrimestrielToulon);
			this.lienRapportVentilationParEmplacementTrimestrielToulon= getLink(link,   cheminRapportVentilationParEmplacementTrimestrielToulon);
			this.lienRapportListeDesAcomptesTrimestrielToulon= getLink(link,   cheminRapportListeDesAcomptesTrimestrielToulon);
			this.lienRapportListeDesAnnulationsTrimestrielToulon= getLink(link,   cheminRapportListeDesAnnulationsTrimestrielToulon);
			this.lienListeDesImpayesTrimestrielToulon= getLink(link,   cheminRapportListeDesImpayesTrimestrielToulon);
			
			this.lienRapportVersementJournalierToulon=getLink(link, cheminRapportVersementJournalierToulon);
			
			
			
			this.lienRapportVersementMensuelToulon=getLink(link, cheminRapportVersementMensuelToulon);
			this.lienRapportRecapitulatifComptesMensuelToulon=getLink(link, cheminRapportRecapitulatifComptesMensuelToulon);
			 
			
			
			this.lienRemboursement=getLink(link,  cheminRemboursement);
			this.lienRoleRemboursement =getLink(link,  cheminRoleRemboursement);
			
			
			//Rapport Comptable annuelle
			this.lienRapportBalanceAnnuelToulon=getLink(link, cheminRapportBalanceAnnuelToulon);
			this.lienRapportListeDesAcomptesAnnuelToulon=getLink(link, cheminRapportListeDesAcomptesAnnuelToulon);
			this.lienRapportListeDesAnnulationsAnnuelToulon=getLink(link, cheminRapportListeDesAnnulationsAnnuelToulon);
			this.lienRapportListeDesImpayesAnnuelEtalageToulon=getLink(link, cheminRapportListeDesImpayesAnnuelEtalageToulon);
			this.lienRapportListeDesImpayesAnnuelTLPEToulon=getLink(link, cheminRapportListeDesImpayesAnnuelTLPEToulon);
			this.lienRapportVentilationParBaremeAnnuelEtalageToulon=getLink(link, cheminRapportVentilationParBaremeAnnuelEtalageToulon);
			this.lienRapportVentilationParBaremeAnnuelTLPEToulon=getLink(link, cheminRapportVentilationParBaremeAnnuelTLPEToulon);
			this.lienRapportVentilationParEmplacementAnnuelEtalageToulon=getLink(link, cheminRapportVentilationParEmplacementAnnuelEtalageToulon);
			this.lienRapportVentilationParEmplacementAnnuelTLPEToulon=getLink(link, cheminRapportVentilationParEmplacementAnnuelTLPEToulon);
			
			
			
			//Rapport Comptable marché		
			this.lienRapportEncaissementJour=getLink(link, cheminRapportEncaissementJour);
			this.lienRapportSuiviPresenceRedevable=getLink(link, cheminRapportSuiviPresenceRedevable);
			this.lienRapportRecapMensuelParFamilleMarche=getLink(link, cheminRapportRecapMensuelParFamilleMarche);
			this.lienRapportRecapMoisParJour=getLink(link, cheminRapportRecapMoisParJour);
			this.lienRapportRecuAbonne=getLink(link, cheminRapportRecuAbonne);
			
			//paul
			//propriete.close();
			
			
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
			DebuggerLog4J.logger.fatal("Fichier introuvable "  + e1.getMessage());		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			DebuggerLog4J.logger.fatal(e.getMessage());		
		}		
	}





	/**
	 * @return the cheminFichierConfiguration
	 */
	public static final String getCheminFichierConfiguration() {
		return cheminFichierConfiguration;
	}





	/**
	 * @param cheminFichierConfiguration the cheminFichierConfiguration to set
	 */
	public static final void setCheminFichierConfiguration(
			String cheminFichierConfiguration) {
		FichierDeConfiguration.cheminFichierConfiguration = cheminFichierConfiguration;
	}





	/**
	 * @return the cheminAbsoluApplication
	 */
	public final String getCheminAbsoluApplication() {
		return cheminAbsoluApplication;
	}





	/**
	 * @param cheminAbsoluApplication the cheminAbsoluApplication to set
	 */
	public final void setCheminAbsoluApplication(String cheminAbsoluApplication) {
		this.cheminAbsoluApplication = cheminAbsoluApplication;
	}






	/**
	 * @return the cheminFichierFromPleiade
	 */
	public final String getCheminFichierFromPleiade() {
		return cheminFichierFromPleiade;
	}





	/**
	 * @param cheminFichierFromPleiade the cheminFichierFromPleiade to set
	 */
	public final void setCheminFichierFromPleiade(String cheminFichierFromPleiade) {
		this.cheminFichierFromPleiade = cheminFichierFromPleiade;
	}





	/**
	 * @return the cheminFichierToPleiade
	 */
	public final String getCheminFichierToPleiade() {
		return cheminFichierToPleiade;
	}





	/**
	 * @param cheminFichierToPleiade the cheminFichierToPleiade to set
	 */
	public final void setCheminFichierToPleiade(String cheminFichierToPleiade) {
		this.cheminFichierToPleiade = cheminFichierToPleiade;
	}





	/**
	 * @return the cheminRapportJRXML
	 */
	public final String getCheminRapportJRXMLFacture() {
		return cheminRapportJRXMLFacture;
	}





	/**
	 * @param cheminRapportJRXML the cheminRapportJRXML to set
	 */
	public final void setCheminRapportJRXMLFacture(String cheminRapportJRXMLFacture) {
		this.cheminRapportJRXMLFacture = cheminRapportJRXMLFacture;
	}





	/**
	 * @return the cheminRapportModele
	 */
	public final String getCheminRapportModele() {
		return cheminRapportModele;
	}





	/**
	 * @param cheminRapportModele the cheminRapportModele to set
	 */
	public final void setCheminRapportModele(String cheminRapportModele) {
		this.cheminRapportModele = cheminRapportModele;
	}





	/**
	 * @return the dbName
	 */
	public final String getDbName() {
		return dbName;
	}





	/**
	 * @param dbName the dbName to set
	 */
	public final void setDbName(String dbName) {
		this.dbName = dbName;
	}





	/**
	 * @return the host
	 */
	public final String getHost() {
		return host;
	}





	/**
	 * @param host the host to set
	 */
	public final void setHost(String host) {
		this.host = host;
	}










	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}





	/**
	 * @param password the password to set
	 */
	public final void setPassword(String password) {
		this.password = password;
	}





	




	/**
	 * @return the port
	 */
	public final String getPort() {
		return port;
	}





	/**
	 * @param port the port to set
	 */
	public final void setPort(String port) {
		this.port = port;
	}









	/**
	 * @return the user
	 */
	public final String getUser() {
		return user;
	}





	/**
	 * @param user the user to set
	 */
	public final void setUser(String user) {
		this.user = user;
	}






	
	/**
	 * @return the cheminFactures
	 */
	public  String getCheminFactures()
	{
		return cheminFactures;
	}






	/**
	 * @param cheminFactures the cheminFactures to set
	 */
	public  void setCheminFactures(String cheminFactures) {
		this.cheminFactures = cheminFactures;
	}

	public String getCheminRoleJRXML() {
		return cheminRoleJRXML;
	}





	public void setCheminRoleJRXML(String cheminRoleJRXML) {
		this.cheminRoleJRXML = cheminRoleJRXML;
	}

	public String getCheminData() {
		return cheminData;
	}





	public void setCheminData(String cheminData) {
		this.cheminData = cheminData;
	}





	public String getCheminRoleChgtAdresseRedevable() {
		return cheminRoleChgtAdresseRedevable;
	}





	public void setCheminRoleChgtAdresseRedevable(
			String cheminRoleChgtAdresseRedevable) {
		this.cheminRoleChgtAdresseRedevable = cheminRoleChgtAdresseRedevable;
	}





	/**
	 * @return the cheminRoleSuivieFacturation
	 */
	public final String getCheminRoleSuivieFacturation() {
		return cheminRoleSuivieFacturation;
	}





	/**
	 * @param cheminRoleSuivieFacturation the cheminRoleSuivieFacturation to set
	 */
	public final void setCheminRoleSuivieFacturation(
			String cheminRoleSuivieFacturation) {
		this.cheminRoleSuivieFacturation = cheminRoleSuivieFacturation;
	}





	public String getCheminPhotosEmplacement() {
		return cheminPhotosEmplacement;
	}





	public void setCheminPhotosEmplacement(String cheminPhotosEmplacement) {
		this.cheminPhotosEmplacement = cheminPhotosEmplacement;
	}





	/**
	 * @return the cheminFichierLog
	 */
	public final String getCheminFichierLog() {
		return cheminFichierLog;
	}





	/**
	 * @param cheminFichierLog the cheminFichierLog to set
	 */
	public final void setCheminFichierLog(String cheminFichierLog) {
		this.cheminFichierLog = cheminFichierLog;
	}





	/**
	 * @return the cheminFluxPleiade
	 */
	public final String getCheminFluxPleiade() {
		return cheminFluxPleiade;
	}





	/**
	 * @param cheminFluxPleiade the cheminFluxPleiade to set
	 */
	public final void setCheminFluxPleiade(String cheminFluxPleiade) {
		this.cheminFluxPleiade = cheminFluxPleiade;
	}





	/**
	 * @return the lienCertificatAnnulationRecette
	 */
	public final String getLienCertificatAnnulationRecette() {
		return lienCertificatAnnulationRecette;
	}





	/**
	 * @param lienCertificatAnnulationRecette the lienCertificatAnnulationRecette to set
	 */
	public final void setLienCertificatAnnulationRecette(
			String lienCertificatAnnulationRecette) {
		this.lienCertificatAnnulationRecette = lienCertificatAnnulationRecette;
	}





	/**
	 * @return the lienFichierDesFactures
	 */
	public final String getLienFichierDesFactures() {
		return lienFichierDesFactures;
	}





	/**
	 * @param lienFichierDesFactures the lienFichierDesFactures to set
	 */
	public final void setLienFichierDesFactures(String lienFichierDesFactures) {
		this.lienFichierDesFactures = lienFichierDesFactures;
	}





	/**
	 * @return the lienFichierFromPleaide
	 */
	public final String getLienFichierFromPleaide() {
		return lienFichierFromPleaide;
	}





	/**
	 * @param lienFichierFromPleaide the lienFichierFromPleaide to set
	 */
	public final void setLienFichierFromPleaide(String lienFichierFromPleaide) {
		this.lienFichierFromPleaide = lienFichierFromPleaide;
	}





	/**
	 * @return the lienFichierToPleaide
	 */
	public final String getLienFichierToPleaide() {
		return lienFichierToPleaide;
	}





	/**
	 * @param lienFichierToPleaide the lienFichierToPleaide to set
	 */
	public final void setLienFichierToPleaide(String lienFichierToPleaide) {
		this.lienFichierToPleaide = lienFichierToPleaide;
	}





	/**
	 * @return the lienRoleAnnulation
	 */
	public final String getLienRoleAnnulation() {
		return lienRoleAnnulation;
	}





	/**
	 * @param lienRoleAnnulation the lienRoleAnnulation to set
	 */
	public final void setLienRoleAnnulation(String lienRoleAnnulation) {
		this.lienRoleAnnulation = lienRoleAnnulation;
	}





	/**
	 * @return the lienRoleFacturation
	 */
	public final String getLienRoleFacturation() {
		return lienRoleFacturation;
	}





	/**
	 * @param lienRoleFacturation the lienRoleFacturation to set
	 */
	public final void setLienRoleFacturation(String lienRoleFacturation) {
		this.lienRoleFacturation = lienRoleFacturation;
	}





	/**
	 * @return the lienRoleJustificatif
	 */
	public final String getLienRoleJustificatif() {
		return lienRoleJustificatif;
	}





	/**
	 * @param lienRoleJustificatif the lienRoleJustificatif to set
	 */
	public final void setLienRoleJustificatif(String lienRoleJustificatif) {
		this.lienRoleJustificatif = lienRoleJustificatif;
	}





	/**
	 * @return the listeFactures
	 */
	public final String getListeFactures() {
		return listeFactures;
	}





	/**
	 * @param listeFactures the listeFactures to set
	 */
	public final void setListeFactures(String listeFactures) {
		this.listeFactures = listeFactures;
	}





	/**
	 * @return the nomFichierLogs
	 */
	public final String getNomFichierLogs() {
		return nomFichierLogs;
	}





	/**
	 * @param nomFichierLogs the nomFichierLogs to set
	 */
	public final void setNomFichierLogs(String nomFichierLogs) {
		this.nomFichierLogs = nomFichierLogs;
	}

	/**
	 * @return the lienSuivieDeFacturation
	 */
	public final String getLienSuivieDeFacturation() {
		return lienSuivieDeFacturation;
	}

	/**
	 * @param lienSuivieDeFacturation the lienSuivieDeFacturation to set
	 */
	public final void setLienSuivieDeFacturation(String lienSuivieDeFacturation) {
		this.lienSuivieDeFacturation = lienSuivieDeFacturation;
	}

	/**
	 * @return the lienRoleChgtAdresseRedevable
	 */
	public final String getLienRoleChgtAdresseRedevable() {
		return lienRoleChgtAdresseRedevable;
	}

	/**
	 * @param lienRoleChgtAdresseRedevable the lienRoleChgtAdresseRedevable to set
	 */
	public final void setLienRoleChgtAdresseRedevable(
			String lienRoleChgtAdresseRedevable) {
		this.lienRoleChgtAdresseRedevable = lienRoleChgtAdresseRedevable;
	}

	/**
	 * @return the lienRoleSuivieFacturation
	 */
	public final String getLienRoleSuivieFacturation() {
		return lienRoleSuivieFacturation;
	}

	/**
	 * @param lienRoleSuivieFacturation the lienRoleSuivieFacturation to set
	 */
	public final void setLienRoleSuivieFacturation(String lienRoleSuivieFacturation) {
		this.lienRoleSuivieFacturation = lienRoleSuivieFacturation;
	}

	/**
	 * @return the lienPhotos
	 */
	public final String getLienPhotos() {
		return lienPhotos;
	}

	/**
	 * @param lienPhotos the lienPhotos to set
	 */
	public final void setLienPhotos(String lienPhotos) {
		this.lienPhotos = lienPhotos;
	}

	/**
	 * @return the cheminJRXMLCourrier
	 */
	public final String getCheminJRXMLCourrier() {
		return cheminJRXMLCourrier;
	}

	/**
	 * @param cheminJRXMLCourrier the cheminJRXMLCourrier to set
	 */
	public final void setCheminJRXMLCourrier(String cheminJRXMLCourrier) {
		this.cheminJRXMLCourrier = cheminJRXMLCourrier;
	}

	/**
	 * @return the cheminCouriers
	 */
	public final String getCheminCouriers() {
		return cheminCouriers;
	}

	/**
	 * @param cheminCouriers the cheminCouriers to set
	 */
	public final void setCheminCouriers(String cheminCouriers) {
		this.cheminCouriers = cheminCouriers;
	}

	/**
	 * @return the cheminIdoss
	 */
	public final String getCheminIdoss() {
		return cheminIdoss;
	}

	/**
	 * @param cheminIdoss the cheminIdoss to set
	 */
	public final void setCheminIdoss(String cheminIdoss) {
		this.cheminIdoss = cheminIdoss;
	}

	

	/**
	 * @return the cheminDossierTmp
	 */
	public final String getCheminDossierTmp() {
		return cheminDossierTmp;
	}

	/**
	 * @param cheminDossierTmp the cheminDossierTmp to set
	 */
	public final void setCheminDossierTmp(String cheminDossierTmp) {
		this.cheminDossierTmp = cheminDossierTmp;
	}

	/**
	 * @return the lienDossierTmp
	 */
	public final String getLienDossierTmp() {
		return lienDossierTmp;
	}

	/**
	 * @param lienDossierTmp the lienDossierTmp to set
	 */
	public final void setLienDossierTmp(String lienDossierTmp) {
		this.lienDossierTmp = lienDossierTmp;
	}

	/**
	 * @return the cheminRoleCommunique
	 */
	public final String getCheminRoleCommunique() {
		return cheminRoleCommunique;
	}

	/**
	 * @param cheminRoleCommunique the cheminRoleCommunique to set
	 */
	public final void setCheminRoleCommunique(String cheminRoleCommunique) {
		this.cheminRoleCommunique = cheminRoleCommunique;
	}

	/**
	 * @return the lienRoleCommunique
	 */
	public final String getLienRoleCommunique() {
		return lienRoleCommunique;
	}

	/**
	 * @param lienRoleCommunique the lienRoleCommunique to set
	 */
	public final void setLienRoleCommunique(String lienRoleCommunique) {
		this.lienRoleCommunique = lienRoleCommunique;
	}

	

	/**
	 * @return the cheminDataCourier
	 */
	public final String getCheminDataCourier() {
		return cheminDataCourier;
	}

	/**
	 * @param cheminDataCourier the cheminDataCourier to set
	 */
	public final void setCheminDataCourier(String cheminDataCourier) {
		this.cheminDataCourier = cheminDataCourier;
	}

	public String getCheminRapportJRXMLFactureTLPE() {
		return cheminRapportJRXMLFactureTLPE;
	}

	public void setCheminRapportJRXMLFactureTLPE(
			String cheminRapportJRXMLFactureTLPE) {
		this.cheminRapportJRXMLFactureTLPE = cheminRapportJRXMLFactureTLPE;
	}

	public String getCheminRapportModeleTLPE() {
		return cheminRapportModeleTLPE;
	}

	public void setCheminRapportModeleTLPE(String cheminRapportModeleTLPE) {
		this.cheminRapportModeleTLPE = cheminRapportModeleTLPE;
	}

	public String getCheminListeQuittancesJournalierToulon() {
		return cheminListeQuittancesJournalierToulon;
	}

	public void setCheminListeQuittancesJournalierToulon(
			String cheminListeQuittancesJournalierToulon) {
		this.cheminListeQuittancesJournalierToulon = cheminListeQuittancesJournalierToulon;
	}



	public String getCheminRapportListeQuittancesJournalierToulon() {
		return cheminRapportListeQuittancesJournalierToulon;
	}

	public void setCheminRapportListeQuittancesJournalierToulon(
			String cheminRapportListeQuittancesJournalierToulon) {
		this.cheminRapportListeQuittancesJournalierToulon = cheminRapportListeQuittancesJournalierToulon;
	}

	public String getCheminRapportRecapitulatifComptesJournalierToulon() {
		return cheminRapportRecapitulatifComptesJournalierToulon;
	}

	public void setCheminRapportRecapitulatifComptesJournalierToulon(
			String cheminRapportRecapitulatifComptesJournalierToulon) {
		this.cheminRapportRecapitulatifComptesJournalierToulon = cheminRapportRecapitulatifComptesJournalierToulon;
	}

	public String getCheminRapportVersementChequesJournalierToulon() {
		return cheminRapportVersementChequesJournalierToulon;
	}

	public void setCheminRapportVersementChequesJournalierToulon(
			String cheminRapportVersementChequesJournalierToulon) {
		this.cheminRapportVersementChequesJournalierToulon = cheminRapportVersementChequesJournalierToulon;
	}

	public String getCheminRapportVersementJournalierToulon() {
		return cheminRapportVersementJournalierToulon;
	}

	public void setCheminRapportVersementJournalierToulon(
			String cheminRapportVersementJournalierToulon) {
		this.cheminRapportVersementJournalierToulon = cheminRapportVersementJournalierToulon;
	}

	public String getCheminModeleTLPEToulon() {
		return cheminModeleTLPEToulon;
	}

	public void setCheminModeleTLPEToulon(String cheminModeleTLPEToulon) {
		this.cheminModeleTLPEToulon = cheminModeleTLPEToulon;
	}

	public String getCheminRecapitulatifComptesJournalierToulon() {
		return cheminRecapitulatifComptesJournalierToulon;
	}

	public void setCheminRecapitulatifComptesJournalierToulon(
			String cheminRecapitulatifComptesJournalierToulon) {
		this.cheminRecapitulatifComptesJournalierToulon = cheminRecapitulatifComptesJournalierToulon;
	}

	public String getCheminVersementChequesJournalierToulon() {
		return cheminVersementChequesJournalierToulon;
	}

	public void setCheminVersementChequesJournalierToulon(
			String cheminVersementChequesJournalierToulon) {
		this.cheminVersementChequesJournalierToulon = cheminVersementChequesJournalierToulon;
	}

	public String getCheminVersementJournalierToulon() {
		return cheminVersementJournalierToulon;
	}

	public void setCheminVersementJournalierToulon(
			String cheminVersementJournalierToulon) {
		this.cheminVersementJournalierToulon = cheminVersementJournalierToulon;
	}

	public String getCheminFactureTLPEToulon() {
		return cheminFactureTLPEToulon;
	}

	public void setCheminFactureTLPEToulon(String cheminFactureTLPEToulon) {
		this.cheminFactureTLPEToulon = cheminFactureTLPEToulon;
	}



	public String getCheminRapportJournalierToulon() {
		return cheminRapportJournalierToulon;
	}

	public void setCheminRapportJournalierToulon(
			String cheminRapportJournalierToulon) {
		this.cheminRapportJournalierToulon = cheminRapportJournalierToulon;
	}

	public String getLienRapportListeQuittancesJournalierToulon() {
		return lienRapportListeQuittancesJournalierToulon;
	}

	public void setLienRapportListeQuittancesJournalierToulon(
			String lienRapportListeQuittancesJournalierToulon) {
		this.lienRapportListeQuittancesJournalierToulon = lienRapportListeQuittancesJournalierToulon;
	}

	public String getLienRapportRecapitulatifComptesJournalierToulon() {
		return lienRapportRecapitulatifComptesJournalierToulon;
	}

	public void setLienRapportRecapitulatifComptesJournalierToulon(
			String lienRapportRecapitulatifComptesJournalierToulon) {
		this.lienRapportRecapitulatifComptesJournalierToulon = lienRapportRecapitulatifComptesJournalierToulon;
	}

	public String getLienRapportVersementChequesJournalierToulon() {
		return lienRapportVersementChequesJournalierToulon;
	}

	public void setLienRapportVersementChequesJournalierToulon(
			String lienRapportVersementChequesJournalierToulon) {
		this.lienRapportVersementChequesJournalierToulon = lienRapportVersementChequesJournalierToulon;
	}

	public String getLienRapportVersementJournalierToulon() {
		return lienRapportVersementJournalierToulon;
	}

	public void setLienRapportVersementJournalierToulon(
			String lienRapportVersementJournalierToulon) {
		this.lienRapportVersementJournalierToulon = lienRapportVersementJournalierToulon;
	}

	public String getCheminRapportJRXMLFactureTLPEToulon() {
		return cheminRapportJRXMLFactureTLPEToulon;
	}

	public void setCheminRapportJRXMLFactureTLPEToulon(
			String cheminRapportJRXMLFactureTLPEToulon) {
		this.cheminRapportJRXMLFactureTLPEToulon = cheminRapportJRXMLFactureTLPEToulon;
	}

	public String getCheminRapportModeleTLPEToulon() {
		return cheminRapportModeleTLPEToulon;
	}

	public void setCheminRapportModeleTLPEToulon(
			String cheminRapportModeleTLPEToulon) {
		this.cheminRapportModeleTLPEToulon = cheminRapportModeleTLPEToulon;
	}

	public String getLienFichierFilien() {
		return lienFichierFilien;
	}

	public void setLienFichierFilien(String lienFichierFilien) {
		this.lienFichierFilien = lienFichierFilien;
	}

	public String getCheminFichierFilien() {
		return cheminFichierFilien;
	}

	public void setCheminFichierFilien(String cheminFichierFilien) {
		this.cheminFichierFilien = cheminFichierFilien;
	}

	public String getCheminRapportMensuelToulon() {
		return cheminRapportMensuelToulon;
	}

	public void setCheminRapportMensuelToulon(String cheminRapportMensuelToulon) {
		this.cheminRapportMensuelToulon = cheminRapportMensuelToulon;
	}

	public String getCheminRapportRecapitulatifComptesMensuelToulon() {
		return cheminRapportRecapitulatifComptesMensuelToulon;
	}

	public void setCheminRapportRecapitulatifComptesMensuelToulon(
			String cheminRapportRecapitulatifComptesMensuelToulon) {
		this.cheminRapportRecapitulatifComptesMensuelToulon = cheminRapportRecapitulatifComptesMensuelToulon;
	}

	public String getCheminRapportVersementMensuelToulon() {
		return cheminRapportVersementMensuelToulon;
	}

	public void setCheminRapportVersementMensuelToulon(
			String cheminRapportVersementMensuelToulon) {
		this.cheminRapportVersementMensuelToulon = cheminRapportVersementMensuelToulon;
	}

	public String getLienRapportRecapitulatifComptesMensuelToulon() {
		return lienRapportRecapitulatifComptesMensuelToulon;
	}

	public void setLienRapportRecapitulatifComptesMensuelToulon(
			String lienRapportRecapitulatifComptesMensuelToulon) {
		this.lienRapportRecapitulatifComptesMensuelToulon = lienRapportRecapitulatifComptesMensuelToulon;
	}

	public String getLienRapportVersementMensuelToulon() {
		return lienRapportVersementMensuelToulon;
	}

	public void setLienRapportVersementMensuelToulon(
			String lienRapportVersementMensuelToulon) {
		this.lienRapportVersementMensuelToulon = lienRapportVersementMensuelToulon;
	}

	public String getCheminRecapitulatifComptesMensuelToulon() {
		return cheminRecapitulatifComptesMensuelToulon;
	}

	public void setCheminRecapitulatifComptesMensuelToulon(
			String cheminRecapitulatifComptesMensuelToulon) {
		this.cheminRecapitulatifComptesMensuelToulon = cheminRecapitulatifComptesMensuelToulon;
	}

	public String getCheminVersementMensuelToulon() {
		return cheminVersementMensuelToulon;
	}

	public void setCheminVersementMensuelToulon(String cheminVersementMensuelToulon) {
		this.cheminVersementMensuelToulon = cheminVersementMensuelToulon;
	}
	
	

	

	public String getCheminBalanceTrimestrielToulon() {
		return cheminBalanceTrimestrielToulon;
	}

	public void setCheminBalanceTrimestrielToulon(
			String cheminBalanceTrimestrielToulon) {
		this.cheminBalanceTrimestrielToulon = cheminBalanceTrimestrielToulon;
	}



	public String getCheminR2tData() {
		return cheminR2tData;
	}

	public void setCheminR2tData(String cheminR2tData) {
		this.cheminR2tData = cheminR2tData;
	}


	public String getCheminRapportBalanceTrimestrielToulon() {
		return cheminRapportBalanceTrimestrielToulon;
	}

	public void setCheminRapportBalanceTrimestrielToulon(
			String cheminRapportBalanceTrimestrielToulon) {
		this.cheminRapportBalanceTrimestrielToulon = cheminRapportBalanceTrimestrielToulon;
	}
	public String getCheminRapportTrimestrielToulon() {
		return cheminRapportTrimestrielToulon;
	}

	public void setCheminRapportTrimestrielToulon(
			String cheminRapportTrimestrielToulon) {
		this.cheminRapportTrimestrielToulon = cheminRapportTrimestrielToulon;
	}

	public String getCheminRapportVentilationParBaremeTrimestrielToulon() {
		return cheminRapportVentilationParBaremeTrimestrielToulon;
	}

	public void setCheminRapportVentilationParBaremeTrimestrielToulon(
			String cheminRapportVentilationParBaremeTrimestrielToulon) {
		this.cheminRapportVentilationParBaremeTrimestrielToulon = cheminRapportVentilationParBaremeTrimestrielToulon;
	}

	public String getCheminRapportVentilationParEmplacementTrimestrielToulon() {
		return cheminRapportVentilationParEmplacementTrimestrielToulon;
	}

	public void setCheminRapportVentilationParEmplacementTrimestrielToulon(
			String cheminRapportVentilationParEmplacementTrimestrielToulon) {
		this.cheminRapportVentilationParEmplacementTrimestrielToulon = cheminRapportVentilationParEmplacementTrimestrielToulon;
	}

	public String getCheminVentilationParBaremeTrimestrielToulon() {
		return cheminVentilationParBaremeTrimestrielToulon;
	}

	public void setCheminVentilationParBaremeTrimestrielToulon(
			String cheminVentilationParBaremeTrimestrielToulon) {
		this.cheminVentilationParBaremeTrimestrielToulon = cheminVentilationParBaremeTrimestrielToulon;
	}

	public String getCheminVentilationParEmplacementTrimestrielToulon() {
		return cheminVentilationParEmplacementTrimestrielToulon;
	}

	public void setCheminVentilationParEmplacementTrimestrielToulon(
			String cheminVentilationParEmplacementTrimestrielToulon) {
		this.cheminVentilationParEmplacementTrimestrielToulon = cheminVentilationParEmplacementTrimestrielToulon;
	}

	public File getFichierProperties() {
		return fichierProperties;
	}

	public void setFichierProperties(File fichierProperties) {
		this.fichierProperties = fichierProperties;
	}

	

	public String getCheminRapportJRXMLFacturePubToulon() {
		return cheminRapportJRXMLFacturePubToulon;
	}

	public void setCheminRapportJRXMLFacturePubToulon(
			String cheminRapportJRXMLFacturePubToulon) {
		this.cheminRapportJRXMLFacturePubToulon = cheminRapportJRXMLFacturePubToulon;
	}

	public String getCheminRapportModelePubToulon() {
		return cheminRapportModelePubToulon;
	}

	public void setCheminRapportModelePubToulon(String cheminRapportModelePubToulon) {
		this.cheminRapportModelePubToulon = cheminRapportModelePubToulon;
	}


	
	 public String getLienRapportVersementChequesJournalierCompteRegieToulon() {
			return lienRapportVersementChequesJournalierCompteRegieToulon;
		}

		public void setLienRapportVersementChequesJournalierCompteRegieToulon(
				String lienRapportVersementChequesJournalierCompteRegieToulon) {
			this.lienRapportVersementChequesJournalierCompteRegieToulon = lienRapportVersementChequesJournalierCompteRegieToulon;
		}
		
		public String getLienRapportVersementCarteBancaireJournalierToulon(){
			return lienRapportVersementCarteBancaireJournalierToulon;
		}
		
		 public String getCheminVersementCarteBancaireJournalierToulon() {
			return cheminVersementCarteBancaireJournalierToulon;
		}
		 
		public String getCheminRapportVersementCarteBancaireJournalierToulon() {
			return cheminRapportVersementCarteBancaireJournalierToulon;
		}

		public void setCheminRapportVersementCarteBancaireJournalierToulon(
				String cheminRapportVersementCarteBancaireJournalierToulon) {
			this.cheminRapportVersementCarteBancaireJournalierToulon = cheminRapportVersementCarteBancaireJournalierToulon;
		}

		public void setCheminVersementCarteBancaireJournalierToulon(
				String cheminVersementCarteBancaireJournalierToulon) {
			this.cheminVersementCarteBancaireJournalierToulon = cheminVersementCarteBancaireJournalierToulon;
		}

		public String getCheminRapportVersementChequesJournalierCompteRegie() {
				return cheminRapportVersementChequesJournalierCompteRegie;
			}

			public void setCheminRapportVersementChequesJournalierCompteRegie(
					String cheminRapportVersementChequesJournalierCompteRegie) {
				this.cheminRapportVersementChequesJournalierCompteRegie = cheminRapportVersementChequesJournalierCompteRegie;
			}

			public String getCheminListeDesAnnulationsTrimestrielToulon() {
				return cheminListeDesAnnulationsTrimestrielToulon;
			}

			public void setCheminListeDesAnnulationsTrimestrielToulon(
					String cheminListeDesAnnulationsTrimestrielToulon) {
				this.cheminListeDesAnnulationsTrimestrielToulon = cheminListeDesAnnulationsTrimestrielToulon;
			}

			public String getCheminListeDesAcomptesTrimestrielToulon() {
				return cheminListeDesAcomptesTrimestrielToulon;
			}

			public void setCheminListeDesAcomptesTrimestrielToulon(
					String cheminListeDesAcomptesTrimestrielToulon) {
				this.cheminListeDesAcomptesTrimestrielToulon = cheminListeDesAcomptesTrimestrielToulon;
			}

			public String getCheminRapportListeDesAnnulationsTrimestrielToulon() {
				return cheminRapportListeDesAnnulationsTrimestrielToulon;
			}

			public void setCheminRapportListeDesAnnulationsTrimestrielToulon(
					String cheminRapportListeDesAnnulationsTrimestrielToulon) {
				this.cheminRapportListeDesAnnulationsTrimestrielToulon = cheminRapportListeDesAnnulationsTrimestrielToulon;
			}

			public void setCheminAbsoluTemplateRapport(String cheminAbsoluTemplateRapport) {
				this.cheminAbsoluTemplateRapport = cheminAbsoluTemplateRapport;
			}

			public String getLienListeDesImpayesTrimestrielToulon() {
				return lienListeDesImpayesTrimestrielToulon;
			}

			public void setLienListeDesImpayesTrimestrielToulon(
					String lienListeDesImpayesTrimestrielToulon) {
				this.lienListeDesImpayesTrimestrielToulon = lienListeDesImpayesTrimestrielToulon;
			}

			public String getCheminListeDesImpayesTrimestrielToulon() {
				return cheminListeDesImpayesTrimestrielToulon;
			}

			public void setCheminListeDesImpayesTrimestrielToulon(
					String cheminListeDesImpayesTrimestrielToulon) {
				this.cheminListeDesImpayesTrimestrielToulon = cheminListeDesImpayesTrimestrielToulon;
			}

			public String getCheminRapportListeDesImpayesTrimestrielToulon() {
				return cheminRapportListeDesImpayesTrimestrielToulon;
			}

			public void setCheminRapportListeDesImpayesTrimestrielToulon(
					String cheminRapportListeDesImpayesTrimestrielToulon) {
				this.cheminRapportListeDesImpayesTrimestrielToulon = cheminRapportListeDesImpayesTrimestrielToulon;
			}

			/**
			 * @return the lienRemboursement
			 */
			public String getLienRemboursement() {
				return lienRemboursement;
			}

			/**
			 * @param lienRemboursement the lienRemboursement to set
			 */
			public void setLienRemboursement(String lienRemboursement) {
				this.lienRemboursement = lienRemboursement;
			}

			/**
			 * @return the lienRoleRemboursement
			 */
			public String getLienRoleRemboursement() {
				return lienRoleRemboursement;
			}

			/**
			 * @param lienRoleRemboursement the lienRoleRemboursement to set
			 */
			public void setLienRoleRemboursement(String lienRoleRemboursement) {
				this.lienRoleRemboursement = lienRoleRemboursement;
			}

			/**
			 * @return the cheminRapportJRXMLRemboursement
			 */
			public String getCheminRapportJRXMLRemboursement() {
				return cheminRapportJRXMLRemboursement;
			}

			/**
			 * @param cheminRapportJRXMLRemboursement the cheminRapportJRXMLRemboursement to set
			 */
			public void setCheminRapportJRXMLRemboursement(
					String cheminRapportJRXMLRemboursement) {
				this.cheminRapportJRXMLRemboursement = cheminRapportJRXMLRemboursement;
			}

			/**
			 * @return the cheminRapportModeleRemboursement
			 */
			public String getCheminRapportModeleRemboursement() {
				return cheminRapportModeleRemboursement;
			}

			/**
			 * @param cheminRapportModeleRemboursement the cheminRapportModeleRemboursement to set
			 */
			public void setCheminRapportModeleRemboursement(
					String cheminRapportModeleRemboursement) {
				this.cheminRapportModeleRemboursement = cheminRapportModeleRemboursement;
			}

			/**
			 * @return the cheminRemboursement
			 */
			public String getCheminRemboursement() {
				return cheminRemboursement;
			}

			/**
			 * @param cheminRemboursement the cheminRemboursement to set
			 */
			public void setCheminRemboursement(String cheminRemboursement) {
				this.cheminRemboursement = cheminRemboursement;
			}

			/**
			 * @return the cheminRoleRemboursement
			 */
			public String getCheminRoleRemboursement() {
				return cheminRoleRemboursement;
			}

			/**
			 * @param cheminRoleRemboursement the cheminRoleRemboursement to set
			 */
			public void setCheminRoleRemboursement(String cheminRoleRemboursement) {
				this.cheminRoleRemboursement = cheminRoleRemboursement;
			}

			/**
			 * @return the lienRapportListeDesAcomptesAnnuelToulon
			 */
			public String getLienRapportListeDesAcomptesAnnuelToulon() {
				return lienRapportListeDesAcomptesAnnuelToulon;
			}

			/**
			 * @param lienRapportListeDesAcomptesAnnuelToulon the lienRapportListeDesAcomptesAnnuelToulon to set
			 */
			public void setLienRapportListeDesAcomptesAnnuelToulon(
					String lienRapportListeDesAcomptesAnnuelToulon) {
				this.lienRapportListeDesAcomptesAnnuelToulon = lienRapportListeDesAcomptesAnnuelToulon;
			}

			/**
			 * @return the lienRapportListeDesAnnulationsAnnuelToulon
			 */
			public String getLienRapportListeDesAnnulationsAnnuelToulon() {
				return lienRapportListeDesAnnulationsAnnuelToulon;
			}

			/**
			 * @param lienRapportListeDesAnnulationsAnnuelToulon the lienRapportListeDesAnnulationsAnnuelToulon to set
			 */
			public void setLienRapportListeDesAnnulationsAnnuelToulon(
					String lienRapportListeDesAnnulationsAnnuelToulon) {
				this.lienRapportListeDesAnnulationsAnnuelToulon = lienRapportListeDesAnnulationsAnnuelToulon;
			}

			/**
			 * @return the lienRapportListeDesImpayesAnnuelEtalageToulon
			 */
			public String getLienRapportListeDesImpayesAnnuelEtalageToulon() {
				return lienRapportListeDesImpayesAnnuelEtalageToulon;
			}

			/**
			 * @param lienRapportListeDesImpayesAnnuelEtalageToulon the lienRapportListeDesImpayesAnnuelEtalageToulon to set
			 */
			public void setLienRapportListeDesImpayesAnnuelEtalageToulon(
					String lienRapportListeDesImpayesAnnuelEtalageToulon) {
				this.lienRapportListeDesImpayesAnnuelEtalageToulon = lienRapportListeDesImpayesAnnuelEtalageToulon;
			}

			/**
			 * @return the lienRapportListeDesImpayesAnnuelTLPEToulon
			 */
			public String getLienRapportListeDesImpayesAnnuelTLPEToulon() {
				return lienRapportListeDesImpayesAnnuelTLPEToulon;
			}

			/**
			 * @param lienRapportListeDesImpayesAnnuelTLPEToulon the lienRapportListeDesImpayesAnnuelTLPEToulon to set
			 */
			public void setLienRapportListeDesImpayesAnnuelTLPEToulon(
					String lienRapportListeDesImpayesAnnuelTLPEToulon) {
				this.lienRapportListeDesImpayesAnnuelTLPEToulon = lienRapportListeDesImpayesAnnuelTLPEToulon;
			}

			/**
			 * @return the lienRapportVentilationParBaremeAnnuelEtalageToulon
			 */
			public String getLienRapportVentilationParBaremeAnnuelEtalageToulon() {
				return lienRapportVentilationParBaremeAnnuelEtalageToulon;
			}

			/**
			 * @param lienRapportVentilationParBaremeAnnuelEtalageToulon the lienRapportVentilationParBaremeAnnuelEtalageToulon to set
			 */
			public void setLienRapportVentilationParBaremeAnnuelEtalageToulon(
					String lienRapportVentilationParBaremeAnnuelEtalageToulon) {
				this.lienRapportVentilationParBaremeAnnuelEtalageToulon = lienRapportVentilationParBaremeAnnuelEtalageToulon;
			}

			/**
			 * @return the lienRapportVentilationParBaremeAnnuelTLPEToulon
			 */
			public String getLienRapportVentilationParBaremeAnnuelTLPEToulon() {
				return lienRapportVentilationParBaremeAnnuelTLPEToulon;
			}

			/**
			 * @param lienRapportVentilationParBaremeAnnuelTLPEToulon the lienRapportVentilationParBaremeAnnuelTLPEToulon to set
			 */
			public void setLienRapportVentilationParBaremeAnnuelTLPEToulon(
					String lienRapportVentilationParBaremeAnnuelTLPEToulon) {
				this.lienRapportVentilationParBaremeAnnuelTLPEToulon = lienRapportVentilationParBaremeAnnuelTLPEToulon;
			}

			/**
			 * @return the lienRapportVentilationParEmplacementAnnuelEtalageToulon
			 */
			public String getLienRapportVentilationParEmplacementAnnuelEtalageToulon() {
				return lienRapportVentilationParEmplacementAnnuelEtalageToulon;
			}

			/**
			 * @param lienRapportVentilationParEmplacementAnnuelEtalageToulon the lienRapportVentilationParEmplacementAnnuelEtalageToulon to set
			 */
			public void setLienRapportVentilationParEmplacementAnnuelEtalageToulon(
					String lienRapportVentilationParEmplacementAnnuelEtalageToulon) {
				this.lienRapportVentilationParEmplacementAnnuelEtalageToulon = lienRapportVentilationParEmplacementAnnuelEtalageToulon;
			}

			/**
			 * @return the lienRapportVentilationParEmplacementAnnuelTLPEToulon
			 */
			public String getLienRapportVentilationParEmplacementAnnuelTLPEToulon() {
				return lienRapportVentilationParEmplacementAnnuelTLPEToulon;
			}

			/**
			 * @param lienRapportVentilationParEmplacementAnnuelTLPEToulon the lienRapportVentilationParEmplacementAnnuelTLPEToulon to set
			 */
			public void setLienRapportVentilationParEmplacementAnnuelTLPEToulon(
					String lienRapportVentilationParEmplacementAnnuelTLPEToulon) {
				this.lienRapportVentilationParEmplacementAnnuelTLPEToulon = lienRapportVentilationParEmplacementAnnuelTLPEToulon;
			}

			/**
			 * @return the cheminListeDesAcomptesAnnuelToulon
			 */
			public String getCheminListeDesAcomptesAnnuelToulon() {
				return cheminListeDesAcomptesAnnuelToulon;
			}

			/**
			 * @param cheminListeDesAcomptesAnnuelToulon the cheminListeDesAcomptesAnnuelToulon to set
			 */
			public void setCheminListeDesAcomptesAnnuelToulon(
					String cheminListeDesAcomptesAnnuelToulon) {
				this.cheminListeDesAcomptesAnnuelToulon = cheminListeDesAcomptesAnnuelToulon;
			}

			/**
			 * @return the cheminListeDesAnnulationsAnnuelToulon
			 */
			public String getCheminListeDesAnnulationsAnnuelToulon() {
				return cheminListeDesAnnulationsAnnuelToulon;
			}

			/**
			 * @param cheminListeDesAnnulationsAnnuelToulon the cheminListeDesAnnulationsAnnuelToulon to set
			 */
			public void setCheminListeDesAnnulationsAnnuelToulon(
					String cheminListeDesAnnulationsAnnuelToulon) {
				this.cheminListeDesAnnulationsAnnuelToulon = cheminListeDesAnnulationsAnnuelToulon;
			}

			/**
			 * @return the cheminListeDesImpayesAnnuelEtalageToulon
			 */
			public String getCheminListeDesImpayesAnnuelEtalageToulon() {
				return cheminListeDesImpayesAnnuelEtalageToulon;
			}

			/**
			 * @param cheminListeDesImpayesAnnuelEtalageToulon the cheminListeDesImpayesAnnuelEtalageToulon to set
			 */
			public void setCheminListeDesImpayesAnnuelEtalageToulon(
					String cheminListeDesImpayesAnnuelEtalageToulon) {
				this.cheminListeDesImpayesAnnuelEtalageToulon = cheminListeDesImpayesAnnuelEtalageToulon;
			}

			/**
			 * @return the cheminListeDesImpayesAnnuelTLPEToulon
			 */
			public String getCheminListeDesImpayesAnnuelTLPEToulon() {
				return cheminListeDesImpayesAnnuelTLPEToulon;
			}

			/**
			 * @param cheminListeDesImpayesAnnuelTLPEToulon the cheminListeDesImpayesAnnuelTLPEToulon to set
			 */
			public void setCheminListeDesImpayesAnnuelTLPEToulon(
					String cheminListeDesImpayesAnnuelTLPEToulon) {
				this.cheminListeDesImpayesAnnuelTLPEToulon = cheminListeDesImpayesAnnuelTLPEToulon;
			}

			/**
			 * @return the cheminVentilationParBaremeAnnuelEtalageToulon
			 */
			public String getCheminVentilationParBaremeAnnuelEtalageToulon() {
				return cheminVentilationParBaremeAnnuelEtalageToulon;
			}

			/**
			 * @param cheminVentilationParBaremeAnnuelEtalageToulon the cheminVentilationParBaremeAnnuelEtalageToulon to set
			 */
			public void setCheminVentilationParBaremeAnnuelEtalageToulon(
					String cheminVentilationParBaremeAnnuelEtalageToulon) {
				this.cheminVentilationParBaremeAnnuelEtalageToulon = cheminVentilationParBaremeAnnuelEtalageToulon;
			}

			/**
			 * @return the cheminVentilationParBaremeAnnuelTLPEToulon
			 */
			public String getCheminVentilationParBaremeAnnuelTLPEToulon() {
				return cheminVentilationParBaremeAnnuelTLPEToulon;
			}

			/**
			 * @param cheminVentilationParBaremeAnnuelTLPEToulon the cheminVentilationParBaremeAnnuelTLPEToulon to set
			 */
			public void setCheminVentilationParBaremeAnnuelTLPEToulon(
					String cheminVentilationParBaremeAnnuelTLPEToulon) {
				this.cheminVentilationParBaremeAnnuelTLPEToulon = cheminVentilationParBaremeAnnuelTLPEToulon;
			}

			/**
			 * @return the cheminVentilationParEmplacementAnnuelEtalageToulon
			 */
			public String getCheminVentilationParEmplacementAnnuelEtalageToulon() {
				return cheminVentilationParEmplacementAnnuelEtalageToulon;
			}

			/**
			 * @param cheminVentilationParEmplacementAnnuelEtalageToulon the cheminVentilationParEmplacementAnnuelEtalageToulon to set
			 */
			public void setCheminVentilationParEmplacementAnnuelEtalageToulon(
					String cheminVentilationParEmplacementAnnuelEtalageToulon) {
				this.cheminVentilationParEmplacementAnnuelEtalageToulon = cheminVentilationParEmplacementAnnuelEtalageToulon;
			}

			/**
			 * @return the cheminVentilationParEmplacementAnnuelTLPEToulon
			 */
			public String getCheminVentilationParEmplacementAnnuelTLPEToulon() {
				return cheminVentilationParEmplacementAnnuelTLPEToulon;
			}

			/**
			 * @param cheminVentilationParEmplacementAnnuelTLPEToulon the cheminVentilationParEmplacementAnnuelTLPEToulon to set
			 */
			public void setCheminVentilationParEmplacementAnnuelTLPEToulon(
					String cheminVentilationParEmplacementAnnuelTLPEToulon) {
				this.cheminVentilationParEmplacementAnnuelTLPEToulon = cheminVentilationParEmplacementAnnuelTLPEToulon;
			}

			/**
			 * @return the cheminRapportListeDesAcomptesAnnuelToulon
			 */
			public String getCheminRapportListeDesAcomptesAnnuelToulon() {
				return cheminRapportListeDesAcomptesAnnuelToulon;
			}

			/**
			 * @param cheminRapportListeDesAcomptesAnnuelToulon the cheminRapportListeDesAcomptesAnnuelToulon to set
			 */
			public void setCheminRapportListeDesAcomptesAnnuelToulon(
					String cheminRapportListeDesAcomptesAnnuelToulon) {
				this.cheminRapportListeDesAcomptesAnnuelToulon = cheminRapportListeDesAcomptesAnnuelToulon;
			}

			/**
			 * @return the cheminRapportListeDesAnnulationsAnnuelToulon
			 */
			public String getCheminRapportListeDesAnnulationsAnnuelToulon() {
				return cheminRapportListeDesAnnulationsAnnuelToulon;
			}

			/**
			 * @param cheminRapportListeDesAnnulationsAnnuelToulon the cheminRapportListeDesAnnulationsAnnuelToulon to set
			 */
			public void setCheminRapportListeDesAnnulationsAnnuelToulon(
					String cheminRapportListeDesAnnulationsAnnuelToulon) {
				this.cheminRapportListeDesAnnulationsAnnuelToulon = cheminRapportListeDesAnnulationsAnnuelToulon;
			}

			/**
			 * @return the cheminRapportListeDesImpayesAnnuelEtalageToulon
			 */
			public String getCheminRapportListeDesImpayesAnnuelEtalageToulon() {
				return cheminRapportListeDesImpayesAnnuelEtalageToulon;
			}

			/**
			 * @param cheminRapportListeDesImpayesAnnuelEtalageToulon the cheminRapportListeDesImpayesAnnuelEtalageToulon to set
			 */
			public void setCheminRapportListeDesImpayesAnnuelEtalageToulon(
					String cheminRapportListeDesImpayesAnnuelEtalageToulon) {
				this.cheminRapportListeDesImpayesAnnuelEtalageToulon = cheminRapportListeDesImpayesAnnuelEtalageToulon;
			}

			/**
			 * @return the cheminRapportListeDesImpayesAnnuelTLPEToulon
			 */
			public String getCheminRapportListeDesImpayesAnnuelTLPEToulon() {
				return cheminRapportListeDesImpayesAnnuelTLPEToulon;
			}

			/**
			 * @param cheminRapportListeDesImpayesAnnuelTLPEToulon the cheminRapportListeDesImpayesAnnuelTLPEToulon to set
			 */
			public void setCheminRapportListeDesImpayesAnnuelTLPEToulon(
					String cheminRapportListeDesImpayesAnnuelTLPEToulon) {
				this.cheminRapportListeDesImpayesAnnuelTLPEToulon = cheminRapportListeDesImpayesAnnuelTLPEToulon;
			}

			/**
			 * @return the cheminRapportVentilationParBaremeAnnuelEtalageToulon
			 */
			public String getCheminRapportVentilationParBaremeAnnuelEtalageToulon() {
				return cheminRapportVentilationParBaremeAnnuelEtalageToulon;
			}

			/**
			 * @param cheminRapportVentilationParBaremeAnnuelEtalageToulon the cheminRapportVentilationParBaremeAnnuelEtalageToulon to set
			 */
			public void setCheminRapportVentilationParBaremeAnnuelEtalageToulon(
					String cheminRapportVentilationParBaremeAnnuelEtalageToulon) {
				this.cheminRapportVentilationParBaremeAnnuelEtalageToulon = cheminRapportVentilationParBaremeAnnuelEtalageToulon;
			}

			/**
			 * @return the cheminRapportVentilationParBaremeAnnuelTLPEToulon
			 */
			public String getCheminRapportVentilationParBaremeAnnuelTLPEToulon() {
				return cheminRapportVentilationParBaremeAnnuelTLPEToulon;
			}

			/**
			 * @param cheminRapportVentilationParBaremeAnnuelTLPEToulon the cheminRapportVentilationParBaremeAnnuelTLPEToulon to set
			 */
			public void setCheminRapportVentilationParBaremeAnnuelTLPEToulon(
					String cheminRapportVentilationParBaremeAnnuelTLPEToulon) {
				this.cheminRapportVentilationParBaremeAnnuelTLPEToulon = cheminRapportVentilationParBaremeAnnuelTLPEToulon;
			}

			/**
			 * @return the cheminRapportVentilationParEmplacementAnnuelEtalageToulon
			 */
			public String getCheminRapportVentilationParEmplacementAnnuelEtalageToulon() {
				return cheminRapportVentilationParEmplacementAnnuelEtalageToulon;
			}

			/**
			 * @param cheminRapportVentilationParEmplacementAnnuelEtalageToulon the cheminRapportVentilationParEmplacementAnnuelEtalageToulon to set
			 */
			public void setCheminRapportVentilationParEmplacementAnnuelEtalageToulon(
					String cheminRapportVentilationParEmplacementAnnuelEtalageToulon) {
				this.cheminRapportVentilationParEmplacementAnnuelEtalageToulon = cheminRapportVentilationParEmplacementAnnuelEtalageToulon;
			}

			/**
			 * @return the cheminRapportVentilationParEmplacementAnnuelTLPEToulon
			 */
			public String getCheminRapportVentilationParEmplacementAnnuelTLPEToulon() {
				return cheminRapportVentilationParEmplacementAnnuelTLPEToulon;
			}

			/**
			 * @param cheminRapportVentilationParEmplacementAnnuelTLPEToulon the cheminRapportVentilationParEmplacementAnnuelTLPEToulon to set
			 */
			public void setCheminRapportVentilationParEmplacementAnnuelTLPEToulon(
					String cheminRapportVentilationParEmplacementAnnuelTLPEToulon) {
				this.cheminRapportVentilationParEmplacementAnnuelTLPEToulon = cheminRapportVentilationParEmplacementAnnuelTLPEToulon;
			}

			public static String getVille() {
				return ville;
			}

			public static void setVille(String ville) {
				FichierDeConfiguration.ville = ville;
			}

			public String getLienRapportEncaissementJour() {
				return lienRapportEncaissementJour;
			}

			public void setLienRapportEncaissementJour(String lienRapportEncaissementJour) {
				this.lienRapportEncaissementJour = lienRapportEncaissementJour;
			}

			public String getLienRapportSuiviPresenceRedevable() {
				return lienRapportSuiviPresenceRedevable;
			}

			public void setLienRapportSuiviPresenceRedevable(
					String lienRapportSuiviPresenceRedevable) {
				this.lienRapportSuiviPresenceRedevable = lienRapportSuiviPresenceRedevable;
			}

			public String getLienRapportRecapMensuelParFamilleMarche() {
				return lienRapportRecapMensuelParFamilleMarche;
			}

			public void setLienRapportRecapMensuelParFamilleMarche(
					String lienRapportRecapMensuelParFamilleMarche) {
				this.lienRapportRecapMensuelParFamilleMarche = lienRapportRecapMensuelParFamilleMarche;
			}

			public String getLienRapportRecapMoisParJour() {
				return lienRapportRecapMoisParJour;
			}

			public void setLienRapportRecapMoisParJour(String lienRapportRecapMoisParJour) {
				this.lienRapportRecapMoisParJour = lienRapportRecapMoisParJour;
			}

			public String getLienRapportRecuAbonne() {
				return lienRapportRecuAbonne;
			}

			public void setLienRapportRecuAbonne(String lienRapportRecuAbonne) {
				this.lienRapportRecuAbonne = lienRapportRecuAbonne;
			}

			public String getCheminRapportEncaissementJour() {
				return cheminRapportEncaissementJour;
			}

			public void setCheminRapportEncaissementJour(
					String cheminRapportEncaissementJour) {
				this.cheminRapportEncaissementJour = cheminRapportEncaissementJour;
			}

			public String getCheminRapportSuiviPresenceRedevable() {
				return cheminRapportSuiviPresenceRedevable;
			}

			public void setCheminRapportSuiviPresenceRedevable(
					String cheminRapportSuiviPresenceRedevable) {
				this.cheminRapportSuiviPresenceRedevable = cheminRapportSuiviPresenceRedevable;
			}

			public String getCheminRapportRecapMensuelParFamilleMarche() {
				return cheminRapportRecapMensuelParFamilleMarche;
			}

			public void setCheminRapportRecapMensuelParFamilleMarche(
					String cheminRapportRecapMensuelParFamilleMarche) {
				this.cheminRapportRecapMensuelParFamilleMarche = cheminRapportRecapMensuelParFamilleMarche;
			}

			public String getCheminRapportRecapMoisParJour() {
				return cheminRapportRecapMoisParJour;
			}

			public void setCheminRapportRecapMoisParJour(
					String cheminRapportRecapMoisParJour) {
				this.cheminRapportRecapMoisParJour = cheminRapportRecapMoisParJour;
			}

			public String getCheminRapportRecuAbonne() {
				return cheminRapportRecuAbonne;
			}

			public void setCheminRapportRecuAbonne(String cheminRapportRecuAbonne) {
				this.cheminRapportRecuAbonne = cheminRapportRecuAbonne;
			}

			public String getLienData() {
				return lienData;
			}

			public void setLienData(String lienData) {
				this.lienData = lienData;
			}

			public String getCheminEncaissementJour() {
				return cheminEncaissementJour;
			}

			public void setCheminEncaissementJour(String cheminEncaissementJour) {
				this.cheminEncaissementJour = cheminEncaissementJour;
			}

			public String getCheminSuiviPresenceRedevable() {
				return cheminSuiviPresenceRedevable;
			}

			public void setCheminSuiviPresenceRedevable(String cheminSuiviPresenceRedevable) {
				this.cheminSuiviPresenceRedevable = cheminSuiviPresenceRedevable;
			}

			public String getCheminRecapMensuelParFamilleMarche() {
				return cheminRecapMensuelParFamilleMarche;
			}

			public void setCheminRecapMensuelParFamilleMarche(
					String cheminRecapMensuelParFamilleMarche) {
				this.cheminRecapMensuelParFamilleMarche = cheminRecapMensuelParFamilleMarche;
			}

			public String getCheminRecapMoisParJour() {
				return cheminRecapMoisParJour;
			}

			public void setCheminRecapMoisParJour(String cheminRecapMoisParJour) {
				this.cheminRecapMoisParJour = cheminRecapMoisParJour;
			}

			public String getCheminRecuAbonne() {
				return cheminRecuAbonne;
			}

			public void setCheminRecuAbonne(String cheminRecuAbonne) {
				this.cheminRecuAbonne = cheminRecuAbonne;
			}

			public String getCheminRapportMarcheToulon() {
				return cheminRapportMarcheToulon;
			}

			public void setCheminRapportMarcheToulon(String cheminRapportMarcheToulon) {
				this.cheminRapportMarcheToulon = cheminRapportMarcheToulon;
			}

			public String getAnneeFacturationTLPE() {
				return anneeFacturationTLPE;
			}

			public void setAnneeFacturationTLPE(String anneeFacturationTLPE) {
				this.anneeFacturationTLPE = anneeFacturationTLPE;
			}

			public String getAdresseServeur() {
				return adresseServeur;
			}

			public void setAdresseServeur(String adresseServeur) {
				this.adresseServeur = adresseServeur;
			}

			public String getUserftp() {
				return userftp;
			}

			public void setUserftp(String userftp) {
				this.userftp = userftp;
			}

			public String getPortftp() {
				return portftp;
			}

			public void setPortftp(String portftp) {
				this.portftp = portftp;
			}

			public String getServeurftp() {
				return serveurftp;
			}

			public void setServeurftp(String serveurftp) {
				this.serveurftp = serveurftp;
			}

			public String getPasswordftp() {
				return passwordftp;
			}

			public void setPasswordftp(String passwordftp) {
				this.passwordftp = passwordftp;
			}

			public String getCheminfactureftp() {
				return cheminfactureftp;
			}

			public void setCheminfactureftp(String cheminfactureftp) {
				this.cheminfactureftp = cheminfactureftp;
			}

			public String getCheminJRXMLExport() {
				return cheminJRXMLExport;
			}

			public void setCheminJRXMLExport(String cheminJRXMLExport) {
				this.cheminJRXMLExport = cheminJRXMLExport;
			}

			public String getDossierFacture() {
				return dossierFacture;
			}

			public void setDossierFacture(String dossierFacture) {
				this.dossierFacture = dossierFacture;
			}
			
}

	
