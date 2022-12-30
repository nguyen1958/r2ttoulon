
package fr.analogon.r2t.reclamation;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.Utilitaire.ReglerCheminSelonOs;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.request.Connecteur;

/**	-----------------------------------------------------------------------<br>
*
* @author 	P. TREBOSC
* 20/02/2008	16h10<br>
*-----------------------------------------------------------------------<br>
*Classe qui lance la réalisation des courriers rtf<br>
*A partir d'un modéle créé avec ireport<br>
*<br>
*
*Installation : Placer le répertoire TemplateRapport qui contient le modéle jrxml de la facture<br>
*dans le répertoire WEB-INF <br>
*Par exemple : D:\ProjetEclipse\r2tBordeaux\WEB-INF\TemplateRapport <br>
*Intégrer dans lib les librairies: <br>
*		jasperreports-3.0.0.jar   dépend de la version de ireport (ireport 3.0.0)<br>
*		commons-digester-1.8 (inutile - supprimé le 05 septembre 2008)<br>
*		commons-collections-3.2<br>
*		commons-logging-api-1.1.1 (utile pour org/apache/commons/logging/LogFactory)<br>
*		commons-logging-1.1.1   (inutile - supprimé le 05 septembre 2008)<br>
*		itext-1.3.1.jar   (pour la génération de pdf)<br>
*		commons-beanutils-1.7<br>
*<br>
*Les versions de bibliotheques compatibles peuvent etre trouvee dans le repertoire lib de la version de 
*ireport utilisé pour générer le rapport<br>
*Les fichiers seront stockes sur un répertoire de type r2tData\data\courriers\<br>
*Nommage des factures<br>
*Les fichiers seront nommés <br>
*exemple.  reclamationTAV2-2008000001.doc<br>
*<br>
*Séquence :
*	Constructeur : recupere le numero de facture
*	InitCreationCourrier : creation du chemin  
*	LancerFactures : recherche les données de toutes les factures correspondant au numéro de batch
*	CreationFactures : créer chaque facture
*	
*Transmettre a l'application java uniquement les fichier .jrxml du rapport et de ses sous-rapports
*
**reste é faire :<br>
*-----------------------------------------------------------------------<br>
*/
public class CreerCourrier {
		//Déclaration des variables de la classe
		//-------------------------------------------------------------------		
		private String cheminModele;
		
		private Connection connexionr2tBordeaux= InitialisationConnexionLectureConfiguration.getConnexion().connexion;
		private String RepertoireCourant;
		private String NomFichier;
		private String pathRapportJRXML;
		private File RepertoireFactures;
		//Le numero de facture concernant la reclamation
		private Integer NumeroFacture;
		//Le type de courrier en reclamation
		private String typeCourrier;
		FichierDeConfiguration  fichierDeConfiguration = InitialisationConnexionLectureConfiguration.fichierDeConfiguration;

		/**	-------------------------------------------------------------------<br>
		*Méthode constructeur de la classe <br>
		*@param NumeroBatch : le numéro du batch des factures é éditer <br>
		*@throws SQLException : gére les exeptions sql de base de données<br>
		*Récupération de la connexion é partir de la méthode Connecteur du package request<br>
		*Définition des répertoires nécessaires<br>
		*Lancement des factures <br>
		*-------------------------------------------------------------------
		*/
		public CreerCourrier(String leNumeroFacture, String leTypeCourrier) 
		{										 		
			setNumeroFacture(new Integer(leNumeroFacture));	
			setTypeCourrier(leTypeCourrier);
		}// Fin du constructeur

		/*
		 * Connexion à la base de données
		 * Creation du chemin du Emplacement du répertoire WEB-INF\TemplateRapport\
		 */
		public boolean InitCreationCourrier()
		{
			Connecteur Connecteurr2tbordeaux = new Connecteur();
			try{
				setConnexionr2tBordeaux(connexionr2tBordeaux);
			}
			catch(Exception e){
				//System.out.println("Classe : CreerFactures - Méthode : CreerFactures" +
        		DebuggerLog4J.logger.fatal("Probleme de connexion à la base de données"+e.getMessage());
				//System.out.println("Vérifier que le serveur de base de données et OK");				
			}//fin catch
			
			//Emplacement du template du courrier WEB-INF\TemplateRapport\
			//On complète avec la version du courrier de reclamation a traiter
			//cheminAbsoluApplication +"/WEB-INF/Templates/TemplateCourrierReclamation/CourrierReclamation"
			setPathRapportJRXML (fichierDeConfiguration.getCheminJRXMLCourrier()+ getTypeCourrier()+".jrxml");
			
			//setPathRapportModele(fichierDeConfiguration.getCheminRapportModele());
			CreationCourrier();
			//CreerFichierDesFactures(getNumeroFacture());
			return true;
		}
		
