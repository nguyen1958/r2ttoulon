
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
import fr.analogon.r2t.pojo.Facture;
import fr.analogon.r2t.request.RequestBatch;
import fr.analogon.r2t.request.RequestFacture;
import fr.analogon.r2t.request.RequestParametres;

/**	-----------------------------------------------------------------------<br>
*
* @author 	P. TREBOSC
* 20/02/2008	16h10<br>
*-----------------------------------------------------------------------<br>
*Classe qui lance la réalisation des fichiers pdf<br>
*A partir d'un modéle créé avec ireport<br>
*<br>
*REGLE
*On facture par taxe, mais comme pour un batch, il n’y a qu’un seul type de taxe,<br> 
*la facturation que l’on fait par batch donne le bon résultat.<br>
*On imprime les factures indiquées dans le tableau d'objet dynamique LesFactures<br>
*Les factures pdf sont enregistrees dans le répertoire data de l'application<br>
*
*TAV :Taxe annuelle (M2/AN, unite/AN, ML/AN......)
*Facturation annee suivante ( si DI <=01/01/AAAA), facture toute l'annee la date d'instalation 
*reste de l'information
*Passage de l'etat de "AFacturer" a l'etat "Facturee"
*L'element ne sera pas refacturer que si a la fin de l'annee il sera bascule (Bascule des TAv Actif)
*Facturation de 01/01/AAAA au 31/12/AAAA
*Utilisation du prix de bareme selon l'annee
*Pas de necissite de controle que suite a une reclamation
*Pas de prorata applique 
*
*DDV droie de voirie (M2/Semaine, unite/Mois, ML/Semaine, Forfait,......)
*Facturation si l'etat de l'ouvrage et "AFacturer" et si la date d'instalation < date de lancement du batch
*Passage de l'etat de "AFacturer" a l'etat "FacturerAControler"
*L'element devra etre controler a partir de la date (Date d'instlation + nombre de Peiode facture +1 *Periode) 
*Ne sera facturer que si il a ete valide par le PDA ou un utilisateur change l'etat de l'ouvrae a partir de l'apllication 
*Utilisation du prix de bareme selon la perioe 
*Facturation de (Date d'instlation + nombre de Peiode facture *Periode) au (Date d'instlation + nombre de Peiode facture + 1 * Periode) 
*Pas de prorata applique 
*
*Publicite : Elles sont annuelle (M2/AN, unite/AN, ML/AN,......)
*Facturation si l'etat de l'ouvrage et "AFacturer" et si la date d'instalation < date de lancement du batch
*On connait des le debut la date de debut et la date de fin de l'ouvrage, saisie dans l'application  
*Facturation de l'ouvrage de (date de debut a la date de fin de l'ouvrage )
*Passage de l'etat de "AFacturer" a l'etat "Facturee"
*L'element ne sera plus refacturer 
*Utilisation du prix de bareme selon l'annee
*Pas de necissite de controle que suite a une reclamation
*Prorata applique
*
*Infraction:??
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
*Les versions de bibliothéques compatibles peuvent étre trouvé dans le répertoire lib de la version de 
*ireport utilisé pour générer le rapport<br>
*Les fichiers seront stockés sur un répertoire de type /année/Né batch/ exemple:  /2008/11/<br>
*Nommage des factures<br>
*Les fichiers seront nommés avec un numéro chrono dans le batch ce qui permettra une reprise d'impression.<br>
*NumeroChrono.numeroBatch.TypeTaxe.nuemroClient.numeroTitre.pdf<br>
*en cas de probléme d'impression, il faudra réimprimer les fichiers é partir du numéro chrono spécifié.<br>
*exemple.  210.100.TAV.548.760000.pdf<br>
*<br>
*Séquence :
*	Constructeur : initialise le module de facturation
*		RecupCheminApplication
*	LancerFactures : recherche les données de toutes les factures correspondant au numéro de batch
*	CreationFactures : créer chaque facture
*	
*Transmettre a l'application java uniquement les fichier .jrxml du rapport et de ses sous-rapports
*
**reste é faire :<br>
*-----------------------------------------------------------------------<br>
*/

public class CreerFactures {
		//Déclaration des variables de la classe
		//-------------------------------------------------------------------		
		private String cheminModele;
		
