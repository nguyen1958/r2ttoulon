package fr.analogon.r2t.view.batch;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.facturation.pleiade.ShcudulerEmulator;
import fr.analogon.r2t.pojo.Batch;
import fr.analogon.r2t.request.RequestAdmin;
import fr.analogon.r2t.request.RequestBatch;


/**
 * Bean d'affichage du batch Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0
 */
public class GestionBatch extends fr.analogon.r2t.main.RacineBean {
	
	private int numeroBatch;	
	private String nombreFactures;
	private String nombreRemboursements;
	
	private String anExercice = "";
	private String anneeFacturationTLPE = "";	
	private String typeDeTaxe="";
	private String dateDeCreation="";
	private String nomFichierToPleaide="";
	private String nomFichierFromPleaide="";
	private String editerDesFactureAnnulee="";
	private String editerDesRemboursement="";

	private String etapeFacturation="";
	private HttpServletRequest request;
	
	
	public GestionBatch() 
	{
		try {
			//jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	

	public void setRequest(HttpServletRequest req)
	{				
		this.request = req;

		
		
		if (request.getParameter("anExercice") != null) 
			{ this.anExercice = request.getParameter("anExercice");	}
		
		if (request.getParameter("etapeFacturation") != null) 
		{ this.etapeFacturation = request.getParameter("etapeFacturation");	}
		
		if (request.getParameter("typeDeTaxe") != null) 
		{ this.typeDeTaxe = request.getParameter("typeDeTaxe");	}
		
		
		if (typeDeTaxe.equalsIgnoreCase("TLPE"))
		{
			if (request.getParameter("anneeFacturationTLPE") != null) 
				 this.anneeFacturationTLPE = request.getParameter("anneeFacturationTLPE");
		}
		
		//Gestion action pour bloquer/débloquer la procédure en cours d'exécution
		if(anExercice!=null && typeDeTaxe !=null )
		{
			RequestAdmin request= new RequestAdmin();
			//Bloquer l'action
			request.InsertAction("batchTraitement "+typeDeTaxe,GestionDate.getDateTime() );
			ShcudulerEmulator s = new ShcudulerEmulator(anExercice, 
					typeDeTaxe,etapeFacturation,anneeFacturationTLPE);
			s.startOrdononceur();
			this.nombreFactures = String.valueOf(s.getNombreDeFactureCree());
			this.nombreRemboursements = String.valueOf( s.getNombreRemboursementsCree());
			this.numeroBatch = s.getIdBatchTraitement();
			this.typeDeTaxe = s.getTypeTaxe();
			
			this.dateDeCreation = s.getDateExecution();
			Batch batch = new RequestBatch().getBatch(numeroBatch);
			this.editerDesFactureAnnulee = batch.getAEditerDesFactureAnnulee();
			this.editerDesRemboursement = batch.getaEditerDesRemboursement();
			//Débloquer l'action
			request.removeAction("batchTraitement", typeDeTaxe);
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


	public String getEditerDesFactureAnnulee() {
		return editerDesFactureAnnulee;
	}


	public void setEditerDesFactureAnnulee(String editerDesFactureAnnulee) {
		this.editerDesFactureAnnulee = editerDesFactureAnnulee;
	}


	public String getEtapeFacturation() {
		return etapeFacturation;
	}


	public void setEtapeFacturation(String etapeFacturation) {
		this.etapeFacturation = etapeFacturation;
	}
	
	/**
	 * @return the editerDesRemboursement
	 */
	public String getEditerDesRemboursement() {
		return editerDesRemboursement;
	}


	/**
	 * @param editerDesRemboursement the editerDesRemboursement to set
	 */
	public void setEditerDesRemboursement(String editerDesRemboursement) {
		this.editerDesRemboursement = editerDesRemboursement;
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


