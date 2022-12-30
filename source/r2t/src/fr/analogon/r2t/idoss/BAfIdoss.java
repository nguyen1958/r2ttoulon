package fr.analogon.r2t.idoss;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.request.RequestIdoss;
import fr.analogon.r2t.util.web.StaticManipHtml;


/**
 * Bean IDOSS . Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0
 */
public class BAfIdoss extends fr.analogon.r2t.main.RacineBean
{
	
	private HttpServletRequest request;	
	private String choix= "";	
	private String dateDeCreation="";	
	private String numeroBatchIdoss="";
	private String tableauLigneIdossNonValide="";
	private String tableauLigneIdossValide="";
	private String tableauLigneNonReconnu="";
	
	private boolean creation=true;	
	
	
	private String listeRechercheBatch="Aucun batch ne correspond a vos critères de recherche";
	private String nombreDeBatch="";
	
	
	RequestIdoss requestIdoss = new RequestIdoss(); 
	
	//Setting Request 
	public void setRequest(HttpServletRequest req)
	{
		this.request = req;	
		try 
		{
			this.request = req;
			if (request.getParameter("choix") != null && ! request.getParameter("choix").equalsIgnoreCase(" ") )
			{
				choix=request.getParameter("choix");
				DebuggerLog4J.logger.debug("choix= " + choix);
				if(choix.equalsIgnoreCase("lancer"))
				{
					DebuggerLog4J.logger.debug("Lancement de batch IDOSS...... ");
					this.setCreation(true);	
					this.numeroBatchIdoss = ""+requestIdoss.ajouterLigneBatchRapprochemetnIdoss();
					this.dateDeCreation = GestionDate.getDateAujourdhuiString();					
					LectureFichierIdoss idoss = new LectureFichierIdoss(Integer.parseInt(numeroBatchIdoss));
					Vector contenuLigneIdossValide= requestIdoss.getListeLigneIdoss(""+numeroBatchIdoss,"valide");
					Vector contenuLigneIdossNonValide= requestIdoss.getListeLigneIdoss(""+numeroBatchIdoss,"nonValide");
					Vector contenuLigneIdossNonReconnu= requestIdoss.getListeLigneIdossNonReconnu(""+numeroBatchIdoss);
					this.tableauLigneIdossValide=  StaticManipHtml.genererTableauIdoss(contenuLigneIdossValide,true);
					this.tableauLigneIdossNonValide=  StaticManipHtml.genererTableauIdoss(contenuLigneIdossNonValide,false);
					this.tableauLigneNonReconnu =StaticManipHtml.genererTableauNonReconnu(contenuLigneIdossNonReconnu);
					if(contenuLigneIdossNonReconnu.size()==0)
						this.tableauLigneNonReconnu = "Pas d'elements dans cette rubrique"; 
					DebuggerLog4J.logger.debug("Fin batch IDOSS...........[OK]");					
				}
				else if(choix.equalsIgnoreCase("modifierUneLigneIdoss") )
				{
					DebuggerLog4J.logger.debug("Modification d'une ligne IDOSS ");
					this.setCreation(false);
					this.numeroBatchIdoss = request.getParameter("numeroBatchIdoss");					
					this.dateDeCreation = request.getParameter("dateDeCreation");
					//Si on va mettrea jour une ligne 
					String idLigneIdoss = request.getParameter("idLigneIdoss");
					requestIdoss.majLigneIdoss(idLigneIdoss);
					Vector contenuLigneIdossValide= requestIdoss.getListeLigneIdoss(""+numeroBatchIdoss,"valide");
					Vector contenuLigneIdossNonValide= requestIdoss.getListeLigneIdoss(""+numeroBatchIdoss,"nonValide");
					this.tableauLigneIdossValide=  StaticManipHtml.genererTableauIdoss(contenuLigneIdossValide,true);
					this.tableauLigneIdossNonValide=  StaticManipHtml.genererTableauIdoss(contenuLigneIdossNonValide,false);					
					Vector contenuLigneIdossNonReconnu= requestIdoss.getListeLigneIdossNonReconnu(""+numeroBatchIdoss);
					this.tableauLigneNonReconnu =StaticManipHtml.genererTableauNonReconnu(contenuLigneIdossNonReconnu);
				}
				else if (request.getParameter("choix").equalsIgnoreCase("modifier") || 
						request.getParameter("choix").equalsIgnoreCase("validerTousLesLigneIdoss") ||
						request.getParameter("choix").equalsIgnoreCase("nePasValiderTousLesLigneIdoss") )
				{
					//on va valider tous les rapprochement du batch
					DebuggerLog4J.logger.debug("Valider tous les raprochements IDOSS ");
					this.setCreation(false);
					this.numeroBatchIdoss = request.getParameter("numeroBatchIdoss");					
					this.dateDeCreation = request.getParameter("dateDeCreation");
					if (request.getParameter("choix").equalsIgnoreCase("validerTousLesLigneIdoss"))
						requestIdoss.selectionnerTousLesRaprochementIdoss(numeroBatchIdoss,true);
					else if (request.getParameter("choix").equalsIgnoreCase("nePasValiderTousLesLigneIdoss"))
						requestIdoss.selectionnerTousLesRaprochementIdoss(numeroBatchIdoss,false);
					Vector contenuLigneIdossValide= requestIdoss.getListeLigneIdoss(""+numeroBatchIdoss,"valide");
					Vector contenuLigneIdossNonValide= requestIdoss.getListeLigneIdoss(""+numeroBatchIdoss,"nonValide");
					this.tableauLigneIdossValide=  StaticManipHtml.genererTableauIdoss(contenuLigneIdossValide,true);
					this.tableauLigneIdossNonValide=  StaticManipHtml.genererTableauIdoss(contenuLigneIdossNonValide,false);					
					Vector contenuLigneIdossNonReconnu= requestIdoss.getListeLigneIdossNonReconnu(""+numeroBatchIdoss);
					this.tableauLigneNonReconnu =StaticManipHtml.genererTableauNonReconnu(contenuLigneIdossNonReconnu);
					
				}
				
				else if (request.getParameter("choix").equalsIgnoreCase("chercher"))
				{
					RequestIdoss  requestIdoss = new RequestIdoss();
					
					String dateAction = request.getParameter("dateAction");
					Vector contenu = requestIdoss.getListeBatchIdoss(dateAction) ;			
					if( contenu.size() <200 )
					{
						nombreDeBatch ="Nombre de batchs = "+contenu.size();
						listeRechercheBatch = StaticManipHtml.genererTableaulisteBatchIdoss(contenu);
					}
					else
					{
						listeRechercheBatch=" Le résultat contient plus que 200 batchs, veuillez remplir un ou plusieurs champs dans les filtres de recherche ";
					}					
				}
			}			
		} 
		catch (Exception e) 
		{			
			DebuggerLog4J.logger.fatal("Erreur dans BaffIdoss " + e.getCause() + e.getMessage());
		}
		
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
	 * @return the dateDeCreation
	 */
	public final String getDateDeCreation() {
		return dateDeCreation;
	}

	/**
	 * @param dateDeCreation the dateDeCreation to set
	 */
	public final void setDateDeCreation(String dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}

	/**
	 * @return the numeroBatchIdoss
	 */
	public final String getNumeroBatchIdoss() {
		return numeroBatchIdoss;
	}

	/**
	 * @param numeroBatchIdoss the numeroBatchIdoss to set
	 */
	public final void setNumeroBatchIdoss(String numeroBatchIdoss) {
		this.numeroBatchIdoss = numeroBatchIdoss;
	}

	/**
	 * @return the tableauLigneIdossNonValide
	 */
	public final String getTableauLigneIdossNonValide() {
		return tableauLigneIdossNonValide;
	}

	/**
	 * @param tableauLigneIdossNonValide the tableauLigneIdossNonValide to set
	 */
	public final void setTableauLigneIdossNonValide(
			String tableauLigneIdossNonValide) {
		this.tableauLigneIdossNonValide = tableauLigneIdossNonValide;
	}

	/**
	 * @return the tableauLigneIdossValide
	 */
	public final String getTableauLigneIdossValide() {
		return tableauLigneIdossValide;
	}

	/**
	 * @param tableauLigneIdossValide the tableauLigneIdossValide to set
	 */
	public final void setTableauLigneIdossValide(String tableauLigneIdossValide) {
		this.tableauLigneIdossValide = tableauLigneIdossValide;
	}

	/**
	 * @return the tableauLigneNonReconnu
	 */
	public final String getTableauLigneNonReconnu() {
		return tableauLigneNonReconnu;
	}

	/**
	 * @param tableauLigneNonReconnu the tableauLigneNonReconnu to set
	 */
	public final void setTableauLigneNonReconnu(String tableauLigneNonReconnu) {
		this.tableauLigneNonReconnu = tableauLigneNonReconnu;
	}

	/**
	 * @return the listeRechercheBatch
	 */
	public final String getListeRechercheBatch() {
		return listeRechercheBatch;
	}

	/**
	 * @param listeRechercheBatch the listeRechercheBatch to set
	 */
	public final void setListeRechercheBatch(String listeRechercheBatch) {
		this.listeRechercheBatch = listeRechercheBatch;
	}

	/**
	 * @return the nombreDeBatch
	 */
	public final String getNombreDeBatch() {
		return nombreDeBatch;
	}

	/**
	 * @param nombreDeBatch the nombreDeBatch to set
	 */
	public final void setNombreDeBatch(String nombreDeBatch) {
		this.nombreDeBatch = nombreDeBatch;
	}	

}



