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

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;

 

public class TestMontantTotalParBareme
{
	static Logger logger = Logger.getLogger(TestMontantTotalParBareme.class);
	
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
		    String listBareme ="2949,2940,2929,2909,2900,2159,2150,2149,2140," 
		    		         +	"2139,2130,2129,2122,2120,2119,2110,2109,2101,2100,8900,8901";
		    
		    String requete = " SELECT  SUM(`lignefacture`.`montantLigne` ) as montantTotal ," 
		    		         +" SUM(`lignefacture`.`quantite`) as quantiteTotal," 
		    		         +" lignefacture.`prixUnitaire` as tarifDuBareme , " 
		    		         +" bareme.`code` as codeDuBareme," 
		    		         +"`bareme`.`libelle` as libelleDuBareme " 
		    		         +"	FROM  bareme bareme  , imputation imputation,`lignefacture` , `facture`" 		    		         
		    		         +" WHERE `lignefacture`.`codeBareme` = bareme.`code`" 		    		         
		    		         +"	AND facture.`numeroFacture` = `lignefacture`.`idFacture`" 
		    		         +" AND bareme.`idImputation` = imputation.`idImputation`" 
		    		         //+" AND imputation.`libelle` = 'ETALAGE'"
		    		         //+"	AND bareme.`libelle`  like 'ter%'" 
		    		         +"	AND facture.`etat`='VALIDE'" 
		    		         +" AND imputation.`anneeExercice` = 2010 " 
		    		         +" AND bareme.`code` IN ("+listBareme+")"
		    		         +" GROUP BY libelleDuBareme ORDER BY codeDuBareme ASC" ;
		    System.out.println(requete);
		    resultat = instruction.executeQuery(requete);
		    
		    //Bareme 
		    //Libelle
		    //Tarif
		    //Quantite Total [(Surface) ou (long*largeur )] * qte 
		    //Montant Total
		    
		    
		    
		    
		    WritableWorkbook w = Workbook.createWorkbook(new File("C:\\MontantTotalParBareme"+
		    		GestionDate.getDateAujourdhuiString().replaceAll("/", "_")+".xls"));
	        WritableSheet s = w.createSheet("Montant_Total_Par_Bareme", 0); 
	        s.addCell(new Label(0, 0, "Bareme"));
	        s.addCell(new Label(1, 0, "Libelle"));
	        s.addCell(new Label(2, 0, "Tarif"));
	        s.addCell(new Label(3, 0, "Quantite total"));
	        s.addCell(new Label(4, 0, "Montant "));	        
	        
	        int j =1 ;
		    while (resultat.next()) 
		    {
		    	String codeDuBareme = resultat.getString("codeDuBareme");
		    	String libelleDuBareme = resultat.getString("libelleDuBareme");
		    	String tarifDuBareme = String.valueOf(resultat.getDouble("tarifDuBareme"));		    	
		    	String quantiteTotal = resultat.getString("quantiteTotal");
		    	String montantTotal = resultat.getString("montantTotal");
		    	montantTotal = ""+FonctionCommun.arrondir(Double.valueOf(montantTotal));
		    	
		    	s.addCell(new Label(0, j, codeDuBareme));
		    	s.addCell(new Label(1, j,libelleDuBareme));
		    	s.addCell(new Label(2, j,tarifDuBareme));
		    	s.addCell(new Label(3, j,quantiteTotal));
		    	s.addCell(new Label(4, j,montantTotal));
		    	j++;
		    }
		    //System.out.println("Nombre d'etalage actif dans le secteur 64 = " + j);
		    w.write();
			w.close();
		}		
		catch (Exception ex)
		{
			System.out.println("Erreur: " + ex.getMessage());			
		}			
	}	
}








