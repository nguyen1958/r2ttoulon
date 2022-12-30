package fr.analogon.r2t.request;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.ReglerCheminSelonOs;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.BatchFilien;

public class RequestFilien  extends Request
{		
	
	public static String chaineVide(int nombrEspace) {
		String res ="";
		for (int i = 1; i < nombrEspace; i++) {
			res =res +" ";
		}
		return res;
	}
	

	public void majBatchFilien(int idBatchFilien, int nombreFactureImpaye, String listeNumeroFacture) 
	{
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String requete = " UPDATE batchfilien set nombreFactureImpaye="
						+ nombreFactureImpaye + " , listeNumeroFacture = '"+listeNumeroFacture +"' WHERE idBatchFilien='"
						+ idBatchFilien + "'";
				debug.logger.debug(requete);

				int result1 = instruction.executeUpdate(requete);
			} catch (SQLException e) {
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction.close();
			}
		} catch (SQLException e) {
			debug.logger.fatal(e.getMessage());
		}
	}

	public int ajouterLigneBatchFilien(String dateLancement,
			String numeroBatchFilien, String typeFilien, String anneeCourante) {
		int res = 0;
		String requete = " INSERT INTO batchfilien(dateLancement,periode,type,annee)"
				+ " VALUES('"
				+ dateLancement
				+ "','"
				+ numeroBatchFilien
				+ "','" + typeFilien + "','" + anneeCourante + "')";
		debug.logger.debug(requete);
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				int result1 = instruction.executeUpdate(requete);
				String req1 = " SELECT max(idBatchFilien) as idBatchFilien "
						+ " FROM batchfilien";
				debug.logger.debug(req1);
				ResultSet resultat = instruction.executeQuery(req1);
				while (resultat.next()) {
					res = resultat.getInt("idBatchFilien");
				}
			} catch (SQLException e) {
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction.close();
			}
		} catch (SQLException e) {
			debug.logger.fatal(e.getMessage());
		}

		return res;
	}

	
	
	public Vector getFichierFilien(int numeroBatchFilien, String codenaturejuridique, 
				String idNatureJur,boolean tous) 
	{	
			  Vector res= new Vector();		
			  RequestFilien requestFilien = new RequestFilien();			  
			  BatchFilien batchFilien =  requestFilien.getBatchFilien(numeroBatchFilien);
			  
			  String periode = batchFilien.getPeriode();
			  String type = batchFilien.getType();			  
			  
			  String typeFacturation ="";
			  if (type.equalsIgnoreCase("a"))
				  typeFacturation = "Avance-Annuelle";
			  else if (type.equalsIgnoreCase("t"))
				  typeFacturation = "Avance-Trimestrielle";
			  else if (type.equalsIgnoreCase("m"))
				  typeFacturation = "Avance-Mensuelle";
			  
			  
			  String annee = batchFilien.getAnnee();
			  String debutPeriode="";
			  String finPeriode="";
			  
			  
			  //Paul 25/06/2019 modif pour balise 26 à 266
			  /*
			  String dossierFacture=ReglerCheminSelonOs.reglerCheminSelonOS(
					  fichierDeConfiguration.getAdresseServeur()+"/r2tData/data/factures/");
			  String slashe=ReglerCheminSelonOs.reglerCheminSelonOS("/");
			  System.out.println("Cheminfactures="+dossierFacture);
			  */
			  if(periode.equalsIgnoreCase("1")) {
				   debutPeriode = "01/01/"+ annee ;
				   finPeriode = "31/03/" + annee;
			  }else if(periode.equalsIgnoreCase("2")) {
				   debutPeriode = "01/04/"+ annee ;
				   finPeriode = "30/06/" + annee;
			  }else if(periode.equalsIgnoreCase("3")) {
				   debutPeriode = "01/07/"+ annee ;
				   finPeriode = "30/09/" + annee;
			  }else if(periode.equalsIgnoreCase("4")) {
				   debutPeriode = "01/10/"+ annee ;
				   finPeriode = "31/12/" + annee;
			  }else if( type.equalsIgnoreCase("a")) {
				  debutPeriode = "01/01/"+ annee ;
				  finPeriode = "31/12/" + annee;
			  }
			  
			  DebuggerLog4J.logger.debug("debutPeriode="+debutPeriode);
			  DebuggerLog4J.logger.debug("finPeriode="+finPeriode);
			  Statement instruction;
			  try {
					instruction = con.connexion.createStatement();
				  try {
						String bloc ="100";//remplacer 80 par 100
						String separateur =";;;";					
	
						String CODEIMPUTATIONSECTION = chaineVide(10)+"73" + 
						chaineVide(6) +"020" + chaineVide(3)+"7368" +
						chaineVide(3) + "60014" + chaineVide(31) + "R1";
						
						String lettre="R";
						
						String requete =
					    	" SELECT "+
					    	" CONCAT( CONCAT('/##/PARAM/01/05/',facture.anneeEx),'/5/N/N/N"+separateur+"') as premierLigne,"+
					    	" CONCAT("+				    					    	
					    	" RPAD(CONCAT('/##/',''),"+bloc+",' '),'"+separateur+"',"+
					    	" RPAD( CONCAT(  CONCAT('/01/',SUBSTR(facture.anneeEx,3,4)),'R'),"+bloc+",' '),'"+separateur+"',"+
					    	" RPAD(CONCAT('/02/R',''),"+bloc+",' '),'"+separateur+"',"+
					    	" RPAD(CONCAT('/03/"+codenaturejuridique+"',''),"+bloc+",' '),'"+separateur+"',"+
					    	//Paul evolution eCadre budgetaire, Limiter le champs à 40 car
					    	//" RPAD(CONCAT('/04/ROLE IMPAYE EMPLACEMENT ',imputation.libelle),"+bloc+",' '),'"+separateur+"',"+
					    	" '/04/',RPAD(CONCAT('IMPAYE ',imputation.libelle),40,' '),'"+separateur+"',"+
					    	" RPAD(CONCAT('/05/02',''),"+bloc+",' '),'"+separateur+"',"+
					    	" RPAD(CONCAT('/06/E',''),"+bloc+",' '),'"+separateur+"',"+
					    	" RPAD(CONCAT('/11/9900',''),"+bloc+",' '),'"+separateur+"',"+				    	
					    	" RPAD(CONCAT('/13/',''),"+bloc+",' '),'"+separateur+"',"+
					    	" RPAD(CONCAT('/15/02',''),"+bloc+",' '),'"+separateur+"',"+
					    	//Paul evolution eCadre budgetaire, Limiter le champs à 40 car
					    	//" RPAD(CONCAT('/20/ROLE IMPAYE EMPLACEMENT ',imputation.libelle),"+bloc+",' '),'"+separateur+"',"+
					    	" '/20/',RPAD(CONCAT('IMPAYE ',imputation.libelle),40,' '),'"+separateur+"',"+
					    	" RPAD(CONCAT('/21/',facture.numeroFacture),"+bloc+",' '),'"+separateur+"',"+
					    	" RPAD(CONCAT('/22/','' ),"+bloc+",' '),'"+separateur+"',"+
					    	" RPAD(CONCAT('/23/','VOIR DETAIL SUR LA FACTURE'),"+bloc+",' '),'"+separateur+"',"+
					    	" RPAD(CONCAT('/24/',redevable.nomRedevable,' ',redevable.prenom ),"+bloc+",' '),'"+separateur+"',"+	
					    	" RPAD(CONCAT('/25/',imputation.libelle ),"+bloc+",' '),'"+separateur+"',"+
					    	//Paul 25-06-2019 modifier et ajouter 26 à 266
					    	//" RPAD(CONCAT('/26/',redevable.naturejuridique),"+bloc+",' '),'"+separateur+"'," +
					    	" RPAD(CONCAT('/26/',facture.numeroFacture),"+bloc+",' '),'"+separateur+"'," +
					    	" RPAD(CONCAT('/261/',facture.numeroFacture,'.pdf'),"+bloc+",' '),'"+separateur+"'," +
					    	" RPAD(CONCAT('/262/','01'),"+bloc+",' '),'"+separateur+"'," +
					    	" RPAD(CONCAT('/263/',''),"+bloc+",' '),'"+separateur+"'," +
					    	" RPAD(CONCAT('/264/','002'),"+bloc+",' '),'"+separateur+"'," +
					    	" RPAD(CONCAT('/265/','06'),"+bloc+",' '),'"+separateur+"'," +
					    	" RPAD(CONCAT('/266/','FAC'),"+bloc+",' '),'"+separateur+"'," +
							" RPAD(CONCAT('/31/',redevable.nomRedevable),"+bloc+",' '),'"+separateur+"',";
				    	
					    	//Si personne privee mettre :
							//Balise /31 avec le nom
							//Balise /321 avec prenom du redevable 
							//codenaturejuridique commence par 11
							debug.logger.debug("idNatureJur>>>" + idNatureJur);
					 	
					    	if (idNatureJur.startsWith("11"))
					    	{
					    		requete = requete+
					    	    "RPAD(CONCAT('/321/',redevable.prenom),"+bloc+",' '),'"+separateur+"',"+
					    		"RPAD(CONCAT('/33/',redevable.civilite),"+bloc+",' '),'"+separateur+"'," ;
					    	}
					    	requete = requete+	
					    	
					    	" RPAD(CONCAT('/34/', redevable.numRue,' ' ,redevable.adresse1 ),"+bloc+",' '),'"+separateur+"',"+		
					    	" RPAD(CONCAT('/35/',redevable.adresse2,' ',redevable.adresse3 ),"+bloc+",' '),'"+separateur+"',"+
					    	" RPAD(CONCAT('/36/',redevable.codePostal),"+bloc+",' '),'"+separateur+"',"+
					    	" RPAD(CONCAT('/37/',redevable.ville),"+bloc+",' '),'"+separateur+"',"+
					    	" RPAD(CONCAT('/38/',redevable.ville),"+bloc+",' '),'"+separateur+"',"+					    	
//Paul evolution 22/06/2021 ajouter le bloc /500/..../506/
							" RPAD(CONCAT('/**/',''),"+bloc+",' '),'"+separateur+"',"+
							" RPAD(CONCAT('/500/P',''),"+bloc+",' '),'"+separateur+"',"+
							" RPAD(CONCAT('/501/001',''),"+bloc+",' '),'"+separateur+"',"+
							" RPAD(CONCAT('/502/',imputation.libelle ),"+bloc+",' '),'"+separateur+"',"+
							" RPAD(CONCAT('/503/','"+debutPeriode.replaceAll("/", "")+"'),"+bloc+",' '),'"+separateur+"',"+
							" RPAD(CONCAT('/504/','"+finPeriode.replaceAll("/", "")+"' ),"+bloc+",' '),'"+separateur+"',"+
							" RPAD(CONCAT('/505/1,00',''),"+bloc+",' '),'"+separateur+"',"+
							" RPAD(CONCAT('/506/',facture.solde ),"+bloc+",' '),'"+separateur+"',"+
//fin
							" RPAD(CONCAT('/--/',''),"+bloc+",' '),'"+separateur+"',"+
					    	" RPAD(CONCAT('/51/001',''),"+bloc+",' '),'"+separateur+"',"+
					    	//section dans la BD est le numero d'envloppe bugetaire
					    	//" RPAD(CONCAT('/54/',imputation.section),"+bloc+",' '),'"+separateur+"',"+
					    	//Paul evolution eCadre budgetaire
					    	//paul remplacer /54/ par /541/,/542/,/543/,/544/,/545/ dont chaque bloc=100 au lieu de 80
					    	//Seule la balise /541/ est obligatoire
					    	//Attention, ici les balises ne comptent pas dans le bloc
					    	" '/541/',RPAD(CONCAT(RPAD(imputation.chapitre,10,' '),RPAD(imputation.nature,10,' '),RPAD(imputation.fonction,10,' '),RPAD(imputation.codeOpeEquipement,10,' '),SUBSTR(imputation.typeMouvement,1,1),SUBSTR(imputation.sens,1,1)),"+bloc+",' '),'"+separateur+"',"+
					    	" '/542/',RPAD(CONCAT(RPAD(imputation.codeSegStructurelle,10,' '),RPAD(imputation.codeEleStructurelleGestionnaire,10,' '),RPAD(imputation.codeEleStructurelleDestinataire,10,' ')),"+bloc+",' '),'"+separateur+"',"+
					    	" '/543/',RPAD(imputation.codeSegment1,10,' '),RPAD(imputation.codeEleSectoriel1,10,' '),RPAD(imputation.codeSegment2,10,' '),RPAD(imputation.codeEleSectoriel2,10,' ')," +
					    			 "RPAD(imputation.codeSegment3,10,' '),RPAD(imputation.codeEleSectoriel3,10,' '),RPAD(imputation.codeSegment4,10,' '),RPAD(imputation.codeEleSectoriel4,10,' ')," +
					    			 "RPAD(imputation.codeSegment5,10,' '),RPAD(imputation.codeEleSectoriel5,10,' '),'"+separateur+"',"+
					    	" '/544/',RPAD(CONCAT(RPAD(imputation.codeSegOperationnel,10,' '),RPAD(imputation.codeEleOperationnel,10,' ')),"+bloc+",' '),'"+separateur+"',"+
					    	" '/545/',RPAD(CONCAT(RPAD(imputation.codeSegStrategique,10,' '),RPAD(imputation.codeEleStrategique,10,' ')),"+bloc+",' '),'"+separateur+"',"+					 					 		    	
			    	
					    	" RPAD(CONCAT('/66/',facture.solde),"+bloc+",' '),'"+separateur+"'"+
					    	 					    	
					    	" ) as uneLigneFacture , facture.numeroFacture "+
					    	" FROM facture ,imputation,redevable "+
					    	
					    	" WHERE facture.typeTaxe = imputation.libelle" ; 
					    	
						//ajout de condition pour avoir plusieur fichiers en finction de la nature juridique	
						if(!tous)
						requete = requete+ " AND redevable.naturejuridique like'"+ idNatureJur+ "%'" ;
						
					    	if (typeFacturation.equalsIgnoreCase("Avance-Annuelle"))
					    	{
					    		requete = requete+" AND ( imputation.cycleFacturation = 'Avance-Annuelle' || " +
					    				" imputation.libelle='TLPE') ";
					    	}
					    	else 
					    	{
					    		requete = requete+" AND (imputation.cycleFacturation = 'Avance-Trimestrielle' " 
					    				 + " OR imputation.cycleFacturation = 'Avance-Mensuelle' " 
					    				 + " OR ( imputation.cycleFacturation = 'Normal' && imputation.libelle !='TLPE' ) )" ;
					    	}
							requete = requete +" AND facture.anneeEx = imputation.anneeExercice"+
					    	" AND   facture.idClient = redevable.numRedevable"+
					    	" AND facture.etat !='ANNULEE' AND facture.etat !='preRefacturation' " +
					    	" AND facture.etat !='preFacturation'"+
					    	" AND facture.envoyerALaTresorie = 'false' "+
					    	" AND facture.anneeEx = imputation.anneeExercice" +
					    	" AND STR_TO_DATE(facture.dateCreation,'%d/%m/%Y') <= STR_TO_DATE('"+finPeriode+"','%d/%m/%Y') " +
					    	" AND STR_TO_DATE(facture.dateCreation,'%d/%m/%Y') >= STR_TO_DATE('"+debutPeriode+"','%d/%m/%Y') " +
					    	" AND facture.solde != 0   " +
					    	" ORDER BY imputation.libelle,facture.numeroFacture DESC " ;
					 	debug.logger.debug(requete);
					 	
						ResultSet resultat = instruction.executeQuery(requete) ;		
						
						int nombreFactureImpaye=0;
						boolean premierLigne= true;
						String entetFichier= "";
						String listeNumeroFacture ="";
						while(resultat.next()) 
						{
							String uneLigneFacture= resultat.getString("uneLigneFacture");
							if(premierLigne) {
								premierLigne = false;
								String premiereLigneFilien = resultat.getString("premierLigne");
								res.addElement(premiereLigneFilien);
							}
							
							res.addElement(uneLigneFacture);
							listeNumeroFacture = listeNumeroFacture + ";" +resultat.getString("numeroFacture"); 
							nombreFactureImpaye++;
						}
						DebuggerLog4J.logger.debug("nombreFactureImpaye="+nombreFactureImpaye);
						requestFilien.majBatchFilien(numeroBatchFilien, nombreFactureImpaye,listeNumeroFacture);
					}catch (Exception e) {		
						debug.logger.fatal("erreur recuperation data pour le fichier filien");
						debug.logger.fatal(e.getMessage());
					}finally {
						instruction.close();
					}
			  }catch (Exception e) {
				  debug.logger.fatal(e.getMessage());
			}
			 return res;			
	}

	public String getIndexFilien(String annee) {
		int res =0 ;		
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try 
			{
					String request1 ="SELECT MAX(indexFacture) as indexFacture FROM indexfilien  WHERE annee ='"+annee+"'" ;
					ResultSet resultat = instruction.executeQuery( request1 );
					//debug.logger.debug(request1);		
					while(resultat.next()) {		
						res = resultat.getInt("indexFacture");				
					}
					if (res != 0) {
						//un index existe deja 
						res =res +1;
						//insertion
						Statement instruction2 = con.connexion.createStatement();
						String req= "INSERT INTO indexfilien " +
							"(indexFacture,annee)" +				
							" VALUES("+res+","+ annee+")";			
						//debug.logger.debug(req);						
						int resultat3 = instruction2.executeUpdate(req);
					}else {
						int debut = 20001;
						//pas d'index pour cette annee 
						Statement instruction2 = con.connexion.createStatement();
						String req= "INSERT INTO indexfilien " +
							"(indexFacture,annee)" +				
							" VALUES("+debut+","+ annee+")";			
						debug.logger.debug(req);	
						try {
							int resultat3 = instruction2.executeUpdate(req);
						}catch (Exception e) {
							debug.logger.fatal(e.getMessage());
						}finally {
							instruction2.close();							
						}
						//instruction = con.connexion.createStatement();
						resultat = instruction.executeQuery( "SELECT MAX(indexFacture) as indexFacture " +
								 " FROM indexfilien " +
								 " WHERE annee ='"+annee+"'");
								
						while(resultat.next())	{		
							res = resultat.getInt("indexFacture");					
						}
					}
			}catch (Exception e) {		
				debug.logger.fatal(e.getMessage());
			}finally {
				instruction.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return ""+res;
	}

	
	
	
	public BatchFilien getBatchFilien(int numeroBatchFilien) {

		BatchFilien batchFilien = new BatchFilien();

		try {
			Statement instruction = con.connexion.createStatement();
			try {

				String r1 = " SELECT  *  " + " FROM batchfilien "
						+ " WHERE idBatchFilien=" + numeroBatchFilien;

				debug.logger.debug(r1);
				ResultSet resultat1 = instruction.executeQuery(r1);
				while (resultat1.next()) {
					batchFilien.setDateLancement(resultat1
							.getString("dateLancement"));
					batchFilien.setIdBatchFilien(""
							+ resultat1.getInt("idBatchFilien"));
					batchFilien.setNumeroBatchTraitement(""
							+ resultat1.getInt("idBatchFilien"));
					batchFilien.setPeriode(resultat1.getString("periode"));
					batchFilien.setAnnee(resultat1.getString("annee"));
					batchFilien.setEtatBatchFilien(resultat1.getString("etat"));
					batchFilien.setType(resultat1.getString("type"));
					batchFilien.setListeNumeroFacture(resultat1.getString("listeNumeroFacture"));					
					batchFilien.setNombreFactureImpaye(resultat1
							.getInt("nombreFactureImpaye"));
					

				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
			e.printStackTrace();
		}
		return batchFilien;
	}

	public void validerBatchFilien(int numeroBatchFilien) 
	{		
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String requete = " UPDATE batchfilien  set etat='valide' " +
						" WHERE idBatchFilien="+ numeroBatchFilien ;
				debug.logger.debug(requete);

				int result1 = instruction.executeUpdate(requete);
				instruction.close();
			} catch (SQLException e) {
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction.close();
			}
		} catch (SQLException e) {
			debug.logger.fatal(e.getMessage());
		}
		
		BatchFilien batchFilien =  this.getBatchFilien(numeroBatchFilien);
		String listeNumeroFacture =  batchFilien.getListeNumeroFacture();
		//System.out.println("listeNumeroFacture " + listeNumeroFacture);
		StringTokenizer st = new StringTokenizer(listeNumeroFacture,";");
		int i = 0 ;
		while (st.hasMoreElements()) 
		{
			String numeroFacture = (String)st.nextToken();			
			try {
				Statement instruction = con.connexion.createStatement();
				try {
					String requete = " UPDATE facture set envoyerALaTresorie='true' " +
							" WHERE numeroFacture="+ numeroFacture ;
					debug.logger.debug(requete);
					int result1 = instruction.executeUpdate(requete);
					instruction.close();
				} catch (SQLException e) {
					debug.logger.fatal(e.getMessage());
				} finally {
					instruction.close();
				}
			} catch (SQLException e) {
				debug.logger.fatal(e.getMessage());
			}			
			i++;			
		}
		debug.logger.debug("Nombre de facture envoye a la resorie " + i);
	}

	
	
}
