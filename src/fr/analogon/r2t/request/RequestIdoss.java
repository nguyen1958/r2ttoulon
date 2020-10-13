package fr.analogon.r2t.request;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.BatchRapprochementIdoss;
import fr.analogon.r2t.pojo.Emplacement;
import fr.analogon.r2t.pojo.LigneIdoss;

public class RequestIdoss  extends Request
{
	RequestEmplacement req = new RequestEmplacement();	
	
	public Vector getListeLigneIdossNonReconnu(String numeroBatch) {
		Vector  res =  new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try	{
				String req1= " SELECT * FROM  rapprochementidoss  WHERE  idEmplacement = 0 " +
							 " AND idBatchRaprocementIdoss = " + numeroBatch  ;	
				debug.logger.debug("Req="+ req1);
				ResultSet resultat = instruction.executeQuery(req1);
				while(resultat.next()) {
					String ligneNonRapproche =resultat.getString("ligneNonRapproche");			
					res.addElement(ligneNonRapproche);
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
	
	public Vector getListeLigneIdoss(String numeroBatch, String type) {
		Vector  res =  new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req1= " SELECT * FROM  rapprochementidoss  " +
							 " WHERE  idEmplacement != 0 " +
							 " AND idBatchRaprocementIdoss = " + numeroBatch  ;	
				if (type.equalsIgnoreCase("valide"))
					req1= req1+" AND rapprochementValide='true'";
				else if (type.equalsIgnoreCase("nonValide"))
					req1= req1+" AND rapprochementValide='false'";
				debug.logger.debug("Req="+ req1);
				ResultSet resultat = instruction.executeQuery(req1);
				while(resultat.next()) {		
					LigneIdoss  ligneIdoss = new LigneIdoss();
					ligneIdoss.setDateDebutAutorisation(resultat.getString("dateDebutAutorisation"));
					ligneIdoss.setDateFinAutorisationIdoss(resultat.getString("dateFinAutorisationIdoss"));
					ligneIdoss.setIdBatchRaprocementIdoss(resultat.getString("idBatchRaprocementIdoss"));
					ligneIdoss.setIdEmplacement(resultat.getString("idEmplacement"));
					ligneIdoss.setIdRapprochementIdoss(resultat.getString("idRapprochementIdoss"));
					ligneIdoss.setInfosIdoss(resultat.getString("infosIdoss"));
					ligneIdoss.setLigneNonRapproche(resultat.getString("ligneNonRapproche"));
					ligneIdoss.setRapprochementTrouve(resultat.getString("rapprochementTrouve"));
					ligneIdoss.setRapprochementValide(resultat.getString("rapprochementValide"));
					ligneIdoss.setTypeInfos(resultat.getString("typeInfos"));
					ligneIdoss.setTypetaxe(resultat.getString("typetaxe"));
					res.addElement(ligneIdoss);
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
	
	public int ajouterLigneBatchRapprochemetnIdoss() {
		int res = 0;
		String date = GestionDate.getDateAujourdhuiString();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req1= " INSERT INTO  batchrapprochementidoss (dateLancement )" +
						  " VALUES(" +
						  "'"+ date +"')" ;		
				debug.logger.debug("Req="+ req1);
				debug.logger.debug("Ajout d'une ligne dans Batch raprochement Idoss  ");
				int result1= instruction.executeUpdate(req1);			
				
				String req2= "SELECT max(idRapprochemenIdoss) as dernierId  from batchrapprochementidoss " ;			
				debug.logger.debug(req2);
				ResultSet resultat = instruction.executeQuery(req2);
				while(resultat.next()) {		
					res = Integer.parseInt(resultat.getString("dernierId"));			
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
	
	
	public void ajouterLigneRapprochemetnIdoss( int idBatchIdoss,
												String typeTaxe,
												String numRue,
												String codeRivolie,
												String nomRedevable,
												String typeInfos,
												String infosIdoss,
												String dateDebut,
												String dateFin,
												String ligne ) {
		infosIdoss = FonctionCommun.ajouterAntislash(infosIdoss);
		ligne = FonctionCommun.ajouterAntislash(ligne);
		//recherche de l'emplacement avec les parametres ( typeTaxe,  numRue , codeRivolie , nomRedevable )
		Emplacement empl = req.getEmplacement(typeTaxe,numRue,codeRivolie,nomRedevable);
		String idEmplacement=empl.getNumEmplacement(); 
		//L"empalcment est trouvé par rapprochement
		DebuggerLog4J.logger.debug("idEmplacement="+idEmplacement);
		String rapprochement="false";
		//L"empalcment est trouvé par rapprochement
		if(! idEmplacement.equalsIgnoreCase("0")) {
			rapprochement ="true";
			String rapprochementValide = "false";
			try {
				Statement instruction = con.connexion.createStatement();
				try {
					String r= " INSERT INTO  rapprochementidoss (idBatchRaprocementIdoss,idEmplacement,typeInfos," +
							  " infosIdoss,dateDebutAutorisation,dateFinAutorisationIdoss, " +
							  " rapprochementValide,rapprochementTrouve,typeTaxe )" +
							  " VALUES(" +
							  "'"+ idBatchIdoss +"',"+
							  "'"+ idEmplacement +"',"+
							  "'"+ typeInfos +"',"+
							  "'"+ infosIdoss +"',"+
							  "'"+ dateDebut +"',"+
							  "'"+ dateFin +"',"+
							  "'"+rapprochementValide+"',"+
							  "'"+rapprochement+"',"+
							  "'"+typeTaxe+"'"+
							  ")" ;		
					debug.logger.debug("Req="+ r);
					debug.logger.debug("Ajout d'une ligne dans raprochement Idoss pour l'emplacment numero "+ idEmplacement);
					int result1= instruction.executeUpdate(r);			
				}catch (Exception e) {			
					debug.logger.fatal(e.getMessage());
				}finally {
					instruction.close();
				}
			}catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}
		}else {
			rapprochement ="false";
			try {
				Statement instruction = con.connexion.createStatement();
				try	{
					String r= " INSERT INTO  rapprochementidoss " +
							  " (idBatchRaprocementIdoss,ligneNonRapproche,rapprochementTrouve,typeInfos,typetaxe )" +
							  " VALUES(" +
							  "'"+ idBatchIdoss +"','"+ ligne +"','"+ rapprochement +"','"+ typeInfos +"','"+ typeTaxe +"')" ;		
					debug.logger.debug("Req="+ r);
					debug.logger.debug("Ajout d'une ligne dans raprochement Idoss , Rapprochement " +
									   " impossible pour la ligne  "+ ligne );
					int result1= instruction.executeUpdate(r);
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
	
	
	public void majInofsIdoss( String typeTaxe, String numRue ,String codeRivolie ,
				String nomRedevable ,String infosIdoss, boolean  memeTraitment )
	{
		infosIdoss = FonctionCommun.ajouterAntislash(infosIdoss);
		//recherche de l'emplacement avec les parametres ( typeTaxe,  numRue , codeRivolie , nomRedevable )
		RequestEmplacement req = new RequestEmplacement();
		Emplacement empl = req.getEmplacement(typeTaxe,numRue,codeRivolie,nomRedevable);
		if(! empl.getNumEmplacement().equalsIgnoreCase("0"))
		{
			debug.logger.debug("Maj infos idoss  de l'emplacment "+ empl.getNumEmplacement());
			String infosIdossEmpalcment = empl.getInfosIdoss();
			//System.out.println("memeTraitment"+memeTraitment);
			if (  memeTraitment &&  infosIdoss.length()!=0) infosIdossEmpalcment="";
			if( infosIdoss.equalsIgnoreCase("null") ) infosIdoss="";			
			if( infosIdossEmpalcment.equalsIgnoreCase("null") ) infosIdossEmpalcment="";				
			if( ! infosIdoss.equalsIgnoreCase("null") )				
				infosIdoss = infosIdossEmpalcment +"\n" + infosIdoss;
			try {
				Statement instruction = con.connexion.createStatement();
				try {
					String r= " UPDATE emplacementgeneral set" +	
					" infosIdoss='" + infosIdoss+"' " +
					" WHERE numero=" + empl.getNumEmplacement();
					debug.logger.debug("Mise a jour emplacement"+r);
					int result1= instruction.executeUpdate(r);			
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
	
	public void majLigneInofsIdoss( Emplacement emplacement ,String infosIdoss ) {
		infosIdoss = FonctionCommun.ajouterAntislash(infosIdoss);	
		RequestEmplacement req = new RequestEmplacement();	
		if(! emplacement.getNumEmplacement().equalsIgnoreCase("0"))	{
			debug.logger.debug("MAJ Ligne Inofs Idoss  pour l'emplacment "+ emplacement.getNumEmplacement());
			try {
				Statement instruction = con.connexion.createStatement();
				try	{
					String r= " UPDATE emplacementgeneral set" +	
							  " infosIdoss='" + infosIdoss+"' " +
							  " WHERE numero=" + emplacement.getNumEmplacement();
					debug.logger.debug("Mise a jour emplacement"+r);
					int result1= instruction.executeUpdate(r);			
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
	
	public void majInfosDate( String typeTaxe, String numRue ,String codeRivolie ,
			String nomRedevable ,String dateMiseEnApplication ,String dateFinAutorisationIdoss )
	{	
		//recherche de l'emplacement avec les parametres ( typeTaxe,  numRue , codeRivolie , nomRedevable )
		RequestEmplacement req = new RequestEmplacement();
		Emplacement empl = req.getEmplacement(typeTaxe,numRue,codeRivolie,nomRedevable);
		//Recuperer le numero de l'empalacement 
		if(! empl.getNumEmplacement().equalsIgnoreCase("0")) {
			debug.logger.debug("Maj date fin autorisation de tous les ouvrage de l'emplacment "+ empl.getNumEmplacement());
			String infosIdossEmpalcment = empl.getInfosIdoss();
			try {
				Statement instruction = con.connexion.createStatement();
				try {
					String r= " UPDATE article, elementfacturation set " +
							" dateFinAutorisationIdoss='"+ dateFinAutorisationIdoss+"'" +
							" WHERE article.id_elementfacturation = elementfacturation.numero" +
							" AND elementfacturation.numeroEmplacement ='" +empl.getNumEmplacement() + "'"+
							" ANd article.DateDebutAutorisation ='"+ dateMiseEnApplication+"'";
					debug.logger.debug("Mise a jour de la date fin autorisation de l'ouvrage "+r);
					int result1= instruction.executeUpdate(r);			
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
	
	public void majInfosDate( Emplacement emplacement ,String dateMiseEnApplication ,String dateFinAutorisationIdoss )
	{	
		//recherche de l'emplacement avec les parametres ( typeTaxe,  numRue , codeRivolie , nomRedevable )
		RequestEmplacement req = new RequestEmplacement();		
		//Recuperer le numero de l'empalacement 
		if(! emplacement.getNumEmplacement().equalsIgnoreCase("0"))
		{
			debug.logger.debug("Maj date fin autorisation de tous les ouvrage de l'emplacment "+ emplacement.getNumEmplacement());
			String infosIdossEmpalcment = emplacement.getInfosIdoss();
			try {
				Statement instruction = con.connexion.createStatement();
				try	{
					String r= " UPDATE article, elementfacturation set " +
							" dateFinAutorisationIdoss='"+ dateFinAutorisationIdoss+"'" +
							" WHERE article.id_elementfacturation = elementfacturation.numero" +
							" AND elementfacturation.numeroEmplacement ='" +emplacement.getNumEmplacement() + "'"+
							" ANd article.DateDebutAutorisation ='"+ dateMiseEnApplication+"'";
					debug.logger.debug("Mise a jour de la date fin autorisation de l'ouvrage \n"+r);
					int result1= instruction.executeUpdate(r);			
				}catch (Exception e) {			
					debug.logger.fatal(e.getMessage());
					e.printStackTrace();
				}finally {
					instruction.close();
				}
			}catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}
		}
	}
	
	public void suuprimerInofsIdoss() {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r= " UPDATE emplacementgeneral set infosIdoss=''" ;		
				debug.logger.debug("Suppresion champs infos idoss "+r);
				int result1= instruction.executeUpdate(r);			
			}catch (Exception e) {			
				debug.logger.fatal(e.getMessage());
			}finally {
				instruction.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
    }
	
	public void suuprimerDteFinAutorisationIoss() {
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r= " UPDATE article set dateFinAutorisationIdoss=''" ;		
				debug.logger.debug("Suppresion champs dateFinAutorisationIdoss "+r);
				int result1= instruction.executeUpdate(r);			
			}catch (Exception e) {			
				debug.logger.fatal(e.getMessage());
			}finally {
				instruction.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}	  
	
	public void majLigneIdoss(String idLigneIdoss)
	{
		LigneIdoss ligneIdoss= new LigneIdoss();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req2= " SELECT * FROM  rapprochementidoss " +
							 " WHERE idRapprochementIdoss="+idLigneIdoss ;			
				debug.logger.debug("Requete=" +req2);
				ResultSet resultat = instruction.executeQuery(req2);
				
				while(resultat.next()) {
					String rapprochementValide= resultat.getString("rapprochementValide");
					String idEmplacement = resultat.getString("idEmplacement");
					String infosIdoss = resultat.getString("infosIdoss");				
					String typeInfos = resultat.getString("typeInfos");
					String dateMiseEnApplication = resultat.getString("dateDebutAutorisation");
					String dateFinAutorisationIdoss = resultat.getString("dateFinAutorisationIdoss");
					Emplacement emplacement = new RequestEmplacement().getEmplacement(idEmplacement);
					String infosIdossEmpalcment = emplacement.getInfosIdoss();
					debug.logger.debug("rapprochementValide="+rapprochementValide);
					debug.logger.debug("typeInfos="+typeInfos);
					String newInfosIdoss="";
					if(rapprochementValide.equalsIgnoreCase("true")) {
						rapprochementValide ="false";
						//ON va supprimer la ligne a l'infos idoss de l'emplacement
						if(typeInfos.equalsIgnoreCase("INFO")) {																	
							newInfosIdoss = infosIdossEmpalcment.replaceAll(infosIdoss+"\n", "");
							newInfosIdoss = newInfosIdoss.replaceAll(infosIdoss, "");						
							majLigneInofsIdoss(emplacement,newInfosIdoss);
						}
						// on va metre a jour les champs fin autorisation idoss de tous les ouvrages dans cet empalcment
						//Valeur=dateFinAutorisationIdoss
						else if (typeInfos.equalsIgnoreCase("DATE")) {
							majInfosDate( emplacement , dateMiseEnApplication , "" );						
						}
					} else if(rapprochementValide.equalsIgnoreCase("false")) {
						rapprochementValide ="true";	
						//ON va ajouter la ligne a l'infos idoss de l'emplacement
						if(typeInfos.equalsIgnoreCase("INFO")) {						
							if(infosIdossEmpalcment !=null && infosIdossEmpalcment.length()!=0)
								newInfosIdoss = infosIdossEmpalcment +"\n" + infosIdoss;
							else
								newInfosIdoss = infosIdossEmpalcment  + infosIdoss;
							majLigneInofsIdoss(emplacement,newInfosIdoss);
						}
						//on va metre a jour les champs fin autorisation idoss de tous les ouvrages dans cet empalcment
						//Valeur=Chaine vide
						else {
							majInfosDate( emplacement , dateMiseEnApplication , dateFinAutorisationIdoss );
						}					
					}
					String r= " UPDATE rapprochementidoss set" +	
					" rapprochementValide='"+rapprochementValide+"'" +
					" WHERE idRapprochementIdoss="+idLigneIdoss ;
					debug.logger.debug(""+r);
					Statement instruction1 = con.connexion.createStatement();
					int result1= instruction1.executeUpdate(r);								
				}	
			}catch (Exception e) {			
				debug.logger.fatal(e.getMessage());
				e.printStackTrace();
			}finally {
				instruction.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}
	
	
	public void selectionnerTousLesRaprochementIdoss(String idBatchIdoss,boolean valide)
	{
		LigneIdoss ligneIdoss= new LigneIdoss();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req2= " SELECT * FROM  rapprochementidoss,batchrapprochementidoss " +
				 " WHERE rapprochementValide ='"+ valide+"'" +
				 " AND rapprochementidoss.idBatchRaprocementIdoss = batchrapprochementidoss.idRapprochemenIdoss" +
				 " AND batchrapprochementidoss.idRapprochemenIdoss="+ idBatchIdoss ;			
				debug.logger.debug("Requete=" +req2);
				ResultSet resultat = instruction.executeQuery(req2);
				
				while(resultat.next())
				{
					String typeInfos = resultat.getString("typeInfos");
					int idEmplacement= resultat.getInt("idEmplacement");
					String idLigneIdoss = ""+resultat.getInt("idRapprochementIdoss");
					if (idEmplacement != 0)
					{
						majLigneIdoss(idLigneIdoss);				
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
	}		
	
	
	
	public Vector getListeBatchIdoss(String dateAction)
	{
		Vector res = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try	{
				String req2= " SELECT * FROM  batchrapprochementidoss " ;
				if(dateAction.length()!=0)
					req2= req2 + " WHERE dateLancement='" + dateAction+"'";
				req2 = req2 + "	order by idRapprochemenIdoss DESC" ;			
				debug.logger.debug("Requete=" +req2);
				ResultSet resultat = instruction.executeQuery(req2);
				
				while(resultat.next())
				{
					BatchRapprochementIdoss batchRapprochementIdoss = new BatchRapprochementIdoss();
					batchRapprochementIdoss.setDateLancement(resultat.getString("dateLancement"));
					batchRapprochementIdoss.setIdRapprochemenIdoss(resultat.getString("idRapprochemenIdoss"));
					batchRapprochementIdoss.setNombreDeLigneLu(resultat.getString("nombreDeLigneLu"));
					batchRapprochementIdoss.setNombreDeLigneTrouve(resultat.getString("nombreDeLigneTrouve"));
					batchRapprochementIdoss.setNombreDeLigneNonTrouve(resultat.getString("nombreDeLigneNonTrouve"));
					
					res.addElement(batchRapprochementIdoss);							
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
	
	public void majNombreLigne(String idBatchIdoss)
	{
		try {
			Statement instruction = con.connexion.createStatement();
			try	{
				String r= " UPDATE batchrapprochementidoss " +
						  "	set  nombreDeLigneTrouve = (SELECT count(*) from rapprochementidoss " +
						  " 	 WHERE rapprochementidoss.idBatchRaprocementIdoss =" + idBatchIdoss +
						  " 	 AND rapprochementidoss.rapprochementTrouve='true') " +
						  "     WHERE batchrapprochementidoss.idRapprochemenIdoss =" + idBatchIdoss ;		
				debug.logger.debug(" "+r);
				int result1= instruction.executeUpdate(r);
				r= " UPDATE batchrapprochementidoss " +
				  "	set  nombreDeLigneNonTrouve = (SELECT count(*) from rapprochementidoss " +
				  " 	 WHERE rapprochementidoss.idBatchRaprocementIdoss =" + idBatchIdoss +
				  " 	 AND rapprochementidoss.rapprochementTrouve='false') " +
				  "     WHERE batchrapprochementidoss.idRapprochemenIdoss =" + idBatchIdoss ;		
				debug.logger.debug(" "+r);
				result1= instruction.executeUpdate(r);
				r= " UPDATE batchrapprochementidoss " +
				  "	set  nombreDeLigneLu = (SELECT count(*) from rapprochementidoss " +
				  " 	 WHERE rapprochementidoss.idBatchRaprocementIdoss =" + idBatchIdoss +")" + 
				  "     WHERE batchrapprochementidoss.idRapprochemenIdoss =" + idBatchIdoss ;
				 		
				debug.logger.debug(" "+r);
				result1= instruction.executeUpdate(r);
			}catch (Exception e) {			
				debug.logger.fatal(e.getMessage());
			}finally {
				instruction.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}	  
	//majNombreLigneRapproche(idBatchIdoss);
}
