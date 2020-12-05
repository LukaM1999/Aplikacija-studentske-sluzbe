package domaci1;

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

public class MyStatusBar {
	JPanel statusBar;

	public JPanel getStatusBar() {
		return this.statusBar;
	}
	
	public void setStatusBar(JPanel sb) {
		this.statusBar = sb;
	}
	
	public MyStatusBar() {
		this.statusBar = new JPanel();
	}
	
	public void createStatusBar() {
	statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
	statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS));
			
	JLabel SSluzba = new JLabel("Studentska sluzba", SwingConstants.LEFT);
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
