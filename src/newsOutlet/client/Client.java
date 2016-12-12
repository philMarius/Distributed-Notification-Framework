package newsOutlet.client;

import newsOutlet.newsChannel.Article;
import newsOutlet.newsChannel.NewsChannel;
import newsOutlet.notification.Notifiable;
import newsOutlet.notification.NotificationSink;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Objects;

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
	private JTable articleList;
	private JScrollPane articleListSrollPane;
	private NotificationSink notificationSink;
	private DefaultTableModel dtm;
	
	public Client() throws HeadlessException {
		super("Client Chat Window");
		
		Login l = new Login(this);
		
//		addChannelButton.addActionListener(e -> Client.this.addChannel(new NewsChannel("BBC")));
		
//		addArticleToBBCButton.addActionListener(e -> Client.this.addArticle(new Article("Me", "Preview Title", "This is some nice body", new Date()), new NewsChannel("BBC")));
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Client.this.exitClient();
			}
		});
	}
	
	public void init(String userID) {
		this.setContentPane(this.content);
		this.pack();
		this.setVisible(true);
		System.out.println(userID);
		
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
	
//	public void addChannel(NewsChannel newsChannel) {
//		JPanel newTabPanel = new JPanel(new GridLayout(0, 1));
//		newTabPanel.setBackground(Color.red);
//
//		channelTabs.addTab(newsChannel.getName(), newTabPanel);
//	}
	
	public void addArticle(Article article, NewsChannel newsChannel) {
//		int index = this.getTab(newsChannel.getName());
//		if (index == -1) {
//			System.out.println("This tab doesn't exist");
//		} else {
//			System.out.println("Adding article to tab");
//			JPanel tabPanel = (JPanel) this.channelTabs.getComponentAt(this.getTab(newsChannel.getName()));
//			tabPanel.add(new ArticleLink(article, this));
//		}
		
		
	}
	
//	public int getTab(String tabName) {
//		int channelCount = this.channelTabs.getTabCount();
//		for (int i = 0; i < channelCount; i++) {
//			if (Objects.equals(this.channelTabs.getTitleAt(i), tabName)) {
//				return i;
//			}
//		}
//		return -1;
//	}
	
	public void exitClient() {
		System.out.println("[CLI] Exiting client");
		try {
			notificationSink.exit();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	@Override
	public void receiveNotification(Article article) {
		this.dtm.addRow(new Object[] {article.getChannel(), article.getTitle(), article.getAuthor(), article.getDate().toString()});
		System.out.println("[CLI] New Article received!");
		System.out.println(article.getTitle());
	}
	
	private void createUIComponents() {
		//Custom creation of JTable for client article list
		this.articleList = new JTable();
		String header[] = new String[] {"News Channel", "Title", "Author", "Date"};
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(header);
		this.articleList.setModel(dtm);
	}
}