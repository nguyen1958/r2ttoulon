package fr.analogon.r2t.administration;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.pojo.Utilisateur;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.request.RequestUtilisateur;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage des urulisateurs  . Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0
 */
public class BAffUtilisateur extends fr.analogon.r2t.main.RacineBean
{
	
	private HttpServletRequest request;	
	private String choix= "";	
	private boolean creation=true;	
	private String codeUtilisateur="";
	private String nomUtilisateur="";
	private String prenomUtilisateur="";
	private String typeUtilisateur="";
	private String actifUtilisateur="OUI";	
	private String loginUtilisateur="";	
	private String motDePaseeUtilisateur="";
	private String remarqueUtilisateur="";
	private String listeUtilisateur="";
	private String peutEtreSupprimer="";
	private String nombreUtulisateur="";	
	private String listeDesTypesDeTaxeAutorise="";
	private String boutonListeDesTypesDeTaxeAutorise="";
	
	
	public String getListeUtilisateur()
	{
		StaticManipHtml mHtml = new StaticManipHtml();	
		RequestUtilisateur requestUtilisateur  = new RequestUtilisateur(); 
		Vector contenu =requestUtilisateur.getListeUtilisateur(codeUtilisateur,
				nomUtilisateur,prenomUtilisateur,typeUtilisateur);				
		
		if( contenu.size() <200 )
		{
			nombreUtulisateur ="Nombre d'utilisateurs = "+contenu.size();
			listeUtilisateur = listeUtilisateur = StaticManipHtml.genererTableaulisteUtilisateur(contenu);
		}
		else
		{
			listeUtilisateur=" Le résultat contient plus que 200 utilisateurs, veuillez remplir un ou plusieurs champs dans les filtres de recherche ";
		}
		return listeUtilisateur;
		
	}


	public void setListeUtilisateur(String listeUtilisateur) {
		this.listeUtilisateur = listeUtilisateur;
	}


