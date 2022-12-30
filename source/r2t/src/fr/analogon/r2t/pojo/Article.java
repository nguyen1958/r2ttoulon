package fr.analogon.r2t.pojo;

import fr.analogon.r2t.Utilitaire.StaticNombres;

/**
 * creer par   le 23/03/2006 pour ANALOGON.
 */
public class Article {
	private String id;

	private String idElementFacturation;
	private String peutEtreSuuprimer;
	private String peutEtreModifier;
	


	private String numEmplacment;
		
	private String numRedevable;
	
	private String codeSecteur;	
	private String numPeriode;	   	
	private String nom;
	private String anExercice;
	private String codeBareme;
	
	private String codeImputation;
	private String libelleImputation;
	//valeur par defaut
	private double longueur=1.00;
	private double largeur=1.00;
	private double surface=1.00;	
	
	private String DateDebutAutorisation="";	
	private String DateFinAutorisation="";	
	private String dateDernierControle="";
	private String dateProchainControl="";
	
	//since 2.0
	private double quantite_article;	
	private String etatArticle;
	private String etatRembourseArticle;
	private String commentaireOuvrage;	
	private String adresseOuvrage;
	private String idImputation;
		
	private String bareme;	
	private String uniteTravail;	
	private String dureeUnitaire;		
	private double prixPeriode1;
	private double prixPeriode2;
	private double prixPeriode3;
	private double prixPeriode4;
	private double prixPeriode5;	
	private String typeArrondi;	
	private String utiliserProrata;
	private String utiliserPeriodicite;
	private String  prixUnitaire;	
	private String  dernierePeriodeFacture;
	private String dateFinIdoss="";
	
	private String  source;
	private String  numeroDePeriodeActuelle="";
	
	private String  libelleBareme="";
	
	private String  typeOuvrage="";
	private String  nombreFaceAffiche="1";

	public String getNombreFaceAffiche() {
		return nombreFaceAffiche;
	}

	public void setNombreFaceAffiche(String nombreFaceAffiche) {
		this.nombreFaceAffiche = nombreFaceAffiche;
	}

	/**
	 * @return the typeOuvrage
	 */
	public final String getTypeOuvrage() {
		return typeOuvrage;
	}

	/**
	 * @param typeOuvrage the typeOuvrage to set
	 */
	public final void setTypeOuvrage(String typeOuvrage) {
		this.typeOuvrage = typeOuvrage;
	}

	/**
	 * @return the libelleBareme
	 */
	public final String getLibelleBareme() {
		return libelleBareme;
	}

	/**
	 * @param libelleBareme the libelleBareme to set
	 */
	public final void setLibelleBareme(String libelleBareme) {
		this.libelleBareme = libelleBareme;
	}

	/**
	 * @return the numeroDePeriodeActuelle
	 */
	public final String getNumeroDePeriodeActuelle() {
		return numeroDePeriodeActuelle;
	}

	/**
	 * @param numeroDePeriodeActuelle the numeroDePeriodeActuelle to set
	 */
	public final void setNumeroDePeriodeActuelle(String numeroDePeriodeActuelle) {
		this.numeroDePeriodeActuelle = numeroDePeriodeActuelle;
	}

	/**
	 * @return the prixUnitaire
	 */
	public final String getPrixUnitaire() {
		return prixUnitaire;
	}

