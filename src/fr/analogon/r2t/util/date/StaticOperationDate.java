package fr.analogon.r2t.util.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Titre : Ensemble de methodes statiques concernant l'utilisation de dates. .
 * Sofien CHARFI
 * 
 * @version 2.2
 * @since 1.0
 */

public class StaticOperationDate {
	/**
	 * convertie un objet Date en int de la forme yyyy (option=1) ou
	 * yyyymm(option=2) ou yyyymmjj(option=3) ou jj(option 4) ou mm(option 5)
	 * 
	 * @param d
	 *            la date a convertir.
	 * @param option
	 *            l'option de convertion a choisir.
	 * @return la date convertie ... ou 00000000 si option mauvaise. . David
	 *         Gimelle
	 * @since 1.0
	 */
	public static int dateToInt(Date d, int option) {
		int res;

		if (option == 1) {
			SimpleDateFormat fAn = new SimpleDateFormat("yyyy");
			res = Integer.parseInt(fAn.format(d));
		} else if (option == 2) {
			SimpleDateFormat fMois = new SimpleDateFormat("yyyyMM");
			res = Integer.parseInt(fMois.format(d));
		} else if (option == 3) {
			SimpleDateFormat fJour = new SimpleDateFormat("yyyyMMdd");
			res = Integer.parseInt(fJour.format(d));
		} else if (option == 4) {
			SimpleDateFormat fJour = new SimpleDateFormat("dd");
			res = Integer.parseInt(fJour.format(d));
		} else if (option == 5) {
			SimpleDateFormat fMois = new SimpleDateFormat("MM");
			res = Integer.parseInt(fMois.format(d));
		} else
			res = 00000000;

		return res;
	}

	/**
	 * a partir des 3 valeurs passées parametre jour jj/mois mm/annee aaaa, cree
	 * un entier unique : aaaammjj . Sofien CHARFI
	 * 
	 * @since 1.0
	 */
	public static int regroupeDate(int jour, int mois, int annee) {
		String jj, mm, aa, res;

		jj = new Integer(jour).toString();
		mm = new Integer(mois).toString();
		aa = new Integer(annee).toString();

		res = regroupeDate(jj, mm, aa);

		return new Integer(res).intValue();
	}

	/**
	 * a partir des 3 valeurs passées parametre jour jj/mois mm/annee aaaa, cree
	 * un entier unique : aaaammjj ex : "2" "9" "2003" --->"20030902" . David
	 * Gimelle
	 * 
	 * @since 1.0
	 */
	public static String regroupeDate(String intJour, String intMois,
			String intAnnee) {

		if (intJour.length() == 1)
			intJour = '0' + intJour;

		if (intMois.length() == 1)
			intMois = '0' + intMois;

		return intAnnee + intMois + intJour;
	}

	/**
	 * Récupere a partir d'une date de la forme aaaammjj : aaaa si option = 1 mm
	 * si option = 2 jj si option = 3 0 sinon . Sofien CHARFI
	 * 
	 * @since 1.0
	 */
	public static int decomposeDate(int date, int option) {
		String res = new String();
		String s = new Integer(date).toString();

		res = decomposeDate(s, option);

		return Integer.parseInt(res);
	}

	/**
	 * Récupere a partir d'une date de la forme "aaaammjj" : "aaaa" si option =
	 * 1 "mm" si option = 2 "jj" si option = 3 "0" sinon . Sofien CHARFI
	 * 
	 * @since 1.0
	 */
	public static String decomposeDate(String date, int option) {
		String res = new String();

		try {
			if (date.equals("0"))
				res = "0";
			else if (option == 1)
				res = date.substring(0, 4);
			else if (option == 2)
				res = date.substring(4, 6);
			else if (option == 3)
				res = date.substring(6);
			else
				res = "0";
		} catch (Exception e) {
			System.out
					.println("erreur dans la classe : fr.analogon.r2t.util.date.OperationDate ---> decomposeDate");
		}

		return res;
	}

