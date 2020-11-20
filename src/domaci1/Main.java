package domaci1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class Main {

	public static void main(String[] args) {
		
		JFrame frame = new MainFrame();
		frame.setVisible(true);
		
	//	REFFERENCE: https://stackoverflow.com/questions/3035880/how-can-i-create-a-bar-in-the-bottom-of-a-java-app-like-a-status-bar
		
		JPanel statusBar = new JPanel();
		statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
		frame.add(statusBar, BorderLayout.SOUTH);
		statusBar.setPreferredSize(new Dimension(frame.getWidth(), 22));
		statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS));
		
		LocalTime currentLocalTime = LocalTime.now();
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm");
		String formattedTime = currentLocalTime.format(timeFormat);
		
		LocalDate currentLocalDate = LocalDate.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		String formattedDate = currentLocalDate.format(dateFormat);
		
		JLabel SSluzba = new JLabel("Studentska sluzba", SwingConstants.LEFT);
		statusBar.add(SSluzba);
		statusBar.add(Box.createGlue());
		statusBar.add(Box.createHorizontalStrut(frame.getWidth()-240));
		
		JLabel DateAndTime = new JLabel(formattedTime + "   " + formattedDate, SwingConstants.RIGHT);		
		statusBar.add(DateAndTime);
		
		frame.setVisible(true);

	}

}

