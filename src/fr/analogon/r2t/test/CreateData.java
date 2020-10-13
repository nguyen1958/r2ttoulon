package fr.analogon.r2t.test;

public class CreateData
{
	int idBatchTraitement =0;
	

	public static void main(String[] args) 
	{
		CreateData cd = new CreateData();
		String date = args[0];
		String typeTaxe = args[1];
		//System.out.println("Lancment de batch le "+date + " pour le type de taxe "+ typeTaxe);		
		//System.out.println("Traitment en cours ....");
		cd.idBatchTraitement = 3451;			
		//System.out.println("Traitment finis ");
	}


	/**
	 * @return the idBatchTraitement
	 */
	public final int getIdBatchTraitement() {
		return idBatchTraitement;
	}


	/**
	 * @param idBatchTraitement the idBatchTraitement to set
	 */
	public final void setIdBatchTraitement(int idBatchTraitement) {
		this.idBatchTraitement = idBatchTraitement;
	}

	
	
}








