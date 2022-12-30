package fr.analogon.r2t.Utilitaire;



/**
 * ensemble de methodes static manipulants des nombres - Sofien CHARFI
 * 21.01.2006
 */
public final class StaticNombres {

	/**
	 * Arrondie un double a un nombre avec x chiffre apres la virgule, y compris
	 * des zero finaux.
	 * 
	 * @param nbChiffreAVirgule
	 *            si inferieur au egale a zero, aucun chiffre n'est ajouté apres
	 *            la virgule. . Sofien CHARFI
	 */
	public static String arrondie(double d, int nbChiffreAVirgule) {
		String res = null;
		if (nbChiffreAVirgule > 0) {
			double xd = (d * java.lang.Math.pow(10, nbChiffreAVirgule));
			long xl = java.lang.Math.round(xd);
			double xd2 = xl / (java.lang.Math.pow(10, nbChiffreAVirgule));
			String xStr = xd2 + "";
			int positionPoint = xStr.indexOf(".");
			int longueur = xStr.length();
			if (longueur - positionPoint < (nbChiffreAVirgule + 1)) {
				res = xStr + "0";
			} else {
				res = xStr;
			}
		} else {
			res = "" + java.lang.Math.round(d);
		}
		return res;
	}

	/**
	 * Convertit un montant au format N.2 au format financier ex : 2012.25 -> 2
	 * 012.25 Attention, le format de départ doit etre au format N.2
	 */
	public static String arrondi2Financier(String formatArrondi) {

		// //System.out.println("formatArrondi = "+formatArrondi);

		// index qui délimite les bornes de la partie entiere du prix a traiter
		// car on va laisser la partie des centimes a l'identique.
		int indexDeb = formatArrondi.length() - 4;
		int indexFin = 0;
		float compteur = 0;

		StringBuffer result = new StringBuffer();

		for (int i = indexDeb; i >= indexFin; i--) {
			if (compteur % 3 == 0 && i != indexDeb) {
				result.insert(0, ' ');
			}
			compteur++;
			result.insert(0, formatArrondi.charAt(i));
		}
		result.append(formatArrondi.substring(formatArrondi.length() - 3,
				formatArrondi.length()));

		return result.toString();
	}



	/**
	 * Arrondie un double en double avec 2 chiffres significatifs apres la
	 * virgule.
	 */
	public static double arronDD2(double val) {
		double res = 0;
		
		String ds= String.valueOf(val);
		int posVir = ds.indexOf(".");
		res = Double.parseDouble(StaticNombres.arrondie(val, 2));
		
		return res;
	}

	/**
	 * Arrondie un double en double avec 4 chiffres significatifs apres la
	 * virgule.
	 */
	public static double arronDD4(double val) {
		double res = 0;
		res = Double.parseDouble(StaticNombres.arrondie(val, 4));
		return res;
	}
	
	/**
	 * Arrondie un double en double avec 4 chiffres significatifs apres la
	 * virgule.
	 */
	public static double arronDD5(double val) {
		double res = 0;
		res = Double.parseDouble(StaticNombres.arrondie(val, 5));
		return res;
	}

	/**
	 * Arrondie un float en float avec 2 chiffres significatifs apres la
	 * virgule.
	 */
	public static float arronFF2(double val) {
		float res = 0;
		res = Float.parseFloat(StaticNombres.arrondie(val, 2));
		return res;
	}

	/** Return true si la chaine est un entier */
	public static boolean isUnEntier(String chaineEntier) {
		boolean res = false;
		try {
			int entier = Integer.parseInt(chaineEntier);
			res = true;
		} catch (NumberFormatException nfe) {

			res = false;
		}
		return res;

	}

}