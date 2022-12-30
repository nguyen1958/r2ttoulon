package fr.analogon.r2t.request;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Article;
import fr.analogon.r2t.pojo.Bareme;
import fr.analogon.r2t.pojo.Bascule;
import fr.analogon.r2t.pojo.Imputation;

public class RequestBascule extends Request {
	public void finalize() {
		// System.out.println(
		// System.gc();
	}

	public Bascule getBascule(String anneeExercice) {
		Bascule bascule = new Bascule();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1 = " SELECT historiquebascule.*  "
						+ " FROM historiquebascule " + " WHERE deAnnee="
						+ anneeExercice;
				debug.logger.debug(r1);
				ResultSet resultat1 = instruction.executeQuery(r1);
				while (resultat1.next()) {
					bascule.setDateBascule(resultat1.getString("dateBacule"));
					bascule.setDeAnnee(resultat1.getString("deAnnee"));
					bascule.setVersAnnee(resultat1.getString("versAnnee"));
					bascule.setPourcentage(resultat1.getString("pourcentage"));
				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return bascule;
	}

	public void basculerImputation(String anneeExercice, double pourcentage) {
		try {
			RequestImputation reqImputation = new RequestImputation();
			Vector listeTaxe = reqImputation.getListeImputations("", "", "",
					anneeExercice, "");
			for (int i = 0; i < listeTaxe.size(); i++) {
				Imputation imputation = (Imputation) listeTaxe.elementAt(i);
				reqImputation.ajouterTypeTaxe(
						Integer.parseInt(imputation.getNumtypeTaxe()), 
						imputation.getLibelle(), 
						imputation.getDesignation(), 
						imputation.getCodeBudget(),
						imputation.getCode(), 
						imputation.getSection(),
						imputation.getCodeFonction(), 
						imputation.getLibelleFonction(), 
						imputation.getCodeCentreResponsable(), 
						imputation.getLibelleCentreResponsable(), 
						imputation.getCodeCentreExecution(), 
						imputation.getLibelleCentreExecution(), 
						imputation.getNecessiteControleTerrain(), 
						imputation.getControleInduitFacturation(), 
						imputation.getNomElu(),
						imputation.getEluRenseignement1(), 
						imputation.getEluRenseignement2(), 
						imputation.getEluRenseignement3(), 
						imputation.getNomGestionnaire(), 
						imputation.getTelGestionnaire(), 
						imputation.getFaxGestionnaire(), 
						FonctionCommun.ajouterPourcentage(Double.valueOf(imputation.getMinimumPerception()), pourcentage),
						""+ String.valueOf((Integer.parseInt(anneeExercice) + 1)),
						imputation.getTypeFacturation(), 
						imputation.getCycleFacturation(),
						//@cadre budgétaire
						imputation.getChapitre(),
						imputation.getNature(),
						imputation.getFonction(),
						imputation.getCodeOpeEquipement(),
						imputation.getTypeMouvement(),
						imputation.getSens(),
						imputation.getCodeSegStructurelle(),
						imputation.getCodeEleStructurelleGestionnaire(),
						imputation.getCodeEleStructurelleDestinataire(),
						imputation.getCodeSegment1(),
						imputation.getCodeEleSectoriel1(),
						imputation.getCodeSegment2(),
						imputation.getCodeEleSectoriel2(),
						imputation.getCodeSegment3(),
						imputation.getCodeEleSectoriel3(),
						imputation.getCodeSegment4(),
						imputation.getCodeEleSectoriel4(),
						imputation.getCodeSegment5(),
						imputation.getCodeEleSectoriel5(),
						imputation.getCodeSegOperationnel(),
						imputation.getCodeEleOperationnel(),
						imputation.getCodeSegStrategique(),
						imputation.getCodeEleStrategique(),
						//marche
						imputation.getMarche()
						);
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}

	public String verifierSiBasculeEstFaite(String anneeExercice) {
		String res = "";
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r = " SELECT * FROM historiquebascule WHERE deAnnee="
						+ anneeExercice;
				debug.logger.debug(r);
				ResultSet resultat1 = instruction.executeQuery(r);
				while (resultat1.next()) {
					res = resultat1.getString("dateBacule");
				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return res;
	}

	public void ecrireHistoriqueBascule(String anneeExercice, String date,
			String poucentage) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = "INSERT INTO historiquebascule (deAnnee, versAnnee,dateBacule,pourcentage) "
						+ "VALUES('"
						+ anneeExercice
						+ "','"
						+ (Integer.parseInt(anneeExercice) + 1)
						+ "','"
						+ date
						+ "','" + poucentage + "')";
				debug.logger.debug(req);
				instruction.executeUpdate(req);
				new RequestAdmin().InsertAction(
						"Bascule des baremes et des imputations "
								+ " de l'annee "
								+ anneeExercice
								+ " a  l'annee "
								+ Integer.valueOf(Integer
										.valueOf(anneeExercice) + 1) + ""
								+ " avec un pourcentage " + poucentage,
						GestionDate.getDateTime());
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}

	public void basculerBaeme(String anneeExercice, double pourcentage) {
		try {
			RequestBareme reqBareme = new RequestBareme();
			Vector listeBareme = reqBareme.getListeBareme("", anneeExercice,
					"", "");
			for (int i = 0; i < listeBareme.size(); i++) {
				Bareme bareme = (Bareme) listeBareme.elementAt(i);
				String Prorata, Periodicite;
				if (bareme.getProrata().equalsIgnoreCase("OUI"))
					Prorata = "true";
				else
					Prorata = "false";
				if (bareme.getPeriodicite().equalsIgnoreCase("OUI"))
					Periodicite = "true";
				else
					Periodicite = "false";
				Vector listeTranche = new Vector();
				//		
				reqBareme.ajouterBareme(bareme.getCode(), 
						String.valueOf((Integer.parseInt(anneeExercice) + 1)), 
						bareme.getLibelle(), 
						bareme.getIdImputation(), 
						FonctionCommun.ajouterPourcentage(bareme.getPrixUnit(), pourcentage),
						bareme.getUniteDeTravail(), 
						bareme.getDureeUnitaire(),
						Prorata, 
						Periodicite, 
						bareme.getTypeArrondi(),
						FonctionCommun.ajouterPourcentage(bareme.getPeriode1(),pourcentage), 
						FonctionCommun.ajouterPourcentage(bareme.getPeriode2(),pourcentage), 
						FonctionCommun.ajouterPourcentage(bareme.getPeriode3(),pourcentage), 
						FonctionCommun.ajouterPourcentage(bareme.getPeriode4(),pourcentage), 
						FonctionCommun.ajouterPourcentage(bareme.getPeriode5(),pourcentage),
						bareme.getImputationBareme(), 
						bareme.getSectionBareme() );
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}

	public void basculerLesTav() {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r = " UPDATE article, bareme,imputation set article.etat ='AFacturer' "
						+ " WHERE article.codebareme = bareme.code  "
						+ " AND bareme.idImputation = imputation.idImputation "
						+ " AND imputation.libelle='TAV' "
						+ " AND article.source = 'normal' "
						+ " AND article.etat ='Facturer' ";
				debug.logger.debug(r);
				int result2 = instruction.executeUpdate(r);
				new RequestAdmin().InsertAction("Bascule des TAV ", GestionDate
						.getDateTime());
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}
	
	
	//Paul 19/12/2022
	/**
	 * Pour ETLAGE
	 * Changer l état de l ouvrage actif qui ont le status ( FactureAControler ) a l état ( AFacturer )
	 * Pour les autres 	 
	 * Changer l état de l ouvrage actif qui ont le status ( Facturer ) a l état ( AFacturer )
	 * Mettre dans historiqueAction une seule action "Bascule des taxes ...."
	 * Pour chaque ouvrage, Mettre dans HistoriqueEtatOuvrage (modification de l'etat de l'ouvrage par Bacule)  
	 */
	public void basculerUneTaxe(String taxe) {
		try {
			Statement instruction = con.connexion.createStatement();
			//Pour conserver le resultset de la select
			Statement instruction2 = con.connexion.createStatement();
			String r="";
			String r1="";
			try {
				//taxe etalage
				if(taxe.equalsIgnoreCase("ETALAGE")){
					r = "UPDATE article, bareme,imputation set article.etat ='AFacturer' "
							+ " WHERE article.codebareme = bareme.code  "
							+ " AND bareme.idImputation = imputation.idImputation "
							+ " AND imputation.libelle='"+taxe+"' "
							+ " AND article.source = 'normal' "
							+ " AND article.etat ='FacturerAControler' ";
					//Sélectionner les ouvrages pour création historiqueEtatOuvrage
					r1="SELECT DISTINCT(article.id_article) "
							+ " FROM article,bareme,imputation "
							+ " WHERE article.codebareme = bareme.code  "
							+ " AND bareme.idImputation = imputation.idImputation "
							+ " AND imputation.libelle='"+taxe+"' "
							+ " AND article.source = 'normal' "
							+ " AND article.etat ='FacturerAControler' ";
				}
				//autres taxes
				else{
					r = "UPDATE article, bareme,imputation set article.etat ='AFacturer' "
							+ " WHERE article.codebareme = bareme.code  "
							+ " AND bareme.idImputation = imputation.idImputation "
							+ " AND imputation.libelle='"+taxe+"' "
							+ " AND article.source = 'normal' "
							+ " AND article.etat ='Facturer' ";
					//Sélectionner les ouvrages pour création historiqueEtatOuvrage
					r1="SELECT DISTINCT(article.id_article) "
							+ " FROM article,bareme,imputation "
							+ " WHERE article.codebareme = bareme.code  "
							+ " AND bareme.idImputation = imputation.idImputation "
							+ " AND imputation.libelle='"+taxe+"' "
							+ " AND article.source = 'normal' "
							+ " AND article.etat ='Facturer' ";
				}
				debug.logger.debug(r);
				debug.logger.debug(r1);	
				//Select ouvrages devant changé état
				ResultSet resultatSelect = instruction2.executeQuery(r1);
				//maj etat des ouvrages
				int result2 = instruction.executeUpdate(r);
				//Ajout dans historiqueAction
				new RequestAdmin().InsertAction("Bascule des "+taxe, GestionDate
						.getDateTime());
				//Ajout dans historiqueEtatOuvrage
				String message = "Modification de l'etat de l'ouvrage par Bacule";
				message = FonctionCommun.ajouterAntislash(message);
				while (resultatSelect.next()) {
					int idOuvrage = resultatSelect.getInt("id_article");
					String requete = " INSERT INTO historiqueetatouvrage(etatOuvrage,  dateModification, "
							+ " typeModifcation,type, idOuvrage)"
							+ " VALUES('A Facturer',"
							+ "'"
							+ GestionDate.getDateAujourdhuiString()
							+ "',"
							+ "'"
							+ message + "'," + "'bascule'," + idOuvrage + ") ";
					debug.logger.debug(requete);
					instruction.executeUpdate(requete);			
				}
				
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
				instruction2.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}
	
	
	
	
	public void basculerTLPE(String anneeEx) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r = " UPDATE article, bareme,imputation " 
						+ " set article.etat ='AFacturer' "
						+ " WHERE article.codebareme = bareme.code  "
						+ " AND bareme.idImputation = imputation.idImputation "
						+ " AND imputation.libelle='TLPE' "
						+ " AND article.source = 'normal' "
						+ " AND article.etat ='Facturer'" 
						+ " AND   ( article.DateFinAutorisation = '' " 
								+ "OR " 
								+ "( STR_TO_DATE(article.DateFinAutorisation, '%d/%m/%Y') >= STR_TO_DATE('01/01/" + anneeEx +"', '%d/%m/%Y') )" 
							    + ")";			 
								
				debug.logger.debug("Request bacule TLPE " +r);
				int result2 = instruction.executeUpdate(r);
				new RequestAdmin().InsertAction("Bascule la TLPE ", GestionDate.getDateTime());
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}
	
	
	
	
}
