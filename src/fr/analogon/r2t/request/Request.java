package fr.analogon.r2t.request;


import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;


public class Request 
{
	public static DebuggerLog4J debug = new DebuggerLog4J();

	Connecteur con = InitialisationConnexionLectureConfiguration.getConnexion();
	FichierDeConfiguration  fichierDeConfiguration = InitialisationConnexionLectureConfiguration.getFichierDeConfiguration();
	
	/**
	 * @return the con
	 */
	public final Connecteur getCon() 
	{
		//System.out.println("TESTE connecteur");
		return con;
	}
	/**
	 * @param con the con to set
	 */
	public final void setCon(Connecteur con) 
	{
		this.con = con;
	}

}
