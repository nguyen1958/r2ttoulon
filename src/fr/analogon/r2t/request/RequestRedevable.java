package fr.analogon.r2t.request;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.Redevable;
import fr.analogon.r2t.pojo.Remboursement;
import fr.analogon.r2t.pojo.Rue;

public class RequestRedevable extends Request {

	public Vector getListeRedevable(String numRedevable, String nomRedevable,
			String codeVoie, String raisonSociale, String profession,
			String codeSecteur, String codeEmplacement, String exercice,
			String nomQuartier, String libelleBareme, String typeTaxe,
			String nomVoieEmplacement, String numVoieEmplacement,
			String numEmplacement, String etatEmplacement, String redevbaleActif) {
		Vector res = new Vector();
		// debug.logger.debug("numRedevable>>>>>"+numRedevable);
		try {
			nomRedevable = FonctionCommun.ajouterAntislash(nomRedevable);
			profession = FonctionCommun.ajouterAntislash(profession);
			nomQuartier = FonctionCommun.ajouterAntislash(nomQuartier);
			nomVoieEmplacement = FonctionCommun
					.ajouterAntislash(nomVoieEmplacement);

			String requete = "";
			// Si il y a un filtre de recherche qui touche a l'emplacment
			boolean besoinRecherchParEmplacement = false;
			if (codeVoie.length() != 0 || raisonSociale.length() != 0
					|| codeSecteur.length() != 0
					|| codeEmplacement.length() != 0
					|| nomQuartier.length() != 0 || libelleBareme.length() != 0
					|| typeTaxe.length() != 0
					|| nomVoieEmplacement.length() != 0
					|| numVoieEmplacement.length() != 0
					|| numEmplacement.length() != 0
					|| !etatEmplacement.equalsIgnoreCase("tous"))
				besoinRecherchParEmplacement = true;
			System.out.println("besoinRecherchParEmplacement="+besoinRecherchParEmplacement);
			// //////////////////////////////////////////////////////
			// Si la recherche est a partir du numero du redevable seulement ou
			// aucun
			// filtre de recherche sur l'emplacment est choisi
			if (numRedevable.length() != 0) {
				requete = "SELECT  DISTINCT( redevable.numRedevable),"
						+ " redevable.nomRedevable,redevable.civilite,redevable.numRue "
						+ " ,redevable.prenom,redevable.typeRedevable,redevable.adresse1 "
						+ " FROM redevable,activiteprofession  "
						+ " WHERE redevable.numeroProfession=activiteprofession.code "
						+ "AND numRedevable=" + numRedevable;
			}
			// //////////////////////////////////////////////////////////
			// Si la recherche contient autres filtre de recherche sur des infos
			// de l'emplacment ou de l' ouvrage
			else if (besoinRecherchParEmplacement) {
				requete = " SELECT distinct(emplacementgeneral.numero),"
						+ " redevable.numRedevable,redevable.adresse1,"
						+ " redevable.nomRedevable,redevable.civilite,"
						+ " redevable.prenom,redevable.numRue,redevable.typeRedevable,"
						+ " emplacementgeneral.enActivite,emplacementgeneral.codeVoie, "
						+ " emplacementgeneral.numRue as numRueEmplacement, "
						+ " emplacementgeneral.adresse1 as adresse1Emplacement, "
						+ " emplacementgeneral.adresse2 as adresse2Emplacement,"
						+ " emplacementgeneral.adresse3 as adresse3Emplacement,"
						+ " emplacementgeneral.numero as numeroEmplacment "
						+ " FROM redevable,activiteprofession,emplacementgeneral,imputation";
				if (!libelleBareme.equalsIgnoreCase(""))
					requete = requete + ",bareme,article,elementfacturation";

				requete = requete
						+ " WHERE emplacementgeneral.numRedevable =redevable.numRedevable"
						+ " AND redevable.numeroProfession=activiteprofession.code "
						+ " AND emplacementgeneral.source ='normal' ";

				if (!raisonSociale.equalsIgnoreCase("")) {
					requete = requete
							+ " AND emplacementgeneral.raisonSocial LIKE '%"
							+ raisonSociale + "%'";
				}

				RequestParametres requestParametres = new RequestParametres();
				String ville = requestParametres.getVille();

				if (!numEmplacement.equalsIgnoreCase("")) {
					if (ville.equalsIgnoreCase("toulon"))
						requete = requete
								+ " AND emplacementgeneral.numeroEmplacementPersonalise ='"
								+ numEmplacement + "'";
					else
						requete = requete + " AND emplacementgeneral.numero ='"
								+ numEmplacement + "'";
				}

				if (!typeTaxe.equalsIgnoreCase("")) {
					requete = requete
							+ " AND emplacementgeneral.codeType = imputation.idImputation";
					requete = requete + " AND imputation.libelle ='" + typeTaxe
							+ "'";
				}

				if (!etatEmplacement.equalsIgnoreCase("tous")) {
					requete = requete + " AND emplacementgeneral.enActivite ='"
							+ etatEmplacement + "'";
				}

				if (!codeSecteur.equalsIgnoreCase("")) {
					requete = requete + " AND emplacementgeneral.codeSecteur ="
							+ codeSecteur;
				}

				if (!nomQuartier.equalsIgnoreCase("")) {
					requete = requete
							+ " AND emplacementgeneral.nomQuartier ='"
							+ nomQuartier + "'";
				}

				if (!numVoieEmplacement.equalsIgnoreCase("")) {
					requete = requete + " AND emplacementgeneral.numRue ="
							+ numVoieEmplacement;
				}

				if (!nomVoieEmplacement.equalsIgnoreCase("")) {
					requete = requete
							+ " AND emplacementgeneral.adresse1 LIKE '%"
							+ nomVoieEmplacement + "%'";
				}

				if (!libelleBareme.equalsIgnoreCase("")) {
					requete = requete
							+ " AND article.id_elementfacturation = elementfacturation.numero "
							+ " AND elementfacturation.numeroEmplacement = emplacementgeneral.numero"
							+ " AND article.codebareme= bareme.code"
							+ " AND  bareme.libelle ='" + libelleBareme + "'";
				}

			} else {
				requete = "SELECT  DISTINCT( redevable.numRedevable),"
						+ " redevable.nomRedevable,redevable.civilite,redevable.numRue "
						+ " ,redevable.prenom,redevable.typeRedevable,redevable.adresse1 "
						+ " FROM redevable,activiteprofession "
						+ " WHERE redevable.numeroProfession=activiteprofession.code";
			}

			if (!nomRedevable.equalsIgnoreCase("")) {
				requete = requete
						+ " AND Concat(redevable.nomRedevable,\" \",redevable.prenom) LIKE '%"
						+ nomRedevable + "%'";
			}

			if (!profession.equalsIgnoreCase("")) {
				requete = requete + " AND activiteprofession.libelle = '"
						+ profession + "'";
			}

			if (redevbaleActif.equalsIgnoreCase("OUI")) {
				requete = requete + " AND redevable.actif = 'true'";
			} else if (redevbaleActif.equalsIgnoreCase("NON")) {
				requete = requete + " AND redevable.actif = 'false'";
			}

			// Si on veut regrouper les tulisateurs
			// requete = requete + " GROUP by redevable.numRedevable";
			requete = requete + " ORDER BY redevable.nomRedevable";
			System.out.println("requete="+requete);
			// Execution de la requete
			debug.logger
					.debug("requete de recherche de redevable:\n" + requete);
			try {
				Statement instruction = con.connexion.createStatement();
				try {
					ResultSet resultat1 = instruction.executeQuery(requete);
					while (resultat1.next()) {
						Redevable redevable = new Redevable();
						// //System.out.println("dd" + i);
						// i++;
						redevable.setNumRedevable(resultat1
								.getString("numRedevable"));
						redevable.setNomRedevable(resultat1
								.getString("nomRedevable"));
						redevable.setPrenomRedevable(resultat1
								.getString("prenom"));
						redevable.setType(resultat1.getString("typeRedevable"));
						redevable.setCivilite(resultat1.getString("civilite"));
						redevable.setAdressRedevable(resultat1
								.getString("numRue")
								+ " " + resultat1.getString("adresse1"));
						redevable.setEtatEmplacement(etatEmplacement);
						redevable.setAdressEmplacment("-");

						try {
							String numRueEmplacment = String.valueOf(resultat1
									.getInt("numRueEmplacement"));
							redevable.setNumeroEmplacment(String
									.valueOf(resultat1
											.getInt("numeroEmplacment")));
							String adresse1Emplacment = resultat1
									.getString("adresse1Emplacement");
							String adresse2Emplacment = resultat1
									.getString("adresse2Emplacement");
							String adresse3Emplacment = resultat1
									.getString("adresse3Emplacement");
							String AdessEmplacment = numRueEmplacment + " "
									+ adresse1Emplacment + " "
									+ adresse2Emplacment + " "
									+ adresse3Emplacment;

							redevable.setAdressEmplacment(AdessEmplacment);
							res.addElement(redevable);
						} catch (Exception e) {
							//System.out.println("ON EST ICI ");
							redevable.setAdressEmplacment("-");
							// debug.logger.debug("requete de recherche d'emplacment pour le redevable:"
							// + redevable.getNumRedevable());
							//Pour alerte, on ajoute le tri par numero desc (ordre chronologique pas besoin date)
							String requete2 = " SELECT * from emplacementgeneral  "
									+ " WHERE emplacementgeneral.source='normal' "
									+ " AND numRedevable='"
									+ resultat1.getString("numRedevable") + "'"
									+ " ORDER BY numero DESC";
							//		+ " ORDER BY STR_TO_DATE(datecreation, '%d/%m/%Y') numero DESC";
							//System.out.println("requete2="+requete2);
							Statement instruction2 = con.connexion
									.createStatement();
							ResultSet resultat2 = instruction2
									.executeQuery(requete2);
							debug.logger.debug(requete2);
							boolean aUnEmpalcment = false;
							while (resultat2.next()) {
								aUnEmpalcment = true;
								// debug.logger.fatal("Recherche des empalcmeents "+
								// e.getMessage() + e.getCause() +
								// e.getMessage());
								String numRueEmplacment = "";
								try {
									numRueEmplacment = String.valueOf(resultat2
											.getInt("numRue"));
								} catch (Exception e2) {
									numRueEmplacment = "0";
								}

								String adresse1Emplacment = resultat2
										.getString("adresse1");
								String adresse2Emplacment = resultat2
										.getString("adresse2");
								String adresse3Emplacment = resultat2
										.getString("adresse3");
								String AdessEmplacment = numRueEmplacment + " "
										+ adresse1Emplacment + " "
										+ adresse2Emplacment + " "
										+ adresse3Emplacment;

								Redevable red = new Redevable();
								red
										.setNumRedevable(redevable
												.getNumRedevable());
								red
										.setNomRedevable(redevable
												.getNomRedevable());
								red.setPrenomRedevable(redevable
										.getPrenomRedevable());
								red.setType(redevable.getType());
								red.setCivilite(redevable.getCivilite());
								red.setAdressRedevable(redevable
										.getAdressRedevable());
								red.setEtatEmplacement(redevable
										.getEtatEmplacement());
								red.setAdressEmplacment(AdessEmplacment);
								red.setNumeroEmplacment(resultat2
										.getString("numero"));
								res.addElement(red);
							}
							if (!aUnEmpalcment)
								res.addElement(redevable);

							instruction2.close();
						}

					}
				} catch (Exception e) {
					throw e;
				} finally {
					instruction.close();
				}
			} catch (Exception e) {
				debug.logger.fatal("Erreur 1" + e.getMessage() + e.getCause()
						+ e.getMessage());
				e.printStackTrace();
			}

		} catch (Exception e) {
			debug.logger.fatal("Erreur 2" + e.getMessage() + e.getCause()
					+ e.getMessage());
			e.printStackTrace();
		}

		return res;
	}

