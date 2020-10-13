package fr.analogon.r2t.request;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

/*
import com.sun.org.apache.bcel.internal.generic.IDIV;
*/

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Banque;
import fr.analogon.r2t.pojo.Facture;
import fr.analogon.r2t.pojo.Payement;
import fr.analogon.r2t.pojo.TypePayement;

public class RequestPayement extends Request
{
	
	public void supprimerPayement(String idPayement ) 
	{
		try {
			Statement instruction = con.connexion.createStatement();
			try 
			{
				String req = "delete from payement WHERE idPayement=" + idPayement ;
				debug.logger.debug(req);
				int result1 = instruction.executeUpdate(req);
				new RequestAdmin().InsertAction("Suprresion du Payement marche numero "
						+ idPayement ,
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
	
	
	public void supprimerLignePayement(String idPayement ) 
	{
		try {
			Statement instruction = con.connexion.createStatement();
			try 
			{
				String req = "delete from lignepayement WHERE idPayement=" + idPayement ;
				debug.logger.debug(req);
				int result1 = instruction.executeUpdate(req);
				new RequestAdmin().InsertAction("Suprresion du lignePayement marche payement numero "
						+ idPayement ,
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
	
	
	public void annulerPayement(String idPayement ,String idUtilisateur)
	{		  
			int res=0;
			DecimalFormat df = new DecimalFormat("0.00");
			try {
				 Statement instruction = con.connexion.createStatement();
				 try 
				 {
					
					 //Recuperation des elements du paiement
					instruction = con.connexion.createStatement();
					 String req= " SELECT lignepayement.idFacture,lignepayement.montantPayement " +
					 			 " FROM lignepayement, payement " +
					 			 " WHERE lignepayement.idPayement="+ idPayement + 
					 			 " AND payement.idPayement=lignepayement.idPayement" +
					 			 " AND payement.etat='valide'" ;						
					 
					 //Recuperation des factures et des montants correspondants			
					ResultSet resultat2 = instruction.executeQuery(req);	
					debug.logger.debug(req);
					while(resultat2.next())
					{
						int idFacture = resultat2.getInt("idFacture");	
						System.out.println("idFacture="+idFacture);
						double montantPayement = resultat2.getDouble("montantPayement");
						RequestFacture requestFacture = new RequestFacture();
						Facture facture = requestFacture.getFactureFromIdFacture(""+idFacture);
						if (idFacture !=0)
						{
							double oldSolde = Double.valueOf(facture.getSoldeFacture());
							//double newSoldeFacture = oldSolde + montantPayement ;
							String newSoldeFacture = df.format(oldSolde + montantPayement) ;
							newSoldeFacture = newSoldeFacture.replaceAll(",", ".");
							requestFacture.updateFacture(""+idFacture , newSoldeFacture );	
						}
						
					}
					
					//maj infos payement
					 String r= " UPDATE payement set etat ='annulle' , excedant='0.00' " +				  
					  		  " WHERE  idPayement ="+ idPayement ;
					 debug.logger.debug(r);
					 
					int result1= instruction.executeUpdate(r);
					String msg =  
							"Annulation du payement numero"+ idPayement
							+ " " + GestionDate.getDateTime() + " par l utilisateur " + idUtilisateur;
							
					debug.logger.debug(msg);
					new RequestAdmin().InsertAction("Annulation du payement numero"+ idPayement
							, GestionDate.getDateTime() + " par l utilisateur " + idUtilisateur);
					
				}catch (Exception e)  {
					 debug.logger.fatal(e.getMessage()); 
				}finally {
					instruction.close();
				}
			}catch (Exception e) {
				 debug.logger.fatal(e.getMessage());
			}
	}
	  
	  public int ajouterPayement( String datePayement, String idTypePayement ,String idBanque, String numeroCheque,
			  String montantPayement, String numeroQuittance, int nombreDeFacturePayee, String excedant,
			  String idTypeDeTaxe,String numeroTransaction)
		{
		  
			int res=0;
			try {
				Statement instruction = con.connexion.createStatement();
				try {
					 String req= "INSERT INTO `payement` (datePayement,  idTypePayement , " +
					 		 " idBanque, numeroCheque, montantPayement, numeroQuittance, "+
							 " nombreDeFacturePayee,excedant,idImputation,numeroTransaction)"+
							 " VALUES('"+datePayement+"'," +
							 ""+idTypePayement + "," +
							 ""+idBanque+ "," +
							 ""+numeroCheque+ "," +
							 ""+montantPayement+ "," +
							 "'"+ numeroQuittance+ "'," +
							 ""+ nombreDeFacturePayee+ ",'" +
							 ""+ excedant + "'," +
							 ""+idTypeDeTaxe +",'" +
					 		 ""+ numeroTransaction + "')" ;
							 				 
					debug.logger.debug("Requete d'insertion d'un nouveau payement :\n"+req);					
					instruction.executeUpdate(req);
					//Recuperation de l'identifiant du nouveau type de taxe			
					String r2= " SELECT max(idPayement) lastIdPayement from `payement`";
					debug.logger.debug(r2);						
					ResultSet resultat2 = instruction.executeQuery(r2);						
					while(resultat2.next()) {
						res= resultat2.getInt("lastIdPayement");							
					}	
			}catch (Exception e)  {
				 debug.logger.fatal(e.getMessage()); 
			}finally {
				instruction.close();					
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return res;
	}
	  
	  
	  //paul ajout numeroTransaction
	  public int modifierPayement( String datePayement, String idTypePayement ,String idBanque, String numeroCheque,
			  String montantPayement, String numeroQuittance, int nombreDeFacturePayee, String excedant,String idTypeDeTaxe,
			  int idPayement,String numeroTransaction)
		{
		  
			int res=0;
			try {
				Statement instruction = con.connexion.createStatement();
				try {
					 String req= "UPDATE payement set  " +
					 		"datePayement='" + datePayement + "',"+
					 		"idTypePayement=" + idTypePayement + ","+
					 		"idBanque=" + idBanque +","+
					 		"numeroCheque=" + numeroCheque +","+
					 		"montantPayement=" + montantPayement +","+
					 		"numeroQuittance=" + numeroQuittance +","+
					 		"nombreDeFacturePayee=" + nombreDeFacturePayee +","+
					 		"excedant=" + excedant +","+
					 		"numeroTransaction="+numeroTransaction+","+
					 		"idImputation="+ idTypeDeTaxe + " WHERE  idPayement=  "+ idPayement ;						 
							 				 
					debug.logger.debug("Requete de maj de  payement :\n"+req);					
					int result1 = instruction.executeUpdate(req);
					
			}catch (Exception e)  {
				 debug.logger.fatal(e.getMessage()); 
			}finally {
				instruction.close();					
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return res;
	}
	  
	  
	  

	public int ajouterLignePayement(String idPayement,String idFacture, String montantPayement ) {
		int res=0;
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String req= "INSERT INTO `lignepayement` (idPayement,idFacture,montantPayement)"+
							 " VALUES("+ idPayement+"," +
							 ""+idFacture + "," +
							 ""+montantPayement+ ")" ;					 
							 				 
				debug.logger.debug("Requete d'insertion d'une nouvelle lige de payement :\n"+req);					
				instruction.executeUpdate(req);
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
	  
	public Vector getTousLesBanques() {
		Vector res= new Vector();		     
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String req= "SELECT * FROM banque order by banque.libelle ";
				ResultSet resultat = instruction.executeQuery( req);
				//debug.logger.debug(""+req);	
				while(resultat.next())
				{		
					Banque banque = new Banque();
					banque.setIdBanque(""+resultat.getInt("idBanque"));
					banque.setLibelle(resultat.getString("libelle"));
					banque.setCode(resultat.getString("code"));
					res.add(banque);
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
	  
	public Vector getTousLesTypePayement() {
		Vector res= new Vector();		     
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction.executeQuery( "SELECT * FROM typepayement");				 
				while(resultat.next()) {
					TypePayement typePayement =new TypePayement();	
					typePayement.setIdTypePayement(resultat.getString("idTypePayement"));
					typePayement.setTypePayement(resultat.getString("typePayement"));
					res.addElement(typePayement);				
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
	
	
	public Vector getTousLesTypePayementMarche() {
		Vector res= new Vector();		     
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction.executeQuery( "SELECT * FROM typepayement WHERE typePayement='Espece' ");				 
				while(resultat.next()) {
					TypePayement typePayement =new TypePayement();	
					typePayement.setIdTypePayement(resultat.getString("idTypePayement"));
					typePayement.setTypePayement(resultat.getString("typePayement"));
					res.addElement(typePayement);				
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
	
	
	public Vector getTypePayementMarche() {
		Vector res= new Vector();		     
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction.executeQuery( "SELECT * FROM typepayement where typepayement ='Espece' ");				 
				while(resultat.next()) {
					TypePayement typePayement =new TypePayement();	
					typePayement.setIdTypePayement(resultat.getString("idTypePayement"));
					typePayement.setTypePayement(resultat.getString("typePayement"));
					res.addElement(typePayement);				
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
	
	
	  
	public int getBanque(String nomBanque) {
		int res= 0;		     
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction.executeQuery( "SELECT * FROM banque Where libelle="+ nomBanque);
				while(resultat.next()) {		
					res= resultat.getInt("code");				
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
	  
	public Banque getBanqueFromId(int  idBanque) {		  		     
		Statement instruction;
		Banque banque = new Banque();
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction.executeQuery( "SELECT * FROM banque Where idBanque="+ idBanque);
				while(resultat.next()) {		
					banque.setCode(resultat.getString("code"));
					banque.setLibelle(resultat.getString("libelle"));
					banque.setIdBanque(""+idBanque);				
				}
			}catch (Exception e) {		
				debug.logger.fatal(e.getMessage());
			}finally {
				instruction.close();				
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		 return banque;
	  }

	public int getTypePayement(String typePayement) {
		int res= 0;		     
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction.executeQuery( "SELECT * FROM typePayement Where typePayement="+typePayement);
				while(resultat.next()) {		
					res= resultat.getInt("idTypePayement");				
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

	public TypePayement getTypePayementFromId ( int idTypePayement) {
		TypePayement typePayement = new TypePayement(); 
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String req = "SELECT * FROM typepayement Where idTypePayement="+idTypePayement;
				ResultSet resultat = instruction.executeQuery( req);
				debug.logger.debug(req);
				while(resultat.next()) {	
					typePayement.setIdTypePayement(""+idTypePayement);
					typePayement.setTypePayement(resultat.getString("typePayement"));				
				}
			}catch (Exception e) {		
				debug.logger.fatal(e.getMessage());
			}finally {
				instruction.close();				
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return typePayement;
	}

	public Payement getPayement(String idPayement) {
		Payement payement = new Payement();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String request = 
					 " SELECT  DISTINCT(numRedevable), payement.* " +
					 " FROM payement, lignepayement, redevable , `facture` " +
					 " Where payement.idPayement= " + idPayement +
					 " AND payement.idPayement = lignepayement.idPayement " +
					 " AND lignepayement.idFacture = facture.idFacture " +
					 " AND facture.idClient = redevable.numRedevable " ;
				
				debug.logger.debug(request);
				ResultSet resultat = instruction.executeQuery( request);
						
				while(resultat.next()) {
					payement.setIdBanque(""+resultat.getInt("idBanque"));
					payement.setIdPayement(""+resultat.getInt("idPayement"));
					payement.setIdTypePayement(""+resultat.getInt("idTypePayement"));
					payement.setMontantPayement(""+resultat.getDouble("montantPayement"));
					payement.setNombreDeFacturePayee(""+resultat.getInt("nombreDeFacturePayee"));
					payement.setNumeroCheque(""+resultat.getInt("numeroCheque"));
					payement.setNumeroQuittance(""+resultat.getInt("numeroQuittance"));				
					payement.setDatePayement(resultat.getString("datePayement"));
					payement.setNumRedevable(""+resultat.getInt("numRedevable"));
					payement.setEtat(resultat.getString("etat"));
					payement.setNumeroTransaction(resultat.getString("numeroTransaction"));
				}
			}catch (Exception e) {		
				debug.logger.fatal(e.getMessage());
			}finally {
				instruction.close();
			}
		}catch (Exception e) {
				debug.logger.fatal(e.getMessage());
		}
		return payement;
	}
	
	
	public Payement getPayementMarche(String idPayement) {
		Payement payement = new Payement();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String request = 
					 " SELECT  payement.* " +
					 " FROM payement  " +
					 " Where payement.idPayement= " + idPayement ;		
				debug.logger.debug(request);
				ResultSet resultat = instruction.executeQuery( request);
						
				while(resultat.next()) {
					payement.setIdBanque(""+resultat.getInt("idBanque"));
					payement.setIdPayement(""+resultat.getInt("idPayement"));
					payement.setIdTypePayement(""+resultat.getInt("idTypePayement"));
					payement.setMontantPayement(""+resultat.getDouble("montantPayement"));
					payement.setNumeroCheque(""+resultat.getInt("numeroCheque"));
					payement.setNumeroQuittance(""+resultat.getInt("numeroQuittance"));				
					payement.setDatePayement(resultat.getString("datePayement"));
					payement.setEtat(resultat.getString("etat"));
					payement.setTypeTaxe(resultat.getString("idImputation"));
					payement.setNumeroTransaction(resultat.getString("numeroTransaction"));
				}
			}catch (Exception e) {		
				debug.logger.fatal(e.getMessage());
			}finally {
				instruction.close();
			}
		}catch (Exception e) {
				debug.logger.fatal(e.getMessage());
		}
		return payement;
	}
	
	
	
	  
	public Vector getListeDesFactureFromPayement(String idPayement) {
		Vector res = new Vector();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				String request = 
					 " SELECT  facture.numeroFacture " +
					 " FROM lignepayement, facture" +
					 " Where facture.idFacture = lignepayement.idFacture" +
					 " AND lignepayement.idPayement= " + idPayement ;
				debug.logger.debug(request);
				ResultSet resultat = instruction.executeQuery( request);
				RequestFacture requestFacture = new RequestFacture();
				while(resultat.next()) {
					res.addElement(""+resultat.getInt("numeroFacture"));
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
	  
	public ArrayList getListeDesPayements(String datePayement, String montantPayement, String nombreFacturePaye,
			  						     String numeroQuittance,String  numeroCheque,
			  						     String  numeroFacture, String idBanque, String idTypePayement,
			  						     String idFacture, String typeTaxe, String etatPaiement,
			  						     String anneePaiement, String numeroTransaction)
	{
		ArrayList res = new ArrayList();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				if (etatPaiement.toLowerCase().contains("annul")) etatPaiement ="annulle";
				
				if (etatPaiement.toLowerCase().contains("tous")) etatPaiement ="";
				String request = 
					 " SELECT DISTINCT(payement.idPayement), payement.* " +
					 " FROM payement,lignepayement " ;
				
				/*
				if ( typeMarche.length()!=0 &&  ! typeMarche.equalsIgnoreCase("0") )				
					request= request +" ,imputation  ";
					*/
				
				if ( typeTaxe.length()!=0 &&  ! typeTaxe.equalsIgnoreCase("0") )				
					request= request +" ,facture  ";
				
				request = request + " WHERE payement.idPayement = lignepayement.idPayement " 	;
				
				if ( typeTaxe.length()!=0 &&  ! typeTaxe.equalsIgnoreCase("0") ) {
					request = request + " AND facture.typeTaxe='"+ typeTaxe+"'" +
										" AND facture.idFacture = lignepayement.idFacture  " ;
				}
				
				if ( idFacture.length()!=0 &&  ! idFacture.equalsIgnoreCase("0") )
					request= request +" AND lignepayement.idFacture='"+ idFacture +"'" ;
				
				if ( etatPaiement.length()!=0 )
					request= request +" AND payement.etat='"+ etatPaiement +"'" ;
				
				if ( datePayement.length()!=0)
					request= request +" AND datePayement='"+ datePayement +"'" ;
	
				
				if ( anneePaiement.length()!=0)
					request= request +" AND datePayement like '%%/%%/"+ anneePaiement  +"'" ;
				
				
				if ( numeroQuittance.length()!=0)
					request= request +" AND numeroQuittance="+ numeroQuittance +"" ;
				
				if ( numeroTransaction.length()!=0)
					request= request +" AND numeroTransaction="+ numeroTransaction +"" ;
				
				
				if ( numeroCheque.length()!=0)
					request= request +" AND numeroCheque="+ numeroCheque +"" ;
				
				if ( ! idBanque.equalsIgnoreCase("0") &&  idBanque.length() !=0 )
					request= request +" AND idBanque="+ idBanque +"" ;
				
				if ( idTypePayement.length() !=0 &&  ! idTypePayement.equalsIgnoreCase("0") )
					request= request +" AND idTypePayement="+ idTypePayement +"" ;
							
				if ( nombreFacturePaye.length()!=0)
					request= request +" AND nombreDeFacturePayee='"+ nombreFacturePaye +"'" ;
				
				if ( montantPayement.length()!=0)
					request = request +" AND payement.montantPayement='"+ montantPayement +"'" ;
			
				/*
				if ( typeMarche.length()!=0 &&  ! typeMarche.equalsIgnoreCase("0") )
				{
					request = request +" AND payement.idImputation = imputation.idImputation " +
							           " AND imputation.libelle = '"+ typeMarche +"' "  ;
				}
				*/
				
				
				request = request + " ORDER BY payement.idPayement DESC";
				debug.logger.debug(request);
				ResultSet resultat = instruction.executeQuery( request);
				RequestFacture requestFacture = new RequestFacture();
				
				while(resultat.next()) 
				{
					Payement payement = new Payement();
					RequestPayement requestPayement = new RequestPayement();
					Payement payementTmp= requestPayement.getPayement(""+resultat.getInt("idPayement"));
					payement.setIdPayement(""+resultat.getInt("idPayement"));
					payement.setNumRedevable(payementTmp.getNumRedevable());
					payement.setDatePayement(resultat.getString("datePayement"));
					payement.setEtat(resultat.getString("etat"));
					payement.setIdTypePayement(resultat.getString("idTypePayement"));
					payement.setMontantPayement(""+resultat.getDouble("montantPayement"));
					payement.setNombreDeFacturePayee(""+resultat.getInt("nombreDeFacturePayee"));				
					payement.setTypePayement(getTypePayementFromId(resultat.getInt("idTypePayement")).getTypePayement());				
					res.add(payement);
				}
				
				
			}
			catch (Exception e) 
			{		
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
