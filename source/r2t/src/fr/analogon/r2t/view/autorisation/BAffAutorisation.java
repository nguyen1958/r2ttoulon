package fr.analogon.r2t.view.autorisation;

import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.Autorisation;
import fr.analogon.r2t.pojo.Emplacement;
import fr.analogon.r2t.pojo.Redevable;

import fr.analogon.r2t.pojo.Imputation;
import fr.analogon.r2t.request.DataFromBD;
import fr.analogon.r2t.request.RequestAutorisation;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.request.RequestParametres;
import fr.analogon.r2t.request.RequestRedevable;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de l'autoristion . Paul
 * 
 * @version 2.0
 * @since 2.0
 */
public class BAffAutorisation extends fr.analogon.r2t.main.RacineBean
{
	
	private HttpServletRequest request;	
	private String choix= "";
	private String idautorisation="";
	private boolean creation=true;
	private String anneeExercice;
	private String resultatRecherche="";
	private String nombreAutorisation="";
	private Autorisation autorisation;
	private Redevable redevable;
	private Emplacement emplacement;
	
	private RequestRedevable reqRedevable = new RequestRedevable();
	private RequestAutorisation reqAutorisation = new RequestAutorisation();

	//Setting Request 
	public void setRequest(HttpServletRequest req)
	{	
			
		this.request = req;
		choix=req.getParameter("choix");
		anneeExercice = GestionDate.getAnneeCourante();
		
		StaticManipHtml mHtml = new StaticManipHtml();
		DebuggerLog4J.logger.debug("PARAMETRE DE RECHERCHE " + req.getQueryString());
		//Ceci vient de l'écran liste
		idautorisation= req.getParameter("idautorisation");
		if (idautorisation!=null){
			this.autorisation = reqAutorisation.getAutorisation(idautorisation);
		}
		
		System.out.println(">>>>>> BAffAutorisation choix= "+choix);
		
		if (choix!=null && choix.equalsIgnoreCase("rechercher")){					
			//Ceci vient de l'écran recherche
			//Recuperation des parametres de recherche : 
			String numAutorisation= req.getParameter("numAutorisation");
			String dateCreation= req.getParameter("dateCreation");
			String refDossier= req.getParameter("refDossier");
			String nomRedevable= req.getParameter("nomRedevable");
			String adresseEmplacement= req.getParameter("adresseEmplacement");
			String typeDeTaxe= req.getParameter("typeDeTaxe");
			String etatAutorisation= req.getParameter("etatAutorisation");
			
			//System.out.println(">>>>>> "+dateCreation+":"+nomRedevable+":"+adresseEmplacement+":"+typeDeTaxe+":"+etatAutorisation);
			
			/////////////////////////////////////////////////////////
			//Affichage du taleau resultat de la recherche: 
			
			Vector contenu =new Vector();
			
			if (numAutorisation!=null && numAutorisation.length()!=0)
			{
			contenu =reqAutorisation.getListeAutorisation(numAutorisation);
			}
			else
			{
			contenu =reqAutorisation.getListeAutorisation(refDossier,dateCreation, nomRedevable,adresseEmplacement,typeDeTaxe, etatAutorisation);			
			}
						
			resultatRecherche =  mHtml.genererListeAutorisation(contenu);
			nombreAutorisation = "Nombre de resultats = "+contenu.size();
			contenu = null;
			System.gc();			
			
		}
		
	}

	/**
	 * @return the anneeExercice
	 */
	public final String getAnneeExercice()
	{	
		return anneeExercice;
	}

	/**
	 * @param anneeExercice the anneeExercice to set
	 */
	public final void setAnneeExercice(String anneeExercice) {
		this.anneeExercice = anneeExercice;
	}

	/**
	 * @return the choix
	 */
	public final String getChoix() {
		return choix;
	}

	/**
	 * @param choix the choix to set
	 */
	public final void setChoix(String choix) {
		this.choix = choix;
	}


	/**
	 * @return the creation
	 */
	public final boolean getCreation() {
		return creation;
	}

	/**
	 * @param creation the creation to set
	 */
	public final void setCreation(boolean creation) {
		this.creation = creation;
	}


	/**
	 * @return the request
	 */
	public final HttpServletRequest getRequest() {
		return request;
	}
	

	public String getResultatRecherche() {
		return resultatRecherche;
	}

	public String getNombreAutorisation() {
		return nombreAutorisation;
	}

	public Autorisation getAutorisation() {
		return autorisation;
	}

	public void setAutorisation(Autorisation autorisation) {
		this.autorisation = autorisation;
	}
	
	public Redevable getRedevable() {
		return autorisation!=null?reqRedevable.getRedevable(autorisation.getNumRedevable()):null;
	}

	public void setRedevable(Redevable redevable) {
		this.redevable = redevable;
	}

	public void setResultatRecherche(String resultatRecherche) {
		this.resultatRecherche = resultatRecherche;
	}

	public void setNombreAutorisation(String nombreAutorisation) {
		this.nombreAutorisation = nombreAutorisation;
	}

	public Emplacement getEmplacement() {
		return  autorisation!=null?reqAutorisation.getEmplacement(autorisation.getIdAutorisation()):null;
	}

	public void setEmplacement(Emplacement emplacement) {
		this.emplacement = emplacement;
	}

	public String getIdautorisation() {
		return idautorisation;
	}

	public void setIdautorisation(String idautorisation) {
		this.idautorisation = idautorisation;
	}

	public final String getComplNumRue()
	{
		String res="";
		DataFromBD data = new DataFromBD();
		Vector contenu = new Vector();		
		contenu= data.getTousComplementNumRue();		
		res = StaticManipHtml.genererListeDeroulante("complNumRue", 1,"", contenu,true);
		return res;
		
	}
	
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
				("typeDeTaxe", 1,"", contenu,true);
		//res= contenu.toString();
		return res;	
	}
	
	
	public String getListeEtat(){
		String contenu="";
		String etat=this.getAutorisation().getEtat();
		contenu+="<select size='1' name='etatAutorisation'>";
		contenu+="<option value='En cours'"+(etat.equalsIgnoreCase("En cours")?"selected":"")+">En cours</option>";
		contenu+="<option value='Acceptée'"+(etat.equalsIgnoreCase("Acceptée")?"selected":"")+">Acceptée</option>";
		contenu+="<option value='Refusée'"+(etat.equalsIgnoreCase("Refusée")?"selected":"")+">Refusée</option>";
		contenu+="</select>"; 
		
		return contenu;
		
	}
	
	public String getListeOuvrageAutorise()	
    {
      
		RequestAutorisation req = new RequestAutorisation();	
		Vector contenu = req.getListeOuvrageAutorise(autorisation.getIdAutorisation());
		StaticManipHtml html = new StaticManipHtml();		
		String res= html.getListeOuvrageAutorise(contenu,autorisation.getEtat());
		contenu = null;
		System.gc();
		return res;
         
    }
	
	public String getVilleMairie() {		 
		RequestParametres req= new RequestParametres();
		 return req.getVille();
	 }
	
	public String getCpMairie() {		 
		RequestParametres req= new RequestParametres();
		 return req.getCodePotal();
	 }

}