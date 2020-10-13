package fr.analogon.r2t.test;

public class startPleaide
{
	public static void main(String[] args) 
	{		
		System.out.print("lecture du numero de batch="+args[0]);	
		System.out.print("Traitment en cours....");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("Termine pleaide finis");
	}

	
	
}








