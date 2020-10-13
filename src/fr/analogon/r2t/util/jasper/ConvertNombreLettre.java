package fr.analogon.r2t.util.jasper;

/**
 * Classe de conversion d'un nombre en lettre
 * Cette classe peut être testée avec le main()
 * ireport envoie un Double
 * @author Pascal
 *
 */
public abstract class ConvertNombreLettre {
    
	/** Creates a new instance of JRIreportDefaultScriptlet */
	public  ConvertNombreLettre() {
			
	}

	/**Méthopde permettant de convertir l'entier sous forme de lettres
	 * Valable pour des doubles avec deux chiffres après la virgules
	    * @param intToString valeur entiére qu'on veut sous forme de chaine
	    * @return Nombre sous forme de lettres*/
	   public static String convertIntToString(int intToString){
	   	 int valeur = intToString;
	   	 String result = "";
	      	    	 
	   	 if(valeur < 0){
	   	 	result = " ";
	   	 	valeur = -1 * valeur; //On le met en positif
	   	 }
	   	 
	   	 //Nous allons commencer par convertir les chiffres supérieure à 1 000 000 000
	   	 if(valeur > 1000000000){
	   	 	int valeurSupMilliard = (int)valeur / 1000000000;
	   	 	result = result + " " + convertIntToString(valeurSupMilliard) + " milliard";
	   	 	valeur = valeur - (valeurSupMilliard * 1000000000);
	   	 }
	   	 
	   	 //Nous allons convertir les chifres entre 1 000 000 et 999 999 999  
	   	 if(valeur > 1000000){
	   	 	int valeurMillion = (int)valeur / 1000000;
	   	 	if (valeurMillion == 1)
	   	 		result = result + " " + convertIntToString(valeurMillion) + " million";
	   	 	else		//Mettre au pluriel si Plus de  un million
	   	 		result = result + " " + convertIntToString(valeurMillion) + " millions";
	   	 	valeur = valeur - (valeurMillion * 1000000);
	   	 }
	   	 
	   	 //Nous allons convertir les chiffres entre 1000 et 999 999
	   	 if(valeur > 1000){
	   	 	int valeurMille = (int)valeur / 1000;

	   	 	if(valeurMille > 1){
	   	 		result = result + " " + convertIntToString(valeurMille) + " mille";
	   	 	}
	   	 	else{
	   	 		result = result + " mille";
	   	 	}
	   	 	
	   	 	valeur = valeur - (valeurMille * 1000);
	   	 }
	   	
	   	 //Nous allons convertir les chiffres entre 100 et 999	
	   	 if(valeur >= 100){
	   	 	int valeurCent = (int)valeur / 100;

	   	 	if(valeurCent > 1){
	   	 		result = result + " " + convertIntToString(valeurCent) + " cent";
	   	 	}
	   	 	else{
	   	 		result = result + " cent";
	   	 	}
	   	 	
	   	 	valeur = valeur - (valeurCent * 100);
	   	 }
	   	 
	   	 //Nous allons convertir les chiffres entre 0 et 99
	   	 switch(valeur){
	   	 	case 0 : 
	   	 		if(result == "" || result == " "){ result = "zéro";
	   	 		}
	   	 		break;
	   	 	case 1 : 
	   	 		result = result + " un";
	   	 		break;
	   	 	case 2 : 
	   	 		result = result + " deux";
	   	 		break;
	   	 	case 3 : 
	   	 		result = result + " trois";
	   	 		break;
	   	 	case 4 : 
	   	 		result = result + " quatre";
	   	 		break;
	   		case 5 : 
	   			result = result + " cinq";
	   			break;
	   		case 6 : 
	   			result = result + " six";
	   			break;
	   		case 7 : 
	   			result = result + " sept";
	   			break;
	   		case 8 : 
	   			result = result + " huit";
	   			break;
	   		case 9 : 
	   			result = result + " neuf";
	   			break;
	   		case 10 : 
	   			result = result + " dix";
	   			break;
	   		case 11 : 
	   			result = result + " onze";
	   			break;
	   		case 12 : 
	   			result = result + " douze";
	   			break;
	   		case 13 : 
	   			result = result + " treize";
	   			break;
	   		case 14 : 
	   			result = result + " quatorze";
	   			break;
	   		case 15 : 
	   			result = result + " quinze";
	   			break;
	   		case 16 : 
	   			result = result + " seize";
	   			break;   
	   		case 17 : 
	   			result = result + " dix sept";
	   			break;
	   		case 18 : 
	   			result = result + " dix huit";
	   			break;
	   		case 19 : 
	   			result = result + "  dix neuf";
	   			break;
	   		case 20 : 
	   			result = result + " vingt";
	   			break;
	   		case 30 : 
	   			result = result + " trente";
	   			break;
	   		case 40 : 
	   			result = result + " quarante";
	   			break;
	   		case 50 : 
	   			result = result + " cinquante";
	   			break;
	   		case 60 : 
	   			result = result + " soixante";
	   			break;
	   		case 70 : 
	   			result = result + " soixante dix";
	   			break;
	   		case 80 : 
	   			result = result + " quatre vingt";
	   			break;
	   		case 81 : 
	   			result = result + " quatre vingt un";
	   			break;
	   		case 90 : 
	   			result = result + " quatre vingt dix";
	   			break;
	   		case 91 : 
	   			result = result + " quatre vingt onze";
	   			break;
	   		default :
	   			int valeurDizaine = (valeur / 10) * 10;
	   			int valeurUnite = valeur % 10;
	   			
	   			//Les valeurs entre 16 et 20, 70 et 80, et entre 90 et 100 sont des cas
	   			//particuliers, nous devons faire un traitement particulier
	   			if((valeur > 16 && valeur < 20) || (valeur > 70 && valeur < 80) || (valeur > 90 && valeur < 100)){
	   				valeurDizaine -= 10;  			
	   				valeurUnite +=  10;
	   			}
	   			
	   			if(valeurDizaine == 0){
					result = result + " " + convertIntToString(valeurUnite);
				}
				else if(valeurUnite == 0){
					result = result + " " + convertIntToString(valeurDizaine);
				}
				else if(valeurUnite == 1){
					result = result + " " + convertIntToString(valeurDizaine)
					+ " et " + convertIntToString(valeurUnite);
				}
				else{
					result = result + " " + convertIntToString(valeurDizaine)
					+ " " + convertIntToString(valeurUnite);
				}
	   	 }
	   	
	   	 
	   	 return result.trim();
	   }//Fin de methode

