package game.test.blackjack;

import static org.junit.Assert.*;
import game.blackjack.Kartya;
import game.blackjack.Szin;

import org.junit.Test;

public class TestKartya {

	@Test
	public void testKartyaToString() {
		Kartya kartya = new Kartya("8", 8, Szin.Kor);
		Kartya kartya1 = new Kartya("3", 3, Szin.Karo);
		Kartya kartya2 = new Kartya("J", 10, Szin.Treff);
		Kartya kartya3 = new Kartya("A", 1, Szin.Pikk);
		
		assertEquals("Kor 8", kartya.toString());
		assertEquals("Karo 3", kartya1.toString());
		assertEquals("Treff J", kartya2.toString());
		assertEquals("Pikk A", kartya3.toString());
	}

}
