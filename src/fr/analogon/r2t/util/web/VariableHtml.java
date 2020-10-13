package fr.analogon.r2t.util.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
/**
 * Description : Classe qui permet de mettre dans un tableau les variables html
 * é partir d'un HttpServletRequest . : Frédéric Guillaume
 * 
 * @version 1.0
 */

public class VariableHtml {
	/**
	 * nom de la variable html permettant de savoir si le bean va utiliser un
	 * objet de session variable html pour se charger
	 */
	public static String NOMVARHTMLVARIABLEHTML = "chargeVariablesHtml";

	/**
	 * nom de la variable de session de l'instance de VaraibleHtml qui peut etre
	 * utilise par ce bean pour se charger
	 */
	public static String NOMVARSESSIONVARIABLEHTML = "variableHtml";

	private String tableau[][] = new String[100][2];

	private int nombreElement = 0;

	public VariableHtml() {
	}

	/**
	 * Constructeur qui remplit le tableau de variables html par rapport au
	 * request . Frédéric Guillaume
	 * 
	 * @since 1.0
	 */
	public VariableHtml(HttpServletRequest request)
	{
		Enumeration enum1;
		//Enumeration enum;
		enum1 = request.getParameterNames();

		while (enum1.hasMoreElements()) {
			nombreElement++;
			tableau[nombreElement][0] = new String(enum1.nextElement() + "");
			tableau[nombreElement][1] = request
					.getParameter(tableau[nombreElement][0]);
		}

	}

	/**
	 * Méthode permettant la recherche de la valeur du paramétre nom dans le
	 * tableau . Frédéric Guillaume
	 * 
	 * @since 1.0
	 */
	private String getValeur(String nom) {
		String retour = null;

		for (int i = 1; i <= nombreElement; i++) {
			if (nom.equalsIgnoreCase(tableau[i][0])) {
				retour = tableau[i][1];
			}
		}
		return retour;
	}

	/**
	 * Méthode permettant d'ajouter un paramétre dans le tableau . Frédéric
	 * Guillaume
	 * 
	 * @since 1.0
	 */
	public void setValeur(String nom, String Valeur) {
		for (int i = 1; i <= nombreElement; i++) {
			if (nom.equalsIgnoreCase(tableau[i][0])) {
				tableau[i][1] = Valeur;
				return;
			}
		}
		nombreElement++;
		tableau[nombreElement][0] = new String(nom);
		tableau[nombreElement][1] = new String(Valeur);
	}

	/**
	 * Méthode qui remplie les valeurs de tableauBean par rapport au tableau .
	 * Frédéric Guillaume
	 * 
	 * @since 1.0
	 */
	public void copieVariablesDansTableau(String tableauBean[][],
			int numeroLigneNom, int numeroLigneValeur) {
		// //System.out.println("tableau.length = " + tableauBean[0].length);
		for (int i = 0; i < tableauBean[0].length; i++) {
			if (getValeur(tableauBean[numeroLigneNom][i]) != null) {
				tableauBean[numeroLigneValeur][i] = getValeur(tableauBean[numeroLigneNom][i]);
			}
		}
	}

	public void trace() {
		for (int i = 1; i <= nombreElement; i++) {
			//System.out.println(tableau[i][0] + " = " + tableau[i][1]);
		}
	}

}
