package fr.analogon.r2t.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;



public class ExportExcel extends  fr.analogon.r2t.main.RacineServlet 
{  
     
	@SuppressWarnings("deprecation")
	public  void doMain(HttpServletRequest requestObj, HttpServletResponse responseObj)  throws IOException
    {
		try{ 
			System.out.println("ExportExcel ...");
		    //get parameters store into the hashmap
		    HashMap paramsMap = new HashMap();
		    Enumeration paramEnum = requestObj.getParameterNames();
		    while(paramEnum.hasMoreElements())
		    {
		       String paramName = (String)(paramEnum.nextElement());
		       paramsMap.put(paramName, requestObj.getParameter(paramName));
		       System.out.println(paramName+"|"+requestObj.getParameter(paramName));
		    }
		    
		    Connection connexionr2t= InitialisationConnexionLectureConfiguration.connexion.connexion;
		    //String sql=requestObj.getParameter("sql");
		    //String type=requestObj.getParameter("type");
		    String type=requestObj.getParameter("typeExport");
		    System.out.println("type="+type);
		    String sql="";
		    String fileName="";
		    String pathRapportJRXML="";
		    if (type.equalsIgnoreCase("ouvrage")){
		    	fileName = "exportOuvrage.xls";
		    	pathRapportJRXML = fichierDeConfiguration.getCheminJRXMLExport() +"ExportRedevable.jrxml";
		    	sql=requestObj.getParameter("sqlExportOuvrage");
		    }
		    if (type.equalsIgnoreCase("paiement")){
		    	fileName = "exportFacturePaiement.xls";
		    	pathRapportJRXML = fichierDeConfiguration.getCheminJRXMLExport() +"ExportPaiement.jrxml";
		    	sql=requestObj.getParameter("sqlExportPaiement");
		    }
		    System.out.println("sql=>"+sql);
		    /**-------------------------------------------------------------------
			*Impression du rapport excel											
			*	charge le rapport jrxml
			*	prépare les paramétres à passer au rapport
			*	Compile le rapport
			*	lie les paramétres au rapport
			*	créer le rapport au format excel
			*
		    */	    
	    	System.out.println("pathRapportModele="+pathRapportJRXML);	
			JasperDesign jasperDesign = JRXmlLoader.load(pathRapportJRXML);	
			System.out.println("JRXmlLoader.load");
			//Affecter la requete 
			JRDesignQuery jdq= new JRDesignQuery();
			jdq.setText(sql);
			jasperDesign.setQuery(jdq);
			System.out.println("set Query");
			JasperReport jasperReport=JasperCompileManager.compileReport(jasperDesign);
			System.out.println("JasperCompile report");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connexionr2t);
			System.out.println("JasperFillManager.fillReport");
			responseObj.setHeader("Content-disposition","inline; filename=\"" +fileName+"\"");
			System.out.println("mimetype="+getServletContext().getMimeType(fileName));
			//responseObj.setCharacterEncoding("ISO-8859-1");
			responseObj.setCharacterEncoding("UTF-8");
			//On récupère le flux de sortie pour écrire
	        ServletOutputStream outputStream = responseObj.getOutputStream();
	        JRXlsExporter exporter = new JRXlsExporter();
	        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,true);
	        exporter.setParameter(JRXlsExporterParameter.MAXIMUM_ROWS_PER_SHEET,60000);
	        exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT,jasperPrint);
			exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,outputStream);
			exporter.exportReport();	
	    }
	    catch (Exception ex){
	    	ex.printStackTrace();
	    }
	 }        
}
	
