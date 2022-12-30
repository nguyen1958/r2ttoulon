package fr.analogon.r2t.view.regie;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.main.ConfVariable;
import fr.analogon.r2t.pojo.Emplacement;
import fr.analogon.r2t.pojo.Redevable;
import fr.analogon.r2t.request.RequestBareme;
import fr.analogon.r2t.request.RequestEmplacement;
import fr.analogon.r2t.request.RequestOuvrage;
import fr.analogon.r2t.request.RequestRedevable;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage des de la liste des elements de facturation d'un redevable .
 * Sofien CHARFI
 * 
 * @version 1.0
 */

public class BAffListeElementFacturation extends fr.analogon.r2t.main.RacineBean 
{
	
	private String anExercice = "";
	private String numEmplacement = "";	
	private String  typeTaxe="";
	private String libelleImputation = "";
	private String numRedevable = "";	
	private String listeElementsFacturation="";	
	private String raisonSocialEmplacement="";	
	private String lienEmplacement="";
	private String nomRd = "";
	private String prenomRd = "";
	private String civiliteRd = "";
	private String codeSecteur = "";
	private String necessiteControleTerrain="";
	private String choix = "";
	private String chainecCaractereEF = "";	
	private String adresseComplete="";				

	HttpServletRequest request;	
	RequestEmplacement requestEmplacement = new RequestEmplacement();
	RequestRedevable requestRedevable= new RequestRedevable();
	private static double SommeTotal=0.00;
	Emplacement emplacement = null;
	
