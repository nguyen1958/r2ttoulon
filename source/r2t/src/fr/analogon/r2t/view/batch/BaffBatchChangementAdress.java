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
public class BaffBatchChangementAdress extends fr.analogon.r2t.main.RacineBean {
	
	private int numeroBatch=0;
	private String dateDeCreation="";	
	private String nombreFichier="";
	private HttpServletRequest request;
	private String lienFichierChangementAdresseRedevable="";

	public String getLienFichierChangementAdresseRedevable() {
		return lienFichierChangementAdresseRedevable;
	}


	public String getDateDeCreation() {
		return dateDeCreation;
	}


	public void setDateDeCreation(String dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}

	public BaffBatchChangementAdress() 
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
				
				RequestBatch reqBatch = new RequestBatch();
				this.dateDeCreation=GestionDate.getDateAujourdhuiString();				
				
				CreerRoleFacturation cr =  new CreerRoleFacturation();
				//lancement de batch de changement d'adresse :
				this.numeroBatch = reqBatch.ajouterLigneBatchChangementAdresse(dateDeCreation);
				String resRCAR = cr.lancerCreerRoleFacturation("RCAR",numeroBatch,"","","","","","");				
								
				if (resRCAR.equalsIgnoreCase("true"))
				{
					this.nombreFichier ="1";
					reqBatch.majBatchChangementAdresse(""+numeroBatch, "true");
					
					String lienRoleChgtAdresseRedevable = fichierDeConfiguration.getLienRoleChgtAdresseRedevable();
					this.lienFichierChangementAdresseRedevable=lienRoleChgtAdresseRedevable 
					                            + "RCAR_"+numeroBatch +".pdf";
					//il y a une edition d'un fichier pour le changement d'adress
					//On retourne un lien vers le fichier 
				}
				else
				{				
					reqBatch.majBatchChangementAdresse(""+numeroBatch, "false");
					//il n' y a une edition d'un fichier pour le changement d'adress
					this.nombreFichier ="0";
				}
			}
			catch (Exception e) 
			{
				DebuggerLog4J.logger.fatal(e.getMessage());
			}	
	}


	public String getNombreFichier() {
		return nombreFichier;
	}


	public void setNombreFichier(String nombreFichier) {
		this.nombreFichier = nombreFichier;
	}


	public int getNumeroBatch() {
		return numeroBatch;
	}
}


