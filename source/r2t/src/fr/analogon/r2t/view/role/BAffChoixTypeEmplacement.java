package fr.analogon.r2t.view.role;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Imputation;
import fr.analogon.r2t.request.DataFromBD;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage des types d'emplacements . Sofien CHARFI
 * 
 * @version 1.0
 */
public class BAffChoixTypeEmplacement extends fr.analogon.r2t.main.RacineBean {

	private String numRedevable = "";
	private String anneeTypeTaxe= "";	
	private String  ListeTypes="";
	private String  secteur="";
	

	private HttpServletRequest request;

	public BAffChoixTypeEmplacement() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

	}

	/**
	 * Methode set permettant declarer un objet httprequest . Frédéric Guillaume
	 * 
	 * @since 1.0
	 */
	public void setRequest(HttpServletRequest req) 
	{
		this.request = req;
		this.numRedevable = request.getParameter("numRedevable");
		
		
		RequestImputation reqImputation = new RequestImputation();
		DataFromBD t= new DataFromBD();
		Vector contenu1= new Vector ();				
		Vector contenu3= new Vector ();
		Vector listDesTypeTaxe = reqImputation.getLesDifferentTypesTaxe();
		for (int i = 0; i < listDesTypeTaxe.size(); i++) 
		{
			contenu1.addElement(((Imputation)listDesTypeTaxe.elementAt(i)).getCode() +"---"+
					((Imputation)listDesTypeTaxe.elementAt(i)).getLibelle());
			contenu3.addElement(((Imputation)listDesTypeTaxe.elementAt(i)).getLibelle());
		}
		//ListeTypes = StaticManipHtml.genererListeDeroulante("codeType", 1,"", contenu,false);	
		ListeTypes = StaticManipHtml.genererListeTypeTaxe("codeType","", contenu3,contenu1);
		
					
			
		anneeTypeTaxe = GestionDate.getAnneeCourante();
		
		
		Vector contenu2= t.getTousLesSecteurs();			
		secteur = StaticManipHtml.genererListeDeroulante("codeSecteur",1,"", contenu2, false);
		
	}

	public String getNumRedevable() {
		return numRedevable;
	}

	/**
	 * Permet de recupere la liste deroulante HTML comprenant les types des
	 * emplacements. . Mustapha Charraki
	 * 
	 * @since 1.0
	 */
	public String getListeTypes()
	{
		return ListeTypes;
	
	}

	/**
	 * Permet de recupere la liste deroulante HTML comprenant les types des
	 * emplacements. . Mustapha Charraki
	 * 
	 * @since 1.0
	 */
	public String getListeTypesRapide() {
		String res = "";
		String nomLD = "codeTypeRapide";		
		return res;
	}

	/**
	 * @return the anneeTypeTaxe
	 */
	public final String getAnneeTypeTaxe() {
		return anneeTypeTaxe;
	}

	/**
	 * @param anneeTypeTaxe the anneeTypeTaxe to set
	 */
	public final void setAnneeTypeTaxe(String anneeTypeTaxe) {
		this.anneeTypeTaxe = anneeTypeTaxe;
	}

	/**
	 * @return the secteur
	 */
	public final String getSecteur() {
		return secteur;
	}

	/**
	 * @param secteur the secteur to set
	 */
	public final void setSecteur(String secteur) {
		this.secteur = secteur;
	}

	/**
	 * @param numRedevable the numRedevable to set
	 */
	public final void setNumRedevable(String numRedevable) {
		this.numRedevable = numRedevable;
	}
	
}



