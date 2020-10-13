package fr.analogon.r2t.Utilitaire;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public final class ReglerCheminSelonOs 
{
	public static String reglerCheminSelonOS(String chemin) 
	{	
	String osName = System.getProperty("os.name");
	osName = osName.toLowerCase();		
	if(StringUp.Contains(osName, "windows"))
	{
		/**
		 * paul 26/01/2016
		 * Il faut deEncoder pour transformer "%20" en espace
		 * ex:C:/Program%20Files/Apache%20Software%20Foundation/Tomcat%206.0/wtpwebapps/r2t/
		 * en C:/Program Files/Apache Software Foundation/Tomcat 6.0/wtpwebapps/r2t/
		 */		
		try {
			chemin=URLDecoder.decode(chemin, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		if (chemin.startsWith("\\"))
			chemin = chemin.substring(1);
		chemin = chemin.replaceAll("/", "\\\\");
		*/
		
	}
	else
	{
		chemin = chemin.replaceAll("\\\\", "/");			
	}
	
	return chemin;
	}
	
	public static String linkFile(String link)
	{	
		String osName = System.getProperty("os.name");
		osName = osName.toLowerCase();
		String res;
		if(StringUp.Contains(osName, "windows"))
		{
					res=link +"///";
		}
		else
		{
			res =link;			
		}
		return res;
	}

}