	/**
	 * Donne l'annee d'une date dans le calendrier gregorien
	 * 
	 * @test le 08/02/2002
	 * @param d
	 *            Date dont on veut connaitre l'annee gregorienne
	 * @return L'annee correspondant a cette Date dans le calendrier Gregorien .
	 *         Sofien CHARFI
	 * @since 1.0
	 */
	public static int yearOfDate(Date d) {
		SimpleDateFormat fYear = new SimpleDateFormat("yyyy");
		int year = Integer.parseInt(fYear.format(d));
		return (year);
	}

	/**
	 * Donne le mois d'une date dans le calendrier gregorien
	 * 
	 * @test le 08/02/2002
	 * @param d
	 *            Date dont on veut connaitre l'mois gregorienne
	 * @return L'annee correspondant a cette Date dans le calendrier Gregorien .
	 *         Sofien CHARFI
	 * @since 1.0
	 */
	public static int monthOfDate(Date d) {
		SimpleDateFormat fMonth = new SimpleDateFormat("MM");
		int year = Integer.parseInt(fMonth.format(d));
		return (year);
	}

	/**
	 * Donne le jour d'une date dans le calendrier gregorien
	 * 
	 * @param d
	 *            Date dont on veut connaitre le jour greporien
	 * @return Le jour correspondant a cette Date dans le calendrier Gregorien .
	 *         Sofien CHARFI
	 * @since 2.2
	 */
	public static int dayOfDate(Date d) {
		SimpleDateFormat fMonth = new SimpleDateFormat("dd");
		int year = Integer.parseInt(fMonth.format(d));
		return (year);
	}

	/**
	 * renvoie le numero de semaine a partir d'une Date . Sofien CHARFI
	 * 
	 * @since 1.0
	 */
	public static int numberWeek(Date d) {
		int res;
		SimpleDateFormat formatDate = new SimpleDateFormat("ww");
		res = Integer.parseInt(formatDate.format(d)) - 1;
		return res;
	}

	/**
	 * Cette fonction permet de savoir si cette date existe dans le calandrier
	 * gregorien . Sofien CHARFI
	 * 
	 * @since 1.0
	 */
	public static boolean isValide(int jour, int mois, int annee) {
		boolean res;
		Calendar calendrier = new GregorianCalendar();
		int moisDuCalendrier;
		Date laDate;
		// //System.out.println("mois: " + mois);
		calendrier.set(annee, (mois - 1), jour, 0, 0, 0);
		laDate = calendrier.getTime();
		moisDuCalendrier = StaticOperationDate.monthOfDate(laDate);
		// //System.out.println("moisDuCalendrier: " + moisDuCalendrier);
		if (mois == moisDuCalendrier)
			res = true;
		else
			res = false;

		return res;
	}

	/**
	 * ajoute a une date, des jours, mois , annee et retourne la date
	 * correspondante.
	 * 
	 * @param date
	 *            la date de reference
	 * @param jour,
	 *            le nb de jours a ajouter
	 * @param mois,
	 *            le nb de mois a ajouter
	 * @param annee,
	 *            le nb d'anne a rajouter
	 * @return Date le date obtenue en ajoutant jour/mois/annee a la date passée
	 *         en parametre. . Sofien CHARFI
	 * @since 1.0
	 */
	public static Date AjouteJourMoisAnnee(Date date, int jour, int mois,
			int annee) {
		GregorianCalendar calendrier = new GregorianCalendar();
		calendrier.setTime(date);
		calendrier.add(Calendar.DATE, jour);
		calendrier.add(Calendar.MONTH, mois);
		calendrier.add(Calendar.YEAR, annee);
		return calendrier.getTime();
	}

	/**
	 * Donne en entree trois String et on retourne une date au format date java.
	 * 
	 * @param jour
	 * @param mois
	 * @param annee
	 * @return Date le date obtenue en ajoutant jour/mois/annee a la date passée
	 *         en parametre. Retourne une dateSQL pour l'insertion dans la base .
	 *         Vincent Michal
	 * @since 2.1
	 */
	public static java.sql.Date stringToDate(String jour, String mois,
			String annee) {
		String dateExp = annee + "-" + mois + "-" + jour;
		java.sql.Date dateExpirationContrat = java.sql.Date.valueOf(dateExp);
		return dateExpirationContrat;
	}

