package view;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

public class HelpDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7532094756704393899L;

	public HelpDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);

		setSize(850, 600);
		setLocationRelativeTo(parent);
		getContentPane().setLayout(null);

		JTextArea textHelp = new JTextArea();
		textHelp.setTabSize(4);
		textHelp.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textHelp.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textHelp);
        
        //REFERENCE: 
        //https://www.daniweb.com/programming/software-development/threads/203262/how-to-show-a-text-file-jtextarea
        try {
        	
			BufferedReader input = new BufferedReader(
					new FileReader("help and about" + File.separator + "HelpDijalog.txt"));
			try {
				String line = null;
				
				while (( line = input.readLine()) != null){
					textHelp.append(line+"\n");
				}
			}
			finally {
				input.close();
			}
		}
		catch (IOException ex){
			ex.printStackTrace();
		}

        this.setPreferredSize(new Dimension(600, 600));
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
	}
}
