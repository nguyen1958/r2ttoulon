package fr.analogon.r2t.facturation.pleiade;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.Utilitaire.ReglerCheminSelonOs;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.pojo.BatchFilien;
import fr.analogon.r2t.pojo.Emplacement;
import fr.analogon.r2t.pojo.Facture;
import fr.analogon.r2t.request.RequestFacture;
import fr.analogon.r2t.request.RequestFilien;


/**	-----------------------------------------------------------------------<br>
* @author 	Sofien CHARFI
* 20/09/2008	16h10<br>
*-----------------------------------------------------------------------<br>
*Classe qui permet de creer un fichier Filien
*
*-----------------------------------------------------------------------<br>
*/
public class CreerFichierFilien
{
	int nbreFacture =0;
	
	
	public int getNbreFacture() {
		return nbreFacture;
	}

	public void setNbreFacture(int nbreFacture) {
		this.nbreFacture = nbreFacture;
	}

	FichierDeConfiguration  fichierDeConfiguration = InitialisationConnexionLectureConfiguration.fichierDeConfiguration;
	RequestFacture requestFacture = new RequestFacture();
	
	public  CreerFichierFilien(int numeroBatchFilien,String codenaturejuridique,
			String idNatureJur, String nomNatureJur, boolean tous) 
	{	
		
		String nomFichier="";
		//Paul 16/10/2019 upload facture on serveur
		//paul modif 12/10/2020
		//SFtpClient sftp=new SFtpClient();
		try
		{
			DebuggerLog4J.logger.debug("Creation du fichier Filien.......................");
			//paul modif 12/10/2020
			//sftp.connect();
			RequestFilien  requestFilien  = new  RequestFilien(); 
			RequestFilien reqbatch = new RequestFilien();
			RequestFacture requestFacture = new RequestFacture();
			BatchFilien batchFilien = reqbatch.getBatchFilien(numeroBatchFilien);
		
			String jour = String.valueOf(new Date().getDate());
			String mois = String.valueOf(new Date().getMonth());
			String annee = String.valueOf(new Date().getYear()+1900);
			String heure = String.valueOf(new Date().getHours());
			String minute = String.valueOf(new Date().getMinutes());
			if ( Integer.parseInt(heure) <10 ) heure = "0"+heure;
			if ( Integer.parseInt(minute) <10 ) minute = "0"+minute;
			if ( Integer.parseInt(jour) <10 ) jour = "0"+jour;
			if ( Integer.parseInt(mois) <10 ) mois = "0"+mois;
			
			String type = batchFilien.getType();
			String anneeFilien = batchFilien.getAnnee(); 
			String dateLancement = batchFilien.getDateLancement();
			dateLancement = dateLancement.replaceAll("/", "");
			String cheminFichierFilien= fichierDeConfiguration.getCheminFichierFilien();
			
			nomFichier=  "filien_"+nomNatureJur+"_"+numeroBatchFilien+"_"+dateLancement+".txt";
			if( type.equalsIgnoreCase("t"))
				type ="trimestrielle";
			else if( type.equalsIgnoreCase("a"))
				type ="annuelle";
			String cheminFinale = cheminFichierFilien+"/"+annee+"/"+type+"/"+numeroBatchFilien;
			
			String dossier1 = cheminFichierFilien+"/"+annee;
			File newRepertoire1 = new File(dossier1);		
			newRepertoire1.mkdir();
			
			String dossier2 = cheminFichierFilien+"/"+annee+"/"+type;
			File newRepertoire2 = new File(dossier2);		
			newRepertoire2.mkdir();
			
			String dossier3 = cheminFichierFilien+"/"+annee+"/"+type+"/"+numeroBatchFilien;
			File newRepertoire3 = new File(dossier3);		
			newRepertoire3.mkdir();
			//System.out.println("chemin="+chemin);
			FileWriter fw = new FileWriter( cheminFinale +"/"+nomFichier);
		    BufferedWriter output = new BufferedWriter(fw);		    
		    //Recuperation des donn??es a parir du nuemero de batch		    
		    Vector res =requestFilien.getFichierFilien(numeroBatchFilien,codenaturejuridique,idNatureJur,tous);
		    DebuggerLog4J.logger.debug("Nombre de facture"+res.size());
		    if (res.size()>0 ) nbreFacture= res.size() -1;
		    //test 
		   
		    //Creation du fichier filien: 
		    //Pour le premier trimestre  commencer                10R10001
		    //Deuxi??me trimestre                                  10R15001
		    //Troisi??me trimestre                                 10R20001
		    //Quatri??me trimestre                                 10R25001
		    //Annuelle 			                                  10R30001
		    int trimestre = GestionDate.getTrimestreCourant();
		    int index = 00000;
		    int indexParNatureJuridque = 00000;
		    //l'index depend de la nature juridiue
		    if (idNatureJur.startsWith("11"))
	    	{
		    	indexParNatureJuridque= 1100000;
	    	}
		    else if (idNatureJur.startsWith("12"))
		    {
		    	indexParNatureJuridque= 1200000;
		    }
		    else if (idNatureJur.startsWith("21"))
		    {
		    	indexParNatureJuridque= 2100000;
		    }
		    else if (idNatureJur.startsWith("22"))
		    {
		    	indexParNatureJuridque= 2200000;
		    }
		    else if (idNatureJur.startsWith("23"))
		    {
		    	indexParNatureJuridque= 2300000;
		    }
		    
		    if( type.equalsIgnoreCase("trimestrielle"))
		    {
		    	if(trimestre == 1)       index = indexParNatureJuridque + 10001;
			    else if(trimestre == 2)  index = indexParNatureJuridque + 15001;
			    else if(trimestre == 3)  index = indexParNatureJuridque + 20001;
			    else if(trimestre == 4)  index = indexParNatureJuridque + 25001;
		    }
			else if( type.equalsIgnoreCase("annuelle"))
				index = indexParNatureJuridque + 30001 ;
		    
		    
		    for (int i = 0; i < res.size(); i++) 
		    {
		    	String ligne = (String)res.elementAt(i);
		    	StringTokenizer st = new StringTokenizer(ligne,";;;");
		    	//DebuggerLog4J.logger.debug("LIGNE="+ligne);
		    	//String index = requestFilien.getIndexFilien(anneeFilien);		    	
		    	//int premierLigne = -1 ;
		    	if (i >1 ) 	index = index +1;
		    	String numeroFacture ="";
		    	
		    	while (st.hasMoreElements()) 
		    	{
					String element = (String) st.nextElement();					
					//DebuggerLog4J.logger.debug(element);					
					if (element.startsWith("/01/"))
					{
						element = element.replaceAll(" ", "");
						element = element + index;
					}					
					else if (element.startsWith("/13/"))
					{
						element = element.replaceAll(" ", "");
						element = element +"0" +index;
					}
					else if (element.startsWith("/21/"))
					{
						element = element.replaceAll(" ", "");
						numeroFacture = element.substring(4);
						//DebuggerLog4J.logger.debug(numeroFacture);
					}
					else if (element.startsWith("/22/"))
					{						
						if (numeroFacture != "")
						{							
							Emplacement emplacement = requestFacture.getEmplacementFromFacture(numeroFacture);
							element = emplacement.getLibelleProfeesion();
							element = "/22/"+element; 
						}
					}
					else if (element.startsWith("/26/"))
					{
						element = element.replaceAll(" ", "");
						StringTokenizer sst = new StringTokenizer(element, "-");
						element = sst.nextToken();						
					}
					//Paul 16/10/2019
					//Uploader la facture sur le serveur
					//Cr??er le lien vers la facture uploaded
					else if (element.startsWith("/263/"))
					{
						//paul modif 12/10/2020
						DebuggerLog4J.logger.debug("263--requesFacture");
						Facture facture=requestFacture.getFacture(numeroFacture);
						DebuggerLog4J.logger.debug("263--requesFacture--OK");
						/*
						String pathSource=fichierDeConfiguration.getCheminFactures()+"/"+facture.getNomDossier()+"/"+facture.getNumeroFacture()+".pdf";
						DebuggerLog4J.logger.debug("263--uploadFile");
						String pathTarget=sftp.uploadFile(pathSource,facture);
						DebuggerLog4J.logger.debug("263--uploadFile--OK");
						element="/263/"+pathTarget;
						*/
						String pathFacture=ReglerCheminSelonOs.reglerCheminSelonOS(fichierDeConfiguration.getDossierFacture()+"/factures/"+facture.getNomDossier()+"/"+facture.getNumeroFacture()+".pdf");
						DebuggerLog4J.logger.debug("263--path:"+pathFacture);
						element="/263/"+pathFacture;
					}
					//Paul evolution eCadre budgetaire
					//balise /54/ est supprim??e
					/*
					else if (element.startsWith("/54/"))
					{
						String dernierBloc = this.getDernierBloc(element); 
						element = element.substring(0, element.indexOf(dernierBloc) + dernierBloc.length());
						if ( dernierBloc.length() == 3 )
							element = element + ajouterEspace(5) ;							
						else if ( dernierBloc.length() == 4 )
							element = element + ajouterEspace(4) ;
						else if ( dernierBloc.length() == 5 )
							element = element + ajouterEspace(3) ;
													
						element = element + "60014" + ajouterEspace(31) + "R1" ;						
					
					 */	
					//le montant de la facture sur 9 chiffre,XXXXXXXXX.YY
					else if (element.startsWith("/66/"))
					{	
						element = element.replaceAll("/66/", "");
						element = element.replace(".", ",");
						int nbreChifreAvantVirgule =element.indexOf(",");
						for (int k = 0; k < 9 - nbreChifreAvantVirgule ; k++) 
						{
							element = "0"+element;			
						}
						element = "/66/" + element;
					}
					element =  element.trim();
					output.write(element);					
					output.write("\n");				
				}		       	
		    	
			}
		    //output.write("**Ce fichier contient des informations sur le batch Numero "+numeroBatch+".\n");
		    output.write("/##/");		    
		    output.flush();
		    output.close();
		    //paul modif 12/10/2020
		    //sftp.disconnect();
			
		}		
		catch (Exception e) 
		{
			DebuggerLog4J.logger.fatal("Impossible de cr??er le fichier "+nomFichier+" \n..............[Erreur]"
					+ e.getMessage() +  e.getMessage() + e.getCause());	
		}
		
	}
	
	public String ajouterEspace(int nombreEspace)
	{
		String res ="";
		for (int i = 0; i < nombreEspace; i++) 
		{
			res = res + " ";
		}
		return res ;		
	}
	
	public String getDernierBloc(String bloc) 
	{				
		StringTokenizer st = new StringTokenizer(bloc," ");
		String dernierBloc ="";
		while (st.hasMoreElements()) 
		{
			dernierBloc = (String) st.nextElement();				
		}				
		//System.out.println("lastElement="+dernierBloc.length());
		return dernierBloc; 
	}

}// Fin de la classe
