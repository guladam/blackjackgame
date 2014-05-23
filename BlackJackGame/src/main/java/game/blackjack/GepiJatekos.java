package game.blackjack;


/**
 * A Kartyajatekban egy gepi jatekost (osztot) reprezentalo osztaly.
 */
public class GepiJatekos extends Jatekos{

	/**
	 * A {@code GepiJatekos} erteke, mely meghatarozza meddig kell lapot kernie.
	 */
	public final int megallasiHatar = 17;
	
	/**
	 * Ures konstruktor egy {@code GepiJatekos} letrehozasara.
	 */
	public GepiJatekos() {
	}
	
	
	/**
	 * {@inheritDoc}
	 * 
	 * @see game.blackjack.Jatekos#lapHuzas(game.blackjack.Kartya)
	 */
	@Override
	public void lapHuzas(Kartya kartya) {
		this.getKez().lapHuzas(kartya);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see game.blackjack.Jatekos#megallas()
	 */
	@Override
	public int megallas() {
		return this.getKez().getErtek();
	}

	
}
