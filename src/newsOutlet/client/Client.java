package newsOutlet.client;

import newsOutlet.newsChannel.Article;
import newsOutlet.notification.Notifiable;
import newsOutlet.notification.NotificationSink;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Philip on 08/12/2016.
 */
public class Client extends JFrame implements Notifiable {
	private JPanel content;
	private JTabbedPane channelTabs;
	private JPanel chatPanel;
	private JPanel logPanel;
	private JSplitPane mainPanel;
	private JButton joinChannelButton;
	private JButton leaveChannelButton;
	private JTextArea articleViewer;
	private JLabel articleName;
	private JButton addChannelButton;
	private JButton addArticleToBBCButton;
	private JTable articleTable;
	private JScrollPane articleListSrollPane;
	private JButton connectedChannelsButton;
	
	private NotificationSink notificationSink; //Sink attached to client
	private DefaultTableModel dtm; //used in the list of articles
	private ArrayList<Article> articleList; // list of articles to display
	private String userID; //Name of user signed in (sent to server)
	
	public Client() throws HeadlessException {
		super("Client Chat Window");
		
		Login l = new Login(this);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Client.this.exitClient();
			}
		});
		
		connectedChannelsButton.addActionListener(e -> {
			try {
				String[] sources = Client.this.notificationSink.getArrayOfSources();
				if (sources.length == 0) {
					JOptionPane.showMessageDialog(null, "No open connections.", "Message", JOptionPane.INFORMATION_MESSAGE);
				} else {
					ListOfConnections loc = new ListOfConnections(sources);
				}
			} catch (RemoteException e1) {
				JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		leaveChannelButton.addActionListener(e -> {
			ChannelDialogue cd = new ChannelDialogue(this, "leave");
		});
	}
	
	public void init(String userID) {
		this.setContentPane(this.content);
		this.pack();
		this.setVisible(true);
		System.out.println(userID);
		this.userID = userID;
		this.articleList = new ArrayList<>();
		
		try {
			notificationSink = new NotificationSink(this.userID, this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		joinChannelButton.addActionListener(e -> {
			ChannelDialogue jr = new ChannelDialogue(this, "join");
		});
	}
	
	/**
	 * Connect to a given news channel
	 *
	 * @param channelName channel to connect to
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws NotBoundException
	 */
	public void connectToChannel(String channelName) throws RemoteException, MalformedURLException, NotBoundException {
		notificationSink.subscribe(channelName);
	}
	
	/**
	 * Disconnect from a given channel
	 *
	 * @param channelName channel to disconnect from
	 * @throws RemoteException
	 */
	public void leaveChannel(String channelName) throws RemoteException {
		notificationSink.unsubscribe(channelName);
	}
	
	/**
	 * Exit the client, causes the sink to deregister itself from the source and delete the source from its set of
	 * sources
	 */
	public void exitClient() {
		System.out.println("[CLI] Exiting client");
		try {
			notificationSink.exit();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	/**
	 * Display the article on the JTextArea
	 *
	 * @param article article to display
	 */
	public void displayArticle(Article article) {
		this.articleViewer.setText(article.getTitle() + "\n");
		this.articleViewer.append("By: " + article.getAuthor() + "     Date: " + article.getDate().toString() + "\n");
		this.articleViewer.append(article.getBody());
	}
	
	/**
	 * Receive the notification from the sink
	 *
	 * @param article received article
	 */
	@Override
	public void receiveNotification(Article article) {
		this.articleList.add(article);
		this.dtm.addRow(new Object[] {article.getChannel(), article.getTitle(), article.getAuthor(), article.getDate().toString()});
		System.out.println("[CLI] New Article received!");
		System.out.println(article.getTitle());
	}
	
	private void createUIComponents() {
		this.content = new JPanel();
		//Custom creation of JTable for client article list
		this.articleTable = new JTable();
		this.articleTable.setCellSelectionEnabled(true);
		this.articleTable.getTableHeader().setReorderingAllowed(false);
		this.articleTable.setColumnSelectionAllowed(false);
		
		String header[] = new String[] {"News Channel", "Title", "Author", "Date"};
		
		dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		dtm.setColumnIdentifiers(header);
		this.articleTable.setModel(dtm);
		
		this.articleTable.getSelectionModel().addListSelectionListener(e -> Client.this.displayArticle(Client.this.articleList.get(Client.this.articleTable.getSelectedRow())));
		
		//Custom article viewing panel creation
		this.articleViewer = new JTextArea();
	}
	
}