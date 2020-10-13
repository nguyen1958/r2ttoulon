package fr.analogon.r2t.facturation;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import fr.analogon.r2t.Utilitaire.ReglerCheminSelonOs;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.request.RequestParametres;
/**	-------------------------------------------------------------------<br>
*			GENERE TOUS LES ROLES DE FACTURATION <br>
*
*@param MontypeDeRole : le type de role a editer <br>
*RA = 	RoleAnnulation <br>
*		Exemple Appel : CreerRoleFacturation("RA",1111,"","","TAV","","");<br>
*		On fait un role d'annulation pour un type de taxe <br>
*		édition des facture s triees par types de taxe, par numero de facture (non utilise)<br>
*		On edite les factures qui sont ANNULEE et non editees (champ annulationEditee a false)<br>
*		Lorsqu'un facture est edite, on passe le champ annulationEditee a true => la facture ne <br>
*		sera plus éditee dans ce type de rapport<br>
*		tri par :<br>
*			type de taxe<br>
*			annee<br>
*		Le champ facture.etat reste 'ANNULEE'; c'est l'application qui gere ce champ<br>
*		Le numero de batch qui est le même que celui de la facturation sert à nommer le nom du role edite<br>
*		Exemple nom rapport : RA_1168.pdf<br>
*
*RAR : 	RoleCertificatAnnulationRecette <br>
*		idem role annulation sauf que l'on edite toutes les factures annulees sans tenir compte <br>
*		du fait qu'elle soient deja editees<br>
*		Exemple Appel : CreerRoleFacturation("RAR",1111,"","","TAV","","");<br>
*		Le numero de batch qui est le même que celui de la facturation sert à nommer le nom du role edite<br>
*		Exemple nom rapport : RAR_1168.pdf<br>
*
*RCAR : RoleChgtAdresseRedevable <br>
*		Ce role edite tous les redevables qui ont <br>
*			un changement d'adresse en cours : le champ changementAdresse = true +<br>
*			solde de la facture  !=0   <br>
*			Dont il existe au moins une facture en cours VALIDE<br>
*			pour toutes les annees d'exercice<br>		
*		Donc role a faire avant chaque traitement de facturation pour les facture validée<br>
*		Les changements d’adresse d’un redevable qui n’ont aucune facture en cours ne sont <br>
*		pas affichés par contre le changement d’adresse et validé.<br>
*		Lorsque ce role est edite, on passe le champ changementAdresse a false, il ne sera<br>
*		plus edite dans les prochains roles<br>
*		Exemple : CreerRoleFacturation("RCAR",1106,"","","","","");<br>
*		Le numero de batch qui est le même que celui de la facturation sert à nommer le nom du role edite<br>
*		Exemple nom rapport : RCAR_1168.pdf<br>
*
*
*REJR : RoleEtatJustificatifRecette <br>
*		Exemple Appel : CreerRoleFacturation("REJR",1106,"","","","","");<br>
*		On fait un role d'annulation pour un batch de facturation courant, pour et par tous les types de taxe<br>
*		On trie par taxes, par numeros de titre<br>
*		On passe a la page a chaque taxe<br>
*		Le numero de batch sert à nommer le nom du role edite<br>
*		Exemple nom rapport : REJR_1169.pdf<br>
*
*RF : 	RoleFacturation<br>		
*		On fait un role d'annulation pour le batch, pour et par tous les types de taxe<br>
*		Un role pour toutes les taxes changement de page à chaque changement de taxe<br>
*		Si pas de facture pour une taxe pas de page<br>
*		Tri par : numero de titre<br>
*		Exemple Appel : CreerRoleFacturation("RF",1106,"","","","","");<br>
*		Exemple nom rapport : RF_1168.pdf<br>
* A voir : Pour mention j'ai mis $F{facture_etat}.equals("VALIDE")?"":"X", c'est à dire 
*          que je regarde l'état de la facture. Est-ce ça ?
*
*RSF : 	RoleSuiviFacturation avec parametre1 supplementaire : <br>
*		Tri par : numero de titre<br>
*			Nom du demandeur exemple :Pascal ZACCHELLO  <br>
*			Type de taxe								<br>
*           Date debut de periode	Format 22/10/2008	<br>
*           Date fin de periode							<br>
*           exemple : CreerRoleFacturation("RSF",0,"Pascal ZACCHELLO","","DDV","23/10/2007","22/12/2009","");<br>
*  			Exemple nom rapport : RSF_DDV_04-11-2008.pdf
* 
* RCO :	RoleCommuniqueOuvrage
* 		Ce role donne les ouvrages dont article.etat = "FacturerAControler" 
* 		pour une date limite (la date du jour) passee en parametre ou
*       reclamation.etat = "ENCOURS"
*       Pour un quartier
*      	Exemple Appel : CreerRoleFacturation("RCO",1325,"","","","","22/12/2009","Bacalan");<br>
*		Exemple nom rapport : RCO_1325.pdf<br>
*		Le numero de batch sert à nommer le nom du role edite<br>
* 
* REGLES
* Etat issue du batchs de la facturation : 4 états Role d’annulation, Certificat d’annulation de recette, 
* Role facturation, Justificatif de recette 
* l etat Rôle suivi de facturation : Cet etat est produit a la demande à partir d un ecran permettant 
* de saisie la periode et le type de taxe.
* l etat Changement Adresse redevable : Cet etat est produit par un batch pour tout type de taxe. 
* Il est accessible par l’historique batch sans que le type de taxe soit renseigné.
* Lorsqu un redevable a fait l’objet d’un changement d’adresse, l edition ce ces modifications 
* est faite sur l etat. Au prochain batch, il ne sera pas re-edite.
* 
* Attention : les repertoires qui contiennent les roles doivent etre crees a l'avance<br>
* 	RoleAnnulation
* 	RoleCertificatAnnulationRecette
* 	RoleChgtAdresseRedevable
* 	RoleCommuniqueOuvrage
* 	RoleEtatJustificatifRecette
* 	RoleFacturation
* 	RoleSuiviFacturation
* 

*
*@throws SQLException : gere les exeptions sql de base de donnees<br>
*<br>
*<br>
*Lancement des factures <br>
*-------------------------------------------------------------------
*/
public class CreerRoleFacturation {
	//Déclaration des variables de la classe
	//-------------------------------------------------------------------		
	private String cheminModele;

