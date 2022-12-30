package fr.analogon.r2t.parametres.imputation;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.FamilleMarche;
import fr.analogon.r2t.pojo.Imputation;
import fr.analogon.r2t.pojo.Marche;
import fr.analogon.r2t.request.DataFromBD;
import fr.analogon.r2t.request.RequestFamilleMarche;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean de Imputation  . Sofien CHARFI
 * 
 * @version 1.0
 * @since 1.0
 */
public class BAffImputation extends fr.analogon.r2t.main.RacineBean
{
	public static DebuggerLog4J debug = new DebuggerLog4J();
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
	private String necessiteControleTerrain= "OUI";
	private String controleInduitFacturation= "OUI";	
	private String listeImputation="";
	private String nomElu="";
	private String eluRenseignement1="";
	private String eluRenseignement2="";
	private String eluRenseignement3="";
	private String nomGestionnaire="";
	private String telGestionnaire="";
	private String faxGestionnaire="";
	private String minimumPerception="";
	private String minimumDeFacturation="0.00";
	private String anneeExercice="";
	private String typeFacturation="";
	private String  peutEtreSupprimer="";	
	private String coefficientNumerique="";
	private String cycleFacturation="Normal";
	private String renouvellementAutomatique="Non";
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
	

	public String getMarche() 
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();	
		RequestFamilleMarche  reqFamilleMarche= new RequestFamilleMarche();
		Vector listDesMarches =reqFamilleMarche.getListeFamilleMarche("","");
			