		private Connection connexionr2t= InitialisationConnexionLectureConfiguration.connexion.connexion;
		//private Facture UneFacture;
		private String RepertoireCourant;
		private String NomFichierFacture;
		private ResultSet RSAnneebatch;
		private ResultSet RSDonneesFactures;
		private String pathRapportJRXML;
		private String pathRapportModele;
		private File RepertoireFactures;
		//private String pathImage;
		private String AnneeBatch;
		//Le batch é facturer
		private Integer NumeroDuBatch;
		FichierDeConfiguration fichierDeConfiguration 
		   =  InitialisationConnexionLectureConfiguration.getFichierDeConfiguration();
		//ceci marche pour test
		//FichierDeConfiguration fichierDeConfiguration=new FichierDeConfiguration();
		/**	-------------------------------------------------------------------<br>
		*Méthode constructeur de la classe <br>
		*@param NumeroBatch : le numéro du batch des factures é éditer <br>
		*@throws SQLException : gére les exeptions sql de base de données<br>
		*Récupération de la connexion é partir de la méthode Connecteur du package request<br>
		*Définition des répertoires nécessaires<br>
		*Lancement des factures <br>
		*-------------------------------------------------------------------
		*/
		public CreerFactures(int NumeroBatch, String mode) throws Exception 
		{
			boolean TestRapportexist = true;							 		
			setNumeroDuBatch(new Integer(NumeroBatch));
	    	try{
	    		setconnexionr2t(connexionr2t);
	    	}
	        catch(Exception e){
	                DebuggerLog4J.logger.fatal("Classe : CreerFactures - Méthode : CreerFactures" +
	                		"Probleme de connexion à la base de données");
	                DebuggerLog4J.logger.fatal("Vérifier que le serveur de base de données et OK");
	                DebuggerLog4J.logger.fatal(e.getMessage());
	        }//fin catch
	        
			
			
			if(TestRapportexist)
			{
				RequestBatch requestBatch = new RequestBatch();
				Batch batch = requestBatch.getBatch(NumeroBatch);
				String typeTaxe = batch.getTypeEmplacement();
				//Utuliser le template TLPE si le batch concerne des factures est TLPE
				//utulisation du template selon la ville 
				RequestParametres requestParametres = new RequestParametres();
				String ville =  requestParametres.getVille();
				if(ville.equalsIgnoreCase("Toulon") ) 
				{
					DebuggerLog4J.logger.debug("typeTaxe=>"+typeTaxe);
					//Facture TLPE de toulon
					if (typeTaxe.equalsIgnoreCase("Tlpe"))
					{
						setPathRapportJRXML (fichierDeConfiguration.getCheminRapportJRXMLFactureTLPEToulon());
						setPathRapportModele(fichierDeConfiguration.getCheminRapportModeleTLPEToulon());
						DebuggerLog4J.logger.debug("Imputation TLPE");
					}
					//Facture Pub de toulon
					else if (typeTaxe.equalsIgnoreCase("Pub"))
					{
						setPathRapportJRXML (fichierDeConfiguration.getCheminRapportJRXMLFacturePubToulon());
						setPathRapportModele(fichierDeConfiguration.getCheminRapportModelePubToulon());
						DebuggerLog4J.logger.debug("Imputation Pub");
					}
					
					
					//TemplateFactureAN :   Imputation (grand type de taxe) CONVENTION_ANNUELLE et ETALAGE					
					else if ( typeTaxe.equalsIgnoreCase("ETALAGE") )
					{
						pathRapportModele = fichierDeConfiguration.getCheminRoleJRXML() +"/TemplateFactures/TemplateFactureAN/";
						pathRapportJRXML = pathRapportModele +"FactureANr2tToulonV1.jrxml";
						DebuggerLog4J.logger.debug("Imputation ETALAGE");
					}
					//TemplateFactureCONV : Imputation CONVENTION_PUB / CONVENTION_MOURILLON / CONVENTION_MAYOL / CONVENTION_70323
					else if ( typeTaxe.equalsIgnoreCase("CONVENTION_PUB")
							 || typeTaxe.equalsIgnoreCase("CONVENTION_MOURILLON")
							 || typeTaxe.equalsIgnoreCase("CONVENTION_MAYOL")
							 || typeTaxe.equalsIgnoreCase("CONVENTION_70323") )
					{
						
						pathRapportModele = fichierDeConfiguration.getCheminRoleJRXML() +"/TemplateFactures/TemplateFactureCONV/";
						pathRapportJRXML = pathRapportModele +"FactureCONVr2tToulonV1.jrxml";
						DebuggerLog4J.logger.debug("Imputation CONVENTION_PUB / CONVENTION_MOURILLON / CONVENTION_MAYOL / CONVENTION_70323");
						
					}
					//TemplateFactureMOIS : Imputation  MARCHE_MENSUEL /TOUR_ROYALE
					else if (  typeTaxe.equalsIgnoreCase("MARCHE_MENSUEL") 
							  || typeTaxe.equalsIgnoreCase("TOUR_ROYALE") )						 
					{
						pathRapportModele = fichierDeConfiguration.getCheminRoleJRXML() +"/TemplateFactures/TemplateFactureMOIS/";
						pathRapportJRXML = pathRapportModele +"FactureMOISr2tToulonV1.jrxml";
						DebuggerLog4J.logger.debug("Imputation   MARCHE_MENSUEL /TOUR_ROYALE");
					}
					//TemplateFacture : Imputation  MANIFESTATION_DIVERS
					else if ( typeTaxe.equalsIgnoreCase("MANIFESTATION_DIVERS")  )						 
					{
						pathRapportModele = fichierDeConfiguration.getCheminRoleJRXML() +"/TemplateFactures/TemplateFactureManifestationsDivers/";
						pathRapportJRXML = pathRapportModele +"FactureManifestationsDiversr2tToulonV1.jrxml";
						DebuggerLog4J.logger.debug("Imputation  MANIFESTATION_DIVERS ");
						
						
					}		
					//TemplateFactureTRIM : Imputation AMBULANT / KIOSQUE / MARCHE_TRIMESTRIEL / STATIONNEMENT
					else if (    typeTaxe.equalsIgnoreCase("AMBULANT")
							  || typeTaxe.equalsIgnoreCase("KIOSQUE")
							  || typeTaxe.equalsIgnoreCase("MARCHE_TRIMESTRIEL")
							  || typeTaxe.equalsIgnoreCase("STATIONNEMENT")
							)
					{
						pathRapportModele = fichierDeConfiguration.getCheminRoleJRXML() +"/TemplateFactures/TemplateFactureTRIM/";
						pathRapportJRXML = pathRapportModele +"FactureTRIMr2tToulonV1.jrxml";
						DebuggerLog4J.logger.debug("Imputation  AMBULANT / KIOSQUE / MARCHE_TRIMESTRIEL / STATIONNEMENT");
						DebuggerLog4J.logger.debug("pathRapportModele : "+ pathRapportModele );
						DebuggerLog4J.logger.debug("pathRapportJRXML : "+ pathRapportJRXML );
					}
					
					//TemplateFactureODP :  Imputation (grand type de taxe) ODP
					else if ( typeTaxe.equalsIgnoreCase("ODP")  )
					{
						pathRapportModele = fichierDeConfiguration.getCheminRoleJRXML() +"/TemplateFactures/TemplateFactureODP/";
						pathRapportJRXML = pathRapportModele +"FactureODPr2tToulonV1.jrxml";
						DebuggerLog4J.logger.debug("Imputation ODP");
					}					
					
					//Template par defaut pour toulon
					else 
					{
						//cheminRoleJRXML = D:/.../r2t/WEB-INF/Templates/TemplateRapport/RapportToulon						
						pathRapportModele = fichierDeConfiguration.getCheminRoleJRXML() +"/TemplateFactures/TemplateFacture/";
						pathRapportJRXML = pathRapportModele +"Facturer2tToulonV1.jrxml";
					}
					DebuggerLog4J.logger.debug("PathRapportJRXML="+fichierDeConfiguration.getCheminRapportJRXMLFactureParDefautToulon());
					DebuggerLog4J.logger.debug("PathRapportModele="+fichierDeConfiguration.getCheminRapportModeleParDefautToulon());
				
				}
				
				//Facture par defaut : actuellement celle de bordeaux 
				else
				{ 
					//template de la facture TLPE de Bordeaux 
					if(typeTaxe.equalsIgnoreCase("Tlpe"))
					{
						setPathRapportJRXML (fichierDeConfiguration.getCheminRapportJRXMLFactureTLPE());
						setPathRapportModele(fichierDeConfiguration.getCheminRapportModeleTLPE());					
					}
					//template par defaut 
					else
					{
						setPathRapportJRXML (fichierDeConfiguration.getCheminRapportJRXMLFacture());
						setPathRapportModele(fichierDeConfiguration.getCheminRapportModele());
					}
				}
				DebuggerLog4J.logger.debug("Template utulise");
				DebuggerLog4J.logger.debug(getPathRapportJRXML());
				DebuggerLog4J.logger.debug(getPathRapportModele());
				
				//Le paramtres mode sert a faire la difference entre les mode
				//mode normale = on lance la creation de toutes 
				if(mode.equalsIgnoreCase("normale"))
				{					
					LancerFactures(NumeroBatch,true);
					CreerFichierDesFactures(NumeroBatch);
				}
			}
		
		}// Fin du constructeur

	
		
