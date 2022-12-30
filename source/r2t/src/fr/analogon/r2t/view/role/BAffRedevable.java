package fr.analogon.r2t.view.role;

import java.text.ParseException;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.Redevable;
import fr.analogon.r2t.request.DataFromBD;
import fr.analogon.r2t.request.RequestCommentaire;
import fr.analogon.r2t.request.RequestEmplacement;
import fr.analogon.r2t.request.RequestFacture;
import fr.analogon.r2t.request.RequestRedevable;
import fr.analogon.r2t.util.web.StaticManipHtml;

public class BAffRedevable extends fr.analogon.r2t.main.RacineBean 
{
	
	private HttpServletRequest request;		
	private String choix;
	private String anneeFacture="";    
	private String numRedevable="";	
    private String natureJuridiqueRedevable ="";
	private String etatEmplacement="";
    private String etatFacture="enCours";
    private String raisonSocialeRedevable="";
	private String nomRedevable="";
	private String nomJeuneFilleRedevable="";
	private String prenomRedevable="";	
	private String informationComplementaire="";
	
	
	private String siret="";
	private String siren="";	
	private String commentaire="";	
	private String responsableRedevable="";
	private String dateNaissanceRedevable="";
	private String lieuNaissanceRedevable="";
	private String nationalliteRedevable="";
	private String numVoieRedevable="";
	private String codeVoixRedevable="";
	private String codeRivolieRedevable="";
	private String adressRedevable="";
	private String complement1AdressRedevable="";
	private String complement2AdressRedevable="";
	private String codePostaleRedevable="";
	private String villeeRedevable="";
	private String cedexRedevable="";
	private String numTelFixeRedevable="";
	private String numTelPortableRedevable="";
	private String numTelFaxeRedevable="";
	private String emailRedevable="";
	private String proffesionRedevable="";	
    private String raisonSocialeLiquidateur="";
	private String nomLiquidateur="";
	private String prenomLiquidateur="";	
	private String numVoieLiquidateur="";
	private String codeVoixLiquidateur="";
	private String adressLiquidateur="";
	private String complement1AdressLiquidateur="";
	private String complement2AdressLiquidateur="";
	private String codePostaleLiquidateur="";
	private String villeeLiquidateur="";
	private String cedexLiquidateur="";
	private String numTelFixeLiquidateur="";
	private String numTelPortableLiquidateur="";
	private String numTelFaxeLiquidateur="";
	private String emailLiquidateur="";	
	private String  peutEtreSupprimer="";
	private String  peutEtreDesactvier="";
	private String redevableActif="true";
	private String ListePaiementFacture="";	
	private String ListeDesFacturesNonPayer="";
	private String ListeRemboursementUtilisateur="";
	
	private String complementNumeroRueRedevable="";
	private String complementNumeroRueLiquidateur="";
	private String immeubleResidenceRedevable="";
	private String dateDuJour= GestionDate.getDateAujourdhuiString();
	
	private String raisonSocialeRedevableok="";
	
	
	
	   
	public void setNatureJuridiqueRedevable(String natureJuridiqueRedevable) 
	{
		this.natureJuridiqueRedevable = natureJuridiqueRedevable;
	}

	
	 public String getNatureJuridiqueRedevable()
	    {
	    	String res="";
	    	StaticManipHtml mHtml = new StaticManipHtml();
	    	DataFromBD data = new DataFromBD();
	    	Vector contenu =data.getToutesLesNaturesJuridique();
	    	
	    	//natureJuridiqueRedevable=11;12;21;22;23
	    	//11-Debiteur de droit prive-->Personne privee
	    	//12-Debiteur de droit prive-->Personne morale
	    	//21-Debiteur de droit public-->Etat
	    	//22-Debiteur de droit public-->Region
	    	//23-Debiteur de droit public-->Departement
	    	
	    	if (natureJuridiqueRedevable !=null )
	    	  {
	    		String natureJuridiqueRedevableValeur ="";
	    		if (natureJuridiqueRedevable.startsWith("11")) 	natureJuridiqueRedevableValeur="11-Debiteur de droit prive-->Personne privee";
	    		if (natureJuridiqueRedevable.startsWith("12")) 	natureJuridiqueRedevableValeur="12-Debiteur de droit prive-->Personne morale";
	    		if (natureJuridiqueRedevable.startsWith("21")) 	natureJuridiqueRedevableValeur="21-Debiteur de droit public-->Etat";
	    		if (natureJuridiqueRedevable.startsWith("22")) 	natureJuridiqueRedevableValeur="22-Debiteur de droit public-->Region";
	    		if (natureJuridiqueRedevable.startsWith("23")) 	natureJuridiqueRedevableValeur="23-Debiteur de droit public-->Departement";
	    	
	    		
	    		res= StaticManipHtml.genererListeDeroulanteApartirDeVacteurNatureJuridique("natureJuridiqueRedevable",
		    			natureJuridiqueRedevableValeur, contenu, 1, false);		
	    	  }
	    	
	    	else
	    		res= StaticManipHtml.genererListeDeroulanteApartirDeVacteurNatureJuridique("natureJuridiqueRedevable",
	    				"", contenu, 1, false);    	
	    	return res;		
		}

	
	/**
	 * @return the complementNumeroRueLiquidateur
	 */
	public final String getComplementNumeroRueLiquidateur() 
	{		
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();		
		DataFromBD data = new DataFromBD();
		Vector contenu = new Vector();		
		contenu= data.getTousComplementNumRue();
		//System.out.println("complementNumeroRueProprietaire"+complementNumeroRueProprietaire);
		if(complementNumeroRueLiquidateur==null ) complementNumeroRueLiquidateur="";
		res = StaticManipHtml.genererListeDeroulante("complementNumeroRueLiquidateur", 1,complementNumeroRueLiquidateur, contenu,true);
		contenu = null;
		System.gc();
		return res;
	}

