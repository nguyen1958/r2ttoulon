package fr.analogon.r2t.view.role;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.pojo.Redevable;
import fr.analogon.r2t.request.DataFromBD;
import fr.analogon.r2t.request.RequestRedevable;

/**
 * Servlet de gestion des redevables . 
 * Sofien CHARFI
 * 
 * @version 1.0
 * @since 1.0
 */
public class GestionRedevable extends fr.analogon.r2t.main.RacineServlet 
{	
//	 Apartir de la version 2
    private String numRedevable;    
    private int codeProffesionRedevable;
    private String raisonSocialeRedevable="";
    private String natureJuridiqueRedevable="";    
	private String nomRedevable="";
	private String informationComplementaire="";
	private String commentaire="";
	private String  siret ="" ;
	private String  siren ="" ;
	private String nomJeuneFilleRedevable="";
	private String prenomRedevable="";
	private String responsableRedevable="";
	private String dateNaissanceRedevable="";
	private String lieuNaissanceRedevable="";
	private String nationalliteRedevable="";
	private String numVoieRedevable="";
	private String codeVoixRedevable="";
	private String adressRedevable="";
	private String complement1AdressRedevable="";
	private String complement2AdressRedevable="";
	private String codePostaleRedevable="";
	private String villeeRedevable="";
	private String cedexRedevable="";
	private String numTelFixeRedevable="";
	private String numTelPortableRedevable="";
	private String numTelFaxeRedevable="";
	private String emailRedevable="";	
	private String proffesionRedevable="";
	
	
    private String raisonSocialeLiquidateur="";
	private String nomLiquidateur="";
	private String prenomLiquidateur="";	
	private String numVoieLiquidateur="";
	private String codeVoixLiquidateur="";
	private String adressLiquidateur="";
	private String complement1AdressLiquidateur="";
	private String complement2AdressLiquidateur="";
	private String codePostaleLiquidateur="";
	private String villeeLiquidateur="";
	private String cedexLiquidateur="";
	private String numTelFixeLiquidateur="";
	private String numTelPortableLiquidateur="";
	private String numTelFaxeLiquidateur="";
	private String emailLiquidateur="";
	private String redevableActif="";
	
	private String choix="";
	private String changementAdresseRedevable="";
	private HttpServletRequest request;	
	private String complementNumeroRueRedevable="";
	private String complementNumeroRueLiquidateur="";
	private String immeubleResidenceRedevable="";
	
	
	/**
	 * @return the adressLiquidateur
	 */
	public String getAdressLiquidateur() {
		return adressLiquidateur;
	}

	/**
	 * @param adressLiquidateur the adressLiquidateur to set
	 */
	public void setAdressLiquidateur(String adressLiquidateur) {
		this.adressLiquidateur = adressLiquidateur;
	}

	/**
	 * @return the adressRedevable
	 */
	public String getAdressRedevable() {
		return adressRedevable;
	}

	/**
	 * @param adressRedevable the adressRedevable to set
	 */
	public void setAdressRedevable(String adressRedevable) {
		this.adressRedevable = adressRedevable;
	}

	/**
	 * @return the cedexLiquidateur
	 */
	public String getCedexLiquidateur() {
		return cedexLiquidateur;
	}

	/**
	 * @param cedexLiquidateur the cedexLiquidateur to set
	 */
	public void setCedexLiquidateur(String cedexLiquidateur) {
		this.cedexLiquidateur = cedexLiquidateur;
	}

	/**
	 * @return the cedexRedevable
	 */
	public String getCedexRedevable() {
		return cedexRedevable;
	}

	/**
	 * @param cedexRedevable the cedexRedevable to set
	 */
	public void setCedexRedevable(String cedexRedevable) {
		this.cedexRedevable = cedexRedevable;
	}

	/**
	 * @return the codePostaleLiquidateur
	 */
	public String getCodePostaleLiquidateur() {
		return codePostaleLiquidateur;
	}

	/**
	 * @param codePostaleLiquidateur the codePostaleLiquidateur to set
	 */
	public void setCodePostaleLiquidateur(String codePostaleLiquidateur) {
		this.codePostaleLiquidateur = codePostaleLiquidateur;
	}

	/**
	 * @return the codePostaleRedevable
	 */
	public String getCodePostaleRedevable() {
		return codePostaleRedevable;
	}

