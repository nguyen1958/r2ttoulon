package fr.analogon.r2t.view.regie;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.StaticNombres;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.Article;
import fr.analogon.r2t.pojo.Emplacement;
import fr.analogon.r2t.pojo.Image;
import fr.analogon.r2t.pojo.Redevable;
import fr.analogon.r2t.request.DataFromBD;
import fr.analogon.r2t.request.RequestBareme;
import fr.analogon.r2t.request.RequestEmplacement;
import fr.analogon.r2t.request.RequestOuvrage;
import fr.analogon.r2t.util.date.LaDate;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage pour les élements de facturation
 * 
 * @version 1.1 .Sofien CHARFI
 * @since 1.0
 */

public class BaffElementFacturation extends fr.analogon.r2t.main.RacineBean 
{
	
	private String dateDebutAutorisation="";
	private String dateFinAutorisation ="";
	private String dateDernierControle = "";
	private String dateProchainControle = "";
	private String dernierePeriodeFacture="";	
	private String memeLien="";	
	private String adresseComplete="";	
	private String lienRedvable="";	
	private String raisonSocialEmplacement="";	
	private String lienEmplacement="";	
	private String necessiteControleTerrain="";
	private String nomPrenomRedevable="";	
	private String numRedevable = "" ;
	private String nomRedevable = "" ;	
	private String exercice = "";
	private String numEmplacment = "";	
	private String codeType = "";
	private String codeSecteur = "";
	private String codeEmplacement = "";
	private String numAlerte= "";	
	private String numElementFacturation = "";		
	private String nombreImageEmplacement = "0";	
	private String nombreImageAlerte = "0";
	private String libelleImputation ="";
	private String peutEtreModifier="true";
	private String quantite="0.00";		
	private String typeRecherche = "";
	private String largeurArticle = "0.00";	
	private String commentaireOuvrage = "";
	private String longueurArticle = "0.00";	
	private String nomArticle = "";
	private String surface="0.00";
	private String peutEtreSuuprimer="true";
	private String choix = "";
	private HttpServletRequest request;
	private LaDate dateCourante = new LaDate();
	private String traitement = "elementfacturation";
	private String idArticle = "0";	
	private String etatArticle="";
	private String  dateFinIdoss="";
	private String  typeOuvrage="";
	private String  listeOuvrage="";
	
	private String  historiqueEtat="";
	private String  nombreFaceAffiche;
	
	RequestEmplacement requestEmplacement= new RequestEmplacement();

	
	/**
	 * @return the dateFinIdoss
	 */
	public final String getDateFinIdoss() {
		return dateFinIdoss;
	}

	/**
	 * @param dateFinIdoss the dateFinIdoss to set
	 */
	public final void setDateFinIdoss(String dateFinIdoss) {
		this.dateFinIdoss = dateFinIdoss;
	}

