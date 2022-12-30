package fr.analogon.r2t.util.web;

import javax.servlet.http.HttpServletRequest;

import com.borland.dx.dataset.DataRow;
import com.borland.dx.dataset.TableDataSet;
import com.borland.dx.dataset.Variant;
import com.borland.dx.sql.dataset.QueryDataSet;



/**
 * cette classe contient des methodes statiques permettant d'utiliser des
 * servlets. Sofien CHARFI
 * 
 * @version 2.2
 * @since 1.0
 */

public class StaticManipServlet {



	/**
	 * @deprecated depuis 2.2 Cette methode permet de faire un servletPost
	 *             simple qui insere seulement une ligne dans un QueryDataSet en
	 *             executant la methode servletPost du ixPageProduceur, cette
	 *             fonction sauve aussi les changements dans la base.
	 * @param servlet
	 *            la servlet qui doit executer le servlet post
	 * @param request
	 *            la requete de la servlet
	 * @param response
	 *            la response de la servlet
	 * @param dqs
	 *            le QueryDataset a utiliser
	 * @param ixPageProducer
	 *            sur lequel sera executer la methode servletPost(). . David
	 *            Gimelle
	 * @since 1.0
	 */
	/*
	 * public static void singleServletPost(HttpServlet servlet,
	 * HttpServletRequest request,HttpServletResponse response, QueryDataSet
	 * qds, IxPageProducer ixPageProducer) throws java.io.IOException,
	 * javax.servlet.ServletException { qds.refresh(); qds.insertRow(false);
	 * ixPageProducer.servletPost(servlet,request,response); qds.post();
	 * qds.saveChanges(); }
	 */

	/**
	 * @deprecated Remplit dynamiquement un TableDataSet a partir des elements
	 *             contenus dans la colonne d'un queryDataSet. Par ailleurs, les
	 *             valeurs inscrites dans le TableDataSet sont modifiées afin
	 *             d'obtenir des liens. Ce TableDataSet est par la suite utilisé
	 *             dans la confection de Pop-Ups en affichant ses valeurs via un
	 *             ixTable. Les liens inscrits dans le TableDatSet sont de la
	 *             forme suivante : lien = "<a href=" + "\"#\"" + "
	 *             onClick=\"Javascript:window.opener.top." + frame + "." +
	 *             formulaire + "." + champs + "." + "value='" + var.toString() +
	 *             "'; window.close(); \"> " + var.toString() + " </a>";
	 * @param tds
	 *            le TableDataset que l'on remplit
	 * @param qds
	 *            l'ensemble de données qui nous sert de modele
	 * @param colonne
	 *            la colonne du qds a copier
	 * @param frame
	 *            le nom de la frame que l'on inscrit dans le lien
	 * @param form
	 *            le nom du formulaire que l'on inscrit dans le lien
	 * @param champs
	 *            le nom du champs que l'on inscrit dans le lien . Sofien CHARFI
	 * @since 1.0
	 */

	/*
	 * public static void superPopUp(TableDataSet tds, String colonneTds,
	 * QueryDataSet qds, String colonne, String frame, String formulaire, String
	 * champs) { DataRow dataTds; int ligne; int nbLignes = qds.getRowCount();
	 * Variant var = new Variant(); String chaine;
	 * 
	 * try { dataTds = new DataRow(tds, colonneTds);
	 * 
	 * for (ligne = 0; ligne < nbLignes; ligne++) { qds.goToRow(ligne); //on met
	 * dans le variant "var" la valeur qu'il y a dans la colonne "colonne" de
	 * l'ens de donnees qds. qds.getVariant(colonne, var);
	 * 
	 * //Si le texte a afficher est different de la chaine vide... if
	 * (!var.toString().equals("")) { //on cree le lien . on protege les ' dans
	 * le lien. chaine = "<a href=" + "\"#\"" + "
	 * onClick=\"Javascript:window.opener.top." + frame + "." + formulaire + "." +
	 * champs + "." + "value='" +
	 * ChainesDeCharacteres.protege(var.toString(),"'","\\","") + "';
	 * window.close(); \"> " + var.toString() + " </a>";
	 * 
	 * //on met dans me dataRow dataTds la valeur du variant "var".
	 * dataTds.setString(colonneTds, chaine);
	 * 
	 * //on ajoute la ligne dans tds. tds.insertRow(false); tds.goToRow(ligne);
	 * tds.addRow(dataTds); } } } catch(Exception e) { //System.out.println("pb au
	 * niveau de pop-up, verifier le nom de colonne du Tds en parametre"); }
	 *  }
	 */
	
