package fr.analogon.r2t.test;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;

 

public class Test
{
	static Logger logger = Logger.getLogger(Test.class);
	
	public static void main(String[] args) 
	{	
		try
		{
			Class.forName("org.gjt.mm.mysql.Driver" ).newInstance();
			Statement instruction = null;
			ResultSet resultat = null;
		    String url="jdbc:mysql://localhost/r2ttoulon";
		    String user="root";
		    String pwd="admin";
		    Connection conn = DriverManager.getConnection(url, user, pwd );
		    instruction = conn.createStatement();
		    String requete =" SELECT DISTINCT(emplacementgeneral.numero) as numeroEmplacement, " +
		    		        " redevable.nomRedevable as nomRedevable ,redevable.prenom as prenomRedevable ," +
		    		        " redevable.civilite as civiliteRedevable , " +
		    		        " redevable.ville as villeRedevable , " +
		    		        " redevable.codePostal as codePostalRedevable , " +
		    		        " redevable.codeVoie as codeVoieRedevable, redevable.numRue numRueRedevable," +
		    		        " redevable.adresse1 as adresse1Redevable, redevable.adresse2 as adresse2Redevable," +
		    		        " redevable.adresse3 as adresse3Redevable, " +
		    		        " activiteprofession.libelle as professionRedevable, " +
		    		        " emplacementgeneral.numRue as numRueEmplacement," +
		    		        " emplacementgeneral.adresse1 as adresse1Emplacement," +
		    		        " emplacementgeneral.adresse2 as adresse2Emplacement," +
		    		        " emplacementgeneral.adresse3 as adresse3Emplacement, " +
		    		        " emplacementgeneral.codeVoie as codeVoieEmplacement, " +
		    		        " emplacementgeneral.codePostal as codePostalEmplacement, " +
		    		        " emplacementgeneral.ville  as villeEmplacement" +
		    		       
		    		        " FROM emplacementgeneral,redevable,activiteprofession,imputation " +
		    		        
		    		        " WHERE redevable.numeroProfession =activiteprofession.code " +
		    		        " AND redevable.numRedevable = emplacementgeneral.numRedevable " +
		    		        " AND imputation.idImputation = emplacementgeneral.codeType " +
		    		        " AND imputation.libelle='ETALAGE' AND  emplacementgeneral.enActivite='enActivite' " +
		    		        " AND emplacementgeneral.codeSecteur=64 " ;
		    System.out.println(requete);
		    resultat = instruction.executeQuery(requete);
		    
		    //Nom
		    //prénom
		    //raison sociale
		    //profession
		    //code rivoli redevable
		    //adresse du redevable
		    //Code postal du redevable
		    //Ville du redevable
		    //code rivoli emplacement
		    //adresse de l'emplacement 
		    //code postal de l'emplacement
		    //ville de l'emplacement
		    WritableWorkbook w = Workbook.createWorkbook(new File("C:\\FichierExcel.xls"));
	        WritableSheet s = w.createSheet("Etalages_en_activité", 0); 
	        s.addCell(new Label(0, 0, "Nom Redevable"));
	        s.addCell(new Label(1, 0, "Prenom Redevable"));
	        s.addCell(new Label(2, 0, "RaisonSocial Redevable"));
	        s.addCell(new Label(3, 0, "Profession Redevable"));
	        s.addCell(new Label(4, 0, "Code Rivoli Redevable"));
	        s.addCell(new Label(5, 0, "Adresse Redevable"));
	        s.addCell(new Label(6, 0, "Code Postal Redevable"));
	        s.addCell(new Label(7, 0, "Ville Redevable"));
	        s.addCell(new Label(8, 0, "Code Rivoli Emplacement"));
	        s.addCell(new Label(9, 0, "Adresse Emplacement"));
	        s.addCell(new Label(10, 0, "Code Postal Emplacement"));
	        s.addCell(new Label(11, 0, "Ville Emplacement"));	        
	        
	        
	        int j =1 ;
		    while (resultat.next()) 
		    {
		    	String nomRedevable = resultat.getString("nomRedevable");
		    	String prenomRedevable = resultat.getString("prenomRedevable");
		    	String raisonSocialRedevable = resultat.getString("civiliteRedevable");
		    	String professionRedevable = resultat.getString("professionRedevable");
		    	String codeRivoliRedevable= resultat.getString("codeVoieRedevable");
		    	String codeRivoliEmplacement= resultat.getString("codeVoieEmplacement");
		    	String adresseRedevable= resultat.getString("numRueRedevable") + 
		    	                        " " + resultat.getString("adresse1Redevable") + 
		    							" " + resultat.getString("adresse2Redevable") + 
		    							" " + resultat.getString("adresse3Redevable")  ;
		    	String codePostalRedevable= resultat.getString("codePostalRedevable");
		    	String villeRedevable= resultat.getString("villeRedevable");
		    	String adresseEmplacement= resultat.getString("numRueEmplacement") + 
						                " " + resultat.getString("adresse1Emplacement") + 
										" " + resultat.getString("adresse2Emplacement") + 
										" " + resultat.getString("adresse3Emplacement")  ;
		    	String codePostalEmplacement= resultat.getString("codePostalEmplacement");
		    	String villeEmplacement= resultat.getString("villeEmplacement");
		    	
		    	s.addCell(new Label(0, j, nomRedevable));
		    	s.addCell(new Label(1, j, prenomRedevable));
		    	s.addCell(new Label(2, j, raisonSocialRedevable));
		    	s.addCell(new Label(3, j, professionRedevable));
		    	s.addCell(new Label(4, j, codeRivoliRedevable));
		    	s.addCell(new Label(5, j, adresseRedevable));
		    	s.addCell(new Label(6, j, codePostalRedevable));
		    	s.addCell(new Label(7, j, villeRedevable));
		    	s.addCell(new Label(8, j, codeRivoliEmplacement));
		    	s.addCell(new Label(9, j, adresseEmplacement));
		    	s.addCell(new Label(10, j, codePostalEmplacement));
		    	s.addCell(new Label(11, j, villeEmplacement));		    	
		    	j++;
		    }
		    System.out.println("Nombre d'etalage actif dans le secteur 64 = " + j);
		    w.write();
			w.close();
		}		
		catch (Exception ex)
		{
			System.out.println("Erreur: " + ex.getMessage());			
		}			
	}	
}








