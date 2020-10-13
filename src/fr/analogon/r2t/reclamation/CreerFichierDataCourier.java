package fr.analogon.r2t.reclamation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.pojo.Reclamation;
import fr.analogon.r2t.pojo.Redevable;
import fr.analogon.r2t.request.RequestReclamation;
import fr.analogon.r2t.request.RequestRedevable;


/**	-----------------------------------------------------------------------<br>
* @author 	Sofien CHARFI
* 20/09/2008	16h10<br>
*-----------------------------------------------------------------------<br>
*Classe qui permet de creer un fichier data pour les courier 
*
*-----------------------------------------------------------------------<br>
*/
public class CreerFichierDataCourier
{

	FichierDeConfiguration  fichierDeConfiguration = InitialisationConnexionLectureConfiguration.fichierDeConfiguration;
	String lienDataFile="";
	String cheminDataFile="";
	
	/**
	 * @return the lienDataFile
	 */
	public final String getLienDataFile() {
		return lienDataFile;
	}

	/**
	 * @param lienDataFile the lienDataFile to set
	 */
	public final void setLienDataFile(String lienDataFile) {
		this.lienDataFile = lienDataFile;
	}

	public CreerFichierDataCourier(int idReclamation) 
	{	
		String nomFichier="";		
		try
		{	
			RequestReclamation RequestReclamation = new RequestReclamation();
			String cheminDossierTmp= fichierDeConfiguration.getCheminDossierTmp();
			//Recuperation des données a parir du nuemero de la reclmation 		    
		    Vector v = RequestReclamation.getReclamation(idReclamation); 
		    Reclamation reclamation = (Reclamation)v.elementAt(0);	
			Long now= new Date().getTime();
			nomFichier=   idReclamation + ".txt";
			cheminDataFile =  cheminDossierTmp +"/"+nomFichier;
			lienDataFile = fichierDeConfiguration.getLienDossierTmp()+ nomFichier;
			FileWriter fw = new FileWriter(cheminDataFile);
		    BufferedWriter output = new BufferedWriter(fw);		    
		    
		    //RAISONSOCIAL|NOM|PRENOM|ADRESSE|FACTURE_NUMERO|MOTIF_RECLAMATION|DATE_RECLAMATION
		    int idRedevable = reclamation.getIdRedevable();
		    Redevable  redevable = (new RequestRedevable()).getRedevable(""+idRedevable);
		    String dateContole="";    
		    if(reclamation.getDateContole() !=null) dateContole = reclamation.getDateContole();
		    
		    output.write("NUM_REDEVABLE|RAISONSOCIAL|NOM|PRENOM|RESPONSABLE|" +
		    			 "ADRESSE|COMPL1_ADRESSE|COMPL2_ADRESSE|CODE_POSTAL|VILLE|" +
		    			 "NUM_RECLAMATION|NUM_FACTURE|MOTIF_RECLAMATION|" +
		    			 "DATE_RECLAMATION|DATE_CONTROLE"
		    		+"\n");
		 
		    output.write(redevable.getNumRedevable()+"|");
		   	output.write(redevable.getRaisonSocialeRedevable()+"|");
		   	output.write(redevable.getNomRedevable()+"|");
		   	output.write(redevable.getPrenomRedevable()+"|");
		   	output.write(redevable.getResponsableRedevable()+"|");		   	
		   	output.write(redevable.getNumVoieRedevable () + " "	+redevable.getAdressRedevable()+"|");
		   	output.write(redevable.getComplement1AdressRedevable()+"|");
		   	output.write(redevable.getComplement2AdressRedevable()+"|");
		   	output.write(redevable.getCodePostaleRedevable()+"|");
		   	output.write(redevable.getVilleeRedevable()+"|");
		   	output.write(reclamation.getIdReclamation()+"|");		   	
		   	output.write(reclamation.getIdFacture()+"|");
		   	output.write(reclamation.getMotifReclamation()+"|");
		   	output.write(reclamation.getDateReclamation()+"|");
		   	output.write(dateContole);		   			 
		    output.flush();
		    output.close();
			
		}		
		catch (Exception e) 
		{
			DebuggerLog4J.logger.fatal("Impossible de créer le fichier..............[Erreur]" +nomFichier
					+ e.getMessage() +  e.getMessage() );	
			e.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		CreerFichierDataCourier c = new CreerFichierDataCourier(753);
	}
}// Fin de la classe
