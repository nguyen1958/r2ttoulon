package fr.analogon.r2t.Utilitaire;

import java.text.DecimalFormat;


public final class Arrondi 
{
	public static String getFromDoubleToString(double d)
	{
		String res="";
		Double dd =new Double(d);
		if (dd.intValue()== d) 
			res = String.valueOf(dd.intValue()) ;
		else
			res = String.valueOf(d);		
		return res ;		
	}
	
	public static String arrondirTotalLigneFacture(double d)
	{
		String res="";
		Double dd =new Double(d);
		if (dd.intValue()== d) 
			res = String.valueOf(dd.intValue())+".00" ;
		else
			res = String.valueOf(d);		
		return res ;		
	}
	
	
	public static String arrondir2Digit(double d)
	{
		
		String res ="";		
		DecimalFormat df = new DecimalFormat("#.00");
		String ds= df.format(d);		
		ds = ds.replaceAll(",", ".");
		res = ds;
		return res;
	}
	
	
		
	public static String arrindiParLigne(double d)
	{
		String res="";
		if (d< 1)
		{	
			d =1;
			res= Arrondi.arrondir2Digit(d);
		}
		else 
		{
			//res= Arrondi.arrondir2Digit(d)+"0";
			//if (d % 5 )
			//
			if ( Math.abs(d - Math.round(d)) != 0.5 )
				res =   Arrondi.arrondir2Digit( Math.round(d));
			else 
				res =   Arrondi.arrondir2Digit(d);				
		}
		return res;
	}
		
	

}
