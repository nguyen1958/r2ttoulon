package fr.analogon.r2t.request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Article;
import fr.analogon.r2t.pojo.Autorisation;
import fr.analogon.r2t.pojo.Emplacement;


public class RequestAutorisation extends Request {

	// Ajout d'une autorisation
	public String ajouterAutorisation(String numeroTmp,
			String numRedevable, String etat, String refDossier,
			String dateCreation, String createur) {

		String res = "";
		String requete = " INSERT INTO autorisation( numeroTmp, "
				+ " numRedevable,  etat, refDossier, dateCreation, createur) "
				+ " VALUES('" + numeroTmp + "','"+numRedevable+"','" + etat
				+ "','" + refDossier + "','" + dateCreation+ "','" + createur +"')";

		debug.logger.debug(requete);
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				int result1 = instruction.executeUpdate(requete);
				instruction = con.connexion.createStatement();
				String req1 = " SELECT * FROM autorisation WHERE numeroTmp =" + numeroTmp;
				debug.logger.debug(req1);
				ResultSet resultat = instruction.executeQuery(req1);
				while (resultat.next()) {
					res = String.valueOf(resultat.getInt("idAutorisation"));
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

	// Ajout d'une autorisation-emplacement
	public String ajouterAutorisationEmplacement(String idAutorisation, String idEmplacement) 
	{
		String res = "";
		String requete = " INSERT INTO autorisationEmplacement( idAutorisation, idEmplacement) "
				+ " VALUES(" + idAutorisation + ","+idEmplacement+")";

		debug.logger.debug(requete);
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				int result1 = instruction.executeUpdate(requete);
				
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
	
	public Vector getListeAutorisation(String numAutorsation){
		Vector res = new Vector();
		String requete = "SELECT autorisation.*,emplacementgeneral.* from autorisation,autorisationemplacement,emplacementgeneral "
					   + "where autorisation.idautorisation=autorisationemplacement.idautorisation and "
			           + "autorisationemplacement.idemplacement=emplacementgeneral.numero and "	
					   + "autorisation.idautorisation="+numAutorsation
					   + " group by autorisation.idautorisation";
		// Execution de la requete
		debug.logger.debug("requete de recherche de l'autorisation : " + requete);
		try {
			Statement instruction = con.connexion.createStatement();
			ResultSet resultat = instruction.executeQuery(requete);
			while (resultat.next()) {
				Autorisation autorisation = new Autorisation();
				autorisation.setIdAutorisation(resultat.getString("idautorisation"));
				autorisation.setDateCreation(resultat.getString("dateCreation"));
				autorisation.setCreateur(resultat.getString("createur"));
				autorisation.setEtat(resultat.getString("etat"));
				autorisation.setNumRedevable(resultat.getString("numRedevable"));
				autorisation.setRefDossier(resultat.getString("refDossier"));
				autorisation.setAdresseEmplacement(resultat.getString("numRue")+" "+resultat.getString("adresse1")+" "+resultat.getString("adresse2")+" "+resultat.getString("adresse3"));
				autorisation.setCp(resultat.getString("codePostal"));
				autorisation.setVille(resultat.getString("ville"));
				autorisation.setNombreEmplacement(getNombreEmplacement(autorisation.getIdAutorisation()));
				res.addElement(autorisation);
			}
		
		}
		catch (Exception e) {
			debug.logger.fatal("Erreur " + e.getMessage() + e.getCause()
					+ e.getMessage());
			e.printStackTrace();
		}
		
		return res;
		
	}
	
	public Vector getListeAutorisation(String refDossier, 
									   String dateCreation, 
									   String nomRedevable,
									   String adresseEmplacement,
									   String typeDeTaxe, 
									   String etatAutorisation)
	{
		nomRedevable=FonctionCommun.ajouterAntislash(nomRedevable);
		adresseEmplacement=FonctionCommun.ajouterAntislash(adresseEmplacement);
		Vector res = new Vector();
		String requete = "SELECT autorisation.*,emplacementgeneral.* from autorisation, autorisationemplacement, redevable, emplacementgeneral, imputation "
				       + "where autorisation.numRedevable=redevable.numRedevable and "
				       + "autorisation.idautorisation=autorisationemplacement.idautorisation and "
				       + "autorisationemplacement.idemplacement=emplacementgeneral.numero and "
				       + "emplacementgeneral.codeType = imputation.idImputation";
		
		if (refDossier!=null && !refDossier.isEmpty()){
			requete +=" AND autorisation.refDossier ='"+refDossier+"'";					
		}	
		if (etatAutorisation!=null && !etatAutorisation.isEmpty()){
			requete +=" AND autorisation.etat ='"+etatAutorisation+"'";					
		}
		if (dateCreation!=null && !dateCreation.isEmpty()){
			requete +=" AND autorisation.datecreation ='"+dateCreation+"'";					
		}
		if (nomRedevable!=null && !nomRedevable.isEmpty()){
			requete +=" AND Concat(redevable.nomRedevable,' ',redevable.prenom) LIKE '%"+nomRedevable+"%'";			
		}
		if (adresseEmplacement!=null && !adresseEmplacement.isEmpty()){
			requete +=" AND Concat(emplacementgeneral.numRue,' ',emplacementgeneral.adresse1) LIKE '%"+adresseEmplacement+"%'";							
		}
		if (typeDeTaxe!=null && !typeDeTaxe.isEmpty()){
			requete +=" AND imputation.libelle ='"+typeDeTaxe+"'";					
		}
		if (etatAutorisation!=null && !etatAutorisation.isEmpty()){
			requete +=" AND autorisation.etat ='"+etatAutorisation+"'";					
		}
		requete+=" group by autorisation.idautorisation";
		

		// Execution de la requete
		debug.logger.debug("requete de recherche de l'autorisation : " + requete);
		
		try {
			Statement instruction = con.connexion.createStatement();
			ResultSet resultat = instruction.executeQuery(requete);
			while (resultat.next()) {
				Autorisation autorisation = new Autorisation();
				autorisation.setIdAutorisation(resultat.getString("idautorisation"));
				autorisation.setDateCreation(resultat.getString("dateCreation"));
				autorisation.setCreateur(resultat.getString("createur"));
				autorisation.setEtat(resultat.getString("etat"));
				autorisation.setNumRedevable(resultat.getString("numRedevable"));
				autorisation.setRefDossier(resultat.getString("refDossier"));
				autorisation.setAdresseEmplacement(resultat.getString("numRue")+" "+resultat.getString("adresse1")+" "+resultat.getString("adresse2")+" "+resultat.getString("adresse3"));
				autorisation.setCp(resultat.getString("codePostal"));
				autorisation.setVille(resultat.getString("ville"));
				autorisation.setNombreEmplacement(getNombreEmplacement(autorisation.getIdAutorisation()));
				res.addElement(autorisation);
			}
		
		}
		catch (Exception e) {
			debug.logger.fatal("Erreur " + e.getMessage() + e.getCause()
					+ e.getMessage());
			e.printStackTrace();
		}
		
		return res;
			
	}
	
	
	public String getNombreEmplacement(String idautorisation){
		
		String res = "";
		try {
			Statement instruction = con.connexion.createStatement();
			try {				
				String requete = "SELECT count(*) as nbEmplacement from autorisationEmplacement where idautorisation="+idautorisation;
				debug.logger.debug(requete);
				ResultSet resultat1 = instruction.executeQuery(requete);
				while (resultat1.next()) {
					res = resultat1.getString("nbEmplacement");
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
	
	public Autorisation getAutorisation(String numAutorsation){
		
		Autorisation autorisation = new Autorisation();
		
		String requete = "SELECT autorisation.* from autorisation where idautorisation="+numAutorsation;				   
		// Execution de la requete
		debug.logger.debug("requete de recherche de l'autorisation : " + requete);
		try {
			Statement instruction = con.connexion.createStatement();
			debug.logger.debug("instruction : " + instruction);
			ResultSet resultat = instruction.executeQuery(requete);
			while (resultat.next()) {
				autorisation.setIdAutorisation(resultat.getString("idautorisation"));
				autorisation.setDateCreation(resultat.getString("dateCreation"));
				autorisation.setCreateur(resultat.getString("createur"));
				autorisation.setEtat(resultat.getString("etat"));
				autorisation.setNumRedevable(resultat.getString("numRedevable"));
				autorisation.setRefDossier(resultat.getString("refDossier"));
				autorisation.setNombreEmplacement(getNombreEmplacement(autorisation.getIdAutorisation()));
			}
		
		}
		catch (Exception e) {
			debug.logger.fatal("Erreur " + e.getMessage() + e.getCause()
					+ e.getMessage());
			e.printStackTrace();
		}
		
		return autorisation;
		
	}
	
	public Emplacement getEmplacement(String idAutorisation){
		
		Emplacement emplacement = new Emplacement();
		
		String requete = "SELECT idautorisation,emplacementgeneral.* from autorisationemplacement, emplacementgeneral "
				       + "where idemplacement=emplacementgeneral.numero and idautorisation="+idAutorisation
				       + " group by idautorisation";
		// Execution de la requete
		debug.logger.debug("requete de recherche de l'emplacement : " + requete);
		try {
			Statement instruction = con.connexion.createStatement();
			ResultSet resultat = instruction.executeQuery(requete);
			while (resultat.next()) {
				emplacement.setNumEmplacement(resultat.getString("numero"));
				/*emplacement.setNomProprietaire(resultat
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
				*/
				emplacement.setRaisonSocial(resultat.getString("raisonSocial"));
				emplacement.setCodeProfeesion(String.valueOf(resultat.getInt("codePrefession")));
				emplacement.setNumRue(resultat.getString("numRue"));
				emplacement.setCodeVoie(resultat.getString("codeVoie"));
				emplacement.setCodeSecteur(resultat.getString("codeSecteur"));
				emplacement.setQuartier(resultat.getString("nomQuartier"));
				emplacement.setAdresse1(resultat.getString("adresse1"));
				emplacement.setAdresse2(resultat.getString("adresse2"));
				emplacement.setAdresse3(resultat.getString("adresse3"));
				emplacement.setCodePostal(resultat.getString("codePostal"));
				emplacement.setVille(resultat.getString("ville"));
				/*emplacement.setNumTel(resultat.getString("numTel"));
				emplacement.setNumPortable(resultat
						.getString("numPortable"));
				emplacement.setNumFax(resultat.getString("numFax"));
				emplacement.setEmail(resultat.getString("email"));
				emplacement.setCodeInscription(resultat
						.getString("codeInscription"));
				emplacement.setNumInscription(resultat
						.getString("numInscription"));*/
				emplacement.setNumRedevable(String.valueOf(resultat.getInt("numRedevable")));
				emplacement.setType(resultat.getString("codeType"));
				/*emplacement.setCedex(resultat.getString("cedex"));
				emplacement.setIdRedevableAutorise(String.valueOf(resultat.getInt("idRedevableAutorise")));*/
				emplacement.setEtatEmplacement(resultat.getString("enActivite"));
				emplacement.setNumeroEmplacementPersonalise(resultat.getString("numeroEmplacementPersonalise"));
				emplacement.setAdresseComplete(resultat.getString("numRue")
						+ " " + resultat.getString("adresse1") + " "
						+ resultat.getString("adresse2") + " "
						+ resultat.getString("adresse3") + " ");

			}
		
		}
		catch (Exception e) {
			debug.logger.fatal("Erreur " + e.getMessage() + e.getCause()
					+ e.getMessage());
			e.printStackTrace();
		}
		
		return emplacement;
		
	}
	
	public Vector getListeOuvrageAutorise(String idAutorisation)
	{
		Vector res = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = "SELECT distinct emplacementgeneral.numero,imputation.libelle,article.*,bareme.*"
						+ " FROM autorisationEmplacement,emplacementgeneral,imputation,elementfacturation,article,bareme"
						+ " WHERE idEmplacement=emplacementgeneral.numero"
						+ " AND emplacementgeneral.numero= elementfacturation.numeroEmplacement"
						+ " AND elementfacturation.numero= article.id_elementfacturation"
						+ " AND article.codebareme= bareme.code"
						+ " AND article.anexercice= bareme.anexercice"
						+ " AND imputation.idImputation= emplacementgeneral.codeType"
						+ " AND imputation.anneeExercice= emplacementgeneral.anneeExerciceImputation"
						+ " AND idautorisation=" + idAutorisation;

				debug.logger.debug(req);
				ResultSet resultat = instruction.executeQuery(req);
				while (resultat.next()) {
					Article article = new Article();
					article.setNumEmplacment(resultat.getString("emplacementgeneral.numero"));
					article.setLibelleImputation(resultat.getString("imputation.libelle"));
					article.setDateDebutAutorisation(resultat.getString("article.dateDebutAutorisation"));
					article.setDateFinAutorisation(resultat.getString("article.dateFinAutorisation"));
					article.setLibelleBareme(resultat.getString("bareme.libelle"));
					article.setNom(resultat.getString("article.nom"));
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
	
	public List getListeEmplacement(String idAutorisation)
	{
		List res = new ArrayList();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = "SELECT distinct emplacementgeneral.*,imputation.libelle"
						+ " FROM autorisationEmplacement,emplacementgeneral,imputation"
						+ " WHERE idEmplacement=emplacementgeneral.numero"
						+ " AND imputation.idImputation= emplacementgeneral.codeType"
						+ " AND imputation.anneeExercice= emplacementgeneral.anneeExerciceImputation"
						+ " AND idautorisation=" + idAutorisation;

				debug.logger.debug(req);
				ResultSet resultat = instruction.executeQuery(req);
				while (resultat.next()) {
					Emplacement emplacement= new Emplacement();
					emplacement.setId(resultat.getString("numero"));
					emplacement.setLibelleImputation(resultat.getString("imputation.libelle"));
					req = "SELECT distinct article.*,bareme.*"
							+ " FROM elementfacturation,article,bareme"
							+ " WHERE elementfacturation.numero= article.id_elementfacturation"
							+ " AND article.codebareme= bareme.code"
							+ " AND article.anexercice= bareme.anexercice"
							+ " AND elementfacturation.numeroEmplacement=" + resultat.getString("numero");
					Statement instruction1 = con.connexion.createStatement();
					ResultSet resultat1 = instruction1.executeQuery(req);
					List res1 = new ArrayList();
					while (resultat1.next()) {
						Article article = new Article();
						article.setDateDebutAutorisation(resultat1.getString("article.dateDebutAutorisation"));
						article.setDateFinAutorisation(resultat1.getString("article.dateFinAutorisation"));
						article.setLibelleBareme(resultat1.getString("bareme.libelle"));
						article.setNom(resultat1.getString("article.nom"));
						article.setQuantite_article(resultat1.getDouble("quantite_article"));
						res1.add(article);
					}
					emplacement.setListeOuvrage(res1);
					
					res.add(emplacement);
					
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
	//Maj la source selon l'état
	public void modifierAutorisation(String idautorisation, String etat, String refDossier){
				String source=etat.equalsIgnoreCase("Acceptée")?"normal":"autorisation";
				String requete = "UPDATE autorisation,autorisationemplacement,emplacementgeneral,elementfacturation,article "
						       + "set emplacementgeneral.source='"+source+"', article.source='"+source+"',elementfacturation.source='"+source
						       + "', autorisation.refDossier='"+refDossier+"', autorisation.etat='"+etat
						       + "' , autorisation.dateModification='"+GestionDate.getDateAujourdhuiString()+"'"
						       + " where autorisationemplacement.idautorisation=autorisation.idautorisation and "
						       + " autorisationemplacement.idemplacement=emplacementgeneral.numero and "
						       + " emplacementgeneral.numero=elementfacturation.numeroemplacement and "
						       + " article.id_elementfacturation=elementfacturation.numero and "
						       + " autorisation.idautorisation="+idautorisation;

				debug.logger.info("Modification de l'autorisation " + idautorisation);
				debug.logger.debug(requete);
				// MAJ EF :
				Statement instruction;
				try {
					instruction = con.connexion.createStatement();
					try {
						String message = "Modification de l'etat de l'autorisation n° "+idautorisation;
						message = FonctionCommun.ajouterAntislash(message);
						int result1 = instruction.executeUpdate(requete);
					} catch (SQLException e) {
						throw e;
					} finally {
						instruction.close();
					}
				} catch (SQLException e) {
					debug.logger.debug(e.getMessage());
				}
				
	}
	
	//Supprier l'autorisation
		public void supprimerAutorisation(String idautorisation){
					String requete = "DELETE autorisation,autorisationemplacement,emplacementgeneral,elementfacturation,article "
								   + " from autorisation,autorisationemplacement,emplacementgeneral,elementfacturation,article "
							       + " where autorisationemplacement.idautorisation=autorisation.idautorisation and "
							       + " autorisationemplacement.idemplacement=emplacementgeneral.numero and "
							       + " emplacementgeneral.numero=elementfacturation.numeroemplacement and "
							       + " article.id_elementfacturation=elementfacturation.numero and "
							       + " autorisation.idautorisation="+idautorisation;

					debug.logger.info("Suppression de l'autorisation " + idautorisation);
					debug.logger.debug(requete);
					// MAJ EF :
					Statement instruction;
					try {
						instruction = con.connexion.createStatement();
						try {
							String message = "Suppression de l'autorisation n° "+idautorisation;
							message = FonctionCommun.ajouterAntislash(message);
							int result1 = instruction.executeUpdate(requete);
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
