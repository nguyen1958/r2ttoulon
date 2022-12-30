package fr.analogon.r2t.util.web;


import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.Arrondi;
import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.pojo.Alerte;
import fr.analogon.r2t.pojo.Article;
import fr.analogon.r2t.pojo.Autorisation;
import fr.analogon.r2t.pojo.Banque;
import fr.analogon.r2t.pojo.Bareme;
import fr.analogon.r2t.pojo.Batch;
import fr.analogon.r2t.pojo.BatchRapprochementIdoss;
import fr.analogon.r2t.pojo.Commentaire;
import fr.analogon.r2t.pojo.Communique;
import fr.analogon.r2t.pojo.CourierReponse;
import fr.analogon.r2t.pojo.Emplacement;
import fr.analogon.r2t.pojo.Facture;
import fr.analogon.r2t.pojo.FamilleMarche;
import fr.analogon.r2t.pojo.GroupeTaxe;
import fr.analogon.r2t.pojo.HistoriqueControle;
import fr.analogon.r2t.pojo.HistoriqueEtatOuvrage;
import fr.analogon.r2t.pojo.Imputation;
import fr.analogon.r2t.pojo.LigneIdoss;
import fr.analogon.r2t.pojo.Marche;
import fr.analogon.r2t.pojo.ModeleCourier;
import fr.analogon.r2t.pojo.Parametres;
import fr.analogon.r2t.pojo.Payement;
import fr.analogon.r2t.pojo.RapportChangementAdresse;
import fr.analogon.r2t.pojo.RapportSuivieFacturation;
import fr.analogon.r2t.pojo.Reclamation;
import fr.analogon.r2t.pojo.Redevable;
import fr.analogon.r2t.pojo.Refacturation;
import fr.analogon.r2t.pojo.Remboursement;
import fr.analogon.r2t.pojo.Rue;
import fr.analogon.r2t.pojo.Synchronisation;
import fr.analogon.r2t.pojo.Tache;
import fr.analogon.r2t.pojo.TypePayement;
import fr.analogon.r2t.pojo.Utilisateur;
import fr.analogon.r2t.pojo.Ville;
import fr.analogon.r2t.request.DataFromBD;
import fr.analogon.r2t.request.RequestBareme;
import fr.analogon.r2t.request.RequestEmplacement;
import fr.analogon.r2t.request.RequestFacture;
import fr.analogon.r2t.request.RequestImputation;
import fr.analogon.r2t.request.RequestParametres;
import fr.analogon.r2t.request.RequestRedevable;


/**
 * cette classe contient des methodes statiques permettant de creer des objets
 * html. . Sofien CHARFI
 * 
 * @version 2.0
 * @since 2.0
 */
public class StaticManipHtml 
{
	
	InitialisationConnexionLectureConfiguration c = new InitialisationConnexionLectureConfiguration();	
	public static FichierDeConfiguration  fichierDeConfiguration = InitialisationConnexionLectureConfiguration.fichierDeConfiguration;
	RequestEmplacement  requestEmplacement = new RequestEmplacement();
	//RequestFacture requestFacture = new RequestFacture();
	RequestParametres requestParametres = new RequestParametres();
	String ville = requestParametres.getVille();
	//bleu
	//public String couleur="bgcolor=\"#CCE6FF\"";
	
	//Jaune
	public String couleur="bgcolor=\"#FFFFF5\"";
	
