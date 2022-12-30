package fr.analogon.r2t.view.role;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.pojo.Emplacement;
import fr.analogon.r2t.request.DataFromBD;
import fr.analogon.r2t.request.RequestEmplacement;

/**
 * Servlet de gestion des emplacements . 
 * Sofien CHARFI
 * 
 * @version 1.0
 * @since 1.0
 */
public class GestionEmplODP extends fr.analogon.r2t.main.RacineServlet 
{
	private String nomCommercialEmplacment="";
	private String etatEmplacement = "";
	private String rapprochementIdoss="";
	private String numRue;
	private String codeVoie;
	private String NomQuartier;
	private String adresse1;
	private String adresse2;
	private String adresse3;
	private String cedex;
	private String codePostal;
	private String ville;
	private String numTel;
	private String numPortable;
	private String numfax;
	private String email;
	private String codeInscription;
	private String numInscription;
	private String dateInscription;
	private String dateDebutActivite;
	private String dateFinActivite;
	private String codeProfession;
	private String numRedevable;
	private String codeSecteur ;	
	private String idImputation;
	private String anneeExerciceImputation;
	private String numEmplacment;
	private String raisonSocialeProprietaire="";
	private String numeroEmplacementPersonalise="";
	
	private String numeroAutorisation = "";	
	private String observation = "";
	private String dateReceptionDeclaration = "";
	
	
	
	
	
	
	
	private String complementNumeroRueEmpl="";
	private String complementNumeroRueProprietaire="";
	
		private String nomProprietaire="";
		private String prenomProprietaire="";	
		private String numVoieProprietaire="";
		private String codeVoixProprietaire="";
		private String adressProprietaire="";
		private String complement1AdressProprietaire="";
		private String complement2AdressProprietaire="";
		private String codePostaleProprietaire="";
		private String villeeProprietaire="";
		private String cedexProprietaire="";
		private String numTelFixeProprietaire="";
		private String numTelPortableProprietaire="";
		private String numTelFaxeProprietaire="";
		private String emailProprietaire="";		
		private String choix="";
		private String idRedevableAutorise="";
		private HttpServletRequest request;	

		//AMBULANT (OK):
		String ambulantNomTournee ="";
		String ambulantCarteProfessionelleDateObtention="";
		String ambulantCarteProfessionelleNumero="";
		String ambulantCertficatHygieneDateObtention="";
		String ambulantCertficatHygieneNumero="";
		
		String ambulantCertficatPompierDateObtention="";
		String ambulantCertficatPompierNumero="";
		String ambulantVehiculeMarque="";
		String ambulantVehiculeModele="";
		String ambulantVehiculeImmatriculation="";
		String ambulantCompanieAssurance="";

		//BUS (OK) :
		String busCompanieAutobus="";
		String busNombreCar="";
		String busLigneDeBus="";
		String busNombreDePlaceAssises="";
		String busNombreDePlaceDebout="";

		//TAXI  (OK) :
		String taxiNumeroTaxi="";
		String taxiNumeroAssurance="";
		String taxiImmatriculation="";
		String taxiNumeroDeCarteGrise="";

		//CONVENTION :
		String conventionDate ="";
		String conventionDureeAns ="";
		String conventionDureeMois ="";
		String conventionDateEffet ="";
		String conventionDatePremiereRevision ="";
		String conventionDureeDatePremiereRevision ="";
		String conventionObjet ="";
		String conventionLibelle ="";
		String conventionRenouvellement ="";
		String conventionMontantDuConditionnement ="";
		String conventionRecondiction ="";
		String conventionRecondictionNombreMaximum ="";


		//DROIT DE VOIRIE :
		String droitDeVoirieAutorisationNumeroAutorisation="";
		String droitDeVoirieAutorisationDate="";
		String droitDeVoirieAutorisationType="";
		String droitDeVoirieTraveauxDureeMois="";
		String droitDeVoirieTraveauxDureeJour="";
		String droitDeVoirieTraveauxExecute="";
		String droitDeVoirieTraveauxConforme="";
		String droitDeVoirieNomInspecteur="";
		String droitDeVoirieNature="";
		String droitDeVoirieObjetDeTraveaux="";
		String droitDeVoirieObjetDeAutorisation="";

		//MARCHE :
		String marcheNomMarche="";


		//KIOSQUE
		String kiosqueCategorie="";
		String kiosqueNatureVente="";
		


