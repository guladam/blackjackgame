package play.blackjack;

import game.blackjack.EmberJatekos;
import highscore.blackjack.RangLista;

import java.io.IOException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Grafikus felulet nelkuli jatekot implementalo osztaly.
 */
public class KonzolosJatek {
	
	/**
	 * A naplozashoz szukseges logger mezo.
	 */
	private static Logger logger = LoggerFactory.getLogger(KonzolosJatek.class);
	
	/**
	 * A konzolos jatekhoz elengedhetetlen standart input.
	 */
	public static Scanner input = new Scanner(System.in);
	
	/**
	 * A konzolos jatekot elindito metodus.
	 */
	public static void konzolosJatek(){
		
		boolean jatszolMeg = true;
		
		System.out.print("Kerem adja meg a nevet: ");
		Jatek.jatekosNeve = input.nextLine();
		
		while(jatszolMeg){
			
			konzolosJatekEgyKor();
			Jatek.osszesJatszma++;
			System.out.println("____________________");
			System.out.println("Akarsz meg jatszani? (I/N)");
			
			char dontes = input.nextLine().charAt(0);

			if(dontes == 'N'){
				System.out.println("Koszi a jatekot!");
				System.out.println("-------");
				break;
			}
			else if(dontes == 'I')
				continue;
			else
				System.out.println("Kerem jo karaktert adjon meg! (I/N)");

		}
		Jatek.arany = (double)Jatek.nyertJatszmak/Jatek.osszesJatszma*100;
		
		try {
			RangLista.hozzaAdLista(Jatek.jatekosNeve, Jatek.nyertJatszmak, Jatek.osszesJatszma,
					Jatek.arany);
			System.out.println("Az aktualis ranglista: ");
			System.out.println(RangLista.olvasLista(5));
		} catch (IOException e) {
			logger.error("A ranglista.xml allomany betoltese sikertelen." + e.getClass());
			RangLista.letrehozLista(Jatek.jatekosNeve, Jatek.nyertJatszmak, Jatek.osszesJatszma,
					Jatek.arany);
		}
		
		input.close();
		System.exit(0);
	}
	
	/**
	 * A konzolos jatekon belul egy konkret kort megvalosito metodus.
	 */
	public static void konzolosJatekEgyKor(){
		
		System.out.println("**********");
		
		System.out.println("Udv a BlackJack jatekban! " +
				"\n" + "Megallas: M, Laphuzas: L" + "\n");
		
		Kor kor = new Kor();
		
		kor.korKezdes();
		
		if(kor.getAllas().size() == 1 && kor.getAllas().get(0) == Allas.jatekosBJ)
			System.out.println("BlackJacked van, nyertél!");
		else if(kor.getAllas().size() == 1 && kor.getAllas().get(0) == Allas.dontetlen)
			System.out.println("Dontetlen kor.");
		
		if(!kor.isKorVege()){
			if(kor.getEmberJatekosok().get(0).szetValaszthat()){
				System.out.println("Szetvalasztod a lapjaidat? (I/N)");
				while(true){
					char c = input.nextLine().charAt(0);

					if(c == 'I'){
						System.out.println("Szetvalasztottad a lapjaid! " +
								"Mostantol 2 kezzel jatszol!");
						kor.jatekosKore
						(kor.getEmberJatekosok().get(0), Dontes.Szetvalaszt);
						Jatek.osszesJatszma++;
						break;
					} else if (c == 'N') {
						break;
					} else {
						System.out.println("Kerem I vagy N betut irjon be!");
					}
				}

			}

			for(EmberJatekos eJatekos : kor.getEmberJatekosok()){

				while (kor.isVanDontes()) {

					Character c = input.nextLine().charAt(0);

					if(c == 'M') {
						kor.jatekosKore(eJatekos, Dontes.Megall);
					} else if(c == 'L'){
						kor.jatekosKore(eJatekos, Dontes.Huz);	
					} else
						System.out.println("Kerem jo karaktert adjon meg! (M/L)");
					
					for(Allas a : kor.getAllas())
						if(a == Allas.jatekosSok)
							System.out.println("Besokalltal! Ezt a kezed buktad!");
				}

				kor.setVanDontes(true);
			}
			
			if( !kor.mindenKezBesokallt() ){
				kor.gepKore();
				eredmenyKiiras(kor);
			}
			else
				System.out.println("Az oszto nyert!");
		}
	}

	/**
	 * Az eredmeny konzolra torteno kiirasat megvalosito metodus.
	 * 
	 * @param kor , az aktualis kor aminek az Eredmenyet szeretnenk kiirni
	 */
	private static void eredmenyKiiras(Kor kor){
		for(Allas a : kor.getAllas()){
			System.out.println(kor.getAllasUzenet(a));
		}
	}
	
}
