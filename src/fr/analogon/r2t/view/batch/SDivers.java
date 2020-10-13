package fr.analogon.r2t.view.batch;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.main.ConfVariable;

/** Servelt les batchs divers */
public class SDivers extends fr.analogon.r2t.main.RacineServlet 
{
	
	private HttpServletRequest req;
	private String choix;
	public static String CHOIXBASCULEDESEF = "basculerLesElementsDeFacturation";
	private String basculeElementFacturationFait = "false";
	private HttpServletRequest request;	
	
	public void init() throws ServletException 
	{		
	}
	
	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{		
		this.request =request;			
		RecuperationParamHtml(request);
		String compChaineInterro = "";
		String pageDeSortie = "";
		try {
			if (choix.equalsIgnoreCase(SDivers.CHOIXBASCULEDESEF)) 
			{
				//basculeDesElementsDeFacturation();
				basculeElementFacturationFait = "true";
				/*
				compChaineInterro = compChaineInterro
						+ "anneeOrigineBasculeEF=" + anneeOrigineBasculeEF
						+ "&&basculeElementFacturationFait="
						+ basculeElementFacturationFait;
				*/
				pageDeSortie = ConfVariable.pageJSPBatchDivers;
			} else if (choix.equalsIgnoreCase("remplacerLeRedevable")) {
				//remplacerLesRedevables();
				compChaineInterro = "";
				pageDeSortie = "batch_divers_remplace_redevable.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
			javax.servlet.ServletException jse = new javax.servlet.ServletException(
					"Exception dans SJourneaux", e);
			throw jse;
		}

		//this.allerVersJSP(response, pageDeSortie, compChaineInterro);
	}

	/** Recuperation des parametres html */
	private void RecuperationParamHtml(HttpServletRequest request) {
		this.req = request;
		this.choix = req.getParameter("choix");
		//anneeOrigineBasculeEF = req.getParameter("anneeOrigineBasculeEF");
	}	
}