	//paul
	public Redevable getRedevable(String idRedevable) {
		Redevable redevable = new Redevable();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String requete = "SELECT redevable.* FROM redevable  WHERE numRedevable="
						+ idRedevable;
				debug.logger.debug(requete);
				ResultSet resultat = instruction.executeQuery(requete);
				while (resultat.next()) {
					// debug.logger.debug("test");
					redevable.setNumRedevable(resultat
							.getString("numRedevable"));
					redevable.setNomRedevable(resultat
							.getString("nomRedevable"));
					redevable.setRedevableActif(resultat.getString("actif"));
					redevable.setRaisonSocialeRedevable(resultat
							.getString("civilite"));
					redevable.setNomJeuneFilleRedevable(resultat
							.getString("nomJF"));
					redevable.setPrenomRedevable(resultat.getString("prenom"));
					redevable.setResponsableRedevable(resultat
							.getString("responsable"));
					redevable.setDateNaissanceRedevable(resultat
							.getString("dateNaissance"));
					redevable.setLieuNaissanceRedevable(resultat
							.getString("lieuNaissance"));
					redevable.setNationalliteRedevable(resultat
							.getString("nationalite"));
					redevable.setNumVoieRedevable(resultat.getString("numRue"));
					redevable.setCodeVoixRedevable(resultat
							.getString("codeVoie"));
					redevable.setNatureJuridiqueRedevable(resultat
							.getString("naturejuridique"));
					
					redevable
							.setAdressRedevable(resultat.getString("adresse1"));
					redevable.setComplement1AdressRedevable(resultat
							.getString("adresse2"));
					redevable.setComplement2AdressRedevable(resultat
							.getString("adresse3"));
					redevable.setCodePostaleRedevable(resultat
							.getString("codePostal"));
					redevable.setVilleeRedevable(resultat.getString("ville"));
					redevable.setCedexRedevable(resultat.getString("rdCedex"));
					redevable.setNumTelFixeRedevable(resultat
							.getString("numTel"));
					redevable.setNumTelPortableRedevable(resultat
							.getString("numPortable"));
					redevable.setNumTelFaxeRedevable(resultat
							.getString("numfax"));
					redevable.setEmailRedevable(resultat.getString("email"));
					redevable.setProffesionRedevable(resultat.getString("numeroProfession"));
					
					redevable.setSiren(resultat.getString("siren"));
					redevable.setSiret(resultat.getString("siret"));
				
					redevable.setInformationComplementaire(resultat.getString("informationComplementaire"));
					redevable.setCommentaire(resultat.getString("commentaire"));
					redevable.setImmeubleResidenceRedevable(resultat.getString("immeubleResidence"));
					
					
					redevable.setNomLiquidateur(resultat
							.getString("nomLiquidateur"));
					redevable.setRaisonSocialeLiquidateur(resultat
							.getString("raisonSocialeLiquidateur"));
					redevable.setPrenomLiquidateur(resultat
							.getString("prenomLiquidateur"));
					redevable.setNumVoieLiquidateur(resultat
							.getString("numVoieLiquidateur"));
					redevable.setCodeVoixLiquidateur(resultat
							.getString("codeVoixLiquidateur"));
					redevable.setAdressLiquidateur(resultat
							.getString("adressLiquidateur"));
					redevable.setComplement1AdressLiquidateur(resultat
							.getString("complement1AdressLiquidateur"));
					redevable.setComplement2AdressLiquidateur(resultat
							.getString("complement2AdressLiquidateur"));
					redevable.setCodePostaleLiquidateur(resultat
							.getString("codePostaleLiquidateur"));
					redevable.setVilleeLiquidateur(resultat
							.getString("villeeLiquidateur"));
					redevable.setCedexLiquidateur(resultat
							.getString("cedexLiquidateur"));
					redevable.setNumTelFixeLiquidateur(resultat
							.getString("numTelFixeLiquidateur"));
					redevable.setNumTelPortableLiquidateur(resultat
							.getString("numTelPortableLiquidateur"));
					redevable.setNumTelFaxeLiquidateur(resultat
							.getString("numTelFaxeLiquidateur"));
					redevable.setEmailLiquidateur(resultat
							.getString("emailLiquidateur"));

					redevable.setComplementNumeroRueRedevable(resultat
							.getString("complementNumeroRueRedevable"));
					redevable.setComplementNumeroRueLiquidateur(resultat
							.getString("complementNumeroRueLiquidateur"));

					RequestRue requRue = new RequestRue();
					Rue rue = requRue.getRue(resultat.getString("codeVoie"));
					redevable.setCodeRivolieRedevable(rue.getCodeRivolie());
					
				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage() + e.getCause() + e.getMessage());
		}
		return redevable;
	}

	public Redevable getRedevable(String raisonSocialeRedevable,
			String nomRedevable, String prenomRedevable) {
		nomRedevable = FonctionCommun.ajouterAntislash(nomRedevable);
		prenomRedevable = FonctionCommun.ajouterAntislash(prenomRedevable);

		Redevable redevable = new Redevable();

		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String requete = "SELECT redevable.* FROM redevable "
						+ " WHERE civilite='" + raisonSocialeRedevable + "'"
						+ " AND nomRedevable='" + nomRedevable + "'"
						+ " AND prenom='" + prenomRedevable + "'";
				debug.logger.debug(requete);
				ResultSet resultat = instruction.executeQuery(requete);
				while (resultat.next()) {
					redevable.setNumRedevable(resultat
							.getString("numRedevable"));
					redevable.setNomRedevable(resultat
							.getString("nomRedevable"));
					redevable.setRaisonSocialeRedevable(resultat
							.getString("civilite"));
					redevable.setNomJeuneFilleRedevable(resultat
							.getString("nomJF"));
					redevable.setPrenomRedevable(resultat.getString("prenom"));
					redevable.setResponsableRedevable(resultat
							.getString("responsable"));
					redevable.setDateNaissanceRedevable(resultat
							.getString("dateNaissance"));
					redevable.setLieuNaissanceRedevable(resultat
							.getString("lieuNaissance"));
					redevable.setNationalliteRedevable(resultat
							.getString("nationalite"));
					redevable.setNumVoieRedevable(resultat.getString("numRue"));
					redevable.setCodeVoixRedevable(resultat
							.getString("codeVoie"));
					redevable
							.setAdressRedevable(resultat.getString("adresse1"));
					redevable.setComplement1AdressRedevable(resultat
							.getString("adresse2"));
					redevable.setComplement2AdressRedevable(resultat
							.getString("adresse3"));
					redevable.setCodePostaleRedevable(resultat
							.getString("codePostal"));
					redevable.setVilleeRedevable(resultat.getString("ville"));
					redevable.setCedexRedevable(resultat.getString("rdCedex"));
					redevable.setNumTelFixeRedevable(resultat
							.getString("numTel"));
					redevable.setNumTelPortableRedevable(resultat
							.getString("numPortable"));
					redevable.setNumTelFaxeRedevable(resultat
							.getString("numfax"));
					redevable.setEmailRedevable(resultat.getString("email"));
					redevable.setProffesionRedevable(resultat
							.getString("numeroProfession"));

					redevable.setNomLiquidateur(resultat
							.getString("nomLiquidateur"));
					redevable.setRaisonSocialeLiquidateur(resultat
							.getString("raisonSocialeLiquidateur"));
					redevable.setPrenomLiquidateur(resultat
							.getString("prenomLiquidateur"));
					redevable.setNumVoieLiquidateur(resultat
							.getString("numVoieLiquidateur"));
					redevable.setCodeVoixLiquidateur(resultat
							.getString("codeVoixLiquidateur"));
					redevable.setAdressLiquidateur(resultat
							.getString("adressLiquidateur"));
					redevable.setComplement1AdressLiquidateur(resultat
							.getString("complement1AdressLiquidateur"));
					redevable.setComplement2AdressLiquidateur(resultat
							.getString("complement2AdressLiquidateur"));
					redevable.setCodePostaleLiquidateur(resultat
							.getString("codePostaleLiquidateur"));
					redevable.setVilleeLiquidateur(resultat
							.getString("villeeLiquidateur"));
					redevable.setCedexLiquidateur(resultat
							.getString("cedexLiquidateur"));
					redevable.setNumTelFixeLiquidateur(resultat
							.getString("numTelFixeLiquidateur"));
					redevable.setNumTelPortableLiquidateur(resultat
							.getString("numTelPortableLiquidateur"));
					redevable.setNumTelFaxeLiquidateur(resultat
							.getString("numTelFaxeLiquidateur"));
					redevable.setEmailLiquidateur(resultat
							.getString("emailLiquidateur"));

				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage() + e.getCause() + e.getMessage());
		}
		return redevable;
	}
