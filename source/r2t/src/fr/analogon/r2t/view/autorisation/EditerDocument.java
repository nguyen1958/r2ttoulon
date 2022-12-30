package fr.analogon.r2t.view.autorisation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import java.io.IOException;
import java.io.InputStream;

import java.util.Enumeration;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.pojo.Autorisation;
import fr.analogon.r2t.pojo.Emplacement;
import fr.analogon.r2t.pojo.Redevable;
import fr.analogon.r2t.request.RequestAutorisation;
import fr.analogon.r2t.request.RequestRedevable;
/*
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
filien
*/

public class EditerDocument extends  fr.analogon.r2t.main.RacineServlet 
{  
     
	public  void doMain(HttpServletRequest request, HttpServletResponse response)  throws IOException
    {
		Enumeration elements=request.getParameterNames();
		while(elements.hasMoreElements()){
			String parametre=(String) elements.nextElement();
			System.out.println(">>>> "+ parametre+":"+request.getParameter(parametre));
		}		
		
		RequestAutorisation reqAutorisation= new RequestAutorisation();
		RequestRedevable reqRedevable= new RequestRedevable();
		
		String idautorisation=request.getParameter("idautorisation");
		
		response.setCharacterEncoding("ISO-8859-1");
		
		//System.out.println(">>>> paths "+this.getServletContext().getRealPath("/r2tData/modele/modeleAutorisation1.docx"));
		/*
		try
        {
			Autorisation autorisation=reqAutorisation.getAutorisation(idautorisation);
			Redevable redevable = reqRedevable.getRedevable(autorisation.getNumRedevable());
			Emplacement emplacement=reqAutorisation.getEmplacement(idautorisation);
			List<Emplacement> listeEmplacement= reqAutorisation.getListeEmplacement(idautorisation);
			String date=GestionDate.getDateAujourdhuiString();
            // 1) Load Docx file by filling Velocity template engine and cache
            // it to the registry
            //InputStream in = TestVelocity.class.getResourceAsStream( "ModeleAutorisation1.docx" );
			String modele=this.getServletContext().getRealPath("/r2tData/modele/modeleAutorisation1.docx");
			InputStream in = new FileInputStream( new File(modele));
            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport( in, TemplateEngineKind.Velocity );        	
        	String parametre=this.getServletContext().getRealPath("/r2tData/modele/modelData.txt");
        	System.out.println(">>>> parametre = "+parametre);
            
            // 2) Create context Java model
        	//IContext context = report.createContext();
            context.put("date",date);
            context.put("parametre",parametre);
            context.put("autorisation",autorisation);
            context.put("redevable",redevable);
            context.put("emplacement",emplacement);
            context.put("listeEmplacement",listeEmplacement);
            // 3) Generate report by merging Java model with the Docx
            File outputFile=new File( "temp.docx" );
            OutputStream out = new FileOutputStream(outputFile);
            report.process( context, out );
            System.out.println("outputFile >>>> "+outputFile.getAbsolutePath());
            response.setContentType(this.getServletContext().getMimeType(outputFile.getAbsolutePath()));
            //On récupère le flux de sortie pour écrire
	        ServletOutputStream outputStream = response.getOutputStream();			
		    FileInputStream inputStream = new FileInputStream(outputFile);	
	        byte[] bytes = new byte[32000];
	        int bytesRead;
	        while ((bytesRead = inputStream.read(bytes, 0, bytes.length)) != -1)
		      {
		    	 outputStream.write(bytes, 0, bytesRead);
		      }			
        }
		catch (Exception e){
			e.printStackTrace();
		}
		*/
    }
}
	
