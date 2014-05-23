package gui.blackjack;

import game.blackjack.Kartya;
import highscore.blackjack.RangLista;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import java.awt.GridLayout;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.blackjack.Allas;
import play.blackjack.Dontes;
import play.blackjack.Jatek;
import play.blackjack.Kor;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * JFrame-bol szarmaztatott osztaly, mely implementalja a tenyleges grafikus feluletet a programban.
 */
@SuppressWarnings("serial")
public class FoAblak extends JFrame implements ActionListener{

	/**
	 * A naplozashoz szukseges logger mezo. 
	 */
	private static Logger logger = LoggerFactory.getLogger(FoAblak.class);
	
	/**
	 * Az ablak fopanele.
	 */
	private JPanel tartalomPanel;
	
	/**
	 * Az ablak tetejen talalhato menusor. 
	 */
	private JMenuBar menuSor;
	
	/**
	 * A menusorban elhelyezkedo fomenu.
	 */
	private JMenu foMenu;
	
	/**
	 * A menusorban elhelyezkedo beallitasok menu.
	 */
	private JMenu beallitasok;
	
	/**
	 * A menusorban elhelyezkedo segitseg menu.
	 */
	private JMenu segitseg;
	
	/**
	 * A fomenuben talalhato uj jatek menuelem.
	 */
	private JMenuItem ujJatek;
	
	/**
	 * A fomenuben talalhato ranglista megtekintese menuelem.
	 */
	private JMenuItem ranglista;
	
	/**
	 * A fomenuben talalhato ranglista uritese menuelem.
	 */
	private JMenuItem ranglistaUrit;
	
	/**
	 * A fomenuben talalhato kilepes menuelem.
	 */
	private JMenuItem kilepes;
	
	/**
	 * A beallitasok menuben talalhato felhasznalonev beallitasa menuelem.
	 */
	private JMenuItem felhasznaloNev;
	
	/**
	 * A segitseg menuben talalhato szabalyok menuelem.
	 */
	private JMenuItem szabalyok;
	
	/**
	 * A segitseg menuben talalhato rolunk menuelem.
	 */
	private JMenuItem rolunk;
	
	/**
	 * A fopanel Deli reszen elhelyezkedo parancspanel.
	 */
	private JPanel parancsPanel;
	
	/**
	 * A kartyakat tartalmazo kartyapanel.
	 */
	private JPanel kartyaPanel;
	
	/**
	 * Az oszto kartyait tartalmazo osztopanel.
	 */
	private JPanel osztoPanel;
	
	/**
	 * A jatekos kartyait tartalmazo jatekospanel.
	 */
	private JPanel jatekosPanel;
	
	/**
	 * A fopanel Eszaki reszen elhelyezkedo statuszpanel.
	 */
	private JPanel statuszPanel;
	
	/**
	 * A fopanel Keleti reszen elhelyezkedo ertekpanel.
	 */
	private JPanel ertekPanel;
	
	/**
	 * A jatekospanelen elhelyezkedo 1. kez panel.
	 */
	private JPanel kez1;
	
	/**
	 * A jatekospanelen elhelyezkedo 2. kez panel.
	 */
	private JPanel kez2;
	
	/**
	 * Az ertekpanelen elhelyezkedo jatekos ertek panel.
	 */
	private JPanel jatekosErtekPanel;
	
	/**
	 * A parancspanelen elhelyezkedo laphuzas gomb.
	 */
	private JButton laphuzas;
	
	/**
	 * A parancspanelen elhelyezkedo megallas gomb.
	 */
	private JButton megallas;
	
	/**
	 * A parancspanelen elhelyezkedo szetvalasztas gomb.
	 */
	private JButton szetvalasztas;
	
	/**
	 * Az osszes jatszma szamat tartalmazo cimke.
	 */
	private JLabel osszjatszma;
	
	/**
	 * A nyert jatszmak szamat tartalmazo cimke.
	 */
	private JLabel nyertjatszma;
	
	/**
	 * A gyozelmek aranyat tartalmazo cimke.
	 */
	private JLabel arany;
	
	/**
	 * A Felhasznalonevet tartalmazo cimke.
	 */
	private JLabel nev;
	
	/**
	 * Az oszto kezenek erteket tartalmazo cimke.
	 */
	private JLabel osztoErtek;
	
	/**
	 * A jatekos 1. kezenek erteket tartalmazo cimke.
	 */
	private JLabel jatekosErtek1;
	
	/**
	 * A jatekos 2. kezenek erteket tartalmazo cimke.
	 */
	private JLabel jatekosErtek2;
	
