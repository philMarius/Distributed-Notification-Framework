package newsOutlet.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Philip on 09/12/2016.
 */
public class ChannelDialogue extends JFrame {
	private JPanel content;
	private JTextField userChannelInput;
	private JButton subscribeButton;
	private Client client;
	
	/**
	 * Simple channel joining GUI that asks the user what channel they wish to join, the user provides the name and the
	 * GUI shows and error box if an error occurs
	 *
	 * @param client Client class that the ChannelDialogue is attached to
	 * @throws HeadlessException
	 */
	public ChannelDialogue(Client client, String action) throws HeadlessException {
		super("Join Room");
		this.client = client;
		this.setContentPane(this.content);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		if (action.equals("join")) {
			subscribeButton.addActionListener(e -> ChannelDialogue.this.joinChannel());
			
			userChannelInput.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						ChannelDialogue.this.joinChannel();
					}
				}
			});
		} else if (action.equals("leave")) {
			subscribeButton.addActionListener(e -> ChannelDialogue.this.leaveChannel());
			
			userChannelInput.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						ChannelDialogue.this.leaveChannel();
					}
				}
			});
		}
	}
	
	private void joinChannel() {
		if (userChannelInput.getText().length() >= 0) {
			try {
				ChannelDialogue.this.client.connectToChannel(userChannelInput.getText());
				this.dispose();
				System.out.println("[CLI] Connection Successful!");
			} catch (RemoteException | MalformedURLException | NotBoundException e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Please type in a channel name", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void leaveChannel() {
		if (userChannelInput.getText().length() >= 0) {
			try {
				ChannelDialogue.this.client.leaveChannel(userChannelInput.getText());
				this.dispose();
				System.out.println("[CLI] Disconnection successful");
			} catch (RemoteException e) {
				JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Please type in channel name", "Error", JOptionPane.ERROR_MESSAGE);
			
		}
	}
	
}
