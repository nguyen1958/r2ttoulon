package fr.analogon.r2t.rue;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.Rue;
import fr.analogon.r2t.request.DataFromBD;
import fr.analogon.r2t.request.RequestFamilleMarche;
import fr.analogon.r2t.request.RequestRue;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean de Reclamation . Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0
 */
public class BAffRue extends fr.analogon.r2t.main.RacineBean
{
	
	private HttpServletRequest request;	
	private String choix= "";	
	private boolean creation=true;	
	
	private String codeRue="";
	private String codeRivolieRue="";
	private String codePostal="33000";
	private String adresseRue="";
	private String debutNumeroRue="debut";
	private String finNumeroRue="fin";
	private String quartierRue="";
	private String remarqueRue="";
	private String liaisonAdresseRue="";
	private String prefixeAdresseRue="";
	private String nomAdresseRue="";
	private String listeQuartier="";
	private String listeRechercheRue="Aucune rue ne correspond a vos critères de recherche";
	private String nombreDeRue="";
	private String peutEtreSupprimer="";
	private String codeSecteur="";
	private String familleMarche="";
	
	public void setListeQuartier(String listeQuartier) {
		this.listeQuartier = listeQuartier;
	}

	//Setting Request 
	public void setRequest(HttpServletRequest req)
	{		
						
		this.request = req;
		//System.out.println("TESTREQ" +req.getAttribute(choix) );
		
		if (request.getParameter("choix") != null && ! request.getParameter("choix").equalsIgnoreCase(" ") )
		//if ( true )
		{		
			
			choix=request.getParameter("choix");
		
			if(choix.equalsIgnoreCase("ajouter"))
			{
				this.setCreation(true);	
				DebuggerLog4J.logger.debug("Creation d'une nouvelle rue " + codeRue);
			}
			else if(choix.equalsIgnoreCase("modifier") )
			{
				this.setCreation(false);	
				codeRue = req.getParameter("codeRue");
				DebuggerLog4J.logger.debug("MODIFICATION de la rue " + codeRue);				
				RequestRue  requestRue = new RequestRue();				
				Rue rue = new Rue();				
				rue = requestRue.getRue(codeRue);				
				this.peutEtreSupprimer =""+ requestRue.peutEtreSupprimer(codeRue);
				this.codeRue=rue.getCodeVoie();
				this.codeRivolieRue=rue.getCodeRivolie();
				this.adresseRue="";
				if(! rue.getDebutNumeroRue().equalsIgnoreCase("Debut"))
					this.debutNumeroRue=""+Integer.parseInt(rue.getDebutNumeroRue());
				if(! rue.getFinNumeroRue().equalsIgnoreCase("Fin"))
					this.finNumeroRue=""+Integer.parseInt(rue.getFinNumeroRue());
				this.quartierRue=rue.getNomquartier();
				this.remarqueRue=rue.getRemarqueRue();
				this.codePostal = rue.getCodePostal();
				this.liaisonAdresseRue=rue.getLiaison();
				this.prefixeAdresseRue=rue.getPrefixe();
				this.codeSecteur= rue.getCodeSecteur();
				this.familleMarche= rue.getFamilleMarche();
				this.nomAdresseRue= rue.getNomrue();				
				this.listeQuartier="";
				
			}
			else if (request.getParameter("choix").equalsIgnoreCase("chercher"))
			{				
				codeRivolieRue= request.getParameter("codeRivolieRue");
				adresseRue= request.getParameter("adresseRue");
				quartierRue= request.getParameter("quartierRue");		
			}
		}
		
		
		
	
	}

	public String getChoix() {
		return choix;
	}


	public void setChoix(String choix) {
		this.choix = choix;
	}


	public String getCodeRue() {
		return codeRue;
	}


	public void setCodeRue(String codeRue) {
		this.codeRue = codeRue;
	}


	public boolean getCreation() {
		return creation;
	}


	public void setCreation(boolean creation) {
		this.creation = creation;
	}

	public HttpServletRequest getRequest() {
		return request;
	}


	public String getRemarqueRue() {
		return remarqueRue;
	}


	public void setRemarqueRue(String remarqueRue) {
		this.remarqueRue = remarqueRue;
	}


	public String getPeutEtreSupprimer() {
		return peutEtreSupprimer;
	}


	public void setPeutEtreSupprimer(String peutEtreSupprimer) {
		this.peutEtreSupprimer = peutEtreSupprimer;
	}


	/**
	 * @return the adresseRue
	 */
	public final String getAdresseRue() {
		return adresseRue;
	}


	/**
	 * @param adresseRue the adresseRue to set
	 */
	public final void setAdresseRue(String adresseRue) {
		this.adresseRue = adresseRue;
	}


	/**
	 * @return the codeRivolieRue
	 */
	public final String getCodeRivolieRue() {
		return codeRivolieRue;
	}


	/**
	 * @param codeRivolieRue the codeRivolieRue to set
	 */
	public final void setCodeRivolieRue(String codeRivolieRue) {
		this.codeRivolieRue = codeRivolieRue;
	}


