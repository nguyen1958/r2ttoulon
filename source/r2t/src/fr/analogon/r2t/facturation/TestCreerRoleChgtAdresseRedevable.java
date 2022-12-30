package fr.analogon.r2t.facturation;

import java.sql.SQLException;

import fr.analogon.r2t.main.DebuggerLog4J;

public class TestCreerRoleChgtAdresseRedevable {

	public static void main(String[] args) throws SQLException {
		
		//Création du role
		//Il faut mettre en parametre la premiére parti de nom du rapport .jrxml
		//On rajoutera en plus dans le code _r2tBordeauxV1
		DebuggerLog4J.logger.debug("Début du test RoleChgtAdresseRedevable");
		CreerRoleFacturation Montest = new CreerRoleFacturation();
		Montest.lancerCreerRoleFacturation("RCAR",1168,"","","","","","");
		DebuggerLog4J.logger.debug("Fin du test RoleChgtAdresseRedevable");
	} 	
}// Fin de la classe
