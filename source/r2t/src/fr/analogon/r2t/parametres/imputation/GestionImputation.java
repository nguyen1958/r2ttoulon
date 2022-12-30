package fr.analogon.r2t.parametres.imputation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.request.RequestImputation;


/**
 * Permet de gerer les Imputations .
 * Gestion des imputations. . Sofien CHARFI
 * 
 * @since 1.0
 * @version 1.3
 */
public class GestionImputation extends fr.analogon.r2t.main.RacineServlet 
{	
	private HttpServletRequest request;	
	private String choix= "";

	
	private boolean creation=true;
	private String numtypeTaxe= "";
	private String libelle= "";
	private String designation= "";
	private String codeBudget= "";
	private String code= "";
	private String section= "";
	private String codeFonction= "";
	private String libelleFonction="";
	private String codeCentreResponsable= "";
	private String libelleCentreResponsable= "";
	private String codeCentreExecution= "";
	private String libelleCentreExecution= "";
	private String necessiteControleTerrain= "";
	private String controleInduitFacturation= "";	
	private String nomElu="";
	private String eluRenseignement1="";
	private String eluRenseignement2="";
	private String eluRenseignement3="";
	private String nomGestionnaire="";
	private String telGestionnaire="";
	private String faxGestionnaire="";
	
	private String minimumPerception="";
	private String minimumDeFacturation="";
	private String anneeExercice="";
	private String typeFacturation="";
	

	private String coefficientNumerique="";
	private String cycleFacturation="";
	private String renouvellementAutomatique="";
	private String marche="";
	
	//Paul evolution eCadre Budgetaire
	private String chapitre="";
	private String nature="";
	private String fonction="";
	private String codeOpeEquipement="";
	private String typeMouvement="";
	private String sens="";
	private String codeSegStructurelle="";
	private String codeEleStructurelleGestionnaire="";
	private String codeEleStructurelleDestinataire="";
	private String codeSegment1="";
	private String codeEleSectoriel1="";
	private String codeSegment2="";
	private String codeEleSectoriel2="";
	private String codeSegment3="";
	private String codeEleSectoriel3="";
	private String codeSegment4="";
	private String codeEleSectoriel4="";
	private String codeSegment5="";
	private String codeEleSectoriel5="";
	private String codeSegOperationnel="";
	private String codeEleOperationnel="";
	private String codeSegStrategique="";
	private String codeEleStrategique="";
	
	
	
	public String getCycleFacturation() {
		return cycleFacturation;
	}

	public void setCycleFacturation(String cycleFacturation) {
		this.cycleFacturation = cycleFacturation;
	}

	public String getRenouvellementAutomatique() {
		return renouvellementAutomatique;
	}

	public void setRenouvellementAutomatique(String renouvellementAutomatique) {
		this.renouvellementAutomatique = renouvellementAutomatique;
	}

	public String getCoefficientNumerique() {
		return coefficientNumerique;
	}

	public void setCoefficientNumerique(String coefficientNumerique) {
		this.coefficientNumerique = coefficientNumerique;
	}


	/**
	 * @return the eluRenseignement1
	 */
	public final String getEluRenseignement1() {
		return eluRenseignement1;
	}

	/**
	 * @param eluRenseignement1 the eluRenseignement1 to set
	 */
	public final void setEluRenseignement1(String eluRenseignement1) {
		eluRenseignement1 = eluRenseignement1;
	}

	/**
	 * @return the eluRenseignement2
	 */
	public final String getEluRenseignement2() {
		return eluRenseignement2;
	}

	/**
	 * @param eluRenseignement2 the eluRenseignement2 to set
	 */
	public final void setEluRenseignement2(String eluRenseignement2) {
		eluRenseignement2 = eluRenseignement2;
	}

	/**
	 * @return the eluRenseignement3
	 */
	public final String getEluRenseignement3() {
		return eluRenseignement3;
	}

	/**
	 * @param eluRenseignement3 the eluRenseignement3 to set
	 */
	public final void setEluRenseignement3(String eluRenseignement3) {
		eluRenseignement3 = eluRenseignement3;
	}

