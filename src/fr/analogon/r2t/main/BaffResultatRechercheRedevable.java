package fr.analogon.r2t.main;

import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.request.RequestRedevable;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage pour le resultant d'une recherche de redevbale 
 * 
 * @version 2.0 . Sofien CHARFI
 * @since 2.0
 */
public class BaffResultatRechercheRedevable extends fr.analogon.r2t.main.RacineBean {

	//Parametres de recherche 	
	private String resultatRecherche="";
	private String numRedevable="";	
	private String nomRedevable="";	
	private String codeVoie="";
	private String raisonSociale=""; 
	private String profession="";
	private String codeSecteur="";
	private String codeEmplacement="";	
	private String exercice="";
	private String nomQuartier="";
	private String libelleBareme="";
	private String typeTaxe="";
	private String nomVoieEmplacement="";
	private String numVoieEmplacement="";
	private String numEmplacement="";
	private String etatEmplacement="";
	private String nombreRedevable="";	
	private String redevbaleActif="";

	StaticManipHtml mHtml = new StaticManipHtml();	
	RequestRedevable reqRedevable = new RequestRedevable();
	 
	/**
	 * @return the resultatRecherche
	 */
	public final String getResultatRecherche() {
		return resultatRecherche;
	}

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
		DebuggerLog4J.logger.debug("PARAMETRE DE RCHERCHE " + paramRechercheRedevable);
		
		//Recuperation des parametres de recherche : 		
		if (request.getParameter("numRedevable") != null) { this.numRedevable = request.getParameter("numRedevable");  }
		if (request.getParameter("nomRedevable") != null) { this.nomRedevable = request.getParameter("nomRedevable");  }
		if (request.getParameter("raisonSociale") != null) { this.raisonSociale = request.getParameter("raisonSociale");  }
		if (request.getParameter("numEmplacement") != null) { this.numEmplacement = request.getParameter("numEmplacement");  }
		if (request.getParameter("etatEmplacement") != null) { this.etatEmplacement = request.getParameter("etatEmplacement");  }
		if (request.getParameter("numSecteur") != null) { this. codeSecteur = request.getParameter("numSecteur");  }
		if (request.getParameter("nomQuartier") != null) { this.nomQuartier = request.getParameter("nomQuartier");  }
		if (request.getParameter("numVoieEmplacement") != null) { this.numVoieEmplacement = request.getParameter("numVoieEmplacement");  }
		if (request.getParameter("nomVoieEmplacement") != null) { this.nomVoieEmplacement = request.getParameter("nomVoieEmplacement");  }
		if (request.getParameter("typeTaxe") != null) { this.typeTaxe = request.getParameter("typeTaxe");  }
		if (request.getParameter("listeBareme") != null) { this.libelleBareme = request.getParameter("listeBareme");  }
		if (request.getParameter("profession") != null) { this.profession = request.getParameter("profession");  }
		if (request.getParameter("redevbaleActif") != null) { this.redevbaleActif = request.getParameter("redevbaleActif");  }
		
		/////////////////////////////////////////////////////////
		//Affichage du taleau resltat de la recherche: 
		
		Vector contenu =new Vector();
		
		if(numRedevable.length()!=0)
		{
			contenu =reqRedevable.getListeRedevable(numRedevable,"", "",
					"","","","","","","","","","","","","");
		}
		else
		{
				contenu =reqRedevable.getListeRedevable("",nomRedevable, codeVoie,
				raisonSociale, profession,  codeSecteur,codeEmplacement,  exercice,	
				nomQuartier , libelleBareme,  typeTaxe, nomVoieEmplacement, numVoieEmplacement,
				numEmplacement, etatEmplacement,redevbaleActif);			
		}
		
		resultatRecherche =  mHtml.genererListeRedevable(contenu,etatEmplacement);
		
		
		nombreRedevable = "Nombre de resultats = "+contenu.size();
		contenu = null;
		System.gc();
		///////////////////////////////////////////////////////
	}

	/**
	 * @param resultatRecherche the resultatRecherche to set
	 */
	public final void setResultatRecherche(String resultatRecherche) {
		this.resultatRecherche = resultatRecherche;
	}

	/**
	 * @return the nombreRedevable
	 */
	public final String getNombreRedevable() {
		return nombreRedevable;
	}

	/**
	 * @param nombreRedevable the nombreRedevable to set
	 */
	public final void setNombreRedevable(String nombreRedevable) {
		this.nombreRedevable = nombreRedevable;
	}	
	
	
	
}
