package game.test.blackjack;

import static org.junit.Assert.*;

import game.blackjack.Kartya;
import game.blackjack.Kez;
import game.blackjack.Szin;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class TestKez {
	
	private Kez kez;
	
	@Before
	public void beallit(){
		kez = new Kez();
	}
	
	public static LinkedList<Kartya> letrehozKez(int[] ertekek){
		LinkedList<Kartya> kez = new LinkedList<>();
		for(int index=0;index<ertekek.length;index++)
			kez.add(new Kartya("valami", ertekek[index], Szin.Karo));
		return kez;
	}
	
	@Test
	public void test4Asz() {
		kez.setLapok(letrehozKez(new int[] {1,1,1,1}));
		assertEquals(14, kez.getErtek());
	}

	@Test
	public void test3Dama(){
		kez.setLapok(letrehozKez(new int[] {10,10,10}));
		assertEquals(30, kez.getErtek());
	}
	
	@Test
	public void test21(){
		kez.setLapok(letrehozKez(new int[] {10,1}));
		assertEquals(21, kez.getErtek());
		assertEquals(true, kez.isBlackJack());
		
		kez.setLapok(letrehozKez(new int[] {10,10}));
		assertEquals(false, kez.isBlackJack());
	}
	
	@Test
	public void testNemSok(){
		kez.setLapok(letrehozKez(new int[] {10,7,2,1}));
		assertEquals(20, kez.getErtek());
	}
	
	@Test
	public void testSoftA6(){
		kez.setLapok(letrehozKez(new int[] {1, 6, 6}));
		assertEquals(13, kez.getErtek());
	}

}
