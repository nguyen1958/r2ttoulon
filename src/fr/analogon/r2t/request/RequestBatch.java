package fr.analogon.r2t.request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.pojo.Batch;
import fr.analogon.r2t.pojo.RapportChangementAdresse;
import fr.analogon.r2t.pojo.RapportSuivieFacturation;

public class RequestBatch extends Request {
	public void finalize() {
		// System.out.println(
	}

	public void majBatchChangementAdresse(String idBatch, String aCreeFichier) {
		int res = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String requete = " UPDATE batchchangementadresseredevable set fichierCree='"
						+ aCreeFichier
						+ "'"
						+ " WHERE idBatchChangementAdresseRedevable='"
						+ idBatch + "'";
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
	}

	public void majBatchSuivieDeFacturation(String idBatch, String aCreeFichier) {
		int res = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String requete = " UPDATE batchsuiviefacturation set aEditerFichier='"
						+ aCreeFichier
						+ "'"
						+ " WHERE idBatchSuivieFacturation='" + idBatch + "'";
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

	
	public void majBatchAEditerFactureAnnule(String idBatch,
			String aEditeeDesFacturesAnnulee) {
		int res = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String requete = " UPDATE batchtraitement "
						+ " set aEditeeDesFacturesAnnulee='"
						+ aEditeeDesFacturesAnnulee + "'"
						+ " WHERE numeroBatchTraitement='" + idBatch + "'";
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

	public void majBatchAEditerRoleChangementAdresseRedevable(String idBatch,
			String aEditerRoleChangementAdresseRedevable) {
		int res = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String requete = " UPDATE batchtraitement "
						+ " set aEditerRoleChangementAdresseRedevable='"
						+ aEditerRoleChangementAdresseRedevable + "'"
						+ " WHERE numeroBatchTraitement='" + idBatch + "'";
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

	public int ajouterLigneBatchChangementAdresse(String dateCreation) {
		int res = 0;
		String requete = " INSERT INTO batchchangementadresseredevable(dateCreation)"
				+ " VALUES('" + dateCreation + "')";
		debug.logger.debug(requete);
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				int result1 = instruction.executeUpdate(requete);
				String req1 = " SELECT max(idBatchChangementAdresseRedevable) as idBatchChangementAdresseRedevable "
						+ " FROM batchchangementadresseredevable";
				debug.logger.debug(req1);
				ResultSet resultat = instruction.executeQuery(req1);
				while (resultat.next()) {
					res = resultat.getInt("idBatchChangementAdresseRedevable");
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


	public int ajouterLigneBatchListeCommunique(String dateLancement,
			String quartier, String numSecteur) {
		int res = 0;
		String requete = " INSERT INTO batchrolecommunique(dateLancement,quartier,secteur)"
				+ " VALUES('"
				+ dateLancement
				+ "','"
				+ quartier
				+ "','"
				+ numSecteur + "')";
		debug.logger.debug(requete);
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {

				int result1 = instruction.executeUpdate(requete);
				// instruction = con.connexion.createStatement();
				String req1 = " SELECT max(idBatchRoleCommunique) as maxIdBatchRoleCommunique "
						+ " FROM batchrolecommunique";
				debug.logger.debug(req1);
				ResultSet resultat = instruction.executeQuery(req1);
				while (resultat.next()) {
					res = resultat.getInt("maxIdBatchRoleCommunique");
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

	public int ajouterLigneBatchSuivieFacturation(String dateCreation,
			String dateDebut, String dateFin, String typeTaxe, String idUser) {
		int res = 0;
		String requete = " INSERT INTO batchsuiviefacturation(dateCreation,dateDebut,"
				+ " dateFin,typeTaxe,idDemandeur)"
				+ " VALUES('"
				+ dateCreation
				+ "','"
				+ dateDebut
				+ "','"
				+ dateFin
				+ "','"
				+ typeTaxe
				+ "','" + idUser + "')";
		debug.logger.debug(requete);
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {

				int result1 = instruction.executeUpdate(requete);
				String req1 = " SELECT max(idBatchSuivieFacturation) as idBatchSuivieFacturation "
						+ " FROM batchsuiviefacturation";
				debug.logger.debug(req1);
				ResultSet resultat = instruction.executeQuery(req1);
				while (resultat.next()) {
					res = resultat.getInt("idBatchSuivieFacturation");
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

	public Vector updateSoldeEtTitreFacture(String numPretitre,
			String numTitre, String solde) {
		Vector params = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r = " UPDATE facture set numeroTitre='" + numTitre
						+ "'," + " solde='" + solde + "'"
						+ " WHERE numeroPretitre='" + numPretitre + "'";
				debug.logger.debug("Update Solde/NumTitre de la facture :\n"
						+ r);
				int result1 = instruction.executeUpdate(r);

			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}

		return params;
	}

	public Batch getBatch(int numeroBatch) {
		// Vector params=new Vector();
		// TEST
		Batch batch = new Batch();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1 = " SELECT  batchtraitement.*  "
						+ " FROM batchtraitement "
						+ " WHERE numeroBatchTraitement=" + numeroBatch;

				debug.logger.debug(r1);
				// System.out.println("//////Requete//////////"+r1);
				ResultSet resultat1 = instruction.executeQuery(r1);
				String listeFactures = "";
				int i = 0;
				int k = 0;

				while (resultat1.next()) {
					batch.setNumeroBatchTraitement(String.valueOf(numeroBatch));
					batch.setTypeEmplacement(resultat1.getString("typeEmplacement"));
					batch.setaEditerDesRemboursement(resultat1.getString("aEditerDesRemboursements"));					
					batch.setEtape(resultat1.getString("etape"));
					batch.setNombreRemboursements(""+resultat1.getInt("nombreDeRemboursementCree"));
					batch.setNumeroBatchTraitement(resultat1.getString("numeroBatchTraitement"));
					batch.setDateExecution(resultat1.getString("dateExecution"));
					batch.setAEditerDesFactureAnnulee(resultat1	.getString("aEditeeDesFacturesAnnulee"));
					batch.setAnneeExercice(resultat1.getString("anExercice"));
					batch.setNomDossier(resultat1.getString("anExercice") + "-"			+ resultat1.getString("numeroBatchTraitement"));
					batch.setNombreFactures(String.valueOf(resultat1.getInt("nombreDeFactureCree")));
					batch.setNomFichierEnvoyeAPleiade(resultat1.getString("nomFichierEnvoyeAPleiade"));
					batch.setAEditerRoleChangementAdresseRedevable(resultat1.getString("aEditerRoleChangementAdresseRedevable"));
					batch.setValide(resultat1.getString("valide"));
					i++;
				}

				r1 = " SELECT  facture.numeroFacture " + " FROM facture "
						+ " WHERE facture.idBatchTraitement=" + numeroBatch
						+ " ORDER BY facture.numeroFacture DESC";
				debug.logger.debug(r1);
				resultat1 = instruction.executeQuery(r1);
				Vector tab = new Vector();
				while (resultat1.next()) {
					tab.addElement(" <option>Facture numero "
							+ String.valueOf(resultat1.getInt("numeroFacture"))
							+ " </option><BR>");
				}
				listeFactures = tab.toString();
				if (i != 0) {
					batch.setListeFactures(listeFactures);
				}
				// cas ou le batch n'agenere auune facture
				else {
					// System.out.println("Dans else ");
					// debug.logger.debug("Pas de facture pour la batch "+numeroBatch);
					Statement instruction2 = con.connexion.createStatement();
					String r2 = " SELECT  batchtraitement.*  "
							+ " FROM batchtraitement "
							+ " WHERE numeroBatchTraitement=" + numeroBatch;

					debug.logger.debug(r2);
					ResultSet resultat2 = instruction.executeQuery(r2);
					while (resultat2.next()) {
						batch.setNumeroBatchTraitement(String
								.valueOf(numeroBatch));
						batch.setTypeEmplacement(resultat2
								.getString("typeEmplacement"));
						batch.setNumeroBatchTraitement(resultat2
								.getString("numeroBatchTraitement"));
						batch.setDateExecution(resultat2
								.getString("dateExecution"));
						// debug.logger.debug(resultat1.getString("aEditeeDesFacturesAnnulee"));
						batch.setAEditerDesFactureAnnulee(resultat2
								.getString("aEditeeDesFacturesAnnulee"));
						batch.setNombreFactures(String.valueOf(resultat2
								.getInt("nombreDeFactureCree")));
					}
					batch.setListeFactures(listeFactures);
				}
				// System.out.println("Fin try");
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
				// System.out.println("Erreur");
				e.printStackTrace();
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
			// System.out.println("Erreur");
			e.printStackTrace();
		}
		// System.out.println("AVANT LA FIN " +batch.getNombreFactures());
		return batch;
	}


	public Vector getListeRapportChangementAdresse() {
		Vector params = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {

				String r1 = " SELECT * FROM batchchangementadresseredevable "
						+ " WHERE fichierCree='true'"
						+ " ORDER BY idBatchChangementAdresseRedevable DESC";
				debug.logger.debug(r1);
				ResultSet resultat1 = instruction.executeQuery(r1);

				String listeFactures = "";
				int i = 0;
				while (resultat1.next()) {
					RapportChangementAdresse rcar = new RapportChangementAdresse();
					rcar
							.setIdBatch(""
									+ resultat1
											.getInt("idBatchChangementAdresseRedevable"));
					rcar.setDateCreation(""
							+ resultat1.getString("dateCreation"));
					rcar.setACreeFichier(""
							+ resultat1.getString("fichierCree"));
					params.addElement(rcar);
				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return params;
	}

	public Vector getListeRapportSuivieFacturation() {
		Vector params = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {

				String r1 = " SELECT * FROM batchsuiviefacturation  "
						+ " ORDER BY idBatchSuivieFacturation DESC";
				debug.logger.debug(r1);
				ResultSet resultat1 = instruction.executeQuery(r1);

				while (resultat1.next()) {
					RapportSuivieFacturation rsfacturation = new RapportSuivieFacturation();
					rsfacturation.setACreeFichier(resultat1
							.getString("aEditerFichier"));
					rsfacturation.setDateCreation(resultat1
							.getString("dateCreation"));
					rsfacturation
							.setDatedebut(resultat1.getString("dateDebut"));
					rsfacturation.setDateFin(resultat1.getString("dateFin"));
					rsfacturation.setTypeTaxe(resultat1.getString("typeTaxe"));
					rsfacturation.setIdDemandeur(resultat1
							.getString("idDemandeur"));
					rsfacturation.setIdBatch(""
							+ resultat1.getInt("idBatchSuivieFacturation"));
					params.addElement(rsfacturation);
				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return params;
	}
}
