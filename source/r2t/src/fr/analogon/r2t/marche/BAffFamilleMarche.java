package fr.analogon.r2t.marche;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.pojo.FamilleMarche;
import fr.analogon.r2t.request.RequestFamilleMarche;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de FamilleMarche  . Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0
 */
public class BAffFamilleMarche extends fr.analogon.r2t.main.RacineBean
{
	
	private HttpServletRequest request;	
	private String choix= "";
	private boolean creation ;
	private String codeFamilleMarche= "";
	private String libelleFamilleMarche= "";
	private String listeFamilleMarche= "";
	private String peutEtreSupprimer= "true";
	
	
	//Setting Request 
	public void setRequest(HttpServletRequest req)
	{		
		this.request = req;		
		if (request.getParameter("choix") != null && ! request.getParameter("choix").equalsIgnoreCase(" ") )
		{
			choix=request.getParameter("choix");			
			if(choix.equalsIgnoreCase("ajouter"))
			{
					this.setCreation(true);
			}
			else if(choix.equalsIgnoreCase("modifier") )
			{
					this.setCreation(false);
					codeFamilleMarche = request.getParameter("codeFamilleMarche");										
					RequestFamilleMarche requestFamilleMarche = new RequestFamilleMarche();
					FamilleMarche familleMarche = requestFamilleMarche.getFamilleMarche(codeFamilleMarche) ;
					libelleFamilleMarche = familleMarche.getLibelleFamilleMarche();
					peutEtreSupprimer = familleMarche.getPeutEtreSupprimer();
			}
			else if (request.getParameter("choix").equalsIgnoreCase("chercher"))
			{
				codeFamilleMarche = request.getParameter("codeFamilleMarche");
				libelleFamilleMarche = request.getParameter("libelleFamilleMarche");										
			}
		}	
	}





	public String getChoix() {
		return choix;
	}
	
	public void setChoix(String choix) {
		this.choix = choix;
	}

	public String getCodeFamilleMarche() {
		return codeFamilleMarche;
	}

	public void setCodeFamilleMarche(String codeFamilleMarche) {
		this.codeFamilleMarche = codeFamilleMarche;
	}

	public String getLibelleFamilleMarche() {
		return libelleFamilleMarche;
	}

	public void setLibelleFamilleMarche(String libelleFamilleMarche) {
		this.libelleFamilleMarche = libelleFamilleMarche;
	}

	public String getPeutEtreSupprimer() {
		return peutEtreSupprimer;
	}

	public void setPeutEtreSupprimer(String peutEtreSupprimer) {
		this.peutEtreSupprimer = peutEtreSupprimer;
	}

	public boolean getCreation() {
		return creation;
	}

	public void setCreation(boolean creation) {
		this.creation = creation;
	}

	public String getListeFamilleMarche() 
	{
		//listeImputation ="TABLEAU";
		String res="";
		StaticManipHtml mHtml = new StaticManipHtml();
		RequestFamilleMarche requestFamilleMarche = new RequestFamilleMarche();
		Vector contenu = requestFamilleMarche.getListeFamilleMarche(codeFamilleMarche, libelleFamilleMarche);		
		res=mHtml.genererTableauListeFamilleMarche(contenu);		
		return res;
	}

	public void setListeFamilleMarche(String listeFamilleMarche) {
		this.listeFamilleMarche = listeFamilleMarche;
	}
	
	
	
}