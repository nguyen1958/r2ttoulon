package fr.analogon.r2t.reclamation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.pojo.Article;
import fr.analogon.r2t.pojo.Facture;
import fr.analogon.r2t.request.RequestFacture;


/**
 * cette servlet fdonne les infos d'une facture a aprtir du numero 
 * 
 * 
 */
public class GetInfosFacture extends fr.analogon.r2t.main.RacineServlet 
{    
    /**
     * This method is overriden from the base class to handle the
     * get request. 
     */
	
     
	public synchronized void doMain(HttpServletRequest requestObj, HttpServletResponse responseObj)
                   throws IOException
   {    	 
    	 //set the content type
		
		
        responseObj.setContentType("text/xml");
        responseObj.setCharacterEncoding("utf-8");
        responseObj.setHeader("Cache-Control", "no-cache");
        
        //get the PrintWriter object to write the html page
        PrintWriter writer = responseObj.getWriter();
        
        //get parameters store into the hashmap
        HashMap paramsMap = new HashMap();
        Enumeration paramEnum = requestObj.getParameterNames();
        while(paramEnum.hasMoreElements())
        {
            String paramName = (String)(paramEnum.nextElement());
            paramsMap.put(paramName, requestObj.getParameter(paramName));
        }
        //get the author name passed
        String numeroFacture= (String)paramsMap.get("author");        
        String numeroTitre= (String)paramsMap.get("numeroTitre");    
        String anneeTitre= (String)paramsMap.get("anneeTitre");        
        String typeFacture= (String)paramsMap.get("typeFacture");
        
    
        
        //System.out.println("typeFacture="+typeFacture);
        //System.out.println("numeroTitre="+numeroTitre);
        ////System.out.println("numeroTitre "+numeroTitre);
        //System.out.println("numeroFacture "+numeroFacture);
        //creating the author bean
        //AuthorsBean authBean = new AuthorsBean();
        
        RequestFacture  req= new RequestFacture();
        Facture facture=null;
        
        if (numeroFacture != null )
        {       
          	facture= req.getFacture(numeroFacture);
        }
        else                
        	facture = req.getFacture(numeroTitre,Integer.parseInt(anneeTitre));
        
        Vector listeOuvrage= facture.getListeOuvrage();
        //nom redevable et numero redevable ,lien vers facture et lien vers client 
        
        //verfier si la facture existe 
        writer.println("<facture>");
        if ( facture !=null && facture.getIdClient()!=0 )
        {  
        	writer.println("<existe>true</existe>");
	        String numClient= String.valueOf(facture.getIdClient());        
	        String nomClient= String.valueOf(facture.getNomPrenomClient());
	        String typeTaxe= String.valueOf(facture.getTypeTaxe());
	        String etatFacture= String.valueOf(facture.getEtat());
	        String numTitre= facture.getNumeroTitre();
	        anneeTitre= facture.getAnneeTitre();
	        
	        String lienFacture= fichierDeConfiguration.getLienFichierDesFactures() +String.valueOf(facture.getNomDossier())+"/"+numeroFacture+".pdf" ;
	                
	        writer.println("<lienFacture>"+lienFacture+"</lienFacture>");
		        writer.println("<idFacture>"+facture.getIdFacture()+"</idFacture>") ;
		        writer.println("<numeroFacture>"+ facture.getNumeroFacture()+"</numeroFacture>") ;		        	
		        writer.println("<etatFacture>"+facture.getEtat()+"</etatFacture>");
		        writer.println("<nomClient>"+ nomClient+"</nomClient>");
		        writer.println("<numClient>"+numClient +"</numClient>");
		        writer.println("<typeTaxe>"+typeTaxe +"</typeTaxe>");
		        writer.println("<numTitre>"+numTitre +"</numTitre>");
		        writer.println("<anneeTitre>"+anneeTitre +"</anneeTitre>");
	   
	        
	        for (int i = 0; i < listeOuvrage.size(); i++) 
	        {
	        	Article a = (Article)listeOuvrage.elementAt(i);
	        	////System.out.println("N é ouvrage "+a.getId());
	        	////System.out.println("Nom ouvrage "+a.getNom());
	        	////System.out.println("Nom bareme "+a.getBareme());
	        	
	        	// get the author profile by quering the AuthorsBean by passing author name  
	        	if(a.getNom().startsWith("#") )
	        		a.setNom("-");
	            writer.println("<ouvrage>");
	            writer.println("<numeroOuvrage>" +a.getId() + "</numeroOuvrage>");
	            writer.println("<nomOuvrage>" +a.getNom() + "</nomOuvrage>");
	            writer.println("<nomBareme>" +a.getBareme() + "</nomBareme>");	            
	            writer.println("</ouvrage>");
			}	       
        }
        else
        {
        	writer.println("<existe>false</existe>");
        }
        writer.println("</facture>");
        writer.close();
       
        //close the write
        
                           
    }        
}