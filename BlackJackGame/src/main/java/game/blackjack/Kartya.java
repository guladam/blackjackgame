package game.blackjack;

/**
 * A kartyajatekban egy kartyat reprezentalo osztaly.
 */
public class Kartya {
	
	/**
	 * A kartya neve. 
	 */
	private String nev;
	
	/**
	 * A kartya erteke. 
	 */
	private int ertek;
	
	/**
	 * A kartya Szine.
	 */
	private Szin szin;
	
	
	/**
	 * Konstruktor, amely letrehoz egy {@code Kartya}t a megadott parameterekkel.
	 * 
	 * @param nev a {@code Kartya} neve
	 * @param ertek a {@code Kartya} erteke
	 * @param szin a <code>Kartya</code>nak a <code>Szin</code>e
	 */
	public Kartya(String nev, int ertek, Szin szin) {
		this.nev = nev;
		this.ertek = ertek;
		this.szin = szin;
	}
	
	
	/**
	 * Getter metodus, mely visszaadja a {@code Kartya} nevet.
	 * 
	 * @return nev, a {@code Kartya} neve
	 */
	public String getNev() {
		return nev;
	}
	
	/**
	 * Setter metodus, mely beallitja a {@code Kartya} nevet.
	 * 
	 * @param nev a {@code Kartya} neve
	 */
	public void setNev(String nev) {
		this.nev = nev;
	}
	
	/**
	 * Getter metodus, mely visszaadja a {@code Kartya} erteket.
	 * 
	 * @return ertek, a {@code Kartya} erteke
	 */
	public int getErtek() {
		return ertek;
	}
	
	/**
	 * Setter metodus, mely beallitja a {@code Kartya} erteket.
	 * 
	 * @param ertek a {@code Kartya} erteke
	 */
	public void setErtek(int ertek) {
		this.ertek = ertek;
	}
	
	/**
	 * Getter metodus, mely visszaadja a {@code Kartya} Szinet.
	 * 
	 * @return szin, a {@code Kartya} Szine
	 */
	public Szin getSzin() {
		return szin;
	}
	
	/**
	 * Setter metodus, mely beallitja a {@code Kartya} Szinet.
	 * 
	 * @param szin a {@code Kartya} Szine.
	 */
	public void setSzin(Szin szin) {
		this.szin = szin;
	}
	
	
	/**
	 * Visszaadja a {@code Kartya} String-reprezentaciojat az alabbi formaban.
	 * 
	 * @return <code>Szin</code> + " " + <code>nev</code>  
	 */
	@Override
	public String toString() {
		return szin + " " + nev;
	}
	
}