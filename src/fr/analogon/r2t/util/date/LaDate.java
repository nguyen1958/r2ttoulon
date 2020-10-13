package fr.analogon.r2t.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Titre : LaDate Description : Cette classe permet de gérer les dates
 * facilement sous plusieurs format (String, int, java.util.Date, long)
 * Copyright : Actif Plurimédia Copyright (c) 2002 Société : Actif Plurimédia . :
 * Frédéric Guillaume
 * 
 * @version 1.0
 */

public class LaDate {
	private java.util.Date laDateJava;

	// /////////////////
	// Constructeurs //
	// /////////////////

	/**
	 * Constructeur par défaut : intialise laDateJava avec la date d'aujourdh'ui .
	 * Frédéric Guillaume
	 * 
	 * @since 1.0
	 */
	public LaDate() {
		laDateJava = new java.util.Date();
	}

	/**
	 * Intialise laDateJava avec la date passée en paramétre
	 * 
	 * @param date :
	 *            long représentant le nombre de secondes depuis le 01/01/1970 .
	 *            Frédéric Guillaume
	 * @since 1.0
	 */
	public LaDate(long date) {
		laDateJava = new java.util.Date(date);
	}

	/**
	 * Intialise laDateJava avec la date passée en paramétre
	 * 
	 * @param date :
	 *            date java . Frédéric Guillaume
	 * @since 1.0
	 */
	public LaDate(java.util.Date date) {
		laDateJava = date;
	}

	/**
	 * Intialise laDateJava avec la date passée en paramétre
	 * 
	 * @param date :
	 *            entier représentant la date é l'envers (20020920) . Frédéric
	 *            Guillaume
	 * @since 1.0
	 */
	public LaDate(int date) throws ParseException, IllegalArgumentException {
		if (isValide(date)) {
			String dateString = String.valueOf(date);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			laDateJava = dateFormat.parse(dateString);
		} else {
			IllegalArgumentException erreur = new IllegalArgumentException(
					"La date n'est pas valide.");
			throw erreur;
		}
	}

