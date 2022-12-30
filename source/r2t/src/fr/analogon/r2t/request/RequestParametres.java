package fr.analogon.r2t.request;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.pojo.Parametres;

public class RequestParametres extends Request
{
	public Vector getTousLesSecteurs()
	{
		Vector res= new Vector();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction.executeQuery( "SELECT DISTINCT(valeur) " +
						"FROM parametre WHERE type ='code_secteur' ORDER BY  CAST(valeur AS DECIMAL(8,2)) ");
				while(resultat.next()) {		
					res.addElement(resultat.getString("valeur"));					
				}
				instruction.close();
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
	
	public void ajouterMotifAnnulation(String motifAnnulation) {
		try {
			Statement instruction2 = con.connexion.createStatement();
			try	{
				motifAnnulation= FonctionCommun.ajouterAntislash(motifAnnulation);
				String req= "INSERT INTO parametre " +
					"(type,valeur)" +				
					" VALUES('motifAnnulationFacture','"+ motifAnnulation+"')";			
				debug.logger.debug(req);
				debug.logger.debug("Ajout d un motif d annulation de facture" + motifAnnulation);	
				int resultat3 = instruction2.executeUpdate(req);
			}catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}finally {
				instruction2.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}
	
	public void ajouterRaisonSocial(String RaisonSocial) {
		try {
			Statement instruction2 = con.connexion.createStatement();
			try {
				RaisonSocial= FonctionCommun.ajouterAntislash(RaisonSocial);
				String req= "INSERT INTO parametre " +
					"(type,valeur)" +				
					" VALUES('type_civilite','"+ RaisonSocial+"')";			
				debug.logger.debug(req);
				debug.logger.debug("Ajout d une nouvelle raison Social " + RaisonSocial);	
				int resultat3 = instruction2.executeUpdate(req);
			}catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}finally {
				instruction2.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}
	
	public void ajouterBanque(String banque) 
	{
		try {
			Statement instruction2 = con.connexion.createStatement();
			try {
				banque= FonctionCommun.ajouterAntislash(banque);
				String req= "INSERT INTO banque  " +
					"(libelle)" +				
					" VALUES('"+ banque+"')";			
				debug.logger.debug(req);
				debug.logger.debug("Ajout d une nouvelle banque " + banque);	
				int resultat3 = instruction2.executeUpdate(req);
			}catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}finally {
				instruction2.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}
	
	
	public void ajouterProfesion(String profesion) 
	{
		try {
			Statement instruction2 = con.connexion.createStatement();
			try {
				profesion= FonctionCommun.ajouterAntislash(profesion);
				String req= "INSERT INTO activiteprofession  " +
					"(libelle)" +				
					" VALUES('"+ profesion+"')";			
				debug.logger.debug(req);
				debug.logger.debug("Ajout d une nouvelle profesion " + profesion);	
				int resultat3 = instruction2.executeUpdate(req);
			}catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}finally {
				instruction2.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}
	
	
	
	public void modifierBanque(String oldValue, String newValue ) {
		try {
			Statement instruction = con.connexion.createStatement();
			try	{
				oldValue= FonctionCommun.ajouterAntislash(oldValue);
				newValue= FonctionCommun.ajouterAntislash(newValue);		
				String req= "UPDATE banque " +
			 		" set libelle='" +newValue +"'"+
			 		" WHERE libelle ='" + oldValue+"'" ;		
				debug.logger.debug("modifier banque  :\n"+req);			
				int resultat = instruction.executeUpdate(req);
			}catch (Exception e) {
				debug.logger.fatal(e.getMessage()+e.getLocalizedMessage() +  e.getMessage() + e.getCause());
			}finally {
				instruction.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage()+e.getLocalizedMessage() +  e.getMessage() + e.getCause());
		}
	}
	
	
	public void modifierProfesion(String oldValue, String newValue ) {
		try {
			Statement instruction = con.connexion.createStatement();
			try	{
				oldValue= FonctionCommun.ajouterAntislash(oldValue);
				newValue= FonctionCommun.ajouterAntislash(newValue);		
				String req= "UPDATE activiteprofession " +
			 		" set libelle='" +newValue +"'"+
			 		" WHERE libelle ='" + oldValue+"'" ;		
				debug.logger.debug("modifier Profesion  :\n"+req);			
				int resultat = instruction.executeUpdate(req);
			}catch (Exception e) {
				debug.logger.fatal(e.getMessage()+e.getLocalizedMessage() +  e.getMessage() + e.getCause());
			}finally {
				instruction.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage()+e.getLocalizedMessage() +  e.getMessage() + e.getCause());
		}
	}
	
	
	
	
	
	
	
	public void modifierMotifAnnulation(String oldValue, String newValue ) {
		try {
			Statement instruction = con.connexion.createStatement();
			try	{
				oldValue= FonctionCommun.ajouterAntislash(oldValue);
				newValue= FonctionCommun.ajouterAntislash(newValue);		
				String req= "UPDATE parametre " +
			 		" set valeur='" +newValue +"'"+
			 		" WHERE valeur ='" + oldValue+"'" + 
			 	    " AND type='motifAnnulationFacture'";		
				debug.logger.debug("modifier motif Annulation  :\n"+req);			
				int resultat = instruction.executeUpdate(req);
			}catch (Exception e) {
				debug.logger.fatal(e.getMessage()+e.getLocalizedMessage() +  e.getMessage() + e.getCause());
			}finally {
				instruction.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage()+e.getLocalizedMessage() +  e.getMessage() + e.getCause());
		}
	}
	
	public void modifierRaisonSocial(String oldValue, String newValue ) {
		try {
			Statement instruction = con.connexion.createStatement();
			try	{
				oldValue= FonctionCommun.ajouterAntislash(oldValue);
				newValue= FonctionCommun.ajouterAntislash(newValue);		
				String req= "UPDATE parametre " +
			 		" set valeur='" +newValue +"'"+
			 		" WHERE valeur ='" + oldValue+"'" + 
			 	    " AND type='type_civilite'";		
				debug.logger.debug("modifier Raison Social  :\n"+req);			
				int resultat = instruction.executeUpdate(req);
			}catch (Exception e) {
				debug.logger.fatal(e.getMessage()+e.getLocalizedMessage() +  e.getMessage() + e.getCause());
			}finally {
				instruction.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage()+e.getLocalizedMessage() +  e.getMessage() + e.getCause());
		}
	}
	
	
	public void supprimerMotifAnnulation(String oldValue ) {
		try {
			Statement instruction = con.connexion.createStatement();
			try	{
				oldValue= FonctionCommun.ajouterAntislash(oldValue);
				String req= "DELETE  from  parametre  " +		 		
			 		" WHERE valeur ='" + oldValue+"'" + 
			 	    " AND type='motifAnnulationFacture'";		
				debug.logger.debug("supression motif Annulation  :\n"+req);			
				int resultat = instruction.executeUpdate(req);
			}catch (Exception e) {
				debug.logger.fatal(e.getMessage()+e.getLocalizedMessage() +  e.getMessage() + e.getCause());
			}finally {
				instruction.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage()+e.getLocalizedMessage() +  e.getMessage() + e.getCause());
		}
	}
	
	public void supprimerRaisonSocial(String oldValue ) {
		try {
			Statement instruction = con.connexion.createStatement();
			try	{
				oldValue= FonctionCommun.ajouterAntislash(oldValue);
				String req= "DELETE  from  parametre  " +		 		
			 		" WHERE valeur ='" + oldValue+"'" + 
			 	    " AND type='type_civilite'";		
				debug.logger.debug("supression Raison Social  :\n"+req);			
				int resultat = instruction.executeUpdate(req);
			}catch (Exception e) {
				debug.logger.fatal(e.getMessage()+e.getLocalizedMessage() +  e.getMessage() + e.getCause());
			}finally {
				instruction.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage()+e.getLocalizedMessage() +  e.getMessage() + e.getCause());
		}
	}
	
	
	
	public void supprimerBanque(String oldValue ) {
		try 
		{
			
			Statement instruction0 = con.connexion.createStatement();
			Statement instruction = con.connexion.createStatement();
			String res = "0";
			try
			
			{
				oldValue= FonctionCommun.ajouterAntislash(oldValue);
				String req= "SELECT count(payement.idBanque) as nombre " +
						" FROM payement , banque " +
						" WHERE payement.idBanque = banque.idBanque " +
						" AND banque.libelle ='"+ oldValue+"'";	
				ResultSet resultat = instruction0.executeQuery(req);
				
				while(resultat.next()) 
				{		
					res = resultat.getString("nombre");
				}		
			}catch (Exception e) {
				debug.logger.fatal(e.getMessage()+e.getLocalizedMessage() +  e.getMessage() + e.getCause());
			}
			
			
			if( res.equalsIgnoreCase("0"))
			{
				try	{
					oldValue= FonctionCommun.ajouterAntislash(oldValue);
					String req= "DELETE  from  banque  " +		 		
				 		" WHERE libelle  ='" + oldValue+"'" ;		
					debug.logger.debug("supression banque  :\n"+req);			
					int resultat = instruction.executeUpdate(req);
				}catch (Exception e) {
					debug.logger.fatal(e.getMessage()+e.getLocalizedMessage() +  e.getMessage() + e.getCause());
				}
			finally {
					instruction.close();
					instruction0.close();
				}
			}
			else
			{
				debug.logger.fatal("Impossible de supprimer cette banque il y a une reference deja");
			}
				
			
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage()+e.getLocalizedMessage() +  e.getMessage() + e.getCause());
		}
	
	}
	
	
	
	public void supprimerProfesion(String oldValue ) {
		try 
		{
			
			Statement instruction0 = con.connexion.createStatement();
			Statement instruction = con.connexion.createStatement();
			String res = "0";
			try
			
			{
				oldValue= FonctionCommun.ajouterAntislash(oldValue);
				String req= "SELECT count(redevable.numeroProfession) as nombre " +
						" FROM redevable , activiteprofession " +
						" WHERE redevable.numeroProfession = activiteprofession.code " +
						" AND activiteprofession.libelle ='"+ oldValue+"'";	
				ResultSet resultat = instruction0.executeQuery(req);
				
				while(resultat.next()) 
				{		
					res = resultat.getString("nombre");
				}		
			}catch (Exception e) {
				debug.logger.fatal(e.getMessage()+e.getLocalizedMessage() +  e.getMessage() + e.getCause());
			}
			
			
			if( res.equalsIgnoreCase("0"))
			{
				try	{
					oldValue= FonctionCommun.ajouterAntislash(oldValue);
					String req= "DELETE  from activiteprofession " +		 		
				 		" WHERE libelle  ='" + oldValue+"'" ;		
					debug.logger.debug("supression profesion :\n"+req);			
					int resultat = instruction.executeUpdate(req);
				}catch (Exception e) {
					debug.logger.fatal(e.getMessage()+e.getLocalizedMessage() +  e.getMessage() + e.getCause());
				}
			finally {
					instruction.close();
					instruction0.close();
				}
			}
			else
			{
				debug.logger.fatal("Impossible de supprimer cette profesion il y a une reference deja");
			}
				
			
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage()+e.getLocalizedMessage() +  e.getMessage() + e.getCause());
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	public Vector getTousLesMotifsAnnulationFacture() {
		Vector res= new Vector();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try	{
				ResultSet resultat = instruction.executeQuery( "SELECT DISTINCT(valeur) " +
						"FROM parametre WHERE type ='motifAnnulationFacture' ORDER BY  valeur ");
				while(resultat.next()) {		
					res.addElement(resultat.getString("valeur"));					
				}
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
	 
	public Vector getTousLesRaisonSocial() {
		Vector res= new Vector();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {

				String r = "SELECT DISTINCT(valeur) FROM parametre WHERE type ='type_civilite' ORDER BY  valeur ";
				//debug.logger.debug(r);	
				ResultSet resultat = instruction.executeQuery( r);				
				while(resultat.next()) {		
					res.addElement(resultat.getString("valeur"));					
				}
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
	
	
	
	
	public Vector getTousLesProffesion() {
		Vector res= new Vector();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {

				String r = "SELECT DISTINCT(libelle) FROM activiteprofession ORDER BY  libelle ";
				//debug.logger.debug(r);	
				ResultSet resultat = instruction.executeQuery( r);				
				while(resultat.next()) {		
					res.addElement(resultat.getString("libelle"));					
				}
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

	
	
	public Vector getTousLesBanque() {
		Vector res= new Vector();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {

				String r = "SELECT DISTINCT(libelle) FROM banque ORDER BY  libelle ASC ";
				//debug.logger.debug(r);	
				ResultSet resultat = instruction.executeQuery( r);				
				while(resultat.next())
				{		
					res.addElement(resultat.getString("libelle"));				
					
				}
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

	
	
	
	 
	public String getVille() {		 
		String res = "";
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String r = "SELECT ville FROM parametresmairie   ";			
				ResultSet resultat = instruction.executeQuery( r);				
				while(resultat.next()) {		
					res= resultat.getString("ville");	
				}
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
	 
	 public String getRelance() {		 
		String res = "false";
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String r = " SELECT idModule " +
						   " FROM modules " +
						   " WHERE nomModule='relance'  ";			
				ResultSet resultat = instruction.executeQuery( r);				
				while(resultat.next()) {		
					res= "true";
				}
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
	
	public String getSiteWebville() {		 
		String res = "";
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String r = "SELECT siteWeb FROM parametresmairie  ";			
				ResultSet resultat = instruction.executeQuery( r);				
				while(resultat.next()) {		
					res= resultat.getString("siteWeb");					
				}
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
	 
	 
	 

	 public String getCodePotal()
	  {		 
		 String res = "";
		 Statement instruction;
		 try {
				instruction = con.connexion.createStatement();
			 try {
				String r = "SELECT codePostal  FROM parametresmairie  ";			
				ResultSet resultat = instruction.executeQuery( r);				
				while(resultat.next()) {		
					res= resultat.getString("codePostal");	
				}
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
	//Paul 18/12/2020 pour modue export 
	 public Vector getParemetres(String type) {
			Vector res= new Vector();
			Statement instruction;
			try {
				instruction = con.connexion.createStatement();
				try	{
					ResultSet resultat = instruction.executeQuery( "SELECT * FROM parametre WHERE type ='"+type+"' ORDER BY  valeur ");
					while(resultat.next()) {
						Parametres parametres= new Parametres();
						parametres.setValeur(resultat.getString("valeur"));
						parametres.setLibelle(resultat.getString("libelle"));
						res.addElement(parametres);					
					}
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
}
