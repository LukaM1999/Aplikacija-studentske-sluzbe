/**
 * 
*/
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;


//Koriscen materijal sa vezbi
public class MainFrame extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6391879538928021934L;
	
	private static MainFrame instance = null;
	
	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
	
	
	public void azurirajStudente(String akcija, int vrednost) {
		AbstractTableModelStudent model = (AbstractTableModelStudent) TableStudent.getInstance().getModel();
		model.fireTableDataChanged();
		validate();
	}
	
	public void azurirajProfesore(String akcija, int vrednost) {
		AbstractTableModelProfesor model = (AbstractTableModelProfesor) TableProfesor.getInstance().getModel();
		model.fireTableDataChanged();
		validate();
	}
	
	
	
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
		
		JMenuBar menuBar = new MyMenuBar();
		setJMenuBar(menuBar);
		menuBar.setBackground(Color.WHITE);
		
		Toolbar toolbar = new Toolbar();
		add(toolbar, BorderLayout.NORTH);
		
		MyStatusBar statusBar = new MyStatusBar();
		add(statusBar.getStatusBar(),BorderLayout.SOUTH);
		statusBar.createStatusBar();
		
		Tabs tabs = Tabs.getInstance();
		add(tabs,BorderLayout.CENTER);
        
		
	}
}

