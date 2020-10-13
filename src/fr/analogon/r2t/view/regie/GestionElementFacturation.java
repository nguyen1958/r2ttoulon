package fr.analogon.r2t.view.regie;


import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.Utilisateur;
import fr.analogon.r2t.request.DataFromBD;
import fr.analogon.r2t.request.RequestAlerte;
import fr.analogon.r2t.request.RequestElementFacturation;
import fr.analogon.r2t.request.RequestEmplacement;
import fr.analogon.r2t.request.RequestOuvrage;
import fr.analogon.r2t.request.RequestUtilisateur;
import fr.analogon.r2t.util.date.LaDate;


/**
 * Servlet de gestion des elements de Elements de facturations . 
 * Sofien CHARFI
 * Facturation et modification
 * @version 1.0
 * @since 1.0
 */
public class GestionElementFacturation extends fr.analogon.r2t.main.RacineServlet 
{
	DebuggerLog4J debug = new DebuggerLog4J();
	private String dateDebutAutorisation="";
	private String dateFinAutorisation ="";
	private String dateDernierControle = "";
	private String dateProchainControle = "";
	private String dernierePeriodeFacture="";
	DataFromBD tmp = new DataFromBD();		
	private String numRedevable = "";
	private int numeroEmplacement = 0;
	private int codeBareme = 0;	
	private String anExercice = "";	
	private String choix = "";
	private String codeType = "";
	private String codeSecteur = "";	
	private String numeroElementFacturation = "";
	private String commentaireEmpl = "";	
	private double largeur = 0;
	private double longueur = 0;	
	private double quantiteArticle = 0;	
	private double surface = 0;
	private int nombreFaceAffiche= 1;
	private String nomArticle = "";	
	private String idArticle = "";		
	private String etatArticle="";	
	private String commentaireOuvrage="";
	private String libelleImputation="";
	private String typeFacturation="";
	
	private String  typeOuvrage="";
	
	
	public void jbInit() throws Exception 
	{
		
	}

