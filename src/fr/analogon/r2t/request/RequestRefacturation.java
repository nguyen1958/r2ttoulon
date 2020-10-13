package fr.analogon.r2t.request;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.pojo.Refacturation;

public class RequestRefacturation extends Request
{
	public void ajouterRefacturation( String anneeExercice ,
									  String  typeTaxe ,
									  String numeroFacture ,
									  String numRedevable ,
									  String dateRefacturation ,
									  String etapeFacturation ) {
		String requete= " INSERT INTO historiquerefacturation(anneeExercice,typeTaxe" +
						",numeroFacture,numRedevable,dateRefacturation,etape ) " +
						"VALUES('"+anneeExercice+"','"+
						typeTaxe+"','"+numeroFacture+"','"+
						numRedevable+"','"+dateRefacturation+"','"+etapeFacturation+"')";
		debug.logger.debug(requete);
		Statement instruction;
		try {
			instruction = con.connexion.createStatement();
			try	{
				int result1= instruction.executeUpdate(requete);				
			}catch (SQLException e) {
				debug.logger.fatal(e.getMessage());
			}finally {
				instruction.close();
			}
		}catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
	}
	  
	public Vector getListeRefacturation( String anneeExercice ,
			  							 String  typeTaxe ,
			  							 String numeroFacture ,
			  							 String numRedevable ,
			  							 String dateRefacturation ) {
		Vector params=new Vector();
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				String r1= " SELECT * FROM historiquerefacturation " +
						   " WHERE historiquerefacturation.idHistoriqueEefacturation= historiquerefacturation.idHistoriqueEefacturation " +
						   " AND historiquerefacturation.etape='refacturation' ";
				if (anneeExercice.length()!=0)	r1=r1+ " AND historiquerefacturation.anneeExercice ='"+ anneeExercice+"'";
				if (typeTaxe.length()!=0)	r1=r1+ " AND historiquerefacturation.typeTaxe ='"+ typeTaxe+"'";
				if (numeroFacture.length()!=0)	r1=r1+ " AND historiquerefacturation.numeroFacture ='"+ numeroFacture+"'";
				if (numRedevable.length()!=0)	r1=r1+ " AND historiquerefacturation.numRedevable ='"+ numRedevable+"'";
				if (dateRefacturation.length()!=0)	r1=r1+ " AND historiquerefacturation.dateRefacturation ='"+ dateRefacturation+"'";          		
	
				r1=r1+ " ORDER BY historiquerefacturation.idHistoriqueEefacturation DESC ";
				debug.logger.debug(r1);	
				ResultSet resultat1 = instruction.executeQuery(r1);			
				while(resultat1.next()) {
					Refacturation  refacturation = new Refacturation();
					refacturation.setAnneeExercice(resultat1.getString("anneeExercice"));
					refacturation.setIdRefacturation(""+resultat1.getInt("idHistoriqueEefacturation"));
					refacturation.setTypeTaxe(resultat1.getString("typeTaxe"));
					refacturation.setNumeroFacture(resultat1.getString("numeroFacture"));
					refacturation.setNumRedevable(resultat1.getString("numRedevable"));
					refacturation.setDateRefacturation(resultat1.getString("dateRefacturation"));					
					params.addElement(refacturation);				
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
}
