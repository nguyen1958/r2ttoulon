package fr.analogon.r2t.request;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Bareme;
import fr.analogon.r2t.pojo.TranchePrixBareme;

public class RequestBareme extends Request {
	public int ajouterBareme(String anExercice, String libelle,
			String typeTaxe, String prixUnitaire, String uniteDeTravail,
			String uniteDeTemps, String utiliserProrata,
			String utiliserPeriodicite, String typeArrondi,
			String prixPeriode1, String prixPeriode2, String prixPeriode3,
			String prixPeriode4, String prixPeriode5, Vector listeTranche,
			String coefficientNumerique, String imputationBareme,
			String sectionBareme) {
		libelle = FonctionCommun.ajouterAntislash(libelle);
		if (!listeTranche.isEmpty()) {
			prixPeriode1 = "0";
			prixPeriode2 = "0";
			prixPeriode3 = "0";
			prixPeriode4 = "0";
			prixPeriode5 = "0";
			prixUnitaire = "0";
		}

		int res = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = "INSERT INTO `bareme` (anExercice,libelle,idImputation,prixUnit,"
						+ "uniteDeTravail,dureeunitaire,prorata,periodicite,"
						+ "typeArrondi,prixPeriode1,prixPeriode2,prixPeriode3,prixPeriode4,"
						+ " prixPeriode5,coefficientNumerique, imputationBareme,sectionBareme)"
						+ " VALUES('"
						+ anExercice
						+ "','"
						+ libelle
						+ "','"
						+ typeTaxe
						+ "','"
						+ prixUnitaire
						+ "','"
						+ uniteDeTravail
						+ "','"
						+ uniteDeTemps
						+ "','"
						+ utiliserProrata
						+ "','"
						+ utiliserPeriodicite
						+ "','"
						+ typeArrondi
						+ "','"
						+ prixPeriode1
						+ "','"
						+ prixPeriode2
						+ "','"
						+ prixPeriode3
						+ "','"
						+ prixPeriode4
						+ "','"
						+ prixPeriode5
						+ "',"
						+ coefficientNumerique
						+ ",'"
						+ imputationBareme 
						+ "','"
						+ sectionBareme 
											
						+ "')";
				debug.logger
						.debug("Requete d'insertion d'un nouveau Bareme de taxe :\n"
								+ req);
				instruction.executeUpdate(req);
				instruction.executeUpdate("commit;");
				// Recuperation de l'identifiant du nouveau type de taxe
				String r2 = " SELECT bareme.code " + " FROM bareme WHERE "
						+ " anExercice='"
						+ anExercice
						+ "'"
						+ " AND libelle='"
						+ libelle
						+ "'"
						+ " AND idImputation='"
						+ typeTaxe
						+ "'"
						+ " AND prixUnit='"
						+ prixUnitaire
						+ "'"
						+ " AND uniteDeTravail='"
						+ uniteDeTravail
						+ "'"
						+ " AND dureeunitaire='"
						+ uniteDeTemps
						+ "'"
						+ " AND prorata='"
						+ utiliserProrata
						+ "'"
						+ " AND periodicite='"
						+ utiliserPeriodicite
						+ "'"
						+ " AND typeArrondi='"
						+ typeArrondi
						+ "'"
						+ " AND prixPeriode1='"
						+ prixPeriode1
						+ "'"
						+ " AND prixPeriode2='"
						+ prixPeriode2
						+ "'"
						+ " AND prixPeriode3='"
						+ prixPeriode3
						+ "'"
						+ " AND prixPeriode4='"
						+ prixPeriode4
						+ "'"
						+ " AND prixPeriode5='" + prixPeriode5 + "'";
				debug.logger.debug(r2);

				ResultSet resultat2 = instruction.executeQuery(r2);
				new RequestAdmin().InsertAction("Ajouter un nouveau Bareme "
						+ libelle + " pour " + " l'annee" + anExercice,
						GestionDate.getDateTime());
				while (resultat2.next()) {
					res = resultat2.getInt("code");
				}

				// Insertion des tranches des prix si type de taxe = TLPE
				if (!listeTranche.isEmpty()) {
					for (int i = 0; i < listeTranche.size(); i++) {
						TranchePrixBareme TPBareme = (TranchePrixBareme) listeTranche
								.elementAt(i);
						String idBareme = String.valueOf(res);
						String annee = TPBareme.getAnnee();
						String surfaceMin = TPBareme.getSurfaceMin();
						String surfaceMax = TPBareme.getSurfaceMax();
						String prix = TPBareme.getPrix();

						// Requete d'insertion des tranches;
						String req2 = "INSERT INTO `tarifbaremetlpe` "
								+ "(idBareme, anExercice, "
								+ " surfaceMin, surfaceMax, prix)" + " VALUES("
								+ idBareme + ",'" + anExercice + "','"
								+ surfaceMin + "','" + surfaceMax + "'," + prix
								+ ")";
						debug.logger
								.debug("Requete d'insertion d'une nouvelle tranche de prix bareme :\n"
										+ req2);
						instruction.executeUpdate(req2);
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

	public int ajouterBareme(String codeBareme, String anExercice,
			String libelle, String typeTaxe, String prixUnitaire,
			String uniteDeTravail, String uniteDeTemps, String utiliserProrata,
			String utiliserPeriodicite, String typeArrondi,
			String prixPeriode1, String prixPeriode2, String prixPeriode3,
			String prixPeriode4, String prixPeriode5, String imputationBareme,
			String sectionBareme) {
		//
		libelle = FonctionCommun.ajouterAntislash(libelle);
		int res = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = "INSERT INTO `bareme` (code,anExercice,libelle,idImputation,prixUnit,"
						+ "uniteDeTravail,dureeunitaire,prorata,periodicite,"
						+ "typeArrondi,prixPeriode1,prixPeriode2,prixPeriode3,prixPeriode4,prixPeriode5,imputationBareme,sectionBareme)"
						+ " VALUES('"
						+ codeBareme
						+ "','"
						+ anExercice
						+ "','"
						+ libelle
						+ "','"
						+ typeTaxe
						+ "','"
						+ prixUnitaire
						+ "','"
						+ uniteDeTravail
						+ "','"
						+ uniteDeTemps
						+ "','"
						+ utiliserProrata
						+ "','"
						+ utiliserPeriodicite
						+ "','"
						+ typeArrondi
						+ "','"
						+ prixPeriode1
						+ "','"
						+ prixPeriode2
						+ "','"
						+ prixPeriode3
						+ "','"
						+ prixPeriode4 
						+ "','" 
						+ prixPeriode5
						+ "','"
						+ imputationBareme
						+ "','"
						+ sectionBareme
						+ "')";
				debug.logger
						.debug("Requete d'insertion d'un nouveau Bareme :\n"
								+ req);
				instruction.executeUpdate(req);
				instruction.executeUpdate("commit;");
				// Recuperation de l'identifiant du nouveau type de taxe
				String r2 = " SELECT bareme.code " + " FROM bareme WHERE "
						+ " anExercice='"
						+ anExercice
						+ "'"
						+ " AND libelle='"
						+ libelle
						+ "'"
						+ " AND idImputation='"
						+ typeTaxe
						+ "'"
						+ " AND prixUnit='"
						+ prixUnitaire
						+ "'"
						+ " AND uniteDeTravail='"
						+ uniteDeTravail
						+ "'"
						+ " AND dureeunitaire='"
						+ uniteDeTemps
						+ "'"
						+ " AND prorata='"
						+ utiliserProrata
						+ "'"
						+ " AND periodicite='"
						+ utiliserPeriodicite
						+ "'"
						+ " AND typeArrondi='"
						+ typeArrondi
						+ "'"
						+ " AND prixPeriode1='"
						+ prixPeriode1
						+ "'"
						+ " AND prixPeriode2='"
						+ prixPeriode2
						+ "'"
						+ " AND prixPeriode3='"
						+ prixPeriode3
						+ "'"
						+ " AND prixPeriode4='"
						+ prixPeriode4
						+ "'"
						+ " AND prixPeriode5='" + prixPeriode5 + "'";
				debug.logger.debug(r2);

				ResultSet resultat2 = instruction.executeQuery(r2);
				new RequestAdmin().InsertAction("Ajouter un nouveau Bareme "
						+ libelle + " pour " + " l'annee" + anExercice,
						GestionDate.getDateTime());
				while (resultat2.next()) {
					res = resultat2.getInt("code");
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

	public Bareme getBareme(String codeBareme, String anneeExercicie) {
		Bareme bareme = new Bareme();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = " SELECT DISTINCT(imputation.libelle) as libelleTaxe ,bareme.* "
						+ " ,imputation.necessiteControleTerrain "
						+ " FROM bareme,imputation "
						+ " WHERE bareme.code="
						+ codeBareme
						+ " AND  bareme.anExercice="
						+ anneeExercicie
						+ " AND imputation.idImputation = bareme.idImputation"
						+ " AND imputation.anneeExercice = bareme.anExercice";
				debug.logger.debug(req);
				ResultSet resultat = instruction.executeQuery(req);
				while (resultat.next()) {
					bareme.setAnExercice(""
							+ resultat.getInt("bareme.anExercice"));
					bareme.setCode("" + resultat.getInt("bareme.code"));
					bareme.setCoefficientNumerique(""
							+ resultat.getInt("coefficientNumerique"));
					bareme.setIdImputation(""
									+ resultat.getInt("idImputation"));
					bareme.setLibelle(resultat.getString("bareme.libelle"));
					bareme.setTypeTaxe(resultat.getString("libelleTaxe"));
					bareme.setPrixUnit(resultat.getBigDecimal("prixUnit").doubleValue());
					bareme.setUniteDeTravail(resultat.getString("uniteDeTravail"));
					bareme.setTypeArrondi(resultat.getString("typeArrondi"));
					bareme.setPeriodicite(resultat.getString("periodicite"));
					bareme.setDureeUnitaire(resultat.getString("dureeunitaire"));
					bareme.setSectionBareme(resultat.getString("sectionBareme"));
					bareme.setImputationBareme(resultat.getString("imputationBareme"));
					bareme.setNecessiteControleTerrain(resultat.getString("necessiteControleTerrain"));
					if (resultat.getString("prorata").equalsIgnoreCase("false"))
						bareme.setProrata("NON");
					else
						bareme.setProrata("OUI");

					if (resultat.getString("periodicite").equalsIgnoreCase("false"))
						bareme.setPeriodicite("NON");
					else
						bareme.setPeriodicite("OUI");

					bareme.setDureeUnitaire(resultat.getString("dureeunitaire"));
					bareme.setPeriode1(resultat.getBigDecimal("prixPeriode1").doubleValue());
					bareme.setPeriode2(resultat.getBigDecimal("prixPeriode2").doubleValue());
					bareme.setPeriode3(resultat.getBigDecimal("prixPeriode3").doubleValue());
					bareme.setPeriode4(resultat.getBigDecimal("prixPeriode4").doubleValue());
					bareme.setPeriode5(resultat.getBigDecimal("prixPeriode5").doubleValue());
					
					
				}

				// recuperation des tranches de prix
				Statement instruction2 = con.connexion.createStatement();
				String req2 = " SELECT * FROM tarifbaremetlpe "
						+ " WHERE idBareme=" + codeBareme + " AND anExercice ="
						+ anneeExercicie;
				debug.logger.debug(req2);
				ResultSet resultat2 = null;
				try {
					resultat2 = instruction.executeQuery(req2);
				} catch (Exception e) {
					debug.logger.fatal(e.getMessage());
				} finally {
					instruction2.close();
				}
				String listeDesTranchesDesPrix = "";
				while (resultat2.next()) {
					String surfaceMin = resultat2.getString("surfaceMin");
					String surfaceMax = resultat2.getString("surfaceMax");
					Double prix = resultat2.getDouble("prix");
					listeDesTranchesDesPrix = listeDesTranchesDesPrix + "$"
							+ surfaceMin + "|" + surfaceMax + "|" + prix;
				}
				bareme.setListeDesTranchesDesPrix(listeDesTranchesDesPrix);
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return bareme;
	}

	public void supprimerBareme(String idBareme, String anneeExercice) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = "delete from bareme WHERE code=" + idBareme
						+ " AND anExercice=" + anneeExercice;
				debug.logger.debug(req);
				int result1 = instruction.executeUpdate(req);

				req = "delete from tarifbaremetlpe WHERE idBareme=" + idBareme
						+ " AND anExercice=" + anneeExercice;
				debug.logger.debug(req);
				result1 = instruction.executeUpdate(req);

				new RequestAdmin().InsertAction("Suprresion du bareme numero "
						+ idBareme + " pour " + " l'annee" + anneeExercice,
						GestionDate.getDateTime());

			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}

	public Vector getListeBareme(String codeBareme, String anneeExercice,
			String libelle, String typeTaxe) {
		libelle = FonctionCommun.ajouterAntislash(libelle);
		Vector res = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = " SELECT bareme.* , imputation.libelle  as libelleTaxe"
						+ " FROM bareme,imputation "
						+ " WHERE bareme.idImputation = imputation.idImputation"
						+ " AND bareme.anExercice= imputation.anneeExercice ";

				if (codeBareme != null && codeBareme.length() != 0)
					req = req + " AND bareme.code='" + codeBareme + "'";
				if (anneeExercice != null && anneeExercice.length() != 0)
					req = req + " AND anExercice='" + anneeExercice + "'";
				if (libelle != null && libelle.length() != 0)
					req = req + " AND bareme.libelle like '%" + libelle + "%'";
				if (typeTaxe != null && typeTaxe.length() != 0)
					req = req + " AND imputation.libelle ='" + typeTaxe + "'";

				debug.logger.debug(req);
				ResultSet resultat = instruction.executeQuery(req);
				while (resultat.next()) {
					Bareme bareme = new Bareme();
					bareme.setAnExercice(""+ resultat.getInt("bareme.anExercice"));
					bareme.setCode("" + resultat.getInt("bareme.code"));
					bareme.setIdImputation(""+ resultat.getInt("idImputation"));
					bareme.setLibelle(resultat.getString("bareme.libelle"));
					bareme.setTypeTaxe(resultat.getString("libelleTaxe"));
					bareme.setPrixUnit(resultat.getBigDecimal("prixUnit").doubleValue());
					bareme.setUniteDeTravail(resultat.getString("uniteDeTravail"));
					bareme.setTypeArrondi(resultat.getString("typeArrondi"));
					if (resultat.getString("prorata").equalsIgnoreCase("false"))
						bareme.setProrata("NON");
					else
						bareme.setProrata("OUI");

					if (resultat.getString("periodicite").equalsIgnoreCase("false"))
						bareme.setPeriodicite("NON");
					else
						bareme.setPeriodicite("OUI");

					bareme.setDureeUnitaire(resultat.getString("dureeunitaire"));
					bareme.setPeriode1(resultat.getBigDecimal("prixPeriode1").doubleValue());
					bareme.setPeriode2(resultat.getBigDecimal("prixPeriode2").doubleValue());
					bareme.setPeriode3(resultat.getBigDecimal("prixPeriode3").doubleValue());
					bareme.setPeriode4(resultat.getBigDecimal("prixPeriode4").doubleValue());
					bareme.setPeriode5(resultat.getBigDecimal("prixPeriode5").doubleValue());
					bareme.setImputationBareme(resultat.getString("imputationBareme"));
					bareme.setSectionBareme(resultat.getString("sectionBareme"));
					res.addElement(bareme);
				}
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
				e.printStackTrace();
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public int modifierBareme(String codeBareme, String anExercice,
			String libelle, String typeTaxe, String prixUnitaire,
			String uniteDeTravail, String uniteDeTemps, String utiliserProrata,
			String utiliserPeriodicite, String typeArrondi,
			String prixPeriode1, String prixPeriode2, String prixPeriode3,
			String prixPeriode4, String prixPeriode5, Vector listeTranche,
			String coefficientNumerique, String imputationBareme,
			String sectionBareme) {
		libelle = FonctionCommun.ajouterAntislash(libelle);
		int res = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				if (!listeTranche.isEmpty()) {
					prixPeriode1 = "0";
					prixPeriode2 = "0";
					prixPeriode3 = "0";
					prixPeriode4 = "0";
					prixPeriode5 = "0";
					prixUnitaire = "0";
				}

				String req = "UPDATE bareme " + " set anExercice='"
						+ anExercice + "'" + " ,libelle='" + libelle + "'"
						+ " ,idImputation='" + typeTaxe + "'" + " ,prixUnit='"
						+ prixUnitaire + "'" + " ,uniteDeTravail='"
						+ uniteDeTravail + "'" + " ,dureeunitaire='"
						+ uniteDeTemps + "'" + " ,prorata='" + utiliserProrata
						+ "'" + " ,periodicite='" + utiliserPeriodicite + "'"
						+ " ,typeArrondi='" + typeArrondi + "'"
						+ " ,prixPeriode1='" + prixPeriode1 + "'"
						+ " ,prixPeriode2='" + prixPeriode2 + "'"
						+ " ,prixPeriode3='" + prixPeriode3 + "'"
						+ " ,prixPeriode4='" + prixPeriode4 + "'"
						+ " ,prixPeriode5='" + prixPeriode5 + "'"
						+ " ,imputationBareme='" + imputationBareme + "'"
						+ " ,sectionBareme='" + sectionBareme + "'"
						+ " ,coefficientNumerique='" + coefficientNumerique+ "'"
						 
						+ " WHERE code='" + codeBareme + "'"
						+ " AND anExercice='" + anExercice + "'";
				debug.logger.debug("modifier un Bareme :\n" + req);
				int result1 = instruction.executeUpdate(req);

				// MAJ DES TRANCHES DES PRIX
				req = "delete from tarifbaremetlpe WHERE idBareme="
						+ codeBareme + " AND anExercice=" + anExercice;
				debug.logger.debug(req);
				result1 = instruction.executeUpdate(req);
				// Insertion des tranches des prix si type de taxe = TLPE
				if (!listeTranche.isEmpty()) {
					for (int i = 0; i < listeTranche.size(); i++) {
						TranchePrixBareme TPBareme = (TranchePrixBareme) listeTranche
								.elementAt(i);
						String annee = TPBareme.getAnnee();
						String surfaceMin = TPBareme.getSurfaceMin();
						String surfaceMax = TPBareme.getSurfaceMax();
						String prix = TPBareme.getPrix();

						// Requete d'insertion des tranches;
						String req2 = "INSERT INTO `tarifbaremetlpe` "
								+ "(idBareme, anExercice, "
								+ " surfaceMin, surfaceMax, prix)" + " VALUES("
								+ codeBareme + ",'" + anExercice + "','"
								+ surfaceMin + "','" + surfaceMax + "'," + prix
								+ ")";
						debug.logger
								.debug("Requete d'insertion d'une nouvelle tranche de prix bareme :\n"
										+ req2);
						instruction.executeUpdate(req2);
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

	public Vector getTousLesBaremes() {
		Vector res = new Vector();

		try {
			Statement instruction = con.connexion.createStatement();
			try {

				ResultSet resultat = instruction
						.executeQuery("SELECT libelle FROM bareme Order by libelle  ");
				while (resultat.next()) {
					res.addElement(resultat.getString("libelle"));
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

	public Vector getTousLesBaremesUnique() {
		Vector res = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction
						.executeQuery("SELECT DISTINCT(libelle) FROM bareme Order by libelle  ");
				while (resultat.next()) {
					res.addElement(resultat.getString("libelle"));
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

	/**
	 * Methode qui permet de retourner le prix de la periode attribut du bareme
	 * dont le code CHARFI Sofien
	 * 
	 * @since 2.0
	 */

	public double getPrixPeriode(int codeBareme, int numeroPeriode,
			String anneeEx) {
		double res = 0;

		Statement instruction;
		String attribut = "";
		boolean trouve = false;
		// Cas des TAV
		if (numeroPeriode == 0)
			numeroPeriode = 1;
		if (numeroPeriode < 6)
			attribut = "prixPeriode" + numeroPeriode;
		else
			attribut = "prixPeriode5";
		try {
			instruction = con.connexion.createStatement();
			String req = "SELECT " + attribut + " FROM bareme WHERE code="
					+ codeBareme + " AND  bareme.anExercice='" + anneeEx + "'";
			ResultSet resultat = instruction.executeQuery(req);
			while (resultat.next()) {
				res = resultat.getDouble(attribut);
				trouve = true;
			}
			debug.logger.debug(req);
		} catch (Exception e) {
			// debug.logger.fatal(e.getMessage());
			try {
				attribut = "prixPeriode5";
				debug.logger.debug("Impossible de trouver le prix du bareme "
						+ codeBareme + " pour l'annee " + anneeEx
						+ "........................[Erreur]");
				debug.logger
						.debug(" Recherche du prix du bareme pour la derniere annne connu....... ");
				instruction = con.connexion.createStatement();
				ResultSet resultat = instruction.executeQuery("SELECT "
						+ attribut + " FROM bareme WHERE code=" + codeBareme);
				while (resultat.next()) {
					res = resultat.getDouble(attribut);
					trouve = true;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		// La cas ou on a pas trouve le prix du bareme pour l'annee donnee
		if (!trouve) {
			// attribut = "prixPeriode5";
			try {

				debug.logger.debug("Impossible de trouver le prix du bareme "
						+ codeBareme + " pour l'annee " + anneeEx
						+ "........................[Erreur]");
				debug.logger
						.debug(" Recherche du prix du bareme pour la derniere annne connu....... ");
				instruction = con.connexion.createStatement();
				ResultSet resultat = instruction.executeQuery("SELECT "
						+ attribut + " FROM bareme WHERE code=" + codeBareme);
				while (resultat.next()) {
					res = resultat.getDouble(attribut);
				}
				instruction.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return res;
	}

	public Vector getListeBareme(String typeEmplacement, String anExercice) {
		Vector res = new Vector();

		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction
						.executeQuery("SELECT DISTINCT (bareme.code), bareme.* "
								+ " FROM imputation,bareme "
								+ " WHERE imputation.idImputation = bareme.idImputation "
								+ " AND  imputation.libelle='"
								+ typeEmplacement
								+ "'"
								+ " AND  bareme.anExercice='"
								+ anExercice
								+ "'"
								+ " AND imputation.anneeExercice="
								+ anExercice);
				while (resultat.next()) {
					String bareme = resultat.getString("code");
					bareme = bareme + "-----" + resultat.getString("libelle")
							+ "-----";
					if (!typeEmplacement.equalsIgnoreCase("TLPE"))
						bareme = bareme + resultat.getString("prixUnit")
								+ " euros-----";
					bareme = bareme + " "
							+ resultat.getString("uniteDeTravail");
					if (!resultat.getString("uniteDeTravail").equalsIgnoreCase(
							"FORFAIT"))
						bareme = bareme + "/"
								+ resultat.getString("dureeunitaire");
					bareme = bareme + "-----Tarif("
							+ resultat.getString("anExercice") + ")";
					// bareme="ALI";
					res.addElement(bareme);
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

	public String peutEtreSuuprimer(String code, String anneeExercice) {
		String res = "true";
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = " SELECT id_article from article "
						+ " WHERE codebareme='" + code + "'"
						+ " AND anexercice='" + anneeExercice + "'";
				debug.logger.debug(req);
				ResultSet resultat2 = instruction.executeQuery(req);
				while (resultat2.next()) {
					res = "false";
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
	
	
	public boolean changeDeTarif (double surface1, double surface2, int codeBareme, String annee) 
	{		
		boolean res = false;
		try 
		{
			Statement instruction = con.connexion.createStatement();
			try 
			{
				String req =  " SELECT prix " 
					         + " FROM tarifbaremetlpe "
						     + " WHERE idBareme=" + codeBareme + " " 
						     + " AND surfaceMin <= " + surface1
						     + " AND surfaceMax >= " + surface1
						     + " AND anExercice ="	+ annee;
				//debug.logger.debug(req);	
				ResultSet resultat = instruction.executeQuery(req);
				double prix1= 0;
				while (resultat.next()) 
				{
					prix1 = resultat.getDouble("prix");
				}
				req =  " SELECT prix " 
			         + " FROM tarifbaremetlpe "
				     + " WHERE idBareme=" + codeBareme + " " 
				     + " AND surfaceMin <= " + surface2
				     + " AND surfaceMax >= " + surface2
				     + " AND anExercice ="	+ annee;
				//debug.logger.debug(req);	
				resultat = instruction.executeQuery(req);
				double prix2= 0;
				while (resultat.next()) 
				{
					prix2 = resultat.getDouble("prix");
				}
				//debug.logger.debug("Prix1="+prix1 + "Prix2="+prix2);
				
				if (prix1 != prix2) res = true;
			} 
			catch (Exception e) 
			{
				debug.logger.fatal(e.getMessage());
			}
			finally 
			{
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return res;
	}
	
	
}
