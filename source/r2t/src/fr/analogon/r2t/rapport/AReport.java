package fr.analogon.r2t.rapport;

import java.io.File;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.view.JasperViewer;
/**
 * 
 * @author paul
 * C'est une classe abstraite définissant des paramétres nécessaires pour le module ireport
 * constructeurs selon les paramétres fournis en entrées pour ireport
 * Définir les méthodes abstraits surchargés selon le type de flux en sortie
 * Classes dérivées : ReportPdf, ReportXls, ReportHtml
 */
public abstract class AReport {
	protected String modele;
	protected HashMap hashMap;
	protected Connection connection;
	protected JRFileVirtualizer virtualizer=new JRFileVirtualizer(2);
	
	private Logger log = Logger.getLogger(AReport.class);
	
	public AReport(){}
	
	public AReport(String modele,HashMap hashMap,Connection connection){
		this.hashMap=hashMap;
		this.modele=modele;
		this.connection=connection;
	}
	
	public HashMap getHashMap() {
		return hashMap;
	}

	public void setHashMap(HashMap hashMap) {
		this.hashMap = hashMap;
	}
	
	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
/**
 * L'objet jasperPrint utilise un beanCollection (objet jpa) s'il n'est pas null
 * sinon un object connection (requete sql)  
 */
	public JasperPrint jasperPrint() throws JRException{
		//Utilisation swap sur disque permet de reduire consommation memoire
		//Pas besoin d'augmenter stack size du jvm 
		//et éviter plantage outOfMemory
		if (hashMap!=null){			
			hashMap.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		}
						
		if (connection!=null){	
			return JasperFillManager.fillReport(modele,hashMap,connection);		
		}
		else
		{
			return JasperFillManager.fillReport(modele,hashMap);
		}

	}
	/**
	 * Afficher le rapport avec l'utilitaire fournie par jasper
	 * @throws JRException
	 */
	public void reportToFile() throws JRException{
		JasperViewer.viewReport(jasperPrint(),false)	;	
	}
	
	/**
	 * Enregistrer le rapport dans le fichier (type File)
	 * @param file
	 * @throws JRException
	 */
	public abstract void reportToFile(File file) throws JRException;
	/**
	 * Enregistrer le rapport dans le fichier (chemin et nom du fichier)
	 * @param fileName
	 * @throws JRException
	 */
	public abstract void reportToFile(String fileName) throws JRException;
	/**
	 * renvoie le contenu du rapport dans le flux sortie (type OutputStream)
	 * @param outputStream
	 * @throws JRException
	 */
	public abstract void reportToFile(OutputStream outputStream) throws JRException;
}
