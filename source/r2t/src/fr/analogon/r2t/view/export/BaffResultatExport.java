package fr.analogon.r2t.view.export;

import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.lowagie.tools.encrypt_pdf;
import com.sun.org.apache.bcel.internal.generic.Instruction;

import fr.analogon.r2t.Utilitaire.FonctionCommun;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.pojo.Redevable;
import fr.analogon.r2t.request.Connecteur;
import fr.analogon.r2t.request.RequestParametres;
import fr.analogon.r2t.request.RequestRedevable;
import fr.analogon.r2t.util.web.StaticManipHtml;

/**
 * Bean d'affichage pour le resultant d'une recherche pour export 
 * 
 * @version 2.0 . Sofien CHARFI
 * @since 2.0
 */
public class BaffResultatExport extends fr.analogon.r2t.main.RacineBean {

	//Parametres de recherche 	
	private String resultatRecherche="";
	private String nombreRedevable="";
	private String numRedevable="";	
	private String nomRedevable="";	
	private String prenomRedevable="";
	private String raisonSociale=""; 
	private String profession="";
	private String codeSecteur="";
	private String codeEmplacement="";	
	private String exercice="";
	private String nomQuartier="";
	private String typeBareme="";
	private String typeTaxe="";
	private String nomVoieEmplacement="";
	private String numVoieEmplacement="";
	private String numEmplacement="";
	private String etatEmplacement="";	
	private String redevbaleActif="";
	
	private String anneeFacture="";
	private String etatFacture="";
	private String montantFactureMax="";
	private String montantFactureMin="";
	private String numFacture="";
	private String dateFactureMax="";
	private String dateFactureMin="";
	
	private String typePaiement="";
	private String etatPaiement="";
	
	private String etatOuvrage="";
	private String vue="";
	
	private String linkExportOuvrage="";
	private String linkExportPaiement="";
	
	Connecteur con = InitialisationConnexionLectureConfiguration.getConnexion();
	private String anneeEnCours=new SimpleDateFormat("yyyy").format(new Date());
	private String sqlExportOuvrage="";
	private String sqlExportPaiement="";
	private String couleur="bgcolor=\"#FFFFF5\"";

