package fr.analogon.r2t.request;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.pojo.BatchRelance;

public class RequestRelance extends Request
{
	
	public int ajouterLigneBatchRelance(String dateLancement,
			String periode, String typeRelance, String anneeCourante) {
		int res = 0;
		String requete = " INSERT INTO batchrelance(dateLancement,periode,type,annee)"
				+ " VALUES('"
				+ dateLancement
				+ "','"
				+ periode
				+ "','" + typeRelance + "','" + anneeCourante + "')";
		debug.logger.debug(requete);
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				int result1 = instruction.executeUpdate(requete);
				String req1 = " SELECT max(idBatchRelance) as idBatchRelance "
						+ " FROM batchrelance";
				debug.logger.debug(req1);
				ResultSet resultat = instruction.executeQuery(req1);
				while (resultat.next()) {
					res = resultat.getInt("idBatchRelance");
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

	
	
	public Vector getFactureNonPaye(String typeTaxe)	{	
		Vector res = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try	{
				String req = " SELECT " + 
							 " redevable.`civilite` AS redevable_civilite,"+
							 " redevable.`nomRedevable` AS redevable_nomRedevable,"+
							 " redevable.`prenom` AS redevable_prenom,"+
							 " redevable.`numRue` AS redevable_numRue,"+
							 " redevable.`adresse1` AS redevable_adresse1,"+
							 " redevable.`adresse2` AS redevable_adresse2,"+
							 " redevable.`adresse3` AS redevable_adresse3,"+
							 " redevable.`ville` AS redevable_ville,"+
							 " redevable.`codePostal` AS redevable_codePostal,"+
							 " redevable.`rdCedex` AS redevable_rdCedex,"+
							 " redevable.`numRedevable` AS redevable_numRedevable,"+
							 " redevable.`complementNumeroRueRedevable` AS redevable_complementNumeroRueRedevable,"+
							
							 " facture.`numeroTitre` AS facture_numeroTitre,"+
							 " facture.`dateCreation` AS facture_dateCreation,"+
							 " facture.`typeTaxe` AS facture_typeTaxe,"+
							 " facture.`idClient` AS facture_idClient,"+
							 " facture.`anneeEx` AS facture_anneeEx,"+
							 " facture.`montantTotal` AS facture_montantTotal,"+
						 	
					 		 " facture.`idBatchTraitement` AS facture_idBatchTraitement,"+
				 			 " facture.`numeroFacture` AS facture_numeroFacture,"+
							 " facture.`utiliserMontantDeSeuil` AS facture_utiliserMontantDeSeuil,"+
							 " facture.`etat` AS facture_etat,"+
	
							 " imputation.`designation` AS imputation_designation,"+
							 " imputation.`minimumPerception` AS imputation_minimumPerception,"+
							 " imputation.`telGestionnaire` AS imputation_telGestionnaire,"+
							 " imputation.`faxGestionnaire` AS imputation_faxGestionnaire,"+
							
							 " lignefacture.`montantLigne` AS lignefacture_montantLigne,"+
							 " lignefacture.`numeroPeriode` AS lignefacture_numeroPeriode,"+
							 " lignefacture.`debutPeriode` AS lignefacture_debutPeriode,"+
							 " lignefacture.`finPeriode` AS lignefacture_finPeriode,"+
							 " lignefacture.`quantite` AS lignefacture_quantite,"+
							 " lignefacture.`prixUnitaire` AS lignefacture_prixUnitaire,"+
	
							 " bareme.`prixUnit` AS bareme_prixUnit,"+
							 " bareme.`uniteDeTravail` AS bareme_uniteDeTravail,"+
							 " bareme.`dureeunitaire` AS bareme_dureeunitaire,"+
							 " bareme.`libelle` AS bareme_libelle,"+
	
							 " elementfacturation.`numeroEmplacement` AS elementfacturation_numeroEmplacement,"+
							 " emplacementgeneral.`numRue` AS emplacementgeneral_numRue,"+
							 " emplacementgeneral.`adresse1` AS emplacementgeneral_adresse1,"+
							 " emplacementgeneral.`adresse2` AS emplacementgeneral_adresse2,"+
							 " emplacementgeneral.`adresse3` AS emplacementgeneral_adresse3,"+
							 " emplacementgeneral.`codePostal` AS emplacementgeneral_codePostal,"+
							 " emplacementgeneral.`ville` AS emplacementgeneral_ville,"+
							 " emplacementgeneral.`raisonSocial` AS emplacementgeneral_raisonSocial,"+
							 " emplacementgeneral.`complementNumeroRueEmpl` AS emplacementgeneral_complementNumeroRueEmpl,"+
	
							 " article.`nom` AS article_nom,"+
							 " article.`quantite_article` AS article_quantite_article,"+
							 " article.`id_article` AS article_id_article"+
	
						" FROM " +
							" `facture` facture,`redevable` redevable,`imputation` imputation, "+
							" `lignefacture` lignefacture,`article` article,"+
							" `elementfacturation` elementfacturation,`emplacementgeneral` emplacementgeneral,"+
							" `bareme` bareme "+  
	
						" WHERE  "+
							" redevable.numRedevable = facture.idClient"+ 
							" AND imputation.libelle = facture.typeTaxe "+
							" AND facture.`anneeEx` = `imputation`.`anneeExercice`"+ 
							" AND lignefacture.idFacture =  `facture`.`numeroFacture`"+ 
							" AND `facture`.`anneeEx` = `bareme`.`anExercice`"+
							" AND article.id_article = lignefacture.idArticle "+
							" AND elementfacturation.numero = article.id_elementfacturation"+ 
							" AND emplacementgeneral.numero = elementfacturation.numeroEmplacement"+ 
							" AND bareme.code = article.codebareme "+						
							" AND facture.etat = 'Valide'" +
							" AND facture.solde !='0.00' " +
	
						" ORDER BY "+
							" elementfacturation.`numeroEmplacement` ASC,"+
							" article.id_article ASC,"+
							" lignefacture.`numeroPeriode` ASC ;";
						debug.logger.debug(req);					
						ResultSet resultat = instruction.executeQuery(req);
						int i = 0; 
						
						int nombreFacture =0; ;
						String lastNumeroFacture ="" ;
						double somme= 0;
						while(resultat.next())
						{
							//System.out.print(i++);
							for (int k = 1; k < 48; k++) 
							{
								String newNumero = resultat.getString("facture_numeroFacture");
								String newmontant =resultat.getString("facture_montantTotal");
								if (! newNumero.equalsIgnoreCase(lastNumeroFacture))
								{
									lastNumeroFacture = newNumero;
									nombreFacture++;
									somme = somme + Double.valueOf(newmontant);								
								}
								
								res.addElement(resultat.getString(k));
							}
							res.addElement("FINLIGNEEXCEL");																	
						}	
						System.out.println("Somme non paye="+ somme);
						System.out.println("nombreFacture="+ nombreFacture);
				 }catch (Exception e) {
					 debug.logger.fatal(e.getMessage()); 
				}finally {
					instruction.close();
				}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
			 return res;
		}
	
	
	public BatchRelance getBatchRealRelance(int numeroBatchRelance) {

		BatchRelance  batchRelance = new BatchRelance();

		try {
			Statement instruction = con.connexion.createStatement();
			try {

				String r1 = " SELECT  *  " + " FROM batchrelance "
						+ " WHERE idBatchRelance=" + numeroBatchRelance;

				debug.logger.debug(r1);
				ResultSet resultat1 = instruction.executeQuery(r1);
				while (resultat1.next()) {
					batchRelance.setDateLancement(resultat1
							.getString("dateLancement"));
					batchRelance.setIdBatchRelance(""
							+ resultat1.getInt("idBatchRelance"));
					batchRelance.setIdBatchRelance(""
							+ resultat1.getInt("idBatchRelance"));
					batchRelance.setPeriode(resultat1.getString("periode"));
					batchRelance.setAnnee(resultat1.getString("annee"));					
					batchRelance.setType(resultat1.getString("type"));										
					batchRelance.setNombreFactureImpaye(resultat1
							.getInt("nombreFactureImpaye"));
				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
			e.printStackTrace();
		}
		return batchRelance;
	}

	
	public void majBatchRelance(int idBatchRelance, int nombreFactureImpaye) 
	{
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String requete = " UPDATE batchrelance set nombreFactureImpaye="
						+ nombreFactureImpaye + " WHERE idBatchRelance='"
						+ idBatchRelance +"'";
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


	  
}
