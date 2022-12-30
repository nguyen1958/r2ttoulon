package fr.analogon.r2t.facturation;

import java.sql.SQLException;

import fr.analogon.r2t.main.DebuggerLog4J;

public class TestCreerRoleAnnulation {
	public static void main(String[] args) throws SQLException {
		
		//Création du role
		//Il faut mettre en parametre la premiére partie de nom du rapport .jrxml
		//On rajoutera en plus dans le code _r2tBordeauxV1
		//Le numero de batch sert pour creer le repertoire
		//L'annee d'exercice pour la requet
		DebuggerLog4J.logger.debug("Début du test RoleAnnulation");
		CreerRoleFacturation Montest = new CreerRoleFacturation();
		Montest.lancerCreerRoleFacturation("RA",1325,"2008","","","","","");
		DebuggerLog4J.logger.debug("Fin du test RoleAnnulation");
	} 
}// Fin de la classe
