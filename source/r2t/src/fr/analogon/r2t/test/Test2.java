package fr.analogon.r2t.test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.apache.log4j.Logger;

import fr.analogon.r2t.Utilitaire.Arrondi;
import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.Utilitaire.ReglerCheminSelonOs;
import fr.analogon.r2t.facturation.CreerFactures;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.pojo.Facture;
import fr.analogon.r2t.pojo.Imputation;
import fr.analogon.r2t.request.RequestFacture;
import fr.analogon.r2t.request.RequestImputation;

 

public class Test2
{
	static Logger logger = Logger.getLogger(Test2.class);
	
	public static void main(String[] args) throws Exception 
	{/*
		FichierDeConfiguration  fichierDeConfiguration = new FichierDeConfiguration();
		String dossierFacture=ReglerCheminSelonOs.reglerCheminSelonOS("/r2tData/data/factures/");
		dossierFacture=ReglerCheminSelonOs.reglerCheminSelonOS(dossierFacture);
		System.out.println("Cheminfactures="+dossierFacture);
		String cheminDesFactures= fichierDeConfiguration.getCheminFactures();
		System.out.println("Cheminfactures="+cheminDesFactures);
		*/
		String path="//si.tpmed.org/fichiersAppVDT/PJVDT/PJRecettes_VDT/R2T-EMPLACEMENTS/Test";
		path=path.replaceAll("/", "\\\\");
		System.out.println("path="+path);
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
		String anneeExercice="2021";
		FichierDeConfiguration  fichierDeConfiguration = new FichierDeConfiguration();
		/*
		RequestImputation reqImputation = new RequestImputation();
		Vector listeTaxe = reqImputation.getListeImputations("", "", "",
				anneeExercice, "17");
		System.out.println(listeTaxe.get(0));
		Imputation imputation = (Imputation) listeTaxe.elementAt(0);
		reqImputation.ajouterTypeTaxe(
				Integer.parseInt(imputation.getNumtypeTaxe()), 
				imputation.getLibelle(), 
				imputation.getDesignation(), 
				imputation.getCodeBudget(),
				imputation.getCode(), 
				imputation.getSection(),
				imputation.getCodeFonction(), 
				imputation.getLibelleFonction(), 
				imputation.getCodeCentreResponsable(), 
				imputation.getLibelleCentreResponsable(), 
				imputation.getCodeCentreExecution(), 
				imputation.getLibelleCentreExecution(), 
				imputation.getNecessiteControleTerrain(), 
				imputation.getControleInduitFacturation(), 
				imputation.getNomElu(),
				imputation.getEluRenseignement1(), 
				imputation.getEluRenseignement2(), 
				imputation.getEluRenseignement3(), 
				imputation.getNomGestionnaire(), 
				imputation.getTelGestionnaire(), 
				imputation.getFaxGestionnaire(), 
				FonctionCommun.ajouterPourcentage(Double.valueOf(imputation.getMinimumPerception()), 0),
				""+ String.valueOf((Integer.parseInt(anneeExercice) + 1)),
				imputation.getTypeFacturation(), 
				imputation.getCycleFacturation(),
				imputation.getChapitre(),
				imputation.getNature(),
				imputation.getFonction(),
				imputation.getCodeOpeEquipement(),
				imputation.getTypeMouvement(),
				imputation.getSens(),
				imputation.getCodeSegStructurelle(),
				imputation.getCodeEleStructurelleGestionnaire(),
				imputation.getCodeEleStructurelleDestinataire(),
				imputation.getCodeSegment1(),
				imputation.getCodeEleSectoriel1(),
				imputation.getCodeSegment2(),
				imputation.getCodeEleSectoriel2(),
				imputation.getCodeSegment3(),
				imputation.getCodeEleSectoriel3(),
				imputation.getCodeSegment4(),
				imputation.getCodeEleSectoriel4(),
				imputation.getCodeSegment5(),
				imputation.getCodeEleSectoriel5(),
				imputation.getCodeSegOperationnel(),
				imputation.getCodeEleOperationnel(),
				imputation.getCodeSegStrategique(),
				imputation.getCodeEleStrategique(),
				imputation.getMarche()
				);*/
		String numeroFacture= "2021000214";
        RequestFacture requestFacture = new RequestFacture();
        Facture facture = requestFacture.getFacture(numeroFacture);
        int numeroBatch = Integer.parseInt(facture.getIdBatch());		        
		CreerFactures cf =new CreerFactures(numeroBatch,"reediter");
		System.out.println("avt cf.lancerFactures");
		cf.LancerFactures(numeroBatch,  false);	
		System.out.println("after cf.lancerFactures");
		cf.CreationFactures(
				Integer.parseInt(numeroFacture),
				210,		//numéro chrono non défini
				numeroBatch, 
				facture.getTypeTaxe(), 
				facture.getIdClient(), 
				Integer.parseInt(facture.getNumeroTitre()), "relance");
				
	}		
}








