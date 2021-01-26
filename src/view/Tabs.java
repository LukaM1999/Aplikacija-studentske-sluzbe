package view;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.tables.TablePredmet;
import view.tables.TableProfesor;
import view.tables.TableStudent;


/**
 * Klasa koja instancira tabove studenata, profesora i predmeta.
 * 
 * @author Luka Miletić
 *
 */
public class Tabs extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2949880040869967205L;
	
	/**
	 * Instanca tabova.
	 */
	private static Tabs instance = null;
	
	/**
	 * Dobavlja instancu tabova po Singleton šablonu.
	 * 
	 * @return instanca tabova
	 */
	public static Tabs getInstance() {
		if (instance == null) {
			instance = new Tabs();
		}
		return instance;
	}
	
	/**
	 * Kreira tabove studenata, profesora i predmeta
	 * koji predstavljaju tabele sa trakom za listanje i
	 * prikazanim zaglavljem.
	 */
	public Tabs() {
		
		TableStudent studentiTable = TableStudent.getInstance();
        JScrollPane stud = new JScrollPane(studentiTable);
        
        TableProfesor profesoriTable = TableProfesor.getInstance();
        JScrollPane prof = new JScrollPane(profesoriTable);
        
        TablePredmet predmetiTable = TablePredmet.getInstance();
        JScrollPane pred = new JScrollPane(predmetiTable);
        
        addTab("Student", stud);
        addTab("Profesor", prof);
        addTab("Predmet", pred);
        addChangeListener(new ChangeListener() {

	        public void stateChanged(ChangeEvent e) {

	        }
	    });
		        
        
	}


}
