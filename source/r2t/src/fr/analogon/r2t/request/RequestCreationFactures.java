package fr.analogon.r2t.request;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.Arrondi;
import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Article;
import fr.analogon.r2t.pojo.Bareme;
import fr.analogon.r2t.pojo.Batch;
import fr.analogon.r2t.pojo.Facture;


public class RequestCreationFactures extends Request 
{	
	RequestBareme requestBareme = new RequestBareme();		
	RequestOuvrage requestOuvrage = new RequestOuvrage();
	RequestImputation requestImputation = new RequestImputation();	
	RequestParametres requestParametres = new RequestParametres();
	int idBatchTraitement;
	String dateLancementDuBatch = "";
	GestionDate gestionDate = new GestionDate();
		
	public int generFacture(String dateLancementDuBatch, String typeTaxe,
			String etapeFacturation, String timeTransaction, String anneeEx, String anneeFacturationTLPE) 
	{
		
		if(typeTaxe.equalsIgnoreCase("tlpe") )
		{			
			debug.logger.debug("anneeFacturationTLPE="+anneeFacturationTLPE);			
		}
			
		String anneeCourante = GestionDate.getAnneeFromDateString(dateLancementDuBatch);
		String ville = requestParametres.getVille();
		this.dateLancementDuBatch = dateLancementDuBatch;
		String typeFacturation = "";
		String minimumPerception = "0";
		String minimumDeFacturation = "0";
		//Le cycle de facturation est pour Toulon il peuvent facture en Trimestre
		//Alors que le bareme est en mois ou en semaine comme les marches
		String cycleFacturation = "";	
		boolean nouvelleFacture = true;
		Statement instruction0 = null ;	
		int idFacture = 0 ;		
		
		debug.logger.debug(" \n\n\n\n\n\n\n");			
		debug.logger.debug("##################################################");
		debug.logger.debug("LANCEMENT DE BATCH TYPE " + typeTaxe + " LE "	+ GestionDate.getDateTime());
		debug.logger.debug("etapeFacturation="+etapeFacturation);
		debug.logger.debug("##################################################");
		try
		{
			instruction0 = con.connexion.createStatement();
			try 
			{
				new RequestAdmin().InsertAction("Lancement du batch type "+ typeTaxe, GestionDate.getDateTime());
				//////////////////////////////////////////////////////////////////////////////////////
				// Requete pour recuperer le type de facturation qui depend du type de taxe +annee
				/////////////////////////////////////////////////////////////////////////////////////
				String r0 = " SELECT typeFacturation, cycleFacturation FROM imputation"
					+ " WHERE imputation.libelle ='" + typeTaxe	+ "'"
					+ " AND imputation.anneeExercice=" + anneeEx;
				ResultSet resultat0 = instruction0.executeQuery(r0);
				while (resultat0.next()) 
				{
					typeFacturation = resultat0.getString("typeFacturation");
					cycleFacturation = resultat0.getString("cycleFacturation");
				}
				debug.logger.debug("Le type de facturation est " + typeFacturation + " pour le type de taxe " + typeTaxe+ "......................................");
				String dateCreation = GestionDate.getDateAujourdhuiString();
				String enteteFichierPleaide = "";
				ajouterligneBatchTraitement(dateCreation + " - "+ GestionDate.getTimeNow(), anneeEx, typeTaxe,	etapeFacturation);
				this.idBatchTraitement = getLastBatchTraitement();			
			
				/////////////////////////////////////////////////////////////////////////////////////
				//TLPE Module remboursement du a un changement de surface dans N-1
				//Ce module n'est pas lance pour Toulon 
				/////////////////////////////////////////////////////////////////////////////////////
				if ( typeTaxe.equalsIgnoreCase("TLPE")  
						&& ! ville.equalsIgnoreCase("toulon")						
						&& etapeFacturation.equalsIgnoreCase("facturation")
							
					)
				{
					remboursement(idBatchTraitement, anneeCourante,typeTaxe,etapeFacturation,anneeFacturationTLPE);
				}
				//FIN Module remboursement
				/////////////////////////////////////////////////////////////////////////////////////
				
				
				RequestBareme reqBareme = new RequestBareme();			
				//La premier fois (i=0) pour les ouvrages qui proviennt de l'application
				//La deuxieme fois (i=1) pour les ouvrages qui proviennt d'une refacturation
			
				int debut = 0;
				String etatFacture = "";
				int nombreIteration = 2;			
				// Cas d'une preRefacturation (visualistion de la facture pour une refacturation )
				if (etapeFacturation.equalsIgnoreCase("preRefacturation")) 
				{
					debut = 1;
					enteteFichierPleaide = "BR-";
				}		
				if (etapeFacturation.equalsIgnoreCase("preFacturation"))
					nombreIteration = 1;
			
				//Parcours des iterations
				for (int i = debut; i < nombreIteration; i++)
				{
					enteteFichierPleaide = "BN-";
					if (i == 0) 
					{
						// Facturation des elements pour les factures normales
						debug.logger.debug("*******************************************************************");
						debug.logger.debug("***Selection des elements pour les nouvelles factures normales*****");
						debug.logger.debug("*******************************************************************");
						etatFacture = "Valide";
					} 
					else
					{
						debug.logger.debug("**************************************************************************");
						debug.logger.debug("***Selection des elements pour les factures suite a une refacturation*******");
						debug.logger.debug("**************************************************************************");
						etatFacture = "Refacture";
					}
					
					if (etapeFacturation.equalsIgnoreCase("preFacturation"))
						etatFacture = "preFacturation";
			
					int numRedevable = 0;
					int numRedTmp = 0;
					int numEmplacment = 0;
					int numEmplacmentTmp = 0;
					idFacture = genererNumeroFacture(anneeEx);
					if (etapeFacturation.equalsIgnoreCase("preRefacturation")) 
					{
						idFacture = genererNumeroFacture(anneeEx);
						etatFacture = "preRefacturation";
					}
					
					String dateFinPeriode = "";			
					String stQuantiteTotal = "";
					String dateDebutPeriodeTmp = "";
					int numeroDePeriode = 0;
					int nombreDePeriodeAFacturer = 1;
					double montantTotalFacture = 0.00;
					ResultSet resultat1 = null;
			
					// ////////////////////////////////////////////////////////////////////////////////////
					// Requete pour selectionner les ouvrages a facurer
					// ///////////////////////////////////////////////////////////////////////////////////
					Statement instruction1 = con.connexion.createStatement();					
					String r1 = " SELECT emplacementgeneral.numRedevable, "
							+ "	emplacementgeneral.numero as numEmplacement , "
							+ "	elementfacturation.numeroEmplacement, "
							+ " article.typeOuvrage, "
							+ " article.id_article, "
							+ " article.codebareme , "
							+ " article.surface , "
							+ " article.largeur , "
							+ " article.longueur , "
							+ " article.nombreFaceAffiche, "
							+ " article.quantite_article , "
							+ " article.dernierePeriodeFacture , "
							+ " article.anexercice , "
							+ " article.source , "
							+ " article.dateDernierControl , "
							+ " article.DateDebutAutorisation, "
							+ " article.dateFinAutorisation, "
							+ " bareme.prixUnit, "
							+ " bareme.coefficientNumerique, "
							+ " bareme.typeArrondi, "
							+ " bareme.prorata, "
							+ " bareme.libelle as libelleBareme, "
							+ " bareme.periodicite, "
							+ " bareme.uniteDeTravail , "
							+ " bareme.imputationBareme, "
							+ " bareme.sectionBareme, "
							+ " imputation.necessiteControleTerrain , "
							+ " bareme.dureeunitaire , "
							+ " imputation.idImputation , "
							+ " imputation.libelle as libelleImputation, "
							+ " imputation.minimumPerception , "
							+ " imputation.minimumDeFacturation , "			
							+ " article.largeur * article.longueur * article.quantite_article as quantite "
							+ " FROM  article,elementfacturation, bareme,emplacementgeneral,imputation ";
					
					r1 = r1
							+ " WHERE  ( article.etat ='ControlerAFacturer' OR article.etat ='AFacturer' OR article.etat ='Rembourser' "
							+ "OR article.etat ='ControlerPresent' ) "
							+ " AND article.id_elementfacturation = elementfacturation.numero "
							+ " AND elementfacturation.numeroEmplacement = emplacementgeneral.numero "
							+ " AND bareme.idImputation = imputation.idImputation "
							+ " AND article.codebareme = bareme.code "
							+ " AND imputation.anneeExercice=bareme.anExercice`"
							+ " AND imputation.libelle ='" + typeTaxe + "'";
					
				
					//Cas de la TLPE, c la date de anneeFacturationTLPE qui est utulisé pour les condtions
					if (typeTaxe.equalsIgnoreCase("TLPE") && i == 0) 
					{
						r1 = r1	+ " AND STR_TO_DATE(article.DateDebutAutorisation, '%d/%m/%Y')"
								+ " <= STR_TO_DATE('31/12/" + anneeFacturationTLPE
								+ "', '%d/%m/%Y') ";
					}
					//Cas ou on facture jusqu a la date de lancement du batch
					else if (i == 0  && !cycleFacturation.toLowerCase().contains("avance"))
					{
							r1 = r1 + " AND STR_TO_DATE(article.DateDebutAutorisation, '%d/%m/%Y')"
									+ " <= STR_TO_DATE('" + dateLancementDuBatch
									+ "', '%d/%m/%Y') ";
					}			
					//Cas des TAV facturation que si DateDebutAutorisation <= 01/01/AnneeEnCours
					if (typeTaxe.equalsIgnoreCase("TAV") && i == 0) 
					{
						r1 = r1
								+ " AND STR_TO_DATE(article.DateDebutAutorisation, '%d/%m/%Y')"
								+ " <= STR_TO_DATE('01/01/" + anneeEx
								+ "', '%d/%m/%Y') ";
					}			
					
					//On ajoute une condition pour selectionner
					//que les elements qui proviennt d'une refacturation					
					//Cas de facturation normale
					if (i == 0) 
					{
						r1 = r1 + " AND article.source ='normal'  AND `bareme`.`anExercice`=" + anneeEx;
					}
					//Cas de refacturation
					else if (i == 1) 
					{
						r1 = r1 + " AND bareme.anExercice=article.anexercice ";			
						if (etapeFacturation.equalsIgnoreCase("preRefacturation")) 
						{
							r1 = r1 + " AND article.source='preRefacturation'"
								+ " AND article.idTransaction="	+ timeTransaction;
						}
						else if (etapeFacturation.equalsIgnoreCase("refacturation")
								|| etapeFacturation.equalsIgnoreCase("facturation")) 
						{
							r1 = r1 + " AND article.source='refacturation' ";			
						}
					}
			
					// Ordonner le resultat soit par empalacement ou par redevable
					if (typeFacturation.equalsIgnoreCase("ParRedevable"))
						r1 = r1 + " Order by emplacementgeneral.numRedevable ";
					else if (typeFacturation.equalsIgnoreCase("ParEmplacement"))
						r1 = r1 + " Order by emplacementgeneral.numero ";
			
					debug.logger.debug("**************************************");
					debug.logger.debug("Requete pour la selection des ouvrages a facturer \n"+ r1);
					debug.logger.debug("**************************************");
					resultat1 = instruction1.executeQuery(r1);
					int coeffisienTypeNumerique = 1;
			
					// ////////////////////////////////////////////////////////////////////////////////////
					// Le resultat est ordonné par redevable ou par emplacement
					// (donc on va creer les factures une par une)
					// ///////////////////////////////////////////////////////////////////////////////////
					while (resultat1.next()) 
					{
						coeffisienTypeNumerique = resultat1.getInt("coefficientNumerique");
						String libelleBareme = resultat1.getString("libelleBareme");
						String typeOuvrageArticle = resultat1.getString("typeOuvrage");
						int id_article = resultat1.getInt("id_article");						
						int codeBareme = resultat1.getInt("codebareme");
						int dernierePeriodeFacture = resultat1.getInt("dernierePeriodeFacture");
						double quantiteTotal = 0.00;
						double nombreFaceAffiche = resultat1.getDouble("nombreFaceAffiche");
						double largeurArticle = resultat1.getDouble("largeur");
						double longeurArticle = resultat1.getDouble("longueur");
						double surfaceArticle = resultat1.getDouble("surface");
						double quantiteArticle = resultat1.getDouble("quantite_article");						
						double prixBareme = resultat1.getDouble("prixUnit");
						String numeroPretitre = genererNumeroPretitre(dateLancementDuBatch, nouvelleFacture);
						nouvelleFacture = false;
						minimumPerception = resultat1.getString("minimumPerception");
						minimumDeFacturation= resultat1.getString("minimumDeFacturation");						
						String dureeunitaire = resultat1.getString("dureeunitaire");
						String uniteDeTravail = resultat1.getString("uniteDeTravail");
						String typeArrondi = resultat1.getString("typeArrondi");
						String utiliserProrata = resultat1.getString("prorata");
						String debutPeriode = resultat1.getString("DateDebutAutorisation");
						String source = resultat1.getString("source");
						String DateFinAutorisation = resultat1.getString("dateFinAutorisation");
						String DateDernierControl = resultat1.getString("dateDernierControl");
						String idImputation = ""+ resultat1.getInt("idImputation");
						String libelleImputation = resultat1.getString("libelleImputation");
						String imputationBareme = resultat1.getString("imputationBareme");
						String sectionBareme = resultat1.getString("sectionBareme");						
					
						//////////////////////////////////////////
						//INFORMATION POUR LA FACTURE
						//////////////////////////////////////////
						if (typeFacturation.equalsIgnoreCase("ParRedevable")) 
						{	
							//Premiere fois 
							if (numRedevable == 0) 
							{
								numRedevable = resultat1.getInt("numRedevable");
								numRedTmp = numRedevable;
								nouvelleFacture = true;
								ajouterFacture(idFacture, typeTaxe,	numRedevable, dateCreation, anneeEx,montantTotalFacture,
										idBatchTraitement,	numeroPretitre, enteteFichierPleaide,etatFacture,anneeFacturationTLPE);
							}
						}
						else if (typeFacturation.equalsIgnoreCase("ParEmplacement")) 
						{
							//Premiere fois 
							if (numEmplacment == 0) 
							{
								numEmplacment = resultat1.getInt("numEmplacement");
								numRedevable = resultat1.getInt("numRedevable");
								numEmplacmentTmp = numEmplacment;
								nouvelleFacture = true;
								ajouterFacture(idFacture, typeTaxe,	numRedevable, dateCreation, anneeEx,montantTotalFacture,
										idBatchTraitement,	numeroPretitre, enteteFichierPleaide,etatFacture,anneeFacturationTLPE);
							}
						}
						numRedevable = resultat1.getInt("numRedevable");
						numEmplacment = resultat1.getInt("numeroEmplacement");
						if (typeFacturation.equalsIgnoreCase("ParRedevable")) 
						{
							if (numRedTmp != numRedevable) 
							{
								debug.logger.debug("Nouveau redevable");
								numRedTmp = numRedevable;
								numeroPretitre = this.getNumeroDePretireMemeBatch(numeroPretitre);
								montantTotalFacture = getMontantTotalFacture(idFacture);								
								majFacture(idFacture, montantTotalFacture,	minimumPerception);								
								// verfier si la facture precedante 
								//n a pas de ligne de facture, on l a supprime
								verfierSiLigneFactureDansUneFacture(""+idFacture);
					
								// initilalisation des nouveaux parametre
								idFacture = genererNumeroFacture(anneeEx);
								montantTotalFacture = 0.00;
								nouvelleFacture = true;
								ajouterFacture(idFacture, typeTaxe,numRedevable, dateCreation, anneeEx,montantTotalFacture,
										idBatchTraitement,numeroPretitre, enteteFichierPleaide,	etatFacture,anneeFacturationTLPE);			
							}
						} 
						else if (typeFacturation.equalsIgnoreCase("ParEmplacement")) 
						{
							if (numEmplacmentTmp != numEmplacment) 
							{
								debug.logger.debug("Nouveau Emplacement");
								numEmplacmentTmp = numEmplacment;
								numeroPretitre = this.getNumeroDePretireMemeBatch(numeroPretitre);
								montantTotalFacture = getMontantTotalFacture(idFacture);
								majFacture(idFacture, montantTotalFacture,	minimumPerception);
								// verfier si la facture precedante n a pas de ligne
								// de facture, on l a supprime
								verfierSiLigneFactureDansUneFacture(""+idFacture);
					
								// initilalisation des nouveaux parametre
								idFacture = genererNumeroFacture(anneeEx);
								montantTotalFacture = 0.00;
								nouvelleFacture = true;
								ajouterFacture(idFacture, typeTaxe,	numRedevable, dateCreation, anneeEx,montantTotalFacture, 
										idBatchTraitement,numeroPretitre, enteteFichierPleaide,	etatFacture,anneeFacturationTLPE);
							}
						}
					
						
						//////////////////////////////////////////
						//CALCUL DE LA QUANTITE A FACTURER :
						//////////////////////////////////////////
						stQuantiteTotal= getQuantiteTotal(typeArrondi, longeurArticle, largeurArticle,
								surfaceArticle, quantiteArticle);
						quantiteTotal = Double.valueOf(stQuantiteTotal);					
						
						//////////////////////////////////////////
						//CALCUL DU NOMBRE DE PERIODE A FACURER :
						//////////////////////////////////////////
						String dateFin = dateLancementDuBatch;
						//Si tlpe on facture jusqu a la fin de l'annee passe en prametre
						if (typeTaxe.equalsIgnoreCase("tlpe"))
							dateFin = "31/12/" + anneeFacturationTLPE ;
						
						if (typeTaxe.equalsIgnoreCase("DDV") && DateDernierControl.length() != 0)
							dateFin = DateDernierControl;					
					
						debug.logger.debug("cycleFacturation ="		+ cycleFacturation);
						debug.logger.debug("DateFinAutorisation ="	+ DateFinAutorisation);
						
						//Facturation en avance 
						if (cycleFacturation.toLowerCase().contains("avance")) 
						{
							// Calcul de la date de fin selon le cycle de facturation
							// Si mensuelle et en avance, on calcule la date de fin du mois
							GestionDate gd = new GestionDate();	
							debug.logger.debug("Facturation en avance ");			
							if (cycleFacturation.toLowerCase().contains("mensuelle")) 
							{								
								debug.logger.debug("Facturation en avance mensuelle ");
								dateFin = gd.getDernierJOurDuMois(dateLancementDuBatch);
								if (DateFinAutorisation != null && DateFinAutorisation.length() !=0 && gd.comparerDate(DateFinAutorisation, dateFin) == 1)
									dateFinPeriode = DateFinAutorisation;								
							}
							//Si trimestrielle et en avance, on calcule la date de fin du trimestre
							else if (cycleFacturation.toLowerCase().contains("trimestrielle")) 
							{
								debug.logger.debug("Facturation en avance trimestrielle ");
								dateFin = gd.getDernierJOurDuTrimestre(dateLancementDuBatch);																
								//si la date de finautorisation < date de fin 
								if (DateFinAutorisation != null && DateFinAutorisation.length() !=0 && gd.comparerDate(DateFinAutorisation, dateFin) == 1)
									dateFinPeriode = dateFin;
							}
							//Si annuelle et en avance, on calcule au 31/12/annee
							else if (cycleFacturation.toLowerCase().contains("annuelle")) 
							{
								debug.logger.debug("Facturation en avance annuelle ");
								dateFin = "31/12/"	+ gd.getAnneeFromDateString(dateLancementDuBatch);
								if (DateFinAutorisation != null && DateFinAutorisation.length() !=0 && gd.comparerDate(DateFinAutorisation, dateFin) == 1)
									dateFinPeriode = dateFin;	
							}
						}
					
					
						// Verification si date de fin existe on l'utilise
						// Sinon on utulise la date de lancecment du batch
						if(typeTaxe.equalsIgnoreCase("TLPE") && DateFinAutorisation != null && DateFinAutorisation.length() != 0)
						{
							String anneeFinAutoirsation = GestionDate.getAnneeFromDateString(DateFinAutorisation);
							if(Integer.parseInt(anneeFinAutoirsation) < Integer.parseInt(anneeCourante))
							{
								dateFinPeriode = DateFinAutorisation;
								dateFin = DateFinAutorisation;
							}
						}
						else if (DateFinAutorisation != null && DateFinAutorisation.length() != 0) 
						{
							dateFinPeriode = DateFinAutorisation;
							dateFin = DateFinAutorisation;
						}											
						debug.logger.debug("Date de fin facturation =" + dateFin);
					
						
						//////////////////////////////////////////
						//CALCUL DU NOMBRE DE PERIODE A FACURER :
						//////////////////////////////////////////					
						if (dureeunitaire.equalsIgnoreCase("JOUR")) 
						{
							numeroDePeriode = GestionDate.getNombreDeJour(debutPeriode, dateFin);
							nombreDePeriodeAFacturer = numeroDePeriode	- dernierePeriodeFacture;
						}
						else if (dureeunitaire.equalsIgnoreCase("SEMAINE")) 
						{
							numeroDePeriode = GestionDate.getNombreDeSemaine(debutPeriode, dateFin);
							nombreDePeriodeAFacturer = numeroDePeriode	- dernierePeriodeFacture;
						}
						else if (dureeunitaire.equalsIgnoreCase("MOIS")) 
						{
							numeroDePeriode = GestionDate.getNombreDeMois( debutPeriode, dateFin);
							nombreDePeriodeAFacturer = numeroDePeriode	- dernierePeriodeFacture;
						} 
						else if (dureeunitaire.equalsIgnoreCase("TRIMESTRE")) 
						{
							numeroDePeriode = GestionDate.getNombreDeTrimestre(	debutPeriode, dateFin);
							nombreDePeriodeAFacturer = numeroDePeriode	- dernierePeriodeFacture;
						}
						else if (dureeunitaire.equalsIgnoreCase("AN")) 
						{
							numeroDePeriode = GestionDate.getNombreDannee(	debutPeriode, dateFin);
							nombreDePeriodeAFacturer = numeroDePeriode	- dernierePeriodeFacture;
						} 
						else 
						{
							nombreDePeriodeAFacturer = 1;
						}
					
						
						if (source.equalsIgnoreCase("normal") && typeTaxe.equalsIgnoreCase("TAV")) 
						{
							// On ne fait le -1 que si la date d'instalation <= 01/01/AnneEnCours
							if (!debutPeriode.contains("01/01/")) 
							{
								numeroDePeriode = numeroDePeriode - 1;
								nombreDePeriodeAFacturer = nombreDePeriodeAFacturer - 1;
							}
						}
					
						////////////////////////////////////////////////////
						// FACTURATION DES OUVRAGES DANS DES ANCIENNE PERIODE
						////////////////////////////////////////////////////
						int numeroPeriodeTmp = 0;
						double montantLigne = 0;
						boolean semaineDejaCalcule = false;
						debug.logger.debug("Facturation de l'aticle " + id_article);
						debug.logger.debug("dernierePeriodeFacture="+ dernierePeriodeFacture);
						debug.logger.debug("nombreDePeriodeAFacturer="+ nombreDePeriodeAFacturer);
					
						if (!typeTaxe.equalsIgnoreCase("TLPE")) 
						{
							// Parcours de periode a facture
							for (int j = 1; j <= nombreDePeriodeAFacturer; j++) 
							{
								debug.logger.debug("Facturation de la periode "	+ j + " pour l'aticle " + id_article);
								///////////////////////////////////////////////
								//CAS OU LE BAREME EST DU TYPE FORFAIT
								///////////////////////////////////////////////
								if (uniteDeTravail.equalsIgnoreCase("FORFAIT")) 
								{
									numeroPeriodeTmp = 1;
									numeroDePeriode = 1;
									//Pour Toulon, si le type = forfait on affiche la date debut et fin 
									if(ville.equalsIgnoreCase("toulon"))
									{										
										if(debutPeriode != null && debutPeriode.length() != 0
											&& DateFinAutorisation != null && DateFinAutorisation.length() != 0 )
										{
											dateDebutPeriodeTmp=  debutPeriode ;
											dateFinPeriode = dateFin;											
										}																				
									}
									else
									{
										dateDebutPeriodeTmp = "0";									
										dateFinPeriode = "0";										
									}
									String anneeExerciceTmp = GestionDate.getAnneeFromDateString(debutPeriode);
									prixBareme = reqBareme.getPrixPeriode(codeBareme, numeroPeriodeTmp,	anneeExerciceTmp);
									montantLigne = prixBareme * quantiteTotal;	
								}
								///////////////////////////////////////////////
								//CAS OU LE BAREME N'EST PAS DU TYPE FORFAIT
								///////////////////////////////////////////////
								else 
								{
									numeroPeriodeTmp = dernierePeriodeFacture+ j;
									debug.logger.debug(">>>numeroPeriodeTmp="+ numeroPeriodeTmp);					
									
									// /////////////////////////////////////////////
									// CAS OU LE BAREME EST DU TYPE PAR/AN
									// PEUT AVOIR PRORATA ET PERIDICITE)
									// /////////////////////////////////////////////
									if (dureeunitaire.equalsIgnoreCase("AN")) 
									{
										// Cas du premier tours
										dateDebutPeriodeTmp = debutPeriode;
										int annee = Integer.parseInt(GestionDate.getAnneeFromDateString(debutPeriode))+ numeroPeriodeTmp - 1;
										// POUR LES TAV ON FACTURE L'ANNE SUIVANTE AVEC LES PRIX DU BAREME DE L'ANNE SUIVANTE
										if (typeTaxe.equalsIgnoreCase("TAV")) 
										{
											annee = Integer.parseInt(GestionDate.getAnneeFromDateString(debutPeriode))	+ numeroPeriodeTmp;
											// Si l'ouvrage est present au 01/01 de l'anne on facture cette annee
											if (debutPeriode.contains("01/01/"))
												annee = annee - 1;
											dateDebutPeriodeTmp = "01/01/" + annee;
										}
										else if (numeroPeriodeTmp > 1) 
										{
											// Pour les anne suivante de la premier anne d'instalation
											// Cas ou un ouvrage a commence en 01/09/2008 et on lance le batch en 2009
											// Alors il y a factuarion de 01/09/2008 au 31/12/2008
											// ET il y a une facturation de 01/01/2009 au 31/12/2009
											dateDebutPeriodeTmp = "01/01/"	+ annee;
										}
					
										String anneeExerciceTmp = GestionDate.getAnneeFromDateString(dateDebutPeriodeTmp);
										// Recuperer le bon prix du bareme
										prixBareme = reqBareme.getPrixPeriode(	codeBareme, numeroPeriodeTmp,anneeExerciceTmp);
					
										// Fin 31/12/AAAA que pour les TAV , les autre annuel :
										// Publicite recuperer la date de fin de la BD										
										dateFinPeriode = "31/12/" + GestionDate.getAnneeFromDateString(dateDebutPeriodeTmp);
										
										if (j == nombreDePeriodeAFacturer && DateFinAutorisation.length() != 0)
											dateFinPeriode = DateFinAutorisation;
					
										int nombreDeJour = GestionDate.getNombreDeJour(	dateDebutPeriodeTmp,dateFinPeriode);
					
										// POUR LES PUB
										if (utiliserProrata	.equalsIgnoreCase("true")&& dateFinPeriode.length() != 0) 
										{
											if (nombreDeJour < 367) 
											// on calcule avec proporata
											{
												if (GestionDate	.contient366Jour(anneeExerciceTmp))
													montantLigne = prixBareme	* Double.valueOf(stQuantiteTotal)	* nombreDeJour	/ 366;
												else
													montantLigne = prixBareme * Double.valueOf(stQuantiteTotal)	* nombreDeJour	/ 365;
											}
										} 
										else 
											montantLigne = prixBareme* quantiteTotal;										
									}//Fin annuelle
									
									/////////////////////////////////////////////////
									//CAS OU LE BAREME EST PAR /TRIMESTRE
									////////////////////////////////////////////////////
									else if (dureeunitaire.equalsIgnoreCase("TRIMESTRE")) 
									{					
										dateDebutPeriodeTmp = GestionDate.ajouterNombreTrimestre("DDebut",debutPeriode,	j+ dernierePeriodeFacture);
										dateFinPeriode = gestionDate.getDernierJOurDuTrimestre(dateDebutPeriodeTmp);						
										if (DateFinAutorisation != null && DateFinAutorisation.length()!= 0 
												&& gestionDate.comparerDate(DateFinAutorisation, dateFinPeriode) == 1)
										{						
											dateFinPeriode = DateFinAutorisation;
										}						
										debug.logger.debug("DateFinAutorisation="	+ DateFinAutorisation);
										debug.logger.debug("dateFinPeriode="+ dateFinPeriode);
										int res = 0;
										if ( DateFinAutorisation.length() != 0	&& dateFinPeriode.length() != 0)
											res = GestionDate.comparerDate(DateFinAutorisation,dateFinPeriode);
										if (res == 1)
											dateFinPeriode = DateFinAutorisation;	
										prixBareme = reqBareme.getPrixPeriode(codeBareme,numeroPeriodeTmp,GestionDate.getAnneeFromDateString(dateDebutPeriodeTmp));
										debug.logger.debug("utiliserProrata="+ utiliserProrata);
										if (utiliserProrata	.equalsIgnoreCase("true") ) 
										{
											int nombreDeJour = GestionDate.getNombreDeJour(dateDebutPeriodeTmp,							dateFinPeriode);
											debug.logger.debug("nombreDeJour a facturer="	+ nombreDeJour);	
											// A culculer	
											int nombreDeJourDuTrimestre = gestionDate.getNombreDeJourDuTrimestre(dateDebutPeriodeTmp);
											montantLigne = prixBareme * Double.valueOf(stQuantiteTotal)	* nombreDeJour	/ nombreDeJourDuTrimestre;
											debug.logger.debug("montantLigne="	+ montantLigne);	
										} 
										else 
										{
											montantLigne = prixBareme* quantiteTotal;
										}
									}//fin par trimestre
					
									/////////////////////////////////////////////////
									// CAS OU LE BAREME EST PAR /MOIS
									////////////////////////////////////////////////////
									else if (dureeunitaire.equalsIgnoreCase("MOIS")) 
									{
										dateDebutPeriodeTmp = GestionDate.ajouterNombreMois("DDebut",	debutPeriode,j				+ dernierePeriodeFacture	- 1);
										dateFinPeriode = GestionDate.ajouterNombreMois(	"DFin",	debutPeriode,j+ dernierePeriodeFacture);
										if (DateFinAutorisation != null && DateFinAutorisation.length()!= 0 
												&& gestionDate.comparerDate(DateFinAutorisation, dateFinPeriode) == 1)
										{						
											dateFinPeriode = DateFinAutorisation;
										}
										
										debug.logger.debug("utiliserProrata="+ utiliserProrata);
										if (utiliserProrata	.equalsIgnoreCase("true")) 
										{
											int nombreDeJour = GestionDate.getNombreDeJour(dateDebutPeriodeTmp,	dateFinPeriode);
											debug.logger.debug("nombreDeJour a facturer="	+ nombreDeJour);								
											int nombreDeJourDuTrimestre = gestionDate.getNombreDeJourDuMois(dateDebutPeriodeTmp);
											montantLigne = prixBareme * Double.valueOf(stQuantiteTotal)	* nombreDeJour	/ nombreDeJourDuTrimestre;
											debug.logger.debug("montantLigne="	+ montantLigne);	
										} 
										else
										{
											prixBareme = reqBareme.getPrixPeriode(codeBareme,numeroPeriodeTmp,GestionDate	.getAnneeFromDateString(dateDebutPeriodeTmp));
											montantLigne = prixBareme	* quantiteTotal;
										}
									}//fin par mois 
					
									// ///////////////////////////////////////////////
									// CAS OU LE BAREME EST PAR /SEMAINE
									// //////////////////////////////////////////////////
									else if (dureeunitaire	.equalsIgnoreCase("SEMAINE")) 
									{
										if (semaineDejaCalcule == true) 
										{
											dateDebutPeriodeTmp = GestionDate.addaDay(dateFinPeriode);
											debug.logger.debug("utulisation date Fin periode precdeante");
										} 
										else 
										{
											dateDebutPeriodeTmp = GestionDate.ajouterNombreSemaineComplet(debutPeriode,	numeroPeriodeTmp - 1);
											semaineDejaCalcule = true;
											debug.logger.debug("utulisation date normale");
										}
					
										dateFinPeriode = GestionDate.ajouterNombreSemaine(dateDebutPeriodeTmp, 1);
										prixBareme = reqBareme.getPrixPeriode(codeBareme,numeroPeriodeTmp,GestionDate.getAnneeFromDateString(dateDebutPeriodeTmp));
										montantLigne = prixBareme	* quantiteTotal;
										if (  DateFinAutorisation != null && DateFinAutorisation.length()!= 0 
												&& gestionDate.comparerDate(DateFinAutorisation, dateFinPeriode) == 1)
										{
											//Si on utulise le prorat dans semaine 
											//on prend en compte la date de fin d'autorisation
											//sinon on affiche sur la facture la fin de la semaine 
											if(utiliserProrata.equalsIgnoreCase("true"))
											{
												dateFinPeriode = DateFinAutorisation;
												int nbreJourTmp = GestionDate.getNombreDeJour(dateDebutPeriodeTmp, DateFinAutorisation);
												montantLigne = prixBareme	* quantiteTotal * nbreJourTmp/7;
												montantLigne = FonctionCommun.arrondir(montantLigne);
											}
										}	
												
									}//fin par semaine 
					
									// ///////////////////////////////////////////////
									// CAS OU LE BAREME EST PAR /JOUR
									// //////////////////////////////////////////////////
									else if (dureeunitaire.equalsIgnoreCase("JOUR")) 
									{
										dateDebutPeriodeTmp = GestionDate.ajouterNombreJour(debutPeriode,numeroPeriodeTmp - 1);
										dateFinPeriode = GestionDate.ajouterNombreJour(	dateDebutPeriodeTmp, 0);
										if (j == nombreDePeriodeAFacturer	&& DateFinAutorisation.length() != 0)
											dateFinPeriode = DateFinAutorisation;
										//toto
										if (  DateFinAutorisation != null && DateFinAutorisation.length()!= 0 
												&& gestionDate.comparerDate(DateFinAutorisation, dateFinPeriode) == 1	)
										{						
											dateFinPeriode = DateFinAutorisation;
										}
										prixBareme = reqBareme.getPrixPeriode(codeBareme,numeroPeriodeTmp,GestionDate.getAnneeFromDateString(dateDebutPeriodeTmp));
										montantLigne = prixBareme* quantiteTotal;
									}//finpar jour
								}// fin else si c pas un forfait
					
								
								////////////////////////////////////
								//AJOUT DE LA LIGNE DE FACTURE
								////////////////////////////////////								
								debug.logger.debug("Date debut de periode "	+ dateDebutPeriodeTmp);
								debug.logger.debug("Date fin de periode " + dateFinPeriode);
								if ( dateDebutPeriodeTmp != null && dateFinPeriode != null &&
									 dateDebutPeriodeTmp.length()==10 && dateFinPeriode.length() == 10 &&
										Integer.parseInt(GestionDate.getAnneeFromDateString(dateDebutPeriodeTmp))
										> Integer.parseInt(GestionDate.getAnneeFromDateString(dateFinPeriode))
									)
								{
									String anneetmpDebut=  GestionDate.getAnneeFromDateString(dateDebutPeriodeTmp);
									dateFinPeriode = dateFinPeriode.substring(0, 6)+ anneetmpDebut;
								}
								ajouterligneFacture(id_article,	numeroPeriodeTmp, idFacture,
										dateDebutPeriodeTmp, dateFinPeriode, montantLigne, prixBareme,
										quantiteTotal, "" + codeBareme,	libelleBareme, imputationBareme,
										sectionBareme, uniteDeTravail, dureeunitaire, anneeEx, idImputation,
										libelleImputation,"normal");
					
							}// FIN BOUCLE-FOR (parcours des de nombre de periode a facturer)
						}// Fin de verfication de condition sur le type de taxe , tous sauf la TLPE
										
						
						//////////////////////////
						// Faturation de la TLPE
						//////////////////////////
						else 
						{
							debug.logger.debug("\n\n");
							debug.logger.debug("/////////////////////////////////////////////////////////////////////");
							debug.logger.debug("/////////TLPE - Faturation de l'ouvrage "+id_article+"///////////////");
							debug.logger.debug("/////////////////////////////////////////////////////////////////////");
							// Parcours de periode a facture							
							for (int j = 1; j <= nombreDePeriodeAFacturer; j++) 
							{
								///////////////////////////////
								//Traitement des dates								
								//Reglage des dates debut et fin:
								//Date de debut : Facturer un mois apres la date d'instlation
								//Date de fin : Facturer jusqu a la fin du mois 
								///////////////////////////////
								int numeroDeLaPeriodeAFacturer = dernierePeriodeFacture + j;
								debug.logger.debug("Facturation de la periode "	+ numeroDeLaPeriodeAFacturer + " pour l'aticle " + id_article);
								debug.logger.debug("Debut instalation  "	 + debutPeriode);
								debug.logger.debug("date de fin autorisation " + dateFinPeriode);
								debug.logger.debug("dernierePeriodeFacture="+ dernierePeriodeFacture);								
								dateDebutPeriodeTmp = debutPeriode;
								dateFinPeriode = DateFinAutorisation;																
								//Annne de facturation qui sera un parametre du batch = anneEx		
								//format MM/AAAA
								String newDateDebutPeriodeTmp = "";
								String newDateFinPeriodeTmp = "";								
								//Calcul de l'annne de facturation																
								String anneeTmp = String.valueOf(
												  Integer.parseInt(GestionDate.getAnneeFromDateString(debutPeriode)) 
										          + numeroDeLaPeriodeAFacturer -1) ;														
								
								debug.logger.debug("Annee de facturation="+ anneeTmp);
								
								//Calcul de la date de debut format MM/AAAA
								if(numeroDeLaPeriodeAFacturer ==1 )
								{
									//L'artcile n'est jamais facture avant
									//si l'article est installe apres le 01/01/AAAA , on facture le mois suivant 
									debug.logger.debug("Facturation de la premiere fois ");					
									newDateDebutPeriodeTmp = "01/"+ anneeTmp;
									if(!debutPeriode.contains("01/01"))
									{						
										int moisDebut = Integer.parseInt(GestionDate.getMoisFromDateString(dateDebutPeriodeTmp));
										//On ajoute un mois que si le mois different de 12
										if(moisDebut != 12 )
											dateDebutPeriodeTmp = GestionDate.ajouterNombreMoisComplet(dateDebutPeriodeTmp, 1);
											
										String moisTmp =  GestionDate.getMoisFromDateString(dateDebutPeriodeTmp);
										newDateDebutPeriodeTmp = moisTmp +"/"+ anneeTmp;
									}									
								}
								else
								{								
									//l'artcile est deja facture une fois dc on renouvelle a partir du 01/AAAA
									newDateDebutPeriodeTmp = "01/"+ anneeTmp;
								}
								
								//Calcul de la date de fin format MM/AAAA
								//soit la date de fin n'est indique ou la date de fin > annee de facturation
								if (DateFinAutorisation == null	|| DateFinAutorisation.length() == 0 ) 
								{
									newDateFinPeriodeTmp = "12/"+anneeTmp;
								} 
								else 
								//la date de fin est indique	
								{									
									if (GestionDate.getAnneeFromDateString(DateFinAutorisation).equalsIgnoreCase(anneeTmp))
									{										
										newDateFinPeriodeTmp = GestionDate.getMoisFromDateString(DateFinAutorisation)	+ "/"+ anneeTmp;
									}
									else
										newDateFinPeriodeTmp = "12/"+anneeTmp;																				
								}
								
								debug.logger.debug("newDateDebutPeriodeTmp="+ newDateDebutPeriodeTmp);
								debug.logger.debug("newDateFinPeriodeTmp="+ newDateFinPeriodeTmp);
																
								
								//Verfier la cause de facturation
								String causeFacturation ="normal";
								if (  Integer.parseInt(anneeEx) > 
								      Integer.parseInt(GestionDate.getAnneeFromDateString("01/"+newDateDebutPeriodeTmp))
									)
								{
									causeFacturation ="rappel";
								}
								
								
								// calcul du nombre de mois a facturer
								String moisDebut = GestionDate.getMoisFromDateString("01/"+newDateDebutPeriodeTmp);
								String moisFin = GestionDate.getMoisFromDateString("01/"+newDateFinPeriodeTmp);
								int nombreMoisAFcturer = Integer.valueOf(moisFin)- Integer.valueOf(moisDebut) + 1;
																
								//////////////////////////////////////////////////////////								
								//determination du tarif a applique selon cette surface								
								//stQuantiteTotal = (Long *Larg * Qte -OU- Surface * Qte)
								///////////////////////////////////////////////////////////
								double surfaceTotalTaxeArticle = Double.valueOf(stQuantiteTotal);
								// Prendre en compte le nombre de face et le nomnbre d'affiche
								if (nombreFaceAffiche != 0)
								surfaceTotalTaxeArticle = surfaceTotalTaxeArticle* nombreFaceAffiche;
					
								
								//codeBareme
								//1-Calcul du montant par Ligne
								//2-Calcul de la surface total du meme type dans l'emplacement que pour les enseignes 
								//3-Caclul du tarif a appliquer selon la surface total								
								debug.logger.debug("libelleBareme=" + libelleBareme);
								double prixSelonSurface = 0;					
								
								//calcul de la somme de la surface total mois par mois pour tous les type d'ouvrages
								//le calcul mois par mois n'est applique que si il y a une variation de surface au cours de l'anne
								Article article = new Article();
								article.setLibelleImputation(typeTaxe);
								article.setNumEmplacment(""+numEmplacment);
								article.setId(""+id_article);
								article.setCodeBareme(""+codeBareme);		
								article.setLibelleBareme(libelleBareme);								
								article.setLibelleBareme(libelleBareme);
								article.setLibelleBareme(libelleBareme);
								article.setLargeur(largeurArticle);
								article.setLongueur(longeurArticle);
								article.setQuantite_article(quantiteArticle);				
								article.setNombreFaceAffiche(""+nombreFaceAffiche);
								article.setSurface(surfaceArticle);
								article.setTypeArrondi(typeArrondi);
								article.setTypeOuvrage(typeOuvrageArticle);
								montantLigne = (Double)getMontantLigne(article, anneeTmp, moisDebut, moisFin, 
										nombreMoisAFcturer,coeffisienTypeNumerique,"normal").elementAt(0);
								
								montantLigne = Double.valueOf(Arrondi.arrondir2Digit(montantLigne));
								ajouterligneFacture(id_article, numeroDePeriode,
										idFacture, newDateDebutPeriodeTmp, newDateFinPeriodeTmp, montantLigne,
										prixSelonSurface, surfaceTotalTaxeArticle,	"" + codeBareme, libelleBareme,
										imputationBareme, sectionBareme,uniteDeTravail, dureeunitaire, anneeTmp,
										idImputation, libelleImputation , causeFacturation);								
							}//FIN Boucle for : Parcours ds periode a facturer (periode=ligneFacture) 
						}//Fin else : cas de la facturation de la TLPE 						
						
						// Mise a jour que dans le cas de facturation reelle
						if (!etapeFacturation.equalsIgnoreCase("preFacturation"))
							majDernierePeriodeFactureEtDateProshainControl(	id_article, numeroDePeriode, anneeEx);
						
						montantTotalFacture = getMontantTotalFacture(idFacture);
						majFacture(idFacture, montantTotalFacture,minimumPerception);			
					  }// FIN BOUCLE-WHILE (parcours du resultat de la requete qui contient les ouvrage a facture)
					 
					//Verfication si la derniere facture est valorise a 0 , alors on la suuprime
					verfierSiLigneFactureDansUneFacture(""+idFacture);	
					}// Fin boucle for pour verifer les ouvrages
					
										
					//Verfication si la derniere facture est valorise a 0 , alors on la suuprime
					verfierSiLigneFactureDansUneFacture(""+idFacture);
					
						
					// ///////////////////////
					// Mise a jour dans batch
					// //////////////////////
					int nombreDeFactureCreeparBtch = 0;
					try 
					{
						Statement instruction = con.connexion.createStatement();
						String r2 = " SELECT * FROM facture WHERE idBatchTraitement= "	+ idBatchTraitement;
						ResultSet resultat2 = instruction.executeQuery(r2);
						
						///////////////////////////
						//Module Rappel :
						///////////////////////////
						if ( typeTaxe.equalsIgnoreCase("TLPE")  
								&& ! ville.equalsIgnoreCase("toulon")						
								&& (etapeFacturation.equalsIgnoreCase("facturation") 
										||
									etapeFacturation.equalsIgnoreCase("prefacturation")
									)
							)
						{
							
							debug.logger.debug(r2);
							while (resultat2.next()) 
							{
									int idClient = resultat2.getInt("idClient");
									int numeroFacture = resultat2.getInt("numeroFacture");			
									double montantFacture = Double.valueOf(resultat2.getString("montantTotal"));
									double montantRappel = ajouterRappelTLPEOuvrageDejaFacture(idClient, numeroFacture ,anneeEx, typeTaxe);
									debug.logger.debug("Mise a jour facture numero="+ numeroFacture);
									debug.logger.debug("Montant rappel ="+montantRappel);
									debug.logger.debug("Ancien montant facture ="+montantFacture);
									montantFacture = montantFacture + montantRappel ;
									debug.logger.debug("Mise a jour montant facture ="+montantFacture);
									majFacture(numeroFacture, montantFacture, minimumPerception  );							
							}
						}
						///////////////////////////
						//Fin module rappel
						/////////////////////////////
						
						validerBatch(idBatchTraitement);
						//Supprimer les factures si le montant de la facture est < minimum de facturation 
						supprimerFacturesSiInferieurAMinimumDeFacturation(idBatchTraitement, minimumDeFacturation);
						debug.logger.debug(r2);
						instruction = con.connexion.createStatement();	
						//mise a jour du nombre de facture cree par le batch 		
						resultat2 = instruction.executeQuery(r2);
						
						while (resultat2.next()) 
						{
							nombreDeFactureCreeparBtch ++ ;			
						}						
					}
					catch (Exception e) 
					{
						debug.logger.debug("\n\n\n Erreur REQ C Facture " + e.getMessage()+ e.getMessage() + e.getCause());
					}	
					try 
					{
						debug.logger.debug("nombreDeFactureCree="+nombreDeFactureCreeparBtch);
						majBatch("true", idBatchTraitement, nombreDeFactureCreeparBtch);
						
					} catch (Exception e) {
						// TODO: handle exception
					}
				} 
				catch (Exception e) 
				{
					debug.logger.debug("Erreur" + e.getMessage()+ e.getMessage() + e.getCause());
				}			
			}// FIN DU PREMIER TRY DE LA FONCTION
			catch (Exception e) 
			{
				debug.logger.debug("Erreur dans la generation des donnees de factures...........[Erreur]");
				debug.logger.fatal(e.getMessage());
				e.printStackTrace();
			}
		return idBatchTraitement;
	}//Fin Fonction de creation de facture 
		
	
	public void ajouterFacture(int numeroFacture, String typeTaxe,	int idClient, String dateCreation, String anneeEx,
							double montantTotal, int idBatchTraitement, String numeroPretitre,
							String enteteFichierPleaide, String etatFacture,String anneeFacturationTLPE) 
	{
		try 
		{
			Statement instruction = con.connexion.createStatement();
			// Avoir un numero de pretitre unique par facture
				String numeroPretitreUnique = verfierNumeroPretitrSiIlExistePourUneAutreFacture(
							numeroPretitre, dateLancementDuBatch);
			String r = "INSERT INTO facture "
						+ "(numeroFacture,source,dateCreation, typeTaxe , idClient"
						+ " , anneeEx , montantTotal,idBatchTraitement,numeroPretitre,etat,anneeTitre,anneeFacturationTLPE) "
						+ "VALUES ( "
						+ numeroFacture
						+ ",'"
						+ enteteFichierPleaide
						+ "','"
						+ dateCreation
						+ "','"
						+ typeTaxe
						+ "','"
						+ idClient
						+ "','"
						+ anneeEx
						+ "','"
						+ montantTotal
						+ "',"
						+ idBatchTraitement
						+ ",'"
						+ numeroPretitreUnique
						+ "','" + etatFacture + "','" + anneeEx + "'" + ",'"+anneeFacturationTLPE+"');";
			debug.logger.debug("Requete pour creer une facture:\n " + r);
			int result1 = instruction.executeUpdate(r);
			//Rappel des ouvrages qui sont deja facture mais il ya un changement de tarif 
			//du a la modification d'information apres facturation			
		}
		catch (Exception e) 
		{
			debug.logger.fatal(e.getMessage());
		}		
	}//Fin Fonction 
					