	/**
	 * Permet d'afficher une liste deroulantes dans une page jsp avec une
	 * cellule vide. Retourne un String.
	 * 
	 * @param nomLD :
	 *            nom de la liste deroulante
	 * @param taille :
	 *            taille de la liste deroulante
	 * @param selection :
	 *            ligne � selectionner dans la liste si vide selection de la
	 *            premiere ligne	
	 * @param Contenu :
	 *            tablo contenant les variable affich�.
	 * @param vide :
	 *            avoir une case vide au debut. 
	 * @since 2.0
	 */
	
	
	public static String genererListeDeroulanteApartirDeVacteur(String nomLD, 
			String selection, Vector Contenu, int taille, boolean vide) 
	{		
		String listeDeroulante;
		listeDeroulante = "<select onchange='javascript:verfierUnicite();' onclick='javascript:verfierUnicite();'  size=\"" + taille + "\" "
				+ " name=\"" + nomLD + "\" "
				+ " id=\"" + nomLD + "\">\n";
		if (vide)
			listeDeroulante = listeDeroulante
					+ "<option value=\"\" ></option>\n";
		int longueur = Contenu.size();
		for (int i = 0; i < longueur; i++) 
		{
			listeDeroulante = listeDeroulante + "\n <option ";
			if (selection.equalsIgnoreCase((String)Contenu.elementAt(i)))
				listeDeroulante = listeDeroulante + "selected ";
			listeDeroulante = listeDeroulante + "value=\"" + (String)Contenu.elementAt(i)
					+ "\" >" + (String)Contenu.elementAt(i) + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";			
		return listeDeroulante;
	}

	
	
	
	public static String genererListeDeroulanteApartirDeVacteurNatureJuridique(String nomLD, 
			String selection, Vector Contenu, int taille, boolean vide) 
	{		
		String listeDeroulante;
		listeDeroulante = "<select onchange='javascript:chargerRaisonSocial();'   size=\"" + taille + "\" "
				+ " name=\"" + nomLD + "\" "
				+ " id=\"" + nomLD + "\">\n";
		if (vide)
			listeDeroulante = listeDeroulante
					+ "<option value=\"\" ></option>\n";
		int longueur = Contenu.size();
		for (int i = 0; i < longueur; i++) 
		{
			listeDeroulante = listeDeroulante + "\n <option ";
			if (selection.equalsIgnoreCase((String)Contenu.elementAt(i)))
				listeDeroulante = listeDeroulante + "selected ";
			listeDeroulante = listeDeroulante + "value=\"" + (String)Contenu.elementAt(i)
					+ "\" >" + (String)Contenu.elementAt(i) + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";			
		return listeDeroulante;
	}
	
	
	
	
	/**
	 * Permet d'afficher une liste deroulantes dans une page jsp avec une
	 * cellule vide. Retourne un String.
	 * 
	 * @param nomLD :
	 *            nom de la liste deroulante
	 * @param taille :
	 *            taille de la liste deroulante
	 * @param selection :
	 *            ligne � selectionner dans la liste si vide selection de la
	 *            premiere ligne
	 * @param tabloInterne :
	 *            tablo contenant les variable interne de la liste (les
	 *            variables retoun�es)
	 * @param tabloAffichage :
	 *            tablo contenant les variable affich�.
	 * @param vide :
	 *            avoir une case vide au debut. ATTENTION : les deux tablo
	 *            doivent avoir la m taille sous peine de generer une exception. .
	 *            Vincent Michal
	 * @since 1.0
	 */
	public static String genererListeDeroulante(String nomLD, int taille,
			String selection, String tabloInterne[], String tabloAffiche[],
			boolean vide) {
		String listeDeroulante;
		listeDeroulante = "<select size=\"" + String.valueOf(taille) + "\" "
				+ "name=\"" + nomLD + "\">\n";
		if (vide)
			listeDeroulante = listeDeroulante
					+ "<option value=\"\" ></option>\n";
		int longueur = (tabloInterne.length - 1);
		for (int i = 0; i <= longueur; i++) {
			listeDeroulante = listeDeroulante + "\n <option ";
			if (selection.equalsIgnoreCase(tabloInterne[i]))
				listeDeroulante = listeDeroulante + "selected ";
			listeDeroulante = listeDeroulante + "value=\"" + tabloInterne[i]
					+ "\" >" + tabloAffiche[i] + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";
		return listeDeroulante;
	}
	
	
	public static String genererListeTypeTaxePourRefacturation(String nomLD, int taille,
			String selection, Vector donnee, boolean vide) 
	{
		String listeDeroulante;	
		listeDeroulante =	"<select onchange=modifierTypeTaxe(); size=\"" + String.valueOf(taille) + "\" "
				+ "name=\"" + nomLD + "\" >\n";
		if (vide)
			listeDeroulante = listeDeroulante
					+ "<option value=\"\" ></option>\n";
		int longueur = (donnee.size());
		for (int i = 0; i < longueur; i++) 
		{
			listeDeroulante = listeDeroulante + "\n <option ";
			if (selection.equalsIgnoreCase((String)donnee.elementAt(i)))
				listeDeroulante = listeDeroulante + "selected ";
			listeDeroulante = listeDeroulante + "value=\"" + (String)donnee.elementAt(i)
					+ "\" >" + (String)donnee.elementAt(i) + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";
		return listeDeroulante;
	}
	
	
	public static String genererListeDeroulanteNumVoieRedevable(String nomLD, int taille,
			String selection, Vector donnee, boolean vide) 
	{
		String listeDeroulante;	
		listeDeroulante =	"<select onChange=\"detecterChangementAdresse();\" size=\"" + String.valueOf(taille) + "\" "
				+ "name=\"" + nomLD + "\" >\n";
		if (vide)
			listeDeroulante = listeDeroulante
					+ "<option value=\"\" ></option>\n";
		int longueur = (donnee.size());
		for (int i = 0; i < longueur; i++) 
		{
			listeDeroulante = listeDeroulante + "\n <option ";
			if (selection.equalsIgnoreCase((String)donnee.elementAt(i)))
				listeDeroulante = listeDeroulante + "selected ";
			listeDeroulante = listeDeroulante + "value=\"" + (String)donnee.elementAt(i)
					+ "\" >" + (String)donnee.elementAt(i) + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";
		return listeDeroulante;
	}
	
	
		public static String genererListeDeroulanteCompNumRueRedevable(String nomLD, int taille,
				String selection, Vector donnee, boolean vide) 
		{
			String listeDeroulante;	
			listeDeroulante =	"<select onChange=detecterChangementAdresse(); size=\"" + String.valueOf(taille) + "\" "
					+ "name=\"" + nomLD + "\" >\n";
			if (vide)
				listeDeroulante = listeDeroulante
						+ "<option value=\"\" ></option>\n";
			int longueur = (donnee.size());
			for (int i = 0; i < longueur; i++) 
			{
				listeDeroulante = listeDeroulante + "\n <option ";
				if (selection.equalsIgnoreCase((String)donnee.elementAt(i)))
					listeDeroulante = listeDeroulante + "selected ";
				listeDeroulante = listeDeroulante + "value=\"" + (String)donnee.elementAt(i)
						+ "\" >" + (String)donnee.elementAt(i) + "</option>\n";
			}
			listeDeroulante = listeDeroulante + "</select>\n";
			return listeDeroulante;
		}
		
		
	public static String genererListeDeroulante(String nomLD, int taille,
			String selection, Vector donnee, boolean vide) 
	{
		String listeDeroulante;	
		listeDeroulante =	"<select size=\"" + String.valueOf(taille) + "\" "
				+ "id='" + nomLD + "'  name=\"" + nomLD + "\" >\n";
		if (vide)
			listeDeroulante = listeDeroulante
					+ "<option value=\"\" ></option>\n";
		int longueur = (donnee.size());
		for (int i = 0; i < longueur; i++) 
		{
			listeDeroulante = listeDeroulante + "\n <option ";
			if (selection.equalsIgnoreCase((String)donnee.elementAt(i)))
				listeDeroulante = listeDeroulante + "selected ";
			listeDeroulante = listeDeroulante + "value=\"" + (String)donnee.elementAt(i)
					+ "\" >" + (String)donnee.elementAt(i) + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";
		return listeDeroulante;
	}
	
	
	public static String genererListeDeroulanteTousLesTypesDeTaxeFacturation(String nomLD, int taille,
			String selection, Vector donnee, boolean vide) 
	{
		String listeDeroulante;	
		listeDeroulante =	"<select onchange='changementTaxe()' size=\"" + String.valueOf(taille) + "\" "
				+ "id='" + nomLD + "'  name=\"" + nomLD + "\" >\n";
		if (vide)
			listeDeroulante = listeDeroulante
					+ "<option value=\"\" ></option>\n";
		int longueur = (donnee.size());
		for (int i = 0; i < longueur; i++) 
		{
			listeDeroulante = listeDeroulante + "\n <option ";
			if (selection.equalsIgnoreCase((String)donnee.elementAt(i)))
				listeDeroulante = listeDeroulante + "selected ";
			listeDeroulante = listeDeroulante + "value=\"" + (String)donnee.elementAt(i)
					+ "\" >" + (String)donnee.elementAt(i) + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";
		return listeDeroulante;
	}
	//nouveau
	public static String genererListeDeroulanteTousLesGroupesDeTaxeFacturation(String nomLD, int taille,
			String selection, Vector donnee, boolean vide) 
	{
		String listeDeroulante;	
		listeDeroulante =	"<select onchange='changementGroupe()' size=\"" + String.valueOf(taille) + "\" "
				+ "id='" + nomLD + "'  name=\"" + nomLD + "\" >\n";
		if (vide)
			listeDeroulante = listeDeroulante
					+ "<option value=\"\" ></option>\n";
		int longueur = (donnee.size());
		for (int i = 0; i < longueur; i++) 
		{
			listeDeroulante = listeDeroulante + "\n <option ";
			if (selection.equalsIgnoreCase((String)donnee.elementAt(i)))
				listeDeroulante = listeDeroulante + "selected ";
			listeDeroulante = listeDeroulante + "value=\"" + (String)donnee.elementAt(i)
					+ "\" >" + (String)donnee.elementAt(i) + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";
		return listeDeroulante;
	}
	
	public static String genererListeDeroulanteBanque(String nomLD,
			String selection, Vector donnee, boolean vide) 
	{
		String listeDeroulante;	
		
		listeDeroulante =	"<select name=\"" + nomLD + "\" onchange=changementTypePayement(); >\n";		
		int longueur = (donnee.size());
		if (vide)
			listeDeroulante = listeDeroulante
					+ "<option value=\"\" ></option>\n";
		for (int i = 0; i < longueur; i++) 
		{
			Banque banque = (Banque)donnee.elementAt(i);			
			listeDeroulante = listeDeroulante + "\n <option ";
			if (selection.equalsIgnoreCase(banque.getLibelle()))
				listeDeroulante = listeDeroulante + "selected ";
			listeDeroulante = listeDeroulante + "value=\"" + banque.getIdBanque()
					+ "\" >" + banque.getLibelle() + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";
		return listeDeroulante;
	}
	
	
	
	
	public static String genererListeDeroulanteTypePayement(String nomLD,
			String selection, Vector donnee , boolean vide) 
	{
		String listeDeroulante;			
		listeDeroulante =	"<select name=\"" + nomLD + "\" onchange=changementTypePayement(); >\n";		
		int longueur = (donnee.size());
		if (vide)
			listeDeroulante = listeDeroulante
					+ "<option value=\"\" ></option>\n";
		for (int i = 0; i < longueur; i++) 
		{
			TypePayement typePayement = (TypePayement)donnee.elementAt(i);
			listeDeroulante = listeDeroulante + "\n <option ";
			if (selection.equalsIgnoreCase(typePayement.getIdTypePayement()))
				listeDeroulante = listeDeroulante + "selected ";
			listeDeroulante = listeDeroulante + "value=\"" + typePayement.getIdTypePayement()
					+ "\" >" +typePayement.getTypePayement() + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";
		return listeDeroulante;
	}
	
	
	
	public static String genererListeDeroulanteFamilleMarche(String nomLD,
			String selection, Vector donnee , boolean vide) 
	{
		String listeDeroulante;			
		listeDeroulante =	"<select name=\"" + nomLD + "\"  >\n";		
		int longueur = (donnee.size());
		if (vide)
			listeDeroulante = listeDeroulante
					+ "<option value='0'></option>\n";
		for (int i = 0; i < longueur; i++) 
		{
			FamilleMarche familleMarche = (FamilleMarche)donnee.elementAt(i);
			listeDeroulante = listeDeroulante + "\n <option ";
			if (selection.equalsIgnoreCase(familleMarche.getCodeFamilleMarche()))
				listeDeroulante = listeDeroulante + "selected ";
			listeDeroulante = listeDeroulante + "value=\"" + familleMarche.getCodeFamilleMarche()
					+ "\" >" + familleMarche.getLibelleFamilleMarche() + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";
		return listeDeroulante;
	}
	
	
	
	
	public static String genererListeDeroulanteChangementTypeTaxe(String nomLD, int taille,
			String selection, Vector donnee, boolean vide) 
	{
		String listeDeroulante;	
		listeDeroulante =	"<select onchange=javascript:changerTypeTaxe(); size=\"" + String.valueOf(taille) + "\" "
				+ "name=\"" + nomLD + "\" >\n";
		if (vide)
			listeDeroulante = listeDeroulante
					+ "<option value=\"\" ></option>\n";
		int longueur = (donnee.size());
		for (int i = 0; i < longueur; i++) 
		{
			listeDeroulante = listeDeroulante + "\n <option ";
			if (selection.equalsIgnoreCase((String)donnee.elementAt(i)))
				listeDeroulante = listeDeroulante + "selected ";
			listeDeroulante = listeDeroulante + "value=\"" + (String)donnee.elementAt(i)
					+ "\" >" + (String)donnee.elementAt(i) + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";
		return listeDeroulante;
	}
	
	
	
	
	public static String genererListeDeroulanteNiveauDebogageR2TMobile(String nomLD, String selection,	Vector donnee) 
	{
		String listeDeroulante="";		
		listeDeroulante =	"<select onchange='changerNiveauDebogageR2TMobile()'  name=\"" + nomLD + "\" >\n";
		int longueur = (donnee.size());
		for (int i = 0; i < longueur; i++) 
		{
			listeDeroulante = listeDeroulante + "\n <option ";
			if (selection.equalsIgnoreCase(String.valueOf(i)))
				listeDeroulante = listeDeroulante + "selected ";
			listeDeroulante = listeDeroulante + "value=\"" + i
					+ "\" >" + (String)donnee.elementAt(i) + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";
		return listeDeroulante;
	}
	
	public static String genererListeDeroulanteNiveauDebogage(String nomLD, String selection,	Vector donnee) 
	{
		String listeDeroulante="";		
		listeDeroulante =	"<select onchange='changerNiveauDebogage()'  name=\"" + nomLD + "\" >\n";
		int longueur = (donnee.size());
		for (int i = 0; i < longueur; i++) 
		{
			listeDeroulante = listeDeroulante + "\n <option ";
			if (selection.equalsIgnoreCase(String.valueOf(i)))
				listeDeroulante = listeDeroulante + "selected ";
			listeDeroulante = listeDeroulante + "value=\"" + i
					+ "\" >" + (String)donnee.elementAt(i) + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";
		return listeDeroulante;
	}
	
	public static String genererListeBoutonTypeDeTaxe(String nomLD,String selection,
			Vector donnee) 
	{
		String listeDeroulante="";				
		int longueur = (donnee.size());		
		for (int i = 0; i < longueur; i++) 
		{			
		   Imputation imputation = (Imputation)donnee.elementAt(i);
		   String checked ="";
		   String[] parts = selection.split(";");
		   //System.out.println(selection);
		   for (int j = 0; j < parts.length ; j++) 
			{
			   if(parts[j].equals(imputation.getCode()))
			   {
				   checked ="checked";
			   }
			}
		   
		   listeDeroulante =  "<input type=\"checkbox\"  " +
		   		" onclick=majTypeTaxeAutoise('"+imputation.getCode()+"'); " +
		   		" name="+imputation.getCode()+"  "+ checked+" >"+ imputation.getLibelle()+ " <BR> "+listeDeroulante ;
		}	
	
		return listeDeroulante;
	}
	
	public static String genererListeBoutonGroupeDeTaxe(String nomLD,String selection,
			Vector donnee) 
	{
		//Transformer string en liste
		List liste = Arrays.asList(selection.split(";"));
		
		String listeDeroulante="";				
		int longueur = (donnee.size());		
		for (int i = 0; i < longueur; i++) 
		{			
		   Imputation imputation = (Imputation)donnee.elementAt(i);
		   String checked ="";
		   if(liste.contains(imputation.getCode()))
			   checked ="checked";
		   else
			   checked="";
		   //System.out.println("genererListeBoutonGroupeDeTaxe>>> "+liste+"//"+imputation.getCode()+":"+checked);
		   listeDeroulante =  "<input type=\"checkbox\" " +
		   		" name= \"taxeLiee\" value="+imputation.getCode()+"  "+ checked+" >"+ imputation.getLibelle()+ " <BR> "+listeDeroulante ;
		}	
	
		return listeDeroulante;
	}
	
	
	public static String genererListeTypeTaxe(String nomLD,String selection, Vector donneeAAfficher,
			Vector valeurBox) 
	{
		String listeDeroulante;	
		listeDeroulante =	"<select size=\"" + String.valueOf(1) + "\" "
				+ "name=\"" + nomLD + "\" >\n";		
		int longueur = (donneeAAfficher.size());
		for (int i = 0; i < longueur; i++) 
		{
			listeDeroulante = listeDeroulante + "\n <option ";
			if (selection.equalsIgnoreCase((String)donneeAAfficher.elementAt(i)))
				listeDeroulante = listeDeroulante + "selected ";
			listeDeroulante = listeDeroulante + "value=\"" + (String)valeurBox.elementAt(i)
					+ "\" >" + (String)donneeAAfficher.elementAt(i) + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";
		return listeDeroulante;
	}
	
	
	
	
	
	
	
	
	public static String genererListeDeroulanteListeBareme(String nomLD, int taille,
			String selection, Vector donnee, boolean vide) 
	{
		String listeDeroulante;	
		listeDeroulante = "<select size='"
			+ String.valueOf(taille)+"' " 
			+ "name='" 
			+ nomLD + "' "
			+ "onchange=majDimension(this.options[this.selectedIndex].value);>";
		if (vide)
			listeDeroulante = listeDeroulante
					+ "<option value=\"\" ></option>\n";
		int longueur = (donnee.size());
		for (int i = 0; i < longueur; i++) 
		{
			listeDeroulante = listeDeroulante + "\n <option ";
			if (selection.equalsIgnoreCase((String)donnee.elementAt(i)))
				listeDeroulante = listeDeroulante + "selected ";
			listeDeroulante = listeDeroulante + "value=\"" + (String)donnee.elementAt(i)
					+ "\" >" + (String)donnee.elementAt(i) + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";
		////System.out.println(listeDeroulante);
		//listeDeroulante="ALI";
		return listeDeroulante;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	public static String genererListeDeroulanteBareme(String nomLD, int taille,
			String selection, String tabloInterne[], String tabloAffiche[],
			boolean vide, int tmp) 
	{
		String listeDeroulante;
		listeDeroulante = "<select size='"
						+ String.valueOf(taille)+"' " 
						+ "name='" 
						+ nomLD + "' "
						+ "onchange=majDimension();>";
				
			
		for (int i = 0; i <tabloInterne.length; i++) 
		{
			listeDeroulante = listeDeroulante + "\n <option"; 
			   if (selection.equalsIgnoreCase(tabloInterne[i]))
				   listeDeroulante = listeDeroulante + " selected ";		
			listeDeroulante = listeDeroulante + "  value='" + tabloAffiche[i]+"'>";			
			listeDeroulante = listeDeroulante + tabloAffiche[i] + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";
		return listeDeroulante;
	}
	
	

	public static String genererTableauHistoriqueBatch(Vector input)
		{
			String res="";
			if (input.size()>0)
			{
				
				res="<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width=\"100%\" "  +
				" borderColorLight=\"#c0c0c0\" border=\"1\" align=\"center\" height=\"20\">";
				res= res+"<td bgcolor=\"#FFFFFF\" width=\"25%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "N batch"
				+ "</font></b></td>";
				res= res+"<td bgcolor=\"#FFFFFF\" width=\"25%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Nombre de factures crees"
				+ "</font></b></td>";
				res= res+"<td bgcolor=\"#FFFFFF\" width=\"25%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Date execution"
				+ "</font></b></td>";
				res= res+"<td bgcolor=\"#FFFFFF\" width=\"25%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Type de taxe"
				+ "</font></b></td>";
				res= res+"<td bgcolor=\"#FFFFFF\" width=\"25%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Etape"
				+ "</font></b></td>";	
				res= res+"<td bgcolor=\"#FFFFFF\" width=\"25%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Valide"
				+ "</font></b></td>";				
				
				//String bgcolor1 ="#FFFFCC";
				//String bgcolor2 ="#D9ECFF";
				//String bgcolor=bgcolor1;				
				
				for (int i = 0; i < input.size(); i++) 
				{
					Batch b = (Batch)input.elementAt(i);									
					res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" >";
					res= res+"<td   width=\"25%\" align=\"center\"><b><font color=\"#000080\" size=\"3\">" 
							  + "<a href=./entree?action=gestionBatch.jsp&numeroBatch="+ b.getNumeroBatchTraitement()+" >"
							  +b.getNumeroBatchTraitement()  
							  +	"</a>" 
							  + "</font></b></td>";
					res= res+"<td   width=\"25%\" align=\"center\"><b><font color=\"#000080\" size=\"3\">" 
					  + b.getNombreFactures() + "</font></b></td>";	
					res= res+"<td  width=\"25%\" align=\"center\"><b><font color=\"#000080\" size=\"3\">" 
					  + b.getDateExecution()  + "</font></b></td>";	
					res= res+"<td   width=\"25%\" align=\"center\"><b><font color=\"#000080\" size=\"3\">" 
					  + b.getTypeEmplacement()  + "</font></b></td>";	
					res= res+"<td   width=\"25%\" align=\"center\"><b><font color=\"#000080\" size=\"3\">" 
					  + b.getEtape()  + "</font></b></td>";					
					res= res+"<td   width=\"25%\" align=\"center\"><b><font color=\"#000080\" size=\"3\">" 
					  + b.getValide()  + "</font></b></td>";	
					res=res+"</tr>";
					
				}	
						
				res=res+"</table>";
				////System.out.println(res);
			}
			else
			{
				res =" Il n 'y a aucun batch ";
			}
			return res;
			
			
		}
		
		
		
		
		public static String genererTableauListeCommunique(Vector input)
		{
			ArrayList retour = new ArrayList();
			String res="";
			if (input.size()>0)
			{				
				res="<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width=\"100%\" "  +
				" borderColorLight=\"#c0c0c0\" border=\"1\" align=\"center\" height=\"20\">";
				res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "N ouvrage"
				+ "</font></b></td>";
				
				res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Type  "
				+ "</font></b></td>";
				
				res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Adresse à contrôler"
				+ "</font></b></td>";
				
				res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Quartier "
				+ "</font></b></td>";
				
				res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Date de contrôle"
				+ "</font></b></td>";
				
				res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ " Redevable"
				+ "</font></b></td>";	
				String idOuvrage ;
				String adresseEmplacement ;
				String dateDeControle ;
				String idRedevable ;
				String idEmplacement ;
				String type ="";
				String quartier="";
				String numeroEF="";
				String codeSecteur="";
				Communique comm;
				retour.add(res);
			
			for (int i = 0; i < input.size(); i++) 
				{
								
					comm = (Communique)input.elementAt(i);
					idOuvrage= comm.getIdOuvrage() ;
					adresseEmplacement= comm.getAdresseEmplacement() ;
					dateDeControle = comm.getDateDeControle();
					idRedevable = comm.getIdRedevable();
					idEmplacement = comm.getIdEmplacement() ;
					type =comm.getType();
					quartier= comm.getQuartier();
					numeroEF=comm.getNumeroEF();
					codeSecteur= comm.getCodeSecteur();
					
					
					
					
					String linkOuvrage= "./entree?action=empl_saisie_element_facturation.jsp&traitement=article&choix=modifier" 
						+ "&idarticle="+ idOuvrage;
									
					
					//RequestRedevable requestRedevable = new RequestRedevable();
					//Redevable redevable = requestRedevable.getRedevable(comm.getIdRedevable());
					String lienRedvable="./entree?action=empl_gestion_redevable.jsp"+
					"&numRedevable="+ idRedevable +	
					"&choix=modifier";
					
					String linkEmplacment= "./entree?action=empl_gestion_emplacementodp.jsp" +
					"&choix=modifier&optionTransfert=role&origine=origine" +
					"&numEmplacment="+ idEmplacement;
					
					res="<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" >";
					
					res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;
					res= res+"<a href ="+ linkOuvrage +">";
					res= res+ idOuvrage  + "</font></b></td>";	
					res= res+"</a>";
					
					res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;
					res= res+ type  + "</font></b></td>";		
					
					
					res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;
					res= res+"<a href ="+ linkEmplacment +">";
					res= res+ adresseEmplacement  + "</font></b></td>";	
					res= res+"</a>";
					
					res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;
					res= res+ quartier  + "</font></b></td>";	
					
					
					res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" +
					  dateDeControle  + "</font></b></td>";	
					res=res+"</td>";					
					
					res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;
					res= res+"<a href= "+ lienRedvable +">";
					res= res+ idRedevable;
					res= res+"</a>";
					res= res + "</font></b></td>";	
					res=res+"</td>";
					res= res+"</a>";
					
					res=res+"</tr>";
					comm =null;					
					retour.add(res);
					res="";
					
				}		
				res = retour.toString();
				res = res.replaceAll(",", "");
				res = res.substring(1, res.length()-1);
				res = res  + "</table>";
				
			}
			else
			{
				res =" <B> Aujourdhui le "+ GestionDate.getDateAujourdhuiString() +" , aucun contrôle n'est programme  </B> ";
			}
			return res;
			
			
			
		}
		
		
		public static String genererTableauListeRapportChangementAdresse(Vector input)
		{
			String res="";
			if (input.size()>0)
			{				
				res="<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width=\"100%\" "  +
				" borderColorLight=\"#c0c0c0\" border=\"1\" align=\"center\" height=\"20\">";
				res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "N batch"
				+ "</font></b></td>";
				
				res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Lien vers le fichier "
				+ "</font></b></td>";
				
				res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Date de creation"
				+ "</font></b></td>";
				
				
				for (int i = 0; i < input.size(); i++) 
				{
					
					RapportChangementAdresse cra = (RapportChangementAdresse)input.elementAt(i);
					String idBatch= cra.getIdBatch();
					
					String lien = fichierDeConfiguration.getLienRoleChgtAdresseRedevable()+"RCAR_"+idBatch +".pdf";
					
					res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" >";
					
					res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" +
					  idBatch + "</font></b></td>";	
					res=res+"</td>";
					
					res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;
					if (cra.getACreeFichier().equalsIgnoreCase("true"))
					{
						res= res+"<a target='_blank' href ="+ lien +">";
						res= res+ "lien</font></b></td>";					
					}
					else
					{						
						res= res+ "-</font></b></td>";
					}
						
					res= res+"</a>";
					
					
					res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" +
					  cra.getDateCreation() + "</font></b></td>";	
					res=res+"</td>";				
					
					
					res=res+"</tr>";
					
				}	
						
				res=res+"</table>";
				////System.out.println(res);
			}
			else
			{
				res =" Liste vide ";
			}
			return res;		
		}
		
		
		
		public static String genererTableauListeRapportSuivieFacturation(Vector input)
		{
			String res="";
			if (input.size()>0)
			{				
				res="<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width=\"100%\" "  +
				" borderColorLight=\"#c0c0c0\" border=\"1\" align=\"center\" height=\"20\">";
				res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "N batch"
				+ "</font></b></td>";
				
				res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Type de taxe"
				+ "</font></b></td>";
				
				res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Lien vers le fichier"
				+ "</font></b></td>";
				
				res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Date de creation"
				+ "</font></b></td>";
				
				res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Date debut"
				+ "</font></b></td>";
				
				res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Date fin"
				+ "</font></b></td>";
				
			
				
				
				for (int i = 0; i < input.size(); i++) 
				{
					
					RapportSuivieFacturation rapport = (RapportSuivieFacturation)input.elementAt(i);
				
					String idBatch= rapport.getIdBatch();
					
					String lien = fichierDeConfiguration.getLienSuivieDeFacturation() +"RSF_"+idBatch +".pdf";
				     
					res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\">";
					
					res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" +
					  idBatch + "</font></b></td>";	
					res=res+"</td>";
					
					res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" +
					  rapport.getTypeTaxe() + "</font></b></td>";	
					res=res+"</td>";
					
					res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;
					
					
					if (rapport.getACreeFichier() != null && rapport.getACreeFichier().equalsIgnoreCase("true"))
					{
						res= res+"<a target='_blank' href ="+ lien +">";
						res= res+ "lien</font></b></td>";					
					}
					else
					{						
						res= res+ "-</font></b></td>";
					}
						
					res= res+"</a>";
					
						
					res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" +
					  rapport.getDateCreation() + "</font></b></td>";	
					res=res+"</td>";	
					
					res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" +
					  rapport.getDatedebut() + "</font></b></td>";	
					res=res+"</td>";
					
					res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" +
					  rapport.getDateFin() + "</font></b></td>";	
					res=res+"</td>";
					
					
					res=res+"</tr>";
					
					
				}	
						
				res=res+"</table>";
				////System.out.println(res);
			}
			else
			{
				res =" Liste vide ";
			}
			return res;		
		}
		
		
		
		
		
		
		public static String genererTableauHistoriqueTaches(Vector input)
		{
			
			String res="",elem;
			res="<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width=\"100%\" "  +
			" borderColorLight=\"#c0c0c0\" border=\"1\" align=\"center\" height=\"20\">";
			res= res+"<td bgcolor=\"#FFFFFF\" width=\"25%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Action"
			+ "</font></b></td>";
			res= res+"<td bgcolor=\"#FFFFFF\" width=\"25%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Date"
			+ "</font></b></td>";			
			
			////System.out.println("TAILLE="+input.size());
			for (int i = 0; i < input.size(); i++) 
			{								
				res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\">";
				Tache tache = ((Tache)input.elementAt(i));
				String action=tache.getAction();
				String date=tache.getDate();				
				res= res+"<td align=\"center\">"+ action + "</td>";
				res= res+"<td align=\"center\">"+ date + "</td>";							
				res=res+"</tr>";			
			}			
			res=res+"</table>";
			////System.out.println(res);
			return res;
		}
		
		
		
		
		public static String genererTableauListeDesCourier(Vector input)
		{
			String res ="Aucune reponse ";
			if(input.size() > 0 )
			{
				res ="<table border=0>";			
				for (int i = 0; i < input.size(); i++) 
				{
					res = res + "<tr><td>";
					CourierReponse courierReponse = (CourierReponse)input.elementAt(i);
					res = res + "Un courier est cree le "+ courierReponse.getDateCreation() + " avec le modele "+ courierReponse.getNomModele() ;
					res = res + "</td></tr>";
				}	
				res = res +"</table>";
			}
			
			return res;
		}
		
		
		
		
		
		
		
		
		
		
		public static String genererTableauHistoriqueSynchro(Vector input)
		{
			
			String res="",elem;
			if(input.size() !=0)
			{
				res="<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width=\"100%\" "  +
				" borderColorLight=\"#c0c0c0\" border=\"1\" align=\"center\" height=\"20\">";
				res= res+"<td bgcolor=\"#FFFFFF\"  width=\"7%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "N PDA"+ "</font></b></td>";
				
				res= res+"<td bgcolor=\"#FFFFFF\"  width=\"15%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Action"
				+ "</font></b></td>";
				
				res= res+"<td bgcolor=\"#FFFFFF\"  width=\"15%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Type"
				+ "</font></b></td>";
				
				res= res+"<td bgcolor=\"#FFFFFF\"  width=\"15%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Date"
				+ "</font></b></td>";
				res= res+"<td bgcolor=\"#FFFFFF\"  width=\"15%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Contrôleur"
				+ "</font></b></td>";
				res= res+"<td bgcolor=\"#FFFFFF\"  width=\"30%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Quartier"
				+ "</font></b></td>";
				
				////System.out.println("TAILLE="+input.size());
				for (int i = 0; i < input.size(); i++) 
				{								
					res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" >";
					Synchronisation syn = ((Synchronisation)input.elementAt(i));
					String action=syn.getAction();
					String date=syn.getDateSynchronisation();
					String controleur=syn.getControleur();
					String idControleur=syn.getIdControleur();
					String quartiers=syn.getQuartiers();
					String typeSynchro=syn.getTypeSynchronisation();
					String idPda=syn.getIdPda();
					if(typeSynchro.equalsIgnoreCase("ST"))
						typeSynchro= "Synchronisation totale";
					else if(typeSynchro.equalsIgnoreCase("SP"))
						typeSynchro= "Synchronisation partiel";
					else
						typeSynchro= "-";
					res= res+"<td align=\"center\">"+ idPda + "</td>";
					res= res+"<td align=\"center\">"+ action + "</td>";
					res= res+"<td align=\"center\">"+ typeSynchro + "</td>";								
					res= res+"<td align=\"center\">"+ date + "</td>";
					
					String link = "<a href=entree?action=empl_gestion_utilisateur.jsp" +
					"&choix=modifier&codeUtilisateur="+idControleur +">";
					res= res+"<td align=\"center\">" + controleur + "</a></td>";		
					res= res+"<td align=\"center\">" + quartiers + "</a></td>";
					//System.out.print(res);
					res=res+"</tr>";			
				}			
				res=res+"</table>";
			}
			else				
				res="Aucun controle Terrain ";
			return res;
		}
	
		
		
	public static String genererTableauListeAlertes(Vector input)
	{		
		String res="",elem;
		if (input.size()!=0)
		{		
			res=res+"<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width=\"100%\" "  +
			" borderColorLight=\"#c0c0c0\" border=\"1\" align=\"center\" ";
			res= res+"<tr>";
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "N alerte"
			+ "</font></b></td>";
			
						
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Etat de l'alerte"
			+ "</font></b></td>";
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Date de creation"
			+ "</font></b></td>";
			
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Date de fin"
			+ "</font></b></td>";
			
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Type de taxe"
			+ "</font></b></td>";
			
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Nom redevable"
			+ "</font></b></td>";
			
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Quartier"
			+ "</font></b></td>";

			
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Adresse "
			+ "</font></b></td>";	
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "N ouvrage"
			+ "</font></b></td>";
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Contrôleur"
			+ "</font></b></td>";
			
			res= res+"</tr>";
			//String bgcolor1 ="#FFFFCC";
			//String bgcolor2 ="#D9ECFF";
			//String bgcolor=bgcolor1;	
			//int nombreAttribut=13;
		
		////System.out.println("Nombre d alerte " +input.size());
		//for (int i = 0; i < input.size()/nombreAttribut; i++)
		for (int i = 0; i < input.size(); i++) 
		{
			Alerte alerte= (Alerte)input.elementAt(i);	
			String id_alerte=alerte.getId_alerte() ;		
			String date_creation=alerte.getDate_creation();
			String text_alerte=alerte.getText_alerte();
			String id_article=alerte.getId_article();
			String id_controleur=alerte.getId_controleur();
			String numeroEmplacement=alerte.getNumeroEmplacement();
			String anexercice=alerte.getAnexercice();
			String numRedevable=alerte.getNumRedevable();
			String numero_ef=alerte.getNumero_ef();			
			String nomPrenomControleur=alerte.getNomPrenomControleur();
			String codeType=alerte.getCodeType();	
			String etatAlerte=alerte.getEtat_alerte();
			String dateFinAlerte= alerte.getDateFinAlerte();
			String adresseOuvrage= alerte.getAdresseOuvrage();
			String nomRedevable= alerte.getNomRedevable();
			String prenomRedevable= alerte.getPrenomRedevable();
			String civilteRedevable= alerte.getCiviliteRedevable();
			
			
						
			String lienArticle ="./entree?action=empl_saisie_element_facturation.jsp" +
					"&traitement=article" +
					"&choix=modifier&idarticle=" +id_article +
					"&numElementFacturation=" +numero_ef +
					"&numEmplacment=" + numeroEmplacement +
					"&exercice=" + anexercice +
					"&numRedevable=" +numRedevable +
					"&numeroAlerte=" +id_alerte +
					"&codeType=" + codeType ;
						
			
			String lienRedvable="./entree?action=empl_gestion_redevable.jsp&choix=modifier" +
					"&boton=ajouter&typeRecherche=role&optionTransfert=role&typeRedevable=normal" +
					"&origine=origine&numRedevable=" + numRedevable +
					"&nomRedevable=" + nomRedevable ;
		
						
			String couleur="";
			
			if( etatAlerte.equalsIgnoreCase("CLOTURER"))
				couleur="#CCCCCC";
			if(couleur.length()!=0)
				res=res+"<tr name=\"ligne\" onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" bgcolor="+ couleur+ " align=\"center\">";
			else
				res=res+"<tr name=\"ligne\" onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\">";
			
			////System.out.println(etatAlerte+" CCCCCCCOULLLLEEUUUUURRR "+couleur );
			
			
			if (text_alerte.length() >30)
				text_alerte = text_alerte.substring(0,30)+".......";
			
			String lienAlerteSpontanne= "./entree?action=empl_saisie_alerteSpantanne.jsp" +
						                "&idAlerteSpontanne="+id_alerte;
			
			////System.out.println(lienAlerteSpontanne);
			//res=res+"<tr   size=\"3\" align=\"center\" bgcolor="+ bgcolor+">";		
			if(! nomRedevable.equalsIgnoreCase("-"))
			{
				res= res+"<td>" 
				+"<a href="+lienArticle+"/>"+ id_alerte +"</a>"	
				+"</font></b></td>";
			}
			else
			{
				res= res+"<td>" 
				+"<a href="+lienAlerteSpontanne+">"+ id_alerte+"</a>"	
				+"</font></b></td>";
			}
		
			
			res= res+"<td>" ;			
			if (etatAlerte.equalsIgnoreCase("ENCOURS"))
				res= res+"<img border=\"0\" src=\"images/vert.jpg\" width=\"15\" height=\"15\">";
			else
				res= res+"<img border=\"0\" src=\"images/rouge.jpg\" width=\"15\" height=\"15\">";
			res= res+"</font></b></td>"; 
			
			
			
			
			res= res+"<td>"  
			+ date_creation  + "</font></b></td>";
			
			if (dateFinAlerte==null ) dateFinAlerte="-";
			else
				{
				dateFinAlerte= dateFinAlerte.trim();
					if ( dateFinAlerte.length()==0 ) dateFinAlerte="-";
				}
			
					
			res= res+"<td>"  + dateFinAlerte  + "</font></b></td>";	
			
			res= res+"<td>" 
			+ codeType  + "</font></b></td>";	
			
			res= res+"<td>";
			if(!nomRedevable.equalsIgnoreCase("-"))
			{
				res=res+"<a href="+lienRedvable+ ">"+
				nomRedevable +" " +prenomRedevable+
				"</a>"+
				"</font></b></td>";
			}
			else
				res=res+nomRedevable;
			
			res= res+"<td>"+alerte.getQuartier()+"</font></b></td>";
			
			res= res+"<td>"	+ adresseOuvrage  + "</font></b></td>";	
			
			if(!id_article.equalsIgnoreCase("-"))
			{
			  res= res+"<td>" 
			  +"<a href="+lienArticle+"/>"+ id_article +"</a>"	
			  +"</font></b></td>";
			}
			else
			{
				res= res+"<td>-"+"</font></b></td>";
			}
			
			res= res+"<td>" ;
			String link = "<a href=entree?action=empl_gestion_utilisateur.jsp" +
					"&choix=modifier&codeUtilisateur="+id_controleur +">";
			 
			//res = res + link + nomPrenomControleur +"</a></font></b></td>";
			res = res +  nomPrenomControleur +"</a></font></b></td>";
			
			res=res+"</tr>";
			//if(bgcolor.equalsIgnoreCase(bgcolor1)) bgcolor=bgcolor2;
			//else if(bgcolor.equalsIgnoreCase(bgcolor2)) bgcolor=bgcolor1;
		}			
		res=res+"</table>";
		}
		else 
			res= "<font size=\"4\">Il n'y a aucune alerte correspondante avec ces criteres de recherche. </font>" ;
					
		////System.out.println(res);
		//System.out.println(
		return res;
		
		
	}
	
	
	public String genererTableauListeReclamations(Vector input)
	{
		
		String res="",elem;
		if (input.size()!=0)
		{		
			res=res+"<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"; 
			res=res+"<tr align=\"center\" bgcolor=\"#FFFFFF\">";
			
			res=res+"<td "+ couleur+ "><b><font size=\"4\">N reclamation</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Etat </font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Date de la reclamation</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Type de taxe</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Nom redevable</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">N facture</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">N titre</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Annee titre</font></b></td>";
				
			
			res= res+"</tr>";
			
			//String bgcolor1 ="#FFFFCC";
			//String bgcolor2 ="#D9ECFF";
			//String bgcolor=bgcolor1;	

		//////System.out.println("input.size()"+input.size())
		for (int i = 0; i < input.size(); i++) 
		{
			Reclamation reclamation = (Reclamation)input.elementAt(i);
			Facture facture = new RequestFacture().getFacture(""+reclamation.getIdFacture());
			//res=res+"<tr align=\"center\" bgcolor=\""+ bgcolor+"\">";			
			String couleur="";
			//if(reclamation.getEtatReclamation().equalsIgnoreCase("CLOTURER"))
				//couleur="#CCCCCC";
			if(couleur.length()!=0)
				res=res+"<tr name=\"ligne\" onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" bgcolor="+ couleur+ " align=\"center\">";
			else
				res=res+"<tr name=\"ligne\" onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\">";
			
			
			String lienRedvable="./entree?action=empl_gestion_redevable.jsp"+
			"&numRedevable="+ reclamation.getIdRedevable()+			
			"&choix=modifier";	
			
					
			
			
			res=res+"<td  height=\"1\">";
			res=res+"<a href=./entree?action=empl_gestion_reclamations.jsp&choix=modifier&idReclamation="
			+reclamation.getIdReclamation()+">"	+reclamation.getIdReclamation()+"</a>";
			res=res+"</td>";
			res=res+"<td>";
			if (reclamation.getEtatReclamation().equalsIgnoreCase("ENCOURS"))
				res= res+"<img border=\"0\" src=\"images/vert.jpg\" width=\"15\" height=\"15\">";
			else
				res= res+"<img border=\"0\" src=\"images/rouge.jpg\" width=\"15\" height=\"15\">";
			//res=res+reclamation.getEtatReclamation();
			res=res+"</td>";
			res=res+"<td  >";
			res=res+reclamation.getDateReclamation();
			res=res+"</td>";
			res=res+"<td>";
			res=res+reclamation.getTypeTaxe();		
			res=res+"</td>";
			
			res=res+"<td >";
			res=res+"<a href="+lienRedvable+">";
			res=res+reclamation.getNomRedevable()+" " + reclamation.getPrenomRedevable() ;
			res=res+"<a >";			
			res=res+"</td>";
			
			res=res+"<td  >";
			res=res+reclamation.getIdFacture() ;
			res=res+"</td>";
			
			res=res+"<td  >";
			res=res+facture.getNumeroTitre() ;
			res=res+"</td>";
			
			res=res+"<td  >";
			res=res+facture.getAnneeTitre();
			res=res+"</td>";
			
			
			
			res=res+"</tr>";
			//if(bgcolor.equalsIgnoreCase(bgcolor1)) bgcolor=bgcolor2;
			//else if(bgcolor.equalsIgnoreCase(bgcolor2)) bgcolor=bgcolor1;

		}			
		res=res+"</table>";
		}
		else 
			res= "<font size=\"4\">Il n'y a aucune reclamation correspondant à ces critères de recherche. </font>" ;
		
		////System.out.println("__________________TABLEAU_______________________________");
		////System.out.println(res);
		////System.out.println("____________________________________________________");
		//System.out.println(
		return res;
	}
	
	
	public String genererTableauListeRefacturation(Vector input)
	{		
		String res="",elem;
		if (input.size()!=0)
		{		
			res=res+"<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"; 
			res=res+"<tr align=\"center\" bgcolor=\"#FFFFFF\">";
			
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Ne refacturation</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Annee de l'exercice</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Type de taxe</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">N facture annulee</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">N redevable</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Date de refacturation</font></b></td>";		
			res= res+"</tr>";


		//System.out.println("input.size()"+input.size());
		for (int i = 0; i < input.size(); i++) 
		{
			
			
			res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\">";
				Refacturation refacturation = (Refacturation)input.elementAt(i);
				
				String lienRedvable="./entree?action=empl_gestion_redevable.jsp"+
				"&numRedevable="+ refacturation.getNumRedevable()+		
				"&choix=modifier&boton=ajouter&typeRecherche=role&optionTransfert=role" +
				"&typeRedevable=normal&origine=origine";
				res=res+"<td>";				
				res=res + refacturation.getIdRefacturation();
				res=res+"</td>";
				
				res=res+"<td>";
				res=res+refacturation.getAnneeExercice();
				res=res+"</td >";
				
				res=res+"<td>";
				res=res+refacturation.getTypeTaxe();
				res=res+"</td>";
				
				res=res+"<td>";
				String numFacture =refacturation.getNumeroFacture();
				if (numFacture.length()==0) numFacture="-";
				res=res+numFacture;
				res=res+"</td>";
				
				res=res+"<td>";
				res=res+"<a href="+lienRedvable+">";
				res=res+refacturation.getNumRedevable();
				res=res+"</a>";
				res=res+"</td>";
				
				res=res+"<td>";
				res=res+refacturation.getDateRefacturation();
				res=res+"</td>";
			
			res=res+"</tr>";
		}			
		res=res+"</table>";
		}
		else 
			res= "<font size=\"4\">Il n'y a aucune refacturation qui correspondant à ces critères de recherche.</font>" ;
		
		//System.out.println(
		return res;
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	/*
	public String genererListeFacturePdf(Vector contenu, String repertoirePdf)
	{
		String res="";
		
		res="<table border=\"0\" width=\"100%\">";
		for (int i = 0; i < contenu.size(); i++) 
		{
			
			res=res+"<tr>";			
			res=res+"<td width=\"3%\"><img border=\"0\" src=\"./images/pdf.png\"" +
			   		"width=\"30\" height=\"30\"></td>";
			res=res+"<td width=97%><a href=" + fichierDeConfiguration.getLienFichierDesFactures() +
			repertoirePdf+"/"+ contenu.elementAt(i)+".pdf >"+ "Facture Num "+contenu.elementAt(i)+"</a></td>";
			//resr=""
			res=res+"</tr>";
			//res="TEST";
			////System.out.println(res);
		}  
		res=res+"</table>";		
		return res;
	}
	*/

	
	
	
	public String genererListeFactureUtilsateurPdf(Vector contenu )
	{
		String res="";			
		////System.out.println("Nombre de facture="+contenu.size());
		if (contenu.size()>0)
		{
			res=res+"<table  border=\"0\" >";
			res=res+"<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"; 
			res=res+"<tr align=\"center\" bgcolor=\"#FFFFFF\">";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Facture</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Type de taxe</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Date de creation de la facture</font></b></td>";		
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Numero titre</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Etat facture</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Reclamation</font></b></td>";
			res=res+"<td ><b><font size=\"4\">ER</font></b></td>";
			res=res+"</tr>";
			
			for (int i = 0; i < contenu.size(); i++) 
			{
				////System.out.println("FACTURE numero"+i);
				Facture facture = (Facture)contenu.elementAt(i);
				////System.out.println(facture.getNomDossier());
				String repertoirePdf= facture.getNomDossier();
				String nomFichier= String.valueOf(facture.getNumeroFacture());
				String typeTaxe= String.valueOf(facture.getTypeTaxe());
				String dateCreation= String.valueOf(facture.getDateCreationFacture());
				int idClient= facture.getIdClient();
				String etatFacture= facture.getEtat();
				String idReclamation= facture.getIdReclamation();
				dateCreation = dateCreation.substring(0,10);
				String numeroTitre= facture.getNumeroTitre();
				String etatReclamation= facture.getEtatReclamation();
				res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\">";	
				
				res=res+"<td >" +
						"<img src=\"images/pdfbleue.png\" border=\"0\" height=\"21\" width=\"20\">&nbsp"+
						"<a target=\"_blank\" href="+  fichierDeConfiguration.getLienFichierDesFactures()  +repertoirePdf+"/"+nomFichier+".pdf >" +
						"Facture No"+nomFichier+".pdf" +"</a></td>";
				String link="./entree?action=empl_gestion_reclamations.jsp&choix=modifier&idReclamation="+idReclamation;
				res=res+"<td>"+ typeTaxe +"</td>";
				res=res+"<td>"+ dateCreation+"</td>";
				res=res+"<td>"+ numeroTitre+"</td>";
				res=res+"<td>"+ etatFacture+"</td>";
				if (idReclamation !=null && idReclamation.length() !=0 )
				{
					res=res+"<td><a href="+ link +">"+ idReclamation +"</a>    ";
					res=res+"</td>";
				}
				else
				{
					res=res+"<td>-</td>";
				}
				res=res+"<td>";
				if(etatReclamation !=null && etatReclamation.equalsIgnoreCase("ENCOURS"))
					res= res+"<img border=\"0\" src=\"images/vert.jpg\" width=\"15\" height=\"15\">";
				else if(etatReclamation !=null && etatReclamation.equalsIgnoreCase("CLOTURER"))
					res= res+"<img border=\"0\" src=\"images/rouge.jpg\" width=\"15\" height=\"15\">";
				else
					res= res+"-";
				res=res+"</td>";
				
				res=res+"</tr>";		
			}	    
			res=res+"</table>";		
		}
		else
		{			 
			res=res+"Aucune facture n'est disponible ";				
		}
		
		return res;
	}
	
	public String genererListePaiementRedevable(Vector contenu )
	{
		String res="";			
		////System.out.println("Nombre de facture="+contenu.size());
		if (contenu.size()>0)
		{
			res=res+"<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"; 
			res=res+"<tr align=\"center\" bgcolor=\"#FFFFFF\">";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Facture</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Date de creation de la facture</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Montant total</font></b></td>";					
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Solde</font></b></td>";		
			res=res+"</tr>";
			for (int i = 0; i < contenu.size(); i++) 
			{
				////System.out.println("FACTURE numero"+i);
				Facture facture = (Facture)contenu.elementAt(i);
				////System.out.println(facture.getNomDossier());
				String repertoirePdf= facture.getNomDossier();
				String nomFichier= String.valueOf(facture.getNumeroFacture());		
				String dateCreation= String.valueOf(facture.getDateCreationFacture());		
				String etatFacture= facture.getEtat();
				String idReclamation= facture.getIdReclamation();
				dateCreation = dateCreation.substring(0,10);		
				String montantTotalFacture= facture.getMontantTotalFacture();
				String soldeFacture= facture.getSoldeFacture();
				res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\">";	
				
				res=res+"<td >" +
						"<img src=\"images/pdfbleue.png\" border=\"0\" height=\"21\" width=\"20\">&nbsp"+
						"<a target=\"_blank\" href="+ fichierDeConfiguration.getLienFichierDesFactures() +repertoirePdf+"/"+nomFichier+".pdf >" +
						"Facture No"+nomFichier+".pdf" +"</a></td>";
				String link="./entree?action=empl_gestion_reclamations.jsp&choix=modifier&idReclamation="+idReclamation;
				res=res+"<td>"+ dateCreation+"</td>";
				res=res+"<td>"+ montantTotalFacture+"</td>";
				res=res+"<td>"+ soldeFacture+"</td>";			
				res=res+"</tr>";		
			}	    
			res=res+"</table>";	
		}
		else
		{			
			res=res+"Aucun paiement n'est disponible ";				
		}
			
		return res;
	}
	
			
	
	public  String genererListeDesFactures(Vector contenu )
	{
		String res="";	
		
		if (contenu.size()!=0)
		{
		res=res+"<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"; 
		res=res+"<tr align=\"center\" bgcolor=\"#FFFFFF\">";
		res=res+"<td "+ couleur+ "><b><font size=\"4\">Redevable</font></b></td>";
		res=res+"<td "+ couleur+ "><b><font size=\"4\">Type de taxe</font></b></td>";
		res=res+"<td "+ couleur+ "><b><font size=\"4\">Facture</font></b></td>";
		res=res+"<td "+ couleur+ "><b><font size=\"4\">Montant</font></b></td>";
		res=res+"<td "+ couleur+ "><b><font size=\"4\">Etat </font></b></td>";			
		res=res+"<td "+ couleur+ "><b><font size=\"4\">Date de creation de la facture </font></b></td>";		
		
			
		res=res+"</tr>";
		Vector tab = new Vector();
		for (int i = 0; i < contenu.size(); i++) 
		{
			Facture facture = (Facture)contenu.elementAt(i);
			////System.out.println(facture.getNomDossier());			
			String repertoirePdf= facture.getNomDossier();
			String montantFacture= facture.getMontantTotalFacture();
			String nomFichier= String.valueOf(facture.getNumeroFacture());
			String typeTaxe= String.valueOf(facture.getTypeTaxe());
			String dateCreation= String.valueOf(facture.getDateCreationFacture());
			int idClient= facture.getIdClient();
			String etatFacture= facture.getEtat();			
			int idFacture = facture.getIdFacture();
			String couleur1="";
			res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\">";
			String linkUser= "./entree?action=empl_gestion_redevable.jsp&choix=modifier" +
			"&boton=ajouter&typeRecherche=role&optionTransfert=role" +
			"&typeRedevable=normal&origine=origine&numRedevable="+ idClient;
			res=res+"<td><a href =" + linkUser+">"+ idClient + " -- "+ facture.getNomPrenomClient() +"</a></td>";
			res=res+"<td>"+ typeTaxe +"</td>";
			res=res+"<td >" +
					"<img src=\"./images/pdfbleue.png\" border=\"0\" height=\"21\" width=\"20\">&nbsp"+
					"<a  href=./entree?action=gestionFacture.jsp&numeroFacture="+ facture.getNumeroFacture() + ">" +
					"N "+nomFichier+".pdf" +"</a></td>";
			
			res= res+"<td>"+ montantFacture +"</td>";
			
			if(etatFacture.equalsIgnoreCase("ANNULEE"))
			{
				res= res+"<td><img border=\"0\" src=\"images/rouge.jpg\" width=\"15\" height=\"15\">"+ facture.getMotifAnnulationFacture() +"</td>";
			}
			else
				res= res+"<td><img border=\"0\" src=\"images/vert.jpg\" width=\"15\" height=\"15\"></td>";
				
			
			
			res=res+"<td>"+ dateCreation+"</td>";
			
								
			res=res+"</tr>";	
			tab.addElement(res);
			res="";
		}	
		res = tab.toString();
		res=res+"</table>";	
		}
		else
		{
			res= "<font size=\"4\">Il n'y a aucune facture correspondante avec ces criteres de recherche. </font>" ;
		}
		res= res.replaceAll(",", "");
		res= res.replaceAll("\\[", "");
		res= res.replaceAll("\\]", "");
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	public static String genererListeReclamationUtilsateur(Vector contenu )
	{
String res="",elem;
		if (contenu.size()!=0)
		{		
			res = "<p align=\"left\"><font size=\"4\"></font></p>";			
			res=res+"<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width=\"100%\" "  +
			" borderColorLight=\"#c0c0c0\" border=\"1\" align=center ";
			res= res+"<tr>";
			res= res+"<td bgcolor=\"#FFFFFF\" width=\"10%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "N reclamation"
			+ "</font></b></td>";
			
			res= res+"<td bgcolor=\"#FFFFFF\" width=\"10%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Etat de la reclamation"
			+ "</font></b></td>";
			
			res= res+"<td bgcolor=\"#FFFFFF\" width=\"20%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Date de la reclamation"
			+ "</font></b></td>";
			
			res= res+"<td bgcolor=\"#FFFFFF\" width=\"10%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Type de taxe"
			+ "</font></b></td>";		
		
			
			res= res+"<td bgcolor=\"#FFFFFF\" width=\"10%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "N titre"
			+ "</font></b></td>";
			
			res= res+"<td bgcolor=\"#FFFFFF\" width=\"10%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Annee titre"
			+ "</font></b></td>";
			
			res= res+"</tr>";
			
			//String bgcolor1 ="#FFFFCC";
			//String bgcolor2 ="#D9ECFF";
			//String bgcolor=bgcolor1;	

		//////System.out.println("input.size()"+input.size())
		for (int i = 0; i < contenu.size(); i++) 
		{
			Reclamation reclamation = (Reclamation)contenu.elementAt(i);			
			//res=res+"<tr align=\"center\" bgcolor=\""+ bgcolor+"\">";			
			String couleur="";
			if(reclamation.getEtatReclamation().equalsIgnoreCase("CLOTURER"))
				couleur="#CCCCCC";
			if(couleur.length()!=0)
				res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" bgcolor="+ couleur+ " align=\"center\">";
			else
				res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\">";
			
			res=res+"<td  height=\"1\">";
			res=res+"<a href=./entree?action=empl_gestion_reclamations.jsp&choix=modifier&idReclamation="
			+reclamation.getIdReclamation()+">"	+reclamation.getIdReclamation()+"</a>";
			res=res+"</td>";
			res=res+"<td>";
			if (reclamation.getEtatReclamation().equalsIgnoreCase("ENCOURS"))
				res= res+"<img border=\"0\" src=\"images/vert.jpg\" width=\"15\" height=\"15\">";
			else
				res= res+"<img border=\"0\" src=\"images/rouge.jpg\" width=\"15\" height=\"15\">";
			//res=res+reclamation.getEtatReclamation();
			res=res+"</td>";
			res=res+"<td  >";
			res=res+reclamation.getDateReclamation();
			res=res+"</td>";
			res=res+"<td>";
			res=res+reclamation.getTypeTaxe();		
			res=res+"</td>";
					
			res=res+"<td  >";
			res=res+"-";
			res=res+"</td>";
			
			res=res+"<td  >";
			res=res+"-";
			res=res+"</td>";
			
			
			
			res=res+"</tr>";
			//if(bgcolor.equalsIgnoreCase(bgcolor1)) bgcolor=bgcolor2;
			//else if(bgcolor.equalsIgnoreCase(bgcolor2)) bgcolor=bgcolor1;

		}			
		res=res+"</table>";
		}
		else 
			res= "<font size=\"4\">Il n'y a aucune reclamation correspondante avec ces criteres de recherche. </font>" ;
		
		////System.out.println("__________________TABLEAU_______________________________");
		////System.out.println(res);
		////System.out.println("____________________________________________________");
		//System.out.println(
		return res;
	}	
	
	
	
	
	public String genererListeEmplacement(Vector contenu )
	{
		String res="";			
		if (contenu.size() !=0 )
		{			
			res=res+"<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"; 
			res=res+"<tr align=\"center\" bgcolor=\"#FFFFFF\">";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Type</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Etat</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Photos</font></b></td>";			
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Infos</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Secteur</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">N emplacement</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Nom commercial</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Adresse emplacement</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Ouvrage actif</font></b></td>";
			res=res+"</tr>";
			for (int i = 0; i < contenu.size(); i++) 
			{
				
				Emplacement emplacement = (Emplacement)contenu.elementAt(i);
				String etatEmplacement= emplacement.getEtatEmplacement();			
				res=res+"<tr name=\"ligne\" onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\">";
				String linkEmplacement="./entree?action=empl_gestion_emplacementodp.jsp&choix=modifier" +
						"&optionTransfert=role&origine=origine&numEmplacment="+emplacement.getNumEmplacement();									
				String linkOuvrages="./entree?action=regie_liste_element_facturation.jsp&traitement=article&choix=modifier" +
						"&numEmplacement=" + emplacement.getNumEmplacement();	
				
				res=res+"<td><a href= "+ linkEmplacement+" >"+ emplacement.getLibelleImputation()+"</a></td>";
				if (etatEmplacement.equalsIgnoreCase("Termine"))
					res= res+"<td><img align=\"center\" border=\"0\" src=\"images/gris.jpg\" width=\"15\" height=\"15\"></td>";
				else
					res= res+"<td><img align=\"center\" border=\"0\" src=\"images/vert.jpg\" width=\"15\" height=\"15\"></td>";
						
				int nombrePhoto = requestEmplacement.getNombreImagesEmplacement(emplacement.getNumEmplacement());
				if (nombrePhoto!=0)
				{
					res=res+"<td><img title='Il y a des images dans cet emplacement !'  border=\"0\" src=\"images/valider4sx.png\" width=\"21\" height=\"18\"></td>";					
				}
				else					
					res=res+"<td>.</td>";								
				
				RequestEmplacement requEmplacement = new RequestEmplacement();
				String nbreAlerte= requEmplacement.getNombreAlerte(emplacement.getNumEmplacement());
				int nbreAlerteActif = Integer.parseInt(nbreAlerte);
				
				if (nbreAlerteActif == 0)
					nbreAlerte =".";
				else
					nbreAlerte ="<img title=\"Il y a des alertes en cours sur des ouvrages dans cet emplacement ! \" border=\"0\" src=\"images/alerte.PNG\" width=\"21\" height=\"18\">";
					
				res=res+"<td>"+nbreAlerte+"</td>";				
				
				res=res+"<td>"+ emplacement.getCodeSecteur()+"</td>";
				res=res+"<td>"+ emplacement.getNumEmplacement()+"</td>";
				String nomCommercial= emplacement.getNomCommercial();
				if (nomCommercial.length()==0) nomCommercial = "-";
				res=res+"<td>"+ nomCommercial+"</td>";
				
				res=res+"<td>"+ emplacement.getNumRue()+ " " + emplacement.getComplementNumeroRueEmpl() 
				 +" "+ emplacement.getAdresse1()+"</td>";
				res=res+"<td><a href= "+ linkOuvrages+" >"+ "Liste des ouvrages ( " + emplacement.getNombreOuvrageActif() +" )"
				+"</a></td>";
				res=res+"</tr>";		
			}		
			res=res+"</table>";	
		}		
		else if (contenu.size() ==0)
		{
			res= "Aucun emplacement n'est disponible" ;
		}
		return res;
	}
	
	
	public String getListeOuvrageAutorise(Vector contenu, String etat )
	{
		String res="";			
		if (contenu.size() !=0 )
		{			
			res=res+"<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"; 
			res=res+"<tr align=\"center\" bgcolor=\"#FFFFFF\">";
			if (etat.equalsIgnoreCase("Acceptée")){
				res=res+"<td "+ couleur+ "><b><font size=\"4\">N° emplacement</font></b></td>";
			}
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Type emplacement</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Nom ouvrage</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Date de début</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Date de fin</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Bareme</font></b></td>";
			res=res+"</tr>";
			for (int i = 0; i < contenu.size(); i++) 
			{
				
				Article article = (Article)contenu.elementAt(i);							
				res=res+"<tr name=\"ligne\" onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\">";
				String linkEmplacement="./entree?action=empl_gestion_emplacementodp.jsp&choix=modifier" +
						"&optionTransfert=role&origine=origine&numEmplacment="+article.getNumEmplacment();								
			/*	String linkOuvrages="./entree?action=regie_liste_element_facturation.jsp&traitement=article&choix=modifier" +
						"&numEmplacement=" + emplacement.getNumEmplacement();	*/
				if (etat.equalsIgnoreCase("Acceptée")){
					res=res+"<td><a href= "+ linkEmplacement+" >"+ article.getNumEmplacment()+"</a></td>";
				}
				res=res+"<td>"+article.getLibelleImputation()+"</td>";
				res=res+"<td>"+(article.getNom().equalsIgnoreCase("#vide")?"-":article.getNom())+"</td>";
				res=res+"<td>"+ article.getDateDebutAutorisation()+"</td>";
				res=res+"<td>"+ (article.getDateFinAutorisation().isEmpty()?"-":article.getDateFinAutorisation())+"</td>";
				res=res+"<td>"+ article.getLibelleBareme()+"</td>";
				res=res+"</tr>";		
			}		
			res=res+"</table>";	
		}		
		else if (contenu.size() ==0)
		{
			res= "Aucun emplacement n'est disponible" ;
		}
		return res;
	}
	
	public String genererListeCommentaire(Vector contenu ) throws ParseException
	{
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy - HH'H'mm");;
		String res="";			
		////System.out.println("Nombre de commentaire="+contenu.size());
		if (contenu.size()>0)
		{
			res=res+"<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"; 
			res=res+"<tr align=\"center\" bgcolor=\"#FFFFFF\">";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Date</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Commentaire</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Résultat de la visite</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Agent</font></b></td>";		
			res=res+"</tr>";
			
			for (int i = 0; i < contenu.size(); i++) 
			{
				Commentaire commentaire = (Commentaire)contenu.elementAt(i);
				res=res+"<tr onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\">";	
				res=res+"<td>"+ sdf.format(commentaire.getDate()) +"</td>";
				res=res+"<td>"+ commentaire.getCommentaire().replace("\n", "</br>")+"</td>";
				res=res+"<td>"+ commentaire.getResultatvisite()+"</td>";
				res=res+"<td>"+ commentaire.getNomAuteur()+"</td>";
				res=res+"</tr>";	
				
				System.out.println(">>>commentaire>>>"+commentaire.getCommentaire());
			}	    
			res=res+"</table>";		
		}
		else
		{			 
			res=res+"Aucun commentaire ";				
		}
		
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String genererTableauListeImputations(Vector contenu )
	{
		String res="";
		if (contenu.size()!=0)
		{		
			res=res+"<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"; 
			res=res+"<tr align=\"center\" bgcolor=\"#FFFFFF\">";			
			res=res+"<td "+ couleur+ "><b><font size=\"4\">N°type de taxe</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Annee</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Libelle</font></b></td>";			
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Designation</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Code tri bordereau</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">N d'enveloppe budgetaire</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Code budget</font></b></td>";					
			res= res+"</tr>";

		
		for (int i = 0; i < contenu.size(); i++) 
		{
			res = res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\">";
			Imputation imputation = (Imputation)contenu.elementAt(i);			
			res=res+"<td  height=\"1\">";
			res=res+"<a href=./entree?action=empl_gestion_imputation.jsp&choix=modifier" +
					"&numtypeTaxe="+imputation.getNumtypeTaxe()+
					"&anneeExercice="+imputation.getAnneeExercice()+
					">"	
			+imputation.getNumtypeTaxe()+"</a>";
			res=res+"</td>";
			
			res=res+"<td  >";
			res= res+imputation.getAnneeExercice();
			res=res+"</td>";
			
			res=res+"<td  >";
			res= res+imputation.getLibelle();
			res=res+"</td>";
					
			res=res+"<td  >";
			res= res+imputation.getDesignation();
			res=res+"</td>";
			
			res=res+"<td  >";
			res= res+imputation.getCode();
			res=res+"</td>";
			
			res=res+"<td >";
			res= res+imputation.getSection();
			res=res+"</td>";
			
			res=res+"<td >";
			res= res+imputation.getCodeBudget();
			res=res+"</td>";
			
			
			res = res+"</tr>";

		}			
		res=res+"</table>";
		}
		else 
			res= "<font size=\"4\">Il n'y  a pas de taxe qui correspond avec ces criteres de recherche. </font>" ;

		//System.out.println(
		
		return res;
	}
	
	
	
	public String genererTableauListeFamilleMarche(Vector contenu )
	{
		String res="";
		if (contenu.size()!=0)
		{		
			res=res+"<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"; 
			res=res+"<tr align=\"center\" bgcolor=\"#FFFFFF\">";			
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Code</font></b></td>";			
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Libelle</font></b></td>";
			res= res+"</tr>";
		
		for (int i = 0; i < contenu.size(); i++) 
		{
			res = res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\">";
			FamilleMarche familleMarche  = (FamilleMarche)contenu.elementAt(i);			
			
			res=res+"<td  >";
			String lien = "<a href='./entree?action=empl_gestion_familleMarche.jsp&choix=modifier&codeFamilleMarche="+familleMarche.getCodeFamilleMarche()+"'>"+familleMarche.getCodeFamilleMarche()+"</a>";
			res= res+ lien;
			res=res+"</td>";
			
			res=res+"<td  >";
			res= res+ familleMarche.getLibelleFamilleMarche();
			res=res+"</td>";
						
			res = res+"</tr>";

		}			
		res=res+"</table>";
		}
		else 
			res= "<font size=\"2\">Il n'y  a pas de famille marche qui correspond avec ces criteres de recherche. </font>" ;
		
		return res;
	}	

	
	public String genererTableauListeMarche(Vector contenu )
	{
		String res="";
		if (contenu.size()!=0)
		{		
			res=res+"<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"; 
			res=res+"<tr align=\"center\" bgcolor=\"#FFFFFF\">";			
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Code</font></b></td>";			
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Libelle</font></b></td>";
			res= res+"</tr>";
		
		for (int i = 0; i < contenu.size(); i++) 
		{
			res = res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\">";
			Marche marche  = (Marche)contenu.elementAt(i);			
			
			res=res+"<td  >";
			String lien = "<a href='./entree?action=empl_gestion_marche.jsp&choix=modifier&codeMarche="+ marche.getCodeMarche()+"'>"+marche.getCodeMarche()+"</a>";
			res= res+ lien;
			res=res+"</td>";
			
			res=res+"<td  >";
			res= res+ marche.getLibelleMarche();
			res=res+"</td>";
						
			res = res+"</tr>";

		}			
		res=res+"</table>";
		}
		else 
			res= "<font size=\"2\">Il n'y  a pas de marche qui correspond avec ces criteres de recherche. </font>" ;
		
		return res;
	}	
	
	public String genererTableauListeVille(Vector contenu )
	{
		String res="";
		if (contenu.size()!=0)
		{		
			res=res+"<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"; 
			res=res+"<tr align=\"center\" bgcolor=\"#FFFFFF\">";			
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Code</font></b></td>";			
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Ville</font></b></td>";
			res= res+"</tr>";
		
		for (int i = 0; i < contenu.size(); i++) 
		{
			res = res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\">";
			Ville ville  = (Ville)contenu.elementAt(i);
			System.out.println(ville);
			
			res=res+"<td  >";
			String lien = "<a href='./entree?action=empl_gestion_ville.jsp&choix=modifier&id="+ ville.getId()+"'>"+ville.getCode()+"</a>";
			res= res+ lien;
			res=res+"</td>";
			
			res=res+"<td  >";
			res= res+ ville.getNom();
			res=res+"</td>";
						
			res = res+"</tr>";

		}			
		res=res+"</table>";
		}
		else 
			res= "<font size=\"4\">Il n'y  a pas de ville qui correspond avec ces criteres de recherche. </font>" ;
		
		return res;
	}	


	
		
	
	
	public static String genererListeDeroulantePeriodicite(String nomLD, int taille,
			String selection, Vector donnee, boolean vide) 
	{
		String listeDeroulante;	
		listeDeroulante =	"<select onClick=utiliserPeriodicte() size=\"" + String.valueOf(taille) + "\" "
				+ "name=\"" + nomLD + "\" >\n";
		if (vide)
			listeDeroulante = listeDeroulante
					+ "<option value=\"\" ></option>\n";
		int longueur = (donnee.size());
		for (int i = 0; i < longueur; i++) 
		{
			listeDeroulante = listeDeroulante + "\n <option ";
			if (selection.equalsIgnoreCase((String)donnee.elementAt(i)))
				listeDeroulante = listeDeroulante + "selected ";
			listeDeroulante = listeDeroulante + "value=\"" + (String)donnee.elementAt(i)
					+ "\" >" + (String)donnee.elementAt(i) + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";
		return listeDeroulante;
	}
	
	

	
	
	public Vector reglerNewContenu( Vector contenu )
	{
		Vector res =new Vector();	
		//Vector newContenu= new Vector();
		for (int i = 0; i < contenu.size(); i++) 
		{
			Article articleTmp= (Article)contenu.elementAt(i);
			//Si l'ouvrage n'existe pas			
			int pos=this.verfierArticleDansUnTableau(articleTmp, res);
			////System.out.println(articleTmp.getId());
			////System.out.println(pos);
				
			if(  pos == -1)
			{
				res.addElement(articleTmp);				
			}
			//si l'ouvrage existe, mais il y a un autre ouvrage qui correpand au bareme de l'anne en cours 
			else
			{
				Article articleEnCours =  (Article)res.elementAt(pos); 
				if(articleTmp.getAnExercice().equalsIgnoreCase(GestionDate.getAnneeCourante()))
				{
					res.removeElementAt(pos);
					res.addElement(articleTmp);			
					////System.out.println("esxiste");
				}
				if( Integer.parseInt(articleTmp.getAnExercice()) < Integer.parseInt(GestionDate.getAnneeCourante())
						&&  Integer.parseInt(articleTmp.getAnExercice()) > Integer.parseInt(articleEnCours.getAnExercice()))
				{
					res.removeElementAt(pos);
					res.addElement(articleTmp);			
					////System.out.println("esxiste");
				}
			}	
		}		
		return res;		
	}
	
	public int verfierArticleDansUnTableau(Article article, Vector contenu )
	{
		
		int res = -1;		
		for (int i = 0; i < contenu.size(); i++) 
		{
			Article artcileTmp= (Article)contenu.elementAt(i);			
			if( artcileTmp.getId().equalsIgnoreCase(article.getId()))
			{
				res= i;
				break;
			}				
		}		
		return res;		
	}
	
	
	
	
	//paul
	//Si surface#0, ne plus multiplier par longueur et largeur
	public  String genererTableauListeElementDeFacturation(Vector contenuInitale )
	{
		String res="";	
		double total =0000.00;
		String libelleTaxe ="";
		if (contenuInitale.size() !=0 )
		{			
			///Traitment des article si il y a un ouvrage qui a une reference sur le bareme 
		    //et on a bcp de bareme pour different annne 
					
			
			Vector contenu = new Vector();
			contenu = this.reglerNewContenu(contenuInitale);

			Vector tab = new Vector();
			for (int i = 0; i < contenu.size(); i++) 
			{
				
				Article article = (Article)contenu.elementAt(i);
				String etatArticle= article.getEtatArticle();
				libelleTaxe = article.getLibelleImputation();
				//String libelleTaxe = "TEST";
				//System.out.println("libelleTaxe="+libelleTaxe);
				
				
				if(i==0)
				{
					res=res+"<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"; 
					res=res+"<tr align=\"center\" bgcolor=\"#FFFFFF\">";				
					res=res+"<td "+ couleur+ "><b><font size=\"4\">Etat</font></b></td>";
					res=res+"<td "+ couleur+ "><b><font size=\"4\">Bareme</font></b></td>";
					res=res+"<td "+ couleur+ "><b><font size=\"4\">Libelle article</font></b></td>";
					
					if(!libelleTaxe.equalsIgnoreCase("TLPE"))
					{
						res=res+"<td "+ couleur+ "><b><font size=\"4\">P.U.Bareme</font></b></td>";					
						res=res+"<td "+ couleur+ "><b><font size=\"4\">N periode actuel</font></b></td>";
					}
					res=res+"<td "+ couleur+ "><b><font size=\"4\">Detail</font></b></td>";
					res=res+"<td "+ couleur+ "><b><font size=\"4\">Qte de bareme</font></b></td>";
					
					if(!libelleTaxe.equalsIgnoreCase("TLPE"))
						res=res+"<td "+ couleur+ "><b><font size=\"4\">Total</font></b></td>";
					
					if(libelleTaxe.equalsIgnoreCase("TLPE"))
					{
						res=res+"<td "+ couleur+ "><b><font size=\"4\">Type</font></b></td>";
						res=res+"<td "+ couleur+ "><b><font size=\"4\">Nbre face/affiche</font></b></td>";
						res=res+"<td "+ couleur+ "><b><font size=\"4\">Date debut</font></b></td>";
						res=res+"<td "+ couleur+ "><b><font size=\"4\">Date fin</font></b></td>";
					}
						
					res=res+"</tr>";
				}
				
				
				
				
				
				
				
				res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\">";
										 			
				String linkBareme= "./entree?action=empl_gestion_bareme.jsp&choix=modifier" 
									+"&codeBareme=" + article.getCodeBareme()
									+"&anneeExercice="+ article.getAnExercice();
				
				String linkOuvrage= "./entree?action=empl_saisie_element_facturation.jsp&traitement=article&choix=modifier" 
						+ "&idarticle="+ article.getId() 
						+ "&numElementFacturation=" +article.getIdElementFacturation() 
						+ "&numEmplacment=" +article.getNumEmplacment() 
						+ "&exercice=" + new Integer(new Date().getYear() +1900).toString()						
						+ "&numRedevable=" +article.getNumRedevable()
						+ "&codeType=" +article.getIdImputation()
						+ "&codeSecteur=" +article.getCodeSecteur()	;						
												
				
				RequestBareme reqBareme = new RequestBareme();
				String codeBareme= article.getCodeBareme();				
				String dureeUnitaire= article.getDureeUnitaire();
				String uniteTravailBareme= article.getUniteTravail();
				String libelleArticle= article.getNom();
				String utiliserPeriodicite= article.getUtiliserPeriodicite();	
				String utiliserProrata= article.getUtiliserProrata();
				String idArticle = article.getId();
				String numeroDePeriode;
				String dateDebut=article.getDateDebutAutorisation();
				String DateFinAutorisation=article.getDateFinAutorisation();
				double surfaceArticle =article.getSurface();				
				double largeurArticle =article.getLargeur();
				double longeurArticle= article.getLongueur();
				double quantiteArticle= article.getQuantite_article();	
				double prixBareme = Double.valueOf(article.getPrixUnitaire());
				String detaille="";
				String stQuantiteTotal="";							
				String stTotalLigneFacture ;
				String libelleBareme;				
				String anneeExercice = GestionDate.getAnneeCourante();
				String typeArrondi=article.getTypeArrondi();	
				String dateFin= GestionDate.getDateAujourdhuiString();
				String stNombreArticle=Arrondi.getFromDoubleToString(quantiteArticle);	
				
				
				DataFromBD dfd = new DataFromBD(); 
				numeroDePeriode = String.valueOf(dfd.getNumeroPeriode(codeBareme,anneeExercice, dateDebut, dateFin));
				
				RequestImputation reqImputation = new RequestImputation();
				Imputation imputation = reqImputation.getImputation(article.getIdImputation(),"");
				String libelleTypeTaxe = imputation.getLibelle();
				
				if(libelleTypeTaxe.equalsIgnoreCase("TAV"))
				{					
					if(!dateDebut.contains("01/01/"))
						numeroDePeriode = String.valueOf(Integer.parseInt(numeroDePeriode)-1);
				}
				//Recuperation du bon prix de bareme 
				//Cas ou le bareme a des prix selon le numero de periode (Periodidicte=true)				
				prixBareme= reqBareme.getPrixPeriode(Integer.parseInt(codeBareme),
						Integer.parseInt(numeroDePeriode),GestionDate.getAnneeCourante());					
				
				String stPrixBareme= Arrondi.arrondir2Digit(prixBareme);			
				
							
				if(!uniteTravailBareme.equalsIgnoreCase("FORFAIT"))
					libelleBareme= article.getBareme()+ " ("+uniteTravailBareme +"/"+ dureeUnitaire+") " ;
				else
					libelleBareme= article.getBareme()+ " ("+uniteTravailBareme +")" ;
				
				if (libelleArticle.startsWith("#")) libelleArticle ="Non precise";
								
				//////////////////////////////////////////////////
				//Ajjustement du type d'arrondi
				//////////////////////////////////////////////////
				if ( typeArrondi.startsWith("N"))
				{
					if (surfaceArticle!=0)
					{			
						//stQuantiteTotal= Arrondi.arrondir2Digit(longeurArticle * largeurArticle * surfaceArticle * quantiteArticle);
						stQuantiteTotal= Arrondi.arrondir2Digit(surfaceArticle * quantiteArticle);
					}
					else
						stQuantiteTotal= Arrondi.arrondir2Digit(longeurArticle * largeurArticle  * quantiteArticle);
				}
				else if ( typeArrondi.startsWith("L"))
				{
					if (surfaceArticle!=0)
					{			
						//stQuantiteTotal= Arrondi.arrindiParLigne(longeurArticle * largeurArticle * surfaceArticle);
						stQuantiteTotal= Arrondi.arrindiParLigne(surfaceArticle);
						stQuantiteTotal= Arrondi.arrondir2Digit(Double.valueOf(stQuantiteTotal).doubleValue()*quantiteArticle);
					}
					else
					{
						stQuantiteTotal= Arrondi.arrindiParLigne(longeurArticle * largeurArticle );
						stQuantiteTotal= Arrondi.arrondir2Digit(Double.valueOf(stQuantiteTotal).doubleValue()*quantiteArticle);
					}					
				}
				else if ( typeArrondi.startsWith("T"))
				{
										
					if (surfaceArticle!=0)
					{			
						//stQuantiteTotal= Arrondi.arrindiParLigne(longeurArticle * largeurArticle * surfaceArticle *quantiteArticle);
						stQuantiteTotal= Arrondi.arrindiParLigne(surfaceArticle *quantiteArticle);
					}
					else
					{
						stQuantiteTotal= Arrondi.arrindiParLigne(longeurArticle * largeurArticle * quantiteArticle);
					}
				}
				
				/////////////////////////////////////////////////								
				if (uniteTravailBareme.equalsIgnoreCase("UNITE")) 
				{	
					detaille=  stQuantiteTotal;					
				}
				else if (uniteTravailBareme.equalsIgnoreCase("M2")) 
				{
					uniteTravailBareme ="M2";
					if (surfaceArticle!=0)
					{						
						detaille= surfaceArticle + " "+  uniteTravailBareme + " * "+ stNombreArticle ;
					}
					else
					{						
						String surface=Arrondi.arrondir2Digit(longeurArticle * largeurArticle);
						detaille= surface + " "+  uniteTravailBareme + " * "+ stNombreArticle ;
					}					
				}
				else if (uniteTravailBareme.equalsIgnoreCase("ML")) 
				{
					String longeur=Arrondi.arrondir2Digit(longeurArticle );
					detaille= longeur + " "+  uniteTravailBareme + " * "+ stNombreArticle ;
				}
				else if (uniteTravailBareme.equalsIgnoreCase("FORFAIT")) 
				{				
					detaille= stQuantiteTotal ;
				}
				
				
				stTotalLigneFacture = Arrondi.arrondir2Digit(prixBareme * Double.valueOf(stQuantiteTotal).doubleValue());
				
				
				
				
				if(dureeUnitaire.equalsIgnoreCase("AN") )
				{
					//Pour les annuelles on cherche soit jusqu a fin de l'anne sinon jusqu a la date precise
					if (DateFinAutorisation.length()==0)
						DateFinAutorisation ="31/12/"+ GestionDate.getAnneeCourante();
				
					int nombreDeJour = GestionDate.getNombreDeJour(dateDebut, DateFinAutorisation);
					
					DebuggerLog4J.logger.debug("DateFinAutorisation "+DateFinAutorisation);
					DebuggerLog4J.logger.debug("Nombre de jour "+nombreDeJour);
					
					if(utiliserProrata.equalsIgnoreCase("true"))
					{																
						if(nombreDeJour < 367 && DateFinAutorisation.length()!=0 ) // on calcule avec proporata 
						{
							if(GestionDate.contient366Jour(GestionDate.getAnneeCourante()))
								stTotalLigneFacture= Arrondi.arrondir2Digit(prixBareme * Double.valueOf(stQuantiteTotal) * nombreDeJour/366);						
							else
								stTotalLigneFacture= Arrondi.arrondir2Digit(prixBareme * Double.valueOf(stQuantiteTotal) * nombreDeJour/365);					
						}					
					}
				}
				
				
				
				


//////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////String a afficher///////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////				
				if (etatArticle.equalsIgnoreCase("ControlerAlerte"))
				{					
					res= res+"<td align=\"left\" ><img   border=\"0\" src=\"images/rouge.jpg\" width=\"15\" height=\"15\">Alerte</td>";
				}
				else if (etatArticle.equalsIgnoreCase("ControlerPresent"))
				{						
					res= res+"<td align=\"left\"><img  border=\"0\" src=\"images/vert.jpg\" width=\"15\" height=\"15\">CPresent</td>";
				}
				else if (etatArticle.equalsIgnoreCase("ControlerAbsent"))
				{
					res= res+"<td align=\"left\" ><img  border=\"0\" src=\"images/gris.jpg\" width=\"15\" height=\"15\">CAbsent</td>";
				}
				else if (etatArticle.equalsIgnoreCase("Facturer"))
				{
					res= res+"<td align=\"left\" ><img  border=\"0\" src=\"images/vert.jpg\" width=\"15\" height=\"15\">Facture</td>";
				}
				else if (etatArticle.equalsIgnoreCase("AFacturer"))
				{
					res= res+"<td align=\"left\" ><img border=\"0\" src=\"images/bleu.jpg\" width=\"15\" height=\"15\">AFacturer</td>";
				}
				else if (etatArticle.equalsIgnoreCase("FacturerAControler"))
				{
					res= res+"<td align=\"left\"><img  border=\"0\" src=\"images/jaune.jpg\" width=\"15\" height=\"15\">FAControler</td>";
				}
				else if (etatArticle.equalsIgnoreCase("ControlerAFacturer"))
				{
					res= res+"<td align=\"left\" ><img  border=\"0\" src=\"images/bleu.jpg\" width=\"15\" height=\"15\">CAFacturer</td>";
				}
				else if (etatArticle.equalsIgnoreCase("NePlusFacturer"))
				{
					res= res+"<td align=\"left\" ><img  border=\"0\" src=\"images/gris.jpg\" width=\"15\" height=\"15\">NPlusF</td>";
				}
				else if (etatArticle.equalsIgnoreCase("NePasFacturer"))
				{
					res= res+"<td align=\"left\"><img  border=\"0\" src=\"images/gris.jpg\" width=\"15\" height=\"15\">NPasF</td>";
				}
				else if (etatArticle.equalsIgnoreCase("Rembourser"))
				{
					res= res+"<td align=\"left\"><img  border=\"0\" src=\"images/gris.jpg\" width=\"15\" height=\"15\">Rembourse</td>";
				}
				else
					res=res+"<td align=\"left\" >"+"-"+"</td>";
				

				
				/*//System.out.println("------------ARTICLE  "+ idArticle + "-----------------");
				//System.out.println("libelle= "+ libelleArticle);
				//System.out.println("Prorata= "+utiliserProrata);
				//System.out.println("Peridocite= "+utiliserPeriodicite);
				//System.out.println("PrixBareme= "+prixBareme);
				//System.out.println("\n");
				*/
				
				
				
				
				res=res+"<td><a href= "+ linkBareme+" >"+libelleBareme+"</a></td>";
				//res=res+"<td>"+libelleBareme+"</td>";
				res=res+"<td><a href= "+ linkOuvrage+" >"+ libelleArticle+"</a></td>";
				
				if(!libelleTaxe.equalsIgnoreCase("TLPE"))
				{
					res=res+"<td>"+stPrixBareme+"</td>";
					res=res+"<td>"+numeroDePeriode+"</td>";
				}
				res=res+"<td>"+detaille+"</td>";
				
			
				res=res+"<td>"+ Arrondi.arrondir2Digit(Double.valueOf(stQuantiteTotal)) +"</td>";
		
				if(!libelleTaxe.equalsIgnoreCase("TLPE"))
					res=res+"<td>"+stTotalLigneFacture+"</td>";	
				
				total= total + Double.valueOf(stTotalLigneFacture).doubleValue();
				

				//System.out.println("stTotalLigneFacture="+stTotalLigneFacture);
				//System.out.println("stTotalLigneFacture="+Double.valueOf(stTotalLigneFacture).doubleValue());
				
				if(libelleTaxe.equalsIgnoreCase("TLPE"))
				{
					String DateFinAutorisationAafficher=article.getDateFinAutorisation();						
					if(dateDebut.length()==0) dateDebut ="-";
					if(DateFinAutorisationAafficher.length()==0)  DateFinAutorisationAafficher ="-";
					
					String type = article.getTypeOuvrage();
					String nombreFaceAffcihe= article.getNombreFaceAffiche();
					res=res+"<td>"+type+"</td>";
					res=res+"<td>"+nombreFaceAffcihe+"</td>";
					res=res+"<td>"+dateDebut+"</td>";
					res=res+"<td>"+DateFinAutorisationAafficher+"</td>";	
				}			
				res=res+"</tr>";
				 tab.addElement(res);
				 res="";
			}// Fin boucle FOR
			
			res= tab.toString();
			res = res.replaceAll(",", "");
			res = res.replaceAll("\\[", "");
			res = res.replaceAll("\\]", "");
			res=res+"<tr>";
			res=res+"<td  colspan=\"7\"> </td>";
			String stTotal = Arrondi.arrondir2Digit(total);
			if(!libelleTaxe.equalsIgnoreCase("TLPE"))
				res=res+"<td><B><font color=\"#FF0000\">TOTAL="+stTotal+"</font></B></td>";
			res=res+"</tr>";
			res=res+"</table>";
				
		}		
		else if (contenuInitale.size() ==0)
		{
			res= "<font size=\"4\">Aucun ouvrage dans cet emplacement  </font>" ;
		}
		return res;
	}
	
	public static String genererListeDeroulanteMotifAnnulation(String nomLD, 
			String selection, Vector Contenu, int taille, boolean vide) 
	{		
		String listeDeroulante;
		listeDeroulante = "<select " 
				+ " name=\"" + nomLD + "\">\n";
		if (vide)
			listeDeroulante = listeDeroulante
					+ "<option value=\"\" ></option>\n";
		int longueur = Contenu.size();
		for (int i = 0; i < longueur; i++) 
		{
			listeDeroulante = listeDeroulante + "\n <option ";
			if (selection.equalsIgnoreCase((String)Contenu.elementAt(i)))
				listeDeroulante = listeDeroulante + "selected ";
			listeDeroulante = listeDeroulante + "value=\"" + (String)Contenu.elementAt(i)
					+ "\" >" + (String)Contenu.elementAt(i) + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";			
		return listeDeroulante;
	}
	
	public static String genererTableaulisteUtilisateur(Vector input)
	{
		String res="";
		try 
		{
			if(input.size() >0)
			{
				res="<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width=\"100%\" "  +
				" borderColorLight=\"#c0c0c0\" border=\"1\" align=\"center\" height=\"20\">";
				res= res+"<td bgcolor=\"#FFFFFF\" width=\"25%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Identifiant"
				+ "</font></b></td>";
				res= res+"<td bgcolor=\"#FFFFFF\" width=\"25%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Nom - Prenom"
				+ "</font></b></td>";
				res= res+"<td bgcolor=\"#FFFFFF\" width=\"25%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Type d'utilisateur"
				+ "</font></b></td>";			
				
				////System.out.println("TAILLE="+input.size());
				for (int i = 0; i < input.size(); i++) 
				{								
					res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" >";
					Utilisateur utilisateur = ((Utilisateur)input.elementAt(i));
					String codeUtilisateur = utilisateur.getId();
					String nomPrenomUtilisateur = utilisateur.getNom() + " " + utilisateur.getPrenom();
					String typeUtilisateur = utilisateur.getTypeUtilisateur();					
					String link ="./entree?action=empl_gestion_utilisateur.jsp" +
							"&choix=modifier&codeUtilisateur="+codeUtilisateur;
					res= res+"<td align=\"center\"><a href="+ link +">"+ codeUtilisateur + "</td>";
					res= res+"<td align=\"center\">"+ nomPrenomUtilisateur + "</td>";			
					res= res+"<td align=\"center\">"+ typeUtilisateur + "</td>";		
					
					res=res+"</tr>";			
				}			
				res=res+"</table>";
			}
			else
			{
				res ="Aucun utilisateur ne correspond a vos critères de recherche";
			}
			////System.out.println(res);
		}
		catch (Exception e) 
		{
			DebuggerLog4J.logger.fatal(e.getMessage());
		}
		return res;
	}
	
	public static String genererZoneDeTexeGrise(String nomChamps, String valeur) 
	{
		String res;	
		res =	"<input type ='text' readonly value ='"+valeur + "' >\n";		
		return res;
	}
	

	public static String genererTableaulisteRue(Vector input)
	{
		
		String res="";
		try 
		{
			if(input.size() >0)
			{
			res="<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width=\"100%\" "  +
			" borderColorLight=\"#c0c0c0\" border=\"1\" align=\"center\" height=\"20\">";
			res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Ne"
			+ "</font></b></td>";
			res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Adresse"
			+ "</font></b></td>";			
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Quartier"
			+ "</font></b></td>";
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Code rivolie"
			+ "</font></b></td>";
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "N debut"
			+ "</font></b></td>";
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "N fin"
			+ "</font></b></td>";
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Code postal"
			+ "</font></b></td>";
			}
			else
			{
				res= "Aucune rue ne correspond a vos critères de recherche";
			}
			
			////System.out.println("TAILLE="+input.size());
			for (int i = 0; i < input.size(); i++) 
			{
				res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" >";
				Rue rue = ((Rue)input.elementAt(i));
				String idRue = rue.getCodeVoie();
				String adresse = rue.getPrefixe() + " " + rue.getLiaison() +" " + rue.getNomrue();
				
				String quartier = " "+rue.getNomquartier();
				String codeRivolie = rue.getCodeRivolie();
				String debutNumero = rue.getDebutNumeroRue();
				String finNumero = rue.getFinNumeroRue();
				String codePostal = rue.getCodePostal();
				String link="./entree?action=empl_gestion_Rue.jsp&choix=modifier&codeRue="+idRue;
				res= res+"<td align=\"center\"><a href="+ link +">"+ idRue + "</td>";
				res= res+"<td align=\"center\">"+ adresse + "</td>";			
				res= res+"<td align=\"center\">"+ quartier + "</td>";
				if (codeRivolie.length() == 0) codeRivolie = "-";
				res= res+"<td align=\"center\">"+ codeRivolie + "</td>";
				res= res+"<td align=\"center\">"+ debutNumero + "</td>";			
				res= res+"<td align=\"center\">"+ finNumero + "</td>";
				res= res+"<td align=\"center\">"+ codePostal + "</td>";
				res=res+"</tr>";	
						
			}			
			res=res+"</table>";			
		}
		catch (Exception e) 
		{
			DebuggerLog4J.logger.fatal(e.getMessage());
		}			
		return res;
	}
	
	public static String genererTableauIdoss(Vector input, boolean valide)
	{
		String res="",elem;
		if(input.size() != 0)
		{
		
			
			res="<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width=\"100%\" "  +
			" borderColorLight=\"#c0c0c0\" border=\"1\" align=\"center\" height=\"20\">";
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Redevable"
			+ "</font></b></td>";
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Type de taxe"
			+ "</font></b></td>";
			
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Emplacement"
			+ "</font></b></td>";	
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Type Infos"
			+ "</font></b></td>";
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Infos IDOSS"
			+ "</font></b></td>";
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Date debut"
			+ "</font></b></td>";
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Date Fin d'autorisation"
			+ "</font></b></td>";
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Rapprochement"
			+ "</font></b></td>";
			
			////System.out.println("TAILLE="+input.size());
			for (int i = 0; i < input.size(); i++) 
			{
				LigneIdoss ligneIdoss =  (LigneIdoss)input.elementAt(i);
				
				String dateDebutAutorisation =  ligneIdoss.getDateDebutAutorisation();
				if(dateDebutAutorisation== null || dateDebutAutorisation.length() == 0) 		dateDebutAutorisation="-";
				
				String dateFinAutorisation =  ligneIdoss.getDateFinAutorisationIdoss();
				if(dateFinAutorisation== null ||dateFinAutorisation.length() == 0) 			dateFinAutorisation="-";
				
				String infosIdoss =  ligneIdoss.getInfosIdoss();
				if(infosIdoss== null ||  infosIdoss.length() == 0) infosIdoss="-";
				String chekced ="checked";
				String action =" onclick=majLigneIdoss('"+ ligneIdoss.getIdRapprochementIdoss() +"')";
				if ( ligneIdoss.getRapprochementValide().equalsIgnoreCase("false"))
					chekced="";
				
				String  zoneACocher ="<input type='checkbox' "+  action + " " + chekced +" name='"+ ligneIdoss.getIdRapprochementIdoss() +"' >";
				String etat = ligneIdoss.getRapprochementValide();
				RequestEmplacement requestEmplacement = new RequestEmplacement();
				Emplacement emplacement=  requestEmplacement.getEmplacement(ligneIdoss.getIdEmplacement());
				RequestRedevable  requestRedevable = new RequestRedevable();
				Redevable redevable = requestRedevable.getRedevable(emplacement.getNumRedevable());
				String linkEmplacment ="./entree?action=empl_gestion_emplacementodp.jsp" +
									   "&choix=modifier&optionTransfert=role&origine=origine" +
									   "&numEmplacment="+ligneIdoss.getIdEmplacement();
				res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\">";			

				String linkRedevbale="./entree?action=empl_gestion_redevable.jsp&" +
									  "choix=modifier&numRedevable="+redevable.getId();
				String nomRedevable = redevable.getRaisonSocialeRedevable() + " " ;
				nomRedevable = nomRedevable + redevable.getNomRedevable() + " " ;
				nomRedevable = nomRedevable + redevable.getPrenomRedevable() + " " ;
				res= res+"<td align=\"center\"><a href="+ linkRedevbale +">" +nomRedevable + "</a>" ;
				
				res= res+"<td align=\"center\">"+ ligneIdoss.getTypetaxe() + "</td>";
				res= res+"<td align=\"center\"><a href="+ linkEmplacment +">";
				res= res+ "  "+ emplacement.getNumRue() + " " + emplacement.getAdresse1(); 
				res= res+  emplacement.getAdresse2() +" ";
				res= res+  emplacement.getAdresse3() +" ";
				
				res= res+  "</a></td>";
				res= res+"<td align=\"center\">"+ ligneIdoss.getTypeInfos()+ "</td>";
				res= res+"<td align=\"center\">"+ infosIdoss + "</td>";
				res= res+"<td align=\"center\">"+ dateDebutAutorisation + "</td>";
				res= res+"<td align=\"center\">"+ dateFinAutorisation + "</td>";
				res= res+"<td align=\"center\">"+ zoneACocher  + "</td>";
				res=res+"</tr>";
			}			
			res=res+" <tr>";
				res=res+"<td  colspan=8 align=\"center\">";
				if (!valide)
					res=res+"<font color=\"#0000FF\"><b><u><a href=\"javascript:validerTousLesLigneIdoss();\">Selectionner tous</a></u></b></font>";
				else
					res=res+"<font color=\"#0000FF\"><b><u><a href=\"javascript:nePasValiderTousLesLigneIdoss();\">Selectionner tous</a></u></b></font>";					
				res=res+"</td>";
				res=res+"</tr>";
			res=res+"</table>";
			////System.out.println(res);
		}
		else
		{
			res= " Pas d'elements dans cette rubrique ";
		}
		return res;
	}
	
	
	public static String genererTableauNonReconnu(Vector input)
	{
		String res="",elem;
		if(input.size() != 0)
		{			
			res="<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width=\"100%\" "  +
			" borderColorLight=\"#c0c0c0\" border=\"1\" align=\"left\" height=\"20\">";
			
			
			////System.out.println("TAILLE="+input.size());
			for (int i = 0; i < input.size(); i++) 			{
				
				res=res+"<tr>";			
				res= res+"<td align=\"left\">"+ input.elementAt(i) + "</td>";			
				res=res+"</tr>";
			}				
			res=res+"</table>";
			////System.out.println(res);
		}
		else
		{
			res= " <align=\"center\">Pas d'elements dans cette rubrique </align>";
		}
		return res;
	}
	
	
	
	
	
	
	
	
	public static String genererTableaulisteBatchIdoss(Vector input)
	{
		
		String res="";
		
		try 
		{
			if(input.size() >0)
			{
			res="<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width=\"100%\" "  +
			" borderColorLight=\"#c0c0c0\" border=\"1\" align=\"center\" height=\"20\">";
			res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Id"
			+ "</font></b></td>";
			res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Date de lancement"
			+ "</font></b></td>";			
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Nombre de lignes du fichier"
			+ "</font></b></td>";
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Nombre de rapprochements possibles"
			+ "</font></b></td>";
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Nombre de rapprochements impossibles"
			+ "</font></b></td>";
			}
			else
			{
				res= "Aucun batch ne correspond a vos critères de recherche";
			}
			
			////System.out.println("TAILLE="+input.size());
			for (int i = 0; i < input.size(); i++) 
			{
				res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" >";
				
				BatchRapprochementIdoss batchRapprochementIdoss = ((BatchRapprochementIdoss)input.elementAt(i));
				
				
				String idRapprochemenIdoss=batchRapprochementIdoss.getIdRapprochemenIdoss(); 
				String dateLancement =batchRapprochementIdoss.getDateLancement();
				String nombreDeLigneLu=batchRapprochementIdoss.getNombreDeLigneLu();
				String nombreDeLigneTrouve=batchRapprochementIdoss.getNombreDeLigneTrouve();
				String nombreDeLigneNonTrouve=batchRapprochementIdoss.getNombreDeLigneNonTrouve();
				
				String nombreEmplacement=batchRapprochementIdoss.getNombreEmplacement();
				String link="./entree?action=gestionBatchIdoss.jsp&dateDeCreation="+ dateLancement
						+"&choix=modifier&numeroBatchIdoss="+idRapprochemenIdoss;
			
				
				res= res+"<td align=\"center\"><a href="+ link +">"+ idRapprochemenIdoss + "</td>";
				res= res+"<td align=\"center\">"+ dateLancement + "</td>";			
				res= res+"<td align=\"center\">"+ nombreDeLigneLu + "</td>";
				res= res+"<td align=\"center\">"+ nombreDeLigneTrouve + "</td>";
				res= res+"<td align=\"center\">"+ nombreDeLigneNonTrouve + "</td>";
				res=res+"</tr>";
										
			}			
			res=res+"</table>";			
		}
		catch (Exception e) 
		{
			DebuggerLog4J.logger.fatal(e.getMessage());
		}			
		return res;
	}
	
	public static String genererListeModelesCouriers(String nomLD, Vector contenu) 
	{
		String listeDeroulante;	
		listeDeroulante =	"<select size=\"" + String.valueOf(1) + "\" "
				+ "name=\"" + nomLD + "\" >\n";	
		
		int longueur = contenu.size();
		for (int i = 0; i < longueur; i++) 
		{
			ModeleCourier m = (ModeleCourier)contenu.elementAt(i);
			listeDeroulante = listeDeroulante + "\n <option ";			
			listeDeroulante = listeDeroulante + "value=\"" + m.getIdModeleCourier()
					+ "\" >" + m.getNomModele() + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";
		return listeDeroulante;
	}
	
	
	public static String genererTableaulisteModeleCourier(Vector input)
	{
		
		String res="";
		try 
		{
			if(input.size() >0)
			{
			res="<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width=\"100%\" "  +
			" borderColorLight=\"#c0c0c0\" border=\"1\" align=\"center\" height=\"20\">";
			res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Identifiant"
			+ "</font></b></td>";
			res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Nom du modèle "
			+ "</font></b></td>";			
			res= res+"<td bgcolor=\"#FFFFFF\"  align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Chemin vers le modèle"
			+ "</font></b></td>";			
			}
			else
			{
				res= "Aucun modèle ne correspond à vos critères de recherche";
			}
			
			////System.out.println("TAILLE="+input.size());
			for (int i = 0; i < input.size(); i++) 
			{
				res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" >";
				ModeleCourier  modeleCourier = ((ModeleCourier)input.elementAt(i));
				 
				String idModeleCourier= modeleCourier.getIdModeleCourier();
				String nomModele= modeleCourier.getNomModele();
				String cheminModele=modeleCourier.getCheminModele();
				
				String link="./entree?action=empl_gestion_ModeleCourier.jsp" +
						    "&choix=modifier&idModeleCourier="+idModeleCourier;
				
				res= res+"<td align=\"center\"><a href="+ link +">"+ idModeleCourier + "</td>";
				res= res+"<td align=\"center\">"+ nomModele + "</td>";			
				res= res+"<td align=\"center\">"+ cheminModele + "</td>";				
				res=res+"</tr>";	
						
			}			
			res=res+"</table>";			
		}
		catch (Exception e) 
		{
			DebuggerLog4J.logger.fatal(e.getMessage());
		}			
		return res;
	}
	
	
	
	

	public static String genererTableauListeEtatOuvrage(Vector input)
	{
		
		Vector retour= new Vector();
		String res="";
		if (input.size()>0)
		{				
			res="<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width=\"100%\" "  +
			" borderColorLight=\"#c0c0c0\" border=\"1\" align=\"center\" height=\"20\">";
			res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Etat"
			+ "</font></b></td>";
			
			res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Date de modification"
			+ "</font></b></td>";
			
			res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Action"
			+ "</font></b></td>";	
			res=res+"</tr>";
				
		
			for (int i = 0; i < input.size(); i++) 
			{
								
				HistoriqueEtatOuvrage historiqueEtatOuvrage=  (HistoriqueEtatOuvrage)input.elementAt(i);
				String etatOuvrage =historiqueEtatOuvrage.getEtatOuvrage();
				String dateModification=historiqueEtatOuvrage.getDateModification(); 
				String typeModifcation=historiqueEtatOuvrage.getTypeModifcation();
				
				res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;
				res= res+ etatOuvrage  + "</font></b></td>";
				
				res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;
				res= res+ dateModification  + "</font></b></td>";
				
				res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;
				res= res+ typeModifcation  + "</font></b></td>";
									
				res=res+"</tr>";
				retour.addElement(res);
				res="";
					
			}	
			
			for (int i = 0; i < retour.size(); i++) 
			{
				res = res + retour.elementAt(i);				
			}
						
			res=res+"</table>";			
		}	
		return res;
	}
	
	
	
	public static String genererTableauListeRuptureControle(Vector input)
	{
		
		Vector retour= new Vector();
		String res="";
		if (input.size()>0)
		{				
			res="<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width=\"100%\" "  +
			" borderColorLight=\"#c0c0c0\" border=\"1\" align=\"center\" height=\"20\">";
			res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Ouvrage"
			+ "</font></b></td>";
			
			res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Adresse"
			+ "</font></b></td>";
			
			res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "N dernière periode facturee"
			+ "</font></b></td>";
			
			res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "N periode actuelle"
			+ "</font></b></td>";
			
			res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Date dernier contrôle"
			+ "</font></b></td>";
			
			res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Date prochain contrôle"
			+ "</font></b></td>";
			
			res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Ne plus facturer"
			+ "</font></b></td>";
			
			res=res+"</tr>";
				
		
			for (int i = 0; i < input.size(); i++) 
			{
								
				Article article =  (Article)input.elementAt(i);		
				String nomOuvrage = article.getLibelleBareme();
				
				String linkEmplacment=  "./entree?action=empl_gestion_emplacementodp.jsp"
					  + "&choix=modifier&numEmplacment="+ article.getNumEmplacment();
				
				String linkOuvrage= "./entree?action=empl_saisie_element_facturation.jsp&traitement=article&choix=modifier" 
					+ "&idarticle="+ article.getId();
				
				res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;
				res= res+"<a href ="+ linkOuvrage +">";
				res= res+  nomOuvrage + "</font></b></td>";	
				res= res+"</a>";	
				
				res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;
				res= res+"<a href ="+ linkEmplacment +">";
				res= res+  article.getAdresseOuvrage() + "</font></b></td>";	
				res= res+"</a>";
		
				
				res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;				
				res= res+ ""+ article.getDernierePeriodeFacture() + "</font></b></td>";
				
				res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;				
				res= res+ ""+ article.getNumeroDePeriodeActuelle() + "</font></b></td>";
				
				String dateDernierControle=article.getDateDernierControle();
				String dateProchainControl=article.getDateProchainControl();
				if(dateDernierControle.length()==0) dateDernierControle ="-";
				if(dateProchainControl.length()==0) dateProchainControl ="-";
				
				
				
				
				String action =" onclick=arreterFacturation('"+ article.getId() +"')";
				
				String  zoneACocher ="<input type='checkbox' "+  action +" name='"+ article.getId() +"' >";
				
				res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;				
				res= res+ ""+ dateDernierControle + "</font></b></td>";
				
				res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;				
				res= res+ ""+dateProchainControl + "</font></b></td>";
				
				res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;				
				res= res+ ""+zoneACocher + "</font></b></td>";
				
				res=res+"</tr>";				
				retour.addElement(res);
				res="";
			}				
				
					res= retour.toString();
					res = res.replaceAll(",", "");
					res = res.replaceAll("\\[", "");
					res = res.replaceAll("\\]", "");
			
		
						
			res=res+"</table>";			
		}	
		return res;
	}
	
	
	public static String genererListeDeroulanteUser(String nomLD, Vector contenu) 
	{
		String listeDeroulante;	
		listeDeroulante =	"<select size=\"" + String.valueOf(1) + "\" "
				+ "name=\"" + nomLD + "\" >\n";		
		int longueur = (contenu.size());
		listeDeroulante = listeDeroulante + "\n <option> </option>\n";
		for (int i = 0; i < longueur; i++) 
		{
			Utilisateur  utilisateur  = (Utilisateur)contenu.elementAt(i);
			listeDeroulante = listeDeroulante + "\n <option ";			
			listeDeroulante = listeDeroulante + "value=\"" + utilisateur.getId()
					+ "\" >" + utilisateur.getNom() + " " + utilisateur.getPrenom() + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";
		//System.out.println(listeDeroulante);
		return listeDeroulante;
	}
	//paul avec sélection
	public static String genererListeDeroulanteUser(String nomLD, Vector contenu,String selection) 
	{
		String listeDeroulante;	
		listeDeroulante =	"<select size=\"" + String.valueOf(1) + "\" "
				+ "name=\"" + nomLD + "\" >\n";		
		int longueur = (contenu.size());
		listeDeroulante = listeDeroulante + "\n <option> </option>\n";
		for (int i = 0; i < longueur; i++) 
		{
			Utilisateur  utilisateur  = (Utilisateur)contenu.elementAt(i);
			listeDeroulante = listeDeroulante + "\n <option ";	
			if (selection.equalsIgnoreCase(utilisateur.getId())) listeDeroulante = listeDeroulante + "selected ";
			listeDeroulante = listeDeroulante + "value=\"" + utilisateur.getId()
					+ "\" >" + utilisateur.getNom() + " " + utilisateur.getPrenom() + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";
		//System.out.println(listeDeroulante);
		return listeDeroulante;
	}
	
	
	
	public static String genererTableauListeControle(Vector input)
	{
		
		Vector retour= new Vector();
		String res="";
		if (input.size()>0)
		{				
			res="<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width=\"100%\" "  +
			" borderColorLight=\"#c0c0c0\" border=\"1\" align=\"center\" height=\"20\">";
			res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Ouvrage"
			+ "</font></b></td>";
			
			
			res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Date contrôle"
			+ "</font></b></td>";			
			
					
			res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Contrôleur"
			+ "</font></b></td>";
			
			res= res+"<td bgcolor=\"#FFFFFF\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
			+ "Quartier"
			+ "</font></b></td>";
			
			res=res+"</tr>";
				
		
			for (int i = 0; i < input.size(); i++) 
			{
								
				HistoriqueControle historiqueControle =  (HistoriqueControle)input.elementAt(i);				
				String linkOuvrage= "./entree?action=empl_saisie_element_facturation.jsp&traitement=article&choix=modifier" 
					+ "&idarticle="+ historiqueControle.getIdOuvrage();
				
				res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;
				res= res+"<a href ="+ linkOuvrage +">";
				res= res+  historiqueControle.getIdOuvrage()  + "</font></b></td>";	
				res= res+"</a>";				
				
				res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;				
				res= res+ ""+ historiqueControle.getDateControle() + "</font></b></td>";
				
				res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;				
				res= res+ ""+ historiqueControle.getNomControleur() + "</font></b></td>";
				
				res= res+"<td  align=\"center\"><b><font color=\"#000080\" size=\"3\">" ;				
				res= res+ ""+ historiqueControle.getNomQuartier() + "</font></b></td>";
				
				res=res+"</tr>";				
				retour.addElement(res);
				res="";
			}				
				
					res= retour.toString();
					res = res.replaceAll(",", "");
					res = res.replaceAll("\\[", "");
					res = res.replaceAll("\\]", "");
			
		
						
			res=res+"</table>";			
		}
		else
		{
			res= "<font size=\"4\">Il n'y a aucune reponse qui correspond avec ces criteres de recherche. </font>" ;
		}
		return res;
	}
	
	
	
	
	public String genererListeFactureUtilsateurPdfNonPaye(Vector contenu )
	{
		String res="";			
		////System.out.println("Nombre de facture="+contenu.size());
		if (contenu.size()>0)
		{
			res=res+"<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"; 
			res=res+"<tr align=\"center\" bgcolor=\"#FFFFFF\">";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Facture</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Date de creation de la facture</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Montant total</font></b></td>";					
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Solde</font></b></td>";		
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Paiement</font></b></td>";
			res=res+"</tr>";
			for (int i = 0; i < contenu.size(); i++) 
			{
				////System.out.println("FACTURE numero"+i);
				Facture facture = (Facture)contenu.elementAt(i);
				System.out.println("facture=>"+facture.getNumeroFacture()+":"+facture.getSoldeFacture());
				String repertoirePdf= facture.getNomDossier();
				String nomFichier= String.valueOf(facture.getNumeroFacture());		
				String dateCreation= String.valueOf(facture.getDateCreationFacture());		
				String etatFacture= facture.getEtat();
				String idReclamation= facture.getIdReclamation();
				dateCreation = dateCreation.substring(0,10);		
				String montantTotalFacture= facture.getMontantTotalFacture();
				String soldeFacture= facture.getSoldeFacture();
				res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\">";	
				
				res=res+"<td >" +
						"<img src=\"images/pdfbleue.png\" border=\"0\" height=\"21\" width=\"20\">&nbsp"+						
						"<a href=./entree?action=gestionFacture.jsp&numeroFacture="+ nomFichier+">" +
						"Facture No"+nomFichier+".pdf" +"</a></td>";
				String link="./entree?action=empl_gestion_reclamations.jsp&choix=modifier&idReclamation="+idReclamation;
				res=res+"<td>"+ dateCreation+"</td>";
				res=res+"<td>"+ montantTotalFacture+"</td>";
				res=res+"<td>"+ soldeFacture+"<input id=\"V"+ facture.getNumeroFacture() +"\"type=\"hidden\" value=\""+ soldeFacture+"\"></td>";
				String disabled =" ";
				String checked=" ";
				String envoyerALaTresorie = facture.getEnvoyerALaTresorie();
				if (envoyerALaTresorie.equalsIgnoreCase("true"))				
				{
					res=res+"<td>Envoye a la tresorie</td>";
				}
				else
				{
					if(soldeFacture.equalsIgnoreCase("0.00") || soldeFacture.equalsIgnoreCase("0"))					
					{
						disabled ="disabled";
						checked="checked";					
					}
					res=res+"<td><input type=\"checkbox\" "+ checked+ " " + disabled+" onclick=\"mettreAJourTableau(this.name)\" name=\""+facture.getNumeroFacture()+"\"/></td>";
				}
				
					
				res=res+"</tr>";		
			}	    
			res=res+"</table>";	
		}
		else
		{			
			res=res+"Aucun paiement n'est disponible ";				
		}
			
		return res;
	}
	
	
	
	
	
	public static String genererTableauFactureParPayement(Vector input)
	{
		String res="";
		try 
		{
			if(input.size() >0)
			{
				res="";
				for (int i = 0; i < input.size(); i++) 
				{
					String numeroFacture=""+input.elementAt(i);
					res =  res +"<tr align='center'><td>" +
							"<a href ='./entree?action=gestionFacture.jsp&numeroFacture="+ numeroFacture +"'> " +
							" Facture No"+numeroFacture+".pdf </a></td></tr>";	
				}			 
			}
			else
			{
				res ="Aucune facture";
			}
			////System.out.println(res);
		}
		catch (Exception e) 
		{
			DebuggerLog4J.logger.fatal(e.getMessage());
		}
		return res;
	}
	
	
	public  String genererListeDesPayement( ArrayList contenu )
	{
		String res="";
		ArrayList  retour = new ArrayList();
		if (contenu.size()!=0)
		{
			res=res+"<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"; 
			res=res+"<tr align=\"center\" bgcolor=\"#FFFFFF\">";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">N Paiement</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Etat</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">N redevable</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Date du paiement</font></b></td>";	
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Type de paiement</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Montant du paiement</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Nombre de factures payees</font></b></td>";						
			res=res+"</tr>";
			retour.add(res);			
			Payement payement ; 
			for (int i = 0; i < contenu.size(); i++) 
			{
				payement = (Payement)contenu.get(i);
				int nbrefacturepaye = Integer.parseInt(payement.getNombreDeFacturePayee());
				res="<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\">";
				if(nbrefacturepaye > 0)
					res=res+"<td><a href=./entree?action=gestionPayement.jsp&idPayement="+payement.getIdPayement()+">" +payement.getIdPayement()+ "</a></td>";
				//lien vers la page d'edition de paiement aure que type marche
				else
					res=res+"<td><a href=./entree?action=empl_gestion_paiementMarche.jsp&paiementMarche=true&choix=modifier&idPayement="+payement.getIdPayement()+">" +payement.getIdPayement()+ "</a></td>";
				    //lien vers la page d'edition de paiement de type marche
				
				
				res=res+"<td>"+payement.getEtat()+"</td>";
				res=res+"<td><a href=./entree?action=empl_gestion_redevable.jsp" +
						"&choix=modifier&boton=modifier&typeRecherche=role" +
						"&numRedevable=" +payement.getNumRedevable() +">"+payement.getNumRedevable()+"</a>";
				
				res=res+"<td>"+payement.getDatePayement()+"</td>";
				res=res+"<td>"+payement.getTypePayement()+"</td>";
				
				DecimalFormat df = new DecimalFormat("0.00");
				String montantPayement = df.format(Double.valueOf(payement.getMontantPayement()).doubleValue());
				montantPayement = montantPayement.replaceAll(",", ".");
				res=res+"<td>"+ montantPayement+"</td>";
				
				res=res+"<td>"+payement.getNombreDeFacturePayee()+"</td>";
				
				res=res+"</tr>";
				retour.add(res);
			}
			
			/*for (int i = 0; i < contenu.size(); i++) 
			{
				Facture facture = (Facture)contenu.elementAt(i);
				////System.out.println(facture.getNomDossier());
				String repertoirePdf= facture.getNomDossier();
				String nomFichier= String.valueOf(facture.getNumeroFacture());
				String typeTaxe= String.valueOf(facture.getTypeTaxe());
				String dateCreation= String.valueOf(facture.getDateCreationFacture());
				int idClient= facture.getIdClient();
				String etatFacture= facture.getEtat();			
				int idFacture = facture.getIdFacture();
				String couleur1="";
				res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\">";
				res=res+"<td >" +
						"<img src=\"./images/pdfbleue.png\" border=\"0\" height=\"21\" width=\"20\">&nbsp"+
						"<a  href=./entree?action=gestionFacture.jsp&numeroFacture="+ facture.getNumeroFacture() + ">" +
						"Facture No"+nomFichier+".pdf" +"</a></td>";
				
				if(etatFacture.equalsIgnoreCase("ANNULEE"))
					res= res+"<td><img border=\"0\" src=\"images/rouge.jpg\" width=\"15\" height=\"15\"></td>";
				else
					res= res+"<td><img border=\"0\" src=\"images/vert.jpg\" width=\"15\" height=\"15\"></td>";
					
				
				res=res+"<td>"+ typeTaxe +"</td>";
				res=res+"<td>"+ dateCreation+"</td>";
				String linkUser= "./entree?action=empl_gestion_redevable.jsp&choix=modifier" +
						"&boton=ajouter&typeRecherche=role&optionTransfert=role" +
						"&typeRedevable=normal&origine=origine&numRedevable="+ idClient;
				res=res+"<td><a href =" + linkUser+">"+ idClient +"</a></td>";					
				res=res+"</tr>";	
				tab.addElement(res);
				res="";
				*/
				
		}	
		else
		{
			retour.add("<font size=\"4\">Il n'y a aucun paiement qui correspond à ces criteres de recherche. </font>") ;
		}	
		res = retour.toString();
		res = res.replaceAll(",", "");
		res = res.substring(1, res.length()-1);
		res =res+"</table>";
		return res;	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	//////////////////////////////////////////////////////////
	//OPTIMISATION 
	public String genererTableauListeBareme(Vector<Bareme> contenu )
	{

		String res="";
		ArrayList list = new ArrayList();
		if (contenu.size()!=0)
		{		
			list.add("<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"); 
			list.add("<tr align=\"center\" bgcolor=\"#FFFFFF\">");			
			list.add("<td "+ couleur+ "><b><font size=\"4\">Code</font></b></td>");
			list.add("<td "+ couleur+ "><b><font size=\"4\">Annee</font></b></td>");
			list.add("<td "+ couleur+ "><b><font size=\"4\">Libelle</font></b></td>");
			list.add("<td "+ couleur+ "><b><font size=\"4\">Type de taxe</font></b></td>");							
			list.add("</tr>");

		Bareme bareme = new Bareme();		
		for (int i = 0; i < contenu.size(); i++) 
		{
			//System.out.println("TTTTTTTTT"+i);
			bareme = contenu.elementAt(i);
			String t = "";
			t = "<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\">"
			+"<td  height=\"1\">"
			+"<a href=./entree?action=empl_gestion_bareme.jsp&choix=modifier" +
					"&codeBareme="+bareme.getCode()+	
					"&anneeExercice="+bareme.getAnExercice()
			+">"
			+bareme.getCode()+"</a>"
			+"</td>"			
			+"<td  >"
			+ bareme.getAnExercice()
			+"</td>"
			+"<td >"
			+ bareme.getLibelle()
			+"</td>"			
			+"<td  >"
			+"<a href=./entree?action=empl_gestion_imputation.jsp" +
					"&choix=modifier&numtypeTaxe="+ bareme.getIdImputation() +"" +
					"&anneeExercice="+bareme.getAnExercice()+
					">"			
			+ bareme.getTypeTaxe()
			+"</a>"
			+"</td>"
			+"</tr>";
			list.add(t);
		}			
		list.add("</table>");
		}
		else 
			list.add("<font size=\"4\">Il n'y  a pas de bareme  qui correspond avec ces criteres de recherche. </font>" );

		res = list.toString().replaceAll(",", "");
		res = res.substring(1, res.length()-1);		
		return res;
	}

	
	
	
	
	public String genererTableauListeDesDocuments(Vector contenu , String lienRepertoire, String numEmplacment )
	{
		String res="";
		ArrayList list = new ArrayList();
		if (contenu.size()!=0)
		{		
			for (int i = 0; i < contenu.size(); i++) 
			{
				String fileName = (String)contenu.elementAt(i);
				
				String lienDocuement = lienRepertoire + fileName;
				String lienDeleteFile ="javascript:SupprimerFichierDocument(\""+fileName+"\");";
				res = res + "<tr>" +
						"<td><a href='"+lienDocuement+"'>"+fileName+"</a></td>" +
						"<td><a href='"+lienDeleteFile+"'>supprimer</a></td>" +
						"</tr>";
			}			
		
		}
		else 
			res = res + "Il n'y  a pas de document";

		return res;
	}
	
	//paul 31/05/2018
	public  String genererListeEmplacementLibre(Vector<Emplacement> contenu)
	{
		String res="";
		ArrayList list = new ArrayList();
		Emplacement emplacement = null;

		if (contenu.size() !=0 && contenu.size() <300)
		{
			list.add("<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"); 
			list.add("<tr  align=\"center\" >");
			list.add("<td "+ couleur+ "><b><font size=\"4\">Transfert d'emplacement</font></b></td>");		
			list.add("<td "+ couleur+ "><b><font size=\"4\">Créé le</font></b></td>");
			list.add("<td "+ couleur+ "><b><font size=\"4\">Type emplacement</font></b></td>");
			list.add("<td "+ couleur+ "><b><font size=\"4\">Adresse emplacement</font></b></td>");
			list.add("</tr>");			
			
			for (int i = 0; i < contenu.size(); i++) 
			{
				emplacement = (Emplacement)contenu.elementAt(i);
				
				String link="./entree?action=batch_divers.jsp" +				
				"&numEmplacement=" + emplacement.getNumEmplacement();
				
				String linkEmplacment= "./entree?action=empl_gestion_emplacementodp.jsp" +
				"&choix=modifier&optionTransfert=role&origine=origine" +
				"&numEmplacment="+ emplacement.getNumEmplacement();
				
				String ligne ="";
				
				ligne="<tr name=\"ligne\" onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\" >";	
				ligne=ligne+"<td><a href= "+ link+" >"+emplacement.getNumEmplacement()+"</a></td>";
				ligne=ligne+"<td>"+emplacement.getDateCreation() +"</td>";
				String typeDeTaxeEmplacement= emplacement.getLibelleImputation();
				if ( typeDeTaxeEmplacement == null || typeDeTaxeEmplacement.equalsIgnoreCase("") )
					typeDeTaxeEmplacement="-";
					
				ligne=ligne+"<td>"+ typeDeTaxeEmplacement+"</td>";	
				
				if ( emplacement.getAdresse1() != null  && !emplacement.getAdresse1().equalsIgnoreCase("-"))
				{
					ligne=ligne+"<td><font color=\"#0000FF\"><a href="+ linkEmplacment + ">" +
							emplacement.getAdresse1()+"</a></font></td>";
				}
				else
				{
					ligne=ligne+"<td><font color=\"#0000FF\"> - </font></td>";
				}
				
				ligne=ligne+"</tr>";	
				list.add(ligne);
			}		
			list.add("</table>");
		}		
		else if (contenu.size() >300)
		{
			list.add("<table><tr><font size=\"4\">Il y a plus de 300 resultats " +
					", veuillez remplir un ou plusieurs champs de filtre de recherche pour obtenir les informations d�taill�es.  </font>" +
					" </tr></table>" );
		}		
		else if (contenu.size() ==0)
		{
			list.add("<table><tr>" +
					"	<font size=\"4\">Aucun resultat ne correspond avec les parametres indiquees  </font>" +
					"</tr></table>" );
		}
		
		res = list.toString().replaceAll(",", "");
		res = res.substring(1, res.length()-1);
		return res;
	}
	
	//paul 31/05/2018
	public  String genererListeEmplacementAModifier(Vector<Emplacement> contenu)
	{
		String res="";
		ArrayList list = new ArrayList();
		Emplacement emplacement = null;

		if (contenu.size() !=0 && contenu.size() <300)
		{
			list.add("<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"); 
			list.add("<tr  align=\"center\" >");
			list.add("<td "+ couleur+ "><b><font size=\"4\">Transfert d'emplacement</font></b></td>");
			list.add("<td "+ couleur+ "><b><font size=\"4\">Redevable</font></b></td>");
			list.add("<td "+ couleur+ "><b><font size=\"4\">Modifié le</font></b></td>");
			list.add("<td "+ couleur+ "><b><font size=\"4\">Type emplacement</font></b></td>");
			list.add("<td "+ couleur+ "><b><font size=\"4\">Adresse emplacement</font></b></td>");
			list.add("</tr>");			
			
			for (int i = 0; i < contenu.size(); i++) 
			{
				emplacement = (Emplacement)contenu.elementAt(i);
				
				String link="./entree?action=batch_divers.jsp" +				
				"&numEmplacement=" + emplacement.getNumEmplacement();
				
				String linkEmplacment= "./entree?action=empl_gestion_emplacementodp.jsp" +
				"&choix=modifier&optionTransfert=role&origine=origine" +
				"&numEmplacment="+ emplacement.getNumEmplacement();
				
				String ligne ="";
				
				ligne="<tr name=\"ligne\" onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\" >";	
				ligne=ligne+"<td><a href= "+ link+" >"+emplacement.getNumEmplacement()+"</a></td>";
				ligne=ligne+"<td>"+emplacement.getNomRedevable() +"</td>";
				ligne=ligne+"<td>"+emplacement.getDateCreation() +"</td>";
				String typeDeTaxeEmplacement= emplacement.getLibelleImputation();
				if ( typeDeTaxeEmplacement == null || typeDeTaxeEmplacement.equalsIgnoreCase("") )
					typeDeTaxeEmplacement="-";
					
				ligne=ligne+"<td>"+ typeDeTaxeEmplacement+"</td>";	
				
				if ( emplacement.getAdresse1() != null  && !emplacement.getAdresse1().equalsIgnoreCase("-"))
				{
					ligne=ligne+"<td><font color=\"#0000FF\"><a href="+ linkEmplacment + ">" +
							emplacement.getAdresse1()+"</a></font></td>";
				}
				else
				{
					ligne=ligne+"<td><font color=\"#0000FF\"> - </font></td>";
				}
				
				ligne=ligne+"</tr>";	
				list.add(ligne);				
			}		
			
			list.add("</table>");
		}		
		else if (contenu.size() >300)
		{
			list.add("<table><tr><font size=\"4\">Il y a plus de 300 resultats " +
					", veuillez remplir un ou plusieurs champs de filtre de recherche pour obtenir les informations d�taill�es.  </font>" +
					" </tr></table>" );
		}		
		else if (contenu.size() ==0)
		{
			list.add("<table><tr>" +
					"	<font size=\"4\">Aucun resultat ne correspond avec les parametres indiquees  </font>" +
					"</tr></table>" );
		}
		
		res = list.toString().replaceAll(",", "");
		res = res.substring(1, res.length()-1);
		return res;
	}
		
	public  String genererListeRedevable(Vector<Redevable> contenu ,String etatEmplacement)
	{
		String res="";
		ArrayList list = new ArrayList();
		Redevable redevable = null ;
		Emplacement emplacement = null;
		
		if (contenu.size() !=0 && contenu.size() <300)
		{
			list.add("<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"); 
			list.add("<tr  align=\"center\" >");
			list.add("<td "+ couleur+ "><b><font size=\"4\">N redevable</font></b></td>");		
			list.add("<td "+ couleur+ "><b><font size=\"4\">Nom redevable</font></b></td>");
			list.add("<td "+ couleur+ "><b><font size=\"4\">Adresse redevable</font></b></td>");
			list.add("<td "+ couleur+ "><b><font size=\"4\">Type emplacement</font></b></td>");
			list.add("<td "+ couleur+ "><b><font size=\"4\">Adresse emplacement</font></b></td>");
			list.add("</tr>");			
			
			for (int i = 0; i < contenu.size(); i++) 
			{
				redevable = (Redevable)contenu.elementAt(i);
				String link="./entree?action=empl_gestion_redevable.jsp" +
				"&choix=modifier&boton=ajouter&typeRecherche=role" +				
				"&numRedevable=" + redevable.getNumRedevable()+
				"&etatEmplacement=" + etatEmplacement;
				String linkEmplacment= "./entree?action=empl_gestion_emplacementodp.jsp" +
				"&choix=modifier&optionTransfert=role&origine=origine" +
				"&numEmplacment="+ redevable.getNumeroEmplacment();
				
				String ligne ="";
				ligne="<tr name=\"ligne\" onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\" >";	
				ligne=ligne+"<td><a href= "+ link+" >"+ redevable.getNumRedevable()+"</a></td>";
				ligne=ligne+"<td>"+ redevable.getNomRedevable()+ " "+ redevable.getPrenomRedevable()+"</td>";
				ligne=ligne+"<td>"+ redevable.getAdressRedevable()+"</td>";
				
				emplacement = requestEmplacement.getEmplacement(redevable.getNumeroEmplacment());
				String typeDeTaxeEmplacement= emplacement.getLibelleImputation();
				if ( typeDeTaxeEmplacement == null || typeDeTaxeEmplacement.equalsIgnoreCase("") )
					typeDeTaxeEmplacement="-";
					
				ligne=ligne+"<td>"+ typeDeTaxeEmplacement+"</td>";	
				
				if ( redevable.getAdressEmplacment() != null  && !redevable.getAdressEmplacment().equalsIgnoreCase("-"))
				{
					ligne=ligne+"<td><font color=\"#0000FF\"><a href="+ linkEmplacment + ">" +
					redevable.getAdressEmplacment()+"</a></font></td>";
				}
				else
				{
					ligne=ligne+"<td><font color=\"#0000FF\"> - </font></td>";
				}
				
				ligne=ligne+"</tr>";	
				list.add(ligne);				
			}		
			
			res=res+"</table>";	
		}		
		else if (contenu.size() >300)
		{
			list.add("<table><tr><font size=\"4\">Il y a plus de 300 resultats " +
					", veuillez remplir un ou plusieurs champs de filtre de recherche pour obtenir les informations d�taill�es.  </font>" +
					" </tr></table>" );
		}		
		else if (contenu.size() ==0)
		{
			list.add("<table><tr>" +
					"	<font size=\"4\">Aucun resultat ne correspond avec les parametres indiquees  </font>" +
					"</tr></table>" );
		}
		
		res = list.toString().replaceAll(",", "");
		res = res.substring(1, res.length()-1);
		//System.out.println("TTTTTTTTTTTTTTTTTT"+res+"xxx");
		return res;
	}
	

	
	public String genererListeRemboursementRedevable(Vector contenu )
	{
		String res="";			
		////System.out.println("Nombre de facture="+contenu.size());
		if (contenu.size()>0)
		{
			res=res+"<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"; 
			res=res+"<tr align=\"center\" bgcolor=\"#FFFFFF\">";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Remboursement</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Type de taxe</font></b></td>";
			res=res+"<td "+ couleur+ "><b><font size=\"4\">Montant total</font></b></td>";									
			res=res+"</tr>";
			for (int i = 0; i < contenu.size(); i++) 
			{
				////System.out.println("FACTURE numero"+i);
				Remboursement remboursement = (Remboursement)contenu.elementAt(i);
				String montantTotal = remboursement.getMontantTotal();
				String typeTaxe = remboursement.getTypeTaxe();
				String idBatch = remboursement.getIdBatchTraitement();
				String idRemboursement = remboursement.getIdRemboursement();
				String annee = remboursement.getAnneeEx();
				String numRemboursement = remboursement.getIdRemboursement();
				String repertoirePdf= annee +"-"+idBatch;
				String nomFichier= idRemboursement;
				res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\">";	
				
				res=res+"<td >" +
						"<img src=\"images/pdfbleue.png\" border=\"0\" height=\"21\" width=\"20\">&nbsp"+
						"<a target=\"_blank\" href="+ fichierDeConfiguration.getLienRemboursement()
						+repertoirePdf+"/Remb_"+nomFichier+".pdf >" +
						"Remboursement No"+numRemboursement+".pdf" +"</a></td>";
				
				res=res+"<td>"+ typeTaxe+"</td>";
				res=res+"<td>"+ montantTotal +"</td>";							
				res=res+"</tr>";		
			}	    
			res=res+"</table>";	
		}
		else
		{			
			res=res+"Aucun remboursement n'est disponible ";				
		}
			
		return res;
	}
	
	public  String genererListeAutorisation(Vector<Autorisation> contenu)
	{
		String res="";
		ArrayList list = new ArrayList();
		Autorisation autorisation = null ;
		Emplacement emplacement = null;
		RequestRedevable reqRedevable= new RequestRedevable();
		Redevable redevable=null;
		
		if (contenu.size() !=0 && contenu.size() <300)
		{
			list.add("<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"); 
			list.add("<tr  align=\"center\" >");
			list.add("<td "+ couleur+ "><b><font size=\"4\">N° autorisation</font></b></td>");		
			list.add("<td "+ couleur+ "><b><font size=\"4\">Date de création</font></b></td>");
			list.add("<td "+ couleur+ "><b><font size=\"4\">Nom redevable</font></b></td>");
			list.add("<td "+ couleur+ "><b><font size=\"4\">Adresse emplacement</font></b></td>");
			list.add("<td "+ couleur+ "><b><font size=\"4\">Nbre emplacements</font></b></td>");
			list.add("<td "+ couleur+ "><b><font size=\"4\">Etat</font></b></td>");
			list.add("</tr>");			
			
			for (int i = 0; i < contenu.size(); i++) 
			{
				autorisation = (Autorisation) contenu.elementAt(i);
				String linkAutorisation="./entree?action=empl_gestion_autorisation_modifier.jsp&choix=modifier" +
				"&idautorisation="+ autorisation.getIdAutorisation();

				System.out.println("genererListeAutorisation > "+autorisation.getIdAutorisation());
				String ligne ="";
				ligne="<tr name=\"ligne\" onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\" >";	
				ligne=ligne+"<td><a href="+linkAutorisation+" >"+ autorisation.getIdAutorisation()+"</a></td>";
				ligne=ligne+"<td>"+ autorisation.getDateCreation()+"</td>";
				
				redevable=reqRedevable.getRedevable(autorisation.getNumRedevable());
				String lienRedvable="./entree?action=empl_gestion_redevable.jsp"+
						"&numRedevable="+ autorisation.getNumRedevable() + "&choix=modifier";
				ligne=ligne+"<td> <a href= "+ lienRedvable +">"+redevable.getNomRedevable()+ " "+ redevable.getPrenomRedevable()+"</a></td>";
			
				ligne=ligne+"<td>"+ autorisation.getAdresseEmplacement()+"</td>";
				
				ligne=ligne+"<td>"+ autorisation.getNombreEmplacement()+"</td>";
				
				ligne=ligne+"<td>"+ autorisation.getEtat()+"</td>";
				
				ligne=ligne+"</tr>";
				
				list.add(ligne);				
			}		
			
			res=res+"</table>";	
		}		
		else if (contenu.size() >300)
		{
			list.add("<table><tr><font size=\"4\">Il y a plus de 300 resultats " +
					", veuillez remplir un ou plusieurs champs de filtre de recherche pour obtenir les informations d�taill�es.  </font>" +
					" </tr></table>" );
		}		
		else if (contenu.size() ==0)
		{
			list.add("<table><tr>" +
					"	<font size=\"4\">Aucun resultat ne correspond avec les parametres indiquees  </font>" +
					"</tr></table>" );
		}
		
		res = list.toString().replaceAll(",", "");
		res = res.substring(1, res.length()-1);

		return res;
	}
	
	public static String genererTableaulisteGroupeTaxe(Vector input)
	{
		String res="";
		try 
		{
			if(input.size() >0)
			{
				res="<table cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width=\"100%\" "  +
				" borderColorLight=\"#c0c0c0\" border=\"1\" align=\"center\" height=\"20\">";
				res= res+"<td bgcolor=\"#FFFFFF\" width=\"25%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Identifiant"
				+ "</font></b></td>";
				res= res+"<td bgcolor=\"#FFFFFF\" width=\"25%\" align=\"center\"><b><font color=\"#800000\" size=\"4\">" 
				+ "Groupe de Taxe"
				+ "</font></b></td>";
				
				////System.out.println("TAILLE="+input.size());
				for (int i = 0; i < input.size(); i++) 
				{								
					res=res+"<tr onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" >";
					GroupeTaxe groupe = ((GroupeTaxe)input.elementAt(i));
					String idGroupe = groupe.getIdGroupe();
					String libelle = groupe.getLibelle();			
					String link ="./entree?action=empl_gestion_groupeTaxe.jsp" +
							"&choix=modifier&idGroupe="+idGroupe;
					res= res+"<td align=\"center\"><a href="+ link +">"+ idGroupe + "</td>";
					res= res+"<td align=\"center\">"+ libelle + "</td>";									
					res=res+"</tr>";			
				}			
				res=res+"</table>";
			}
			else
			{
				res ="Aucun groupe de taxe ne correspond a vos critères de recherche";
			}
			////System.out.println(res);
		}
		catch (Exception e) 
		{
			DebuggerLog4J.logger.fatal(e.getMessage());
		}
		return res;
	}
	//Paul 19/12/2020 pour module export
	public static String genererListeDeroulanteParametres(String nomLD,
			String selection, Vector donnee , boolean vide) 
	{
		String listeDeroulante;			
		listeDeroulante =	"<select name=\"" + nomLD + "\" onchange=changementEtatOuvrage(); >\n";		
		int longueur = (donnee.size());
		if (vide)
			listeDeroulante = listeDeroulante
					+ "<option value=\"\" ></option>\n";
		for (int i = 0; i < longueur; i++) 
		{
			Parametres parametres = (Parametres)donnee.elementAt(i);
			listeDeroulante = listeDeroulante + "\n <option ";
			if (selection.equalsIgnoreCase(parametres.getValeur()))
				listeDeroulante = listeDeroulante + "selected ";
			listeDeroulante = listeDeroulante + "value=\"" + parametres.getValeur()
					+ "\" >" +parametres.getLibelle() + "</option>\n";
		}
		listeDeroulante = listeDeroulante + "</select>\n";
		return listeDeroulante;
	}

}




