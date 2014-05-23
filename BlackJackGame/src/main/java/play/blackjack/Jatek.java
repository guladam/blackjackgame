package play.blackjack;

import gui.blackjack.FelhasznaloiFelulet;

/**
 * @author guladam
 *
 * Ez az osztaly a program belepesi pontja, itt indul el tenylegesen a Jatek.
 */
public class Jatek {
	
	/**
	 * A nyert jatszmak szama.
	 */
	public static int nyertJatszmak = 0;
	
	/**
	 * Az osszes jatszma szama.
	 */
	public static int osszesJatszma = 0;
	
	/**
	 * A nyert jatszmak aranyanak a szama. 
	 */
	public static double arany = 0;
	
	/**
	 * A jatekos neve. 
	 */
	public static String jatekosNeve = "Gula";
	
	/**
	 * Megmutatja, hogy a felhasznalo kivan-e grafikus feluleten jatszani. 
	 */
	public static boolean grafikusFelulet;
	
	/**
	 * A program main fuggvenye.
	 * 
	 * @param args , parancssori argumentumok listaja
	 */
	public static void main(String[] args) {
		
		grafikusFelulet = FelhasznaloiFelulet.bekapcsolGUI();
		
		if(grafikusFelulet)
			FelhasznaloiFelulet.letrehoz();
		else
			KonzolosJatek.konzolosJatek();
		
	}
	
}