	public void ajouterligneFacture(int NumeroArticle, int numeroDePeriode,
				int idFacture, String debutPeriode, String finPeriode,
				double montantLigne, double prixBareme, double quantiteBareme,
				String codeBareme, String libelleBareme, String imputationBareme,
				String sectionBareme, String uniteDeTravailBareme,
				String dureeunitaireBareme, String anneeExerciceBareme,
				String idImputation, String libelleImputation , String causeFacturation)
	{
		
			try {
				Statement instruction = con.connexion.createStatement();
				libelleBareme = FonctionCommun.ajouterAntislash(libelleBareme);
				libelleImputation = FonctionCommun.ajouterAntislash(libelleImputation);
				
				try {
					String sPrixUnitaire = Arrondi.arrondir2Digit(prixBareme);
					String sMontantLigne = Arrondi.arrondir2Digit(montantLigne);
					String req = "INSERT INTO lignefacture "
			+ "(idArticle, numeroPeriode , idFacture, debutPeriode, "
			+ " finPeriode ,montantLigne,prixUnitaire,quantite,"
			+ " codeBareme ,libelleBareme,imputationBareme,sectionBareme,uniteDeTravailBareme,"
			+ " dureeunitaireBareme,anneeExerciceBareme,idImputation,libelleImputation,causeFacturation ) "
			+ "VALUES ("
			+ NumeroArticle
			+ ","
			+ numeroDePeriode
			+ ","
			+ idFacture
			+ ",'"
			+ debutPeriode
			+ "','"
			+ finPeriode
			+ "','"
			+ sMontantLigne
			+ "','"
			+ sPrixUnitaire
			+ "',"
			+ quantiteBareme
			+ ",'"
		
			+ codeBareme
			+ "','"
			+ libelleBareme
			+ "','"
			+ imputationBareme
			+ "','"
			+ sectionBareme
			+ "','"
			+ uniteDeTravailBareme
			+ "','"
			+ dureeunitaireBareme
			+ "','"
			+ anneeExerciceBareme
			+ "','"
			+ idImputation
			+ "','" + libelleImputation 
			+ "','"
			+causeFacturation
			+"');";
					debug.logger.debug(req);
					int result = instruction.executeUpdate(req);
				} catch (Exception e) {
					throw e;
				} 
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}
		}
		
		
		
