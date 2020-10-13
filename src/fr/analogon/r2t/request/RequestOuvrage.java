package fr.analogon.r2t.request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Article;
import fr.analogon.r2t.pojo.Bareme;
import fr.analogon.r2t.pojo.Communique;
import fr.analogon.r2t.pojo.HistoriqueControle;
import fr.analogon.r2t.pojo.HistoriqueEtatOuvrage;

public class RequestOuvrage extends Request {
	RequestBareme requestBareme = new RequestBareme();
	DataFromBD dataFromBD = new DataFromBD();

	public Vector getEtatOuvrage() {
		Vector params = null;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1 = " SELECT valeur " + " FROM parametre "
						+ " WHERE type='etat_ouvrage'";
				debug.logger.debug(r1);
				ResultSet resultat1 = instruction.executeQuery(r1);
				params = new Vector();
				while (resultat1.next()) {
					params.addElement(resultat1.getString("valeur"));
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

	
	public Article getArticle(String idArticle) 
	{
		Article res = new Article();
		Statement instruction;
		
		//Verfier si l'artcile est facture  
		//Si l'article  est facture il n'est plus possible de le supprimer 
		Statement instruction1;
		boolean peutEtreSuuprimer = true;
		boolean peutEtreModifier = true;
		try {
			instruction1 = con.connexion.createStatement();
			try {
				String r = " SELECT distinct(idArticle) as utulise " +
						   " FROM  lignefacture  " +
						   " WHERE idArticle =" + idArticle ;					
				//debug.logger.debug("Verfier si l ouvrage numero "+idArticle+ "  peut etre supprimer \n"+ r);
				ResultSet resultat1 = instruction1.executeQuery(r);
				while (resultat1.next()) 
				{						
					peutEtreSuuprimer = false ;
				}
				res.setPeutEtreSuuprimer(""+peutEtreSuuprimer);
				//debug.logger.debug(r);
				instruction1.close();
			} 
			catch (Exception e1) 
			{
				debug.logger.fatal(e1.getMessage());
				e1.printStackTrace();
			}		
		}		
		catch (Exception e1) 
		{
			debug.logger.fatal(e1.getMessage());
			e1.printStackTrace();
		}
		
		try {
			instruction1 = con.connexion.createStatement();
			try {
				String r = " SELECT distinct(idArticle) as factureNonAnnulle     " +
						   " FROM  lignefacture , facture " +
						   " WHERE lignefacture.idArticle = '" + idArticle +"'" +
						   " AND lignefacture.idFacture =  facture.numeroFacture " +
						   " AND facture.etat != 'preFacturation'" +
						   " AND facture.etat != 'ANNULLEE'" ; 					
				ResultSet resultat1 = instruction1.executeQuery(r);
				while (resultat1.next()) 
				{						
					peutEtreModifier = false ;
				}
				res.setPeutEtreModifier(""+peutEtreModifier);				
				instruction1.close();
			} 
			catch (Exception e1) 
			{
				debug.logger.fatal(e1.getMessage());
				e1.printStackTrace();
			} 		
		}
		
		catch (Exception e1) 
		{
			debug.logger.fatal(e1.getMessage());
			e1.printStackTrace();
		}
	
		
		
		
		
		
		
		
		
		try {
			instruction = con.connexion.createStatement();
			try {
				String r = " SELECT DISTINCT(article.id_article),article.*,emplacementgeneral.codeSecteur,bareme.libelle,bareme.typeArrondi,"
						+ " emplacementgeneral.codeType,emplacementgeneral.numRue,emplacementgeneral.adresse1, imputation.libelle, "
						+ " imputation.idImputation , emplacementgeneral.numRedevable,emplacementgeneral.numero  "
						+ " From article,elementfacturation,emplacementgeneral,imputation, bareme "
						+ " WHERE id_article="
						+ idArticle
						+ " AND article.id_elementfacturation=elementfacturation.numero "
						+ " AND bareme.code = article.codebareme "
						+ " AND elementfacturation.numeroEmplacement=emplacementgeneral.numero  "
						+ " AND imputation.idImputation= emplacementgeneral.codetype ";
				// if (annee.length() !=0) r= r+" AND anexercice="+annee;
				debug.logger.debug(r);
				ResultSet resultat = instruction.executeQuery(r);
				while (resultat.next()) 
				{
					res.setCodeBareme(resultat.getString("codebareme"));
					res.setId("" + resultat.getInt("id_article"));
					res.setTypeArrondi(resultat.getString("typeArrondi"));
					res.setEtatRembourseArticle(resultat.getString("rembourse"));
					res.setSource(resultat.getString("source"));
					res.setIdElementFacturation(" "  + resultat.getInt("id_elementfacturation"));
					// res.setNumEmplacment(""+resultat.getInt("id_elementfacturation"));
					res.setCodeSecteur(resultat.getString("codeSecteur"));
					res.setNumRedevable(""+ resultat.getInt("emplacementgeneral.numRedevable"));
					res.setNumEmplacment(""	+ resultat.getInt("emplacementgeneral.numero"));
					res.setAdresseOuvrage(resultat.getString("emplacementgeneral.numRue")+ " "+ resultat.getString("emplacementgeneral.adresse1"));
					res.setTypeOuvrage(resultat.getString("typeOuvrage"));					
					res.setIdImputation(""+resultat.getInt("idImputation"));
					res.setCommentaireOuvrage(resultat.getString("commentaire"));
					res.setAnExercice("" + resultat.getInt("anexercice"));
					res.setQuantite_article(resultat.getDouble("quantite_article"));
					res.setLargeur(resultat.getDouble("largeur"));
					res.setLongueur(resultat.getDouble("longueur"));
					res.setCodeImputation(resultat.getString("codeType"));
					res.setSurface(resultat.getDouble("surface"));
					res.setLibelleImputation(resultat.getString("imputation.libelle"));
					res.setDateDebutAutorisation(resultat.getString("dateDebutAutorisation"));
					res.setDateFinAutorisation(resultat.getString("dateFinAutorisation"));
					res.setDateDernierControle(resultat.getString("dateDernierControl"));
					res.setDateProchainControl(resultat.getString("dateProchainControl"));
					res.setDernierePeriodeFacture(""+ resultat.getInt("dernierePeriodeFacture"));
					res.setNom(resultat.getString("nom"));
					res.setNombreFaceAffiche(""	+ resultat.getInt("nombreFaceAffiche"));
					res.setLibelleBareme(resultat.getString("bareme.libelle"));
					res.setEtatArticle(resultat.getString("etat"));
					res.setCodeBareme("" + resultat.getInt("codebareme"));
					res.setIdElementFacturation(""+ resultat.getInt("id_elementfacturation"));
					res.setCommentaireOuvrage(resultat.getString("commentaire"));
					res.setDateFinIdoss(resultat.getString("dateFinAutorisationIdoss"));
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
	
	
	
		
	

	public Vector getListeOuvrage(String idEmplacement, String anneeExercice) {
		Vector res = new Vector();
		// Article res= new Article();

		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String r = " SELECT article.*,bareme.prixUnit, bareme.prorata,bareme.periodicite, bareme.anExercice,"
						+ "  imputation.libelle,bareme.libelle,elementfacturation.numero as numEfacturation,"
						+ "  bareme.prixPeriode1,bareme.prixPeriode2,bareme.prixPeriode3,"
						+ "  bareme.prixPeriode4,bareme.prixPeriode5,bareme.dureeunitaire,"
						+ "  bareme.uniteDeTravail,bareme.typeArrondi, "
						+ " emplacementgeneral.numero as numEmplacement, "
						+ " emplacementgeneral.codeSecteur, emplacementgeneral.numRedevable,"
						+ " emplacementgeneral.codeType  "
						+ " FROM article,elementfacturation,bareme,emplacementgeneral,imputation"
						+ " WHERE article.id_elementfacturation= elementfacturation.numero"
						+ " AND article.source= 'normal'"
						+ " AND imputation.idImputation = bareme.idImputation "
						+ " AND imputation.anneeExercice="
						+ anneeExercice
						+ " AND article.codebareme= bareme.code"
						+ " AND `bareme`.`anExercice`="
						+ anneeExercice
						+ " AND elementfacturation.numeroEmplacement= emplacementgeneral.numero"
						+ " AND  emplacementgeneral.numero="
						+ idEmplacement
						+ " Order by article.etat";
				debug.logger.debug(r);
				ResultSet resultat = instruction.executeQuery(r);
				while (resultat.next()) {
					Article article = new Article();
					article.setCodeBareme(resultat.getString("codebareme"));
					article.setLibelleImputation(resultat
							.getString("imputation.libelle"));
					article.setDateDernierControle(resultat.getString("dateDernierControl"));
					article.setDateFinAutorisation(resultat.getString("DateFinAutorisation"));
					article.setId(resultat.getString("id_article"));
					article.setUniteTravail(resultat.getString("uniteDeTravail"));
					article.setBareme(resultat.getString("bareme.libelle"));
					article.setNom(resultat.getString("nom"));
					article.setIdImputation(resultat.getString("codeType"));
					article.setDureeUnitaire(resultat.getString("dureeunitaire"));
					article.setLargeur(resultat.getDouble("largeur"));
					article.setLongueur(resultat.getDouble("longueur"));
					article.setTypeArrondi(resultat.getString("typeArrondi"));
					article.setSurface(resultat.getDouble("surface"));
					article.setQuantite_article(resultat
							.getDouble("quantite_article"));
					article.setIdElementFacturation(String.valueOf(resultat
							.getInt("numEfacturation")));
					article.setNumEmplacment(String.valueOf(resultat
							.getInt("numEmplacement")));
					article.setCodeSecteur(String.valueOf(resultat
							.getInt("codeSecteur")));
					article.setNumRedevable(String.valueOf(resultat
							.getInt("numRedevable")));
					article.setDateDebutAutorisation(resultat
							.getString("DateDebutAutorisation"));
					article.setPrixPeriode1(resultat.getDouble("prixPeriode1"));
					article.setPrixPeriode2(resultat.getDouble("prixPeriode2"));
					article.setPrixPeriode3(resultat.getDouble("prixPeriode3"));
					article.setPrixPeriode4(resultat.getDouble("prixPeriode4"));
					article.setPrixPeriode5(resultat.getDouble("prixPeriode5"));
					article.setEtatArticle(resultat.getString("etat"));
					article.setAnExercice(resultat
							.getString("bareme.anExercice"));
					article.setUtiliserPeriodicite(resultat
							.getString("periodicite"));
					article.setUtiliserProrata(resultat.getString("prorata"));
					article.setTypeOuvrage(resultat.getString("typeOuvrage"));
					article.setNombreFaceAffiche(resultat
							.getString("nombreFaceAffiche"));
					article.setPrixUnitaire(String.valueOf(resultat
							.getDouble("prixUnit")));
					res.addElement(article);
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

	public Vector getListeCommunique(String dateDujour, String nomQuartier,
			String numSecteur) {
		Vector res = new Vector();
		// Article res= new Article();
		nomQuartier = FonctionCommun.ajouterAntislash(nomQuartier);
		String anneeExercicie = GestionDate.getAnneeCourante();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String r = "SELECT CONCAT('C') as type, elementfacturation.numero,emplacementgeneral.codeSecteur, "
						+ "  emplacementgeneral.numRedevable, rue.nomrue, emplacementgeneral.numRue, article.*,rue.nomQuartier,"
						+ " emplacementgeneral.numero as numeroEmplacement ,"
						+ " CONCAT(emplacementgeneral.numRue ,\" \",  emplacementgeneral.adresse1 ,\" \","
						+ " emplacementgeneral.adresse2 ,\" \",  emplacementgeneral.adresse3 ) as adresseempalcment "
						+ " FROM article,emplacementgeneral,elementfacturation,rue,bareme  "
						+ " WHERE article.etat='FacturerAControler'  AND emplacementgeneral.codeVoie= rue.codeVoie"
						+ " AND emplacementgeneral.enactivite='enActivite'"
						+ " AND bareme.code=article.codebareme"
						+ " AND bareme.anExercice='"+anneeExercicie+"'"
						+ " AND article.id_elementfacturation=elementfacturation.numero  ";
				if (numSecteur.length() != 0)
					r = r + " AND emplacementgeneral.codeSecteur=  "
							+ numSecteur;
				r = r
						+ " AND elementfacturation.numeroEmplacement=emplacementgeneral.numero"
						+ " AND rue.nomQuartier like '%"
						+ nomQuartier
						+ "%'"
						+ " AND STR_TO_DATE(article.dateProchainControl, '%d/%m/%Y') <= STR_TO_DATE('"
						+ dateDujour
						+ "', '%d/%m/%Y')"
						+

						" UNION"
						+

						" SELECT CONCAT('R') as type, elementfacturation.numero,emplacementgeneral.codeSecteur, "
						+ "  emplacementgeneral.numRedevable, rue.nomrue, emplacementgeneral.numRue, article.*,rue.nomQuartier,"
						+ " emplacementgeneral.numero,"
						+ " CONCAT(emplacementgeneral.numRue ,\" \",  emplacementgeneral.adresse1 ,\" \","
						+ " emplacementgeneral.adresse2 ,\" \",  emplacementgeneral.adresse3 ) as adresseempalcment "
						+ " FROM article,emplacementgeneral,elementfacturation,rue,reclamation,reclamation_article "
						+ " WHERE article.id_elementfacturation=elementfacturation.numero   "
						+ " AND elementfacturation.numeroEmplacement=emplacementgeneral.numero"
						+ " AND emplacementgeneral.codeVoie= rue.codeVoie "
						+ " AND article.id_article = reclamation_article.idArticle"
						+ " AND reclamation.idReclamation = reclamation_article.idReclamation"
						+ " AND reclamation.etat ='ENCOURS' "
						+ " AND rue.nomQuartier like '%" + nomQuartier + "%'";
				if (numSecteur.length() != 0)
					r = r + " AND emplacementgeneral.codeSecteur=  "
							+ numSecteur;
				r = r
						+ " ORDER BY nomQuartier,nomrue , CAST(numRue AS DECIMAL(8,2))  ";
				debug.logger.debug(r);
				ResultSet resultat = instruction.executeQuery(r);
				int i = 0;
				while (resultat.next()) {
					Communique comm = new Communique();
					comm.setIdOuvrage(resultat.getString("id_article"));
					comm.setIdRedevable(resultat.getString("numRedevable"));
					comm.setIdEmplacement(""
							+ resultat.getInt("numeroEmplacement"));
					String dateProchainControle = resultat
							.getString("dateProchainControl");
					if (dateProchainControle.length() == 0)
						dateProchainControle = "-";
					comm.setDateDeControle(dateProchainControle);
					comm.setAdresseEmplacement(resultat
							.getString("adresseempalcment"));
					comm.setQuartier(resultat.getString("nomQuartier"));
					comm.setType(resultat.getString("type"));
					comm.setCodeSecteur(resultat.getString("codeSecteur"));
					comm.setNumeroEF(resultat.getString("numero"));
					res.addElement(comm);
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

	public Vector getListeEtatOuvrage(String idOuvrage) {
		Vector res = new Vector();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String r = "SELECT * FROM historiqueetatouvrage "
						+ " WHERE idOuvrage= " + idOuvrage
						+ " ORDER BY idHistoriqueEtatOuvrage DESC";

				debug.logger.debug(r);
				ResultSet resultat = instruction.executeQuery(r);
				int i = 0;
				while (resultat.next()) {
					HistoriqueEtatOuvrage historiqueEtatOuvrage = new HistoriqueEtatOuvrage();
					historiqueEtatOuvrage.setDateModification(resultat
							.getString("dateModification"));
					historiqueEtatOuvrage.setEtatOuvrage(resultat
							.getString("etatOuvrage"));
					historiqueEtatOuvrage.setIdHistoriqueEtatOuvrage(resultat
							.getString("idHistoriqueEtatOuvrage"));
					historiqueEtatOuvrage.setIdOuvrage(resultat
							.getString("idOuvrage"));
					historiqueEtatOuvrage.setTypeModifcation(resultat
							.getString("typeModifcation"));
					res.addElement(historiqueEtatOuvrage);
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

	public Vector getListeRuptureControle(String nomQuartier) {
		Vector res = new Vector();
		nomQuartier = FonctionCommun.ajouterAntislash(nomQuartier);
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String r = " SELECT DISTINCT(article.id_article), article.dateDernierControl,"
						+ " DateDebutAutorisation,dateProchainControl,dernierePeriodeFacture,codebareme, "
						+ " rue.nomQuartier,nomrue, numRue "
						+ " FROM article,emplacementgeneral,elementfacturation,rue, bareme,imputation  "
						+ " WHERE article.codebareme = bareme.code "
						+ " AND article.id_elementfacturation=elementfacturation.numero  "
						+ " AND elementfacturation.numeroEmplacement=emplacementgeneral.numero"
						+ " AND emplacementgeneral.codeVoie= rue.codeVoie "
						+ " AND rue.nomQuartier like '%"
						+ nomQuartier
						+ "%'"
						+ " AND bareme.idImputation = imputation.idImputation "
						+ " AND imputation.libelle='DDV' "
						+ " AND  (article.etat='FacturerAControler' OR article.etat='ControlerAFacturer') "
						+ " ORDER BY rue.nomQuartier,nomrue , CAST(numRue AS DECIMAL(8,2))";
				debug.logger.debug(r);
				ResultSet resultat = instruction.executeQuery(r);

				while (resultat.next()) {
					Article article = getArticle(""
							+ resultat.getInt("id_article"));

					String dernierePeriodeFacture = article
							.getDernierePeriodeFacture();
					String codeBareme = article.getCodeBareme();
					String dateDebut = article.getDateDebutAutorisation();
					String dateFin = GestionDate.getDateAujourdhuiString();
					String anneeExercicie = GestionDate.getAnneeCourante();
					String numeroDePeriode = String.valueOf(dataFromBD
							.getNumeroPeriode(codeBareme, anneeExercicie,
									dateDebut, dateFin));
					article.setNumeroDePeriodeActuelle(numeroDePeriode);
					article.setDernierePeriodeFacture(dernierePeriodeFacture);
					if (Integer.parseInt(numeroDePeriode)
							- Integer.parseInt(dernierePeriodeFacture) > 1)
						res.addElement(article);

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

	public Vector getListeControle(String nomQuartier, String dateControle,
			String utuilsateur) {
		Vector res = new Vector();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String r = " SELECT historiquecontrole.* ,rue.nomQuartier, "
						+ " CONCAT(utilisateur.nom,' ',utilisateur.prenom)  as nomControleur "
						+ " FROM article,emplacementgeneral,elementfacturation,rue,historiquecontrole,utilisateur"
						+ " WHERE utilisateur.numeroUser = historiquecontrole.idControleur  "
						+ " AND emplacementgeneral.codeVoie= rue.codeVoie"
						+ " AND article.id_elementfacturation=elementfacturation.numero  "
						+ " AND elementfacturation.numeroEmplacement=emplacementgeneral.numero"
						+ " AND article.id_article =historiquecontrole.idOuvrage "
						+ " AND rue.nomQuartier like '%" + nomQuartier + "%'"
						+ " AND historiquecontrole.dateControle like '%"
						+ dateControle + "%'";
				if (utuilsateur.length() != 0)
					r = r + " AND historiquecontrole.idControleur ="
							+ utuilsateur;

				r = r
						+ " ORDER by historiquecontrole.idHistoriqueControle DESC ";

				debug.logger.debug(r);
				ResultSet resultat = instruction.executeQuery(r);
				while (resultat.next()) {
					HistoriqueControle historiqueControle = new HistoriqueControle();
					historiqueControle.setDateControle(resultat
							.getString("dateControle"));
					historiqueControle.setEtatOuvrage(resultat
							.getString("etatOuvrage"));
					historiqueControle.setIdControleur(resultat
							.getString("idControleur"));
					historiqueControle.setIdOuvrage(resultat
							.getString("idOuvrage"));
					historiqueControle.setNomQuartier(resultat
							.getString("nomQuartier"));
					historiqueControle.setNomControleur(resultat
							.getString("nomControleur"));

					res.addElement(historiqueControle);
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

	// Ajout d'un ouvrage pour une refacturation
	public void ajouterOuvrage(String typeOuvrage, String nombreFaceAffiche,
			String id_elementfacturation, String codebareme, String longueur,
			String largeur, String quantite_article,
			String DateDebutAutorisation, String DateFinAutorisation,
			String surface, String anexercice, String source, String numeroTmp,
			String dernierePeriodeFacture, String timeTranasaction, String nomOUvrage) 
	{
		nomOUvrage = FonctionCommun.ajouterAntislash(nomOUvrage);
		String requete = " INSERT INTO article( typeOuvrage, nombreFaceAffiche ,id_elementfacturation,codebareme,"
				+ " longueur,largeur,quantite_article,DateDebutAutorisation,DateFinAutorisation,"
				+ " surface,anexercice, source,numeroTmp,dernierePeriodeFacture,idTransaction,nom) "
				+ " VALUES('"
				+ typeOuvrage
				+ "','"
				+ nombreFaceAffiche
				+ "','"
				+ id_elementfacturation
				+ "','"
				+ codebareme
				+ "','"
				+ longueur
				+ "','"
				+ largeur
				+ "','"
				+ quantite_article
				+ "','"
				+ DateDebutAutorisation
				+ "','"
				+ DateFinAutorisation
				+ "','"
				+ surface
				+ "','"
				+ anexercice
				+ "','"
				+ source
				+ "','"
				+ numeroTmp
				+ "',"
				+ (Integer.parseInt(dernierePeriodeFacture) - 1)
				+ ",'"
				+ timeTranasaction + "','"+nomOUvrage+"')";

		debug.logger.debug(requete);
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				int result1 = instruction.executeUpdate(requete);
			} catch (SQLException e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (SQLException e) {
			debug.logger.fatal(e.getMessage());
		}

	}

	// Ajout d'un ouvrage par application (Ajout normale)
	public void ajouterOuvrage(String id_elementfacturation,
			String libelleOuvrage, String codebareme, String longueur,
			String largeur, String quantite_article,
			String DateDebutAutorisation, String DateFinAutorisation,
			String surface, String anexercice, String source,
			int dernierePeriodeFacture, String commentaireOuvrage,
			String typeOuvrage, String nombreFaceAffiche) {
		// System.out.println(">>>>AJOUT D'OUVRAGE");
		commentaireOuvrage = FonctionCommun
				.ajouterAntislash(commentaireOuvrage);
		libelleOuvrage = FonctionCommun.ajouterAntislash(libelleOuvrage);

		String requete = " INSERT INTO article( id_elementfacturation,nom,codebareme,"
				+ " longueur,largeur,quantite_article,DateDebutAutorisation,DateFinAutorisation,"
				+ " surface,anexercice, source,dernierePeriodeFacture,commentaire,typeOuvrage,nombreFaceAffiche) "
				+ " VALUES('"
				+ id_elementfacturation
				+ "','"
				+ libelleOuvrage
				+ "','"
				+ codebareme
				+ "','"
				+ longueur
				+ "','"
				+ largeur
				+ "','"
				+ quantite_article
				+ "','"
				+ DateDebutAutorisation
				+ "','"
				+ DateFinAutorisation
				+ "','"
				+ surface
				+ "','"
				+ anexercice
				+ "','"
				+ source
				+ "','"
				+ dernierePeriodeFacture
				+ "','"
				+ commentaireOuvrage
				+ "','"
				+ typeOuvrage
				+ "','"
				+ nombreFaceAffiche + "')";

		debug.logger.info("Ajout d'un ouvrage ");
		debug.logger.debug(requete);
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				int result1 = instruction.executeUpdate(requete);

				requete = "SELECT MAX(id_article) as maxIdOuvrage FROM article ";
				debug.logger.debug(requete);
				ResultSet resultat = instruction.executeQuery(requete);
				String idOuvrage = "0";
				while (resultat.next()) {
					idOuvrage = resultat.getString("maxIdOuvrage");
				}

				// Ecriture de trace
				String message = "Création de l'ouvrage";
				message = FonctionCommun.ajouterAntislash(message);
				requete = " INSERT INTO historiqueetatouvrage(etatOuvrage,  dateModification, "
						+ " typeModifcation,type, idOuvrage)"
						+ " VALUES('A Facturer',"
						+ "'"
						+ GestionDate.getDateAujourdhuiString()
						+ "',"
						+ "'"
						+ message + "'," + "'creation'," + idOuvrage + ") ";
				debug.logger.debug(requete);
				instruction.executeUpdate(requete);
			} catch (SQLException e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (SQLException e) {
			debug.logger.debug(e.getMessage());
		}
	}

	public void modifierOuvrage(String idOuvrage, String codeBareme,
			String nomArticle, String longueur, String largeur, String surface,
			String quantiteArticle, String commentaireOuvrage,
			String dateDebutAutorisation, String dateFinAutorisation,
			String dateDernierControle, String dateProchainControle,
			String dernierePeriodeFacture, String etatArticle,
			String anExercice, String typeOuvrage, String nombreFaceAffiche) {
		commentaireOuvrage = FonctionCommun
				.ajouterAntislash(commentaireOuvrage);
		nomArticle = FonctionCommun.ajouterAntislash(nomArticle);

		// Mettre a jour la table ouvrage et table elemenet de facturtion
		// MAJ article :
		String requete = " UPDATE article set " + " dernierePeriodeFacture='"
				+ dernierePeriodeFacture + "'" + " ,nom='" + nomArticle + "'"
				+ " ,codebareme='" + codeBareme + "'"
				+ " ,DateDebutAutorisation='" + dateDebutAutorisation + "'"
				+ " ,DateFinAutorisation='" + dateFinAutorisation + "'"
				+ " ,DateDernierControl='" + dateDernierControle + "'"
				+ " ,DateProchainControl='" + dateProchainControle + "'"
				+ " ,quantite_article='" + quantiteArticle + "'"
				+ " ,longueur='" + longueur + "'" + " ,largeur='" + largeur
				+ "'" + " ,etat='" + etatArticle + "'" + " ,commentaire='"
				+ commentaireOuvrage + "'" + " ,surface='" + surface + "'"
				+ " ,typeOuvrage='" + typeOuvrage + "'"
				+ " ,nombreFaceAffiche='" + nombreFaceAffiche + "'"
				+ " WHERE id_article=" + idOuvrage;

		debug.logger.info("Modification de l'ouvrage " + idOuvrage);
		debug.logger.debug(requete);
		// MAJ EF :
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				Article artcile = getArticle(idOuvrage);
				int result1 = instruction.executeUpdate(requete);

				if (!artcile.getEtatArticle().equalsIgnoreCase(etatArticle)) {
					// Ecriture de trace
					String message = "Modification manuelle de l'état de l'ouvrage ";
					message = FonctionCommun.ajouterAntislash(message);
					etatArticle = FonctionCommun.getEtat(etatArticle);
					requete = " INSERT INTO historiqueetatouvrage(etatOuvrage,  dateModification, "
							+ " typeModifcation,idOuvrage)"
							+ " VALUES('"
							+ etatArticle
							+ "',"
							+ "'"
							+ GestionDate.getDateAujourdhuiString()
							+ "',"
							+ "'" + message + "'," + idOuvrage + ") ";
					debug.logger.debug(requete);
					instruction.executeUpdate(requete);
				}
				if (artcile.getQuantite_article() != Double
						.valueOf(quantiteArticle)) {
					// Ecriture de trace
					String message = "Modification de la quantite de "
							+ artcile.getQuantite_article() + " à "
							+ quantiteArticle;
					message = FonctionCommun.ajouterAntislash(message);
					etatArticle = FonctionCommun.getEtat(etatArticle);
					requete = " INSERT INTO historiqueetatouvrage(etatOuvrage,  dateModification, "
							+ " typeModifcation,idOuvrage)"
							+ " VALUES('"
							+ etatArticle
							+ "',"
							+ "'"
							+ GestionDate.getDateAujourdhuiString()
							+ "',"
							+ "'" + message + "'," + idOuvrage + ") ";
					debug.logger.debug(requete);
					instruction.executeUpdate(requete);
				}
				if (!artcile.getDateDebutAutorisation().equalsIgnoreCase(
						dateDebutAutorisation)) {
					// Ecriture de trace
					String message = "Modification de la date de début d'autorisation de "
							+ artcile.getDateDebutAutorisation()
							+ " à "
							+ dateDebutAutorisation;
					message = FonctionCommun.ajouterAntislash(message);
					etatArticle = FonctionCommun.getEtat(etatArticle);
					requete = " INSERT INTO historiqueetatouvrage(etatOuvrage,  dateModification, "
							+ " typeModifcation,idOuvrage)"
							+ " VALUES('"
							+ etatArticle
							+ "',"
							+ "'"
							+ GestionDate.getDateAujourdhuiString()
							+ "',"
							+ "'" + message + "'," + idOuvrage + ") ";
					debug.logger.debug(requete);
					instruction.executeUpdate(requete);
				}
				if (!artcile.getDateDernierControle().equalsIgnoreCase(
						dateDernierControle)) {
					// Ecriture de trace
					String message = "Modification de la date du dernier contrôle de "
							+ artcile.getDateDernierControle()
							+ " à "
							+ dateDernierControle;
					message = FonctionCommun.ajouterAntislash(message);
					etatArticle = FonctionCommun.getEtat(etatArticle);
					requete = " INSERT INTO historiqueetatouvrage(etatOuvrage,  dateModification, "
							+ " typeModifcation,idOuvrage)"
							+ " VALUES('"
							+ etatArticle
							+ "',"
							+ "'"
							+ GestionDate.getDateAujourdhuiString()
							+ "',"
							+ "'" + message + "'," + idOuvrage + ") ";
					debug.logger.debug(requete);
					instruction.executeUpdate(requete);
				}
				if (!artcile.getDateFinAutorisation().equalsIgnoreCase(
						dateFinAutorisation)) {
					// Ecriture de trace
					String message = "Modification de la date de fin autorisation de "
							+ artcile.getDateFinAutorisation()
							+ " à "
							+ dateFinAutorisation;
					message = FonctionCommun.ajouterAntislash(message);
					etatArticle = FonctionCommun.getEtat(etatArticle);
					requete = " INSERT INTO historiqueetatouvrage(etatOuvrage,  dateModification, "
							+ " typeModifcation,idOuvrage)"
							+ " VALUES('"
							+ etatArticle
							+ "',"
							+ "'"
							+ GestionDate.getDateAujourdhuiString()
							+ "',"
							+ "'" + message + "'," + idOuvrage + ") ";
					debug.logger.debug(requete);
					instruction.executeUpdate(requete);
				}
				if (!artcile.getDateProchainControl().equalsIgnoreCase(
						dateProchainControle)) {
					// Ecriture de trace
					String message = "Modification de la date du prochain contrôle de "
							+ artcile.getDateProchainControl()
							+ " à "
							+ dateProchainControle;
					message = FonctionCommun.ajouterAntislash(message);
					etatArticle = FonctionCommun.getEtat(etatArticle);
					requete = " INSERT INTO historiqueetatouvrage(etatOuvrage,  dateModification, "
							+ " typeModifcation,idOuvrage)"
							+ " VALUES('"
							+ etatArticle
							+ "',"
							+ "'"
							+ GestionDate.getDateAujourdhuiString()
							+ "',"
							+ "'" + message + "'," + idOuvrage + ") ";
					debug.logger.debug(requete);
					instruction.executeUpdate(requete);
				}
				if (!artcile.getDernierePeriodeFacture().equalsIgnoreCase(
						dernierePeriodeFacture)) {
					// Ecriture de trace
					String message = "Modification du N° de la dernière période facturée "
							+ artcile.getDernierePeriodeFacture()
							+ " à "
							+ dernierePeriodeFacture;
					message = FonctionCommun.ajouterAntislash(message);
					etatArticle = FonctionCommun.getEtat(etatArticle);
					requete = " INSERT INTO historiqueetatouvrage(etatOuvrage,  dateModification, "
							+ " typeModifcation,idOuvrage)"
							+ " VALUES('"
							+ etatArticle
							+ "',"
							+ "'"
							+ GestionDate.getDateAujourdhuiString()
							+ "',"
							+ "'" + message + "'," + idOuvrage + ") ";
					debug.logger.debug(requete);
					instruction.executeUpdate(requete);
				}
				if (artcile.getCommentaireOuvrage() != null
						&& !artcile.getCommentaireOuvrage().equalsIgnoreCase(
								commentaireOuvrage)) {
					// Ecriture de trace
					String message = "Modification du commentaire de  "
							+ artcile.getCommentaireOuvrage() + " à "
							+ commentaireOuvrage;
					message = FonctionCommun.ajouterAntislash(message);
					etatArticle = FonctionCommun.getEtat(etatArticle);
					requete = " INSERT INTO historiqueetatouvrage(etatOuvrage,  dateModification, "
							+ " typeModifcation,idOuvrage)"
							+ " VALUES('"
							+ etatArticle
							+ "',"
							+ "'"
							+ GestionDate.getDateAujourdhuiString()
							+ "',"
							+ "'" + message + "'," + idOuvrage + ") ";
					debug.logger.debug(requete);
					instruction.executeUpdate(requete);
				}
				if (!artcile.getNom().equalsIgnoreCase(nomArticle)) {
					// Ecriture de trace
					String oldName = artcile.getNom();
					if (nomArticle.startsWith("#"))
						nomArticle = "Non précisé";

					if (oldName.startsWith("#"))
						oldName = "Non précisé";
					String message = "Modification du nom de l'ouvrage de  "
							+ oldName

							+ " à " + nomArticle;
					message = FonctionCommun.ajouterAntislash(message);
					etatArticle = FonctionCommun.getEtat(etatArticle);
					requete = " INSERT INTO historiqueetatouvrage(etatOuvrage,  dateModification, "
							+ " typeModifcation,idOuvrage)"
							+ " VALUES('"
							+ etatArticle
							+ "',"
							+ "'"
							+ GestionDate.getDateAujourdhuiString()
							+ "',"
							+ "'" + message + "'," + idOuvrage + ") ";
					debug.logger.debug(requete);
					instruction.executeUpdate(requete);
				}
				if (!artcile.getCodeBareme().equalsIgnoreCase(codeBareme)) {
					// Ecriture de trace
					Bareme oldBareme = requestBareme.getBareme(artcile
							.getCodeBareme(), GestionDate.getAnneeCourante());
					Bareme newBareme = requestBareme.getBareme(codeBareme,
							GestionDate.getAnneeCourante());
					String message = "Modification du brème de "
							+ oldBareme.getLibelle() + " à "
							+ newBareme.getLibelle();
					message = FonctionCommun.ajouterAntislash(message);
					etatArticle = FonctionCommun.getEtat(etatArticle);
					requete = " INSERT INTO historiqueetatouvrage(etatOuvrage,  dateModification, "
							+ " typeModifcation,idOuvrage)"
							+ " VALUES('"
							+ etatArticle
							+ "',"
							+ "'"
							+ GestionDate.getDateAujourdhuiString()
							+ "',"
							+ "'" + message + "'," + idOuvrage + ") ";
					debug.logger.debug(requete);
					instruction.executeUpdate(requete);
				}

				if (artcile.getLargeur() != Double.valueOf(largeur)
						|| artcile.getLongueur() != Double.valueOf(longueur)
						|| artcile.getSurface() != Double.valueOf(surface)) {
					// Ecriture de trace
					String message = "Modification de dimension de l'ouvrage ";
					message = FonctionCommun.ajouterAntislash(message);
					etatArticle = FonctionCommun.getEtat(etatArticle);
					requete = " INSERT INTO historiqueetatouvrage(etatOuvrage,  dateModification, "
							+ " typeModifcation,idOuvrage)"
							+ " VALUES('"
							+ etatArticle
							+ "',"
							+ "'"
							+ GestionDate.getDateAujourdhuiString()
							+ "',"
							+ "'" + message + "'," + idOuvrage + ") ";
					debug.logger.debug(requete);
					instruction.executeUpdate(requete);
				}
			} catch (SQLException e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (SQLException e) {
			debug.logger.debug(e.getMessage());
		}
	}

	public void supprimerArticle(String idArticle) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				RequestOuvrage req = new RequestOuvrage();
				Article article = req.getArticle(idArticle);
				String r = "DELETE from article WHERE id_article=" + idArticle;
				debug.logger.debug(r);
				int result1 = instruction.executeUpdate(r);
				r = "DELETE from elementfacturation WHERE numero="
						+ article.getIdElementFacturation();
				debug.logger.debug(r);
				result1 = instruction.executeUpdate(r);
				String message = "Suppression de l'ouvrage ";
				message = FonctionCommun.ajouterAntislash(message);
				new RequestAdmin().InsertAction(message + idArticle,
						GestionDate.getDateTime());
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.debug(e.getMessage());
		}
	}

	public void arrterFacturationOuvrae(String idOuvrage) 
	{
		// MAJ article :
		String etatArticle = "NePlusFacturer";
		String requete = " UPDATE article set etat='" + etatArticle + "'"
				+ "  WHERE id_article=" + idOuvrage;

		debug.logger.info("Modification de l'ouvrage " + idOuvrage);
		debug.logger.debug(requete);
		// MAJ EF :
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String message = "Modification de l'etat de l'ouvrage";
				message = FonctionCommun.ajouterAntislash(message);
				int result1 = instruction.executeUpdate(requete);
				requete = " INSERT INTO historiqueetatouvrage(etatOuvrage,  dateModification, "
						+ " typeModifcation,idOuvrage)"
						+ " VALUES('"
						+ etatArticle
						+ "',"
						+ "'"
						+ GestionDate.getDateAujourdhuiString()
						+ "',"
						+ "'"
						+ message + "'," + idOuvrage + ") ";
				debug.logger.debug(requete);
				instruction.executeUpdate(requete);
			} catch (SQLException e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (SQLException e) {
			debug.logger.debug(e.getMessage());
		}
	}
}