	/**
	 * @param codePostaleRedevable the codePostaleRedevable to set
	 */
	public void setCodePostaleRedevable(String codePostaleRedevable) {
		this.codePostaleRedevable = codePostaleRedevable;
	}

	/**
	 * @return the codeVoixLiquidateur
	 */
	public String getCodeVoixLiquidateur() {
		return codeVoixLiquidateur;
	}

	/**
	 * @param codeVoixLiquidateur the codeVoixLiquidateur to set
	 */
	public void setCodeVoixLiquidateur(String codeVoixLiquidateur) {
		this.codeVoixLiquidateur = codeVoixLiquidateur;
	}

	/**
	 * @return the codeVoixRedevable
	 */
	public String getCodeVoixRedevable() {
		return codeVoixRedevable;
	}

	/**
	 * @param codeVoixRedevable the codeVoixRedevable to set
	 */
	public void setCodeVoixRedevable(String codeVoixRedevable) {
		this.codeVoixRedevable = codeVoixRedevable;
	}

	/**
	 * @return the complement1AdressLiquidateur
	 */
	public String getComplement1AdressLiquidateur() {
		return complement1AdressLiquidateur;
	}

	/**
	 * @param complement1AdressLiquidateur the complement1AdressLiquidateur to set
	 */
	public void setComplement1AdressLiquidateur(String complement1AdressLiquidateur) {
		this.complement1AdressLiquidateur = complement1AdressLiquidateur;
	}

	/**
	 * @return the complement1AdressRedevable
	 */
	public String getComplement1AdressRedevable() {
		return complement1AdressRedevable;
	}

	/**
	 * @param complement1AdressRedevable the complement1AdressRedevable to set
	 */
	public void setComplement1AdressRedevable(String complement1AdressRedevable) {
		this.complement1AdressRedevable = complement1AdressRedevable;
	}

	/**
	 * @return the complement2AdressLiquidateur
	 */
	public String getComplement2AdressLiquidateur() {
		return complement2AdressLiquidateur;
	}

	/**
	 * @param complement2AdressLiquidateur the complement2AdressLiquidateur to set
	 */
	public void setComplement2AdressLiquidateur(String complement2AdressLiquidateur) {
		this.complement2AdressLiquidateur = complement2AdressLiquidateur;
	}

	/**
	 * @return the complement2AdressRedevable
	 */
	public String getComplement2AdressRedevable() {
		return complement2AdressRedevable;
	}

	/**
	 * @param complement2AdressRedevable the complement2AdressRedevable to set
	 */
	public void setComplement2AdressRedevable(String complement2AdressRedevable) {
		this.complement2AdressRedevable = complement2AdressRedevable;
	}

	/**
	 * @return the dateNaissanceRedevable
	 */
	public String getDateNaissanceRedevable() {
		return dateNaissanceRedevable;
	}

	/**
	 * @param dateNaissanceRedevable the dateNaissanceRedevable to set
	 */
	public void setDateNaissanceRedevable(String dateNaissanceRedevable) {
		this.dateNaissanceRedevable = dateNaissanceRedevable;
	}

	/**
	 * @return the emailLiquidateur
	 */
	public String getEmailLiquidateur() {
		return emailLiquidateur;
	}

	/**
	 * @param emailLiquidateur the emailLiquidateur to set
	 */
	public void setEmailLiquidateur(String emailLiquidateur) {
		this.emailLiquidateur = emailLiquidateur;
	}

	/**
	 * @return the emailRedevable
	 */
	public String getEmailRedevable() {
		return emailRedevable;
	}

	/**
	 * @param emailRedevable the emailRedevable to set
	 */
	public void setEmailRedevable(String emailRedevable) {
		this.emailRedevable = emailRedevable;
	}

	/**
	 * @return the lieuNaissanceRedevable
	 */
	public String getLieuNaissanceRedevable() {
		return lieuNaissanceRedevable;
	}

	/**
	 * @param lieuNaissanceRedevable the lieuNaissanceRedevable to set
	 */
	public void setLieuNaissanceRedevable(String lieuNaissanceRedevable) {
		this.lieuNaissanceRedevable = lieuNaissanceRedevable;
	}

