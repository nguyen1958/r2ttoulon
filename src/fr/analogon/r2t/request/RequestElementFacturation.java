package fr.analogon.r2t.request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.analogon.r2t.Utilitaire.GestionDate;

public class RequestElementFacturation extends Request {

	// Ajout d'un elementFacturation
	public String ajouterElementFacturation(String numeroEmplacement,
			String anExercice, String source, String numeroTmp,
			String codeBareme) {

		String res = "";
		String requete = " INSERT INTO elementfacturation( numeroEmplacement, "
				+ " source,  numeroTmp) VALUES('" + numeroEmplacement + "','"
				+ source + "','" + numeroTmp + "')";

		debug.logger.debug(requete);
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				int result1 = instruction.executeUpdate(requete);
				instruction = con.connexion.createStatement();
				String req1 = " SELECT * FROM elementfacturation"
						+ " WHERE  elementfacturation.numeroTmp =" + numeroTmp;
				debug.logger.debug(req1);
				ResultSet resultat = instruction.executeQuery(req1);
				while (resultat.next()) {
					res = String.valueOf(resultat.getInt("numero"));
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

	public void supprimerEF(String idEF) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1 = "DELETE from elementfacturation WHERE  numero="
						+ idEF;
				debug.logger.debug(r1);
				int result1 = instruction.executeUpdate(r1);
				new RequestAdmin().InsertAction("Suppression de l EF " + idEF,
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
}
