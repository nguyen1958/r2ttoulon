package fr.analogon.r2t.request;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.pojo.Alerte;
import fr.analogon.r2t.pojo.Image;

public class RequestAlerte extends Request {

	public Vector getListealerte(String numeroAlerteRech,
			String typeAlerteRech, String dateCreationAlerteRech,
			String nomControleurRech, String etatAlerteRech, String numeroRue,
			String nomRue, String nomRedevable, String liedossier,
			String nomQuartier) {
		nomRue = FonctionCommun.ajouterAntislash(nomRue);
		nomRedevable = FonctionCommun.ajouterAntislash(nomRedevable);
		nomQuartier = FonctionCommun.ajouterAntislash(nomQuartier);
		etatAlerteRech = etatAlerteRech.replaceAll(" ", "");
		System.out.println("etatAlerteRech" + etatAlerteRech);
		Vector params = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1 = "";
				ResultSet resultat1 = null;
				Vector idAlertes = new Vector();
				if (!liedossier.equalsIgnoreCase("SansDossier")) {
					r1 = " SELECT alerte.*,elementfacturation.numeroEmplacement, "
							+ " article.anexercice,emplacementgeneral.numRedevable,"
							+ " redevable.nomRedevable ,redevable.civilite,redevable.prenom as prenomRedevable, "
							+ " elementfacturation.numero as numero_ef,emplacementgeneral.codeEmplacement, "
							+ " utilisateur.nom, utilisateur.prenom,"
							+ " imputation.libelle as typeTaxe,"
							+ " emplacementgeneral.numRue as numeroRue ,emplacementgeneral.adresse1,"
							+ " emplacementgeneral.adresse3,emplacementgeneral.adresse2"
							+

							" FROM alerte, article, elementfacturation,redevable, "
							+ " emplacementgeneral,utilisateur,imputation,bareme "
							+

							" WHERE alerte.id_article = article.id_article "
							+ " AND article.id_elementfacturation = elementfacturation.numero "
							+ " AND elementfacturation.numeroEmplacement= emplacementgeneral.numero "
							+ " AND utilisateur.numeroUser = alerte.id_controleur "
							+ " AND redevable.numRedevable = emplacementgeneral.numRedevable"
							+ " AND imputation.idImputation=  bareme.idImputation ";
					if (nomQuartier.length() != 0)
						r1 = r1 + " AND alerte.quartier ='" + nomQuartier + "'";

					if (!etatAlerteRech.equalsIgnoreCase("TOUS"))
						r1 = r1 + " AND alerte.etatAlerte LIKE '"
								+ etatAlerteRech + "'";

					if (numeroAlerteRech.length() != 0)
						r1 = r1 + " AND id_alerte =" + numeroAlerteRech;
					if (dateCreationAlerteRech.length() != 0)
						r1 = r1 + " AND alerte.date_creation ='"
								+ dateCreationAlerteRech + "'";
					if (nomControleurRech.length() != 0)
						r1 = r1
								+ " AND CONCAT(utilisateur.prenom,' ',utilisateur.nom) like '%"
								+ nomControleurRech + "%'";
					if (numeroRue.length() != 0)
						r1 = r1 + " AND emplacementgeneral.numRue LIKE'%"
								+ numeroRue + "%'";
					if (nomRue.length() != 0)
						r1 = r1 + " AND emplacementgeneral.adresse1 LIKE'%"
								+ nomRue + "%'";
					if (nomRedevable.length() != 0)
						r1 = r1
								+ " AND CONCAT(redevable.nomRedevable,' ',redevable.prenom) LIKE'%"
								+ nomRedevable + "%'";

					r1 = r1 + " ORDER BY alerte.id_alerte DESC";

				}

				if (!r1.equalsIgnoreCase("")) {
					debug.logger.debug(r1);
					resultat1 = instruction.executeQuery(r1);

					while (resultat1.next()) {
						Alerte alerte = new Alerte();
						alerte.setId_alerte(String.valueOf(resultat1
								.getInt("id_alerte")));
						alerte.setId_article(String.valueOf(resultat1
								.getInt("id_article")));
						alerte.setDate_creation(resultat1
								.getString("date_creation"));
						alerte.setText_alerte(resultat1
								.getString("text_alerte"));
						alerte.setId_controleur(resultat1
								.getString("id_controleur"));
						alerte.setNumeroEmplacement(resultat1
								.getString("numeroEmplacement"));
						alerte.setAnexercice(resultat1.getString("anexercice"));
						alerte.setNumRedevable(resultat1
								.getString("numRedevable"));
						alerte.setNumero_ef(String.valueOf(resultat1
								.getInt("numero_ef")));
						alerte.setCodeEmplacement(String.valueOf(resultat1
								.getInt("codeEmplacement")));
						alerte.setCodeType(resultat1.getString("typeTaxe"));
						alerte.setNomRedevable(resultat1
								.getString("nomRedevable"));
						alerte.setPrenomRedevable(resultat1
								.getString("prenomRedevable"));
						alerte.setCiviliteRedevable(resultat1
								.getString("civilite"));
						String numRue = resultat1.getString("numeroRue");
						String adresse1 = resultat1.getString("adresse1");
						String adresse2 = resultat1.getString("adresse2");
						String adresse3 = resultat1.getString("adresse3");
						if (numRue == null)
							numRue = " ";
						if (adresse1 == null)
							adresse1 = " ";
						if (adresse2 == null)
							adresse2 = " ";
						if (adresse3 == null)
							adresse3 = " ";

						String adresse = numRue + " " + adresse1 + " "
								+ adresse3 + " " + adresse2;
						alerte.setAdresseOuvrage(adresse);

						alerte.setNomPrenomControleur(resultat1
								.getString("nom")
								+ " " + resultat1.getString("prenom"));
						alerte
								.setEtat_alerte(resultat1
										.getString("etatAlerte"));
						alerte.setDateFinAlerte(resultat1
								.getString("dateFinAlerte"));
						alerte.setQuartier(resultat1.getString("quartier"));

						// if (!
						// idAlertes.contains(String.valueOf(resultat1.getInt("id_alerte"))))
						// params.addElement(alerte);
						// idAlertes.addElement(String.valueOf(resultat1.getInt("id_alerte")));
						if (!idAlertes.contains(String.valueOf(resultat1
								.getInt("id_alerte")))) {
							idAlertes.addElement(String.valueOf(resultat1
									.getInt("id_alerte")));
							params.addElement(alerte);
						}
					}
				}

				// ///////Selection desa alertes spontanne
				// instruction = con.connexion.createStatement();
				if (typeAlerteRech.equalsIgnoreCase("TOUS")
						&& (liedossier.equalsIgnoreCase("TOUS") || liedossier
								.equalsIgnoreCase("SansDossier"))) {
					r1 = " SELECT DISTINCT(alerte.id_alerte),alerte.*, utilisateur.nom, utilisateur.prenom "
							+ " FROM alerte,utilisateur "
							+ " WHERE  utilisateur.numeroUser = alerte.id_controleur "
							+ " AND alerte.id_article= 0";
				} else {
					r1 = " SELECT DISTINCT(alerte.id_alerte),alerte.*, utilisateur.nom, utilisateur.prenom "
							+ " FROM alerte,utilisateur,article,bareme,imputation "
							+ " WHERE  utilisateur.numeroUser = alerte.id_controleur "
							+ " AND    alerte.id_article=article.id_article "
							+ " AND  article.codebareme =bareme.code "
							+ " AND bareme.idImputation=imputation.code "
							+ " AND imputation.libelle='"
							+ typeAlerteRech
							+ "'";
				}

				if (nomQuartier.length() != 0)
					r1 = r1 + " AND alerte.quartier ='" + nomQuartier + "'";

				if (!etatAlerteRech.equalsIgnoreCase("TOUS"))
					r1 = r1 + " AND alerte.etatAlerte LIKE'" + etatAlerteRech
							+ "'";

				if (numeroAlerteRech.length() != 0)
					r1 = r1 + " AND id_alerte =" + numeroAlerteRech;
				if (dateCreationAlerteRech.length() != 0)
					r1 = r1 + " AND alerte.date_creation ='"
							+ dateCreationAlerteRech + "'";
				if (nomControleurRech.length() != 0)
					r1 = r1
							+ " AND CONCAT(utilisateur.prenom,' ',utilisateur.nom) like '%"
							+ nomControleurRech + "%'";

				if (numeroRue.length() != 0)
					r1 = r1 + " AND alerte.numRue LIKE '%" + numeroRue + "%'";

				if (nomRue.length() != 0)
					r1 = r1 + " AND alerte.nomRue LIKE '%" + nomRue + "%'";

				r1 = r1 + " ORDER BY alerte.id_alerte DESC";

				debug.logger.debug(r1);
				resultat1 = instruction.executeQuery(r1);
				int f = 1;
				while (resultat1.next()) {

					Alerte alerte = new Alerte();
					alerte.setId_alerte(String.valueOf(resultat1
							.getInt("id_alerte")));
					alerte.setDate_creation(resultat1
							.getString("date_creation"));
					alerte.setText_alerte(resultat1.getString("text_alerte"));
					alerte.setId_controleur(resultat1
							.getString("id_controleur"));
					alerte.setNomPrenomControleur(resultat1.getString("nom")
							+ " " + resultat1.getString("prenom"));
					alerte.setEtat_alerte(resultat1.getString("etatAlerte"));
					alerte.setDateFinAlerte(resultat1
							.getString("dateFinAlerte"));
					alerte.setQuartier(resultat1.getString("quartier"));
					alerte.setNomRedevable("-");
					alerte.setCodeType("-");
					alerte.setId_article("-");
					String numRue = resultat1.getString("numRue");
					String adresse = numRue + " "
							+ resultat1.getString("nomRue");
					alerte.setAdresseOuvrage(adresse);
					if (!idAlertes.contains(String.valueOf(resultat1
							.getInt("id_alerte"))))
						params.addElement(alerte);
					f++;
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

	public Vector getAlerte(String idAlerte) {
		Vector params = null;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1 = " SELECT alerte.*,utilisateur.nom,utilisateur.prenom "
						+ " FROM alerte,utilisateur  "
						+ " WHERE alerte.id_alerte ="
						+ idAlerte
						+ " AND utilisateur.numeroUser = alerte.id_controleur  ";
				// " AND alerte.etatAlerte='ENCOURS'" ;
				debug.logger.debug(r1);
				ResultSet resultat1 = instruction.executeQuery(r1);
				params = new Vector();
				while (resultat1.next()) {
					Alerte alerte = new Alerte();
					alerte.setId_alerte(String.valueOf(resultat1
							.getInt("id_alerte")));
					alerte.setDate_creation(resultat1
							.getString("date_creation"));
					alerte.setText_alerte(resultat1.getString("text_alerte"));
					alerte.setEtat_alerte(resultat1.getString("etatAlerte"));
					alerte.setRemarque(resultat1.getString("remarque"));
					alerte.setDateFinAlerte(resultat1
							.getString("dateFinAlerte"));
					alerte.setQuartier(resultat1.getString("quartier"));

					alerte.setId_controleur(String.valueOf(resultat1
							.getInt("id_controleur")));
					alerte.setNomPrenomControleur(resultat1.getString("nom")
							+ " " + resultat1.getString("prenom"));
					alerte.setAdresseOuvrage(resultat1.getString("numRue")
							+ " " + resultat1.getString("nomRue"));
					params.addElement(alerte);
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

	public Vector getAlerteFromIdArticle(String idArticle, String idAlerte) {
		Vector params = null;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1 = " SELECT alerte.*,utilisateur.nom,utilisateur.prenom "
						+ " FROM alerte,utilisateur  "
						+ " WHERE alerte.id_article ="
						+ idArticle
						+ " AND utilisateur.numeroUser = alerte.id_controleur  ";
				// " AND alerte.etatAlerte='ENCOURS'" ;

				if (idAlerte != null && idAlerte.length() != 0)
					r1 = r1 + " AND id_alerte=" + idAlerte;
				else
					r1 = r1 + " ORDER BY alerte.id_alerte DESC";
				debug.logger.debug(r1);
				ResultSet resultat1 = instruction.executeQuery(r1);
				params = new Vector();
				while (resultat1.next()) {

					// params.addElement(String.valueOf(resultat1.getInt("id_alerte")));
					// params.addElement(resultat1.getString("date_creation"));
					// params.addElement(resultat1.getString("text_alerte"));
					// params.addElement(String.valueOf(resultat1.getInt("id_controleur")));
					// params.addElement(resultat1.getString("nom")+" "+
					// resultat1.getString("prenom"));

					Alerte alerte = new Alerte();
					alerte.setId_alerte(String.valueOf(resultat1
							.getInt("id_alerte")));
					alerte.setDate_creation(resultat1
							.getString("date_creation"));
					alerte.setText_alerte(resultat1.getString("text_alerte"));
					alerte.setEtat_alerte(resultat1.getString("etatAlerte"));
					alerte.setRemarque(resultat1.getString("remarque"));
					alerte.setDateFinAlerte(resultat1
							.getString("dateFinAlerte"));

					alerte.setId_controleur(String.valueOf(resultat1
							.getInt("id_controleur")));
					alerte.setNomPrenomControleur(resultat1.getString("nom")
							+ " " + resultat1.getString("prenom"));
					params.addElement(alerte);
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

	public Vector getTousLesEtatsAlerte() {
		Vector res = new Vector();

		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction
						.executeQuery("SELECT parametre.valeur FROM parametre"
								+ " WHERE parametre.type='etatAlerte'");
				while (resultat.next()) {
					res.addElement(resultat.getString("valeur"));
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

	public void supprimerAlerte(int idAlerte) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r = "DELETE from alerte WHERE alerte.id_alerte="
						+ idAlerte;
				debug.logger.debug(r);
				int result1 = instruction.executeUpdate(r);
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}

	public void majAlerte(int idAlerte, String etatAlerte,
			String remarqueAlerte, String dateFinAlerte, String textAlerte) {
		remarqueAlerte = FonctionCommun.ajouterAntislash(remarqueAlerte);
		textAlerte = FonctionCommun.ajouterAntislash(textAlerte);

		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r = " UPDATE alerte set remarque ='" + remarqueAlerte
						+ "',etatAlerte='" + etatAlerte + "'"
						+ ",dateFinAlerte='" + dateFinAlerte + "'"
						+ ",text_alerte='" + textAlerte + "'"
						+ "  WHERE id_alerte =" + idAlerte;
				debug.logger.debug(r);
				int result1 = instruction.executeUpdate(r);
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}

	public void transfertImageAlerteEmplacement(int idAlerte, int idEmplacement) {
		debug.logger.debug("Transfert d'image de alerte nuemro= " + idAlerte
				+ " a l'emplacement numero= " + idEmplacement);
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r = "	 SELECT image_alerte.idImage "
						+ "  FROM image_alerte "
						+ "  WHERE image_alerte.IdAlerte =" + idAlerte;
				debug.logger.debug(r);
				ResultSet resultat1 = instruction.executeQuery(r);
				while (resultat1.next()) {
					// Ajout des images de l'alerte a l'emplacement
					int idImage = resultat1.getInt("idImage");
					debug.logger.debug("Transfert idImage=" + idImage);
					String req2 = " INSERT into image_emplacementgeneral "
							+ " (idImage,idEmplacement) " + " VALUE ("
							+ idImage + "," + idEmplacement + ")";
					debug.logger.debug(req2);
					Statement instruction2 = con.connexion.createStatement();
					int result2 = instruction2.executeUpdate(req2);

					// Suppression des images apres le transfert vers
					// emplacement
					String req3 = "DELETE from image_alerte WHERE IdAlerte="
							+ idAlerte;
					debug.logger.debug(req3);
					Statement instruction3 = con.connexion.createStatement();
					int result3 = instruction3.executeUpdate(req3);
				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage() + e.getMessage() + e.getCause());
		}
	}

	public void supprimerImageAlerte(String nomImage) {
		int idImage = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String cheminPhoto = fichierDeConfiguration
						.getCheminPhotosEmplacement()
						+ nomImage;
				nomImage = FonctionCommun.ajouterAntislash(nomImage);
				String req1 = "SELECT idImage from image WHERE nomImage='"
						+ nomImage + "'";
				debug.logger.debug(req1);
				ResultSet resultat1 = instruction.executeQuery(req1);
				while (resultat1.next()) {
					idImage = resultat1.getInt("idImage");
				}

				if (idImage != 0) {
					String req = "delete from image_alerte WHERE idImage="
							+ idImage;
					debug.logger.debug(req);
					int result1 = instruction.executeUpdate(req);
					req = "delete from image WHERE idImage=" + idImage;
					result1 = instruction.executeUpdate(req);
					debug.logger.debug(req);
					deleteFile(cheminPhoto);
				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.debug(e.getMessage());
		}
	}

	public void deleteFile(String name) {
		try {
			debug.logger.debug("Suppresion de l image " + name);
			java.io.File file = new java.io.File(name);
			if (file.exists()) {
				file.delete();
			} else {
				debug.logger.fatal("impossible de suprimer le fichier " + name
						+ " , il n existe pas");
				// javax.swing.JOptionPane.showMessageDialog(null,
				// "Le fichier n'existe pas !");
			}
		} catch (Exception e) {
			debug.logger.debug("Impossible de supprimer le fichier " + name
					+ e.getMessage() + e.getCause());
		}
	}

	public Vector getTableauImagesAlerte(String idAlerte) {
		Vector contenu = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req1 = " SELECT image.* " + " FROM image,image_alerte "
						+ " WHERE image.idImage = image_alerte.idImage "
						+ " AND image_alerte.IdAlerte = " + idAlerte;
				debug.logger.debug(req1);
				ResultSet resultat1 = instruction.executeQuery(req1);
				while (resultat1.next()) {
					Image image = new Image();
					image.setNomImage(resultat1.getString("nomImage"));
					image.setDateCreationImage(resultat1
							.getString("dateCreation"));
					contenu.addElement(image);
				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return contenu;
	}
}
