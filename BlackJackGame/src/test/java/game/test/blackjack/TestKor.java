package game.test.blackjack;

import static org.junit.Assert.*;

import java.util.LinkedList;

import game.blackjack.EmberJatekos;
import game.blackjack.Kartya;
import game.blackjack.Szin;

import org.junit.Before;
import org.junit.Test;

import play.blackjack.Allas;
import play.blackjack.Dontes;
import play.blackjack.Kor;

public class TestKor {

	private Kor kor;
	
	@Before
	public void beallitKor(){
		kor = new Kor();
	}
	
	public static LinkedList<Kartya> letrehozKez(int[] ertekek){
		LinkedList<Kartya> kez = new LinkedList<>();
		for(int index=0;index<ertekek.length;index++)
			kez.add(new Kartya("valami", ertekek[index], Szin.Karo));
		return kez;
	}
	
	@Test
	public void testJatekosNyer(){
		EmberJatekos eJatekos = kor.getEmberJatekosok().get(0);
		
		eJatekos.getKez().setLapok(
				(letrehozKez(new int[] {10,10})));
		kor.getGepJatekos().getKez().setLapok(letrehozKez(new int[] {8,10}));
		
		kor.eredmeny();
		
		assertEquals(Allas.jatekosNyer, kor.getAllas().get(0));
	}
	
	@Test
	public void testOsztoNyer(){
		EmberJatekos eJatekos = kor.getEmberJatekosok().get(0);
		
		eJatekos.getKez().setLapok(
				(letrehozKez(new int[] {8,10})));
		kor.getGepJatekos().getKez().setLapok(letrehozKez(new int[] {1,10}));
		
		kor.eredmeny();
		
		assertEquals(Allas.osztoNyer, kor.getAllas().get(0));
	}
	
	@Test
	public void testDontetlen(){
		EmberJatekos eJatekos = kor.getEmberJatekosok().get(0);
		
		eJatekos.getKez().setLapok(
				(letrehozKez(new int[] {1,10})));
		
		kor.getGepJatekos().getKez().setLapok(letrehozKez(new int[] {1,10}));
		
		kor.eredmeny();
		
		assertEquals(Allas.dontetlen, kor.getAllas().get(0));
	}
	
	@Test
	public void testSzetvalasztas(){
		EmberJatekos eJatekos = kor.getEmberJatekosok().get(0);
		
		eJatekos.getKez().setLapok(
				(letrehozKez(new int[] {10,10})));
		
		kor.jatekosKore(eJatekos, Dontes.Szetvalaszt);
		
		for(EmberJatekos emberJatekos : kor.getEmberJatekosok())
			emberJatekos.getKez().lapHuzas(new Kartya("valami", 8, Szin.Karo));
			
		//Itt mind a ket kezben 18 van a jatekosnal, igy mindket keze nyer.
		kor.getGepJatekos().getKez().setLapok(letrehozKez(new int[] {7,10}));
		
		kor.eredmeny();
		
		assertEquals(Allas.jatekosNyer, kor.getAllas().get(0));
		assertEquals(Allas.jatekosNyer, kor.getAllas().get(1));
		
	}
}
