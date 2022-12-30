package fr.analogon.r2t.test;
public class GenrationCode
{	
	public static void main(String[] args)
	{		
		//String template="if (request.getParameter(\"objet\") != null)	this.objet= request.getParameter(\"objet\");";
		//String template = "if (objet.length()!=0)	r1=r1+ \" AND reclamation.objet =\"+ objet;";	
		//String template = "if (request.getParameter(\"objet\") != null)	this.objet= request.getParameter(\"objet\");";
		String template = "objet = FonctionCommun.ajouterAntislash(objet);";
		String tableau[] = {"raisonSocial","numRue" , "codeVoie","leCodeSecteur" , "NomQuartier" ,
				"adresse1","adresse2","adresse3","cedex","codePostal","ville" , "numTel" , "numPortable",
				"numfax","email", "codeInscription" , "numInscription",
			"dateInscription" , "dateDebutActivite" , "dateFinActivite",
			"codeProfession" , "raisonSocialeProprietaire" , "nomProprietaire",
			"prenomProprietaire" , "numVoieProprietaire" , "codeVoixProprietaire", 
			"adressProprietaire" , "complement1AdressProprietaire", 
			"complement2AdressProprietaire" , "codePostaleProprietaire", 
			"villeeProprietaire" , "cedexProprietaire" , "numTelFixeProprietaire",
			"numTelPortableProprietaire" , "numTelFaxeProprietaire", 
			"emailProprietaire" , "choix","numRedevable","codeSecteur",
			"codeType","idRedevableAutorise","etatEmplacement","anneeExerciceImputation"};
		
		for (int i = 0; i < tableau.length; i++) 
		{
			String ligne= template.replaceAll("objet",tableau[i]);
			//System.out.println(ligne);
		}
	}
}
