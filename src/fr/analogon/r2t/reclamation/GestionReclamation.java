package fr.analogon.r2t.reclamation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.request.RequestReclamation;


/**
 * Permet de gerer les reclamamtions .
 * Gestion des reclamations. . Sofien CHARFI
 * 
 * @since 1.0
 * @version 1.3
 */
public class GestionReclamation extends fr.analogon.r2t.main.RacineServlet 
{	
	public String dateReclamation="";
	
	public String listeOuvrage="";
	
	public String idRedevable="";
	
	public String idFactue="";
	
	public String typeTaxe="";
	
		
	public String natureReclamation="";
	
	public String controleEffectue="";
	
	public String  texteReclamation="";	
	
	public String pieceJointe="";
	
	public String etatReclamation="";	

	public String texteReponse=" ";
	
	public String choix="";
	
	public String idReclamation=" ";
	
	public String typeReceptionReclamation="";	
	
	public String dateReponse="";
	
	private String dateContole="";
	
	private String numControleur="";	
	
	private String nomRedevable="";
	
	private HttpServletRequest request;
	

	
	

	/**
	 * @return the numControleur
	 */
	public final String getNumControleur() {
		return numControleur;
	}

	/**
	 * @param numControleur the numControleur to set
	 */
	public final void setNumControleur(String numControleur) {
		this.numControleur = numControleur;
	}

	
	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)
			
	{
		try 
		{
			this.request =request;	
			////System.out.println("**********************");
			//System.out.println("GESTION Reclamation......................................");			
			getParametres();	
			ServletContext sc = getServletConfig().getServletContext();	
			if( ! choix.equalsIgnoreCase("supprimer"))
			{
				RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=empl_gestion_reclamations.jsp" +
	    			"&choix=modifier&idReclamation="+idReclamation);
			rd.forward(request, response);
			}
			else
			{			
				RequestDispatcher rd = sc.getRequestDispatcher("/entree?action=liste_reclamation.jsp");
				rd.forward(request, response);
			}
			
		} 
		catch (Exception e) 
		{
			//System.out.println("Gestion de reclamation.....................[Erreur]");
			debug.logger.fatal(e.getMessage());
		}
		
			
		//response.sendRedirect("./entree?action=empl_recherche_reclamations.jsp");		
		//response.getWriter().write("<script>history.go(-3);</script>");
		
	}

	
	public void destroy() 
	{
		super.destroy();		
	}

	/**
	 * Permet de recuperer les parametres html . Sofien CHARFI
	 * 
	 * @since 1.0
	 */
	private void getParametres() 
	{		
		
		////System.out.println("TEst"+request.getParameter("texteReponse"));
		if ((request.getParameter("dateReclamation") != null)	) 
			this.dateReclamation = request.getParameter("dateReclamation");		
		
		if ((request.getParameter("idFactue") != null)
				) 
			this.idFactue = request.getParameter("idFactue");	

		if ((request.getParameter("idRedevable") != null)
				) 
			this.idRedevable= request.getParameter("idRedevable");	
		
		if
		((request.getParameter("listeOuvrage") != null)	) 
			this.listeOuvrage= request.getParameter("listeOuvrage");
		
		
		if ((request.getParameter("typeTaxe") != null)
				) 
			this.typeTaxe = request.getParameter("typeTaxe");	
		
	
		
			
		if ((request.getParameter("numControleur") != null)) 
			this.numControleur = request.getParameter("numControleur");	
		
		/*//System.out.println("--------------------------------------");
		//System.out.println("--------------------------------------");
		//System.out.println("numControleur= "+numControleur);
		//System.out.println("--------------------------------------");
		//System.out.println("--------------------------------------");
		*/
		
		if ((request.getParameter("controleEffectue") != null)
				) 
			this.controleEffectue = request.getParameter("controleEffectue");
		
		if ((request.getParameter("natureReclamation") != null)
				) 
			this.natureReclamation = request.getParameter("natureReclamation");	
		
		if ((request.getParameter("texteReclamation") != null)
				) 
			this.texteReclamation = request.getParameter("texteReclamation");	
			
		
		if ((request.getParameter("pieceJointe") != null)
				) 
			this.pieceJointe = request.getParameter("pieceJointe");	
		
		if ((request.getParameter("etatReclamation") != null)
				) 
			this.etatReclamation = request.getParameter("etatReclamation");	
		
		if ((request.getParameter("dateReponse") != null)
				) 
			this.dateReponse = request.getParameter("dateReponse");	
		
		if ((request.getParameter("texteReponse") != null)
				)		
			this.texteReponse = request.getParameter("texteReponse");
		
		if ((request.getParameter("dateContole") != null)
				)		
			this.dateContole = request.getParameter("dateContole");
	
		
		
		if ((request.getParameter("choix") != null)
				) 
			this.choix = request.getParameter("choix");	
		
		if ((request.getParameter("typeReceptionReclamation") != null)
				) 
			this.typeReceptionReclamation = request.getParameter("typeReceptionReclamation");	
		
		if ((request.getParameter("idReclamation") != null)
				) 
			this.idReclamation = request.getParameter("idReclamation");		

		this.listeOuvrage = listeOuvrage.replaceAll("Ouv", "");
		
		//////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////
	    //TRAITEMENT DES DONNNEE ET ACCES A LA BD
		//////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////
		if(choix.equalsIgnoreCase("ajouter"))
		{
			//IL sagit d'ajouter une reclmation 
			RequestReclamation reqRecl= new RequestReclamation();
			int idNewReclamation =reqRecl.ajouterReclamation( dateReclamation,
					  natureReclamation,Integer.parseInt(idRedevable),typeReceptionReclamation,
					  Integer.parseInt(idFactue), texteReclamation,etatReclamation,
					  typeTaxe,controleEffectue,listeOuvrage);
			this.idReclamation = String.valueOf(idNewReclamation);		
			
		}
		else if(choix.equalsIgnoreCase("modifier"))
		{
			RequestReclamation reqRecl= new RequestReclamation();
			if( numControleur.length()==0 ) numControleur="0";
			this.idReclamation=  reqRecl.majReclamation(Integer.parseInt(idReclamation), dateReclamation,
					  natureReclamation,Integer.parseInt(idRedevable),typeReceptionReclamation,
					  Integer.parseInt(idFactue), texteReclamation,etatReclamation,typeTaxe,
					  texteReponse,dateReponse,controleEffectue,dateContole,
					  listeOuvrage,numControleur);			
		}	
		else if(choix.equalsIgnoreCase("supprimer"))
		{
			////System.out.println("Suppresion d'une reclamation"+idReclamation);
			RequestReclamation reqRecl= new RequestReclamation();
			reqRecl.supprimerReclamation(Integer.parseInt(idReclamation));			
		}
		
		
		////System.out.println("**********************");		
	}

	/**
	 * @return the choix
	 */
	public String getChoix() {
		return choix;
	}

	/**
	 * @param choix the choix to set
	 */
	public void setChoix(String choix) {
		this.choix = choix;
	}

	/**
	 * @return the dateReclamation
	 */
	public String getDateReclamation() {
		return dateReclamation;
	}

	/**
	 * @param dateReclamation the dateReclamation to set
	 */
	public void setDateReclamation(String dateReclamation) {
		this.dateReclamation = dateReclamation;
	}

	/**
	 * @return the dateReponse
	 */
	public String getDateReponse() {
		return dateReponse;
	}

	/**
	 * @param dateReponse the dateReponse to set
	 */
	public void setDateReponse(String dateReponse) {
		this.dateReponse = dateReponse;
	}

	/**
	 * @return the etatReclamation
	 */
	public String getEtatReclamation() {
		return etatReclamation;
	}

	/**
	 * @param etatReclamation the etatReclamation to set
	 */
	public void setEtatReclamation(String etatReclamation) {
		this.etatReclamation = etatReclamation;
	}

	/**
	 * @return the idFactue
	 */
	public String getIdFactue() {
		return idFactue;
	}

	/**
	 * @param idFactue the idFactue to set
	 */
	public void setIdFactue(String idFactue) {
		this.idFactue = idFactue;
	}

	/**
	 * @return the idReclamation
	 */
	public String getIdReclamation() {
		return idReclamation;
	}

	/**
	 * @param idReclamation the idReclamation to set
	 */
	public void setIdReclamation(String idReclamation) {
		this.idReclamation = idReclamation;
	}

	/**
	 * @return the idRedevable
	 */
	public String getIdRedevable() {
		return idRedevable;
	}

	/**
	 * @param idRedevable the idRedevable to set
	 */
	public void setIdRedevable(String idRedevable) {
		this.idRedevable = idRedevable;
	}


	/**
	 * @return the natureReclamation
	 */
	public String getnatureReclamation() {
		return natureReclamation;
	}

	/**
	 * @param natureReclamation the natureReclamation to set
	 */
	public void setnatureReclamation(String natureReclamation) {
		this.natureReclamation = natureReclamation;
	}

	/**
	 * @return the pieceJointe
	 */
	public String getPieceJointe() {
		return pieceJointe;
	}

	/**
	 * @param pieceJointe the pieceJointe to set
	 */
	public void setPieceJointe(String pieceJointe) {
		this.pieceJointe = pieceJointe;
	}

	/**
	 * @return the texteReclamation
	 */
	public String getTexteReclamation() {
		return texteReclamation;
	}

	/**
	 * @param texteReclamation the texteReclamation to set
	 */
	public void setTexteReclamation(String texteReclamation) {
		this.texteReclamation = texteReclamation;
	}

	/**
	 * @return the texteReponse
	 */
	public String getTexteReponse() {
		return texteReponse;
	}

	/**
	 * @param texteReponse the texteReponse to set
	 */
	public void setTexteReponse(String texteReponse) {
		this.texteReponse = texteReponse;
	}

	/**
	 * @return the typeReceptionReclamation
	 */
	public String getTypeReceptionReclamation() {
		return typeReceptionReclamation;
	}

	/**
	 * @param typeReceptionReclamation the typeReceptionReclamation to set
	 */
	public void setTypeReceptionReclamation(String typeReceptionReclamation) {
		this.typeReceptionReclamation = typeReceptionReclamation;
	}

	/**
	 * @return the typeTaxe
	 */
	public String getTypeTaxe() {
		return typeTaxe;
	}

	/**
	 * @param typeTaxe the typeTaxe to set
	 */
	public void setTypeTaxe(String typeTaxe) {
		this.typeTaxe = typeTaxe;
	}

	/**
	 * @return the controleEffectue
	 */
	public String getcontroleEffectue() {
		return controleEffectue;
	}

	/**
	 * @param controleEffectue the controleEffectue to set
	 */
	public void setcontroleEffectue(String controleEffectue) {
		this.controleEffectue = controleEffectue;
	}
	
}
