package fr.analogon.r2t.test;

public class Rue 
{
	String rue_type = ""; 
    String rue_liaison ="";
    String rue_nom ="";
 
    
    public Rue(String r)
    {
    	
		String type = r.substring(0, r.indexOf(" "));
		String liaison ="";
		String nomRue = "";
		
		r = r.replace(type, "");
		if (r.startsWith(" ")) r = r.replaceFirst(" ", "");
		if (r.startsWith(" ")) r = r.replaceFirst(" ", "");
		if (r.toLowerCase().startsWith("des") ) liaison = liaison ="Des";
		else if (r.toLowerCase().startsWith("de la")) liaison = liaison ="De La";
		else if (r.toLowerCase().startsWith("de")) liaison = liaison ="De";
		else if (r.toLowerCase().startsWith("du")) liaison = liaison ="Du";
		
		if (r.startsWith(" ")) r = r.replaceFirst(" ", "");
		if (r.startsWith(" ")) r = r.replaceFirst(" ", "");
		r = r.replaceFirst(liaison, "");
		if (r.startsWith(" ")) r = r.replaceFirst(" ", "");
		if (r.startsWith(" ")) r = r.replaceFirst(" ", "");
		nomRue = r ;
		
		//System.out.println(r) ;
		//System.out.println("Type de Rue="+type) ;
		//System.out.println("liaison="+liaison) ;
		//System.out.println("nomRue="+nomRue) ;
    	
		this.rue_type = type;
    	this.rue_liaison= liaison;
    	this.rue_nom=nomRue;
    	
    }


	public String getRue_type() {
		return rue_type;
	}


	public void setRue_type(String rue_type) {
		this.rue_type = rue_type;
	}


	public String getRue_liaison() {
		return rue_liaison;
	}


	public void setRue_liaison(String rue_liaison) {
		this.rue_liaison = rue_liaison;
	}


	public String getRue_nom() {
		return rue_nom;
	}


	public void setRue_nom(String rue_nom) {
		this.rue_nom = rue_nom;
	}

	
    
    

}
