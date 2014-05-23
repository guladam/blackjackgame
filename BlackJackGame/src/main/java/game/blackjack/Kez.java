package game.blackjack;

import java.util.LinkedList;

/**
 * A {@code Jatekos} kezet reprezentalo osztaly, amely {@code Kartya}kat tartalmaz.
 */
public class Kez {

	/**
	 * Lancolt Lista, amely a {@code Kez}ben levo lapokat tarolja.
	 */
	private LinkedList<Kartya> lapok = new LinkedList<Kartya>();
	
	
	/**
	 * Ures Konstruktor a {@code Kez} letrehozasara. 
	 */
	public Kez(){
	}
	
	
	/** Konstruktor a {@code Kez} letrehozasara a {@code Kartya}inak megadasaval.
	 * 
	 * @param lapok a {@code Kez}ben levo {@code Kartya}k Listaja
	 */
	public Kez(LinkedList<Kartya> lapok){
		this.lapok = lapok;
	}
	
	
	/**
	 * Olyan metodus, mely visszaadja a {@code Kez}ben levo {@code Kartya}k erteket a jatek szabalyai szerint.
	 *
	 * @return ertek, a {@code Kez} erteke
	 */
	public int getErtek() {
		int kezErtek = 0;
			
		for(Kartya kartya : lapok)
			if(kartya.getErtek() != 1)
				kezErtek += kartya.getErtek();
		
		for(Kartya kartya : lapok)
			if(kartya.getErtek() == 1)
				if(kezErtek+11 > 21)
					kezErtek++;
				else
					kezErtek += 11;
		
		return kezErtek;
	}
	
	
	/**
	 * Visszaadja, hogy hany lap talalhato ebben a {@code Kez}ben.
	 * 
	 * @return szam a lapok a szama
	 */
	public int getLapokSzama() {
		return lapok.size();
	}
	
	/**
	 * Hozzaadja a {@code Kez}hez a parameterkent atadott {@code Kartya}t.
	 * 
	 * @param kartya a hozzaadando {@code Kartya}
	 */
	public void lapHuzas(Kartya kartya){
		lapok.add(kartya);
	}
	
	/**
	 * Logikai fuggveny, mely eldonti hogy a {@code Kez}ben levo lapok erteke BlackJack-e.
	 *
	 * @return <code>true</code>val ter vissza, ha 21 az erteke a Keznek, <code>false</code>al egyebkent
	 */
	public boolean isBlackJack(){
		return (getErtek() == 21) ? true : false;
	}
	
	/**
	 * Getter metodus, amely visszaadja a {@code Kez} lapjait egy lancolt listaban.
	 * 
	 * @return lapok , a {@code Kez} lapjai
	 */
	public LinkedList<Kartya> getLapok() {
		return lapok;
	}
	
	/**
	 * Setter metodus, amely beallitja a {@code Kez} lapjait egy lancolt lista segitsegevel.
	 * 
	 * @param lapok a beallitando {@code Kartya}k listaja
	 */
	public void setLapok(LinkedList<Kartya> lapok) {
		this.lapok = lapok;
	}
	
	/**
	 * Visszaadja a {@code Kez} String-reprezentaciojat, azaz
	 * A {@code Kez} kartyait egymastol szokozzel elvalasztva.
	 * 
	 * @return String , a Kez String reprezentacioja
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Kartya kartya: lapok)
			sb.append(kartya.toString()+" ");
		return sb.toString();
	}
}
