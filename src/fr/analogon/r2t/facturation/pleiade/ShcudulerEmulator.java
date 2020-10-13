package fr.analogon.r2t.facturation.pleiade;

import fr.analogon.r2t.facturation.scriptBatch.CreerDonneeFacture;
import fr.analogon.r2t.facturation.scriptBatch.ImprimerDonneeFacturePDF;


public class ShcudulerEmulator
{
	String dateExecution="";
	String typeTaxe="";	
	int idBatchTraitement =0;
	int nombreDeFactureCree;
	int nombreRemboursementsCree;
	String anneeExercice="";
	String etapeFacturation="";
	String  shcudulerEmulator;
	String anneeFacturationTLPE;
	
	/**
	 * @return the dateExecution
	 */
	public String getDateExecution() {
		return dateExecution;
	}

	/**
	 * @param dateExecution the dateExecution to set
	 */
	public void setDateExecution(String dateExecution) {
		this.dateExecution = dateExecution;
	}

	/**
	 * @return the idBatchTraitement
	 */
	public int getIdBatchTraitement() {
		return idBatchTraitement;
	}

	/**
	 * @param idBatchTraitement the idBatchTraitement to set
	 */
	public void setIdBatchTraitement(int idBatchTraitement) {
		this.idBatchTraitement = idBatchTraitement;
	}

	/**
	 * @return the nombreDeFactureCree
	 */
	public int getNombreDeFactureCree() {
		return nombreDeFactureCree;
	}

	/**
	 * @param nombreDeFactureCree the nombreDeFactureCree to set
	 */
	public void setNombreDeFactureCree(int nombreDeFactureCree) {
		this.nombreDeFactureCree = nombreDeFactureCree;
	}

	/**
	 * @return the shcudulerEmulator
	 */
	public String getShcudulerEmulator() 
	{
		this.startOrdononceur();
		return shcudulerEmulator;
	}

	/**
	 * @param shcudulerEmulator the shcudulerEmulator to set
	 */
	public void setShcudulerEmulator(String shcudulerEmulator) {
		this.shcudulerEmulator = shcudulerEmulator;
	}

	/**
	 * @return the typeTaxe
	 */
	public String getTypeTaxe() {
		return typeTaxe;
	}

	/**
	 * @param typeTaxe the typeTaxe to set
	 */
	public void setTypeTaxe(String typeTaxe) {
		this.typeTaxe = typeTaxe;
	}

	public ShcudulerEmulator( String dateExecution, String typeTaxe,String etapeFacturation,
			String anneeFacturationTLPE)
	{		
		this.dateExecution = dateExecution;
		this.typeTaxe = typeTaxe;
		this.anneeFacturationTLPE = anneeFacturationTLPE;
		this.etapeFacturation = etapeFacturation;
	}
	
	public void startOrdononceur()
	{	
		//////////////////////////////////////////////////////////////////////////////////////
		//Creation des données des factures et crerion du fichier a ecvnoyer vers Pleaide
		//////////////////////////////////////////////////////////////////////////////////////
		CreerDonneeFacture  cdf = new CreerDonneeFacture(dateExecution,typeTaxe,etapeFacturation,
				anneeFacturationTLPE);
		this.dateExecution = dateExecution;
		this.typeTaxe = typeTaxe;
		this.idBatchTraitement = cdf.getIdBatchTraitement();
		this.nombreDeFactureCree = cdf.getNombreDeFactureCree();
		this.nombreRemboursementsCree = cdf.getNombreRemboursementsCree();
		this.anneeExercice = cdf.getAnneeExercice();		
	    
		
		//////////////////////////////////////////////////////////////////////////////////////
		//Lancer pleaide et recuperation du nuepr de titre et du solde de la facture
		//////////////////////////////////////////////////////////////////////////////////////
		LancerPleiade lp = new LancerPleiade(cdf);
		
		
		
		//////////////////////////////////////////////////////////////////////////////////////
		//Imrpimer les factures et les rapports en PDF
		//////////////////////////////////////////////////////////////////////////////////////
		ImprimerDonneeFacturePDF im = new ImprimerDonneeFacturePDF(cdf.getIdBatchTraitement());
		
		
	}
	
	
	
	//////////////////////////
	//TEST DE L'oronnonceur
	////////////////////////
	public static void main(String[] args)
	{
		String dateExecutionTest="28/09/2008";
		String typeTaxeTest="DDV";
		//ShcudulerEmulator s = new ShcudulerEmulator(dateExecutionTest, typeTaxeTest);
		//s.startOrdononceur();
	}

	/**
	 * @return the nombreRemboursementsCree
	 */
	public int getNombreRemboursementsCree() {
		return nombreRemboursementsCree;
	}

	/**
	 * @param nombreRemboursementsCree the nombreRemboursementsCree to set
	 */
	public void setNombreRemboursementsCree(int nombreRemboursementsCree) {
		this.nombreRemboursementsCree = nombreRemboursementsCree;
	}


	
	
	
	
	
	

}