		/**-----------------------------------------------------------------------
		*Méthode RecupererDonneesFacture()
		*-----------------------------------------------------------------------
		*Récupére les données nécessaires en base dans la table factures
		*Pour la facture
		*NumeroFacture, NumeroChrono, numeroBatch, TypeTaxe, NumeroClient, NumeroTitre
		*Pour le nom du répertoire annee		 
		* @param numeroBatch
		* @param connexionr2t
		* @throws SQLException
		*/
		public void LancerFactures(int numeroBatch, boolean lancerToutesLesFactures) throws SQLException
		{
			
			//La requete pour trouver l'année du batch en supposant qu'elle soit la méme pour
			//tout le batch
			String RequeteAnneeBatch = "SELECT `anneeEx` FROM `facture` " +
					"WHERE `idBatchTraitement`="+numeroBatch;
			//DebuggerLog4J.logger.debug(RequeteAnneeBatch);
			//La requete pour trouver les élément de la facture
			String RequeteDonneesFactures = "SELECT `numeroFacture`,`numeroTitre`,`typeTaxe`," +
					"`idClient` FROM `facture` WHERE `idBatchTraitement`="+numeroBatch;
			
			//Ouverture des Connexions
			Statement st1 = connexionr2t.createStatement();
			Statement st2 = connexionr2t.createStatement();
			
			try{

				RSAnneebatch = st1.executeQuery(RequeteAnneeBatch);
				RSDonneesFactures = st2.executeQuery(RequeteDonneesFactures);
			}//fin try
        	catch(Exception e){
        		DebuggerLog4J.logger.fatal("Classe : CreerFactures - Méthode : LancerFactures =>Probleme de récupération des données pour la facturation");
                DebuggerLog4J.logger.fatal(e.getMessage());
            }
        	
        	
        	//fin catch
        	//Récupération de l'année
        	//Je suppose que pour un batch c'est la méme année donc je prend le premier
        	//DebuggerLog4J.logger.debug(RSDonneesFactures.getString("anneeEx"));
        	try{
        		RSAnneebatch.first();
        		setAnneeBatch(RSAnneebatch.getString("anneeEx"));
        	}//fin try
        	catch(Exception e){
                DebuggerLog4J.logger.fatal("Classe : CreerFactures - Méthode : LancerFactures =>Pas d'année d'exercice pour le numéro de batch ");
                DebuggerLog4J.logger.fatal(e.getMessage());
            }//fin catch
        	// Création du répertoire d'enregistrement des facture
        	DebuggerLog4J.logger.debug("Création du répertoire d'enregistrement des facture");
        	setRepertoireFactures(CreerRepertoireFacture(AnneeBatch,numeroBatch));
        	
        	DebuggerLog4J.logger.debug(CreerRepertoireFacture(AnneeBatch,numeroBatch));
        	
        	if(lancerToutesLesFactures)
        	{
	        	//Impression des facture trouvées dans la base correspondant au numéro de batch
				while(RSDonneesFactures.next())
				{
					DebuggerLog4J.logger.debug("Creation de la facture Num : " + RSDonneesFactures.getInt("numeroFacture"));
					RequestFacture requestFacture = new RequestFacture();
					Facture factureTmp = requestFacture.getFacture(""+RSDonneesFactures.getInt("numeroFacture"));
					String mm= factureTmp.getMontantTotalFacture();
					//if( ! mm.equalsIgnoreCase("0") && ! mm.equalsIgnoreCase("0.00"))
					if( true)
					{
						//
						
						CreationFactures(
								RSDonneesFactures.getInt("numeroFacture"),
								210,		//numéro chrono non défini
								numeroBatch, 
								RSDonneesFactures.getString("TypeTaxe"), 
								RSDonneesFactures.getInt("idClient"), 
								RSDonneesFactures.getInt("numeroTitre"),
								"normale");
						//DebuggerLog4J.logger.debug(RSDonneesFactures.getInt("numeroFacture"));
					}
				}
        	}//fin 
			RSAnneebatch.close();
			RSDonneesFactures.close();
		}//Fin méthode
		
		
		
			
		
		
		
		
		/**-------------------------------------------------------------------<br>
		*Impression d'une facture											<br>
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
	    *@param NumeroChrono : table factures é voir ???<br>
	    *@param NumeroBatch : ce numéro est passé en paramétre é la classe<br>
	    *@param TypeTaxe : table factures<br>
	    *@param NumeroClient <br>
	    *@param NumeroTitre : table factures<br>
	    **/
		//paul 07072020 - relance facture (type="relance")
		public void CreationFactures(int numeroFacture,int NumeroChrono,
				int NumeroBatch, String TypeTaxe, int NumeroClient, int NumeroTitre, String type){
			
			//il faut créer le répertoire /année/Né batch/ exemple:  /2008/11/
			//dans data\\factures
			
			try {				
	            // - Chargement du rapport
	        	//Le fichier jrxml est obtenu par l'éditeur de rapport ireport
	        	//L'emplacement du fichier est important						
				//DebuggerLog4J.logger.debug("pathRapportJRXML="+pathRapportJRXML);
				//DebuggerLog4J.logger.debug("pathRapportModele="+pathRapportModele);	
				System.out.println("pathRapportJRXML="+pathRapportJRXML);
				System.out.println("pathRapportModele="+pathRapportModele);	
				JasperDesign jasperDesign = JRXmlLoader.load(pathRapportJRXML);	
				System.out.println("JRXmlLoader.load");
				Map parameters = new HashMap(); 				
				parameters.put("par_CheminModele", pathRapportModele);	
				parameters.put("par_numeroFacture", new Integer(numeroFacture));
				//le type peut etre : normal, reedit,normale
				parameters.put("par_relance", new String(type));
				JasperReport jasperReport=null;
				//- compilation du rapport
				try 
				{					
					jasperReport = JasperCompileManager.compileReport(jasperDesign);
					System.out.println("JasperCompileManager.compileReport");
				} 
				catch (Exception e) 
				{
					DebuggerLog4J.logger.fatal("Erreur dans la compilation....................[Erreur]");
					DebuggerLog4J.logger.fatal(e.getMessage());
				}
							
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connexionr2t);
				System.out.println("JasperFillManager.fillReport");
	            //Création du fichier pdf contenant le rapport complet 
	            try{
	            //Construction du nom du fichier de la facturepdf en ajoutant le numéro du client et le numéro de contrat
	            //Modéle : NumeroChrono.numeroBatch.TypeTaxe.nuemroClient.numeroTitre.pdf
	            /*NomFichierFacture = RepertoireFactures+"\\"+
	            					NumeroChrono+"" + "-"+
	            					NumeroBatch +"" + "-"+
	            					TypeTaxe + "-"+
	            					NumeroClient+""+ "-"+
	            					NumeroTitre +"" +
	            					".pdf";
	            */
	            //Pour sofien
	            NomFichierFacture = RepertoireFactures+"/"+numeroFacture +	".pdf";
	            if (type.equalsIgnoreCase("relance"))	
	            		NomFichierFacture = RepertoireFactures+"/"+numeroFacture +	"relance.pdf";
	            NomFichierFacture = ReglerCheminSelonOs.reglerCheminSelonOS(NomFichierFacture);
	            

	            DebuggerLog4J.logger.debug("**************************************");	            
	            DebuggerLog4J.logger.debug("NomFichierFacture"+NomFichierFacture);
	            DebuggerLog4J.logger.debug("**************************************");
	            
	            
	            		JasperExportManager.exportReportToPdfFile(jasperPrint,NomFichierFacture);
	            		System.out.println("JasperExportManager.exportReportToPdfFile");
	            	}//fin try
	            	catch(Exception e)
	            	{
	                    DebuggerLog4J.logger.fatal("Classe : CreerFactures - Méthode : CreationFactures => Probleme d'exportation du pdf dans classe JasperRapport méthode CreationRapport");
	                    DebuggerLog4J.logger.fatal("Classe : CreerFactures - Méthode : CreationFactures => vérifier par exemple le chemin dans le fichier application.properties cheminDataWind");
	                    DebuggerLog4J.logger.fatal("Classe : CreerFactures - Méthode : CreationFactures => vérifier si le fichier" + NomFichierFacture + "n'est pas ouvert");
	                    DebuggerLog4J.logger.fatal(e.getMessage());
	                }//fin catch	            	
	            	//DebuggerLog4J.logger.debug("*************FIN DE CREATION DE LA FACTURE pdf**************");	
	            	
	        } //fin try
	        
