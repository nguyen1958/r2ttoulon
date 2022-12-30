package fr.analogon.r2t.reclamation;

import java.sql.SQLException;

public class TestCreerCourrierReclamation {
	public static void main(String[] args) throws SQLException {
		
		//Création du role
		//Il faut mettre en parametre la premiére parti de nom du rapport .jrxml
		//On rajoutera en plus dans le code _r2tBordeauxV1
		//Pas de numéro de batch nécessaire en paramètre
		//System.out.println("Début du test CreerCourrierReclamation");
		CreerCourrier MonCourrierReclamation = new CreerCourrier("2009000002","TAV1");
		MonCourrierReclamation.InitCreationCourrier();
		//System.out.println("Fin du test CreerCourrierReclamation");
	} 
}// Fin de la classe
