package fr.analogon.r2t.request;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Ville;

public class RequestVille extends Request {
	
	
	public int ajouterVille( String code, String ville ) 
	{
		ville = FonctionCommun.ajouterAntislash(ville);
		int res = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = " INSERT INTO ville (code,nom)"
						   + " VALUES('"+ code +"','" + ville +"')";
				debug.logger.debug("Requete d'insertion d'une ville " + req);
				instruction.executeUpdate(req);				
				// Recuperation de l'identifiant 
				String r2 = " SELECT  max(id) as lastId FROM ville " 
						    + " WHERE code='"+ code +"'" ;						
				debug.logger.debug(r2);
				ResultSet resultat2 = instruction.executeQuery(r2);
				new RequestAdmin().InsertAction("Ajouter une nouvelle ville  " 
						 + ville  , 	GestionDate.getDateTime());
				while (resultat2.next()) {
					res = resultat2.getInt("lastId");
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

	public Ville getVille(String id) {
		Ville ville = new Ville();
		try {
			Statement instruction = con.connexion.createStatement();
			try 
			{
				String req = " SELECT * FROM ville WHERE id="+id;
				debug.logger.debug(req);
				ResultSet resultat = instruction.executeQuery(req);
				while (resultat.next()) 
				{
					ville.setId(resultat.getInt("id"));
					ville.setCode(resultat.getString("code"));
					ville.setNom(resultat.getString("nom"));
				}				
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return ville;
	}
	
	public int modifierVille(String id,String code, String ville)
	{
		ville = FonctionCommun.ajouterAntislash(ville);
		int res = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try 
			{			
				String req =   " UPDATE ville " 
					         + " SET nom='" + ville +  "', code='" + code +"'"
					         + " WHERE id=" + id ; 
				debug.logger.debug("modifier une ville :\n" + req);
				int result1 = instruction.executeUpdate(req);
			} 
			catch (Exception e) 
			{
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return res;
	}

	public boolean supprimerVille(String id) 
	{
		boolean res = true;
		try {
			Statement instruction = con.connexion.createStatement();
			try 
			{
				//Verfication de dependence .... A faire
				
					
					String req = "delete from ville WHERE id=" + id ;
					debug.logger.debug(req);
					int result1 = instruction.executeUpdate(req);
					new RequestAdmin().InsertAction("Suppression de la ville  "
							+ id ,
							GestionDate.getDateTime());		
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

	public Vector getListeVille(String code,String nom )
	{
		nom = FonctionCommun.ajouterAntislash(nom);
		Vector res = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = " SELECT *  FROM ville "
						+ " WHERE 1 ";
				
				if (nom != null && nom.length() != 0)
					req = req + " AND nom like '%" + nom + "%'";
				
				if ( code != null && code.length() != 0)
					req = req + " AND code like '" + code + "%'";

				System.out.println(req);
				ResultSet resultat = instruction.executeQuery(req);
				
				while (resultat.next()) 
				{
					Ville ville = new Ville();
					ville.setId(resultat.getInt("id"));
					ville.setCode(resultat.getString("code"));
					ville.setNom(resultat.getString("nom"));					
					res.addElement(ville);
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
