package domaci1;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainFrame extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6391879538928021934L;

	public MainFrame() {
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		//postavljam dimenzije prozora i centriramo ga
		int sirina =screenWidth * 3/4;
		int duzina =screenHeight * 3/4;
		setSize(sirina, duzina);
		
		setTitle("Studentska služba");
		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		this.add(panel);
	}
}

