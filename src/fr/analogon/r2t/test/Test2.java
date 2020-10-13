package fr.analogon.r2t.test;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import fr.analogon.r2t.Utilitaire.Arrondi;
import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.Utilitaire.ReglerCheminSelonOs;
import fr.analogon.r2t.facturation.CreerFactures;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.pojo.Facture;
import fr.analogon.r2t.request.RequestFacture;

 

public class Test2
{
	static Logger logger = Logger.getLogger(Test2.class);
	
	public static void main(String[] args) throws Exception 
	{
		FichierDeConfiguration  fichierDeConfiguration = new FichierDeConfiguration();
		String dossierFacture=ReglerCheminSelonOs.reglerCheminSelonOS("/r2tData/data/factures/");
		dossierFacture=ReglerCheminSelonOs.reglerCheminSelonOS(dossierFacture);
		System.out.println("Cheminfactures="+dossierFacture);
		String cheminDesFactures= fichierDeConfiguration.getCheminFactures();
		System.out.println("Cheminfactures="+cheminDesFactures);
		/*String test = "12---AMBULANT"; 
		System.out.println(test.substring(0,test.indexOf("---"))) ;
		String dateDebut="20/02/2019";
		String dateDebutPeriodeTmp;
		String dateFinPeriodeTmp;
		String dateDebutSemaine="";
		String dateFinSemaine="";
		boolean semaineDejaCalcule=false;
		System.out.println("dateDebut="+dateDebut) ;
		
		System.out.println("ajouterNombreMoisComplet="+GestionDate.ajouterNombreMoisComplet(dateDebut, 1)) ;
		
		System.out.println("DenierJourTrimestre:"+GestionDate.getDernierJOurDuTrimestre("01/12/2019"));
		System.out.println("NombreJour:"+GestionDate.getNombreDeJour("01/01/2019", "01/01/2019"));
		System.out.println("NombreSemaine:"+GestionDate.getNombreDeSemaine("01/01/2019", "08/01/2019"));
		System.out.println("NombreMois:"+GestionDate.getNombreDeMois("01/01/2019", "08/02/2019"));

		
		for(int i=1;i<10;i++){
			dateDebutPeriodeTmp = GestionDate.ajouterNombreMois("DDebut",dateDebut,i-1);
			dateFinPeriodeTmp=GestionDate.ajouterNombreMois("DFin",	dateDebut,i);
			System.out.println("resultat="+dateDebutPeriodeTmp+"|"+dateFinPeriodeTmp) ;
			if (semaineDejaCalcule)
				dateDebutSemaine=GestionDate.addaDay(dateFinSemaine);
			else{
				dateDebutSemaine=GestionDate.ajouterNombreSemaineComplet(dateDebut,	i);
				semaineDejaCalcule=true;
			}
			dateFinSemaine=GestionDate.ajouterNombreSemaine(dateDebutSemaine,	1);
			System.out.println("**"+dateDebutSemaine+"|"+dateFinSemaine);
			int nbreJourTmp = GestionDate.getNombreDeJour(dateDebutSemaine, dateFinSemaine);
			System.out.println("**"+dateDebutSemaine+"|"+dateFinSemaine+"|"+nbreJourTmp);
		}
		
		String dateProchainControl = GestionDate.ajouterNombreSemaineComplet(
				dateDebut, 2);
		
		dateProchainControl = GestionDate
				.validerUneDate(dateProchainControl);
		System.out.println(dateProchainControl);
		*/
	}		
}








