package fr.analogon.r2t.Utilitaire;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import fr.analogon.r2t.main.DebuggerLog4J;


public final class GestionDate 
{

	public static String ajouterNombreMoisFinPeriode(String date, int nombreDeMois )
	{
		
		String  res ="";
		int moisInput = Integer.parseInt(GestionDate.getMoisFromDateString(date));
		int nombreDejour = getNombreDeJourDuMois(date);	
		//System.out.println("nombreDejour du mois "+ nombreDejour);
		//String jour= date.substring(0,2);
	
		res = validerUneDate(res);
		return (res);
	
	}

	
	public static String ajouterNombreAnnees(String date, int nombreDeAnnee )
	{
		String res;
		String jourInput = GestionDate.getJourFromDateString(date);
		String moisInput = GestionDate.getMoisFromDateString(date);		
		int anneeOut = Integer.parseInt(GestionDate.getAnneeFromDateString(date)) + nombreDeAnnee ;							
		res= jourInput+"/"+moisInput+"/"+anneeOut;
		return res;
	}
	
	
	public static String ajouterNombreSemaine(String date, int nombreDeSemaine )
	{
		////System.out.println("debut="+date);
		////System.out.println("Nombre de jour ="+6*nombreDeSemaine);
		String res =addDays(date, 6*nombreDeSemaine);
		res = validerUneDate(res);
		return  res;
	}
	
	public static String ajouterNombreSemaineComplet(String date, int nombreDeSemaine )
	{		
		String res =addDays(date, 7*nombreDeSemaine);
		res = validerUneDate(res);
		return  res;
	}
	
	
	public static String ajouterNombreJour(String date, int nombreDeJour )
	{
		String res = addDays(date, nombreDeJour);
		res = validerUneDate(res);
		return res;
	}
	
	

	
	
