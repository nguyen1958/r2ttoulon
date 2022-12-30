package fr.analogon.r2t.parametres.bareme;

import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.pojo.TranchePrixBareme;
import fr.analogon.r2t.request.RequestBareme;


/**
 * Permet de gerer le bareme .
 * Gestion des baremes. . Sofien CHARFI
 * 
 * @since 1.0
 * @version 1.3
 */
public class GestionBareme extends fr.analogon.r2t.main.RacineServlet 
{	
	private HttpServletRequest request;	
	private String choix= "";	
	private boolean creation=true;
	private String codeBareme= "";
	private String anneeExercice= "";
	private String libelle= "";
	private String typeTaxe= "";
	private String prixUnitaire= "";
	private String uniteDeTravail= "";
	private String uniteDeTemps= "";
	private String utiliserProrata= "NON";
	private String utiliserPeriodicite= "NON";
	private String typeArrondi= "";
	private String prixPeriode1= "";
	private String prixPeriode2= "";
	private String prixPeriode3= "";
	private String prixPeriode4= "";
	private String prixPeriode5= "";	
	private String listeDesTranchesDesPrix="";
	private String coefficientNumerique="1";


	private String sectionBareme="";
	private String imputationBareme="";
	

	
	
	
	
	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)			
	{
		try 
		{
			////System.out.println("**********************");
			//System.out.println("GESTION Bareme.....................................");		
			
			debug.logger.debug("");		
			this.request = request;			
			getParametres();	
			ServletContext sc = getServletConfig().getServletContext();	
			if( choix.equalsIgnoreCase("modifier") || choix.equalsIgnoreCase("Ajouter"))
			{
				//System.out.println();
				RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=empl_gestion_bareme.jsp" +
					"&choix=modifier&codeBareme="+codeBareme);
				rd.forward(request, response);			
			}
			else
			{			
				RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=liste_bareme.jsp");
				rd.forward(request, response);					
			}
			
		} 
		catch (Exception e) 
		{
			//System.out.println("Gestion de bareme.....................[Erreur]");
			debug.logger.fatal(e.getMessage());
		}
	}

	
	public void destroy() 
	{
		super.destroy();		
	}

	/**
	 * Permet de recuperer les parametres html . Sofien CHARFI
	 * 
	 * @since 1.0
	 */
	private void getParametres() 
	{		
		
		////System.out.println("TEst"+request.getParameter("texteReponse"));
		if ((request.getParameter("choix") != null)	) 
			this.choix = request.getParameter("choix");	
		
		
		//////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////
	    //TRAITEMENT DES DONNNEE ET ACCES A LA BD
		//////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////
		//System.out.println("choixchoixchoixchoixchoixchoix"+ choix);
		
		if ((request.getParameter("choix") != null)) this.choix = request.getParameter("choix");		
		if ((request.getParameter("codeBareme") != null)) this. codeBareme = request.getParameter("codeBareme");
		if ((request.getParameter("anneeExercice") != null)) this. anneeExercice = request.getParameter("anneeExercice");
		if ((request.getParameter("libelle") != null)) this. libelle = request.getParameter("libelle");
		if ((request.getParameter("typeTaxe") != null))
		{
			this. typeTaxe = request.getParameter("typeTaxe");
			String tableau[] = typeTaxe.split("---");
			typeTaxe = tableau[0];		
			//System.out.println(typeTaxe);
		}
		if ((request.getParameter("prixUnitaire") != null)) this. prixUnitaire = request.getParameter("prixUnitaire");
		if ((request.getParameter("uniteDeTravail") != null)) this. uniteDeTravail = request.getParameter("uniteDeTravail");
		if ((request.getParameter("uniteDeTemps") != null)) this. uniteDeTemps = request.getParameter("uniteDeTemps");
		if ((request.getParameter("utiliserProrata")!= null)) 
		{
			this. utiliserProrata = request.getParameter("utiliserProrata");
			if (utiliserProrata.equalsIgnoreCase("OUI"))
				utiliserProrata="true";
			else
				utiliserProrata="false";
		}
		if ((request.getParameter("utiliserPeriodicite")!= null)) 
		{
			this. utiliserPeriodicite = request.getParameter("utiliserPeriodicite");
			if (utiliserPeriodicite.equalsIgnoreCase("OUI"))
				utiliserPeriodicite="true";
			else
				utiliserPeriodicite="false";
		}
		
		if ((request.getParameter("typeArrondi") != null)) this.typeArrondi = request.getParameter("typeArrondi");
		if ((request.getParameter("prixPeriode1") != null)) this.prixPeriode1 = request.getParameter("prixPeriode1");
		if ((request.getParameter("prixPeriode2") != null)) this.prixPeriode2 = request.getParameter("prixPeriode2");
		if ((request.getParameter("prixPeriode3") != null)) this.prixPeriode3 = request.getParameter("prixPeriode3");
		if ((request.getParameter("prixPeriode4") != null)) this.prixPeriode4 = request.getParameter("prixPeriode4");
		if ((request.getParameter("prixPeriode5") != null)) this.prixPeriode5 = request.getParameter("prixPeriode5");
		if ((request.getParameter("coefficientNumerique") != null) && (request.getParameter("coefficientNumerique").length()!=0 ) )
			this.coefficientNumerique = request.getParameter("coefficientNumerique");
		
		
		if (request.getParameter("sectionBareme") != null)	
			this.sectionBareme = request.getParameter("sectionBareme");


		if (request.getParameter("imputationBareme") != null)	
			this.imputationBareme = request.getParameter("imputationBareme");
		
		if (request.getParameter("listeDesTranchesDesPrix") != null)	
			this.listeDesTranchesDesPrix = request.getParameter("listeDesTranchesDesPrix");

		
		Vector  listeTranche = new Vector();
		
		if(listeDesTranchesDesPrix != null && listeDesTranchesDesPrix.length()!=0)
		{
			debug.logger.debug("////////////////////////////Systeme de prix en tranches///////////////");
			StringTokenizer st = new StringTokenizer(listeDesTranchesDesPrix,"$");
			while (st.hasMoreElements()) 
			{
				debug.logger.debug("Nouvelle tranche de Prix");
				StringTokenizer st1 = new StringTokenizer(st.nextElement().toString(),"|");
				String surfaceMin = "";
				String surfaceMax = "";
				String prix = "";
				TranchePrixBareme TPBareme = new TranchePrixBareme();
				while (st1.hasMoreElements()) 
				{					
					surfaceMin = (String)st1.nextElement();
					surfaceMax = (String)st1.nextElement();
					prix = (String)st1.nextElement();
					debug.logger.debug("Surface Min="+surfaceMin);	
					debug.logger.debug("Surface Max="+surfaceMax);
					debug.logger.debug("Prix ="+ prix);
					
					TPBareme.setSurfaceMax(surfaceMax);
					TPBareme.setSurfaceMin(surfaceMin);
					TPBareme.setPrix(prix);
					TPBareme.setIdBareme(codeBareme);
					TPBareme.setAnnee(anneeExercice);
				}				
				listeTranche.add(TPBareme);
			}
		}
		else
		{
			debug.logger.debug("Systeme de prix unitaire");
		}		
		
		debug.logger.debug("////////////////////////////////");
		debug.logger.debug("////////////////////////////////");
		debug.logger.debug("////////////////////////////////");
		debug.logger.debug(coefficientNumerique);
		
		if(choix.equalsIgnoreCase("ajouter"))
		{
			//System.out.println("Ajout d'un nouveau du bareme"+ libelle+"......");			
			RequestBareme reqBareme = new RequestBareme();
			codeBareme = String.valueOf(reqBareme.ajouterBareme(anneeExercice,libelle,typeTaxe,prixUnitaire,uniteDeTravail
					,uniteDeTemps,utiliserProrata,utiliserPeriodicite,typeArrondi
					,prixPeriode1,prixPeriode2,prixPeriode3,prixPeriode4,
					prixPeriode5, listeTranche,coefficientNumerique,
					imputationBareme, sectionBareme) );
		}
		else if(choix.equalsIgnoreCase("modifier"))
		{
			//System.out.println("Modifciation d'un bareme"+ codeBareme);			
			RequestBareme reqBareme = new RequestBareme();
			reqBareme.modifierBareme(codeBareme, anneeExercice,libelle,typeTaxe,prixUnitaire,uniteDeTravail
					,uniteDeTemps,utiliserProrata,utiliserPeriodicite,typeArrondi
					,prixPeriode1,prixPeriode2,prixPeriode3,prixPeriode4,prixPeriode5,
					listeTranche,coefficientNumerique,
					imputationBareme, sectionBareme);			
		}	
		else if(choix.equalsIgnoreCase("supprimer"))
		{
			//System.out.println("Sppreession d'un bareme"+ codeBareme);			
			RequestBareme reqBareme = new RequestBareme();
			reqBareme.supprimerBareme(codeBareme,anneeExercice);			
		}	
	}		
}


