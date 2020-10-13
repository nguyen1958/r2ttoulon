package fr.analogon.r2t.request;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.pojo.GroupeTaxe;
import fr.analogon.r2t.pojo.GroupeTaxe;

public class RequestGroupeTaxe  extends Request
{
	
	public GroupeTaxe getGroupeTaxe( String idGroupe ) {
		GroupeTaxe res = new GroupeTaxe();
		String listeDesTypesDeTaxeLiee="";
		String listeLibelleDesTypesDeTaxeAutorise="";
		Statement instruction =null;
		try {
			try	{
				instruction = con.connexion.createStatement();				
				String requete = "SELECT *  FROM GroupeTaxe WHERE idGroupe="+idGroupe;
				ResultSet resultat = instruction.executeQuery(requete);				
				while(resultat.next())
				{
					res.setIdGroupe(resultat.getString("idGroupe"));
					res.setLibelle(resultat.getString("libelle"));						
					//Recheche des types de taxe liés :				
					requete = " SELECT * from regrouperTaxe where idGroupe=" + idGroupe;
					debug.logger.debug(requete);
					ResultSet resultatTypeTaxe = null;
					Statement instruction2 = con.connexion.createStatement();
					try {
						resultatTypeTaxe = instruction2.executeQuery(requete);
					}catch (Exception e) {
						debug.logger.fatal("Erreur "+e.getMessage()+e.getCause());
					}
					
					while(resultatTypeTaxe.next())
					{
						listeDesTypesDeTaxeLiee=listeDesTypesDeTaxeLiee+ resultatTypeTaxe.getString("idImputation") +";";
					}
					debug.logger.debug("Code Taxe lié ="+listeDesTypesDeTaxeLiee);
					res.setListeDesTypesDeTaxeLiee(listeDesTypesDeTaxeLiee);
					
				}
			}catch (Exception e) {		
				debug.logger.fatal("Erreur "+e.getMessage()+e.getCause());
				e.printStackTrace();
			}finally {
				instruction.close();	
			}
		}catch (Exception e) {
			debug.logger.fatal("Erreur "+e.getMessage()+e.getCause());
		}
		return res;
	}
	