		public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
		{
			try 
			{
				this.request = request;
				numEmplacment= request.getParameter("numEmplacment");
			
				getParametres();	
				ServletContext sc = getServletConfig().getServletContext();
				if ( ! choix.equalsIgnoreCase("supprimer"))
				{
					response.sendRedirect("./entree?action=empl_gestion_emplacementodp.jsp" +
						"&choix=modifier&optionTransfert=role&origine=origine&&numEmplacment="+numEmplacment);
				}
				else
				{
					response.sendRedirect("./entree?action=empl_gestion_redevable.jsp" +
							"&choix=modifier&boton=modifier&typeRecherche=role&numRedevable="+ numRedevable);
				}				
			}
			catch (Exception e) 
			{
				debug.logger.debug("Erreur dans la servlet gestion emplcmaent " + e.getMessage());
			}
			
		}	
		
		
		/**
		 * @return the adresse1
		 */
		public String getAdresse1() {
			return adresse1;
		}

		/**
		 * @param adresse1 the adresse1 to set
		 */
		public void setAdresse1(String adresse1) {
			this.adresse1 = adresse1;
		}

		/**
		 * @return the adresse2
		 */
		public String getAdresse2() {
			return adresse2;
		}

		/**
		 * @param adresse2 the adresse2 to set
		 */
		public void setAdresse2(String adresse2) {
			this.adresse2 = adresse2;
		}

		/**
		 * @return the adresse3
		 */
		public String getAdresse3() {
			return adresse3;
		}

		/**
		 * @param adresse3 the adresse3 to set
		 */
		public void setAdresse3(String adresse3) {
			this.adresse3 = adresse3;
		}

		/**
		 * @return the codeInscription
		 */
		public String getCodeInscription() {
			return codeInscription;
		}

		/**
		 * @param codeInscription the codeInscription to set
		 */
		public void setCodeInscription(String codeInscription) {
			this.codeInscription = codeInscription;
		}

		/**
		 * @return the codePostal
		 */
		public String getCodePostal() {
			return codePostal;
		}

		/**
		 * @param codePostal the codePostal to set
		 */
		public void setCodePostal(String codePostal) {
			this.codePostal = codePostal;
		}

		/**
		 * @return the codeProfession
		 */
		public String getCodeProfession() {
			return codeProfession;
		}

		/**
		 * @param codeProfession the codeProfession to set
		 */
		public void setCodeProfession(String codeProfession) {
			this.codeProfession = codeProfession;
		}

		/**
		 * @return the codeVoie
		 */
		public String getCodeVoie() {
			return codeVoie;
		}

		/**
		 * @param codeVoie the codeVoie to set
		 */
		public void setCodeVoie(String codeVoie) {
			this.codeVoie = codeVoie;
		}

		/**
		 * @return the dateDebutActivite
		 */
		public String getDateDebutActivite() {
			return dateDebutActivite;
		}

		/**
		 * @param dateDebutActivite the dateDebutActivite to set
		 */
		public void setDateDebutActivite(String dateDebutActivite) {
			this.dateDebutActivite = dateDebutActivite;
		}

		/**
		 * @return the dateFinActivite
		 */
		public String getDateFinActivite() {
			return dateFinActivite;
		}

		/**
		 * @param dateFinActivite the dateFinActivite to set
		 */
		public void setDateFinActivite(String dateFinActivite) {
			this.dateFinActivite = dateFinActivite;
		}

		/**
		 * @return the dateInscription
		 */
		public String getDateInscription() {
			return dateInscription;
		}

		/**
		 * @param dateInscription the dateInscription to set
		 */
		public void setDateInscription(String dateInscription) {
			this.dateInscription = dateInscription;
		}

		/**
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * @param email the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}
		

		/**
		 * @return the nomQuartier
		 */
		public String getNomQuartier() {
			return NomQuartier;
		}

		/**
		 * @param nomQuartier the nomQuartier to set
		 */
		public void setNomQuartier(String nomQuartier) {
			NomQuartier = nomQuartier;
		}

		/**
		 * @return the numfax
		 */
		public String getNumfax() {
			return numfax;
		}

		/**
		 * @param numfax the numfax to set
		 */
		public void setNumfax(String numfax) {
			this.numfax = numfax;
		}

		/**
		 * @return the numInscription
		 */
		public String getNumInscription() {
			return numInscription;
		}

		/**
		 * @param numInscription the numInscription to set
		 */
		public void setNumInscription(String numInscription) {
			this.numInscription = numInscription;
		}

		/**
		 * @return the numPortable
		 */
		public String getNumPortable() {
			return numPortable;
		}

		/**
		 * @param numPortable the numPortable to set
		 */
		public void setNumPortable(String numPortable) {
			this.numPortable = numPortable;
		}

		/**
		 * @return the numRue
		 */
		public String getNumRue() {
			return numRue;
		}

		/**
		 * @param numRue the numRue to set
		 */
		public void setNumRue(String numRue) {
			this.numRue = numRue;
		}

		/**
		 * @return the numTel
		 */
		public String getNumTel() {
			return numTel;
		}

		/**
		 * @param numTel the numTel to set
		 */
		public void setNumTel(String numTel) {
			this.numTel = numTel;
		}

		

		/**
		 * @return the ville
		 */
		public String getVille() {
			return ville;
		}