	/**
	 * @return the nationalliteRedevable
	 */
	public String getNationalliteRedevable() {
		return nationalliteRedevable;
	}

	/**
	 * @param nationalliteRedevable the nationalliteRedevable to set
	 */
	public void setNationalliteRedevable(String nationalliteRedevable) {
		this.nationalliteRedevable = nationalliteRedevable;
	}

	/**
	 * @return the nomJeuneFilleRedevable
	 */
	public String getNomJeuneFilleRedevable() {
		return nomJeuneFilleRedevable;
	}

	/**
	 * @param nomJeuneFilleRedevable the nomJeuneFilleRedevable to set
	 */
	public void setNomJeuneFilleRedevable(String nomJeuneFilleRedevable) {
		this.nomJeuneFilleRedevable = nomJeuneFilleRedevable;
	}

	/**
	 * @return the nomLiquidateur
	 */
	public String getNomLiquidateur() {
		return nomLiquidateur;
	}

	/**
	 * @param nomLiquidateur the nomLiquidateur to set
	 */
	public void setNomLiquidateur(String nomLiquidateur) {
		this.nomLiquidateur = nomLiquidateur;
	}

	/**
	 * @return the nomRedevable
	 */
	public String getNomRedevable() {
		return nomRedevable;
	}

	/**
	 * @param nomRedevable the nomRedevable to set
	 */
	public void setNomRedevable(String nomRedevable) {
		this.nomRedevable = nomRedevable;
	}

	/**
	 * @return the numRedevable
	 */
	public String getNumRedevable() {
		return numRedevable;
	}

	/**
	 * @param numRedevable the numRedevable to set
	 */
	public void setNumRedevable(String numRedevable) {
		this.numRedevable = numRedevable;
	}

	/**
	 * @return the numTelFaxeLiquidateur
	 */
	public String getNumTelFaxeLiquidateur() {
		return numTelFaxeLiquidateur;
	}

	/**
	 * @param numTelFaxeLiquidateur the numTelFaxeLiquidateur to set
	 */
	public void setNumTelFaxeLiquidateur(String numTelFaxeLiquidateur) {
		this.numTelFaxeLiquidateur = numTelFaxeLiquidateur;
	}

	/**
	 * @return the numTelFaxeRedevable
	 */
	public String getNumTelFaxeRedevable() {
		return numTelFaxeRedevable;
	}

	/**
	 * @param numTelFaxeRedevable the numTelFaxeRedevable to set
	 */
	public void setNumTelFaxeRedevable(String numTelFaxeRedevable) {
		this.numTelFaxeRedevable = numTelFaxeRedevable;
	}

	/**
	 * @return the numTelFixeLiquidateur
	 */
	public String getNumTelFixeLiquidateur() {
		return numTelFixeLiquidateur;
	}

	/**
	 * @param numTelFixeLiquidateur the numTelFixeLiquidateur to set
	 */
	public void setNumTelFixeLiquidateur(String numTelFixeLiquidateur) {
		this.numTelFixeLiquidateur = numTelFixeLiquidateur;
	}

	/**
	 * @return the numTelFixeRedevable
	 */
	public String getNumTelFixeRedevable() {
		return numTelFixeRedevable;
	}

	/**
	 * @param numTelFixeRedevable the numTelFixeRedevable to set
	 */
	public void setNumTelFixeRedevable(String numTelFixeRedevable) {
		this.numTelFixeRedevable = numTelFixeRedevable;
	}

	/**
	 * @return the numTelPortableLiquidateur
	 */
	public String getNumTelPortableLiquidateur() {
		return numTelPortableLiquidateur;
	}

	/**
	 * @param numTelPortableLiquidateur the numTelPortableLiquidateur to set
	 */
	public void setNumTelPortableLiquidateur(String numTelPortableLiquidateur) {
		this.numTelPortableLiquidateur = numTelPortableLiquidateur;
	}

	/**
	 * @return the numTelPortableRedevable
	 */
	public String getNumTelPortableRedevable() {
		return numTelPortableRedevable;
	}

	/**
	 * @param numTelPortableRedevable the numTelPortableRedevable to set
	 */
	public void setNumTelPortableRedevable(String numTelPortableRedevable) {
		this.numTelPortableRedevable = numTelPortableRedevable;
	}

