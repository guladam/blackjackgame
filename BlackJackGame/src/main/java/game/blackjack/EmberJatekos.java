package game.blackjack;


/**
 * A kartyajatekban egy embert reprezentalo osztaly.
 *
 */
public class EmberJatekos extends Jatekos{

	/**
	 * Ures konstruktor egy {@code EmberJatekos} letrehozasahoz.
	 */
	public EmberJatekos() {
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
	
	/**
	 * Olyan logikai fuggveny, amely eldonti, hogy az {@code EmberJatekos} lapjait szet lehet-e valasztani.
	 * @return <code>true</code>-val ter vissza ha szetvalaszthat, <code>false</code>-al egyebkent.
	 */
	public boolean szetValaszthat(){
		
		if(getKez().getLapokSzama() == 2 && getKez().getLapok().get(0).getErtek()
				== getKez().getLapok().get(1).getErtek())
			return true;
		else
			return false;
	}
	
}