		/**-------------------------------------------------------------------<br>
		*Impression d'un courrier											<br>
		*Cette méthode : <br>
		*	charge le rapport jrxml<br>
		*	prépare les paramétres é passer au rapport<br>
		*	Compile le rapport<br>
		*	lie les paramétres au rapport<br>
		*	créer la facture au format pdf<br>
		*
	    *-------------------------------------------------------------------
	    *Les paramétres issue des tables<br>
	    *@param numeroFacture : table factures<br>
	    **/
		public void CreationCourrier(){
			
			//il faut créer le répertoire /année/Né batch/ exemple:  /2008/11/
			//dans data\\factures
			
			try {				
	            // - Chargement du rapport
	        	//Le fichier jrxml est obtenu par l'éditeur de rapport ireport
	        	//L'emplacement du fichier est important						
								
				JasperDesign jasperDesign = JRXmlLoader.load(pathRapportJRXML);					
				Map parameters = new HashMap(); 				
				parameters.put("par_CheminModele", cheminModele);	
				parameters.put("par_numeroFacture", new Integer(getNumeroFacture()));
				JasperReport jasperReport=null;
				//- compilation du rapport
				try 
				{					
					jasperReport = JasperCompileManager.compileReport(jasperDesign);					
				} 
				catch (Exception e) 
				{
					//System.out.println("Erreur dans la compilation....................[Erreur]");
					e.printStackTrace();
				}
								
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connexionr2tBordeaux);

	            //Création du fichier doc contenant le courrier complet 
	            try{

	            //Pour sofien
	            NomFichier = fichierDeConfiguration.getCheminCouriers()+"reclamation-"+getTypeCourrier()+"-"+getNumeroFacture() + ".doc";
	            NomFichier = ReglerCheminSelonOs.reglerCheminSelonOS(NomFichier);

	            //Creation du courrier
	            JRRtfExporter rtfExporter = new JRRtfExporter(); 
	            rtfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint); 
	            rtfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,NomFichier);
	            rtfExporter.exportReport();		
	            
	            	}//fin try
	            	catch(Exception e)
	            	{
	                    //System.out.println("Classe : CreerCourrier - Méthode : CreationCourrier => Probleme d'exportation du rtf dans classe JasperRapport");
	                    //System.out.println("Classe : CreerCourrier - Méthode : CreationCourrier => vérifier par exemple le chemin dans le fichier application.properties cheminDataWind");
	                    //System.out.println("Classe : CreerCourrier - Méthode : CreationCourrier => vérifier si le fichier" + NomFichier + "n'est pas ouvert");
	                    e.printStackTrace();
	                }//fin catch	            	
	            	////System.out.println("*************FIN DE CREATION DE LA FACTURE pdf**************");	
	            	
	        } //fin try
	        
	        catch (JRException e) {
	            e.printStackTrace();     
	        }//fin catch       
	    }// Fin de la méthode	

		
		/**------------------------------------------------------------------------------<br>
		*Methode de traitement des exceptions<br>
	    *--------------------------------------------------------------------------------<br>
	    */

		public static void erreur(String msg, int exitCode)
		{	System.err.print(msg);
			System.exit(exitCode);
		}

		public String getCheminModele() {
			return cheminModele;
		}
		public void setCheminModele(String cheminModele) {
			this.cheminModele = cheminModele;
		}

		public Connection getConnexionr2tBordeaux() {
			return connexionr2tBordeaux;
		}
		public void setConnexionr2tBordeaux(Connection connexionr2tBordeaux) {
			this.connexionr2tBordeaux = connexionr2tBordeaux;
		}
		public String getRepertoireCourant() {
			return RepertoireCourant;
		}
		public void setRepertoireCourant(String repertoireCourant) {
			RepertoireCourant = repertoireCourant;
		}

		public File getRepertoireFactures() {
			return RepertoireFactures;
		}
		public void setRepertoireFactures(File repertoireFactures) {
			RepertoireFactures = repertoireFactures;
		}
		public String getPathRapportJRXML() {
			return pathRapportJRXML;
		}

		public void setPathRapportJRXML(String pathRapportJRXML) {
			this.pathRapportJRXML = pathRapportJRXML;
		}
		
		public Integer getNumeroFacture() {
			return NumeroFacture;
		}

		public void setNumeroFacture(Integer numeroFacture) {
			NumeroFacture = numeroFacture;
		}

		public String getTypeCourrier() {
			return typeCourrier;
		}

		public void setTypeCourrier(String typeCourrier) {
			this.typeCourrier = typeCourrier;
		}
	}// Fin de la classe