	/**
	 * @param complementNumeroRueLiquidateur the complementNumeroRueLiquidateur to set
	 */
	public final void setComplementNumeroRueLiquidateur(
			String complementNumeroRueLiquidateur) {
		this.complementNumeroRueLiquidateur = complementNumeroRueLiquidateur;
	}

	/**
	 * @return the complementNumeroRueRedevable
	 */
	public final String getComplementNumeroRueRedevable()
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();		
		DataFromBD data = new DataFromBD();
		Vector contenu = new Vector();		
		contenu= data.getTousComplementNumRue();
		//System.out.println("complementNumeroRueProprietaire"+complementNumeroRueProprietaire);
		if(complementNumeroRueRedevable==null ) complementNumeroRueRedevable="";
		res = StaticManipHtml.genererListeDeroulanteCompNumRueRedevable("complementNumeroRueRedevable", 1,complementNumeroRueRedevable, contenu,true);
		contenu = null;
		System.gc();
		return res;
	}

	/**
	 * @param complementNumeroRueRedevable the complementNumeroRueRedevable to set
	 */
	public final void setComplementNumeroRueRedevable(
			String complementNumeroRueRedevable) {
		this.complementNumeroRueRedevable = complementNumeroRueRedevable;
	}

	public String getPeutEtreSupprimer() {
		return peutEtreSupprimer;
	}

	public void setPeutEtreSupprimer(String peutEtreSupprimer) {
		this.peutEtreSupprimer = peutEtreSupprimer;
	}

	/**
	 * @return the adressLiquidateur
	 */
	public String getAdressLiquidateur() {
		return adressLiquidateur;
	}

	/**
	 * @param adressLiquidateur the adressLiquidateur to set
	 */
	public void setAdressLiquidateur(String adressLiquidateur) {
		this.adressLiquidateur = adressLiquidateur;
	}

	/**
	 * @return the cedexLiquidateur
	 */
	public String getCedexLiquidateur() 
	{
		if (cedexLiquidateur != null && cedexLiquidateur.equalsIgnoreCase("true"))
			return "checked";
		else
			return "";
	
	}

	/**
	 * @param cedexLiquidateur the cedexLiquidateur to set
	 */
	public void setCedexLiquidateur(String cedexLiquidateur) {
		this.cedexLiquidateur = cedexLiquidateur;
	}

	/**
	 * @return the codePostaleLiquidateur
	 */
	public String getCodePostaleLiquidateur() {
		return codePostaleLiquidateur;
	}

	/**
	 * @param codePostaleLiquidateur the codePostaleLiquidateur to set
	 */
	public void setCodePostaleLiquidateur(String codePostaleLiquidateur) {
		this.codePostaleLiquidateur = codePostaleLiquidateur;
	}

	/**
	 * @return the codeVoixLiquidateur
	 */
	public String getCodeVoixLiquidateur() {
		return codeVoixLiquidateur;
	}

	/**
	 * @param codeVoixLiquidateur the codeVoixLiquidateur to set
	 */
	public void setCodeVoixLiquidateur(String codeVoixLiquidateur) {
		this.codeVoixLiquidateur = codeVoixLiquidateur;
	}

	/**
	 * @return the complement1AdressLiquidateur
	 */
	public String getComplement1AdressLiquidateur() {
		return complement1AdressLiquidateur;
	}

	/**
	 * @param complement1AdressLiquidateur the complement1AdressLiquidateur to set
	 */
	public void setComplement1AdressLiquidateur(String complement1AdressLiquidateur) {
		this.complement1AdressLiquidateur = complement1AdressLiquidateur;
	}

	/**
	 * @return the complement2AdressLiquidateur
	 */
	public String getComplement2AdressLiquidateur() {
		return complement2AdressLiquidateur;
	}

	/**
	 * @param complement2AdressLiquidateur the complement2AdressLiquidateur to set
	 */
	public void setComplement2AdressLiquidateur(String complement2AdressLiquidateur) {
		this.complement2AdressLiquidateur = complement2AdressLiquidateur;
	}

	/**
	 * @return the emailLiquidateur
	 */
	public String getEmailLiquidateur() {
		return emailLiquidateur;
	}

	/**
	 * @param emailLiquidateur the emailLiquidateur to set
	 */
	public void setEmailLiquidateur(String emailLiquidateur) {
		this.emailLiquidateur = emailLiquidateur;
	}

	/**
	 * @return the nomLiquidateur
	 */
	public String getNomLiquidateur() {
		return nomLiquidateur;
	}

	/**
	 * @param nomLiquidateur the nomLiquidateur to set
	 */
	public void setNomLiquidateur(String nomLiquidateur) {
		this.nomLiquidateur = nomLiquidateur;
	}

	/**
	 * @return the numTelFaxeLiquidateur
	 */
	public String getNumTelFaxeLiquidateur() {
		return numTelFaxeLiquidateur;
	}

	/**
	 * @param numTelFaxeLiquidateur the numTelFaxeLiquidateur to set
	 */
	public void setNumTelFaxeLiquidateur(String numTelFaxeLiquidateur) {
		this.numTelFaxeLiquidateur = numTelFaxeLiquidateur;
	}

	/**
	 * @return the numTelFixeLiquidateur
	 */
	public String getNumTelFixeLiquidateur() {
		return numTelFixeLiquidateur;
	}

	/**
	 * @param numTelFixeLiquidateur the numTelFixeLiquidateur to set
	 */
	public void setNumTelFixeLiquidateur(String numTelFixeLiquidateur) {
		this.numTelFixeLiquidateur = numTelFixeLiquidateur;
	}

	/**
	 * @return the numTelPortableLiquidateur
	 */
	public String getNumTelPortableLiquidateur() {
		return numTelPortableLiquidateur;
	}

	/**
	 * @param numTelPortableLiquidateur the numTelPortableLiquidateur to set
	 */
	public void setNumTelPortableLiquidateur(String numTelPortableLiquidateur) {
		this.numTelPortableLiquidateur = numTelPortableLiquidateur;
	}

	/**
	 * @return the numVoieLiquidateur
	 */
	public String getNumVoieLiquidateur() 
	{		
		return numVoieLiquidateur;
		
	}

	/**
	 * @param numVoieLiquidateur the numVoieLiquidateur to set
	 */
	public void setNumVoieLiquidateur(String numVoieLiquidateur) {
		this.numVoieLiquidateur = numVoieLiquidateur;
	}

	/**
	 * @return the prenomLiquidateur
	 */
	public String getPrenomLiquidateur() {
		return prenomLiquidateur;
	}

	/**
	 * @param prenomLiquidateur the prenomLiquidateur to set
	 */
	public void setPrenomLiquidateur(String prenomLiquidateur) {
		this.prenomLiquidateur = prenomLiquidateur;
	}

	/**
	 * @return the raisonSocialeLiquidateur
	 */
	public String getRaisonSocialeLiquidateur() 
	{
		DataFromBD data = new DataFromBD();
		Vector contenuRaisonSocial =data.getTousLesRaisonSocial();
		if (raisonSocialeLiquidateur !=null )
			raisonSocialeLiquidateur= StaticManipHtml.genererListeDeroulanteApartirDeVacteur("raisonSocialeLiquidateur",
			raisonSocialeLiquidateur, contenuRaisonSocial, 1, true);	
		else
			raisonSocialeLiquidateur= StaticManipHtml.genererListeDeroulanteApartirDeVacteur("raisonSocialeLiquidateur",
					"", contenuRaisonSocial, 1, true);
		
		return raisonSocialeLiquidateur;		
	}

	/**
	 * @param raisonSocialeLiquidateur the raisonSocialeLiquidateur to set
	 */
	public void setRaisonSocialeLiquidateur(String raisonSocialeLiquidateur) {
		this.raisonSocialeLiquidateur = raisonSocialeLiquidateur;
	}

	/**
	 * @return the villeeLiquidateur
	 */
	public String getVilleeLiquidateur() {
		return villeeLiquidateur;
	}

	/**
	 * @param villeeLiquidateur the villeeLiquidateur to set
	 */
	public void setVilleeLiquidateur(String villeeLiquidateur) {
		this.villeeLiquidateur = villeeLiquidateur;
	}

	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @param numRedevable the numRedevable to set
	 */
	public void setNumRedevable(String numRedevable) {
		this.numRedevable = numRedevable;
	}

	public void setRequest(HttpServletRequest req)
	{
		try 
		{
			
		
		//System.out.println("BaffRedevable setRequest");
		this.request = req;			
		DataFromBD data = new DataFromBD();
		
	
		if (request.getParameter("fromRechercherRue") != null)
		{						
			this.raisonSocialeRedevable=request.getParameter("raisonSocialeRedevable");
	    	this.nomRedevable=request.getParameter("nomRedevable");	  
		}
		else
		{
		
			RequestRedevable reqRed= new RequestRedevable();		
			if (request.getParameter("numRedevable") != null)
			{
				this.numRedevable = request.getParameter("numRedevable");			
				//System.out.println("numRedevable="+numRedevable);
			}
			else
				this.numRedevable = "0";
			
			
			
			if (request.getParameter("anneeFacture")!= null)
			{
				this.anneeFacture = request.getParameter("anneeFacture");							
			}
			else 
			{
				this.anneeFacture = GestionDate.getAnneeCourante();
			}
			DebuggerLog4J.logger.debug("Affichage des facture de l annee"+ anneeFacture);
				
			
			
			if (request.getParameter("etatEmplacement") != null)
			{
				this.etatEmplacement = request.getParameter("etatEmplacement");	
				
				if (etatEmplacement.equalsIgnoreCase("enactivite")) 
					etatEmplacement= "enActivite"; 
				else if (etatEmplacement.equalsIgnoreCase("termine"))	
					etatEmplacement= "termine";
				else if (etatEmplacement.equalsIgnoreCase("tous"))
					etatEmplacement= "tous";
				else
					etatEmplacement= "";
			}
			
			if (request.getParameter("etatFacture") != null)
			{
				this.etatFacture = request.getParameter("etatFacture");					
			}
			
			
					
			//System.out.println("Choix="+request.getParameter("choix"));
			if (request.getParameter("choix") != null)
				this.choix= request.getParameter("choix");				   
			    Redevable redevable =reqRed.getRedevable(numRedevable);		    
		        this.numRedevable= redevable.getNumRedevable();	  
		        
		        
		        if (numRedevable != null && !numRedevable.equalsIgnoreCase("0"))	    
		        {
		        	this.peutEtreSupprimer = ""+  reqRed.peutEtreSupprimer(numRedevable);
		        	this.peutEtreDesactvier = ""+  reqRed.peutEtreSupprimer(numRedevable);
		        	this.raisonSocialeRedevable=redevable.getRaisonSocialeRedevable();
		        	this.natureJuridiqueRedevable = redevable.getNatureJuridiqueRedevable();
		        	
			    	this.nomRedevable=redevable.getNomRedevable();
			    	//DebuggerLog4J.logger.debug("NOM REDEAVBLE" +  nomRedevable);			    	
					nomRedevable= nomRedevable.replaceAll("\"", "&quot;");
					
			    	
			    	this.nomJeuneFilleRedevable=redevable.getNomJeuneFilleRedevable();
			    	this.prenomRedevable=redevable.getPrenomRedevable();			    	
			    	this.informationComplementaire=redevable.getInformationComplementaire();
			    	this.commentaire=redevable.getCommentaire();			    	
			    	this.siret=redevable.getSiret();			    	
			    	this.siren=redevable.getSiren();
			    	
			    	if (siret == null || siret.equalsIgnoreCase("null") ) siret = "";			    				    	
			    	if (siren == null || siren.equalsIgnoreCase("null") ) siren = "";
			    	if (commentaire == null || commentaire.equalsIgnoreCase("null") ) commentaire = "";
			    	if (informationComplementaire == null || informationComplementaire.equalsIgnoreCase("null") ) informationComplementaire = "";
			    	
			    	this.responsableRedevable=redevable.getResponsableRedevable();
			    	responsableRedevable= responsableRedevable.replaceAll("\"", "&quot;");
			    	this.dateNaissanceRedevable=redevable.getDateNaissanceRedevable();
			    	this.lieuNaissanceRedevable=redevable.getLieuNaissanceRedevable();
			    	this.nationalliteRedevable=redevable.getNationalliteRedevable();
			    	this.numVoieRedevable=redevable.getNumVoieRedevable();
			    	this.codeVoixRedevable=redevable.getCodeVoixRedevable();
			    	this.codeRivolieRedevable= redevable.getCodeRivolieRedevable();
			    	this.adressRedevable=redevable.getAdressRedevable();
			    	this.complement1AdressRedevable=redevable.getComplement1AdressRedevable();
			    	this.complement2AdressRedevable=redevable.getComplement2AdressRedevable();
			    	this.codePostaleRedevable=redevable.getCodePostaleRedevable();
			    	this.villeeRedevable=redevable.getVilleeRedevable();
			    	this.cedexRedevable=redevable.getCedexRedevable();
			    	this.numTelFixeRedevable=redevable.getNumTelFixeRedevable();
			    	this.numTelPortableRedevable=redevable.getNumTelPortableRedevable();
			    	this.numTelFaxeRedevable=redevable.getNumTelFaxeRedevable();
			    	this.emailRedevable=redevable.getEmailRedevable(); 
			    	DataFromBD d = new DataFromBD();	    	
			    	this.proffesionRedevable=d.getLibelleProffesion(Integer.parseInt(redevable.getProffesionRedevable()));  
			        this.raisonSocialeLiquidateur=redevable.getRaisonSocialeLiquidateur();
			    	this.nomLiquidateur=redevable.getNomLiquidateur();	    	
			    	this.prenomLiquidateur=redevable.getPrenomLiquidateur();
			    	this.numVoieLiquidateur=redevable.getNumVoieLiquidateur();
			    	this.codeVoixLiquidateur=redevable.getCodeVoixLiquidateur();
			    	this.adressLiquidateur=redevable.getAdressLiquidateur();
			    	this.complement1AdressLiquidateur=redevable.getComplement1AdressLiquidateur();
			    	this.complement2AdressLiquidateur=redevable.getComplement2AdressLiquidateur();
			    	this.codePostaleLiquidateur=redevable.getCodePostaleLiquidateur();
			    	this.villeeLiquidateur=redevable.getVilleeLiquidateur();
			    	
			    	if (adressLiquidateur == null) adressLiquidateur ="";
			    	if (codeVoixLiquidateur == null) codeVoixLiquidateur ="";
			    	if (numVoieLiquidateur == null) numVoieLiquidateur ="";			    	
			    	if (villeeLiquidateur == null) villeeLiquidateur ="";
			    	if (complement1AdressLiquidateur == null) complement1AdressLiquidateur ="";
			    	if (complement2AdressLiquidateur == null) complement2AdressLiquidateur ="";
			    	
			    	this.cedexLiquidateur=redevable.getCedexLiquidateur();
			    	this.numTelFixeLiquidateur=redevable.getNumTelFixeLiquidateur();
			    	this.numTelPortableLiquidateur=redevable.getNumTelPortableLiquidateur();
			    	this.numTelFaxeLiquidateur=redevable.getNumTelFaxeLiquidateur();
			    	this.emailLiquidateur=redevable.getEmailLiquidateur();
			    	this.redevableActif = redevable.getRedevableActif();
			    	this.immeubleResidenceRedevable = redevable.getImmeubleResidenceRedevable();
			    	if(immeubleResidenceRedevable ==null) immeubleResidenceRedevable ="";
			    	this.complementNumeroRueRedevable = redevable.getComplementNumeroRueRedevable();
			    	this.complementNumeroRueLiquidateur = redevable.getComplementNumeroRueLiquidateur();
			    	
		        }
		}
		}
		catch (Exception e) 
		{
			DebuggerLog4J.logger.debug("Erreur" + e.getMessage());
			e.printStackTrace();
		}
	      
    }    
    
    public String getDateDuJour() {
		return dateDuJour;
	}


	public void setDateDuJour(String dateDuJour) {
		this.dateDuJour = dateDuJour;
	}


	public String getAffichageEmplacement()	
    {
        	String res = "";		
		
			res = "<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\">\n"
					+ "<tr>"					
					+ "<td  colspan=\"7\" bgcolor=\"#DFFFEF\" height=\"19\" >\n"
					+ "<p align=\"right\" style=\"margin-left: 5\"><b><font face=\"Arial\" size=\"3\">\n"
					+ "Ajouter un emplacement :&nbsp;</font></b>\n"
					+ "<a href=\""
					+ conf.getPreUrlJbin()
					+ "entree?action=choisir_type_emplacement.jsp&numRedevable="
					+ numRedevable
					+ "\"><img border=\"0\" src=\""
					+ conf.getUrlImageServlet()
					+ "ok.gif\" width=\"23\" height=\"19\"></a>\n"
					+ "</td>\n"
					+ "</tr>\n" + "</table>";		 
		
		return res;
    }
    
    public String getListeFactureUtilisateur()	
    {
        RequestFacture reqFac = new RequestFacture();
        if (numRedevable ==null) numRedevable ="0";       
		Vector contenu= reqFac.getfacturePdfUtilisateur(Integer.parseInt(numRedevable),etatFacture,anneeFacture,false);		
		StaticManipHtml mHtml = new StaticManipHtml();						 
		String res = mHtml.genererListeFactureUtilsateurPdf(contenu);	
		////System.out.println("NOMBRE DE FACTURE "+contenu.size());
		////System.out.println(res);
		contenu = null;
		System.gc();
		return res;
    }
    
    public String getListeFactureAnnuleeUtilisateur()
    {
    	RequestFacture reqFac = new RequestFacture();
        if (numRedevable ==null) numRedevable ="0";       
   		Vector contenu= reqFac.getfacturePdfUtilisateur(Integer.parseInt(numRedevable),etatFacture,anneeFacture, true);		
   		StaticManipHtml mHtml = new StaticManipHtml();						 
   		String res = mHtml.genererListeFactureUtilsateurPdf(contenu);	
   		////System.out.println("NOMBRE DE FACTURE "+contenu.size());
   		////System.out.println(res);
   		contenu = null;
   		System.gc();
   		return res;
    	
    }
     
    
    public String getListeEmplacementRedevable()	
    {
         //////////////////////////////////////////////////////////
		//AFFICHAGE DE LA LISTE DES EMPLACMENT ;			
        //////////////////////////////////////////////////////////
		RequestEmplacement req = new RequestEmplacement();	
		//Vector contenu = req.getListeEmplacement(numRedevable,etatEmplacement);
		Vector contenu = req.getListeEmplacement(numRedevable,etatEmplacement);
		StaticManipHtml html = new StaticManipHtml();		
		String res= html.genererListeEmplacement(contenu);
		contenu = null;
		System.gc();
		return res;
         
    }
    
    //Paul 
    public String getListeCommentaire() throws ParseException	
    {
         //////////////////////////////////////////////////////////
		//AFFICHAGE DE LA LISTE DES COMMENTAIRES ;			
        //////////////////////////////////////////////////////////
		RequestCommentaire req = new RequestCommentaire();	
		Vector contenu = req.getListeCommentaire(numRedevable);
		StaticManipHtml html = new StaticManipHtml();		
		String res= html.genererListeCommentaire(contenu);
		contenu = null;
		System.gc();
		return res;
         
    }
    
    
    
    
     public String getNumRedevable()
     {
        return numRedevable;
         
     }
    
    /**
 * @return the adressRedevable
 */
