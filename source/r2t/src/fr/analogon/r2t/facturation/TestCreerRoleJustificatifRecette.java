package fr.analogon.r2t.facturation;

import java.sql.SQLException;

import fr.analogon.r2t.main.DebuggerLog4J;

public class TestCreerRoleJustificatifRecette {
	public static void main(String[] args) throws SQLException {
		
		//Création du role
		//Il faut mettre en parametre la premiére parti de nom du rapport .jrxml
		//qui corresond aussi au nom du répertoire
		//On rajoutera en plus dans le code _r2tBordeauxV1
		//DebuggerLog4J.logger.debug("Début du test RoleEtatJustificatifRecette");
		CreerRoleFacturation Montest = new CreerRoleFacturation();
		Montest.lancerCreerRoleFacturation("REJR",4216,"","","","","","");
		//DebuggerLog4J.logger.debug("Fin du test RoleEtatJustificatifRecette");
	} 
}// Fin de la classe
