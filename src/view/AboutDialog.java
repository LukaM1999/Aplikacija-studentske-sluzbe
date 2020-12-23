package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AboutDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AboutDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);

		setSize(600, 600);
		setLocationRelativeTo(parent);
		getContentPane().setLayout(null);

		JTextArea textAbout = new JTextArea();
		textAbout.setTabSize(4);
		textAbout.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textAbout.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAbout);
        
        //REFERENCE: 
        //https://www.daniweb.com/programming/software-development/threads/203262/how-to-show-a-text-file-jtextarea
        try {
        	
			BufferedReader input = new BufferedReader(
					new FileReader("help and about" + File.separator + "AboutDijalog.txt"));
			try {
				String line = null;
				
				while (( line = input.readLine()) != null){
					textAbout.append(line+"\n");
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
