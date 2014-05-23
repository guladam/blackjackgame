package highscore.blackjack;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


/**
 * A RangListaval vegzendo statikus muveletek vannak implementalva ebben az osztalyban.
 */
public class RangLista {

	/**
	 * A naplozashoz szukseges logger valtozo.
	 */
	private static Logger logger = LoggerFactory.getLogger(RangLista.class);
	
	/**
	 * Olyan metodus, mely a parameterkent megadott szamu elso n elemet adja vissza a Ranglistabol Stringkent.
	 * @param elsoHany , az elso n elem szama
	 * @return listaResz , a lista beolvasott resze Stringkent
	 * @throws IOException , Ha a lista olvasasa soran IO error lepett fel
	 */
	public static String olvasLista(int elsoHany) throws IOException{
		
		StringBuilder sb = new StringBuilder();
		
		SAXParserFactory spfactory = SAXParserFactory.newInstance();
		try {
			SAXParser sParser = spfactory.newSAXParser();
			RangListaKezelo rangLK = new RangListaKezelo();
			sParser.parse(new File("ranglista.xml"), rangLK);
			
			List<Eredmeny> eredmenyek = rangLK.getEredmenyek();
			
			Collections.sort(eredmenyek);
			
			if(eredmenyek.size() >= elsoHany){
				for(int index = 0; index<elsoHany; index++) {
					Eredmeny e = eredmenyek.get(index);
					sb.append(index+1 + ". " + e.getFelhasznalo() + " - " + e.getNyertJatszma()
							+ "/" + e.getOsszJatszma() + " - " + e.getArany() + "%\n");
				}
			} else if (eredmenyek.size() != 0){
				for(int index=0; index<eredmenyek.size(); index++) {
					Eredmeny eredmeny = eredmenyek.get(index);
					sb.append(index+1 + ". " + eredmeny.getFelhasznalo() + " - " 
					+ eredmeny.getNyertJatszma() + "/" + eredmeny.getOsszJatszma() + 
					" - " + eredmeny.getArany() + "%\n");
				}
			} else {
				sb.append("A lista ures.");
			}
			
		} catch (ParserConfigurationException e) {
			logger.error("Kivetel valtodott ki a RangLista olvasa soran: " + e.getClass());
		} catch (SAXException e) {
			logger.error("Kivetel valtodott ki a RangLista olvasa soran: " + e.getClass());
		} catch (IOException e) {
			logger.error("Kivetel valtodott ki a RangLista olvasa soran: " + e.getClass());
		}
		
		return sb.toString();
		
	}
	
	/**
	 * Olyan metodus, mely a megadott parameterekkel biro Eredmenyt hozzaadja az aktualis Ranglistahoz.
	 * 
	 * @param jatekosNeve , az Eredmenyt elero jatekos neve
	 * @param nyert , a nyert jatszmak szama
	 * @param ossz , az osszes jatszma szama
	 * @param jatekosArany , a nyert jatszmak aranya
	 * @throws IOException , Ha a hozzaadas egy IO error miatt meghiusul
	 */
	public static void hozzaAdLista(String jatekosNeve, int nyert, 
			int ossz, double jatekosArany) throws IOException{
		
		DocumentBuilderFactory dBfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dBfactory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			logger.error("Kivetel valtodott ki a RangListahoz hozzaadas soran: " + e1.getClass());
		}
		
		Document doc = null;
		try {
			doc = dBuilder.parse(new File("ranglista.xml"));
		} catch (SAXException e1) {
			logger.error("Kivetel valtodott ki a RangListahoz hozzaadas soran: " + e1.getClass());
		}
		
		Node eredmenyek = doc.getDocumentElement();
		
		Element eredmeny = doc.createElement("eredmeny");
		eredmenyek.appendChild(eredmeny);
		
		Element felhasznalo = doc.createElement("felhasznalo");
		felhasznalo.appendChild(doc.createTextNode(jatekosNeve));
		eredmeny.appendChild(felhasznalo);
		
		Element nyertJatszma = doc.createElement("nyertjatszma");
		nyertJatszma.appendChild(doc.createTextNode(String.valueOf
				(nyert)));
		eredmeny.appendChild(nyertJatszma);
		