	        catch (JRException e) {
	        	DebuggerLog4J.logger.fatal(e.getMessage());   
	        }//fin catch       
	    }// Fin de la méthode	

		
		
		/**---------------------------------------------------------------------<br>
		*Méthode CreerRepertoireFacture()<br>
		*-----------------------------------------------------------------------<br>
		*Création du répertoire des factures é éditer suivant le modéle<br>
		*RepertoireCourant+/data/factures/annee-NumeroBatch<br>
		* <br>
		* @param annee<br>
		* @param NumeroBatch<br>
		* @return<br>
		*/
		public File CreerRepertoireFacture(String annee,int NumeroBatch){
			File RepertoireFactures= new File("");
			try
			{				
				String cheminDesFactures= fichierDeConfiguration.getCheminFactures();
				String nomNewDossier= annee+"-"+NumeroBatch;
				RepertoireFactures = new File( cheminDesFactures+"//"+nomNewDossier);
				RepertoireFactures.mkdir();
				
			}//fin try
        	catch(Exception e){
                DebuggerLog4J.logger.fatal("Classe : CreerFactures - Méthode : CreerRepertoireFacture => Probleme d'exportation du pdf dans classe JasperRapport méthode CreationRapport");
                DebuggerLog4J.logger.fatal(e.getMessage());
            }//fin catch
        	return RepertoireFactures;   			  
		}
		
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

		public Connection getconnexionr2t() {
			return connexionr2t;
		}
		public void setconnexionr2t(Connection connexionr2t) {
			this.connexionr2t = connexionr2t;
		}
		public String getRepertoireCourant() {
			return RepertoireCourant;
		}
		public void setRepertoireCourant(String repertoireCourant) {
			RepertoireCourant = repertoireCourant;
		}
		public ResultSet getRSDonneesFactures() {
			return RSDonneesFactures;
		}
		public void setRSDonneesFactures(ResultSet donneesFactures) {
			RSDonneesFactures = donneesFactures;
		}
		public Integer getNumeroDuBatch() {
			return NumeroDuBatch;
		}
		public void setNumeroDuBatch(Integer numeroDuBatch) {
			NumeroDuBatch = numeroDuBatch;
		}
		public String getAnneeBatch() {
			return AnneeBatch;
		}
		public void setAnneeBatch(String anneeBatch) {
			AnneeBatch = anneeBatch;
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

		public String getPathRapportModele() {
			return pathRapportModele;
		}

		public void setPathRapportModele(String pathRapportModele) {
			this.pathRapportModele = pathRapportModele;
		}
		
		
		
		
		// class pour l'impression des factures 
		public void CreerFichierDesFactures(int numeroBatch)
		{
			RequestFacture reqFacture = new RequestFacture();			
			Vector listDesFacture= reqFacture.getListeDesfacture(numeroBatch);
			
			Vector listeDesFacturesAConcatiner = new Vector();
			if (listDesFacture.size()>0)
			{
				//
				Facture facture=null;
				//concatenation des factures PDF dans un seul fichier PDF
				for (int i = 0; i < listDesFacture.size(); i++) 
				{
					facture = (Facture)listDesFacture.elementAt(i);
					String cheminFacture=fichierDeConfiguration.getCheminFactures()+facture.getNomDossier()+"/"+facture.getNumeroFacture()+".pdf"; 
					cheminFacture=ReglerCheminSelonOs.reglerCheminSelonOS(cheminFacture);				
					DebuggerLog4J.logger.debug(cheminFacture);
					listeDesFacturesAConcatiner.addElement(cheminFacture);
				}
			String cheminNewFile=fichierDeConfiguration.getCheminFactures()+facture.getNomDossier()+"/BatchNumero"+numeroBatch+".pdf";
			//cheminNewFile=ReglerCheminSelonOs.reglerCheminSelonOS(cheminNewFile);
			DebuggerLog4J.logger.debug(cheminNewFile);		
			ConcatenationFile cf = new ConcatenationFile();		
			cf.concat(listeDesFacturesAConcatiner, cheminNewFile);			
		 }
		}
		
		
		
		
		
	}// Fin de la classe
