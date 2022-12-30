package fr.analogon.r2t.main;


import javax.servlet.http.HttpServletRequest;

import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.util.web.RacineBeanWeb;

/**
 * Bean etendue par tout les beans de l'application . Sofien CHARFI
 * 
 * @version 1.1
 * @since 1.0
 */
public class RacineBean extends RacineBeanWeb 
{

	
	
	 public FichierDeConfiguration fichierDeConfiguration 
	 		= InitialisationConnexionLectureConfiguration.fichierDeConfiguration;

	/**
	 * Un constructeur qui instancie l'attribut conf avec une instance de
	 * fr.analogon.r2t.Config. Rmq tous les beans fils ce celui-ci, et qui
	 * redefinisse ce constructeur, vous executer ce constructeur et donc mettre
	 * a jour l'instance de conf. . Sofien CHARFI
	 * 
	 * @since 1.1
	 */
	public RacineBean() {
		//super(new Config());
		////System.out.println("RacineBean()");
	}

	/**
	 * Retourne l'url d'appel d'une JSP en utilisant la servlet entree.
	 * 
	 * @param laJSP
	 *            le nom de la JSP visée avec le .jsp
	 * @param chaineInterro
	 *            la chaine d'interrogation ajouter sans le premier & ni aucun ?
	 *            ni aucun parametre action.
	 */
	public String actionJsp(String laJSP, String chaineInterro) {
		String res;
		res = "" + conf.getPreUrlJbin() + ConfVariable.pageServletEntree + "?"
				+ ConfVariable.varParametreAction + "=" + laJSP + "&"
				+ chaineInterro;
		return res;
	}
	public void setRequest(HttpServletRequest req)
	{
		//System.out.println(
	}

}