		/**
		 * @param ville the ville to set
		 */
		public void setVille(String ville) {
			this.ville = ville;
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
			
			//System.out.println("//="+request.getParameter("ambulantCertificatPompierDateObtention"));
			
			if (request.getParameter("complementNumeroRueEmpl") != null)	 
				this.complementNumeroRueEmpl = request.getParameter("complementNumeroRueEmpl");
			
			if (request.getParameter("complementNumeroRueProprietaire") != null)	 
				this.complementNumeroRueProprietaire = request.getParameter("complementNumeroRueProprietaire");
			
			
			
			if (request.getParameter("numEmplacment") != null)	 
				this.numEmplacment = request.getParameter("numEmplacment");
			
			
			if (request.getParameter("choix") != null)	 
				this.choix = request.getParameter("choix");

			
			
			if (request.getParameter("numRue")!= null)
						this. numRue = request.getParameter("numRue");
			if (request.getParameter("codeVoie")!= null)
						this.codeVoie = request.getParameter("codeVoie");
			if (request.getParameter("leCodeSecteur")!= null)
						this.codeSecteur = request.getParameter("leCodeSecteur");
			
			
			if (request.getParameter("numeroEmplacementPersonalise") != null )
				this.numeroEmplacementPersonalise =request.getParameter("numeroEmplacementPersonalise");
			
			if (request.getParameter("numeroEmplacementPersonalise") != null )
				this.numeroEmplacementPersonalise =request.getParameter("numeroEmplacementPersonalise");
			
			if (request.getParameter("numeroAutorisation") != null )
				this.numeroAutorisation =request.getParameter("numeroAutorisation");
			
			if (request.getParameter("observation") != null )
				this.observation =request.getParameter("observation");

			if (request.getParameter("dateReceptionDeclaration") != null )
				this.dateReceptionDeclaration =request.getParameter("dateReceptionDeclaration");
			else 
				dateReceptionDeclaration ="";
			
			System.out.println(dateReceptionDeclaration);
			
			
			if (request.getParameter("etatEmplacement")!= null)
						this.etatEmplacement = request.getParameter("etatEmplacement");
			if (request.getParameter("NomQuartier")!= null)
						this.NomQuartier = request.getParameter("NomQuartier");
			
			if (request.getParameter("rapprochementIdoss")!= null)
				this.rapprochementIdoss = request.getParameter("rapprochementIdoss");
			
			if (rapprochementIdoss.equalsIgnoreCase("OUI")) 
				rapprochementIdoss ="true";
			else
				rapprochementIdoss ="false";
			
			
			if (request.getParameter("adresse1")!= null)
			{
						this.adresse1 = request.getParameter("adresse1");
						
			}
			if (request.getParameter("adresse2")!= null)
			{
						this.adresse2= request.getParameter("adresse2");					
			}
			if (request.getParameter("adresse3")!= null)
			{
						this.adresse3= request.getParameter("adresse3");
						
			}
			if (request.getParameter("cedex")!= null)
					this.cedex= request.getParameter("cedex");
			if (request.getParameter("codePostal")!= null)
						this.codePostal = request.getParameter("codePostal");
			if (request.getParameter("ville")!= null)
			{
						this.ville = request.getParameter("ville");
						
			}
			if (request.getParameter("numTel")!= null)
						this.numTel = request.getParameter("numTel");
			if (request.getParameter("numPortable")!= null)
						this.numPortable = request.getParameter("numPortable");
			if (request.getParameter("numfax")!= null)
						this.numfax = request.getParameter("numfax");
			if (request.getParameter("email")!= null)
							this.email = request.getParameter("email");
			if (request.getParameter("codeInscription")!= null)
							this.codeInscription = request.getParameter("codeInscription");
			if (request.getParameter("numInscription")!= null)
							this.numInscription = request.getParameter("numInscription");
			if (request.getParameter("dateInscription")!= null)
							this.dateInscription = request.getParameter("dateInscription");
			if (request.getParameter("dateDebutActivite")!= null)
							this.dateDebutActivite = request.getParameter("dateDebutActivite");
			if (request.getParameter("dateFinActivite")!= null)
							this.dateFinActivite = request.getParameter("dateFinActivite");
			if (request.getParameter("codeProfession")!= null)
			{
				//this.codeProfession = ;
				DataFromBD d = new DataFromBD();
				this.codeProfession = String.valueOf(d.getCodeProffesion(request.getParameter("codeProfession")));				
			}
			if (request.getParameter("numRedevable")!= null)
				this.numRedevable = request.getParameter("numRedevable");
		
			if (request.getParameter("idImputation")!= null)
			{
				this.idImputation = request.getParameter("idImputation");
			}
			
			if (request.getParameter("anneeExerciceImputation")!= null)
			{
				this.anneeExerciceImputation = request.getParameter("anneeExerciceImputation");
			}
			
			
			//INformation du prprotaire 			
			if (request.getParameter("raisonSocialeProprietaire")!= null)
				this.raisonSocialeProprietaire = request.getParameter("raisonSocialeProprietaire");

			if (request.getParameter("nomProprietaire")!= null)
			{
				this.nomProprietaire = request.getParameter("nomProprietaire");
				
			}

			if (request.getParameter("prenomProprietaire")!= null)
			{
				this.prenomProprietaire = request.getParameter("prenomProprietaire");
			
			}

			if (request.getParameter("numVoieProprietaire")!= null)
				this.numVoieProprietaire = request.getParameter("numVoieProprietaire");

			if (request.getParameter("codeVoixProprietaire")!= null)
				this.codeVoixProprietaire = request.getParameter("codeVoixProprietaire");

			if (request.getParameter("adressProprietaire")!= null)	
			{
				this.adressProprietaire = request.getParameter("adressProprietaire");
			
			}

			if (request.getParameter("complement1AdressProprietaire")!= null)
			{
				this.complement1AdressProprietaire = request.getParameter("complement1AdressProprietaire");
				
			}

			if (request.getParameter("complement2AdressProprietaire")!= null)
			{
				this.complement2AdressProprietaire = request.getParameter("complement2AdressProprietaire");
				
			}

			if (request.getParameter("codePostaleProprietaire")!= null)
				this.codePostaleProprietaire = request.getParameter("codePostaleProprietaire");

			if (request.getParameter("villeeProprietaire")!= null)	
			{
				this.villeeProprietaire = request.getParameter("villeeProprietaire");
				
			}

			if (request.getParameter("cedexProprietaire")!= null)
				this.cedexProprietaire = request.getParameter("cedexProprietaire");

			if (request.getParameter("numTelFixeProprietaire")!= null)
				this.numTelFixeProprietaire = request.getParameter("numTelFixeProprietaire");

			if (request.getParameter("numTelPortableProprietaire")!= null)
				this.numTelPortableProprietaire = request.getParameter("numTelPortableProprietaire");

			if (request.getParameter("numTelFaxeProprietaire")!= null)
				this.numTelFaxeProprietaire = request.getParameter("numTelFaxeProprietaire");

			if (request.getParameter("emailProprietaire")!= null)
				this.emailProprietaire = request.getParameter("emailProprietaire");
			
			if (request.getParameter("idRedevableAutorise")!= null)
				this.idRedevableAutorise = request.getParameter("idRedevableAutorise");
			
			if (request.getParameter("nomCommercialEmplacment")!= null)
				this.nomCommercialEmplacment = request.getParameter("nomCommercialEmplacment");
			
			
			if (request.getParameter("ambulantNomTournee") != null)
				this.ambulantNomTournee= request.getParameter("ambulantNomTournee");

				if (request.getParameter("ambulantCarteProfessionelleDateObtention") != null)
				this.ambulantCarteProfessionelleDateObtention= request.getParameter("ambulantCarteProfessionelleDateObtention");

				if (request.getParameter("ambulantCarteProfessionelleNumero") != null)
				this.ambulantCarteProfessionelleNumero= request.getParameter("ambulantCarteProfessionelleNumero");

				if (request.getParameter("ambulantCertficatHygieneDateObtention") != null)
				this.ambulantCertficatHygieneDateObtention= request.getParameter("ambulantCertficatHygieneDateObtention");

				if (request.getParameter("ambulantCertficatHygieneNumero") != null)
				this.ambulantCertficatHygieneNumero= request.getParameter("ambulantCertficatHygieneNumero");

				if (request.getParameter("ambulantCertificatPompierDateObtention") != null)
				
					this.ambulantCertficatPompierDateObtention= request.getParameter("ambulantCertificatPompierDateObtention");
				
				if (request.getParameter("ambulantCertficatPompierNumero") != null)
				this.ambulantCertficatPompierNumero= request.getParameter("ambulantCertficatPompierNumero");

				if (request.getParameter("ambulantVehiculeMarque") != null)
				this.ambulantVehiculeMarque= request.getParameter("ambulantVehiculeMarque");




				if (request.getParameter("ambulantVehiculeModele") != null)
				this.ambulantVehiculeModele= request.getParameter("ambulantVehiculeModele");

				if (request.getParameter("ambulantVehiculeImmatriculation") != null)
				this.ambulantVehiculeImmatriculation= request.getParameter("ambulantVehiculeImmatriculation");

				if (request.getParameter("ambulantCompanieAssurance") != null)
				this.ambulantCompanieAssurance= request.getParameter("ambulantCompanieAssurance");

				if (request.getParameter("busCompanieAutobus") != null)
				this.busCompanieAutobus= request.getParameter("busCompanieAutobus");

				if (request.getParameter("busNombreCar") != null)
				this.busNombreCar= request.getParameter("busNombreCar");

				if (request.getParameter("busLigneDeBus") != null)
				this.busLigneDeBus= request.getParameter("busLigneDeBus");

				if (request.getParameter("busNombreDePlaceAssises") != null)
				this.busNombreDePlaceAssises= request.getParameter("busNombreDePlaceAssises");

				if (request.getParameter("busNombreDePlaceDebout") != null)
				this.busNombreDePlaceDebout= request.getParameter("busNombreDePlaceDebout");

				if (request.getParameter("taxiNumeroTaxi") != null)
				this.taxiNumeroTaxi= request.getParameter("taxiNumeroTaxi");

				if (request.getParameter("taxiNumeroAssurance") != null)
				this.taxiNumeroAssurance= request.getParameter("taxiNumeroAssurance");

				if (request.getParameter("taxiImmatriculation") != null)
				this.taxiImmatriculation= request.getParameter("taxiImmatriculation");

				if (request.getParameter("taxiNumeroDeCarteGrise") != null)
				this.taxiNumeroDeCarteGrise= request.getParameter("taxiNumeroDeCarteGrise");


				
				//CONVENTION :
				if (request.getParameter("conventionDate") != null) 
					this.conventionDate = request.getParameter("conventionDate");
				
				if (request.getParameter("conventionDureeAns") != null) 
					this.conventionDureeAns=request.getParameter("conventionDureeAns");
				
				if (request.getParameter("conventionDureeMois") != null) 
				  this.conventionDureeMois=request.getParameter("conventionDureeMois");
				
				if (request.getParameter("conventionDateEffet") != null) 
					this.conventionDateEffet=request.getParameter("conventionDateEffet");
				
				if (request.getParameter("conventionDatePremiereRevision") != null) 
					this.conventionDatePremiereRevision=request.getParameter("conventionDatePremiereRevision");
				
				if (request.getParameter("conventionDureeDatePremiereRevision") != null)   
					this.conventionDureeDatePremiereRevision=request.getParameter("conventionDureeDatePremiereRevision");
				
				if (request.getParameter("conventionObjet") != null)  
					this.conventionObjet=request.getParameter("conventionObjet");
				
				if (request.getParameter("conventionLibelle") != null)  
				 this.conventionLibelle=request.getParameter("conventionLibelle");
				
				if (request.getParameter("conventionRenouvellement") != null)  
					this.conventionRenouvellement=request.getParameter("conventionRenouvellement");
				
				if (request.getParameter("conventionMontantDuConditionnement") != null)  
				 	this.conventionMontantDuConditionnement=request.getParameter("conventionMontantDuConditionnement");
				
				if (request.getParameter("conventionRecondiction") != null)  
					this.conventionRecondiction=request.getParameter("conventionRecondiction");
				
				if (request.getParameter("conventionRecondictionNombreMaximum") != null)  
					this.conventionRecondictionNombreMaximum=request.getParameter("conventionRecondictionNombreMaximum");

				
				
				//droit DE VOIRIE :
				if (request.getParameter("droitDeVoirieAutorisationNumeroAutorisation") != null) 
					this.droitDeVoirieAutorisationNumeroAutorisation=request.getParameter("droitDeVoirieAutorisationNumeroAutorisation");
							
				if (request.getParameter("droitDeVoirieAutorisationDate") != null)  
				    this.droitDeVoirieAutorisationDate=request.getParameter("droitDeVoirieAutorisationDate");
						
				
				System.out.println("\n\ndroitDeVoirieAutorisationType="+request.getParameter("droitDeVoirieAT"));
				if (request.getParameter("droitDeVoirieAT") != null)  
					this.droitDeVoirieAutorisationType=request.getParameter("droitDeVoirieAT");
				
				if (request.getParameter("droitDeVoirieTraveauxDureeMois") != null)  
					this.droitDeVoirieTraveauxDureeMois=request.getParameter("droitDeVoirieTraveauxDureeMois");
				
				if (request.getParameter("droitDeVoirieTraveauxDureeJour") != null)  
					this.droitDeVoirieTraveauxDureeJour=request.getParameter("droitDeVoirieTraveauxDureeJour");
				
				if (request.getParameter("droitDeVoirieTraveauxExecute") != null)  
					this.droitDeVoirieTraveauxExecute=request.getParameter("droitDeVoirieTraveauxExecute");
				
				if (request.getParameter("droitDeVoirieTraveauxConforme") != null) 
				  this.droitDeVoirieTraveauxConforme=request.getParameter("droitDeVoirieTraveauxConforme");
				
				if (request.getParameter("droitDeVoirieNomInspecteur") != null)  
					this.droitDeVoirieNomInspecteur=request.getParameter("droitDeVoirieNomInspecteur");
				
				if (request.getParameter("droitDeVoirieNature") != null)   
					this.droitDeVoirieNature=request.getParameter("droitDeVoirieNature");
				
				if (request.getParameter("droitDeVoirieObjetDeTraveaux") != null)  
				 this.droitDeVoirieObjetDeTraveaux=request.getParameter("droitDeVoirieObjetDeTraveaux");
				
				if (request.getParameter("droitDeVoirieObjetDeAutorisation") != null)  
					this.droitDeVoirieObjetDeAutorisation=request.getParameter("droitDeVoirieObjetDeAutorisation");

				
				
				//MARCHE :
				if (request.getParameter("marcheNomMarche") != null)   
					this.marcheNomMarche=request.getParameter("marcheNomMarche");

				//KIOSQUE				
				if (request.getParameter("kiosqueCategorie") != null)   
					this.kiosqueCategorie=request.getParameter("kiosqueCategorie");
							
				if (request.getParameter("kiosqueNatureVente") != null)   
					this.kiosqueNatureVente=request.getParameter("kiosqueNatureVente");
				

			
			
			////////////////////////////////////////////////////////////////////////
		    //TRAITEMENT DES DONNNEE ET ACCES A LA BD
			//////////////////////////////////////////////////////////////////////
			RequestEmplacement requete= new RequestEmplacement();
			if(choix.equalsIgnoreCase("creer"))
			{
				//IL sagit de creer d'un redevable 			
				if(cedex.length()==0) cedex ="false";
				if(cedexProprietaire.length()==0) cedexProprietaire ="false";
				requete.ajouterEmplacment( nomCommercialEmplacment, numRue,  codeVoie, 
						 codeSecteur,NomQuartier, adresse1, adresse2,adresse3,cedex, 
						 codePostal, ville,  numTel,  numPortable,
						 numfax, email,  codeInscription,  numInscription,
						 dateInscription, dateDebutActivite,  dateFinActivite,
						 codeProfession, raisonSocialeProprietaire,  nomProprietaire,
						 prenomProprietaire,numVoieProprietaire,  codeVoixProprietaire, 
						 adressProprietaire,complement1AdressProprietaire, 
						 complement2AdressProprietaire,  codePostaleProprietaire, 
						 villeeProprietaire,cedexProprietaire,  numTelFixeProprietaire,
						 numTelPortableProprietaire,  numTelFaxeProprietaire, 
						 emailProprietaire,choix,numRedevable,codeSecteur,idImputation,
						 idRedevableAutorise,etatEmplacement,anneeExerciceImputation,rapprochementIdoss,
						 complementNumeroRueEmpl, complementNumeroRueProprietaire,
						 ambulantNomTournee ,ambulantCarteProfessionelleDateObtention,ambulantCarteProfessionelleNumero,
						 ambulantCertficatHygieneDateObtention,ambulantCertficatHygieneNumero,ambulantCertficatPompierDateObtention,
						 ambulantCertficatPompierNumero,	ambulantVehiculeMarque,	ambulantVehiculeModele,
						 ambulantVehiculeImmatriculation,ambulantCompanieAssurance,
						 busCompanieAutobus,busNombreCar,busLigneDeBus,busNombreDePlaceAssises,	busNombreDePlaceDebout,
						 taxiNumeroTaxi,taxiNumeroAssurance,taxiImmatriculation,taxiNumeroDeCarteGrise,
						 conventionDate, conventionDureeAns, conventionDureeMois, conventionDateEffet,
						 conventionDatePremiereRevision, conventionDureeDatePremiereRevision, conventionObjet,
						 conventionLibelle, conventionRenouvellement, conventionMontantDuConditionnement,
						 conventionRecondiction, conventionRecondictionNombreMaximum,
						 marcheNomMarche,
						 droitDeVoirieAutorisationNumeroAutorisation, droitDeVoirieAutorisationDate,
						 droitDeVoirieAutorisationType, droitDeVoirieTraveauxDureeMois,
						 droitDeVoirieTraveauxDureeJour, droitDeVoirieTraveauxExecute,
						 droitDeVoirieTraveauxConforme,	 droitDeVoirieNomInspecteur,
						 droitDeVoirieNature,droitDeVoirieObjetDeTraveaux,
						 droitDeVoirieObjetDeAutorisation,
						 kiosqueCategorie ,kiosqueNatureVente,
						 numeroEmplacementPersonalise,observation,dateReceptionDeclaration
				);

				Emplacement empl= requete.getEmplacement(nomCommercialEmplacment, numRue, adresse1, NomQuartier,numRedevable);
				this.numEmplacment =empl.getNumEmplacement();
				debug.logger.debug("ajout d'un emplacmeent num "+ numEmplacment+" pour le redevable Num " + numRedevable );
			}
			else if(choix.equalsIgnoreCase("modifier"))
			{								
				
				String nomImageASupprimer=request.getParameter("nomImageASupprimer");	
				RequestEmplacement reqEmplacement = new RequestEmplacement();						
				
				if (nomImageASupprimer != null && !nomImageASupprimer.equalsIgnoreCase("null") 
						&& !nomImageASupprimer.equalsIgnoreCase("") )
				{
					//suppresion de l'image 
					reqEmplacement.supprimerImageEmplacementGeneral(nomImageASupprimer);					
				}	
				else
				{
					debug.logger.debug("Modification de l'emplacment "+ numEmplacment);
					requete.modifierEmplacment( numEmplacment, nomCommercialEmplacment, numRue,  codeVoie, 
							 codeSecteur,NomQuartier, adresse1, adresse2,adresse3,cedex, 
							 codePostal, ville,  numTel,  numPortable,
							 numfax, email,  codeInscription,  numInscription,
							 dateInscription, dateDebutActivite,  dateFinActivite,
							 codeProfession, raisonSocialeProprietaire,  nomProprietaire,
							 prenomProprietaire,numVoieProprietaire,  codeVoixProprietaire, 
							 adressProprietaire,complement1AdressProprietaire, 
							 complement2AdressProprietaire,  codePostaleProprietaire, 
							 villeeProprietaire,cedexProprietaire,  numTelFixeProprietaire,
							 numTelPortableProprietaire,  numTelFaxeProprietaire, 
							 emailProprietaire,choix,numRedevable,codeSecteur,idImputation,
							 idRedevableAutorise,etatEmplacement,rapprochementIdoss,
							 complementNumeroRueEmpl, complementNumeroRueProprietaire,
							 taxiNumeroTaxi,taxiNumeroAssurance,taxiImmatriculation, taxiNumeroDeCarteGrise,
							 busCompanieAutobus, busNombreCar, busLigneDeBus, busNombreDePlaceAssises, busNombreDePlaceDebout,
							 ambulantNomTournee , ambulantCarteProfessionelleDateObtention, ambulantCarteProfessionelleNumero,
							 ambulantCertficatHygieneDateObtention, ambulantCertficatHygieneNumero, ambulantCertficatPompierDateObtention,
							 ambulantCertficatPompierNumero, ambulantVehiculeMarque, ambulantVehiculeModele,
							 ambulantVehiculeImmatriculation, ambulantCompanieAssurance,
							 
							 conventionDate, conventionDureeAns, conventionDureeMois, conventionDateEffet,
							 conventionDatePremiereRevision, conventionDureeDatePremiereRevision, conventionObjet,
							 conventionLibelle, conventionRenouvellement, conventionMontantDuConditionnement,
							 conventionRecondiction, conventionRecondictionNombreMaximum,
							 marcheNomMarche,
							 
							 droitDeVoirieAutorisationNumeroAutorisation, droitDeVoirieAutorisationDate,
							 droitDeVoirieAutorisationType,	droitDeVoirieTraveauxDureeMois,
							 droitDeVoirieTraveauxDureeJour, droitDeVoirieTraveauxExecute,
							 droitDeVoirieTraveauxConforme,	droitDeVoirieNomInspecteur,
							 droitDeVoirieNature, droitDeVoirieObjetDeTraveaux,
							 droitDeVoirieObjetDeAutorisation,
							 kiosqueCategorie ,kiosqueNatureVente, observation, dateReceptionDeclaration,
							 numeroEmplacementPersonalise
					);
					
				}
			
			}			
			else if(choix.equalsIgnoreCase("supprimer"))
			{				
				debug.logger.debug("Suppresion de l'emplacement"+numEmplacment);
				requete.supprimerEmplacementGeneral(numEmplacment);			
			}
				
		}

