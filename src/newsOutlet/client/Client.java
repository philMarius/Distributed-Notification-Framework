package newsOutlet.client;

import newsOutlet.newsChannel.Article;
import newsOutlet.notification.Notifiable;
import newsOutlet.notification.NotificationSink;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
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
	private NotificationSink notificationSink;
	private DefaultTableModel dtm;
	private ArrayList<Article> articleList;
	
	public Client() throws HeadlessException {
		super("Client Chat Window");
		
		Login l = new Login(this);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Client.this.exitClient();
			}
		});
		connectedChannelsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
	public void init(String userID) {
		this.setContentPane(this.content);
		this.pack();
		this.setVisible(true);
		System.out.println(userID);
		this.articleList = new ArrayList<>();
		
		try {
			notificationSink = new NotificationSink(this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		joinChannelButton.addActionListener(e -> {
			JoinChannel jr = new JoinChannel(this);
		});
	}
	
	public void connectToChannel(String channelName) throws RemoteException, MalformedURLException, NotBoundException {
		notificationSink.subscribe(channelName);
	}

	
	public void exitClient() {
		System.out.println("[CLI] Exiting client");
		try {
			notificationSink.exit();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public void displayArticle(Article article) {
		
	}
	
	@Override
	public void receiveNotification(Article article) {
		this.articleList.add(article);
		this.dtm.addRow(new Object[] {article.getChannel(), article.getTitle(), article.getAuthor(), article.getDate().toString()});
		System.out.println("[CLI] New Article received!");
		System.out.println(article.getTitle());
	}
	
	private void createUIComponents() {
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
		
		this.articleTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println(Client.this.articleList.get(Client.this.articleTable.getSelectedRow()));
			}
		});
		
	}
}