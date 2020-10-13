package fr.analogon.r2t.request;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Bareme;
import fr.analogon.r2t.pojo.ImputationSection;
import fr.analogon.r2t.pojo.Redevable;
import fr.analogon.r2t.pojo.Rue;
import fr.analogon.r2t.pojo.Synchronisation;

public class DataFromBD extends Request
{
  
  public static void main(String args[]) throws Exception
  {	
	  //DataFromBD tmp = new DataFromBD();	  
  }
/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////// FONCTION OK /////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Methode qui permet de retourner un type de taxe a partir d'un attribut valeur  
	 * CHARFI Sofien
	 *  
	 * @since 2.0
	 */

	public ImputationSection getTaxe(String attribut,String ValAttribut)
	{
		attribut = FonctionCommun.ajouterAntislash(attribut);
		ValAttribut = FonctionCommun.ajouterAntislash(ValAttribut);
		ImputationSection res= new ImputationSection();
		Statement instruction;
		try
		{
			instruction = con.connexion.createStatement();
			try 
			{
				String r= "SELECT * from imputation " +
						  " WHERE " + attribut +" ='" + ValAttribut +"'";
				debug.logger.debug(r);
				ResultSet resultat = instruction.executeQuery(r);
				while(resultat.next())
				{		
					res.setCodeImputation(String.valueOf(resultat.getInt("code")));
					res.setIdImputation(String.valueOf(resultat.getInt("idImputation")));
					res.setCodeSection(String.valueOf(resultat.getInt("section")));
					res.setLibelle(resultat.getString("libelle"));
					res.setPeriodicite(resultat.getString("periodicite"));			
				}
			}
			catch (Exception e) 
			{		
				debug.logger.fatal(e.getMessage());
			}finally{
				instruction.close();	
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		return res;
	}
  
	/**
	 * Methode qui permet de retourner tous les  secteurs  
	 * CHARFI Sofien
	 *  
	 * @since 2.0
	 */
  
	public Vector getTousLesSecteurs()
	{
		Vector res= new Vector();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			
			try 
			{
				ResultSet resultat = instruction.executeQuery( "SELECT DISTINCT(valeur) " +
									 "FROM parametre WHERE type ='code_secteur' ORDER BY  CAST(valeur AS DECIMAL(8,2)) ");
				while(resultat.next())
				{		
					res.addElement(resultat.getString("valeur"));					
				}
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

	/**
	 * Methode qui permet de retourner tous les  quartier a partir de la table rue 
	 * CHARFI Sofien
	 *  
	 * @since 2.0
	 */
  /*
  public Vector getTousLesQuartiers()
  {
	  Vector res= new Vector();
	     
	  Statement instruction;
	 try 
	 {
		instruction = con.connexion.createStatement();
		String requete ="select DISTINCT(nomQuartier) from rue" +
		" Where nomQuartier !=\"\" ";
		debug.logger.debug(requete);
		ResultSet resultat = instruction.executeQuery( requete );
		while(resultat.next())
		{		
			res.addElement(resultat.getString("nomQuartier"));					
		}
	 }
	 catch (Exception e) 
	 {		
		debug.logger.fatal(e.getMessage());
	 }	
	     
	 return res;
  } 
  
  */
  public Vector getTousLesRaisonSocial()
  {
	  Vector res= new Vector();
	     
	  Statement instruction;
	  try {
		  instruction = con.connexion.createStatement();
		 try 
		 {
			ResultSet resultat = instruction.executeQuery( "SELECT DISTINCT(valeur) " +
					"FROM parametre WHERE type ='type_civilite' ORDER BY  valeur ");
					
			while(resultat.next())
			{		
				res.addElement(resultat.getString("valeur"));					
			}
			instruction.close();
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
  
  
  
  public Vector getToutesLesNaturesJuridique()
  {
	  Vector res= new Vector();
	     
	  Statement instruction;
	  try {
		  instruction = con.connexion.createStatement();
		 try 
		 {
			ResultSet resultat = instruction.executeQuery( "SELECT DISTINCT(valeur) " +
					"FROM parametre WHERE type ='nature_juridique' ORDER BY  valeur ");
					
			while(resultat.next())
			{		
				res.addElement(resultat.getString("valeur"));					
			}
			instruction.close();
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
  
  
  
  public Vector getTousLesTypesDeFacturation()
  {
	  
	  Vector res= new Vector();
	     
	  Statement instruction;
	  try {
		  instruction = con.connexion.createStatement();
		 try 
		 {
			ResultSet resultat = instruction.executeQuery("SELECT valeur" +
						" FROM parametre WHERE type='typeFacturation' ");
			while(resultat.next())
			{		
				res.addElement(resultat.getString("valeur"));					
			}
			instruction.close();
		 }
		 catch (Exception e) 
		 {		
			debug.logger.fatal(e.getMessage());
		 } finally {
			 instruction.close();
		 }
	  }catch (Exception e) {
		  debug.logger.fatal(e.getMessage());
	  }
	     
	 return res;
  }

	public Vector getTousLesUniteDeTemps() {
		Vector res= new Vector();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction.executeQuery("SELECT valeur" +
									" FROM parametre WHERE type='uniteTemps' ");
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
  
	public Vector getTousLesTypeArrondi() {
		Vector res= new Vector();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try	{
				ResultSet resultat = instruction.executeQuery("SELECT valeur" +
									" FROM parametre WHERE type='typeArrondi' ");
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
  
	public Vector getTousLesEtatAlerte() {
		Vector res= new Vector();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction.executeQuery("SELECT valeur" +
									" FROM parametre WHERE type='etatAlerte' ");
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
  
	public Vector getTousComplementNumRue() {
		Vector res= new Vector();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction.executeQuery("SELECT valeur" +
									" FROM parametre WHERE type='complNumRue' ");
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
  
	public Vector getTousLesEtatsReclamation() {
		Vector res= new Vector();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction.executeQuery("SELECT valeur" +
								" FROM parametre WHERE type='etatReclamation' ");
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

	/**
	 * Methode qui permet de retourner une rue a partir du code de la rue
	 * CHARFI Sofien
	 *  
	 * @since 2.0
	 */
	public Rue getRue(String attribut,String ValAttribut) {
		Rue res= new Rue();
		Statement instruction;
		try{
			instruction = con.connexion.createStatement();
			try {
				String r= "SELECT * from rue " +
						  " WHERE "+ attribut +"='"+ ValAttribut+"'" ;
				debug.logger.debug(r);
				ResultSet resultat = instruction.executeQuery(r);
				while(resultat.next()) {		
					res.setNomquartier(resultat.getString("nomQuartier"));		
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

	/**
	 * Methode qui permet de retourner un article a partir du attribut
	 * CHARFI Sofien
	 *  
	 * @since 2.0
	 */
	public int getNumeroPeriode( String codeBareme,String anneeExercicie, String dateDebut, String dateFin ) 
	{		 
		int res =0;	 
		GestionDate gd = new GestionDate();
		RequestBareme reqBareme = new RequestBareme();
		
		Bareme bareme=reqBareme.getBareme(codeBareme, anneeExercicie);
		String typePeriode= bareme.getDureeUnitaire();	
		res=gd.getNumeroPeriode(dateDebut, dateFin,typePeriode);	 
		
		return res;
	}

/**
 * Methode qui permet de retourner la date de premier installation d article 
 * CHARFI Sofien
 *  
 * @since 2.0
 */

	public String getDateDebutAutoisationLaPlutPetiteFromArticle(int NumeroElementFacturation)
	{
		String min ="";
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction.executeQuery(
						"SELECT DateDebutAutorisation " +
						" FROM article" +
						" WHERE id_elementfacturation="+NumeroElementFacturation);
				Vector v= new Vector();
				while(resultat.next()) {		
					v.addElement(resultat.getString("DateDebutAutorisation"));
					//debug.logger.debug("res"+resultat.getString("DateDebutAutorisation"));
				}
				if( v.size()!=0) {
					min= (String)v.elementAt(0);			
					for (int i = 0; i < v.size(); i++) {
						int r= GestionDate.comparerDate(min, (String)v.elementAt(i));
						if(r==-1) {
							min =(String)v.elementAt(i);
						}
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
		//debug.logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>"+min);
		return min;
	}

	public int  getDureeUnitaire(int NumeroElementFacturation) {
		int res=0;
		Statement instruction;
		String dureeUnitaire="";
		String premierDateInstalation;
		try {
			instruction = con.connexion.createStatement();
			try {		
				ResultSet resultat = instruction.executeQuery(
						  "SELECT dureeUnitaire FROM bareme,article " 
						+ " WHERE article.id_elementfacturation = "+NumeroElementFacturation
						+ " AND elementfacturation.numero=artcile.code " 
						+ " AND  bareme.code= article.codebareme ;") ; 				
				while(resultat.next()) {		
					dureeUnitaire=resultat.getString("dureeUnitaire");			 
				}	
			}catch (Exception e) {		
				debug.logger.fatal(e.getMessage());
			}finally {
				instruction.close();				
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		premierDateInstalation= this.getDateDebutAutoisationLaPlutPetiteFromArticle(NumeroElementFacturation);
		//debug.logger.debug("premierDateInstalation="+premierDateInstalation);
		try	{
			if(dureeUnitaire.equalsIgnoreCase("jour")) {
			//debug.logger.debug("Peridicite=jour");
			res= GestionDate.getNombreDeJour(premierDateInstalation);				
			}
			if(dureeUnitaire.equalsIgnoreCase("semaine")) {
			//debug.logger.debug("Peridicite=semaine");
			res=GestionDate.getNombreDeSemaine(premierDateInstalation);				
			}
			if(dureeUnitaire.equalsIgnoreCase("mois")) {
				//debug.logger.debug("Peridicite=mois");
				res=GestionDate.getNombreDeMois(premierDateInstalation);				
			}
			if(dureeUnitaire.equalsIgnoreCase("an")) {
				//debug.logger.debug("Peridicite=an");
				res=GestionDate.getNombreDannee(premierDateInstalation);				
			}
		}catch (Exception e)	{
			debug.logger.fatal(e.getMessage());
		}
		return res;
	}

	public Vector  getTousLestypeReceptionReclamation() {
		Vector res= new Vector();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction.executeQuery("SELECT parametre.valeur FROM parametre" +
													" WHERE parametre.type='typeReceptionReclamation'");
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

	public Redevable getRedevable(int idRedevable) {	
		Redevable redevable = new Redevable();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1= " SELECT redevable.* FROM redevable " +				    				
						   " WHERE redevable.numRedevable =" +idRedevable ;				    
				//debug.logger.debug(r1);	
				ResultSet resultat1 = instruction.executeQuery(r1);	
				while(resultat1.next()) {			
					redevable.setNom(resultat1.getString("nomRedevable"));								
					redevable.setPrenom(resultat1.getString("prenom"));
					redevable.setCivilite(resultat1.getString("civilite"));
				}
			}catch (Exception e) {
				debug.logger.fatal(e.getMessage()); 
			}finally {
				instruction.close();				
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		 return redevable;
	}

	public Vector getSynchonisaltion(String nomQuartier, String dateControle , String utuilsateur )
	{	
		Vector res = new Vector();
		try {
			Statement instruction = con.connexion.createStatement();			
			try {
				System.out.println("=========="+dateControle);
				dateControle = dateControle.replaceAll("2013", "13");
				dateControle = dateControle.replaceAll("2014", "14");
				String r1= " SELECT DISTINCT(synchronisation.idSynchronisation),synchronisation.*," +
						   " utilisateur.nom,utilisateur.prenom, synchronisation.quartiers" +
						   " FROM synchronisation, utilisateur" +
						   " WHERE synchronisation.idControleur = utilisateur.numeroUser " +
						   " AND quartiers like '%"+ nomQuartier +"%'" +
						   " AND date like '%"+ dateControle+"%'" ;
				if (utuilsateur.length() != 0)
			  	    r1 = r1 +  " AND idControleur ="+ utuilsateur  ;
				r1 = r1 +   " ORDER BY synchronisation.idSynchronisation DESC" ;			
				debug.logger.debug(r1);	
				ResultSet resultat1 = instruction.executeQuery(r1);	
				int i =0;
				while(resultat1.next()) {	
					i++;
					Synchronisation syn = new Synchronisation();	
					syn.setAction(resultat1.getString("action"));
					syn.setDateSynchronisation(resultat1.getString("date"));
					syn.setQuartiers(resultat1.getString("quartiers"));
					syn.setTypeSynchronisation(resultat1.getString("typeSynchronisation"));
					syn.setIdPda(resultat1.getString("idPda"));			
					syn.setIdControleur(resultat1.getString("idControleur"));
					syn.setControleur(resultat1.getString("nom") +  " " +resultat1.getString("prenom"));
					res.addElement(syn);
					//debug.logger.debug(i);
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

	public Vector  getTousLesProffesions() {
		Vector res= new Vector();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction.executeQuery("SELECT * FROM activiteprofession order by libelle" );	
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

	public int getCodeProffesion(String libelleProffesion) {
		int res=0;
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try 
			{
				libelleProffesion = FonctionCommun
				.ajouterAntislash(libelleProffesion);
				String req = "SELECT * FROM activiteprofession where libelle='"+libelleProffesion+"'"; 
				ResultSet resultat = instruction.executeQuery(req );	
				while(resultat.next()) {		
					res=resultat.getInt("code");					
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

	public String getLibelleProffesion(int codeProffesion) {
		String  res="";
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction.executeQuery("SELECT * FROM activiteprofession where code="+codeProffesion);	
				while(resultat.next()) {		
					res=resultat.getString("libelle");					
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

	public Vector getTousLesEtatsEmplacement() {
		Vector res= new Vector();
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try {
				ResultSet resultat = instruction.executeQuery( "SELECT DISTINCT(valeur) " +
						"FROM parametre WHERE type ='etat_emplacement' ORDER BY  valeur ");
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
}