	public String getTypeUtilisateur()
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();		
		RequestUtilisateur  reqUtilisateur = new RequestUtilisateur();		
		Vector contenu = reqUtilisateur.getListeTypeUtilisateur();			
		res = StaticManipHtml.genererListeDeroulante("typeUtilisateur", 1,typeUtilisateur, contenu,true);
		return res;			
	}


	public void setTypeUtilisateur(String typeUtilisateur) {
		this.typeUtilisateur = typeUtilisateur;
	}

 
	public void setRequest(HttpServletRequest req)
	{	
				
		this.request = req;					
		
		if (request.getParameter("choix") != null && ! request.getParameter("choix").equalsIgnoreCase(" ") )
		{
			choix=request.getParameter("choix");
			
			if(choix.equalsIgnoreCase("ajouter"))
			{
				this.setCreation(true);
								
			}
			if(choix.equalsIgnoreCase("modifier") )
			{
				this.setCreation(false);	
				codeUtilisateur = req.getParameter("codeUtilisateur");
				
				RequestUtilisateur requestUtilisateur = new RequestUtilisateur();
				Utilisateur utilisateur = new Utilisateur();
				utilisateur = requestUtilisateur.getUtilisateur(codeUtilisateur,"","");				
				peutEtreSupprimer =""+ requestUtilisateur.peutEtreSupprimer(codeUtilisateur);
				this.nomUtilisateur = utilisateur.getNom();
				this.prenomUtilisateur = utilisateur.getPrenom();
				this.loginUtilisateur = utilisateur.getLogin();
				this.motDePaseeUtilisateur = utilisateur.getPassword();
				this.remarqueUtilisateur = utilisateur.getRemarque();
				this.actifUtilisateur = utilisateur.getValide();				
				this.typeUtilisateur = utilisateur.getTypeUtilisateur();
				//System.out.println("utilisateur.getListeDesTypesDeTaxeAutorise()="+utilisateur.getListeDesTypesDeTaxeAutorise());
				this.listeDesTypesDeTaxeAutorise= utilisateur.getListeDesTypesDeTaxeAutorise();
			}
			else if (request.getParameter("choix").equalsIgnoreCase("chercher"))
			{
				codeUtilisateur=request.getParameter("codeUtilisateur");
				nomUtilisateur=request.getParameter("nomUtilisateur");
				prenomUtilisateur=request.getParameter("prenomUtilisateur");
				typeUtilisateur=request.getParameter("typeUtilisateur");
			}
		}
	
	}


	public String getActifUtilisateur() 
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();				
		Vector contenu = new Vector();		
		contenu.addElement("OUI");
		contenu.addElement("NON");
		res = StaticManipHtml.genererListeDeroulante("actifUtilisateur", 1,actifUtilisateur, contenu,false);
		return res;
	}


	public void setActifUtilisateur(String actifUtilisateur) {
		this.actifUtilisateur = actifUtilisateur;
	}


	public String getChoix() {
		return choix;
	}


	public void setChoix(String choix) {
		this.choix = choix;
	}


	public String getCodeUtilisateur() {
		return codeUtilisateur;
	}


	public void setCodeUtilisateur(String codeUtilisateur) {
		this.codeUtilisateur = codeUtilisateur;
	}


	public boolean getCreation() {
		return creation;
	}


	public void setCreation(boolean creation) {
		this.creation = creation;
	}


	public String getMotDePaseeUtilisateur() {
		return motDePaseeUtilisateur;
	}


	public void setMotDePaseeUtilisateur(String motDePaseeUtilisateur) {
		this.motDePaseeUtilisateur = motDePaseeUtilisateur;
	}


	public String getNomUtilisateur() {
		return nomUtilisateur;
	}


	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}


	public String getPrenomUtilisateur() {
		return prenomUtilisateur;
	}


	public void setPrenomUtilisateur(String prenomUtilisateur) {
		this.prenomUtilisateur = prenomUtilisateur;
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public String getLoginUtilisateur() {
		return loginUtilisateur;
	}


	public void setLoginUtilisateur(String loginUtilisateur) {
		this.loginUtilisateur = loginUtilisateur;
	}


	public String getRemarqueUtilisateur() {
		return remarqueUtilisateur;
	}


	public void setRemarqueUtilisateur(String remarqueUtilisateur) {
		this.remarqueUtilisateur = remarqueUtilisateur;
	}


	public String getPeutEtreSupprimer() {
		return peutEtreSupprimer;
	}


	public void setPeutEtreSupprimer(String peutEtreSupprimer) {
		this.peutEtreSupprimer = peutEtreSupprimer;
	}


	/**
	 * @return the nombreUtulisateur
	 */
	public final String getNombreUtulisateur() {
		return nombreUtulisateur;
	}


	/**
	 * @param nombreUtulisateur the nombreUtulisateur to set
	 */
	public final void setNombreUtulisateur(String nombreUtulisateur) {
		this.nombreUtulisateur = nombreUtulisateur;
	}


	public String getListeDesTypesDeTaxeAutorise() 
	{
		return listeDesTypesDeTaxeAutorise;
	}


	public void setListeDesTypesDeTaxeAutorise(String listeDesTypesDeTaxeAutorise) {
		this.listeDesTypesDeTaxeAutorise = listeDesTypesDeTaxeAutorise;
	}


	public String getBoutonListeDesTypesDeTaxeAutorise() 
	{
		String res="";
		Vector contenu= new Vector ();	
		RequestImputation reqImputation = new RequestImputation();
		Vector listDesTypeTaxe = reqImputation.getLesDifferentTypesTaxe();
		for (int i = 0; i < listDesTypeTaxe.size(); i++) 
		{
			contenu.addElement(listDesTypeTaxe.elementAt(i));
		}
		res = StaticManipHtml.genererListeBoutonTypeDeTaxe("listeDesTypesDeTaxeAutorise",
				listeDesTypesDeTaxeAutorise, 
				contenu); 
		return res;
	}


	public void setBoutonListeDesTypesDeTaxeAutorise(
			String boutonListeDesTypesDeTaxeAutorise) {
		this.boutonListeDesTypesDeTaxeAutorise = boutonListeDesTypesDeTaxeAutorise;
	}

	
}



