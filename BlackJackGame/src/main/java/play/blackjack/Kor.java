package play.blackjack;

import game.blackjack.EmberJatekos;
import game.blackjack.GepiJatekos;
import game.blackjack.Jatekos;
import game.blackjack.Kartya;
import game.blackjack.Kez;
import game.blackjack.Pakli;

import java.util.ArrayList;

/**
 * Egy konkret BlackJack Jatek Koret megvalosito osztaly.
 */
public class Kor {

	/**
	 * Az EmberJatekosokat tartalmazo lista. (1, ha nincs szetvalasztas, 2, ha szetvalasztott).
	 */
	private ArrayList<EmberJatekos> emberJatekosok;
	
	/**
	 * Az kor osztoja, a GepiJatekos. 
	 */
	private GepiJatekos gepJatekos;
	
	/**
	 * A Korben hasznalt Pakli. 
	 */
	private Pakli pakli;
	
	/**
	 * Megmutatja, hogy a Jatekos-nak meg van-e dontese ebben a Korben. 
	 */
	private boolean vanDontes;
	
	/**
	 * Megmutatja, hogy a Jatekos szetvalasztotta a lapjait, amennyiben volt ra lehetosege. 
	 */
	private boolean szetValasztott;
	
	/**
	 * Az aktualis kez, amivel a Jatekos jatszik (1 vagy 2). 
	 */
	private int aktualisKez;
	
	/**
	 * Megmutatja, hogy az adott Kor a vegehez ert-e. 
	 */
	private boolean korVege;
	
	/**
	 * Az allasokat tartalmazo lista. 
	 */
	private ArrayList<Allas> allas;
	
	/**
	 * Ures konstruktor egy uj Kor letrehozasahoz.
	 */
	public Kor(){
		
		emberJatekosok = new ArrayList<EmberJatekos>();
		vanDontes = true;
		korVege = false;
		allas = new ArrayList<Allas>();
		EmberJatekos eJatekos = new EmberJatekos();
		GepiJatekos gJatekos = new GepiJatekos();
		
		aktualisKez = 1;
		
		pakli = new Pakli();
		pakli.generalPakli();
		pakli.keverPakli();
		
		gepJatekos = gJatekos;
		emberJatekosok.add(eJatekos);
		
	}
	
	/**
	 * A kor elejet megvalosito metodus. 
	 * Itt vizsgaljuk meg, hogy a Jatekos nyerhet-e amennyiben rogton BlackJack-et kap a kezebe.
	 */
	public void korKezdes() {
		
		EmberJatekos eJatekos = emberJatekosok.get(0);
		
		gepJatekos.lapHuzas(pakli.lapHuzas());
		eJatekos.lapHuzas(pakli.lapHuzas());
		eJatekos.lapHuzas(pakli.lapHuzas());
		
		allastKiir();
		
		if(eJatekos.getKez().isBlackJack()) {
			gepJatekos.lapHuzas(pakli.lapHuzas());
			allastKiir();
			if(gepJatekos.getKez().isBlackJack()) {
				allas.add(Allas.dontetlen); 
			} else {
				allas.add(Allas.jatekosBJ); 
				Jatek.nyertJatszmak++;
			}
			korVege = true;
		}
		
	}
	
	/**
	 * A gep koret lejatszo metodus.
	 * Itt tortenik meg az {@link #eredmeny() eredmeny()} metodus meghivasa is.
	 */
	public void gepKore() {
		
		boolean besokallt = false;
		
		while(gepJatekos.getKez().getErtek() < gepJatekos.megallasiHatar) {
			
			gepJatekos.lapHuzas(pakli.lapHuzas());
			
			allastKiir();
			
			if(tobbMint21(gepJatekos)){
				allas.add(Allas.osztoSok); 
				besokallt = true;
				for(int i=0; i<emberJatekosok.size(); i++)
					Jatek.nyertJatszmak++;
			}
		}
		
		if(!besokallt){
			eredmeny();
		}
	}
	
	/**
	 * Ez a metodus hozzaadja a Kor {@link #allas allasahoz} a megfelelo eredmenyt.
	 */
	public void eredmeny() {
		
		for(EmberJatekos eJatekos : emberJatekosok){
			
			if( eJatekos.getKez().getErtek() <= 21){
				if( eJatekos.getKez().getErtek() > gepJatekos.getKez().getErtek() ){
					Jatek.nyertJatszmak++;
					allas.add(Allas.jatekosNyer);
				}
				else if( eJatekos.getKez().getErtek() < gepJatekos.getKez().getErtek() ){
					allas.add(Allas.osztoNyer);
				} else {
					allas.add(Allas.dontetlen);
				}
			}
		}
		
	}
	