//paul
	public void ajouterRedevable(String raisonSocialeRedevable,
			String nomRedevable, String nomJeuneFilleRedevable,
			String prenomRedevable, String responsableRedevable,
			String dateNaissanceRedevable, String lieuNaissanceRedevable,
			String nationalliteRedevable, String numVoieRedevable,
			String codeVoixRedevable, String adressRedevable,
			String complement1AdressRedevable,
			String complement2AdressRedevable, String codePostaleRedevable,
			String villeeRedevable, String cedexRedevable,
			String numTelFixeRedevable, String numTelPortableRedevable,
			String numTelFaxeRedevable, String emailRedevable,
			String raisonSocialeLiquidateur, String nomLiquidateur,
			String prenomLiquidateur, String numVoieLiquidateur,
			String codeVoixLiquidateur, String adressLiquidateur,
			String complement1AdressLiquidateur,
			String complement2AdressLiquidateur, String codePostaleLiquidateur,
			String villeeLiquidateur, String cedexLiquidateur,
			String numTelFixeLiquidateur, String numTelPortableLiquidateur,
			String numTelFaxeLiquidateur, String emailLiquidateur,
			String proffesionRedevable, String redevableActif,
			String complementNumeroRueRedevable,
			String complementNumeroRueLiquidateur, String natureJuridiqueRedevable,
			String informationComplementaire, String commentaire,
			String siret, String siren, String immeubleResidence) 
	{
		
		immeubleResidence= FonctionCommun.ajouterAntislash(immeubleResidence);
		raisonSocialeRedevable = FonctionCommun
				.ajouterAntislash(raisonSocialeRedevable);
		nomRedevable = FonctionCommun.ajouterAntislash(nomRedevable);
		nomJeuneFilleRedevable = FonctionCommun
				.ajouterAntislash(nomJeuneFilleRedevable);
		prenomRedevable = FonctionCommun.ajouterAntislash(prenomRedevable);
		responsableRedevable = FonctionCommun
				.ajouterAntislash(responsableRedevable);
		dateNaissanceRedevable = FonctionCommun
				.ajouterAntislash(dateNaissanceRedevable);
		lieuNaissanceRedevable = FonctionCommun
				.ajouterAntislash(lieuNaissanceRedevable);
		adressRedevable = FonctionCommun.ajouterAntislash(adressRedevable);
		complement1AdressRedevable = FonctionCommun
				.ajouterAntislash(complement1AdressRedevable);
		complement2AdressRedevable = FonctionCommun
				.ajouterAntislash(complement2AdressRedevable);
		villeeRedevable = FonctionCommun.ajouterAntislash(villeeRedevable);

		nomLiquidateur = FonctionCommun.ajouterAntislash(nomLiquidateur);
		prenomLiquidateur = FonctionCommun.ajouterAntislash(prenomLiquidateur);
		adressLiquidateur = FonctionCommun.ajouterAntislash(adressLiquidateur);
		complement1AdressLiquidateur = FonctionCommun
				.ajouterAntislash(complement1AdressLiquidateur);
		complement2AdressLiquidateur = FonctionCommun
				.ajouterAntislash(complement2AdressLiquidateur);
		villeeLiquidateur = FonctionCommun.ajouterAntislash(villeeLiquidateur);
		proffesionRedevable = FonctionCommun.ajouterAntislash(proffesionRedevable);
		
		informationComplementaire = FonctionCommun.ajouterAntislash(informationComplementaire);		
		commentaire = FonctionCommun.ajouterAntislash(commentaire);
		siret = FonctionCommun.ajouterAntislash(siret);
		siren = FonctionCommun.ajouterAntislash(siren);
		

		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = "INSERT INTO redevable"
						+ "(civilite, nomRedevable,nomJF, prenom,responsable,dateNaissance,"
						+ "lieuNaissance,nationalite, numRue,codeVoie,adresse1,adresse2,"
						+ "adresse3,codePostal,ville,rdCedex,  numTel,  numPortable,numfax, email,"
						+ "raisonSocialeLiquidateur,nomLiquidateur,  prenomLiquidateur,  numVoieLiquidateur,"
						+ "codeVoixLiquidateur,  adressLiquidateur,	  complement1AdressLiquidateur,"
						+ "complement2AdressLiquidateur,  codePostaleLiquidateur,  villeeLiquidateur,"
						+ "cedexLiquidateur,  numTelFixeLiquidateur,  numTelPortableLiquidateur,"
						+ "numTelFaxeLiquidateur,  emailLiquidateur,numeroProfession,actif,complementNumeroRueRedevable,"
						+ "complementNumeroRueLiquidateur,naturejuridique,informationComplementaire, commentaire," 
						+ "siret, siren, immeubleResidence )" +

						" VALUES('"
						+ raisonSocialeRedevable
						+ "','"
						+ nomRedevable
						+ "','"
						+ nomJeuneFilleRedevable
						+ "','"
						+ prenomRedevable
						+ "','"
						+ responsableRedevable
						+ "','"
						+ dateNaissanceRedevable
						+ "','"
						+ lieuNaissanceRedevable
						+ "','"
						+ nationalliteRedevable
						+ "','"
						+ numVoieRedevable
						+ "','"
						+ codeVoixRedevable
						+ "','"
						+ adressRedevable
						+ "','"
						+ complement1AdressRedevable
						+ "','"
						+ complement2AdressRedevable
						+ "','"
						+ codePostaleRedevable
						+ "','"
						+ villeeRedevable
						+ "','"
						+ cedexRedevable
						+ "','"
						+ numTelFixeRedevable
						+ "','"
						+ numTelPortableRedevable
						+ "','"
						+ numTelFaxeRedevable
						+ "','"
						+ emailRedevable
						+ "','"
						+ raisonSocialeLiquidateur
						+ "','"
						+ nomLiquidateur
						+ "','"
						+ prenomLiquidateur
						+ "','"
						+ numVoieLiquidateur
						+ "','"
						+ codeVoixLiquidateur
						+ "','"
						+ adressLiquidateur
						+ "','"
						+ complement1AdressLiquidateur
						+ "','"
						+ complement2AdressLiquidateur
						+ "','"
						+ codePostaleLiquidateur
						+ "','"
						+ villeeLiquidateur
						+ "','"
						+ cedexLiquidateur
						+ "','"
						+ numTelFixeLiquidateur
						+ "','"
						+ numTelPortableLiquidateur
						+ "','"
						+ numTelFaxeLiquidateur
						+ "','"
						+ emailLiquidateur
						+ "',"
						+ proffesionRedevable
						+ ",'"
						+ redevableActif
						+ "','"
						+ complementNumeroRueRedevable
						+ "','"
						+ complementNumeroRueLiquidateur 
						+ "','" 
						+ natureJuridiqueRedevable 
						+ "','"
						+ informationComplementaire
						+ "','" 
						+ commentaire
						+"','"
						+ siret
						+"','"
						+ siren
						+"','"
						+immeubleResidence
						
						+"')";

				debug.logger
						.debug("Requete d'insertion d'un nouveau Redevable :\n"
								+ req);

				// debug.logger.debug( numRedevable);
				/*
				 * debug.logger.debug( raisonSocialeRedevable);
				 * debug.logger.debug( nomRedevable); debug.logger.debug(
				 * nomJeuneFilleRedevable); debug.logger.debug(
				 * prenomRedevable); debug.logger.debug( responsableRedevable);
				 * debug.logger.debug( dateNaissanceRedevable);
				 * debug.logger.debug( lieuNaissanceRedevable);
				 * debug.logger.debug( nationalliteRedevable);
				 * debug.logger.debug( numVoieRedevable); debug.logger.debug(
				 * codeVoixRedevable); debug.logger.debug( adressRedevable);
				 * debug.logger.debug( complement1AdressRedevable);
				 * debug.logger.debug( complement2AdressRedevable);
				 * debug.logger.debug( codePostaleRedevable);
				 * debug.logger.debug( villeeRedevable); debug.logger.debug(
				 * cedexRedevable); debug.logger.debug( numTelFixeRedevable);
				 * debug.logger.debug( numTelPortableRedevable);
				 * debug.logger.debug( numTelFaxeRedevable); debug.logger.debug(
				 * emailRedevable); debug.logger.debug(
				 * raisonSocialeLiquidateur); debug.logger.debug(
				 * nomLiquidateur); debug.logger.debug( prenomLiquidateur);
				 * debug.logger.debug( numVoieLiquidateur); debug.logger.debug(
				 * codeVoixLiquidateur); debug.logger.debug( adressLiquidateur);
				 * debug.logger.debug( complement1AdressLiquidateur);
				 * debug.logger.debug( complement2AdressLiquidateur);
				 * debug.logger.debug( codePostaleLiquidateur);
				 * debug.logger.debug( villeeLiquidateur); debug.logger.debug(
				 * cedexLiquidateur); debug.logger.debug(
				 * numTelFixeLiquidateur); debug.logger.debug(
				 * numTelPortableLiquidateur); debug.logger.debug(
				 * numTelFaxeLiquidateur); debug.logger.debug(
				 * emailLiquidateur);
				 */

				int result1 = instruction.executeUpdate(req);
				new RequestAdmin().InsertAction(
						"Ajout d'un nouveau redevable ", GestionDate
								.getDateTime());
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage() + e.getCause() + e.getMessage());
		}

	}

	public void modifierRedevable(String idRedevable,
			String raisonSocialeRedevable, String nomRedevable,
			String nomJeuneFilleRedevable, String prenomRedevable,
			String responsableRedevable, String dateNaissanceRedevable,
			String lieuNaissanceRedevable, String nationalliteRedevable,
			String numVoieRedevable, String codeVoixRedevable,
			String adressRedevable, String complement1AdressRedevable,
			String complement2AdressRedevable, String codePostaleRedevable,
			String villeeRedevable, String cedexRedevable,
			String numTelFixeRedevable, String numTelPortableRedevable,
			String numTelFaxeRedevable, String emailRedevable,
			String raisonSocialeLiquidateur, String nomLiquidateur,
			String prenomLiquidateur, String numVoieLiquidateur,
			String codeVoixLiquidateur, String adressLiquidateur,
			String complement1AdressLiquidateur,
			String complement2AdressLiquidateur, String codePostaleLiquidateur,
			String villeeLiquidateur, String cedexLiquidateur,
			String numTelFixeLiquidateur, String numTelPortableLiquidateur,
			String numTelFaxeLiquidateur, String emailLiquidateur,
			String proffesionRedevable, String redevableActif,
			String complementNumeroRueRedevable,
			String complementNumeroRueLiquidateur , String natureJuridiqueRedevable,
			String informationComplementaire, String  commentaire,
			String siret, String siren , String immeubleResidence ) {

		siret = FonctionCommun.ajouterAntislash(siret);
		immeubleResidence= FonctionCommun.ajouterAntislash(immeubleResidence);
		siren = FonctionCommun.ajouterAntislash(siren);
		nomRedevable = FonctionCommun.ajouterAntislash(nomRedevable);
		villeeRedevable = FonctionCommun.ajouterAntislash(villeeRedevable);
		villeeLiquidateur = FonctionCommun.ajouterAntislash(villeeLiquidateur);
		nomJeuneFilleRedevable = FonctionCommun
				.ajouterAntislash(nomJeuneFilleRedevable);
		prenomRedevable = FonctionCommun.ajouterAntislash(prenomRedevable);
		responsableRedevable = FonctionCommun
				.ajouterAntislash(responsableRedevable);
		lieuNaissanceRedevable = FonctionCommun
				.ajouterAntislash(lieuNaissanceRedevable);
		nationalliteRedevable = FonctionCommun
				.ajouterAntislash(nationalliteRedevable);
		adressRedevable = FonctionCommun.ajouterAntislash(adressRedevable);
		complement1AdressRedevable = FonctionCommun
				.ajouterAntislash(complement1AdressRedevable);
		complement2AdressRedevable = FonctionCommun
				.ajouterAntislash(complement2AdressRedevable);
		codePostaleRedevable = FonctionCommun
				.ajouterAntislash(codePostaleRedevable);
		nomLiquidateur = FonctionCommun.ajouterAntislash(nomLiquidateur);
		prenomLiquidateur = FonctionCommun.ajouterAntislash(prenomLiquidateur);
		adressLiquidateur = FonctionCommun.ajouterAntislash(adressLiquidateur);
		complement1AdressLiquidateur = FonctionCommun
				.ajouterAntislash(complement1AdressLiquidateur);
		complement2AdressLiquidateur = FonctionCommun
				.ajouterAntislash(complement2AdressLiquidateur);
		informationComplementaire = FonctionCommun.ajouterAntislash(informationComplementaire);		
		commentaire = FonctionCommun.ajouterAntislash(commentaire);

		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r = " UPDATE redevable set" + " civilite='"
						+ raisonSocialeRedevable + "'," + " nomRedevable='"
						+ nomRedevable + "'," + " nomJF='"
						+ nomJeuneFilleRedevable + "'," + " prenom='"
						+ prenomRedevable + "'," + " responsable='"
						+ responsableRedevable + "'," + " dateNaissance ='"
						+ dateNaissanceRedevable + "'," + " lieuNaissance='"
						+ lieuNaissanceRedevable + "'," + " nationalite='"
						+ nationalliteRedevable + "'," + " numRue='"
						+ numVoieRedevable + "'," + " codeVoie='"
						+ codeVoixRedevable + "'," + " adresse1='"
						+ adressRedevable + "'," + " adresse2='"
						+ complement1AdressRedevable + "'," + " adresse3='"
						+ complement2AdressRedevable + "'," + " codePostal='"
						+ codePostaleRedevable + "'," + " ville='"
						+ villeeRedevable + "'," + " rdCedex='"
						+ cedexRedevable + "'," + " numTel='"
						+ numTelFixeRedevable + "'," + " numPortable='"
						+ numTelPortableRedevable + "'," + " numfax='"
						+ numTelFaxeRedevable + "'," + " email='"
						+ emailRedevable + "'," + " raisonSocialeLiquidateur='"
						+ raisonSocialeLiquidateur + "'," + " nomLiquidateur='"
						+ nomLiquidateur + "'," + " prenomLiquidateur='"
						+ prenomLiquidateur + "'," + " numVoieLiquidateur='"
						+ numVoieLiquidateur + "'," + " codeVoixLiquidateur='"
						+ codeVoixLiquidateur + "'," + " adressLiquidateur='"
						+ adressLiquidateur + "',"
						+ " complement1AdressLiquidateur='"
						+ complement1AdressLiquidateur + "',"
						+ " complement2AdressLiquidateur='"
						+ complement2AdressLiquidateur + "',"
						+ " codePostaleLiquidateur='" + codePostaleLiquidateur
						+ "'," + " villeeLiquidateur='" + villeeLiquidateur
						+ "'," + " cedexLiquidateur='" + cedexLiquidateur
						+ "'," + " numTelFixeLiquidateur='"
						+ numTelFixeLiquidateur + "',"
						+ " numTelPortableLiquidateur='"
						+ numTelPortableLiquidateur + "',"+ 
						" numTelFaxeLiquidateur='" + numTelFaxeLiquidateur + "'," + 
						" emailLiquidateur='" + emailLiquidateur+ "'," + 
						" complementNumeroRueRedevable='"+ complementNumeroRueRedevable + "',"+
						" complementNumeroRueLiquidateur='"+ complementNumeroRueLiquidateur + "'," + 
						" actif='"	+ redevableActif + "'," + 
						" numeroProfession="+ proffesionRedevable + 
						" ,naturejuridique = '"+natureJuridiqueRedevable + "'" +
						" ,informationComplementaire= '"+informationComplementaire + "'" +
						" ,commentaire = '"+commentaire+ "'" +
						" ,siret = '"+siret+ "'" +
						" ,siren = '"+siren+ "',immeubleResidence='"+immeubleResidence+"'" +

						" WHERE numRedevable="
						+ idRedevable;
				debug.logger.debug(r);
				int result1 = instruction.executeUpdate(r);
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage() + e.getCause() + e.getMessage());
		}

	}

	public void supprimerRedevable(String idRedevable) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r = "DELETE from redevable WHERE numRedevable="
						+ idRedevable;
				debug.logger.debug(r);
				int result1 = instruction.executeUpdate(r);
				new RequestAdmin().InsertAction("Suppression du redevable " + idRedevable, GestionDate.getDateTime());
				
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage() + e.getCause());
		}

	}

	public Vector getListeRedevable(String numRedevable, String nomRedevable,
			String adresseRedevable) {

		Vector res = new Vector();
		try {

			Statement instruction = con.connexion.createStatement();
			try {
				String req1 = " SELECT  redevable.*,  "
						+ " redevable.nomRedevable,redevable.civilite,redevable.numRue  ,"
						+ " redevable.prenom,redevable.typeRedevable,redevable.adresse1  "
						+ " FROM redevable "
						+ " WHERE redevable.numRedevable =redevable.numRedevable ";

				if (numRedevable != null && numRedevable.length() != 0) {
					req1 = req1 + " AND numRedevable =" + numRedevable;
				}

				if (nomRedevable != null && nomRedevable.length() != 0)
					req1 = req1
							+ " AND concat(nomRedevable,\" \",prenom )like '%"
							+ nomRedevable + "%'";

				if (adresseRedevable != null && adresseRedevable.length() != 0)
					req1 = req1
							+ " AND concat(numRue,\" \",adresse1,\" \",adresse2,\" \",adresse3,\" \" ) like'%"
							+ adresseRedevable + "%'";

				debug.logger.debug(req1);
				ResultSet resultat = instruction.executeQuery(req1);
				while (resultat.next()) {
					Redevable redevable = new Redevable();
					redevable.setNumRedevable(resultat
							.getString("numRedevable"));
					redevable.setNomRedevable(resultat
							.getString("nomRedevable"));
					redevable.setRaisonSocialeRedevable(resultat
							.getString("civilite"));
					redevable.setNomJeuneFilleRedevable(resultat
							.getString("nomJF"));
					redevable.setPrenomRedevable(resultat.getString("prenom"));
					redevable.setResponsableRedevable(resultat
							.getString("responsable"));
					redevable.setDateNaissanceRedevable(resultat
							.getString("dateNaissance"));
					redevable.setLieuNaissanceRedevable(resultat
							.getString("lieuNaissance"));
					redevable.setNationalliteRedevable(resultat
							.getString("nationalite"));
					redevable.setNumVoieRedevable(resultat.getString("numRue"));
					redevable.setCodeVoixRedevable(resultat
							.getString("codeVoie"));
					redevable
							.setAdressRedevable(resultat.getString("adresse1"));
					redevable.setComplement1AdressRedevable(resultat
							.getString("adresse2"));
					redevable.setComplement2AdressRedevable(resultat
							.getString("adresse3"));
					redevable.setCodePostaleRedevable(resultat
							.getString("codePostal"));
					redevable.setVilleeRedevable(resultat.getString("ville"));
					redevable.setCedexRedevable(resultat.getString("rdCedex"));
					redevable.setNumTelFixeRedevable(resultat
							.getString("numTel"));
					redevable.setNumTelPortableRedevable(resultat
							.getString("numPortable"));
					redevable.setNumTelFaxeRedevable(resultat
							.getString("numfax"));
					redevable.setEmailRedevable(resultat.getString("email"));
					redevable.setProffesionRedevable(resultat
							.getString("numeroProfession"));
					redevable.setNomLiquidateur(resultat
							.getString("nomLiquidateur"));
					redevable.setRaisonSocialeLiquidateur(resultat
							.getString("raisonSocialeLiquidateur"));
					redevable.setPrenomLiquidateur(resultat
							.getString("prenomLiquidateur"));
					redevable.setNumVoieLiquidateur(resultat
							.getString("numVoieLiquidateur"));
					redevable.setCodeVoixLiquidateur(resultat
							.getString("codeVoixLiquidateur"));
					redevable.setAdressLiquidateur(resultat
							.getString("adressLiquidateur"));
					redevable.setComplement1AdressLiquidateur(resultat
							.getString("complement1AdressLiquidateur"));
					redevable.setComplement2AdressLiquidateur(resultat
							.getString("complement2AdressLiquidateur"));
					redevable.setCodePostaleLiquidateur(resultat
							.getString("codePostaleLiquidateur"));
					redevable.setVilleeLiquidateur(resultat
							.getString("villeeLiquidateur"));
					redevable.setCedexLiquidateur(resultat
							.getString("cedexLiquidateur"));
					redevable.setNumTelFixeLiquidateur(resultat
							.getString("numTelFixeLiquidateur"));
					redevable.setNumTelPortableLiquidateur(resultat
							.getString("numTelPortableLiquidateur"));
					redevable.setNumTelFaxeLiquidateur(resultat
							.getString("numTelFaxeLiquidateur"));
					redevable.setEmailLiquidateur(resultat
							.getString("emailLiquidateur"));

					redevable.setAdressRedevable(resultat.getString("numRue")
							+ " " + resultat.getString("adresse1") + " "
							+ resultat.getString("adresse2") + " "
							+ resultat.getString("adresse3") + " ");
					res.addElement(redevable);
				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}

		}

		catch (Exception e) {
			debug.logger.fatal(e.getMessage() + e.getCause());
		}

		return res;
	}

	public void changerAdresse(String numRedevableAChangerAdresse,
			String numVoieRedevable, String codeVoixRedevable,
			String adressRedevable, String complement1AdressRedevable,
			String complement2AdressRedevable, String codePostaleRedevable,
			String villeeRedevable, String cedexRedevable,
			String complementNumeroRueRedevable) {

		RequestRedevable reqRedevable = new RequestRedevable();
		boolean res = reqRedevable
				.aDesFacturesEnCours(numRedevableAChangerAdresse);
		DebuggerLog4J.logger.debug("////////////////////////////////////////");
		DebuggerLog4J.logger.debug(" LE redevable a des variable=" + res);
		DebuggerLog4J.logger.debug("////////////////////////////////////////");

		// On ne met a jour le flag que pour les edevable ayant des facture en
		// cours
		if (res) {
			adressRedevable = FonctionCommun.ajouterAntislash(adressRedevable);
			complement1AdressRedevable = FonctionCommun
					.ajouterAntislash(complement1AdressRedevable);
			complement2AdressRedevable = FonctionCommun
					.ajouterAntislash(complement2AdressRedevable);
			villeeRedevable = FonctionCommun.ajouterAntislash(villeeRedevable);
			try {

				Statement instruction = con.connexion.createStatement();
				try {
					Redevable redvable = this
							.getRedevable(numRedevableAChangerAdresse);
					String anciennAdressRedevable = FonctionCommun
							.ajouterAntislash(redvable.getAdressRedevable());
					String anciennComplement1AdressRedevable = FonctionCommun
							.ajouterAntislash(redvable
									.getComplement1AdressRedevable());
					String anciennComplement2AdressRedevable = FonctionCommun
							.ajouterAntislash(redvable
									.getComplement2AdressRedevable());
					String anciennVilleeRedevable = FonctionCommun
							.ajouterAntislash(redvable.getVilleeRedevable());

					DebuggerLog4J.logger
							.debug("///////////INSERET historiqueadresseredevable//////////////////");

					String req = "INSERT INTO `historiqueadresseredevable`"
							+ " ( `idRedevable`, `codeVoie`, `numRue`, `adresse1`, `adresse2`, "
							+ "`adresse3`, `ville`, `rdCedex`, `codePostal`,`complementNumeroRueRedevable`)"
							+ " VALUES(" + numRedevableAChangerAdresse + ",'"
							+ redvable.getCodeVoixRedevable() + "',"
							+ redvable.getNumVoieRedevable() + ",'"
							+ anciennAdressRedevable + "','"
							+ anciennComplement1AdressRedevable + "','"
							+ anciennComplement2AdressRedevable + "','"
							+ anciennVilleeRedevable + "','"
							+ redvable.getCedexRedevable() + "','"
							+ redvable.getCodePostaleRedevable() + "','"
							+ redvable.getComplementNumeroRueRedevable() + "')";
					DebuggerLog4J.logger.debug(req);
					int result1 = instruction.executeUpdate(req);

					// mise a jour de la nouvelle adresse :
					String req2 = " UPDATE redevable set" + " codeVoie='"
							+ codeVoixRedevable + "'," + " numRue="
							+ numVoieRedevable + "," + " adresse1='"
							+ adressRedevable + "'," + " adresse2='"
							+ complement1AdressRedevable + "'," + " adresse3='"
							+ complement2AdressRedevable + "'," + " ville='"
							+ villeeRedevable + "'," + " rdCedex='"
							+ cedexRedevable + "'," + " codePostal='"
							+ codePostaleRedevable + "',"
							+ " changementAdresse= 'true'"
							+ " WHERE numRedevable="
							+ numRedevableAChangerAdresse;
					DebuggerLog4J.logger
							.debug("Changement d'adresse redevable : " + req2);
					int result2 = instruction.executeUpdate(req2);

					new RequestAdmin().InsertAction(
							"Changement de l'adresse du redevable numero "
									+ numRedevableAChangerAdresse, GestionDate
									.getDateTime());
				} catch (Exception e) {
					throw e;
				} finally {
					instruction.close();
				}
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage() + e.getCause() + "\n"
						+ e.getMessage());
			}
		}// FIN IF , verfier si le redevabel a des facture en cours
	}



	
	public boolean peutEtreSupprimer(String idUser) {
		boolean res = true;

		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try 
			{
				// Le redevable ne peut pas etre supprimser:
				//pour Toulon si il a des factures en cours 
				//pour Bordeaux si il a un emplcement en cours								
				RequestParametres requestParametres = new RequestParametres();
				String ville = requestParametres.getVille();				
				if (ville.equalsIgnoreCase("bordeaux"))
				{
					// Verfier si il a un empalcment actif
					// instruction = con.connexion.createStatement();
					String requete = " SELECT numero FROM emplacementgeneral "
								+ " WHERE emplacementgeneral.source ='normal'"
								+ " AND emplacementgeneral.enActivite= 'enActivite' "
								+ " AND emplacementgeneral.numRedevable = "
								+ idUser;
						ResultSet resultat = instruction.executeQuery(requete);
						debug.logger.debug(requete);
						while (resultat.next()) {
							res = false;
						}
				}
				else
				{
					// Verfier si il a une facture en cours
					//Si il n a pas de facture , verfier si il a des emplacements affecte a ce redevable
					RequestRedevable req = new RequestRedevable();
					res = !req.aDesFacturesEnCours(idUser);
					if(res == true)
					{
						String requete = " SELECT numero FROM emplacementgeneral "
							+ " WHERE emplacementgeneral.source ='normal'"
							+ " AND emplacementgeneral.numRedevable = "
							+ idUser;
						ResultSet resultat = instruction.executeQuery(requete);
						debug.logger.debug(requete);
						while (resultat.next()) 
						{
							res = false;
						}
					}
					
					
				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage() + e.getCause() + e.getMessage());
		}

		return res;
	}

	
	
	

	public boolean aDesFacturesEnCours(String idUser) {
		boolean res = false;

		Statement instruction;
		try {
			// Verfier si il a une facture en cours
			// Facture en cours cad solde !=0
			instruction = con.connexion.createStatement();
			try {
				String requete = "SELECT numeroFacture FROM facture "
						+ " WHERE facture.solde != 0  "
						+ " AND facture.etat= 'Valide' "
						+ " AND facture.idClient =" + idUser;
				ResultSet resultat = instruction.executeQuery(requete);
				debug.logger
						.debug("Verfier si le redevable a des factures en cours "
								+ requete);
				while (resultat.next()) {
					res = true;
				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage() + e.getCause() + e.getMessage());
		}

		return res;
	}

	public boolean peutEtreDesactvier(String idUser) 
	{
		debug.logger.debug("a des facture en cours="+ this.aDesFacturesEnCours(idUser));
		return !this.aDesFacturesEnCours(idUser);
	}
	
	
	
	public Vector getListeRemboursement(String idClient,	String annee) 
	{
		Vector res = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1 = " SELECT remboursement.* " 
					      + " FROM remboursement,batchtraitement "
						  + " WHERE  idClient = "+idClient 
						  + " AND anneeEx ='" + annee + "'" 
						  +	" AND remboursement.idBatchTraitement = batchtraitement.numeroBatchTraitement "
						  +	" AND batchtraitement.etape='facturation'"						  
						  + " ORDER BY idRemboursement DESC ";				
				debug.logger.debug(r1);
				ResultSet resultat1 = instruction.executeQuery(r1);
				while (resultat1.next()) 
				{
					Remboursement remboursement = new Remboursement();
					remboursement.setTypeTaxe(resultat1.getString("typeTaxe"));
					remboursement.setAnneeEx(resultat1.getString("anneeEx"));
					remboursement.setIdClient(""+resultat1.getInt("idClient"));
					remboursement.setMontantTotal(resultat1.getString("montantTotal"));
					remboursement.setIdBatchTraitement(""+resultat1.getInt("idBatchTraitement"));
					remboursement.setIdRemboursement(""+resultat1.getInt("idRemboursement"));
					res.addElement(remboursement);
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

	

	public String rechercherRedevableBySiretSiren(String numRedevable, String siret,String siren)
	{
		String res = "ok";
		try 
		{
			Statement instruction = con.connexion.createStatement();
			try 
			{
				     String req1 = " SELECT siret,siren From redevable WHERE " +
							" numRedevable != "+ numRedevable+" AND siret='" + siret + "'  AND  siren='" + siren + "'"  ; 
					
				    debug.logger.debug(req1);
					ResultSet resultat1 = instruction.executeQuery(req1);
					while (resultat1.next()) 
					{
						res = "ko";
						break;
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
