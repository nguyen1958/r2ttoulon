package fr.analogon.r2t.fileupload;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.main.DebuggerLog4J;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.pojo.Article;
import fr.analogon.r2t.request.RequestEmplacement;
import fr.analogon.r2t.request.RequestOuvrage;

public class ImportImageEmplacment extends HttpServlet 
{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
		{
		
		try 
		{
			FichierDeConfiguration  fichierDeConfiguration = InitialisationConnexionLectureConfiguration.fichierDeConfiguration;
			
			String tailleMaxPhotos = fichierDeConfiguration.getTailleMaxImage();
			DataUpload	form 	= new DataUpload(req,resp);
			String		Buffer	= new String();	
			
			//inseration dans la BD	 :			
			String idEmplacement="";
			String idarticle="";
			String source="";
			String lien="";
			if(req.getParameter("source")!= null  )
				source = req.getParameter("source");
			if(req.getParameter("numEmplacment")!= null  )
				idEmplacement = req.getParameter("numEmplacment");
			if(req.getParameter("idarticle")!= null  )
				idarticle = req.getParameter("idarticle");
			
			if (source.equalsIgnoreCase("ouvrage"))
			{
				RequestOuvrage reqOuvrage = new RequestOuvrage();
				Article ouvrage = reqOuvrage.getArticle(idarticle);
				
				lien ="./entree?action=empl_saisie_element_facturation.jsp&traitement=article" +
				"&choix=modifier&idarticle="+ouvrage.getId()+
				"&numElementFacturation=" + ouvrage.getIdElementFacturation()+
				"&numEmplacment=" + ouvrage.getNumEmplacment()+
				"&exercice=" +ouvrage.getAnExercice()+
				"&numRedevable=" +ouvrage.getNumRedevable()+
				"&codeSecteur="+ouvrage.getCodeSecteur();			
			}
			else if(source.equalsIgnoreCase("emplacement"))
			{			
				lien ="./entree?action=empl_gestion_emplacementodp.jsp" +
			     "&choix=modifier&optionTransfert=role&origine=origine" +
			     "&numEmplacment="+idEmplacement;
			}
			
			form.Init();
			Buffer = form.getValueFileName("myimage");
			//Taille de l'image en Koctet
			int tailleMaxImage = Integer.parseInt(tailleMaxPhotos) ;
			long tailleImage = form.getSizeFileName("myimage") /1000  ;
			DebuggerLog4J.logger.error("////La taille de l'image = "+tailleImage + " Koctet ////" ); 
			if (tailleImage > tailleMaxImage )
			{
				DebuggerLog4J.logger.error("La taille de l'image + "+ tailleImage+" Koctet est  > a "+tailleMaxImage+" koctet, Impossible de telecharger l'image " );
				lien=lien+"&errortailleimage="+tailleMaxImage;
			}
			else
			{	
				DebuggerLog4J.logger.debug("La taille de l'image = "+tailleImage+" Koctet est < a "+tailleMaxImage+"  koctet , telchargement de l'image en cours..." );
				DebuggerLog4J.logger.debug("Demande de transfet d'image " );
				RequestEmplacement reqEmpalcment = new RequestEmplacement();
				String		Path = fichierDeConfiguration.getCheminPhotosEmplacement();		
				
				String nomImage =reqEmpalcment.ajouterImageAunEmplacement(idEmplacement);
				//Transfert de l'image du poste client vers le serveur :
				form.getUploadFileName("myimage",Path + "//"+ nomImage);
				lien=lien+"&errortailleimage=ok";
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
