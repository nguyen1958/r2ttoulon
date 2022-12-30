package fr.analogon.r2t.view.regie;


import java.io.IOException;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.pojo.Facture;
import fr.analogon.r2t.pojo.Imputation;
import fr.analogon.r2t.pojo.LigneIdoss;
import fr.analogon.r2t.pojo.Payement;
import fr.analogon.r2t.request.RequestCreationFactures;
import fr.analogon.r2t.request.RequestFacture;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.request.RequestPayement;

/**
 * Servlet de gestion des elements de Elements de facturations . 
 * Sofien CHARFI
 * 
 * @version 1.0
 * @since 1.0
 */
public  class GestionPayement extends fr.analogon.r2t.main.RacineServlet 
{
	DebuggerLog4J debug = new DebuggerLog4J();
	private String listeNumeroFacture="";
	private String nombreDeFacturePayee="";
	private String typePayement="";
	private String montantAPayer="";
	private String paiementMarche="";
	private String montantTotalFactures="";
	private String numeroCheque="";
	private String numeroQuittance="";
	private String idBanque="";
	private String nomMarche= "";
	String idTypeDeTaxe="0";
	//Paul carte bancaire
	private String numeroTransaction="";
	
	
	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		try 
		{
			String datePayement ="";
			String choix = request.getParameter("choix");
			paiementMarche=request.getParameter("paiementMarche");
			debug.logger.debug("paiementMarche="+paiementMarche);
			///Gestion du paiement des marches sans abonné  
			
			
			if(paiementMarche !=null && paiementMarche.equalsIgnoreCase("true") )
			{
				debug.logger.debug("Paiement de type marche.... ");
				montantAPayer = request.getParameter("montantPayement");
				montantTotalFactures =montantAPayer;
				typePayement = request.getParameter("typePayementMarche");
				numeroQuittance =  request.getParameter("numeroQuittance");
				nomMarche=  request.getParameter("nomMarche");
				idTypeDeTaxe= nomMarche.substring(0,nomMarche.indexOf("---"));
				nombreDeFacturePayee = "0";
				idBanque = "0";
				numeroCheque= "0";
			}
			
			
			//System.out.println("request.numeroQuittance="+request.getParameter("numeroQuittance"));
			
			if ((request.getParameter("datePayement") != null)
					&& (!request.getParameter("datePayement").equalsIgnoreCase(""))) 
			{ datePayement = request.getParameter("datePayement");	}
			
			
			if ((request.getParameter("listeNumeroFacture") != null)
					&& (!request.getParameter("listeNumeroFacture").equalsIgnoreCase(""))) 
			{ listeNumeroFacture = request.getParameter("listeNumeroFacture");	}
			
			if ((request.getParameter("typePayement") != null)
					&& (!request.getParameter("typePayement").equalsIgnoreCase(""))) 
			{ typePayement = request.getParameter("typePayement");	}
			
			if ((request.getParameter("montantAPayer") != null)
					&& (!request.getParameter("montantAPayer").equalsIgnoreCase(""))) 
			{ montantAPayer = request.getParameter("montantAPayer");	}
			
			
			
			if ((request.getParameter("montantTotalFactures") != null)
					&& (!request.getParameter("montantTotalFactures").equalsIgnoreCase(""))) 
			{ montantTotalFactures = request.getParameter("montantTotalFactures");	}
			
			if (request.getParameter("numeroQuittance") != null)
			{ numeroQuittance = request.getParameter("numeroQuittance");	}
			
			if ((request.getParameter("numeroCheque") != null)
					&& (!request.getParameter("numeroCheque").equalsIgnoreCase(""))) 
			{ numeroCheque = request.getParameter("numeroCheque");	}
		
			if ((request.getParameter("numeroTransaction") != null)
					&& (!request.getParameter("numeroTransaction").equalsIgnoreCase(""))) 
			{ numeroTransaction = request.getParameter("numeroTransaction");	}

			
			if ((request.getParameter("idBanque") != null)
					&& (!request.getParameter("idBanque").equalsIgnoreCase(""))) 
			{ idBanque = request.getParameter("idBanque");	}
			
			if ((request.getParameter("nombreFacture") != null)
					&& (!request.getParameter("nombreFacture").equalsIgnoreCase(""))) 
			{ nombreDeFacturePayee = request.getParameter("nombreFacture");	}
			
			//System.out.println("numeroQuittance="+numeroQuittance+"|numeroCheque="+numeroCheque+"|numeroTransaction="+numeroTransaction);
			
			RequestPayement requestPayement = new RequestPayement();
			
			DecimalFormat df = new DecimalFormat("0.00");
			montantAPayer= df.format(Double.valueOf(montantAPayer).doubleValue());
			montantAPayer = montantAPayer.replaceAll(",", ".");
			
			//System.out.println("montantAPayer="+montantAPayer);
			//System.out.println("montantTotalFactures="+montantTotalFactures);			 
			String diffd ="0.00";			
			double diff= Double.valueOf(montantAPayer) - Double.valueOf(montantTotalFactures) ;
			boolean excedant = false;
			if(diff > 0)
			{
				
				diffd = ""+FonctionCommun.arrondir(diff);
				excedant =true;
				//chaque ligne de payement = old solde de la facture
			}
			
			////CAS DE PAIEMENT DES MARCHES/////////// 
			if(paiementMarche != null && paiementMarche.equalsIgnoreCase("true") )
			{
				int idPayement =0;
				if (choix.equalsIgnoreCase("modifier"))
				{
					//maj du paiement marche:
					idPayement = Integer.parseInt(request.getParameter("idPayement"));
					requestPayement.modifierPayement(datePayement, typePayement, 
							idBanque, numeroCheque, montantAPayer, numeroQuittance,Integer.parseInt(nombreDeFacturePayee),
							diffd,idTypeDeTaxe,idPayement,numeroTransaction);
					response.sendRedirect("./entree?action=empl_gestion_paiementMarche.jsp&paiementMarche=true&choix=modifier&idPayement=" + idPayement );
					
				}
				
				else if (choix.equalsIgnoreCase("supprimer"))
				{
					//annuler du paiement marche:
					idPayement = Integer.parseInt(request.getParameter("idPayement"));
					
					RequestFacture requestFacture = new RequestFacture();
					RequestPayement reqPayement = new RequestPayement();
					Vector listFacture =  reqPayement.getListeDesFactureFromPayement(""+idPayement);
					if (listFacture.size() > 0)
					{
						try 
						{
							String  numeroFacture = (String)listFacture.elementAt(0);
							Facture facture = requestFacture.getFactureFromNumeroFacture(numeroFacture);
							int idFacture =  facture.getIdFacture();
							requestFacture.deleteFacture(""+idFacture);
							
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
						}
						
					}
					requestPayement.supprimerLignePayement(request.getParameter("idPayement"));
					requestPayement.supprimerPayement(request.getParameter("idPayement"));
					response.sendRedirect("./entree?action=empl_recherche_paiement.jsp" );
					
					
				}
				
				
				else if (choix.equalsIgnoreCase("ajouter"))
				{	
					//Create facture factice pour pivot pour les rapports 
					int numeroFacture =0;
					
					RequestCreationFactures requestCreationFactures = new RequestCreationFactures();
					RequestImputation requestImputation = new RequestImputation();
					RequestFacture requestFacture = new RequestFacture();
					String anneeCourante = GestionDate.getAnneeCourante();
					String dateDuJour = GestionDate.getDateAujourdhuiString();
					
					numeroFacture = requestCreationFactures.genererNumeroFacture(anneeCourante);
					
					Imputation imputation = requestImputation.getImputation(idTypeDeTaxe, anneeCourante);
					
					String typetaxe= imputation.getLibelle();
					requestCreationFactures.ajouterFacture(numeroFacture, typetaxe, 0,
							dateDuJour, anneeCourante, Double.valueOf(montantAPayer).doubleValue(), 0, 
							"0", "", "factice", "");
					Facture facture = requestFacture.getFactureFromNumeroFacture(""+numeroFacture);
					
					
					//ajout d'un nouevau paiement marche
					idPayement = requestPayement.ajouterPayement(datePayement, typePayement, 
							idBanque, numeroCheque, montantAPayer, numeroQuittance,Integer.parseInt(nombreDeFacturePayee),
							diffd,idTypeDeTaxe,numeroTransaction);			
					
					//paiement marche 
					requestPayement.ajouterLignePayement(""+idPayement, ""+facture.getIdFacture(), montantAPayer);
					
					response.sendRedirect("./entree?action=empl_gestion_paiementMarche.jsp&paiementMarche=true&choix=modifier&idPayement=" + idPayement );
				}
				
				
			 //CAS DE PAIEMENT DIFFERENT DES MARCHES///////////	
			}
			else //paul 07072020
			{
				//idTypeDeTaxe ="";
				//paiement autre que marche
				int idPayement = requestPayement.ajouterPayement(datePayement, typePayement, 
						idBanque, numeroCheque, montantAPayer, numeroQuittance,Integer.parseInt(nombreDeFacturePayee),
						diffd,idTypeDeTaxe,numeroTransaction);			
				StringTokenizer st = new StringTokenizer(listeNumeroFacture,";");
				while (st.hasMoreElements()) 
				{
					String  numeroFacture= ""+st.nextElement();				
					RequestFacture requestFacture= new RequestFacture();
					Facture facture = requestFacture.getFactureFromNumeroFacture(numeroFacture);
					String idFacture = ""+facture.getIdFacture();				
					String soldeFacture = facture.getSoldeFacture();
					String montantPayement="0";
					
					
					if(nombreDeFacturePayee.equalsIgnoreCase("1"))
					{
						//une seul facture est payé
						if (excedant)
							montantPayement =soldeFacture;
						else
							montantPayement =montantAPayer;
					}
					else
					{
						//plusieur facture pour le meme payement
						montantPayement = soldeFacture;										
					}					
					
					montantPayement= df.format(Double.valueOf(montantPayement).doubleValue());
					montantPayement = montantPayement.replaceAll(",", ".");				
					requestPayement.ajouterLignePayement(""+idPayement, idFacture, montantPayement);
					double newSoldeFacture =Double.parseDouble(soldeFacture) - Double.parseDouble(montantPayement);
					if (newSoldeFacture <0 ) newSoldeFacture = 0;
					String newSoldeFactureS= df.format(Double.valueOf(newSoldeFacture).doubleValue());
					newSoldeFactureS = newSoldeFactureS.replaceAll(",", ".");	
					requestFacture.updateFacture(idFacture , ""+newSoldeFactureS );
				}
				//Redirection vers la page Liste des EF
				response.sendRedirect("./entree?action=gestionPayement.jsp&idPayement=" + idPayement );
				
			}
			
			
	}
	catch (Exception e) 
	{
			// TODO: handle exception
	}
 }
}
