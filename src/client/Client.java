package client;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Philip on 08/12/2016.
 */
public class Client extends JFrame {
	
	private LoginWindow loginWindow;
	
	private JPanel listOfUsersPanel, chatPanel, programLogPanel, contentPanel;
	private JButton refreshButton;
	private JList listOfUsers;
	private JScrollPane listOfUsersTextArea;
	
	public Client() {
		super("Client Chat Window");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1000, 800));
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		listOfUsersPanel = new JPanel(new BorderLayout());
		chatPanel = new JPanel();
		programLogPanel = new JPanel();
		
		loginWindow = new LoginWindow(this);
		loginWindow.init();
	}
	
	public void init() {
		contentPanel = new JPanel(new GridBagLayout());
		this.setContentPane(contentPanel);
		
		GridBagConstraints g = new GridBagConstraints();
		
		g.gridx = 0;
		contentPanel.add(listOfUsersPanel, g);
		
		g.gridx += 1;
		contentPanel.add(chatPanel);
		
		g.gridx += 1;
		contentPanel.add(programLogPanel);
		
		this.initListOfUsersPanel();
		
		this.setVisible(true);
	}
	
	private void initListOfUsersPanel() {
		this.listOfUsersPanel.setPreferredSize(new Dimension(200, this.getHeight()));
		this.listOfUsersPanel.setMinimumSize(new Dimension(200, -1));
		this.refreshButton = new JButton("Refresh User List");
		
		//TODO Get list of user-IDs from server here
		String[] data = {"stevie-wonder", "bigMoneyCodeBoy", "xxxHACKORZxxx", "15-inch-penis", "generic_username", "your_mum"};
		JList placeholder = new JList(data);
		placeholder.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		placeholder.setLayoutOrientation(JList.VERTICAL);
		placeholder.setVisibleRowCount(-1);
		
		this.listOfUsersTextArea = new JScrollPane(placeholder);
	}
}

class LoginWindow extends JFrame {
	
	private JPanel loginPanel;
	private JPanel registerPanel;
	private Client client;
	private JLabel loginLabel, passwordLabel, registerIDLabel, registerPasswordLabel;
	private JButton loginButton;
	private JTextField loginField, registerIDTextField;
	private JPasswordField passwordField, registerPasswordTextField;
	private JButton submitButton;
	private JButton bypass = new JButton("Bypass");
	
	public LoginWindow(Client client) throws HeadlessException {
		super("Login Window");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(600, 200));
		this.setResizable(false);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		loginLabel = new JLabel("Login ID: ");
		passwordLabel = new JLabel("Password: ");
		loginField = new JTextField(20);
		passwordField = new JPasswordField(20);
		loginButton = new JButton("Login");
		
		registerIDLabel = new JLabel("Register ID: ");
		registerPasswordLabel = new JLabel("Register Password: ");
		registerIDTextField = new JTextField(20);
		registerPasswordTextField = new JPasswordField(20);
		submitButton = new JButton("Submit");
		
		this.client = client;
	}
	
	/**
	 * Initialises the LoginWindow
	 * TODO Check for response from server and crash out if none
	 */
	public void init() {
		JPanel contentPanel = new JPanel(new GridBagLayout());
		this.setContentPane(contentPanel);
		GridBagConstraints g = new GridBagConstraints();
		
		this.loginPanel = new JPanel();
		this.registerPanel = new JPanel();
		
		g.gridx = 0;
		contentPanel.add(loginPanel, g);
		
		g.gridx = 1;
		contentPanel.add(registerPanel, g);
		
		this.initLogin();
		this.initRegister();
		
		this.setVisible(true);
	}
	
	/**
	 * Initialisation for the login half of the login window
	 * TODO Check that user credentials are correct
	 */
	private void initLogin() {
		this.loginPanel.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		g.fill = GridBagConstraints.HORIZONTAL;
		
		loginPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		
		g.gridx = 0;
		g.gridy = 0;
		loginPanel.add(loginLabel, g);
		
		g.gridx += 1;
		loginPanel.add(loginField, g);
		
		g.gridx -= 1;
		g.gridy += 1;
		loginPanel.add(passwordLabel, g);
		
		g.gridx += 1;
		loginPanel.add(passwordField, g);
		
		g.gridy += 1;
		loginPanel.add(loginButton, g);
		
		g.gridy += 1;
		loginPanel.add(bypass, g);
		
		bypass.addActionListener(e -> {
			LoginWindow.this.dispose();
			LoginWindow.this.client.init();
		});
	}
	
	/**
	 * Initialisation of the registering part of the window
	 * TODO Check for username usage
	 */
	private void initRegister() {
		this.registerPanel.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		g.fill = GridBagConstraints.HORIZONTAL;
		
		registerPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		
		g.gridx = 0;
		g.gridy = 0;
		registerPanel.add(registerIDLabel, g);
		
		g.gridx += 1;
		registerPanel.add(registerIDTextField, g);
		
		g.gridx -= 1;
		g.gridy += 1;
		registerPanel.add(registerPasswordLabel, g);
		
		g.gridx += 1;
		registerPanel.add(registerPasswordTextField, g);
		
		g.gridy += 1;
		registerPanel.add(submitButton, g);
	}
	
}