	public static void superPopUp1(TableDataSet tds, String colonneTds,
			QueryDataSet qds, String colonne, String formulaire, String champs) {
		DataRow dataTds;
		int ligne;
		int nbLignes = qds.getRowCount();
		Variant var = new Variant();
		String chaine;

		try {
			dataTds = new DataRow(tds, colonneTds);

			for (ligne = 0; ligne < nbLignes; ligne++) {
				qds.goToRow(ligne);
				// on met dans le variant "var" la valeur qu'il y a dans la
				// colonne "colonne" de l'ens de donnees qds.
				qds.getVariant(colonne, var);

				// Si le texte a afficher est different de la chaine vide...
				
					// on met dans me dataRow dataTds la valeur du variant
					// "var".
					dataTds.setString(colonneTds, "");

					// on ajoute la ligne dans tds.
					tds.insertRow(false);
					tds.goToRow(ligne);
					tds.addRow(dataTds);
				}
			
		} catch (Exception e) {
			System.out
					.println("pb au niveau de pop-up, verifier le nom de colonne du Tds en parametre");
		}

	}

	/**
	 * voir l'autre superPopUp.
	 * 
	 * @deprecated . Sofien CHARFI
	 * @since 1.0
	 */
	/*
	 * public static void superPopUp(TableDataSet tds, QueryDataSet qds, String
	 * colonne, String frame, String formulaire, String champs) {
	 * superPopUp(tds, "colonne", qds, colonne, frame, formulaire, champs); }
	 */
	
	public static void superPopUp2(TableDataSet tds, QueryDataSet qds,
			String colonne, String formulaire, String champs) {
		superPopUp1(tds, "colonne", qds, colonne, formulaire, champs);
	}

	/**
	 * Comme les autres popup, mais le commentaire du lien correspond a toutes
	 * les colonnes du qds et seulement un champs est remplacé.
	 * 
	 * @param colonne
	 *            la colonne du qds
	 * @param champs
	 *            le champs du html . Sofien CHARFI
	 * @since 1.0
	 */
	public static void popUp(TableDataSet tds, String nomColTds,
			QueryDataSet qds, String frame, String formulaire, String champs,
			String colonne) {
		popUp(tds, nomColTds, qds, frame, formulaire, new String[][] { {
				colonne, champs } }, new String[] { "" }, false);
	}

	/**
	 * Comme les autres popUp mais le commentaire du lien correspond a toutes
	 * les colonnes du qds. . Sofien CHARFI
	 * 
	 * @since 1.0
	 */
	public static void popUp(TableDataSet tds, String nomColTds,
			QueryDataSet qds, String frame, String formulaire,
			String[][] champsEtColonnes) {
		popUp(tds, nomColTds, qds, frame, formulaire, champsEtColonnes,
				new String[] { "" }, false);
	}

