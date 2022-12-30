package fr.analogon.r2t.view.alertes;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.Alerte;
import fr.analogon.r2t.pojo.Image;
import fr.analogon.r2t.request.RequestAlerte;
import fr.analogon.r2t.request.RequestEmplacement;
import fr.analogon.r2t.request.RequestRue;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de la liste des alertesSofien CHARFI
 * 
 * @version 1.0
 * @since 1.0
 */
public class BAffListeAlertes extends fr.analogon.r2t.main.RacineBean {

	//private String listealertes;
	private String listeAlerteOuvrage="";
	
	private String memeLien="";
	
	private String nomRedevable="";

	private HttpServletRequest request;
	
	private String idArticle = "0";
	
	private String idAlerte = "";
	
	private String idAlerteSpontanne = "";	
	
	private String dateAlerte = "";
	
	private String nombreImageAlerteSpontanne="";
	
	private String textAlerte = "";
	
	private String controleurAlerte = "";
	
	private String nomPrenomControleur = "";
	
	private String alerteExiste = "false";
	
	private String numeroAlerteRech = "";
	
	private String typeAlerteRech= "";
	
	private String dateCreationAlerteRech= "";
	
	private String nomControleurRech= "";
	
	private String remarque = "";
	
	private String etatAlerte= "";	
	
	private String dateFinAlerte="";
	
	private String numeroRue="";
	
	private String nomRue="";
	
	private String adresseOuvrage="";
	
	private String liedossier="";
	private String listeQuartier="";
	public String nomQuartier="";

	
	/**
	 * @return the adresseOuvrage
	 */
	public String getAdresseOuvrage() {
		return adresseOuvrage;
	}

	public String getetatAlerte() 
	{
		StaticManipHtml mHtml = new StaticManipHtml();
		RequestAlerte req = new RequestAlerte();
		Vector Contenu = req.getTousLesEtatsAlerte();		
		String res= StaticManipHtml.genererListeDeroulanteApartirDeVacteur("etatAlerte", 
				etatAlerte, Contenu, 1, false);
		return res;
	}

	
	public String getEtatAlerte() 
	{
		return getetatAlerte();
	}




	public void setRequest(HttpServletRequest req)
	{

		
		//////System.out.println("set request");
		this.request = req;
		if (request.getParameter("idarticle") != null)
			this.idArticle = request.getParameter("idarticle");
		
		if (request.getParameter("numeroAlerte") != null)
			this.numeroAlerteRech = request.getParameter("numeroAlerte");
		
		if (numeroAlerteRech !=null && numeroAlerteRech.length() != 0)
			this.getAlerte(numeroAlerteRech);
		
		//////System.out.println("/////////////Parametre recherche////////////");
		//////System.out.println("//////////////////////////////////////");
		
		if (request.getParameter("nomQuartier") != null)
			this.nomQuartier = request.getParameter("nomQuartier");
	
		if (request.getParameter("typeAlerte") != null)
			this.typeAlerteRech = request.getParameter("typeAlerte");
		if (request.getParameter("dateCreationAlerte") != null)
			this.dateCreationAlerteRech = request.getParameter("dateCreationAlerte");
		if (request.getParameter("nomControleur") != null)
			this.nomControleurRech = request.getParameter("nomControleur");
		if (request.getParameter("etatAlerte") != null)
			this.etatAlerte= request.getParameter("etatAlerte");
		if (request.getParameter("id_alerte") != null)
			this.idAlerte= request.getParameter("id_alerte");
		if (request.getParameter("numeroRue") != null)
			this.numeroRue= request.getParameter("numeroRue");
		if (request.getParameter("nomRue") != null)
			this.nomRue= request.getParameter("nomRue");
		if (request.getParameter("nomRedevable") != null)
			this.nomRedevable= request.getParameter("nomRedevable");
		if (request.getParameter("idAlerteSpontanne") != null)
			this.idAlerteSpontanne= request.getParameter("idAlerteSpontanne");
		if (request.getParameter("adresseOuvrage") != null)
			this.adresseOuvrage= request.getParameter("adresseOuvrage");
		
		if ((request.getParameter("liedossier") != null)) 
			this.liedossier = request.getParameter("liedossier");
		
		this.memeLien="./entree?action=empl_saisie_alerteSpantanne.jsp&idAlerteSpontanne="+idAlerteSpontanne;
			

		if(idAlerteSpontanne.length()!=0)
			////System.out.println("idAlerteSpontanne="+ idAlerteSpontanne);
		if(idAlerte.length()!=0)
			this.getAlerte(""+idAlerte);			
		
		//////System.out.println("//////////////////////////////////////");

		String nomImageAlerteASupprimer=request.getParameter("nomImageAlerteASupprimer");
	
		
		if (idAlerteSpontanne.length() != 0)
		{
			//Recuperation du nombre d'image de l'alerte
			RequestEmplacement req1 = new RequestEmplacement();
			nombreImageAlerteSpontanne = String.valueOf(req1.getNombreImagesAlerteSpontanne(idAlerteSpontanne));		
		}
		////System.out.println("nombre image pour lalerte spontanne "+ nombreImageAlerteSpontanne);
		
		if(idArticle !=null && idArticle.length() !=0)
		{
			RequestAlerte requestAlerte = new RequestAlerte();
			Vector <Alerte> listeAlerteOuvrageV = requestAlerte.getAlerteFromIdArticle(idArticle, "");
			for (int i = 0; i < listeAlerteOuvrageV.size(); i++) 
			{
				Alerte alerteTmp =  listeAlerteOuvrageV.elementAt(i);
				String lienVersAlerteOuvrage="./entree?action=empl_saisie_element_facturation.jsp" +
						"&traitement=article&choix=modifier&idarticle=" + idArticle +
					    "&numeroAlerte=" + alerteTmp.getId_alerte();
				listeAlerteOuvrage  = listeAlerteOuvrage + "    <a href ="+ lienVersAlerteOuvrage +"> " 
									  +  "  Alerte "+alerteTmp.getId_alerte() +"</a>  -  ";
			} 
			
		}
	}	
	
	
	