	/**
	 * A jatek egy Korenek peldanya.
	 */
	private Kor kor;

	/**
	 * Ures Konstruktor, mely beallitja az Ablak cimet, meretet, es feltolti tartalommal a tobbi metoduson keresztul.
	 */
	public FoAblak() {
		
		setTitle("BlackJack");
		//setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		
		setLocationRelativeTo(null);
		
		tartalomPanel = new JPanel();
		tartalomPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(tartalomPanel);
		tartalomPanel.setLayout(new BorderLayout(10, 10));
		
		addWindowListener( new WindowAdapter() {
		   
			@Override
			public void windowClosing(WindowEvent e) {
				biztosanKilepsz();
			}
		 
		});
		
		setParancsPanel();
		setKartyaPanel();
		setStatuszPanel();
		setMenu();
		setErtekPanel();
		
	}

	/**
	 * Olyan metodus, amely a statusz panel megfelelo beallitasaert felel.
	 */
	private void setStatuszPanel() {
		statuszPanel = new JPanel();
		statuszPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
		tartalomPanel.add(statuszPanel, BorderLayout.NORTH);
		osszjatszma = new JLabel("Osszes jatszma: 0");
		nyertjatszma = new JLabel("Nyert jatszma: 0");
		arany = new JLabel("Arany: 0%");
		nev = new JLabel("Felhasznalonev: " + Jatek.jatekosNeve);
		
		statuszPanel.add(osszjatszma);
		statuszPanel.add(nyertjatszma);
		statuszPanel.add(arany);
		statuszPanel.add(nev);
	}

	/**
	 * Olyan metodus, amely a KartyaPanel megfelelo beallitasaert felel.
	 */
	private void setKartyaPanel() {
		kartyaPanel = new JPanel();
		tartalomPanel.add(kartyaPanel, BorderLayout.CENTER);
		kartyaPanel.setBorder(new EmptyBorder(0, 75, 0, 50));
		kartyaPanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		osztoPanel = new JPanel();
		kartyaPanel.add(osztoPanel);
		osztoPanel.setLayout(new FlowLayout());
		
		jatekosPanel = new JPanel();
		kartyaPanel.add(jatekosPanel);
		jatekosPanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		kez1 = new JPanel();
		kez2 = new JPanel();
		kez1.setLayout(new FlowLayout());
		kez2.setLayout(new FlowLayout());
		
		jatekosPanel.add(kez1);
		jatekosPanel.add(kez2);
	}

	/**
	 * Olyan metodus, amely a parancspanel megfelelo beallitasaert felel.
	 */
	private void setParancsPanel() {
		parancsPanel = new JPanel();
		parancsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
		tartalomPanel.add(parancsPanel, BorderLayout.SOUTH);
		
		laphuzas = new JButton("Laphuzas");
		laphuzas.addActionListener(this);
		laphuzas.setEnabled(false);
		
		megallas = new JButton("Megallas");
		megallas.addActionListener(this);
		megallas.setEnabled(false);
		
		szetvalasztas = new JButton("Szetvalasztas");
		szetvalasztas.addActionListener(this);
		szetvalasztas.setEnabled(false);
		
		parancsPanel.add(laphuzas);
		parancsPanel.add(megallas);
		parancsPanel.add(szetvalasztas);
	}

