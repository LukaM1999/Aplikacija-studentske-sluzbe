package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;


/**
 * Klasa u kojoj se kreira statusna traka
 * na dnu glavnog prozora.
 * 
 * @author Luka Miletić
 *
 */
public class MyStatusBar {
	
	/**
	 * Objekat statusne trake.
	 */
	JPanel statusBar;
	
	/**
	 * Dobavlja objekat statusne trake.
	 * 
	 * @return objekat statusne trake
	 */
	public JPanel getStatusBar() {
		return this.statusBar;
	}
	
	/**
	 * Postavlja polje <code>statusBar</code> na vrednost
	 * prosledjenog objekta statusne trake.
	 * 
	 * @param sb objekat statusne trake
	 */
	public void setStatusBar(JPanel sb) {
		this.statusBar = sb;
	}
	
	/**
	 * Kreira statusnu traku koja sadrži naziv aplikacije.
	 * Takođe prikazuje trenutno vreme i datum.
	 */
	public MyStatusBar() {
		this.statusBar = new JPanel();
	}
	
	public void createStatusBar() {
	statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
	statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS));
			
	JLabel SSluzba = new JLabel("Studentska služba", SwingConstants.LEFT);
	statusBar.add(SSluzba);
	statusBar.add(Box.createGlue());

	SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm dd.MM.yyyy.");
	
//	REFERENCE: https://stackoverflow.com/questions/2959718/dynamic-clock-in-java
	
	JLabel DateAndTime = new JLabel();
	ActionListener updateClockAction = new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
		      DateAndTime.setText(timeFormat.format(new Date()).toString());
		    }
	};
	Timer t = new Timer(100, updateClockAction);
	t.start();
	statusBar.add(DateAndTime);
	}
	
}