		Element osszJatszma = doc.createElement("osszjatszma");
		osszJatszma.appendChild(doc.createTextNode(String.valueOf(
				ossz)));
		eredmeny.appendChild(osszJatszma);
		
		Element arany = doc.createElement("arany");
		arany.appendChild(doc.createTextNode(String.valueOf(
				(int)jatekosArany)));
		eredmeny.appendChild(arany);
		
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		
		try {
			transformer = tFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			logger.error("Kivetel valtodott ki a RangListahoz hozzaadas soran: " + e.getClass());
		}
		
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("ranglista.xml"));
		
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			logger.error("Kivetel valtodott ki a RangListahoz hozzaadas soran: " + e.getClass());
		}
		
	}
	
	/**
	 * Amennyiben a ranglista.xml allomany hianyzik, ez a metodus inicializal egy listat, az atadott eredmennyel.
	 * 
	 * @param jatekosNeve , az Eredmenyt elero jatekos neve
	 * @param nyert , a nyert jatszmak szama
	 * @param ossz , az osszes jatszma szama 
	 * @param jatekosArany , a nyert jatszmak aranya
	 */
	public static void letrehozLista(String jatekosNeve, int nyert, 
			int ossz, double jatekosArany){
		
			DocumentBuilderFactory dBfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = null;
			try {
				dBuilder = dBfactory.newDocumentBuilder();
			} catch (ParserConfigurationException e1) {
				logger.error("Kivetel valtodott ki a RangLista letrehozasa soran: " + e1.getClass());
			}
			
			Document doc = dBuilder.newDocument();
			
			Element rootElement = doc.createElement("eredmenyek");
			doc.appendChild(rootElement);
			
			Element eredmeny = doc.createElement("eredmeny");
			rootElement.appendChild(eredmeny);
			
			Element felhasznalo = doc.createElement("felhasznalo");
			felhasznalo.appendChild(doc.createTextNode(jatekosNeve));
			eredmeny.appendChild(felhasznalo);
			
			Element nyertJatszma = doc.createElement("nyertjatszma");
			nyertJatszma.appendChild(doc.createTextNode(String.valueOf
					(nyert)));
			eredmeny.appendChild(nyertJatszma);
			
			Element osszJatszma = doc.createElement("osszjatszma");
			osszJatszma.appendChild(doc.createTextNode(String.valueOf(
					ossz)));
			eredmeny.appendChild(osszJatszma);
			
			Element arany = doc.createElement("arany");
			arany.appendChild(doc.createTextNode(String.valueOf(
					(int)jatekosArany)));
			eredmeny.appendChild(arany);
			
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = null;
			
			try {
				transformer = tFactory.newTransformer();
			} catch (TransformerConfigurationException e) {
				logger.error("Kivetel valtodott ki a RangLista letrehozasa soran: " + e.getClass());
			}
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("ranglista.xml"));
			
			try {
				transformer.transform(source, result);
				logger.info("A Ranglista letrejott.");
			} catch (TransformerException e) {
				logger.error("Kivetel valtodott ki a RangLista letrehozasa soran: " + e.getClass());
			}
	}

	/**
	 * Olyan metodus, mely az aktualis ranglistat kiuriti.
	 * 
	 * @throws IOException , Ha a kiurites soran IO error lepett fel
	 */
	public static void uritLista() throws IOException{
		
		DocumentBuilderFactory dBfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dBfactory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			logger.error("Kivetel valtodott ki a RangLista uritese soran: " + e1.getClass());
		}
		
		Document doc = null;
		try {
			doc = dBuilder.parse(new File("ranglista.xml"));
		} catch (SAXException e1) {
			logger.error("Kivetel valtodott ki a RangLista uritese soran: " + e1.getClass());
		}
		
		Node eredmenyek = doc.getDocumentElement();
		
		while(eredmenyek.hasChildNodes()){
			eredmenyek.removeChild(eredmenyek.getFirstChild());
		}
		
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		
		try {
			transformer = tFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			logger.error("Kivetel valtodott ki a RangLista uritese soran: " + e.getClass());
		}
		
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("ranglista.xml"));
		
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			logger.error("Kivetel valtodott ki a RangLista uritese soran: " + e.getClass());
		}
		
	}
	
}