	private Connection connexionr2tBordeaux=InitialisationConnexionLectureConfiguration.connexion.connexion;
	private String RepertoireCourant;
	private String NomFichierRole;
	private String LetypeDeRole;
	private String pathRoleJRXML;
	private String pathRapportModele;
	private String pathData;
	private File RepertoireRole;
	//Paramétre facultatif : pour Role Suivi de facturation NomUtilisateur
	private Integer LeBatch;
	private String LeNomUtilisateur;
	private String LAnneeDExercice;
	private String LerepertoireRole;
	//	Pour le rôle suivi de facturation
	private String LetypeTaxe; 
	private String DateDebutPeriode;
	private String DateFinPeriode;
	private String leQuartier;

	FichierDeConfiguration fichierDeConfiguration 
	   =  InitialisationConnexionLectureConfiguration.getFichierDeConfiguration(); 
/**	-------------------------------------------------------------------<br>
*Méthode constructeur de la classe <br>
*@param NumeroBatch : le numéro du batch des factures é éditer <br>
*@throws SQLException : gére les exeptions sql de base de données<br>
*Récupération de la connexion é partir de la méthode Connecteur du package request<br>
*Définition des répertoires nécessaires<br>
*Lancement des factures <br>
*-------------------------------------------------------------------*/
public  CreerRoleFacturation()
{	
}

/**********************************************************************
 * 		lancerCreerRoleFacturation()
 * 
 * Parametres entrant :
 * 	String MontypeDeRole
 *  int NumeroBatch
 *  String anneeExercice
 *  String NomUtilisateur
 *  String TypeTaxe
 *  String LaDateDebutPeriode
 *  String LaDateFinPeriode
 *  String leQuartier
 */
public String  lancerCreerRoleFacturation(String MontypeDeRole,int NumeroBatch, String anneeExercice, String NomUtilisateur, 
				String TypeTaxe, String LaDateDebutPeriode, String LaDateFinPeriode, String leQuartier) throws SQLException {
	
	
		//initialisation des variables
		setLetypeDeRole(MontypeDeRole);
		setLeBatch(NumeroBatch);
		boolean TestRapportexist = true;
		setLAnneeDExercice(anneeExercice);
		String nomFichierJRXML ="";
		RequestParametres requestParametres = new RequestParametres();
		String ville = requestParametres.getVille();
		
		//Pour le role suivi de facturation
		setLeNomUtilisateur(NomUtilisateur);
		setDateDebutPeriode(LaDateDebutPeriode);
		setDateFinPeriode(LaDateFinPeriode);
		//Pour le role annulation
		setLetypeTaxe(TypeTaxe);
		
		//Pour le role communique
		setLeQuartier("%"+leQuartier+"%");
		
    	try{
    		setConnexionr2tBordeaux(connexionr2tBordeaux);
    	}//fin try
    	catch(Exception e){
            DebuggerLog4J.logger.fatal("Classe : CreerRoleFacturation - Méthode : CreerRoleFacturation" +
            		"Probleme de connexion à la base de données");
            DebuggerLog4J.logger.fatal("Vérifier que le serveur de base de données et OK");
            DebuggerLog4J.logger.fatal(e.getMessage());
        }//fin catch
    	
    	//Pour la définition du repertoire en fonction du type de role
    	//Les repertoires doivent etre crees
   
    	if (ville.equalsIgnoreCase("toulon") &&   LetypeDeRole !="RCOD" && LetypeDeRole !="RCO" 
    		&&   LetypeDeRole !="RF" && LetypeDeRole !="REJR" )
    		nomFichierJRXML = "_r2tToulonV1.jrxml";
    	else
    		nomFichierJRXML = "_r2tBordeauxV1.jrxml";
    	
    	
    	
    	
    	
    		if( LetypeDeRole == "RA" ) setLerepertoireRole("RoleAnnulation");							
			else if( LetypeDeRole == "RAR") setLerepertoireRole("RoleCertificatAnnulationRecette");
			else if( LetypeDeRole ==  "RCAR") setLerepertoireRole("RoleChgtAdresseRedevable");			
			else if( LetypeDeRole == "RF") setLerepertoireRole("RoleFacturation");					
			else if( LetypeDeRole == "REJR") setLerepertoireRole("RoleEtatJustificatifRecette");
			else if( LetypeDeRole == "RSF")	setLerepertoireRole("RoleSuiviFacturation");
			else if( LetypeDeRole == "RCO")	setLerepertoireRole("RoleCommuniqueOuvrage");
			else if( LetypeDeRole == "Rem")	setLerepertoireRole("Remboursement");
			else if( LetypeDeRole == "RRem")	setLerepertoireRole("RoleRemboursement");
    	
		
		//On teste l'existence du rapport
		if(LetypeDeRole == "RA" || LetypeDeRole == "RAR" || LetypeDeRole ==  "RCAR" || 
				LetypeDeRole ==  "RF" || LetypeDeRole ==  "RSF"|| 
				LetypeDeRole ==  "REJR" || LetypeDeRole ==  "RCO" )
			//On regarde si il y a des element à afficher dans le rapport
			// On n edite pas rapport vide
			TestRapportexist = testExistenceRapport();
	
		if(TestRapportexist)
		{    	
			setPathRoleJRXML (fichierDeConfiguration.getCheminRoleJRXML());
			setPathRapportModele(fichierDeConfiguration.getCheminRapportModele());
			setPathData(fichierDeConfiguration.getCheminData());
		
			
				String urlPathRapportModele =getPathRoleJRXML()+LerepertoireRole+"/";
				urlPathRapportModele = ReglerCheminSelonOs.reglerCheminSelonOS(urlPathRapportModele);
				setPathRapportModele(urlPathRapportModele);
				DebuggerLog4J.logger.debug("ville="+ville);
				DebuggerLog4J.logger.debug("urlPathRapportModele="+urlPathRapportModele);
				String nomCompletduFichierJRXML = LerepertoireRole+nomFichierJRXML;
				String urlPathRoleJRXML =urlPathRapportModele + nomCompletduFichierJRXML;
				DebuggerLog4J.logger.debug("urlPathRoleJRXML="+urlPathRoleJRXML);
				urlPathRoleJRXML = ReglerCheminSelonOs.reglerCheminSelonOS(urlPathRoleJRXML);
			
			
			
			
			///////////////////////////////////
			//////////A MDIFIER SOFIEN///////			
			if  (LetypeDeRole== "RCOD" ||
					LetypeDeRole== "RCO" || 
						LetypeDeRole== "RF" || 
							LetypeDeRole== "REJR" ||
							LetypeDeRole== "RA" ||
							LetypeDeRole== "RAR" ||
								LetypeDeRole== "RSF" ||
								LetypeDeRole== "Rem" ||
								LetypeDeRole== "RRem" ||
								LetypeDeRole== "RCAR" )
			{
				String pathData= fichierDeConfiguration.getCheminData();				
				urlPathRoleJRXML = fichierDeConfiguration.getCheminAbsoluTemplateRapport();				
				
				if (LetypeDeRole== "RCOD")
				{				
					nomFichierJRXML = "RoleCommuniqueDetaille.jrxml";
					urlPathRoleJRXML= urlPathRoleJRXML + "RapportToulon/RoleCommuniqueDetaille/";					
					NomFichierRole = pathData + "RoleCommuniqueDetaille/RCOD_"+NumeroBatch+".pdf";				
				}
				else if (LetypeDeRole== "RCO")
				{					
					nomFichierJRXML = "RoleCommuniqueOuvrage_r2tBordeauxV1.jrxml";
					urlPathRoleJRXML= urlPathRoleJRXML + "RoleCommuniqueOuvrage/";					
					NomFichierRole = pathData + "RoleCommunique/RCO_"+NumeroBatch+".pdf";
				}				
				else if (LetypeDeRole== "REJR")
				{
					
					if (ville.equalsIgnoreCase("toulon"))
					{
						nomFichierJRXML = "RoleEtatJustificatifRecette_r2tToulonV1.jrxml";
						urlPathRoleJRXML= urlPathRoleJRXML + "RapportToulon/RoleEtatJustificatifRecette/";
					}
					else
					{
						
						nomFichierJRXML = "RoleEtatJustificatifRecette_r2tBordeauxV1.jrxml";
						urlPathRoleJRXML= urlPathRoleJRXML + "RoleEtatJustificatifRecette/";				
						
					}
					NomFichierRole = pathData + "RoleEtatJustificatifRecette/REJR_"+NumeroBatch+".pdf";
				}
				else if (LetypeDeRole== "RF")
				{
					if (ville.equalsIgnoreCase("toulon"))
					{
						nomFichierJRXML = "RoleFacturation_r2tToulonV1.jrxml";
						urlPathRoleJRXML= urlPathRoleJRXML + "RapportToulon/RoleFacturation/";
					}
					else
					{
						nomFichierJRXML = "RoleFacturation_r2tBordeauxV1.jrxml";
						urlPathRoleJRXML= urlPathRoleJRXML + "RoleFacturation/";	
					}
					NomFichierRole = pathData + "RoleFacturation/RF_"+NumeroBatch+".pdf";	
				}			
				else if (LetypeDeRole== "RSF")
				{
						nomFichierJRXML = "RoleSuiviFacturation_r2tBordeauxV1.jrxml";
						urlPathRoleJRXML= urlPathRoleJRXML + "RoleSuiviFacturation/";
						NomFichierRole = pathData + "RoleSuiviFacturation/RSF_"+NumeroBatch+".pdf";		
				}
				else if (LetypeDeRole== "RCAR")
				{
						nomFichierJRXML = "RoleChgtAdresseRedevable_r2tBordeauxV1.jrxml";
						urlPathRoleJRXML= urlPathRoleJRXML + "RoleChgtAdresseRedevable/";
						NomFichierRole = pathData + "RoleChgtAdresseRedevable/RCAR_"+NumeroBatch+".pdf";		
				}
				else if (LetypeDeRole== "RA")
				{
						nomFichierJRXML = "RoleAnnulation_r2tBordeauxV1.jrxml";
						urlPathRoleJRXML= urlPathRoleJRXML + "RoleAnnulation/";
						System.out.println("////////////////////////////////////////");
						System.out.println("URL RoleAnnulation ");
						System.out.println(urlPathRoleJRXML);
						System.out.println("////////////////////////////////////////");
						NomFichierRole = pathData + "RoleAnnulation/RA_"+NumeroBatch+".pdf";		
				}
				else if (LetypeDeRole== "RAR")
				{
						nomFichierJRXML = "RoleCertificatAnnulationRecette_r2tBordeauxV1.jrxml";
						urlPathRoleJRXML= urlPathRoleJRXML + "RoleCertificatAnnulationRecette/";
						System.out.println("===========================================");
						System.out.println("URL RoleCertificatAnnulationRecette");
						
						NomFichierRole = pathData + "RoleCertificatAnnulationRecette/RAR_"+NumeroBatch+".pdf";
						System.out.println("===========================================");
				}
				else if (LetypeDeRole== "Rem")
				{
						nomFichierJRXML = "Remboursementr2tBordeauxV1.jrxml";
						urlPathRoleJRXML= urlPathRoleJRXML + "Remboursement/";
						NomFichierRole = pathData + "Remboursement/Rem_"+NumeroBatch+".pdf";		
				}
				else if (LetypeDeRole== "RRem")
				{
						nomFichierJRXML = "RoleRemboursement_r2tBordeauxV1.jrxml";
						urlPathRoleJRXML= urlPathRoleJRXML + "RoleRemboursement/";
						NomFichierRole = pathData + "RoleRemboursement/RRem_"+NumeroBatch+".pdf";		
				}
				
				pathRoleJRXML = urlPathRoleJRXML  + nomFichierJRXML;
			}
				
			DebuggerLog4J.logger.debug("\n\n\nDebut cretion du role "+ getLetypeDeRole() +" ..............");
			DebuggerLog4J.logger.debug("urlPathRoleJRXML ="+ urlPathRoleJRXML);
			DebuggerLog4J.logger.debug("nomFichierJRXML ="+ nomFichierJRXML);			
			CreationRole(NumeroBatch);
			DebuggerLog4J.logger.debug("Fin cretion du role "+ getLetypeDeRole() +"..............[OK]");
    	   	
			//Mise a jour des champs de controle d'édition
			//Changement d'adresse et Annulation
			
			//System.out.println("AVANT MAJApresEditionRole type role="+getLetypeDeRole());
			if( getLetypeDeRole() ==  "RCAR" || getLetypeDeRole() == "RA" )
			{
				MAJApresEditionRole();
			}
		}
		//fin du if
		
    	
    	return(""+TestRapportexist);    	
	}// Fin du constructeur