	/**
	 * Olyan metodus, mely a menusor megfelelo beallitasaert felel.
	 * Itt vannak implementalva a menuelem ActionListener-jei is.
	 */
	private void setMenu(){
		
		menuSor = new JMenuBar();
		setJMenuBar(menuSor);
		
		foMenu = new JMenu("Fomenu");
		ujJatek = new JMenuItem("Uj Jatek");
		ranglista = new JMenuItem("Ranglista megtekintese");
		ranglistaUrit = new JMenuItem("Ranglista uritese");
		kilepes = new JMenuItem("Kilepes");
		
		beallitasok = new JMenu("Beallitasok");
		felhasznaloNev = new JMenuItem("Felhasznalonev beallitasa");

		segitseg = new JMenu("Segitseg");
		szabalyok = new JMenuItem("Szabalyok");
		rolunk = new JMenuItem("Rolunk");

		menuSor.add(foMenu);
		menuSor.add(beallitasok);
		menuSor.add(segitseg);
		
		foMenu.add(ujJatek); 
		foMenu.add(ranglista);
		foMenu.add(ranglistaUrit);
		foMenu.add(kilepes);
		beallitasok.add(felhasznaloNev);
		segitseg.add(szabalyok);
		segitseg.add(rolunk);
		
		ujJatek.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				
				ujKorKezdes();
				
			}
		});
	
		ranglista.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				rangListaMegjelenit();
			}
		});
	
		ranglistaUrit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					RangLista.uritLista();
					megjelenitInformacio("A ranglista kiuritese sikeres!");
					logger.info("A Ranglistat a felhasznalo kiuritette.");
				} catch (IOException e1) {
					logger.error("A Ranglista kiuritese sikertelen volt! Kivetel: " + e1.getClass());
				}
				
			}
		});
		
		kilepes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				biztosanKilepsz();
			}
		});
	
		felhasznaloNev.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ujNev = JOptionPane
						.showInputDialog(null, "Add meg az uj felhasznaloneved: ", "", 1);
				Jatek.jatekosNeve = ujNev;
				
				nev.setText("Felhasznalonev: " + ujNev);
				
			}
		});
	
		szabalyok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String szabaly;
				try {
					szabaly = Betolto.betoltSzoveg("szabalyok.data");
					logger.info("A szabalyok betoltese sikeres!");
				} catch (NullPointerException | IOException e1) {
					szabaly = "";
					logger.error("A szabalyok betoltese sikertelen! Kivetel: " + e1.getClass());
				}
				
				megjelenitInformacio(szabaly);
				
			}
		});
	
		rolunk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String rolunk;
				try {
					rolunk = Betolto.betoltSzoveg("rolunk.data");
					logger.info("A rolunk szoveg betoltese sikeres!");
				} catch (IllegalArgumentException | IOException e1) {
					rolunk = "";
					logger.error("A rolunk szoveg betoltese sikertelen! Kivetel: " + e1.getClass());
				}
				
				megjelenitInformacio(rolunk);
			}
		});
	}
	
	/**
	 * Olyan metodus, mely az ertekpanel megfelelo beallitasaert felel.
	 */
	private void setErtekPanel(){
		osztoErtek = new JLabel("Ertek: 0");
		jatekosErtekPanel = new JPanel();
		jatekosErtekPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		jatekosErtek1 = new JLabel("Ertek: 0");
		jatekosErtek2 = new JLabel("");
		
		jatekosErtekPanel.add(jatekosErtek1);
		jatekosErtekPanel.add(jatekosErtek2);
		
		ertekPanel = new JPanel();
		tartalomPanel.add(ertekPanel, BorderLayout.EAST);
		ertekPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		ertekPanel.add(osztoErtek);
		ertekPanel.add(jatekosErtekPanel);
	}
	
	/**
	 * A jatek megfelelo mukodesehez ebben a metodusban tortenik meg a parancsok megfelelo kezelese egy-egy kor folyaman.
	 * @param event , a kivalto esemeny (kattintaskor)
	 */
	@Override
 	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == szetvalasztas){
			kor.jatekosKore
			(kor.getEmberJatekosok().get(0), Dontes.Szetvalaszt);
			megjelenitInformacio("Szetvalasztottad a lapjaid!");
			
			kez1.removeAll();
			
			megjelenitKartya(kez1, kor.getEmberJatekosok().get(0)
					.getKez().getLapok().getLast());
			
			megjelenitKartya(kez2, kor.getEmberJatekosok().get(1)
					.getKez().getLapok().getLast());
			
			setJatekosErtekek();
			
			Jatek.osszesJatszma++;
			
			szetvalasztas.setEnabled(false);
			kor.setSzetValasztott(true);
		}
				
		if(event.getSource() == laphuzas && kor.isVanDontes()){
			
			if( szetvalasztas.isEnabled() )
				szetvalasztas.setEnabled(false);
			
			if(kor.getAktualisKez() == 1){
				kor.jatekosKore(kor.getEmberJatekosok().get(0), Dontes.Huz);
				megjelenitKartya(kez1, kor.getEmberJatekosok().get(0)
						.getKez().getLapok().getLast());
			}
			
			if(kor.getAktualisKez() == 2){
				kor.jatekosKore(kor.getEmberJatekosok().get(1), Dontes.Huz);
				megjelenitKartya(kez2, kor.getEmberJatekosok().get(1)
						.getKez().getLapok().getLast());
			}
			
			setJatekosErtekek();
			
			if( kor.mindenKezBesokallt() ){
					ujJatek.setEnabled(true);
					laphuzas.setEnabled(false);
					megallas.setEnabled(false);
					kor.setKorVege(true);
					statuszUpdate();
					megjelenitEredmeny("Besokalltal! Az oszto nyert!");
			}
			
			if( kor.getAktualisKez() == 1 && kor.tobbMint21
					(kor.getEmberJatekosok().get(0)) && kor.isSzetValasztott()){
				
				kor.setAktualisKez(2);
				kor.setVanDontes(true);
			}
			
			if( kor.getAktualisKez() == 2 && kor.tobbMint21
					(kor.getEmberJatekosok().get(1)) ){
				
				kor.removeEmberJatekos(1);
				kor.setAktualisKez(1);
				
				if(!kor.isKorVege())
					gepKorVege();
			}
			
		}

		if(event.getSource() == megallas){
			
			if(kor.getAktualisKez() == 2)
				kor.jatekosKore(kor.getEmberJatekosok().get(1), Dontes.Megall);
			
			if(kor.getAktualisKez() == 1){
				kor.jatekosKore(kor.getEmberJatekosok().get(0), Dontes.Megall);
				
				if(kor.isSzetValasztott()){
					kor.setVanDontes(true);
					megjelenitInformacio("Az elso kez megallt!");
				}
			}

			
			if( !kor.mindenKezBesokallt() && kor.isKorVege()){
				gepKorVege();
			}
		}
		
	}

	/**
	 * Olyan metodus, mely egy uj kor inditasaert felel. 
	 */
	public void ujKorKezdes(){
		kez1.removeAll();
		kez2.removeAll();
		osztoPanel.removeAll();
		
		osztoErtek.setText("");
		jatekosErtek1.setText("");
		jatekosErtek2.setText("");
		
		szetvalasztas.setEnabled(false);
		
		revalidate(); repaint();
		
		kor = new Kor();
		kor.korKezdes();
		
		for(Kartya kartya : kor.getEmberJatekosok().get(0).getKez().getLapok()){
			megjelenitKartya(kez1, kartya);
		}
		for(Kartya kartya : kor.getGepJatekos().getKez().getLapok()){
			megjelenitKartya(osztoPanel, kartya);
		}
		
		osztoErtek.setText(getGepErtek());
		setJatekosErtekek();
		
		if(kor.getAllas().size() == 1 && kor.getAllas().get(0) == Allas.jatekosBJ)
			megjelenitEredmeny(kor.getAllasUzenet(kor.getAllas().get(0)));
		else if(kor.getAllas().size() == 1 && kor.getAllas().get(0) == Allas.dontetlen)
			megjelenitEredmeny(kor.getAllasUzenet(kor.getAllas().get(0)));
		
		if(!kor.isKorVege()){
			laphuzas.setEnabled(true);
			megallas.setEnabled(true);

			if(kor.getEmberJatekosok().get(0).szetValaszthat())
				szetvalasztas.setEnabled(true);

			ujJatek.setEnabled(false);
		}
		
		
		statuszUpdate();
		
		Jatek.osszesJatszma++;
		
	}
	
	/**
	 * Olyan metodus, mely a kor vegen lejatszo az oszto koret, amennyiben erre szukseg van.
	 */
	public void gepKorVege(){
		
		kor.gepKore();
		
		osztoPanel.removeAll();
		
		for(Kartya kartya : kor.getGepJatekos().getKez().getLapok())
			megjelenitKartya(osztoPanel, kartya);

		osztoErtek.setText("Ertek: " + kor.getGepJatekos().getKez()
				.getErtek());

		laphuzas.setEnabled(false);
		megallas.setEnabled(false);

		if(kor.getAllas().size() == 1){
			megjelenitEredmeny(kor.getAllasUzenet(kor.getAllas().get(0)));
		} else if (kor.getAllas().size() == 2){
			megjelenitInformacio(kor.getAllasUzenet(kor.getAllas().get(0)));
			megjelenitEredmeny(kor.getAllasUzenet(kor.getAllas().get(1)));
		}
		ujJatek.setEnabled(true);
		
		statuszUpdate();

	}
	
	/**
	 * Olyan metodus, mely a jatekos ertekpanelen beallitja az aktualis kez vagy kezek erteket.
	 */
	public void setJatekosErtekek(){

		jatekosErtek1.setText("Ertek: " + kor.getEmberJatekosok().get(0)
				.getKez().getErtek());
		
		if(kor.getEmberJatekosok().size() == 2){
			jatekosErtek2.setText("Ertek: " + kor.getEmberJatekosok().get(1)
					.getKez().getErtek());
		}
		
	}
	
	/**
	 * Olyan metodus, mely visszaadja String-kent az oszto ertekpanelen megjelenitendo szoveget.
	 * 
	 * @return szoveg - az oszto ertekpanelenek szovege
	 */
	public String getGepErtek(){
		return "Ertek: " + 	String.valueOf(kor.getGepJatekos().getKez()
				.getErtek());
	}

	/**
	 * Olyan metodus, mely a Betolto osztaly segitsegevel megjeleniti a parameterkent megadott a kartyat a megadott panelen.
	 * 
	 * @param panel , az a panel, amelyiken szeretnenk latni a kartyat
	 * @param kartya , az a kartya amelyet szeretnenk latni az adott panelen
	 */
	public void megjelenitKartya(JPanel panel, Kartya kartya){
		
		try {
			panel.add(new JLabel(new ImageIcon(Betolto.betoltKep(kartya.getSzin()
					.name() + "_" + kartya.getNev()))));
			logger.info("A kartya megjelenitese sikeres!");
		} catch (IOException | IllegalArgumentException e) {
			panel.add(new JLabel(kartya.toString()));
			logger.error("A kartya kep betoltese sikertelen! Kivetel: " + e.getClass());
		}
	}
	
	/**
	 * Olyan metodus mely megjelenit egy JOptionPane uzenetpanelt a felhasznalo szamara, a parameterkent megadott uzenettel.
	 * 
	 * @param uzenet , a megjelenitendo uzenet
	 */
	public void megjelenitInformacio(String uzenet){
		JOptionPane.showInternalMessageDialog(tartalomPanel, uzenet,
				"Informacio", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Olyan metodus, mely megjeleniti az adott kor eredmenyet, es felkinalja az uj jatek lehetoseget.
	 * 
	 * @param uzenet , a megjelenitendo eredmeny String formaban
	 */
	public void megjelenitEredmeny(String uzenet){
		Object[] lehetosegek = {"Uj Jatek", "Ok"};
		int valasz;
		
		valasz = JOptionPane.showOptionDialog(tartalomPanel, uzenet, 
				"Eredmeny", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, lehetosegek, lehetosegek[0]);
		
		if(valasz == JOptionPane.YES_OPTION){
			ujKorKezdes();
		}
	}
	
	/**
	 * Olyan metodus mely a RangLista osztaly segitsegevel megjeleniti az aktualis ranglista elso 10 elemet.
	 */
	public void rangListaMegjelenit(){
		
		try {
			JOptionPane.showInternalMessageDialog(tartalomPanel, RangLista.olvasLista(10),
					"Ranglista", JOptionPane.INFORMATION_MESSAGE);
			logger.info("Ranglista beolvasva!");
		} catch (IOException e) {
			JOptionPane.showInternalMessageDialog(tartalomPanel, "Ures Ranglista",
					"Ranglista", JOptionPane.INFORMATION_MESSAGE);
			logger.error("A Ranglista betoltese sikertelen! Kivetel: " + e.getClass());
		}
	}
	
	/**
	 * Olyan metodus, mely kilepeskor megkerdezi a felhasznalot hogy tenyleg ki kivan-e lepni.
	 * Itt zajlik tovabba a kilepes elott az adott eredmeny hozzaadasa a ranglistahoz.
	 */
	public void biztosanKilepsz(){
		Object[] lehetosegek = {"Igen", "Nem"};
		int valasz;
		
		valasz = JOptionPane.showOptionDialog(tartalomPanel, "Biztos befejezed a Jatekot?", 
				"Kilepes", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, lehetosegek, lehetosegek[0]);
		
		if(valasz == JOptionPane.YES_OPTION){
			try {
				
				if(Jatek.osszesJatszma != 0){
					RangLista.hozzaAdLista(Jatek.jatekosNeve, Jatek.nyertJatszmak, Jatek.osszesJatszma
							, Jatek.arany);
					megjelenitInformacio("Az eredmenyed a ranglistaba rogzitesre kerult! Koszi a jatekot!");
				}
			} catch (IOException e) {
				logger.error("A Ranglistahoz hozzaadas sikertelen volt! Kivetel: " + e.getClass());
			}
			dispose();
			System.exit(0);
		}
	}
	
	/**
	 * Olyan metodus, mely a statuszpanelen megjeleno aktualis eredmeny helyesseget biztositja.
	 */
	public void statuszUpdate(){
		Jatek.arany = (double)Jatek.nyertJatszmak/Jatek.osszesJatszma*100;
		
		osszjatszma.setText("Osszes jatszma: " + Jatek.osszesJatszma);
		nyertjatszma.setText("Nyert jatszma: " + Jatek.nyertJatszmak);
		arany.setText("Arany: " + (int)Jatek.arany + "%");
	}
	
}
