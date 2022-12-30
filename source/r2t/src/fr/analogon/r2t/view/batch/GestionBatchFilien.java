package fr.analogon.r2t.view.batch;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.facturation.pleiade.CreerFichierFilien;
import fr.analogon.r2t.pojo.BatchFilien;
import fr.analogon.r2t.request.RequestFilien;


/**
 * Bean d'affichage du batch Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0
 */
public class GestionBatchFilien extends fr.analogon.r2t.main.RacineBean {
	
	private int numeroBatchFilien;	
	private String nombreFacturesImpayeFilien="";	
	private String anExerciceFilien = "";	
	private String numeroTrimestreFilien="";
	private String dateDeLancementBatcheFilien="";
	private int nbreFacture11;
	private int nbreFacture12;
	private int nbreFacture21;
	private int nbreFacture22;
	private int nbreFacture23;
	
	private String nomFichierToRegie11="";
	private String nomFichierToRegie12="";
	private String nomFichierToRegie21="";
	private String nomFichierToRegie22="";
	private String nomFichierToRegie23="";
	private String choixFilien="";
	private String etatBatchFilien="";
	private String actionBatch="";	
	public String getActionBatch() {
		return actionBatch;
	}


	public void setActionBatch(String actionBatch) {
		this.actionBatch = actionBatch;
	}


