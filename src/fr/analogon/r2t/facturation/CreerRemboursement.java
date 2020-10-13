
package fr.analogon.r2t.facturation;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import fr.analogon.r2t.Utilitaire.ConcatenationFile;
import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.Utilitaire.ReglerCheminSelonOs;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.pojo.Batch;
import fr.analogon.r2t.pojo.Remboursement;
import fr.analogon.r2t.request.RequestBatch;
import fr.analogon.r2t.request.RequestRemboursement;


public class CreerRemboursement
{	
		private String cheminModele;	
		private Connection connexionr2t= InitialisationConnexionLectureConfiguration.connexion.connexion;
		private String RepertoireCourant;
		private String NomFichierRemboursement;		
		private ResultSet RSDonneesFactures;
		private String pathRapportJRXML;
		private String pathRapportModele;
		private File RepertoireRemboursements;
		private Integer NumeroDuBatch;
		
		FichierDeConfiguration fichierDeConfiguration 
		   =  InitialisationConnexionLectureConfiguration.getFichierDeConfiguration();
	
		public CreerRemboursement(int NumeroBatch) throws Exception 
		{										 		
			setNumeroDuBatch(new Integer(NumeroBatch));
	    	try
	    	{
	    		setConnexionr2t(connexionr2t);
				RequestBatch requestBatch = new RequestBatch();
				Batch batch = requestBatch.getBatch(NumeroBatch);	
				setPathRapportJRXML (fichierDeConfiguration.getCheminRapportJRXMLRemboursement());
				setPathRapportModele(fichierDeConfiguration.getCheminRapportModeleRemboursement());				
				DebuggerLog4J.logger.debug(getPathRapportJRXML());
				DebuggerLog4J.logger.debug(getPathRapportModele());				
				LancerRemboursements(NumeroBatch,connexionr2t);
				CreerFichierDesRemboursement(NumeroBatch);
	    	}
	        catch(Exception e)
	        {
	                DebuggerLog4J.logger.fatal(e.getMessage());
	        }	    
		}
	
		

