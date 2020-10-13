package fr.analogon.r2t.Utilitaire;

public class StringUp 
{
	public static boolean Contains(String chaine, String elem)
	{
		if(chaine.indexOf(elem)==-1) return false;
		else return true;		
	}
	
	
	public static synchronized String replaceAll (String str, String pattern, String replace)
	  {  
	   StringBuffer lSb = new StringBuffer( ); 
	   if((str != null)  &&  (pattern != null)  &&  (pattern.length() > 0)  &&  (replace != null))
	     {  
	       int i = 0;  
	       int j = str.indexOf (pattern, i); 
	       int l = pattern.length(); 
	       int m = str.length();

	       if(j > -1)
		 {  
	           while(j > -1)
		     {       
		       if(i != j)
			 {  
			   lSb.append(str.substring(i, j));  
			 }

		       lSb.append(replace);
		       i = j + l; 
		       j =  (i > m) ? -1 : str.indexOf(pattern, i); 
		     }
	  
		   if(i < m)
		     {  
		       lSb.append(str.substring(i)); 
		     }  
		 }  
	       else
		 {  
		   lSb.append(str); 
		 }  
	     }
	  
	   return lSb.toString(); 
	  } 
	
	
	
	
	
	
	
	
	
	public static void main(String args[])
	{
		
	}

}