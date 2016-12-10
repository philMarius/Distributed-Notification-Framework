package newsOutlet.client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Philip on 09/12/2016.
 */
public class JoinChannel extends JFrame {
	private JPanel content;
	private JLabel joinChannelLabel;
	private JList listOfChannels;
	private JScrollPane listOfChanelsPane;
	private JButton subscribeButton;
	
	public JoinChannel() throws HeadlessException {
		super("Join Room");
		this.setContentPane(this.content);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
	}
	
	private void createUIComponents() {
		// TODO: place custom component creation code here
		String[] data = {"CNN", "BBC", "Sky News", "Spiegel", "Daily Mail", "Richmond & Twickenham Times"};
		listOfChannels = new JList(data);
		listOfChannels.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listOfChannels.setLayoutOrientation(JList.VERTICAL);
		listOfChannels.setVisibleRowCount(-1);
		
		listOfChanelsPane = new JScrollPane(listOfChannels);
	}
}
