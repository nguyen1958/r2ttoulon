package fr.analogon.r2t.batch.facture;
import java.util.Date;
import java.util.StringTokenizer;

import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.facturation.CreerFactures;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.pojo.Article;
import fr.analogon.r2t.pojo.Emplacement;
import fr.analogon.r2t.pojo.Rue;
import fr.analogon.r2t.request.RequestCreationFactures;
import fr.analogon.r2t.request.RequestElementFacturation;
import fr.analogon.r2t.request.RequestEmplacement;
import fr.analogon.r2t.request.RequestOuvrage;
import fr.analogon.r2t.request.RequestRefacturation;
import fr.analogon.r2t.request.RequestRue;

/**
 * Permet de genenr le refacturation  
 *  Apercu de la refacturation  . Sofien CHARFI
 * 
 * @since 2
 * @version 2
 */
public final class Refacturation 
{
	String anneeExercice=" ";
	//String anneeFacturationTLPE="";
	String adresse2Emplacement=" ";
	String adresse3Emplacement=" ";
	
	String typeTaxe=" ";
	String numRedevable=" ";
	String numeroFacture=" ";
	int nombreEmplacement;
	int idBatchTraiement;
	String lienFacture="vide.pdf";
	FichierDeConfiguration  fichierDeConfiguration = InitialisationConnexionLectureConfiguration.fichierDeConfiguration;
	
