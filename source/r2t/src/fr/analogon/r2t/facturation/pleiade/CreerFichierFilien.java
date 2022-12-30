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
import fr.analogon.r2t.facturation.CreerFactures;
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
			System.out.println("Creation du fichier Filien.......................");
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
			DebuggerLog4J.logger.debug("cheminFichierFilien : "+cheminFichierFilien);
			nomFichier=  "filien_"+nomNatureJur+"_"+numeroBatchFilien+"_"+dateLancement+".txt";
			if( type.equalsIgnoreCase("t"))
				type ="trimestrielle";
			else if( type.equalsIgnoreCase("a"))
				type ="annuelle";
			String cheminFinale = cheminFichierFilien+"/"+annee+"/"+type+"/"+numeroBatchFilien;
			DebuggerLog4J.logger.debug("cheminFinale : "+cheminFinale);
			String dossier1 = cheminFichierFilien+"/"+annee;
			File newRepertoire1 = new File(dossier1);		
			DebuggerLog4J.logger.debug("dossier1="+dossier1+" créé:"+newRepertoire1.mkdir());
			
			String dossier2 = cheminFichierFilien+"/"+annee+"/"+type;
			File newRepertoire2 = new File(dossier2);		
			DebuggerLog4J.logger.debug("dossier2="+dossier2+" créé:"+newRepertoire2.mkdir());
			
			String dossier3 = cheminFichierFilien+"/"+annee+"/"+type+"/"+numeroBatchFilien;
			File newRepertoire3 = new File(dossier3);		
			DebuggerLog4J.logger.debug("dossier3="+dossier3+" créé:"+newRepertoire3.mkdir());
			
			FileWriter fw = new FileWriter( cheminFinale +"/"+nomFichier);
		    BufferedWriter output = new BufferedWriter(fw);		    
		    //Recuperation des données a parir du nuemero de batch		    
		    Vector res =requestFilien.getFichierFilien(numeroBatchFilien,codenaturejuridique,idNatureJur,tous);
		    DebuggerLog4J.logger.debug("Nombre de facture"+res.size());
		    if (res.size()>0 ) nbreFacture= res.size() -1;
		    //test 
		   
		    //Creation du fichier filien: 
		    //Pour le premier trimestre  commencer                10R10001
		    //Deuxième trimestre                                  10R15001
		    //Troisième trimestre                                 10R20001
		    //Quatrième trimestre                                 10R25001
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
					//Créer le lien vers la facture uploaded
					else if (element.startsWith("/263/"))
					{
						//paul modif 12/10/2020
						DebuggerLog4J.logger.debug("263--requesFacture");
						Facture facture=requestFacture.getFacture(numeroFacture);
						DebuggerLog4J.logger.debug("263--requesFacture--OK");
						//paul évolution créer facture relance
					        int numeroBatch = Integer.parseInt(facture.getIdBatch());		        
							CreerFactures cf =new CreerFactures(numeroBatch,"reediter");
							cf.LancerFactures(numeroBatch,  false);	
							cf.CreationFactures(
									Integer.parseInt(numeroFacture),
									210,		//numéro chrono non défini
									numeroBatch, 
									facture.getTypeTaxe(), 
									facture.getIdClient(), 
									Integer.parseInt(facture.getNumeroTitre()), "relance");
					//fin 
						String pathFacture=fichierDeConfiguration.getDossierFacture()+"/factures/"+facture.getNomDossier()+"/"+facture.getNumeroFacture()+"relance.pdf";
						DebuggerLog4J.logger.debug("263--path:"+pathFacture);
						//1er \ escape string et 2è \ escape reg expression (\n,\s)
						pathFacture=pathFacture.replaceAll("/", "\\\\");
						DebuggerLog4J.logger.debug("after replace--path:"+pathFacture);
						element="/263/"+pathFacture;
					}
					//Paul evolution ajouter tag 506 montant total
					else if (element.startsWith("/506/"))
					{	
						element = element.replaceAll("/506/", "");
						element = element.replace(".", ",");
						int nbreChifreAvantVirgule =element.indexOf(",");
						for (int k = 0; k < 9 - nbreChifreAvantVirgule ; k++) 
						{
							element = "0"+element;			
						}
						element = "/506/" + element;
					}
					
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
			DebuggerLog4J.logger.fatal("Impossible de créer le fichier "+nomFichier+" \n..............[Erreur]"
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
