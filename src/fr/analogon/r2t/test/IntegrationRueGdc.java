package fr.analogon.r2t.test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;


 
public class IntegrationRueGdc
{
	static Logger logger = Logger.getLogger(IntegrationRueGdc.class);
	public static Vector getRue ( String fichier )
	{
		Vector res = new Vector();
		try
		{
			File inputWorkbook = new File(fichier);
			Workbook w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			// Loop over first 10 column and lines

			for (int j = 0; j < sheet.getColumns(); j++) {
				for (int i = 0; i < sheet.getRows(); i++) {
					Cell cell = sheet.getCell(j, i);
					CellType type = cell.getType();
					if (cell.getType() == CellType.LABEL) 
					{
						String nomRue = cell.getContents();
						//System.out.println(nomRue);
						res.addElement(nomRue);
					}
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return res;
	}
	
	public static void main(String[] args) 
	{
		    //insertRueCouteaux();
		
			try
			{
				Class.forName("org.gjt.mm.mysql.Driver" ).newInstance();
				Statement instruction = null;
				ResultSet resultat = null;
			    String url="jdbc:mysql://localhost/gdcbezons";
			    String user="root";
			    String pwd="admin";
			    Connection conn = DriverManager.getConnection(url, user, pwd );
			    instruction = conn.createStatement();
			    
                ////////////////////////////VILLE bezons/////////////////////////////////////////////////////////////////////////////////
			    //String vrue_sup ="n";
				//String vrue_ville ="Bezons";
				//String vrue_codeposte ="95870";
			    //String vrue_ville_id ="9";
			    
			    //bezons
			    //String fichier1 ="/Users/sofien/Volume.b236f625.58a8.11e0.b841.0015af5eaf18/analogon/bezons/Bezons/OK/traite/bezonsRue.xls";
			    //String vrue_quartier ="64";
			    //Vector listeRue = getRue(fichier1);
			    
			    
			    
			    
			    ////////////////////////////VILLE Argentueil /////////////////////////////////////////////////////////////////////////////////
			    String vrue_sup ="n";
			    String vrue_ville ="Argenteuil";
			    String vrue_codeposte ="95100";
			    String vrue_ville_id ="9";
			    
			    //CentreVille
			    //String fichier1 ="/Users/sofien/Volume.b236f625.58a8.11e0.b841.0015af5eaf18/analogon/bezons/Argentueil/OK/CentreVille.xls";
			    //String vrue_quartier ="58";
			    
			    //ORGEMONT VOLEMBERT
			    //String fichier1 ="/Users/sofien/Volume.b236f625.58a8.11e0.b841.0015af5eaf18/analogon/bezons/Argentueil/OK/ORGEMONT VOLEMBERT.xls";
			    //String vrue_quartier ="60";
			    
			    
			    //VAL D'ARGENT NORD
			    //String fichier1 ="/Users/sofien/Volume.b236f625.58a8.11e0.b841.0015af5eaf18/analogon/bezons/Argentueil/OK/VAL D'ARGENT NORD.xls";
			    //String vrue_quartier ="61";
			    
			    //VAL D'ARGENT SUD
			    //String fichier1 ="/Users/sofien/Volume.b236f625.58a8.11e0.b841.0015af5eaf18/analogon/bezons/Argentueil/OK/VAL D'ARGENT SUD.xls";
			    //String vrue_quartier ="62";
			    
			    //VAL NOTRE DAME
			    String fichier1 ="/Users/sofien/Desktop/argent-Nord-ouest.xls";
			    String vrue_quartier ="68";
			    Vector listeRue = getRue(fichier1);
			    for (int i = 0; i < listeRue.size() ; i++) 
				    {
				    	
				    	String rue = listeRue.elementAt(i).toString();
				    	Rue r = new Rue (rue);
				    	String ruetype = r.getRue_type(); 
					    String vrue_liaison =r.getRue_liaison();
					    String vrue_nom =r.getRue_nom();
					    String vrue_nomrech =r.getRue_nom();
					    
				    	
						System.out.println(rue);
						vrue_nom=vrue_nom.replaceAll("'","\\\\'");	
					    vrue_nomrech=vrue_nomrech.replaceAll("'","\\\\'");	
					    vrue_liaison=vrue_liaison.replaceAll("'","\\\\'");
					    String requeteInsertRue ="INSERT INTO `vrue` (`vrue_type`,`vrue_liaison`,`vrue_nom`," +
						  "`vrue_nomrech`,`vrue_quartier`, `vrue_secteur`,`vrue_ville`," +
						  "`vrue_codeposte`,`vrue_ville_id`,`vrue_sup`) VALUES" +
						  "('"+ruetype+"','"+vrue_liaison+"','"+vrue_nom+"','"+vrue_nomrech+"',"+vrue_quartier+
						  ",'-1','"+vrue_ville+"',"+vrue_codeposte+","+vrue_ville_id+",'"+vrue_sup+"');";
					    instruction.executeUpdate(requeteInsertRue);
					    //System.out.println(requeteInsertRue);
					}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
	}
	
	
	public static void insertRueCouteaux() 
	{	
		
			try
			{
				Class.forName("org.gjt.mm.mysql.Driver" ).newInstance();
				Statement instruction = null;
				ResultSet resultat = null;
			    String url="jdbc:mysql://localhost/gdcbezons";
			    String user="root";
			    String pwd="admin";
			    Connection conn = DriverManager.getConnection(url, user, pwd );
			    instruction = conn.createStatement();
		
			    ////////////////////////////VILLE Argentueil /////////////////////////////////////////////////////////////////////////////////
			    String vrue_sup ="n";
			    String vrue_ville ="Argenteuil";
			    String vrue_codeposte ="95100";
			    String vrue_ville_id ="9";
			     String fichierCouteaux ="/Users/sofien/Volume.b236f625.58a8.11e0.b841.0015af5eaf18/analogon/bezons/Argentueil/OK/LES COTEAUX.xls";
			    String fichierCouteauxEst ="/Users/sofien/Volume.b236f625.58a8.11e0.b841.0015af5eaf18/analogon/bezons/Argentueil/OK/LES COTEAUX-Recherche-EST.xls";
			    String fichierCouteauxOuest ="/Users/sofien/Volume.b236f625.58a8.11e0.b841.0015af5eaf18/analogon/bezons/Argentueil/OK/LES COTEAUX-Recherche-OUEST.xls";
			    
			    
			    Vector listeRueCouteaux = getRue(fichierCouteaux);
			    Vector listeRueCouteauxEstRecherche = getRue(fichierCouteauxEst);
			    Vector listeRueCouteauxOuestRecherche = getRue(fichierCouteauxOuest);
			    System.out.println("TOTAL="+listeRueCouteaux);
			    System.out.println("EST="+listeRueCouteauxEstRecherche);
			    System.out.println("OUEST="+listeRueCouteauxOuestRecherche);
			    String listeRueCouteauxOuestRechercheString = listeRueCouteauxOuestRecherche.toString().toUpperCase();
			    String listeRueCouteauxEstRechercheString = listeRueCouteauxEstRecherche.toString().toUpperCase();
			   
			    
			    String vrue_quartier ="";
			    Vector listeRue = null;
			    Vector listeRueCouteauxEst= new Vector()  ;
			    Vector listeRueCouteauxOuest= new Vector()  ;
			    
			    //Remplissage des quartier couteauxEst et couteuaxWest
			    for (int i = 0; i < listeRueCouteaux.size() ; i++)
			    {
			    	String rue = listeRueCouteaux.elementAt(i).toString();
			    	
			    	if (listeRueCouteauxEstRechercheString.indexOf(rue) != -1 )
			    		listeRueCouteauxEst.addElement(rue);
			    	else
			    		listeRueCouteauxOuest.addElement(rue);
			    	
			    }
			    
			    for (int j = 0; j < 2 ; j++)
			    {
			    	if (j == 0)
			    	{
			    		listeRue = listeRueCouteauxEst;
			    		vrue_quartier ="59";
					    
			    	}
			    	if (j == 1)
			    	{
			    		listeRue = listeRueCouteauxOuest;
			    		vrue_quartier ="67";
			    	}
			    	
				    for (int i = 0; i < listeRue.size() ; i++) 
				    {
				    	
				    	String rue = listeRue.elementAt(i).toString();
				    	Rue r = new Rue (rue);
				    	String ruetype = r.getRue_type(); 
					    String vrue_liaison =r.getRue_liaison();
					    String vrue_nom =r.getRue_nom();
					    String vrue_nomrech =r.getRue_nom();
					    
				    	
						//System.out.println(rue);
						vrue_nom=vrue_nom.replaceAll("'","\\\\'");	
					    vrue_nomrech=vrue_nomrech.replaceAll("'","\\\\'");	
					    vrue_liaison=vrue_liaison.replaceAll("'","\\\\'");
				
					    String requeteInsertRue ="INSERT INTO `vrue` (`vrue_type`,`vrue_liaison`,`vrue_nom`," +
						  "`vrue_nomrech`,`vrue_quartier`, `vrue_secteur`,`vrue_ville`," +
						  "`vrue_codeposte`,`vrue_ville_id`,`vrue_sup`) VALUES" +
						  "('"+ruetype+"','"+vrue_liaison+"','"+vrue_nom+"','"+vrue_nomrech+"',"+vrue_quartier+
						  ",'-1','"+vrue_ville+"',"+vrue_codeposte+","+vrue_ville_id+",'"+vrue_sup+"');";
					    //instruction.executeUpdate(requeteInsertRue);
					    //System.out.println(requeteInsertRue);
					}
			    }
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
	}
	
	
	
}








