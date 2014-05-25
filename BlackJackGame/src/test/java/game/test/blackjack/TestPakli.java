package game.test.blackjack;

import static org.junit.Assert.*;
import game.blackjack.Kartya;
import game.blackjack.Pakli;

import org.junit.Before;
import org.junit.Test;

public class TestPakli {

	private Pakli pakli;
	
	@Before
	public void beallit(){
		pakli = new Pakli();
		pakli.generalPakli();
	}
	
	@Test
	public void testPakliMeret() {
		assertEquals(52, pakli.getPakli().size());
	}
	
	@Test
	public void testPakliKartyatAd(){
		assertEquals(Kartya.class, pakli.lapHuzas().getClass());
	}

}