		/**
		 * @return the adressProprietaire
		 */
		public String getAdressProprietaire() {
			return adressProprietaire;
		}

		/**
		 * @param adressProprietaire the adressProprietaire to set
		 */
		public void setAdressProprietaire(String adressProprietaire) {
			this.adressProprietaire = adressProprietaire;
		}

		/**
		 * @return the cedexProprietaire
		 */
		public String getCedexProprietaire() {
			return cedexProprietaire;
		}

		/**
		 * @param cedexProprietaire the cedexProprietaire to set
		 */
		public void setCedexProprietaire(String cedexProprietaire) {
			this.cedexProprietaire = cedexProprietaire;
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
		 * @return the codePostaleProprietaire
		 */
		public String getCodePostaleProprietaire() {
			return codePostaleProprietaire;
		}

		/**
		 * @param codePostaleProprietaire the codePostaleProprietaire to set
		 */
		public void setCodePostaleProprietaire(String codePostaleProprietaire) {
			this.codePostaleProprietaire = codePostaleProprietaire;
		}

		/**
		 * @return the codeVoixProprietaire
		 */
		public String getCodeVoixProprietaire() {
			return codeVoixProprietaire;
		}

		/**
		 * @param codeVoixProprietaire the codeVoixProprietaire to set
		 */
		public void setCodeVoixProprietaire(String codeVoixProprietaire) {
			this.codeVoixProprietaire = codeVoixProprietaire;
		}

		/**
		 * @return the complement1AdressProprietaire
		 */
		public String getComplement1AdressProprietaire() {
			return complement1AdressProprietaire;
		}

		/**
		 * @param complement1AdressProprietaire the complement1AdressProprietaire to set
		 */
		public void setComplement1AdressProprietaire(
				String complement1AdressProprietaire) {
			this.complement1AdressProprietaire = complement1AdressProprietaire;
		}

		/**
		 * @return the complement2AdressProprietaire
		 */
		public String getComplement2AdressProprietaire() {
			return complement2AdressProprietaire;
		}

		/**
		 * @param complement2AdressProprietaire the complement2AdressProprietaire to set
		 */
		public void setComplement2AdressProprietaire(
				String complement2AdressProprietaire) {
			this.complement2AdressProprietaire = complement2AdressProprietaire;
		}

		/**
		 * @return the emailProprietaire
		 */
		public String getEmailProprietaire() {
			return emailProprietaire;
		}

		/**
		 * @param emailProprietaire the emailProprietaire to set
		 */
		public void setEmailProprietaire(String emailProprietaire) {
			this.emailProprietaire = emailProprietaire;
		}

		/**
		 * @return the nomProprietaire
		 */
		public String getNomProprietaire() {
			return nomProprietaire;
		}

		/**
		 * @param nomProprietaire the nomProprietaire to set
		 */
		public void setNomProprietaire(String nomProprietaire) {
			this.nomProprietaire = nomProprietaire;
		}

		/**
		 * @return the numTelFaxeProprietaire
		 */
		public String getNumTelFaxeProprietaire() {
			return numTelFaxeProprietaire;
		}

		/**
		 * @param numTelFaxeProprietaire the numTelFaxeProprietaire to set
		 */
		public void setNumTelFaxeProprietaire(String numTelFaxeProprietaire) {
			this.numTelFaxeProprietaire = numTelFaxeProprietaire;
		}

		/**
		 * @return the numTelFixeProprietaire
		 */
		public String getNumTelFixeProprietaire() {
			return numTelFixeProprietaire;
		}

		/**
		 * @param numTelFixeProprietaire the numTelFixeProprietaire to set
		 */
		public void setNumTelFixeProprietaire(String numTelFixeProprietaire) {
			this.numTelFixeProprietaire = numTelFixeProprietaire;
		}

		/**
		 * @return the numTelPortableProprietaire
		 */
		public String getNumTelPortableProprietaire() {
			return numTelPortableProprietaire;
		}

		/**
		 * @param numTelPortableProprietaire the numTelPortableProprietaire to set
		 */
		public void setNumTelPortableProprietaire(String numTelPortableProprietaire) {
			this.numTelPortableProprietaire = numTelPortableProprietaire;
		}

		/**
		 * @return the numVoieProprietaire
		 */
		public String getNumVoieProprietaire() {
			return numVoieProprietaire;
		}

		/**
		 * @param numVoieProprietaire the numVoieProprietaire to set
		 */
		public void setNumVoieProprietaire(String numVoieProprietaire) {
			this.numVoieProprietaire = numVoieProprietaire;
		}

		/**
		 * @return the prenomProprietaire
		 */
		public String getPrenomProprietaire() {
			return prenomProprietaire;
		}

		/**
		 * @param prenomProprietaire the prenomProprietaire to set
		 */
		public void setPrenomProprietaire(String prenomProprietaire) {
			this.prenomProprietaire = prenomProprietaire;
		}

		/**
		 * @return the raisonSocialeProprietaire
		 */
		public String getRaisonSocialeProprietaire() {
			return raisonSocialeProprietaire;
		}

		/**
		 * @param raisonSocialeProprietaire the raisonSocialeProprietaire to set
		 */
		public void setRaisonSocialeProprietaire(String raisonSocialeProprietaire) {
			this.raisonSocialeProprietaire = raisonSocialeProprietaire;
		}

		/**
		 * @return the villeeProprietaire
		 */
		public String getVilleeProprietaire() {
			return villeeProprietaire;
		}

		/**
		 * @param villeeProprietaire the villeeProprietaire to set
		 */
		public void setVilleeProprietaire(String villeeProprietaire) {
			this.villeeProprietaire = villeeProprietaire;
		}

		/**
		 * @return the cedex
		 */
		public String getCedex() {
			return cedex;
		}

		/**
		 * @param cedex the cedex to set
		 */
		public void setCedex(String cedex) {
			this.cedex = cedex;
		}

		/**
		 * @return the etatEmplacement
		 */
		public String getEtatEmplacement() {
			return etatEmplacement;
		}

		/**
		 * @param etatEmplacement the etatEmplacement to set
		 */
		public void setEtatEmplacement(String etatEmplacement) {
			this.etatEmplacement = etatEmplacement;
		}			
}

