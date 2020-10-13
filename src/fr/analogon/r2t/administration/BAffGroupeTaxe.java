package fr.analogon.r2t.administration;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.pojo.GroupeTaxe;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.request.RequestGroupeTaxe;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage des groupes de taxes
 * 
 * @version 2.0
 * @since 2.0
 */
public class BAffGroupeTaxe extends fr.analogon.r2t.main.RacineBean
{
	
	private HttpServletRequest request;	
	private String choix= "";	
	private boolean creation=true;	
	private String idGroupe="";
	private String libelle="";
	private String nombreGroupeTaxe="";
	
	private String listeGroupeTaxe="";
	private String listeDesTypesDeTaxeLiee="";
	private String boutonlisteDesTypesDeTaxeLiee="";
 
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
			if(choix.equalsIgnoreCase("modifier") )
			{
				this.setCreation(false);	
				idGroupe = req.getParameter("idGroupe");				
				RequestGroupeTaxe requestGroupeTaxe = new RequestGroupeTaxe();
				GroupeTaxe groupeTaxe = new GroupeTaxe();
				groupeTaxe = requestGroupeTaxe.getGroupeTaxe(idGroupe);				
				this.libelle = groupeTaxe.getLibelle();
				this.listeDesTypesDeTaxeLiee= groupeTaxe.getListeDesTypesDeTaxeLiee();
				
			}
			else if (request.getParameter("choix").equalsIgnoreCase("chercher"))
			{
				libelle=request.getParameter("libelle");
				StaticManipHtml mHtml = new StaticManipHtml();	
				RequestGroupeTaxe requestGroupeTaxe  = new RequestGroupeTaxe(); 
				Vector contenu =requestGroupeTaxe.getListeGroupeTaxe(libelle);
				
				if (contenu.size() !=0 ){
					
					if( contenu.size() < 200 )
					{
						listeGroupeTaxe = listeGroupeTaxe = StaticManipHtml.genererTableaulisteGroupeTaxe(contenu);
					}
					else
					{
						listeGroupeTaxe=" Le résultat contient plus que 200 Groupes de taxes, veuillez filtrer la recherche ";
					}
				}
				else {
					
					listeGroupeTaxe="Aucun resultat trouvé";
				}
				
				nombreGroupeTaxe ="Nombre de groupes = "+contenu.size();
				
			}
		}
	
	}

	public String getListeGroupeTaxe()
	{		
		return listeGroupeTaxe;		
	}


	public void setListeGroupeTaxe(String listeGroupeTaxe) {
		this.listeGroupeTaxe = listeGroupeTaxe;
	}


	
	public String getBoutonlisteDesTypesDeTaxeLiee() 
	{
		String res="";
		Vector contenu= new Vector ();	
		RequestImputation reqImputation = new RequestImputation();
		Vector listDesTypeTaxe = reqImputation.getLesDifferentTypesTaxe();
		for (int i = 0; i < listDesTypeTaxe.size(); i++) 
		{
			contenu.addElement(listDesTypeTaxe.elementAt(i));
		}
		res = StaticManipHtml.genererListeBoutonGroupeDeTaxe("listeDesTypesDeTaxeLiee",
				listeDesTypesDeTaxeLiee, 
				contenu); 
		return res;
	}



	public void setBoutonlisteDesTypesDeTaxeLiee(
			String boutonlisteDesTypesDeTaxeLiee) {
		this.boutonlisteDesTypesDeTaxeLiee = boutonlisteDesTypesDeTaxeLiee;
	}
	
	public String getNombreGroupeTaxe() {
		return nombreGroupeTaxe;
	}

	public void setNombreGroupeTaxe(String nombreGroupeTaxe) {
		this.nombreGroupeTaxe = nombreGroupeTaxe;
	}

	public String getlisteDesTypesDeTaxeLiee() {
		return listeDesTypesDeTaxeLiee;
	}

	public void setlisteDesTypesDeTaxeLiee(String listeDesTypesDeTaxeLiee) {
		this.listeDesTypesDeTaxeLiee = listeDesTypesDeTaxeLiee;
	}

	public boolean getCreation() {
		return creation;
	}

	public void setCreation(boolean creation) {
		this.creation = creation;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getChoix() {
		return choix;
	}

	public void setChoix(String choix) {
		this.choix = choix;
	}

	public String getIdGroupe() {
		return idGroupe;
	}

	public void setIdGroupe(String idGroupe) {
		this.idGroupe = idGroupe;
	}


	
}



