package fr.analogon.r2t.request;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.FamilleMarche;

public class RequestFamilleMarche extends Request {
	
	
	public int ajouterFamilleMarche( String libelleFamilleMarche ) 
	{
		libelleFamilleMarche = FonctionCommun.ajouterAntislash(libelleFamilleMarche);
		int res = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = " INSERT INTO marche (nomMarche)"
						   + " VALUES('"+ libelleFamilleMarche +"')";
				debug.logger.debug("Requete d'insertion d'un nouveau marche " + req);
				instruction.executeUpdate(req);				
				// Recuperation de l'identifiant 
				String r2 = " SELECT  max(idMarche) as lastId FROM marche " 
						    + " WHERE nomMarche='"+ libelleFamilleMarche +"'" ;						
				debug.logger.debug(r2);
				ResultSet resultat2 = instruction.executeQuery(r2);
				new RequestAdmin().InsertAction("Ajouter une nouveau marche  " 
						 + libelleFamilleMarche  , 	GestionDate.getDateTime());
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
		return res;	}

	
	
	public FamilleMarche getFamilleMarche(String codeFamilleMarche) {
		FamilleMarche familleMarche = new FamilleMarche();
		try {
			Statement instruction = con.connexion.createStatement();
			try 
			{
				String req = " SELECT * FROM marche WHERE idMarche='" + codeFamilleMarche +"'";
				debug.logger.debug(req);
				ResultSet resultat = instruction.executeQuery(req);
				while (resultat.next()) 
				{					
					familleMarche.setCodeFamilleMarche(resultat.getString("idMarche"));
					familleMarche.setLibelleFamilleMarche(resultat.getString("nomMarche"));
					familleMarche.setPeutEtreSupprimer(this.peutEtreSuuprimer(resultat.getString("idMarche")));
				}				
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return familleMarche;
	}
	
	
	public String peutEtreSuuprimer( String idMarcheMarche) {
		String res = "true";
		try {
			Statement instruction = con.connexion.createStatement();
			try {

				String req = " SELECT * from imputation "
						+ " WHERE marche="+ idMarcheMarche ;
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

	
	
	
	public int modifierFamilleMarche(String codeFamilleMarche, String libelleFamilleMarche)
	{
		libelleFamilleMarche = FonctionCommun.ajouterAntislash(libelleFamilleMarche);
		int res = 0;
		try {
			Statement instruction = con.connexion.createStatement();
			try 
			{			
				String req =   " UPDATE marche " 
					         + " SET nomMarche='" + libelleFamilleMarche +  "'" 
					         + " WHERE idMarche=" + codeFamilleMarche ; 
				debug.logger.debug("modifier un Bareme :\n" + req);
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

	
	
	
	

	
	public boolean supprimerFamilleMarche(String codeFamilleMarche) 
	{
		boolean res = true;
		try {
			Statement instruction = con.connexion.createStatement();
			try 
			{
				//Verfication de dependence .... A faire
				
					
					String req = "delete from marche WHERE idMarche=" + codeFamilleMarche ;
					debug.logger.debug(req);
					int result1 = instruction.executeUpdate(req);
					new RequestAdmin().InsertAction("Suprresion du marche  "
							+ codeFamilleMarche ,
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

	public Vector getListeFamilleMarche(String codeFamilleMarche,String libelleFamilleMarche )
	{
		libelleFamilleMarche = FonctionCommun.ajouterAntislash(libelleFamilleMarche);
		Vector res = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req = " SELECT *  FROM marche "
						+ " WHERE nomMarche != \"\" ";
				
				if (libelleFamilleMarche != null && libelleFamilleMarche.length() != 0)
					req = req + " AND nomMarche like '%" + libelleFamilleMarche + "%'";
				
				if ( codeFamilleMarche != null && codeFamilleMarche.length() != 0)
					req = req + " AND idMarche ='" + codeFamilleMarche + "'";

				debug.logger.debug(req);
				ResultSet resultat = instruction.executeQuery(req);
				
				while (resultat.next()) 
				{
					FamilleMarche familleMarche = new FamilleMarche();
					familleMarche.setCodeFamilleMarche(resultat.getString("idMarche"));
					familleMarche.setLibelleFamilleMarche(resultat.getString("nomMarche"));					
					res.addElement(familleMarche);
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