	/**
	 * @return the numVoieLiquidateur
	 */
	public String getNumVoieLiquidateur() {
		return numVoieLiquidateur;
	}

	/**
	 * @param numVoieLiquidateur the numVoieLiquidateur to set
	 */
	public void setNumVoieLiquidateur(String numVoieLiquidateur) {
		this.numVoieLiquidateur = numVoieLiquidateur;
	}

	/**
	 * @return the numVoieRedevable
	 */
	public String getNumVoieRedevable() {
		return numVoieRedevable;
	}

	/**
	 * @param numVoieRedevable the numVoieRedevable to set
	 */
	public void setNumVoieRedevable(String numVoieRedevable) {
		this.numVoieRedevable = numVoieRedevable;
	}

	/**
	 * @return the prenomLiquidateur
	 */
	public String getPrenomLiquidateur() {
		return prenomLiquidateur;
	}

	/**
	 * @param prenomLiquidateur the prenomLiquidateur to set
	 */
	public void setPrenomLiquidateur(String prenomLiquidateur) {
		this.prenomLiquidateur = prenomLiquidateur;
	}

	/**
	 * @return the prenomRedevable
	 */
	public String getPrenomRedevable() {
		return prenomRedevable;
	}

	/**
	 * @param prenomRedevable the prenomRedevable to set
	 */
	public void setPrenomRedevable(String prenomRedevable) {
		this.prenomRedevable = prenomRedevable;
	}

	/**
	 * @return the raisonSocialeLiquidateur
	 */
	public String getRaisonSocialeLiquidateur() {
		return raisonSocialeLiquidateur;
	}

	/**
	 * @param raisonSocialeLiquidateur the raisonSocialeLiquidateur to set
	 */
	public void setRaisonSocialeLiquidateur(String raisonSocialeLiquidateur) {
		this.raisonSocialeLiquidateur = raisonSocialeLiquidateur;
	}

	/**
	 * @return the raisonSocialeRedevable
	 */
	public String getRaisonSocialeRedevable() {
		return raisonSocialeRedevable;
	}

	/**
	 * @param raisonSocialeRedevable the raisonSocialeRedevable to set
	 */
	public void setRaisonSocialeRedevable(String raisonSocialeRedevable) {
		this.raisonSocialeRedevable = raisonSocialeRedevable;
	}

	/**
	 * @return the responsableRedevable
	 */
	public String getResponsableRedevable() {
		return responsableRedevable;
	}

	/**
	 * @param responsableRedevable the responsableRedevable to set
	 */
	public void setResponsableRedevable(String responsableRedevable) {
		this.responsableRedevable = responsableRedevable;
	}

	/**
	 * @return the villeeLiquidateur
	 */
	public String getVilleeLiquidateur() {
		return villeeLiquidateur;
	}

	/**
	 * @param villeeLiquidateur the villeeLiquidateur to set
	 */
	public void setVilleeLiquidateur(String villeeLiquidateur) {
		this.villeeLiquidateur = villeeLiquidateur;
	}

	/**
	 * @return the villeeRedevable
	 */
	public String getVilleeRedevable() {
		return villeeRedevable;
	}

