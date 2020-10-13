package fr.analogon.r2t.Utilitaire;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


public final class FonctionCommun 
{
	
	public static String ajouterAntislash(String chaine)
	{	
		if (chaine != null)
		{
			chaine=chaine.replaceAll("'","\\\\'");
		}
			return chaine;	
	}
	
	
	public static double ReglerMontant(double valeur)
	{
		return tronquer(valeur);
	}
	
	public static double tronquer(double valeur)
	{
		double res=0.00;
		if (String.valueOf(valeur).length()==5)
		{
			//cas double par exemple 12.56
			res = valeur; 
			
		}
		else if ( (valeur*10)%2 == 0 || (valeur*10)%2 == 1)
		{
			res = res + valeur;
		}	
		else
		{
			float f = ((float) ((int) (valeur*100))) / 100;		  
		    res= Double.valueOf(Float.toString(f)).doubleValue();
		}
		return res;
	}
	
	public static String ajouterPourcentage(double montant, double pourcentage)
	{
		double res=  montant + (montant /100 ) * pourcentage ;		
		return Arrondi.arrondir2Digit(res);
	}
	
	
	
	
	
	
	public static double arrondir(double valeur)
	{		
		return (tronquer(StaticNombres.arronFF2(valeur)));
	}
	
	public static void main(String args[]) throws Exception
	{	
		double d= 100;		
		////System.out.println("Valuer= "+d); //ités a String
		////System.out.println("Tronquer a 2 chiifre= "+FonctionCommun.tronquer(d)); //ités a String
		//System.out.println("Res= "+	FonctionCommun.ajouterPourcentage(100, 3.5); 
	}
	
	public static void  copy(String source, String destination)  
	{
		try {
			
		
		File src= new File(source);
        InputStream in = new FileInputStream(src);
        
        OutputStream out = new FileOutputStream(destination);       
        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
        in=null;
        out=null;
        //System.out.println(
		} catch (Exception e) {
			// TODO: handle exception
		}
        
       }
	
	public static String getEtat(String typeSelectionner)
	{
		if (typeSelectionner.equalsIgnoreCase("AFacturer"))
			typeSelectionner="A Facturer";
		else if (typeSelectionner.equalsIgnoreCase("Facturer"))
				typeSelectionner="Facturé";
		else if (typeSelectionner.equalsIgnoreCase("Rembourser"))
			typeSelectionner="Remboursé";
		else if (typeSelectionner.equalsIgnoreCase("FacturerAControler"))
				typeSelectionner="Facturé à Controler";
		else if (typeSelectionner.equalsIgnoreCase("ControlerAFacturer"))
				typeSelectionner="Contrôlé à Facturer";
		else if (typeSelectionner.equalsIgnoreCase("ControlerPresent"))
			typeSelectionner="Contrôlé Présent";
		else if (typeSelectionner.equalsIgnoreCase("ControlerAbsent"))
			typeSelectionner="Contrôlé Absent";
		else if (typeSelectionner.equalsIgnoreCase("ControlerAlerte"))
			typeSelectionner="Contrôlé Alerte";
		else if (typeSelectionner.equalsIgnoreCase("NePlusFacturer"))
			typeSelectionner="Ne Plus Facturer";
		else if (typeSelectionner.equalsIgnoreCase("NePasFacturer"))
			typeSelectionner="Ne Pas Facturer";
		
		return typeSelectionner;
	}

}
