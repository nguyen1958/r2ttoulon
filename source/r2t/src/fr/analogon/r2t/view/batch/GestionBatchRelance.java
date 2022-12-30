package fr.analogon.r2t.view.batch;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.facturation.pleiade.CreerFichierExcelImpayee;
import fr.analogon.r2t.pojo.BatchRelance;
import fr.analogon.r2t.request.RequestRelance;


/**
 * Bean d'affichage du batch Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0
 */
public class GestionBatchRelance extends fr.analogon.r2t.main.RacineBean {
	
	private int numeroBatchRelance;			
	private String anExerciceRelance = "";	
	private String periodeRelance="";
	private String choixRelance="";
	private String nombreFacturesImpaye="";	
	private String dateDeLancementBatchRelance="";
	private String nomFichierToRegie="";
	private String nomFichierExcel="";
	private String nomFichierPdf="";	
	private HttpServletRequest request;
	
	
		
	
	public GestionBatchRelance() 
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
		String anneeCourante = GestionDate.getAnneeCourante();
		this.dateDeLancementBatchRelance = GestionDate.getDateAujourdhuiString();
		String periodeRelanceMois="";
		String periodeRelanceTrimestre="";
		if (request.getParameter("periodeRelanceTrimestre") != null) 
			{ periodeRelanceTrimestre = request.getParameter("periodeRelanceTrimestre");	}
		
		if (request.getParameter("periodeRelanceMois") != null) 
		{ periodeRelanceMois = request.getParameter("periodeRelanceMois");	}
		
		if (request.getParameter("choixRelance") != null) 
		{ this.choixRelance = request.getParameter("choixRelance");	}
		
				
		if(choixRelance != null & choixRelance.equalsIgnoreCase("a")) 
			periodeRelance = anneeCourante;
		else if(choixRelance != null & choixRelance.equalsIgnoreCase("t"))
			periodeRelance = periodeRelanceTrimestre ;
		else if(choixRelance != null & choixRelance.equalsIgnoreCase("m"))
			periodeRelance = periodeRelanceMois ;						
				
		
		RequestRelance requRelance  = new RequestRelance();		
		
		this.numeroBatchRelance= requRelance.ajouterLigneBatchRelance
			(dateDeLancementBatchRelance, periodeRelance, choixRelance, anneeCourante); 
		  
		
		
		if(choixRelance != null & choixRelance.equalsIgnoreCase("a")) 
			periodeRelance = "Annee " +anneeCourante;
		else if(choixRelance != null & choixRelance.equalsIgnoreCase("t"))
		{			
			periodeRelance = "Trimestre " +  periodeRelance;
		}
		else if(choixRelance != null & choixRelance.equalsIgnoreCase("m"))
		{		
			periodeRelance = "Mois " + periodeRelance;			
		}
			
		
		
		//creation du fichier imapye :
		CreerFichierExcelImpayee cfim = new CreerFichierExcelImpayee(numeroBatchRelance);
		
		RequestRelance requestRelance = new RequestRelance();
		BatchRelance batchRelance = requestRelance.getBatchRealRelance(numeroBatchRelance);
		this.nombreFacturesImpaye = ""+ batchRelance.getNombreFactureImpaye();
		
		if (choixRelance.equalsIgnoreCase("a"))  choixRelance ="annuelle";
		else if (choixRelance.equalsIgnoreCase("t")) 	choixRelance ="trimestrielle";		
		else 	if (choixRelance.equalsIgnoreCase("m"))  choixRelance ="mensuelle";		
		String dateDeLancementBatchRelanceTmp = dateDeLancementBatchRelance.replaceAll("/", "");
		this.nomFichierExcel = fichierDeConfiguration.getLienFichierExcelImpaye()+"/"+anneeCourante+"/"+choixRelance+"/"+numeroBatchRelance+"/impayee_"+numeroBatchRelance+"_"+dateDeLancementBatchRelanceTmp+".xls";   
		this.nomFichierPdf = fichierDeConfiguration.getLienFichierExcelImpaye()+"/"+anneeCourante+"/"+choixRelance+"/"+numeroBatchRelance+"/factureImpayee_"+numeroBatchRelance+"_"+dateDeLancementBatchRelanceTmp+".pdf";
			
	}


	public int getNumeroBatchRelance() {
		return numeroBatchRelance;
	}


	public void setNumeroBatchRelance(int numeroBatchRelance) {
		this.numeroBatchRelance = numeroBatchRelance;
	}


	public String getAnExerciceRelance() {
		return anExerciceRelance;
	}


	public void setAnExerciceRelance(String anExerciceRelance) {
		this.anExerciceRelance = anExerciceRelance;
	}


	public String getPeriodeRelance() {
		return periodeRelance;
	}


	public void setPeriodeRelance(String periodeRelance) {
		this.periodeRelance = periodeRelance;
	}


	public String getChoixRelance() {
		return choixRelance;
	}


	public void setChoixRelance(String choixRelance) {
		this.choixRelance = choixRelance;
	}


	public String getNombreFacturesImpaye() {
		return nombreFacturesImpaye;
	}


	public void setNombreFacturesImpaye(String nombreFacturesImpaye) {
		this.nombreFacturesImpaye = nombreFacturesImpaye;
	}


	public String getDateDeLancementBatchRelance() {
		return dateDeLancementBatchRelance;
	}


	public void setDateDeLancementBatchRelance(String dateDeLancementBatchRelance) {
		this.dateDeLancementBatchRelance = dateDeLancementBatchRelance;
	}


	public String getNomFichierToRegie() {
		return nomFichierToRegie;
	}


	public void setNomFichierToRegie(String nomFichierToRegie) {
		this.nomFichierToRegie = nomFichierToRegie;
	}


	public String getNomFichierExcel() {
		return nomFichierExcel;
	}


	public void setNomFichierExcel(String nomFichierExcel) {
		this.nomFichierExcel = nomFichierExcel;
	}


	public String getNomFichierPdf() {
		return nomFichierPdf;
	}


	public void setNomFichierPdf(String nomFichierPdf) {
		this.nomFichierPdf = nomFichierPdf;
	}
	
	
}


