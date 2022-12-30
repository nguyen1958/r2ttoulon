package fr.analogon.r2t.batch.facture;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.Facture;
import fr.analogon.r2t.request.RequestFacture;
import fr.analogon.r2t.request.RequestParametres;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de listeFactures.jsp . CHARFI Sofien
 * 
 * @version 1.0
 * @since 1.0
 */
public class BAffUneFacture extends fr.analogon.r2t.main.RacineBean 
{

	private HttpServletRequest request;
	public String  numeroFacture="";
	public String  numeroTitre="";
	public String  numeroBatch="";	
	public String  lienRedevable="";	
	public String  lienFacture="";
	public String  typeTaxe="";
	public String  montant="";
	public String  solde="";
	public String  etatFacture="";	
	public String  dateCreationFacture="";
	public String  dateAnnulation="";
	public String  motifAnnuation="";
	public String  historiquePayement="";	
	   
	public String  peutetreannule="";	
	


	public void setRequest(HttpServletRequest req)
	{
		try 
		{
			DebuggerLog4J.logger.debug("set request Baff une Factures");
			this.request = req;		
			
			if ((request.getParameter("numeroFacture") != null)
					&& (!request.getParameter("numeroFacture").equalsIgnoreCase(""))) 
				this.numeroFacture = request.getParameter("numeroFacture");
			RequestFacture reqFacture = new RequestFacture();
			Facture facture = reqFacture.getFacture(numeroFacture);
			this.montant = facture.getMontantTotalFacture();
			this.solde = facture.getSoldeFacture();
			this.etatFacture = facture.getEtat();
			this.numeroTitre = facture.getNumeroTitre();
			this.numeroBatch = facture.getIdBatch();
			this.typeTaxe = facture.getTypeTaxe();
			this.dateAnnulation = facture.getDateAnnulation();
			this.dateCreationFacture = facture.getDateCreationFacture();
			
			String linkRedevable = "./entree?action=empl_gestion_redevable.jsp&choix=modifier" +
					"&boton=ajouter&typeRecherche=role&optionTransfert=role&typeRedevable=normal" +
					"&origine=origine&numRedevable=" + facture.getIdClient() +
					"&nomRedevable=" + facture.getNomPrenomClient() ;
			
			String linkBatch = "./entree?action=gestionBatch.jsp&numeroBatch="+ facture.getIdBatch();
			
			this.lienRedevable = "<a href="+linkRedevable+">"+ facture.getNomPrenomClient()+"</a>" ;
			this.numeroBatch = "<a href="+linkBatch+">Batch Num "+ facture.getIdBatch()+"</a>" ;			
			String repertoirePdf=  "";
			this.lienFacture = "<a target='_blank' href=" + fichierDeConfiguration.getLienFichierDesFactures() +
				facture.getNomDossier()+"/"+ numeroFacture+".pdf >"+ "Facture Num "+numeroFacture+"</a>";				

			RequestFacture  requestFacture = new RequestFacture();
			
			
			if( etatFacture != null && ! etatFacture.equalsIgnoreCase("Annulee"))
			{
				StaticManipHtml mHtml = new StaticManipHtml();
				RequestParametres requestParametres = new RequestParametres();
			    Vector contenu = requestParametres.getTousLesMotifsAnnulationFacture(); 
			    peutetreannule = requestFacture.peutAnnulerLaFactue(numeroFacture);
			    if (peutetreannule != null &&  peutetreannule.equalsIgnoreCase("false"))
			    	this.motifAnnuation = "Impossible d'annuler cette facture, il y a des paiements valides associés" ; 
			    else
			    	this.motifAnnuation=mHtml.genererListeDeroulanteMotifAnnulation("motifAnnulation","",contenu,1,true);
			}
			else
			{			
				 this.motifAnnuation = facture.getMotifAnnulationFacture();	
			}
			
			this.historiquePayement="<a href='./entree?action=liste_payement.jsp&numeroFacture="+numeroFacture+"&etatPaiement=tous&typeTaxe='>" +
					" historique des paiements" +
					"</a> ";
			
		}
		catch (Exception e) 
		{
			DebuggerLog4J.logger.fatal("Erreur "+ e.getMessage() +  e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * @return the dateCreationFacture
	 */
	public final String getDateCreationFacture() {
		return dateCreationFacture;
	}


	/**
	 * @param dateCreationFacture the dateCreationFacture to set
	 */
	public final void setDateCreationFacture(String dateCreationFacture) {
		this.dateCreationFacture = dateCreationFacture;
	}


	/**
	 * @return the etatFacture
	 */
	public final String getEtatFacture() {
		return etatFacture;
	}


	/**
	 * @param etatFacture the etatFacture to set
	 */
	public final void setEtatFacture(String etatFacture) {
		this.etatFacture = etatFacture;
	}


	/**
	 * @return the lienFacture
	 */
	public final String getLienFacture() {
		return lienFacture;
	}


	/**
	 * @param lienFacture the lienFacture to set
	 */
	public final void setLienFacture(String lienFacture) {
		this.lienFacture = lienFacture;
	}


	/**
	 * @return the lienRedevable
	 */
	public final String getLienRedevable() {
		return lienRedevable;
	}


	/**
	 * @param lienRedevable the lienRedevable to set
	 */
	public final void setLienRedevable(String lienRedevable) {
		this.lienRedevable = lienRedevable;
	}


	/**
	 * @return the montant
	 */
	public final String getMontant() {
		return montant;
	}


	/**
	 * @param montant the montant to set
	 */
	public final void setMontant(String montant) {
		this.montant = montant;
	}


	/**
	 * @return the motifAnnuation
	 */
	public final String getMotifAnnuation() {
		return motifAnnuation;
	}


	/**
	 * @param motifAnnuation the motifAnnuation to set
	 */
	public final void setMotifAnnuation(String motifAnnuation) {
		this.motifAnnuation = motifAnnuation;
	}


	/**
	 * @return the numeroFacture
	 */
	public final String getNumeroFacture() {
		return numeroFacture;
	}


	/**
	 * @param numeroFacture the numeroFacture to set
	 */
	public final void setNumeroFacture(String numeroFacture) {
		this.numeroFacture = numeroFacture;
	}


	/**
	 * @return the numeroTitre
	 */
	public final String getNumeroTitre() {
		return numeroTitre;
	}


	/**
	 * @param numeroTitre the numeroTitre to set
	 */
	public final void setNumeroTitre(String numeroTitre) {
		this.numeroTitre = numeroTitre;
	}


	/**
	 * @return the solde
	 */
	public final String getSolde() {
		return solde;
	}


	/**
	 * @param solde the solde to set
	 */
	public final void setSolde(String solde) {
		this.solde = solde;
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


	/**
	 * @return the numeroBatch
	 */
	public final String getNumeroBatch() {
		return numeroBatch;
	}


	/**
	 * @param numeroBatch the numeroBatch to set
	 */
	public final void setNumeroBatch(String numeroBatch) {
		this.numeroBatch = numeroBatch;
	}


	/**
	 * @return the dateAnnulation
	 */
	public final String getDateAnnulation() {
		return dateAnnulation;
	}


	/**
	 * @param dateAnnulation the dateAnnulation to set
	 */
	public final void setDateAnnulation(String dateAnnulation) {
		this.dateAnnulation = dateAnnulation;
	}



	public boolean verfierAcces(String listeLibelleDesTypesDeTaxeAutorise) 
	{
		boolean res=false;	
		if(listeLibelleDesTypesDeTaxeAutorise.indexOf(typeTaxe) != -1)
			res= true; 
		
		return res;
	}


	public String getHistoriquePayement() {
		return historiquePayement;
	}


	public void setHistoriquePayement(String historiquePayement) {
		this.historiquePayement = historiquePayement;
	}
	
	
	public String getPeutetreannule() {
		return peutetreannule;
	}


	public void setPeutetreannule(String peutetreannule) {
		this.peutetreannule = peutetreannule;
	}

}