	public Refacturation(String requestParameter,String etapeFacturation) 
	{
		
	  String timeTransaction=""+new Date().getTime();
	  
	  requestParameter=  requestParameter.replaceAll("%2F", "/");
	  requestParameter=  requestParameter.replaceAll("%0D", "");
	  requestParameter=  requestParameter.replaceAll("%0A", "");
	  requestParameter=  requestParameter.replaceAll("%23", "#");
	  requestParameter=  requestParameter.replaceAll("%27", "'");	  
	  requestParameter=  requestParameter.replaceAll("%28", "(");
	  requestParameter=  requestParameter.replaceAll("%29", ")");
	  requestParameter=  requestParameter.replaceAll("  ", " ");
	  requestParameter=  requestParameter.replaceFirst("Parameter","");
	  requestParameter=  requestParameter.replace('+', '%');
	  requestParameter=  requestParameter.replaceAll("%", " ");
	  requestParameter=  requestParameter.replaceAll(" 20","###");	
	  requestParameter=  requestParameter.replaceAll("###"," ");
	  requestParameter=  requestParameter.replaceAll("'","APOSTROPHE");
	  //requestParameter=  requestParameter.replaceAll("E8", "è");
	  //requestParameter=  requestParameter.replaceAll("E9", "é");
	  
	  System.out.println("///////////////////////////////////////////");
	  System.out.println("Request = " + requestParameter);
	  System.out.println("///////////////////////////////////////////");
	  StringTokenizer st = new StringTokenizer(requestParameter,"&");
	  Emplacement emplacement = new Emplacement(); ;
	  RequestEmplacement reqEmplacement = new RequestEmplacement();
	  RequestOuvrage reqOuvrage= new RequestOuvrage();
	  RequestElementFacturation requestElementFacturation= new RequestElementFacturation(); 
	  Article article=new Article();
	  
	  long time= new Date().getTime();	  
	  String numeroTmpArticle= String.valueOf(new Date().getTime());
	  boolean newEmpalcment=true;
	  boolean newOuvrage=false;
	 
	  while (st.hasMoreElements()) 
	  {		  
	    	String element=(String)st.nextElement();
	    	System.out.println("element="+element);
	    	
	    	String nomElement= this.getElement(element);
	    	
	    	
	    	String valeurElement= this.getValeur(element);
	    	
	    	if(nomElement.equalsIgnoreCase("anneeExercice")) anneeExercice= valeurElement;
	    	//if(nomElement.equalsIgnoreCase("anneeFacturationTLPE"))		this.anneeFacturationTLPE= valeurElement;
	    	if(nomElement.equalsIgnoreCase("typeTaxe")) typeTaxe= valeurElement;
	    	if(nomElement.equalsIgnoreCase("numRedevable")) numRedevable= valeurElement;
	    	if(nomElement.equalsIgnoreCase("idFactue")) 
	    	{
	    		 numeroFacture= valeurElement;
	    		 if(numeroFacture.equalsIgnoreCase("idFactue")) numeroFacture="-";
	    	}
	    	if(element.contains("*newEmplacement*"))
	    	{
	    		System.out.println("*newEmplacement*");
	    		 if(newOuvrage)
	    		{
	    		    	numeroTmpArticle= ""+new Date().getTime();
	    				String numeroElementFacturation= requestElementFacturation
	    				.ajouterElementFacturation(emplacement.getId(), anneeExercice,
	    						etapeFacturation, numeroTmpArticle,article.getCodeBareme());
	    				reqOuvrage.ajouterOuvrage( article.getTypeOuvrage(),article.getNombreFaceAffiche(),numeroElementFacturation,
	    						article.getCodeBareme(), ""+article.getLongueur(), ""+article.getLargeur(),
	    						""+article.getQuantite_article(), article.getDateDebutAutorisation(),
	    						article.getDateFinAutorisation(), ""+article.getSurface(), 
	    						article.getAnExercice(),etapeFacturation,""+numeroTmpArticle,
	    						article.getDernierePeriodeFacture(),timeTransaction , article.getNom());
	    				newOuvrage=false;
	    		}
	    		System.out.println("*********************************************************************");
	    		System.out.println("*************************New Emplacement*****************************");
	    		System.out.println("*********************************************************************");
	    		time= new Date().getTime();
	    		newEmpalcment =true;	    		
	    	}
	    	else if(element.contains("*newOuvrage*"))
	    	{
	    		System.out.println("*newOuvrage*");
	    		if(newEmpalcment)
	    		{
	    			newEmpalcment =false;
	    			String numeroTmpEmplacement= ""+new Date().getTime();
	    			//paul il manque le passage des paramètres suivants :
	    			//dateDebutActivte,codepostal et ville
	    			String idEmplacment= reqEmplacement.ajouterEmplacment(
	    					//emplacement.getDateDebutActivite().getLaDateString(),
	    					"",
	    					emplacement.getCodeVoie(),
	    					emplacement.getNumRue(), 
	    					emplacement.getAdresse1(), 
		    				numRedevable,typeTaxe, 
		    				anneeExercice, etapeFacturation,numeroTmpEmplacement, 
		    				emplacement.getComplementNumeroRueEmpl() , emplacement.getAdresse2(),
		    				emplacement.getAdresse3(),"",""); //emplacement.getCodePostal(), emplacement.getVille());
	    			emplacement.setId(idEmplacment);
	    		}

	    		if(newOuvrage)
	    		{
	    			numeroTmpArticle= ""+new Date().getTime();
    				String numeroElementFacturation= requestElementFacturation
    				.ajouterElementFacturation(emplacement.getId(), anneeExercice,
    						etapeFacturation, numeroTmpArticle,article.getCodeBareme());
    				reqOuvrage.ajouterOuvrage( article.getTypeOuvrage(),article.getNombreFaceAffiche(),numeroElementFacturation,
    						article.getCodeBareme(), ""+article.getLongueur(), ""+article.getLargeur(),
    						""+article.getQuantite_article(), article.getDateDebutAutorisation(),
    						article.getDateFinAutorisation(), ""+article.getSurface(), 
    						article.getAnExercice(),etapeFacturation,""+numeroTmpArticle,
    						article.getDernierePeriodeFacture(),timeTransaction , article.getNom());
    				newOuvrage=false;
	    		}
	    		newOuvrage=true;	    		 	    		
	     	}
	    	else if(element.contains("nombreEmplacment"))
	    	{
	    		System.out.println("------------------------------------" );
	    		System.out.println(element);
	    		this.nombreEmplacement= Integer.parseInt(this.getValeur(element));
	    		System.out.println("------------------------------------" );
	    	}
	    	else 
	    	{	    			
	    		
	    			    		
	    		System.out.println("Remplissage de l'emplacement");
	    		if(element.contains("numRue_"))
	    		{
	    			emplacement.setNumRue(this.getValeur(element));	    			
	    			emplacement.setNumeroTmp(String.valueOf(time));
	    		}
	    		    		
	    		if(element.contains("complNumRue_"))
	    		{	
	    			String comp = this.getValeur(element);
	    			if(comp.contains("complNumRue")) comp="";	    			
	    			emplacement.setComplementNumeroRueEmpl(comp);
	    		}
	    		if(element.contains("codeAdresse_"))
	    		{
	    			String idRue =this.getValeur(element);
	    			RequestRue reqRue = new RequestRue();
	    			Rue rue =  reqRue.getRue(idRue);
	    			emplacement.setAdresse1( rue.getPrefixe() + " "+rue.getLiaison()+ " " + rue.getNomrue() );
	    			//paul
	    			emplacement.setCodeVoie(idRue);
	    		}
	    		
	    		if(element.contains("adresse2Emplacement_"))
	    		{
	    			//System.out.println("XXXXXXXXXXXXXXXXXXXXX"+element);	
	    			emplacement.setAdresse2(this.getValeur(element));
	    		}
	    		if(element.contains("adresse3Emplacement_"))
	    		{
	    			
	    			
	    			emplacement.setAdresse3(this.getValeur(element));
	    		}
	    		
	    	
	    		
	    		//Remplissage de l'ouvrage	    		
	    		if(element.contains("quantite"))
	    			 article.setQuantite_article(Double.valueOf(this.getValeur(element)));
	    		if(element.contains("numPeriode"))
	    			 article.setDernierePeriodeFacture(this.getValeur(element));
	    		if(element.contains("dateDebut"))
	    			 article.setDateDebutAutorisation(this.getValeur(element));
	    		if(element.contains("typeOuvrage"))
	    			 article.setTypeOuvrage(this.getValeur(element));
	    		if(element.contains("nombreFace"))
	    			 article.setNombreFaceAffiche(this.getValeur(element));
	    		if(element.contains("dateFin"))
	    		{
	    			 article.setDateFinAutorisation(this.getValeur(element));	    			
	    			// //System.out.println("geter: "+ this.getValeur(element));
	    		}
	    		if(element.contains("longueur"))
	    			 article.setLongueur(Double.valueOf(this.getValeur(element)));
	    		if(element.contains("largeur"))
	    			 article.setLargeur(Double.valueOf(this.getValeur(element)));
	    		if(element.contains("surface"))
	    			 article.setSurface(Double.valueOf(Refacturation.getValeur(element)));
	    		if(element.contains("nomOuvrage"))
	    			 article.setNom(this.getValeur(element));
	    		
	    		if(element.contains("typeBareme"))
	    		{
	    			//System.out.println("Type de bareme="+element);
	    			String ligneBareme= Refacturation.getValeur(element).trim();	    			
	    			article.setCodeBareme(Refacturation.getIdBareme(ligneBareme));	    			 
	    			//System.out.println("article.getCodeBareme="+article.getCodeBareme());
	    		}	    		 
	    		article.setAnExercice(anneeExercice); 
	    
	    	}	    	
		}//fin while
	  
	    if(newOuvrage)
		{
	    	numeroTmpArticle= ""+new Date().getTime();
			String numeroElementFacturation= requestElementFacturation
			.ajouterElementFacturation(emplacement.getId(), anneeExercice,
					etapeFacturation, numeroTmpArticle,article.getCodeBareme());
			reqOuvrage.ajouterOuvrage( article.getTypeOuvrage(),article.getNombreFaceAffiche(),numeroElementFacturation,
					article.getCodeBareme(), ""+article.getLongueur(), ""+article.getLargeur(),
					""+article.getQuantite_article(), article.getDateDebutAutorisation(),
					article.getDateFinAutorisation(), ""+article.getSurface(), 
					article.getAnExercice(),etapeFacturation,""+numeroTmpArticle,
					article.getDernierePeriodeFacture(),timeTransaction , article.getNom());
			
			newOuvrage=false;
		}
	    
	    RequestRefacturation reqRefacturation = new RequestRefacturation();	    
	    reqRefacturation.ajouterRefacturation(anneeExercice, typeTaxe, 
	    		numeroFacture, numRedevable, GestionDate.getDateAujourdhuiString(),etapeFacturation );	  
	    
	    ////////////////////////////////////////////////////
	    //Recuperation des donnees de refacturation	    
	    //Previsualisaiton de la facture et creation de la facture
	    ////////////////////////////////////////////////////
	    if(etapeFacturation.equalsIgnoreCase("preRefacturation"))
        {
	    	try 
	    	{
	    		//System.out.println("Previsualisation de la facture...");
	        	 RequestCreationFactures rcf = new RequestCreationFactures();
	        	 rcf.generFacture(GestionDate.getDateAujourdhuiString(), 
	 	    			typeTaxe, etapeFacturation, timeTransaction,GestionDate.getAnneeCourante(),
	 	    			anneeExercice);
	        	 int idBatchTraiement=rcf.getIdBatchTraitement();
	        	 rcf.validerBatch(idBatchTraiement);
	        	 //System.out.println("idBatchTraiement"+idBatchTraiement);
	        	 CreerFactures preFacture = new CreerFactures(idBatchTraiement,"normale");
	        	 
	        	 
	        	 this.lienFacture= fichierDeConfiguration.getLienFichierDesFactures()+GestionDate.getAnneeCourante()+"-"+idBatchTraiement+"/BatchNumero"+idBatchTraiement+".pdf" ;
	      	} 
	    	catch (Exception e) 
	    	{
	    		DebuggerLog4J.logger.fatal(e.getMessage());
			}        	 
        }
       	    
	}
	
	public static String getValeur(String chaine)
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
	    res = res.replaceAll("APOSTROPHE", "'");
	    res = res.replaceAll(" E9", "é");
	    res = res.replaceAll(" E8", "è");
	    //Regler les esapaces
	     
	    
		 return res ;
	}
	public static String getElement(String chaine)
	{
		String res="";
		StringTokenizer st = new StringTokenizer(chaine,"=");	  
	   	res=(String)st.nextElement();	    		 
		 return res ;
	}
	
	public static String getIdBareme(String chaine)
	{
		String res="";
		chaine = chaine.trim();
		StringTokenizer st = new StringTokenizer(chaine,"-----");	  
	   	res=(String)st.nextElement();
		return res ;
	}

	/**
	 * @return the lienFacture
	 */
	public final String getLienFacture() {
		return lienFacture;
	}
	
}




