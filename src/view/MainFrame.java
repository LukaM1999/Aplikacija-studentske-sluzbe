/**
 * 
*/
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import controller.OcenaController;
import controller.PredmetController;
import controller.ProfesorController;
import controller.StudentController;
import view.abstractTableModels.AbstractTableModelPredmet;
import view.abstractTableModels.AbstractTableModelProfesor;
import view.abstractTableModels.AbstractTableModelStudent;
import view.tables.TablePredmet;
import view.tables.TableProfesor;
import view.tables.TableStudent;

/**
 * Ova klasa predstavlja instancu glavnog prozora.
 * 
 * @author Mihajlo Kisić
 */
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6391879538928021934L;
	
	/**
	 * Instanca glavnog prozora inicijalno postavljena na <code>null</code>.
	 */
	private static MainFrame instance = null;
	
	/**
	 * Dobavlja instancu glavnog prozora po Singleton šablonu.
	 * 
	 * @return instanca glavnog prozora
	 */
	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}

	/**
	 * Ažurira izgled tabele studenata.
	 * 
	 * @param akcija kakav vid azuriranja je u pitanju
	 * @param vrednost vrednost korespondentna vidu azuriranja
	 */
	public void azurirajStudente(String akcija, int vrednost) {
		AbstractTableModelStudent model = (AbstractTableModelStudent) TableStudent.getInstance().getModel();
		model.fireTableDataChanged();
		validate();
	}

	/**
	 * Ažurira izgled tabele profesora.
	 * 
	 * @param akcija kakav vid azuriranja je u pitanju
	 * @param vrednost vrednost korespondentna vidu azuriranja
	 */
	public void azurirajProfesore(String akcija, int vrednost) {
		AbstractTableModelProfesor model = (AbstractTableModelProfesor) TableProfesor.getInstance().getModel();
		model.fireTableDataChanged();
		validate();
	}

	/**
	 * Ažurira izgled tabele predmeta.
	 * 
	 * @param akcija kakav vid azuriranja je u pitanju
	 * @param vrednost vrednost korespondentna vidu azuriranja
	 */
	public void azurirajPredmete(String akcija, int vrednost) {
		AbstractTableModelPredmet model = (AbstractTableModelPredmet) TablePredmet.getInstance().getModel();
		model.fireTableDataChanged();
		validate();
	}

	/**
	 * Ažurira izgled tabele studenata za prosledjeni
	 * red u tabeli koji je izmenjen.
	 * 
	 * @param row indeks izmenjenog reda tabele studenata	
	 */
	public void izmeniStudenta(int row) {
		AbstractTableModelStudent model = (AbstractTableModelStudent) TableStudent.getInstance().getModel();
		model.fireTableRowsUpdated(row, row);
		validate();
	}

	/**
	 * Ažurira izgled tabele profesora za prosledjeni
	 * red u tabeli koji je izmenjen.
	 * 
	 * @param row indeks izmenjenog reda tabele profesora	
	 */
	public void izmeniProfesora(int row) {
		AbstractTableModelProfesor model = (AbstractTableModelProfesor) TableProfesor.getInstance().getModel();
		model.fireTableRowsUpdated(row, row);
		validate();
	}

	/**
	 * Ažurira izgled tabele predmeta za prosledjeni
	 * red u tabeli koji je izmenjen.
	 * 
	 * @param row indeks izmenjenog reda tabele predmeta	
	 */
	public void izmeniPredmet(int row) {
		AbstractTableModelPredmet model = (AbstractTableModelPredmet) TablePredmet.getInstance().getModel();
		model.fireTableRowsUpdated(row, row);
		validate();
	}

	/**
	 * Konstruktor pravi glavni prozor Studentske službe.
	 * Glavni prozor se sastoji od meni trake, tool trake, trake
	 * sa trenutnim datumom i vremenom na dnu i tri taba sa tabelama
	 * koje odgovaraju njihovim nazivima.
	 */
	public MainFrame() {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		// postavljam dimenzije prozora i centriramo ga
		int sirina = screenWidth * 3 / 4;
		int duzina = screenHeight * 3 / 4;
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
		add(statusBar.getStatusBar(), BorderLayout.SOUTH);
		statusBar.createStatusBar();

		Tabs tabs = Tabs.getInstance();
		add(tabs, BorderLayout.CENTER);

		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				int answer = JOptionPane.showConfirmDialog(getContentPane(),
						"Da li ste sigurni? :(", "Gašenje studentske službe",
						JOptionPane.OK_CANCEL_OPTION);	
				if(answer == JOptionPane.YES_OPTION) {
					windowClosed(e);
				}
				else {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				//serijalizacija uradjena uz pomoc vezbi - XStream primer
				StudentController.getInstance().XstreamSerialization("deserijalizacija" + File.separator + "studenti.xml");
				ProfesorController.getInstance().XstreamSerialization("deserijalizacija" + File.separator + "profesori.xml"); 
				PredmetController.getInstance().XstreamSerialization("deserijalizacija" + File.separator + "predmeti.xml"); 
				OcenaController.getInstance().XstreamSerialization("deserijalizacija" + File.separator + "ocene.xml");
				setDefaultCloseOperation(EXIT_ON_CLOSE);
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