	/**
	 * Cette fonction remplie un tds avec une liste de liens qui permet de
	 * remplir plusieurs champs de formulaire en fonction du contenue d'un qds.
	 * le texte du liens correspond aux noms de colonnes de colonnesCommentaire.
	 * La correspondance champs et colonne est données par le tableau
	 * champsEtColonne, exemple: String[][] tab = {{nomColonne,nomChampsHTML},
	 * {"prenom","leprenom"}, {"qualite","laqualite"}}; l'affichage des liens se
	 * fait suivant l'ordre du querydataset.
	 * 
	 * @param tds
	 *            le tds qui sert a l'affichage
	 * @param nomColTds
	 *            le nom de la colonne du tds pour l'affichage dans la liste
	 * @param qds
	 *            le qds utiliser pour remplir le tds
	 * @param frame
	 *            le nom de la frame a raffraichir, en general ca commence par
	 *            top ex:top.nomframe qui designe la frame nomframe depuis la
	 *            fenetre la plus haute (top en javascript).
	 * @param formulaire
	 *            le nom du formulaire a remplir
	 * @param champsEtColonnes
	 *            tableau contenant la liste des colonnes du qds et des champs
	 *            html a remplir correspondant.
	 * @param colonnesCommentaires
	 *            la liste des colonnes du qds a utiliser a l'affichage, ces
	 *            colonnes s'affichent dans l'ordre de la liste.
	 * @param avecColonnesCommentaires
	 *            si true alors seul les champs precisées dans
	 *            colonnesCommentaires sont utilisés é l'affichage . David
	 *            Gimelle
	 * @since 1.0
	 */
	public static void popUp(TableDataSet tds, String nomColTds,
			QueryDataSet qds, String frame, String formulaire,
			String[][] champsEtColonnes, String[] colonnesCommentaires,
			boolean avecColonnesCommentaire) {
		// Le tds a afficher
		DataRow dataTds;
		// le numero de ligne dans le qds
		int ligneQds;
		// le nb de ligne dans le qds
		int nbLignesQds = qds.getRowCount();
		// le numero de colonne dans le qds
		int colQds;
		// le nombre de colonnes dans le qds
		int nbColumnQds = qds.columnCount();
		// la valeur du parametre dans le fichier HTML obtenu depuis le qds
		Variant valeurParam = new Variant();
		// Valeur d'une colonne du qds pour l'affichage dans lien
		Variant valeurCommentaire = new Variant();
		// la chaine de caractere de commentaire
		String commentaire;
		// La valeur de la chaine de caractere qui sera dans une ligne du tds
		String chaine;
		// le nom de champs a modifier dans la formulaire
		int nbChamps = champsEtColonnes.length;
		//System.out.println("nbChamps " + nbChamps);
		// la chaine d'instruction javascript de remplissage de d'instruction de
		// modification de champs
		String javaScript;
		// la longueur du tableau de commentaire
		int nbColCommentaire = colonnesCommentaires.length;

		dataTds = new DataRow(tds, nomColTds);

		// boucle de rempissage du qds
		for (ligneQds = 0; ligneQds < nbLignesQds; ligneQds++) {
			qds.goToRow(ligneQds);
			// on ajoute la ligne dans tds.
			tds.insertRow(false);
			tds.goToRow(ligneQds);
			// on initialise la chaine javascript a zero
			javaScript = new String("");
			// boucle de remplissage de la chaine javascript
			for (int i = 0; i < nbChamps; i++) {
				// //System.out.println("i "+i);
				// on met dans le variant "valeurParam" la valeur qu'il y a dans
				// la colonne "colonne" de l'ens de donnees qds.
				String column = champsEtColonnes[i][0];
				String champs = champsEtColonnes[i][1];

				qds.getVariant(column, valeurParam);
				// la valeur du champs dans le html
				String value = valeurParam.toString();
				

			}
			// on initialise le commentaire a zero
			commentaire = new String("");
			// boucle de remplissage du commentaire d'affichage
			if (!avecColonnesCommentaire) {
				for (colQds = 0; colQds < nbColumnQds; colQds++) {
					qds.getVariant(colQds, valeurCommentaire);
					commentaire = new String(commentaire + " "
							+ valeurCommentaire.toString());
				}
			} else {
				for (int colTabCom = 0; colTabCom < nbColCommentaire; colTabCom++) {
					qds.getVariant(colonnesCommentaires[colTabCom],
							valeurCommentaire);
					commentaire = new String(commentaire + " "
							+ valeurCommentaire.toString());
				}
			}

			// on modifie le variant pour en faire un lien .
			chaine = "<a href=" + "\"#\"" + " onClick=\"" + javaScript
					+ " window.close(); \"> " + commentaire + " </a>";

			// //System.out.println("chaine :" + chaine);

			// on met dans me dataRow dataTds la chaine du lien
			dataTds.setString(nomColTds, chaine);
			tds.addRow(dataTds);
			// }
		}

	}