	public BAffListeElementFacturation() {
		super();

		try 
		{
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	/**
	 * Methode set permettant declarer un objet httprequest
	 * 
	 * @since 1.0
	 */
	public void setRequest(HttpServletRequest req)
	{			
		this.request = req;
		this.choix = request.getParameter(ConfVariable.valVariableChoix);
		this.numEmplacement = request.getParameter("numEmplacement");
		this.anExercice = GestionDate.getAnneeFromDateString(GestionDate.getDateAujourdhuiString());
		
	
		if(numEmplacement != null && ! numEmplacement.equalsIgnoreCase("0"))
		{
			
			emplacement =  requestEmplacement.getEmplacement(numEmplacement);
			libelleImputation = emplacement.getLibelleImputation();
			necessiteControleTerrain= emplacement.getNecessiteControleTerrain();
			numRedevable = emplacement.getNumeroRedevable();
			//System.out.println("numRedevable="+numRedevable);
			
			codeSecteur = emplacement.getCodeSecteur();
			Redevable redevable = requestRedevable.getRedevable(numRedevable);
			nomRd = redevable.getNomRedevable();
			prenomRd = redevable.getPrenomRedevable();
			civiliteRd = redevable.getCivilite();		
		}				
		else
		{
			
		}	
		/*
		this.libelleImputation = request.getParameter("libelleImputation");
		this.anExercice = GestionDate.getAnneeFromDateString(GestionDate.getDateAujourdhuiString());
		this.choix = request.getParameter(ConfVariable.valVariableChoix);		
		this.numEmplacement = request.getParameter("numEmplacement");		
		this.numRedevable = request.getParameter(ConfVariable.varHTMLNumRedevable);
		this.codeSecteur = request.getParameter(ConfVariable.varHTMLCodeSecteur);
		//this.codeEmplacement = request.getParameter(ConfVariable.varHTMLCodeEmplacement);
		*/
		
	}


	
	/**
	 * Retourne la liste des elements de facturation existants pour
	 * l'emplacements.
	 */

	public String getListeElementsFacturation()
	{
		StaticManipHtml mHtml = new StaticManipHtml();
		RequestOuvrage reqOuvrage= new RequestOuvrage();		
		Vector contenu = reqOuvrage.getListeOuvrage( this.numEmplacement,GestionDate.getAnneeCourante());		
		this.listeElementsFacturation =mHtml.genererTableauListeElementDeFacturation(contenu);	
		return listeElementsFacturation;
	}
	
	
	

	public String getListeCodeBareme() 
	{		
		String res = "Aucun bareme disponible. ";		
		StaticManipHtml mHtml = new StaticManipHtml();
		RequestBareme reqBareme = new RequestBareme();		
		Vector contenu= reqBareme.getListeBareme(libelleImputation,anExercice);		
		res = mHtml.genererListeDeroulanteListeBareme("codeBareme", 1,	"", contenu,false);	
		return res;	
	}

	

	/**
	 * @return the libelleImputation
	 */
	public final String getLibelleImputation() {
		return libelleImputation;
	}

	/**
	 * @param libelleImputation the libelleImputation to set
	 */
	public final void setLibelleImputation(String libelleImputation) {
		this.libelleImputation = libelleImputation;
	}

	public String getAnExercice() {
		return anExercice;
	}

	public String getNumEmplacement() {
		return numEmplacement;
	}

	public String getNumRedevable() {
		return numRedevable;
	}


	
	

	/**
	 * @return the adresseComplete
	 */
	public String getAdresseComplete() 
	{							
		this.adresseComplete=emplacement.getAdresseComplete();
		return adresseComplete;
	}

	/**
	 * @param adresseComplete the adresseComplete to set
	 */
	public void setAdresseComplete(String adresseComplete) {
		this.adresseComplete = adresseComplete;
	}

	public String getCodeSecteur() {
		return codeSecteur;
	}

	public String getChoix() {
		return choix;
	}

	

	public String getChainecCaractereEF() {
		return chainecCaractereEF;
	}



	public String getHtmlLienVersRd()
	{
		String res = "";
		
			
		res = "<a href=\""
				+ conf.getPreUrlJbin()
				+ "entree?action=empl_gestion_redevable.jsp&numRedevable=" +numRedevable
				+ "&choix=modifier&boton=ajouter&typeRecherche=role" 
				+ "&optionTransfert=role&typeRedevable=normal&origine=origine\">";

		RequestRedevable req= new RequestRedevable();
		Redevable red =req.getRedevable(numRedevable);
		res =res + red.getRaisonSocialeRedevable() + " " + red.getPrenomRedevable() + " " + red.getNomRedevable() ;
		res= res+  "</a>";		
		return res;
	}


	
	
	
	public  String getNumeroEF()
	{
		return this.numEmplacement ;
	}

	

	/**
	 * @return the lienEmplacement
	 */
	public String getLienEmplacement() 
	{				
		this.lienEmplacement="./entree?action=empl_gestion_emplacementodp.jsp" +
				"&choix=modifier&optionTransfert=role&origine=origine" +
				"&numEmplacment="+this.numEmplacement;		
		return lienEmplacement;
	}

	/**
	 * @param lienEmplacement the lienEmplacement to set
	 */
	public void setLienEmplacement(String lienEmplacement) {
		this.lienEmplacement = lienEmplacement;
	}

	/**
	 * @return the raisonSocialEmplacement
	 */
	public String getRaisonSocialEmplacement()
	{
		RequestEmplacement reqEmplacment = new RequestEmplacement();
		this.raisonSocialEmplacement=emplacement.getRaisonSocial();
		return raisonSocialEmplacement;
	}

	/**
	 * @param raisonSocialEmplacement the raisonSocialEmplacement to set
	 */
	public void setRaisonSocialEmplacement(String raisonSocialEmplacement) {
		this.raisonSocialEmplacement = raisonSocialEmplacement;
	}

	/**
	 * @return the typeTaxe
	 */
	public final String getTypeTaxe() {
		return typeTaxe;
	}

	/**
	 * @param typeTaxe the typeTaxe to set
	 */
	public final void setTypeTaxe(String typeTaxe) {
		this.typeTaxe = typeTaxe;
	}
	
	public boolean verfierAcces(String listeLibelleDesTypesDeTaxeAutorise) 
	{
		boolean res=false;	
		if(listeLibelleDesTypesDeTaxeAutorise.indexOf(libelleImputation) != -1)
			res= true; 
		return res;
	}


	/**
	 * @return the necessiteControleTerrain
	 */
	public String getNecessiteControleTerrain() {
		return necessiteControleTerrain;
	}


	/**
	 * @param necessiteControleTerrain the necessiteControleTerrain to set
	 */
	public void setNecessiteControleTerrain(String necessiteControleTerrain) {
		this.necessiteControleTerrain = necessiteControleTerrain;
	}
	
	
}