	public static String getProchainControle(String dateDerniercontrole,String periodicite) throws Exception 
	{
		String res="00/00/0000";		
	
		if(periodicite.equalsIgnoreCase("jour"))
		{
			////System.out.println("Periode jour");				
		}
		else if(periodicite.equalsIgnoreCase("semaine") || periodicite.equalsIgnoreCase("mois") )
		{
			
			SimpleDateFormat  sdf1=  new SimpleDateFormat("dd/MM/yyyy");			 
			Date dateProchainControl = sdf1.parse(dateDerniercontrole);
			if (periodicite.equalsIgnoreCase("semaine"))
			{
				////System.out.println("Periode semaine");
				dateProchainControl.setDate(dateProchainControl.getDate()+8);
			}
			if (periodicite.equalsIgnoreCase("mois"))
			{
				////System.out.println("Periode mois");
				dateProchainControl.setDate(dateProchainControl.getDate()+31);
			}
			Calendar calendar = Calendar.getInstance();
		    calendar.setTime(dateProchainControl);	  
		    String jour= String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)); 
			String mois= String.valueOf(dateProchainControl.getMonth()+1);
			if (jour.length()==1) jour="0"+jour;
			if (mois.length()==1) mois="0"+mois;			
			String annee= String.valueOf(dateProchainControl.getYear()+1900);
			res=jour+"/"+mois+"/"+annee;
		}	
		else if(periodicite.equalsIgnoreCase("an"))
		{			
			////System.out.println("Periode annee");
			SimpleDateFormat  sdf1=  new SimpleDateFormat("dd/MM/yyyy");			 
			Date dateProchainControl = sdf1.parse(dateDerniercontrole);
			res = "01/01/"+String.valueOf(dateProchainControl.getYear()+1900+1);
		}
		else
		{
			////System.out.println("Periode inconnu");			
		}			
		return res;	
	}
	
	
	/**
	 * Methode qui permet de retourner comparer des dates 
	 * CHARFI Sofien
	 *  
	 * @since 2.0
	 */
	public static int comparerDate(String date1, String date2)	
	{
		//retourne 0 si d1 = d2
		//retourne 1 si d1 > d2
		//retourne -1 si d1 < d2
		
		  int res=0;
		  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	   
	    Date d1,d2;
		  try 
		  {
		 	d1 = sdf.parse(date1);
		 	d2 = sdf.parse(date2);
		 	if(d1.before(d2))res=1;
		 	if(d1.after(d2))res=-1;
		 	if(d1.equals(d2))res=0;
		 }
		  catch (ParseException e) {DebuggerLog4J.logger.fatal(e.getMessage());}     
		  return res;
		  
	}
	
	/**
	 * Methode qui permet de retourner le nombre de jour  a partir d'une datee
	 * CHARFI Sofien
	 *  
	 * @since 2.0
	 */

	public static int getNombreDeJour(String debut) 
	{
		int res=0;
		try 
		{
			if(debut.length() != 0)
			{
				 SimpleDateFormat  sdf1=  new SimpleDateFormat("dd/MM/yyyy");		 		 
				 Date debut2 = sdf1.parse(debut);
				 Date maDateAvecFormat=new Date();  		
			  	 SimpleDateFormat dateAujourdhui = new SimpleDateFormat("yyyy/MM/dd");  	
			  	 Date fin= new Date(dateAujourdhui.format(maDateAvecFormat));
				 long diff = 1+(fin.getTime() - debut2.getTime()) / (24*60*60*1000) ;		 
				 res= (int)diff;
			}
		} 
		catch (Exception e) { DebuggerLog4J.logger.fatal(e.getMessage()); }
		return res;	 	
	}


	/**
	 * Methode qui permet de retourner le nombre de semaine  a partir d'une datee
	 * CHARFI Sofien
	 *  
	 * @since 2.0
	 */
	public static int getNombreDeSemaine(String debut) 
	{
		int res=0;
		try 
		{
			 SimpleDateFormat  sdf1=  new SimpleDateFormat("dd/MM/yyyy");		 		 
			 Date debut2 = sdf1.parse(debut);
			 Date maDateAvecFormat=new Date();  		
		  	 SimpleDateFormat dateAujourdhui = new SimpleDateFormat("yyyy/MM/dd");  	
		  	 Date fin= new Date(dateAujourdhui.format(maDateAvecFormat));
		  	 long diff =  (1+(fin.getTime() - debut2.getTime()) / (24*60*60*1000));	  	
		  	 if(diff%7!=0)
		  	 {
		  		 diff=diff/7+1;
		  	 }
		  	 else
		  		diff=diff/7;	 
			 res= (int)diff;	
			
		} 
		catch (Exception e) { DebuggerLog4J.logger.fatal(e.getMessage()); }
		return res;		
	}



	/**
	 * Methode qui permet de retourner le nombre de mois  a partir d'une datee
	 * CHARFI Sofien
	 *  
	 * @since 2.0
	 */
	public static int getNombreDeMois(String debut) 
	{
		int res=0;
		try 
		{
		 SimpleDateFormat  sdf1=  new SimpleDateFormat("dd/MM/yyyy");		 		 
		 Date debut2 = sdf1.parse(debut);
		 Date maDateAvecFormat=new Date();  		
		 SimpleDateFormat dateAujourdhui = new SimpleDateFormat("yyyy/MM/dd");  	
		 Date fin= new Date(dateAujourdhui.format(maDateAvecFormat));
		 long diff =  ((fin.getTime() - debut2.getTime()) / (24*60*60*1000));	  	
		 if(diff%30!=0)
		 {	  		
		 		 diff=diff/30+1;
		 }
		 else
		diff=diff/30;	 
			res= (int)diff;
		} 
		catch (Exception e)	{DebuggerLog4J.logger.fatal(e.getMessage());}
		return res ;		
	}

	/**
	 * Methode qui permet de retourner le nombre d'annee  a partir d'une datee
	 * CHARFI Sofien
	 *  
	 * @since 2.0
	 */

	public static int getNombreDannee(String debut) 
	{	
		int res=0;
		try 
		{
			 SimpleDateFormat  sdf1=  new SimpleDateFormat("dd/MM/yyyy");		 		 
			 Date debut2 = sdf1.parse(debut);
			 Date maDateAvecFormat=new Date();  		
		  	 SimpleDateFormat dateAujourdhui = new SimpleDateFormat("yyyy/MM/dd");  	
		  	 Date fin= new Date(dateAujourdhui.format(maDateAvecFormat));
		  	 long diff =  (1+(fin.getTime() - debut2.getTime()) / (24*60*60*1000));	  	
		  	 if(diff%30!=0)
		  	 {	  		
		  		 diff=diff/365+1;
		  	 }
		  	 else
		  		diff=diff/365;	 
			 res= (int)diff;
			
		} 
		catch (Exception e) { DebuggerLog4J.logger.fatal(e.getMessage()); }
		return res;			
	} 
	
	public static Date getDateFromString(String date)
	{
		Date res=null;
		SimpleDateFormat dateStandard = new SimpleDateFormat("dd/MM/yyyy");
		try 
		{
			res = dateStandard.parse(date);
		} 
		catch (ParseException e){DebuggerLog4J.logger.fatal(e.getMessage());}
		return res;
	}
	
	public static String getJourFromDateString(String date)
	{
		return date.substring(0, 2);
	}
	
	public static String getMoisFromDateString(String date)
	{ 			
		return date.substring(3, 5);
		
	}
	
	public static String getAnneeFromDateString(String date)
	{
		return date.substring(6, 10);
	}
	
	public static String getAnneeCourante()
	{
		String res="";
		String date = GestionDate.getDateAujourdhuiString();
		Date d=getDateFromString(date);
		res = String.valueOf(d.getYear() +1900);		
		return res;
	}
	
	public static int getTrimestreCourant()
	{
		int res= 1;
		String date = GestionDate.getDateAujourdhuiString();
		Date d=getDateFromString(date);		
		int m = d.getMonth() +1;
		System.out.println(m);
		if(m <= 3) res = 1;
		else if(m > 3 && m <= 6) res = 2;
		else if(m > 6 && m <= 9) res = 3;
		else res = 4;
		return res;
	}
	
	public static String getDateAujourdhuiString()
	{
		String res="";
		SimpleDateFormat dateStandard = new SimpleDateFormat("dd/MM/yyyy");		 
		Date maDateAvecFormat=new Date();	
		res=dateStandard.format(maDateAvecFormat).toString();		
		return res;
	}
	
	public static String getTimeNow()
	{
		String res="";
		StringTokenizer st = new StringTokenizer(new Date().toLocaleString()," ");
		st.nextElement();
		st.nextElement();
		st.nextElement();
		res= (String)st.nextElement();	
		return res;
	}
	
	public static int getNumeroPeriode(String debut,String dateFin, String typePeriode)
	{
		int res=1;	
		//////System.out.println("DEBUT="+debut);
		//////System.out.println("FIN="+dateFin);
		//////System.out.println("TYPE DE PERIODE="+typePeriode);
		if(typePeriode.equalsIgnoreCase("jour"))
		{
			res=  getNombreDeJour (debut,dateFin);
			//System.out.println("***************************************");
			//System.out.println("***************************************");
			//System.out.println("Debut = "+debut);
			//System.out.println("Nombre de jour= "+res);
			//System.out.println("Fin = "+res);
			//System.out.println("***************************************");
			//System.out.println("***************************************");
		}
		else if(typePeriode.equalsIgnoreCase("semaine"))
		{
			res=  getNombreDeSemaine(debut,dateFin);
			//////System.out.println("Nombre de semaine= "+res);
		}
		else if(typePeriode.equalsIgnoreCase("mois"))
		{
			res=  getNombreDeMois(debut,dateFin);
			////System.out.println("Nombre de mois= "+res);
		}
		else if(typePeriode.equalsIgnoreCase("trimestre"))
		{
			res=  getNombreDeTrimestre(debut,dateFin);
			////System.out.println("Nombre de mois= "+res);
		}
		else if(typePeriode.equalsIgnoreCase("an"))
		{
			res=  getNombreDannee(debut,dateFin);
			
			////System.out.println("-----------------------------");
			////System.out.println("Debut= "+debut);
			////System.out.println("Fin= "+dateFin);
			////System.out.println("Nombre d'annee= "+res);
			////System.out.println("-----------------------------");
			
		}		
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Methode qui permet de retourner le nombre de jour  a partir d'une datee
	 * CHARFI Sofien
	 *  
	 * @since 2.0
	 */

	public static int getNombreDeJour(String debut, String fin) 
	{
		int res=1;
		try 
		{
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date1 = sdf.parse(debut);
			GregorianCalendar gc1 = new GregorianCalendar();
			gc1.setTime(date1);
			Date date2 = sdf.parse(fin);
			GregorianCalendar gc2 = new GregorianCalendar();
			gc2.setTime(date2);			
			gc1.add(GregorianCalendar.DAY_OF_YEAR, 1);			
			while(gc1.compareTo(gc2)<=0) 
			{
				res++;
			     gc1.add(GregorianCalendar.DAY_OF_YEAR, 1);
			}
			
		
			/*
			DebuggerLog4J.logger.debug("Debut="+debut);
			DebuggerLog4J.logger.debug("Fin="+fin);
			DebuggerLog4J.logger.debug("NombreDeJour="+res);
			*/
			
		} 
		catch (Exception e) { DebuggerLog4J.logger.fatal(e.getMessage()); }
		return res;	 	
	}
	

	/**
	 * Methode qui permet de retourner le nombre de semaine  a partir d'une datee
	 * CHARFI Sofien
	 *  
	 * @since 2.0
	 */
	public static int getNombreDeSemaine(String debut, String fin)
	{
		int res=0;
		try 
		{
			int nombreDeJour= getNombreDeJour( debut, fin);
			int nombreDeSemaine ; 
				if (nombreDeJour % 7 ==0 )
					nombreDeSemaine = nombreDeJour / 7;
				else
					nombreDeSemaine = (nombreDeJour / 7) +1;
			res = nombreDeSemaine; 
			
		} 
		catch (Exception e) { DebuggerLog4J.logger.fatal(e.getMessage()); }
		return res;		
	}


	
	/**
	 * Methode qui permet de retourner le nombre de trimestre a partir d'une datee
	 * CHARFI Sofien
	 *  
	 * @since 2.0
	 */
	public static int getNombreDeTrimestre(String debut, String fin) 
	{
		int res = 1;
		////System.out.println("DEBUT=="+debut);
		////System.out.println("FIN=="+fin);
		try 
		{
			int nombreDeMois = getNombreDeMois(debut, fin);
			
			if (nombreDeMois < 4)  
				res =1;
			else
			{
				if (nombreDeMois % 3 == 0  )  
					res = nombreDeMois /3 ;
				else
					res = 1 + nombreDeMois / 3 ;
				
			}
		} 
		catch (Exception e)	{DebuggerLog4J.logger.fatal(e.getMessage());}
		return res ;		
	}
	
	

	/**
	 * Methode qui permet de retourner le nombre de mois  a partir d'une datee
	 * CHARFI Sofien
	 *  
	 * @since 2.0
	 */
	public static int getNombreDeMois(String debut, String fin) 
	{
		int res = 1;
		////System.out.println("DEBUT=="+debut);
		////System.out.println("FIN=="+fin);
		try 
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date1 = sdf.parse(debut);
			GregorianCalendar gc1 = new GregorianCalendar();
			gc1.setTime(date1);
			Date date2 = sdf.parse(fin);
			GregorianCalendar gc2 = new GregorianCalendar();
			gc2.setTime(date2);
			
			gc1.add(GregorianCalendar.MONTH, 1);
			while(gc1.compareTo(gc2)<=0) 
			{
				res++;
			     gc1.add(GregorianCalendar.MONTH, 1);
			}		 	
		} 
		catch (Exception e)	{DebuggerLog4J.logger.fatal(e.getMessage());}
		return res ;		
	}
	
	/*
	public static int getNombreDeMois(String debut, String fin) 
	{
		int res=0;
		try 
		{
		 SimpleDateFormat  sdf1=  new SimpleDateFormat("dd/MM/yyyy");		 		 
		 Date debut2 = sdf1.parse(debut);
		 Date maDateAvecFormat=new Date();  		
		 SimpleDateFormat dateAujourdhui = new SimpleDateFormat("yyyy/MM/dd");  	
		 Date fin2= sdf1.parse(fin);
		 long diff =  ((fin2.getTime() - debut2.getTime()) / (24*60*60*1000));	  	
		 if(diff%30!=0)
		 {	  		
		 		 diff=diff/30+1;
		 }
		 else
		diff=diff/30;	 
			res= (int)diff;
		} 
		catch (Exception e)	{debug.logger.fatal(e.getMessage());}
		return res ;		
	}
*/

	/**
	 * Methode qui permet de retourner le nombre d'annee  a partir d'une datee
	 * CHARFI Sofien
	 *  
	 * @since 2.0
	 */

	public static int getNombreDannee(String debut, String fin) 
	{	
		int mois=0;
		try 
		{
			 DebuggerLog4J.logger.debug("debut="+debut);
			 DebuggerLog4J.logger.debug("fin="+fin);
			 String anneDebut = GestionDate.getAnneeFromDateString(debut);
			 String anneFin = GestionDate.getAnneeFromDateString(fin);
			 int i =Integer.parseInt(anneFin) -Integer.parseInt(anneDebut)+1;
			 return (i);
		} 
		catch (Exception e) 
		{
			DebuggerLog4J.logger.fatal(e.getMessage());
		}
		return mois;			
	} 
	
	public static int getNombreDanneeSeulement(String debut, String fin) 
	{	
		int res=0;	
		try 
		{
			SimpleDateFormat  sdf1=  new SimpleDateFormat("dd/MM/yyyy");	
			Date debut2 = sdf1.parse(debut);
			int idebut2= debut2.getYear();
			SimpleDateFormat  sdf2=  new SimpleDateFormat("dd/MM/yyyy");	
			Date fin2 = sdf2.parse(fin);	
			int ifin2= fin2.getYear();
			res= ifin2 - idebut2;
		} 
		catch (Exception e) { DebuggerLog4J.logger.fatal(e.getMessage()); }
		return res;			
	} 
	
	
	


	
	public static boolean contient366Jour(String annee)
	{
		boolean res=false;
		res =new GregorianCalendar().isLeapYear(Integer.valueOf(annee));
		return res;
	}
	
	public static String  getDateTime()
	{		
		return getDateAujourdhuiString() + " - " + getTimeNow();
	}
	
	public static String addDays(String date, int nombreDeJour)
	{
		for (int i = 0; i < nombreDeJour; i++) 
		{
			date =addaDay(date);
		}
		return date;
	
	}
	
	
	
	public static String addaDay(String date){

		String day = date.substring(0, 2);	
		String months = date.substring(3,5);	
		String  years= date.substring(6, 10);
		
		String result=day+"/"+months+"/"+years;
		////System.out.println(result);
		int iday = Integer.parseInt(day);
		int imonth = Integer.parseInt(months);
		int iyears = Integer.parseInt(years);

		if(iday<28){
		iday++;
		}
		else if(iday>=28&&iday<30){
//		every months has more than 29 day expect february
		if(imonth!=2){
		iday++;
		}
		else{
//		in bisextile year february has 29 days
		if(iday==28&&(iyears%4)!=0){
		imonth++;
		iday=1;
		}
		else iday++;
		}
		}
		else if(iday==30){
//		months with 31 day
		if(imonth==1||imonth==3||imonth==5||imonth==7||imonth==8||imonth==10||imonth==12){

		iday++;

		}
		else{
		imonth++;
		iday=1;
		}

		}
		else if(iday==31){
		if(imonth!=12){
		imonth++;
		iday=1;
		}
		if(imonth==12){
		iday=1;
		imonth=1;
		iyears=iyears+1;
		}
		}

		if(iday<10){
		day="0"+iday;
		}
		else{
		day=""+iday;
		}
		if(imonth<10){
		months="0"+imonth;
		}
		else{
		months=""+imonth;
		}
		 result=day+"/"+months+"/"+iyears;
		 return result;
	}
	
	
	public static String getDernierJOurDuMois (String date)
	{
		String res="" ;
		String mois =getMoisFromDateString(date);
		String annee =getAnneeFromDateString(date);
		res =validerUneDate("31/"+mois+"/"+annee);		
		return res;
	}
	
	public static String getDernierJOurDuTrimestre (String date)
	{
		String res="" ;
		try {
			res =ajouterNombreTrimestre("DFin", date, 1);
		} 
		catch (Exception e) { DebuggerLog4J.logger.fatal(e.getMessage()); }			
		return res;
	}
	
	public int getNumeroDuTrimestre (String date)
	{
		String mois= getMoisFromDateString(date);
		int moisi = Integer.parseInt(mois);
		if (moisi <=3) moisi = 1 ;
		else if ( moisi >3 && moisi <=6  ) moisi = 2;
		else if (moisi >6 && moisi <=9 ) moisi = 3;
		else if (moisi >9 ) moisi = 4;
		return moisi;
	}
	
	public int getNombreDeJourDuTrimestre (String date)
	{		
		int numeroTrimestre = getNumeroDuTrimestre(date);
		String annee = getAnneeFromDateString(date);
		int nombreJourTrimestre = 0;
		if (numeroTrimestre == 1)
		{
			if(GestionDate.contient366Jour(annee))
				nombreJourTrimestre = 91;
			else
				nombreJourTrimestre = 90;
		}
		else if (numeroTrimestre == 2)
			nombreJourTrimestre = 91; 
		else if (numeroTrimestre == 3)
			nombreJourTrimestre = 92;
		else if (numeroTrimestre == 4)
			nombreJourTrimestre = 92;
		
	
		return nombreJourTrimestre;			
			
	}
	
	
	
	
	public static String validerUneDate(String dateAVerifier)	
	{
		String jour = getJourFromDateString(dateAVerifier);
		String mois = getMoisFromDateString(dateAVerifier);
		String annee = getAnneeFromDateString(dateAVerifier);
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(Calendar.MONTH, Integer.parseInt(mois));//mois suivant  
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);//on enlève un jour pour passer au dernier jour du mois précédent		
		
		int nombreDeJour = calendar.get(Calendar.DAY_OF_MONTH);
		if (contient366Jour(annee) && mois.equalsIgnoreCase("02")) nombreDeJour =29;
		if (! contient366Jour(annee) && mois.equalsIgnoreCase("02")) nombreDeJour =28;
		int jourDuMoisEntree = Integer.parseInt(jour);
		if(jourDuMoisEntree > nombreDeJour)
		{			
			dateAVerifier = "" + nombreDeJour + dateAVerifier.substring(2,dateAVerifier.length());		
		}
		return dateAVerifier;
	}
	
	public static int getNombreDeJourDuMois(String date)	
	{	
	
		String mois = date.substring(3, 5);
		String annee = date.substring(6,10);
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(Calendar.MONTH, Integer.parseInt(mois));//mois suivant  
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");		
		calendar.add(Calendar.DAY_OF_MONTH, -1);//on enlève un jour pour passer au dernier jour du mois précédent
		//System.out.println(format.format(calendar.getTime()));		
		int nombreDeJour = calendar.get(Calendar.DAY_OF_MONTH);
		if (contient366Jour(annee) && mois.equalsIgnoreCase("02")) nombreDeJour =29;
		if (! contient366Jour(annee) && mois.equalsIgnoreCase("02")) nombreDeJour =28;
		
		return nombreDeJour;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static String getDateDebutAjoutMois(String dateDebut,int nombreDeMois)
	{
		String res ="";		
		int jourInput = Integer.parseInt(GestionDate.getJourFromDateString(dateDebut));
		int moisInput = Integer.parseInt(GestionDate.getMoisFromDateString(dateDebut));
		int annneeInput = Integer.parseInt(GestionDate.getAnneeFromDateString(dateDebut));
			 
		int newJour = jourInput;
		int newMois = moisInput +nombreDeMois;
		int newAnnee=annneeInput;
		if(newMois>12) 
		{
			int j = newMois /12;
			newMois = (newMois-12) %12;
			if (newMois==0) 
			{
				newMois =12;
				j=j-1;
			}
			newAnnee = annneeInput +j;
		}		
		
		String day = String.valueOf(newJour);
		String month = String.valueOf(newMois);
		String year = String.valueOf(newAnnee);
		if (jourInput<10) day ="0"+String.valueOf(newJour);		 
		if (newMois<10) month ="0"+String.valueOf(newMois);	
		res= day+"/"+month+"/"+year;
		res= validerUneDate(res);	
		return res; 
	}
	
	
	
	
	
	
	public static String ajouterNombreMoisComplet(String date, int nombreDeMois )
	{
		String res="";	
		try 
		{
			int jourInput = Integer.parseInt(GestionDate.getJourFromDateString(date));			
			int moisInput = Integer.parseInt(GestionDate.getMoisFromDateString(date));
			int annneeInput = Integer.parseInt(GestionDate.getAnneeFromDateString(date));			 
			int newJour = jourInput;
			int newMois = moisInput +nombreDeMois;
			int newAnnee=annneeInput;	
			
			if(newMois>12) 
			{
				int j = newMois /12;
				newMois = (newMois-12) %12;
				if (newMois==0) 
				{
					newMois =12;
					j=j-1;
				}
				newAnnee = annneeInput +j;
			}		
			
			String day = String.valueOf(newJour);
			String month = String.valueOf(newMois);
			String year = String.valueOf(newAnnee);
			if (jourInput<10) day ="0"+String.valueOf(newJour);		 
			if (newMois<10) month ="0"+String.valueOf(newMois);
			res= day+"/"+month+"/"+year;		
		
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}			
			return res;		
	}
	
	
	
	public static String ajouterNombreMois(String type, String dateDebut, int nombreDeMois ) throws Exception
	{
		String res ="";
		if(type.equalsIgnoreCase("DDebut"))
		{
			res = ajouterNombreMoisComplet(dateDebut,nombreDeMois);
			res = validerUneDate(res);
		}
		else if(type.equalsIgnoreCase("DFin"))
		{
			res = ajouterNombreMoisComplet(dateDebut,nombreDeMois);
			res = validerUneDate(res);			
		    DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");		      
		    Date parsedDate = sdf.parse(res);			 	 
		    Calendar now = Calendar.getInstance();	
		    now.setTime(parsedDate);	 
		    now.add(Calendar.DAY_OF_MONTH, -1);
		    res =format.format(now.getTime());		
		}
		return res ;
	}
	
	public static String ajouterNombreTrimestre(String type, String dateDebut, int nombreTrimestre ) throws Exception
	{
		String res ="";
		if(type.equalsIgnoreCase("DDebut"))
		{
			int mois = Integer.valueOf(getMoisFromDateString(dateDebut));
			String annee =getAnneeFromDateString(dateDebut);
			if (nombreTrimestre > 1)
			{
				String dateFin = GestionDate.ajouterNombreTrimestre("DFin",dateDebut, nombreTrimestre-1 );				
				res = addaDay(dateFin);				
			}
			else
			{
				res = dateDebut;
			}
		}
		else if(type.equalsIgnoreCase("DFin"))
		{
			int mois = Integer.valueOf(getMoisFromDateString(dateDebut));
			String annee =getAnneeFromDateString(dateDebut);
			if (mois <=3)
			{
				String dateDebutTmp = "01/01/" + annee;
				res = ajouterNombreMois("DFin", dateDebutTmp, 3*nombreTrimestre );
			}
			else if (  3 < mois && mois <= 6)
			{
				String dateDebutTmp = "01/04/" + annee;
				res = ajouterNombreMois("DFin", dateDebutTmp, 3*nombreTrimestre);				
			}
			else if (  6 < mois && mois <= 9)
			{
				String dateDebutTmp = "01/07/" + annee;
				res = ajouterNombreMois("DFin", dateDebutTmp, 3*nombreTrimestre);				
			}
			else 
			{
				String dateDebutTmp = "01/10/" + annee;
				res = ajouterNombreMois("DFin", dateDebutTmp, 3*nombreTrimestre);				
			}
				
				
			/*
			int mois = Integer.valueOf(getMoisFromDateString(dateDebut));					
			if (mois<4) 
			res = ajouterNombreMoisComplet(dateDebut,nombreDeMois);
			res = validerUneDate(res);			
		    DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");		      
		    Date parsedDate = sdf.parse(res);			 	 
		    Calendar now = Calendar.getInstance();	
		    now.setTime(parsedDate);	 
		    now.add(Calendar.DAY_OF_MONTH, -1);
		    res =format.format(now.getTime());
		    */		
		}
		return res ;
	}
	
	
	
	

}