	public String getListealertes()
	{
		System.out.println("parametres");
		System.out.println(numeroAlerteRech+":"+typeAlerteRech+":"+etatAlerte+":"+liedossier);
		String res="";
		StaticManipHtml mHtml = new StaticManipHtml();
		RequestAlerte req= new RequestAlerte();
		Vector contenu = req.getListealerte(numeroAlerteRech, typeAlerteRech,
				dateCreationAlerteRech, nomControleurRech,etatAlerte,numeroRue,nomRue,nomRedevable,liedossier
				,nomQuartier);
		 		
		res=StaticManipHtml.genererTableauListeAlertes(contenu);		
		//System.out.println(
		return res;
	}
	

	
	
	public void getAlerte(String numeroAlerteRech)
	{	
		
		RequestAlerte req= new RequestAlerte();
		Vector contenu = req.getAlerte(numeroAlerteRech);
		
		DebuggerLog4J.logger.debug("ALERTE DEMANDE = " + numeroAlerteRech);		
		if(contenu.size()!=0)
		{	
			Alerte alerte = (Alerte)contenu.elementAt(0);
			this.idAlerte=alerte.getId_alerte();
			this.dateAlerte=alerte.getDate_creation();
			this.textAlerte=alerte.getText_alerte();
			this.controleurAlerte=alerte.getId_controleur();
			this.nomPrenomControleur=alerte.getNomPrenomControleur();
			this.remarque=alerte.getRemarque();
			this.etatAlerte=alerte.getEtat_alerte();
			this.dateFinAlerte= alerte.getDateFinAlerte();
			this.alerteExiste="true";
			this.nomQuartier = alerte.getQuartier();			
			
		}
		//System.out.println("FIN METHODE DOUTE");
		 
		 
				
	}
	
	
	//Methode appele par la JSP
	public String getAlerte()
	{			
		RequestAlerte req= new RequestAlerte();
		Vector contenu = req.getAlerteFromIdArticle(idArticle, numeroAlerteRech);
		//////System.out.println("*****idArticle*******"+idArticle);
		 		
		if(contenu.size()!=0)
		{
	
			Alerte alerte = (Alerte)contenu.elementAt(0);
			this.idAlerte=alerte.getId_alerte();
			this.dateAlerte=alerte.getDate_creation();
			this.textAlerte=alerte.getText_alerte();
			this.controleurAlerte=alerte.getId_controleur();
			this.nomPrenomControleur=alerte.getNomPrenomControleur();
			this.remarque=alerte.getRemarque();
			this.etatAlerte=alerte.getEtat_alerte();
			this.dateFinAlerte= alerte.getDateFinAlerte();
			this.nomQuartier = alerte.getQuartier();
			this.alerteExiste="true";		
		}
				
		//System.out.println(
		return this.alerteExiste;
	}
	