	/**
	 * Donne en entree trois int et on retourne une date au format date java.
	 * 
	 * @param jour
	 * @param mois
	 * @param annee
	 * @return Date le date obtenue en ajoutant jour/mois/annee a la date passée
	 *         en parametre. Retourne une dateSQL pour l'insertion dans la base .
	 *         Vincent Michal
	 * @since 2.1
	 */
	public static java.sql.Date intToDate(int jour, int mois, int annee) {
		String dateExp = String.valueOf(annee) + "-" + String.valueOf(mois)
				+ "-" + String.valueOf(jour);
		java.sql.Date dateExpirationContrat = java.sql.Date.valueOf(dateExp);
		return dateExpirationContrat;
	}

	/**
	 * Permet le reformatage d'un date.
	 * 
	 * @param date
	 * @param option
	 *            format de la date 0 : sortie brut de la base de donnée 1 :
	 *            jour 2 : mois 3 : annee 4 : date formate francais avec - de
	 *            separation 5 : date formate francais avec / de separation
	 *            Rajoute les zeros qui manque pour bien donné des dates au
	 *            formats jj/mm/aaaa
	 * @return String la date formatte. . Vincent Michal
	 * @since 2.2
	 */
	public static String reformatageDateSQL(java.sql.Date date, int option) {
		Date javaDate = new Date(date.getTime());
		String resultat = "";

		// Renvoie le date brut non formaté
		if (option == 0) {
			resultat = date.toString();
		}
		// Renvoie le jour de la date passé en parametre au format jj
		if (option == 1) {
			String jourT = String.valueOf(dateToInt(javaDate, 4));
			if (jourT.length() < 2)
				jourT = "0" + jourT;
			resultat = jourT;
		}
		// Renvoie le mois de la date passé en parametre au format mm
		if (option == 2) {
			String moisT = String.valueOf(dateToInt(javaDate, 5));
			if (moisT.length() < 2)
				moisT = "0" + moisT;
			resultat = moisT;
		}
		// Renvoie l'annee de la date passé en parametre au format aaaa
		if (option == 3) {
			String anneeT = String.valueOf(dateToInt(javaDate, 1));
			int nbcChar = anneeT.length();
			if (anneeT.length() < 4) {
				int nbCharAAjoute = 4 - anneeT.length();
				for (int i = 0; i < nbCharAAjoute; i++) {
					anneeT = "0" + anneeT;
				}
			}
			resultat = anneeT;
		}

		/*
		 * Renvoie la date reformaté au format jj-mm-aaaa avec l'option 4 et
		 * auformat jj/mm/aaaa avec l'option 5
		 */
		if ((option == 4) || (option == 5)) {
			String dateReformate = "";
			String jourT = String.valueOf(dateToInt(javaDate, 4));
			if (jourT.length() < 2)
				jourT = "0" + jourT;
			String moisT = String.valueOf(dateToInt(javaDate, 5));
			if (moisT.length() < 2)
				moisT = "0" + moisT;
			String anneeT = String.valueOf(dateToInt(javaDate, 1));
			int nbcChar = anneeT.length();
			if (anneeT.length() < 4) {
				int nbCharAAjoute = 4 - anneeT.length();
				for (int i = 0; i < nbCharAAjoute; i++) {
					anneeT = "0" + anneeT;
				}
			}
			if (option == 4) {
				dateReformate = jourT + "-" + moisT + "-" + anneeT;
			}
			if (option == 5) {
				dateReformate = jourT + "/" + moisT + "/" + anneeT;
			}
			resultat = dateReformate;
		}

		return resultat;
	}

	/**
	 * Permet de décomoser une date au format jj/mm/aaaa en String selon
	 * l'option. Le séparateur peut etre différent de /.
	 * 
	 * @param date
	 * @param option
	 *            format de la date 0 : date d'entree 1 : jour 2 : mois 3 :
	 *            annee
	 * @return String la date formatte. . Vincent Michal
	 * @since 2.2
	 */
	public static String decomposeDateFormate(String date, int option) {
		String res = new String();
		if (option == 0)
			res = date;
		if (option == 1)
			res = date.substring(0, 2);
		if (option == 2)
			res = date.substring(3, 5);
		if (option == 3)
			res = date.substring(6, 10);
		return res;
	}

