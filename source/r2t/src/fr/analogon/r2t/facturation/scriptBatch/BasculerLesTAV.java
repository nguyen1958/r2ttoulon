package fr.analogon.r2t.facturation.scriptBatch;


import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.request.RequestBascule;



/**	-----------------------------------------------------------------------<br>
* @author 	Sofien CHARFI
* 20/09/2008	16h10<br>
*-----------------------------------------------------------------------<br>
*Classe qui permet de basculer les TAV
*
*-----------------------------------------------------------------------<br>
*/
public class BasculerLesTAV
{	
	public static void main(String[] args) 
	{		
		try 
		{
			RequestBascule req = new RequestBascule();				
			req.basculerLesTav();
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