public String getAdressRedevable() 
{
	////System.out.println("adressRedevable="+adressRedevable);
	return adressRedevable;
}
/**
 * @param adressRedevable the adressRedevable to set
 */
public void setAdressRedevable(String adressRedevable) {
	this.adressRedevable = adressRedevable;
}
/**
 * @return the cedexRedevable
 */
public String getCedexRedevable() 
{
	if (cedexRedevable != null && cedexRedevable.equalsIgnoreCase("true"))
		return "checked";
	else
		return "";
}
/**
 * @param cedexRedevable the cedexRedevable to set
 */
public void setCedexRedevable(String cedexRedevable) {
	this.cedexRedevable = cedexRedevable;
}
/**
 * @return the codePostaleRedevable
 */
public String getCodePostaleRedevable() {
	return codePostaleRedevable;
}
/**
 * @param codePostaleRedevable the codePostaleRedevable to set
 */
public void setCodePostaleRedevable(String codePostaleRedevable) {
	this.codePostaleRedevable = codePostaleRedevable;
}
/**
 * @return the codeVoixRedevable
 */
public String getCodeVoixRedevable() {
	return codeVoixRedevable;
}
/**
 * @param codeVoixRedevable the codeVoixRedevable to set
 */
public void setCodeVoixRedevable(String codeVoixRedevable) {
	this.codeVoixRedevable = codeVoixRedevable;
}
/**
 * @return the complement1AdressRedevable
 */
