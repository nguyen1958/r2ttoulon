package fr.analogon.r2t.main;

import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.request.DataFromBD;
import fr.analogon.r2t.request.RequestEmplacement;
import fr.analogon.r2t.request.RequestRedevable;
import fr.analogon.r2t.request.RequestUtilisateur;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage pour le resultant d'une recherche de redevbale 
 * 
 * @version 2.0 . Sofien CHARFI
 * @since 2.0
 */
public class BaffResultatRechercheAlerte extends fr.analogon.r2t.main.RacineBean {

	//Parametres de recherche 
	
	private String resultatEmplacementLibre="",resultatEmplacementAModifier="";
	private String nombreEmplacementLibre="",nombreEmplacementAModifier="";
	private String codeSecteur="";
	private String typeTaxe="";
	private String nomVoieEmplacement="";
	private String numVoieEmplacement="";
	private String dateControle="";
	private String controleur="";
	StaticManipHtml mHtml = new StaticManipHtml();	
	DataFromBD data = new DataFromBD();
	RequestEmplacement reqEmplacement = new RequestEmplacement();
	 
	private void jbInit() throws Exception 
	{		
	}

	public void setRequest(HttpServletRequest request)
	{				
		/////////////////////////////////////////////////////////
		 Enumeration param = request.getParameterNames();
		 String paramRechercheRedevable="";
		 while (param.hasMoreElements()) 
		 {
			 //paramRechercheRedevable = paramRechercheRedevable+ request.getParameter((String)param.nextElement());
			 String attribut =(String)param.nextElement();
			 String value = request.getParameter(attribut);
			 //if( value.length() == 0 )value =" ";
			 paramRechercheRedevable=paramRechercheRedevable + "&"+attribut +"=" + value ; 		 			
		}
		System.out.println("PARAMETRE DE RECHERCHE " + paramRechercheRedevable);
		
		//Recuperation des parametres de recherche : 		
		if (request.getParameter("numSecteur") != null) { this. codeSecteur = request.getParameter("numSecteur");  }
		if (request.getParameter("numVoieEmplacement") != null) { this.numVoieEmplacement = request.getParameter("numVoieEmplacement");  }
		if (request.getParameter("nomVoieEmplacement") != null) { this.nomVoieEmplacement = request.getParameter("nomVoieEmplacement");  }
		if (request.getParameter("typeTaxe") != null) { this.typeTaxe = request.getParameter("typeTaxe");  }
		if (request.getParameter("dateControle") != null) { this.dateControle = request.getParameter("dateControle");  }
		if (request.getParameter("controleur") != null) { this.controleur = request.getParameter("controleur");  }
		/////////////////////////////////////////////////////////
		//Affichage du taleau resultat de la recherche: 
		
		Vector contenu =new Vector();
		contenu =reqEmplacement.getListeEmplacement("libre",codeSecteur, typeTaxe,numVoieEmplacement,nomVoieEmplacement,dateControle,controleur);
		resultatEmplacementLibre=mHtml.genererListeEmplacementLibre(contenu);		
		nombreEmplacementLibre="Nombre de resultats = "+contenu.size();
		contenu =new Vector();
		contenu =reqEmplacement.getListeEmplacement("aModifier",codeSecteur, typeTaxe,numVoieEmplacement,nomVoieEmplacement,dateControle,controleur);
		resultatEmplacementAModifier=mHtml.genererListeEmplacementAModifier(contenu);		
		nombreEmplacementAModifier="Nombre de resultats = "+contenu.size();
				
		contenu = null;
		System.gc();
		///////////////////////////////////////////////////////
	}

	/**
	 * @return the resultatEmplacementLibre
	 */
	public final String getResultatEmplacementLibre() {
		return resultatEmplacementLibre;
	}
	
	/**
	 * @return the resultatEmplacementAModifier
	 */
	public final String getResultatEmplacementAModifier() {
		return resultatEmplacementAModifier;
	}
	
	public String getNombreEmplacementLibre() {
		return nombreEmplacementLibre;
	}

	public String getNombreEmplacementAModifier() {
		return nombreEmplacementAModifier;
	}

	public String getNumSecteur(){
		Vector contenu3=data.getTousLesSecteurs();
		return StaticManipHtml.genererListeDeroulante("numSecteur", 1,codeSecteur, contenu3,true);
	}
	
	public String getTypeTaxe(){
		Vector contenu= new Vector();
		contenu.add("ETALAGE");
		contenu.add("ODP");
		contenu.add("TLPE");
		return StaticManipHtml.genererListeDeroulante("typeTaxe", 1,typeTaxe, contenu,true);		
	}
	
	public String getControleur(){
		Vector contenu3= (new RequestUtilisateur()).getListeUtilisateur("", "","","");		
		return StaticManipHtml.genererListeDeroulanteUser("controleur",contenu3,controleur);
	}

	public String getCodeSecteur() {
		return codeSecteur;
	}

	public String getNomVoieEmplacement() {
		return nomVoieEmplacement;
	}

	public String getNumVoieEmplacement() {
		return numVoieEmplacement;
	}
	
	public String getDateControle() {
		return dateControle;
	}
	
}
