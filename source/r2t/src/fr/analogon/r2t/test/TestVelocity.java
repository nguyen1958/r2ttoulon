/*
package fr.analogon.r2t.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import fr.analogon.r2t.Utilitaire.GestionDate;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.pojo.Article;
import fr.analogon.r2t.pojo.Autorisation;
import fr.analogon.r2t.pojo.Redevable;
import fr.analogon.r2t.pojo.Emplacement;
import fr.analogon.r2t.request.RequestAutorisation;
import fr.analogon.r2t.request.RequestRedevable;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public class TestVelocity {


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		RequestAutorisation reqAutorisation= new RequestAutorisation();
		RequestRedevable reqRedevable= new RequestRedevable();
		new InitialisationConnexionLectureConfiguration();
		String date=GestionDate.getDateAujourdhuiString();
		try
        {
			Autorisation autorisation=reqAutorisation.getAutorisation("5");
			Redevable redevable = reqRedevable.getRedevable(autorisation.getNumRedevable());
			Emplacement emplacement = reqAutorisation.getEmplacement("5");
			List<Emplacement> listeEmplacement= reqAutorisation.getListeEmplacement("5");
			System.out.println(">>> "+redevable.getRaisonSocialeRedevable());
            // 1) Load Docx file by filling Velocity template engine and cache
            // it to the registry
            //InputStream in = TestVelocity.class.getResourceAsStream( "ModeleAutorisation1.docx" );
			InputStream in = new FileInputStream( new File("e:/ModeleAutorisation1.docx" ));
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport( in, TemplateEngineKind.Velocity );
            	
            // 2) Create fields metadata to manage lazy loop (#forech velocity)
            // for table row.
            /*FieldsMetadata metadata = report.createFieldsMetadata();
            metadata.addFieldAsList( "listeOuvrage.nom");
            metadata.addFieldAsList( "listeOuvrage.libelle" );
            //report.setFieldsMetadata(metadata);

            
            // 2) Create context Java model
            IContext context = report.createContext();
            context.put("date",date);
            context.put("autorisation",autorisation);
            context.put("redevable",redevable);
            context.put("emplacement",emplacement);
            context.put("listeEmplacement",listeEmplacement);
            
            System.out.println(listeEmplacement.get(0).getListeOuvrage().size());
            // 3) Generate report by merging Java model with the Docx
            OutputStream out = new FileOutputStream( new File( "e:/toto.docx" ) );
            report.process( context, out );
          
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        catch ( XDocReportException e )
        {
            e.printStackTrace();
        }
 
        System.out.println("fin !!!!");
	}
	

}
*/
