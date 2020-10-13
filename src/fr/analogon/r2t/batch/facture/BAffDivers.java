package fr.analogon.r2t.batch.facture;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.pojo.Imputation;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.request.RequestRue;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de traitements divers  CHARFI Sofien
 * 
 * @version 1.0
 * @since 1.0
 */
public class BAffDivers extends fr.analogon.r2t.main.RacineBean {



	private HttpServletRequest request;
	private String quartier;
	private String  typeBatchRelance;

	public String getTypeBatchRelance()
	{
		Vector tabtypeBatchRelance = new Vector();
		tabtypeBatchRelance.addElement("Annuel");
		for (int i = 1; i < 4; i++) 
		{
			tabtypeBatchRelance.addElement("Trimestre"+i);	
		}
		for (int i = 1; i < 13; i++) 
		{
			tabtypeBatchRelance.addElement("Mois"+i);	
		}
		
		typeBatchRelance = StaticManipHtml.genererListeDeroulante("typeBatchRelance", 1,"",tabtypeBatchRelance,true);
		return typeBatchRelance;
	}


	public void setTypeBatchRelance(String typeBatchRelance) {
		this.typeBatchRelance = typeBatchRelance;
	}


	public String getQuartier() 
	{
		RequestRue requestRue = new RequestRue();		
		Vector tabQuartier = requestRue.getListeQuartiers("");
		quartier = StaticManipHtml.genererListeDeroulante("quartier", 1,"",tabQuartier,true);		
		return quartier;
	}


	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}


	public void setRequest(HttpServletRequest req)
	{
		//System.out.println("set request Baff divers");
		this.request = req;	
	}		
	
	
	/**
	 * @return rous les typeTaxe
	 */
	public final String getTypeTaxe() 
	{	
		String res="";			
		StaticManipHtml mHtml = new StaticManipHtml();
		Vector contenu= new Vector ();	
		RequestImputation reqImputation = new RequestImputation();	
		String typeTaxe="";
		Vector listDesTypeTaxe = reqImputation.getLesDifferentTypesTaxe();
		for (int i = 0; i < listDesTypeTaxe.size(); i++) 
		{
			contenu.addElement(((Imputation)listDesTypeTaxe.elementAt(i)).getLibelle());
		}				
		res = StaticManipHtml.genererListeDeroulante("typeTaxe", 1,typeTaxe, contenu,true);
		//res= contenu.toString();
		return res;		
	}	
	
}