	/**
	 * @param prixUnitaire the prixUnitaire to set
	 */
	public final void setPrixUnitaire(String prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	/**
	 * @return the commentaireOuvrage
	 */
	public String getCommentaireOuvrage() {
		return commentaireOuvrage;
	}

	/**
	 * @param commentaireOuvrage the commentaireOuvrage to set
	 */
	public void setCommentaireOuvrage(String commentaireOuvrage) {
		this.commentaireOuvrage = commentaireOuvrage;
	}

	public String getEtatArticle() {
		return etatArticle;
	}

	public void setEtatArticle(String etatArticle) {
		this.etatArticle = etatArticle;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdElementFacturation() {
		return idElementFacturation;
	}

	public void setIdElementFacturation(String idElementFacturation) {
		this.idElementFacturation = idElementFacturation;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAnExercice() {
		return anExercice;
	}

	public void setAnExercice(String anExercice) {
		this.anExercice = anExercice;
	}

	public String getCodeBareme() {
		return codeBareme;
	}

	public void setCodeBareme(String codeBareme) {
		this.codeBareme = codeBareme;
	}

	public double getLongueur() {
		return longueur;
	}

	/**
	 * Permtre de specifier la longueur sous format xx.xx
	 * 
	 * @param longueur
	 */
	public void setLongueur(double longueur) {
		this.longueur = StaticNombres.arronDD2(longueur);
	}

	public double getLargeur() {
		return largeur;
	}

	/**
	 * Permet de specifier la largueur avec un format x,2
	 * 
	 * @param largeur
	 */
	public void setLargeur(double largeur) {
		this.largeur = StaticNombres.arronDD2(largeur);
	}
	
	public double getQuantite_article()
	{
		return quantite_article;
	}
	/**
	 * Permet de specifier la nombre d'article 
	 * 
	 * @param quantite_article
	 */
	public void setQuantite_article(double x) 
	{
		////System.out.println("setting "+x);
		this.quantite_article = StaticNombres.arronDD2(x);
	}
	


	public String getDateDebutAutorisation() {
		return DateDebutAutorisation;
	}

	public void setDateDebutAutorisation(String dateDebutAutorisation) {
		this.DateDebutAutorisation = dateDebutAutorisation;
	}

	/**
	 * @return the dateProchainControl
	 */
	public String getDateProchainControl() {
		return dateProchainControl;
	}

	/**
	 * @param dateProchainControl the dateProchainControl to set
	 */
	public void setDateProchainControl(String dateProchainControl) {
		this.dateProchainControl = dateProchainControl;
	}

	/**
	 * @return the adresseOuvrage
	 */
	public String getAdresseOuvrage() {
		return adresseOuvrage;
	}

	/**
	 * @param adresseOuvrage the adresseOuvrage to set
	 */
	public void setAdresseOuvrage(String adresseOuvrage) {
		this.adresseOuvrage = adresseOuvrage;
	}


	/**
	 * @return the dateDernierControle
	 */
	public String getDateDernierControle() {
		return dateDernierControle;
	}

	/**
	 * @param dateDernierControle the dateDernierControle to set
	 */
	public void setDateDernierControle(String dateDernierControle) {
		this.dateDernierControle = dateDernierControle;
	}

	/**
	 * @return the bareme
	 */
	public String getBareme() {
		return bareme;
	}

	/**
	 * @param bareme the bareme to set
	 */
	public void setBareme(String bareme) {
		this.bareme = bareme;
	}

	/**
	 * @return the codeSecteur
	 */
	public String getCodeSecteur() {
		return codeSecteur;
	}

	/**
	 * @param codeSecteur the codeSecteur to set
	 */
	public void setCodeSecteur(String codeSecteur) {
		this.codeSecteur = codeSecteur;
	}

	/**
	 * @return the numEmplacment
	 */
	public String getNumEmplacment() {
		return numEmplacment;
	}

	/**
	 * @param numEmplacment the numEmplacment to set
	 */
	public void setNumEmplacment(String numEmplacment) {
		this.numEmplacment = numEmplacment;
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
	 * @return the prixPeriode1
	 */
	public double getPrixPeriode1() {
		return prixPeriode1;
	}

	/**
	 * @param prixPeriode1 the prixPeriode1 to set
	 */
	public void setPrixPeriode1(double prixPeriode1) {
		this.prixPeriode1 = prixPeriode1;
	}

	/**
	 * @return the prixPeriode2
	 */
	public double getPrixPeriode2() {
		return prixPeriode2;
	}

	/**
	 * @param prixPeriode2 the prixPeriode2 to set
	 */
	public void setPrixPeriode2(double prixPeriode2) {
		this.prixPeriode2 = prixPeriode2;
	}

	/**
	 * @return the prixPeriode3
	 */
	public double getPrixPeriode3() {
		return prixPeriode3;
	}

	/**
	 * @param prixPeriode3 the prixPeriode3 to set
	 */
	public void setPrixPeriode3(double prixPeriode3) {
		this.prixPeriode3 = prixPeriode3;
	}

	/**
	 * @return the prixPeriode4
	 */
	public double getPrixPeriode4() {
		return prixPeriode4;
	}

	/**
	 * @param prixPeriode4 the prixPeriode4 to set
	 */
	public void setPrixPeriode4(double prixPeriode4) {
		this.prixPeriode4 = prixPeriode4;
	}

	/**
	 * @return the prixPeriode5
	 */
	public double getPrixPeriode5() {
		return prixPeriode5;
	}

	/**
	 * @param prixPeriode5 the prixPeriode5 to set
	 */
	public void setPrixPeriode5(double prixPeriode5) {
		this.prixPeriode5 = prixPeriode5;
	}

	/**
	 * @return the uniteTravail
	 */
	public String getUniteTravail() {
		return uniteTravail;
	}

	/**
	 * @param uniteTravail the uniteTravail to set
	 */
	public void setUniteTravail(String uniteTravail) {
		this.uniteTravail = uniteTravail;
	}

	/**
	 * @return the dureeUnitaire
	 */
	public String getDureeUnitaire() {
		return dureeUnitaire;
	}

	/**
	 * @param dureeUnitaire the dureeUnitaire to set
	 */
	public void setDureeUnitaire(String dureeUnitaire) {
		this.dureeUnitaire = dureeUnitaire;
	}

	/**
	 * @return the typeArrondi
	 */
	public String getTypeArrondi() {
		return typeArrondi;
	}

	/**
	 * @param typeArrondi the typeArrondi to set
	 */
	public void setTypeArrondi(String typeArrondi) {
		this.typeArrondi = typeArrondi;
	}

	/**
	 * @return the surface
	 */
	public double getSurface() {
		return surface;
	}

	/**
	 * @param surface the surface to set
	 */
	public void setSurface(double surface) {
		this.surface = surface;
	}

	/**
	 * @return the numPeriode
	 */
	public String getNumPeriode() {
		return numPeriode;
	}

	/**
	 * @param numPeriode the numPeriode to set
	 */
	public void setNumPeriode(String numPeriode) {
		this.numPeriode = numPeriode;
	}

	/**
	 * @return the utiliserPeriodicite
	 */
	public final String getUtiliserPeriodicite() {
		return utiliserPeriodicite;
	}

	/**
	 * @param utiliserPeriodicite the utiliserPeriodicite to set
	 */
	public final void setUtiliserPeriodicite(String utiliserPeriodicite) {
		this.utiliserPeriodicite = utiliserPeriodicite;
	}

	/**
	 * @return the utiliserProrata
	 */
	public final String getUtiliserProrata() {
		return utiliserProrata;
	}

	/**
	 * @param utiliserProrata the utiliserProrata to set
	 */
	public final void setUtiliserProrata(String utiliserProrata) {
		this.utiliserProrata = utiliserProrata;
	}

	/**
	 * @return the dateFinAutorisation
	 */
	public final String getDateFinAutorisation() {
		return DateFinAutorisation;
	}

	/**
	 * @param dateFinAutorisation the dateFinAutorisation to set
	 */
	public final void setDateFinAutorisation(String dateFinAutorisation) {
		DateFinAutorisation = dateFinAutorisation;
	}

	/**
	 * @return the dernierePeriodeFacture
	 */
	public final String getDernierePeriodeFacture() {
		return dernierePeriodeFacture;
	}

	/**
	 * @param dernierePeriodeFacture the dernierePeriodeFacture to set
	 */
	public final void setDernierePeriodeFacture(String dernierePeriodeFacture) {
		this.dernierePeriodeFacture = dernierePeriodeFacture;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the codeImputation
	 */
	public final String getCodeImputation() {
		return codeImputation;
	}

	/**
	 * @param codeImputation the codeImputation to set
	 */
	public final void setCodeImputation(String codeImputation) {
		this.codeImputation = codeImputation;
	}

	/**
	 * @return the libelleImputation
	 */
	public final String getLibelleImputation() {
		return libelleImputation;
	}

	/**
	 * @param libelleImputation the libelleImputation to set
	 */
	public final void setLibelleImputation(String libelleImputation) {
		this.libelleImputation = libelleImputation;
	}

	/**
	 * @return the dateFinIdoss
	 */
	public final String getDateFinIdoss() {
		return dateFinIdoss;
	}

	/**
	 * @param dateFinIdoss the dateFinIdoss to set
	 */
	public final void setDateFinIdoss(String dateFinIdoss) {
		this.dateFinIdoss = dateFinIdoss;
	}



	public String getPeutEtreSuuprimer() {
		return peutEtreSuuprimer;
	}

	public void setPeutEtreSuuprimer(String peutEtreSuuprimer) {
		this.peutEtreSuuprimer = peutEtreSuuprimer;
	}

	/**
	 * @return the etatRembourseArticle
	 */
	public String getEtatRembourseArticle() {
		return etatRembourseArticle;
	}

	/**
	 * @param etatRembourseArticle the etatRembourseArticle to set
	 */
	public void setEtatRembourseArticle(String etatRembourseArticle) {
		this.etatRembourseArticle = etatRembourseArticle;
	}

	/**
	 * @return the idImputation
	 */
	public String getIdImputation() {
		return idImputation;
	}

	/**
	 * @param idImputation the idImputation to set
	 */
	public void setIdImputation(String idImputation) {
		this.idImputation = idImputation;
	}

	/**
	 * @return the peutEtreModifier
	 */
	public String getPeutEtreModifier() {
		return peutEtreModifier;
	}

	/**
	 * @param peutEtreModifier the peutEtreModifier to set
	 */
	public void setPeutEtreModifier(String peutEtreModifier) {
		this.peutEtreModifier = peutEtreModifier;
	}
	

}