	/**
	 * Cette fonction remplie un tds qui permet de remplir plusieurs champs de
	 * formulaire en fonction du contenue d'un qds. La correspondance champs et
	 * colonne est données par le tableau champsEtColonne, exemple: String[][]
	 * tab = {{nomColonne,nomChampsHTML}, {"prenom","leprenom"},
	 * {"qualite","laqualite"}};
	 * 
	 * @param tds
	 *            le tds qui sert a l'affichage
	 * @param nomColTds
	 *            le nom de la colonne du tds pour l'affichage dans la liste
	 * @param qds
	 *            le qds utiliser pour remplir le tds
	 * @param frame
	 *            le nom de la frame a raffraichir, en general ca commence par
	 *            top ex:top.nomframe qui designe la frame nomframe depuis la
	 *            fenetre la plus haute (top en javascript).
	 * @param formulaire
	 *            le nom du formulaire a remplir
	 * @param champsEtColonnes
	 *            tableau contenant la liste des colonnes du qds et des champs
	 *            html a remplir correspondant.
	 * @param colonnesCommentaires
	 *            la liste des colonnes du qds a utiliser a l'affichage, ces
	 *            colonnes s'affichent dans l'ordre de la liste. . Sofien CHARFI
	 * @since 1.0
	 */
	public static void popUpMax(TableDataSet tds, String nomColTds,
			QueryDataSet qds, String frame, String formulaire,
			String[][] champsEtColonnes, String[] colonnesCommentaires,
			boolean avecColonnesCommentaire) {
		// Le tds a afficher
		DataRow dataTds;
		// le numero de ligne dans le qds
		int ligneQds;
		// le nb de ligne dans le qds
		int nbLignesQds = qds.getRowCount();
		// le numero de colonne dans le qds
		int colQds;
		// le nombre de colonnes dans le qds
		int nbColumnQds = qds.columnCount();
		// la valeur du parametre dans le fichier HTML obtenu depuis le qds
		Variant valeurParam = new Variant();
		// Valeur d'une colonne du qds pour l'affichage dans lien
		Variant valeurCommentaire = new Variant();
		// la chaine de caractere de commentaire
		String commentaire;
		// La valeur de la chaine de caractere qui sera dans une ligne du tds
		String chaine;
		// le nom de champs a modifier dans la formulaire
		int nbChamps = champsEtColonnes.length;
		//System.out.println("nbChamps " + nbChamps);
		// la chaine d'instruction javascript de remplissage de d'instruction de
		// modification de champs
		String javaScript;
		// la longueur du tableau de commentaire
		int nbColCommentaire = colonnesCommentaires.length;

		dataTds = new DataRow(tds, nomColTds);

		// boucle de rempissage du qds
		for (ligneQds = 0; ligneQds < nbLignesQds; ligneQds++) {
			qds.goToRow(ligneQds);
			// on ajoute la ligne dans tds.
			tds.insertRow(false);
			tds.goToRow(ligneQds);
			// on initialise la chaine javascript a zero
			javaScript = new String("");
			// boucle de remplissage de la chaine javascript
			for (int i = 0; i < nbChamps; i++) {
				// //System.out.println("i "+i);
				// on met dans le variant "valeurParam" la valeur qu'il y a dans
				// la colonne "colonne" de l'ens de donnees qds.
				String column = champsEtColonnes[i][0];
				String champs = champsEtColonnes[i][1];

				qds.getVariant(column, valeurParam);
				// la valeur du champs dans le html
				String value = valeurParam.toString();
				

			}
			// on initialise le commentaire a zero
			commentaire = new String("");
			// boucle de remplissage du commentaire d'affichage
			if (!avecColonnesCommentaire) {
				for (colQds = 0; colQds < nbColumnQds; colQds++) {
					qds.getVariant(colQds, valeurCommentaire);
					commentaire = new String(commentaire + " "
							+ valeurCommentaire.toString());
				}
			} else {
				for (int colTabCom = 0; colTabCom < nbColCommentaire; colTabCom++) {
					qds.getVariant(colonnesCommentaires[colTabCom],
							valeurCommentaire);
					commentaire = new String(commentaire + " "
							+ valeurCommentaire.toString());
				}
			}

			// on modifie le variant pour en faire un lien .
			chaine = "<script>" + javaScript + " window.close();</script> ";

			// //System.out.println("chaine :" + chaine);

			// on met dans me dataRow dataTds la chaine du lien
			dataTds.setString(nomColTds, chaine);
			tds.addRow(dataTds);
			// }
		}

	}

