package fr.analogon.r2t.facturation.pleiade;


import java.io.File;
import java.util.Date;
import java.util.Vector;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import fr.analogon.r2t.Utilitaire.ConcatenationFile;
import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.Utilitaire.ReglerCheminSelonOs;
import fr.analogon.r2t.facturation.CreerFactures;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.pojo.BatchRelance;
import fr.analogon.r2t.pojo.Facture;
import fr.analogon.r2t.request.RequestFacture;
import fr.analogon.r2t.request.RequestImpayee;
import fr.analogon.r2t.request.RequestRelance;


/**	-----------------------------------------------------------------------<br>
* @author 	Sofien CHARFI
* 20/09/2008	16h10<br>
*-----------------------------------------------------------------------<br>
*Classe qui permet de creer un fichier Excel pour les impayes 
*
*-----------------------------------------------------------------------<br>
*/
public class CreerFichierExcelImpayee
{
	
	FichierDeConfiguration  fichierDeConfiguration = InitialisationConnexionLectureConfiguration.fichierDeConfiguration;
		
	
	public  CreerFichierExcelImpayee(int numeroBatchRelance) 
	{	
		try 
		{
			String nomFichier="";
			String chemineFichierExcel ="";
			RequestImpayee requestImpayee  = new  RequestImpayee(); 
			RequestRelance requestRelance = new RequestRelance();
			RequestFacture requestFacture = new RequestFacture();
			BatchRelance batchRelance = requestRelance.getBatchRealRelance(numeroBatchRelance);			
		
			String jour = String.valueOf(new Date().getDate());
			String mois = String.valueOf(new Date().getMonth());
			String annee = String.valueOf(new Date().getYear()+1900);
			String heure = String.valueOf(new Date().getHours());
			String minute = String.valueOf(new Date().getMinutes());
			if ( Integer.parseInt(heure) <10 ) heure = "0"+heure;
			if ( Integer.parseInt(minute) <10 ) minute = "0"+minute;
			if ( Integer.parseInt(jour) <10 ) jour = "0"+jour;
			if ( Integer.parseInt(mois) <10 ) mois = "0"+mois;
			
			String type = batchRelance.getType();
			String anneeRelance = batchRelance.getAnnee(); 
			String dateLancement = batchRelance.getDateLancement();
			dateLancement = dateLancement.replaceAll("/", "");
			String cheminFichierImpaye= fichierDeConfiguration.getCheminFichierExcelImpaye();
			
			nomFichier=  "impayee_"+numeroBatchRelance+"_"+dateLancement+".xls";
			if( type.equalsIgnoreCase("t"))
				type ="trimestrielle";
			else if( type.equalsIgnoreCase("a"))
				type ="annuelle";
			else if( type.equalsIgnoreCase("m"))
				type ="mensuelle";
			String cheminFinale = cheminFichierImpaye+"/"+annee+"/"+type+"/"+numeroBatchRelance;
			
			String dossier1 = cheminFichierImpaye+"/"+annee;
			File newRepertoire1 = new File(dossier1);		
			newRepertoire1.mkdir();
			
			String dossier2 = cheminFichierImpaye+"/"+annee+"/"+type;
			File newRepertoire2 = new File(dossier2);		
			newRepertoire2.mkdir();
			
			String dossier3 = cheminFichierImpaye+"/"+annee+"/"+type+"/"+numeroBatchRelance;
			File newRepertoire3 = new File(dossier3);		
			newRepertoire3.mkdir();
			
			chemineFichierExcel = cheminFinale +"/"+nomFichier;
			
					
					    
		    //Recuperation des données a parir du nuemero de batch		    
		    Vector retour =requestImpayee.getFichierImpayee(numeroBatchRelance);
		    
		    
			
			
		      //Creation du fichier impayee entete:
		    
			  WritableWorkbook w = Workbook.createWorkbook(new File(chemineFichierExcel));	          
	          WritableSheet s = w.createSheet("Factures_Impayees", 0);
	          //Colone, ligne
	          
	          s.addCell(new Label(0, 0, "Date creation de ce fichier "));
	          s.addCell(new Label(1, 0,  GestionDate.getDateAujourdhuiString()));
	          
	          //Date d'edition du fichier Excel 
	          
	          //typeTaxe
	          //secteur
	          //codeEmplacement
	          //numeroFacture
	          //solde
	          //dateCreation
	          //cycleFacturation
	          //civilite
	          //responsable	
	          //nomRedevable
	          //prenom
	          //nomJF
	          //numeroRue
	          //adresseRedevable
	          //adresseRedevableCompl1
	          //adresseRedevableCompl2
	          //codePostal
	          //ville	          
	         
	          
	          s.addCell(new Label(0, 1, "typeTaxe"));
	          s.addCell(new Label(1, 1, "secteur"));
	          s.addCell(new Label(2, 1, "numeroEmplacement"));
	          s.addCell(new Label(3, 1, "numeroFacture"));
	          s.addCell(new Label(4, 1, "soldeFacture"));
	          s.addCell(new Label(5, 1, "dateCreationFacture"));
	          s.addCell(new Label(6, 1, "cycleFacturation"));
	          s.addCell(new Label(7, 1, "civiliteRedevable"));
	          s.addCell(new Label(8, 1, "responsableRedevable"));
	          s.addCell(new Label(9, 1, "nomRedevable"));
	          s.addCell(new Label(10, 1, "prenomRedevable"));
	          s.addCell(new Label(11, 1, "nomJFRedevable"));
	          s.addCell(new Label(12, 1, "numeroRue"));
	          s.addCell(new Label(13, 1, "adresseRedevable"));
	          s.addCell(new Label(14, 1, "adresseRedevableCompl1"));
	          s.addCell(new Label(15, 1, "adresseRedevableCompl2"));
	          s.addCell(new Label(16, 1, "codPostalRedevable"));
	          s.addCell(new Label(17, 1, "villeRedevable"));
	          	          
	           //Creation du fichier impayee : contenu :
			    int colone = 0 ;
			    int ligne = 2; 
			    String numeroFacture = "";
			    Vector listeDesFacturesAConcatiner = new Vector();
			    for (int i = 0; i < retour.size(); i++) 
			    {	
			    	try 
					{
						String elem = (String)retour.elementAt(i);						
						if( ! elem.equalsIgnoreCase("FIN_INFOS_FACTURE"))
						{							
							//System.out.println("ELEM="+elem);
							//System.out.println("ligne="+ligne);
							//System.out.println("colone="+colone);
							if (elem.equalsIgnoreCase("Avance-Trimestrielle"))
								elem = "trimestrielle";
							if (elem.equalsIgnoreCase("Avance-mensuelle"))
								elem = "mensuelle";
							if (elem.equalsIgnoreCase("Avance-anuelle"))
								elem = "anuelle";
							s.addCell(new Label(colone, ligne, elem));
							if(colone== 3)
								numeroFacture = elem;
							
							colone ++;							
						}
						else
						{
							 ligne ++;
							 colone= 0;
							//concatenation des factures PDF dans un seul fichier PDF
					    	RequestFacture reqFacture = new RequestFacture();			
							Facture facture=reqFacture.getFacture(numeroFacture);
							String numeroBatch = facture.getIdBatch();
							//Re-Creer la facture avec le template relance :
							CreerFactures cfacture =new CreerFactures(Integer.parseInt(numeroBatch),"relance");
							RequestFacture rq = new RequestFacture();
							cfacture.LancerFactures(Integer.parseInt(numeroBatch),  false);
							DebuggerLog4J.logger.debug("Recreation de la facture "+numeroFacture);
							cfacture.CreationFactures(
									Integer.parseInt(numeroFacture),
									210,		//numéro chrono non défini
									Integer.parseInt(numeroBatch), 
									facture.getTypeTaxe(), 
									facture.getIdClient(), 
									Integer.parseInt(facture.getNumeroTitre()), "relance");							
							String cheminFacture=fichierDeConfiguration.getCheminFactures()
									+facture.getNomDossier()
									+"/"+facture.getNumeroFacture()+"relance.pdf"; 
							cheminFacture=ReglerCheminSelonOs.reglerCheminSelonOS(cheminFacture);				
							DebuggerLog4J.logger.debug("cheminFacture="+cheminFacture);
							listeDesFacturesAConcatiner.addElement(cheminFacture);							
							String cheminNewFile=cheminFinale+"/factureImpayee_"+numeroBatchRelance+"_"+dateLancement+".pdf";
							cheminNewFile=ReglerCheminSelonOs.reglerCheminSelonOS(cheminNewFile);
							DebuggerLog4J.logger.debug("chemin File res="+cheminNewFile);							
							ConcatenationFile cf = new ConcatenationFile();							
							cf.concat(listeDesFacturesAConcatiner, cheminNewFile);
						}	
						
					}			    	
					catch (Exception e) 
					{
						e .printStackTrace();
						
					}
				}
			    w.write();
				w.close();
	          
		}
		catch (Exception e1) 
		{
			e1.printStackTrace();
		} 
		
	}	
	

}// Fin de la classe
