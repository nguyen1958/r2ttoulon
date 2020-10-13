package fr.analogon.r2t.request;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Rue;

public class RequestRue extends Request {

	public Vector getListeRue(String codeRivolieRue, String adresseRue,
			String quartierRue) {
		Vector params = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				adresseRue = FonctionCommun.ajouterAntislash(adresseRue);
				quartierRue = FonctionCommun.ajouterAntislash(quartierRue);
				String r1 = " SELECT *  FROM rue "
						+ " WHERE "
						+ " ( "
						+ "  CONCAT(prefixe, \" \", liaison, \" \", nomrue, \" \")  like '%"
						+ adresseRue + "%' "
						+ "  OR CONCAT(prefixe, \" \", nomrue, \" \")  like '%"
						+ adresseRue + "%' " + " )"
						+ " AND nomQuartier like '%" + quartierRue + "%'"
						+ " AND codeRivolie like '%" + codeRivolieRue + "%'";
				debug.logger.debug(r1);
				ResultSet resultat = instruction.executeQuery(r1);
				int i = 0;
				while (resultat.next()) {
					Rue rue = new Rue();
					// rue = this.getRue(resultat1.getString("codeVoie"));
					rue.setCodeRivolie(resultat.getString("codeRivolie"));
					rue.setCodeVoie(resultat.getString("codeVoie"));
					rue.setLiaison(resultat.getString("liaison"));
					rue.setNomrue(resultat.getString("nomrue"));
					rue.setPrefixe(resultat.getString("prefixe"));
					rue.setCodePostal(resultat.getString("codePostal"));
					rue.setNomquartier(resultat.getString("nomQuartier"));
					
					rue.setFamilleMarche(""+resultat.getInt("idMarche"));
					rue.setCodeSecteur(resultat.getString("codeSecteur"));
					
					rue.setFinNumeroRue(resultat.getString("finNumeroRue"));
					rue.setDebutNumeroRue(resultat.getString("debutNumeroRue"));
					rue.setRemarqueRue(resultat.getString("remarqueRue"));
					params.addElement(rue);
					i++;
					if (i > 200) {
						break;
					}
				}
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

	public Vector getListeTypeRue() {
		Vector res = new Vector();

		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction.executeQuery("SELECT valeur"
						+ " FROM parametre WHERE type='type_Rue' ");
				while (resultat.next()) {
					res.addElement(resultat.getString("valeur"));
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

	public Rue getRue(String idRue) {
		Rue res = new Rue();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String requete = "SELECT *  FROM rue WHERE codeVoie= codeVoie ";
				if (idRue.length() != 0)
					requete = requete + "AND codeVoie= '" + idRue + "'";
				ResultSet resultat = instruction.executeQuery(requete);
				while (resultat.next()) {
					res.setCodeRivolie(resultat.getString("codeRivolie"));
					res.setCodeVoie(resultat.getString("codeVoie"));
					res.setLiaison(resultat.getString("liaison"));
					res.setNomrue(resultat.getString("nomrue"));
					res.setPrefixe(resultat.getString("prefixe"));
					res.setNomquartier(resultat.getString("nomQuartier"));
					res.setFinNumeroRue(resultat.getString("finNumeroRue"));
					res.setDebutNumeroRue(resultat.getString("debutNumeroRue"));
					res.setRemarqueRue(resultat.getString("remarqueRue"));
					res.setCodePostal(resultat.getString("codePostal"));
					
					res.setFamilleMarche(""+resultat.getInt("idMarche"));
					res.setCodeSecteur(resultat.getString("codeSecteur"));
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

	public boolean peutEtreSupprimer(String codeVoie) {
		boolean res = true;

		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String requete = "SELECT codeVoie  FROM redevable  WHERE codeVoie= "
						+ codeVoie;
				ResultSet resultat = instruction.executeQuery(requete);
				while (resultat.next()) {
					res = false;
				}
				if (res) {
					instruction = con.connexion.createStatement();
					requete = "SELECT codeVoie  FROM emplacementgeneral  WHERE codeVoie= "
							+ codeVoie;
					resultat = instruction.executeQuery(requete);
					while (resultat.next()) {
						res = false;
					}
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

	public int ajouterRue(String prefixeAdresseRue, String liaisonAdresseRue,
			String nomAdresseRue, String codeRivolieRue, String debutNumeroRue,
			String finNumeroRue, String quartierRue, String remarqueRue,
			String codePostal, String codeSecteur,String familleMarche) {

		prefixeAdresseRue = FonctionCommun.ajouterAntislash(prefixeAdresseRue);
		liaisonAdresseRue = FonctionCommun.ajouterAntislash(liaisonAdresseRue);
		nomAdresseRue = FonctionCommun.ajouterAntislash(nomAdresseRue);
		quartierRue = FonctionCommun.ajouterAntislash(quartierRue);
		remarqueRue = FonctionCommun.ajouterAntislash(remarqueRue);

		int newCodeVoie = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = "INSERT INTO `rue` (codeRivolie,prefixe,liaison,"
						+ " nomrue,debutNumeroRue,finNumeroRue,nomQuartier,remarqueRue,codePostal,codeSecteur,idMarche)"
						+ " VALUES('" + codeRivolieRue + "','"
						+ prefixeAdresseRue + "','" + liaisonAdresseRue + "','"
						+ nomAdresseRue + "','" + debutNumeroRue + "','"
						+ finNumeroRue + "','" + quartierRue + "','"
						+ remarqueRue + "','" + codePostal + "','"+codeSecteur+"'"+ ",'"+familleMarche+"')"; 
				
				debug.logger.debug("Requete d'insertion d'une nouvelle rue :\n"
						+ req);
				instruction.executeUpdate(req);
				instruction.executeUpdate("commit;");
				new RequestAdmin().InsertAction("Ajouter un nouvelle Rue : "
						+ " " + prefixeAdresseRue + " " + liaisonAdresseRue
						+ " " + nomAdresseRue, GestionDate.getDateTime());
				// Recuperation du dernier index dans la table Rue:
				String r2 = " SELECT MAX(codeVoie) as  codeVoie FROM rue  ";
				debug.logger.debug(r2);
				String codeVoie = "0";
				ResultSet resultat2 = instruction.executeQuery(r2);
				while (resultat2.next()) {
					newCodeVoie = resultat2.getInt("codeVoie");
				}
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return newCodeVoie;
	}

	public void modifierRue(String codeRue, String prefixeAdresseRue,
			String liaisonAdresseRue, String nomAdresseRue,
			String codeRivolieRue, String debutNumeroRue, String finNumeroRue,
			String quartierRue, String remarqueRue, String codePostal, String codeSecteur,String familleMarche ) {
		prefixeAdresseRue = FonctionCommun.ajouterAntislash(prefixeAdresseRue);
		liaisonAdresseRue = FonctionCommun.ajouterAntislash(liaisonAdresseRue);
		nomAdresseRue = FonctionCommun.ajouterAntislash(nomAdresseRue);
		quartierRue = FonctionCommun.ajouterAntislash(quartierRue);
		remarqueRue = FonctionCommun.ajouterAntislash(remarqueRue);
		int res = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = "UPDATE rue " + " set " 
						+" codeRivolie ='" + codeRivolieRue + "'" 
						+ " ,prefixe ='" + prefixeAdresseRue + "'" 
						+ " ,liaison ='" + liaisonAdresseRue + "'" 
						+ " ,nomrue='" + nomAdresseRue + "'" 
						+ " ,debutNumeroRue='" + debutNumeroRue + "'" 
						+ " ,finNumeroRue='" + finNumeroRue + "'" 
						+ " ,nomQuartier='" + quartierRue + "'" 
						+ " ,remarqueRue='" + remarqueRue + "'"
						+ " ,codePostal='" + codePostal + "'"
						
						+ " ,codeSecteur='" + codeSecteur + "'"
						+ " ,idMarche='" + familleMarche + "'"
						
						+ " WHERE codeVoie='" + codeRue + "'";
				debug.logger.debug("modifier le profil Rue :\n" + req);

				int result1 = instruction.executeUpdate(req);
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}

	public void supprimerRue(String codeRue) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = "delete from rue WHERE codeVoie='" + codeRue + "'";
				debug.logger.debug(req);
				int result1 = instruction.executeUpdate(req);
				new RequestAdmin().InsertAction("Suprresion de la rue "
						+ codeRue, GestionDate.getDateTime());
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}

	}

	public Vector getListeQuartiers(String nomQuartier) {

		nomQuartier = FonctionCommun.ajouterAntislash(nomQuartier);
		Vector res = new Vector();

		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String requete = "select DISTINCT(nomQuartier) from rue"
						+ " Where nomQuartier !=\"\"  AND nomQuartier like '%"
						+ nomQuartier + "%'" + " ORDER BY nomQuartier";
				debug.logger.debug(requete);
				ResultSet resultat = instruction.executeQuery(requete);
				while (resultat.next()) {
					res.addElement(resultat.getString("nomQuartier"));
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

	public Vector getListeRue(String nomRue) {
		nomRue = FonctionCommun.ajouterAntislash(nomRue);
		Vector params = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1 = " SELECT *  "
						+ " FROM rue "
						+ " WHERE "
						+ " CONCAT(prefixe, \" \", liaison,\" \",rue.nomrue)  like '%"
						+ nomRue + "%'" + " OR "
						+ " CONCAT(prefixe, \" \", rue.nomrue)  like '%"
						+ nomRue + "%'";

				debug.logger.debug(r1);
				ResultSet resultat1 = instruction.executeQuery(r1);
				while (resultat1.next()) {
					Rue rue = new Rue();
					rue = this.getRue(resultat1.getString("codeVoie"));
					params.addElement(rue);
				}
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

	public Vector getListeRueFromCodeVoieRedevable(String CodeRivolie,
			String numRue) {

		Vector params = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1 = "  SELECT *  FROM rue  "
						+ " WHERE rue.codeRivolie ='" + CodeRivolie + "'";

				if (numRue != null && numRue.length() != 0) {
					if (numRue.indexOf("-") != -1)
						numRue = numRue.substring(0, numRue.indexOf("-"));

					int numeroRue = Integer.parseInt(numRue);
					String rq = "";
					if (numeroRue % 2 == 0)
						rq = "P";
					else
						rq = "I";
					r1 = r1 + "  AND ( (" + numRue + " <= rue.finNumeroRue "
							+ "	AND  rue.debutNumeroRue <= " + numRue + ")" +
							// "   AND ("+
							// numRue+" + rue.debutNumeroRue ) % 2 ='0' )" +
							"   OR ( rue.debutNumeroRue='1' AND rue.finNumeroRue='500' )  )";
					if (rq.length() != 0)
						r1 = r1 + " AND ( remarqueRue='" + rq + "' OR  remarqueRue='' )";
				}
				debug.logger.debug(r1);
				ResultSet resultat1 = instruction.executeQuery(r1);
				int i = 0;
				while (resultat1.next()) {
					Rue rue = new Rue();
					rue = this.getRue(resultat1.getString("codeVoie"));
					params.addElement(rue);
					i++;
				}

				if (i > 1) {
					r1 = "  SELECT *  FROM rue WHERE rue.codeRivolie ='"
							+ CodeRivolie + "'";
					r1 = r1 + "  AND " + numRue + " <= rue.finNumeroRue "
							+ "	AND  rue.debutNumeroRue <= " + numRue
							+ "  AND (" + numRue
							+ " + rue.debutNumeroRue ) % 2 ='0' ";
					debug.logger.debug(r1);
					resultat1 = instruction.executeQuery(r1);
					while (resultat1.next()) {
						params.clear();
						Rue rue = new Rue();
						rue = this.getRue(resultat1.getString("codeVoie"));
						params.addElement(rue);
					}
				}
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
}
