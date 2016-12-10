package newsOutlet.client;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Philip on 09/12/2016.
 */
public class Login extends JFrame {
	private JPanel content;
	private JTextField userIDTextField;
	private JLabel userIDLabel;
	private JButton enterButton;
	private Client client;
	
	public Login(Client client) {
		super("Login Window");
		this.setContentPane(this.content);
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.client = client;
		
		this.enterButton.addActionListener(e -> Login.this.enter());
		
		userIDTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Login.this.enter();
				}
			}
		});
	}
	
	public void enter() {
		this.dispose();
		this.client.init(this.userIDTextField.getText());
	}
	
	
	private void createUIComponents() {
		// TODO: place custom component creation code here
	}
}
