package newsOutlet.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Philip on 09/12/2016.
 */
public class JoinChannel extends JFrame {
	private JPanel content;
	private JTextField userChannelInput;
	private JButton subscribeButton;
	private Client client;
	
	public JoinChannel(Client client) throws HeadlessException {
		super("Join Room");
		this.client = client;
		this.setContentPane(this.content);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
		subscribeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (userChannelInput.getText().length() >= 0) {
					try {
						JoinChannel.this.client.connectToChannel(userChannelInput.getText());
						
					} catch (RemoteException | MalformedURLException | NotBoundException e1) {
						JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please type in an amount", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
	}
	
	private void createUIComponents() {
		// TODO: place custom component creation code here
//		String[] data = {"CNN", "BBC", "Sky News", "Spiegel", "Daily Mail", "Richmond & Twickenham Times"};
//		listOfChannels = new JList(data);
//		listOfChannels.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		listOfChannels.setLayoutOrientation(JList.VERTICAL);
//		listOfChannels.setVisibleRowCount(-1);
//
//		listOfChanelsPane = new JScrollPane(listOfChannels);
	}
}