	/*
	 * Cette methode permet de recuperer un message stocker dans une variable de
	 * session qui est un objet String. Retourne un message. @param req la
	 * requette http @param nomVarSess le nom de l'objet String en session @parm
	 * msgParDefaut le message par defaut a retourner si cette variable n'existe
	 * pas . Michal Vincent
	 * 
	 * @since 2.1
	 * @deprecated depuis 2.1 utiliser Message ou Message Erreur é la place.
	 */

	public static String afficheMessageSessionJSP(HttpServletRequest req,
			String nomVarSess, String msgParDefaut) {
		if (req.getSession(true).getAttribute(nomVarSess) == null) {
			return msgParDefaut;
		} else {
			String msgTemp = req.getSession(true).getAttribute(nomVarSess)
					.toString();
			return msgTemp;
		}
	}
	
	/**Permet de recupere un parametre html et de lui mettre une valeur par defaut si null ou non defini*/
	public static String getParamHtmlStr(String nom,String valueSiNullouChaineVide,HttpServletRequest request){
		String res=request.getParameter(nom);
		if ((res==null)||(res.equalsIgnoreCase(""))){
			res=valueSiNullouChaineVide;
		}
		return res;
	}
	
	/**Permet de recupere un parametre html et de lui mettre une valeur par defaut si null ou non defini*/
	public static int getParamHtmlInt(String nom,int valueSiNullouChaineVide,HttpServletRequest request){
		int res=-1;
		String resStr=request.getParameter(nom);
		if ((resStr==null)||(resStr.equalsIgnoreCase(""))){
			res=valueSiNullouChaineVide;
		}
		else{
			res=Integer.parseInt(resStr);
		}
		return res;
	}
	
	/**Permet de recupere un parametre html et de lui mettre une valeur par defaut si null ou non defini*/
	public static Long getParamHtmlLong(String nom,Long valueSiNullouChaineVide,HttpServletRequest request){
		Long res=null;
		String resStr=request.getParameter(nom);
		if ((resStr==null)||(resStr.equalsIgnoreCase(""))){
			res=valueSiNullouChaineVide;
		}
		else{
			res=new Long(resStr);
		}
		return res;
	}
	
	/**Permet de recupere un parametre html et de lui mettre une valeur par defaut si null ou non defini*/
	public static double getParamHtmlDouble(String nom,double valueSiNullouChaineVide,HttpServletRequest request){
		double res=-1;
		String resStr=request.getParameter(nom);
		if ((resStr==null)||(resStr.equalsIgnoreCase(""))){
			res=valueSiNullouChaineVide;
		}
		else{
			res=(new Double(resStr)).doubleValue();
		}
		return res;
	}
	
	

	
	
	

}
