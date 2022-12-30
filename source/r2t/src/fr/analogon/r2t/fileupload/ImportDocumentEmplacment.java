package fr.analogon.r2t.fileupload;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.request.RequestEmplacement;

public class ImportDocumentEmplacment extends HttpServlet 
{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException 
{

try 
{
	FichierDeConfiguration  fichierDeConfiguration = InitialisationConnexionLectureConfiguration.fichierDeConfiguration;
	
	String tailleMaxDocument = fichierDeConfiguration.getTailleMaxDocument();
	DataUpload	form 	= new DataUpload(req,resp);
	String		Buffer	= new String();	
	
	//inseration dans la BD	 :			
	String idEmplacement="";
	String source="";
	String lien="";
	String fileName=req.getParameter("fileName");
	String action =req.getParameter("action");
	DebuggerLog4J.logger.debug(">>>>action>>>"+action);
	int pos = 0;
	pos = fileName.lastIndexOf("/");
	if (pos == -1)  pos = fileName.lastIndexOf("\\");
	if (pos == -1)  
		pos = 0;
	else
		pos ++;
	fileName = fileName.substring(pos, fileName.length());
	DebuggerLog4J.logger.debug("fileName="+fileName);
	
	RequestEmplacement reqEmpalcment = new RequestEmplacement();
	
	if(req.getParameter("source")!= null  )
		source = req.getParameter("source");
	if(req.getParameter("numEmplacment")!= null  )
		idEmplacement = req.getParameter("numEmplacment");
	
	
   if(source.equalsIgnoreCase("emplacement"))
	{			
		lien ="./entree?action=empl_gestion_emplacementodp.jsp" +
	     "&choix=modifier&optionTransfert=role&origine=origine" +
	     "&numEmplacment="+idEmplacement;
	}
	if (action.equalsIgnoreCase("deleteDocumentEmplacement"))
	{
		reqEmpalcment.supprimerDocumentEmplacementGeneral(fileName,idEmplacement);
	}
	else if (action.equalsIgnoreCase("addDocumentEmplacement"))
	{
		form.Init();
		Buffer = form.getValueFileName("myDocument");
		String path = fichierDeConfiguration.getCheminData()+"/emplacements" ;	
		File pathRepertoireEmplacement = new File( path+"//"+idEmplacement);
		pathRepertoireEmplacement.mkdir();
		//Transfert de l'Document du poste client vers le serveur :
		String fileNameSaved = reqEmpalcment.ajouterDocumentEmplacement(fileName, idEmplacement);
		form.getUploadFileName("myDocument",pathRepertoireEmplacement + "//"+ fileNameSaved);
		lien=lien+"&errortailleDocument=ok";
		DebuggerLog4J.logger.debug("----FIN TRANSFERT---");
			
	}
	
	//Redirection vers la page actualisé
	resp.sendRedirect(lien);
	
			
	
}
catch (Exception e) 
{
	DebuggerLog4J.logger.fatal("Ereur" + e.getMessage());
}				
}
}