	/**
	 * @param villeeRedevable the villeeRedevable to set
	 */
	public void setVilleeRedevable(String villeeRedevable) {
		this.villeeRedevable = villeeRedevable;
	}

	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{	
		this.request =request;	
		getParametres();	
		ServletContext sc = getServletConfig().getServletContext();
		RequestDispatcher rd ;
		if (choix.equalsIgnoreCase("supprimer"))
		{
		 rd = sc.getRequestDispatcher("/entree?action=empl_recherche_redevable.jsp?" +
		 			"action=empl_recherche_redevable.jsp&typeRecherche=role");		
		}
		else
		{
    	 rd = sc.getRequestDispatcher("/entree?action=empl_gestion_redevable.jsp" +
    	"&choix=modifier&boton=ajouter&typeRecherche=role&optionTransfert=role" +
    	"&typeRedevable=normal&origine=origine&numRedevable="+numRedevable);
		}
		rd.forward(request, response);   	
			
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
		
		
		if (request.getParameter("choix") != null)	 
			this.choix = request.getParameter("choix");
		
		if (request.getParameter("complementNumeroRueRedevable") != null)	 
			this.complementNumeroRueRedevable = request.getParameter("complementNumeroRueRedevable");
		
		if (request.getParameter("complementNumeroRueLiquidateur") != null)	 
			this.complementNumeroRueLiquidateur = request.getParameter("complementNumeroRueLiquidateur");
		
		if (request.getParameter("immeubleResidenceRedevable") != null)	 
			this.immeubleResidenceRedevable = request.getParameter("immeubleResidenceRedevable");
		

		if (request.getParameter("numRedevable") != null)
			this.numRedevable = request.getParameter("numRedevable");	

		if (request.getParameter("raisonSocialeRedevable")!= null)
			this.raisonSocialeRedevable = request.getParameter("raisonSocialeRedevable");
		
		
		if (request.getParameter("natureJuridiqueRedevable")!= null)
			this.natureJuridiqueRedevable = request.getParameter("natureJuridiqueRedevable");

		
		
		if (request.getParameter("nomRedevable")!= null)
		{
			this.nomRedevable = request.getParameter("nomRedevable");
		}
		if (request.getParameter("informationComplementaire")!= null)
		{
			this.informationComplementaire = request.getParameter("informationComplementaire");
		}
		if (request.getParameter("commentaire")!= null)
		{
			this.commentaire = request.getParameter("commentaire");
		}
		if (request.getParameter("siret")!= null)
		{
			this.siret = request.getParameter("siret");
		}
		if (request.getParameter("siren")!= null)
		{
			this.siren = request.getParameter("siren");
		}
		
		
		
		
		
		if (request.getParameter("nomJeuneFilleRedevable")!= null)
		{
			this.nomJeuneFilleRedevable = request.getParameter("nomJeuneFilleRedevable");				
		}
			

		if (request.getParameter("prenomRedevable")!= null)
		{
			this.prenomRedevable = request.getParameter("prenomRedevable");
			
		}
		    

		if (request.getParameter("responsableRedevable")!= null)
		{
			this.responsableRedevable = request.getParameter("responsableRedevable");
			
		}
			
		
		if (request.getParameter("dateNaissanceRedevable")!= null)
			this.dateNaissanceRedevable = request.getParameter("dateNaissanceRedevable");

		if (request.getParameter("lieuNaissanceRedevable")!= null)
		{
			this.lieuNaissanceRedevable = request.getParameter("lieuNaissanceRedevable");
			
		}

		if (request.getParameter("nationalliteRedevable")!= null)
		{
			this.nationalliteRedevable = request.getParameter("nationalliteRedevable");
			
		}

		if (request.getParameter("numVoieRedevable")!= null)
			this.numVoieRedevable = request.getParameter("numVoieRedevable");
	
		if (request.getParameter("codeVoixRedevable")!= null)	
			this.codeVoixRedevable = request.getParameter("codeVoixRedevable");

		if (request.getParameter("adressRedevable")!= null)
		{
			this.adressRedevable = request.getParameter("adressRedevable");
			
		}
			

		if (request.getParameter("complement1AdressRedevable")!= null)
		{
			this.complement1AdressRedevable = request.getParameter("complement1AdressRedevable");
			
		}
			

		if (request.getParameter("complement2AdressRedevable")!= null)
		{
			this.complement2AdressRedevable = request.getParameter("complement2AdressRedevable");
			
		}
			

		if (request.getParameter("codePostaleRedevable")!= null)
			this.codePostaleRedevable = request.getParameter("codePostaleRedevable");

		if (request.getParameter("villeeRedevable")!= null)
		{
			this.villeeRedevable = request.getParameter("villeeRedevable");
			
		}
			

		if (request.getParameter("cedexRedevable")!= null)
			this.cedexRedevable = request.getParameter("cedexRedevable");

		if (request.getParameter("numTelFixeRedevable")!= null)
			this.numTelFixeRedevable = request.getParameter("numTelFixeRedevable");

		if (request.getParameter("numTelPortableRedevable")!= null)
			this.numTelPortableRedevable = request.getParameter("numTelPortableRedevable");

		if (request.getParameter("numTelFaxeRedevable")!= null)
			this.numTelFaxeRedevable = request.getParameter("numTelFaxeRedevable");

		if (request.getParameter("emailRedevable")!= null)
			
			this.emailRedevable = request.getParameter("emailRedevable");

		if (request.getParameter("raisonSocialeLiquidateur")!= null)
			this.raisonSocialeLiquidateur = request.getParameter("raisonSocialeLiquidateur");

		if (request.getParameter("nomLiquidateur")!= null)
		{
			this.nomLiquidateur = request.getParameter("nomLiquidateur");
			
		}	
			

		if (request.getParameter("prenomLiquidateur")!= null)	
		{
			this.prenomLiquidateur = request.getParameter("prenomLiquidateur");
			
		}
			

		if (request.getParameter("numVoieLiquidateur")!= null)
			this.numVoieLiquidateur = request.getParameter("numVoieLiquidateur");

		if (request.getParameter("codeVoixLiquidateur")!= null)
			this.codeVoixLiquidateur = request.getParameter("codeVoixLiquidateur");

		if (request.getParameter("adressLiquidateur")!= null)
		{
			this.adressLiquidateur = request.getParameter("adressLiquidateur");
			
		}
			

		if (request.getParameter("complement1AdressLiquidateur")!= null)
		{
			this.complement1AdressLiquidateur = request.getParameter("complement1AdressLiquidateur");
			
		}
			

		if (request.getParameter("complement2AdressLiquidateur")!= null)
		{
			this.complement2AdressLiquidateur = request.getParameter("complement2AdressLiquidateur");
			
		}
			

		if (request.getParameter("codePostaleLiquidateur")!= null)
			this.codePostaleLiquidateur = request.getParameter("codePostaleLiquidateur");

		if (request.getParameter("villeeLiquidateur")!= null)	
			this.villeeLiquidateur = request.getParameter("villeeLiquidateur");

		if (request.getParameter("cedexLiquidateur")!= null)
			this.cedexLiquidateur = request.getParameter("cedexLiquidateur");

		if (request.getParameter("numTelFixeLiquidateur")!= null)
			this.numTelFixeLiquidateur = request.getParameter("numTelFixeLiquidateur");

		if (request.getParameter("numTelPortableLiquidateur")!= null)
			this.numTelPortableLiquidateur = request.getParameter("numTelPortableLiquidateur");

		if (request.getParameter("numTelFaxeLiquidateur")!= null)
			this.numTelFaxeLiquidateur = request.getParameter("numTelFaxeLiquidateur");

		if (request.getParameter("emailLiquidateur")!= null)
			this.emailLiquidateur = request.getParameter("emailLiquidateur");
		
		if (request.getParameter("proffesionRedevable")!= null)
		{
			this.proffesionRedevable = request.getParameter("proffesionRedevable");
			DataFromBD t = new DataFromBD();	
			//System.out.println("proffesionRedevable"+proffesionRedevable);
			this.codeProffesionRedevable = t.getCodeProffesion(proffesionRedevable);
			//System.out.println("codeProffesionRedevable"+codeProffesionRedevable);
		
		}	
		
		if (request.getParameter("changementAdresseRedevable")!= null)
		{
			this.changementAdresseRedevable = request.getParameter("changementAdresseRedevable");		
		}
		
		if (request.getParameter("redevableActif")!= null)
		{
			this.redevableActif = request.getParameter("redevableActif");
			if (redevableActif.equalsIgnoreCase("OUI")) 
				redevableActif ="true";
			else
				redevableActif="false" ;
		}	
		

		
		
		////////////////////////////////////////////////////////////////////////
	    //TRAITEMENT DES DONNNEE ET ACCES A LA BD
		//////////////////////////////////////////////////////////////////////
		RequestRedevable requete= new RequestRedevable();
		
		if(choix.equalsIgnoreCase("creer"))
		{
			//IL sagit de creer d'un redevable 			
			//System.out.println("ajout d'un redevable ");
			//paul
 			requete.ajouterRedevable( raisonSocialeRedevable,  nomRedevable,  nomJeuneFilleRedevable,
					  prenomRedevable,  responsableRedevable,	  dateNaissanceRedevable,
					  lieuNaissanceRedevable,	  nationalliteRedevable,  numVoieRedevable,
					  codeVoixRedevable,  adressRedevable,	  complement1AdressRedevable,
					  complement2AdressRedevable,  codePostaleRedevable,  villeeRedevable,
					  cedexRedevable,  numTelFixeRedevable,		  numTelPortableRedevable,
					  numTelFaxeRedevable,  emailRedevable,	  raisonSocialeLiquidateur,
					  nomLiquidateur,  prenomLiquidateur,  numVoieLiquidateur,
					  codeVoixLiquidateur,  adressLiquidateur,	  complement1AdressLiquidateur,
					  complement2AdressLiquidateur,  codePostaleLiquidateur,  villeeLiquidateur,
					  cedexLiquidateur,  numTelFixeLiquidateur,  numTelPortableLiquidateur,
					  numTelFaxeLiquidateur,  emailLiquidateur,String.valueOf(codeProffesionRedevable)
					  ,redevableActif,complementNumeroRueRedevable,complementNumeroRueLiquidateur,
					  natureJuridiqueRedevable, informationComplementaire, commentaire,siret,siren,
					  immeubleResidenceRedevable);
 			
 			
			Redevable red = requete.getRedevable(raisonSocialeRedevable,  nomRedevable, prenomRedevable);
			this.numRedevable = red.getNumRedevable();
			
		}
		else if(choix.equalsIgnoreCase("modifier"))
		{			
			//System.out.println("modification du redevable Num "+numRedevable);
			//System.out.println("changementAdresseRedevable "+changementAdresseRedevable);
			//Verfier si le redevable a  changer d'adresse 
			if(changementAdresseRedevable.equalsIgnoreCase("true"))
			{
				//System.out.println("Changement de l'adresse du redevable "+numRedevable+"...........");				
				RequestRedevable req = new RequestRedevable();
				req.changerAdresse(numRedevable,numVoieRedevable,
						codeVoixRedevable,adressRedevable,complement1AdressRedevable,
						complement2AdressRedevable,	codePostaleRedevable,villeeRedevable,
						cedexRedevable,complementNumeroRueRedevable);
				
			}
			//R2tDebug.write("modification du redevable Né"+numRedevable);
			requete.modifierRedevable(numRedevable,raisonSocialeRedevable,  nomRedevable,  nomJeuneFilleRedevable,
					  prenomRedevable,  responsableRedevable,	  dateNaissanceRedevable,
					  lieuNaissanceRedevable,	  nationalliteRedevable,  numVoieRedevable,
					  codeVoixRedevable,  adressRedevable,	  complement1AdressRedevable,
					  complement2AdressRedevable,  codePostaleRedevable,  villeeRedevable,
					  cedexRedevable,  numTelFixeRedevable,		  numTelPortableRedevable,
					  numTelFaxeRedevable,  emailRedevable,	  raisonSocialeLiquidateur,
					  nomLiquidateur,  prenomLiquidateur,  numVoieLiquidateur,
					  codeVoixLiquidateur,  adressLiquidateur,	  complement1AdressLiquidateur,
					  complement2AdressLiquidateur,  codePostaleLiquidateur,  villeeLiquidateur,
					  cedexLiquidateur,  numTelFixeLiquidateur,  numTelPortableLiquidateur,
					  numTelFaxeLiquidateur,  emailLiquidateur, String.valueOf(codeProffesionRedevable)
					  ,redevableActif,complementNumeroRueRedevable,complementNumeroRueLiquidateur,
					  natureJuridiqueRedevable,informationComplementaire, commentaire,siret,siren,
					  immeubleResidenceRedevable);	
		}	
		else if(choix.equalsIgnoreCase("supprimer"))
		{
			//System.out.println("Suppresion d'une redevable"+numRedevable);			
			requete.supprimerRedevable(numRedevable);			
		}		
	}

	/**
	 * @return the proffesionRedevable
	 */
	public String getProffesionRedevable() {
		return proffesionRedevable;
	}

	/**
	 * @param proffesionRedevable the proffesionRedevable to set
	 */
	public void setProffesionRedevable(String proffesionRedevable) {
		this.proffesionRedevable = proffesionRedevable;
	}
	
	public String getNatureJuridiqueRedevable() {
		return natureJuridiqueRedevable;
	}

	public void setNatureJuridiqueRedevable(String natureJuridiqueRedevable) {
		this.natureJuridiqueRedevable = natureJuridiqueRedevable;
	}


		
}