	/**-----------------------------------------------------------------------
	*Méthode RecupererDonneesFacture()
	*-----------------------------------------------------------------------
	*Récupére les donnees necessaires en base dans la table factures
	*Pour la facture
	*NumeroFacture, NumeroChrono, numeroBatch, TypeTaxe, NumeroClient, NumeroTitre
	*Pour le nom du répertoire annee		 
	* @param numeroBatch
	* @param connexionr2tBordeaux
	* @throws SQLException
	*/

/**	testExistenceRapport
 * 
 * Teste dans la base de donnee si il existe des champs pour le role
 * Si il n'existe pas de champ on d'edite pas le rapport
 * @return
 * @throws SQLException
 */
public boolean testExistenceRapport() throws SQLException
{	
	Statement st1 = null;
	ResultSet RS = null;
	String LaRequete = null;
	
	//Role changement d'adresse redevable, on cherche si il y a des changement d'adresse
	//pour des redevable qui ont une facture en cours
	//dont le solde != 0 , même ceux qui n'ont pas de facture
	if(getLetypeDeRole()=="RCAR")
		LaRequete = "SELECT DISTINCT redevable.numRedevable"+
	    		" FROM redevable,facture,batchtraitement"+
	    	    " WHERE facture.idClient = redevable.numRedevable"+
	    	    " AND redevable.changementAdresse = 'true'"+
	    	    " AND facture.etat NOT LIKE 'ANNULEE'"+
	    	    " AND facture.etat NOT LIKE 'preRefacturation'"+
                " AND facture.solde != 0 "+
	    	    " AND facture.idBatchTraitement = batchtraitement.numeroBatchTraitement"+  
                " AND batchtraitement.valide='true'"+
	    		" GROUP BY redevable.numRedevable";
	
		//Role annulation, on cherche si il y a des factures annulees et non editees
		else if(getLetypeDeRole()=="RA" )
          LaRequete = "SELECT DISTINCT facture.numeroFacture " +
                " FROM facture, batchtraitement, imputation" +
                " WHERE " +
				" imputation.libelle = facture.typeTaxe " +
				" AND imputation.anneeExercice = facture.anneeEx " +	
				" AND batchtraitement.numeroBatchTraitement = facture.idBatchTraitement " +
				" AND facture.etat = 'ANNULEE' " +
				" AND facture.typeTaxe = '" +  getLetypeTaxe() + "'" + 
				" AND facture.annulationEditee = 'false'";
        
   


    	//Certificat annulation de recette, on cherche si il y a des factures annulees 
		else if(getLetypeDeRole()=="RAR" )

          LaRequete = "SELECT DISTINCT facture.numeroFacture " +
              " FROM facture, batchtraitement, imputation" +
                " WHERE " +
				" imputation.libelle = facture.typeTaxe " +
				" AND imputation.anneeExercice = facture.anneeEx " +	
				" AND batchtraitement.numeroBatchTraitement = facture.idBatchTraitement " +
				" AND facture.etat = 'ANNULEE' " +
				" AND facture.typeTaxe = '" +  getLetypeTaxe()  +"'"+ 
				" AND facture.annulationEditee = 'false'";

	
	//Role de facturation, on cherche si il y a des factures pour un batch
	else if(getLetypeDeRole()=="RF" )
		LaRequete = "SELECT DISTINCT facture.numeroFacture FROM facture,batchtraitement"+
			" WHERE facture.idBatchTraitement = '"+ getLeBatch()+"'" + 
			" AND batchtraitement.numeroBatchTraitement = facture.idBatchTraitement" +
			" AND facture.etat NOT LIKE 'preRefacturation'";
	
	//Role suivi de facturation, on cherche si il y a des factures pour une taxe dans une periode
	else if(getLetypeDeRole()=="RSF" )
		LaRequete = "SELECT DISTINCT facture.numeroFacture FROM facture,imputation"+
			" WHERE facture.typeTaxe = '"+ getLetypeTaxe()+"'" +
			" AND imputation.libelle = facture.typeTaxe" +
			" AND facture.etat NOT LIKE 'preRefacturation'"+
			" AND STR_TO_DATE(facture.dateCreation, '%d/%m/%Y') >= STR_TO_DATE('"+getDateDebutPeriode()+"', '%d/%m/%Y')"+ 
			" AND STR_TO_DATE(facture.dateCreation, '%d/%m/%Y') <= STR_TO_DATE('"+getDateFinPeriode()+"', '%d/%m/%Y')";

	//Etat justificatif de recette, on cherche si il y a des factures VALIDE pour le batch
	else if(getLetypeDeRole()=="REJR" )
		LaRequete = "SELECT DISTINCT facture.numeroFacture " +
			" FROM facture, batchtraitement,imputation" +
			" WHERE " +
		    " imputation.libelle = facture.typeTaxe" +
		    " AND batchtraitement.numeroBatchTraitement = facture.idBatchTraitement" +
			" AND facture.idBatchTraitement = '"+ getLeBatch()+"'" + 
		    " AND facture.etat NOT LIKE 'preRefacturation'";
	
	//Role communique ouvrage, on cherche si il y a des factures en cours de modif
	//pour un quartier dans une période donnee
	else if(getLetypeDeRole()=="RCO" )
		/*
		LaRequete = "SELECT DISTINCT( article.id_article)  " +
			" FROM article,redevable,emplacementgeneral,elementfacturation, rue" +
			" WHERE " +
		    " article.etat = 'FacturerAControler'" +
		    " AND article.codebareme = bareme.code" +
		    " AND redevable.numRedevable = emplacementgeneral.numRedevable" +
		    " AND emplacementgeneral.codeVoie = rue.codeVoie" +
		    " AND article.id_elementfacturation = elementfacturation.numero" +
		    " AND article.dateProchainControl NOT LIKE  ''" +
		    " AND elementfacturation.numeroEmplacement = emplacementgeneral.numero" +
		    " AND rue.nomQuartier LIKE "+  getLeQuartier() +
		    " AND STR_TO_DATE(article.dateProchainControl,'%d/%m/%Y') <= STR_TO_DATE('" +getDateFinPeriode()+ "','%d/%m/%Y')" +
			" UNION" +
			" (SELECT  DISTINCT(reclamation_article.idArticle)" +
			" FROM reclamation,reclamation_article" +
			" WHERE " +
			" AND      article.id_elementfacturation = elementfacturation.numero"+
			" AND redevable.numRedevable = emplacementgeneral.numRedevable"+
			" AND article.codebareme = bareme.code"+
			" AND elementfacturation.numeroEmplacement = emplacementgeneral.numero"+
			" AND emplacementgeneral.codeVoie = rue.codeVoie"+
			" AND article.id_article = reclamation_article.idArticle"+
			" AND reclamation.idReclamation = reclamation_article.idReclamation"+
			" AND reclamation.etat = 'ENCOURS'"+
			" AND rue.nomQuartier LIKE "+ getLeQuartier();
	*:		LaRequete = "SELECT DISTINCT( article.id_article)  " +
			" FROM article,redevable,emplacementgeneral,elementfacturation, rue" +
			" WHERE " +
		    " article.etat = 'FacturerAControler'" +		    
		    " AND redevable.numRedevable = emplacementgeneral.numRedevable" +
		    " AND emplacementgeneral.codeVoie = rue.codeVoie" +
		    " AND article.id_elementfacturation = elementfacturation.numero" +
		    " AND article.dateProchainControl NOT LIKE  ''" +
		    " AND elementfacturation.numeroEmplacement = emplacementgeneral.numero" +
		    " AND rue.nomQuartier LIKE '"+  getLeQuartier() + "'" +
		    " AND STR_TO_DATE(article.dateProchainControl,'%d/%m/%Y') <= STR_TO_DATE('" +getDateFinPeriode()+ "','%d/%m/%Y')" +
		    
		    " UNION " +		
			" SELECT  DISTINCT(reclamation_article.idArticle) " +
			" FROM reclamation,reclamation_article,article,redevable,emplacementgeneral,elementfacturation, rue " +
			" WHERE  reclamation.idReclamation = reclamation_article.idReclamation " +
			" AND reclamation.etat = 'ENCOURS' " +
			" And reclamation_article.idArticle= article.id_article" +
			" AND redevable.numRedevable = emplacementgeneral.numRedevable" +
			" AND emplacementgeneral.codeVoie = rue.codeVoie" +
			" AND article.id_elementfacturation = elementfacturation.numero" +
			" AND article.dateProchainControl NOT LIKE  ''" +
			" AND elementfacturation.numeroEmplacement = emplacementgeneral.numero" +
			" AND rue.nomQuartier LIKE '"+ getLeQuartier()+"'" ;
		*/
		LaRequete = "SELECT DISTINCT( article.id_article)  " +
		" FROM article,redevable,emplacementgeneral,elementfacturation, rue" +
		" WHERE " +
	    " article.etat = 'FacturerAControler'" +		    
	    " AND redevable.numRedevable = emplacementgeneral.numRedevable" +
	    " AND emplacementgeneral.codeVoie = rue.codeVoie" +
	    " AND article.id_elementfacturation = elementfacturation.numero" +	    
	    " AND elementfacturation.numeroEmplacement = emplacementgeneral.numero" +
	    " AND rue.nomQuartier LIKE '"+  getLeQuartier() + "'" +
	    " AND STR_TO_DATE(article.dateProchainControl,'%d/%m/%Y') <= STR_TO_DATE('" +getDateFinPeriode()+ "','%d/%m/%Y')" +
	    
	    " UNION " +		
		" SELECT  DISTINCT(reclamation_article.idArticle) " +
		" FROM reclamation,reclamation_article,article,redevable,emplacementgeneral,elementfacturation, rue " +
		" WHERE  reclamation.idReclamation = reclamation_article.idReclamation " +
		" AND reclamation.etat = 'ENCOURS' " +
		" And reclamation_article.idArticle= article.id_article" +
		" AND redevable.numRedevable = emplacementgeneral.numRedevable" +
		" AND emplacementgeneral.codeVoie = rue.codeVoie" +
		" AND article.id_elementfacturation = elementfacturation.numero" +
		" AND elementfacturation.numeroEmplacement = emplacementgeneral.numero" +
		" AND rue.nomQuartier LIKE '"+ getLeQuartier()+"'" ;

	
	DebuggerLog4J.logger.debug(LaRequete);
	//Recherche
	try{
		st1 = connexionr2tBordeaux.createStatement();
		RS = st1.executeQuery(LaRequete);
		
	}//fin try
	catch(Exception e){
        DebuggerLog4J.logger.fatal("Classe : CreerRoleFacturation - Méthode : testExistenceRapport =>" +
        		"Probleme de récupération des données");
        DebuggerLog4J.logger.fatal(e.getMessage());
    }//fin catch
	
	//Test si pas de resultat
	if ( RS.next())
	{		
		RS.close();
		st1.close();
		return true;  //pas de changement d'adresse
		}
	else{
		RS.close();
		st1.close();
		return false;}		
}
/**		MAJApresEditionRole()
 * Mise a jour des champ aprés édition des roles
 * 
 * Pour le role changement d'adresse
 * on passe a false les changements d'adresse des redevables edites dans le role pour le batch
 * 
 * Pour le role annulation
 * on passe a tue le champ annulationEditee
 * @throws SQLException
 */
public void MAJApresEditionRole() throws SQLException{
	Statement st1 = null;
	String LaRequete = null;
	//Role changement d'adresse redevable
	//On valide les changement d'adresse pour les redevable qui ont une facture en cours
	//dont le solde != 0 , même ceux qui n'ont pas de facture
	
	if(getLetypeDeRole()=="RCAR")
		LaRequete = "UPDATE redevable,facture,batchtraitement SET redevable.changementAdresse='false'"+
	    " WHERE facture.idClient = redevable.numRedevable"+
	    " AND redevable.changementAdresse = 'true'"+
	    " AND facture.etat<> 'ANNULEE'"+
	    " AND facture.etat<>'preRefacturation'"+
	    " AND facture.solde != 0 "+
	    " AND facture.idBatchTraitement = batchtraitement.numeroBatchTraitement"+  
	    " AND batchtraitement.valide='true'";
	//Role annulation
	else if(getLetypeDeRole()=="RA")
		LaRequete = "UPDATE facture,imputation,batchtraitement SET facture.annulationEditee='true'"+
                " WHERE " +				
                " imputation.libelle = facture.typeTaxe " +
				" AND imputation.anneeExercice = facture.anneeEx " +	
				" AND batchtraitement.numeroBatchTraitement = facture.idBatchTraitement " +
				" AND facture.etat = 'ANNULEE' " +
				" AND facture.typeTaxe = '" +  getLetypeTaxe() + "'"+ 
				" AND facture.annulationEditee = 'false' ";
		
	//	Mise a jour
	try{
		st1 = connexionr2tBordeaux.createStatement();
		System.out.println("\n\nRequest="+LaRequete);
		st1.executeUpdate(LaRequete);
		
	}//fin try
	catch(Exception e){
		DebuggerLog4J.logger.fatal("Classe : CreerFactures - Méthode : MAJApresEditionRole" +
        		"Probleme de mise a jour des données");
        DebuggerLog4J.logger.fatal(e.getMessage());
    }//fin catch
	finally {
	st1.close();
	}
}


