package view;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Tabs extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2949880040869967205L;
	
	private static int tabIndex = 0;
	
	private static TablePredmet predmetiTable;
	
	private static TableStudent studentiTable;
	
	private static TableProfesor profesoriTable;
	
	private static Tabs instance = null;
	
	public static Tabs getInstance() {
		if (instance == null) {
			instance = new Tabs();
		}
		return instance;
	}
	
	public static int getTabIndex() {
		
		return tabIndex;
	}
	
	public Tabs() {
		
		studentiTable = TableStudent.getInstance();
        JScrollPane stud = new JScrollPane(studentiTable);
        
        profesoriTable = TableProfesor.getInstance();
        JScrollPane prof = new JScrollPane(profesoriTable);
        
        predmetiTable = TablePredmet.getInstance();
        JScrollPane pred = new JScrollPane(predmetiTable);
        
        addTab("Student", stud);
        addTab("Profesor", prof);
        addTab("Predmet", pred);
        addChangeListener(new ChangeListener() {

	        public void stateChanged(ChangeEvent e) {

	            tabIndex = getSelectedIndex();
	            System.out.println(tabIndex);
	        }
	    });
		        
        
	}


}