	StaticManipHtml mHtml = new StaticManipHtml();	
	RequestRedevable reqRedevable = new RequestRedevable();
	/*
	 * tranformer date dd/mm/yyyy en yyyy-mm-dd
	 */
	private String inverserDate(String strDate) {
		SimpleDateFormat sdf1= new SimpleDateFormat("dd/mm/yyyy");
		SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-mm-dd");
		try {
			return sdf2.format(sdf1.parse(strDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	 
	/**
	 * @return the resultatRecherche
	 */
	public final String getResultatRecherche() {
		return resultatRecherche;
	}

	public void setRequest(HttpServletRequest request)
	{
		System.out.println(">>>setRequest in BaffResultatExport");
		/////////////////////////////////////////////////////////
		 Enumeration param = request.getParameterNames();
		 String paramRechercheRedevable="";
		 while (param.hasMoreElements()) 
		 {
			 //paramRechercheRedevable = paramRechercheRedevable+ request.getParameter((String)param.nextElement());
			 String attribut =(String)param.nextElement();
			 String value = request.getParameter(attribut);
			 //if( value.length() == 0 )value =" ";
			 paramRechercheRedevable=paramRechercheRedevable + "\n"+attribut +"=" + value ; 			 
		}
		DebuggerLog4J.logger.debug("PARAMETRE DE RCHERCHE " + paramRechercheRedevable);
		System.out.println("Export request parameters "+paramRechercheRedevable);
		
		//Recuperation des parametres de recherche : 		
		if (request.getParameter("numRedevable") != null) { this.numRedevable = request.getParameter("numRedevable");  }
		if (request.getParameter("nomRedevable") != null) { this.nomRedevable = FonctionCommun.ajouterAntislash(request.getParameter("nomRedevable"));  }
		if (request.getParameter("prenomRedevable") != null) { this.prenomRedevable = FonctionCommun.ajouterAntislash(request.getParameter("prenomRedevable"));  }
		if (request.getParameter("raisonSociale") != null) { this.raisonSociale = FonctionCommun.ajouterAntislash(request.getParameter("raisonSociale"));  }
		if (request.getParameter("numEmplacement") != null) { this.numEmplacement = request.getParameter("numEmplacement");  }
		if (request.getParameter("etatEmplacement") != null) { this.etatEmplacement = request.getParameter("etatEmplacement");  }
		if (request.getParameter("numSecteur") != null) { this. codeSecteur = request.getParameter("numSecteur");  }
		if (request.getParameter("nomQuartier") != null) { this.nomQuartier = request.getParameter("nomQuartier");  }
		if (request.getParameter("numVoieEmplacement") != null) { this.numVoieEmplacement = request.getParameter("numVoieEmplacement");  }
		if (request.getParameter("nomVoieEmplacement") != null) { this.nomVoieEmplacement = FonctionCommun.ajouterAntislash(request.getParameter("nomVoieEmplacement"));  }
		if (request.getParameter("typeTaxe") != null) { this.typeTaxe = request.getParameter("typeTaxe");  }
		if (request.getParameter("typeBareme") != null) { this.typeBareme = request.getParameter("typeBareme");  }
		if (request.getParameter("profession") != null) { this.profession = FonctionCommun.ajouterAntislash(request.getParameter("profession"));  }
		if (request.getParameter("redevbaleActif") != null) { this.redevbaleActif = request.getParameter("redevbaleActif");  }
		
		if (request.getParameter("anneeFacture") != null) { this.anneeFacture = request.getParameter("anneeFacture");  }
		if (request.getParameter("etatFacture") != null) { this.etatFacture = request.getParameter("etatFacture");  }
		if (request.getParameter("montantFactureMax") != null) { this.montantFactureMax = request.getParameter("montantFactureMax");  }
		if (request.getParameter("montantFactureMin") != null) { this.montantFactureMin = request.getParameter("montantFactureMin");  }
		if (request.getParameter("numFacture") != null) { this.numFacture = request.getParameter("numFacture");  }
		if (request.getParameter("dateFactureMax") != null) { this. dateFactureMax = request.getParameter("dateFactureMax");  }
		if (request.getParameter("dateFactureMin") != null) { this.dateFactureMin = request.getParameter("dateFactureMin");  }
		
		if (request.getParameter("typePaiement") != null) { this. typePaiement = request.getParameter("typePaiement");  }
		if (request.getParameter("etatPaiement") != null) { this.etatPaiement = request.getParameter("etatPaiement");  }
		if (request.getParameter("etatOuvrage") != null) { this.etatOuvrage = request.getParameter("etatOuvrage");  }
		if (request.getParameter("vue") != null) { this.vue = request.getParameter("vue");  }
		
		/////////////////////////////////////////////////////////
		//Affichage du taleau resltat de la recherche: 
		
		Vector contenu =new Vector();
		String sqlRecherche="select DISTINCT r.numRedevable,r.nomredevable,r.prenom,r.numrue,r.adresse1,"+
				"e.numero,e.enactivite,e.numrue,e.adresse1,e.adresse2,e.adresse3,i.libelle ";
		if (vue.equalsIgnoreCase("facture")){
			sqlRecherche+=",f.numeroFacture ";
		}
		sqlExportOuvrage="select DISTINCT r.numRedevable,r.nomredevable,r.prenom,r.naturejuridique,r.civilite,r.nomjf,r.actif,"+
				  "r.numrue as r_numrue,r.complementnumerorueredevable,r.adresse1 as r_adresse1,r.adresse2 as r_adresse2,r.adresse3 as r_adresse3,"+
				  "r.codepostal as r_codepostal,r.ville as r_ville,apr.libelle as r_profession,r.numtel as r_numtel,"+
				  "r.numportable as r_numportable,r.email as r_email,i.libelle as typetaxe,"+
				  "e.numero,e.enactivite,e.codesecteur,e.numrue,e.complementNumeroRueEmpl,e.adresse1,e.adresse2,e.adresse3,"+
				  "e.codepostal,e.ville,e.numtel,e.numportable,e.email,ape.libelle as activite,"+
				  "b.libelle as bareme,a.id_article,a.nom,a.longueur,a.largeur,a.surface,a.quantite_article,a.etat ";
		sqlExportPaiement="select DISTINCT r.numRedevable,r.nomredevable,r.prenom,r.naturejuridique,r.civilite,r.nomjf,r.actif,"+
				  "r.numrue as r_numrue,r.complementnumerorueredevable,r.adresse1 as r_adresse1,r.adresse2 as r_adresse2,r.adresse3 as r_adresse3,"+
				  "r.codepostal as r_codepostal,r.ville as r_ville,apr.libelle as r_profession,r.numtel as r_numtel,"+
				  "r.numportable as r_numportable,r.email as r_email,i.libelle as typetaxe,"+
				  "f.numeroFacture,f.anneeEx,str_to_date(f.datecreation,'%d/%m/%Y') as dateFacture,f.etat,f.montantTotal,f.solde,"+
				  "tp.typepayement,str_to_date(p.datepayement,'%d/%m/%Y') as datePaiement, p.montantpayement,p.idPayement,p.etat as p_etat ";
		String from=" from redevable r ";
		from+=" join emplacementgeneral e on e.numRedevable=r.numRedevable";
		from+=" join activiteprofession apr on r.numeroProfession=apr.code";
		from+=" join activiteprofession ape on e.codePrefession=ape.code";
		from+=" join imputation i on i.idImputation=e.codeType and e.anneeExerciceImputation=i.anneeExercice";
		from+=" join elementfacturation el on el.numeroEmplacement=e.numero";
		from+=" join article a on el.numero=a.id_ElementFacturation";
		from+=" join facture f on f.idclient=r.numRedevable and f.typeTaxe=i.libelle";
		from+=" join bareme b on b.code=a.codeBareme and b.anExercice="+anneeEnCours;
		from+=" join lignefacture lf on lf.idfacture=f.numerofacture and lf.idarticle=a.id_article";
		//Payement est optionnel (car il y a des factures sans aucun paiement)
		from+=" left join lignepayement lp on f.idfacture=lp.idfacture"+
			  " left join payement p on lp.idpayement=p.idpayement"+
			  " left join typepayement tp on p.idtypepayement=tp.idtypepayement";
			  
		
		//Exclusion
		String where=" WHERE r.numRedevable<>-1 and f.etat <> 'preFacturation' ";
		String orderBy=" order by r.nomRedevable";
		
		if(!numRedevable.isEmpty()){
			where+=" AND r.numRedevable="+numRedevable;
		}
		if(!nomRedevable.isEmpty()){
			where+=" AND r.nomRedevable LIKE '%"+ nomRedevable + "%'";
		}
		if(!prenomRedevable.isEmpty()){
			where+=" AND r.prenom LIKE '%"+ prenomRedevable + "%'";
		}
		if(!redevbaleActif.isEmpty()){
			where+=" AND r.actif = '"+redevbaleActif+"'";
		}
		if(!profession.isEmpty()){
			where+=" AND apr.libelle = '"+ profession + "'";
		}
		if(!raisonSociale.isEmpty()){
			where+=" AND e.raisonSocial LIKE '%"+ raisonSociale + "%'";
		}
		
		RequestParametres requestParametres = new RequestParametres();
		String ville = requestParametres.getVille();
		if (!numEmplacement.isEmpty()) {
			if (ville.equalsIgnoreCase("toulon"))
				where += "AND e.numeroEmplacementPersonalise ='"+ numEmplacement + "'";
			else
				where +=" AND e.numero ='"+ numEmplacement + "'";
		}
		if(!etatEmplacement.isEmpty()){
			where+=" AND e.enActivite ='"+ etatEmplacement + "'";
		}		
		if(!codeSecteur.isEmpty()){
			where+=" AND e.codeSecteur ="+ codeSecteur;
		}
		if(!nomQuartier.isEmpty()){
			where+=" AND e.nomQuartier ='"+ nomQuartier + "'";
		}
		if(!numVoieEmplacement.isEmpty()){
			where+=" AND e.numRue ="+ numVoieEmplacement;
		}
		if(!nomVoieEmplacement.isEmpty()){
			where+=" AND e.adresse1 LIKE '%"+ nomVoieEmplacement + "%'";
		}
		if(!typeTaxe.isEmpty()){
			where+=" AND i.libelle ='" + typeTaxe+ "'";
		}
		if(!typeBareme.isEmpty()){
			where+=" AND  b.code ='" + typeBareme + "'";
		}
		
		if(!anneeFacture.isEmpty()){
			where+=" AND f.anneeEx='"+anneeFacture+"'";
		}

		if(!etatFacture.isEmpty()){
			if (etatFacture.equalsIgnoreCase("PAYEE"))
				where+=" AND f.solde = 0 AND f.etat ='VALIDE'";
			else if (etatFacture.equalsIgnoreCase("IMPAYE"))
				where+=" AND f.solde!= 0 AND f.etat ='VALIDE'";
			else
				where+=" AND f.etat ='"+ etatFacture+"'";
		}
		
		if(!numFacture.isEmpty()){
			where+=" AND f.numerofacture ="+ numFacture;
		}
		if(!montantFactureMin.isEmpty()){
			where+=" AND f.montantTotal >="+ montantFactureMin;
		}
		if(!montantFactureMax.isEmpty()){
			where+=" AND f.montantTotal<="+montantFactureMax;
		}
		if(!dateFactureMin.isEmpty()){
			where+=" AND str_to_date(f.datecreation,'%d/%m/%Y')>='" + inverserDate(dateFactureMin)+ "'";
		}
		if(!dateFactureMax.isEmpty()){
			where+=" AND str_to_date(f.datecreation,'%d/%m/%Y')<='" + inverserDate(dateFactureMax)+ "'";
		}
		if(!etatPaiement.isEmpty()){
			where+=" AND p.etat='"+etatPaiement+"'";
		}	
		if(!typePaiement.isEmpty()){
			where+=" AND p.idTypePayement="+typePaiement;
		}		
		if(!etatOuvrage.isEmpty()){
			where+=" AND a.etat='" +etatOuvrage+ "'";
		}
		
		//Query pour liste résultat
		sqlRecherche+=from+where+orderBy;
		//Query pour rapport excel ouvrage 
		sqlExportOuvrage+=from+where+orderBy;
		//Query pour rapport excel paiement
		sqlExportPaiement+=from+where+" order by dateFacture, f.numerofacture";
		System.out.println("sqlRecherche="+sqlRecherche);
		System.out.println("sqlExportOuvrage="+sqlExportOuvrage);
		System.out.println("sqlExportpaiement="+sqlExportPaiement);
		Statement instruction;
		StringBuilder list = new StringBuilder();
		//Générer la liste du résultat -> resultatRecherche
		try {
			instruction = con.connexion.createStatement();
			try{			
				ResultSet rs = instruction.executeQuery(sqlRecherche);
				rs.last();
				int count=rs.getRow();
				nombreRedevable = "Nombre de resultats = "+count;
				System.out.println("Count.... "+count);
				if(count==0){
					linkExportOuvrage="";linkExportPaiement="";
					list.append("<table><tr>" +
							"	<font size=\"4\">Aucun resultat ne correspond avec les parametres indiquees  </font>" +
							"</tr></table>" );
				}
				else if (count> 25000){
					list.append("<table><tr><font size=\"4\">Il y a plus de 25000 resultats " +
							", veuillez remplir un ou plusieurs champs de filtre de recherche pour obtenir les informations d�taill�es.  </font>" +
							" </tr></table>" );
				}
				else{
					linkExportOuvrage="<a href='javascript:exporterOuvrage()'><font face='Arial' size='4'><b>Export Ouvrages : </b></font><img border='0' src='images/ok.gif' align='absmiddle'></a>";
					linkExportPaiement="<a href='javascript:exporterPaiement()'><font face='Arial' size='4'><b>Export Factures,Paiements : </b></font><img border='0' src='images/ok.gif' align='absmiddle'></a>";
					//linkExportOuvrage="<a href='exportExcel?sql="+URLEncoder.encode(sqlExportOuvrage,"UTF-8")  +"'><font face='Arial' size='4'><b>Exporter : </b></font><img border='0' src='images/ok.gif' align='absmiddle'></a>";
					list.append("<table bordercolorlight=\"#C0C0C0\" bordercolordark=\"#FFFFFF\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"); 
					list.append("<tr  align=\"center\" >");
					list.append("<td "+ couleur+ " width='10%'><b><font size=\"4\">N redevable</font></b></td>");		
					list.append("<td "+ couleur+ " width='20%'><b><font size=\"4\">Nom redevable</font></b></td>");
					list.append("<td "+ couleur+ " width='25%'><b><font size=\"4\">Adresse redevable</font></b></td>");			
					list.append("<td "+ couleur+ " width='10%'><b><font size=\"4\">Type emplacement</font></b></td>");
					list.append("<td "+ couleur+ " width='25%'><b><font size=\"4\">Adresse emplacement</font></b></td>");
					if(vue.equalsIgnoreCase("facture")){
						list.append("<td "+ couleur+ " width='10%'><b><font size=\"4\">Facture</font></b></td>");
					}					
					list.append("</tr>");	
					rs.beforeFirst();
					while (rs.next()) {
						String link="./entree?action=empl_gestion_redevable.jsp" +
								"&choix=modifier&boton=ajouter&typeRecherche=role" +				
								"&numRedevable=" + rs.getString("numRedevable")+
								"&etatEmplacement=" + rs.getString("enActivite");
						String linkEmplacment= "./entree?action=empl_gestion_emplacementodp.jsp" +
								"&choix=modifier&optionTransfert=role&origine=origine" +
								"&numEmplacment="+ rs.getInt("numero");
						String ligne ="";		
						ligne+="<tr name=\"ligne\" onclick=\"selec(this)\" onmousemove=\"lavend(this)\" onmouseout =\"transp(this)\" align=\"center\" >";	
						ligne+="<td><a href= "+ link+" >"+ rs.getString("numRedevable")+"</a></td>";
						ligne+="<td>"+ rs.getString("nomRedevable")+ " "+ rs.getString("prenom")+"</td>";
						ligne+="<td>"+ rs.getString("r.numRue")+ " " + rs.getString("r.adresse1")+"</td>";			
								
						String typeDeTaxeEmplacement= rs.getString("i.libelle");
						if ( typeDeTaxeEmplacement == null || typeDeTaxeEmplacement.equalsIgnoreCase("") )
							typeDeTaxeEmplacement="-";
						ligne+="<td>"+ typeDeTaxeEmplacement+"</td>";
						String adresseEmplacement=rs.getString("e.numRue")+" "
								+ rs.getString("e.adresse1")+ " "
								+ rs.getString("e.adresse2")+ " "
								+ rs.getString("e.adresse3");		
						ligne+="<td><font color=\"#0000FF\"><a href="+ linkEmplacment + ">" +
								adresseEmplacement+"</a></font></td>";
						
						if(vue.equalsIgnoreCase("facture")){
							ligne+="<td>" +
									"<img src=\"./images/pdfbleue.png\" border=\"0\" height=\"21\" width=\"20\">&nbsp"+
									"<a  href=./entree?action=gestionFacture.jsp&numeroFacture="+ rs.getString("numeroFacture") + ">" +
									rs.getString("numeroFacture")+".pdf" +"</a></td>";
						}					
						ligne+="</tr>";	
						list.append(ligne);								
						}		
							
					list.append("</table>");		
				}
				
				resultatRecherche=list.toString();
			}
			catch( Exception ex){
				ex.printStackTrace();
			}
			finally {			
				instruction.close();
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * @param resultatRecherche the resultatRecherche to set
	 */
	public final void setResultatRecherche(String resultatRecherche) {
		this.resultatRecherche = resultatRecherche;
	}

	/**
	 * @return the nombreRedevable
	 */
	public final String getNombreRedevable() {
		return nombreRedevable;
	}

	/**
	 * @param nombreRedevable the nombreRedevable to set
	 */
	public final void setNombreRedevable(String nombreRedevable) {
		this.nombreRedevable = nombreRedevable;
	}

	public String getLinkExportOuvrage() {
		return linkExportOuvrage;
	}

	public void setLinkExportOuvrage(String linkExportOuvrage) {
		this.linkExportOuvrage = linkExportOuvrage;
	}

	public String getSqlExportOuvrage() {
		return sqlExportOuvrage;
	}

	public void setSqlExportOuvrage(String sqlExportOuvrage) {
		this.sqlExportOuvrage = sqlExportOuvrage;
	}

	public String getSqlExportPaiement() {
		return sqlExportPaiement;
	}

	public void setSqlExportPaiement(String sqlExportPaiement) {
		this.sqlExportPaiement = sqlExportPaiement;
	}

	public String getLinkExportPaiement() {
		return linkExportPaiement;
	}

	public void setLinkExportPaiement(String linkExportPaiement) {
		this.linkExportPaiement = linkExportPaiement;
	}
	
	
}