	/**-------------------------------------------------------------------<br>
	*Impression d'un rapport											  <br>
	*Cette méthode : <br>
	*	charge le rapport jrxml<br>
	*	prépare les paramétres é passer au rapport<br>
	*	Compile le rapport<br>
	*	lie les paramétres au rapport<br>
	*	créer la facture au format pdf<br>
	*
    *-------------------------------------------------------------------
    *Les parametres issue des tables<br>
    **/
	public void CreationRole(int numeroBatch)
	{
		//Pour le rôle suivi de facturation
		SimpleDateFormat dateStandard = new SimpleDateFormat("dd-MM-yyyy");
		Date maDateAvecFormat=new Date();
		
		try {				
            // - Chargement du rapport
        	//Le fichier jrxml est obtenu par l'éditeur de rapport ireport
        	//L'emplacement du fichier est important
			System.out.println("pathRoleJRXML=>"+pathRoleJRXML);
			JasperDesign jasperDesign = JRXmlLoader.load(pathRoleJRXML);
			
			//- compilation du rapport
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			
			//DebuggerLog4J.logger.debug("numéro facture"+UneFacture.getNumeroFacture());
			//- Parametres a envoyer au rapport
			//__________________________________
			Map parameters = new HashMap();
            	
			//Pour tous les rapports
			parameters.put("par_CheminModele", getPathRapportModele());	            	
			parameters.put("NumeroBatch", getLeBatch());
			parameters.put("AnneeExercice", getLAnneeDExercice()); 
			
			//Si c'est le role RoleSuiviFacturation, paramètre supplementaire
			if(getLetypeDeRole() == "RSF"){
				//Le nom de l'utilisateur
				parameters.put("UtilisateurDemandeur", getLeNomUtilisateur()); 
				//La date du jour pour le nom du rapport
			    parameters.put("DateRole", dateStandard.format(maDateAvecFormat));
			    //Le type de taxe demande
			    parameters.put("TypeTaxe", getLetypeTaxe());
			    //La période demande
			    parameters.put("DateDebutPeriode", getDateDebutPeriode());
			    parameters.put("DateFinPeriode", getDateFinPeriode());
			}

			
			if(getLetypeDeRole() == "RCO")
			{
				//Le nom de l'utilisateur
			    parameters.put("dateLimite", getDateFinPeriode());
			    parameters.put("nomQuartier", getLeQuartier());			    
			}			
			
			//Si c'est le role RoleAnnulation, paramètre supplementaire
			if(getLetypeDeRole() == "RA" || getLetypeDeRole() == "RAR")
			{
				System.out.println("pathRoleJRXML=>"+pathRoleJRXML);
			    //Le type de taxe demande
			    parameters.put("LeTypedeTaxe", getLetypeTaxe());
			    
			}					
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connexionr2tBordeaux);
			 
            try
            {

	            //Pour sofien
	            if(LetypeDeRole != "RCOD") 
	            {          
	            	NomFichierRole = getPathData()+"/"+LerepertoireRole+"/"+LetypeDeRole+"_"+LeBatch+".pdf";
	            	NomFichierRole= ReglerCheminSelonOs.reglerCheminSelonOS(NomFichierRole);
	            }
	            
	            
	            JasperExportManager.exportReportToPdfFile(jasperPrint,NomFichierRole);
            }//fin try
            catch(Exception e){
            		DebuggerLog4J.logger.fatal("Classe : CreerRoleFacturation - Méthode : CreationRole => Probleme d'exportation du pdf dans classe JasperRapport méthode CreationRapport");
                    DebuggerLog4J.logger.fatal("Classe : CreerRoleFacturation - Méthode : CreationRole => vérifier que le pdf n'est pas ouvert");
                    DebuggerLog4J.logger.fatal("Classe : CreerRoleFacturation - Méthode : CreationRole => vérifier que le chemin dans le fichier application.properties cheminDataWind");
                    DebuggerLog4J.logger.fatal("Classe : CreerRoleFacturation - Méthode : CreationRole => vérifier que le chemin dans le répertoire contenant le fichier existe");
                    DebuggerLog4J.logger.fatal(e.getMessage());
                    
                }//fin catch	            	
            	//DebuggerLog4J.logger.debug("*************FIN DE CREATION DE LA FACTURE pdf**************");				
        } //fin try
        