		public Vector getListFactureFromNumBatch(int numeroBatch) {
			Vector res = new Vector();
			try {
				Statement instruction1 = con.connexion.createStatement();
				try {
					String r1 = " SELECT facture.numeroFacture " + " FROM facture"
			+ " WHERE facture.idBatchTraitement=" + numeroBatch;
		ResultSet resultat1 = instruction1.executeQuery(r1);
		while (resultat1.next()) {
			res.addElement(String.valueOf(resultat1
					.getInt("numeroFacture")));
					}
				} catch (Exception e) {
					throw e;
				} finally {
					instruction1.close();
				}
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}
			return res;
		}
		
		public int getLastBatchTraitement() {
			int res = 0;
			try {
				Statement instruction1 = con.connexion.createStatement();
				try {
					String r1 = " select  MAX(batchtraitement.numeroBatchTraitement) as id from batchtraitement ;";
		ResultSet resultat1 = instruction1.executeQuery(r1);
		while (resultat1.next()) {
			res = resultat1.getInt("id");
					}
				} catch (Exception e) {
					throw e;
				} finally {
					instruction1.close();
				}
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}
			return res;
		}
		
		
		
		public double getMontantTotalFacture(int numeroFacture) {
			double res = 0.00;
			try {
				Statement instruction1 = con.connexion.createStatement();
				try {
					String r1 = " SELECT SUM(lignefacture.montantLigne) as montantTotalFacture  FROM lignefacture"
			+ " WHERE lignefacture.idFacture =" + numeroFacture;
		ResultSet resultat1 = instruction1.executeQuery(r1);
		while (resultat1.next())
		{
			res = resultat1.getDouble("montantTotalFacture");	
		}
				} catch (Exception e) {
					throw e;
				} finally {
					instruction1.close();
				}
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}
			return res;
		}
		
