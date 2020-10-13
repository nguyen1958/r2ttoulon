package fr.analogon.r2t.facturation;

import java.sql.SQLException;

import fr.analogon.r2t.main.DebuggerLog4J;

public class TestCreerRoleSuiviFacturation {
	public static void main(String[] args) throws SQLException {
		
		//Création du role
		//Il faut mettre en parametre la premiére parti de nom du rapport .jrxml
		//On rajoutera en plus dans le code _r2tBordeauxV1
		//Pas de numéro de batch nécessaire en paramètre
		DebuggerLog4J.logger.debug("Début du test RoleSuiviFacturation");
		CreerRoleFacturation Montest = new CreerRoleFacturation();
		Montest.lancerCreerRoleFacturation("RSF",1325,"Pascal ZACCHELLO","","DDV","23/10/2007","22/12/2009","");
		DebuggerLog4J.logger.debug("Fin du test RoleSuiviFacturation");
	} 
}// Fin de la classe