public String getComplement1AdressRedevable() {
	return complement1AdressRedevable;
}
/**
 * @param complement1AdressRedevable the complement1AdressRedevable to set
 */
public void setComplement1AdressRedevable(String complement1AdressRedevable) {
	this.complement1AdressRedevable = complement1AdressRedevable;
}
/**
 * @return the complement2AdressRedevable
 */
public String getComplement2AdressRedevable() {
	return complement2AdressRedevable;
}
/**
 * @param complement2AdressRedevable the complement2AdressRedevable to set
 */
public void setComplement2AdressRedevable(String complement2AdressRedevable) {
	this.complement2AdressRedevable = complement2AdressRedevable;
}
/**
 * @return the dateNaissanceRedevable
 */
public String getDateNaissanceRedevable() {
	return dateNaissanceRedevable;
}
/**
 * @param dateNaissanceRedevable the dateNaissanceRedevable to set
 */
public void setDateNaissanceRedevable(String dateNaissanceRedevable) {
	this.dateNaissanceRedevable = dateNaissanceRedevable;
}
/**
 * @return the emailRedevable
 */
public String getEmailRedevable() {
	return emailRedevable;
}
/**
 * @param emailRedevable the emailRedevable to set
 */
public void setEmailRedevable(String emailRedevable) {
	this.emailRedevable = emailRedevable;
}
/**
 * @return the lieuNaissanceRedevable
 */
