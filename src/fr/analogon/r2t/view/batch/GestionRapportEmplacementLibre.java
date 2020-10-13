package fr.analogon.r2t.view.batch;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.facturation.CreerRapportComptable;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.rapport.ReportPdf;
import fr.analogon.r2t.request.RequestRaportComptable;


/**
 * paul
 * 
 * @version 2.0
 * @since 2.0
 */
public class GestionRapportEmplacementLibre extends fr.analogon.r2t.main.RacineServlet 
{
	FichierDeConfiguration fichierDeConfiguration =  InitialisationConnexionLectureConfiguration.getFichierDeConfiguration();
	Connection connexionBD = InitialisationConnexionLectureConfiguration.connexion.connexion;
	
	public synchronized void doMain(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		try {
			String modele=fichierDeConfiguration.getCheminRoleJRXML()+"ListeEmplacementLibre/ListeEmplacementLibreToulon.jasper";
			File file=new File(modele);
			HashMap parameters = new HashMap();
			//Utilisation swap sur disque permet de reduire consommation memoire
			//Pas besoin d'augmenter stack size du jvm 
			//et éviter plantage outOfMemory
			JRFileVirtualizer virtualizer=new JRFileVirtualizer(2);		
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);		
			JasperPrint jasperprint = JasperFillManager.fillReport(file.getAbsolutePath(),parameters,connexionBD);
			response.setContentType("application/pdf");
			//On récupère le flux de sortie pour écrire
	        ServletOutputStream outputStream = response.getOutputStream();  
	        JasperExportManager.exportReportToPdfStream(jasperprint, outputStream);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}


