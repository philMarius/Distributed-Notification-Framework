package messager.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Philip on 08/12/2016.
 */
public class Client extends JFrame {
	private JPanel content;
	private JTabbedPane channelTabs;
	private JTextArea programLog;
	private JPanel chatPanel;
	private JPanel logPanel;
	private JSplitPane mainPanel;
	private JButton joinChannelButton;
	private JButton leaveChannelButton;
	private JPanel channelPanel;
	
	public Client() throws HeadlessException {
		super("Client Chat Window");
		
		Login l = new Login(this);
	}
	
	public void init(String userID) {
		this.setContentPane(this.content);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		System.out.println(userID);
		
		joinChannelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JoinChannel jr = new JoinChannel();
			}
		});
	}
	
	private void createUIComponents() {
		// TODO: place custom component creation code here
	}
	
}
