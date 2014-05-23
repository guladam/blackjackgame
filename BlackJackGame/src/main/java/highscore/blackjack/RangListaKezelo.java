package highscore.blackjack;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A SAX feldolgozashoz szukseges Kezeloosztaly.
 */
public class RangListaKezelo extends DefaultHandler{

	
	/**
	 * A Ranglista eredmenyeit tartalmazo Lista.
	 */
	private List<Eredmeny> eredmenyek = new ArrayList<Eredmeny>();
	
	/**
	 * Olyan Getter metodus, mely visszaadja az Eredmenyeket tartalmazo listat.
	 * 
	 * @return eredmenyek , a Ranglista eredmenyei listakent
	 */
	public List<Eredmeny> getEredmenyek() {
		return eredmenyek;
	}
	
	/**
	 * Minden aktualis Eredmenyt tarolunk a feldolgozas soran.
	 */
	private Eredmeny eredmeny = null;
	
	/**
	 * Megmutatja, hogy Felhasznalo elemet talalt-e a Kezelo.
	 */
	private boolean pFelhasznalo = false;
	
	/**
	 * Megmutatja, hogy Nyertjatszma elemet talalt-e a Kezelo.
	 */
	private boolean pNyertJatszma = false;
	
	/**
	 * Megmutatja, hogy Osszjatszma elemet talalt-e a Kezelo. 
	 */
	private boolean pOsszJatszma = false;
	
	/**
	 * Megmutatja, hogy Arany eleme talalt-e a Kezelo.
	 */
	private boolean pArany = false;
	
	/**
	 * A szovegcsomopontok ertekeit kiolvaso metodus.
	 * 
	 * @param arg0 , az elso argumentum a feldolgozashoz 
	 * @param arg1 , a masodik argumentum a feldolgozashoz
	 * @param arg2 , a harmadik argumentum a feldolgozashoz
	 * 
	 * @throws SAXException , Ha a feldolgozas egy SAX error miatt meghiusul
	 */
	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		if(pFelhasznalo){
			eredmeny.setFelhasznalo(new String(arg0, arg1, arg2));
			pFelhasznalo = false;
		} else if(pNyertJatszma){
			eredmeny.setNyertJatszma(Integer.parseInt(new String(arg0, arg1, arg2)));
			pNyertJatszma = false;
		}  else if(pOsszJatszma){
			eredmeny.setOsszJatszma(Integer.parseInt(new String(arg0, arg1, arg2)));
			pOsszJatszma = false;
		}  else if(pArany){
			eredmeny.setArany(Integer.parseInt(new String(arg0, arg1, arg2)));
			pArany = false;
		}
		
	}

	/**
	 * Metodus, mely minden "eredmeny" elem vegen, hozzaadja az adott Eredmenyt az Eredmenyek listajahoz.
	 * 
	 * @param arg0 , az elso argumentum
	 * @param arg1 , a masodik argumentum
	 * @param qName , az aktualis Elem neve
	 * 
	 * @throws SAXException Ha SAX error valtodik ki
	 */
	@Override
	public void endElement(String arg0, String arg1, String qName)
			throws SAXException {
		
		if(qName.equalsIgnoreCase("eredmeny")){
			eredmenyek.add(eredmeny);
		}
	}

	/**
	 * Metodus, mely minden "eredmeny" elem elejen, igazra allitja a megfelelo boolean erteket.
	 * 
	 * @param arg0 , az elso argumentum
	 * @param arg1 , a masodik argumentum
	 * @param qName , az aktualis Elem neve
	 * @param attr , az attributuma az Elemnek
	 * 
	 * @throws SAXException Ha SAX error valtodik ki
	 */
	@Override
	public void startElement(String arg0, String arg1, String qName,
			Attributes attr) throws SAXException {
		
		if(qName.equalsIgnoreCase("eredmeny")){
			
			eredmeny = new Eredmeny();
			
		} else if (qName.equalsIgnoreCase("felhasznalo")){
			pFelhasznalo = true;
		}  else if (qName.equalsIgnoreCase("nyertjatszma")){
			pNyertJatszma = true;
		}  else if (qName.equalsIgnoreCase("osszjatszma")){
			pOsszJatszma = true;
		}  else if (qName.equalsIgnoreCase("arany")){
			pArany = true;
		}
			
	}
	
}
