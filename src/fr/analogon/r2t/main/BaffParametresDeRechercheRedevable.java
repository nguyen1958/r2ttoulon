package fr.analogon.r2t.main;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.analogon.r2t.pojo.Imputation;
import fr.analogon.r2t.request.DataFromBD;
import fr.analogon.r2t.request.RequestBareme;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.request.RequestRue;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage pour la recherche de redevbale 
 * 
 * @version 2.0 . Sofien CHARFI
 * @since 2.0
 */
public class BaffParametresDeRechercheRedevable extends fr.analogon.r2t.main.RacineBean {

	private String  typeTaxeAutorise ;
	private String  nomQuartier ;
	private String  NumSecteur;
	private String  listeTaxe;
	private String  numSecteur;
	private String listeBareme;
	private String ListeProfessions;
	private void jbInit() throws Exception 
	{		
	}

	public void setRequest(HttpServletRequest req)
	{	
		
		HttpSession session = req.getSession(true);
		DataFromBD data = new DataFromBD();
		RequestRue reqRue = new RequestRue();
		RequestImputation reqImputation = new RequestImputation();
		RequestBareme reqBareme = new RequestBareme();
		
		
		Vector listDesTypeTaxe = reqImputation.getLesDifferentTypesTaxe();
		Vector contenu1= reqRue.getListeQuartiers("");
		typeTaxeAutorise =  (String)session.getAttribute("typeTaxeAutorise");
		nomQuartier = StaticManipHtml.genererListeDeroulante("nomQuartier", 1,"", contenu1,true);
		
		Vector contenu2= new Vector ();			
		for (int i = 0; i < listDesTypeTaxe.size(); i++) 
		{
			contenu2.addElement(((Imputation)listDesTypeTaxe.elementAt(i)).getLibelle());
		}				
		listeTaxe = StaticManipHtml.genererListeDeroulante("typeTaxe", 1,"", contenu2,true);
				
		
		Vector contenu3=data.getTousLesSecteurs();
		numSecteur = StaticManipHtml.genererListeDeroulante("numSecteur", 1,"", contenu3,true);
		
				
		Vector contenu4= reqBareme.getTousLesBaremesUnique();
		listeBareme = StaticManipHtml.genererListeDeroulante("listeBareme", 1,"", contenu4,true);	
	
				
		Vector contenu5=data.getTousLesProffesions();
		ListeProfessions = StaticManipHtml.genererListeDeroulante("profession", 1,"", contenu5,true);		
		
		
	}
	
	public final String getListeTaxe()
	{
		return listeTaxe;
				
	}
	
	public final String getNomQuartier()
	{
		return 	nomQuartier;	
	}
	
	public final String getNumSecteur()
	{		
		return numSecteur;
	}
	
	public final String getListeBareme()
	{
		return listeBareme;
	}
	
	public final String getListeProfessions()
	{
		return ListeProfessions;		
	}	
}
