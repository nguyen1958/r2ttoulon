package fr.analogon.r2t.pojo;


/**
 * creer par   le 23/03/2006 pour ANALOGON.
 * Modifier par Sofien CHARFI le 11/11/2007 pour ANALOGON.
 */
public class Ville 
{
	private int id=0;
	private String code= "";
	private String nom= "";

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getNom() {
	return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