	/**
	 * A parameterkent adott jatekosnak, vegrehajtja a parameterkent atadott donteset.
	 * 
	 * @param eJatekos , a Jatekos aki dont
	 * @param dontes , a donto Jatekos dontese
	 */
	public void jatekosKore(EmberJatekos eJatekos, Dontes dontes){
		
		if(dontes == Dontes.Huz && vanDontes) {
			
			eJatekos.lapHuzas(pakli.lapHuzas());
			
			allastKiir();
			
			if(tobbMint21(eJatekos)) {
				allas.add(Allas.jatekosSok);
				vanDontes = false;
			}
		}
		
		if(dontes == Dontes.Megall && vanDontes) {
			
			if(!szetValasztott)
				korVege = true;

			if(szetValasztott && aktualisKez == 2)
				korVege = true;
			
			if(szetValasztott && aktualisKez == 1)
				aktualisKez = 2;

			vanDontes = false;
			allastKiir();
		}
		
		if(dontes == Dontes.Szetvalaszt && eJatekos.szetValaszthat()) {
			
			szetValasztott = true;
			EmberJatekos ujJatekos = new EmberJatekos();
			Kartya szetvalasztottLap1 = eJatekos.getKez().getLapok().get(0);
			Kartya szetvalasztottLap2 = eJatekos.getKez().getLapok().get(1);
			ujJatekos.getKez().lapHuzas(szetvalasztottLap2);
			Kez egyLap = new Kez();
			egyLap.lapHuzas(szetvalasztottLap1);
			
			eJatekos.setKez(egyLap);
			
			emberJatekosok.add(ujJatekos);
			
		} 
		
	}
	
	/**
	 * Megmutatja, hogy a jatekos minden keze besokallt-e.
	 * 
	 * @return <code>true</code> , ha minden kez besokallt, <code>false</code> egyebkent
	 */
	public boolean mindenKezBesokallt(){
		
		boolean eredmeny = true;
		
		for(EmberJatekos eJatekos : emberJatekosok ){
			if( !tobbMint21(eJatekos) )
				eredmeny = false;
		}
		
		return eredmeny;
		
	}
	
	/**
	 * Megmutatja, hogy a parameterkent atadott jatekos lapjainak erteke meghaladje-e a 21et.
	 * 
	 * @param jatekos , a vizsgalando Jatekos
	 * @return <code>true</code> , ha meghaladta a 21et, <code>false</code> , egyebkent
	 */
	public boolean tobbMint21(Jatekos jatekos){
		if(jatekos.getKez().getErtek() > 21 )
			return true;
		else 
			return false;
	}
	
	/**
	 * Konzolos Jatek eseten ez a metodus irja ki a kepernyore az aktualis allast.
	 */
	public void allastKiir(){
		
		if( !Jatek.grafikusFelulet ){
			System.out.println("-------------");

			for(EmberJatekos eJatekos : emberJatekosok)
				System.out.println("A lapjaid: | " + eJatekos.getKez().toString()
						+ " | Erteke: " + eJatekos.getKez().getErtek());

			System.out.println("-------------");

			System.out.println("A Gep lapjai: | " + gepJatekos.getKez().toString()
					+ " | Erteke: " + gepJatekos.getKez().getErtek());

			System.out.println("");
		}
	}

	/**
	 * Ez a metodus Stringkent visszaadja az adott {@code Allas} hoz tartozo megfelelo szoveget.
	 * 
	 * @param allas , az Allas amelynek tenyleges uzenetet kerjuk
	 * @return uzenet , az Allashoz tartozo tenyleges uzenet String formajaban
	 */
	public String getAllasUzenet(Allas allas){
		
		if(allas == Allas.osztoNyer){
			return "Az oszto nyert!";
		} else if(allas == Allas.jatekosNyer) {
			return "Ezzel a kezzel nyertel!";
		} else if(allas == Allas.dontetlen) {
			return "Dontetlen.";
		} else if(allas == Allas.osztoSok){
			return  "A gep besokallt, nyertel!";
		} else if(allas == Allas.jatekosSok){
			return "Ez a kezed besokallt!";
		} else {
			return "BlackJacked van! Nyertel!";
		}
	}
	
	/**
	 * Olyan Getter metodus, amely visszaadja az EmberJatekosok listajat.
	 * 
	 * @return lista , Az emberjatekosok listaja
	 */
	public ArrayList<EmberJatekos> getEmberJatekosok() {
		return emberJatekosok;
	}

