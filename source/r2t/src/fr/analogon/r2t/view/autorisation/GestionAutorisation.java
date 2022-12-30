package fr.analogon.r2t.view.autorisation;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.pojo.Article;

import fr.analogon.r2t.request.RequestAdmin;
import fr.analogon.r2t.request.RequestAutorisation;
import fr.analogon.r2t.request.RequestElementFacturation;
import fr.analogon.r2t.request.RequestEmplacement;
import fr.analogon.r2t.request.RequestOuvrage;

/**
 * Permet de gerer les autorisation . Sofien CHARFI
 * 
 * @since 1.0
 * @version 1.3
 */

public class GestionAutorisation extends  fr.analogon.r2t.main.RacineServlet 
{ 
	private String idautorisation="";
     
	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)  throws IOException
    {	
		ServletContext sc = getServletConfig().getServletContext();	
		RequestDispatcher rd=null;
	    //autorisation autorisation = new autorisation(requestParameter,etape);
        String action=request.getParameter("choix");
        System.out.println(">>>>>> GestionAutorisation action= "+action);
        if (action.equalsIgnoreCase("creer")){
        	idautorisation=creer_autorisation(request);
        	rd= sc.getRequestDispatcher("/entree?action=empl_gestion_autorisation_modifier.jsp&choix=modifier&idautorisation="+idautorisation);
        }
        
        if (action.equalsIgnoreCase("modifier")){
        	modifier_autorisation(request);
        	rd= sc.getRequestDispatcher("/entree?action=empl_gestion_autorisation_modifier.jsp");
        }
        
        if (action.equalsIgnoreCase("supprimer")){
    		supprimer_autorisation(request);
    		rd = sc.getRequestDispatcher("/entree?action=liste_autorisation.jsp&choix=rechercher");
        }
	     
		//System.out.println("autorisation..................................[OK]");
		try {
			rd.forward( request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			debug.logger.fatal(e.getMessage());
		}	
	    
	 } 
	
	private synchronized void supprimer_autorisation(HttpServletRequest request)
	{
		Enumeration elements=request.getParameterNames();
		while(elements.hasMoreElements()){
			String parametre=(String) elements.nextElement();
			System.out.println(parametre+":"+request.getParameter(parametre));
		}
		
		String idautorisation=request.getParameter("idautorisation");
		
		RequestAutorisation reqAutorisation = new RequestAutorisation();
		reqAutorisation.supprimerAutorisation(idautorisation);

	}
	
	private synchronized void modifier_autorisation(HttpServletRequest request)
	{
		Enumeration elements=request.getParameterNames();
		while(elements.hasMoreElements()){
			String parametre=(String) elements.nextElement();
			System.out.println(parametre+":"+request.getParameter(parametre));
		}
		
		String refDossier=request.getParameter("refDossier");
		String etatAutorisation=request.getParameter("etatAutorisation");
		String idautorisation=request.getParameter("idautorisation");
		
		RequestAutorisation reqAutorisation = new RequestAutorisation();
		reqAutorisation.modifierAutorisation(idautorisation,etatAutorisation,refDossier);

	}
	
	private synchronized String creer_autorisation(HttpServletRequest request) throws UnsupportedEncodingException
	{
	    String requestParameter= URLDecoder.decode(request.getQueryString(),"windows-1252");
	    System.out.println("///////////////////////////////////////////");
	  	System.out.println("Request = " + requestParameter);
	  	System.out.println("///////////////////////////////////////////");
	  	/*
	  	StringTokenizer st = new StringTokenizer(requestParameter,"&");
	  	while (st.hasMoreElements()) 
		  {		  
		    	String element=(String)st.nextElement();
		    	System.out.println("element="+element);
		  }
		*/

	  FichierDeConfiguration  fichierDeConfiguration = InitialisationConnexionLectureConfiguration.fichierDeConfiguration;
	
	  String timeTransaction=String.valueOf(new Date().getTime());
	
	  StringTokenizer st = new StringTokenizer(requestParameter,"&");

	  RequestAutorisation reqAutorisation = new RequestAutorisation();
	  RequestEmplacement reqEmplacement = new RequestEmplacement();
	  RequestOuvrage reqOuvrage= new RequestOuvrage();
	  RequestElementFacturation requestElementFacturation= new RequestElementFacturation(); 
	  Article article=new Article();
	  
	  long time= new Date().getTime();
	  
	  String numeroTmpArticle= "";
	  boolean newEmplacement=true;
	  boolean newOuvrage=false;
	  String element="";
	  String nomElement="";
	  String valeurElement="";
	  String numeroElementFacturation;
	  String numeroTmpEmplacement;
	  String typeEmplacement="";
	  String idEmplacement="";
	  //Création autorisation
	  String etapeFacturation="autorisation";
	  String idUtilisateur = (String)request.getSession().getAttribute("idUtilisateur");	
	  String anneeExercice=request.getParameter("anneeExercice");
	  String numRedevable= request.getParameter("numRedevable");	  
	  String refDossier=request.getParameter("refDossier");	  
	  String numRue= request.getParameter("numRue");
	  String complNumRue=request.getParameter("complNumRue");
	  String adresseEmplacement= request.getParameter("adresseEmplacement");
	  String adresse2Emplacement= request.getParameter("adresse2Emplacement");
	  String adresse3Emplacement= request.getParameter("adresse3Emplacement");
	  String codeVoie=request.getParameter("codeVoie");
	  String cp=request.getParameter("cp");	  
	  String ville= request.getParameter("ville");
	  
	  
	  String idAutorisation=reqAutorisation.ajouterAutorisation(
			  String.valueOf(new Date().getTime()), 
			  numRedevable, 
			  "En cours", 
			  refDossier, 
			  GestionDate.getDateAujourdhuiString(), 
			  idUtilisateur);
	  
	  
	  while (st.hasMoreElements()) 
	  {		  
	    	element=(String)st.nextElement();	    	
	    	nomElement= this.getElement(element);	    		    	
	    	valeurElement= this.getValeur(element);	
	    	
	    	
	    		    	
	    	if(element.contains("*newEmplacement*"))
	    	{
	    		if(newOuvrage)
	    		{
	    		    	numeroTmpArticle= String.valueOf(new Date().getTime());
	    				numeroElementFacturation= requestElementFacturation
	    				.ajouterElementFacturation(idEmplacement, anneeExercice,
	    						etapeFacturation, numeroTmpArticle,article.getCodeBareme());
	    				reqOuvrage.ajouterOuvrage( article.getTypeOuvrage(),article.getNombreFaceAffiche(),numeroElementFacturation,
	    						article.getCodeBareme(), ""+article.getLongueur(), ""+article.getLargeur(),
	    						""+article.getQuantite_article(), article.getDateDebutAutorisation(),
	    						article.getDateFinAutorisation(), ""+article.getSurface(), 
	    						article.getAnExercice(),etapeFacturation,""+numeroTmpArticle,
	    						"1",timeTransaction , article.getNom());
	    				newOuvrage=false;
	    		}
	    		//System.out.println("*********************************************************************");
	    		//System.out.println("*************************New Emplacement*****************************");
	    		//System.out.println("*********************************************************************");
	    		time= new Date().getTime();
	    		newEmplacement =true;	    		
	    	}
	    	else if(element.contains("*newOuvrage*"))
	    	{
	    		if(newEmplacement)
	    		{
	    			newEmplacement =false;
	    			numeroTmpEmplacement= String.valueOf(new Date().getTime());
	    			idEmplacement= reqEmplacement.ajouterEmplacment(GestionDate.getDateAujourdhuiString(),
	    					codeVoie,numRue,adresseEmplacement, 
		    				numRedevable,typeEmplacement, 
		    				anneeExercice, etapeFacturation,numeroTmpEmplacement, 
		    				complNumRue , adresse2Emplacement,adresse3Emplacement,cp,ville);
	    			//Créer autorisation_emplacement
	    			reqAutorisation.ajouterAutorisationEmplacement(idAutorisation, idEmplacement);
	    		}

	    		if(newOuvrage)
	    		{
	    			numeroTmpArticle= String.valueOf(new Date().getTime());
    				numeroElementFacturation= requestElementFacturation
    				.ajouterElementFacturation(idEmplacement, anneeExercice,
    						etapeFacturation, numeroTmpArticle,article.getCodeBareme());
    				reqOuvrage.ajouterOuvrage( article.getTypeOuvrage(),article.getNombreFaceAffiche(),numeroElementFacturation,
    						article.getCodeBareme(), ""+article.getLongueur(), ""+article.getLargeur(),
    						""+article.getQuantite_article(), article.getDateDebutAutorisation(),
    						article.getDateFinAutorisation(), ""+article.getSurface(), 
    						article.getAnExercice(),etapeFacturation,""+numeroTmpArticle,
    						"1",timeTransaction , article.getNom());
    				newOuvrage=false;
	    		}
	    		newOuvrage=true;	    		 	    		
	     	}
	    	else 
	    	{
	    		//Remplissage de l'emplacement
	    		if(element.contains("typeTaxe"))
	    			typeEmplacement=this.getValeur(element);
	    		
	    		//Remplissage de l'ouvrage
	    		article.setTypeOuvrage("Normal"); //par défaut 
	    		if(element.contains("quantite"))
	    			 article.setQuantite_article(Double.valueOf(this.getValeur(element)));
	    		//if(element.contains("numPeriode"))
	    		//	 article.setDernierePeriodeFacture(this.getValeur(element));
	    		if(element.contains("dateDebut"))
	    			 article.setDateDebutAutorisation(this.getValeur(element));
	    		if(element.contains("typeOuvrage"))
	    			 article.setTypeOuvrage(this.getValeur(element));
	    		if(element.contains("nombreFace"))
	    			 article.setNombreFaceAffiche(this.getValeur(element));
	    		if(element.contains("dateFin"))
	    		{
	    			 article.setDateFinAutorisation(this.getValeur(element));	    			
	    		}
	    		if(element.contains("longueur"))
	    			 article.setLongueur(Double.valueOf(this.getValeur(element)));
	    		if(element.contains("largeur"))
	    			 article.setLargeur(Double.valueOf(this.getValeur(element)));
	    		if(element.contains("surface"))
	    			 article.setSurface(Double.valueOf(this.getValeur(element)));
	    		if(element.contains("nomOuvrage")){
	    			 article.setNom(this.getValeur(element).isEmpty()?"#vide":this.getValeur(element));
	    		}
	    		
	    		
	    			    		
	    		if(element.contains("typeBareme"))
	    		{
	    			String ligneBareme= this.getValeur(element).trim();	    			
	    			article.setCodeBareme(this.getIdBareme(ligneBareme));	    			 
	    		}	    		 
	    		article.setAnExercice(anneeExercice); 
	    
	    	}	    	
		}//fin while
	  
	    if(newOuvrage)
		{
	    	numeroTmpArticle= String.valueOf(new Date().getTime());
			numeroElementFacturation= requestElementFacturation
			.ajouterElementFacturation(idEmplacement, anneeExercice,
					etapeFacturation, numeroTmpArticle,article.getCodeBareme());
			reqOuvrage.ajouterOuvrage( article.getTypeOuvrage(),article.getNombreFaceAffiche(),numeroElementFacturation,
					article.getCodeBareme(), ""+article.getLongueur(), ""+article.getLargeur(),
					""+article.getQuantite_article(), article.getDateDebutAutorisation(),
					article.getDateFinAutorisation(), ""+article.getSurface(), 
					article.getAnExercice(),etapeFacturation,""+numeroTmpArticle,
					"1",timeTransaction , article.getNom());
			
			newOuvrage=false;
		}
	    //Créer historique action
	    new RequestAdmin().InsertAction("Ajouter une Autorisation (id= " + idAutorisation + ") "
										+ " pour le numRedevable " + numRedevable,
				GestionDate.getDateTime());
	    
	    return idAutorisation;
}
	
private String getValeur(String chaine)
{
	String res="";
	StringTokenizer st = new StringTokenizer(chaine,"=");	
	int i = 0;
    while (st.hasMoreTokens()) 
    {
    	res=(String)st.nextElement();
    	i++;
    }
    if (i==1) res=""; 
   
	return res ;
}

private  String getElement(String chaine)
{
	String res="";
	StringTokenizer st = new StringTokenizer(chaine,"=");	  
   	res=(String)st.nextElement();	    		 
	return res ;
}

private String getIdBareme(String chaine)
{
	String res="";
	chaine = chaine.trim();
	StringTokenizer st = new StringTokenizer(chaine,"-----");	  
   	res=(String)st.nextElement();
	return res ;
}
	
	
}
	
