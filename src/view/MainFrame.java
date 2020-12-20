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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


//Koriscen materijal sa vezbi
public class MainFrame extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6391879538928021934L;
	
	private static JTabbedPane tabs;
	
	private static int tabIndex;
	
	private static MainFrame instance = null;
	
	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
	
	public JTabbedPane getTabs() {
		return tabs;
	}

	public int getTabIndex() {
		return tabIndex;
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
		
		JPanel panel = new JPanel();
		
		panel.setBackground(Color.WHITE);
		this.add(panel);
		
		JMenuBar menuBar = new MyMenuBar();
		
		setJMenuBar(menuBar);
		menuBar.setBackground(Color.WHITE);
		
		Toolbar toolbar = new Toolbar();
		add(toolbar, BorderLayout.NORTH);
		
		MyStatusBar statusBar = new MyStatusBar();
		add(statusBar.getStatusBar(),BorderLayout.SOUTH);
		statusBar.createStatusBar();
		
		//tabovi
		
		JTabbedPane tabs = Tabs.getInstance();

        
        
       // setPreferredSize(new Dimension(sirina-100, duzina-150));

        //tabs.setMaximumSize(tabs.getPreferredSize());
        //tabs.setMinimumSize(tabs.getPreferredSize());
 
        
        //panel.add(tabs);
		add(tabs,BorderLayout.CENTER);
        
		
	}
}

