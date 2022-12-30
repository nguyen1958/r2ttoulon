package fr.analogon.r2t.request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Commentaire;



public class RequestCommentaire extends Request {

	public Vector getListeCommentaire(String numRedevable){
		Vector res = new Vector();
		String requete = "SELECT * from commentaireredevable left join utilisateur on auteur=numeroUser "
					   + " where numRedevable="+numRedevable
					   + " order by date desc";
		// Execution de la requete
		debug.logger.debug("requete de recherche des commentaires : " + requete);
		try {
			Statement instruction = con.connexion.createStatement();
			ResultSet resultat = instruction.executeQuery(requete);
			while (resultat.next()) {
				Commentaire Commentaire = new Commentaire();
				Commentaire.setIdCommentaire(resultat.getString("idCommentaire"));
				Commentaire.setDate(resultat.getTimestamp("date"));
				Commentaire.setCommentaire(resultat.getString("commentaire"));
				Commentaire.setNumRedevable(resultat.getString("numRedevable"));
				Commentaire.setAuteur(resultat.getString("auteur"));
				Commentaire.setResultatvisite(resultat.getString("resultatvisite"));
				Commentaire.setNomAuteur(resultat.getString("prenom")+" "+resultat.getString("nom"));
				
				res.addElement(Commentaire);
			}
		
		}
		catch (Exception e) {
			debug.logger.fatal("Erreur " + e.getMessage() + e.getCause()
					+ e.getMessage());
			e.printStackTrace();
		}
		
		return res;
		
	}
}
