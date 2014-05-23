package highscore.blackjack;

/**
 * Egy a Ranglistaban szereplo Eredmenyt reprezentalo osztaly.
 */
public class Eredmeny implements Comparable<Eredmeny>{

	/**
	 * Az eredmeny gyozelmi aranya.
	 */
	private int arany;
	
	/**
	 * Az osszes lejatszott jatszma.
	 */
	private int osszJatszma;
	
	/**
	 * A nyert jatszmak szama. 
	 */
	private int nyertJatszma;
	
	/**
	 * A eredmenyt elero jatekos neve.
	 */
	private String felhasznalo;
	
	/**
	 * Ures konstruktor az eredmeny letrehozasara.
	 */
	public Eredmeny(){
	}

	/**
	 * Konstruktor az eredmeny letrehozasara.
	 * 
	 * @param arany , a nyertes jatszmak aranya
	 * @param osszJatszma , az osszes jatszma szama
	 * @param nyertJatszma , a nyert jasztmak szama
	 * @param felhasznalo , az eredmenyt elero jatekos neve
	 */
	public Eredmeny(int arany, int osszJatszma, int nyertJatszma,
			String felhasznalo) {
		
		this.arany = arany;
		this.osszJatszma = osszJatszma;
		this.nyertJatszma = nyertJatszma;
		this.felhasznalo = felhasznalo;
	}
	
	/**
	 * Olyan osszehasonlito metodus, mely eldonti melyik eredmeny nagyobb.
	 * 
	 * Ha pl. {@code if( eredmeny1.getArany() > eredmeny2.getArany() )} , igaz, akkor eredmeny1 nagyobb.
	 * 
	 * @param masikEredmeny , amivel az adott Eredmenyt ossze szeretnenk hasonlitani
	 * @return szam, -1 ha eredmeny1 nagyobb, 0 ha egyenlo, 1 ha masikEredmeny nagyobb
	 */
	@Override
	public int compareTo(Eredmeny masikEredmeny) {
		if(this.getArany() > masikEredmeny.getArany())
			return -1;
		else if(this.getArany() < masikEredmeny.getArany())
			return 1;
		else
			return 0;
	}

	/**
	 * Olyan Getter metodus, mely visszaadja az Eredmeny aranyat.
	 * 
	 * @return arany , az Eredmeny aranya
	 */
	public int getArany() {
		return arany;
	}

	/**
	 * Olyan Setter metodus, mely beallitja az Eredmeny aranyat.
	 * 
	 * @param arany , az Eredmeny aranya
	 */
	public void setArany(int arany) {
		this.arany = arany;
	}

	/**
	 * Olyan Getter metodus, mely visszaadja az osszes jatszma szamat.
	 * 
	 * @return osszJatszma , az osszes jatszma szama
	 */
	public int getOsszJatszma() {
		return osszJatszma;
	}

	/**
	 * Olyan Setter metodus, mely beallitja az osszes jatszmak szamat.
	 * 
	 * @param osszJatszma , az osszes jatszmak szama
	 */
	public void setOsszJatszma(int osszJatszma) {
		this.osszJatszma = osszJatszma;
	}

	/**
	 * Olyan Getter metodus mely visszaadja a nyertes jatszmak szamat.
	 * 
	 * @return nyertJatszma , a nyertes jatszmak szama
	 */
	public int getNyertJatszma() {
		return nyertJatszma;
	}

	/**
	 * Olyan Setter metodus mely beallitja a nyertes jatszmak szamat.
	 * 
	 * @param nyertJatszma , a nyertes jatszmak szama
	 */
	public void setNyertJatszma(int nyertJatszma) {
		this.nyertJatszma = nyertJatszma;
	}

	/**
	 * Olyan Getter metodus, mely visszaadja az Eredmenyt elero jatekos nevet.
	 * 
	 * @return felhasznalo , a jatekos neve
	 */
	public String getFelhasznalo() {
		return felhasznalo;
	}

	/**
	 * Olyan Setter metodus, mely beallitja az Eredmenyt elero jatekos nevet.
	 * 
	 * @param felhasznalo , a jatekos neve
	 */
	public void setFelhasznalo(String felhasznalo) {
		this.felhasznalo = felhasznalo;
	}

	/**
	 * Olyan metodus, mely visszaadja az Eredmeny String-reprezentaciojat.
	 * 
	 * @return reprezentacio , az Eredmeny Stringkent reprezentalva
	 */
	@Override
	public String toString() {
		return "Eredmeny [arany=" + arany + ", osszJatszma=" + osszJatszma
				+ ", nyertJatszma=" + nyertJatszma + ", felhasznalo="
				+ felhasznalo + "]";
	}
	
}