public String getLieuNaissanceRedevable() {
	return lieuNaissanceRedevable;
}
/**
 * @param lieuNaissanceRedevable the lieuNaissanceRedevable to set
 */
public void setLieuNaissanceRedevable(String lieuNaissanceRedevable) {
	this.lieuNaissanceRedevable = lieuNaissanceRedevable;
}
/**
 * @return the nationalliteRedevable
 */
public String getNationalliteRedevable() {
	return nationalliteRedevable;
}
/**
 * @param nationalliteRedevable the nationalliteRedevable to set
 */
public void setNationalliteRedevable(String nationalliteRedevable) {
	this.nationalliteRedevable = nationalliteRedevable;
}
/**
 * @return the nomJeuneFilleRedevable
 */
public String getNomJeuneFilleRedevable() {
	return nomJeuneFilleRedevable;
}
/**
 * @param nomJeuneFilleRedevable the nomJeuneFilleRedevable to set
 */
public void setNomJeuneFilleRedevable(String nomJeuneFilleRedevable) {
	this.nomJeuneFilleRedevable = nomJeuneFilleRedevable;
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
 * @return the numTelFaxeRedevable
 */
public String getNumTelFaxeRedevable() {
	return numTelFaxeRedevable;
}
/**
 * @param numTelFaxeRedevable the numTelFaxeRedevable to set
 */
public void setNumTelFaxeRedevable(String numTelFaxeRedevable) {
	this.numTelFaxeRedevable = numTelFaxeRedevable;
}
/**
 * @return the numTelFixeRedevable
 */
public String getNumTelFixeRedevable() {
	return numTelFixeRedevable;
}
/**
 * @param numTelFixeRedevable the numTelFixeRedevable to set
 */
public void setNumTelFixeRedevable(String numTelFixeRedevable) {
	this.numTelFixeRedevable = numTelFixeRedevable;
}
/**
 * @return the numTelPortableRedevable
 */
public String getNumTelPortableRedevable() {
	return numTelPortableRedevable;
}
/**
 * @param numTelPortableRedevable the numTelPortableRedevable to set
 */
public void setNumTelPortableRedevable(String numTelPortableRedevable) {
	this.numTelPortableRedevable = numTelPortableRedevable;
}
/**
 * @return the numVoieRedevable
 */
public String getNumVoieRedevable() 
{
	return numVoieRedevable;	
}
/**
 * @param numVoieRedevable the numVoieRedevable to set
 */
public void setNumVoieRedevable(String numVoieRedevable) {
	this.numVoieRedevable = numVoieRedevable;
}
/**
 * @return the prenomRedevable
 */
public String getPrenomRedevable() {
	return prenomRedevable;
}
/**
 * @param prenomRedevable the prenomRedevable to set
 */
public void setPrenomRedevable(String prenomRedevable) {
	this.prenomRedevable = prenomRedevable;
}
/**
 * @return the raisonSocialeRedevable
 */
public String getRaisonSocialeRedevable()
{
	String res="";
	StaticManipHtml mHtml = new StaticManipHtml();
	DataFromBD data = new DataFromBD();
	Vector contenu = data.getTousLesRaisonSocial();
	System.out.println("natureJuridiqueRedevable"+natureJuridiqueRedevable);

	if (raisonSocialeRedevable !=null )
	res= StaticManipHtml.genererListeDeroulanteApartirDeVacteur("raisonSocialeRedevable",
			raisonSocialeRedevable, contenu, 1, false);	
	else
		res= StaticManipHtml.genererListeDeroulanteApartirDeVacteur("raisonSocialeRedevable",
				"", contenu, 1, false);
	contenu = null;
	System.gc();
	return res;	
}
public String getRaisonSocialeRedevableok()
{
	return raisonSocialeRedevable;	
}


/**
 * @param raisonSocialeRedevable the raisonSocialeRedevable to set
 */
public void setRaisonSocialeRedevable(String raisonSocialeRedevable) {
	this.raisonSocialeRedevable = raisonSocialeRedevable;
}
/**
 * @return the responsableRedevable
 */
public String getResponsableRedevable() {
	return responsableRedevable;
}
/**
 * @param responsableRedevable the responsableRedevable to set
 */
public void setResponsableRedevable(String responsableRedevable) {
	this.responsableRedevable = responsableRedevable;
}
/**
 * @return the villeeRedevable
 */
public String getVilleeRedevable() {
	return villeeRedevable;
}
/**
 * @param villeeRedevable the villeeRedevable to set
 */
public void setVilleeRedevable(String villeeRedevable) {
	this.villeeRedevable = villeeRedevable;
}

/**
 * @return the choix
 */
public String getChoix() {
	return choix;
}

/**
 * @param choix the choix to set
 */
public void setChoix(String choix) {
	this.choix = choix;
}

/**
 * @return the proffesionRedevable
 */
public String getProffesionRedevable() 
{
	String res="";
	StaticManipHtml mHtml = new StaticManipHtml();
	DataFromBD data = new DataFromBD();
	Vector contenu =data.getTousLesProffesions();	
	if (proffesionRedevable !=null && ! proffesionRedevable.equalsIgnoreCase(""))
	{	
		res= StaticManipHtml.genererListeDeroulanteApartirDeVacteur("proffesionRedevable",
				proffesionRedevable, contenu, 1, false);
	}
	else
	{			
		//String stProffesionRedevable = (String)contenu.elementAt(0);
		//valeur par defaut divers:
		String proffesionRedevable="DIVERS";
		res= StaticManipHtml.genererListeDeroulanteApartirDeVacteur("proffesionRedevable",
				proffesionRedevable, contenu, 1, false);
	}
	contenu = null;
	System.gc();
	return res;	
}

/**
 * @param proffesionRedevable the proffesionRedevable to set
 */
public void setProffesionRedevable(String proffesionRedevable) {
	this.proffesionRedevable = proffesionRedevable;
}
        

public String getListePaiementFacture()	
{
    RequestFacture reqFac = new RequestFacture();
    if (numRedevable ==null) numRedevable ="0";       
	Vector contenu= reqFac.getfacturePdfPourSuiviePayement(Integer.parseInt(numRedevable),etatFacture,anneeFacture);		
	StaticManipHtml mHtml = new StaticManipHtml();						 
	ListePaiementFacture = mHtml.genererListePaiementRedevable(contenu);
	contenu = null;
	System.gc();
	return ListePaiementFacture;
	
}

/**
 * @return the codeRivolieRedevable
 */
public final String getCodeRivolieRedevable() {
	return codeRivolieRedevable;
}

/**
 * @param codeRivolieRedevable the codeRivolieRedevable to set
 */
public final void setCodeRivolieRedevable(String codeRivolieRedevable) {
	this.codeRivolieRedevable = codeRivolieRedevable;
}

/**
 * @return the redevableActif
 */
public final String getRedevableActif() 
{
	 	RequestFacture reqFac = new RequestFacture();
	    if (numRedevable ==null) numRedevable ="0";       
		Vector contenu= new Vector();
		contenu.add("OUI");
		contenu.add("NON");	
		String selection= "";
		if (redevableActif.equalsIgnoreCase("true")) selection ="OUI";
		else selection ="NON";
		StaticManipHtml mHtml = new StaticManipHtml();						 
		redevableActif = mHtml.genererListeDeroulanteApartirDeVacteur("redevableActif", selection,
				contenu, 1, false) ; 
		contenu = null;
		System.gc();
		return redevableActif;
}

/**
 * @param redevableActif the redevableActif to set
 */
public final void setRedevableActif(String redevableActif) 
{
   
}

/**
 * @return the peutEtreDesactvier
 */
public final String getPeutEtreDesactvier() {
	return peutEtreDesactvier;
}

/**
 * @param peutEtreDesactvier the peutEtreDesactvier to set
 */
public final void setPeutEtreDesactvier(String peutEtreDesactvier) {
	this.peutEtreDesactvier = peutEtreDesactvier;
}

/**
 * @return the etatFacture
 */
public final String getEtatFacture() {
	return etatFacture;
}

/**
 * @param etatFacture the etatFacture to set
 */
public final void setEtatFacture(String etatFacture) {
	this.etatFacture = etatFacture;
}

public String getEtatEmplacement() {
	return etatEmplacement;
}

public void setEtatEmplacement(String etatEmplacement) {
	this.etatEmplacement = etatEmplacement;
}

public String getListeDesFacturesNonPayer() 
{
	System.out.println("getListeDesFacturesNonPayer....");
    RequestFacture reqFac = new RequestFacture();
    if (numRedevable ==null) numRedevable ="0";       
	Vector contenu= reqFac.getListeFactureUtilsateurPdfNonPaye(Integer.parseInt(numRedevable),etatFacture,anneeFacture);		
	StaticManipHtml mHtml = new StaticManipHtml();						 
	ListeDesFacturesNonPayer = mHtml.genererListeFactureUtilsateurPdfNonPaye(contenu);
	contenu = null;
	System.gc();
	return ListeDesFacturesNonPayer;
}

public void setListeDesFacturesNonPayer(String listeDesFacturesNonPayer) {
	ListeDesFacturesNonPayer = listeDesFacturesNonPayer;
}
    

public String getAnneeFacture() {
	return anneeFacture;
}


public void setAnneeFacture(String anneeFacture) {
	this.anneeFacture = anneeFacture;
}


/**
 * @return the listeRemboursementUtilisateur
 */
public String getListeRemboursementUtilisateur()
{
    RequestRedevable requestRedevable = new RequestRedevable();
    if (numRedevable ==null) numRedevable ="0";       
	Vector contenu= requestRedevable.getListeRemboursement( ""+numRedevable, anneeFacture) ;			
	StaticManipHtml mHtml = new StaticManipHtml();						 
	ListeRemboursementUtilisateur = mHtml.genererListeRemboursementRedevable(contenu);
	contenu = null;
	System.gc();
	return ListeRemboursementUtilisateur;
}


/**
 * @param listeRemboursementUtilisateur the listeRemboursementUtilisateur to set
 */
public void setListeRemboursementUtilisateur(
		String listeRemboursementUtilisateur) {
	ListeRemboursementUtilisateur = listeRemboursementUtilisateur;
}


/**
 * @return the informationComplementaire
 */
public String getInformationComplementaire() {
	return informationComplementaire;
}


/**
 * @param informationComplementaire the informationComplementaire to set
 */
public void setInformationComplementaire(String informationComplementaire) {
	this.informationComplementaire = informationComplementaire;
}


/**
 * @return the commentaire
 */
public String getCommentaire() {
	return commentaire;
}


/**
 * @param commentaire the commentaire to set
 */
public void setCommentaire(String commentaire) {
	this.commentaire = commentaire;
}


/**
 * @return the siret
 */
public String getSiret() {
	return siret;
}


/**
 * @param siret the siret to set
 */
public void setSiret(String siret) {
	this.siret = siret;
}


/**
 * @return the siren
 */
public String getSiren() {
	return siren;
}


/**
 * @param siren the siren to set
 */
public void setSiren(String siren) {
	this.siren = siren;
}


/**
 * @return the immeubleResidenceRedevable
 */
public String getImmeubleResidenceRedevable() {
	return immeubleResidenceRedevable;
}


/**
 * @param immeubleResidenceRedevable the immeubleResidenceRedevable to set
 */
public void setImmeubleResidenceRedevable(String immeubleResidenceRedevable) {
	this.immeubleResidenceRedevable = immeubleResidenceRedevable;
}
	

}