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
			
	private static Tabs instance = null;
	
	public static Tabs getInstance() {
		if (instance == null) {
			instance = new Tabs();
		}
		return instance;
	}
	
	
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

	        	System.out.println(getSelectedIndex());
	        }
	    });
		        
        
	}


}
