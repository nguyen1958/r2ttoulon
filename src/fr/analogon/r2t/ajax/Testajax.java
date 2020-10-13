package fr.analogon.r2t.ajax;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.facturation.CreerFactures;
import fr.analogon.r2t.pojo.Facture;
import fr.analogon.r2t.request.RequestFacture;
import fr.analogon.r2t.request.RequestImputation;

public class Testajax extends  fr.analogon.r2t.main.RacineServlet 
{  
     
	public synchronized void doMain(HttpServletRequest requestObj, HttpServletResponse responseObj)                throws IOException
    {   	 
	    	System.out.println("appel ajax");
	    	try 
			{
				int numeroBatch = 2740;
				int numeroFacture = 2011000014;
				CreerFactures cf =new CreerFactures(numeroBatch,"test");
				RequestFacture rq = new RequestFacture();
				cf.LancerFactures(numeroBatch,  false);
				Facture facture = rq.getFacture(""+numeroFacture);
				System.out.println(facture.getNumeroFacture());
				
				cf.CreationFactures(
						numeroFacture,
						210,		//numéro chrono non défini
						numeroBatch, 
						facture.getTypeTaxe(), 
						facture.getIdClient(), 
						Integer.parseInt(facture.getNumeroTitre()), "relance");

				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}

		   	   
	 }        
}
	
