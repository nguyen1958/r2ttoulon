package fr.analogon.r2t.reclamation;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Reclamation;
import fr.analogon.r2t.request.DataFromBD;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.request.RequestReclamation;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de reclamation . Sofien CHARFI
 * 
 * @version 1.0
 * @since 1.0
 */
public class BAffReclamation extends fr.analogon.r2t.main.RacineBean
{
	
	private String choix= "";		
	private HttpServletRequest request;	
	public String dateReclamation="";	
	public String idReclamation="";	
	public String idRedevable="";	
	public String idFactue="";	
	public String typeTaxe="";	
	public String natureReclamation="";	
	public String  texteReclamation="";	
	public String motsclef="";	
	public String pieceJointe="";	
	public String etatReclamation="";	
	public String dateReponse="";	
	public String texteReponse="";	
	public String controleEffectue="";	
	public String typeReceptionReclamation="";		
	private boolean creation=false;	
	private String dateContole="";	
	private String nomControleur="";	
	private String numControleur="";	
	private String nomRedevable="";	
	private String listeOuvrage="";
	private String lienInfosFacture="";
	private String listeModelesCouriers="";
	private String listeCourierReponse="";
	private String cheminDataCourier="";
	
	

	/**
	 * @return the listeModelesCouriers
	 */
	public final String getListeModelesCouriers() {
		return listeModelesCouriers;
	}

	/**
	 * @param listeModelesCouriers the listeModelesCouriers to set
	 */
	public final void setListeModelesCouriers(String listeModelesCouriers) {
		this.listeModelesCouriers = listeModelesCouriers;
	}

	/**
	 * @return the numControleur
	 */
	public final String getNumControleur() {
		return numControleur;
	}

	/**
	 * @param numControleur the numControleur to set
	 */
	public final void setNumControleur(String numControleur) {
		this.numControleur = numControleur;
	}

	/**
	 * @return the dateContole
	 */
	public String getDateContole() {
		return dateContole;
	}

	/**
	 * @param dateContole the dateContole to set
	 */
	public void setDateContole(String dateContole) {
		this.dateContole = dateContole;
	}

	

	public void setRequest(HttpServletRequest req)
	{

		
		//System.out.println("set request");
		this.request = req;
		if (request.getParameter("dateReclamation") != null 
				&& ! request.getParameter("dateReclamation").equalsIgnoreCase(" ") )
		this.dateReclamation = request.getParameter("dateReclamation");		
		
		
		if (request.getParameter("choix") != null 
				&& ! request.getParameter("choix").equalsIgnoreCase(" ") )
		this.choix = request.getParameter("choix");
		//System.out.println("choix="+choix);
		
		if (request.getParameter("idReclamation") != null 
				&& ! request.getParameter("idReclamation").equalsIgnoreCase(" ") )
		this.idReclamation = request.getParameter("idReclamation");
		//System.out.println("idReclamation="+idReclamation);
		
		
		if(choix.equalsIgnoreCase("ajouter"))
		{
			this.creation=true;
			setdateReclamation(GestionDate.getDateAujourdhuiString());
		}
		else if(choix.equalsIgnoreCase("modifier"))
		{
			this.creation=false;	
			RequestReclamation reqReclam= new RequestReclamation();
			Vector contenu= reqReclam.getReclamation(Integer.parseInt(idReclamation));
			Reclamation reclamation= (Reclamation)contenu.elementAt(0);
			this.setidReclamation(String.valueOf(reclamation.getIdReclamation()));
			this.setdateReclamation(reclamation.getDateReclamation());
			this.setidRedevable(String.valueOf(reclamation.getIdRedevable()));
			this.settypeTaxe(reclamation.getTypeTaxe());
			this.settypeReceptionReclamation(reclamation.getTypeReception());
			this.setcontroleEffectue(reclamation.getControleEffectue());
			this.setdateReponse(reclamation.getDateReponse());
			this.setidFactue(String.valueOf(reclamation.getIdFacture()));
			this.setetatReclamation(reclamation.getEtatReclamation());			
			this.setnatureReclamation(reclamation.getnatureReclamation());
			this.settexteReclamation(reclamation.getTextReclamation());		
			this.settexteReponse(reclamation.getTexteReponse());
			this.setNomControleur(reclamation.getNomContoleur());
			this.setNumControleur(reclamation.getNumContoleur());
			this.setDateContole(reclamation.getDateContole());
			this.setNomRedevable(reclamation.getNomRedevable());
			this.setListeOuvrage(reclamation.getListeOuvrage());
			this.lienInfosFacture="<a  href=./entree?action=gestionFacture.jsp&numeroFacture="+ reclamation.getIdFacture() + ">" +
								"Annuler la facture numéro "+reclamation.getIdFacture()+".pdf" +"</a>";
			
			
			if(gettexteReponse()==null)
				settexteReponse("");
			if(getDateContole()==null)
				setDateContole("");
			if(getdateReponse()==null )
				setdateReponse("");		
			if(gettexteReclamation()==null)
				settexteReclamation("");
			if(getnatureReclamation()==null)
				setnatureReclamation("");
			if(getidFactue()==null)
				setidFactue("");
			if(getidRedevable()==null)
				setidRedevable("");			
			Vector contenuModele = reqReclam.getTousLesModlesCouriers();
			listeModelesCouriers = StaticManipHtml.genererListeModelesCouriers("modeleCourier", contenuModele);
			//listeModelesCouriers ="TEST";
			
			Vector listeDesReponse = reqReclam.getListeDesCourierReponse(idReclamation) ; 
			listeCourierReponse = StaticManipHtml.genererTableauListeDesCourier(listeDesReponse);
			
			this.cheminDataCourier = fichierDeConfiguration.getCheminDataCourier();
		}
	}	
	
