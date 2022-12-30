package fr.analogon.r2t.view.regie;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.Facture;
import fr.analogon.r2t.pojo.Imputation;
import fr.analogon.r2t.request.RequestFacture;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.request.RequestPayement;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de listePayement.jsp . CHARFI Sofien
 * 
 * @version 1.0
 * @since 1.0
 */
public class BAffListePayement extends fr.analogon.r2t.main.RacineBean {

	
	private String listePayement;


	private HttpServletRequest request;	
		
	public String  montantPayement="";
	public String  nombreFacturePaye="";
	public String  datePayement="";
	public String  numeroCheque="";
	public String  numeroTransaction="";
	public String  numeroQuittance="";
	public String  numeroFacture="";
	public String  idFacture="";
	public String  banque="";
	public String  idBanque="";
	public String  typePaiement="";
	public String  idTypePayement="";
	private String etatPaiement="";
	public String  typeTaxe="";
	public String  typeMarche="";
	public String   anneePaiement="";
	

	public void setRequest(HttpServletRequest req)
	{

		
		DebuggerLog4J.logger.debug("set request Baff liste des Factures");
		this.request = req;					
		
		if ((request.getParameter("datePayement") != null)) 
			this.datePayement = request.getParameter("datePayement");	
		
		if ((request.getParameter("etatPaiement") != null)
				&& (!request.getParameter("etatPaiement").equalsIgnoreCase(""))) 
		{ etatPaiement = request.getParameter("etatPaiement");	}
		
		
		
		
		if ((request.getParameter("montantPayement") != null)) 
			this.montantPayement = request.getParameter("montantPayement");
		
		if ((request.getParameter("typeMarche") != null)) 
			this.typeMarche = request.getParameter("typeMarche");
		
		
		if ((request.getParameter("nombreFacturePaye") != null)) 
			this.nombreFacturePaye = request.getParameter("nombreFacturePaye");
		
		if ((request.getParameter("numeroQuittance") != null)) 
			this.numeroQuittance = request.getParameter("numeroQuittance");
		
		if ((request.getParameter("numeroFacture") != null)) 
		{
			this.numeroFacture = request.getParameter("numeroFacture");
			//recherche de l'id de la facture 
			RequestFacture requestFacture = new RequestFacture();
			Facture facture =  requestFacture.getFactureFromNumeroFacture(numeroFacture);
			idFacture = ""+facture.getIdFacture();
		}
		
		if ((request.getParameter("numeroCheque") != null)) 
			this.numeroCheque = request.getParameter("numeroCheque");
		
		if ((request.getParameter("numeroTransaction") != null)) 
			this.numeroTransaction = request.getParameter("numeroTransaction");

		
		if ((request.getParameter("anneePaiement") != null)) 
			this.anneePaiement = request.getParameter("anneePaiement");
  
		
		if ((request.getParameter("idBanque") != null)) 
			this.idBanque = request.getParameter("idBanque");
		
		if ((request.getParameter("idTypePayement") != null)) 
			this.idTypePayement = request.getParameter("idTypePayement");
		
		if ((request.getParameter("idTypePayement") != null)) 
			this.idTypePayement = request.getParameter("idTypePayement");
		
		
			
		RequestPayement requestPayement = new RequestPayement();
		Vector listeDesBanque = requestPayement.getTousLesBanques();	
		this.banque = StaticManipHtml.genererListeDeroulanteBanque("banque", "", listeDesBanque,true);
		
		
		Vector listeDesTypePayement = requestPayement.getTousLesTypePayement();	
		this.typePaiement = StaticManipHtml.genererListeDeroulanteTypePayement("typePaiement", "", listeDesTypePayement,true);
			
		Vector contenu= new Vector ();	
		RequestImputation reqImputation = new RequestImputation();		
		Vector listDesTypeTaxe = reqImputation.getLesDifferentTypesTaxe();
		for (int i = 0; i < listDesTypeTaxe.size(); i++) 
		{
			contenu.addElement(((Imputation)listDesTypeTaxe.elementAt(i)).getLibelle());
		}				
		typeTaxe = StaticManipHtml.genererListeDeroulante("typeTaxe", 1,typeTaxe, contenu,true);
		typeMarche= StaticManipHtml.genererListeDeroulante("typeMarche", 1,typeMarche, contenu,true);
		
		if ((request.getParameter("typeTaxe") != null)) 
			this.typeTaxe = request.getParameter("typeTaxe");
		
		if ((request.getParameter("typeMarche") != null)) 
			this.typeMarche = request.getParameter("typeMarche");
		
	}	
	
	
	public String getlistePayement()
	{
		String res="";
		StaticManipHtml mHtml = new StaticManipHtml();
		RequestPayement requestPayement = new RequestPayement();
		ArrayList contenu = requestPayement.getListeDesPayements(datePayement,montantPayement
							,nombreFacturePaye,numeroQuittance,numeroCheque,numeroFacture
							,idBanque,idTypePayement, idFacture,typeTaxe,etatPaiement,anneePaiement,numeroTransaction);				
		res=mHtml.genererListeDesPayement(contenu );
		
		return res;
	}
	
	

	public String getBanque() {
		return banque;
	}


	public void setBanque(String banque) {
		this.banque = banque;
	}


	public String getTypePaiement() {
		return typePaiement;
	}


	public void setTypePaiement(String typePaiement) {
		this.typePaiement = typePaiement;
	}


	public String getTypeTaxe() {
		return typeTaxe;
	}


	public void setTypeTaxe(String typeTaxe) {
		this.typeTaxe = typeTaxe;
	}


	public String getEtatPaiement() {
		return etatPaiement;
	}


	public void setEtatPaiement(String etatPaiement) {
		this.etatPaiement = etatPaiement;
	}


	public String getTypeMarche() {
		return typeMarche;
	}


	public void setTypeMarche(String typeMarche) {
		this.typeMarche = typeMarche;
	}	
	
	
	
}