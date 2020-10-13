package fr.analogon.r2t.view.export;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.analogon.r2t.pojo.Imputation;
import fr.analogon.r2t.request.DataFromBD;
import fr.analogon.r2t.request.RequestBareme;
import fr.analogon.r2t.request.RequestFacture;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.request.RequestParametres;
import fr.analogon.r2t.request.RequestRue;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage pour la recherche de export
 * 
 * @version 2.0 . Sofien CHARFI
 * @since 2.0
 */
public class BaffExport extends fr.analogon.r2t.main.RacineBean {

	private String  nomQuartier ;
	private String  listeTaxe;
	private String  numSecteur;
	private String  listeBareme;
	private String  ListeProfessions;
	
	RequestParametres requestParametres = new RequestParametres();
	String ville = requestParametres.getVille();

	public void setRequest(HttpServletRequest req)
	{	
		
		DataFromBD data = new DataFromBD();
		RequestImputation reqImputation = new RequestImputation();
		RequestRue reqRue = new RequestRue();
		Vector contenu1= reqRue.getListeQuartiers("");
		nomQuartier = StaticManipHtml.genererListeDeroulante("nomQuartier", 1,"", contenu1,true);
		
		Vector listDesTypeTaxe = reqImputation.getLesDifferentTypesTaxe();
		
		Vector contenu2= new Vector ();			
		for (int i = 0; i < listDesTypeTaxe.size(); i++) 
		{
			contenu2.addElement(((Imputation)listDesTypeTaxe.elementAt(i)).getLibelle());
		}				
		listeTaxe = StaticManipHtml.genererListeDeroulante("typeTaxe", 1,"", contenu2,true);
				
		
		Vector contenu3=data.getTousLesSecteurs();
		numSecteur = StaticManipHtml.genererListeDeroulante("numSecteur", 1,"", contenu3,true);
		
				
		Vector contenu4= new Vector(); //reqBareme.getTousLesBaremesUnique();
		listeBareme = StaticManipHtml.genererListeDeroulante("typeBareme", 1,"", contenu4,true);	
	
				
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
	
	public String getetatFacture()
	{
		StaticManipHtml mHtml = new StaticManipHtml();
		RequestFacture req = new RequestFacture();
		Vector Contenu = req.getTousLesEtatsFacture();

		if ( ! ville.equalsIgnoreCase("bordeaux"))
		{
			Contenu.addElement("PAYEE");
			Contenu.addElement("IMPAYE");
		}
		String res = StaticManipHtml.genererListeDeroulante("etatFacture", 1,"", Contenu,true);
		//String res= StaticManipHtml.genererListeDeroulanteApartirDeVacteur("etatFacture", 
		//		etatFacture, Contenu, 1, true);
		return res;
		
	}
}