	public BaffElementFacturation() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		
	}

	public void setRequest(HttpServletRequest req) 
	{		
		this.request = req;
		if (request.getParameter("numRedevable") != null)
			this.numRedevable = request.getParameter("numRedevable");
		if (request.getParameter("numEmplacment") != null)
			this.numEmplacment = request.getParameter("numEmplacment");
		if (request.getParameter("exercice") != null)
			this.exercice = request.getParameter("exercice");
		if (this.exercice.equalsIgnoreCase(""))
			this.exercice = dateCourante.getAnneeString();
		if (request.getParameter("choix") != null)
			this.choix = request.getParameter("choix");
		if (request.getParameter("libelleImputation") != null)
			this.libelleImputation = request.getParameter("libelleImputation");
		if (request.getParameter("necessiteControleTerrain") != null)
			this.necessiteControleTerrain = request.getParameter("necessiteControleTerrain");
		if (request.getParameter("codeSecteur") != null)
			this.codeSecteur = request.getParameter("codeSecteur");
		if (request.getParameter("codeEmplacement") != null)
			this.codeEmplacement = request.getParameter("codeEmplacement");
		if (request.getParameter("numElementFacturation") != null)
			this.numElementFacturation = request
					.getParameter("numElementFacturation");
		if (request.getParameter("typeRecherche") != null)
			this.typeRecherche = request.getParameter("typeRecherche");
		if (request.getParameter("traitement") != null)
			this.traitement = request.getParameter("traitement");
		
		if (request.getParameter("idarticle") != null)
			this.idArticle = request.getParameter("idarticle");
		
		if (request.getParameter("numeroAlerte") != null)
			this.numAlerte = request.getParameter("numeroAlerte");
			
		if (request.getParameter("etatArticle") != null)
			this.etatArticle = request.getParameter("etatArticle");	
		
		if (request.getParameter("surface") != null)
			this.surface = request.getParameter("surface");		
		
		if (request.getParameter("dateDernierControle") != null)
			this.dateDernierControle = request.getParameter("dateDernierControle");		
		else	
			this.dateDernierControle = "";		
		
		if (request.getParameter("dateProchainControle") != null)
			this.dateProchainControle = request.getParameter("dateProchainControle");
		else	
			this.dateProchainControle = "";				
		
		if (request.getParameter("dateDebutAutorisation") != null)
			this.dateDebutAutorisation = request.getParameter("dateDebutAutorisation");	
		else	
			this.dateDebutAutorisation = "";
		
		if (request.getParameter("dateFinAutorisation") != null)
			this.dateFinAutorisation = request.getParameter("dateFinAutorisation");	
		else	
			this.dateFinAutorisation = "";
		
		
		RequestOuvrage requestOuvrage = new RequestOuvrage();
		Article article = requestOuvrage.getArticle(this.idArticle);
		
		if (article != null) 
		{
			this.quantite = ""	+ StaticNombres.arronDD2(article.getQuantite_article());				
			this.nomArticle = article.getNom();			
			this.largeurArticle = ""	+ StaticNombres.arronDD2(article.getLargeur());
			this.longueurArticle = ""	+ StaticNombres.arronDD2(article.getLongueur());			
			this.surface = ""	+ StaticNombres.arronDD2(article.getSurface());			
			this.commentaireOuvrage= article.getCommentaireOuvrage();			
			this.quantite = ""	+ StaticNombres.arronDD2(article.getQuantite_article());			
			this.dateDernierControle = article.getDateDernierControle();
			this.dateProchainControle = article.getDateProchainControl();
			this.dateDebutAutorisation = article.getDateDebutAutorisation();
			this.dateFinAutorisation = article.getDateFinAutorisation();
			this.typeOuvrage = article.getTypeOuvrage();
			this.peutEtreSuuprimer = article.getPeutEtreSuuprimer();
			this.peutEtreModifier = article.getPeutEtreModifier();
			
			this.nombreFaceAffiche = article.getNombreFaceAffiche();
			this.dernierePeriodeFacture = article.getDernierePeriodeFacture();
			this.dateFinIdoss = article.getDateFinIdoss();
			this.etatArticle = article.getEtatArticle();
			this.numEmplacment = article.getNumEmplacment();
			this.nombreImageEmplacement = String.valueOf(requestEmplacement.getNombreImagesEmplacement(numEmplacment));	
			this.nombreImageAlerte = String.valueOf(requestEmplacement.getNombreImagesAlerteOuvrage( numAlerte, idArticle));
			this.numRedevable = article.getNumRedevable();
			Vector res = requestOuvrage.getListeEtatOuvrage(idArticle);
			historiqueEtat = StaticManipHtml.genererTableauListeEtatOuvrage(res);
		}
		
		
		if (dateDebutAutorisation != null && dateDebutAutorisation.equalsIgnoreCase("null") )	this.dateDebutAutorisation = "";	
		if (dateFinAutorisation != null && dateFinAutorisation.equalsIgnoreCase("null") )		this.dateFinAutorisation = "";
		if (dateDernierControle != null && dateDernierControle.equalsIgnoreCase("null") )		this.dateDernierControle= "";
		if (dateProchainControle != null && dateProchainControle.equalsIgnoreCase("null") )		this.dateProchainControle = "";
		if (commentaireOuvrage == null )	this.commentaireOuvrage = "";			
	}

	public String getNumEmplacment() {
		return numEmplacment;
	}

	public String getExercice() {
		return exercice;
	}

	public String getNumRedevable() {
		return numRedevable;
	}

	public String getChoix() {
		return choix;
	}



	public String getEntete() 
	{
		
		String retour="";		
		DataFromBD req= new DataFromBD();
		Redevable redevable = req.getRedevable(Integer.parseInt(this.numRedevable));
		if (redevable!=null)
			this.nomPrenomRedevable= redevable.getCivilite() + " "+ redevable.getNom()+ " "+ redevable.getPrenom();
					
		Emplacement e= requestEmplacement.getEmplacement(this.numEmplacment);
		this.codeSecteur=e.getCodeSecteur();
		this.adresseComplete=e.getAdresseComplete();
		this.libelleImputation = e.getLibelleImputation();
		this.necessiteControleTerrain= e.getNecessiteControleTerrain();
		this.lienRedvable="./entree?action=empl_gestion_redevable.jsp"+
		"&numRedevable="+this.numRedevable+
		"&nomRedevable="+ redevable.getNom()+
		"&prenom="+redevable.getPrenom()+
		"&civilite="+redevable.getCivilite()+
		"&choix=modifier&boton=ajouter&typeRecherche=role&optionTransfert=role" +
		"&typeRedevable=normal&origine=origine";		
		
		this.lienEmplacement="./entree?action=empl_gestion_emplacementodp.jsp" +
				"&choix=modifier&optionTransfert=role" +
				"&origine=origine&numEmplacment="+	this.numEmplacment ;
		String lienOuvrage = "./entree?action=regie_liste_element_facturation.jsp" +
				"&traitement=article&choix=modifier&numEmplacement="+numEmplacment;
		
		this.listeOuvrage= "<a href="+lienOuvrage
		+" >"+e.getNombreOuvrageActif() +" ouvrages" + "</a>";
		
		this.raisonSocialEmplacement=e.getRaisonSocial();		
		////System.out.println(lienEmplacement);		
		//System.out.println(		
		return retour;
	}
	

	public String getListeCodeBareme() 
	{
		String res = "Aucun bareme disponible. ";		
		StaticManipHtml mHtml = new StaticManipHtml();			
		RequestOuvrage req = new RequestOuvrage();
		Article article = req.getArticle( idArticle);
		String codeBareme= article.getCodeBareme();	
		RequestBareme reqBareme = new RequestBareme();
		Vector contenu= reqBareme.getListeBareme(libelleImputation,exercice);
		String bareme="";
		
		for (int i = 0; i < contenu.size(); i++) 
		{
			if( ((String)contenu.elementAt(i)).startsWith(codeBareme+"-"))
			{
				bareme = (String)contenu.elementAt(i);
				break;
			}
		}
		if(article.getPeutEtreModifier().equalsIgnoreCase("true") )
			res = mHtml.genererListeDeroulanteListeBareme("codeBareme", 1,	bareme, contenu,false);
		else
		{	
			Vector contenutmp = new Vector();
			contenutmp.add( bareme);
			res = mHtml.genererListeDeroulanteListeBareme("codeBareme", 1,	bareme, contenutmp,false);
			res = res + "<font size='2' color='#ff0000'>Cet ouvrage est facturé au moins une fois!!</font>" ;
		}
		
		
		return res;			

	}



	
	
	
	
	
	
	public String getCodeType() {
		return codeType;
	}

	public String getCodeSecteur() {
		return codeSecteur;
	}

	public String getCodeEmplacement() {
		return codeEmplacement;
	}

	public String getNomRedevable() {
		return nomRedevable;
	}

	public String getNumElementFacturation() {
		return numElementFacturation;
	}
	

	public String getTraitement() {
		return traitement;
	}

	public String getNomArticle()
	{
		
		if(nomArticle.startsWith("#"))
			return "";
		else		
			return nomArticle;
	}
	
	public String getNomArticleCache()
	{
		return nomArticle;
	}

	public String getLargeurArticle() {
		return largeurArticle;
	}
	
	public String getcommentaireOuvrage() 
	{		
		return commentaireOuvrage;
	}
	
	public String getDateDebutAutorisation() 
	{
		return dateDebutAutorisation;	
	}
	
	
	
	
	
	public String getetatArticle()
	{
		String res="";
		
		StaticManipHtml mHtml = new StaticManipHtml();
		////System.out.println(">>>>>>>>>"+etatArticle);		
		String typeSelectionner=etatArticle;
		
		
		typeSelectionner = FonctionCommun.getEtat(typeSelectionner);
				
			
		Vector contenu= new Vector();
		contenu.addElement("A Facturer");
		contenu.addElement("Facturé");
		contenu.addElement("Facturé à Controler");
		contenu.addElement("Contrôlé à Facturer");
		contenu.addElement("Contrôlé Présent");
		contenu.addElement("Contrôlé Absent");
		contenu.addElement("Contrôlé Alerte");
		contenu.addElement("Remboursé");
		contenu.addElement("Ne Plus Facturer");
		contenu.addElement("Ne Pas Facturer");
		
		res = mHtml.genererListeDeroulante("etatArticle", 1,
				typeSelectionner, contenu,false);
				
		return res;
		
	}
	
	
	public String getquantite() 
	{
		return quantite;
	}
	

	public String getLongueurArticle() {
		return longueurArticle;
	}

	public String getIdArticle() {
		return idArticle;
	}

	
	


	public String getdateDernierControle() 
	{		
		return dateDernierControle;
	}

	
	public String getdateProchainControle() 
	{		
		return dateProchainControle;
	}

	/**
	 * @return the nomPrenomRedevable
	 */
	public String getNomPrenomRedevable() {
		return nomPrenomRedevable;
	}

	/**
	 * @param nomPrenomRedevable the nomPrenomRedevable to set
	 */
	public void setNomPrenomRedevable(String nomPrenomRedevable) {
		this.nomPrenomRedevable = nomPrenomRedevable;
	}

	/**
	 * @return the lienRedvable
	 */
	public String getLienRedvable() {
		return lienRedvable;
	}

	/**
	 * @param lienRedvable the lienRedvable to set
	 */
	public void setLienRedvable(String lienRedvable) {
		this.lienRedvable = lienRedvable;
	}

	/**
	 * @return the adresseComplete
	 */
	public String getAdresseComplete() {
		return adresseComplete;
	}

	/**
	 * @param adresseComplete the adresseComplete to set
	 */
	public void setAdresseComplete(String adresseComplete) {
		adresseComplete = adresseComplete;
	}

	/**
	 * @return the lienEmplacement
	 */
	public String getLienEmplacement() {
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
	public String getRaisonSocialEmplacement() {
		return raisonSocialEmplacement;
	}

	/**
	 * @param raisonSocialEmplacement the raisonSocialEmplacement to set
	 */
	public void setRaisonSocialEmplacement(String raisonSocialEmplacement) {
		this.raisonSocialEmplacement = raisonSocialEmplacement;
	}
	
	public String getTableauImagesEmplacement()
	{
		String retour="function getTableauImagesEmplacement() \n" +
					"{\n" +			
					" \nvar tableauImage=new Array;" ;
			
			RequestEmplacement reqEmplacement = new RequestEmplacement();
			Vector contenu = reqEmplacement.getTableauImagesEmplacement(this.numEmplacment);
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
	
	public String getTableauImagesAlerte()
	{
			String retour="function getTableauImagesAlerte() \n" +
			"{\n" +			
			" \nvar tableauImage=new Array;" ;
			
			RequestEmplacement req = new RequestEmplacement();
			Vector contenu = req.getTableauImagesAlerte(this.numAlerte , idArticle);
			
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
		
	

	/**
	 * @return the memeLien
	 */
	public String getMemeLien() {
		return memeLien;
	}

	/**
	 * @return the nombreImageEmplacement
	 */
	public String getNombreImageEmplacement()
	{
		return nombreImageEmplacement;
	}
	
	/**
	 * @return the nombreImageAlerte
	 */
	
	public String getNombreImageAlerte()
	{	
		return nombreImageAlerte;
	}
	
	
	/**
	 * @param nombreImageEmplacement the nombreImageEmplacement to set
	 */
	public void setNombreImageEmplacement(String nombreImageEmplacement) {
		this.nombreImageEmplacement = nombreImageEmplacement;
	}

	/**
	 * @return the surface
	 */
	public String getSurface() {
		return surface;
	}

	/**
	 * @param surface the surface to set
	 */
	public void setSurface(String surface) {
		this.surface = surface;
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

	/**
	 * @return the dateDernierControle
	 */
	public final String getDateDernierControle() {
		return dateDernierControle;
	}

	/**
	 * @return the dateFinAutorisation
	 */
	public final String getDateFinAutorisation() {
		return dateFinAutorisation;
	}

	/**
	 * @param dateFinAutorisation the dateFinAutorisation to set
	 */
	public final void setDateFinAutorisation(String dateFinAutorisation) {
		this.dateFinAutorisation = dateFinAutorisation;
	}

	/**
	 * @return the dernierePeriodeFacture
	 */
	public final String getDernierePeriodeFacture() {
		return dernierePeriodeFacture;
	}

	/**
	 * @param dernierePeriodeFacture the dernierePeriodeFacture to set
	 */
	public final void setDernierePeriodeFacture(String dernierePeriodeFacture) {
		this.dernierePeriodeFacture = dernierePeriodeFacture;
	}

	/**
	 * @return the historiqueEtat
	 */
	public final String getHistoriqueEtat() {
		return historiqueEtat;
	}

	/**
	 * @param historiqueEtat the historiqueEtat to set
	 */
	public final void setHistoriqueEtat(String historiqueEtat) {
		this.historiqueEtat = historiqueEtat;
	}

	/**
	 * @return the typeOuvrage
	 */
	public final String getTypeOuvrage()
	{
		Vector contenu = new Vector();
		if( peutEtreModifier.equalsIgnoreCase("true") )
			{
				contenu.addElement("Normal");
				contenu.addElement("Numerique");
				typeOuvrage = StaticManipHtml.genererListeDeroulante("typeOuvrage", 0, typeOuvrage, contenu, false);
			}
		else
		{
			contenu.addElement(typeOuvrage);
			typeOuvrage = StaticManipHtml.genererListeDeroulante("typeOuvrage", 0, typeOuvrage, contenu, false);
		
		}
			
		return typeOuvrage;
	}

	/**
	 * @param typeOuvrage the typeOuvrage to set
	 */
	public final void setTypeOuvrage(String typeOuvrage) {
		this.typeOuvrage = typeOuvrage;
	}
	
	public boolean verfierAcces(String listeLibelleDesTypesDeTaxeAutorise) 
	{
		boolean res=false;	
		if(listeLibelleDesTypesDeTaxeAutorise.indexOf(libelleImputation) != -1)
			res= true; 

		return res;
	}

	public String getListeOuvrage() {
		return listeOuvrage;
	}

	public void setListeOuvrage(String listeOuvrage) {
		this.listeOuvrage = listeOuvrage;
	}

	public String getNombreFaceAffiche() {
		return nombreFaceAffiche;
	}

	public void setNombreFaceAffiche(String nombreFaceAffiche) {
		this.nombreFaceAffiche = nombreFaceAffiche;
	}

	public String getNumAlerte() {
		return numAlerte;
	}

	public void setNumAlerte(String numAlerte) {
		this.numAlerte = numAlerte;
	}
	
	public Vector getImagesEmplacement()
	{
		Vector retour = new Vector();
		try 
		{			
			if (numEmplacment != null && numEmplacment.length() != 0 )
			{
				RequestEmplacement reqEmplacement = new RequestEmplacement();
				Vector contenu = reqEmplacement.getTableauImagesEmplacement(this.numEmplacment);
				
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

	public String getPeutEtreSuuprimer() {
		return peutEtreSuuprimer;
	}

	public void setPeutEtreSuuprimer(String peutEtreSuuprimer) {
		this.peutEtreSuuprimer = peutEtreSuuprimer;
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

	/**
	 * @return the peutEtreModifier
	 */
	public String getPeutEtreModifier() {
		return peutEtreModifier;
	}

	/**
	 * @param peutEtreModifier the peutEtreModifier to set
	 */
	public void setPeutEtreModifier(String peutEtreModifier) {
		this.peutEtreModifier = peutEtreModifier;
	}

	
}
