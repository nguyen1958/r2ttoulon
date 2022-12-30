package fr.analogon.r2t.request;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.CourierReponse;
import fr.analogon.r2t.pojo.ModeleCourier;
import fr.analogon.r2t.pojo.Reclamation;

public class RequestReclamation  extends Request
{

	public int ajouterReclamation(String  dateReclamation,
			String  natureReclamation,int idRedevable,String typeReception,int idFacture,
			 String textReclamation, String  etat,
			 String typeTaxe,String controleEffectue, String listeOuvrage )
	
	{
		dateReclamation= FonctionCommun.ajouterAntislash(dateReclamation);
		natureReclamation=FonctionCommun.ajouterAntislash(natureReclamation);
		textReclamation=FonctionCommun.ajouterAntislash(textReclamation);
		typeTaxe=FonctionCommun.ajouterAntislash(typeTaxe);		
		typeReception=FonctionCommun.ajouterAntislash(typeReception);
		 int res=0;		 
		 try {
			 Statement instruction = con.connexion.createStatement();
			 try {
				String req= "INSERT INTO reclamation" +
							"(dateReclamation,natureReclamation, idRedevable," +
							" typeReception, idFacture, textReclamation, etat,typeTaxe,controleEffectue)" +
							" VALUES('"+dateReclamation+ "','"+
							natureReclamation+"',"+
							idRedevable+",'"+
							typeReception+"',"+
							idFacture+",'"+				
							textReclamation+"','ENCOURS','"+					
							typeTaxe+"','NON')";
				debug.logger.debug(req);
				int result1= instruction.executeUpdate(req);
				//Recuperation de l'identifiant de la reclamtion			
				String r1= " SELECT reclamation.idReclamation  FROM reclamation" + 
				   " WHERE  dateReclamation='" + dateReclamation +"'"+
				   " AND natureReclamation='" + natureReclamation +"'" +
				   " AND idRedevable=" + idRedevable +
				   " AND typeReception='" + typeReception +"'" +
				   " AND idFacture=" + idFacture +
				   " AND textReclamation='" + textReclamation +"'"+
				   " AND etat='ENCOURS'" +
				   " AND typeTaxe='" + typeTaxe +"'" +
				   " AND controleEffectue='NON'";				
				debug.logger.debug(r1);			
				int idReclamation=0;
				ResultSet resultat2 = instruction.executeQuery(r1);
				while(resultat2.next())
				{
					idReclamation= resultat2.getInt("idReclamation");
					res=idReclamation;
				}
				debug.logger.debug("Id reclamation= "+idReclamation);
				debug.logger.debug("La liste des ouvrages= "+listeOuvrage);
				
				int idOuvrage=0;
				int numPeriode=0;
				StringTokenizer st = new StringTokenizer(listeOuvrage,";");
				
				while (st.hasMoreElements()) 
				{
					idOuvrage= Integer.parseInt((String)st.nextElement());
					//numPeriode= Integer.parseInt((String)st.nextElement());
					debug.logger.debug("Add Reclamtion_article relie a l'ouvrage"+idOuvrage);
					String req2= "INSERT INTO reclamation_article " +
							"( idReclamation , idArticle, numPeriode) " +
					" VALUES("+ idReclamation +","+ idOuvrage+","+ numPeriode +")";
					debug.logger.debug(req2);
					int result2= instruction.executeUpdate(req2);
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
	
	public String majReclamation(int idReclamation ,String  dateReclamation,
			String  natureReclamation,int idRedevable,String typeReception,int idFacture,
			String textReclamation, String  etat, String typeTaxe,
			String reponseReclamation,String dateReponseReclamation
			,String controleEffectue,String dateContole, 
			String listeDesOuvrages, String numControleur)
	{
		dateReclamation=FonctionCommun.ajouterAntislash(dateReclamation);
		natureReclamation=FonctionCommun.ajouterAntislash(natureReclamation);
		textReclamation=FonctionCommun.ajouterAntislash(textReclamation);
		typeTaxe=FonctionCommun.ajouterAntislash(typeTaxe);
		reponseReclamation=FonctionCommun.ajouterAntislash(reponseReclamation);
		dateReponseReclamation=FonctionCommun.ajouterAntislash(dateReponseReclamation);
		numControleur=FonctionCommun.ajouterAntislash(numControleur);
		typeReception=FonctionCommun.ajouterAntislash(typeReception);
		try {
			Statement instruction = con.connexion.createStatement();
			try 
			{
							
				String req= "update reclamation set  reclamation.dateReclamation='"+ dateReclamation+"'," 
				+"idRedevable="+idRedevable
				+",idFacture="+idFacture
				+",natureReclamation='"+natureReclamation			
				+"',typeReception='"+typeReception		
				+"',textReclamation='"+textReclamation
				+"',etat='"+etat
				+"',controleEffectue='"+controleEffectue			
				+"',reponseReclamation='"+reponseReclamation
				+"',dateReponse='"+dateReponseReclamation
				+"',typeTaxe='"+typeTaxe
				+"',dateControle='"+dateContole+"'"
				+",idAgentControl="+numControleur
				+" WHERE idReclamation="+idReclamation;									
	
				debug.logger.debug(req);	
				int result1= instruction.executeUpdate(req);						
			}catch (Exception e) {
				 debug.logger.fatal(e.getMessage()); 
			}finally {
				instruction.close();
			}
		}catch (Exception e) {
			 debug.logger.fatal(e.getMessage());
		}  
		 return String.valueOf(idReclamation);
	}
	
	public void supprimerReclamation(int idReclamation ) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req= "delete from reclamation WHERE idReclamation="+idReclamation;
				debug.logger.debug(req);	
				int result1= instruction.executeUpdate(req);						
			}catch (Exception e) {
				debug.logger.fatal(e.getMessage()); 
			}finally {
				instruction.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}
	
	public void supprimerModeleCourier(int idModeleCourier ) {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req= "delete from  modelecourier WHERE idModeleCourier="+idModeleCourier;
				debug.logger.debug(req);	
				int result1= instruction.executeUpdate(req);						
			}catch (Exception e) {
				debug.logger.fatal(e.getMessage()); 
			}finally {
				instruction.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}
	
	public Vector getListeReclamations(String etatReclamation,String numeroFacture,
			String typeTaxe,String numeroRedevable,
			String dateCreationReclamation,String controleEffectue,String nomRedevable)
	{
	Vector params=new Vector();		
		try {
			Statement instruction = con.connexion.createStatement();
			try 
			{
				String r1= " SELECT reclamation.*,redevable.nomRedevable,redevable.prenom,redevable.civilite " +						  
						   " FROM reclamation,redevable " +
						   " WHERE reclamation.idRedevable=redevable.numRedevable";
			   
				if (etatReclamation.length()!=0 && !etatReclamation.equalsIgnoreCase("TOUS") )
								r1=r1+ " AND reclamation.etat='"+etatReclamation+"'" ;
			   
				if (numeroFacture.length()!=0)
					r1=r1+ " AND reclamation.idFacture ="+ numeroFacture;			
				
				if (numeroRedevable.length()!=0)
					r1=r1+ " AND reclamation.idRedevable ="+ numeroRedevable;
				
				if (typeTaxe.length()!=0)
					r1=r1+ " AND reclamation.typeTaxe ='"+ typeTaxe+"'";
				
				if (dateCreationReclamation.length()!=0)
					r1=r1+ " AND reclamation.dateReclamation ='"+dateCreationReclamation+"'";
				
				if (controleEffectue.equalsIgnoreCase("OUI") || controleEffectue.equalsIgnoreCase("NON") ) 
					r1=r1+ " AND reclamation.controleEffectue ='"+controleEffectue+"'";			
				
				if (nomRedevable.length()!=0)				
					r1=r1+ " AND CONCAT(redevable.nomRedevable ,' ',redevable.prenom) like '%"+nomRedevable+"%'" ;						
									
				r1=r1+ " ORDER BY  reclamation.etat ASC , reclamation.idReclamation DESC ";
				
				debug.logger.debug(r1);	
				ResultSet resultat1 = instruction.executeQuery(r1);			
				while(resultat1.next())
				{
					Reclamation reclamation = new Reclamation();
					reclamation.setIdReclamation(resultat1.getInt("idReclamation"));				
					reclamation.setDateReclamation(resultat1.getString("dateReclamation"));
					reclamation.setTypeTaxe(resultat1.getString("typeTaxe"));
					reclamation.setIdRedevable(resultat1.getInt("idRedevable"));			
					reclamation.setDateReponse(resultat1.getString("dateReponse"));
					reclamation.setTexteReponse(resultat1.getString("reponseReclamation"));				
					reclamation.setEtatReclamation(resultat1.getString("etat"));
					reclamation.setTypeReception(resultat1.getString("typeReception"));
					reclamation.setnatureReclamation(resultat1.getString("natureReclamation"));
					reclamation.setTextReclamation(resultat1.getString("textReclamation"));
					reclamation.setDateReponse(resultat1.getString("dateReponse"));
					reclamation.setIdFacture(resultat1.getInt("idFacture"));
					reclamation.setNomRedevable(resultat1.getString("nomRedevable"));
					reclamation.setPrenomRedevable(resultat1.getString("prenom"));
					reclamation.setCiviliteRedevable(resultat1.getString("civilite"));
					
					params.addElement(reclamation);				
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
	
	public Vector getReclamation(int idReclamation)
	{
	Vector params=new Vector();	
		try {
			 Statement instruction = con.connexion.createStatement();
			 Statement instruction1 = con.connexion.createStatement();
			try { 
					String r= " SELECT reclamation.idAgentControl" +
							   " FROM reclamation " +
							   " WHERE reclamation.idReclamation="+idReclamation ;				 			
					debug.logger.debug(r);	
					ResultSet resultat = instruction.executeQuery(r);
					int idAgentControl=0;
					while(resultat.next())
					{					
						idAgentControl = resultat.getInt("idAgentControl");									
					}		 
				    String r1= " SELECT reclamation.*," +										   
						   " CONCAT(redevable.nomRedevable,' ',redevable.prenom) as nomPrenomRedevable  " +
						   " FROM reclamation, redevable " +
						   " WHERE reclamation.idReclamation="+idReclamation +					  
						   " AND reclamation.idRedevable=redevable.numRedevable ";
				    
				if (idAgentControl!=0)
					  r1= " SELECT utilisateur.numeroUser ,reclamation.*,CONCAT(utilisateur.nom ,' ',utilisateur.prenom) as nomContoleur, " +										   
					   " CONCAT(redevable.nomRedevable,' ',redevable.prenom) as nomPrenomRedevable  " +
					   " FROM reclamation, utilisateur, redevable " +
					   " WHERE reclamation.idReclamation="+idReclamation +					  
					   " AND reclamation.idRedevable=redevable.numRedevable "+
					   " AND reclamation.idAgentControl=utilisateur.numeroUser";
				
				debug.logger.debug(r1);	
				ResultSet resultat1 = instruction1.executeQuery(r1);			
				while(resultat1.next())
				{
					Reclamation reclamation = new Reclamation();
					reclamation.setIdReclamation(resultat1.getInt("idReclamation"));				
					reclamation.setDateReclamation(resultat1.getString("dateReclamation"));
					reclamation.setTypeTaxe(resultat1.getString("typeTaxe"));
					reclamation.setIdRedevable(resultat1.getInt("idRedevable"));				
					reclamation.setDateReponse(resultat1.getString("dateReponse"));
					reclamation.setTexteReponse(resultat1.getString("reponseReclamation"));
					reclamation.setMotifReclamation(resultat1.getString("natureReclamation"));	
					reclamation.setEtatReclamation(resultat1.getString("etat"));
					reclamation.setTypeReception(resultat1.getString("typeReception"));
					reclamation.setnatureReclamation(resultat1.getString("natureReclamation"));
					reclamation.setTextReclamation(resultat1.getString("textReclamation"));
					reclamation.setDateReponse(resultat1.getString("dateReponse"));
					reclamation.setIdFacture(resultat1.getInt("idFacture"));
					reclamation.setControleEffectue(resultat1.getString("controleEffectue"));
					reclamation.setDateContole(resultat1.getString("dateControle"));
					if (idAgentControl!=0)
					{
					  reclamation.setNomContoleur(resultat1.getString("nomContoleur"));	
					  reclamation.setNumContoleur(String.valueOf(resultat1.getInt("numeroUser")));				
					}
					reclamation.setNomRedevable(resultat1.getString("nomPrenomRedevable"));
					
					
					//Recuperation de la liste des ouvrages						
					String r3= " SELECT DISTINCT(reclamation_article.idArticle), bareme.libelle,article.nom " +
							   " FROM reclamation_article, article ,bareme " +
					           " WHERE reclamation_article.idReclamation =" + idReclamation + 
					           " AND article.id_article= reclamation_article.idArticle " +
					           " AND bareme.code= article.codebareme  ";
					debug.logger.debug(r3);				
					ResultSet resultat3 = instruction.executeQuery(r3);
					String listeouvrage="";
					
					while(resultat3.next())
					{
						String nomOuvrage =resultat3.getString("nom");
						if(nomOuvrage.startsWith("#")) nomOuvrage = "-";
						listeouvrage= listeouvrage+";"+resultat3.getInt("idArticle") + ": "+
						nomOuvrage +
						"  ------(" + resultat3.getString("libelle") +")";
						//"  ------(" + resultat3.getString("libelle") +")" ;
					}
					debug.logger.debug("liste article="+listeouvrage);
					reclamation.setListeOuvrage(listeouvrage);				
					params.addElement(reclamation);				
				}	
			}catch (Exception e) {
				 debug.logger.fatal(e.getMessage()); 
			}finally {
				instruction.close();
				instruction1.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		 return params;
	}
	
	public Vector getReclamationUtilisateur(int idRedevable) {		
		Vector params=new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1= " SELECT *" +										   
						   " FROM reclamation " +
						   " WHERE reclamation.idRedevable="+idRedevable +
						   " ORDER BY etat"	;
				debug.logger.debug(r1);	
				ResultSet resultat1 = instruction.executeQuery(r1);			
				while(resultat1.next())	{
					Reclamation reclamation = new Reclamation();
					reclamation.setIdReclamation(resultat1.getInt("idReclamation"));				
					reclamation.setDateReclamation(resultat1.getString("dateReclamation"));
					reclamation.setTypeTaxe(resultat1.getString("typeTaxe"));
					reclamation.setIdRedevable(resultat1.getInt("idRedevable"));			
					reclamation.setDateReponse(resultat1.getString("dateReponse"));
					reclamation.setTexteReponse(resultat1.getString("reponseReclamation"));				
					reclamation.setEtatReclamation(resultat1.getString("etat"));
					reclamation.setTypeReception(resultat1.getString("typeReception"));
					reclamation.setnatureReclamation(resultat1.getString("natureReclamation"));
					reclamation.setTextReclamation(resultat1.getString("textReclamation"));
					reclamation.setDateReponse(resultat1.getString("dateReponse"));
					reclamation.setIdFacture(resultat1.getInt("idFacture"));
					params.addElement(reclamation);				
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
	
	public Vector getTousLesEtatsReclamation() {
		Vector res= new Vector();
		try {
		Statement instruction = con.connexion.createStatement();;
			try {
				instruction = con.connexion.createStatement();
				ResultSet resultat = instruction.executeQuery("SELECT parametre.valeur FROM parametre" +
						" WHERE parametre.type='etatReclamation'");
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

	public Vector getTousLesNaturesReclamation() {
		Vector res= new Vector();
		try {
			Statement instruction= con.connexion.createStatement();
			try {
				ResultSet resultat = instruction.executeQuery("SELECT parametre.valeur FROM parametre" +
						" WHERE parametre.type='motifAnnulationFacture'");
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
	  
	public Vector getTousLesModlesCouriers() {
		Vector res= new Vector();
		try {
			Statement instruction= con.connexion.createStatement();
			try {
				ResultSet resultat = instruction.executeQuery("SELECT * FROM modelecourier ");			
				while(resultat.next()) {
					ModeleCourier modeleCourier = new ModeleCourier(); 
					modeleCourier.setIdModeleCourier(""+resultat.getInt("idModeleCourier"));
					modeleCourier.setCheminModele(resultat.getString("cheminModele")); 
					modeleCourier.setNomModele(resultat.getString("nomModele"));
					res.addElement(modeleCourier);
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
	  
	public Vector getListeDesCourierReponse ( String idReclamation ) {
		Vector res= new Vector();
		try {
			Statement instruction= con.connexion.createStatement();
			try {
				String req = "" +
				" SELECT historiquecourier.dateCreation,modelecourier.nomModele " +
				" FROM reclamation,historiquecourier, modelecourier  " +
				" WHERE reclamation.idReclamation = historiquecourier.idReclamation" +
				" AND modelecourier.idModeleCourier = historiquecourier.idModele " +
				" AND historiquecourier.idReclamation= "+idReclamation +
				" ORDER BY  historiquecourier.idHistoriqueCourier DESC"; 
				debug.logger.debug(req);
				ResultSet resultat = instruction.executeQuery(req);			
				while(resultat.next()) {
					CourierReponse courierReponse = new CourierReponse();
					courierReponse.setDateCreation(resultat.getString("dateCreation"));
					courierReponse.setNomModele(resultat.getString("nomModele"));
					res.addElement(courierReponse);				
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
	  
	public int ajouterModeleCourier(String nomModele, String  cheminModele ) {
		nomModele= FonctionCommun.ajouterAntislash(nomModele);
		cheminModele = FonctionCommun.ajouterAntislash(cheminModele);			
		int res=0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req= "INSERT INTO modelecourier" +
							"(nomModele,cheminModele)" +						
							" VALUES('"+nomModele+ "','"+cheminModele+"')" ;
				debug.logger.debug(req);
				int result1= instruction.executeUpdate(req);
				//Recuperation de l'identifiant du modele			
				String r1= " SELECT idModeleCourier  FROM modelecourier" + 
					   " WHERE  nomModele='" + nomModele +"'"+
					   " AND cheminModele='" + cheminModele +"'" ;				
				debug.logger.debug(r1);			
				int idModeleCourier=0;
				ResultSet resultat2 = instruction.executeQuery(r1);
				while(resultat2.next())	{
					idModeleCourier= resultat2.getInt("idModeleCourier");
					res=idModeleCourier;
				}
				new RequestAdmin().InsertAction("Ajouter un nouveau modele de courier : " + nomModele , GestionDate.getDateTime());
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
	  
	public ModeleCourier getModeleCourier(String idModeleCourier ) {
		int res=0;
		ModeleCourier modeleCourier = new ModeleCourier();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				//Recuperation de l'identifiant de la reclamtion			
				String r1= " SELECT * FROM modelecourier" + 
						   " WHERE  idModeleCourier='" + idModeleCourier +"'"; 					   	
				debug.logger.debug(r1);			
						
				ResultSet resultat2 = instruction.executeQuery(r1);
				while(resultat2.next()) {
					modeleCourier.setIdModeleCourier(""+resultat2.getInt("idModeleCourier"));
					modeleCourier.setCheminModele(resultat2.getString("cheminModele"));
					modeleCourier.setNomModele(resultat2.getString("nomModele"));
				}				
			}catch (Exception e) {	
				 debug.logger.fatal(e.getMessage()); 
			}finally {
				instruction.close();				
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return modeleCourier;
	}
	  
	public Vector getListeDesModeleCourier(String nomModeleCourier ) {
		Vector res = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				//Recuperation de l'identifiant de la reclamtion			
				String r1= " SELECT * FROM modelecourier  ";
				if(nomModeleCourier.length()!=0)
					r1 = r1 + " WHERE nomModele LIKE '%" + nomModeleCourier+ "%'";
				r1 = r1 + " order by idModeleCourier DESC ";
				debug.logger.debug(r1);			
				ResultSet resultat2 = instruction.executeQuery(r1);
				while(resultat2.next()) {
					ModeleCourier modeleCourier = new ModeleCourier();
					modeleCourier.setIdModeleCourier(""+resultat2.getInt("idModeleCourier"));
					modeleCourier.setCheminModele(resultat2.getString("cheminModele"));
					modeleCourier.setNomModele(resultat2.getString("nomModele"));
					res.addElement(modeleCourier);
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
	  
	public void majModeleCourier( String idModeleCourier, String nomModeleCourier, String cheminModeleCourier)
	{
			nomModeleCourier=FonctionCommun.ajouterAntislash(nomModeleCourier);
			cheminModeleCourier=FonctionCommun.ajouterAntislash(cheminModeleCourier);
			try {
				Statement instruction = con.connexion.createStatement();
				try {
					String req= "update modelecourier set nomModele='"+ nomModeleCourier+"'," 
					+" cheminModele='"+cheminModeleCourier +"'"							
					+" WHERE idModeleCourier="+idModeleCourier;									
					debug.logger.debug(req);	
					int result1= instruction.executeUpdate(req);						
				}catch (Exception e) {
					 debug.logger.fatal(e.getMessage()); 
				}finally {
					instruction.close();
				}
			}catch (Exception e) {
				 debug.logger.fatal(e.getMessage());
			}
	}
	  
	public String  ModelePeutEtreSuuprimer(String idModeleCourier) {	
		String res= "true";
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req= " SELECT * from historiquecourier " +
						    " WHERE idModele='" +idModeleCourier + "'"   ; 
				debug.logger.debug(req);					
				ResultSet resultat2 = instruction.executeQuery(req);
				while(resultat2.next())	{
					res= "false";						
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

	public void ajouterCourierReponseReclamation(String idReclamation, String idModeleCourier )
	{
		try {
			Statement instruction = con.connexion.createStatement();				
			try {
				String req= "INSERT INTO historiquecourier" +
							"(idReclamation,idModele,dateCreation)" +						
							" VALUES" +
							"(" +
							""+idReclamation+ "," +
							""+idModeleCourier+"," +
							"'"+GestionDate.getDateAujourdhuiString()+ " a "
							+	GestionDate.getTimeNow() +"'" +
							")" ;
				debug.logger.debug(req);
				int result1= instruction.executeUpdate(req);
				new RequestAdmin().InsertAction("un nouveau courier est cree pour la " +
							" reclamation numero " + idReclamation + " avec le modele numero "+ idModeleCourier , 
							GestionDate.getDateAujourdhuiString() +")");
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
