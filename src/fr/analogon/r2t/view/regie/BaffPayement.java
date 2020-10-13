package fr.analogon.r2t.view.regie;

import java.text.DecimalFormat;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.Banque;
import fr.analogon.r2t.pojo.Imputation;
import fr.analogon.r2t.pojo.Payement;
import fr.analogon.r2t.pojo.Redevable;
import fr.analogon.r2t.pojo.TypePayement;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.request.RequestPayement;
import fr.analogon.r2t.request.RequestRedevable;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de payement 
 * 
 * @version 1.1 .Sofien CHARFI
 * @since 1.0
 */

public class BaffPayement extends fr.analogon.r2t.main.RacineBean 
{	
	private HttpServletRequest request;
	private String banque="";
	private String idPayement="0";
	private String typePayement="";
	private String typePayementMarche="";
	
	private String datePayement="";
	private String idTypePayement="";
	private String idBanque="";
	private String numeroCheque="";
	private String montantPayement="";
	private String numeroQuittance="";
	private String nombreDeFacturePayee="";
	private String numRedevable="";
	private String listeDesFactures="";
	private String etatPaiement="";
	private boolean creation=true;
	private String typeTaxe="";
	
	private String numeroTransaction="";
	
	private String cheminRapportListeQuittancesJournalierToulon;
	private String cheminRapportRecapitulatifComptesJournalierToulon;
	private String cheminRapportVersementChequesJournalierToulon;
	private String cheminRapportVersementJournalierToulon;
	private String annulerPaiement;
	String paiementMarche="";
	
	private String  nomMarche;
	
	RequestPayement requestPayement = new RequestPayement();
	
	
	public void setRequest(HttpServletRequest req) 
	{
		DebuggerLog4J.logger.debug("-------------SET REQUEST -------------");
		this.request = req;
		if (request.getParameter("paiementMarche") != null)
			this.paiementMarche = request.getParameter("paiementMarche");
		
		if (request.getParameter("idPayement") != null)
			this.idPayement = request.getParameter("idPayement");
		
		if (request.getParameter("annulerPaiement") != null)
			this.annulerPaiement = request.getParameter("annulerPaiement");
		
		DebuggerLog4J.logger.debug("idPayement="+idPayement);
		DebuggerLog4J.logger.debug("annulerPaiement="+annulerPaiement);
		
		Vector listeBanque = requestPayement.getTousLesBanques();
		Vector listeTypePayement = requestPayement.getTousLesTypePayement();
		Vector listeTypePayementMarche = requestPayement.getTousLesTypePayementMarche();
		typePayement = StaticManipHtml.genererListeDeroulanteTypePayement("typePayement", "", listeTypePayement,false);
		typePayementMarche = StaticManipHtml.genererListeDeroulanteTypePayement("typePayement", "", listeTypePayementMarche,false);
		banque= StaticManipHtml.genererListeDeroulanteBanque("banque", "", listeBanque,false);
		
		if(paiementMarche.equalsIgnoreCase("true") )
		{
			if(!idPayement.equalsIgnoreCase("0"))
			{
				creation = false;
				Payement payement = requestPayement.getPayementMarche(idPayement);
				this.numeroQuittance = payement.getNumeroQuittance();
				this.datePayement = payement.getDatePayement();
				this.montantPayement = payement.getMontantPayement();
				this.typeTaxe = payement.getTypeTaxe();
				this.nomMarche = payement.getTypeTaxe();
				this.etatPaiement = payement.getEtat();
				this.numeroTransaction = payement.getNumeroTransaction();
			}
			
			
		}
		else
		{
			if(!idPayement.equalsIgnoreCase("0"))
			{
				creation = false;
				//cas d'un payement
				Payement payement = requestPayement.getPayement(idPayement);
				montantPayement = payement.getMontantPayement();
				
				DecimalFormat df = new DecimalFormat("0.00");
				montantPayement = df.format(Double.valueOf(montantPayement).doubleValue());
				montantPayement = montantPayement.replaceAll(",", ".");
				
				
				nombreDeFacturePayee = payement.getNombreDeFacturePayee();
				numeroQuittance = payement.getNumeroQuittance();			
				numeroCheque = payement.getNumeroCheque();
				numeroTransaction = payement.getNumeroTransaction();
				datePayement= payement.getDatePayement();
				etatPaiement = payement.getEtat();
				numRedevable =payement.getNumRedevable();
				RequestRedevable requestRedevable= new RequestRedevable(); 
				Redevable redevable =  requestRedevable.getRedevable(numRedevable);			
				
				String lienRedevable=redevable.getRaisonSocialeRedevable()+ " " + redevable.getNomRedevable() ;
				numRedevable = "<a href= ./entree?action=empl_gestion_redevable.jsp&choix=modifier&boton=modifier" +
							   "&typeRecherche=role&numRedevable="+ numRedevable +">"+ lienRedevable +"</a>";
				Banque banqueobj = requestPayement.getBanqueFromId( Integer.valueOf(payement.getIdBanque()));			 
				TypePayement typePayementObj = requestPayement.getTypePayementFromId(Integer.valueOf(payement.getIdTypePayement()));
							
				typePayement= typePayementObj.getTypePayement();			
				banque= banqueobj.getLibelle();
							 
				Vector listeDesFacturesobj = requestPayement.getListeDesFactureFromPayement(idPayement);
				listeDesFactures= StaticManipHtml.genererTableauFactureParPayement(listeDesFacturesobj);
				
				//Si on a une demande d'annulation de la transaction , on appelle le module d'annulation
				if(annulerPaiement !=null && annulerPaiement.equalsIgnoreCase("true"))
				{
					RequestPayement  requestPayement = new RequestPayement();
					HttpSession session = request.getSession(true);
					String idUtilisateur = "";
					session.getAttribute("idUtilisateur");
					if (session.getAttribute("idUtilisateur") != null )
						idUtilisateur =  (String) session.getAttribute("idUtilisateur");
					requestPayement.annulerPayement(idPayement,idUtilisateur);
					this.setEtatPaiement("annulle");
				}			
			}

			
		}
					
	}