        catch (JRException e) {
        	DebuggerLog4J.logger.fatal(e.getMessage());     
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

	public File getRepertoireRole() {
		return RepertoireRole;
	}
	public void setRepertoireRole(File repertoireRole) {
		RepertoireRole = repertoireRole;
	}
	public String getLetypeDeRole() {
		return LetypeDeRole;
	}

	public void setLetypeDeRole(String letypeDeRole) {
		LetypeDeRole = letypeDeRole;
	}

	public String getPathRapportModele() {
		return pathRapportModele;
	}

	public void setPathRapportModele(String pathRapportModele) {
		this.pathRapportModele = pathRapportModele;
	}
	public Integer getLeBatch() {
		return LeBatch;
	}

	public void setLeBatch(Integer leBatch) {
		LeBatch = leBatch;
	}
	public String getPathRoleJRXML() {
		return pathRoleJRXML;
	}

	public void setPathRoleJRXML(String pathRoleJRXML) {
		this.pathRoleJRXML = pathRoleJRXML;
	}
	
	public String getPathData() {
		return pathData;
	}

	public void setPathData(String pathData) {
		this.pathData = pathData;
	}
	public String getLerepertoireRole() {
		return LerepertoireRole;
	}

	public void setLerepertoireRole(String lerepertoireRole) {
		LerepertoireRole = lerepertoireRole;
	}
	public String getLetypeTaxe() {
		return LetypeTaxe;
	}

	public void setLetypeTaxe(String letypeTaxe) {
		LetypeTaxe = letypeTaxe;
	}
	public String getDateDebutPeriode() {
		return DateDebutPeriode;
	}

	public void setDateDebutPeriode(String dateDebutPeriode) {
		DateDebutPeriode = dateDebutPeriode;
	}

	public String getDateFinPeriode() {
		return DateFinPeriode;
	}

	public void setDateFinPeriode(String dateFinPeriode) {
		DateFinPeriode = dateFinPeriode;
	}
	public String getLAnneeDExercice() {
		return LAnneeDExercice;
	}

	public void setLAnneeDExercice(String anneeDExercice) {
		LAnneeDExercice = anneeDExercice;
	}
	
	public String getLeNomUtilisateur() {
		return LeNomUtilisateur;
	}

	public void setLeNomUtilisateur(String leNomUtilisateur) {
		LeNomUtilisateur = leNomUtilisateur;
	}
	public String getLeQuartier() {
		return leQuartier;
	}

	public void setLeQuartier(String leQuartier) {
		this.leQuartier = leQuartier;
	}
}// Fin de la classe