		public void LancerRemboursements(int numeroBatch,Connection connexionr2t) throws SQLException
		{
			//La requete pour trouver les élément du remboursement
			String RequeteDonneesRemboursement =  " SELECT * " 
												 +"	FROM remboursement  "
												 +" WHERE `idBatchTraitement`="+numeroBatch;			
			//Ouverture des Connexions
			ResultSet RSDonneesRemboursement = null;
			Statement st1 = connexionr2t.createStatement();
			String annee ="";
			try
			{				
				RSDonneesRemboursement = st1.executeQuery(RequeteDonneesRemboursement);
			}
        	catch(Exception e)
        	{        		
                DebuggerLog4J.logger.fatal(e.getMessage());
            }     
        	
        	int i = 0;
        	//Impression des remboursement trouvées dans la base correspondant au numéro de batch
			while(RSDonneesRemboursement.next())
			{
				if (i==0)
				{
					annee = RSDonneesRemboursement.getString("anneeEx");
					RepertoireRemboursements= CreerRepertoireFacture(annee,numeroBatch);
				}
				int idRemboursement = RSDonneesRemboursement.getInt("idRemboursement");
				_log.logger.debug("Creation du remboursemente Num : " + idRemboursement);				
				CreationRemboursement(idRemboursement);
				i++;
			}	
			RSDonneesRemboursement.close();			
		}//Fin méthode
		
	
		public void CreationRemboursement(int idRemboursement)
		{			
			try 
			{				
	            //Chargement du rapport
	        	//Le fichier jrxml est obtenu par l'éditeur de rapport ireport
	        	//L'emplacement du fichier est important
				JasperDesign jasperDesign = JRXmlLoader.load(pathRapportJRXML);					
				Map parameters = new HashMap(); 				
				parameters.put("par_CheminModele", pathRapportModele);				
				parameters.put("par_numeroRemboursement", new Integer(idRemboursement));
				JasperReport jasperReport=null;
				 
				//- compilation du rapport
				try 
				{					
					jasperReport = JasperCompileManager.compileReport(jasperDesign);					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					_log.logger.fatal("Erreur dans la compilation....................[Erreur]");
					_log.logger.fatal(e.getMessage());
				}
							
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connexionr2t);
				 try
				 {
	            
		            NomFichierRemboursement = RepertoireRemboursements.toString()+"/Remb_"+ idRemboursement +".pdf";
		            NomFichierRemboursement = ReglerCheminSelonOs.reglerCheminSelonOS(NomFichierRemboursement);
		            JasperExportManager.exportReportToPdfFile(jasperPrint,NomFichierRemboursement);
	             }//fin try
	            catch(Exception e)
	            {
	                e.printStackTrace();    
	            	DebuggerLog4J.logger.fatal(e.getMessage());
	            }//fin catch 
	        } //fin try	        
	        catch (JRException e) 
	        {
	        	DebuggerLog4J.logger.fatal(e.getMessage());   
	        }//fin catch       
	    }// Fin de la méthode	

		
		
		public File CreerRepertoireFacture(String annee,int NumeroBatch)
		{
			File RepertoireRemboursements= new File("");
			try
			{				
				String cheminDesRemboursements= fichierDeConfiguration.getCheminRemboursement();
				String nomNewDossier= annee+"-"+NumeroBatch;
				RepertoireRemboursements = new File( cheminDesRemboursements+"//"+nomNewDossier);
				RepertoireRemboursements.mkdir();				
				
			}//fin try
        	catch(Exception e)
        	{                
                DebuggerLog4J.logger.fatal(e.getMessage());
                e.printStackTrace();
            }//fin catch
        	return RepertoireRemboursements;   			  
		}
		
		
	
		
		// class pour l'impression des remboursements 
		public void CreerFichierDesRemboursement(int numeroBatch)
		{
			RequestRemboursement requestRemboursement = new RequestRemboursement();
			Vector listDesRemboursements= requestRemboursement.getListeDesRemboursement(numeroBatch);
			Vector listeDesFacturesAConcatiner = new Vector();
			
			if (listDesRemboursements.size()>0)
			{
				Remboursement remboursement =null;
				//concatenation des factures PDF dans un seul fichier PDF
				for (int i = 0; i < listDesRemboursements.size(); i++) 
				{
					remboursement = (Remboursement)listDesRemboursements.elementAt(i);
					String cheminRemboursement=fichierDeConfiguration.getCheminRemboursement()
						+remboursement.getAnneeEx()+"-"+ remboursement.getIdBatchTraitement()+"/Remb_"
						+ remboursement.getIdRemboursement()+".pdf"; 
					cheminRemboursement=ReglerCheminSelonOs.reglerCheminSelonOS(cheminRemboursement);				
					//cheminRemboursement= cheminRemboursement.replaceAll("//", "/");
					DebuggerLog4J.logger.debug(cheminRemboursement);
					listeDesFacturesAConcatiner.addElement(cheminRemboursement);
				}
			String cheminNewFile=fichierDeConfiguration.getCheminRemboursement()
				+remboursement.getAnneeEx()+"-"+ remboursement.getIdBatchTraitement()+"/"
				+"/BatchNumero"+numeroBatch+".pdf";
			cheminNewFile=ReglerCheminSelonOs.reglerCheminSelonOS(cheminNewFile);			
			//cheminNewFile= cheminNewFile.replaceAll("//", "/");
			DebuggerLog4J.logger.debug(cheminNewFile);
			ConcatenationFile cf = new ConcatenationFile();
			cf.concat(listeDesFacturesAConcatiner, cheminNewFile);			
		 }
		}
		DebuggerLog4J _log = new DebuggerLog4J();

		
		
		
		
		
		
		/**
		 * @return the cheminModele
		 */
		public String getCheminModele() {
			return cheminModele;
		}

		/**
		 * @param cheminModele the cheminModele to set
		 */
		public void setCheminModele(String cheminModele) {
			this.cheminModele = cheminModele;
		}





		/**
		 * @return the connexionr2t
		 */
		public Connection getConnexionr2t() {
			return connexionr2t;
		}





		/**
		 * @param connexionr2t the connexionr2t to set
		 */
		public void setConnexionr2t(Connection connexionr2t) {
			this.connexionr2t = connexionr2t;
		}





		/**
		 * @return the repertoireCourant
		 */
		public String getRepertoireCourant() {
			return RepertoireCourant;
		}





		/**
		 * @param repertoireCourant the repertoireCourant to set
		 */
		public void setRepertoireCourant(String repertoireCourant) {
			RepertoireCourant = repertoireCourant;
		}





		/**
		 * @return the nomFichierRemboursement
		 */
		public String getNomFichierRemboursement() {
			return NomFichierRemboursement;
		}





		/**
		 * @param nomFichierRemboursement the nomFichierRemboursement to set
		 */
		public void setNomFichierRemboursement(String nomFichierRemboursement) {
			NomFichierRemboursement = nomFichierRemboursement;
		}





		/**
		 * @return the rSDonneesFactures
		 */
		public ResultSet getRSDonneesFactures() {
			return RSDonneesFactures;
		}





		/**
		 * @param rSDonneesFactures the rSDonneesFactures to set
		 */
		public void setRSDonneesFactures(ResultSet rSDonneesFactures) {
			RSDonneesFactures = rSDonneesFactures;
		}





		/**
		 * @return the pathRapportJRXML
		 */
		public String getPathRapportJRXML() {
			return pathRapportJRXML;
		}





		/**
		 * @param pathRapportJRXML the pathRapportJRXML to set
		 */
		public void setPathRapportJRXML(String pathRapportJRXML) {
			this.pathRapportJRXML = pathRapportJRXML;
		}





		/**
		 * @return the pathRapportModele
		 */
		public String getPathRapportModele() {
			return pathRapportModele;
		}





		/**
		 * @param pathRapportModele the pathRapportModele to set
		 */
		public void setPathRapportModele(String pathRapportModele) {
			this.pathRapportModele = pathRapportModele;
		}





		/**
		 * @return the repertoireRemboursements
		 */
		public File getRepertoireRemboursements() {
			return RepertoireRemboursements;
		}





		/**
		 * @param repertoireRemboursements the repertoireRemboursements to set
		 */
		public void setRepertoireRemboursements(File repertoireRemboursements) {
			RepertoireRemboursements = repertoireRemboursements;
		}





		/**
		 * @return the numeroDuBatch
		 */
		public Integer getNumeroDuBatch() {
			return NumeroDuBatch;
		}





		/**
		 * @param numeroDuBatch the numeroDuBatch to set
		 */
		public void setNumeroDuBatch(Integer numeroDuBatch) {
			NumeroDuBatch = numeroDuBatch;
		}





		/**
		 * @return the fichierDeConfiguration
		 */
		public FichierDeConfiguration getFichierDeConfiguration() {
			return fichierDeConfiguration;
		}





		/**
		 * @param fichierDeConfiguration the fichierDeConfiguration to set
		 */
		public void setFichierDeConfiguration(
				FichierDeConfiguration fichierDeConfiguration) {
			this.fichierDeConfiguration = fichierDeConfiguration;
		}
		
		
		
	}// Fin de la classe
