package fr.analogon.r2t.batch.facture;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.pojo.Imputation;
import fr.analogon.r2t.request.DataFromBD;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage d'une refacturation  . Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0
 */
public class BaffRefacturation extends fr.analogon.r2t.main.RacineBean
{
	
	private HttpServletRequest request;	
	private String typeTaxe= "";
	private String numRue= "";	
	private String complNumRue="";
	private String anneecourante= "";

	//Setting Request 
	public void setRequest(HttpServletRequest req)
	{		
		
		this.request = req;
		//System.out.println("set request Baff Refacturation...................");	
	
	}

	/**
	 * @return the typeTaxe
	 */
	public final String getTypeTaxe() 
	{	
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();	
		RequestImputation reqImputation = new RequestImputation();		
		Vector listDesTypeTaxe = reqImputation.getLesDifferentTypesTaxe();
		for (int i = 0; i < listDesTypeTaxe.size(); i++) 
		{
			contenu.addElement(((Imputation)listDesTypeTaxe.elementAt(i)).getLibelle());
		}				
		res = StaticManipHtml.genererListeTypeTaxePourRefacturation("typeTaxe", 1,typeTaxe, contenu,false);
		
		
		//res= contenu.toString();
		return res;		
	}

	/**
	 * @return the numRue
	 */
	public final String getNumRue() 
	{
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();		
		DataFromBD data = new DataFromBD();
		Vector contenu = new Vector();			
		for (int i = 1; i < 500; i++)
		{
			contenu.addElement(""+i);
		}
		res = StaticManipHtml.genererListeDeroulante("numRue", 1,numRue, contenu,false);		
		return res;
	}

	/**
	 * @param numRue the numRue to set
	 */
	public final void setNumRue(String numRue) {
		this.numRue = numRue;
	}

	/**
	 * @return the complNumRue
	 */
	public final String getComplNumRue()
	{
		String res="";
		DataFromBD data = new DataFromBD();
		Vector contenu = new Vector();		
		contenu= data.getTousComplementNumRue();		
		res = StaticManipHtml.genererListeDeroulante("complNumRue", 1,complNumRue, contenu,true);
		return res;
		
	}

	/**
	 * @param complNumRue the complNumRue to set
	 */
	public final void setComplNumRue(String complNumRue) {
		this.complNumRue = complNumRue;
	}	
}