	public Vector getListeGroupeTaxe(String groupe)
	{
		Vector liste=new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try  {
				groupe = FonctionCommun.ajouterAntislash(groupe);		 			 	
				String r1= " SELECT *  FROM groupeTaxe " +
						   " WHERE libelle like '%"+ groupe +"%' ";					  
				debug.logger.debug(r1);	
				ResultSet resultat = instruction.executeQuery(r1);			
				while(resultat.next())	{
					GroupeTaxe groupeTaxe = new GroupeTaxe();
					groupeTaxe.setIdGroupe(resultat.getString("idGroupe"));
					groupeTaxe.setLibelle(resultat.getString("libelle"));			
					liste.addElement(groupeTaxe);				
				}		
			}catch (Exception e) {
				 debug.logger.fatal(e.getMessage()); 
			}finally {
				instruction.close();				
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return liste;
	}
	
	public Vector getListeGroupeTaxe()
	{
		Vector liste=new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try  {	 			 	
				String r1= " SELECT *  FROM groupeTaxe ";					  
				debug.logger.debug(r1);	
				ResultSet resultat = instruction.executeQuery(r1);			
				while(resultat.next())	{
					GroupeTaxe groupeTaxe = new GroupeTaxe();
					groupeTaxe.setIdGroupe(resultat.getString("idGroupe"));
					groupeTaxe.setLibelle(resultat.getString("libelle"));			
					liste.addElement(groupeTaxe);				
				}		
			}catch (Exception e) {
				 debug.logger.fatal(e.getMessage()); 
			}finally {
				instruction.close();				
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return liste;
	}	
		
	
	 public int ajouterGroupeTaxe(String libelle,String listeDesTypesDeTaxeLiee )
		{
		 	libelle = FonctionCommun.ajouterAntislash(libelle);		 	
			int idNewGroupeTaxe=0;	
			try {
				Statement instruction = con.connexion.createStatement();
				try 
				{
					String req= "INSERT INTO groupeTaxe (libelle) VALUES('"+libelle+ "')";					 			 
					debug.logger.debug("Requete d'insertion d'un nouveau groupeTaxe :\n"+req);					
					instruction.executeUpdate(req);
					//Recuperation de l'identifiant du nouveau groupe taxe:			
					String r2= " SELECT MAX(groupeTaxe.idgroupe) as  id FROM GroupeTaxe";
					debug.logger.debug(r2);										
					ResultSet resultat2 = instruction.executeQuery(r2);
					new RequestAdmin().InsertAction("Ajouter un nouveau groupeTaxe " + libelle , GestionDate.getDateTime());
					while(resultat2.next())
					{
						idNewGroupeTaxe= resultat2.getInt("id");							
					}
							
					//Insertion des taxes groupées
					StringTokenizer st = new StringTokenizer(listeDesTypesDeTaxeLiee,";");
					Statement instruction2 = con.connexion.createStatement();
					try {
						while (st.hasMoreElements()) 
						{
							int idImputation= Integer.parseInt((String)st.nextElement());
							req= "INSERT INTO regrouperTaxe (idGroupe,idImputation)"+
											 " VALUES("+idNewGroupeTaxe+ ","+ idImputation+")";				 
							debug.logger.debug("Requete d'insertion des taxes associées au nouveau groupeTaxe :\n"+req);
							instruction2.executeUpdate(req);
						}
					}catch (Exception e) {
						debug.logger.fatal(e.getMessage());
					}finally {
						instruction2.close();
					}
				}catch (Exception e)  {
					 debug.logger.fatal(e.getMessage()); 
				}finally {
					instruction.close();					
				}
			}catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}
			 return idNewGroupeTaxe;
		}	
	 
	public void modifierGroupeTaxe(String idGroupe, String libelle,String listeDesTypesDeTaxeLiee )
		{	
		int res=0;
		try {
			Statement instruction = con.connexion.createStatement();
			Statement instruction2 = con.connexion.createStatement();
			try {
				libelle = FonctionCommun.ajouterAntislash(libelle);
				String req= "UPDATE groupeTaxe " +			 		
							" set libelle='" + libelle +"'"+			 			
							" WHERE idGroupe="+ idGroupe ;	
				debug.logger.debug("modifier le GroupeTaxe :\n"+req);
				int result1= instruction.executeUpdate(req);	
				//Suppression des anciens groupements
				String req2= "delete from regrouperTaxe WHERE idGroupe="+ idGroupe ;	
				debug.logger.debug(req2);	
				int result2= instruction.executeUpdate(req2);
				//Insertion des taxes groupées	
				StringTokenizer st = new StringTokenizer(listeDesTypesDeTaxeLiee,";");				
				while (st.hasMoreElements()) {
					int idImputation= Integer.parseInt((String)st.nextElement());
					req= "INSERT INTO regrouperTaxe (idGroupe,idImputation)"+
									 " VALUES("+idGroupe+ ","+ idImputation+")";				 
					debug.logger.debug("Requete d'insertion des taxes associées au nouveau groupeTaxe :\n"+req);
					instruction2.executeUpdate(req);				
				}
			}catch (Exception e) {
				 debug.logger.fatal(e.getMessage()); 
			}finally {
				instruction.close();
				instruction2.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}	
	

	public void  supprimerGroupeTaxe(String idGroupe )
	{
		try {
			Statement instruction = con.connexion.createStatement();
			try { 
				String req= "delete groupeTaxe,regrouperTaxe from groupeTaxe,regrouerTaxe "
						  + " WHERE groupeTaxe.idGroupe=regrouperTaxe.idGroupe and "
						  + " groupeTaxe.idgroupe="+idGroupe;
				debug.logger.debug(req);	
				int result1= instruction.executeUpdate(req);	
				new RequestAdmin().InsertAction("Suprresion du groupeTaxe "+ idGroupe, GestionDate.getDateTime());
			}catch (Exception e) {
				debug.logger.fatal(e.getMessage()); 
			}finally {
				instruction.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	} 
		 
}