		for (int i = 0; i < listDesMarches.size(); i++) 
		{
			contenu.addElement(((FamilleMarche)listDesMarches.elementAt(i)).getCodeFamilleMarche() +"---"+
					((FamilleMarche)listDesMarches.elementAt(i)).getLibelleFamilleMarche());
		}	
		FamilleMarche familleMarche= reqFamilleMarche.getFamilleMarche(marche);
		
		
		marche = marche +"---" + familleMarche.getLibelleFamilleMarche();;
		res = StaticManipHtml.genererListeDeroulante("marche", 1,marche, contenu,true);
		contenu = null;
		System.gc();
		return res;
		
	}
	
	public String getRenouvellementAutomatique() 
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();		
		contenu.addElement("Non");
		contenu.addElement("Oui");
		res = StaticManipHtml.genererListeDeroulante("renouvellementAutomatique", 1,renouvellementAutomatique, contenu,false);
		contenu = null;
		System.gc();
		return res;
		
	}

	
	public void setRenouvellementAutomatique(String renouvellementAutomatique) {
		this.renouvellementAutomatique = renouvellementAutomatique;
	}

	public String getCycleFacturation() 
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();		
		contenu.addElement("Normal");
		contenu.addElement("Avance-Mensuelle");
		contenu.addElement("Avance-Trimestrielle");
		contenu.addElement("Avance-Annuelle");
		res = StaticManipHtml.genererListeDeroulante("cycleFacturation", 1,cycleFacturation, contenu,false);
		contenu = null;
		System.gc();
		return res;	
		
	}

	public void setCycleFacturation(String cycleFacturation) {
		this.cycleFacturation = cycleFacturation;
	}

	public String getCoefficientNumerique() {
		return coefficientNumerique;
	}

	public void setCoefficientNumerique(String coefficientNumerique) {
		this.coefficientNumerique = coefficientNumerique;
	}

	public String getPeutEtreSupprimer() {
		return peutEtreSupprimer;
	}

	public void setPeutEtreSupprimer(String peutEtreSupprimer) {
		this.peutEtreSupprimer = peutEtreSupprimer;
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

	public String getChapitre() {
		return chapitre;
	}

	public void setChapitre(String chapitre) {
		this.chapitre = chapitre;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public String getCodeOpeEquipement() {
		return codeOpeEquipement;
	}

	public void setCodeOpeEquipement(String codeOpeEquipement) {
		this.codeOpeEquipement = codeOpeEquipement;
	}

	public String getTypeMouvement() {
		return typeMouvement;
	}

	public void setTypeMouvement(String typeMouvement) {
		this.typeMouvement = typeMouvement;
	}

	public String getSens() {
		return sens;
	}

	public void setSens(String sens) {
		this.sens = sens;
	}

	public String getCodeSegStructurelle() {
		return codeSegStructurelle;
	}

	public void setCodeSegStructurelle(String codeSegStructurelle) {
		this.codeSegStructurelle = codeSegStructurelle;
	}

	public String getCodeEleStructurelleGestionnaire() {
		return codeEleStructurelleGestionnaire;
	}

	public void setCodeEleStructurelleGestionnaire(
			String codeEleStructurelleGestionnaire) {
		this.codeEleStructurelleGestionnaire = codeEleStructurelleGestionnaire;
	}

	public String getCodeEleStructurelleDestinataire() {
		return codeEleStructurelleDestinataire;
	}

	public void setCodeEleStructurelleDestinataire(
			String codeEleStructurelleDestinataire) {
		this.codeEleStructurelleDestinataire = codeEleStructurelleDestinataire;
	}

	public String getCodeSegment1() {
		return codeSegment1;
	}

	public void setCodeSegment1(String codeSegment1) {
		this.codeSegment1 = codeSegment1;
	}

	public String getCodeEleSectoriel1() {
		return codeEleSectoriel1;
	}

	public void setCodeEleSectoriel1(String codeEleSectoriel1) {
		this.codeEleSectoriel1 = codeEleSectoriel1;
	}

	public String getCodeSegment2() {
		return codeSegment2;
	}

	public void setCodeSegment2(String codeSegment2) {
		this.codeSegment2 = codeSegment2;
	}

	public String getCodeEleSectoriel2() {
		return codeEleSectoriel2;
	}

	public void setCodeEleSectoriel2(String codeEleSectoriel2) {
		this.codeEleSectoriel2 = codeEleSectoriel2;
	}

	public String getCodeSegment3() {
		return codeSegment3;
	}

	public void setCodeSegment3(String codeSegment3) {
		this.codeSegment3 = codeSegment3;
	}

	public String getCodeEleSectoriel3() {
		return codeEleSectoriel3;
	}

	public void setCodeEleSectoriel3(String codeEleSectoriel3) {
		this.codeEleSectoriel3 = codeEleSectoriel3;
	}

	public String getCodeSegment4() {
		return codeSegment4;
	}

	public void setCodeSegment4(String codeSegment4) {
		this.codeSegment4 = codeSegment4;
	}

	public String getCodeEleSectoriel4() {
		return codeEleSectoriel4;
	}

	public void setCodeEleSectoriel4(String codeEleSectoriel4) {
		this.codeEleSectoriel4 = codeEleSectoriel4;
	}

	public String getCodeSegment5() {
		return codeSegment5;
	}

	public void setCodeSegment5(String codeSegment5) {
		this.codeSegment5 = codeSegment5;
	}

	public String getCodeEleSectoriel5() {
		return codeEleSectoriel5;
	}

	public void setCodeEleSectoriel5(String codeEleSectoriel5) {
		this.codeEleSectoriel5 = codeEleSectoriel5;
	}

	public String getCodeSegOperationnel() {
		return codeSegOperationnel;
	}

	public void setCodeSegOperationnel(String codeSegOperationnel) {
		this.codeSegOperationnel = codeSegOperationnel;
	}

	public String getCodeEleOperationnel() {
		return codeEleOperationnel;
	}

	public void setCodeEleOperationnel(String codeEleOperationnel) {
		this.codeEleOperationnel = codeEleOperationnel;
	}

	public String getCodeSegStrategique() {
		return codeSegStrategique;
	}

	public void setCodeSegStrategique(String codeSegStrategique) {
		this.codeSegStrategique = codeSegStrategique;
	}

	public String getCodeEleStrategique() {
		return codeEleStrategique;
	}

	public void setCodeEleStrategique(String codeEleStrategique) {
		this.codeEleStrategique = codeEleStrategique;
	}

	//Setting Request 
	public void setRequest(HttpServletRequest req)
	{
		
		//System.out.println("set request Imputation");	
		
		this.request = req;
		//System.out.println("choix"+request.getParameter("choix"));
		//System.out.println("libelle"+request.getParameter("libelle"));
				
		if (request.getParameter("choix") != null && ! request.getParameter("choix").equalsIgnoreCase(" ") )
		{
			choix=request.getParameter("choix");
			
			if(choix.equalsIgnoreCase("ajouter"))
			{
					this.setCreation(true);
					//System.out.println("AJOUT");
					this.anneeExercice= GestionDate.getAnneeCourante();
			}
			if(choix.equalsIgnoreCase("modifier") )
			{
					this.setCreation(false);
															
					numtypeTaxe= request.getParameter("numtypeTaxe");
					anneeExercice= request.getParameter("anneeExercice");
					RequestImputation reqImputation = new RequestImputation();
					
					Imputation imputation =reqImputation.getImputation(numtypeTaxe,anneeExercice);
				    designation=imputation.getDesignation();
				    libelle=imputation.getLibelle();
				    
				    codeBudget= imputation.getCodeBudget();
					code=imputation.getCode();
					section=imputation.getSection();
					RequestImputation req2 = new RequestImputation();
					//peutEtreSuuprimer = req2.peutEtreSuuprimer(numtypeTaxe,anneeExercice);					
					codeFonction =imputation.getCodeFonction();
				    libelleFonction=imputation.getLibelleFonction();
					codeCentreResponsable =imputation.getCodeCentreResponsable();
					libelleCentreResponsable =imputation.getLibelleCentreResponsable();
					codeCentreExecution =imputation.getCodeCentreExecution();
					libelleCentreExecution =imputation.getLibelleCentreExecution();
					necessiteControleTerrain =imputation.getNecessiteControleTerrain();
					controleInduitFacturation =imputation.getControleInduitFacturation();
					
					controleInduitFacturation =imputation.getControleInduitFacturation();
					nomElu =imputation.getNomElu();
					eluRenseignement1 =imputation.getEluRenseignement1();
					eluRenseignement2 =imputation.getEluRenseignement2();
					eluRenseignement3 =imputation.getEluRenseignement3();
					nomGestionnaire =imputation.getNomGestionnaire();
					telGestionnaire =imputation.getTelGestionnaire();
					faxGestionnaire =imputation.getFaxGestionnaire();					
					minimumPerception =imputation.getMinimumPerception();
					debug.logger.debug("=>MinimumPerception="+minimumPerception);
					minimumDeFacturation =imputation.getMinimumDeFacturation();
					if(minimumDeFacturation.length() == 0) minimumDeFacturation="0.00";
					typeFacturation =imputation.getTypeFacturation();	
					cycleFacturation = imputation.getCycleFacturation();
					renouvellementAutomatique = imputation.getRenouvellementAutomatique();
					marche = imputation.getMarche();
					//Paul evolution eCadre Budgetaire
					chapitre=imputation.getChapitre();
					nature=imputation.getNature();
					fonction=imputation.getFonction();
					codeOpeEquipement=imputation.getCodeOpeEquipement();
					typeMouvement=imputation.getTypeMouvement();
					sens=imputation.getSens();
					codeSegStructurelle=imputation.getCodeSegStructurelle();
					codeEleStructurelleGestionnaire=imputation.getCodeEleStructurelleGestionnaire();
					codeEleStructurelleDestinataire=imputation.getCodeEleStructurelleDestinataire();
					codeSegment1=imputation.getCodeSegment1();
					codeEleSectoriel1=imputation.getCodeEleSectoriel1();
					codeSegment2=imputation.getCodeSegment2();
					codeEleSectoriel2=imputation.getCodeEleSectoriel2();
					codeSegment3=imputation.getCodeSegment3();
					codeEleSectoriel3=imputation.getCodeEleSectoriel3();
					codeSegment4=imputation.getCodeSegment4();
					codeEleSectoriel4=imputation.getCodeEleSectoriel4();
					codeSegment5=imputation.getCodeSegment5();
					codeEleSectoriel5=imputation.getCodeEleSectoriel5();
					codeSegOperationnel=imputation.getCodeSegOperationnel();
					codeEleOperationnel=imputation.getCodeEleOperationnel();
					codeSegStrategique=imputation.getCodeSegStrategique();
					codeEleStrategique=imputation.getCodeEleStrategique();
					
					peutEtreSupprimer = reqImputation.peutEtreSuuprimer( numtypeTaxe, anneeExercice);
				
					if(codeFonction .equalsIgnoreCase("null") )codeFonction="";
					if(libelleFonction.equalsIgnoreCase("null"))  this.libelleFonction="";
					if(codeCentreResponsable .equalsIgnoreCase("null") )codeCentreResponsable="";
					if(libelleCentreResponsable .equalsIgnoreCase("null") )libelleCentreResponsable="";
					if(codeCentreExecution .equalsIgnoreCase("null") )codeCentreExecution="";
					if(libelleCentreExecution .equalsIgnoreCase("null") )libelleCentreExecution="";
					if(necessiteControleTerrain .equalsIgnoreCase("null") )necessiteControleTerrain="";
					if(controleInduitFacturation .equalsIgnoreCase("null") )controleInduitFacturation="";
					if(nomElu.equalsIgnoreCase("null"))  this.nomElu="";
					if(eluRenseignement1.equalsIgnoreCase("null"))  this.eluRenseignement1="";
					if(eluRenseignement2.equalsIgnoreCase("null"))  this.eluRenseignement2="";
					if(eluRenseignement3.equalsIgnoreCase("null"))  this.eluRenseignement3="";
					if(nomGestionnaire.equalsIgnoreCase("null"))  this.nomGestionnaire="";
					if(telGestionnaire.equalsIgnoreCase("null"))  this.telGestionnaire="";
					if(faxGestionnaire.equalsIgnoreCase("null"))  this.faxGestionnaire="";					
					if(minimumPerception.equalsIgnoreCase("null"))  this.minimumPerception="";
					if(minimumDeFacturation.equalsIgnoreCase("null"))  this.minimumDeFacturation="";
					
					if(anneeExercice.equalsIgnoreCase("null"))  this.anneeExercice="";
					if(typeFacturation.equalsIgnoreCase("null"))  this.typeFacturation="";
			
					//System.out.println("anneee"+anneeExercice);
					//System.out.println("anneee"+anneeExercice);
					
			}
			else if (request.getParameter("choix").equalsIgnoreCase("chercher"))
			{
				
				System.out.println("Parametre de recherche=>>>>"+req.getAttributeNames().toString());
				numtypeTaxe=request.getParameter("numtypeTaxe");
				libelle =request.getParameter("libelle");
				code =request.getParameter("code");
				section =request.getParameter("section");	
				anneeExercice=request.getParameter("anneeExercice");
			}
		}
	
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
	public final String getControleInduitFacturation() 
	{
		
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();		
		contenu.addElement("OUI");
		contenu.addElement("NON");
		res = StaticManipHtml.genererListeDeroulante("controleInduitFacturation", 1,controleInduitFacturation, contenu,false);
		contenu = null;
		System.gc();
		return res;	
		
	}

	/**
	 * @param controleInduitFacturation the controleInduitFacturation to set
	 */
	public final void setControleInduitFacturation(String controleInduitFacturation) {
		this.controleInduitFacturation = controleInduitFacturation;
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
	 * @return the necessiteControleTerrain
	 */
	public final String getNecessiteControleTerrain() 
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();		
		contenu.addElement("OUI");
		contenu.addElement("NON");
		res = StaticManipHtml.genererListeDeroulante("necessiteControleTerrain", 1,necessiteControleTerrain, contenu,false);
		contenu = null;
		System.gc();
		return res;			
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
	 * @return the request
	 */
	public final HttpServletRequest getRequest() {
		return request;
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
	 * @return the libelleFonction
	 */
	public final String getLibelleFonction() 
	{		
		return libelleFonction;
	}

	/**
	 * @param libelleFonction the libelleFonction to set
	 */
	public final void setLibelleFonction(String libelleFonction) {
		this.libelleFonction = libelleFonction;
	}

	/**
	 * @return the listeImputation
	 */
	public final String getListeImputation()
	{
		//listeImputation ="TABLEAU";
		String res="";
		StaticManipHtml mHtml = new StaticManipHtml();
		RequestImputation reqImputation= new RequestImputation();
		Vector contenu = reqImputation.getListeImputations(libelle, code, section,anneeExercice,numtypeTaxe);		
		res=mHtml.genererTableauListeImputations(contenu);
		contenu = null;
		System.gc();
		//System.out.println(
		return res;
	}

	/**
	 * @param listeImputation the listeImputation to set
	 */
	public final void setListeImputation(String listeImputation) {
		this.listeImputation = listeImputation;
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
	public final void setk(String anneeExercice) {
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
	public final String getTypeFacturation() 
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();	
		DataFromBD db = new DataFromBD();
		Vector listDesTypeFacturation= db.getTousLesTypesDeFacturation();
		for (int i = 0; i < listDesTypeFacturation.size(); i++) 
		{
			contenu.addElement(listDesTypeFacturation.elementAt(i));
		}				
		res = StaticManipHtml.genererListeDeroulante("typeFacturation", 1,typeFacturation, contenu,false);
		contenu = null;
		System.gc();
		return res;			
	}

	/**
	 * @param typeFacturation the typeFacturation to set
	 */
	public final void setTypeFacturation(String typeFacturation) {
		this.typeFacturation = typeFacturation;
	}

	/**
	 * @return the minimumDeFacturation
	 */
	public String getMinimumDeFacturation() {
		return minimumDeFacturation;
	}

	/**
	 * @param minimumDeFacturation the minimumDeFacturation to set
	 */
	public void setMinimumDeFacturation(String minimumDeFacturation) {
		this.minimumDeFacturation = minimumDeFacturation;
	}

	public void setMarche(String marche) {
		this.marche = marche;
	}
	

}