package fr.analogon.r2t.batch.facture;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.Imputation;
import fr.analogon.r2t.request.RequestFacture;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.request.RequestParametres;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de listeFactures.jsp . CHARFI Sofien
 * 
 * @version 1.0
 * @since 1.0
 */
public class BAffListeFactures extends fr.analogon.r2t.main.RacineBean {

	
	private String listeFactures;


	private HttpServletRequest request;
	
	public String  idFacture="";
	
	public String  actionFacture="";
	
	public String  numeroFacture="";
	
	public String  nomRedevable="";
	
	public String  numeroRedevable="";
	
	public String  typeTaxe="";
	
	public String  etatFacture="";
	
	public String  montantFacture="";
	
	public String  dateCreationFacture="";
	
	public String  anneeFacture="";	
	
	RequestParametres requestParametres = new RequestParametres();
	String ville = requestParametres.getVille();

	
	public void setRequest(HttpServletRequest req)
	{

		
		DebuggerLog4J.logger.debug("set request Baff liste des Factures");
		this.request = req;		
		
		if ((request.getParameter("numeroFacture") != null)) 
			this.numeroFacture = request.getParameter("numeroFacture");
		
		if ((request.getParameter("montantFacture") != null)) 
			this.montantFacture = request.getParameter("montantFacture");
		
		
		if ((request.getParameter("nomRedevable") != null)) 
			this.nomRedevable = request.getParameter("nomRedevable");	

		
		if ((request.getParameter("numeroRedevable") != null)) 
			this.numeroRedevable = request.getParameter("numeroRedevable");	
		
		
		if ((request.getParameter("etatFacture") != null)) 
			this.etatFacture = request.getParameter("etatFacture");	
		
		
		if ((request.getParameter("dateCreationFacture") != null)) 
			this.dateCreationFacture = request.getParameter("dateCreationFacture");
		
		if ((request.getParameter("anneeFacture") != null)) 
			this.anneeFacture = request.getParameter("anneeFacture");
		
		
		
		
		if ((request.getParameter("idFacture") != null)) 
			this.idFacture = request.getParameter("idFacture");	
		
		if ((request.getParameter("actionFacture") != null)) 
			this.actionFacture = request.getParameter("actionFacture");	
		
		
		if ((request.getParameter("typeTaxe") != null)) 
			this.typeTaxe = request.getParameter("typeTaxe");	
		
			
	}	
	
	
	public String getlisteFactures()
	{
		String res="";
		StaticManipHtml mHtml = new StaticManipHtml();
		RequestFacture req= new RequestFacture();
		Vector contenu = req.getListeDesfacture(numeroFacture,numeroRedevable,
				typeTaxe,etatFacture,dateCreationFacture, nomRedevable,montantFacture,anneeFacture) ;
				
		res=mHtml.genererListeDesFactures(contenu );
		//System.out.println(
		return res;
	}
	
	public String getetatFacture()
	{
		StaticManipHtml mHtml = new StaticManipHtml();
		RequestFacture req = new RequestFacture();
		Vector Contenu = req.getTousLesEtatsFacture();

		if ( ! ville.equalsIgnoreCase("bordeaux"))
		{
			Contenu.addElement("PAYEE");
			Contenu.addElement("IMPAYE");
		}
		String res= StaticManipHtml.genererListeDeroulanteApartirDeVacteur("etatFacture", 
				etatFacture, Contenu, 1, true);
		return res;
		
	}
	
	/**
	 * @return the typeTaxe
	 */
	public final String getTypeTaxe() 
	{	
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();	
		RequestImputation reqImputation = new RequestImputation();		
		Vector listDesTypeTaxe = reqImputation.getLesDifferentTypesTaxe();
		for (int i = 0; i < listDesTypeTaxe.size(); i++) 
		{
			contenu.addElement(((Imputation)listDesTypeTaxe.elementAt(i)).getLibelle());
		}				
		res = StaticManipHtml.genererListeDeroulante("typeTaxe", 1,typeTaxe, contenu,true);
		//res= contenu.toString();
		return res;		
	}	
	
}