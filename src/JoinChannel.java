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
public class JoinChannel extends JFrame {
	private JPanel content;
	private JTextField userChannelInput;
	private JButton subscribeButton;
	private Client client;
	
	/**
	 * Simple channel joining GUI that asks the user what channel they wish to join, the user provides the name and the
	 * GUI shows and error box if an error occurs
	 *
	 * @param client Client class that the JoinChannel is attached to
	 * @throws HeadlessException
	 */
	public JoinChannel(Client client) throws HeadlessException {
		super("Join Room");
		this.client = client;
		this.setContentPane(this.content);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
		subscribeButton.addActionListener(e -> JoinChannel.this.joinChannel());
		
		userChannelInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					JoinChannel.this.joinChannel();
				}
			}
		});
	}
	
	private void joinChannel() {
		if (userChannelInput.getText().length() >= 0) {
			try {
				JoinChannel.this.client.connectToChannel(userChannelInput.getText());
				this.dispose();
				System.out.println("[CLI] Connection Successful!");
			} catch (RemoteException | MalformedURLException | NotBoundException e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Please type in an amount", "Error", JOptionPane.ERROR_MESSAGE);
		}
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