	public boolean getCreation()
	{
		return creation;
	}
	
	
	public String gettypeTaxe()
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();		
		RequestImputation reqImputation = new RequestImputation();
		Vector contenu = reqImputation.getLesDifferentTypesTaxe();	
		Vector contenu2= new Vector();
		contenu2.addElement(typeTaxe);
		if (choix.equals("modifier"))
			res = StaticManipHtml.genererListeDeroulante("typeTaxe", 1,typeTaxe, contenu2,false);
		else
			res = StaticManipHtml.genererListeDeroulante("typeTaxe", 1,typeTaxe, contenu,false);
		return res;	
		
	}
	
	public String getetatReclamation()
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();					
		DataFromBD tmp = new DataFromBD();
		Vector contenu= tmp.getTousLesEtatsReclamation();		
		res = StaticManipHtml.genererListeDeroulante("etatReclamation", 1,etatReclamation, contenu,false);
		return res;			
	}


	
	
	public String getidRedevable()
	{
		String res="";		
		return idRedevable;		
	}

	public String getchoix() {
		return choix;
	}

	public void setchoix(String choix) {
		this.choix = choix;
	}

	public String getdateReclamation() 
	{		
		//return choix;
		return dateReclamation;
	}

	public void setdateReclamation(String dateReclamation) {
		this.dateReclamation = dateReclamation;
	}

	public void setetatReclamation(String etatReclamation) {
		this.etatReclamation = etatReclamation;
	}

	public String getChoix() {
		return choix;
	}


	public String getdateReponse() {
		return dateReponse;
	}

	public void setdateReponse(String dateReponse) {
		this.dateReponse = dateReponse;
	}



	public String getidFactue() {
		return idFactue;
	}

	public void setidFactue(String idFactue) {
		this.idFactue = idFactue;
	}


	public void setidRedevable(String idRedevable) {
		this.idRedevable = idRedevable;
	}

	public String getmotsclef() {
		return motsclef;
	}

	public void setmotsclef(String motsclef) {
		this.motsclef = motsclef;
	}

	public String getnatureReclamation() 
	{
		StaticManipHtml mHtml = new StaticManipHtml();
		RequestReclamation reqReclam= new RequestReclamation();
		Vector Contenu = reqReclam.getTousLesNaturesReclamation();
	
		String res=StaticManipHtml.genererListeDeroulanteApartirDeVacteur("natureReclamation",
				natureReclamation, Contenu, 0, false);
		return res;
	}

	public void setnatureReclamation(String natureReclamation) {
		this.natureReclamation = natureReclamation;
	}

	public String getpieceJointe() {
		return pieceJointe;
	}

	public void setpieceJointe(String pieceJointe) {
		this.pieceJointe = pieceJointe;
	}

	public String gettexteReclamation() {
		return texteReclamation;
	}

	public void settexteReclamation(String texteReclamation) {
		this.texteReclamation = texteReclamation;
	}

	public String gettexteReponse() {
		return texteReponse;
	}

	public void settexteReponse(String texteReponse) {
		this.texteReponse = texteReponse;
	}



	public void settypeTaxe(String typeTaxe) {
		this.typeTaxe = typeTaxe;
	}

	public void setcreation(boolean creation) {
		this.creation = creation;
	}

	public String gettypeReceptionReclamation()
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();		
		DataFromBD tmp = new DataFromBD();
		Vector contenu= tmp.getTousLestypeReceptionReclamation();		
		/*
		if (choix.equalsIgnoreCase("Modifier"))
		{
			contenu.removeAllElements();
			contenu.addElement(typeReceptionReclamation);			
		}
		*/
		res = StaticManipHtml.genererListeDeroulante("typeReceptionReclamation", 1,typeReceptionReclamation, contenu,false);
		return res;	
	}

	public void settypeReceptionReclamation(String typeReceptionReclamation) {
		this.typeReceptionReclamation = typeReceptionReclamation;
	}

	public String getidReclamation() {
		return idReclamation;
	}

	public void setidReclamation(String idReclamation) {
		this.idReclamation = idReclamation;
	}

	/**
	 * @return the controleEffectue
	 */
	public String getcontroleEffectue()
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();		
		Vector contenu= new Vector();
		contenu.addElement("NON");
		contenu.addElement("OUI");		
		//System.out.println("controleEffectue"+controleEffectue);
		res = StaticManipHtml.genererListeDeroulante("controleEffectue", 1,controleEffectue, contenu,false);
		//res = mHtml.genererListeDeroulante("controleEffectue", 1,"OUI", contenu,false);		
		return res;		
	}

	/**
	 * @param controleEffectue the controleEffectue to set
	 */
	public void setcontroleEffectue(String controleEffectue) {
		this.controleEffectue = controleEffectue;
	}

	/**
	 * @return the nomRedevable
	 */
	public String getNomRedevable() {
		return nomRedevable;
	}

	/**
	 * @param nomRedevable the nomRedevable to set
	 */
	public void setNomRedevable(String nomRedevable) {
		this.nomRedevable = nomRedevable;
	}

	/**
	 * @return the listeOuvrage
	 */
	public String getListeOuvrage() {
		return listeOuvrage;
	}

	/**
	 * @param listeOuvrage the listeOuvrage to set
	 */
	public void setListeOuvrage(String listeOuvrage) {
		this.listeOuvrage = listeOuvrage;
	}

	/**
	 * @return the nomControleur
	 */
	public final String getNomControleur() {
		return nomControleur;
	}

	/**
	 * @param nomControleur the nomControleur to set
	 */
	public final void setNomControleur(String nomControleur) {
		this.nomControleur = nomControleur;
	}

	/**
	 * @return the lienInfosFacture
	 */
	public final String getLienInfosFacture() {
		return lienInfosFacture;
	}

	/**
	 * @param lienInfosFacture the lienInfosFacture to set
	 */
	public final void setLienInfosFacture(String lienInfosFacture) {
		this.lienInfosFacture = lienInfosFacture;
	}

	/**
	 * @return the listeCourierReponse
	 */
	public final String getListeCourierReponse() {
		return listeCourierReponse;
	}

	/**
	 * @param listeCourierReponse the listeCourierReponse to set
	 */
	public final void setListeCourierReponse(String listeCourierReponse) {
		this.listeCourierReponse = listeCourierReponse;
	}

	/**
	 * @return the cheminDataCourier
	 */
	public final String getCheminDataCourier() {
		return cheminDataCourier;
	}

	/**
	 * @param cheminDataCourier the cheminDataCourier to set
	 */
	public final void setCheminDataCourier(String cheminDataCourier) {
		this.cheminDataCourier = cheminDataCourier;
	}
	
	public boolean verfierAcces(String listeLibelleDesTypesDeTaxeAutorise) 
	{
		boolean res=false;	
		if(listeLibelleDesTypesDeTaxeAutorise.indexOf(typeTaxe) != -1)
			res= true; 
	;
		return res;
	}

	

}