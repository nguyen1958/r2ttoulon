package fr.analogon.r2t.administration;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.pojo.Imputation;
import fr.analogon.r2t.request.RequestAdmin;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de Synchronisaiton
 * 
 * @version 1.0
 * @since 1.0
 
 
 */
// Sofien

public class BAffAdministration extends fr.analogon.r2t.main.RacineBean {


	private String niveauDebogageApplication="";
	private String niveauDebogageR2TMobile="";
	HttpServletRequest req;	
	
	
	public void setRequest(HttpServletRequest req) 
	{		
		this.req=req;
		Vector contenu = new Vector();
		contenu.addElement("Désactivé");
		contenu.addElement("Erreur");
		contenu.addElement("Débogage");
		RequestAdmin requestAdmin = new RequestAdmin();
		niveauDebogageApplication = requestAdmin.getNiveauDebogageApplication();
		niveauDebogageR2TMobile= requestAdmin.getNiveauDebogageR2TMobile();
		this.niveauDebogageApplication = StaticManipHtml.genererListeDeroulanteNiveauDebogage("niveauDebogageApplication", 
				niveauDebogageApplication, contenu) ;
		
		this.niveauDebogageR2TMobile = StaticManipHtml.genererListeDeroulanteNiveauDebogageR2TMobile("niveauDebogageR2TMobile", 
				niveauDebogageR2TMobile, contenu) ;
		//this//
		contenu = null;
		System.gc();
		
	}


	public String getNiveauDebogageApplication() {
		return niveauDebogageApplication;
	}


	public void setNiveauDebogageApplication(String niveauDebogageApplication) {
		this.niveauDebogageApplication = niveauDebogageApplication;
	}


	public String getNiveauDebogageR2TMobile() {
		return niveauDebogageR2TMobile;
	}


	public void setNiveauDebogageR2TMobile(String niveauDebogageR2TMobile) {
		this.niveauDebogageR2TMobile = niveauDebogageR2TMobile;
	}	
	
	/**
	 * @return the tousTypeDeTaxe
	 */
	public String getTousTypeDeTaxe() 
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();	
		RequestImputation reqImputation = new RequestImputation();
		Vector listDesTypeTaxe = reqImputation.getLesDifferentTypesTaxe();
		for (int i = 0; i < listDesTypeTaxe.size(); i++) 
		{
			contenu.addElement(((Imputation)listDesTypeTaxe.elementAt(i)).getLibelle());
		}				
		res = StaticManipHtml.genererListeDeroulanteTousLesTypesDeTaxeFacturation
				("typeDeTaxe", 1,"", contenu,false);
		return res;	
	}

}