	/**Méthode permettant de convertir le réel sous forme de lettre
	 * Appele par le rapport ireport : $P{REPORT_SCRIPTLET}.convertRealToString()
	* @param realToString valeur réelle qu'on veut sous forme de chaine
	* @return Nombre sous forme de lettres
	* Pour deux chiffres après la virgule
	* 
	* C'est la méthode qui est appelée par ireport
	* 
	* */   
	public static String convertRealToString(Double DNombre) throws Exception{
		String result = "";
		String Smontant = null;
		//	Récupération du champ ireport
		//Ce transtypage est obligatoire pour que cela fonctionne!!!!!		
		//Smontant = String.valueOf(DNombre.floatValue());

		//Pour faire l'arrondi au plus proche
		Smontant = String.valueOf(floor(DNombre,2));		
		 
		int valeurApresVirgule = 0;
		int valeurAvantVirgule = 0;
	
		//Recherche partie entiere
		//valeurAvantVirgule = DNombre.intValue();
		valeurAvantVirgule = Integer.parseInt(Smontant.split("[.]")[0]);
		
		//Recherche partie décimale
		//Modification du 05/11/2009 pour régler le pb d'un nombre significatif à un chiffre
		//aprés la virgule
		//Le chiffre doit être sous la forme ####.##
		//On décompose 
		if(Smontant.split("[.]")[1].length()==1)
			valeurApresVirgule = Integer.parseInt(Smontant.split("[.]")[1].substring(0,1).concat("0"));
		else
			valeurApresVirgule = Integer.parseInt(Smontant.split("[.]")[1].substring(0,2));
	
		//Calcul de la conversion
		if(valeurApresVirgule == 0){
			result = convertIntToString(valeurAvantVirgule)+ " euros";
		}
		else{
			result = convertIntToString(valeurAvantVirgule) + " euros et "
					+ convertIntToString(valeurApresVirgule)+ " centimes";
		}
		
		//Traitement du cas particulier 80
	   	result = result.replace("quatre vingt milliard","quatre vingts milliard");
	   	result = result.replace("quatre vingt millions","quatre vingts millions");
	   	result = result.replace("quatre vingt mille","quatre vingts mille");
	   	result = result.replace("quatre vingt euros","quatre vingts euros");
	   	result = result.replace("quatre vingt centimes","quatre vingts centimes");
		return result ;
	}//Fin de methode

	/**
	 * Arrondi d'un double avec n éléments après la virgule.
	 * @param a La valeur à convertir.
	 * @param n Le nombre de décimales à conserver.
	 * @return La valeur arrondi à n décimales.
	 */
	public static double floor(double a, int n) {
		double p = Math.pow(10.0, n);
		return Math.floor((a*p)+0.5) / p;
	}

	/*
	 * Cette classe peut être testée avec le main()
	 */
	public static void main(String[] args) {
		//BigDecimal db = new BigDecimal(0.01);
		//BigDecimal db2 = new BigDecimal(0.00);
		//Attention 10.01999 et arrondi à 10.01 et 10.199999999999999 est arrondi à 10.20
		//10.019999999987
		//BigDecimal db3 = new BigDecimal(0.0100000000001);//db.add(db2);

		//Pour test peut qu'on peut travailler avec des float dans le sous-rapport
		//la somme dans le sous-rapport en float et en double ne donne
		//pas le même résulat de calcul, il faut absiolument travailler en Double
	
		try {
			//Pour test
			System.out.println();
			System.out.println("119697.33 donne "+convertRealToString(new Double(119697.33)));
			System.out.println("1980 donne "+convertRealToString( new Double(1980)));
			System.out.println("10.01111119 donne "+convertRealToString(new Double(10.01111119)));
			System.out.println("0.1 donne "+convertRealToString(new Double(0.1)));
			System.out.println("0.101 donne "+convertRealToString(new Double(0.101)));
			System.out.println("1330.12 donne "+convertRealToString(new Double(1330.12)));
			System.out.println("9695080.80 donne "+convertRealToString(new Double(9695080.80)));
			System.out.println("8081080.81 donne "+convertRealToString(new Double(8081080.81)));
			System.out.println("8080080.80 donne "+convertRealToString(new Double(8080080.80)));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