	public String getIdAlerteSpontanne()
	{	
		//////System.out.println("TESTTESTTESTTESTTESTTESTTESTSOFIENTESTTESTTESTTEST ");
		RequestAlerte req= new RequestAlerte();
		Vector contenu = req.getAlerte(this.idAlerteSpontanne);
		//////System.out.println("*****idAlerteSpontanne******"+idAlerteSpontanne);
		 		
		if(contenu.size()!=0)
		{	
			Alerte alerte = (Alerte)contenu.elementAt(0);
			this.idAlerte=alerte.getId_alerte();
			this.dateAlerte=alerte.getDate_creation();
			this.textAlerte=alerte.getText_alerte();
			this.controleurAlerte=alerte.getId_controleur();
			this.nomPrenomControleur=alerte.getNomPrenomControleur();
			this.remarque=alerte.getRemarque();
			this.etatAlerte=alerte.getEtat_alerte();
			this.dateFinAlerte= alerte.getDateFinAlerte();
			this.adresseOuvrage = alerte.getAdresseOuvrage();
			this.nomQuartier = alerte.getQuartier();
		}
			
		//System.out.println(
		return"";
		
	}
	
	
	
	
	
	
	
	
	


	public String getControleurAlerte() 
	{
		String link = "<a href=entree?action=empl_gestion_utilisateur.jsp" +
		"&choix=modifier&codeUtilisateur="+controleurAlerte +">"; 
       //String res= link + nomPrenomControleur +"</a>";
		String res=  nomPrenomControleur ;
		return res;
	}


	public void setControleurAlerte(String controleurAlerte) {
		this.controleurAlerte = controleurAlerte;
	}


	public String getDateAlerte() {
		return dateAlerte;
	}


	public void setDateAlerte(String dateAlerte) {
		this.dateAlerte = dateAlerte;
	}


	public String getIdAlerte() {
		return idAlerte;
	}


	public void setIdAlerte(String idAlerte) {
		this.idAlerte = idAlerte;
	}


	public String getIdArticle() {
		return idArticle;
	}


	public void setIdArticle(String idArticle) {
		this.idArticle = idArticle;
	}




	public String getTextAlerte() {
		return textAlerte;
	}


	public void setTextAlerte(String textAlerte) {
		this.textAlerte = textAlerte;
	}


	public String getAlerteExiste() {
		return alerteExiste;
	}


	public void setAlerteExiste(String alerteExiste) {
		this.alerteExiste = alerteExiste;	
		
	}