	/**
	 * Intialise laDateJava avec la date passée en paramétre
	 * 
	 * @param date :
	 *            String représentant la date (20/09/2002) . Frédéric Guillaume
	 * @since 1.0
	 */
	public LaDate(String date) throws ParseException, IllegalArgumentException {
		if (isValide(date)) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			laDateJava = dateFormat.parse(date);
		} else {
			IllegalArgumentException erreur = new IllegalArgumentException(
					"La date n'est pas valide.");
			throw erreur;
		}
	}

	// ///////////////
	// Méthodes is //
	// ///////////////

	/**
	 * Cette fonction permet de savoir si cette date existe dans le calandrier
	 * gregorien
	 * 
	 * @param date :
	 *            String représentant la date (20/09/2002) . Frédéric Guillaume
	 * @since 1.0
	 */
	public boolean isValide(String date) {
		boolean resultat=false;
		if(date.length()==10){
		int jour = Integer.parseInt(date.substring(0, 2));
        int mois = Integer.parseInt(date.substring(3, 5));
        int annee = Integer.parseInt(date.substring(6));
        int anneeDuCalendrier;
        int moisDuCalendrier;
        int jourDuCalendrier;
        
        Calendar calendrier = new GregorianCalendar();
        Date laDate;

        calendrier.set(annee, (mois - 1), jour, 0, 0, 0);
        laDate = calendrier.getTime();

        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy");
        anneeDuCalendrier = Integer.parseInt(dateFormat1.format(laDate));

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM");
        moisDuCalendrier = Integer.parseInt(dateFormat2.format(laDate));

        SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd");
        jourDuCalendrier = Integer.parseInt(dateFormat3.format(laDate));


        if (annee == anneeDuCalendrier && mois == moisDuCalendrier && jour == jourDuCalendrier && date.substring(2, 3).equals("/") && date.substring(5, 6).equals("/") && date.length() == 10)
            resultat = true;
        else
            resultat = false;
		}
		else{
			resultat = false;
		}
        return resultat;
	}

	/**
	 * Cette fonction permet de savoir si cette date existe dans le calandrier
	 * gregorien
	 * 
	 * @param date :
	 *            entier représentant la date é l'envers (20020920) . Frédéric
	 *            Guillaume
	 * @since 1.0
	 */
	public boolean isValide(int date) {
		boolean resultat=false;
		String dateString = String.valueOf(date);
		if (dateString.length()==8){
        int jour = Integer.parseInt(dateString.substring(6));
        int mois = Integer.parseInt(dateString.substring(4, 6));
        int annee = Integer.parseInt(dateString.substring(0, 4));
        int anneeDuCalendrier;
        int moisDuCalendrier;
        int jourDuCalendrier;
        
        Calendar calendrier = new GregorianCalendar();
        Date laDate;

        calendrier.set(annee, (mois - 1), jour, 0, 0, 0);
        laDate = calendrier.getTime();

        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy");
        anneeDuCalendrier = Integer.parseInt(dateFormat1.format(laDate));

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM");
        moisDuCalendrier = Integer.parseInt(dateFormat2.format(laDate));

        SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd");
        jourDuCalendrier = Integer.parseInt(dateFormat3.format(laDate));


        if (annee == anneeDuCalendrier && mois == moisDuCalendrier && jour == jourDuCalendrier && dateString.length() == 8)
            resultat = true;
        else
            resultat = false;
		}
		else{
			resultat=false;
		}
        return resultat;
	}

	// ////////////////
	// Méthodes set //
	// ////////////////

	/**
	 * Intialise laDateJava avec la date d'aujourdh'ui . Frédéric Guillaume
	 * 
	 * @since 1.0
	 */
	public void setLaDate() {
		laDateJava = new java.util.Date();
	}

	/**
	 * Intialise laDateJava avec la date passée en paramétre
	 * 
	 * @param date :
	 *            date java . Frédéric Guillaume
	 * @since 1.0
	 */
	public void setLaDate(java.util.Date date) {
		laDateJava = date;
	}

	/**
	 * Intialise laDateJava avec la date passée en paramétre
	 * 
	 * @param date :
	 *            entier représentant la date é l'envers (20020920) . Frédéric
	 *            Guillaume
	 * @since 1.0
	 */
	public void setLaDate(int date) throws ParseException,
			IllegalArgumentException {
		if (isValide(date)) {
			String dateString = String.valueOf(date);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			laDateJava = dateFormat.parse(dateString);
		} else {
			IllegalArgumentException erreur = new IllegalArgumentException(
					"La date n'est pas valide.");
			throw erreur;
		}
	}

	/**
	 * Intialise laDateJava avec la date passée en paramétre
	 * 
	 * @param date :
	 *            String représentant la date (20/09/2002) . Frédéric Guillaume
	 * @since 1.0
	 */
	public void setLaDate(String date) throws ParseException,
			IllegalArgumentException {
		if (isValide(date)) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			laDateJava = dateFormat.parse(date);
		} else {
			IllegalArgumentException erreur = new IllegalArgumentException(
					"La date n'est pas valide.");
			throw erreur;
		}
	}

	/**
	 * Intialise laDateJava avec la date passée en paramétre
	 * 
	 * @param date :
	 *            long représentant le nombre de secondes depuis le 01/01/1970 .
	 *            Frédéric Guillaume
	 * @since 1.0
	 */
	public void setLaDate(long date) {
		laDateJava = new java.util.Date(date);
	}

	// ////////////////
	// Méthodes get //
	// ////////////////

	/**
	 * Retourne un String représantant la date au format normal (20/09/2002) .
	 * Frédéric Guillaume
	 * 
	 * @since 1.0
	 */
	public String getLaDateString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String laDate = dateFormat.format(laDateJava);
		return (laDate);
	}

	/**
	 * Retourne un entier représantant la date é l'envers (20020920) . Frédéric
	 * Guillaume
	 * 
	 * @since 1.0
	 */
	public int getLaDateInt() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		int laDate = Integer.parseInt(dateFormat.format(laDateJava));
		return (laDate);
	}

	/**
	 * Retourne la date au format java . Frédéric Guillaume
	 * 
	 * @since 1.0
	 */
	public java.util.Date getLaDateJava() {
		java.util.Date laDate = laDateJava;
		return (laDate);
	}

	/**
	 * Retourne un long représantant le nombre de secondes depuis le 01/01/1970 .
	 * Frédéric Guillaume
	 * 
	 * @since 1.0
	 */
	public long getLaDateLong() {
		long laDate = laDateJava.getTime();
		return (laDate);
	}

	/**
	 * Retourne un String représantant l'année (2002) . Frédéric Guillaume
	 * 
	 * @since 1.0
	 */
	public String getAnneeString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		String an = dateFormat.format(laDateJava);
		return (an);
	}

	/**
	 * Retourne un entier représantant l'année (2002) . Frédéric Guillaume
	 * 
	 * @since 1.0
	 */
	public int getAnneeInt() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		int an = Integer.parseInt(dateFormat.format(laDateJava));
		return (an);
	}

	/**
	 * Retourne un String représantant le mois (01-12) . Frédéric Guillaume
	 * 
	 * @since 1.0
	 */
	public String getMoisString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		String mois = dateFormat.format(laDateJava);
		return (mois);
	}

	/**
	 * Retourne un entier représantant le mois (1-12) . Frédéric Guillaume
	 * 
	 * @since 1.0
	 */
	public int getMoisInt() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		int mois = Integer.parseInt(dateFormat.format(laDateJava));
		return (mois);
	}

	/**
	 * Retourne un String représantant le nom du mois (janvier-décembre) .
	 * Frédéric Guillaume
	 * 
	 * @since 1.0
	 */
	public String getNomMois() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
		String nomMois = dateFormat.format(laDateJava);
		return (nomMois);
	}

	/**
	 * Retourne un String représantant le jour (01-31) . Frédéric Guillaume
	 * 
	 * @since 1.0
	 */
	public String getJourString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
		String jour = dateFormat.format(laDateJava);
		return (jour);
	}

	/**
	 * Retourne un entier représantant le jour (1-31) . Frédéric Guillaume
	 * 
	 * @since 1.0
	 */
	public int getJourInt() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
		int jour = Integer.parseInt(dateFormat.format(laDateJava));
		return (jour);
	}

	/**
	 * Retourne un entier représantant le jour de l'année (1-365) . Frédéric
	 * Guillaume
	 * 
	 * @since 1.0
	 */
	public int getJourDeAnnee() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("DD");
		int jour = Integer.parseInt(dateFormat.format(laDateJava));
		return (jour);
	}

	/**
	 * Retourne un String représantant le jour de la semaine (01-07) . Frédéric
	 * Guillaume
	 * 
	 * @since 1.0
	 */
	/*
	 * public String getJourDeLaSemaineString() { SimpleDateFormat dateFormat =
	 * new SimpleDateFormat("E"); String jour = dateFormat.format(laDateJava);
	 * return(jour); }
	 */

	/**
	 * Retourne un entier représantant le jour de la semaine (1-7) . Frédéric
	 * Guillaume
	 * 
	 * @since 1.0
	 */
	/*
	 * public int getJourDeLaSemaineInt() { }
	 */

	/**
	 * Retourne un String représantant le nom du jour (lundi-dimanche) .
	 * Frédéric Guillaume
	 * 
	 * @since 1.0
	 */
	public String getNomJour() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
		String nomJour = dateFormat.format(laDateJava);
		return (nomJour);
	}

	/**
	 * Retourne un String représantant le numéro de la semaine dans l'année
	 * (01-53) . Frédéric Guillaume
	 * 
	 * @since 1.0
	 */
	public String getNumeroSemaineString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("w");
		String numeroSemaine = dateFormat.format(laDateJava);
		return (numeroSemaine);
	}

	/**
	 * Retourne un entier représantant le numéro de la semaine dans l'année
	 * (1-53) . Frédéric Guillaume
	 * 
	 * @since 1.0
	 */
	public int getNumeroSemaineInt() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("w");
		int numeroSemaine = Integer.parseInt(dateFormat.format(laDateJava));
		return (numeroSemaine);
	}

	/* Retourne le numero du trimestre de 1 é 4 */
	public int getTrimestre() {
		int res = -1;
		int numMois = getMoisInt();
		if ((numMois > 0) && (numMois < 4)) {
			res = 1;
		}
		if ((numMois > 3) && (numMois < 7)) {
			res = 2;
		}
		if ((numMois > 6) && (numMois < 10)) {
			res = 3;
		}
		if ((numMois > 9) && (numMois <= 12)) {
			res = 4;
		}
		return res;
	}

	// ///////////////////
	// Méthodes autres //
	// ///////////////////

	/**
	 * Ajoute n jours é laDateJava
	 * 
	 * @param jour :
	 *            nombre de jour é ajouter ou é supprimer . Frédéric Guillaume
	 * @since 1.0
	 */
	public void AjouteJour(int jour) {
		GregorianCalendar calendrier = new GregorianCalendar();
		calendrier.setTime(laDateJava);
		calendrier.add(Calendar.DATE, jour);
		laDateJava = calendrier.getTime();
	}

	/**
	 * Ajoute n mois é laDateJava
	 * 
	 * @param mois :
	 *            nombre de mois é ajouter ou é supprimer . Frédéric Guillaume
	 * @since 1.0
	 */
	public void AjouteMois(int mois) {
		GregorianCalendar calendrier = new GregorianCalendar();
		calendrier.setTime(laDateJava);
		calendrier.add(Calendar.MONTH, mois);
		laDateJava = calendrier.getTime();
	}

	/**
	 * Ajoute n années é laDateJava
	 * 
	 * @param annee :
	 *            nombre d'années é ajouter ou é supprimer . Frédéric Guillaume
	 * @since 1.0
	 */
	public void AjouteAnnee(int annee) {
		GregorianCalendar calendrier = new GregorianCalendar();
		calendrier.setTime(laDateJava);
		calendrier.add(Calendar.YEAR, annee);
		laDateJava = calendrier.getTime();
	}

	/**
	 * Compare laDataJava é la date passée en paramétre Renvoi true si elles
	 * sont égales et false sinon
	 * 
	 * @param laDate :
	 *            la date é comparer . Frédéric Guillaume
	 * @since 1.0
	 */
	public boolean eguale(fr.analogon.r2t.util.date.LaDate laDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String laDateJavaString = dateFormat.format(laDateJava);
		if (laDateJavaString.equalsIgnoreCase(laDate.getLaDateString()))
			return true;
		else
			return false;
	}

	/**
	 * Compare laDataJava é la date passée en paramétre Renvoi true si
	 * laDataJava est inférieure é laDate et false sinon
	 * 
	 * @param laDate :
	 *            la date é comparer . Frédéric Guillaume
	 * @since 1.0
	 */
	public boolean inferieure(fr.analogon.r2t.util.date.LaDate laDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		int laDateJavaInt = Integer.parseInt(dateFormat.format(laDateJava));

		if (laDateJavaInt < laDate.getLaDateInt())
			return true;
		else
			return false;
	}

	/**
	 * Compare laDataJava é la date passée en paramétre Renvoi true si
	 * laDataJava est supérieure é laDate et false sinon
	 * 
	 * @param laDate :
	 *            la date é comparer . Frédéric Guillaume
	 * @since 1.0
	 */
	public boolean superieure(fr.analogon.r2t.util.date.LaDate laDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		int laDateJavaInt = Integer.parseInt(dateFormat.format(laDateJava));

		if (laDateJavaInt > laDate.getLaDateInt())
			return true;
		else
			return false;
	}

	/**
	 * Compare laDataJava é la date passée en paramétre Renvoi true si
	 * laDataJava est inférieure ou égale é laDate et false sinon
	 * 
	 * @param laDate :
	 *            la date é comparer . Frédéric Guillaume
	 * @since 1.0
	 */
	public boolean inferieureOuEguale(fr.analogon.r2t.util.date.LaDate laDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		int laDateJavaInt = Integer.parseInt(dateFormat.format(laDateJava));

		if (laDateJavaInt <= laDate.getLaDateInt())
			return true;
		else
			return false;
	}

	/**
	 * Compare laDataJava é la date passée en paramétre Renvoi true si
	 * laDataJava est supérieure ou égale é laDate et false sinon
	 * 
	 * @param laDate :
	 *            la date é comparer . Frédéric Guillaume
	 * @since 1.0
	 */
	public boolean superieureOuEguale(fr.analogon.r2t.util.date.LaDate laDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		int laDateJavaInt = Integer.parseInt(dateFormat.format(laDateJava));

		if (laDateJavaInt >= laDate.getLaDateInt())
			return true;
		else
			return false;
	}

	/**
	 * Renvoi le nombre de jours de différence entre laDateJava et la date
	 * passée en paramétre
	 * 
	 * @param laDate :
	 *            la date é comparer . Frédéric Guillaume
	 * @since 1.0
	 */
	public int joursDeDifference(fr.analogon.r2t.util.date.LaDate laDate) {
		int nombreDeJours = (int) ((laDateJava.getTime() - laDate
				.getLaDateLong()) / (1000 * 60 * 60 * 24));
		return nombreDeJours;
	}

}