	/**
	 * @return the debutNumeroRue
	 */
	public final String getDebutNumeroRue() 
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();		
		DataFromBD data = new DataFromBD();
		Vector contenu = new Vector();			
		for (int i = 1; i < 500; i++)
		{
			contenu.addElement(""+i);
		}
		res = StaticManipHtml.genererListeDeroulante("debutNumeroRue", 1,debutNumeroRue, contenu,false);
		return res;
	}


	/**
	 * @param debutNumeroRue the debutNumeroRue to set
	 */
	public final void setDebutNumeroRue(String debutNumeroRue) {
		this.debutNumeroRue = debutNumeroRue;
	}


	/**
	 * @return the finNumeroRue
	 */
	public final String getFinNumeroRue() 
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();		
		DataFromBD data = new DataFromBD();
		Vector contenu = new Vector();
		contenu.addElement("500");	
		for (int i = 1; i < 501; i++)
		{
			contenu.addElement(""+i);
		}
		res = StaticManipHtml.genererListeDeroulante("finNumeroRue", 1,finNumeroRue, contenu,false);
		return res;
	}


	/**
	 * @param finNumeroRue the finNumeroRue to set
	 */
	public final void setFinNumeroRue(String finNumeroRue) {
		this.finNumeroRue = finNumeroRue;
	}


	/**
	 * @return the liaisonAdresseRue
	 */
	public final String getLiaisonAdresseRue() {
		return liaisonAdresseRue;
	}


	/**
	 * @param liaisonAdresseRue the liaisonAdresseRue to set
	 */
	public final void setLiaisonAdresseRue(String liaisonAdresseRue) {
		this.liaisonAdresseRue = liaisonAdresseRue;
	}


	/**
	 * @return the nomAdresseRue
	 */
	public final String getNomAdresseRue() {
		return nomAdresseRue;
	}


	/**
	 * @param nomAdresseRue the nomAdresseRue to set
	 */
	public final void setNomAdresseRue(String nomAdresseRue) {
		this.nomAdresseRue = nomAdresseRue;
	}


	/**
	 * @return the prefixeAdresseRue
	 */
	public final String getPrefixeAdresseRue() {
		return prefixeAdresseRue;
	}


	/**
	 * @param prefixeAdresseRue the prefixeAdresseRue to set
	 */
	public final void setPrefixeAdresseRue(String prefixeAdresseRue) {
		this.prefixeAdresseRue = prefixeAdresseRue;
	}


	/**
	 * @return the quartierRue
	 */
	public final String getQuartierRue() 
	{
		return quartierRue;
	}




	/**
	 * @return the listeRue
	 */
	public final String getListeQuartier() 
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();		
		RequestRue reqRue = new RequestRue();
		Vector contenu= reqRue.getListeQuartiers("");
		DebuggerLog4J.logger.debug("nombre de quartier"+ contenu.size())  ;
		res = StaticManipHtml.genererListeDeroulante("quartierRue", 1,quartierRue, contenu,true);
		//res= contenu.toString();
		return res;
		}

	/**
	 * @param quartierRue the quartierRue to set
	 */
	public final void setQuartierRue(String quartierRue) {
		this.quartierRue = quartierRue;
	}

	/**
	 * @return the listeRechercheRue
	 */
	public final String getListeRechercheRue() 
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();	
		RequestRue  requestRue = new RequestRue();
				Vector contenu = requestRue.getListeRue(codeRivolieRue,	adresseRue,	quartierRue) ;
		
		if( contenu.size() <200 )
		{
			nombreDeRue ="Nombre de rues = "+contenu.size();
			listeRechercheRue = StaticManipHtml.genererTableaulisteRue(contenu);
		}
		else
		{
			listeRechercheRue=" Le résultat contient plus que 200 rues, veuillez remplir un ou plusieurs champs dans les filtres de recherche ";
		}
		return listeRechercheRue;
		
	}

	/**
	 * @param listeRechercheRue the listeRechercheRue to set
	 */
	public final void setListeRechercheRue(String listeRechercheRue) {
		this.listeRechercheRue = listeRechercheRue;
	}

	/**
	 * @return the nombreDeRue
	 */
	public final String getNombreDeRue() {
		return nombreDeRue;
	}

	/**
	 * @param nombreDeRue the nombreDeRue to set
	 */
	public final void setNombreDeRue(String nombreDeRue) {
		this.nombreDeRue = nombreDeRue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getCodeSecteur() {
		return codeSecteur;
	}

	public void setCodeSecteur(String codeSecteur) {
		this.codeSecteur = codeSecteur;
	}

	public String getFamilleMarche() 
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		RequestFamilleMarche requestFamilleMarche = new RequestFamilleMarche();
		Vector listFamilleMarche = requestFamilleMarche.getListeFamilleMarche("", "");
		res = StaticManipHtml.genererListeDeroulanteFamilleMarche("familleMarche", familleMarche, listFamilleMarche, true);
		return res;
	}

	public void setFamilleMarche(String familleMarche) {
		this.familleMarche = familleMarche;
	}
}



