package fr.analogon.r2t.facturation;

import java.sql.SQLException;

public class TestCreerRoleCommuniqueOuvrage {
	public static void main(String[] args) throws SQLException {
		
		//Création du role
		//Il faut mettre en parametre la premiere partie de nom du rapport .jrxml
		//On rajoutera en plus dans le code _r2tBordeauxV1
	//	DebuggerLog4J.logger.debug("Début du test RoleCommuniqueOuvrage");
		CreerRoleFacturation Montest = new CreerRoleFacturation();
		Montest.lancerCreerRoleFacturation("RCO",1325,"","","","","10/06/1999","");
	//	DebuggerLog4J.logger.debug("Fin du test RoleCommuniqueOuvrage");
	} 
}// Fin de la classe
