package fr.analogon.r2t.request;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Imputation;

public class RequestImputation extends Request {

	public int ajouterTypeTaxe(String libelle, String designation,
			String codeBudget, String code, String section,
			String codeFonction, String libelleFonction,
			String codeCentreResponsable, String libelleCentreResponsable,
			String codeCentreExecution, String libelleCentreExecution,
			String necessiteControleTerrain, String controleInduitFacturation,
			String nomElu, String eluRenseignement1, String eluRenseignement2,
			String eluRenseignement3, String nomGestionnaire,
			String telGestionnaire, String faxGestionnaire,
			String minimumPerception, String anneeExercice,
			String typeFacturation, String cycleFacturation,
			String renouvellementAutomatique, String minimumDeFacturation,
			String marche,String chapitre,String nature,String fonction,
			String codeOpeEquipement,String typeMouvement,String sens,
			String codeSegStructurelle,String codeEleStructurelleGestionnaire,String codeEleStructurelleDestinataire,
			String codeSegment1,String codeEleSectoriel1,
			String codeSegment2,String codeEleSectoriel2,
			String codeSegment3,String codeEleSectoriel3,
			String codeSegment4,String codeEleSectoriel4,
			String codeSegment5,String codeEleSectoriel5,
			String codeSegOperationnel,String codeEleOperationnel,
			String codeSegStrategique,String codeEleStrategique) {
		libelle = FonctionCommun.ajouterAntislash(libelle);
		designation = FonctionCommun.ajouterAntislash(designation);
		section = FonctionCommun.ajouterAntislash(section);
		libelleFonction = FonctionCommun.ajouterAntislash(libelleFonction);
		libelleCentreResponsable = FonctionCommun.ajouterAntislash(libelleCentreResponsable);
		codeCentreExecution = FonctionCommun.ajouterAntislash(codeCentreExecution);
		libelleCentreExecution = FonctionCommun.ajouterAntislash(libelleCentreExecution);
		nomElu = FonctionCommun.ajouterAntislash(nomElu);
		eluRenseignement1 = FonctionCommun.ajouterAntislash(eluRenseignement1);
		eluRenseignement2 = FonctionCommun.ajouterAntislash(eluRenseignement2);
		eluRenseignement3 = FonctionCommun.ajouterAntislash(eluRenseignement3);
		nomGestionnaire = FonctionCommun.ajouterAntislash(nomGestionnaire);
		//Paul evolution eCadre Budgetaire
		chapitre=FonctionCommun.ajouterAntislash(chapitre);
		nature=FonctionCommun.ajouterAntislash(nature);
		fonction=FonctionCommun.ajouterAntislash(fonction);
		codeOpeEquipement=FonctionCommun.ajouterAntislash(codeOpeEquipement);
		typeMouvement=FonctionCommun.ajouterAntislash(typeMouvement);
		sens=FonctionCommun.ajouterAntislash(sens);
		codeSegStructurelle=FonctionCommun.ajouterAntislash(codeSegStructurelle);
		codeEleStructurelleGestionnaire=FonctionCommun.ajouterAntislash(codeEleStructurelleGestionnaire);
		codeEleStructurelleDestinataire=FonctionCommun.ajouterAntislash(codeEleStructurelleDestinataire);
		codeSegment1=FonctionCommun.ajouterAntislash(codeSegment1);
		codeEleSectoriel1=FonctionCommun.ajouterAntislash(codeEleSectoriel1);
		codeSegment2=FonctionCommun.ajouterAntislash(codeSegment2);
		codeEleSectoriel2=FonctionCommun.ajouterAntislash(codeEleSectoriel2);
		codeSegment3=FonctionCommun.ajouterAntislash(codeSegment3);
		codeEleSectoriel3=FonctionCommun.ajouterAntislash(codeEleSectoriel3);
		codeSegment4=FonctionCommun.ajouterAntislash(codeSegment4);
		codeEleSectoriel4=FonctionCommun.ajouterAntislash(codeEleSectoriel4);
		codeSegment5=FonctionCommun.ajouterAntislash(codeSegment5);
		codeEleSectoriel5=FonctionCommun.ajouterAntislash(codeEleSectoriel5);
		codeSegOperationnel=FonctionCommun.ajouterAntislash(codeSegOperationnel);
		codeEleOperationnel=FonctionCommun.ajouterAntislash(codeEleOperationnel);
		codeSegStrategique=FonctionCommun.ajouterAntislash(codeSegStrategique);
		codeEleStrategique=FonctionCommun.ajouterAntislash(codeEleStrategique);
		int res = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {

				String req = "INSERT INTO imputation"
						+ "( libelle, designation,codeBudget, code,"
						+ "  section,codeFonction, libelleFonction, codeCentreResponsable,"
						+ "  libelleCentreResponsable, codeCentreExecution,libelleCentreExecution,"
						+ "  necessiteControleTerrain, controleInduitFacturation,nomElu,eluRenseignement1,"
						+ "  eluRenseignement2,eluRenseignement3,nomGestionnaire,telGestionnaire,"
						+ "  faxGestionnaire,minimumPerception,anneeExercice,typeFacturation,"
						+ "  cycleFacturation,renouvellementAutomatique,minimumDeFacturation,marche,"
						+ "	 chapitre,nature,fonction,codeOpeEquipement,typeMouvement,sens,"
						+ "  codeSegStructurelle,codeEleStructurelleGestionnaire,codeEleStructurelleDestinataire,"
						+ "  codeSegment1,codeEleSectoriel1,codeSegment2,codeEleSectoriel2,codeSegment3,codeEleSectoriel3,"
						+ "  codeSegment4,codeEleSectoriel4,codeSegment5,codeEleSectoriel5,"
						+ "  codeSegOperationnel,codeEleOperationnel,codeSegStrategique,codeEleStrategique)"
						+ "  VALUES('"
						+ libelle
						+ "','"
						+ designation
						+ "','"
						+ codeBudget
						+ "','"
						+ code
						+ "','"
						+ section
						+ "','"
						+ codeFonction
						+ "','"
						+ libelleFonction
						+ "','"
						+ codeCentreResponsable
						+ "','"
						+ libelleCentreResponsable
						+ "','"
						+ codeCentreExecution
						+ "','"
						+ libelleCentreExecution
						+ "','"
						+ necessiteControleTerrain
						+ "','"
						+ controleInduitFacturation
						+ "','"
						+ nomElu
						+ "','"
						+ eluRenseignement1
						+ "','"
						+ eluRenseignement2
						+ "','"
						+ eluRenseignement3
						+ "','"
						+ nomGestionnaire
						+ "','"
						+ telGestionnaire
						+ "','"
						+ faxGestionnaire
						+ "','"
						+ minimumPerception
						+ "','"
						+ anneeExercice
						+ "','"
						+ typeFacturation
						+ "','"
						+ cycleFacturation
						+ "','"
						+ renouvellementAutomatique 
						+ "','"
						+ minimumDeFacturation
						+"','"
						+ marche
						+ "','"
						+ chapitre
						+ "','"
						+ nature
						+ "','"
						+ fonction
						+ "','"
						+ codeOpeEquipement
						+ "','"					
						+ typeMouvement
						+ "','"
						+ sens
						+ "','"
						+ codeSegStructurelle
						+ "','"
						+ codeEleStructurelleGestionnaire
						+ "','"
						+ codeEleStructurelleDestinataire
						+ "','"
						+ codeSegment1
						+ "','"
						+ codeEleSectoriel1
						+ "','"
						+ codeSegment2
						+ "','"
						+ codeEleSectoriel2
						+ "','"
						+ codeSegment3
						+ "','"
						+ codeEleSectoriel3
						+ "','"
						+ codeSegment4
						+ "','"
						+ codeEleSectoriel4
						+ "','"
						+ codeSegment5
						+ "','"
						+ codeEleSectoriel5
						+ "','"
						+ codeSegOperationnel
						+ "','"
						+ codeEleOperationnel
						+ "','"
						+ codeSegStrategique
						+ "','"
						+ codeEleStrategique
						+ "')";
												
				debug.logger
						.debug("Requete d'insertion d'un nouveau type de taxe :\n"
								+ req);
				instruction.executeUpdate(req);
				instruction.executeUpdate("commit;");
				// Recuperation de l'identifiant du nouveau type de taxe
				String r2 = " SELECT imputation.idImputation "
						+ " FROM imputation" + " WHERE libelle='"
						+ libelle
						+ "'"
						+ " AND designation='"
						+ designation
						+ "'"
						+ " AND codeBudget='"
						+ codeBudget
						+ "'"
						+ " AND code='"
						+ code
						+ "'"
						+ " AND section='"
						+ section
						+ "'"
						+ " AND nomElu='"
						+ nomElu
						+ "'"
						+ " AND eluRenseignement1='"
						+ eluRenseignement1
						+ "'"
						+ " AND eluRenseignement2='"
						+ eluRenseignement2
						+ "'"
						+ " AND eluRenseignement3='"
						+ eluRenseignement3
						+ "'"
						+ " AND nomGestionnaire='"
						+ nomGestionnaire
						+ "'"
						+ " AND telGestionnaire='"
						+ telGestionnaire
						+ "'"
						+ " AND faxGestionnaire='"
						+ faxGestionnaire
						+ "'"
						+
						" AND codeFonction='"
						+ codeFonction
						+ "'"
						+ " AND libelleFonction='"
						+ libelleFonction
						+ "'"
						+ " AND codeCentreResponsable='"
						+ codeCentreResponsable
						+ "'"
						+ " AND libelleCentreResponsable='"
						+ libelleCentreResponsable
						+ "'"
						+ " AND codeCentreExecution='"
						+ codeCentreExecution
						+ "'"
						+ " AND libelleCentreExecution='"
						+ libelleCentreExecution
						+ "'"
						+ " AND necessiteControleTerrain='"
						+ necessiteControleTerrain
						+ "'"
						+ " AND controleInduitFacturation='"
						+ controleInduitFacturation
						+ "'"
						+
						" AND minimumPerception='"
						+ minimumPerception
						+ "'"
						+ " AND anneeExercice='"
						+ anneeExercice
						+ "'"
						+ " AND typeFacturation='" + typeFacturation + "'";

				debug.logger.debug(r2);

				ResultSet resultat2 = instruction.executeQuery(r2);
				new RequestAdmin().InsertAction(
						"Ajouter un nouveau type de taxe " + libelle + " pour "
								+ " l'annee" + anneeExercice, GestionDate
								.getDateTime());
				while (resultat2.next()) {
					res = resultat2.getInt("idImputation");
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

	public int ajouterTypeTaxe(int idImputation, String libelle,
			String designation, String codeBudget, String code, String section,
			String codeFonction, String libelleFonction,
			String codeCentreResponsable, String libelleCentreResponsable,
			String codeCentreExecution, String libelleCentreExecution,
			String necessiteControleTerrain, String controleInduitFacturation,
			String nomElu, String eluRenseignement1, String eluRenseignement2,
			String eluRenseignement3, String nomGestionnaire,
			String telGestionnaire, String faxGestionnaire,
			String minimumPerception, String anneeExercice,
			String typeFacturation, String cycleFacturation,
			String chapitre,String nature,String fonction,
			String codeOpeEquipement,String typeMouvement,String sens,
			String codeSegStructurelle,String codeEleStructurelleGestionnaire,String codeEleStructurelleDestinataire,
			String codeSegment1,String codeEleSectoriel1,
			String codeSegment2,String codeEleSectoriel2,
			String codeSegment3,String codeEleSectoriel3,
			String codeSegment4,String codeEleSectoriel4,
			String codeSegment5,String codeEleSectoriel5,
			String codeSegOperationnel,String codeEleOperationnel,
			String codeSegStrategique,String codeEleStrategique, String marche) {
		libelle = FonctionCommun.ajouterAntislash(libelle);
		designation = FonctionCommun.ajouterAntislash(designation);
		section = FonctionCommun.ajouterAntislash(section);
		libelleFonction = FonctionCommun.ajouterAntislash(libelleFonction);
		libelleCentreResponsable = FonctionCommun.ajouterAntislash(libelleCentreResponsable);
		codeCentreExecution = FonctionCommun.ajouterAntislash(codeCentreExecution);
		libelleCentreExecution = FonctionCommun.ajouterAntislash(libelleCentreExecution);
		nomElu = FonctionCommun.ajouterAntislash(nomElu);
		eluRenseignement1 = FonctionCommun.ajouterAntislash(eluRenseignement1);
		eluRenseignement2 = FonctionCommun.ajouterAntislash(eluRenseignement2);
		eluRenseignement3 = FonctionCommun.ajouterAntislash(eluRenseignement3);
		nomGestionnaire = FonctionCommun.ajouterAntislash(nomGestionnaire);
		//Paul evolution eCadre Budgetaire
		chapitre=FonctionCommun.ajouterAntislash(chapitre);
		nature=FonctionCommun.ajouterAntislash(nature);
		fonction=FonctionCommun.ajouterAntislash(fonction);
		codeOpeEquipement=FonctionCommun.ajouterAntislash(codeOpeEquipement);
		typeMouvement=FonctionCommun.ajouterAntislash(typeMouvement);
		sens=FonctionCommun.ajouterAntislash(sens);
		codeSegStructurelle=FonctionCommun.ajouterAntislash(codeSegStructurelle);
		codeEleStructurelleGestionnaire=FonctionCommun.ajouterAntislash(codeEleStructurelleGestionnaire);
		codeEleStructurelleDestinataire=FonctionCommun.ajouterAntislash(codeEleStructurelleDestinataire);
		codeSegment1=FonctionCommun.ajouterAntislash(codeSegment1);
		codeEleSectoriel1=FonctionCommun.ajouterAntislash(codeEleSectoriel1);
		codeSegment2=FonctionCommun.ajouterAntislash(codeSegment2);
		codeEleSectoriel2=FonctionCommun.ajouterAntislash(codeEleSectoriel2);
		codeSegment3=FonctionCommun.ajouterAntislash(codeSegment3);
		codeEleSectoriel3=FonctionCommun.ajouterAntislash(codeEleSectoriel3);
		codeSegment4=FonctionCommun.ajouterAntislash(codeSegment4);
		codeEleSectoriel4=FonctionCommun.ajouterAntislash(codeEleSectoriel4);
		codeSegment5=FonctionCommun.ajouterAntislash(codeSegment5);
		codeEleSectoriel5=FonctionCommun.ajouterAntislash(codeEleSectoriel5);
		codeSegOperationnel=FonctionCommun.ajouterAntislash(codeSegOperationnel);
		codeEleOperationnel=FonctionCommun.ajouterAntislash(codeEleOperationnel);
		codeSegStrategique=FonctionCommun.ajouterAntislash(codeSegStrategique);
		codeEleStrategique=FonctionCommun.ajouterAntislash(codeEleStrategique);

		int res = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {

				String req = "INSERT INTO imputation"
						+ "( idImputation,libelle, designation,codeBudget, code,"
						+ "  section,codeFonction, libelleFonction, codeCentreResponsable,"
						+ "  libelleCentreResponsable, codeCentreExecution,libelleCentreExecution,"
						+ "  necessiteControleTerrain, controleInduitFacturation,nomElu,eluRenseignement1,"
						+ "  eluRenseignement2,eluRenseignement3,nomGestionnaire,telGestionnaire,"
						+ "  faxGestionnaire,minimumPerception,anneeExercice,typeFacturation,cycleFacturation,"
						+ "  chapitre,nature,fonction,codeOpeEquipement,typeMouvement,sens,"
						+ "  codeSegStructurelle,codeEleStructurelleGestionnaire,codeEleStructurelleDestinataire,"
						+ "  codeSegment1,codeEleSectoriel1,codeSegment2,codeEleSectoriel2,codeSegment3,codeEleSectoriel3,"
						+ "  codeSegment4,codeEleSectoriel4,codeSegment5,codeEleSectoriel5,"
						+ "  codeSegOperationnel,codeEleOperationnel,codeSegStrategique,codeEleStrategique,marche )"
						+ "  VALUES('"
						+ idImputation
						+ "','"
						+ libelle
						+ "','"
						+ designation
						+ "','"
						+ codeBudget
						+ "','"
						+ code
						+ "','"
						+ section
						+ "','"
						+ codeFonction
						+ "','"
						+ libelleFonction
						+ "','"
						+ codeCentreResponsable
						+ "','"
						+ libelleCentreResponsable
						+ "','"
						+ codeCentreExecution
						+ "','"
						+ libelleCentreExecution
						+ "','"
						+ necessiteControleTerrain
						+ "','"
						+ controleInduitFacturation
						+ "','"
						+ nomElu
						+ "','"
						+ eluRenseignement1
						+ "','"
						+ eluRenseignement2
						+ "','"
						+ eluRenseignement3
						+ "','"
						+ nomGestionnaire
						+ "','"
						+ telGestionnaire
						+ "','"
						+ faxGestionnaire
						+ "','"
						+ minimumPerception
						+ "','"
						+ anneeExercice
						+ "','"
						+ typeFacturation
						+ "','"
						+ cycleFacturation
						+ "','"
						+ chapitre
						+ "','"
						+ nature
						+ "','"
						+ fonction
						+ "','"
						+ codeOpeEquipement
						+ "','"					
						+ typeMouvement
						+ "','"
						+ sens
						+ "','"
						+ codeSegStructurelle
						+ "','"
						+ codeEleStructurelleGestionnaire
						+ "','"
						+ codeEleStructurelleDestinataire
						+ "','"
						+ codeSegment1
						+ "','"
						+ codeEleSectoriel1
						+ "','"
						+ codeSegment2
						+ "','"
						+ codeEleSectoriel2
						+ "','"
						+ codeSegment3
						+ "','"
						+ codeEleSectoriel3
						+ "','"
						+ codeSegment4
						+ "','"
						+ codeEleSectoriel4
						+ "','"
						+ codeSegment5
						+ "','"
						+ codeEleSectoriel5
						+ "','"
						+ codeSegOperationnel
						+ "','"
						+ codeEleOperationnel
						+ "','"
						+ codeSegStrategique
						+ "','"
						+ codeEleStrategique
						+ "','"
						+ marche
						+ "')";

				debug.logger
						.debug("Requete d'insertion d'un nouveau type de taxe :\n"
								+ req);
				instruction.executeUpdate(req);
				instruction.executeUpdate("commit;");
				// Recuperation de l'identifiant du nouveau type de taxe
				String r2 = " SELECT imputation.idImputation "
						+ " FROM imputation" + " WHERE libelle='"
						+ libelle
						+ "'"
						+ " AND designation='"
						+ designation
						+ "'"
						+ " AND codeBudget='"
						+ codeBudget
						+ "'"
						+ " AND code='"
						+ code
						+ "'"
						+ " AND section='"
						+ section
						+ "'"
						+ " AND nomElu='"
						+ nomElu
						+ "'"
						+ " AND eluRenseignement1='"
						+ eluRenseignement1
						+ "'"
						+ " AND eluRenseignement2='"
						+ eluRenseignement2
						+ "'"
						+ " AND eluRenseignement3='"
						+ eluRenseignement3
						+ "'"
						+ " AND nomGestionnaire='"
						+ nomGestionnaire
						+ "'"
						+ " AND telGestionnaire='"
						+ telGestionnaire
						+ "'"
						+ " AND faxGestionnaire='"
						+ faxGestionnaire
						+ "'"
						+

						" AND codeFonction='"
						+ codeFonction
						+ "'"
						+ " AND libelleFonction='"
						+ libelleFonction
						+ "'"
						+ " AND codeCentreResponsable='"
						+ codeCentreResponsable
						+ "'"
						+ " AND libelleCentreResponsable='"
						+ libelleCentreResponsable
						+ "'"
						+ " AND codeCentreExecution='"
						+ codeCentreExecution
						+ "'"
						+ " AND libelleCentreExecution='"
						+ libelleCentreExecution
						+ "'"
						+ " AND necessiteControleTerrain='"
						+ necessiteControleTerrain
						+ "'"
						+ " AND controleInduitFacturation='"
						+ controleInduitFacturation
						+ "'"
						+

						" AND minimumPerception='"
						+ minimumPerception
						+ "'"
						+ " AND anneeExercice='"
						+ anneeExercice
						+ "'"
						+ " AND typeFacturation='" + typeFacturation + "'";

				debug.logger.debug(r2);

				ResultSet resultat2 = instruction.executeQuery(r2);
				new RequestAdmin().InsertAction(
						"Ajouter un nouveau type de taxe " + libelle + " pour "
								+ " l'annee" + anneeExercice, GestionDate
								.getDateTime());
				while (resultat2.next()) {
					res = resultat2.getInt("idImputation");
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

	public Imputation getImputation(String numtypeTaxe, String anneeExercice) {
		Imputation imputation = new Imputation();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = " SELECT imputation.* FROM imputation "
						+ " where imputation.idImputation=" + numtypeTaxe;
				if (anneeExercice.length() != 0)
					req = req + " AND imputation.anneeExercice="
							+ anneeExercice;
				debug.logger.debug(req);
				ResultSet resultat2 = instruction.executeQuery(req);
				while (resultat2.next()) {
					imputation.setNumtypeTaxe(String.valueOf(resultat2
							.getInt("idImputation")));
					imputation.setAnneeExercice(anneeExercice);
					imputation.setDesignation(resultat2
							.getString("designation"));
					imputation.setCode(resultat2.getString("code"));
					imputation.setLibelle(resultat2.getString("libelle"));
					imputation.setCodeBudget(resultat2.getString("codeBudget"));
					imputation.setSection(resultat2.getString("section"));
					imputation.setNomElu(resultat2.getString("nomElu"));
					imputation.setEluRenseignement1(resultat2
							.getString("eluRenseignement1"));
					imputation.setEluRenseignement2(resultat2
							.getString("eluRenseignement2"));
					imputation.setEluRenseignement3(resultat2
							.getString("eluRenseignement3"));
					imputation.setNomGestionnaire(resultat2
							.getString("nomGestionnaire"));
					imputation.setTelGestionnaire(resultat2
							.getString("telGestionnaire"));
					imputation.setFaxGestionnaire(resultat2
							.getString("faxGestionnaire"));
					imputation.setCycleFacturation(resultat2
							.getString("cycleFacturation"));

					imputation.setCodeFonction(resultat2
							.getString("codeFonction"));
					imputation.setLibelleFonction(resultat2
							.getString("libelleFonction"));
					imputation.setCodeCentreResponsable(resultat2
							.getString("codeCentreResponsable"));
					imputation.setLibelleCentreResponsable(resultat2
							.getString("libelleCentreResponsable"));
					imputation.setCodeCentreExecution(resultat2
							.getString("codeCentreExecution"));
					imputation.setLibelleCentreExecution(resultat2
							.getString("libelleCentreExecution"));
					imputation.setNecessiteControleTerrain(resultat2
							.getString("necessiteControleTerrain"));
					imputation.setControleInduitFacturation(resultat2
							.getString("controleInduitFacturation"));

					imputation.setMinimumDeFacturation(resultat2
							.getString("minimumDeFacturation"));
					imputation.setMinimumPerception(resultat2
							.getString("minimumPerception"));
					imputation.setAnneeExercice(resultat2
							.getString("anneeExercice"));
					imputation.setTypeFacturation(resultat2
							.getString("typeFacturation"));
					imputation.setRenouvellementAutomatique(resultat2
							.getString("renouvellementAutomatique"));
					
					String marche =resultat2.getString("marche");
					if(marche.equalsIgnoreCase("true")) marche ="oui";
					if(marche.equalsIgnoreCase("false")) marche ="non";
					imputation.setMarche(marche);
					//Paul evolution ecadre Budgetaire
					imputation.setChapitre(resultat2.getString("chapitre"));
					imputation.setNature(resultat2.getString("nature"));
					imputation.setFonction(resultat2.getString("fonction"));
					imputation.setCodeOpeEquipement(resultat2.getString("codeOpeEquipement"));
					imputation.setTypeMouvement(resultat2.getString("typeMouvement"));
					imputation.setSens(resultat2.getString("sens"));
					imputation.setCodeSegStructurelle(resultat2.getString("codeSegStructurelle"));
					imputation.setCodeEleStructurelleGestionnaire(resultat2.getString("codeEleStructurelleGestionnaire"));
					imputation.setCodeEleStructurelleDestinataire(resultat2.getString("codeEleStructurelleDestinataire"));
					imputation.setCodeSegment1(resultat2.getString("codeSegment1"));
					imputation.setCodeSegment2(resultat2.getString("codeSegment2"));
					imputation.setCodeSegment3(resultat2.getString("codeSegment3"));
					imputation.setCodeSegment4(resultat2.getString("codeSegment4"));
					imputation.setCodeSegment5(resultat2.getString("codeSegment5"));
					imputation.setCodeEleSectoriel1(resultat2.getString("codeEleSectoriel1"));
					imputation.setCodeEleSectoriel2(resultat2.getString("codeEleSectoriel2"));
					imputation.setCodeEleSectoriel3(resultat2.getString("codeEleSectoriel3"));
					imputation.setCodeEleSectoriel4(resultat2.getString("codeEleSectoriel4"));
					imputation.setCodeEleSectoriel5(resultat2.getString("codeEleSectoriel5"));					
					imputation.setCodeSegOperationnel(resultat2.getString("codeSegOperationnel"));
					imputation.setCodeEleOperationnel(resultat2.getString("codeEleOperationnel"));
					imputation.setCodeSegStrategique(resultat2.getString("codeSegStrategique"));
					imputation.setCodeEleStrategique(resultat2.getString("codeEleStrategique"));
				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return imputation;
	}

	public int majTypeTaxe(String numtypeTaxe, String libelle,
			String designation, String codeBudget, String code, String section,
			String codeFonction, String libelleFonction,
			String codeCentreResponsable, String libelleCentreResponsable,
			String codeCentreExecution, String libelleCentreExecution,
			String necessiteControleTerrain, String controleInduitFacturation,
			String nomElu, String EluRenseignement1, String EluRenseignement2,
			String EluRenseignement3, String nomGestionnaire,
			String telGestionnaire, String faxGestionnaire,
			String minimumPerception, String anneeExercice,
			String typeFacturation, String cycleFacturation,
			String renouvellementAutomatique, String minimumDeFacturation,String marche,
			String chapitre,String nature,String fonction,
			String codeOpeEquipement,String typeMouvement,String sens,
			String codeSegStructurelle,String codeEleStructurelleGestionnaire,String codeEleStructurelleDestinataire,
			String codeSegment1,String codeEleSectoriel1,
			String codeSegment2,String codeEleSectoriel2,
			String codeSegment3,String codeEleSectoriel3,
			String codeSegment4,String codeEleSectoriel4,
			String codeSegment5,String codeEleSectoriel5,
			String codeSegOperationnel,String codeEleOperationnel,
			String codeSegStrategique,String codeEleStrategique	) {
		libelle = FonctionCommun.ajouterAntislash(libelle);
		designation = FonctionCommun.ajouterAntislash(designation);
		section = FonctionCommun.ajouterAntislash(section);
		libelleFonction = FonctionCommun.ajouterAntislash(libelleFonction);
		libelleCentreResponsable = FonctionCommun.ajouterAntislash(libelleCentreResponsable);
		codeCentreExecution = FonctionCommun.ajouterAntislash(codeCentreExecution);
		libelleCentreExecution = FonctionCommun.ajouterAntislash(libelleCentreExecution);
		nomElu = FonctionCommun.ajouterAntislash(nomElu);
		EluRenseignement1 = FonctionCommun.ajouterAntislash(EluRenseignement1);
		EluRenseignement2 = FonctionCommun.ajouterAntislash(EluRenseignement2);
		EluRenseignement3 = FonctionCommun.ajouterAntislash(EluRenseignement3);
		nomGestionnaire = FonctionCommun.ajouterAntislash(nomGestionnaire);
		//Paul evolution eCadre Budgetaire
		chapitre=FonctionCommun.ajouterAntislash(chapitre);
		nature=FonctionCommun.ajouterAntislash(nature);
		fonction=FonctionCommun.ajouterAntislash(fonction);
		codeOpeEquipement=FonctionCommun.ajouterAntislash(codeOpeEquipement);
		typeMouvement=FonctionCommun.ajouterAntislash(typeMouvement);
		sens=FonctionCommun.ajouterAntislash(sens);
		codeSegStructurelle=FonctionCommun.ajouterAntislash(codeSegStructurelle);
		codeEleStructurelleGestionnaire=FonctionCommun.ajouterAntislash(codeEleStructurelleGestionnaire);
		codeEleStructurelleDestinataire=FonctionCommun.ajouterAntislash(codeEleStructurelleDestinataire);
		codeSegment1=FonctionCommun.ajouterAntislash(codeSegment1);
		codeEleSectoriel1=FonctionCommun.ajouterAntislash(codeEleSectoriel1);
		codeSegment2=FonctionCommun.ajouterAntislash(codeSegment2);
		codeEleSectoriel2=FonctionCommun.ajouterAntislash(codeEleSectoriel2);
		codeSegment3=FonctionCommun.ajouterAntislash(codeSegment3);
		codeEleSectoriel3=FonctionCommun.ajouterAntislash(codeEleSectoriel3);
		codeSegment4=FonctionCommun.ajouterAntislash(codeSegment4);
		codeEleSectoriel4=FonctionCommun.ajouterAntislash(codeEleSectoriel4);
		codeSegment5=FonctionCommun.ajouterAntislash(codeSegment5);
		codeEleSectoriel5=FonctionCommun.ajouterAntislash(codeEleSectoriel5);
		codeSegOperationnel=FonctionCommun.ajouterAntislash(codeSegOperationnel);
		codeEleOperationnel=FonctionCommun.ajouterAntislash(codeEleOperationnel);
		codeSegStrategique=FonctionCommun.ajouterAntislash(codeSegStrategique);
		codeEleStrategique=FonctionCommun.ajouterAntislash(codeEleStrategique);
			
		int res = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = "update imputation" + " set libelle='" + libelle
						+ "'" + " ,designation='" + designation + "'"
						+ " ,codeBudget='" + codeBudget + "'" 
						+ " ,code='" + code + "'" 
						+ " ,section='" + section + "'"
						+ " ,nomElu='" + nomElu + "'" 
						+ " ,EluRenseignement1='" + EluRenseignement1 + "'" 
						+ " ,EluRenseignement2='" + EluRenseignement2  + "'" 
						+ " ,EluRenseignement3='" + EluRenseignement3 + "'" 
						+ " ,nomGestionnaire='" + nomGestionnaire + "'" 
						+ " ,telGestionnaire='" + telGestionnaire + "'" 
						+ " ,faxGestionnaire='" + faxGestionnaire + "'" 
						+ " ,codeFonction='" + codeFonction + "'" 
						+ " ,libelleFonction='" + libelleFonction + "'" 
						+ " ,codeCentreResponsable='" + codeCentreResponsable + "'"
						+ " ,libelleCentreResponsable='" + libelleCentreResponsable + "'"
						+ " ,codeCentreExecution='" + codeCentreExecution + "'"
						+ " ,libelleCentreExecution='" + libelleCentreExecution + "'" 
						+ " ,necessiteControleTerrain='" + necessiteControleTerrain + "'"
						+ " ,controleInduitFacturation='" + controleInduitFacturation + "'"
						+ " ,minimumPerception='" + minimumPerception + "'"
						+ " ,minimumDeFacturation='" + minimumDeFacturation + "'"						
						+ " ,typeFacturation='" + typeFacturation + "'"
						+ " ,cycleFacturation='" + cycleFacturation + "'"
						+ " ,marche='" + marche + "'"
						+ " ,renouvellementAutomatique='" + renouvellementAutomatique + "'" 						
						+ " ,chapitre='" + chapitre + "'"						
						+ " ,nature='" + nature + "'"
						+ " ,fonction='" + fonction + "'"
						+ " ,codeOpeEquipement='" + codeOpeEquipement + "'"
						+ " ,typeMouvement='" + typeMouvement + "'"
						+ " ,sens='" + sens + "'"
						+ " ,codeSegStructurelle='" + codeSegStructurelle + "'"
						+ " ,codeEleStructurelleGestionnaire='" + codeEleStructurelleGestionnaire + "'"
						+ " ,codeEleStructurelleDestinataire='" + codeEleStructurelleDestinataire + "'"
						+ " ,codeSegment1='" + codeSegment1 + "'"
						+ " ,codeEleSectoriel1='" + codeEleSectoriel1 + "'"
						+ " ,codeSegment2='" + codeSegment2 + "'"
						+ " ,codeEleSectoriel2='" + codeEleSectoriel2 + "'"
						+ " ,codeSegment3='" + codeSegment3 + "'"
						+ " ,codeEleSectoriel3='" + codeEleSectoriel3 + "'"
						+ " ,codeSegment4='" + codeSegment4 + "'"
						+ " ,codeEleSectoriel4='" + codeEleSectoriel4 + "'"
						+ " ,codeSegment5='" + codeSegment5 + "'"
						+ " ,codeEleSectoriel5='" + codeEleSectoriel5 + "'"						
						+ " ,codeSegOperationnel='" + codeSegOperationnel + "'"
						+ " ,codeEleOperationnel='" + codeEleOperationnel + "'"
						+ " ,codeSegStrategique='" + codeSegStrategique + "'" 						
						+ " ,codeEleStrategique='" + codeEleStrategique + "'"					
						+						
						" WHERE idImputation='" + numtypeTaxe + "'"
						+ " AND anneeExercice='" + anneeExercice + "'";
				debug.logger.debug(req);
				int result1 = instruction.executeUpdate(req);
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

	public Vector getListeImputations(String libelle, String code,
			String section, String anneeExercice, String numtypeTaxe) {
		libelle = FonctionCommun.ajouterAntislash(libelle);

		Vector res = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = " SELECT imputation.* FROM imputation "
						+ " WHERE imputation.idImputation =imputation.idImputation ";

				if (libelle != null && !libelle.equalsIgnoreCase(""))
					req = req + " AND  imputation.libelle like'%" + libelle
							+ "%'";

				if (numtypeTaxe != null && !numtypeTaxe.equalsIgnoreCase(""))
					req = req + " AND  imputation.idImputation ='"
							+ numtypeTaxe + "'";

				if (code != null && !code.equalsIgnoreCase(""))
					req = req + " AND  imputation.code ='" + code + "'";

				if (section != null && !section.equalsIgnoreCase(""))
					req = req + " AND  imputation.section ='" + section + "'";

				if (anneeExercice != null
						&& !anneeExercice.equalsIgnoreCase(""))
					req = req + " AND  imputation.anneeExercice ='"
							+ anneeExercice + "'";

				debug.logger.debug(req);
				ResultSet resultat2 = instruction.executeQuery(req);
				while (resultat2.next()) {
					Imputation imputation = new Imputation();
					imputation.setNumtypeTaxe(String.valueOf(resultat2
							.getInt("idImputation")));
					imputation.setDesignation(resultat2
							.getString("designation"));
					imputation.setCode(resultat2.getString("code"));
					imputation.setLibelle(resultat2.getString("libelle"));
					imputation.setCodeBudget(resultat2.getString("codeBudget"));
					imputation.setSection(resultat2.getString("section"));

					imputation.setNomElu(resultat2.getString("nomElu"));
					imputation.setEluRenseignement1(resultat2
							.getString("EluRenseignement1"));
					imputation.setEluRenseignement2(resultat2
							.getString("EluRenseignement2"));
					imputation.setEluRenseignement3(resultat2
							.getString("EluRenseignement3"));
					imputation.setNomGestionnaire(resultat2
							.getString("nomGestionnaire"));
					imputation.setTelGestionnaire(resultat2
							.getString("telGestionnaire"));
					imputation.setFaxGestionnaire(resultat2
							.getString("faxGestionnaire"));

					imputation.setCodeFonction(resultat2
							.getString("codeFonction"));
					imputation.setLibelleFonction(resultat2
							.getString("libelleFonction"));
					imputation.setCodeCentreResponsable(resultat2
							.getString("codeCentreResponsable"));
					imputation.setLibelleCentreResponsable(resultat2
							.getString("libelleCentreResponsable"));
					imputation.setCodeCentreExecution(resultat2
							.getString("codeCentreExecution"));
					imputation.setLibelleCentreExecution(resultat2
							.getString("libelleCentreExecution"));
					imputation.setNecessiteControleTerrain(resultat2
							.getString("necessiteControleTerrain"));
					imputation.setControleInduitFacturation(resultat2
							.getString("controleInduitFacturation"));

					imputation.setAnneeExercice(resultat2
							.getString("anneeExercice"));
					imputation.setMinimumPerception(resultat2
							.getString("minimumPerception"));
					imputation.setTypeFacturation(resultat2
							.getString("typeFacturation"));
					imputation.setCycleFacturation(resultat2
							.getString("cycleFacturation"));
					//Paul ajout @cadre budgétaire
					imputation.setChapitre(resultat2.getString("chapitre"));
					imputation.setNature(resultat2.getString("nature"));
					imputation.setFonction(resultat2.getString("fonction"));
					imputation.setCodeOpeEquipement(resultat2.getString("codeOpeEquipement"));
					imputation.setTypeMouvement(resultat2.getString("typeMouvement"));
					imputation.setSens(resultat2.getString("sens"));
					imputation.setCodeSegStructurelle(resultat2.getString("codeSegStructurelle"));
					imputation.setCodeEleStructurelleGestionnaire(resultat2.getString("codeEleStructurelleGestionnaire"));
					imputation.setCodeEleStructurelleDestinataire(resultat2.getString("codeEleStructurelleDestinataire"));
					imputation.setCodeSegment1(resultat2.getString("codeSegment1"));					
					imputation.setCodeEleSectoriel1(resultat2.getString("codeEleSectoriel1"));
					imputation.setCodeSegment2(resultat2.getString("codeSegment2"));
					imputation.setCodeEleSectoriel2(resultat2.getString("codeEleSectoriel2"));
					imputation.setCodeSegment2(resultat2.getString("codeSegment3"));
					imputation.setCodeEleSectoriel2(resultat2.getString("codeEleSectoriel3"));
					imputation.setCodeSegment2(resultat2.getString("codeSegment4"));
					imputation.setCodeEleSectoriel2(resultat2.getString("codeEleSectoriel4"));
					imputation.setCodeSegment2(resultat2.getString("codeSegment5"));
					imputation.setCodeEleSectoriel2(resultat2.getString("codeEleSectoriel5"));
					imputation.setCodeSegOperationnel(resultat2.getString("codeSegOperationnel"));
					imputation.setCodeEleOperationnel(resultat2.getString("codeEleOperationnel"));
					imputation.setCodeSegStrategique(resultat2.getString("codeSegStrategique"));
					imputation.setCodeEleStrategique(resultat2.getString("codeEleStrategique"));
					//Ajouter le marche
					imputation.setMarche(resultat2.getString("marche"));
					res.addElement(imputation);
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

	public void supprimerImputation(String idImputation, String anneeExercice) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = "delete from imputation WHERE idImputation="
						+ idImputation + " AND anneeExercice=" + anneeExercice;
				debug.logger.debug(req);
				int result1 = instruction.executeUpdate(req);
				new RequestAdmin().InsertAction(
						"Suppresion du type de taxe Numero " + idImputation
								+ " pour " + " l'annee" + anneeExercice,
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

	public Vector getListeTypeDeTaxe(String anneExercice , String marche) {
		Vector res = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {

				String req = " SELECT * FROM imputation WHERE  anneeExercice='"
						+ anneExercice + "'";
				debug.logger.debug(req);
				if (marche.equalsIgnoreCase("marche") ) req = req + " AND imputation.marche !=''";
				
				ResultSet resultat2 = instruction.executeQuery(req);
				while (resultat2.next()) {
					Imputation imputation = new Imputation();
					imputation.setNumtypeTaxe(String.valueOf(resultat2
							.getInt("idImputation")));
					imputation.setDesignation(resultat2
							.getString("designation"));
					imputation.setCode(resultat2.getString("code"));
					imputation.setLibelle(resultat2.getString("libelle"));
					imputation.setCodeBudget(resultat2.getString("codeBudget"));
					imputation.setSection(resultat2.getString("section"));

					imputation.setCodeFonction(resultat2
							.getString("codeFonction"));
					imputation.setLibelleFonction(resultat2
							.getString("libelleFonction"));
					imputation.setCodeCentreResponsable(resultat2
							.getString("codeCentreResponsable"));
					imputation.setLibelleCentreResponsable(resultat2
							.getString("libelleCentreResponsable"));
					imputation.setCodeCentreExecution(resultat2
							.getString("codeCentreExecution"));
					imputation.setLibelleCentreExecution(resultat2
							.getString("libelleCentreExecution"));
					imputation.setNecessiteControleTerrain(resultat2
							.getString("necessiteControleTerrain"));
					imputation.setControleInduitFacturation(resultat2
							.getString("controleInduitFacturation"));

					imputation.setNomElu(resultat2.getString("nomElu"));
					imputation.setEluRenseignement1(resultat2
							.getString("EluRenseignement1"));
					imputation.setEluRenseignement2(resultat2
							.getString("EluRenseignement2"));
					imputation.setEluRenseignement3(resultat2
							.getString("EluRenseignement3"));
					imputation.setNomGestionnaire(resultat2
							.getString("nomGestionnaire"));
					imputation.setTelGestionnaire(resultat2
							.getString("telGestionnaire"));
					imputation.setFaxGestionnaire(resultat2
							.getString("faxGestionnaire"));
					res.addElement(imputation);
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

	public Vector getTousLesUniteTravail() {

		Vector res = new Vector();

		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				instruction = con.connexion.createStatement();
				ResultSet resultat = instruction.executeQuery("SELECT valeur"
						+ " FROM parametre WHERE type='uniteTravail' ");
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

	public String peutEtreSuuprimer(String code, String anneeExercice) {
		String res = "true";
		try {
			Statement instruction = con.connexion.createStatement();
			try {

				String req = " SELECT idImputation from bareme "
						+ " WHERE bareme.idImputation='" + code + "'"
						+ " AND bareme.anExercice='" + anneeExercice + "'";
				debug.logger.debug(req);
				ResultSet resultat2 = instruction.executeQuery(req);
				while (resultat2.next()) {
					res = "false";
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

	/**
	 * Methode qui permet de retourner tous les type de taxes CHARFI Sofien
	 * 
	 * @since 2.0
	 */

	public Vector getTousLesTypesTaxe() {
		Vector res = new Vector();

		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String request = " SELECT * FROM imputation order by anneeExercice DESC ";
				ResultSet resultat2 = instruction.executeQuery(request);
				while (resultat2.next()) {
					// int idImputatoin = resultat.getInt("idImputation");

					Imputation imputation = new Imputation();
					imputation.setNumtypeTaxe(String.valueOf(resultat2
							.getInt("idImputation")));
					imputation.setDesignation(resultat2
							.getString("designation"));
					imputation.setCode(resultat2.getString("code"));
					imputation.setLibelle(resultat2.getString("libelle"));
					imputation.setCodeBudget(resultat2.getString("codeBudget"));
					imputation.setSection(resultat2.getString("section"));
					imputation.setNomElu(resultat2.getString("nomElu"));
					imputation.setEluRenseignement1(resultat2
							.getString("eluRenseignement1"));
					imputation.setEluRenseignement2(resultat2
							.getString("eluRenseignement2"));
					imputation.setEluRenseignement3(resultat2
							.getString("eluRenseignement3"));
					imputation.setNomGestionnaire(resultat2
							.getString("nomGestionnaire"));
					imputation.setTelGestionnaire(resultat2
							.getString("telGestionnaire"));
					imputation.setFaxGestionnaire(resultat2
							.getString("faxGestionnaire"));
					imputation.setCodeFonction(resultat2
							.getString("codeFonction"));
					imputation.setLibelleFonction(resultat2
							.getString("libelleFonction"));
					imputation.setCodeCentreResponsable(resultat2
							.getString("codeCentreResponsable"));
					imputation.setLibelleCentreResponsable(resultat2
							.getString("libelleCentreResponsable"));
					imputation.setCodeCentreExecution(resultat2
							.getString("codeCentreExecution"));
					imputation.setLibelleCentreExecution(resultat2
							.getString("libelleCentreExecution"));
					imputation.setNecessiteControleTerrain(resultat2
							.getString("necessiteControleTerrain"));
					imputation.setControleInduitFacturation(resultat2
							.getString("controleInduitFacturation"));
					imputation.setMinimumPerception(resultat2
							.getString("minimumPerception"));
					imputation.setAnneeExercice(resultat2
							.getString("anneeExercice"));
					imputation.setTypeFacturation(resultat2
							.getString("typeFacturation"));
					res.addElement(imputation);
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

	public Vector getLesDifferentTypesTaxe() {
		Vector res = new Vector();

		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {

				ResultSet resultat = instruction
						.executeQuery("SELECT DISTINCT(imputation.libelle), idImputation FROM imputation ");
				while (resultat.next()) {
					Imputation imputation = new Imputation();
					imputation.setLibelle(resultat.getString("libelle"));
					imputation.setCode("" + resultat.getInt("idImputation"));
					res.addElement(imputation);
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

	public String getIdImputationFromLibelle(String libelle, String annne) {
		String res = "";

		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String req = " SELECT * FROM imputation  WHERE libelle='"
						+ libelle + "'";
				if (annne.length() != 0)
					req = req + " AND anneeExercice=" + annne;
				debug.logger.debug("req=" + req);

				ResultSet resultat = instruction.executeQuery(req);
				while (resultat.next()) {
					res = String.valueOf(resultat.getInt("idImputation"));
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

	public Vector getTousLesAnneeDeTypesTaxe() {
		Vector res = new Vector();

		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat2 = instruction
						.executeQuery("SELECT DISTINCT(anneeExercice)  FROM imputation "
								+ " order by anneeExercice DESC ");
				while (resultat2.next()) {
					res.addElement(resultat2.getString("anneeExercice"));
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
