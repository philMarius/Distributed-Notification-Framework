package newsOutlet.client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Philip on 13/12/2016.
 */
public class ListOfConnections extends JFrame {
	
	private JList<String> listOfConnections;
	private JPanel content;
	private JScrollPane channelScrollPane;
	private String[] sources;
	
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
//		this.listOfConnections.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		this.listOfConnections.setLayoutOrientation(JList.VERTICAL);
//		this.listOfConnections.setVisibleRowCount(-1);

		this.channelScrollPane = new JScrollPane(this.listOfConnections);
	}
}
