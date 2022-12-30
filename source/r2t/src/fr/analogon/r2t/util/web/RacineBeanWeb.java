package fr.analogon.r2t.util.web;




/**
 * Bean etendue par tout les beans developpés chez APM . Sofien CHARFI
 * 
 * @version 1.2
 * @since 1.0
 */
public class RacineBeanWeb
{
	
	
	
	
	
	
	/**
	 * Le fichier de Config. Cette variable est protected pour etre manipulable
	 * directement lorsque la classe est etendue
	 */
	public IConfig conf = new ConfigurationWeb();


	/**
	 * Un constructeur qui ne fait rien. . Sofien CHARFI
	 * 
	 * @since 1.2
	 */
	public RacineBeanWeb() 
	{
		////System.out.println("RacineBeanWeb()");
	}

	/**
	 * Un constructeur avec un fichier de conf . Sofien CHARFI
	 * 
	 * @since 1.2
	 */
	public RacineBeanWeb(IConfig conf) {
		this.conf = conf;	
		//System.out.println("RacineBeanWeb(ConfigurationWeb conf)");
	}

	public IConfig getConf() {
		return conf;
	}


}