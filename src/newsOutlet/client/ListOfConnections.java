package newsOutlet.client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Philip on 13/12/2016.
 */
public class ListOfConnections extends JFrame {
	
	private JList<String> listOfConnections; //List of open connections for client
	private JPanel content; //content pane
	private JScrollPane channelScrollPane; //scrollpane for JList
	private String[] sources; //NotificationSource names
	
	public ListOfConnections(String[] sources) throws HeadlessException {
		super("List of Connections");
		this.sources = sources;
		
		this.setContentPane(this.content);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	private void createUIComponents() {
		this.listOfConnections = new JList<>(this.sources);
		this.channelScrollPane = new JScrollPane(this.listOfConnections);
	}
	
}