		public int genererNumeroFacture(String annee) {
			int res = 0;
			String s = "000000";
		try {
			Statement instruction1 = con.connexion.createStatement();
			try {
				String r1 = " select  MAX(facture.numeroFacture) as numeroFacture  from facture WHERE  anneeEx='"
			+ annee + "'";
		ResultSet resultat1 = instruction1.executeQuery(r1);
		while (resultat1.next()) {
			res = resultat1.getInt("numeroFacture");
		}
		if (res == 0)
			s = annee + "000001";
					else
						s = String.valueOf(res + 1);
				} catch (Exception e) {
					throw e;
				} finally {
					instruction1.close();
				}
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}
			res = Integer.parseInt(s);
			return res;
		}
		
		public void ajouterligneBatchTraitement(String dateExecution,
				String anneeEx, String TypeTaxe, String etapeFacturation) {
		
			try {
				Statement instruction = con.connexion.createStatement();
				try {
					String r = "INSERT INTO batchtraitement "
			+ "(anExercice, dateExecution, typeEmplacement,etape ) "
			+ "VALUES ('" + anneeEx + "','" + dateExecution + "','"
			+ TypeTaxe + "','" + etapeFacturation + "');";
		int result = instruction.executeUpdate(r);
		// debug.logger.debug(r);
				} catch (Exception e) {
					throw e;
				} finally {
					instruction.close();
				}
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}
		}
		
