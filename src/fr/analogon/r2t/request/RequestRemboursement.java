package fr.analogon.r2t.request;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.pojo.Remboursement;

public class RequestRemboursement extends Request {


	public Vector getListeDesRemboursement(int idBatch)
	{
		Vector res = new Vector();
		try 
		{
			Statement instruction = con.connexion.createStatement();
			try 
			{
				String requete = " SELECT * FROM remboursement WHERE idBatchTraitement =" + idBatch;
				debug.logger.debug(requete);
				ResultSet resultat1 = instruction.executeQuery(requete);			
				while (resultat1.next()) 
				{
					Remboursement remboursement = new Remboursement();
					remboursement.setIdRemboursement(""+resultat1.getInt("idRemboursement"));
					remboursement.setIdBatchTraitement(""+resultat1.getInt("idBatchTraitement"));
					remboursement.setAnneeEx(resultat1.getString("anneeEx"));					
					res.addElement(remboursement);
				}
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			} finally {
				instruction.close();
			}
		}
		catch (Exception e) 
		{
			debug.logger.fatal(e.getCause());
			e.printStackTrace();
		}
		return res;
	}
	
	
}
