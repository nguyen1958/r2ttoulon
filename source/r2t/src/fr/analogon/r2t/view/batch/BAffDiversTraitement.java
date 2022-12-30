package fr.analogon.r2t.view.batch;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.Utilitaire.GestionDate;


public class BAffDiversTraitement extends fr.analogon.r2t.main.RacineBean
{
	private String dateAjourdhui="";	

	public void setRequest(HttpServletRequest req) 
	{
		////System.out.println("Setting BaffDiversTraitement..............");			
	}


	/**
	 * @return the dateAjourdhui
	 */
	public final String getDateAjourdhui() 
	{
		dateAjourdhui=GestionDate.getDateAujourdhuiString();
		return dateAjourdhui;
	}


}