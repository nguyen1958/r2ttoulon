package fr.analogon.r2t.view.export;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringEscapeUtils;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Bareme;
import fr.analogon.r2t.pojo.Imputation;
import fr.analogon.r2t.request.DataFromBD;
import fr.analogon.r2t.request.RequestBareme;
import fr.analogon.r2t.request.RequestFacture;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.request.RequestParametres;
import fr.analogon.r2t.request.RequestPayement;
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
	private String  listeProfessions;
	private String  listeEtatOuvrage;
	
	RequestParametres requestParametres = new RequestParametres();
	String ville = requestParametres.getVille();

	public void setRequest(HttpServletRequest req)
	{	
		System.out.println(">>>setRequest in BaffExport");
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
		listeProfessions = StaticManipHtml.genererListeDeroulante("profession", 1,"", contenu5,true);		
		
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
		/*
		String anneeExercice=GestionDate.getAnneeCourante();
		RequestBareme reqBareme = new RequestBareme();
	    Vector contenu=  reqBareme.getListeBareme("",anneeExercice,"","");
	    String liste="<select name='typeBareme' id='typeBareme'>\n";
   		liste+="<option value=''></option>";
	    for (int i = 0; i < contenu.size(); i++) 
	    {	    	
	    	Bareme bareme  = (Bareme)contenu.elementAt(i);
	    	liste+="<option value='"+bareme.getCode()+"'>"	
	    			+ bareme.getCode()+"-----"	       	
	    			+ StringEscapeUtils.escapeXml(bareme.getLibelle())+"-----";
	       	if (! bareme.getTypeTaxe().equalsIgnoreCase("TLPE"))
	       		liste+=bareme.getPrixUnit()+" euros-----";
	       	
	       	liste+=bareme.getUniteDeTravail();
	       	if(!bareme.getDureeUnitaire().equalsIgnoreCase("NON-PRECISE"))
	       		liste+="/"+bareme.getDureeUnitaire();	      				       	
	       	liste+="-----Tarif("+anneeExercice+")";
	        liste+="</option>\n";			
		}
	    return liste;
	    */
	}
	
	public final String getListeProfessions()
	{
		return listeProfessions;		
	}
	
	public String getEtatFacture()
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
	
	public String getEtatPaiement()
	{
		StaticManipHtml mHtml = new StaticManipHtml();
		RequestFacture req = new RequestFacture();
		Vector Contenu = req.getTousLesEtatsFacture();

		if ( ! ville.equalsIgnoreCase("bordeaux"))
		{
			Contenu.addElement("PAYEE");
			Contenu.addElement("IMPAYE");
		}
		return StaticManipHtml.genererListeDeroulante("etatPaiement", 1,"", Contenu,true);

	}
	
	public String getTypePaiement()
	{
		RequestPayement requestPayement = new RequestPayement();
		Vector listeDesTypePayement = requestPayement.getTousLesTypePayement();	
		return StaticManipHtml.genererListeDeroulanteTypePayement("typePaiement", "", listeDesTypePayement,true);
				
	}

	public String getListeEtatOuvrage() {
		RequestParametres requestParametres = new RequestParametres();
		Vector listeEtatOuvrage=requestParametres.getParemetres("etat_ouvrage");
		return StaticManipHtml.genererListeDeroulanteParametres("etatOuvrage", "", listeEtatOuvrage,true);	
	}
	
	
}
