package game.blackjack;

import java.util.LinkedList;
import java.util.Collections;

/**
 * A kartyajatekban egy Paklit reprezentalo osztaly.
 */
public class Pakli {

	/**
	 * Konstans amely tarolja a lehetseges kartyak neveit.
	 */
	private final String[] nevek = {"2", "3", "4", "5", "6", "7", "8", "9", "10", 
			"J", "Q", "K", "A"};
	
	/**
	 * Konstans amely tarolja a kartyak ertekeit.
	 */
	private final Integer[] ertekek = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 1};
	
	/**
	 * A {@code Pakli} t reprezentalo lancolt lista, amely {@code Kartya} kat tartalmaz.
	 */
	private LinkedList<Kartya> pakli = new LinkedList<Kartya>();
	
	/**
	 * Ures konstruktor egy Pakli letrehozasara.
	 */
	public Pakli() {
	}
	
	/**
	 * Olyan metodus, mely legeneral egy 52 lapbol allo paklit.
	 */
	public void generalPakli(){
		for(Szin szin : Szin.values())
			for(int index=0; index<nevek.length; index++)
					pakli.add(new Kartya(nevek[index], ertekek[index], szin));
	}
	
	/**
	 * A Pakli megkevereset reprezentalo metodus. 
	 */
	public void keverPakli(){
		Collections.shuffle(pakli);
	}
	
	/**
	 * A Pakli legfelso lapjanak kihuzasat implementalo metodus.
	 * 
	 * @return <code>Kartya</code> , a legfelso kartya
	 */
	public Kartya lapHuzas(){
		return pakli.pop();
	}
	
	/**
	 * Getter metodus a Paklit reprezentalo lancolt lista lekeresehez.
	 * 
	 * @return Lista, a kartyak listaja
	 */
	public LinkedList<Kartya> getPakli() {
		return pakli;
	}

	
	/**
	 * Setter metodus a Pakli beallitasara.
	 * 
	 * @param pakli , azon {@code Kartya} k listaja, amibol szeretnenk hogy alljon az aktualis pakli
	 */
	public void setPakli(LinkedList<Kartya> pakli) {
		this.pakli = pakli;
	}

	/**
	 * A {@code Pakli} String-reprezentaciojat visszaado metodus.
	 * A reprezentacio a pakli kartyainak String-reprezentacioit jelenti, egymastol sortoressel elvalasztva.
	 *
	 * @return String , a Pakli String reprezentacioja
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Kartya kartya: pakli)
			sb.append(kartya.toString()+"\n");
		return sb.toString();
	}
	
}