	private HttpServletRequest request;
	
	
	public GestionBatchFilien() 
	{
		try {
			//jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	

	public String getEtatBatchFilien() {
		return etatBatchFilien;
	}


	public void setEtatBatchFilien(String etatBatchFilien) {
		this.etatBatchFilien = etatBatchFilien;
	}


	public void setRequest(HttpServletRequest req)
	{
		System.out.println(">>> GestionBatchFilien ");
		String t11= "11pprivee";
		String t12= "12pmorale";
		String t21= "21etat";
		String t22= "22region";
		String t23= "23departement";
		
		this.request = req;
		System.out.println(">>> GestionBatchFilien request= "+req);
		
		if (request.getParameter("actionBatch") != null) 
		{ 
			this.actionBatch = request.getParameter("actionBatch");
			System.out.println(">>> GestionBatchFilien actionBatch= "+actionBatch);
		}
		
		if (request.getParameter("numeroBatchFilien") != null   ) 
		{
			//consultation 
			this.numeroBatchFilien = Integer.parseInt(request.getParameter("numeroBatchFilien"));	
			System.out.println(">>> GestionBatchFilien numeroBatchFilien= "+numeroBatchFilien);
			RequestFilien requestFilien = new RequestFilien();	
			
			if (actionBatch.equalsIgnoreCase("validerBatch"))
			{
				//validation du batch Filien 
				System.out.println(">>> GestionBatchFilien validerBatchFilien= "+numeroBatchFilien);
				requestFilien.validerBatchFilien(numeroBatchFilien);
				//mise a jour du flag des factures non paye 
			}
			
			System.out.println(">>> GestionBatchFilien ici ");
			
			BatchFilien batchFilien =  requestFilien.getBatchFilien(numeroBatchFilien);
			
			if (request.getParameter("choixFilien") != null)  { this.choixFilien = request.getParameter("choixFilien");	}
			System.out.println(">>> GestionBatchFilien choixFilien="+choixFilien);
			this.nombreFacturesImpayeFilien = ""+batchFilien.getNombreFactureImpaye();
			this.dateDeLancementBatcheFilien = batchFilien.getDateLancement();
			int periode = Integer.parseInt(batchFilien.getPeriode());
			if (periode <= 4) 
				this.numeroTrimestreFilien = "Trimestre " + periode ;
			else
				this.numeroTrimestreFilien = "Année " + periode ;
			this.numeroBatchFilien = Integer.parseInt(batchFilien.getIdBatchFilien());
			String annee = dateDeLancementBatcheFilien.substring(5, dateDeLancementBatcheFilien.length());
			String dateDeLancementBatcheFilienTmp = dateDeLancementBatcheFilien.replaceAll("/", "");
			String choixFilienTmp ="";
			if (batchFilien.getType().equalsIgnoreCase("t")) 	choixFilienTmp ="trimestrielle";
			else 	if ( batchFilien.getType().equalsIgnoreCase("a"))  choixFilienTmp ="annuelle";			
			this.nomFichierToRegie11 = fichierDeConfiguration.getLienFichierFilien()+"/"+annee+"/"+choixFilien+"/"+numeroBatchFilien+"/filien_"+t11+"_"+numeroBatchFilien+"_"+dateDeLancementBatcheFilienTmp+".txt";
			this.nomFichierToRegie12 = fichierDeConfiguration.getLienFichierFilien()+"/"+annee+"/"+choixFilien+"/"+numeroBatchFilien+"/filien_"+t12+"_"+numeroBatchFilien+"_"+dateDeLancementBatcheFilienTmp+".txt";
			this.nomFichierToRegie21 = fichierDeConfiguration.getLienFichierFilien()+"/"+annee+"/"+choixFilien+"/"+numeroBatchFilien+"/filien_"+t21+"_"+numeroBatchFilien+"_"+dateDeLancementBatcheFilienTmp+".txt";
			this.nomFichierToRegie22 = fichierDeConfiguration.getLienFichierFilien()+"/"+annee+"/"+choixFilien+"/"+numeroBatchFilien+"/filien_"+t22+"_"+numeroBatchFilien+"_"+dateDeLancementBatcheFilienTmp+".txt";
			this.nomFichierToRegie23 = fichierDeConfiguration.getLienFichierFilien()+"/"+annee+"/"+choixFilien+"/"+numeroBatchFilien+"/filien_"+t23+"_"+numeroBatchFilien+"_"+dateDeLancementBatcheFilienTmp+".txt";
			if (request.getParameter("nbreFacture11") != null)  { this.nbreFacture11 = Integer.valueOf(request.getParameter("nbreFacture11"));	}
			if (request.getParameter("nbreFacture12") != null)  { this.nbreFacture12 = Integer.valueOf(request.getParameter("nbreFacture12"));	}
			if (request.getParameter("nbreFacture21") != null)  { this.nbreFacture21 = Integer.valueOf(request.getParameter("nbreFacture21"));	}
			if (request.getParameter("nbreFacture22") != null)  { this.nbreFacture22 = Integer.valueOf(request.getParameter("nbreFacture22"));	}
			if (request.getParameter("nbreFacture23") != null)  { this.nbreFacture23 = Integer.valueOf(request.getParameter("nbreFacture23"));	}
			
			
			this.etatBatchFilien = batchFilien.getEtatBatchFilien();		
			
		}
		else
		{
			//Creation 			
			String anneeCourante = GestionDate.getAnneeCourante();			
			
			System.out.println(">>> GestionBatchFilien creation filien");
			
			this.dateDeLancementBatcheFilien = GestionDate.getDateAujourdhuiString();
			if (request.getParameter("numeroTrimestreFilien") != null) 
				{ this.numeroTrimestreFilien = request.getParameter("numeroTrimestreFilien");	}
			
			
			if (request.getParameter("choixFilien") != null) 
			{ this.choixFilien = request.getParameter("choixFilien");	}
			
					
			if(choixFilien != null & choixFilien.equalsIgnoreCase("a")) 
				numeroTrimestreFilien =anneeCourante;
			
			RequestFilien requestBatch = new RequestFilien();
				
			
			this.numeroBatchFilien= requestBatch.ajouterLigneBatchFilien
				(dateDeLancementBatcheFilien,""+numeroTrimestreFilien	, choixFilien , anneeCourante)	;   
			
			if(choixFilien.equalsIgnoreCase("a")) 
				numeroTrimestreFilien = "Annee " +anneeCourante;
			else if	(choixFilien.equalsIgnoreCase("t"))
				numeroTrimestreFilien = "Trimestre " + numeroTrimestreFilien;
			
			//creation de 5 fichiers filien: 
			//11 – Débiteur de droit privé -> Personne privée » / (02,01) / 063103 
			//12 – Débiteur de droit privé -> Personne morale » / (03,50) / 063106 
			//21 – Débiteur de droit public -> Etat » / (07,20) / 063107 
			//22 – Débiteur de droit public -> Région » (09,21) / 063108 
			//23 – Débiteur de droit public -> Département » / (09,22) /063109
			
			//11-pprivee
			//12–pmorale  
			//21–etat
			//22–region
			//23–departement
						
			CreerFichierFilien cf1 = new CreerFichierFilien(numeroBatchFilien,"063103","11",t11,false);
			nbreFacture11=cf1.getNbreFacture();
			
			CreerFichierFilien cf2 = new CreerFichierFilien(numeroBatchFilien,"063106","12",t12,false);
			nbreFacture12=cf2.getNbreFacture();
			
			CreerFichierFilien cf3 = new CreerFichierFilien(numeroBatchFilien,"063107","21",t21,false);
			nbreFacture21=cf3.getNbreFacture();
			
			CreerFichierFilien cf4 = new CreerFichierFilien(numeroBatchFilien,"063108","22",t22,false);
			nbreFacture22=cf4.getNbreFacture();
			
			CreerFichierFilien cf5 = new CreerFichierFilien(numeroBatchFilien,"063109","23",t23,false);
			nbreFacture23=cf5.getNbreFacture();
			
			CreerFichierFilien cftous = new CreerFichierFilien(numeroBatchFilien,"","","",true);	
					
			if (choixFilien.equalsIgnoreCase("t")) 	choixFilien ="trimestrielle";
			else 	if (choixFilien.equalsIgnoreCase("a"))  choixFilien ="annuelle";
			
			String dateDeLancementBatcheFilienTmp = dateDeLancementBatcheFilien.replaceAll("/", "");
			
			
			
		    BatchFilien batchFilien = requestBatch.getBatchFilien(numeroBatchFilien);
			this.nombreFacturesImpayeFilien = ""+batchFilien.getNombreFactureImpaye();
			
			this.nomFichierToRegie11 = fichierDeConfiguration.getLienFichierFilien()+"/"+anneeCourante+"/"+choixFilien+"/"+numeroBatchFilien+"/filien_"+t11+"_"+numeroBatchFilien+"_"+dateDeLancementBatcheFilienTmp+".txt";
			this.nomFichierToRegie12 = fichierDeConfiguration.getLienFichierFilien()+"/"+anneeCourante+"/"+choixFilien+"/"+numeroBatchFilien+"/filien_"+t12+"_"+numeroBatchFilien+"_"+dateDeLancementBatcheFilienTmp+".txt";
			this.nomFichierToRegie21 = fichierDeConfiguration.getLienFichierFilien()+"/"+anneeCourante+"/"+choixFilien+"/"+numeroBatchFilien+"/filien_"+t21+"_"+numeroBatchFilien+"_"+dateDeLancementBatcheFilienTmp+".txt";
			this.nomFichierToRegie22 = fichierDeConfiguration.getLienFichierFilien()+"/"+anneeCourante+"/"+choixFilien+"/"+numeroBatchFilien+"/filien_"+t22+"_"+numeroBatchFilien+"_"+dateDeLancementBatcheFilienTmp+".txt";
			this.nomFichierToRegie23 = fichierDeConfiguration.getLienFichierFilien()+"/"+anneeCourante+"/"+choixFilien+"/"+numeroBatchFilien+"/filien_"+t23+"_"+numeroBatchFilien+"_"+dateDeLancementBatcheFilienTmp+".txt";
			
		}
	
			
	}


	public int getNumeroBatchFilien() {
		return numeroBatchFilien;
	}


	public void setNumeroBatchFilien(int numeroBatchFilien) {
		this.numeroBatchFilien = numeroBatchFilien;
	}


	public String getNombreFacturesImpayeFilien() {
		return nombreFacturesImpayeFilien;
	}


	public void setNombreFacturesImpayeFilien(String nombreFacturesImpayeFilien) {
		this.nombreFacturesImpayeFilien = nombreFacturesImpayeFilien;
	}


	public String getAnExerciceFilien() {
		return anExerciceFilien;
	}


	public void setAnExerciceFilien(String anExerciceFilien) {
		this.anExerciceFilien = anExerciceFilien;
	}


	public String getNumeroTrimestreFilien() {
		return numeroTrimestreFilien;
	}


	public void setNumeroTrimestreFilien(String numeroTrimestreFilien) {
		this.numeroTrimestreFilien = numeroTrimestreFilien;
	}


	public String getDateDeLancementBatcheFilien() {
		return dateDeLancementBatcheFilien;
	}


	public void setDateDeLancementBatcheFilien(String dateDeLancementBatcheFilien) {
		this.dateDeLancementBatcheFilien = dateDeLancementBatcheFilien;
	}




	public HttpServletRequest getRequest() {
		return request;
	}


	public String getNomFichierToRegie11() {
		return nomFichierToRegie11;
	}


	public void setNomFichierToRegie11(String nomFichierToRegie11) {
		this.nomFichierToRegie11 = nomFichierToRegie11;
	}


	public String getNomFichierToRegie12() {
		return nomFichierToRegie12;
	}


	public void setNomFichierToRegie12(String nomFichierToRegie12) {
		this.nomFichierToRegie12 = nomFichierToRegie12;
	}


	public String getNomFichierToRegie21() {
		return nomFichierToRegie21;
	}


	public void setNomFichierToRegie21(String nomFichierToRegie21) {
		this.nomFichierToRegie21 = nomFichierToRegie21;
	}


	public String getNomFichierToRegie22() {
		return nomFichierToRegie22;
	}


	public void setNomFichierToRegie22(String nomFichierToRegie22) {
		this.nomFichierToRegie22 = nomFichierToRegie22;
	}


	public String getNomFichierToRegie23() {
		return nomFichierToRegie23;
	}


	public void setNomFichierToRegie23(String nomFichierToRegie23) {
		this.nomFichierToRegie23 = nomFichierToRegie23;
	}


	public int getNbreFacture11() {
		return nbreFacture11;
	}


	public void setNbreFacture11(int nbreFacture11) {
		this.nbreFacture11 = nbreFacture11;
	}


	public int getNbreFacture12() {
		return nbreFacture12;
	}


	public void setNbreFacture12(int nbreFacture12) {
		this.nbreFacture12 = nbreFacture12;
	}


	public int getNbreFacture21() {
		return nbreFacture21;
	}


	public void setNbreFacture21(int nbreFacture21) {
		this.nbreFacture21 = nbreFacture21;
	}


	public int getNbreFacture22() {
		return nbreFacture22;
	}


	public void setNbreFacture22(int nbreFacture22) {
		this.nbreFacture22 = nbreFacture22;
	}


	public int getNbreFacture23() {
		return nbreFacture23;
	}


	public void setNbreFacture23(int nbreFacture23) {
		this.nbreFacture23 = nbreFacture23;
	}


	public String getChoixFilien() {
		return choixFilien;
	}


	public void setChoixFilien(String choixFilien) {
		this.choixFilien = choixFilien;
	}




}


