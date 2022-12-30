package fr.analogon.r2t.view.batch;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.Batch;
import fr.analogon.r2t.pojo.Remboursement;
import fr.analogon.r2t.request.RequestBatch;
import fr.analogon.r2t.request.RequestRemboursement;


/**
 * Bean d'affichage du batch Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0
 */
public class BaffBatch extends fr.analogon.r2t.main.RacineBean {
	
	private int numeroBatch;	
	private String nombreFactures;
	private String nombreRemboursements;
	private String anExercice = "";	
	private String typeDeTaxe="";
	private String dateDeCreation="";
	private String nomFichierToPleaide="";
	private String nomFichierFromPleaide="";	
		
	
	
	private String editerDesFactureAnnulee="";		
	private String aEditerRoleChangementAdresseRedevable="";
	private String lienRoleAnnulation="";
	private String lienRoleJustificatif="";
	private String lienRoleFacturation="./";
	private String lienCertificatAnnulationRecette="";
	private String listeFactures="";
	private String lienFichierDesFactures="";	
	private String lienaRoleChangementAdresseRedevable ="";
	
	//Flux Pleaide
	private String lienFichierToPleaide="";
	private String lienFichierFromPleaide="";
	
	//Remboursement 
	private String lienRemboursement="";
	private String lienRoleRemboursement="";
	private String editerRemboursement="";
	
	
	
	
	private HttpServletRequest request;	
	
	
	//POUR TOULON
	private String lienFichierFilien="";
	private String nomFichierFilien="";
	
	
	