	/**
	 * Olyan Setter metodus, amely beallitja az EmberJatekosok listajat.
	 * 
	 * @param emberJatekosok , azok az EmberJatekosok, akiket szeretnenk hogy aktualisak legyen ebben a Korben
	 */
	public void setEmberJatekosok(ArrayList<EmberJatekos> emberJatekosok) {
		this.emberJatekosok = emberJatekosok;
	}

	/**
	 * Eltavolitja a parameterkent atadott indexu EmberJatekost az EmberJatekosok listajabol.
	 * 
	 * @param index , az eltavolitando EmberJatekos listabeli indexe
	 */
	public void removeEmberJatekos(int index){
		emberJatekosok.remove(index);
	}
	
	/**
	 * Olyan Getter metodus, amely visszaadja a GepiJatekost, vagyis az osztot.
	 * 
	 * @return oszto , a Kor osztoja
	 */
	public GepiJatekos getGepJatekos() {
		return gepJatekos;
	}

	/**
	 * Olyan Setter metodus, amely beallitja a Kor GepiJatekosat, vagyis az osztojat.
	 * 
	 * @param gepJatekos , a beallitasra szant GepiJatekos
	 */
	public void setGepJatekos(GepiJatekos gepJatekos) {
		this.gepJatekos = gepJatekos;
	}

	/**
	 * Olyan Getter metodus, amely visszaadja a Kor Paklijat.
	 * 
	 * @return pakli , a Kor aktualis Paklija
	 */
	public Pakli getPakli() {
		return pakli;
	}

	/**
	 * Olyan Setter metodus, amely beallitja a Kor Paklijat.
	 * 
	 * @param pakli , a Korhoz beallitando Pakli
	 */
	public void setPakli(Pakli pakli) {
		this.pakli = pakli;
	}

	/**
	 * Megmutatja, hogy a jatekosnak, van-e meg dontesi joga ebben a Korben.
	 * 
	 * @return <code>true</code> ha meg van dontesi joga, <code>false</code> egyebkent
	 */
	public boolean isVanDontes() {
		return vanDontes;
	}

	/**
	 * Beallitja, hogy a jatekosnak legyen-e meg dontesi joga ebben a Korben.
	 * 
	 * @param vanDontes , a beallitando ertek
	 */
	public void setVanDontes(boolean vanDontes) {
		this.vanDontes = vanDontes;
	}

	/**
	 * Megmutatja, hogy a jatekos szetvalasztotta-e a lapjait ebben a Korben.
	 * 
	 * @return <code>true</code> ha szetvalasztotta a lapjait, <code>false</code> egyebkent
	 */
	public boolean isSzetValasztott() {
		return szetValasztott;
	}

	/**
	 * Olyan Setter metodus, amely beallitja, hogy a jatekos szetvalasztotta-e a lapjait ebben a Korben.
	 * 
	 * @param szetValasztott , a beallitando ertek
	 */
	public void setSzetValasztott(boolean szetValasztott) {
		this.szetValasztott = szetValasztott;
	}

	
	/**
	 * Megmutatja, hogy a Kor a vegehez ert-e.
	 * 
	 * @return <code>true</code> ha a Kor a vegehez ert, <code>false</code> egyebkent
	 */
	public boolean isKorVege() {
		return korVege;
	}

	/**
	 * Olyan Setter metodus, amely beallitja, hogy a Kor a vegehez ert-e.
	 * 
	 * @param korVege , a beallitando ertek
	 */
	public void setKorVege(boolean korVege) {
		this.korVege = korVege;
	}

	/**
	 * Olyan Getter metodus, amely visszaadja a Kor allasait tartalmazo listat.
	 * 
	 * @return lista , amely a Kor Allasait tartalmazza
	 */
	public ArrayList<Allas> getAllas() {
		return allas;
	}

	/**
	 * Olyan Setter metodus, amely beallitja a Kor allasait tartalmazo listat.
	 * 
	 * @param allas , a beallitando Allasok listaja
	 */
	public void setAllas(ArrayList<Allas> allas) {
		this.allas = allas;
	}

	/**
	 * Olyan Getter metodus, amely visszaadja a jatekos aktualis kezet, ami 1 vagy 2 lehet.
	 * 
	 * @return szam , a Jatekos aktualis keze
	 */
	public int getAktualisKez() {
		return aktualisKez;
	}

	/**
	 * Olyan Setter metodus, amely beallitja a Jatekos aktualis kezet.
	 * 
	 * @param aktualisKez , a beallitando aktualis Kez sorszama
	 */
	public void setAktualisKez(int aktualisKez) {
		this.aktualisKez = aktualisKez;
	}
	
}
