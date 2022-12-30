package fr.analogon.r2t.request;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Article;
import fr.analogon.r2t.pojo.Emplacement;
import fr.analogon.r2t.pojo.Facture;

public class RequestFacture extends Request {

	//
	public Vector getfacturePdfPourSuiviePayement(int idUtilisateur,
			String etatFacture, String anneeFacture) {
		Vector res = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1 = " SELECT facture.dateCreation,facture.numeroTitre, facture.anneeEx "
						+ ",facture.etat,facture.numeroFacture "
						+ ",facture.solde,facture.montantTotal "
						+ ",batchtraitement.numeroBatchTraitement,"
						+ " batchtraitement.anExercice, batchtraitement.dateExecution,"
						+ " batchtraitement.typeEmplacement "
						+ " FROM facture, batchtraitement "
						+ " WHERE  facture.idBatchTraitement = batchtraitement.numeroBatchTraitement"
						+ " AND batchtraitement.valide='true'"
						+ " AND facture.etat !='ANNULEE' AND facture.etat !='preRefacturation' AND facture.etat !='preFacturation'"
						+ " AND facture.idClient=" + idUtilisateur;
				
				
				if (anneeFacture.length() != 0)
					r1 = r1 + " AND facture.anneeEx = '"+anneeFacture+"'" ;
				
				if (etatFacture.equalsIgnoreCase("paye"))
					r1 = r1 + " AND facture.solde = 0   ";
				else
					r1 = r1 + " AND facture.solde != 0    ";

				r1 = r1 + " ORDER BY facture.numeroFacture , anneeEx DESC";

				debug.logger.debug(r1);
				ResultSet resultat1 = instruction.executeQuery(r1);
				while (resultat1.next()) {
					Facture facture = new Facture();
					facture.setNumeroFacture(resultat1.getInt("numeroFacture"));
					facture.setNomDossier(resultat1.getString("anExercice")
							+ "-"
							+ resultat1.getString("numeroBatchTraitement"));
					facture.setDateCreationFacture(resultat1
							.getString("dateCreation"));
					facture.setTypeTaxe(resultat1.getString("typeEmplacement"));
					facture.setNumeroTitre(resultat1.getString("numeroTitre"));
					facture.setEtat(resultat1.getString("etat"));
					facture.setMontantTotalFacture(resultat1
							.getString("montantTotal"));
					facture.setSoldeFacture(resultat1.getString("solde"));
					// debug.logger.debug("RES="+resultat1.getInt("numeroFacture"));
					res.addElement(facture);
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

	public Vector getfacturePdfUtilisateur(int idUtilisateur, String etatFacture, String anneeFacture, boolean annulle)
	{
		Vector res = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1 = " SELECT facture.dateCreation,facture.numeroTitre, facture.anneeEx"
						+ ",facture.etat,facture.numeroFacture "
						+ ",facture.solde,facture.montantTotal "
						+ ",batchtraitement.numeroBatchTraitement,"
						+ " batchtraitement.anExercice, batchtraitement.dateExecution,"
						+ " batchtraitement.typeEmplacement "
						+ " FROM facture, batchtraitement "
						+ " WHERE  facture.idBatchTraitement = batchtraitement.numeroBatchTraitement"
						+ " AND batchtraitement.valide='true'"
						+ " AND facture.idClient=" + idUtilisateur;

				if (annulle == true)
					r1 = r1 + " AND facture.etat = 'ANNULEE'   ";
				else					
					r1 = r1 + " AND facture.etat != 'ANNULEE'  AND facture.etat != 'preRefacturation' AND facture.etat != 'preFacturation'";
				
				if (etatFacture.equalsIgnoreCase("paye"))
					r1 = r1 + " AND facture.solde = 0   ";
				else
					r1 = r1 + " AND facture.solde != 0    ";

				if (anneeFacture.length() != 0)
					r1 = r1 + " AND facture.anneeEx ='"+anneeFacture+"'";
				
				
				// Cas des factures soldes mais on a une reclamation en cours
				r1 = r1
						+ " UNION "
						+ " ( SELECT facture.dateCreation, facture.anneeEx,facture.numeroTitre"
						+ ",facture.etat,facture.numeroFacture "
						+ ",facture.solde,facture.montantTotal "
						+ ",batchtraitement.numeroBatchTraitement,"
						+ " batchtraitement.anExercice, batchtraitement.dateExecution,"
						+ " batchtraitement.typeEmplacement "
						+ " FROM facture, batchtraitement, reclamation "
						+ " WHERE reclamation.idFacture = facture.numeroFacture "
						+ " AND reclamation.etat='ENCOURS' "
						+ " AND facture.idBatchTraitement = batchtraitement.numeroBatchTraitement"
						+ " AND batchtraitement.valide='true'"
						+ " AND facture.etat !='ANNULEE' AND facture.etat !='preRefacturation' AND facture.etat != 'preFacturation'"
						+ " AND facture.idClient=" + idUtilisateur;
				if (etatFacture.equalsIgnoreCase("paye"))
					r1 = r1 + " AND facture.solde = 0  )  ";
				else
					r1 = r1 + " AND facture.solde != 0 )   ";

				r1 = r1 + " ORDER BY numeroFacture , anneeEx DESC";
				debug.logger.debug(r1);
				ResultSet resultat1 = instruction.executeQuery(r1);
				while (resultat1.next()) {
					Facture facture = new Facture();
					facture.setNumeroFacture(resultat1.getInt("numeroFacture"));
					facture.setNomDossier(resultat1.getString("anExercice")
							+ "-"
							+ resultat1.getString("numeroBatchTraitement"));
					facture.setDateCreationFacture(resultat1
							.getString("dateCreation"));
					facture.setTypeTaxe(resultat1.getString("typeEmplacement"));
					facture.setNumeroTitre(resultat1.getString("numeroTitre"));
					facture.setEtat(resultat1.getString("etat"));
					facture.setMontantTotalFacture(resultat1
							.getString("montantTotal"));
					facture.setSoldeFacture(resultat1.getString("solde"));
					// debug.logger.debug("RES="+resultat1.getInt("numeroFacture"));
					instruction = con.connexion.createStatement();
					String r2 = " SELECT idReclamation, etat FROM reclamation "
							+ " WHERE  idFacture="
							+ resultat1.getInt("numeroFacture");
					debug.logger.debug(r2);
					ResultSet resultat2 = instruction.executeQuery(r2);
					boolean factureAUneReclamation = false;
					while (resultat2.next()) {
						factureAUneReclamation = true;
						// debug.logger.debug("idReclamation"+resultat2.getString("idReclamation"));
						facture.setIdReclamation(resultat2
								.getString("idReclamation"));
						facture.setEtatReclamation(resultat2.getString("etat"));
						Facture ff = new Facture();
						ff.setNumeroFacture(facture.getNumeroFacture());
						ff.setNomDossier(facture.getNomDossier());
						ff.setDateCreationFacture(facture
								.getDateCreationFacture());
						ff.setTypeTaxe(facture.getTypeTaxe());
						ff.setNumeroTitre(facture.getNumeroTitre());
						ff.setEtat(facture.getEtat());
						ff.setMontantTotalFacture(facture
								.getMontantTotalFacture());
						ff.setSoldeFacture(facture.getMontantTotalFacture());
						ff.setIdReclamation(resultat2
								.getString("idReclamation"));
						ff.setEtatReclamation(resultat2.getString("etat"));
						;
						res.addElement(ff);
					}
					if (!factureAUneReclamation)
						res.addElement(facture);
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

	public Vector getListeDesfacture(String numeroFacture,
			String numeroRedevable, String typeTaxe, String etatFacture,
			String dateCreationFacture, String nomRedevable,
			String montantFacture,String anneeFacture) {
		Vector res = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1 = " SELECT facture.motifAnnulation,facture.dateCreation ,facture.montantTotal ,"
						+ " facture.idFacture, facture.anneeEx, facture.etat, facture.numeroFacture, " 
						+ " facture.idClient, "
						+ " batchtraitement.numeroBatchTraitement,"
						+ " batchtraitement.anExercice, batchtraitement.dateExecution,facture.typeTaxe, "
						+ " batchtraitement.typeEmplacement , redevable.nomRedevable,redevable.prenom "
						
						+ " FROM facture, batchtraitement , redevable "
						
						+ " WHERE  facture.idBatchTraitement = batchtraitement.numeroBatchTraitement"
						+ " AND batchtraitement.valide='true'"
						+ " AND redevable.numRedevable=facture.idClient" +

						" AND batchtraitement.etape ='facturation'   " ;						

				if (nomRedevable.length() != 0)
					r1 = r1
							+ " AND Concat(redevable.nomRedevable,\" \",redevable.prenom) LIKE '%"
							+ nomRedevable + "%'";

				if (montantFacture.length() != 0)
					r1 = r1 + " AND facture.montantTotal=" + montantFacture;
				
				if ( anneeFacture.length() != 0)
					r1 = r1 + " AND facture.anneeEx=" + anneeFacture;

				if (numeroFacture.length() != 0)
					r1 = r1 + " AND facture.numeroFacture=" + numeroFacture;
				if (numeroRedevable.length() != 0)
					r1 = r1 + " AND facture.idClient=" + numeroRedevable;
				if (typeTaxe.length() != 0)
					r1 = r1 + " AND facture.typeTaxe='" + typeTaxe + "'";
				
				if (etatFacture.length() != 0)
				{	
					if (etatFacture.equalsIgnoreCase("PAYEE"))
						r1 = r1 + " AND facture.solde=0";
					
					else if (etatFacture.equalsIgnoreCase("IMPAYE"))
						r1 = r1 + " AND facture.solde!= 0 AND facture.etat = 'VALIDE'";
					else
						r1 = r1 + " AND facture.etat='" + etatFacture + "'";
					
						
				}
				
				
				
				
				if (dateCreationFacture.length() != 0)
					r1 = r1 + " AND facture.dateCreation='"
							+ dateCreationFacture + "'";

				
				r1 = r1 + "  ORDER BY facture.typeTaxe ,facture.anneeEx DESC";

				debug.logger.debug(r1);
				ResultSet resultat1 = instruction.executeQuery(r1);
				while (resultat1.next()) {
					Facture facture = new Facture();
					facture.setNumeroFacture(resultat1.getInt("numeroFacture"));
					facture.setNomDossier(resultat1.getString("anExercice")
							+ "-"
							+ resultat1.getString("numeroBatchTraitement"));
					facture.setDateCreationFacture(resultat1
							.getString("dateCreation"));
					facture.setTypeTaxe(resultat1.getString("typeEmplacement"));
					facture.setIdClient(resultat1.getInt("idClient"));
					facture.setEtat(resultat1.getString("etat"));
					facture.setMontantTotalFacture(resultat1.getString("montantTotal"));	
					facture.setNomPrenomClient(resultat1.getString("nomRedevable") +  " " +resultat1.getString("prenom"));
					facture.setMotifAnnulationFacture(resultat1
							.getString("motifAnnulation"));
					facture.setIdFacture(resultat1.getInt("idFacture"));
					res.addElement(facture);
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

	public Vector getEtatFacture() {
		Vector params = null;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1 = " SELECT valeur " + " FROM parametre "
						+ " WHERE type='etat_facture' ";
				debug.logger.debug(r1);
				ResultSet resultat1 = instruction.executeQuery(r1);
				params = new Vector();
				while (resultat1.next()) {
					params.addElement(resultat1.getString("valeur"));
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

	public void annulerFacture(String numeroFacture, String motifAnnulation) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				motifAnnulation = FonctionCommun
						.ajouterAntislash(motifAnnulation);
				String r = " UPDATE facture set etat ='ANNULEE' ,"
						+ " dateAnnulation='"
						+ GestionDate.getDateAujourdhuiString() + "',"
						+ " source='BA-'," + " envoyerAPleiade='false',"
						+ " motifAnnulation='" + motifAnnulation + "'"
						+ " WHERE  facture.numeroFacture =" + numeroFacture;
				debug.logger.debug(r);
				int result1 = instruction.executeUpdate(r);
				new RequestAdmin().InsertAction(
						"Annulation de la facture numero" + numeroFacture,
						GestionDate.getDateTime());
			} catch (Exception e) {
				debug.logger.fatal("Erreur lors d'annulation de la facture"
						+ e.getMessage() + e.getMessage() + e.getCause());
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal("Erreur lors d'annulation de la facture"
					+ e.getMessage() + e.getMessage() + e.getCause());
		}
	}
	
	
	public void deleteFacture(String idFacture) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r = " delete facture From facture WHERE  facture.idFacture =" + idFacture;
				debug.logger.debug(r);
				int result1 = instruction.executeUpdate(r);
				
			} catch (Exception e) {
				debug.logger.fatal("Erreur lors de suppresion de  la facture factice"
						+ e.getMessage() + e.getMessage() + e.getCause());
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal("Erreur lors de suppresion de  la facture factice"
					+ e.getMessage() + e.getMessage() + e.getCause());
		}
	}

	
	
	public void supprimerFacture(String numeroFacture) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				
				String r = " DELETE FROM  facture WHERE  facture.numeroFacture =" + numeroFacture;
				debug.logger.debug(r);
				int result1 = instruction.executeUpdate(r);
				
			} catch (Exception e) {
				debug.logger.fatal("Erreur lors de la suppresion d'une facture factice "
						+ e.getMessage() + e.getMessage() + e.getCause());
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal("Erreur lors d'annulation de la facture"
					+ e.getMessage() + e.getMessage() + e.getCause());
		}
	}

	
	
	
	
	public Vector getTousLesEtatsFacture() {
		Vector res = new Vector();

		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				instruction = con.connexion.createStatement();
				ResultSet resultat = instruction
						.executeQuery("SELECT parametre.valeur FROM parametre"
								+ " WHERE parametre.type='etat_facture'");
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

	public Facture getFacture(String numeroFacture) {
		Facture res = new Facture();

		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String req = "SELECT DISTINCT(lignefacture.idArticle),facture.numeroTitre , facture.numeroFacture ,facture.dateAnnulation , facture.anneeTitre, "
						+ " facture.motifAnnulation, bareme.libelle , facture.etat ,"
						+ " article.nom, facture.dateCreation,facture.solde,facture.montantTotal, "
						+ " redevable.numRedevable, facture.typeTaxe,facture.idBatchTraitement "
						+ " ,CONCAT(redevable.civilite,' ', redevable.nomRedevable ,' ',redevable.prenom ) as nomRedvable"
						+ " ,CONCAT(facture.anneeEx,'-',facture.idBatchTraitement) as nomDossier"
						+ " FROM lignefacture, article ,facture ,redevable,bareme "
						+ " WHERE lignefacture.idFacture='"
						+ numeroFacture
						+ "'"
						+ " AND article.id_article = lignefacture.idArticle"
						+ " AND facture.idClient = redevable.numRedevable"
						+ " AND facture.numeroFacture = lignefacture.idFacture"
						+ " AND bareme.code= article.codebareme ";
				// " AND bareme.idImputation = imputation.idImputation" +
				// " AND imputation.anneeExercice= " +
				// GestionDate.getAnneeFromDateString(date);

				//debug.logger.debug(req);
				ResultSet resultat = instruction.executeQuery(req);
				Vector v = new Vector();
				while (resultat.next()) {
					Article a = new Article();
					a.setId(String.valueOf(resultat.getInt("idArticle")));
					a.setNom(resultat.getString("nom"));
					a.setBareme((resultat.getString("libelle")));
					v.addElement(a);

					res.setNumeroFacture(resultat.getInt("numeroFacture"));
					res.setIdClient(resultat.getInt("numRedevable"));
					res.setDateAnnulation(resultat.getString("dateAnnulation"));
					res.setNomPrenomClient(resultat.getString("nomRedvable"));
					res.setNomDossier(resultat.getString("nomDossier"));
					res.setMontantTotalFacture(resultat.getString("montantTotal"));
					res.setSoldeFacture(resultat.getString("solde"));
					res.setTypeTaxe(resultat.getString("typeTaxe"));
					res.setMotifAnnulationFacture(resultat.getString("motifAnnulation"));
					res.setEtat(resultat.getString("etat"));
					res.setIdBatch(resultat.getString("idBatchTraitement"));
					res.setDateCreationFacture(resultat
							.getString("dateCreation"));
					res.setAnneeTitre(resultat.getString("anneeTitre"));
					res.setNumeroTitre(resultat.getString("numeroTitre"));
				}
				res.setListeOuvrage(v);
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

	public Facture getFactureFromNumeroFacture(String numeroFacture) {
		Facture facture = new Facture();

		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				instruction = con.connexion.createStatement();
				String req = " SELECT facture.solde, facture.idFacture "
						+ " FROM facture " + " WHERE facture.numeroFacture= ' "
						+ numeroFacture + "'";

				debug.logger.debug(req);
				ResultSet resultat = instruction.executeQuery(req);
				while (resultat.next()) {
					facture.setSoldeFacture(resultat.getString("solde"));
					facture.setIdFacture(resultat.getInt("idFacture"));
				}
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return facture;
	}

	public Facture getFactureFromIdFacture(String idFacture) {
		Facture facture = new Facture();

		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String req = " SELECT facture.solde, facture.idFacture "
						+ " FROM facture " + " WHERE facture.idFacture= ' "
						+ idFacture + "'";

				debug.logger.debug(req);
				ResultSet resultat = instruction.executeQuery(req);
				while (resultat.next()) {
					facture.setSoldeFacture(resultat.getString("solde"));
					facture.setIdFacture(resultat.getInt("idFacture"));
				}
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return facture;
	}

	public Facture getFacture(String numeroTitre, int anneeTitre) {
		// debug.logger.debug("GET facture from numeroFacture et annne Titire");
		Facture res = new Facture();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction
						.executeQuery("SELECT numeroFacture  FROM facture  "
								+ " WHERE anneeTitre='" + anneeTitre + "'"
								+ " AND numeroTitre ='" + numeroTitre + "'");
				int numeroFacture = 0;
				while (resultat.next()) {
					numeroFacture = resultat.getInt("numeroFacture");
				}
				// debug.logger.debug("numeroFacture>>>>>"+numeroFacture);
				res = getFacture("" + numeroFacture);
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

	public int getNombreDeFactureCreeParUnBatch(int idBatchTraitement) {
		int res = 0;
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String requete = "SELECT count(*) as resultat  FROM facture  WHERE idBatchTraitement="
						+ idBatchTraitement;
				debug.logger.debug(requete);
				ResultSet resultat = instruction.executeQuery(requete);
				while (resultat.next()) {
					res = resultat.getInt("resultat");
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

	public Vector getListeDesfacture(int numeroBatch) {
		Vector res = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1 = " SELECT DISTINCT(facture.idFacture), facture.*,redevable.codePostal "
						+ " FROM facture , redevable "
						+ " WHERE facture.idClient = redevable.numRedevable "
						+ " AND facture.idBatchTraitement="
						+ numeroBatch
						+ " ORDER BY codePostal ";

				debug.logger.debug(r1);
				ResultSet resultat1 = instruction.executeQuery(r1);
				while (resultat1.next()) {
					Facture facture = new Facture();
					facture.setNumeroFacture(resultat1.getInt("numeroFacture"));
					facture.setNomDossier(resultat1.getString("anneeEx") + "-"
							+ resultat1.getString("idBatchTraitement"));
					facture.setDateCreationFacture(resultat1
							.getString("dateCreation"));
					facture.setTypeTaxe(resultat1.getString("typeTaxe"));
					facture.setIdClient(resultat1.getInt("idClient"));
					facture.setEtat(resultat1.getString("etat"));
					facture.setIdFacture(resultat1.getInt("idFacture"));
					res.addElement(facture);
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

	public static String generateRandomNumeroTitre() {
		String res = "";
		for (int i = 0; i < 8; i++) {
			int k = Double.valueOf(Math.random() * 10).intValue();
			res = res + k;
		}
		return (res);
	}

	public static String generateRandomSolde() {
		String res = "";
		int j1 = Double.valueOf(Math.random() * 10).intValue();
		int j2 = Double.valueOf(Math.random() * 10).intValue();
		int j3 = Double.valueOf(Math.random() * 10).intValue();
		int j4 = Double.valueOf(Math.random() * 10).intValue();
		res = String.valueOf(j1) + String.valueOf(j2) + "."
				+ String.valueOf(j3) + String.valueOf(j4) + "        ";
		// System.out.println(res.length());
		return (res);
	}

	public int getNombreDeFactureAnnuleAEnvoyerToPleiade(String typeTaxe) {
		int res = 0;
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String requete = " SELECT count(*) as resultat  FROM facture"
						+ " WHERE typeTaxe='" + typeTaxe + "'"
						+ " AND facture.etat='ANNULEE'"
						+ " AND envoyerAPleiade='false'  ";
				debug.logger.debug(requete);
				ResultSet resultat = instruction.executeQuery(requete);
				while (resultat.next()) {
					res = resultat.getInt("resultat");
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

	// /////////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////////////

	public Vector getListeFactureUtilsateurPdfNonPaye(int idUtilisateur,
			String etatFacture , String  anneeFacture) {
		Vector res = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1 = " SELECT facture.dateCreation,facture.numeroTitre, facture.anneeEx "
						+ ",facture.etat,facture.numeroFacture,facture.envoyerALaTresorie  "
						+ ",facture.solde,facture.montantTotal "
						+ ",batchtraitement.numeroBatchTraitement,"
						+ " batchtraitement.anExercice, batchtraitement.dateExecution,"
						+ " batchtraitement.typeEmplacement "
						+ " FROM facture, batchtraitement "
						+ " WHERE  facture.idBatchTraitement = batchtraitement.numeroBatchTraitement"
						+ " AND batchtraitement.valide='true'"
						+ " AND facture.etat !='ANNULEE' AND facture.etat !='preRefacturation' AND facture.etat != 'preFacturation' "
						+ " AND facture.idClient=" + idUtilisateur;

				
				
				
				if (anneeFacture.length() != 0)
					r1 = r1 + " AND facture.anneeEx = '"+anneeFacture+"'" ;
				
								
				
				if (etatFacture.equalsIgnoreCase("paye"))
					r1 = r1 + " AND facture.solde = 0   ";
				else
					r1 = r1 + " AND facture.solde != 0    ";

				r1 = r1 + " ORDER BY facture.numeroFacture , facture.anneeEx DESC";
				debug.logger.debug(r1);
				ResultSet resultat1 = instruction.executeQuery(r1);
				while (resultat1.next()) {
					Facture facture = new Facture();
					facture.setNumeroFacture(resultat1.getInt("numeroFacture"));
					facture.setNomDossier(resultat1.getString("anExercice")
							+ "-"
							+ resultat1.getString("numeroBatchTraitement"));
					facture.setDateCreationFacture(resultat1
							.getString("dateCreation"));
					facture.setTypeTaxe(resultat1.getString("typeEmplacement"));
					facture.setNumeroTitre(resultat1.getString("numeroTitre"));
					facture.setEtat(resultat1.getString("etat"));					
					facture.setEnvoyerALaTresorie(resultat1.getString("envoyerALaTresorie"));
					facture.setMontantTotalFacture(resultat1
							.getString("montantTotal"));
					facture.setSoldeFacture(resultat1.getString("solde"));
					// debug.logger.debug("RES="+resultat1.getInt("numeroFacture"));
					res.addElement(facture);
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

	public void updateFacture(String idFacture, String soldeFacture) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {

				String r = " UPDATE facture set facture.solde =" + soldeFacture
						+ " WHERE `facture`.`idFacture`=" + idFacture;
				debug.logger.debug(r);
				int result1 = instruction.executeUpdate(r);

			} catch (Exception e) {
				debug.logger.fatal("Erreur ");
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal("Erreur ");
		}
	}

	public void validerChampsFactureEnvoyerAPleiade(String numeroFacture) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r = " UPDATE facture set envoyerAPleiade ='true' "
						+ " WHERE numeroFacture=" + numeroFacture;
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

	public void validerChampsFactureEnvoyerALaTresorie(String numeroFacture) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r = " UPDATE facture set envoyerALaTresorie ='true' "
						+ " WHERE numeroFacture=" + numeroFacture;
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
	
	
	public Emplacement getEmplacementFromFacture(String numeroFacture) 
	{
		Emplacement emplacement = new Emplacement();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1 = " SELECT emplacementgeneral.* , activiteprofession.code,activiteprofession.libelle as libelleProfeesion " +
						    " FROM facture , lignefacture , elementfacturation ,emplacementgeneral, article,activiteprofession" +
						    
						    " WHERE facture.numeroFacture= " + numeroFacture+
						    " AND facture.numeroFacture = lignefacture.idFacture" +
						    " AND activiteprofession.code =  emplacementgeneral.codePrefession" +
						    " AND lignefacture.idArticle = article.id_article " +
						    " AND  article.id_elementfacturation = elementfacturation.numero " +
						    " AND elementfacturation.numeroEmplacement = emplacementgeneral.numero " +
						    " LIMIT 0, 1 ";
				debug.logger.debug(r1);
				ResultSet resultat1 = instruction.executeQuery(r1);
				while (resultat1.next()) 
				{
					emplacement.setNumEmplacement(resultat1.getString("numero"));
					emplacement.setNumeroEmplacementPersonalise(resultat1.getString("numeroEmplacementPersonalise"));
					emplacement.setCodeSecteur(resultat1.getString("codeSecteur"));
					emplacement.setLibelleProfeesion(resultat1.getString("libelleProfeesion"));
				}
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return emplacement;
	}

	
	
	
	public String  peutAnnulerLaFactue(String numeroFacture) 
	{
		String res = "true";
		RequestFacture requestFacture = new RequestFacture();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1 = " SELECT  lignepayement.* "
						+ " FROM lignepayement, facture , payement "
						+ " Where facture.idFacture = lignepayement.idFacture "
						+ " AND facture.numeroFacture ='"+numeroFacture+"'" 
						+ " AND lignepayement.idPayement = payement.idPayement "
						+ " AND `payement`.`etat` = 'Valide' " ;
				debug.logger.debug(r1);
				//System.out.print(r1);
				ResultSet resultat1 = instruction.executeQuery(r1);
				while (resultat1.next()) 
				{
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
	
	
	
}