	/**
	 * Methode centrale dans laquelle on gere la creation, la modification et la
	 * suppression des éléments de facturation . Frédéric Guillaume
	 * 
	 * @since 1.0
	 */
	public  synchronized void doMain(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{	
				

		try {
			int dateCreation = (new LaDate(new java.util.Date())).getLaDateInt();			
			this.choix = request.getParameter("choix");
			this.libelleImputation = request.getParameter("libelleImputation");			
			this.numRedevable = request.getParameter("numRedevable");			
			this.numeroEmplacement = Integer.parseInt(request.getParameter("numEmplacment"));
			this.anExercice = request.getParameter("exercice").toString();
			this.numeroElementFacturation = request	.getParameter("numeroElementFacturation");
			this.typeFacturation = request.getParameter("typefacturation");
			
			//////////////////////////////////////////////////
			//Recuperation des parametres du formulaire
			//////////////////////////////////////////////////
			
			if ((request.getParameter("typeOuvrage") != null)) 
			{
				//if (typeOuvrage.equalsIgnoreCase("TLPE")  )
					//typeOuvrage ="Numerique";
				typeOuvrage =request.getParameter("typeOuvrage");
			}
			
			if ((request.getParameter("longueur") != null)
					&& (!request.getParameter("longueur").equalsIgnoreCase("")))
			{longueur = Double.parseDouble(request.getParameter("longueur"));}
			
			if ((request.getParameter("largeur") != null)
					&& (!request.getParameter("largeur").equalsIgnoreCase(""))) 
			{ largeur = Double.parseDouble(request.getParameter("largeur"));	}
			
			if ((request.getParameter("surface") != null)
					&& (!request.getParameter("surface").equalsIgnoreCase(""))) 
			{ surface = Double.parseDouble(request.getParameter("surface")); }		
			
			if ((request.getParameter("nombreFaceAffiche") != null)
					&& (!request.getParameter("nombreFaceAffiche").equalsIgnoreCase(""))) 
			{ nombreFaceAffiche = Integer.parseInt(request.getParameter("nombreFaceAffiche")); }
			
			if ((request.getParameter("quantite")) != null	
					&& (!request.getParameter("quantite").equalsIgnoreCase("")))
			{  quantiteArticle = Double.parseDouble(request.getParameter("quantite"));	}
		
			
			if ((request.getParameter("dateDebutAutorisation") != null)) 
			{	dateDebutAutorisation = request.getParameter("dateDebutAutorisation");	}
			else	dateDebutAutorisation="";
			
			if ((request.getParameter("dateFinAutorisation") != null)) 
			{		dateFinAutorisation = request.getParameter("dateFinAutorisation");	}
			else	dateFinAutorisation="";
			
			if ((request.getParameter("dateDernierControle") != null)) 
			{	dateDernierControle = request.getParameter("dateDernierControle");		}
			else	dateDernierControle="";
			
			if ((request.getParameter("dateProchainControle") != null)) 
			{	dateProchainControle = request.getParameter("dateProchainControle");	}
			else	dateProchainControle="";
			
						
			if ((request.getParameter("dernierePeriodeFacture") != null)) 
			{
				dernierePeriodeFacture = request.getParameter("dernierePeriodeFacture");										
			}			
			
			if ((request.getParameter("commentaireOuvrage") != null)) 
			{
				commentaireOuvrage = request.getParameter("commentaireOuvrage");			
			}
			else commentaireOuvrage="";				
			
			
			if ((request.getParameter("etatArticle") != null)
					&& (!request.getParameter("etatArticle").equalsIgnoreCase(""))) 
			{
				etatArticle = request.getParameter("etatArticle");				
				if (etatArticle.equalsIgnoreCase("A Facturer"))
					etatArticle="AFacturer";
				else if (etatArticle.equalsIgnoreCase("Facturé"))
						etatArticle="Facturer";
				else if (etatArticle.equalsIgnoreCase("Remboursé"))
					etatArticle="Rembourser";
				else if (etatArticle.equalsIgnoreCase("Facturé à Controler"))
						etatArticle="FacturerAControler";
				else if (etatArticle.equalsIgnoreCase("Contrôlé à Facturer"))
						etatArticle="ControlerAFacturer";
				else if (etatArticle.equalsIgnoreCase("Contrôlé Présent"))
					etatArticle="ControlerPresent";
				else if (etatArticle.equalsIgnoreCase("Contrôlé Absent"))
					etatArticle="ControlerAbsent";
				else if (etatArticle.equalsIgnoreCase("Contrôlé Alerte"))
					etatArticle="ControlerAlerte";
				else if (etatArticle.equalsIgnoreCase("Ne Plus Facturer"))
					etatArticle="NePlusFacturer";
				else if (etatArticle.equalsIgnoreCase("Ne Pas Facturer"))
					etatArticle="NePasFacturer";
			}		
					
			
			nomArticle = request.getParameter("nomarticle");			 
			if (nomArticle == null) 	{	nomArticle = "";	}			
			this.idArticle = request.getParameter("idarticle");
			if (idArticle == null) 
			{
				idArticle = "0";
			}
			
			if (commentaireEmpl == null) {
				commentaireEmpl = "";
			}
			if (request.getParameter("libelleImputation") != null  && !request.getParameter("libelleImputation").equalsIgnoreCase(""))
				libelleImputation = request.getParameter("libelleImputation");
			
			if (!request.getParameter("codeSecteur").equalsIgnoreCase(""))
				codeSecteur = request.getParameter("codeSecteur");			
			String laPeriode = request.getParameter("periode");			

			String strCodeBareme = request.getParameter("codeBareme");
			if (strCodeBareme != null)
			{
				StringTokenizer st = new StringTokenizer(strCodeBareme,"-");
				codeBareme= Integer.parseInt(st.nextToken().toString().trim());
			} 
			else {
				codeBareme = 0;
			}
		}
		catch (Exception e) 
		{
				debug.logger.fatal(e.getMessage());
		}
		
		
		//TRAITEMENT DES DONNEES (INSERTION / MODIFCIATION /SUPPRESSSION )		
		
		if(choix.equalsIgnoreCase("creer"))
		{
			debug.logger.debug("\n\n\n******************************");
			debug.logger.debug("Creation d'un nouveau ouvrage de l'utulisateur : ");
			String idUser = (String)request.getSession().getAttribute("idUtilisateur");
			debug.logger.debug("id user"+ idUser );
			RequestUtilisateur ru = new RequestUtilisateur();
			Utilisateur user = ru.getUtilisateur(idUser, "","");
			debug.logger.debug("user Name="+  user.getNom() );
			Enumeration e = request.getParameterNames();
			
			while (e.hasMoreElements()) 
			{
				String attribute = (String) e.nextElement();
				String value = (String)request.getParameter(attribute);
				debug.logger.debug( attribute+ "="+  value );			
			}
		
			debug.logger.debug("******************************\n\n");
			
			
			//Creation d'un nouveau element de facturation 
			RequestElementFacturation requestElementFacturation= new RequestElementFacturation();
			String numeroTmpEF= String.valueOf(new Date().getTime());
			this.numeroElementFacturation= requestElementFacturation
			.ajouterElementFacturation(""+numeroEmplacement, anExercice,
					"normal", numeroTmpEF,""+codeBareme);
			debug.logger.debug("Ajout de l'EF num "+numeroElementFacturation);
			
			//Creation d'un ouvrage
			RequestOuvrage reqOuvrage= new RequestOuvrage(); 
			if (nomArticle.length() == 0) nomArticle ="#vide";
			reqOuvrage.ajouterOuvrage( numeroElementFacturation, nomArticle,
					""+codeBareme, ""+longueur, ""+largeur,
					""+quantiteArticle, dateDebutAutorisation,
					dateFinAutorisation, ""+surface, 
					anExercice,"normal",0,commentaireOuvrage,typeOuvrage,""+nombreFaceAffiche);			
		}
		else if(choix.equalsIgnoreCase("modifier"))
		{
			
			debug.logger.debug("Modification de l ouvrage numero " + idArticle);			
			RequestOuvrage reqOuvrage = new RequestOuvrage();	
			if (nomArticle.length() == 0) nomArticle ="#vide";
			reqOuvrage.modifierOuvrage(idArticle,""+codeBareme,nomArticle, ""+longueur,""+ largeur,""+surface,
					""+ quantiteArticle,commentaireOuvrage,dateDebutAutorisation,dateFinAutorisation, 
					dateDernierControle,dateProchainControle,dernierePeriodeFacture,
					etatArticle,anExercice,typeOuvrage,""+nombreFaceAffiche);
			
			String nomImageASupprimer=request.getParameter("nomImageASupprimer");	
			RequestEmplacement reqEmplacement = new RequestEmplacement();
			
			if (nomImageASupprimer != null && !nomImageASupprimer.equalsIgnoreCase("null") 
					&& !nomImageASupprimer.equalsIgnoreCase("")  )
			{					
				reqEmplacement.supprimerImageEmplacementGeneral(nomImageASupprimer);				
			}
			
			
			////////////////////////////////////////////
			//Traitement de l'alerte de l'ouvrage :
			////////////////////////////////////////////
				String nomImageAlerteASupprimer="";
				if ((request.getParameter("nomImageAlerteASupprimer") != null)) 
				{
					nomImageAlerteASupprimer = request.getParameter("nomImageAlerteASupprimer");			
				}
				
				if (nomImageAlerteASupprimer != null && !nomImageAlerteASupprimer.equalsIgnoreCase("null") 
						&& !nomImageAlerteASupprimer.equalsIgnoreCase("")  )
				{					
					RequestAlerte reqAlerte = new RequestAlerte();
					reqAlerte.supprimerImageAlerte(nomImageAlerteASupprimer);				
				}
				String textAlerte="";
				String remarqueAlerte="";
				String etatAlerte="";
				String dateFinAlerte="";
				String numeroAlerte="";
				String  actionAlerte="";
				String transfertImageAlerte="";
				if (request.getParameter("numeroAlerte") != null) 
					numeroAlerte = request.getParameter("numeroAlerte");				
				
				if (request.getParameter("actionAlerte") != null)
					actionAlerte = request.getParameter("actionAlerte");
	
				if (request.getParameter("etatAlerte") != null)
					etatAlerte = request.getParameter("etatAlerte");
					
				if (request.getParameter("remarqueAlerte") != null)				
					remarqueAlerte = request.getParameter("remarqueAlerte");
				
				if (request.getParameter("dateFinAlerte") != null)					
					dateFinAlerte = request.getParameter("dateFinAlerte");
				
				if (request.getParameter("textAlerte") != null)					
					textAlerte = request.getParameter("textAlerte");			
				
				if (request.getParameter("transfertImageAlerte") != null)
					transfertImageAlerte = request.getParameter("transfertImageAlerte");			
			
				//Cas de modifciation de l'alerte
				if(actionAlerte.equalsIgnoreCase("validerAlerte"))
				{
					RequestAlerte req = new RequestAlerte();			
					req.majAlerte(Integer.parseInt(numeroAlerte), etatAlerte, remarqueAlerte
							,dateFinAlerte,textAlerte);
					if(transfertImageAlerte != null && transfertImageAlerte.equalsIgnoreCase("Ok"))
					{						
						req.transfertImageAlerteEmplacement(Integer.parseInt(numeroAlerte), 
								numeroEmplacement);				
					}
					 					
				}
				//Cas de suppresion de l'alerte 
				if(actionAlerte.equalsIgnoreCase("supprimerAlerte"))
				{			
					RequestAlerte req = new RequestAlerte();					
					req.supprimerAlerte(Integer.parseInt(numeroAlerte));							
				}		
		
		//Cas de suppresion de l'ouvrage :
		}
		else if(choix.equalsIgnoreCase("supprimer"))
		{			
			//Suppresion de l'ouvrage :
			RequestOuvrage reqOuvrage = new RequestOuvrage();
			reqOuvrage.supprimerArticle(""+idArticle);
		}	
		
		//Redirection vers la page Liste des EF
		response.sendRedirect("./entree?action=regie_liste_element_facturation.jsp" +
				"&traitement=article&choix=modifier&numEmplacement=" + numeroEmplacement );		
		
		//response.getWriter().write("<script>hisory.go(-1);</script>");		

   }	
}
