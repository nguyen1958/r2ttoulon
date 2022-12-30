package fr.analogon.r2t.facturation.scriptBatch;


import fr.analogon.r2t.idoss.LectureFichierIdoss;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.request.RequestIdoss;



/**	-----------------------------------------------------------------------<br>
* @author 	Sofien CHARFI
* 20/09/2008	16h10<br>
*-----------------------------------------------------------------------<br>
*Classe qui permet de lire le flux IDOSS
*
*-----------------------------------------------------------------------<br>
*/
public class LectureFluxIdoss
{	
	public static void main(String[] args) 
	{		
		try 
		{
			RequestIdoss requestIdoss =  new RequestIdoss() ;
			int numeroBatchIdoss = requestIdoss.ajouterLigneBatchRapprochemetnIdoss();
			LectureFichierIdoss c = new LectureFichierIdoss(numeroBatchIdoss);
			System.out.println("0");
		} 
		catch (Exception e) 
		{
			DebuggerLog4J debug = new DebuggerLog4J();
			debug.logger.fatal("Erreur "+  e.getStackTrace());				
			System.out.println("-1");
			e.printStackTrace();
		}
	}
	
}// Fin de la classe
