package fr.analogon.r2t.request;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.BatchRelance;


public class RequestImpayee  extends Request
{
	RequestFacture requestFacture = new RequestFacture();
	
	public static String chaineVide(int nombrEspace) {
		String res ="";
		for (int i = 1; i < nombrEspace; i++) {
			res =res +" ";
 		}
		return res;
	}
	
	public Vector getFichierImpayee(int numeroBatchRelance) 
	{	
			  Vector retour = new Vector();			  		
			  RequestRelance requestRelance  = new RequestRelance();			  
			  BatchRelance batchRelance =  requestRelance.getBatchRealRelance(numeroBatchRelance);
			  
			  String periode = batchRelance.getPeriode();
			  String type = batchRelance.getType();
			  
			  
			  String typeFacturation ="";
			  if (type.equalsIgnoreCase("a"))
				  typeFacturation = "Avance-Annuelle";
			  else if (type.equalsIgnoreCase("t"))
				  typeFacturation = "Avance-Trimestrielle";
			  else if (type.equalsIgnoreCase("m"))
				  typeFacturation = "Avance-Mensuelle";
			  
			  String annee = batchRelance.getAnnee();
			  String debutPeriode="";
			  String finPeriode="";
			  
			  if( type.equalsIgnoreCase("a")) 
			  {
				  debutPeriode = "01/01/"+ annee ;
				  finPeriode = "31/12/" + annee;
			  }
			  else  if( type.equalsIgnoreCase("t"))
			  {
				  if(periode.equalsIgnoreCase("1")) {
					   debutPeriode = "01/01/"+ annee ;
					   finPeriode = "31/03/" + annee;
				  }
				  else if(periode.equalsIgnoreCase("2")) 
				  {
					   debutPeriode = "01/04/"+ annee ;
					   finPeriode = "30/06/" + annee;
				  }
				  else if(periode.equalsIgnoreCase("3")) {
					   debutPeriode = "01/07/"+ annee ;
					   finPeriode = "30/09/" + annee;
				  }else if(periode.equalsIgnoreCase("4")) {
					   debutPeriode = "01/10/"+ annee ;
					   finPeriode = "31/12/" + annee;
				  }
			  }
			  else  if( type.equalsIgnoreCase("m"))
			  {
				  if (periode.length()<2 ) periode = "0"+periode;
				  debutPeriode = "01/" + periode +"/" +annee;
				  System.out.println("debutPeriode="+debutPeriode);
				  finPeriode = GestionDate.getDernierJOurDuMois(debutPeriode);				  
			  }
			  
			  
			  
			  DebuggerLog4J.logger.debug("debutPeriode="+debutPeriode);
			  DebuggerLog4J.logger.debug("finPeriode="+finPeriode);
			  Statement instruction;
			  
			  try {
					instruction = con.connexion.createStatement();
				  try {
					  

					  				  
						String requete = "  SELECT  DISTINCT(facture.numeroFacture),redevable.numRue,redevable.adresse1,redevable.adresse2,redevable.adresse3," +
										 " emplacementgeneral.numeroEmplacementPersonalise, emplacementgeneral.codeSecteur," +
										 " facture.typeTaxe, " +
								         "	facture.solde,	facture.dateCreation, imputation.cycleFacturation," +
								         " 	redevable.civilite, redevable.nomJF, redevable.responsable, redevable.nomRedevable,	redevable.prenom," +
								         "  CONCAT(redevable.numRue, \" \", redevable.adresse1 ,\" \", redevable.adresse2 ,\" \"  , redevable.adresse3) as adresseRedevable ," +
								         "  redevable.codePostal, redevable.ville "  +
								         
								         "	FROM lignefacture , elementfacturation ,emplacementgeneral, article," +
								         "  facture ,imputation, redevable " +
								         
								         "	WHERE facture.numeroFacture = lignefacture.idFacture " +
								         "  AND lignefacture.idArticle = article.id_article " +
								         "  AND  article.id_elementfacturation = elementfacturation.numero" +
								         "  AND elementfacturation.numeroEmplacement = emplacementgeneral.numero" +
								         "  AND facture.anneeEx = imputation.anneeExercice" +
								         " 	AND  imputation.libelle = facture.typeTaxe " +
								         "  AND facture.envoyerALaTresorie ='false'"+
								         "  AND facture.solde != 0" +
								         "  AND facture.idClient = redevable.numRedevable ";
					 
					    	
						if (typeFacturation.equalsIgnoreCase("Avance-Annuelle"))
						{
				    		requete = requete+" AND (imputation.cycleFacturation = 'Avance-Annuelle'" +
				    						  " OR (imputation.libelle ='ETALAGE' OR imputation.libelle ='TLPE') ) ";
						}
						else if (typeFacturation.equalsIgnoreCase("Avance-Trimestrielle"))
				    	{   
				    		requete = requete+" AND (imputation.cycleFacturation = 'Avance-Trimestrielle' " 
		    				 + " OR imputation.cycleFacturation = 'Avance-Trimestrielle' " 
		    				 + " OR (imputation.cycleFacturation = 'Normal' && imputation.libelle !='ETALAGE' && imputation.libelle !='TLPE' ) )" ;
				    	}
						else if (typeFacturation.equalsIgnoreCase("Avance-Mensuelle"))
				    	{
				    		requete = requete+" AND imputation.cycleFacturation = 'Avance-Mensuelle' " ;
				    	}
				    	
							requete = requete +"  AND facture.etat !='ANNULEE' AND facture.etat !='preRefacturation' " +
					    	" AND facture.etat !='preFacturation'"+					    
					    	" AND STR_TO_DATE(facture.dateCreation,'%d/%m/%Y') <= STR_TO_DATE('"+finPeriode+"','%d/%m/%Y') " +
					    	" AND STR_TO_DATE(facture.dateCreation,'%d/%m/%Y') >= STR_TO_DATE('"+debutPeriode+"','%d/%m/%Y') " +
					    						    	
					    	" ORDER BY " +
					    	"          typeTaxe ASC, " +
					    	"	       CAST( codeSecteur as UNSIGNED ) ASC , " +
					    	"          CAST( numeroEmplacementPersonalise as UNSIGNED ) ASC , " +
					    	"          facture.numeroFacture ASC" ;
						 
					 	debug.logger.debug("get impaye: " +requete);
						ResultSet resultat = instruction.executeQuery(requete) ;		
						
						int nombreFactureImpaye=0;
						boolean premierLigne= true;
						String entetFichier= "";
						while(resultat.next()) 
						{							
							String numeroFacture = resultat.getString("numeroFacture");							
							retour.addElement(resultat.getString("typeTaxe"));
							retour.addElement(resultat.getString("emplacementgeneral.codeSecteur"));
							retour.addElement(resultat.getString("emplacementgeneral.numeroEmplacementPersonalise"));
							retour.addElement(numeroFacture);
							retour.addElement(resultat.getString("solde"));
							retour.addElement(resultat.getString("dateCreation"));
							retour.addElement(resultat.getString("cycleFacturation"));
							retour.addElement(resultat.getString("civilite"));
							retour.addElement(resultat.getString("responsable"));	
							retour.addElement(resultat.getString("nomRedevable"));
							retour.addElement(resultat.getString("prenom"));
							retour.addElement(resultat.getString("nomJF"));
							retour.addElement(resultat.getString("numRue"));
							retour.addElement(resultat.getString("adresse1"));
							retour.addElement(resultat.getString("adresse2"));
							retour.addElement(resultat.getString("adresse3"));
							retour.addElement(resultat.getString("codePostal"));
							retour.addElement(resultat.getString("ville"));
							
							retour.addElement("FIN_INFOS_FACTURE");
							
							nombreFactureImpaye++;
						}
						DebuggerLog4J.logger.debug("nombreFactureImpaye="+nombreFactureImpaye);
						requestRelance.majBatchRelance(numeroBatchRelance, nombreFactureImpaye);
					}catch (Exception e) {		
						debug.logger.debug("erreur recuperation data pour le fichier impaye");
						debug.logger.fatal(e.getMessage());
					}finally {
						instruction.close();
					}
			  }catch (Exception e) {
				  debug.logger.fatal(e.getMessage());
			}
			 return retour;			
	}	
	
}