	public BaffBatch() 
	{
		try {
			//jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	

	public void setRequest(HttpServletRequest req)
	{	
		
		try
		{	
		
		this.request = req;
		if (request.getParameter("numeroBatch") != null) 
		{ this.numeroBatch = Integer.parseInt(request.getParameter("numeroBatch"));	}
		
			RequestBatch reqBatch = new RequestBatch();
			Batch batch= reqBatch.getBatch(numeroBatch);
			this.anExercice = batch.getDateExecution();
			this.typeDeTaxe = batch.getTypeEmplacement();
			this.numeroBatch = Integer.parseInt(batch.getNumeroBatchTraitement());
			this.dateDeCreation = batch.getDateExecution();
			this.nombreFactures = batch.getNombreFactures();
			this.nombreRemboursements = batch.getNombreRemboursements();
			this.nomFichierToPleaide = batch.getNomFichierEnvoyeAPleiade();
			
			//TOULON
			nomFichierFilien ="filien_"+typeDeTaxe+"_"+batch.getAnneeExercice()+"_"+ numeroBatch+".txt";			
			lienFichierFilien= lienFichierFilien+fichierDeConfiguration.getLienFichierFilien()+"/"+batch.getAnneeExercice()+"-"+batch.getNumeroBatchTraitement() +"/"+ nomFichierFilien;
			
			this.nomFichierToPleaide = "toPleiade_"+typeDeTaxe+"_"+batch.getAnneeExercice()+"_"+ numeroBatch+".txt";
			this.nomFichierFromPleaide = "fromPleiade_"+typeDeTaxe+"_"+batch.getAnneeExercice()+"_"+ numeroBatch+".txt";
			this.listeFactures= batch.getListeFactures();
			this.editerDesFactureAnnulee = batch.getAEditerDesFactureAnnulee();
			this.editerRemboursement = batch.getaEditerDesRemboursement();
			this.aEditerRoleChangementAdresseRedevable = batch.getAEditerRoleChangementAdresseRedevable(); 
			RequestBatch reqBatch1 = new RequestBatch();
			
			Batch batch1 = reqBatch1.getBatch(numeroBatch);
			
			
			
			String s= batch.getAnneeExercice()+"-"+batch.getNumeroBatchTraitement();			
			lienFichierToPleaide=lienFichierToPleaide+fichierDeConfiguration.getLienFichierToPleaide()+s+"/"+ nomFichierToPleaide;		
			
			lienFichierFromPleaide=lienFichierFromPleaide+fichierDeConfiguration.getLienFichierFromPleaide()+s+"/"+ nomFichierFromPleaide;		
			
			lienFichierDesFactures = lienFichierDesFactures +fichierDeConfiguration.getLienFichierDesFactures()  +batch.getNomDossier()+"/"+"BatchNumero"+numeroBatch+".pdf";
								
			lienCertificatAnnulationRecette=lienCertificatAnnulationRecette+fichierDeConfiguration.getLienCertificatAnnulationRecette() +"RAR_"+numeroBatch+".pdf";		
								
			lienRoleFacturation=lienRoleFacturation+fichierDeConfiguration.getLienRoleFacturation()+"RF_"+numeroBatch+".pdf";		
						
			lienRoleJustificatif=lienRoleJustificatif+fichierDeConfiguration.getLienRoleJustificatif()+"REJR_"+numeroBatch+".pdf";		
									
			lienRoleAnnulation=lienRoleAnnulation+fichierDeConfiguration.getLienRoleAnnulation()+"RA_"+numeroBatch+".pdf";
			
			
			if(typeDeTaxe.equalsIgnoreCase("TLPE"))
			{
				RequestRemboursement requestRemboursement = new RequestRemboursement();
				Vector listeRemboursement = requestRemboursement.getListeDesRemboursement(numeroBatch);
				Remboursement remboursement= (Remboursement)listeRemboursement.elementAt(0);
				String annee = remboursement.getAnneeEx();
				lienRemboursement=lienRemboursement+fichierDeConfiguration.getLienRemboursement()+"/"+annee+"-"+numeroBatch+"/BatchNumero"+numeroBatch+".pdf";
				lienRoleRemboursement=lienRoleRemboursement+fichierDeConfiguration.getLienRoleRemboursement()+"RRem_"+numeroBatch+".pdf";
			}
			
			
	
			lienaRoleChangementAdresseRedevable= lienaRoleChangementAdresseRedevable +
				fichierDeConfiguration.getLienRoleChgtAdresseRedevable() +"RCAR_"+batch.getAEditerRoleChangementAdresseRedevable()+".pdf";
				
			
		}
		catch (Exception e) 
		{
			DebuggerLog4J.logger.fatal(e.getMessage());
		}
			
				
	}


	/**
	 * @return the anExercice
	 */
	public String getAnExercice() {
		return anExercice;
	}


	/**
	 * @param anExercice the anExercice to set
	 */
	public void setAnExercice(String anExercice) {
		this.anExercice = anExercice;
	}


	/**
	 * @return the nombreFactures
	 */
	public String getNombreFactures() {
		return nombreFactures;
	}


	/**
	 * @param nombreFactures the nombreFactures to set
	 */
	public void setNombreFactures(String nombreFactures) {
		this.nombreFactures = nombreFactures;
	}


	/**
	 * @return the numeroBatch
	 */
	public int getNumeroBatch() {
		return numeroBatch;
	}


	/**
	 * @param numeroBatch the numeroBatch to set
	 */
	public void setNumeroBatch(int numeroBatch) {
		numeroBatch = numeroBatch;
	}


	/**
	 * @return the typeDeTaxe
	 */
	public String getTypeDeTaxe() {
		return typeDeTaxe;
	}


	/**
	 * @param typeDeTaxe the typeDeTaxe to set
	 */
	public void setTypeDeTaxe(String typeDeTaxe) {
		this.typeDeTaxe = typeDeTaxe;
	}


	/**
	 * @return the dateDeCreation
	 */
	public String getDateDeCreation() {
		return dateDeCreation;
	}


	/**
	 * @param dateDeCreation the dateDeCreation to set
	 */
	public void setDateDeCreation(String dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}


	/**
	 * @return the nomFichierFromPleaide
	 */
	public String getNomFichierFromPleaide() {
		return nomFichierFromPleaide;
	}


	/**
	 * @param nomFichierFromPleaide the nomFichierFromPleaide to set
	 */
	public void setNomFichierFromPleaide(String nomFichierFromPleaide) {
		this.nomFichierFromPleaide = nomFichierFromPleaide;
	}


	/**
	 * @return the nomFichierToPleaide
	 */
	public String getNomFichierToPleaide() {
		return nomFichierToPleaide;
	}


	/**
	 * @param nomFichierToPleaide the nomFichierToPleaide to set
	 */
	public void setNomFichierToPleaide(String nomFichierToPleaide) {
		this.nomFichierToPleaide = nomFichierToPleaide;
	}


	/**
	 * @return the listeFactures
	 */
	public final String getListeFactures() {
		return listeFactures;
	}


	/**
	 * @param listeFactures the listeFactures to set
	 */
	public final void setListeFactures(String listeFactures) {
		this.listeFactures = listeFactures;
	}


	/**
	 * @return the lienFichierDesFactures
	 */
	public final String getLienFichierDesFactures() 
	{				
		return lienFichierDesFactures;
	}


	/**
	 * @param lienFichierDesFactures the lienFichierDesFactures to set
	 */
	public final void setLienFichierDesFactures(String lienFichierDesFactures) {
		this.lienFichierDesFactures = lienFichierDesFactures;
	}


	/**
	 * @return the lienRoleAnnulation
	 */
	public final String getLienRoleAnnulation()
	{	
		return lienRoleAnnulation;
	}


	/**
	 * @param lienRoleAnnulation the lienRoleAnnulation to set
	 */
	public final void setLienRoleAnnulation(String lienRoleAnnulation) {
		this.lienRoleAnnulation = lienRoleAnnulation;
	}


	/**
	 * @return the lienRoleJustificatif
	 */
	public final String getLienRoleJustificatif()	
	{		
		return lienRoleJustificatif;
	}


	/**
	 * @param lienRoleJustificatif the lienRoleJustificatif to set
	 */
	public final void setLienRoleJustificatif(String lienRoleJustificatif) {
		this.lienRoleJustificatif = lienRoleJustificatif;
	}


	/**
	 * @return the lienRoleFacturation
	 */
	public final String getLienRoleFacturation()
	{		
		return lienRoleFacturation;
	}


	/**
	 * @param lienRoleFacturation the lienRoleFacturation to set
	 */
	public final void setLienRoleFacturation(String lienRoleFacturation) {
		this.lienRoleFacturation = lienRoleFacturation;
	}


	/**
	 * @return the lienCertificatAnnulationRecette
	 */
	public final String getLienCertificatAnnulationRecette()
	{		
		return lienCertificatAnnulationRecette;
	}


	/**
	 * @param lienCertificatAnnulationRecette the lienCertificatAnnulationRecette to set
	 */
	public final void setLienCertificatAnnulationRecette(
			String lienCertificatAnnulationRecette) {
		this.lienCertificatAnnulationRecette = lienCertificatAnnulationRecette;
	}


	public String getEditerDesFactureAnnulee() {
		return editerDesFactureAnnulee;
	}


	public void setEditerDesFactureAnnulee(String editerDesFactureAnnulee) {
		this.editerDesFactureAnnulee = editerDesFactureAnnulee;
	}


	/**
	 * @return the lienFichierFromPleaide
	 */
	public final String getLienFichierFromPleaide() {
		return lienFichierFromPleaide;
	}


	/**
	 * @param lienFichierFromPleaide the lienFichierFromPleaide to set
	 */
	public final void setLienFichierFromPleaide(String lienFichierFromPleaide) {
		this.lienFichierFromPleaide = lienFichierFromPleaide;
	}


	/**
	 * @return the lienFichierToPleaide
	 */
	public final String getLienFichierToPleaide() {
		return lienFichierToPleaide;
	}


	/**
	 * @param lienFichierToPleaide the lienFichierToPleaide to set
	 */
	public final void setLienFichierToPleaide(String lienFichierToPleaide) {
		this.lienFichierToPleaide = lienFichierToPleaide;
	}


	/**
	 * @return the lienaRoleChangementAdresseRedevable
	 */
	public final String getLienaRoleChangementAdresseRedevable() {
		return lienaRoleChangementAdresseRedevable;
	}


	/**
	 * @param lienaRoleChangementAdresseRedevable the lienaRoleChangementAdresseRedevable to set
	 */
	public final void setLienaRoleChangementAdresseRedevable(
			String lienaRoleChangementAdresseRedevable) {
		this.lienaRoleChangementAdresseRedevable = lienaRoleChangementAdresseRedevable;
	}


	/**
	 * @return the aEditerRoleChangementAdresseRedevable
	 */
	public final String getAEditerRoleChangementAdresseRedevable() {
		return aEditerRoleChangementAdresseRedevable;
	}


	/**
	 * @param editerRoleChangementAdresseRedevable the aEditerRoleChangementAdresseRedevable to set
	 */
	public final void setAEditerRoleChangementAdresseRedevable(
			String editerRoleChangementAdresseRedevable) {
		aEditerRoleChangementAdresseRedevable = editerRoleChangementAdresseRedevable;
	}


	public String getNomFichierFilien() {
		return nomFichierFilien;
	}


	public void setNomFichierFilien(String nomFichierFilien) {
		this.nomFichierFilien = nomFichierFilien;
	}


	public String getLienFichierFilien() {
		return lienFichierFilien;
	}


	public void setLienFichierFilien(String lienFichierFilien) {
		this.lienFichierFilien = lienFichierFilien;
	}


	/**
	 * @return the lienRemboursement
	 */
	public String getLienRemboursement() {
		return lienRemboursement;
	}


	/**
	 * @param lienRemboursement the lienRemboursement to set
	 */
	public void setLienRemboursement(String lienRemboursement) {
		this.lienRemboursement = lienRemboursement;
	}


	/**
	 * @return the lienRoleRemboursement
	 */
	public String getLienRoleRemboursement() {
		return lienRoleRemboursement;
	}


	/**
	 * @param lienRoleRemboursement the lienRoleRemboursement to set
	 */
	public void setLienRoleRemboursement(String lienRoleRemboursement) {
		this.lienRoleRemboursement = lienRoleRemboursement;
	}


	/**
	 * @return the editerRemboursement
	 */
	public String getEditerRemboursement() {
		return editerRemboursement;
	}


	/**
	 * @param editerRemboursement the editerRemboursement to set
	 */
	public void setEditerRemboursement(String editerRemboursement) {
		this.editerRemboursement = editerRemboursement;
	}


	/**
	 * @return the nombreRemboursements
	 */
	public String getNombreRemboursements() {
		return nombreRemboursements;
	}


	/**
	 * @param nombreRemboursements the nombreRemboursements to set
	 */
	public void setNombreRemboursements(String nombreRemboursements) {
		this.nombreRemboursements = nombreRemboursements;
	}
	
	

}


