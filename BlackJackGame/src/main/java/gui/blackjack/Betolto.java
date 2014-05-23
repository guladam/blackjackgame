package gui.blackjack;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

/**
 * Betolto osztaly, amely kepeket es szovegeket tolt be az Osztalybetolto segitsegevel.
 */
public class Betolto {

	/**
	 * Metodus, amely a parameterkent megadott kartya fajlnevet betolti a "main/resources/kartyak/" mappabol.
	 *
	 * @param fajlNev , a betoltendo Kartya kepenek fajlneve
	 * @return BufferedImage formatumba visszaadja a kepet, hogy a Swing-be lehessen hasznalni
	 * @throws IOException Ha egy IO error miatt nem sikerul a betoltes
	 * @throws IllegalArgumentException Ha a parameterkent adott fajlnev nem letezik
	 */
	public static BufferedImage betoltKep(String fajlNev) throws IOException, IllegalArgumentException {
		
      BufferedImage image = ImageIO.read
    		  ( Betolto.class.getResourceAsStream("/kartyak/" + fajlNev + ".png"));
      
	  return image;
	  
	}
	
	/**
	 * Metodus, amely a parameterkent megadott szoveget betolti a "main/resources/szovegek/" mappabol.
	 * 
	 * @param fajlNev , a szoveget tartalmazo fajl neve
	 * @return String , a fajlbol kiolvasott szoveg
	 * @throws IOException Ha a szoveg betoltese IO error miatt meghiusul
	 * @throws NullPointerException Ha a megadott fajlnevu fajl nem letezik
	 */
	public static String betoltSzoveg(String fajlNev) throws IOException, NullPointerException {
		
		return IOUtils.toString( Betolto.class.getResourceAsStream
				("/szovegek/" + fajlNev));
		
	}
	
}