	/**
	 * Donne l'heure d'une date dans le calendrier gregorien
	 * 
	 * @param d
	 *            Date dont on veut connaitre les heures
	 * @return L'heure . Sofien CHARFI
	 * @since 1.0
	 */
	public static int heureDate(Date d) {
		SimpleDateFormat form = new SimpleDateFormat("hh");
		int res = Integer.parseInt(form.format(d));
		return (res);
	}

	/**
	 * Donne les secondes d'une date dans le calendrier gregorien
	 * 
	 * @param d
	 *            Date dont on veut connaitre les heures
	 * @return L'heure . Sofien CHARFI
	 * @since 1.0
	 */
	public static int secondDate(Date d) {
		SimpleDateFormat form = new SimpleDateFormat("ss");
		int res = Integer.parseInt(form.format(d));
		return (res);
	}

	/**
	 * Donne les minutes d'une date dans le calendrier gregorien
	 * 
	 * @param d
	 *            Date dont on veut connaitre les heures
	 * @return L'heure . Sofien CHARFI
	 * @since 1.0
	 */
	public static int minuteDate(Date d) {
		SimpleDateFormat form = new SimpleDateFormat("mm");
		int res = Integer.parseInt(form.format(d));
		return (res);
	}

	/**
	 * Retourne la date sous la forme AAAAMMDDHHMMSS . Sofien CHARFI
	 */
	public static String dateAAAAMMDDHHMMSS(Date d) {
		SimpleDateFormat form = new SimpleDateFormat("yyyyMMddHHmmss");
		String res = form.format(d);
		return (res);
	}

	/**
	 * Retourne true si l'année est bisextile - Sofien CHARFI 02.11.2005 Les
	 * régles de l'année bisextile: 1 une année est bisextile tous les 4 ans. 2
	 * Mais tous les 100 ans il n'y en as pas. 3 Mais tous les 400 ans la regle
	 * 2 est fausse. 4 une année bisextile est tjrs divisible par 4. 5 1900
	 * n'est pas bisextile, 2000 et 1996 le sont ainsi que 2004. Bref tous les
	 * année divisible par 4 sont bisectile sauf celle divisible par 100 mais
	 * pas par 400.
	 */
	public static boolean isUneAnneeBisextile(int annee) {
		boolean res = false;
		int modulo4 = 0;
		int modulo100 = 0;
		int modulo400 = 0;
		modulo4 = annee % 4;
		modulo100 = annee % 100;
		modulo400 = annee % 400;
		if (modulo4 == 0) {
			if (modulo100 == 0) {
				if (modulo400 == 0) {
					res = true;
				} else {
					res = false;
				}
			} else {
				res = true;
			}
		} else {
			res = false;
		}
		return res;
	}

	/** Retourne le mois en francais en fonction du numero de mois */
	public static String moisEnFrancais(int numMois) {
		String res = null;
		if (numMois == 1)
			res = "Janvier";
		if (numMois == 2)
			res = "Février";
		if (numMois == 3)
			res = "Mars";
		if (numMois == 4)
			res = "Avril";
		if (numMois == 5)
			res = "Mai";
		if (numMois == 6)
			res = "Juin";
		if (numMois == 7)
			res = "Juillet";
		if (numMois == 8)
			res = "Aéut";
		if (numMois == 9)
			res = "Septembre";
		if (numMois == 10)
			res = "Octobre";
		if (numMois == 11)
			res = "Novembre";
		if (numMois == 12)
			res = "Décembre";
		return res;
	}
	
	/**retourne l'heure de la date sous la forme 194611*/
	public static int heureDeLaDate(Date d){
		String res = heureDate(d)+""+minuteDate(d)+""+secondDate(d); 
		return Integer.parseInt(res);
	}
	
	/**retourne la date sous la forme dd/mm/yyyy HH:mm:ss*/
	public static String dateEtHeureFrancaise(Date d){
		SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
		String res = form.format(d);
		return (res);
	}
}