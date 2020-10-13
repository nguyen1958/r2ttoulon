package fr.analogon.r2t.facturation;


public class ConversionToLetter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Raccord de m�thode auto-g�n�r�
		String s ="1057.20";
		Double ValeurDouble = Double.valueOf(s);
		String ValeurLettre = convertRealToString(ValeurDouble);
		System.out.println(ValeurLettre);
	}
    /**M�thopde permettant de convertir l'entier sous forme de lettres
     * Valable pour des doubles avec deux chiffres apr�s la virgules
	    * @param intToString valeur enti�re qu'on veut sous forme de chaine
	    * @return Nombre sous forme de lettres*/
	   public static String convertIntToString(int intToString){
	   	 int valeur = intToString;
	   	 String result = "";
	      	    	 
	   	 if(valeur < 0){
	   	 	result = " ";
	   	 	valeur = -1 * valeur; //On le met en positif
	   	 }
	   	 
	   	 //Nous allons commencer par convertir les chiffres sup�rieure � 1 000 000 000
	   	 if(valeur > 1000000000){
	   	 	int valeurSupMilliard = valeur / 1000000000;
	   	 	result = result + " " + convertIntToString(valeurSupMilliard) + " milliard";
	   	 	valeur = valeur - (valeurSupMilliard * 1000000000);
	   	 }
	   	 
	   	 //Nous allons convertir les chifres entre 1 000 000 et 999 999 999  
	   	 if(valeur > 1000000){
	   	 	int valeurMillion = valeur / 1000000;
	   	 	result = result + " " + convertIntToString(valeurMillion) + " million";
	   	 	valeur = valeur - (valeurMillion * 1000000);
	   	 }
	   	 
	   	 //Nous allons convertir les chiffres entre 1000 et 999 999
	   	 if(valeur > 1000){
	   	 	int valeurMille = valeur / 1000;

	   	 	if(valeurMille > 1){
	   	 		result = result + " " + convertIntToString(valeurMille) + " mille";
	   	 	}
	   	 	else{
	   	 		result = result + " mille";
	   	 	}
	   	 	
	   	 	valeur = valeur - (valeurMille * 1000);
	   	 }
	   	
	   	 //Nous allons convertir les chiffres entre 100 et 999	
	   	 if(valeur > 100){
	   	 	int valeurCent = valeur / 100;

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
	   	 		if(result == "" || result == " "){
	   	 			result = "z�ro";
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
	   			result = result + " quatre vingts";
	   			break;
	   		case 81 : 
	   			result = result + " quatre vingts un";
	   			break;
	   		case 90 : 
	   			result = result + " quatre vingts-dix";
	   			break;
	   		case 91 : 
	   			result = result + " quatre vingts onze";
	   			break;
	   		default :
	   			int valeurDizaine = (valeur / 10) * 10;
	   			int valeurUnite = valeur % 10;
	   			
	   			//Les valeurs entre 16 et 20, 70 et 80, et entre 90 et 100 sont des cas
	   			//particuliers, nous devons faire un triatment particulier
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
	   }

	   /**M�thode permettant de convertir le r�el sous forme de lettre
	    * @param realToString valeur r�elle qu'on veut sous forme de chaine
	    * @return Nombre sous forme de lettres
	    * Pour deux chiffres apr�s la virgule
	    * */   
	   public static String convertRealToString(double intToString){
	   	 int valeurApresVirgule = (int)(100*(intToString - (int)intToString));
	   	 //floor intToString;
	   	 int valeurAvantVirgule = (int)(intToString);
	   	 String result = "";
	   	 
	   	 if(valeurApresVirgule == 0){
	   	 	result = convertIntToString(valeurAvantVirgule);
	   	 }
	   	 else{
	   	 	result = convertIntToString(valeurAvantVirgule) + " euros "
				+ convertIntToString(valeurApresVirgule)+ " centimes ";
	   	 }
	   	 
	   	 return result ;
	   }
}
