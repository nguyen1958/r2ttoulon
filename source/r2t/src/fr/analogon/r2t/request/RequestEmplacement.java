package fr.analogon.r2t.request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Emplacement;
import fr.analogon.r2t.pojo.Image;
import fr.analogon.r2t.pojo.Imputation;
import fr.analogon.r2t.util.date.LaDate;

public class RequestEmplacement extends Request {
	public void supprimerImageEmplacementGeneral(String nomImage) {
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
					// test
				}

				if (idImage != 0) {
					String req = "delete from image_emplacementgeneral WHERE idImage="
							+ idImage;
					debug.logger.debug(req);
					int result1 = instruction.executeUpdate(req);
					req = "delete from image WHERE idImage=" + idImage;
					debug.logger.debug(req);
					result1 = instruction.executeUpdate(req);
					deleteFile(cheminPhoto);
					nomImage = nomImage.replaceAll("'", "\'");

					new RequestAdmin().InsertAction("Suppresions d une image "
							+ nomImage, GestionDate.getDateTime());
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

	public String rechercherEmplacementByNumByCode(String codeSecteur,
			String numeroEmplacementPersonalise, String numEmplacementR2T) {
		String res = "-1";
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				// modification du numero emplacment
				if (numEmplacementR2T != null
						&& numEmplacementR2T.length() != 0) {
					String req1 = " SELECT numero from emplacementgeneral "
							+ " WHERE enActivite='enActivite'" 
							+ " AND codeSecteur='" + codeSecteur + "'"
							+ " AND numeroEmplacementPersonalise='"
							+ numeroEmplacementPersonalise + "'";
					// " AND numero != "+numEmplacementR2T;
					debug.logger.debug(req1);
					ResultSet resultat1 = instruction.executeQuery(req1);
					while (resultat1.next()) {
						res = resultat1.getString("numero");
						if (res.equalsIgnoreCase(numEmplacementR2T)) {
							res = "-1";
							break;
						}
					}
				} else {
					String req1 = " SELECT numero from emplacementgeneral "
							+ " WHERE enActivite='enActivite'"	
							+ " AND codeSecteur='" + codeSecteur + "'"
							+ " AND numeroEmplacementPersonalise='"
							+ numeroEmplacementPersonalise + "'";
					debug.logger.debug(req1);
					ResultSet resultat1 = instruction.executeQuery(req1);
					while (resultat1.next()) {
						res = resultat1.getString("numero");
					}
				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.debug(e.getMessage());
		}
		return res;
	}

	public Vector getTableauImagesAlerte(String idAlerte, String idArticle) {
		Vector contenu = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req1 = " SELECT image.* "
						+ " FROM image,image_alerte,alerte "
						+ " WHERE image.idImage= image_alerte.idImage "
						+ " AND image_alerte.idAlerte= alerte.id_alerte";

				if (idArticle != null && idArticle.length() != 0)
					req1 = req1 + " AND alerte.id_article=" + idArticle;
				if (idAlerte != null && idAlerte.length() != 0)
					req1 = req1 + " AND alerte.id_alerte=" + idAlerte;
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

	
	
	
	public Vector getTableauDocumentEmplacement(String idEmplacement) {
		Vector contenu = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req1 = " SELECT * FROM document_emplacement WHERE idEmplacement= "+ idEmplacement ;			
				debug.logger.debug(req1);
				ResultSet resultat1 = instruction.executeQuery(req1);
				while (resultat1.next()) {
					
					String nomDocument = resultat1.getString("nomDocument");
					contenu.addElement(nomDocument);
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

	
	
	
	
	public Vector getTableauImagesAlerteSpontanne(String idAlerteSpontanne) {
		Vector contenu = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req1 = " SELECT image.* " + " FROM image,image_alerte "
						+ " WHERE image.idImage= image_alerte.idImage "
						+ " AND image_alerte.idAlerte= " + idAlerteSpontanne;

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

	public int getNombreImagesAlerteOuvrage(String idAlerte, String idArticle) {
		int retour = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req1 = " SELECT count(image_alerte.idImageAlerte) as nombreImage "
						+ " FROM image_alerte, alerte "
						+ " WHERE image_alerte.IdAlerte= alerte.id_alerte ";

				if (idArticle != null && idArticle.length() != 0)
					req1 = req1 + " AND alerte.id_article=" + idArticle;
				if (idAlerte != null && idAlerte.length() != 0)
					req1 = req1 + " AND alerte.id_alerte=" + idAlerte;

				debug.logger.debug(req1);
				ResultSet resultat1 = instruction.executeQuery(req1);
				while (resultat1.next()) {
					retour = resultat1.getInt("nombreImage");
				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return retour;
	}

	public int getNombreImagesAlerteSpontanne(String idAlerte) {
		int retour = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req1 = " SELECT count(image_alerte.idImageAlerte) as nombreImage "
						+ " FROM image_alerte, alerte "
						+ " WHERE image_alerte.IdAlerte= alerte.id_alerte "
						+ " AND alerte.id_alerte =" + idAlerte;

				debug.logger.debug(req1);
				ResultSet resultat1 = instruction.executeQuery(req1);
				while (resultat1.next()) {
					retour = resultat1.getInt("nombreImage");
					// System.out.println("test="+retour);
				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return retour;
	}

	public int getNombreImagesEmplacement(String idEmplacementGeneral) {
		int retour = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				RequestEmplacement requestEmplacement = new RequestEmplacement();
				RequestImputation requestImputation = new RequestImputation();
				Emplacement emplacement = requestEmplacement
						.getEmplacement(idEmplacementGeneral);
				Imputation imputation = requestImputation.getImputation(
						emplacement.getIdImputation(), emplacement
								.getAnneeExerciceImputation());
				String typeEmplacement = imputation.getLibelle();
				debug.logger.debug("Type emplacement = " + typeEmplacement);

				String req1 = "";
				// CAS DE TAV OU TLPE
				if (typeEmplacement.equalsIgnoreCase("TLPE")
						|| typeEmplacement.equalsIgnoreCase("TAV"))
				// if (false)
				{
					req1 = " SELECT count(image.idImage) as nombreImage "
							+ " FROM image,image_emplacementgeneral,emplacementgeneral "
							+ " WHERE image.idImage= image_emplacementgeneral.idImage "
							+ " AND emplacementgeneral.numero= image_emplacementgeneral.idEmplacement"
							+ " AND emplacementgeneral.codeVoie= "
							+ emplacement.getCodeVoie()
							+ " AND emplacementgeneral.numRue= '"+ emplacement.getNumRue() +"'"
							+ " AND emplacementgeneral.numRedevable= "
							+ emplacement.getNumeroRedevable();
				} else {
					req1 = " SELECT count(image.idImage) as nombreImage "
							+ " FROM image,image_emplacementgeneral "
							+ " WHERE image.idImage= image_emplacementgeneral.idImage "
							+ " AND image_emplacementgeneral.idEmplacement="
							+ idEmplacementGeneral;
				}
				debug.logger.debug(req1);
				ResultSet resultat1 = instruction.executeQuery(req1);
				while (resultat1.next()) {
					retour = resultat1.getInt("nombreImage");
				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return retour;
	}

	
	
	public static void deleteFile(String name) {
		try {
			debug.logger.debug("\n\nSuppresion du fichier  " + name);
			java.io.File file = new java.io.File(name);
			if (file.exists()) {
				file.delete();
			} else {
				debug.logger.debug("impossible de suprimer le fichier " + name
						+ " , il n existe pas");
				// javax.swing.JOptionPane.showMessageDialog(null,
				// "Le fichier n'existe pas !");
			}
		} catch (Exception e) {
			debug.logger.debug("Impossible de supprimer le fichier " + name);
		}
	}

	public Vector getTableauImagesEmplacement(String idEmplacementGeneral) {
		Vector contenu = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				RequestEmplacement requestEmplacement = new RequestEmplacement();
				RequestImputation requestImputation = new RequestImputation();
				Emplacement emplacement = requestEmplacement
						.getEmplacement(idEmplacementGeneral);
				Imputation imputation = requestImputation.getImputation(
						emplacement.getIdImputation(), emplacement
								.getAnneeExerciceImputation());
				String typeEmplacement = imputation.getLibelle();
				debug.logger.debug("Type emplacement = " + typeEmplacement);

				String req1 = "";
				// CAS DE TAV OU TLPE
				if (typeEmplacement.equalsIgnoreCase("TLPE")
						|| typeEmplacement.equalsIgnoreCase("TAV")) {
					req1 = " SELECT image.* "
							+ " FROM image,image_emplacementgeneral,emplacementgeneral "
							+ " WHERE image.idImage= image_emplacementgeneral.idImage "
							+ " AND emplacementgeneral.numero= image_emplacementgeneral.idEmplacement"
							+ " AND emplacementgeneral.codeVoie= "	+ emplacement.getCodeVoie()
							+ " AND emplacementgeneral.numRue= '"+ emplacement.getNumRue() +"'"
							+ " AND emplacementgeneral.numRedevable= " + emplacement.getNumeroRedevable();
				} else {
					req1 = " SELECT image.* "
							+ " FROM image,image_emplacementgeneral "
							+ " WHERE image.idImage= image_emplacementgeneral.idImage "
							+ " AND image_emplacementgeneral.idEmplacement="
							+ idEmplacementGeneral;
				}
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

	// public Vector getListeEmplacement(String idRedevable ,String
	// etatEmplacement )
	public Vector getListeEmplacement(String idRedevable, String etatEmplacement) {
		Vector res = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req1 = " SELECT distinct (emplacementgeneral.numero) ,redevable.numRedevable,imputation.libelle "
						+ " ,emplacementgeneral.*, redevable.nomRedevable,redevable.prenom "
						+ " FROM emplacementgeneral,redevable, imputation "
						+ " WHERE emplacementgeneral.numRedevable = redevable.numRedevable"
						+ " AND emplacementgeneral.anneeExerciceImputation= imputation.anneeExercice"
						+ " AND imputation.idImputation= emplacementgeneral.codeType"
						+ " AND emplacementgeneral.source='normal' "
						+ " AND emplacementgeneral.numRedevable ="
						+ idRedevable;

				if (etatEmplacement.equalsIgnoreCase("termine"))
					req1 = req1
							+ " AND emplacementgeneral.enActivite='termine'";
				else if (etatEmplacement.equalsIgnoreCase("enActivite")
						|| etatEmplacement.length() == 0)
					req1 = req1
							+ " AND emplacementgeneral.enActivite='enActivite'";

				debug.logger.debug(req1);
				ResultSet resultat1 = instruction.executeQuery(req1);
				while (resultat1.next()) {
					// retour=resultat1.getInt("nombreImage");
					Emplacement emplacement = new Emplacement();
					emplacement.setLibelleImputation(resultat1
							.getString("imputation.libelle"));
					emplacement.setIdImputation(resultat1
							.getString("emplacementgeneral.codeType"));
					emplacement.setCodeSecteur(resultat1
							.getString("codeSecteur"));
					emplacement.setNomCommercial(resultat1
							.getString("raisonSocial"));
					emplacement.setAdresse1(resultat1.getString("adresse1"));
					emplacement.setNumRue(resultat1.getString("numRue"));
					emplacement.setNomRedevable(resultat1
							.getString("nomRedevable"));
					emplacement.setPrenomRedevable(resultat1
							.getString("prenom"));
					emplacement
							.setNumEmplacement(resultat1.getString("numero"));
					emplacement.setNumeroRedevable(resultat1
							.getString("numRedevable"));
					int exercice = (new fr.analogon.r2t.util.date.LaDate())
							.getAnneeInt();
					emplacement.setAnneeExercice(String.valueOf(exercice));
					emplacement.setEtatEmplacement(resultat1
							.getString("enActivite"));
					emplacement.setComplementNumeroRueEmpl(resultat1
							.getString("complementNumeroRueEmpl"));
					emplacement.setComplementNumeroRueProprietaire(resultat1
							.getString("complementNumeroRueProprietaire"));
					emplacement.setNombreOuvrageActif(this.getNombreOuvrage(
							resultat1.getString("numero"), true));
					String numeroEmpalcment = resultat1.getString("numero");
					Statement instruction2 = con.connexion.createStatement();
					String req2 = " SELECT count(*)  as nombreOuvrage "
							+ " FROM  article , elementfacturation "
							+ " WHERE article.id_elementfacturation= elementfacturation.numero"
							+ " AND elementfacturation.numeroEmplacement ="
							+ numeroEmpalcment;
					debug.logger.debug(req2);
					ResultSet resultat2 = instruction2.executeQuery(req2);
					while (resultat2.next()) {
						emplacement.setNombreOuvrage(String.valueOf(resultat2
								.getInt("nombreOuvrage")));
					}
					res.addElement(emplacement);
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
	
	//Paul alerte pour tablette
	public Vector getListeEmplacement(String etatEmplacement,String secteur,String taxe,String numVoie, String nomVoie, String dateControle,String controleur) {
		Vector res = new Vector();
		String req="";
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				req="SELECT distinct emplacementgeneral.*,imputation.libelle,redevable.nomRedevable,datecontrole,idcontroleur FROM emplacementgeneral "+
				        "LEFT JOIN imputation on emplacementgeneral.codetype=imputation.idimputation "+
				        "LEFT JOIN redevable on emplacementgeneral.numredevable=redevable.numredevable "+
				        "LEFT JOIN (SELECT * FROM historiquecontrolemplacement GROUP BY idemplacement "+
				        "HAVING str_to_date(datecontrole,'%d/%m/%Y')=MAX(str_to_date(datecontrole,'%d/%m/%Y'))) T ON numero=idemplacement ";
				
				req+=etatEmplacement.equalsIgnoreCase("libre")?"WHERE emplacementgeneral.numRedevable= -1 ":"WHERE enActivite='aModifier' ";
				
				if(!secteur.isEmpty()){
					req+=" AND emplacementgeneral.codeSecteur ="+ secteur;
				}
				if(!taxe.isEmpty()){
					req+=" AND emplacementgeneral.codeType = imputation.idImputation AND imputation.libelle ='" + taxe + "'";
				}
				if(!numVoie.isEmpty()){
					req+=" AND emplacementgeneral.numRue = '"+ numVoie+"'";
				}
				if(!nomVoie.isEmpty()){
					req+=" AND emplacementgeneral.adresse1 LIKE '%"+ nomVoie + "%'";
				}
				if(!dateControle.isEmpty()){
					req+=" AND datecontrole ='" + dateControle + "'";
				}
				if(!controleur.isEmpty()){
					req+=" AND idcontroleur ="+ controleur ;
				}
				
				req+=" ORDER BY str_to_date(datecontrole,'%d/%m/%Y') DESC";
				
				System.out.println("getListeEmplacement>"+req);
				ResultSet resultat1 = instruction.executeQuery(req);
				while (resultat1.next()) {
					// retour=resultat1.getInt("nombreImage");
					Emplacement emplacement = new Emplacement();
					emplacement.setIdImputation(resultat1.getString("emplacementgeneral.codeType"));
					emplacement.setCodeSecteur(resultat1.getString("codeSecteur"));
					emplacement.setAdresse1(resultat1.getString("adresse1"));
					emplacement.setNumRue(resultat1.getString("numRue"));
					emplacement.setNumEmplacement(resultat1.getString("numero"));
					emplacement.setLibelleImputation(resultat1.getString("libelle"));
					emplacement.setDateCreation(resultat1.getString("datecontrole"));
					emplacement.setNomRedevable(resultat1.getString("nomRedevable"));
					res.addElement(emplacement);
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
	
	

	public Emplacement getEmplacement(String raisonSocial, String numRue,
			String adresse1, String NomQuartier, String numRedevable) {
		adresse1 = FonctionCommun.ajouterAntislash(adresse1);
		raisonSocial = FonctionCommun.ajouterAntislash(raisonSocial);
		NomQuartier = FonctionCommun.ajouterAntislash(NomQuartier);
		Emplacement emplacement = new Emplacement();
		try {

			Statement instruction = con.connexion.createStatement();
			try {
				String req1 = " SELECT distinct emplacementgeneral.*,activiteprofession.libelle "
						+ " FROM emplacementgeneral,activiteprofession "
						+ " WHERE raisonSocial='"
						+ raisonSocial
						+ "'"
						+ " AND numRue='"
						+ numRue
						+ "'"
						+ " AND adresse1='"
						+ adresse1
						+ "'"
						+ " AND numRedevable="
						+ numRedevable
						+ " AND emplacementgeneral.source='normal' "
						+ " AND NomQuartier='" + NomQuartier + "'";

				debug.logger.debug(req1);
				ResultSet resultat = instruction.executeQuery(req1);
				while (resultat.next()) {
					emplacement.setNumEmplacement(resultat.getString("numero"));
					emplacement.setNomProprietaire(resultat
							.getString("nomProprietaire"));
					emplacement.setRaisonSocialeProprietaire(resultat
							.getString("raisonSocialeProprietaire"));
					emplacement.setPrenomProprietaire(resultat
							.getString("prenomProprietaire"));
					emplacement.setNumVoieProprietaire(resultat
							.getString("numVoieProprietaire"));
					emplacement.setCodeVoixProprietaire(resultat
							.getString("codeVoixProprietaire"));
					emplacement.setAdressProprietaire(resultat
							.getString("adressProprietaire"));
					emplacement.setComplement1AdressProprietaire(resultat
							.getString("complement1AdressProprietaire"));
					emplacement.setComplement2AdressProprietaire(resultat
							.getString("complement2AdressProprietaire"));
					emplacement.setCodePostaleProprietaire(resultat
							.getString("codePostaleProprietaire"));
					emplacement.setVilleeProprietaire(resultat
							.getString("villeeProprietaire"));
					emplacement.setCedexProprietaire(resultat
							.getString("cedexProprietaire"));
					emplacement.setNumTelFixeProprietaire(resultat
							.getString("numTelFixeProprietaire"));
					emplacement.setNumTelPortableProprietaire(resultat
							.getString("numTelPortableProprietaire"));
					emplacement.setNumTelFaxeProprietaire(resultat
							.getString("numTelFaxeProprietaire"));
					emplacement.setEmailProprietaire(resultat
							.getString("emailProprietaire"));

					emplacement.setRaisonSocial(resultat
							.getString("raisonSocial"));
					emplacement.setCodeProfeesion(String.valueOf(resultat
							.getInt("codePrefession")));
					emplacement.setLibelleProfeesion(resultat
							.getString("libelle"));
					emplacement.setNumRue(resultat.getString("numRue"));
					emplacement.setCodeVoie(resultat.getString("codeVoie"));
					emplacement.setCodeSecteur(resultat
							.getString("codeSecteur"));
					emplacement.setQuartier(resultat.getString("nomQuartier"));
					emplacement.setAdresse1(resultat.getString("adresse1"));
					emplacement.setAdresse2(resultat.getString("adresse2"));
					emplacement.setAdresse3(resultat.getString("adresse3"));
					emplacement.setCodePostal(resultat.getString("codePostal"));
					emplacement.setVille(resultat.getString("ville"));
					emplacement.setNumTel(resultat.getString("numTel"));
					emplacement.setNumPortable(resultat
							.getString("numPortable"));
					emplacement.setNumFax(resultat.getString("numFax"));
					emplacement.setEmail(resultat.getString("email"));
					emplacement.setCodeInscription(resultat
							.getString("codeInscription"));
					emplacement.setNumInscription(resultat
							.getString("numInscription"));
					emplacement.setNumRedevable(String.valueOf(resultat
							.getInt("numRedevable")));
					emplacement.setType(resultat.getString("codeType"));
					emplacement.setCedex(resultat.getString("cedex"));
					emplacement.setIdRedevableAutorise(String.valueOf(resultat
							.getInt("idRedevableAutorise")));
					emplacement.setEtatEmplacement(resultat
							.getString("enActivite"));
					emplacement.setNumeroEmplacementPersonalise(resultat
							.getString("numeroEmplacementPersonalise"));

					if (resultat.getString("dateInscription").length() != 0)
						emplacement.setDateInscription((new LaDate(resultat
								.getString("dateInscription")))
								.getLaDateString());
					else
						emplacement.setDateInscription(GestionDate
								.getDateAujourdhuiString());

					if (resultat.getString("dateDebutActivite").length() != 0)
						emplacement.setDateDebutActivite(new LaDate(resultat
								.getString("dateDebutActivite")));
					else
						emplacement.setDateDebutActivite(new LaDate(""));

					if (resultat.getString("dateFinActivite").length() != 0)
						emplacement.setDateFinActivite(resultat
								.getString("dateFinActivite"));
					else
						emplacement.setDateFinActivite("");
				}

				String idEmplacement = emplacement.getNumEmplacement();
				emplacement.setNombreOuvrageActif(this.getNombreOuvrage(
						idEmplacement, true));
				emplacement.setNombreOuvrage(this.getNombreOuvrage(
						idEmplacement, false));
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}

		return emplacement;
	}
	public Emplacement getEmplacement(String idEmplacement) {
		Emplacement emplacement = new Emplacement();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req1 = " SELECT distinct emplacementgeneral.*,rue.codeRivolie as codeRivolie,activiteprofession.libelle,"
						+ " imputation.anneeExercice,imputation.libelle,imputation.necessiteControleTerrain "
						+ " FROM emplacementgeneral,activiteprofession,imputation,rue "
						+ " WHERE  emplacementgeneral.numero ="
						+ idEmplacement
						+ " AND emplacementgeneral.codePrefession=activiteprofession.code"
						+ " AND imputation.idImputation= emplacementgeneral.codeType"
						+ " AND emplacementgeneral.source='normal'"
						+ " AND rue.codeVoie =   emplacementgeneral.codeVoie"
						+ " AND emplacementgeneral.anneeExerciceImputation= imputation.anneeExercice";
				//debug.logger.debug(req1);
				ResultSet resultat = instruction.executeQuery(req1);
				while (resultat.next()) {
					emplacement.setNomProprietaire(resultat.getString("nomProprietaire"));
					emplacement.setLibelleImputation(resultat.getString("imputation.libelle"));
					emplacement.setAnneeExerciceImputation(resultat.getString("emplacementgeneral.anneeExerciceImputation"));
					emplacement.setIdImputation(resultat.getString("codeType"));
					emplacement.setRaisonSocialeProprietaire(resultat.getString("raisonSocialeProprietaire"));
					emplacement.setPrenomProprietaire(resultat.getString("prenomProprietaire"));
					emplacement.setNumVoieProprietaire(resultat.getString("numVoieProprietaire"));
					emplacement.setNecessiteControleTerrain(resultat.getString("necessiteControleTerrain"));					
					emplacement.setObservation(resultat	.getString("observation"));
					emplacement.setCodeVoixProprietaire(resultat.getString("codeVoixProprietaire"));
					emplacement.setAdressProprietaire(resultat.getString("adressProprietaire"));
					emplacement.setComplement1AdressProprietaire(resultat.getString("complement1AdressProprietaire"));
					emplacement.setComplement2AdressProprietaire(resultat.getString("complement2AdressProprietaire"));
					emplacement.setCodePostaleProprietaire(resultat	.getString("codePostaleProprietaire"));
					emplacement.setVilleeProprietaire(resultat.getString("villeeProprietaire"));
					emplacement.setCedexProprietaire(resultat.getString("cedexProprietaire"));
					emplacement.setNumTelFixeProprietaire(resultat
							.getString("numTelFixeProprietaire"));
					emplacement.setNumTelPortableProprietaire(resultat
							.getString("numTelPortableProprietaire"));
					emplacement.setNumTelFaxeProprietaire(resultat
							.getString("numTelFaxeProprietaire"));
					emplacement.setEmailProprietaire(resultat
							.getString("emailProprietaire"));
					emplacement.setNomCommercialEmplacment(resultat
							.getString("raisonSocial"));
					emplacement.setRaisonSocial(resultat
							.getString("raisonSocial"));
					emplacement.setCodeProfeesion(String.valueOf(resultat
							.getInt("codePrefession")));
					emplacement.setLibelleProfeesion(resultat
							.getString("libelle"));
					emplacement.setNumRue(resultat.getString("numRue"));
					emplacement.setCodeVoie(resultat.getString("codeVoie"));
					emplacement.setCodeRivolie(resultat
							.getString("codeRivolie"));
					emplacement.setVille(resultat.getString("ville"));
					emplacement.setInfosIdoss(resultat.getString("infosIdoss"));
					emplacement.setRapprochementIdoss(resultat
							.getString("rapprochementIdoss"));
					emplacement.setComplementNumeroRueEmpl(resultat
							.getString("complementNumeroRueEmpl"));
					emplacement.setComplementNumeroRueProprietaire(resultat
							.getString("complementNumeroRueProprietaire"));

					emplacement.setDateReceptionDeclaration(resultat
							.getString("dateReceptionDeclaration"));
					
					// AMBULANT (OK):
					emplacement.setAmbulantNomTournee(resultat
							.getString("ambulantNomTournee"));
					emplacement
							.setAmbulantCarteProfessionelleDateObtention(resultat
									.getString("ambulantCarteProfessionelleDateObtention"));
					emplacement.setAmbulantCarteProfessionelleNumero(resultat
							.getString("ambulantCarteProfessionelleNumero"));
					emplacement
							.setAmbulantCertficatHygieneDateObtention(resultat
									.getString("ambulantCertficatHygieneDateObtention"));
					emplacement.setAmbulantCertficatHygieneNumero(resultat
							.getString("ambulantCertficatHygieneNumero"));
					emplacement
							.setAmbulantCertficatPompierDateObtention(resultat
									.getString("ambulantCertficatPompierDateObtention"));
					emplacement.setAmbulantCertficatPompierNumero(resultat
							.getString("ambulantCertficatPompierNumero"));
					emplacement.setAmbulantVehiculeMarque(resultat
							.getString("ambulantVehiculeMarque"));
					emplacement.setAmbulantVehiculeModele(resultat
							.getString("ambulantVehiculeModele"));
					emplacement.setAmbulantVehiculeImmatriculation(resultat
							.getString("ambulantVehiculeImmatriculation"));
					emplacement.setAmbulantCompanieAssurance(resultat
							.getString("ambulantCompanieAssurance"));

					emplacement.setNumeroEmplacementPersonalise(resultat
							.getString("numeroEmplacementPersonalise"));
					// Bus (OK) :
					emplacement.setBusCompanieAutoBus(resultat
							.getString("busCompanieAutobus"));
					emplacement.setBusNombreCar(resultat
							.getString("busNombreCar"));
					emplacement.setBusLigneDeBus(resultat
							.getString("busLigneDeBus"));
					emplacement.setBusNombreDePlaceAssises(resultat
							.getString("busNombreDePlaceAssises"));
					emplacement.setBusNombreDePlaceDebout(resultat
							.getString("busNombreDePlaceDebout"));

					// Taxi (OK) :
					// System.out.println("///////////////////"+resultat.getString("taxiNumeroTaxi"));
					emplacement.setTaxiNumeroTaxi(resultat
							.getString("taxiNumeroTaxi"));
					emplacement.setTaxiNumeroAssurance(resultat
							.getString("taxiNumeroAssurance"));
					emplacement.setTaxiImmatriculation(resultat
							.getString("taxiImmatriculation"));
					emplacement.setTaxiNumeroDeCarteGrise(resultat
							.getString("taxiNumeroDeCarteGrise"));

					// kiosque
					emplacement.setKiosqueCategorie(resultat
							.getString("kiosqueCategorie"));
					emplacement.setKiosqueNatureVente(resultat
							.getString("kiosqueNatureVente"));

					emplacement.setCodeSecteur(resultat
							.getString("codeSecteur"));
					emplacement.setNumEmplacement(String.valueOf(resultat
							.getInt("numero")));
					emplacement.setAdresseComplete(resultat.getString("numRue")
							+ " "
							+ resultat.getString("complementNumeroRueEmpl")
							+ " " + resultat.getString("adresse1") + " "
							+ resultat.getString("adresse2") + " "
							+ resultat.getString("adresse3") + " ");

					emplacement.setQuartier(resultat.getString("nomQuartier"));
					emplacement.setAdresse1(resultat.getString("adresse1"));
					emplacement.setAdresse2(resultat.getString("adresse2"));
					emplacement.setAdresse3(resultat.getString("adresse3"));
					emplacement.setCodePostal(resultat.getString("codePostal"));
					emplacement.setVille(resultat.getString("ville"));
					emplacement.setNumTel(resultat.getString("numTel"));
					emplacement.setNumPortable(resultat
							.getString("numPortable"));
					emplacement.setNumFax(resultat.getString("numFax"));
					emplacement.setEmail(resultat.getString("email"));
					emplacement.setCodeInscription(resultat
							.getString("codeInscription"));
					emplacement.setNumInscription(resultat
							.getString("numInscription"));
					emplacement.setNumRedevable(String.valueOf(resultat
							.getInt("numRedevable")));
					emplacement.setType(resultat.getString("codeType"));
					emplacement.setCedex(resultat.getString("cedex"));
					emplacement.setIdRedevableAutorise(String.valueOf(resultat
							.getInt("idRedevableAutorise")));
					emplacement.setNumeroRedevable(""
							+ resultat.getInt("numRedevable"));
					emplacement.setEtatEmplacement(resultat
							.getString("enActivite"));

					// CONVENTION :
					emplacement.setConventionDate(resultat
							.getString("conventionDate"));
					emplacement.setConventionDureeAns(resultat
							.getString("conventionDureeAns"));
					emplacement.setConventionDureeMois(resultat
							.getString("conventionDureeMois"));
					emplacement.setConventionDateEffet(resultat
							.getString("conventionDateEffet"));
					emplacement.setConventionDatePremiereRevision(resultat
							.getString("conventionDatePremiereRevision"));
					emplacement.setConventionDureeDatePremiereRevision(resultat
							.getString("conventionDureeDatePremiereRevision"));
					emplacement.setConventionObjet(resultat
							.getString("conventionObjet"));
					emplacement.setConventionLibelle(resultat
							.getString("conventionLibelle"));
					emplacement.setConventionRenouvellement(resultat
							.getString("conventionRenouvellement"));
					emplacement.setConventionMontantDuConditionnement(resultat
							.getString("conventionMontantDuConditionnement"));
					emplacement.setConventionRecondiction(resultat
							.getString("conventionRecondiction"));
					emplacement.setConventionRecondictionNombreMaximum(resultat
							.getString("conventionRecondictionNombreMaximum"));

					// DROIT DE VOIRIE :
					emplacement
							.setDroitDeVoirieAutorisationNumeroAutorisation(resultat
									.getString("droitDeVoirieAutorisationNumeroAutorisation"));
					emplacement.setDroitDeVoirieAutorisationDate(resultat
							.getString("droitDeVoirieAutorisationDate"));
					emplacement.setDroitDeVoirieAutorisationType(resultat
							.getString("droitDeVoirieAutorisationType"));
					emplacement.setDroitDeVoirieTraveauxDureeMois(resultat
							.getString("droitDeVoirieTraveauxDureeMois"));
					emplacement.setDroitDeVoirieTraveauxDureeJour(resultat
							.getString("droitDeVoirieTraveauxDureeJour"));
					emplacement.setDroitDeVoirieTraveauxExecute(resultat
							.getString("droitDeVoirieTraveauxExecute"));
					emplacement.setDroitDeVoirieTraveauxConforme(resultat
							.getString("droitDeVoirieTraveauxConforme"));
					emplacement.setDroitDeVoirieNomInspecteur(resultat
							.getString("droitDeVoirieNomInspecteur"));
					emplacement.setDroitDeVoirieNature(resultat
							.getString("droitDeVoirieNature"));
					emplacement.setDroitDeVoirieObjetDeTraveaux(resultat
							.getString("droitDeVoirieObjetDeTraveaux"));
					emplacement.setDroitDeVoirieObjetDeAutorisation(resultat
							.getString("droitDeVoirieObjetDeAutorisation"));

									String dateDebutActivite = resultat
							.getString("dateDebutActivite");
					StringTokenizer st = new StringTokenizer(dateDebutActivite,
							"/");
					st.nextToken();
					st.nextToken();
					emplacement.setAnneeExercice(st.nextElement().toString());

					if (resultat.getString("dateInscription").length() != 0)
						emplacement.setDateInscription((new LaDate(resultat
								.getString("dateInscription")))
								.getLaDateString());
					else
						emplacement.setDateInscription("");

					if (resultat.getString("dateDebutActivite").length() != 0)
						emplacement.setDateDebutActivite(new LaDate(resultat
								.getString("dateDebutActivite")));
					else
						emplacement.setDateDebutActivite(new LaDate(""));

					if (resultat.getString("dateFinActivite").length() != 0)
						emplacement.setDateFinActivite(resultat
								.getString("dateFinActivite"));
					else
						emplacement.setDateFinActivite("");
				}
				emplacement.setNombreOuvrageActif(this.getNombreOuvrage(
						idEmplacement, true));
				emplacement.setNombreOuvrage(this.getNombreOuvrage(
						idEmplacement, false));
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}

		return emplacement;
	}

	// Ajout d'un emplacment
	public void ajouterEmplacment(String raisonSocial, String numRue,
			String codeVoie, String leCodeSecteur, String NomQuartier,
			String adresse1, String adresse2, String adresse3, String cedex,
			String codePostal, String ville, String numTel, String numPortable,
			String numfax, String email, String codeInscription,
			String numInscription, String dateInscription,
			String dateDebutActivite, String dateFinActivite,
			String codeProfession, String raisonSocialeProprietaire,
			String nomProprietaire, String prenomProprietaire,
			String numVoieProprietaire, String codeVoixProprietaire,
			String adressProprietaire, String complement1AdressProprietaire,
			String complement2AdressProprietaire,
			String codePostaleProprietaire, String villeeProprietaire,
			String cedexProprietaire, String numTelFixeProprietaire,
			String numTelPortableProprietaire, String numTelFaxeProprietaire,
			String emailProprietaire, String choix, String numRedevable,
			String codeSecteur, String codeType, String idRedevableAutorise,
			String etatEmplacement, String anneeExerciceImputation,
			String rapprochementIdoss, String complementNumeroRueEmpl,
			String complementNumeroRueProprietaire, String ambulantNomTournee,
			String ambulantCarteProfessionelleDateObtention,
			String ambulantCarteProfessionelleNumero,
			String ambulantCertficatHygieneDateObtention,
			String ambulantCertficatHygieneNumero,
			String ambulantCertficatPompierDateObtention,
			String ambulantCertficatPompierNumero,
			String ambulantVehiculeMarque, String ambulantVehiculeModele,
			String ambulantVehiculeImmatriculation,
			String ambulantCompanieAssurance, String busCompanieAutobus,
			String busNombreCar, String busLigneDeBus,
			String busNombreDePlaceAssises, String busNombreDePlaceDebout,
			String taxiNumeroTaxi, String taxiNumeroAssurance,
			String taxiImmatriculation, String taxiNumeroDeCarteGrise,
			String conventionDate, String conventionDureeAns,
			String conventionDureeMois, String conventionDateEffet,
			String conventionDatePremiereRevision,
			String conventionDureeDatePremiereRevision, String conventionObjet,
			String conventionLibelle, String conventionRenouvellement,
			String conventionMontantDuConditionnement,
			String conventionRecondiction,
			String conventionRecondictionNombreMaximum, String marcheNomMarche,

			String droitDeVoirieAutorisationNumeroAutorisation,
			String droitDeVoirieAutorisationDate,
			String droitDeVoirieAutorisationType,
			String droitDeVoirieTraveauxDureeMois,
			String droitDeVoirieTraveauxDureeJour,
			String droitDeVoirieTraveauxExecute,
			String droitDeVoirieTraveauxConforme,
			String droitDeVoirieNomInspecteur, String droitDeVoirieNature,
			String droitDeVoirieObjetDeTraveaux,
			String droitDeVoirieObjetDeAutorisation, String kiosqueCategorie,
			String kiosqueNatureVente, String numeroEmplacementPersonalise,
			String observation, String dateReceptionDeclaration) 
	{

		NomQuartier = FonctionCommun.ajouterAntislash(NomQuartier);
		raisonSocial = FonctionCommun.ajouterAntislash(raisonSocial);
		adresse1 = FonctionCommun.ajouterAntislash(adresse1);
		adresse2 = FonctionCommun.ajouterAntislash(adresse2);
		adresse3 = FonctionCommun.ajouterAntislash(adresse3);
		observation = FonctionCommun.ajouterAntislash(observation);
		
		nomProprietaire = FonctionCommun.ajouterAntislash(nomProprietaire);
		prenomProprietaire = FonctionCommun
				.ajouterAntislash(prenomProprietaire);
		adressProprietaire = FonctionCommun
				.ajouterAntislash(adressProprietaire);
		complement1AdressProprietaire = FonctionCommun
				.ajouterAntislash(complement1AdressProprietaire);
		complement2AdressProprietaire = FonctionCommun
				.ajouterAntislash(complement2AdressProprietaire);
		if (rapprochementIdoss.equalsIgnoreCase("OUI"))
			rapprochementIdoss = "true";
		else
			rapprochementIdoss = "false";

		String requete = "INSERT INTO `emplacementgeneral`(`numRedevable`, `codePrefession`, `dateDebutActivite`,"
				+ "`dateFinActivite`, `raisonSocial`, `codeSecteur`, `codeType`, "
				+ "`codeVoie`, `numRue`, `adresse1`, `adresse2`, `adresse3`,`cedex`, "
				+ "`codePostal`, `ville`, `numTel`, `numFax`, `numPortable`, `email`, "
				+ "`codeInscription`, `numInscription`, `dateInscription`,  "
				+ "`nomQuartier`,`raisonSocialeProprietaire`, `nomProprietaire`, `prenomProprietaire`, "
				+ "`numTelPortableProprietaire`,`numVoieProprietaire`, "
				+ "`adressProprietaire`, `complement1AdressProprietaire`, `complement2AdressProprietaire`, "
				+ "`codePostaleProprietaire`, `villeeProprietaire`, `cedexProprietaire`, `numTelFixeProprietaire`,"
				+ "`numTelFaxeProprietaire`, `emailProprietaire`, `idRedevableAutorise`,"
				+ "`enActivite`,`anneeExerciceImputation`,`RapprochementIdoss`, "
				+

				" `complementNumeroRueEmpl`,`complementNumeroRueProprietaire`,"
				+ " ambulantNomTournee ,ambulantCarteProfessionelleDateObtention,"
				+ " ambulantCarteProfessionelleNumero, ambulantCertficatHygieneDateObtention,"
				+ " ambulantCertficatHygieneNumero,ambulantCertficatPompierDateObtention,"
				+ " ambulantCertficatPompierNumero,	ambulantVehiculeMarque,	"
				+ " ambulantVehiculeModele, ambulantVehiculeImmatriculation,"
				+ " ambulantCompanieAssurance, busCompanieAutobus, "
				+ " busNombreCar, busLigneDeBus,"
				+ " busNombreDePlaceAssises, busNombreDePlaceDebout,"
				+ " taxiNumeroTaxi,taxiNumeroAssurance,"
				+ " taxiImmatriculation,taxiNumeroDeCarteGrise,"
				+ " conventionDate ,conventionDureeAns ,conventionDureeMois ,"
				+ " conventionDateEffet , conventionDatePremiereRevision , conventionDureeDatePremiereRevision ,"
				+ " conventionObjet , conventionLibelle , conventionRenouvellement ,"
				+ " conventionMontantDuConditionnement , conventionRecondiction , conventionRecondictionNombreMaximum , marcheNomMarche, "
				+

				" droitDeVoirieAutorisationNumeroAutorisation, droitDeVoirieAutorisationDate,"
				+ " droitDeVoirieAutorisationType, droitDeVoirieTraveauxDureeMois, droitDeVoirieTraveauxDureeJour,"
				+ " droitDeVoirieTraveauxExecute, droitDeVoirieTraveauxConforme, droitDeVoirieNomInspecteur,"
				+ " droitDeVoirieNature, droitDeVoirieObjetDeTraveaux, droitDeVoirieObjetDeAutorisation,"
				+ " kiosqueCategorie ,kiosqueNatureVente,numeroEmplacementPersonalise, observation,dateReceptionDeclaration  )"
				+ " VALUES("
				+ numRedevable
				+ ","
				+ codeProfession
				+ ",'"
				+ dateDebutActivite
				+ "','"
				+ dateFinActivite
				+ "','"
				+ raisonSocial
				+ "',"
				+ codeSecteur
				+ ",'"
				+ codeType
				+ "','"
				+ codeVoie
				+ "','"
				+ numRue
				+ "','"
				+ adresse1
				+ "','"
				+ adresse2
				+ "','"
				+ adresse3
				+ "','"
				+ cedex
				+ "','"
				+ codePostal
				+ "','"
				+ ville
				+ "','"
				+ numTel
				+ "','"
				+ numfax
				+ "','"
				+ numPortable
				+ "','"
				+ email
				+ "','"
				+ codeInscription
				+ "','"
				+ numInscription
				+ "','"
				+ dateInscription
				+ "','"
				+ NomQuartier
				+ "','"
				+ raisonSocialeProprietaire
				+ "','"
				+ nomProprietaire
				+ "','"
				+ prenomProprietaire
				+ "','"
				+ numTelPortableProprietaire
				+ "','"
				+ numVoieProprietaire
				+ "','"
				+ adressProprietaire
				+ "','"
				+ complement1AdressProprietaire
				+ "','"
				+ complement2AdressProprietaire
				+ "','"
				+ codePostaleProprietaire
				+ "','"
				+ villeeProprietaire
				+ "','"
				+ cedexProprietaire
				+ "','"
				+ numTelFixeProprietaire
				+ "','"
				+ numTelFaxeProprietaire
				+ "','"
				+ emailProprietaire
				+ "','"
				+ idRedevableAutorise
				+ "','"
				+ etatEmplacement
				+ "','"
				+ anneeExerciceImputation
				+ "','"
				+ rapprochementIdoss
				+ "','"
				+

				complementNumeroRueEmpl
				+ "','"
				+ complementNumeroRueProprietaire
				+ "','"
				+ ambulantNomTournee
				+ "','"
				+ ambulantCarteProfessionelleDateObtention
				+ "','"
				+ ambulantCarteProfessionelleNumero
				+ "','"
				+ ambulantCertficatHygieneDateObtention
				+ "','"
				+ ambulantCertficatHygieneNumero
				+ "','"
				+ ambulantCertficatPompierDateObtention
				+ "','"
				+ ambulantCertficatPompierNumero
				+ "','"
				+ ambulantVehiculeMarque
				+ "','"
				+ ambulantVehiculeModele
				+ "','"
				+ ambulantVehiculeImmatriculation
				+ "','"
				+ ambulantCompanieAssurance
				+ "','"
				+ busCompanieAutobus
				+ "','"
				+ busNombreCar
				+ "','"
				+ busLigneDeBus
				+ "','"
				+ busNombreDePlaceAssises
				+ "','"
				+ busNombreDePlaceDebout
				+ "','"
				+ taxiNumeroTaxi
				+ "','"
				+ taxiNumeroAssurance
				+ "','"
				+ taxiImmatriculation
				+ "','"
				+ taxiNumeroDeCarteGrise
				+ "','"
				+

				conventionDate
				+ "','"
				+ conventionDureeAns
				+ "','"
				+ conventionDureeMois
				+ "','"
				+ conventionDateEffet
				+ "','"
				+ conventionDatePremiereRevision
				+ "','"
				+ conventionDureeDatePremiereRevision
				+ "','"
				+ conventionObjet
				+ "','"
				+ conventionLibelle
				+ "','"
				+ conventionRenouvellement
				+ "','"
				+ conventionMontantDuConditionnement
				+ "','"
				+ conventionRecondiction
				+ "','"
				+ conventionRecondictionNombreMaximum
				+ "','"
				+ marcheNomMarche
				+ "','"
				+

				droitDeVoirieAutorisationNumeroAutorisation
				+ "','"
				+ droitDeVoirieAutorisationDate
				+ "','"
				+ droitDeVoirieAutorisationType
				+ "','"
				+ droitDeVoirieTraveauxDureeMois
				+ "','"
				+ droitDeVoirieTraveauxDureeJour
				+ "','"
				+ droitDeVoirieTraveauxExecute
				+ "','"
				+ droitDeVoirieTraveauxConforme
				+ "','"
				+ droitDeVoirieNomInspecteur
				+ "','"
				+ droitDeVoirieNature
				+ "','"
				+ droitDeVoirieObjetDeTraveaux
				+ "','"
				+ droitDeVoirieObjetDeAutorisation
				+ "','"
				+ kiosqueCategorie
				+ "','"
				+ kiosqueNatureVente
				+ "','"
				+ numeroEmplacementPersonalise 
				+ "','"
				+observation
				+ "','"
				+dateReceptionDeclaration				
				+"')";

		debug.logger.debug(requete);
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				int result1 = instruction.executeUpdate(requete);
				new RequestAdmin().InsertAction(
						"Création d’un nouvel emplacement pour le redvable "
								+ numRedevable, GestionDate.getDateTime());
				debug.logger.debug("Création d’un nouvel emplacement "
						+ raisonSocial + " pour le redvable " + numRedevable);
			} catch (SQLException e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (SQLException e) {
			debug.logger.fatal(e.getMessage());
		}

	}

	// Ajout d'un emplacment
	public String ajouterEmplacment(String dateDebutActivite,String codeVoie,String numRue, String adresse1,
			String numRedevable, String typeTaxe,
			String anneeExerciceImputation, String source, String numeroTmp,
			String complNumRue, String adresse2, String adresse3,
			String cp, String ville) {
		adresse1 = FonctionCommun.ajouterAntislash(adresse1);
		RequestImputation reqImputation = new RequestImputation();
		adresse2 = FonctionCommun.ajouterAntislash(adresse2);
		adresse3 = FonctionCommun.ajouterAntislash(adresse3);
		String codeType = reqImputation
				.getIdImputationFromLibelle(typeTaxe, "");
		String res = "";
		String requete = "INSERT INTO emplacementgeneral( dateDebutActivite, codeVoie, numRue, adresse1,"
				+ "numRedevable, codeType, anneeExerciceImputation,source, numeroTmp,complementNumeroRueEmpl,"
				+ " adresse2,adresse3,codePostal,ville )" 
				+ " VALUES('" 
				+ dateDebutActivite  + "','" 
				+ codeVoie + "','" + numRue + "','"
				+ adresse1 + "','" + numRedevable + "','" + codeType + "','"
				+ anneeExerciceImputation + "','" + source + "','" + numeroTmp
				+ "','" + complNumRue + "','" + adresse2 + "','" + adresse3 + "','" + cp + "','" + ville
				+ "')";

		debug.logger.debug(requete);
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				int result1 = instruction.executeUpdate(requete);
				instruction = con.connexion.createStatement();
				String req1 = " SELECT * FROM emplacementgeneral"
						+ " WHERE  emplacementgeneral.numeroTmp =" + numeroTmp;
				debug.logger.debug(req1);
				ResultSet resultat = instruction.executeQuery(req1);
				while (resultat.next()) {
					res = String.valueOf(resultat.getInt("numero"));
				}
			} catch (SQLException e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (SQLException e) {
			debug.logger.fatal(e.getMessage());
		}
		return res;
	}

	// Ajout d'un emplacment
	public synchronized void modifierEmplacment(String idEmplacement,
			String raisonSocial, String numRue, String codeVoie,
			String leCodeSecteur, String NomQuartier, String adresse1,
			String adresse2, String adresse3, String cedex, String codePostal,
			String ville, String numTel, String numPortable, String numfax,
			String email, String codeInscription, String numInscription,
			String dateInscription, String dateDebutActivite,
			String dateFinActivite, String codeProfession,
			String raisonSocialeProprietaire, String nomProprietaire,
			String prenomProprietaire, String numVoieProprietaire,
			String codeVoixProprietaire, String adressProprietaire,
			String complement1AdressProprietaire,
			String complement2AdressProprietaire,
			String codePostaleProprietaire, String villeeProprietaire,
			String cedexProprietaire, String numTelFixeProprietaire,
			String numTelPortableProprietaire, String numTelFaxeProprietaire,
			String emailProprietaire, String choix, String numRedevable,
			String codeSecteur, String codeType, String idRedevableAutorise,
			String etatEmplacement, String rapprochementIdoss,
			String complementNumeroRueEmpl,
			String complementNumeroRueProprietaire, String taxiNumeroTaxi,
			String taxiNumeroAssurance, String taxiImmatriculation,
			String taxiNumeroDeCarteGrise, String busCompanieAutobus,
			String busNombreCar, String busLigneDeBus,
			String busNombreDePlaceAssises, String busNombreDePlaceDebout,
			String ambulantNomTournee,
			String ambulantCarteProfessionelleDateObtention,
			String ambulantCarteProfessionelleNumero,
			String ambulantCertficatHygieneDateObtention,
			String ambulantCertficatHygieneNumero,
			String ambulantCertficatPompierDateObtention,
			String ambulantCertficatPompierNumero,
			String ambulantVehiculeMarque, String ambulantVehiculeModele,
			String ambulantVehiculeImmatriculation,
			String ambulantCompanieAssurance, String conventionDate,
			String conventionDureeAns, String conventionDureeMois,
			String conventionDateEffet, String conventionDatePremiereRevision,
			String conventionDureeDatePremiereRevision, String conventionObjet,
			String conventionLibelle, String conventionRenouvellement,
			String conventionMontantDuConditionnement,
			String conventionRecondiction,
			String conventionRecondictionNombreMaximum, String marcheNomMarche,

			String droitDeVoirieAutorisationNumeroAutorisation,
			String droitDeVoirieAutorisationDate,
			String droitDeVoirieAutorisationType,
			String droitDeVoirieTraveauxDureeMois,
			String droitDeVoirieTraveauxDureeJour,
			String droitDeVoirieTraveauxExecute,
			String droitDeVoirieTraveauxConforme,
			String droitDeVoirieNomInspecteur, String droitDeVoirieNature,
			String droitDeVoirieObjetDeTraveaux,
			String droitDeVoirieObjetDeAutorisation, String kiosqueCategorie,
			String kiosqueNatureVente,
			String observation,String dateReceptionDeclaration,
			String numeroEmplacementPersonalise) 
	{
		NomQuartier = FonctionCommun.ajouterAntislash(NomQuartier);
		raisonSocial = FonctionCommun.ajouterAntislash(raisonSocial);
		adresse1 = FonctionCommun.ajouterAntislash(adresse1);
		adresse2 = FonctionCommun.ajouterAntislash(adresse2);
		adresse3 = FonctionCommun.ajouterAntislash(adresse3);
		observation = FonctionCommun.ajouterAntislash(observation);
		nomProprietaire = FonctionCommun.ajouterAntislash(nomProprietaire);
		prenomProprietaire = FonctionCommun
				.ajouterAntislash(prenomProprietaire);
		adressProprietaire = FonctionCommun
				.ajouterAntislash(adressProprietaire);

		complement1AdressProprietaire = FonctionCommun
				.ajouterAntislash(complement1AdressProprietaire);
		complement2AdressProprietaire = FonctionCommun
				.ajouterAntislash(complement2AdressProprietaire);
		debug.logger.debug("Modification de l'emplacment " + idEmplacement);
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r = " UPDATE emplacementgeneral set" + " numRedevable="
						+ numRedevable + "," + " codePrefession ="
						+ codeProfession + "," + " dateDebutActivite ='"
						+ dateDebutActivite + "'," + " dateFinActivite  ='"
						+ dateFinActivite + "'," + " raisonSocial   ='"
						+ raisonSocial + "'," + " codeSecteur  =" + codeSecteur
						+ "," + " codeType  ='" + codeType + "',"
						+ " codeVoie ='" + codeVoie + "'," + " numRue  ='"
						+ numRue + "'," + " adresse1 ='" + adresse1 + "',"
						+ " adresse2  ='" + adresse2 + "'," + " adresse3  ='"
						+ adresse3 + "'," + " cedex     ='" + cedex + "',"
						+ " codePostal ='" + codePostal + "'," + " ville    ='"
						+ ville + "'," + " numTel  ='" + numTel + "',"
						+ " numFax  ='" + numfax + "'," + " numPortable   ='"
						+ numPortable + "'," + " email     ='" + email + "',"
						+ " codeInscription  ='" + codeInscription + "',"
						+ " numInscription  ='" + numInscription + "',"
						+ " numeroEmplacementPersonalise  ='" + numeroEmplacementPersonalise + "',"
						+ " dateInscription  ='" + dateInscription + "',"
						+ " nomQuartier   ='" + NomQuartier + "',"
						+ " raisonSocialeProprietaire   ='"
						+ raisonSocialeProprietaire + "',"
						+ " nomProprietaire  ='" + nomProprietaire + "',"
						+ " prenomProprietaire    ='" + prenomProprietaire
						+ "'," + " numTelPortableProprietaire ='"
						+ numTelPortableProprietaire + "',"
						+ " numVoieProprietaire   ='" + numVoieProprietaire
						+ "'," + " adressProprietaire  ='" + adressProprietaire
						+ "'," + " complement1AdressProprietaire ='"
						+ complement1AdressProprietaire + "',"
						+ " complement2AdressProprietaire  ='"
						+ complement2AdressProprietaire + "',"
						+ " codePostaleProprietaire  ='"
						+ codePostaleProprietaire + "',"
						+ " villeeProprietaire  ='" + villeeProprietaire + "',"
						+ " cedexProprietaire ='" + cedexProprietaire + "',"
						+ " numTelFixeProprietaire ='" + numTelFixeProprietaire
						+ "'," + " numTelFaxeProprietaire  ='"
						+ numTelFaxeProprietaire + "',"
						+ " emailProprietaire  ='" + emailProprietaire + "',"
						+ " enActivite  ='" + etatEmplacement + "',"
						+ " rapprochementIdoss  ='" + rapprochementIdoss + "',"
						+ " idRedevableAutorise=" + idRedevableAutorise + ","
						+ " complementNumeroRueEmpl='"
						+ complementNumeroRueEmpl + "',"
						+ " complementNumeroRueProprietaire='"
						+ complementNumeroRueProprietaire + "'," +

						" ambulantNomTournee='" + ambulantNomTournee + "',"
						+ " ambulantCarteProfessionelleDateObtention='"
						+ ambulantCarteProfessionelleDateObtention + "',"
						+ " ambulantCarteProfessionelleNumero='"
						+ ambulantCarteProfessionelleNumero + "',"
						+ " ambulantCertficatHygieneDateObtention='"
						+ ambulantCertficatHygieneDateObtention + "',"
						+ " ambulantCertficatHygieneNumero='"
						+ ambulantCertficatHygieneNumero + "',"
						+ " ambulantCertficatPompierDateObtention='"
						+ ambulantCertficatPompierDateObtention + "',"
						+ " ambulantCertficatPompierNumero='"
						+ ambulantCertficatPompierNumero + "',"
						+ " ambulantVehiculeMarque='" + ambulantVehiculeMarque
						+ "'," + " ambulantVehiculeModele='"
						+ ambulantVehiculeModele + "',"
						+ " ambulantVehiculeImmatriculation='"
						+ ambulantVehiculeImmatriculation + "',"
						+ " ambulantCompanieAssurance='"
						+ ambulantCompanieAssurance + "'," +

						" droitDeVoirieAutorisationNumeroAutorisation='"
						+ droitDeVoirieAutorisationNumeroAutorisation + "',"
						+ " droitDeVoirieAutorisationDate='"
						+ droitDeVoirieAutorisationDate + "',"
						+ " droitDeVoirieAutorisationType='"
						+ droitDeVoirieAutorisationType + "',"
						+ " droitDeVoirieTraveauxDureeMois='"
						+ droitDeVoirieTraveauxDureeMois + "',"
						+ " droitDeVoirieTraveauxDureeJour='"
						+ droitDeVoirieTraveauxDureeJour + "',"
						+ " droitDeVoirieTraveauxExecute='"
						+ droitDeVoirieTraveauxExecute + "',"
						+ " droitDeVoirieTraveauxConforme='"
						+ droitDeVoirieTraveauxConforme + "',"
						+ " droitDeVoirieNomInspecteur='"
						+ droitDeVoirieNomInspecteur + "',"
						+ " droitDeVoirieNature='" + droitDeVoirieNature + "',"
						+ " droitDeVoirieObjetDeTraveaux='"
						+ droitDeVoirieObjetDeTraveaux + "',"
						+ " droitDeVoirieObjetDeAutorisation='"
						+ droitDeVoirieObjetDeAutorisation + "'," +

						" busCompanieAutobus='" + busCompanieAutobus + "',"
						+ " busNombreCar='" + busNombreCar + "',"
						+ " busLigneDeBus='" + busLigneDeBus + "',"
						+ " busNombreDePlaceAssises='"
						+ busNombreDePlaceAssises + "',"
						+ " busNombreDePlaceDebout='" + busNombreDePlaceDebout
						+ "'," +

						" taxiNumeroTaxi='" + taxiNumeroTaxi + "',"
						+ " taxiNumeroAssurance='" + taxiNumeroAssurance + "',"
						+ " taxiImmatriculation='" + taxiImmatriculation + "',"
						+ " taxiNumeroDeCarteGrise='" + taxiNumeroDeCarteGrise
						+ "'," +

						" marcheNomMarche='" + marcheNomMarche + "'," +

						" kiosqueCategorie='" + kiosqueCategorie + "',"
						+ " kiosqueNatureVente='" + kiosqueNatureVente + "'," +

						"  conventionDate ='" + conventionDate + "',"
						+ "  conventionDureeAns ='" + conventionDureeAns + "',"
						+ " conventionDureeMois ='" + conventionDureeMois
						+ "'," + " conventionDateEffet ='"
						+ conventionDateEffet + "',"
						+ " conventionDatePremiereRevision ='"
						+ conventionDatePremiereRevision + "',"
						+ " conventionDureeDatePremiereRevision ='"
						+ conventionDureeDatePremiereRevision + "',"
						+ " conventionObjet ='" + conventionObjet + "',"
						+ " conventionLibelle ='" + conventionLibelle + "',"
						+ " conventionRenouvellement ='"
						+ conventionRenouvellement + "',"
						+ " conventionMontantDuConditionnement ='"
						+ conventionMontantDuConditionnement + "',"
						+ " conventionRecondiction ='" + conventionRecondiction
						+ "'," + " conventionRecondictionNombreMaximum ='"
						+ conventionRecondictionNombreMaximum + "',observation='"+observation+"',dateReceptionDeclaration='"+dateReceptionDeclaration+"'" +
						" WHERE numero=" + idEmplacement;
				debug.logger.debug("Mise a jour emplacement ");
				debug.logger.debug(r);
				int result1 = instruction.executeUpdate(r);
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			// R2tDebug.write(e.toString());
			debug.logger.fatal(e.getMessage());
		}
	}
	
	

	public void supprimerEmplacementGeneral(String numEmplacment) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = "delete from emplacementgeneral WHERE numero="
						+ numEmplacment;
				debug.logger.debug(req);
				int result1 = instruction.executeUpdate(req);
				new RequestAdmin().InsertAction(
						"Suppression de l'emplacment numero" + numEmplacment,
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

	
	
	
	public String getNombreOuvrage(String idEmplacement, boolean actif) {
		String res = "-";
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req1 = "  SELECT count( `article`.`id_article`) as count"
						+ "	FROM `article` ,`emplacementgeneral`,`elementfacturation`"
						+ "	WHERE article.`id_elementfacturation` =`elementfacturation`.`numero`"
						+ "	AND `elementfacturation`.`numeroEmplacement` = `emplacementgeneral`.`numero`"
						+ "	AND `emplacementgeneral`.`numero`=" + idEmplacement;

				if (actif)
					req1 = req1 + " AND article.etat != 'NePlusFacturer'";

				debug.logger.debug(req1);
				ResultSet resultat1 = instruction.executeQuery(req1);
				while (resultat1.next()) {
					res = resultat1.getString("count");
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

	public Vector getListeEmplacement(String numEmplacement,
			String nomEmplacement, String adresseEmplacement) {
		Vector res = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req1 = " SELECT distinct emplacementgeneral.*,activiteprofession.libelle "
						+ " FROM emplacementgeneral,activiteprofession "
						+ " WHERE emplacementgeneral.codePrefession=activiteprofession.code"
						+ " AND emplacementgeneral.source='normal' ";

				if (numEmplacement != null && numEmplacement.length() != 0)
					req1 = req1 + " AND numero =" + numEmplacement;

				if (nomEmplacement != null && nomEmplacement.length() != 0)
					req1 = req1 + " AND raisonSocial like '%" + nomEmplacement
							+ "%'";

				if (adresseEmplacement != null
						&& adresseEmplacement.length() != 0)
					req1 = req1
							+ " AND concat(numRue,\" \",adresse1,\" \",adresse2,\" \",adresse3,\" \" ) like'%"
							+ adresseEmplacement + "%'";

				ResultSet resultat = instruction.executeQuery(req1);
				while (resultat.next()) {
					Emplacement emplacement = new Emplacement();
					emplacement.setNumEmplacement(resultat.getString("numero"));
					emplacement.setNomProprietaire(resultat
							.getString("nomProprietaire"));
					emplacement.setRaisonSocialeProprietaire(resultat
							.getString("raisonSocialeProprietaire"));
					emplacement.setPrenomProprietaire(resultat
							.getString("prenomProprietaire"));
					emplacement.setNumVoieProprietaire(resultat
							.getString("numVoieProprietaire"));
					emplacement.setCodeVoixProprietaire(resultat
							.getString("codeVoixProprietaire"));
					emplacement.setAdressProprietaire(resultat
							.getString("adressProprietaire"));
					emplacement.setComplement1AdressProprietaire(resultat
							.getString("complement1AdressProprietaire"));
					emplacement.setComplement2AdressProprietaire(resultat
							.getString("complement2AdressProprietaire"));
					emplacement.setCodePostaleProprietaire(resultat
							.getString("codePostaleProprietaire"));
					emplacement.setVilleeProprietaire(resultat
							.getString("villeeProprietaire"));
					emplacement.setCedexProprietaire(resultat
							.getString("cedexProprietaire"));
					emplacement.setNumTelFixeProprietaire(resultat
							.getString("numTelFixeProprietaire"));
					emplacement.setNumTelPortableProprietaire(resultat
							.getString("numTelPortableProprietaire"));
					emplacement.setNumTelFaxeProprietaire(resultat
							.getString("numTelFaxeProprietaire"));
					emplacement.setEmailProprietaire(resultat
							.getString("emailProprietaire"));

					emplacement.setRaisonSocial(resultat
							.getString("raisonSocial"));
					emplacement.setCodeProfeesion(String.valueOf(resultat
							.getInt("codePrefession")));
					emplacement.setLibelleProfeesion(resultat
							.getString("libelle"));
					emplacement.setNumRue(resultat.getString("numRue"));
					emplacement.setCodeVoie(resultat.getString("codeVoie"));
					emplacement.setCodeSecteur(resultat
							.getString("codeSecteur"));
					emplacement.setQuartier(resultat.getString("nomQuartier"));
					emplacement.setAdresse1(resultat.getString("adresse1"));
					emplacement.setAdresse2(resultat.getString("adresse2"));
					emplacement.setAdresse3(resultat.getString("adresse3"));
					emplacement.setCodePostal(resultat.getString("codePostal"));
					emplacement.setVille(resultat.getString("ville"));
					emplacement.setNumTel(resultat.getString("numTel"));
					emplacement.setNumPortable(resultat
							.getString("numPortable"));
					emplacement.setNumFax(resultat.getString("numFax"));
					emplacement.setEmail(resultat.getString("email"));
					emplacement.setCodeInscription(resultat
							.getString("codeInscription"));
					emplacement.setNumInscription(resultat
							.getString("numInscription"));
					emplacement.setNumRedevable(String.valueOf(resultat
							.getInt("numRedevable")));
					emplacement.setType(resultat.getString("codeType"));
					emplacement.setCedex(resultat.getString("cedex"));
					emplacement.setIdRedevableAutorise(String.valueOf(resultat
							.getInt("idRedevableAutorise")));
					emplacement.setEtatEmplacement(resultat
							.getString("enActivite"));
					emplacement.setNumEmplacement(String.valueOf(resultat
							.getInt("numero")));
					emplacement.setAdresseComplete(resultat.getString("numRue")
							+ " " + resultat.getString("adresse1") + " "
							+ resultat.getString("adresse2") + " "
							+ resultat.getString("adresse3") + " ");

					emplacement.setQuartier(resultat.getString("nomQuartier"));
					emplacement.setAdresse1(resultat.getString("adresse1"));
					emplacement.setAdresse2(resultat.getString("adresse2"));
					emplacement.setAdresse3(resultat.getString("adresse3"));
					emplacement.setCodePostal(resultat.getString("codePostal"));
					emplacement.setVille(resultat.getString("ville"));
					emplacement.setNumTel(resultat.getString("numTel"));
					emplacement.setNumPortable(resultat
							.getString("numPortable"));
					emplacement.setNumFax(resultat.getString("numFax"));
					emplacement.setEmail(resultat.getString("email"));
					emplacement.setCodeInscription(resultat
							.getString("codeInscription"));
					emplacement.setNumInscription(resultat
							.getString("numInscription"));
					emplacement.setNumRedevable(String.valueOf(resultat
							.getInt("numRedevable")));
					emplacement.setType(resultat.getString("codeType"));
					emplacement.setCedex(resultat.getString("cedex"));
					emplacement.setIdRedevableAutorise(String.valueOf(resultat
							.getInt("idRedevableAutorise")));
					emplacement.setEtatEmplacement(resultat
							.getString("enActivite"));

					res.addElement(emplacement);
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

	public void transfererEmplacement(String numEmplacementATransferer,
			String numNewRedevable) {
		debug.logger.debug("transfert de l'emplacment "
				+ numEmplacementATransferer + " vers le redevable "
				+ numNewRedevable);
		try {
			Statement instruction = con.connexion.createStatement();
			try 
			{
				Emplacement empl= this.getEmplacement(numEmplacementATransferer);
				String numOldRedevable = empl.getNumRedevable();
				//System.out.println("numOldRedevable="+numOldRedevable);
				
				String r = " UPDATE emplacementgeneral set" + " numRedevable="
						+ numNewRedevable + " , idRedevableAutorise= "
						+ numNewRedevable + " WHERE numero="
						+ numEmplacementATransferer;
				debug.logger.debug("Mise a jour emplacement" + r);
				int result1 = instruction.executeUpdate(r);
				new RequestAdmin().InsertAction(
						"Transfert de l'emplacment N°"
								+ numEmplacementATransferer
								+ " du redevable N° " + numOldRedevable 
								+ " au redevable N° " + numNewRedevable,
						GestionDate.getDateTime());
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			// R2tDebug.write(e.toString());
			debug.logger.fatal(e.getMessage());
		}
	}

	// Ajout une image a un emplacement
	public String ajouterImageAunEmplacement(String idEmplacement) {
		// recuperation de l'emplacement :
		RequestEmplacement reqEmplacment = new RequestEmplacement();
		Emplacement emplacement = reqEmplacment.getEmplacement(idEmplacement);
		// Recherche des images existante pour cet Empalcment
		String nomDernierImage = "";
		String nomNewImage = "";
		int newIndex = -1;
		int indexMax=0;
	
		try {

			Statement instruction = con.connexion.createStatement();
			try {
				String adresse1 = FonctionCommun.ajouterAntislash(emplacement
						.getAdresse1());
				String req1 = " SELECT image.nomImage as derniereImage FROM image "
						+ " WHERE image.nomImage LIKE '"
						+ emplacement.getNumRue() + " " + adresse1 + "(%'";
				debug.logger.debug(req1);
				ResultSet resultat1 = instruction.executeQuery(req1);
				//paul 29/03/2019 recherche index max
				while (resultat1.next()) {
					nomDernierImage = resultat1.getString("derniereImage");
					indexMax=Integer.parseInt(nomDernierImage.substring(nomDernierImage.indexOf("(")+1, nomDernierImage.indexOf(")")));
					newIndex=newIndex<indexMax?indexMax:newIndex;
				}
				
		/*		
				if (nomDernierImage != null
						&& !nomDernierImage.equalsIgnoreCase("null")
						&& nomDernierImage.length() != 0) {
					// il y a des images pour cet emplacment
					debug.logger.debug("Il y a des images dans cette adresse ");
					debug.logger.debug("nomDernierImage= " + nomDernierImage);

					// Recuperation du bon index
					String index = nomDernierImage.substring(nomDernierImage
							.indexOf("(") + 1, nomDernierImage.indexOf(")"));

					debug.logger
							.debug("/////////////////////////////////////////");
					debug.logger.debug("dernier indice de l'image= " + index);
					debug.logger
							.debug("/////////////////////////////////////////");
					newIndex = Integer.parseInt(index) + 1;
					debug.logger
							.debug("nouveau indice de l'image= " + newIndex);
				} else {
					// il n'y a des images pour cet emplacment
				}
			*/
				// Nommage du nouveau fichier
				nomNewImage = emplacement.getNumRue() + " "
						+ emplacement.getAdresse1() + "(" + (newIndex+1) + ").jpg";
				debug.logger.debug("nom new image=" + nomNewImage);

				// Insertion dans la table image
				String idNewImage = ajouterImage(nomNewImage, GestionDate
						.getDateAujourdhuiString());

				// Insertion dans la table imageEmplacment :
				ajouterImageEmplacement(idNewImage, idEmplacement);
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}

		return nomNewImage;
	}
	
	
	
	// Inserer un document dans la table document_emplacement
	public String ajouterDocumentEmplacement(String nomDocument, String idEmplacement) {
		String fileNameSaved = "";
		try {
			Statement instruction = con.connexion.createStatement();
			try 
			{
				String lastIdDocument ="0" ;
				String req1 = "SELECT max(idDocument)+1 AS lastIdDocument FROM document_emplacement";
				debug.logger.debug(req1);
				ResultSet resultat1 = instruction.executeQuery(req1);
				
				while (resultat1.next()) 
				{
					lastIdDocument = resultat1.getString("lastIdDocument");
				}
				
				if( lastIdDocument==null) lastIdDocument = "1";
				instruction = con.connexion.createStatement();
				fileNameSaved = lastIdDocument+"-"+nomDocument;
				String requete1 = "INSERT INTO document_emplacement (idEmplacement,nomDocument) "
						+ " VALUES(" + idEmplacement + ",'" + fileNameSaved  + "')";
				debug.logger.debug(requete1);
				int result1 = instruction.executeUpdate(requete1);
				new RequestAdmin().InsertAction("Ajout de document " + nomDocument +" a l'emplacement " + idEmplacement, GestionDate.getDateTime());
				
			} catch (SQLException e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (SQLException e) {
			debug.logger.fatal(e.getMessage());
		}
		return fileNameSaved;
	}


	// Inserer une image
	public String ajouterImage(String nomImage, String dateCreation) {
		String idImage = "";
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				instruction = con.connexion.createStatement();
				nomImage = FonctionCommun.ajouterAntislash(nomImage);
				String requete1 = "INSERT INTO image( nomImage, dateCreation)"
						+ " VALUES('" + nomImage + "','" + dateCreation + "')";
				debug.logger.debug(requete1);
				int result1 = instruction.executeUpdate(requete1);
				instruction = con.connexion.createStatement();

				String requete2 = " SELECT * FROM image"
						+ " WHERE  nomImage ='" + nomImage + "'";
				debug.logger.debug(requete2);
				ResultSet resultat2 = instruction.executeQuery(requete2);
				while (resultat2.next()) {
					idImage = String.valueOf(resultat2.getInt("idImage"));
				}
			} catch (SQLException e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (SQLException e) {
			debug.logger.fatal(e.getMessage());
		}
		return idImage;
	}

	// Inserer une image dans la table image
	public void ajouterImageEmplacement(String idImage, String idEmplacement) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				instruction = con.connexion.createStatement();
				String requete1 = "INSERT INTO image_emplacementgeneral( idImage, idEmplacement)"
						+ " VALUES('" + idImage + "','" + idEmplacement + "')";
				debug.logger.debug(requete1);
				int result1 = instruction.executeUpdate(requete1);
				new RequestAdmin().InsertAction("Ajout de l image N°" + idImage
						+ " pour l emplacement N° " + idEmplacement,
						GestionDate.getDateTime());
			} catch (SQLException e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (SQLException e) {
			debug.logger.fatal(e.getMessage());
		}
	}

	public Emplacement getEmplacement(String typeTaxe, String numRue,
			String codeRivolie, String nomRedevable) {
		nomRedevable = FonctionCommun.ajouterAntislash(nomRedevable);
		Emplacement empl = new Emplacement();
		empl.setNumEmplacement("0");
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req1 = " SELECT emplacementgeneral.numero,emplacementgeneral.infosIdoss "
						+ " FROM emplacementgeneral,redevable,imputation,rue "
						+ " WHERE  emplacementgeneral.rapprochementIdoss='true'"
						+ " AND emplacementgeneral.numRedevable= redevable.numRedevable "
						+ " AND imputation.idImputation = emplacementgeneral.codetype "
						+ " AND emplacementgeneral.codeVoie= rue.codeVoie "
						+ " AND emplacementgeneral.numRue="
						+ numRue
						+ "  AND rue.codeRivolie = '"
						+ codeRivolie
						+ "'"
						+ " AND imputation.libelle='"
						+ typeTaxe
						+ "'"
						+ " AND nomRedevable LIKE '%"
						+ nomRedevable
						+ "%'"
						+ " AND emplacementgeneral.source='normal' ";

				debug.logger.debug(req1);
				ResultSet resultat = instruction.executeQuery(req1);

				while (resultat.next()) {
					empl.setNumEmplacement("" + resultat.getInt("numero"));
					empl.setInfosIdoss(resultat.getString("infosIdoss"));
				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}

		return empl;
	}

	public String getNombreAlerte(String idEmplacement) {
		String res = "";
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req1 = "  SELECT count(alerte.id_alerte) as nbre "
						+ " FRom alerte , article, elementfacturation , emplacementgeneral"
						+ " WHERE alerte.etatAlerte ='ENCOURS'"
						+ " AND alerte.id_article =article.id_article "
						+ " AND article.id_elementfacturation = elementfacturation.numero "
						+ " AND elementfacturation.numeroEmplacement = emplacementgeneral.numero "
						+ " AND `emplacementgeneral`.`numero`=" + idEmplacement;

				debug.logger.debug(req1);
				ResultSet resultat1 = instruction.executeQuery(req1);
				while (resultat1.next()) {
					res = resultat1.getString("nbre");
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
	
	
	
	
	
	public void supprimerDocumentEmplacementGeneral(String nomDocument, String idEmplacement) {
			try {
				Statement instruction = con.connexion.createStatement();
				String req = "delete from document_emplacement WHERE nomDocument='"+nomDocument +"'" ;
				debug.logger.debug(req);
				int result1 = instruction.executeUpdate(req);
				String cheminDocument = fichierDeConfiguration.getCheminData()+"/emplacements//"+idEmplacement +"//"+ nomDocument  ;
				debug.logger.debug(">>>>cheminDocument="+cheminDocument);
				deleteFile(cheminDocument);
				new RequestAdmin().InsertAction("Suppresions du document  " +nomDocument
							, GestionDate.getDateTime());
				
			} catch (Exception e) {
				debug.logger.debug(e.getMessage());
			}
	}

	
	
	public String peutEtreSupprimer(String numEmplacment) {
		String res = "ok";
		try 
		{
			
			Statement instruction = con.connexion.createStatement();
			try {
				String req = "SELECT f.numeroFacture " +
						" FROM facture f, article a, lignefacture lf, elementfacturation ef, emplacementgeneral eg " +
						  //" WHERE  f.`etat` = 'Valide' " +
						" WHERE lf.`idFacture` = f.`numeroFacture` " +
						" AND a.`id_article` = lf.`idArticle` " +
						" AND ef.`numero` = a.`id_elementfacturation` " +
						"AND eg.`numero` = ef.`numeroEmplacement`" +
						"AND eg.numero= "+ numEmplacment; 
				debug.logger.debug(req);
				ResultSet resultat1 = instruction.executeQuery(req);
				while (resultat1.next()) 
				{
					res = "ko";
					break ;
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

	
	
}
