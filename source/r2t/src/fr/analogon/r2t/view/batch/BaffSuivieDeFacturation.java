package fr.analogon.r2t.view.batch;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.facturation.CreerRoleFacturation;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.request.RequestBatch;


/**
 * Bean d'affichage du batch Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0
 */
public class BaffSuivieDeFacturation extends fr.analogon.r2t.main.RacineBean {
	
	private int numeroBatch=0;
	private String dateDeCreation="";	
	private String nombreFichier="";
	private HttpServletRequest request;
	private String lienFichierSuivieFacturation="";
	private String datedebut="";
	private String dateFin="";
	private String typeTaxe="";
	private String idDemandeur="";	

	


	public String getDateDeCreation() {
		return dateDeCreation;
	}


	public void setDateDeCreation(String dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}

	public BaffSuivieDeFacturation() 
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
				RequestBatch reqBatch = new RequestBatch();
				this.dateDeCreation=GestionDate.getDateAujourdhuiString();				
				if (request.getParameter("debutPeriode") != null) 
					this.datedebut = request.getParameter("debutPeriode");
				if (request.getParameter("finPeriode") != null) 
					this.dateFin = request.getParameter("finPeriode");
				if (request.getParameter("typeTaxe") != null) 
					this.typeTaxe = request.getParameter("typeTaxe");		
			
				CreerRoleFacturation cr =  new CreerRoleFacturation();
				//lancement de batch de csuivie de facturation :
				this.numeroBatch = reqBatch.ajouterLigneBatchSuivieFacturation(dateDeCreation,
						datedebut,  dateFin, typeTaxe,  idDemandeur);
				String resRSF = cr.lancerCreerRoleFacturation("RSF", numeroBatch, "",
						idDemandeur, typeTaxe, datedebut, dateFin,"");
								
				if (resRSF.equalsIgnoreCase("true"))
				{
					this.nombreFichier ="1";
					reqBatch.majBatchSuivieDeFacturation(""+numeroBatch, "true");
					
					String lienRoleSuiviFacturation = fichierDeConfiguration.getLienRoleSuivieFacturation();
					this.lienFichierSuivieFacturation= lienRoleSuiviFacturation 
					                            + "RSF_"+numeroBatch +".pdf";
					//il y a une edition d'un fichier pour le suivie de facturation
					//On retourne un lien vers le fichier 
				}
				else
				{				
					reqBatch.majBatchSuivieDeFacturation(""+numeroBatch, "false");
					//il n' y a une edition d'un fichier pour le changement d'adress
					this.nombreFichier ="0";
				}
			}
			catch (Exception e) 
			{
				DebuggerLog4J.logger.fatal(e.getMessage());
			}	
	}


	/**
	 * @return the datedebut
	 */
	public final String getDatedebut() {
		return datedebut;
	}


	/**
	 * @param datedebut the datedebut to set
	 */
	public final void setDatedebut(String datedebut) {
		this.datedebut = datedebut;
	}


	/**
	 * @return the dateFin
	 */
	public final String getDateFin() {
		return dateFin;
	}


	/**
	 * @param dateFin the dateFin to set
	 */
	public final void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}


	/**
	 * @return the idDemandeur
	 */
	public final String getIdDemandeur() {
		return idDemandeur;
	}


	/**
	 * @param idDemandeur the idDemandeur to set
	 */
	public final void setIdDemandeur(String idDemandeur) {
		this.idDemandeur = idDemandeur;
	}


	/**
	 * @return the lienFichierSuivieFacturation
	 */
	public final String getLienFichierSuivieFacturation() {
		return lienFichierSuivieFacturation;
	}


	/**
	 * @param lienFichierSuivieFacturation the lienFichierSuivieFacturation to set
	 */
	public final void setLienFichierSuivieFacturation(
			String lienFichierSuivieFacturation) {
		this.lienFichierSuivieFacturation = lienFichierSuivieFacturation;
	}


	/**
	 * @return the nombreFichier
	 */
	public final String getNombreFichier() {
		return nombreFichier;
	}


	/**
	 * @param nombreFichier the nombreFichier to set
	 */
	public final void setNombreFichier(String nombreFichier) {
		this.nombreFichier = nombreFichier;
	}


	/**
	 * @return the numeroBatch
	 */
	public final int getNumeroBatch() {
		return numeroBatch;
	}


	/**
	 * @param numeroBatch the numeroBatch to set
	 */
	public final void setNumeroBatch(int numeroBatch) {
		this.numeroBatch = numeroBatch;
	}


	/**
	 * @return the typeTaxe
	 */
	public final String getTypeTaxe() {
		return typeTaxe;
	}


	/**
	 * @param typeTaxe the typeTaxe to set
	 */
	public final void setTypeTaxe(String typeTaxe) {
		this.typeTaxe = typeTaxe;
	}


	/**
	 * @return the request
	 */
	public final HttpServletRequest getRequest() {
		return request;
	}


}


