package fr.analogon.r2t.request;

import java.sql.ResultSet;
import java.sql.Statement;

import fr.analogon.r2t.pojo.RaportComptable;

public class RequestRaportComptable extends Request {

	public int ajouterBatchRappportComptable(String dateLancement,
			String periode, String typeRaportComptable, String annee) {
		int res = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = " INSERT INTO batchrapportcomptable (dateLancement,"
						+ " periode,typeRaportComptable,annee)"
						+ " VALUES('"
						+ dateLancement	+ "','"
						+ periode + "','"	
						+ typeRaportComptable + "','" 
						+ annee + "')";
				debug.logger
						.debug("Requete d'insertion d'un nouveau payement :\n"
								+ req);
				instruction.executeUpdate(req);
				//instruction = con.connexion.createStatement();
				ResultSet resultat = instruction
						.executeQuery("SELECT max(idBatchRapportComptable) as lastid From batchrapportcomptable ");
				while (resultat.next()) {
					res = resultat.getInt("lastid");
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

	public RaportComptable getRapport(int idBatchRapport) {
		RaportComptable res = new RaportComptable();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				RaportComptable raport = new RaportComptable();
				String r = " SELECT * FROM batchrapportcomptable "
						+ " WHERE idBatchRapportComptable ='" + idBatchRapport
						+ "'";
				ResultSet resultat = instruction.executeQuery(r);
				debug.logger.debug(r);
				while (resultat.next()) {
					res.setIdBatchRapportComptable("" + idBatchRapport);
					res.setAnnee(resultat.getString("annee"));
					res.setDateLancement(resultat.getString("dateLancement"));
					res.setPeriode(resultat.getString("periode"));
					res.setTypeRaportComptable(resultat
							.getString("typeRaportComptable"));
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
