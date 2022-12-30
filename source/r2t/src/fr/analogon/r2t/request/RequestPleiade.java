package fr.analogon.r2t.request;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Batch;

public class RequestPleiade extends Request {
	public Vector getFichierPleaide(int numeroBatch) {
		Vector res = new Vector();
		RequestBatch reqBatch = new RequestBatch();
		Batch batch = reqBatch.getBatch(numeroBatch);
		String typeTaxe = batch.getTypeEmplacement();
		Statement instruction;
		// i=0 pour les nouvelles factures BN-
		// i=1 pour les factures annullée BA-
		for (int i = 0; i < 2; i++) {
			try {
				instruction = con.connexion.createStatement();
				try {

					String requete = " SELECT "
							+ " CONCAT("
							+ " RPAD(`facture`.`source`,3,' '),"
							+ " RPAD(`facture`.`idBatchTraitement`,10,' '),"
							+ " RPAD(`facture`.`numeroFacture`,10,' '),"
							+ " CONCAT('01/01/',`facture`.`anneeEx`),"
							+ " RPAD(`facture`.`numeroPretitre`,8,' '),"
							+ " RPAD(`facture`.`numeroTitre`,8,' '),"
							+ " RPAD(`facture`.`dateCreation`,10,' '),"
							+ " RPAD(`facture`.`montantTotal`,13,' '),"
							+ " RPAD(`facture`.`solde`,13,' '),"
							+ " RPAD(`imputation`.`section`,6,' '),"
							+ " RPAD(`imputation`.`codeBudget`,2,' '),"
							+ " RPAD(`imputation`.`codeCentreExecution`,6,' '),"
							+ " RPAD(CONCAT(`imputation`.`designation`,' ',`facture`.`anneeEx`),40,' '),"
							+ " RPAD(CONCAT('Facture Numero ',`facture`.`numeroFacture`,' du ',`facture`.`dateCreation` ),40,' '),"
							+ " RPAD(`facture`.`idClient`,13,' '),"
							+ " RPAD(`redevable`.`civilite`,6,' '),"
							+ " RPAD(`redevable`.`nomRedevable`,32,' '),"
							+ " RPAD(CONCAT(`redevable`.`numRue`,' ',`redevable`.`complementNumeroRueRedevable`,' ', `redevable`.`adresse1` ),32,' '),"
							+ " RPAD(`redevable`.`adresse2`,32,' '),"
							+ " RPAD(`redevable`.`adresse3`,32,' '),"
							+ " RPAD(`redevable`.`ville`,26,' '),"
							+ " RPAD(`redevable`.`codePostal`,5,' '),"
							+ " RPAD(`imputation`.`code`,6,' '),"
							+ " RPAD(`redevable`.`responsable`,30,' '),"
							+ " RPAD(`redevable`.`prenom`,30,' '),"
							+ " RPAD(`redevable`.`informationComplementaire`,50,' '),"							
							+ " RPAD(`redevable`.`siret`,20,' '),"
							+ " RPAD(`redevable`.`siren`,20,' ')"
							+ " ) as uneLigneFacture"
							+ " FROM `facture` ,`imputation`,`redevable` "
							+ " WHERE `facture`.`typeTaxe` = `imputation`.`libelle`"
							+ " AND   `facture`.`idClient` = `redevable`.`numRedevable`"
							+ " AND `facture`.`anneeEx` = `imputation`.`anneeExercice`"
							+ " AND `facture`.`typeTaxe` = '" + typeTaxe + "'";

					// condition ajouter pour selectonner les factues crree par
					// le batch
					if (i == 0)
						requete = requete
								+ " AND `facture`.`idBatchTraitement`="
								+ numeroBatch;
					// condition ajouter pour selectonner les factues annullée
					if (i == 1) {
						int anneeC_1 = Integer.valueOf(GestionDate
								.getAnneeCourante());
						int anneeC_2 = anneeC_1 - 1;
						int anneeC_3 = anneeC_1 - 2;
						// A MODIFIER POUR TOIRVER UEN SOLUTION SELON LES ANNEES
						String dateMaximalPleiadeC_1 = this
								.getdateMaximalPleiade("" + anneeC_1);
						String dateMaximalPleiadeC_2 = this
								.getdateMaximalPleiade("" + anneeC_2);
						String dateMaximalPleiadeC_3 = this
								.getdateMaximalPleiade("" + anneeC_3);
						requete = requete
								+ " AND ("
								+ "`facture`.etat='ANNULEE' AND `facture`.envoyerAPleiade ='false' "
								+ " AND " + "(" + "(`facture`.anneeEx='"
								+ anneeC_1 + "' AND STR_TO_DATE('"
								+ GestionDate.getDateAujourdhuiString()
								+ "','%d/%m/%Y') < STR_TO_DATE('"
								+ dateMaximalPleiadeC_1 + "','%d/%m/%Y') "
								+ ")" + " OR (" + " `facture`.anneeEx='"
								+ anneeC_2 + "' AND STR_TO_DATE('"
								+ GestionDate.getDateAujourdhuiString()
								+ "','%d/%m/%Y') < STR_TO_DATE('"
								+ dateMaximalPleiadeC_2 + "','%d/%m/%Y') "
								+ ")" + " OR (" + " `facture`.anneeEx='"
								+ anneeC_3 + "' AND STR_TO_DATE('"
								+ GestionDate.getDateAujourdhuiString()
								+ "','%d/%m/%Y') < STR_TO_DATE('"
								+ dateMaximalPleiadeC_3 + "','%d/%m/%Y') "
								+ ")" + ")" + ")";
					}
					requete = requete + " ORDER BY facture.numeroFacture DESC ";
					debug.logger.debug(requete);
					ResultSet resultat = instruction.executeQuery(requete);
					while (resultat.next()) {
						String uneLigneFacture = resultat
								.getString("uneLigneFacture");
						res.addElement(uneLigneFacture);
					}
				} catch (Exception e) {
					debug.logger.fatal(e.getMessage());
				} finally {
					instruction.close();
				}
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}
		}
		return res;
	}

	public void majNomFichierPleaide(String nomFichierPleaide,
			int idBatchTraitement) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r = "UPDATE batchtraitement set nomFichierEnvoyeAPleiade ='"
						+ nomFichierPleaide
						+ "'"
						+ " WHERE numeroBatchTraitement =" + idBatchTraitement;
				debug.logger.debug(r);
				int result1 = instruction.executeUpdate(r);
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}

	public String getdateMaximalPleiade(String annee) {
		String res = GestionDate.getDateAujourdhuiString();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String r = "SELECT dateMaximalPleiades FROM datemaximalpleiades WHERE annee ='"
						+ annee + "'";
				ResultSet resultat = instruction.executeQuery(r);
				debug.logger.debug(r);
				while (resultat.next()) {
					res = resultat.getString("dateMaximalPleiades");
				}
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return res;
	}

	public void majInfosFactureFromPleaide(String numeroPretitre,
			String numeroTitre, String solde) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r = " UPDATE facture set numeroTitre ='" + numeroTitre
						+ "'," + " solde ='" + solde + "'"
						+ " WHERE numeroPretitre ='" + numeroPretitre + "'";
				debug.logger.debug(r);
				int result1 = instruction.executeUpdate(r);
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}
}
