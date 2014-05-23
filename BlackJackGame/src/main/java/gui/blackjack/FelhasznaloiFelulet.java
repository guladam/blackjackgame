package gui.blackjack;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A felhasznaloi felulet letrehozasaert felelos osztaly.
 */
public class FelhasznaloiFelulet {

	/**
	 * Olyan metodus, amely peldanyositja es megjeleneti a {@code FoAblak} osztalyt.
	 */
	public static void letrehoz() {
        
		FoAblak ablak = new FoAblak();
		ablak.setVisible(true);
		
    }
	
	/**
	 * Logikai fuggveny mely, megkerdezi a felhasznalotol, hogy be kivanja-e kapcsolni a grafikus feluletet.
	 * 
	 * @return <code>true</code> val ter vissza ha be kivanja kapcsolni a GUI-t, <code>false</code> al egyebkent
	 */
	public static boolean bekapcsolGUI(){
		
		int valasz;
		
		JFrame ablak = new JFrame();
		ablak.setLayout(new BorderLayout());
		
		Object[] lehetosegek = {"Grafikus felulet", "Konzolos felulet"};
		
		valasz = JOptionPane.showOptionDialog(ablak, "Valassza ki a kivant megjelenitesi modot!", 
				"Felulet kivalasztasa", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, lehetosegek, lehetosegek[0]);
		
		if(valasz == JOptionPane.YES_OPTION)
			return true;
		else
			return false;
	}
	
}
