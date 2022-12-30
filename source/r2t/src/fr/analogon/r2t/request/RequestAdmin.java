package fr.analogon.r2t.request;

import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Tache;

public class RequestAdmin extends Request {

	public void InsertAction(String action, String date ) {
		action = FonctionCommun.ajouterAntislash(action);
		try {
			Statement instruction2 = con.connexion.createStatement();
			try {
				String req = "INSERT INTO historiqueaction " + "(action,date)"
						+ " VALUES('" + action + "','" + date + "')";
				debug.logger.debug(req);
				int resultat3 = instruction2.executeUpdate(req);
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction2.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}

	public Vector getHistoriquesTaches(String date, String actionRecherche,
			String motClef, String typeTaxe) {
		actionRecherche = FonctionCommun.ajouterAntislash(actionRecherche);
		motClef = FonctionCommun.ajouterAntislash(motClef);

		Vector res = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String requete = " SELECT * FROM historiqueaction WHERE idHistoriqueAction > 0 ";
				if (date.length() != 0)
					requete = requete + " AND date like '%" + date + "%'";
				if (typeTaxe.length() != 0)
					requete = requete + " AND action like '%" + typeTaxe + "%'";
				if (motClef.length() != 0)
					requete = requete + " AND action like '%" + motClef + "%'";
				if (actionRecherche.length() != 0) {

					if (actionRecherche.contains("photo")) {
						requete = requete + " AND action like '%image%'";
					}

					if (actionRecherche.equalsIgnoreCase("Bascule")) {
						requete = requete + " AND action like '%bascule%'";
					}
					if (actionRecherche.equalsIgnoreCase("Lancement de batch")) {
						requete = requete
								+ " AND action like '%Lancement du batch%'";
					}
					if (actionRecherche
							.equalsIgnoreCase("Changement d'adresse")) {
						requete = requete
								+ " AND action like '%Changement de l\\'adresse redevable%'";
					}

				}

				requete = requete + " ORDER BY idHistoriqueAction DESC";
				debug.logger.debug(requete);
				ResultSet resultat1 = instruction.executeQuery(requete);
				int i = 0;

				while (resultat1.next()) {
					i++;
					Tache tache = new Tache();
					tache.setAction(resultat1.getString("action"));
					tache.setDate(resultat1.getString("date"));
					res.addElement(tache);
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

	public String getNiveauDebogageApplication() {
		String res = "";
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String requete = "SELECT DISTINCT(valeur) FROM parametre WHERE type ='niveauDebogageApplication' ORDER BY  valeur";
				ResultSet resultat = instruction.executeQuery(requete);
				debug.logger.debug(requete);
				while (resultat.next()) {
					res = resultat.getString("valeur");
				}
				instruction.close();
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

	public String getNiveauDebogageR2TMobile() {
		String res = "";
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String requete = "SELECT DISTINCT(valeur) FROM parametre WHERE type ='niveauDebogageR2TMobile' ORDER BY  valeur";
				ResultSet resultat = instruction.executeQuery(requete);
				debug.logger.debug(requete);
				while (resultat.next()) {
					res = resultat.getString("valeur");
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

	public String getRegie() {
		String res = "false";
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String requete = "SELECT * FROM modules WHERE nomModule='regie'";
				ResultSet resultat = instruction.executeQuery(requete);
				while (resultat.next()) {
					res = "true";
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
	
	public String getMarche() {
		String res = "false";
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String requete = "SELECT * FROM modules WHERE nomModule='marche'";
				ResultSet resultat = instruction.executeQuery(requete);
				while (resultat.next()) {
					res = "true";
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

	public String majNiveauDebogageApplication(String niveauDebogageApplication) {
		String res = "0";
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = " update parametre set valeur='"
						+ niveauDebogageApplication + "'"
						+ " WHERE type='niveauDebogageApplication' ";
				debug.logger.debug(req);
				int result1 = instruction.executeUpdate(req);
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
				res = "-1";
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
			res = "-1";
		}
		return res;
	}

	public String majNiveauDebogageR2TMobile(String niveauDebogageR2TMobile) {
		String res = "0";
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = " update parametre set valeur='"
						+ niveauDebogageR2TMobile + "'"
						+ " WHERE type='niveauDebogageR2TMobile' ";
				debug.logger.debug(req);
				int result1 = instruction.executeUpdate(req);
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
				res = "-1";
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
			res = "-1";
		}
		return res;
	}

	public String supprimerFichierTemporaire() {
		String res = "0";
		try {
			Statement instruction = con.connexion.createStatement();
			try {

				// Suppresion des fichiers temporaires pour les réclmations
				String dossierTmpCourier = fichierDeConfiguration
						.getCheminDossierTmp();
				File folder = new File(dossierTmpCourier);
				deleteDir(folder);
				folder.mkdir();
				new RequestAdmin().InsertAction(
						"Suppresions des fichiers temporaires "
								+ dossierTmpCourier, GestionDate.getDateTime());

				// Suppresion des donnees de pre-Refacturation
				String r = "DELETE from article WHERE source='preRefacturation'";
				debug.logger.debug(r);
				int result1 = instruction.executeUpdate(r);

				// instruction = con.connexion.createStatement();
				r = "DELETE from elementfacturation WHERE source='preRefacturation'";
				debug.logger.debug(r);
				result1 = instruction.executeUpdate(r);

				// instruction = con.connexion.createStatement();
				r = "DELETE from emplacementgeneral WHERE source='preRefacturation'";
				debug.logger.debug(r);
				result1 = instruction.executeUpdate(r);

				new RequestAdmin().InsertAction(
						"Suppresions des données temporaires de prRefacturation "
								+ dossierTmpCourier, GestionDate.getDateTime());

			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
				res = "-1";
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
			res = "-1";
		}

		return res;
	}

	// Deletes all files and subdirectories under dir.
	// Returns true if all deletions were successful.
	// If a deletion fails, the method stops attempting to delete and returns
	// false.
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// The directory is now empty so delete it
		return dir.delete();
	}
	
	public String getAction(String action,String typeTaxe) {
		String res = "false";
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String requete = "SELECT * FROM historiqueaction WHERE action like '"+action+"%"+typeTaxe+"'";
				System.out.println(requete);
				ResultSet resultat = instruction.executeQuery(requete);
				while (resultat.next()) {
					res = "true";
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
	
	public void removeAction(String action, String typeTaxe ) {
		action = FonctionCommun.ajouterAntislash(action);
		try {
			Statement instruction2 = con.connexion.createStatement();
			try {
				String requete = "DELETE FROM historiqueaction WHERE action like '"+action+"%"+typeTaxe+"'";					
				debug.logger.debug(requete);
				int resultat3 = instruction2.executeUpdate(requete);
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction2.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}
 
}
