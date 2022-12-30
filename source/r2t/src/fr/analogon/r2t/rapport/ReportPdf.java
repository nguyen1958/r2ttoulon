package fr.analogon.r2t.rapport;

import java.io.File;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

public class ReportPdf extends AReport{
	
	public ReportPdf(){
		super();
	}

	public ReportPdf(String modele,HashMap hashMap,Connection connection){
		super(modele,hashMap,connection);
	}
	
	@Override
	public void reportToFile(String outputFileName) throws JRException {
		// TODO Auto-generated method stub
		JasperExportManager.exportReportToPdfFile(jasperPrint(), outputFileName);
	}

	@Override
	public void reportToFile(File file) throws JRException {
		// TODO Auto-generated method stub
	
	}
	
	@Override
	public void reportToFile(OutputStream outputStream) throws JRException {
		// TODO Auto-generated method stub
		JasperExportManager.exportReportToPdfStream(jasperPrint(), outputStream);
	}
	
}
