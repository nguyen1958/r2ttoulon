package fr.analogon.r2t.facturation.pleiade;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import fr.analogon.r2t.Utilitaire.FichierDeConfiguration;
import fr.analogon.r2t.main.InitialisationConnexionLectureConfiguration;
import fr.analogon.r2t.pojo.Facture;


public class SFtpClient {
	private Session session=null;
	ChannelSftp sftpChannel=null;
	private FichierDeConfiguration fichierDeConfiguration  =  InitialisationConnexionLectureConfiguration.getFichierDeConfiguration();
		
	public SFtpClient(){}	
	
    public void connect() throws JSchException {
    	try{   		
    		JSch jsch = new JSch();	
            // Uncomment the line below if the FTP server requires certificate	
            // jsch.addIdentity("private-key-path);	
            session = jsch.getSession(fichierDeConfiguration.getUserftp(),
            						  fichierDeConfiguration.getServeurftp(),
            						  Integer.valueOf(fichierDeConfiguration.getPortftp()));
            session.setPassword(fichierDeConfiguration.getPasswordftp());
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println(session.getServerVersion()+"|"+session.getServerAliveCountMax());
            //Ouverture du canal
    		Channel channel = session.openChannel("sftp"); 
    		//Connexion au canal
            channel.connect(); 
            //cast en canal sftp
            sftpChannel = (ChannelSftp) channel;            
    	}
    	catch (JSchException e){
    		System.out.println("Echec de connexion au serveur SFTP");
    		throw e;
    	}
    }
    
    public void disconnect(){
    	sftpChannel.exit();
    	session.disconnect();
    	
    }
    
    public String uploadFile(String source,Facture facture) {
    	String target="";
    	try{        
            String pathTarget=fichierDeConfiguration.getCheminfactureftp()+"/"+facture.getNomDossier();
            target=pathTarget+"/"+facture.getNumeroFacture()+".pdf";
        	try{
        		sftpChannel.cd(pathTarget);
            	System.out.println("name:"+pathTarget+"|pwd:"+sftpChannel.pwd()+" remoteDirectory exist");
        	}
        	catch (Exception e){
        		System.out.println("name:"+pathTarget+"| remoteDirectory not found ...");
				sftpChannel.mkdir(pathTarget);
				sftpChannel.cd(pathTarget);
        	}
            System.out.println("target:"+target);
            sftpChannel.put(source,target);
    	}
    	catch (SftpException e) {
    		System.out.println(e.getMessage());
    		target="fichier source inexistant";
		}
    	
    	return target;
    }
    
}