	public String getBanque() {
		return banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}

	public String getTypePayement() {
		return typePayement;
	}

	public void setTypePayement(String typePayement) {
		this.typePayement = typePayement;
	}

	public String getIdPayement() {
		return idPayement;
	}

	public void setIdPayement(String idPayement) {
		this.idPayement = idPayement;
	}

	public String getDatePayement() {
		return datePayement;
	}

	public void setDatePayement(String datePayement) {
		this.datePayement = datePayement;
	}

	public String getIdBanque() {
		return idBanque;
	}

	public void setIdBanque(String idBanque) {
		this.idBanque = idBanque;
	}

	public String getIdTypePayement() {
		return idTypePayement;
	}

	public void setIdTypePayement(String idTypePayement) {
		this.idTypePayement = idTypePayement;
	}

	public String getMontantPayement() {
		return montantPayement;
	}

	public void setMontantPayement(String montantPayement) {
		this.montantPayement = montantPayement;
	}

	public String getNombreDeFacturePayee() {
		return nombreDeFacturePayee;
	}

	public void setNombreDeFacturePayee(String nombreDeFacturePayee) {
		this.nombreDeFacturePayee = nombreDeFacturePayee;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public String getNumeroQuittance() {
		return numeroQuittance;
	}

	public void setNumeroQuittance(String numeroQuittance) {
		this.numeroQuittance = numeroQuittance;
	}


	public String getNumRedevable() {
		return numRedevable;
	}

	public void setNumRedevable(String numRedevable) {
		this.numRedevable = numRedevable;
	}

	public String getListeDesFactures() {
		return listeDesFactures;
	}

	public void setListeDesFactures(String listeDesFactures) {
		this.listeDesFactures = listeDesFactures;
	}

	public String getEtatPaiement() {
		return etatPaiement;
	}

	public void setEtatPaiement(String etatPaiement) {
		this.etatPaiement = etatPaiement;
	}

	public boolean getCreation() {
		return creation;
	}

	public void setCreation(boolean creation) {
		this.creation = creation;
	}


	public String getTypePayementMarche() {
		return typePayementMarche;
	}

	public void setTypePayementMarche(String typePayementMarche) {
		this.typePayementMarche = typePayementMarche;
	}

	public String getTypeTaxe() 
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();	
		RequestImputation reqImputation = new RequestImputation();		
		Vector listDesTypeTaxe = reqImputation.getListeTypeDeTaxe(GestionDate.getAnneeCourante(),"");
		for (int i = 0; i < listDesTypeTaxe.size(); i++) 
		{
			contenu.addElement(((Imputation)listDesTypeTaxe.elementAt(i)).getNumtypeTaxe() +"---"+
					((Imputation)listDesTypeTaxe.elementAt(i)).getLibelle());
		}				
		Imputation imputation = reqImputation.getImputation(typeTaxe, GestionDate.getAnneeCourante());
		String libelleimputation =  imputation.getLibelle();
		typeTaxe = typeTaxe +"---" + libelleimputation;
		res = StaticManipHtml.genererListeDeroulanteChangementTypeTaxe("typeTaxe", 1,typeTaxe, contenu,false);
		//res= contenu.toString();
		return res;	
		
	}

	public void setTypeTaxe(String typeTaxe) {
		this.typeTaxe = typeTaxe;
	}

	public String getNomMarche() 
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();	
		RequestImputation reqImputation = new RequestImputation();		
		Vector listDesTypeTaxe = reqImputation.getListeTypeDeTaxe(GestionDate.getAnneeCourante(),"marche");
		for (int i = 0; i < listDesTypeTaxe.size(); i++) 
		{
			contenu.addElement(((Imputation)listDesTypeTaxe.elementAt(i)).getNumtypeTaxe() +"---"+
					((Imputation)listDesTypeTaxe.elementAt(i)).getLibelle());
		}				
		Imputation imputation = reqImputation.getImputation(nomMarche, GestionDate.getAnneeCourante());
		String libelleimputation =  imputation.getLibelle();
		nomMarche = nomMarche +"---" + libelleimputation;
		res = StaticManipHtml.genererListeDeroulanteChangementTypeTaxe("nomMarche", 1,nomMarche, contenu,false);
		//res= contenu.toString();
		return res;	
		
	}

	public void setNomMarche(String nomMarche) {
		this.nomMarche = nomMarche;
	}

	public String getNumeroTransaction() {
		return numeroTransaction;
	}

	public void setNumeroTransaction(String numeroTransaction) {
		this.numeroTransaction = numeroTransaction;
	}
	
	

	
}