	/**
	 * @return the faxGestionnaire
	 */
	public final String getFaxGestionnaire() {
		return faxGestionnaire;
	}

	/**
	 * @param faxGestionnaire the faxGestionnaire to set
	 */
	public final void setFaxGestionnaire(String faxGestionnaire) {
		this.faxGestionnaire = faxGestionnaire;
	}

	/**
	 * @return the nomElu
	 */
	public final String getNomElu() {
		return nomElu;
	}

	/**
	 * @param nomElu the nomElu to set
	 */
	public final void setNomElu(String nomElu) {
		this.nomElu = nomElu;
	}

	/**
	 * @return the nomGestionnaire
	 */
	public final String getNomGestionnaire() {
		return nomGestionnaire;
	}

	/**
	 * @param nomGestionnaire the nomGestionnaire to set
	 */
	public final void setNomGestionnaire(String nomGestionnaire) {
		this.nomGestionnaire = nomGestionnaire;
	}

	/**
	 * @return the telGestionnaire
	 */
	public final String getTelGestionnaire() {
		return telGestionnaire;
	}

	/**
	 * @param telGestionnaire the telGestionnaire to set
	 */
	public final void setTelGestionnaire(String telGestionnaire) {
		this.telGestionnaire = telGestionnaire;
	}

	
	//public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)			
	public void doMain(HttpServletRequest request, HttpServletResponse response)
	{
		try 
		{			
			//System.out.println("GESTION Imputation.....................................");
			//System.out.println("set request");
			this.request = request;			
			getParametres();	
			ServletContext sc = getServletConfig().getServletContext();	
			if( choix.equalsIgnoreCase("modifier") || choix.equalsIgnoreCase("Ajouter") )
			{
				//System.out.println();
				RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=empl_gestion_imputation.jsp" +
						"&choix=modifier&numtypeTaxe="+numtypeTaxe);
				rd.forward(request, response);			
			}
			else
			{			
				RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=liste_imputation.jsp");
				rd.forward(request, response);				
			}
			
		} 
		catch (Exception e) 
		{
			//System.out.println("Gestion d'imputation.....................[Erreur]");
			debug.logger.fatal(e.getMessage());
		}		
	}

	
	public void destroy() 
	{
		super.destroy();		
	}

	/**
	 * Permet de recuperer les parametres html . Sofien CHARFI
	 * 
	 * @since 1.0
	 */
	private void getParametres() 
	{		
		
		////System.out.println("TEst"+request.getParameter("texteReponse"));
		
		if ((request.getParameter("choix") != null)	) this.choix = request.getParameter("choix");	
		if (request.getParameter("nomElu") != null)	this.nomElu = request.getParameter("nomElu");
		if (request.getParameter("eluRenseignement1") != null)	this.eluRenseignement1 = request.getParameter("eluRenseignement1");
		if (request.getParameter("eluRenseignement2") != null)	this.eluRenseignement2 = request.getParameter("eluRenseignement2");
		if (request.getParameter("eluRenseignement3") != null)	this.eluRenseignement3 = request.getParameter("eluRenseignement3");
		if (request.getParameter("nomGestionnaire") != null)	this.nomGestionnaire = request.getParameter("nomGestionnaire");
		if (request.getParameter("telGestionnaire") != null)	this.telGestionnaire = request.getParameter("telGestionnaire");
		if (request.getParameter("faxGestionnaire") != null)	this.faxGestionnaire = request.getParameter("faxGestionnaire");
		if ((request.getParameter("numtypeTaxe") != null))  this. numtypeTaxe = request.getParameter("numtypeTaxe");
		if ((request.getParameter("libelle") != null))  this. libelle = request.getParameter("libelle");
		if ((request.getParameter("designation") != null))  this. designation = request.getParameter("designation");
		if ((request.getParameter("codeBudget") != null))  this.codeBudget= request.getParameter("codeBudget");
		if ((request.getParameter("code") != null))  this.code = request.getParameter("code");
		if ((request.getParameter("section") != null))  this.section = request.getParameter("section");
		if ((request.getParameter("codeFonction") != null))  this.codeFonction = request.getParameter("codeFonction");
		if ((request.getParameter("libelleFonction") != null))  this.libelleFonction = request.getParameter("libelleFonction");
		if ((request.getParameter("codeCentreResponsable") != null))  this.codeCentreResponsable = request.getParameter("codeCentreResponsable");
		if ((request.getParameter("libelleCentreResponsable") != null))  this.libelleCentreResponsable = request.getParameter("libelleCentreResponsable");
		if ((request.getParameter("codeCentreExecution") != null))  this.codeCentreExecution = request.getParameter("codeCentreExecution");
		if ((request.getParameter("libelleCentreExecution") != null))  this.libelleCentreExecution = request.getParameter("libelleCentreExecution");
		if ((request.getParameter("necessiteControleTerrain") != null))  this.necessiteControleTerrain = request.getParameter("necessiteControleTerrain");
		if ((request.getParameter("controleInduitFacturation") != null))  this.controleInduitFacturation = request.getParameter("controleInduitFacturation");
		
		if (request.getParameter("minimumPerception") != null)	this.minimumPerception = request.getParameter("minimumPerception");
		
		if (request.getParameter("minimumDeFacturation") != null)	this.minimumDeFacturation = request.getParameter("minimumDeFacturation");
		
		
		
		if (request.getParameter("anneeExercice") != null)	this.anneeExercice = request.getParameter("anneeExercice");
		
		if (request.getParameter("typeFacturation") != null)	this.typeFacturation = request.getParameter("typeFacturation");

		if (request.getParameter("coefficientNumerique") != null)	
			this.coefficientNumerique = request.getParameter("coefficientNumerique");
		
		if (request.getParameter("cycleFacturation") != null)	
			this.cycleFacturation = request.getParameter("cycleFacturation");
		
		if (request.getParameter("renouvellementAutomatique") != null)	
			this.renouvellementAutomatique = request.getParameter("renouvellementAutomatique");
		
		
		if (request.getParameter("marche") != null)	
			this.marche = request.getParameter("marche");
		
		//Paul evolution eCadre Budgetaire
		chapitre=request.getParameter("chapitre");
		nature=request.getParameter("nature");
		fonction=request.getParameter("fonction");
		codeOpeEquipement=request.getParameter("codeOpeEquipement");
		typeMouvement=request.getParameter("typeMouvement");
		sens=request.getParameter("sens");
		codeSegStructurelle=request.getParameter("codeSegStructurelle");
		codeEleStructurelleGestionnaire=request.getParameter("codeEleStructurelleGestionnaire");
		codeEleStructurelleDestinataire=request.getParameter("codeEleStructurelleDestinataire");
		codeSegment1=request.getParameter("codeSegment1");
		codeEleSectoriel1=request.getParameter("codeEleSectoriel1");
		codeSegment2=request.getParameter("codeSegment2");
		codeEleSectoriel2=request.getParameter("codeEleSectoriel2");
		codeSegment3=request.getParameter("codeSegment3");
		codeEleSectoriel3=request.getParameter("codeEleSectoriel3");
		codeSegment4=request.getParameter("codeSegment4");
		codeEleSectoriel4=request.getParameter("codeEleSectoriel4");
		codeSegment5=request.getParameter("codeSegment5");
		codeEleSectoriel5=request.getParameter("codeEleSectoriel5");
		codeSegOperationnel=request.getParameter("codeSegOperationnel");
		codeEleOperationnel=request.getParameter("codeEleOperationnel");
		codeSegStrategique=request.getParameter("codeSegStrategique");
		codeEleStrategique=request.getParameter("codeEleStrategique");
		//////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////
	    //TRAITEMENT DES DONNNEE ET ACCES A LA BD
		//////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////
		//System.out.println("choixchoixchoixchoixchoixchoix"+ choix);
		//System.out.println("libelle centre responsable="+ libelleCentreResponsable);
		System.out.println("Marche"+marche);
		String idMarche ="";
		if (marche != null && marche.length() !=0   )
			idMarche= marche.substring(0,marche.indexOf("---"));
		
		if(choix.equalsIgnoreCase("ajouter"))
		{
			//System.out.println("Ajout d'un nouveau type de taxe"+ libelle);
			
			RequestImputation reqImutation = new RequestImputation();
			this.numtypeTaxe = String.valueOf(
					reqImutation.ajouterTypeTaxe( 
					libelle, designation,codeBudget,
					code,section,codeFonction, libelleFonction, codeCentreResponsable,
					libelleCentreResponsable, codeCentreExecution,
					 libelleCentreExecution, necessiteControleTerrain,
					 controleInduitFacturation,nomElu, eluRenseignement1, eluRenseignement2,
					 eluRenseignement3, nomGestionnaire,telGestionnaire, 
					 faxGestionnaire,minimumPerception,anneeExercice,typeFacturation, 
					 cycleFacturation,renouvellementAutomatique,minimumDeFacturation,idMarche,
					 chapitre,nature,fonction,codeOpeEquipement,typeMouvement,sens,
					 codeSegStructurelle,codeEleStructurelleGestionnaire,codeEleStructurelleDestinataire,
					 codeSegment1,codeEleSectoriel1,codeSegment2,codeEleSectoriel2,codeSegment3,codeEleSectoriel3,
					 codeSegment4,codeEleSectoriel4,codeSegment5,codeEleSectoriel5,
					 codeSegOperationnel,codeEleOperationnel,codeSegStrategique,codeEleStrategique));
		}
		else if(choix.equalsIgnoreCase("modifier"))
		{
			
			//System.out.println("Modification du type de taxe"+ libelle);
			RequestImputation reqImutation = new RequestImputation();
			reqImutation.majTypeTaxe(numtypeTaxe, libelle, designation,codeBudget,
					code, section, codeFonction, libelleFonction, codeCentreResponsable,
					libelleCentreResponsable, codeCentreExecution, libelleCentreExecution,
					necessiteControleTerrain, controleInduitFacturation,nomElu, 
					eluRenseignement1, eluRenseignement2, eluRenseignement3, 
					nomGestionnaire,telGestionnaire, faxGestionnaire,
					minimumPerception,anneeExercice,typeFacturation,cycleFacturation,renouvellementAutomatique,
					minimumDeFacturation,idMarche,chapitre,nature,fonction,codeOpeEquipement,typeMouvement,sens,
					codeSegStructurelle,codeEleStructurelleGestionnaire,codeEleStructurelleDestinataire,
					codeSegment1,codeEleSectoriel1,codeSegment2,codeEleSectoriel2,codeSegment3,codeEleSectoriel3,
					codeSegment4,codeEleSectoriel4,codeSegment5,codeEleSectoriel5,
					codeSegOperationnel,codeEleOperationnel,codeSegStrategique,codeEleStrategique);			
		}	
		else if(choix.equalsIgnoreCase("supprimer"))
		{
			
			////System.out.println("Suppresion d'une reclamation"+idReclamation);
			RequestImputation reqImutation = new RequestImputation();
			reqImutation.supprimerImputation(numtypeTaxe,anneeExercice);
		}
		
		
		////System.out.println("**********************");		
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
	 * @return the code
	 */
	public final String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public final void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the codeBudget
	 */
	public final String getCodeBudget() {
		return codeBudget;
	}

	/**
	 * @param codeBudget the codeBudget to set
	 */
	public final void setCodeBudget(String codeBudget) {
		this.codeBudget = codeBudget;
	}

	/**
	 * @return the codeCentreExecution
	 */
	public final String getCodeCentreExecution() {
		return codeCentreExecution;
	}

	/**
	 * @param codeCentreExecution the codeCentreExecution to set
	 */
	public final void setCodeCentreExecution(String codeCentreExecution) {
		this.codeCentreExecution = codeCentreExecution;
	}

	/**
	 * @return the codeCentreResponsable
	 */
	public final String getCodeCentreResponsable() {
		return codeCentreResponsable;
	}

	/**
	 * @param codeCentreResponsable the codeCentreResponsable to set
	 */
	public final void setCodeCentreResponsable(String codeCentreResponsable) {
		this.codeCentreResponsable = codeCentreResponsable;
	}

	/**
	 * @return the codeFonction
	 */
	public final String getCodeFonction() {
		return codeFonction;
	}

	/**
	 * @param codeFonction the codeFonction to set
	 */
	public final void setCodeFonction(String codeFonction) {
		this.codeFonction = codeFonction;
	}

	/**
	 * @return the controleInduitFacturation
	 */
	public final String getControleInduitFacturation() {
		return controleInduitFacturation;
	}

	/**
	 * @param controleInduitFacturation the controleInduitFacturation to set
	 */
	public final void setControleInduitFacturation(String controleInduitFacturation) {
		this.controleInduitFacturation = controleInduitFacturation;
	}

	/**
	 * @return the creation
	 */
	public final boolean isCreation() {
		return creation;
	}

	/**
	 * @param creation the creation to set
	 */
	public final void setCreation(boolean creation) {
		this.creation = creation;
	}

	/**
	 * @return the designation
	 */
	public final String getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public final void setDesignation(String designation) {
		this.designation = designation;
	}

	

	/**
	 * @return the libelle
	 */
	public final String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public final void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the libelleCentreExecution
	 */
	public final String getLibelleCentreExecution() {
		return libelleCentreExecution;
	}

	/**
	 * @param libelleCentreExecution the libelleCentreExecution to set
	 */
	public final void setLibelleCentreExecution(String libelleCentreExecution) {
		this.libelleCentreExecution = libelleCentreExecution;
	}

	/**
	 * @return the libelleCentreResponsable
	 */
	public final String getLibelleCentreResponsable() {
		return libelleCentreResponsable;
	}

	/**
	 * @param libelleCentreResponsable the libelleCentreResponsable to set
	 */
	public final void setLibelleCentreResponsable(String libelleCentreResponsable) {
		this.libelleCentreResponsable = libelleCentreResponsable;
	}

	/**
	 * @return the libelleFonction
	 */
	public final String getLibelleFonction() {
		return libelleFonction;
	}

	/**
	 * @param libelleFonction the libelleFonction to set
	 */
	public final void setLibelleFonction(String libelleFonction) {
		this.libelleFonction = libelleFonction;
	}

	/**
	 * @return the necessiteControleTerrain
	 */
	public final String getNecessiteControleTerrain() {
		return necessiteControleTerrain;
	}

	/**
	 * @param necessiteControleTerrain the necessiteControleTerrain to set
	 */
	public final void setNecessiteControleTerrain(String necessiteControleTerrain) {
		this.necessiteControleTerrain = necessiteControleTerrain;
	}

	/**
	 * @return the numtypeTaxe
	 */
	public final String getNumtypeTaxe() {
		return numtypeTaxe;
	}

	/**
	 * @param numtypeTaxe the numtypeTaxe to set
	 */
	public final void setNumtypeTaxe(String numtypeTaxe) {
		this.numtypeTaxe = numtypeTaxe;
	}

	/**
	 * @return the request
	 */
	public final HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public final void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * @return the section
	 */
	public final String getSection() {
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public final void setSection(String section) {
		this.section = section;
	}

	/**
	 * @return the anneeExercice
	 */
	public final String getAnneeExercice() {
		return anneeExercice;
	}

	/**
	 * @param anneeExercice the anneeExercice to set
	 */
	public final void setAnneeExercice(String anneeExercice) {
		this.anneeExercice = anneeExercice;
	}

	/**
	 * @return the minimumPerception
	 */
	public final String getMinimumPerception() {
		return minimumPerception;
	}

	/**
	 * @param minimumPerception the minimumPerception to set
	 */
	public final void setMinimumPerception(String minimumPerception) {
		this.minimumPerception = minimumPerception;
	}

	/**
	 * @return the typeFacturation
	 */
	public final String getTypeFacturation() {
		return typeFacturation;
	}

	/**
	 * @param typeFacturation the typeFacturation to set
	 */
	public final void setTypeFacturation(String typeFacturation) {
		this.typeFacturation = typeFacturation;
	}

	public String getMarche() {
		return marche;
	}

	public void setMarche(String marche) {
		this.marche = marche;
	}


	
	
}