	public String getRemarque()
	{
		if (remarque == null || remarque.equalsIgnoreCase("null") )
			return "";
		else
			return remarque;
	}


	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}


	/**
	 * @return the typeAlerteRech
	 */
	public String gettypeAlerteRech() {
		return typeAlerteRech;
	}
	
	public String getdateFinAlerte() 
	{
		if(dateFinAlerte==null)
			dateFinAlerte="";
		return dateFinAlerte;	
	}
	
	
	public String getTableauImagesAlerte()
	{		
		String retour="function getTableauImagesAlerte() \n" +
		"{\n" +			
		" \nvar tableauImage=new Array;" ;
		
		RequestEmplacement req = new RequestEmplacement();
		Vector contenu = req.getTableauImagesAlerte(this.idAlerteSpontanne, idArticle);
		
		String cheminPhoto = fichierDeConfiguration.getLienPhotos();
		for (int i = 0; i < contenu.size(); i++) 
		{
			Image image = (Image)contenu.elementAt(i);				
			retour= retour+"\n var image=new Array;";
			retour= retour+"\n image[0]=\"./"+ cheminPhoto + image.getNomImage()+"\";";
			retour= retour+"\n image[1]=\""+image.getDateCreationImage()+"\";";
			retour= retour+"\n tableauImage["+i+"]=image;";				
		}
		retour= retour+"\n return tableauImage;";
		retour= retour+"\n}";	
		
		return retour;
		
	}
	
	public String getTableauImagesAlerteSpontanne()
	{
		String retour="function getTableauImagesAlerte() \n" +
		"{\n" +			
		" \nvar tableauImage=new Array;" ;
		RequestEmplacement req = new RequestEmplacement();
		Vector contenu = req.getTableauImagesAlerteSpontanne(this.idAlerteSpontanne);			
		
		String cheminPhoto = fichierDeConfiguration.getLienPhotos();
		for (int i = 0; i < contenu.size(); i++) 
		{
			Image image = (Image)contenu.elementAt(i);				
			retour= retour+"\n var image=new Array;";
			retour= retour+"\n image[0]=\"./"+ cheminPhoto + image.getNomImage()+"\";";
			retour= retour+"\n image[1]=\""+image.getDateCreationImage()+"\";";
			retour= retour+"\n tableauImage["+i+"]=image;";				
		}
		retour= retour+"\n return tableauImage;";
		retour= retour+"\n}";	
		//////System.out.println(retour);
		
		return retour;
		
	}
	
	
	public String getNombreImageAlerteSpontanne()
	{		
		return nombreImageAlerteSpontanne;
	}

	/**
	 * @return the memeLien
	 */
	public String getMemeLien() {
		return memeLien;
	}
	

	/**
	 * @return the liedossier
	 */
	public String getLiedossier() {
		return liedossier;
	}

	/**
	 * @param liedossier the liedossier to set
	 */
	public void setLiedossier(String liedossier) {
		this.liedossier = liedossier;
	}

	/**
	 * @return the nomPrenomControleur
	 */
	public final String getNomPrenomControleur() {
		return nomPrenomControleur;
	}

	/**
	 * @param nomPrenomControleur the nomPrenomControleur to set
	 */
	public final void setNomPrenomControleur(String nomPrenomControleur) {
		this.nomPrenomControleur = nomPrenomControleur;
	}
	
	
	public boolean verfierAcces(String listeLibelleDesTypesDeTaxeAutorise) 
	{
		boolean res=false;	
		if(listeLibelleDesTypesDeTaxeAutorise.length() != 0)
			res= true; 
		
		return res;
	}

	public String getListeQuartier() 
	{
		RequestRue requestRue = new RequestRue();		
		Vector tabQuartier = requestRue.getListeQuartiers("");
		this.listeQuartier = StaticManipHtml.genererListeDeroulante("nomQuartier", 1,"",tabQuartier,true);
		return listeQuartier;
	}

	public void setListeQuartier(String listeQuartier) {
		this.listeQuartier = listeQuartier;
	}

	public String getNomQuartier() {
		return nomQuartier;
	}

	public void setNomQuartier(String nomQuartier) {
		this.nomQuartier = nomQuartier;
	}

	public String getListeAlerteOuvrage() {
		return listeAlerteOuvrage;
	}

	public void setListeAlerteOuvrage(String listeAlerteOuvrage) {
		this.listeAlerteOuvrage = listeAlerteOuvrage;
	}
	
	public Vector getImagesAlerte()
	{
		Vector retour = new Vector();
		try 
		{			
			if (nombreImageAlerteSpontanne != null && ! nombreImageAlerteSpontanne.equalsIgnoreCase("0") )
			{
				RequestAlerte requestAlerte = new RequestAlerte();
				Vector contenu = requestAlerte.getTableauImagesAlerte(idAlerte);				
				String cheminPhoto = fichierDeConfiguration.getLienPhotos();
				for (int i = 0; i < contenu.size(); i++) 
				{
					Image image = (Image)contenu.elementAt(i);				
					retour.addElement(image.getNomImage());
					//System.out.println(image.getNomImage());					
				}					
			}		
			
		}
		catch (Exception e) 
		{
			DebuggerLog4J.logger.fatal( e.getMessage() + e.getCause() );
		}
		return retour;		
		
	}
	
	
	

}