		public void majFacture(int numeroFacture, double montantTotal,
				String minimumPerception) {
			Statement instruction = null ;
			try {
				instruction = con.connexion.createStatement();
				try {
					boolean utiliserMontantDeSeuil;
					double dMinimumPerception = ((Double) Double
							.valueOf(minimumPerception)).doubleValue();
					if (Double.valueOf(montantTotal) < dMinimumPerception
							&& montantTotal != 0) {
						montantTotal = dMinimumPerception;
						utiliserMontantDeSeuil = true;
					} else {
						utiliserMontantDeSeuil = false;
					}
					debug.logger.debug("montant facture" + montantTotal);
		
		String smontantTotal = Arrondi.arrondir2Digit(montantTotal);
		debug.logger.debug("montant facture" + smontantTotal);	
		
		String r = "UPDATE facture set montantTotal ='" + smontantTotal
			+ "',solde='" + smontantTotal + "' "
			+ ", utiliserMontantDeSeuil='" + utiliserMontantDeSeuil
			+ "'" +
			// ", montantEnLettre='"+montantEnLettre +"'"+
			"  WHERE numeroFacture ='" + numeroFacture + "'";
					debug.logger.debug(r);
					int result1 = instruction.executeUpdate(r);
					
				} catch (Exception e) 
				{
					throw e;
				} 
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}
			finally {
				try 
				{
					instruction.close();	
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		}
		
		public void majBatch(String valide, int idBatchTraitement,
				int nombreDeFactureCree) {
		
			try {
				Statement instruction = con.connexion.createStatement();
				try 
				{
					String r = " UPDATE batchtraitement set valide = '" + valide
							+ "',nombreDeFactureCree=" + nombreDeFactureCree
							+ " WHERE numeroBatchTraitement=" + idBatchTraitement;
					debug.logger.debug(r);
					int result1 = instruction.executeUpdate(r);
				} 
				catch (Exception e) 
				{
					throw e;
				} finally {
					instruction.close();
				}
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}
		}
		
		
		public void majEtatArticle(int idBatchTraitement, String nouveauEtat) 
		{
			try 
			{		
				Statement instruction = con.connexion.createStatement();
				try 
				{
					String r1 = " SELECT  DISTINCT(lignefacture.idArticle) , facture.typeTaxe "
							+ " FROM lignefacture,facture"
							+ " WHERE lignefacture.idFacture = facture.numeroFacture"
							+ " AND facture.idBatchTraitement=" + idBatchTraitement;
					debug.logger.debug(r1);
					ResultSet resultat1 = instruction.executeQuery(r1);
					Vector ListIdArticle = new Vector();
					int j = 0;
					while (resultat1.next()) 
					{
						j++;
						ListIdArticle.addElement((String.valueOf(resultat1.getInt("idArticle"))));
					}
					if (j > 0)	debug.logger.debug("Mise a jour des etats des articles ................");
					for (int i = 0; i < ListIdArticle.size(); i++) 
					{
						String nouveauEtatTmp= nouveauEtat ;
						int idArticle = Integer.parseInt((String) ListIdArticle	.elementAt(i));
						instruction = con.connexion.createStatement();
						String r2 = " SELECT  distinct (article.id_article), article.*, bareme.uniteDeTravail "
							+ " FROM article, bareme "
							+ " WHERE article.codebareme = bareme.code "
							+ " AND article.id_article=" + idArticle;
						debug.logger.debug(r2);
						resultat1 = instruction.executeQuery(r2);
						String source = "";
						String uniteDeTravail = "";
						while (resultat1.next()) 
						{
							source = resultat1.getString("source");
							uniteDeTravail = resultat1.getString("uniteDeTravail");
						}
						//Passage de afacturer ou controlerFacturervers les etats:
						//Facturer 
						//FacturerAControler 
						//NePlusFacturer 
						
						if (source.equalsIgnoreCase("refacturation") || source.equalsIgnoreCase("preRefacturation"))
							nouveauEtatTmp = "NePlusFacturer";				
						else if (uniteDeTravail.equalsIgnoreCase ("FORFAIT")&& nouveauEtat.equalsIgnoreCase("FacturerAControler"))
						// Cas des DDV qui sont a Forfait passe de l'etat AFacturer a l'etat Facturer
							nouveauEtatTmp = "Facturer";
						else 
							nouveauEtat = nouveauEtat ;
						
						
						String r = "UPDATE article set etat ='" + nouveauEtatTmp	+ "' WHERE id_article =" + idArticle;
						debug.logger.debug(r);
						int result2 = instruction.executeUpdate(r);				
						// Ecriture de trace
						String message = "Facturation et modification de l'etat de l'ouvrage";
						String etatAfficher = FonctionCommun.getEtat(nouveauEtatTmp);
						message = FonctionCommun.ajouterAntislash(message);
						String requete = " INSERT INTO historiqueetatouvrage(etatOuvrage,  dateModification, "
								+ " typeModifcation,type,idOuvrage)"
								+ " VALUES('"+ etatAfficher+ "','" + GestionDate.getDateAujourdhuiString()	+ "','"						
								+ message	+ "','facturation',"+ idArticle + ") ";
						debug.logger.debug(requete);
						instruction.executeUpdate(requete);
					}
					if (j > 0)	debug.logger.debug("Mise a jour des etats des articles ................................[ok]");
				} 
				catch (Exception e) 
				{
						throw e;
				} 
				finally 
				{
						instruction.close();
				}
			} 
			catch (Exception e) 
			{
					debug.logger.debug("Mise a jour des etats des articles ................[Erreur]");
					debug.logger.fatal(e.getMessage());
			}
		}
		
		
		public void majDernierePeriodeFactureEtDateProshainControl(int idArticle,
				int dernierePeriodeFacture, String anneeExercice) {
			debug.logger
					.debug("MAJ dernierePeriodeFacture et date du prochain controle ");
		try {
			Statement instruction = con.connexion.createStatement();
			try {
				// MAJ de la prochaine date de controle pour les type de taxe
		// qui
		// nesciiste un controle terrain:
		
		RequestOuvrage reqOuvrage = new RequestOuvrage();
		RequestBareme reqBareme = new RequestBareme();
		Article ouvrage = reqOuvrage.getArticle("" + idArticle);
		String codeBareme = ouvrage.getCodeBareme();
		debug.logger.debug("codeBareme=" + codeBareme);
		debug.logger.debug("anneeExercice=" + anneeExercice);
		String dateInstalation = ouvrage.getDateDebutAutorisation();
		Bareme bareme = reqBareme.getBareme(codeBareme, anneeExercice);
		String necessiteControleTerrain = bareme
				.getNecessiteControleTerrain();
		String typePeriode = bareme.getDureeUnitaire();
		String source = ouvrage.getSource();
		debug.logger.debug("necessiteControleTerrain= "
				+ necessiteControleTerrain);
		debug.logger.debug("typePeriode= " + typePeriode);
		String dateProchainControl = "";
		// Calcule de la prochaine date de controle a partie de la DI +
		// numero de perriode facture
		// Que pour les ouvrae dont le type de taxe neciste un controle
		// terrain
		if (necessiteControleTerrain.equalsIgnoreCase("oui")) {
		if (typePeriode.equalsIgnoreCase("jour"))
			dateProchainControl = GestionDate.ajouterNombreJour(
					dateInstalation, dernierePeriodeFacture);
		if (typePeriode.equalsIgnoreCase("semaine"))
			dateProchainControl = GestionDate
					.ajouterNombreSemaineComplet(dateInstalation,
							dernierePeriodeFacture);
		if (typePeriode.equalsIgnoreCase("mois"))
			dateProchainControl = GestionDate
					.ajouterNombreMoisComplet(dateInstalation,
							dernierePeriodeFacture);
		if (typePeriode.equalsIgnoreCase("an"))
				dateProchainControl = GestionDate.ajouterNombreAnnees(
						dateInstalation, dernierePeriodeFacture);
			dateProchainControl = GestionDate
					.validerUneDate(dateProchainControl);
		}
		
		if (!source.equalsIgnoreCase("preRefacturation")) {
		String r = " UPDATE article set dernierePeriodeFacture ='"
				+ dernierePeriodeFacture + "' ";
		if (necessiteControleTerrain.equalsIgnoreCase("oui"))
			r = r + " , dateProchainControl='"
					+ dateProchainControl + "'";
		r = r + " WHERE id_article =" + idArticle;
						debug.logger.debug(r);
						int result2 = instruction.executeUpdate(r);
					}
				} catch (Exception e) {
					throw e;
				} finally {
					instruction.close();
				}
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}
		}
		
		public int getNombreFacture(int idBatchTraitement) {
			int res = 0;
			try {
				Statement instruction = con.connexion.createStatement();
				try {
					String r1 = "SELECT count(*) as nombre " + "FROM facture "
			+ "WHERE facture.idBatchTraitement ="
				+ idBatchTraitement;
		debug.logger.debug(r1);
		ResultSet resultat1 = instruction.executeQuery(r1);
		Vector ListIdArticle = new Vector();
		while (resultat1.next()) {
			res = resultat1.getInt("nombre");
					}
				} catch (Exception e) {
					throw e;
				} finally {
					instruction.close();
				}
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}
			return res;
		}
		
		
		public void validerBatch(int idBatchTraitement) 
		{
			try 
			{
				String minimumDeFacturation = "0";
				double minimumDeFacturationd;				
				Statement instruction = con.connexion.createStatement();
				String etat = "";
				String necessiteControleTerrain = "NON";
				String r1 = "SELECT imputation.necessiteControleTerrain, imputation.minimumDeFacturation "
						+ " FROM imputation, batchtraitement"
						+ " WHERE imputation.libelle= batchtraitement.typeEmplacement"
						+ " AND imputation.anneeExercice= batchtraitement.anExercice "
						+ " AND numeroBatchTraitement=" + idBatchTraitement;
				debug.logger.debug(r1);
				ResultSet resultat1 = instruction.executeQuery(r1);
				while (resultat1.next()) 
				{
					necessiteControleTerrain = resultat1.getString("necessiteControleTerrain");
					//minimumDeFacturation= resultat1.getString("minimumDeFacturation");
					//minimumDeFacturationd= Double.valueOf(minimumDeFacturation);
				}
				if (necessiteControleTerrain.equalsIgnoreCase("OUI"))
					etat = "FacturerAControler";
				else
					etat = "Facturer";
				RequestBatch requestBatch = new RequestBatch();
				Batch batch = requestBatch.getBatch(idBatchTraitement);
				String etapeFacturation = batch.getEtape();
				String dejaValide=batch.getValide();
		
				if (!etapeFacturation.equalsIgnoreCase("preFacturation"))
					majEtatArticle(idBatchTraitement, etat);			
			} 
			catch (Exception e) 
			{
				debug.logger.fatal(e.getMessage());
			}		
		}
		
		public Vector getHistoriqueBatch(String attribut, String date,
				String typeTaxe, String etatBatch, String ACreeRapportsAnnulation,
				String aCreeDesFactures) {
			Vector params = null;
			try {
				Statement instruction = con.connexion.createStatement();
				try {
					String r1 = " SELECT * from batchtraitement"
			+ " WHERE (etape='facturation' || etape='preFacturation') ";
		
		if (date.length() != 0)
			r1 = r1 + " AND dateExecution like '" + date + "%'";
		if (typeTaxe.length() != 0)
			r1 = r1 + " AND typeEmplacement ='" + typeTaxe + "'";
		if (etatBatch.length() != 0)
			r1 = r1 + " AND valide ='" + etatBatch + "'";
		
		if (aCreeDesFactures.length() != 0) {
			if (aCreeDesFactures.equalsIgnoreCase("Oui"))
			r1 = r1 + " AND nombreDeFactureCree > 0 ";
		if (aCreeDesFactures.equalsIgnoreCase("Non"))
			r1 = r1 + " AND nombreDeFactureCree = 0 ";
		}
		
		if (ACreeRapportsAnnulation.length() != 0) {
			if (ACreeRapportsAnnulation.equalsIgnoreCase("Oui"))
			r1 = r1 + " AND aEditeeDesFacturesAnnulee ='true' ";
		if (ACreeRapportsAnnulation.equalsIgnoreCase("Non"))
			r1 = r1 + " AND aEditeeDesFacturesAnnulee = 'false' ";
		}
		
		r1 = r1 + " order by " + attribut + "  DESC";
		debug.logger.debug(r1);
		ResultSet resultat1 = instruction.executeQuery(r1);
		params = new Vector();
		while (resultat1.next()) {
			Batch b = new Batch();
			b.setNumeroBatchTraitement(String.valueOf(resultat1.getInt("numeroBatchTraitement")));
			b.setDateExecution(resultat1.getString("dateExecution"));
			b.setTypeEmplacement(resultat1.getString("typeEmplacement"));
			b.setValide(resultat1.getString("valide"));
			b.setEtape(resultat1.getString("etape"));
			b.setNombreFactures(resultat1.getString("nombreDeFactureCree"));
			params.addElement(b);
		}
				} catch (Exception e) {
					throw e;
				} finally {
					instruction.close();
				}
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}
			return params;
		}
		
		public String getTypeTaxeBatch(int idBatchTraitement) {
			String res = "";
		try {
			Statement instruction = con.connexion.createStatement();
			String r1 = "SELECT batchtraitement.typeEmplacement from batchtraitement "
		+ " WHERE numeroBatchTraitement=" + idBatchTraitement;
		debug.logger.debug(r1);
		ResultSet resultat1 = instruction.executeQuery(r1);
		while (resultat1.next()) {
			res = resultat1.getString("typeEmplacement");
				}
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}
			return res;
		}
		
		public void verfierSiLigneFactureDansUneFacture(String idFacture) 
		{
			try 
			{
				Statement instruction = con.connexion.createStatement();
				try 
				{
					String r1 = "SELECT  count(*) as nombreLigne FROM lignefacture "
							+ " WHERE idFacture = " + idFacture + "";
					//debug.logger.debug("Verifier si il n y pas d EF pour la facture numero "+ idFacture);
					//debug.logger.debug(r1);
					ResultSet resultat1 = instruction.executeQuery(r1);
					String nombreLigne = "0";
					RequestFacture requestFacture = new RequestFacture();
					Facture factureTmp = requestFacture.getFacture(idFacture);
					while (resultat1.next()) 
					{
						nombreLigne = resultat1.getString("nombreLigne");
					}
					//debug.logger.debug("nombreLigne=" + nombreLigne);
					int montantFacture = 1;
					if (factureTmp.getMontantTotalFacture().equalsIgnoreCase("0")
							|| factureTmp.getMontantTotalFacture().equalsIgnoreCase("0.00")) 
					{
						montantFacture = 0;
					}
		
					// Suprresion de la facture sans LFacture ou les factures dont
					// le solde= 0;
					if (nombreLigne.equalsIgnoreCase("0") || montantFacture == 0) 
					{
						r1 = "Delete FROM facture WHERE facture.numeroFacture = " + idFacture;
						//debug.logger.debug("Supression de la facture" + r1);
						instruction.executeUpdate(r1);					
						r1 = "Delete FROM lignefacture " + " WHERE idFacture = "+ idFacture + "";
						//debug.logger.debug("Supression des de ligne de facture de la facture"+ r1);
						instruction.executeUpdate(r1);
					}
				}
				catch (Exception e)	{ throw e; }
				finally {	instruction.close();}
			} 
			catch (Exception e) {	debug.logger.fatal(e.getMessage());	}
		}
		
		
		
		
		
		
		public void supprimerFacturesSiInferieurAMinimumDeFacturation(int idBatch, 
				String leMinimumDeFacturation) 
		{
			try 
			{
				String r2 = " SELECT * FROM facture " + " WHERE idBatchTraitement= "+ idBatch;
				debug.logger.debug(r2);
				Statement instruction = con.connexion.createStatement();
				Statement instruction1 = con.connexion.createStatement();			
				Statement instruction2 = con.connexion.createStatement();
				double minimumDeFacturationd = Double.parseDouble(leMinimumDeFacturation);
				ResultSet resultat2 = instruction.executeQuery(r2);
				while (resultat2.next()) 
				{		
					double montantFacture = Double.valueOf(resultat2.getString("montantTotal"));
					//debug.logger.debug("montantFacture="+montantFacture);
					//debug.logger.debug("minimumDeFacturationd="+minimumDeFacturationd);
					int numeroFacture = resultat2.getInt("numeroFacture");
					//Si le montant de la facture <mimimum de facturation , cette facture sera supprimer
					String r1;
					if(montantFacture <= minimumDeFacturationd)				
					{					
						r1 = "Delete FROM facture WHERE facture.numeroFacture = " + numeroFacture ;						
						debug.logger.debug("Supression de la facture : " + r1);
						instruction1.executeUpdate(r1);
						
						r1 = "Delete FROM lignefacture WHERE idFacture = "+ numeroFacture + "";
						debug.logger.debug("Supression des de ligne de facture de la facture : "+ r1);
						instruction2.executeUpdate(r1);
					}
				}
			instruction.close();
			instruction1.close();
			instruction2.close();
			}
			catch (Exception e) 
			{
				e.printStackTrace();			
			}
			
		}
		
		
		
		public String genererNumeroPretitre(String date, boolean nouvelleFacture) {
			String lastIndex = "";
		String newIndex = "";
		// Prefix represnte les 4 premier digits du numero de pretitre
		// Exemple 09SC0019
		String prefix = date.substring(8, 10) + "S"
				+ getLettreFromMois(date.substring(3, 5));
		try {
		
			Statement instruction = con.connexion.createStatement();
			try {
				String r1 = "SELECT  MAX(facture.numeroPretitre) as dernierIndex "
			+ " FROM facture "
			+ " WHERE facture.`numeroPretitre` like '%"
			+ prefix
			+ "%'";
		debug.logger.debug(r1);
		ResultSet resultat1 = instruction.executeQuery(r1);
		while (resultat1.next()) {
			lastIndex = resultat1.getString("dernierIndex");
				}
			} catch (Exception e) {
				throw e;
			} finally {
				instruction.close();
			}
		} catch (Exception e) {
			debug.logger.fatal(e.getMessage());
		}
		
		// Le cas ou il y a deja un batch qui lance dans le meme mois et la meme
		// annne
		if (lastIndex != null && lastIndex.length() != 0) {
			lastIndex = lastIndex.substring(4, 8);
		}
		// Le cas ou c le premier batch qui lance dans le meme mois et la meme
		// annne
		else {
			lastIndex = "0";
			}
			int newIndextmp;
			if (nouvelleFacture)
				newIndextmp = Integer.parseInt(lastIndex) + 1;
			else
				newIndextmp = Integer.parseInt(lastIndex);
		
			newIndex = prefix
					+ reglerIndexNumeroPretitre(String.valueOf(newIndextmp));
		
			return newIndex;
		}
		
		public String verfierNumeroPretitrSiIlExistePourUneAutreFacture(
				String numeroPretitre, String date) {
			String numeroPretitreUnique = numeroPretitre;
			try {
				Statement instruction = con.connexion.createStatement();
				try {
					String r1 = "SELECT  numeroPretitre FROM facture "
			+ " WHERE numeroPretitre ='" + numeroPretitre + "'";
		debug.logger.debug("Verifier si le numeroPretitre "
			+ numeroPretitre + " existe deja");
		debug.logger.debug(r1);
		ResultSet resultat1 = instruction.executeQuery(r1);
		
		if (resultat1.next()) {
			// Dans ce cas le numeroPretitre existe deja est deja
		// affecte a
		// une facture
		// Generation d'un nouveau numero de numeroPretitre
						numeroPretitreUnique = genererNumeroPretitre(date, true);
					}
				} catch (Exception e) {
					throw e;
				} finally {
					instruction.close();
				}
			} catch (Exception e) {
				debug.logger.fatal(e.getMessage());
			}
			return numeroPretitreUnique;
		}
		
		public String getLettreFromMois(String MM) {
			debug.logger.debug("Mois" + MM);
		if (MM.equalsIgnoreCase("01"))
		MM = "A";
		else if (MM.equalsIgnoreCase("02"))
		MM = "B";
		else if (MM.equalsIgnoreCase("03"))
		MM = "C";
		else if (MM.equalsIgnoreCase("04"))
		MM = "D";
		else if (MM.equalsIgnoreCase("05"))
		MM = "E";
		else if (MM.equalsIgnoreCase("06"))
		MM = "F";
		else if (MM.equalsIgnoreCase("07"))
		MM = "G";
		else if (MM.equalsIgnoreCase("08"))
		MM = "H";
		else if (MM.equalsIgnoreCase("09"))
		MM = "I";
		else if (MM.equalsIgnoreCase("10"))
		MM = "J";
		else if (MM.equalsIgnoreCase("11"))
		MM = "K";
		else if (MM.equalsIgnoreCase("12"))
		MM = "L";
			return MM;
		
		}
		
		public String reglerIndexNumeroPretitre(String dernier4DigitsNumPretitre) {
			String stDernier4DigitsNumPretitre = "";
		if (String.valueOf(dernier4DigitsNumPretitre).length() == 1)
			stDernier4DigitsNumPretitre = "000" + dernier4DigitsNumPretitre;
		else if (String.valueOf(dernier4DigitsNumPretitre).length() == 2)
			stDernier4DigitsNumPretitre = "00" + dernier4DigitsNumPretitre;
		else if (String.valueOf(dernier4DigitsNumPretitre).length() == 3)
			stDernier4DigitsNumPretitre = "0" + dernier4DigitsNumPretitre;
		else
			stDernier4DigitsNumPretitre = "" + dernier4DigitsNumPretitre;
			return stDernier4DigitsNumPretitre;
		}
		
		public String getNumeroDePretireMemeBatch(String numeroPretitre) {
			String res = "";
				String lastIndex = String.valueOf(Integer.parseInt(numeroPretitre
						.substring(4, 8)) + 1);
				res = numeroPretitre.substring(0, 4)
						+ reglerIndexNumeroPretitre(lastIndex);
				return res;
			}
		
		
	
		public double getSurfaceTotalDansEmplacement(
				String anneeTmp,String moisFacturation,
				String typeTaxe,int numEmplacment,int codeBareme,int id_article,double nombreFaceAffiche ,
				Statement instruction2, String etapeCalcul)
		{
			double surfaceTotalDansEmplacement = 0 ;		
			try 
			{	
				//debug.logger.debug("Surface totale des enseignes dans l emplacement numero " + numEmplacment) ;
				String r2 = " SELECT  article.surface, article.anexercice,  article.typeOuvrage, "
					+ " article.nombreFaceAffiche, bareme.typeArrondi , article.nombreFaceAffiche, "
					+ " article.largeur,  article.longueur, "
					+ " article.dernierePeriodeFacture ,  article.quantite_article , "
					+ " article.id_article, article.largeur * article.longueur * article.quantite_article as quantite "
					+ " FROM  article,bareme,elementfacturation, emplacementgeneral,imputation " 
					+ " WHERE  article.codebareme = bareme.code " 
					+ " AND elementfacturation.numeroEmplacement = emplacementgeneral.numero "
					+ " AND imputation.idImputation = bareme.idImputation  "
					+ " AND `imputation`.`anneeExercice` = `bareme`.`anExercice`" 
					+ " AND article.id_elementfacturation = elementfacturation.numero " ;
					if (!etapeCalcul.equalsIgnoreCase("remboursement"))
					{
						r2 = r2 + " AND ( article.etat ='ControlerAFacturer' OR article.etat ='Facturer'  " 
							+ " OR article.etat ='AFacturer' OR article.etat ='ControlerPresent' ) ";						 
					}					
					
					r2 = r2 + " AND bareme.anExercice= '"	+ anneeTmp + "'"
					+ " AND imputation.libelle ='"	+ typeTaxe + "'"
					+ " AND article.codebareme = "	+ codeBareme;
					r2 = r2 + " AND emplacementgeneral.numero = "+ numEmplacment ;					
					if(Integer.parseInt(moisFacturation) != 1)
						moisFacturation = Integer.toString(Integer.parseInt(moisFacturation) -1) ;
				
				   r2 = r2 + " AND ( STR_TO_DATE(article.DateDebutAutorisation, '%d/%m/%Y') " 
			                   + " <= STR_TO_DATE('31/"+moisFacturation+"/"+anneeTmp+"', '%d/%m/%Y') )"
			                   + " AND   ( article.DateFinAutorisation = '' "
					           + "OR ";											        
				   //System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
				   //System.out.println(moisFacturation);				   
				   //System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");

				   
				   if( moisFacturation.equalsIgnoreCase("01") )
				   {
					   r2 = r2 + "( STR_TO_DATE(article.DateFinAutorisation, '%d/%m/%Y') " 
	                   + " >= STR_TO_DATE('01/"+moisFacturation+"/"+anneeTmp+"', '%d/%m/%Y') )";					   
				   }
				   else if( !moisFacturation.equalsIgnoreCase("12") )
				   {					  
					  int moisSiuvant = Integer.parseInt(moisFacturation)+1;
					  r2 = r2 + "( STR_TO_DATE(article.DateFinAutorisation, '%d/%m/%Y') " 
			                   + " >= STR_TO_DATE('01/"+moisSiuvant+"/"+anneeTmp+"', '%d/%m/%Y') )";
				   }
				   else
					  r2 = r2 + "( STR_TO_DATE(article.DateFinAutorisation, '%d/%m/%Y') " 
	                   + " > STR_TO_DATE('31/"+moisFacturation+"/"+anneeTmp+"', '%d/%m/%Y') )";
			                   
				  r2 = r2 + ")";				   
					//article.DateFinAutorisation > 31 moisFacturation pourque les articles qui ont 
				   //une date de fin :31/moisFacturation ne soit pas facturé le mois suivants
				debug.logger.debug("SURFACE TOTAL >>>>>>>>>>>>"+r2);
				//on facture le mois suivant 
				//Cad si un article est installe le 15 janvier , il est facture du 01/02 --> 29/02
				
				ResultSet resultat2 = instruction2.executeQuery(r2);							
				while (resultat2.next()) 
				{	
					double nombreFaceAfficheTmp = resultat2	.getDouble("nombreFaceAffiche");
					double largeurArticleTmp = resultat2.getDouble("largeur");
					double longeurArticleTmp = resultat2.getDouble("longueur");
					double surfaceArticleTmp = resultat2.getDouble("surface");
					double quantiteArticleTmp = resultat2.getDouble("quantite_article");
					String typeArrondi= resultat2.getString("typeArrondi");
					double sTotlalTmp = Double.valueOf( getQuantiteTotal(typeArrondi, longeurArticleTmp,
							largeurArticleTmp,surfaceArticleTmp,	quantiteArticleTmp));
					
					//if (surfaceArticleTmp != 0)		sTotlalTmp = sTotlalTmp	* surfaceArticleTmp;
	
					if (nombreFaceAffiche != 0)
						sTotlalTmp = sTotlalTmp	* nombreFaceAfficheTmp;
	
					surfaceTotalDansEmplacement = surfaceTotalDansEmplacement	+ sTotlalTmp;
				}				
				//debug.logger.debug("Mois "+ moisFacturation+" la suurface total =" + surfaceTotalDansEmplacement);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return  surfaceTotalDansEmplacement ;
		}
		
		public void remboursement(int idBatch , String anneeCourante, String typeTaxe, String etapeFacturation,
				String anneeFacturationTLPE)
		{
			int idClient ;
			try 
			{	
				String anneePrecedante = String.valueOf(Integer.parseInt(anneeFacturationTLPE)-1);
				//String anneePrecedante =anneeFacturationTLPE;
				debug.logger.debug("######################################################################################") ;
				debug.logger.debug("###################Recherche de remboursement pour l'annee "+ anneePrecedante+" Batch " + idBatch +"####################") ;
				debug.logger.debug("######################################################################################") ;
				String r2 = " SELECT facture.idClient, facture.typeTaxe,facture.numeroFacture as numeroDeLaFacture, lignefacture.* "				
					+ " FROM facture,lignefacture,article  "				
					+ " WHERE  "				
					+ " lignefacture.idFacture= facture.numeroFacture "
					+ " AND lignefacture.idArticle = article.id_article"
					+ " AND article.rembourse = 'non' " 
					+ " AND facture.typeTaxe='"+typeTaxe+"' " 
					+ " AND facture.etat='Valide' "
					+ " AND facture.anneeFacturationTLPE='"+ anneePrecedante+"' "
			        + " ORDER BY numeroDeLaFacture ASC";
						//			
				debug.logger.debug(r2);
				Statement instruction2 = con.connexion.createStatement();
				ResultSet resultat2 = instruction2.executeQuery(r2);
				int numeroFactureInit = -1;
				String idRemboursement = "-1";
				double montantTotalRemboursement= 0;
				Vector listeDesOuvrageARembourser = new Vector();
				int nombreDeRemboursement = 0 ;
				debug.logger.debug("\n\n################REMBOURSEMENT##################################");
				while (resultat2.next()) 
				{
					System.gc();
					idClient = resultat2.getInt("idClient");								
					int numeroFacture = resultat2.getInt("numeroDeLaFacture");					
					int idArticle = resultat2.getInt("idArticle");
					debug.logger.debug("articleid= "+idArticle);
					double montantFactureAnneePrecedante = Double.valueOf(resultat2.getString("montantLigne"));
					String debutPeriodeFacture = resultat2.getString("debutPeriode");
					String finPeriodeFacture = resultat2.getString("finPeriode");
					Article  article= requestOuvrage.getArticle(""+idArticle);
					
					String etatArticle = article.getEtatArticle();
					String etatRembourseArticle = article.getEtatRembourseArticle();
					Bareme bareme = requestBareme.getBareme(article.getCodeBareme(), anneePrecedante);
					int coeffisienTypeNumerique = Integer.valueOf(bareme.getCoefficientNumerique());
					String dateFinActuelle= article.getDateFinAutorisation();					
					String dateFinARecalculer =finPeriodeFacture ;
					try 
					{
						if (dateFinActuelle !=null && dateFinActuelle.length() == 10 && 
								GestionDate.getAnneeFromDateString(dateFinActuelle).equalsIgnoreCase(anneePrecedante) )
						{
							dateFinARecalculer = dateFinActuelle.substring(3) ;
						}
					}
					catch (Exception e) 
					{
						debug.logger.fatal("erreur" +e.getMessage());
						e.printStackTrace();					
					}
					
					
					//Calcul du montant de la ligne anneePrecdante de la date debut
					//de debutPeriodeFacture jusqu au dateFinARecalculer
					//Re-Calcul du montant de la ligne de date DebutFacture jusqua dateFinActuelle :
					//Et puis comparaison avec la ligne de facture fait l'annee derniere
					debug.logger.debug("\n\n\n############################################");
					debug.logger.debug("Verfier si il y a un remboursement pour l'article ="+idArticle);
					debug.logger.debug("Numero facture ="+numeroFacture);
					debug.logger.debug("idClient ="+idClient);					
					debug.logger.debug("debutPeriodeFacture" +debutPeriodeFacture);
					debug.logger.debug("dateFinARecalculer" +dateFinARecalculer);
					String moisDebut = GestionDate.getMoisFromDateString("01/"+debutPeriodeFacture);
					String moisFin = GestionDate.getMoisFromDateString("01/"+ dateFinARecalculer);
					//debug.logger.debug("moisDebut" +moisDebut);
					//debug.logger.debug("moisFin" +moisFin);
					int nombreMoisAFcturer =1;
					try 
				    {
						nombreMoisAFcturer = Integer.parseInt(moisFin) -	Integer.parseInt(moisDebut) +1 ;					
					}
				    catch (Exception e) 
				    {
						debug.logger.fatal("erreur" +e.getMessage());
						e.printStackTrace();					
					}
					
					//debug.logger.debug("nombreMoisAFcturer="+nombreMoisAFcturer);
					
				    Vector res = getMontantLigne(article, anneePrecedante,
							moisDebut, moisFin, nombreMoisAFcturer,coeffisienTypeNumerique,"remboursement");
					double revalorisationMontantLigne= (Double)(res.elementAt(0));
					boolean variationSurface= (Boolean)res.elementAt(1);
					String moisVariationSurface= (String)res.elementAt(2);					
					double differenceDeMotant = revalorisationMontantLigne - montantFactureAnneePrecedante ;
					differenceDeMotant = FonctionCommun.arrondir(differenceDeMotant);
					
					////////////////////////////
					//Verifcation si il y a un remboursement  ;
					////////////////////////////									
					//revalorisationMontantLigne = FonctionCommun.arrondir(revalorisationMontantLigne);
					//montantFactureAnneePrecedante = FonctionCommun.arrondir(montantFactureAnneePrecedante);
									
					debug.logger.debug("Montant facture anne precedante  ="+ montantFactureAnneePrecedante);
					debug.logger.debug("Montant recalcule ="+ FonctionCommun.arrondir(revalorisationMontantLigne));
					debug.logger.debug("Difference de motnant ="+differenceDeMotant);
					debug.logger.debug("Date de fin facture=" + finPeriodeFacture);
					debug.logger.debug("Date de fin actuelle=" + dateFinActuelle);				
					debug.logger.debug("Date de fin pour revalorisation=" + dateFinARecalculer);			
				
					//Calcul de remboursement 
					if ( differenceDeMotant < 0  && ! etatRembourseArticle.equalsIgnoreCase("rembourser"+anneePrecedante) 
						  && 
							(
								  (  article.getLibelleBareme().equalsIgnoreCase("enseignes") &&
								    moisVariationSurface!= null && moisVariationSurface.length() != 0 
								  )
							  ||
					  		  		( !article.getLibelleBareme().equalsIgnoreCase("enseignes") )
					  		)
						)
						
					{
					
						differenceDeMotant = Math.abs(differenceDeMotant);					
						debug.logger.debug("Remboursement: Ajout d'une nouvelle ligne de remboursement montant = " + differenceDeMotant);
						if (numeroFactureInit != numeroFacture)
						{							
								montantTotalRemboursement = 0;
								debug.logger.debug("Remboursement: Ajout d'un nouveau remboursement");
								Statement instruction0 = con.connexion.createStatement();
								numeroFactureInit= numeroFacture;
								nombreDeRemboursement ++ ;
								String requete = " INSERT INTO remboursement " +
										         " (numeroFacture,idBatchTraitement," +
										         "  idClient, anneeEx,typeTaxe,dateCreation)"
									+ " VALUES " 
									+ " ("
												+ numeroFacture
												+ "," 
												+ idBatch
												+ ","
												+idClient
												+ ","
												+anneePrecedante
												+ ",'"
												+typeTaxe 
												+ "','"
												+GestionDate.getDateAujourdhuiString()
												+"'" 
									+ ")";
								debug.logger.debug("insert Remboursement:"+requete);							
								instruction0.executeUpdate(requete);
								
								String r = " UPDATE batchtraitement set aEditerDesRemboursements ='true'" +
								   " WHERE numeroBatchTraitement =" + idBatchTraitement;
								debug.logger.debug(r);
								int result2 = instruction0.executeUpdate(r);
								
								//get lastIdRemboursement 
								requete = "SELECT MAX(idRemboursement) as maxIdRemboursement FROM remboursement ";
								debug.logger.debug(requete);
								ResultSet resultat = instruction0.executeQuery(requete);
								
								while (resultat.next()) 
								{
									idRemboursement= resultat.getString("maxIdRemboursement");
								}
								instruction0.close();
						}					
						String debutPeriodeRemboursement = "" ;					
						
						try 
						{
							if(!article.getLibelleBareme().equalsIgnoreCase("enseignes"))
							{

								if( finPeriodeFacture.equalsIgnoreCase(dateFinARecalculer) )
									debutPeriodeRemboursement = debutPeriodeFacture;
								else
								{
									if(!dateFinARecalculer.contains("12/"))
									{
										String dateFinARecalculerTmp = "01/"+dateFinARecalculer;
										dateFinARecalculerTmp = GestionDate.ajouterNombreMoisComplet(dateFinARecalculerTmp, 1) ;
										dateFinARecalculer = dateFinARecalculerTmp.substring(3);
										// on rembourse le mois suivant de la fin de periode
									}
										
									debutPeriodeRemboursement = dateFinARecalculer;
								}
									
							}
							else
							{
								String smoisVariationSurface = String.valueOf(moisVariationSurface);
								if (moisVariationSurface!= null && moisVariationSurface.length() != 0
										&& Integer.parseInt(moisVariationSurface)< 10)
									smoisVariationSurface = "0" + moisVariationSurface;
								debutPeriodeRemboursement = "01/"+ smoisVariationSurface+"/"+anneePrecedante;
								//On ajoute un mois , facturation apres un mois pour l'ouvrage qui a induit le remboursement
								if(!dateFinARecalculer.equalsIgnoreCase(finPeriodeFacture))
								  debutPeriodeRemboursement= GestionDate.ajouterNombreMoisComplet(debutPeriodeRemboursement, 1) ;
								debutPeriodeRemboursement = debutPeriodeRemboursement.substring(3);								
							}
						}
						catch (Exception e) 
						{							
							debug.logger.error("Erreur"+e.getCause().toString() + e.toString() + e.getCause());
							e.printStackTrace();
						}
						
						try 
						{
							debug.logger.debug("Remboursement du "+  debutPeriodeRemboursement + " au " + finPeriodeFacture );
							double surfaceTotalTaxeArticle = Double.valueOf(article.getQuantite_article());		
							if(article.getSurface() == 0)
								surfaceTotalTaxeArticle = surfaceTotalTaxeArticle * article.getLargeur() * article.getLongueur();
							else
								surfaceTotalTaxeArticle = surfaceTotalTaxeArticle * article.getSurface() ;
							
							differenceDeMotant = FonctionCommun.arrondir(differenceDeMotant);
							String requete = " INSERT INTO ligneremboursement " 
								+"(idRemboursement,idArticle,debutPeriodeRemboursement," +
									"finPeriodeRemboursement,montantLigne,quantite)"
								+ " VALUES " 
								+ " ('"														
								+ idRemboursement
								+ "','"
								+ idArticle
								+ "','"
								+ debutPeriodeRemboursement 
								+ "','"							
								+ finPeriodeFacture
								+ "','"							
								+ differenceDeMotant 
								+ "','"							
								+ surfaceTotalTaxeArticle
								+ "'"
								+ ")";
							debug.logger.debug("insert ligne remboursement:"+requete);
							montantTotalRemboursement = montantTotalRemboursement + differenceDeMotant ;
							Statement instruction0 = con.connexion.createStatement();
							instruction0.executeUpdate(requete);
							listeDesOuvrageARembourser.addElement(idArticle);						
							miseAjourMontantTotalRemboursement(idRemboursement, montantTotalRemboursement);
							
						}
						catch (Exception e) 
						{							
							debug.logger.error("Erreur"+e.getCause().toString() + e.toString() + e.getCause());
							e.printStackTrace();
						}
											
					}//Fin cas de remboursmeent
				}//Fin while
			
				if(nombreDeRemboursement != 0)
				{								
					Statement instruction0 = con.connexion.createStatement();
					String r =  " UPDATE batchtraitement set nombreDeRemboursementCree ="+nombreDeRemboursement+""
							  + " WHERE numeroBatchTraitement =" + idBatchTraitement;
					debug.logger.debug(r);
					int result2 = instruction0.executeUpdate(r);
					instruction0.close();
				}
				
				for (int i = 0; i < listeDesOuvrageARembourser.size(); i++) 
				{
					if(etapeFacturation.equalsIgnoreCase("facturation"))
					{
						int idArticle = (Integer)listeDesOuvrageARembourser.elementAt(i);
						String r =   " UPDATE article set etat ='Rembourser'," 
								   + " rembourse ='rembourser"+anneePrecedante+"'" 
								   + " WHERE id_article =" + idArticle;
						debug.logger.debug(r);
						Statement instruction0 = con.connexion.createStatement();
						int result2 = instruction0.executeUpdate(r);
					
					
						// Ecriture de trace 
						String message = "Modification de l'etat de l'ouvrage rembourse";
						String etatAfficher = FonctionCommun.getEtat("rembourser");
						message = FonctionCommun.ajouterAntislash(message);
						String requete = " INSERT INTO historiqueetatouvrage(etatOuvrage,  dateModification, "
								+ " typeModifcation,type,idOuvrage)"
								+ " VALUES('"
								+ etatAfficher
								+ "',"
								+ "'"
								+ GestionDate.getDateAujourdhuiString()
								+ "',"
								+ "'"
								+ message
								+ "',"
								+ "'facturation',"
								+ idArticle + ") ";
						debug.logger.debug(requete);				
						instruction0.executeUpdate(requete);
						instruction0.close();
					}//Fin if etape facturation				
				}//Fin parcours des ouvrages a metre a jour l'etat a rembourser
			}//Fin if remboursement 
			catch (Exception e) 
			{
				e.printStackTrace();
				debug.logger.error("Erreur"+ e.toString() + e.getMessage());
			}		
		}
		
			
		public double  ajouterRappelTLPEOuvrageDejaFacture(int idRedevable , int numeroFacture , 
				String anneeCourante, String typeTaxe)
		{
			int idClient ;
			double retour = 0 ;
			try 
			{	
				String anneePrecedante = String.valueOf(Integer.parseInt(anneeCourante)-1);			
				debug.logger.debug("***************************************************************************************") ;
				debug.logger.debug("*****Recherche de rappel pour l'annee "+ anneePrecedante+" Redevable " + idRedevable +"*********") ;
				debug.logger.debug("***************************************************************************************") ;
				//On selectionne que les factures qui sont valides 
				String r2 = " SELECT facture.idClient, facture.typeTaxe,facture.numeroFacture as numeroDeLaFacture, lignefacture.* "				
					+ " FROM facture,lignefacture  "				
					+ " WHERE  "
					+ " lignefacture.idFacture= facture.numeroFacture "
					+ " AND facture.idClient= " + idRedevable 
					+ " AND facture.typeTaxe='"+typeTaxe+"' " 
					+ " AND facture.anneeFacturationTLPE='"+ anneePrecedante+"' "
					+ " AND facture.etat='Valide' "		        
			        + " ORDER BY numeroDeLaFacture ASC";
									
				debug.logger.debug(r2);
				Statement instruction2 = con.connexion.createStatement();
				ResultSet resultat2 = instruction2.executeQuery(r2);	
				debug.logger.debug("Verifications des articles deja Facturés l'année précedantes ");				
				while (resultat2.next()) 
				{	
					int idArticle = resultat2.getInt("idArticle");								
					double montantFactureAnneePrecedante = Double.valueOf(resultat2.getString("montantLigne"));
					String debutPeriodeFacture = resultat2.getString("debutPeriode");
					String finPeriodeFacture = resultat2.getString("finPeriode");
					Article  article= requestOuvrage.getArticle(""+idArticle);
					Bareme bareme = requestBareme.getBareme(article.getCodeBareme(), anneePrecedante);
					int coeffisienTypeNumerique = Integer.valueOf(bareme.getCoefficientNumerique());
					String dateFinActuelle= article.getDateFinAutorisation();				
					String dateFinARecalculer ;
					if (dateFinActuelle !=null && dateFinActuelle.length() != 0 && 
							GestionDate.getAnneeFromDateString(dateFinActuelle).equalsIgnoreCase(anneePrecedante) )
					{
						dateFinARecalculer = dateFinActuelle.substring(3) ;
					}
					else
					{
						dateFinARecalculer =finPeriodeFacture ;
					}
					
					//Calcul du montant de la ligne anneePrecdante de la date debut
					//de debutPeriodeFacture jusqu au dateFinARecalculer
					//Re-Calcul du montant de la ligne de date DebutFacture jusqua dateFinActuelle :
					//Et puis comparaison avec la ligne de facture fait l'annee derniere
					debug.logger.debug("****************************");
					debug.logger.debug("\n\n\nNumero facture ="+numeroFacture);
					debug.logger.debug("identifiant article ="+ idArticle);
					debug.logger.debug("debutPeriodeFacture" +debutPeriodeFacture);
					debug.logger.debug("dateFinARecalculer" +dateFinARecalculer);
					String moisDebut = GestionDate.getMoisFromDateString("01/"+debutPeriodeFacture);
					String moisFin = GestionDate.getMoisFromDateString("01/"+ dateFinARecalculer);
					//debug.logger.debug("moisDebut" +moisDebut);
					//debug.logger.debug("moisFin" +moisFin);
					int nombreMoisAFcturer =1;
					try 
				    {
						nombreMoisAFcturer = Integer.parseInt(moisFin) -
	    				Integer.parseInt(moisDebut) +1 ;					
					}
				    catch (Exception e) 
				    {
						debug.logger.fatal("erreur" +e.getMessage());
						e.printStackTrace();					
					}
					
					debug.logger.debug("nombreMoisAFcturer="+nombreMoisAFcturer);				
				    Vector res = getMontantLigne(article, anneePrecedante,
							moisDebut, moisFin, nombreMoisAFcturer,coeffisienTypeNumerique,"normal");
					double revalorisationMontantLigne= (Double)res.elementAt(0);
					boolean variationSurface= (Boolean)res.elementAt(1);
					String moisVariationSurface= (String)res.elementAt(2);
					revalorisationMontantLigne = FonctionCommun.arrondir(revalorisationMontantLigne);
					montantFactureAnneePrecedante = FonctionCommun.arrondir(montantFactureAnneePrecedante);
					double differenceDeMotant = revalorisationMontantLigne - montantFactureAnneePrecedante ;
					debug.logger.debug("Montant facture anne precedante  ="+ montantFactureAnneePrecedante);
					debug.logger.debug("Montant recalcule ="+ revalorisationMontantLigne);
					debug.logger.debug("Difference de motnant ="+differenceDeMotant);
					debug.logger.debug("Date de fin actuelle=" + dateFinActuelle);
					debug.logger.debug("Date de fin pour revalorisation=" + dateFinARecalculer);
					debug.logger.debug("Etat article=" + article.getEtatArticle());
					//Pour ne pas facturer des articles qui sont deja facturées
					String debutPeriodeArticle= article.getDateDebutAutorisation();					
					String dateFinArticle= "31/12/"+anneePrecedante;
					int dernierePeriodeFactureArticle = Integer.parseInt(article.getDernierePeriodeFacture());
					int numeroDePeriodeArticle = GestionDate.getNombreDannee(debutPeriodeArticle, dateFinArticle);
					int nombreDePeriodeAFacturerArticle = numeroDePeriodeArticle- dernierePeriodeFactureArticle;
					//debug.logger.debug("nombreDePeriodeAFacturerArticle=" + nombreDePeriodeAFacturerArticle);
					
					
					if ( differenceDeMotant > 0 && article.getEtatArticle().equalsIgnoreCase("afacturer") )
							//&& nombreDePeriodeAFacturerArticle >0 )
					{
							/////////////////////////
							//cas de rappel de l'anne N-1
						    ////////////////////////
							debug.logger.debug("Rappel Il s agit d'un rappel ");
							debug.logger.debug("Rappel Ajout de rappel suite a un changement de tarif ");
							double montantRappel = revalorisationMontantLigne - montantFactureAnneePrecedante ;											
							String imputationBareme = bareme.getImputationBareme();
							String sectionBareme = bareme.getSectionBareme();
							String uniteDeTravailBareme = bareme.getUniteDeTravail();
							String dureeunitaireBareme = bareme.getDureeUnitaire();
							double quantiteBareme = article.getQuantite_article();
							if(article.getSurface() == 0)
								quantiteBareme = quantiteBareme * article.getLargeur() * article.getLongueur();
							else
								quantiteBareme = quantiteBareme * article.getSurface() ;
							if ( Integer.valueOf(moisVariationSurface).intValue() < 10	)
									moisVariationSurface = "0"+ moisVariationSurface;
							String newDabutRappel= moisVariationSurface + "/" + anneePrecedante;
							retour = retour + montantRappel ;
							ajouterligneFacture( idArticle, 1, numeroFacture, 
									newDabutRappel, dateFinARecalculer, montantRappel, 1, 
								quantiteBareme,article.getCodeBareme(), article.getLibelleBareme(),
								imputationBareme,sectionBareme, uniteDeTravailBareme, dureeunitaireBareme, 
								anneePrecedante, article.getIdImputation(), article.getLibelleImputation(), "rappel");
					}//Fin if rappel
					debug.logger.debug("****************************");
				}//Fin while
			}//Fin catch
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return retour ;
		}
		
		
		
		public Vector getMontantLigne (
				Article article,
		  	    String anneeTmp,
				String moisDebut, String moisFin,
				int nombreMoisAFcturer ,
				int coeffisienTypeNumerique,
				String etapeCalcul
				
				)
		{		
			
			//debug.logger.debug("Numero article "+article.getId()  );			
			//debug.logger.debug("Libelle Bareme "+article.getLibelleBareme() );
			//debug.logger.debug("mois debut " + moisDebut);
			//debug.logger.debug("mois fin " + moisFin);
			//debug.logger.debug("nombreMoisAFcturer " + nombreMoisAFcturer);
			
			Vector res = new Vector();	
			String typeTaxe= article.getLibelleImputation();
			String newDateDebutPeriodeTmp= moisDebut +"/" + anneeTmp;
			String newDateFinPeriodeTmp= moisFin +"/" + anneeTmp;
			int numEmplacment = Integer.valueOf(article.getNumEmplacment());
			int id_article = Integer.valueOf(article.getId());
			int codeBareme = Integer.valueOf(article.getCodeBareme());		
			String libelleBareme = article.getLibelleBareme();		
			double nombreFaceAffiche= Double.valueOf(article.getNombreFaceAffiche());
			
			double montantLigne= 0;
			double surfaceUtilisePourLeCalculDuTarif= 0;
			boolean variationSurface = true ;
			double prixSelonSurface = 1;
			boolean init = true ;		
			int moisFacturation = Integer.valueOf(moisDebut);
			double montantDuMois = 0;
			int nombreDeMoisMemeTarif = 1;
			String typeArrondi = article.getTypeArrondi();
			String moisVariationDeSurface = "";
			double dernierPrixSelonSurface = -1;			
			String stQuantiteTotal= getQuantiteTotal(typeArrondi, article.getLongueur(), 
					article.getLargeur(),Double.valueOf(article.getSurface()), article.getQuantite_article());			
			//Prendre en compte le nombre de face et le nomnbre d'affiche
			double surfaceTotalTaxeArticle  = Double.valueOf(stQuantiteTotal) ;
			if (nombreFaceAffiche != 0)
				surfaceTotalTaxeArticle = surfaceTotalTaxeArticle* nombreFaceAffiche;
			
			
			try
			{	
				while ( variationSurface  &&  moisFacturation <= Integer.valueOf(moisFin) )				
				{
					Statement instruction0 = con.connexion.createStatement();
					String mfs= ""+moisFacturation ;
					if (moisFacturation <10) mfs ="0"+ mfs ;
					
					//Correction bug, 06/12/2010 demande de Zacchelo pour le calcul du tarif					
					//SURFACE TOTAL N'CALCULE QUE POUR LES ENSEIGNES
					//Calcul de la surface total du meme type dans l'emplacement que pour les enseignes
					//C’est la somme des surfaces des dispositifs de type enseigne qui détermine le tarif à appliquer.
					if (libelleBareme.equalsIgnoreCase("enseignes"))
					{
						surfaceUtilisePourLeCalculDuTarif =	getSurfaceTotalDansEmplacement(
							anneeTmp,mfs,typeTaxe, numEmplacment, codeBareme,
							id_article, nombreFaceAffiche,	instruction0,etapeCalcul);
					}
					else
					//Tous les autres Type d'ouvrage 
					//Le tarif est déterminé par la surface de chaque ligne d’ouvrage saisi.
					//La quantité facturée tient compte de toutes les valeurs :
 				    //Surface * nombre d’ouvrages de même type * nombre de faces.
					{
						surfaceUtilisePourLeCalculDuTarif = surfaceTotalTaxeArticle;						
					}
					
										
					if ( libelleBareme.equalsIgnoreCase("enseignes"))
					//cas des enseignes 	
					{
						// Cette surface Total va permettre de detrminer le tarif a appliquer pour le mois mf
						//on verfie si il y a un changement de tarif on dois calculer mois par mois,pareque il y a une variation de surface,
						//on recherche du debut de la periode a la fin si surfaceTotalDansEmplacement change
						if(init)
						{
							init = false;
							debug.logger.debug("Verfier si il y a une variation de tarif pendant la periode de facturation ");							
							for (int mf = Integer.valueOf(moisDebut); mf <= Integer.valueOf(moisFin); mf++) 
							{							
								String mfstmp= ""+mf  ;
								moisVariationDeSurface = mfstmp;
								if (mf <10) mfstmp ="0"+ mfstmp ;								
								//calcul de la surface chaque mois 
								double surfaceTotalDansEmplacementTmp =	getSurfaceTotalDansEmplacement(
											anneeTmp,mfstmp,typeTaxe, numEmplacment, 
											codeBareme, id_article, nombreFaceAffiche,instruction0,etapeCalcul);					
					
								//Comparison entre la surface du mois et la surface calcule au debut
								variationSurface =requestBareme.changeDeTarif(surfaceUtilisePourLeCalculDuTarif, 
										surfaceTotalDansEmplacementTmp, codeBareme, anneeTmp);
								if (variationSurface)
								{																	
									break;
								}
							}
						if (variationSurface)
						{
							debug.logger.debug("FACTURATION mois/mois, Il y a une variation de tarif du " + newDateDebutPeriodeTmp  + " au " + newDateFinPeriodeTmp );
							debug.logger.debug("La variation de la surface commence de"+moisVariationDeSurface);
						}
						else					
						{
							debug.logger.debug("FACTURATION pedant toute la periode , pas de variation de tarif du " + newDateDebutPeriodeTmp  + " au " + newDateFinPeriodeTmp );		
						}
						
					  }//FIN verification de tarif pendant la periode de facturation						
					 }//FIN si enseigne: on ne calcule la surface total que si l'ouvrage est de type enseignes
					else 
					// cas des pre enseigne et des panneau publicitaire, on ne calcule pas mois /mois  
					{
						variationSurface = false;
					}
					
					//Calcul du prix selon la surface :
					//Surface = surface de tous les enseignes dans le meme emplacmeent 
					//Surface = surface de l'ouvrage  (panneau et pre-enseigne)  dans le meme emplacmeent
					
					String r3 = " SELECT prix FROM tarifbaremetlpe "
						+ " WHERE " + " idBareme ='" + codeBareme
						+ "'" + " AND anExercice ='" + anneeTmp
						+ "'" + " AND surfaceMin <=  "+ surfaceUtilisePourLeCalculDuTarif
						+ " AND surfaceMax >=  "+ surfaceUtilisePourLeCalculDuTarif;
	
					debug.logger.debug(r3);
					ResultSet resultat3 = instruction0.executeQuery(r3);
					
					while (resultat3.next()) 
					{
						prixSelonSurface = resultat3.getDouble("prix");
					}	
					
					
					if (!variationSurface)
					{						
						montantLigne = nombreMoisAFcturer * surfaceTotalTaxeArticle * prixSelonSurface/12 ;
						//debug.logger.debug("montant de la ligne= " + montantLigne);
					}
					else
					{
						montantDuMois = surfaceTotalTaxeArticle * prixSelonSurface/12 ;
						montantLigne = montantLigne + montantDuMois;
						//debug.logger.debug("montant du mois "+ moisFacturation+" = " + montantDuMois);
						//debug.logger.debug("montant de la ligne= " + montantLigne);
					}			
					//debug.logger.debug("SurfaceTotalTaxeArticle ="	+ surfaceTotalTaxeArticle);
					//debug.logger.debug("Prix selon surface total ="	+ prixSelonSurface);
					//debug.logger.debug("Nombre de mois a facturer ="+ nombreMoisAFcturer);					
										
					moisFacturation ++;
				}//Fin WHILE : Parcours des mois a facturer
				
								
				String typeOuvrageArticle = article.getTypeOuvrage();
				
				// si c numerique				
				//debug.logger.debug("Type ouvrage = "+ typeOuvrageArticle);
				if (typeOuvrageArticle.equalsIgnoreCase("Numerique")) 
				{						
					montantLigne = montantLigne	* coeffisienTypeNumerique;
					debug.logger.debug("Type ouvrage Numerique ");
				}
		
				
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				debug.logger.fatal("erreur" + e.getStackTrace().toString());
			}
			//debug.logger.debug("montantLigne calculé ="+ montantLigne);	
			res.addElement(montantLigne);
			res.addElement(variationSurface);
			res.addElement(moisVariationDeSurface);
			return res;
		}
		
	
	
		
		public void miseAjourMontantTotalRemboursement(String idRemboursement, double montantTotalRemboursement	)
		{
			int numeroFactureInit = -1;
			try 
			{
				Statement instruction0 = con.connexion.createStatement();				
				montantTotalRemboursement = FonctionCommun.arrondir(montantTotalRemboursement);
				//maj montant total 
				//montantTotalRemboursement
				String r = " UPDATE remboursement " +
						   " set montantTotal =" + montantTotalRemboursement +
						   " WHERE idRemboursement =" + idRemboursement ;
				debug.logger.debug(r);
				int result1 = instruction0.executeUpdate(r);
				instruction0.close();
				//neplusfacturer
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}	
			
		}
		
		public int getIdBatchTraitement() 
		{
			return idBatchTraitement;
		}
		
		public String  getQuantiteTotal(String typeArrondi, double longeurArticle , double largeurArticle,
				double surfaceArticle, double quantiteArticle) 
		{
			String stQuantiteTotal ="";
					
			if (typeArrondi.startsWith("N")) 
			{
				if (surfaceArticle != 0) 
					stQuantiteTotal = Arrondi.arrondir2Digit(longeurArticle	* largeurArticle* surfaceArticle		* quantiteArticle);
				else
					stQuantiteTotal = Arrondi.arrondir2Digit(longeurArticle	* largeurArticle* quantiteArticle);					
			}
			else if (typeArrondi.startsWith("L")) 
			{
				if (surfaceArticle != 0) 
				{
					stQuantiteTotal = Arrondi.arrindiParLigne(longeurArticle* largeurArticle* surfaceArticle);
					stQuantiteTotal = Arrondi.arrondir2Digit(Double	.valueOf(stQuantiteTotal).doubleValue()	* quantiteArticle);
				}
				else 
				{
					stQuantiteTotal = Arrondi.arrindiParLigne(longeurArticle* largeurArticle);
					stQuantiteTotal = Arrondi.arrondir2Digit(Double.valueOf(stQuantiteTotal).doubleValue()	* quantiteArticle);
				}
			}
			else if (typeArrondi.startsWith("T")) 
			{
				if (surfaceArticle != 0)
					stQuantiteTotal = Arrondi.arrindiParLigne(longeurArticle * largeurArticle * surfaceArticle * quantiteArticle);
				else
					stQuantiteTotal = Arrondi.arrindiParLigne(longeurArticle * largeurArticle * quantiteArticle);
			}
			
			return stQuantiteTotal;
		}
	
		
}


