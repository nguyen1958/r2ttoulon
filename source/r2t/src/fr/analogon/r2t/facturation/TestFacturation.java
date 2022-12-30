package fr.analogon.r2t.facturation;

import fr.analogon.r2t.main.DebuggerLog4J;

public class TestFacturation 
{
	public static void main(String[] args) throws Exception
	{
		DebuggerLog4J.logger.debug("TestFacturation");
		DebuggerLog4J.logger.debug("Début du test Edition d'une facture");
		new CreerFactures(1326,"test");
		DebuggerLog4J.logger.debug("Fin du test Edition d'une facture");
	} 
	
}// Fin de la classe
