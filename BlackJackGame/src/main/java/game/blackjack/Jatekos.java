package game.blackjack;


/**
 * A kartyajatekban egy jatekost reprezentalo absztrakt osztaly.
 */
public abstract class Jatekos {

	
	/**
	 * A Jatekos Keze. 
	 */
	private Kez kez = new Kez();
	
	/**
	 * Olyan metodus, amely hozzaad egy {@code Kartya}t a Jatekos kezehez.
	 * 
	 * @param kartya a hozzaadando kartya
	 */
	public abstract void lapHuzas(Kartya kartya);
	
	/**
	 * A jatekos megallasat implementalo metodus, ez jelzi hogy a {@code Jatekos} befejezte a koret.
	 * 
	 * @return visszateresi erteke a Jatekos aktualis kezenek erteke
	 */
	public abstract int megallas();

	
	/**
	 * Getter metodus, mely visszaadja a Jatekos kezet.
	 * 
	 * @return a Jatekos {@code Kez}e
	 */
	public Kez getKez() {
		return kez;
	}
	
	
	/**
	 * Setter metodus, mely beallitja a Jatekos kezet.
	 * 
	 * @param kez a {@code Kez} amelyet a Jatekoshoz rendelunk
	 */
	public void setKez(Kez kez) {
		this.kez = kez;
	}
	
}
