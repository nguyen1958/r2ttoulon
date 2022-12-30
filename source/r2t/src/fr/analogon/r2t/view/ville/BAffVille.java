package fr.analogon.r2t.view.ville;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.pojo.Ville;
import fr.analogon.r2t.request.RequestVille;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage de Ville  . Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0
 */
public class BAffVille extends fr.analogon.r2t.main.RacineBean
{
	
	private HttpServletRequest request;	
	private String choix= "";
	private boolean creation ;
	private String id="";
	private String code= "";
	private String ville= "";
	private String listeVille= "";
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
					id = request.getParameter("id");										
					RequestVille requestVille = new RequestVille();
					Ville vil = requestVille.getVille(id) ;
					code=vil.getCode();
					ville = vil.getNom();
			}
			else if (request.getParameter("choix").equalsIgnoreCase("chercher"))
			{
				code = request.getParameter("code");
				ville = request.getParameter("ville");										
			}
		}	
	}





	public String getChoix() {
		return choix;
	}
	
	public void setChoix(String choix) {
		this.choix = choix;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public boolean getCreation() {
		return creation;
	}

	public void setCreation(boolean creation) {
		this.creation = creation;
	}

	public String getId() {
		return id;
	}





	public void setId(String id) {
		this.id = id;
	}





	public String getListeVille() 
	{
		//listeImputation ="TABLEAU";
		String res="";
		StaticManipHtml mHtml = new StaticManipHtml();
		RequestVille requestVille = new RequestVille();
		Vector contenu = requestVille.getListeVille(code, ville);		
		res=mHtml.genererTableauListeVille(contenu);		
		return res;
	}

	public void setListeVille(String listeVille) {
		this.listeVille = listeVille;
	}
	
	
	
}