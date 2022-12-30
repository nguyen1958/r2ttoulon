package fr.analogon.r2t.request;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.pojo.Utilisateur;

public class RequestUtilisateur  extends Request
{	
	public Vector getListeUtilisateur(String codeUtilisateur,
			String nomUtilisateur,	String prenomUtilisateur,	
			String typeUtilisateur)
	{
		Vector params=new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try  {
				nomUtilisateur = FonctionCommun.ajouterAntislash(nomUtilisateur);		 	
				prenomUtilisateur = FonctionCommun.ajouterAntislash(prenomUtilisateur);			 	
				String r1= " SELECT *  FROM utilisateur " +
						   " WHERE nom like '%"+ nomUtilisateur +"%' " +					  
						   " AND prenom like '%"+ prenomUtilisateur +"%' " +
						   " AND numeroUser like '%"+ codeUtilisateur +"%'"+					   
				  		   " AND ensembleUtilisateur like '%"+ typeUtilisateur +"%'"+
						   " ORDER BY nom";
				debug.logger.debug(r1);	
				ResultSet resultat1 = instruction.executeQuery(r1);			
				while(resultat1.next())	{
					Utilisateur utulisateur = new Utilisateur();
					utulisateur.setNom(resultat1.getString("nom"));
					utulisateur.setPrenom(resultat1.getString("prenom"));				
					utulisateur.setTypeUtilisateur(resultat1.getString("ensembleUtilisateur"));
					utulisateur.setId(String.valueOf(resultat1.getInt("numeroUser")));				
					params.addElement(utulisateur);				
				}		
			}catch (Exception e) {
				 debug.logger.fatal(e.getMessage()); 
			}finally {
				instruction.close();				
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return params;
	}
	
	public Vector getListeTypeUtilisateur() {
		Vector res= new Vector();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try	{
				ResultSet resultat = instruction.executeQuery("SELECT valeur" +
					" FROM parametre WHERE type='type_utilisateur' ");
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
		
	public Utilisateur getUtilisateur( String iduser , String login, String pwd  ) {
		Utilisateur res = new Utilisateur();
		Statement instruction =null;
		try {
			try	{
				instruction = con.connexion.createStatement();
				if   (   instruction==null ||this.con == null 
						|| this.con.connexion  == null
						|| con.connexion.isClosed()					 
						|| ! valide(instruction)
					)			
				{
					debug.logger.debug("La connexion a la BD est fermé ");
					InitialisationConnexionLectureConfiguration parametreDeConnexion =
						 new  InitialisationConnexionLectureConfiguration();
					con.connexion = parametreDeConnexion.getConnexion().connexion ;	
					instruction = con.connexion.createStatement();
				}
				String requete = "SELECT *  FROM utilisateur WHERE numerouser= numerouser ";
				if (iduser.length() != 0) 	requete = requete + "AND numerouser= '"+ iduser +"'";
				if (login.length() != 0) 	requete = requete + "AND login= '"+ login +"'";
				if (pwd.length() != 0) 	requete = requete + "AND password= '"+ pwd +"'";
				ResultSet resultat = instruction.executeQuery(requete);
				
				while(resultat.next())
				{
					new RequestAdmin().InsertAction("Connexion de l utilisateur " +
							" "+ resultat.getString("nom") + " " + resultat.getString("prenom"), GestionDate.getDateTime());
					res.setId(""+resultat.getInt("numeroUser"));				
					res.setNom(resultat.getString("nom"));
					res.setPrenom(resultat.getString("prenom"));
					res.setLogin(resultat.getString("login"));
					res.setPassword(resultat.getString("password"));				
					res.setValide(resultat.getString("valide"));				
					res.setRemarque(resultat.getString("remarque"));
					res.setTypeUtilisateur(resultat.getString("ensembleUtilisateur"));
					
					
					//Recheche des types de taxe autorisé :				
					requete = " SELECT DISTINCT(imputation.idImputation),imputation.libelle" +
							  " FROM imputation,droitacces " +
							  " WHERE imputation.idImputation =  droitacces.idImputation " +
							  " AND droitacces.idUtilisateur="+resultat.getInt("numeroUser");
					debug.logger.debug(requete);
					ResultSet resultatTypeTaxe = null;
					Statement instruction2 = con.connexion.createStatement();
					try {
						resultatTypeTaxe = instruction2.executeQuery(requete);
					}catch (Exception e) {
						debug.logger.fatal("Erreur "+e.getMessage()+e.getCause());
					}
					String listeDesTypesDeTaxeAutorise="";
					String listeLibelleDesTypesDeTaxeAutorise="";
					while(resultatTypeTaxe.next())
					{
						listeDesTypesDeTaxeAutorise=listeDesTypesDeTaxeAutorise+ resultatTypeTaxe.getString("idImputation") +";";
						listeLibelleDesTypesDeTaxeAutorise=listeLibelleDesTypesDeTaxeAutorise+ resultatTypeTaxe.getString("libelle") +";";
					}
					debug.logger.debug("Code Taxe Autoise="+listeDesTypesDeTaxeAutorise);
					res.setListeDesTypesDeTaxeAutorise(listeDesTypesDeTaxeAutorise);
					
					debug.logger.debug("Libelle Taxe Autoise="+listeLibelleDesTypesDeTaxeAutorise);
					res.setListeLibelleDesTypesDeTaxeAutorise(listeLibelleDesTypesDeTaxeAutorise);
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
	
	public boolean peutEtreSupprimer( String idRedevable )
	{
		boolean res = true;
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try	{
				String requete = "SELECT id_controleur  FROM alerte  WHERE id_controleur= "+ idRedevable;
				ResultSet resultat = instruction.executeQuery(requete);
				while(resultat.next()) {
					res =false;
				}
				if(res)	{
					instruction = con.connexion.createStatement();
					requete = "SELECT idAgentControl  FROM reclamation  WHERE idAgentControl= "+  idRedevable;
					resultat = instruction.executeQuery(requete);
					while(resultat.next())	{
						res =false;
					}
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
	
	 public int ajouterUtilisateur(String nomUtilisateur,String prenomUtilisateur, String loginUtilisateur, String motDePaseeUtilisateur, String 
			 actifUtilisateur,  String typeUtilisateur, 
			 String remarqueUtilisateur , String listeDesTypesDeTaxeAutorise )
		{
		 	remarqueUtilisateur = FonctionCommun.ajouterAntislash(remarqueUtilisateur);
		 	nomUtilisateur = FonctionCommun.ajouterAntislash(nomUtilisateur);		 	
		 	prenomUtilisateur = FonctionCommun.ajouterAntislash(prenomUtilisateur);
			int idNewUtilisateur=0;	
			try {
				Statement instruction = con.connexion.createStatement();
				try 
				{
					String req= "INSERT INTO `utilisateur` (nom,prenom,login,password" +
					 		",valide,ensembleUtilisateur,remarque)"+
							 " VALUES('"+nomUtilisateur+ "','"+						 
							 prenomUtilisateur+"','"+
							 loginUtilisateur+"','"+
							 motDePaseeUtilisateur+"','"+
							 actifUtilisateur+"','"+						 
							 typeUtilisateur+"','"+
							 remarqueUtilisateur+"')";				 
					debug.logger.debug("Requete d'insertion d'un nouveau utulisateur :\n"+req);					
					instruction.executeUpdate(req);
					instruction.executeUpdate("commit;");
					//Recuperation de l'identifiant du nouveau user:			
							String r2= " SELECT MAX(utilisateur.numeroUser) as  numeroUser " +
							   " FROM utilisateur WHERE " +
							   " nom ='" + nomUtilisateur +"'"+
							   " AND prenom='" + prenomUtilisateur +"'"+
							   " AND login='" + loginUtilisateur +"'"+
							   " AND password='" +motDePaseeUtilisateur +"'"+
							   " AND valide='" + actifUtilisateur +"'" +						   
							   " AND ensembleUtilisateur='" + typeUtilisateur +"'" +
							   " AND remarque='" + remarqueUtilisateur +"'";
							debug.logger.debug(r2);			
							
							ResultSet resultat2 = instruction.executeQuery(r2);
							new RequestAdmin().InsertAction("Ajouter un nouveau utulisateur " +
									" "+ nomUtilisateur + " " + prenomUtilisateur , GestionDate.getDateTime());
							while(resultat2.next())
							{
								idNewUtilisateur= resultat2.getInt("numeroUser");							
							}
							
					//Insetion des droits d'acces a type de taxe 	
					StringTokenizer st = new StringTokenizer(listeDesTypesDeTaxeAutorise,";");
					Statement instruction2 = con.connexion.createStatement();
					try {
						while (st.hasMoreElements()) 
						{
							int idImputation= Integer.parseInt((String)st.nextElement());
							req= "INSERT INTO `droitacces` (idUtilisateur,idImputation)"+
											 " VALUES('"+idNewUtilisateur+ "','"+						 
											 idImputation+"')";				 
							debug.logger.debug("Requete d'insertion des droits d'acces du nouveau utulisateur :\n"+req);
							instruction2.executeUpdate(req);
							instruction2.executeUpdate("commit;");
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
			 return idNewUtilisateur;
		}	
	 
	public void modifierUtilisateur(String codeUtilisateur, String nomUtilisateur,String prenomUtilisateur, String loginUtilisateur, String motDePaseeUtilisateur, String 
			 actifUtilisateur,  String typeUtilisateur, 
			 String remarqueUtilisateur , String listeDesTypesDeTaxeAutorise )
		{	
		int res=0;
		try {
			Statement instruction = con.connexion.createStatement();
			Statement instruction2 = con.connexion.createStatement();
			try {
				remarqueUtilisateur = FonctionCommun.ajouterAntislash(remarqueUtilisateur);
				nomUtilisateur = FonctionCommun.ajouterAntislash(nomUtilisateur);		 	
				prenomUtilisateur = FonctionCommun.ajouterAntislash(prenomUtilisateur);
								 
				String req= "UPDATE utilisateur " +			 		
							" set login='" + loginUtilisateur +"'"+
					 		" ,password='" + motDePaseeUtilisateur +"'"+
					 		" ,ensembleUtilisateur='" + typeUtilisateur +"'"+
					 		" ,valide='" + actifUtilisateur +"'"+
					 		" ,nom='" + nomUtilisateur +"'"+
					 		" ,prenom='" + prenomUtilisateur +"'"+
					 		" ,remarque='" + remarqueUtilisateur +"'"+			 			
							" WHERE numeroUser='"+ codeUtilisateur +"'";	
				debug.logger.debug("modifier le profil utilisateur :\n"+req);
				int result1= instruction.executeUpdate(req);	
				//Supreesion des anciens droits d'acces a type de taxe
				String req2= "delete from droitacces WHERE idUtilisateur='"+ codeUtilisateur +"';";	
				debug.logger.debug(req2);	
				int result2= instruction.executeUpdate(req2);
				//Insetion des droits d'acces a type de taxe 	
				StringTokenizer st = new StringTokenizer(listeDesTypesDeTaxeAutorise,";");				
				while (st.hasMoreElements()) {
					int idImputation= Integer.parseInt((String)st.nextElement());
			//		Statement instruction2 = con.connexion.createStatement();				 
					req= "INSERT INTO `droitacces` (idUtilisateur,idImputation)"+
									 " VALUES('"+codeUtilisateur+ "','"+						 
									 idImputation+"')";				 
					debug.logger.debug("Requete d'insertion des droits d'acces du nouveau utulisateur :\n"+req);					
					instruction2.executeUpdate(req);
					instruction2.executeUpdate("commit;");
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
	
	public boolean verfifUtilisateur( String login , String password )
	{
		boolean res = false;
		try {
		Statement instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction.executeQuery("SELECT * " +
						" FROM utilisateur WHERE login ='"+ login +"'" +
						" AND password ='"+ password +"'" );
				while(resultat.next()) {
					res =true;									
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

	public void  supprimerUtilisateur(String codeUtilisateur )
	{
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req= "delete from utilisateur WHERE numeroUser='"+ codeUtilisateur +"'";	
				debug.logger.debug(req);	
				int result1= instruction.executeUpdate(req);	
				new RequestAdmin().InsertAction("Suprresion de l'utulisateur "+ codeUtilisateur
				, GestionDate.getDateTime());
			}catch (Exception e) {
				debug.logger.fatal(e.getMessage()); 
			}finally {
				instruction.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	} 
		 
//		Verfier la valide du stattement 
	public boolean valide(Statement statementInput) 	
	{
		boolean valide = true;
		try	{
			String sql = "SELECT count(*) FROM parametre;";		
			ResultSet resultat = statementInput.executeQuery (sql);			
		}catch (Exception e) {
					valide =false;
		}
		return valide;
	}
}
