package newsOutlet.client;

import newsOutlet.newsChannel.Article;
import newsOutlet.newsChannel.NewsChannel;
import newsOutlet.notification.NotificationSink;

import javax.swing.*;
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
public class Client extends JFrame {
	private JPanel content;
	private JTabbedPane channelTabs;
	private JPanel chatPanel;
	private JPanel logPanel;
	private JSplitPane mainPanel;
	private JButton joinChannelButton;
	private JButton leaveChannelButton;
	private JTextArea articleViewer;
	private JLabel articleName;
	private JPanel channelPanel;
	private JButton addChannelButton;
	private JButton addArticleToBBCButton;
	private JButton connectToBBCTestButton;
	private NotificationSink notificationSink;
	
	public Client() throws HeadlessException {
		super("Client Chat Window");
		
		Login l = new Login(this);
		
		addChannelButton.addActionListener(e -> Client.this.addChannel(new NewsChannel("BBC")));
		
		addArticleToBBCButton.addActionListener(e -> Client.this.addArticle(new Article("Me", "Preview Title", "This is some nice body", new Date()), new NewsChannel("BBC")));
		connectToBBCTestButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Client.this.connectToChannel("bbc");
			}
		});
		
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
			notificationSink = new NotificationSink();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		joinChannelButton.addActionListener(e -> {
			JoinChannel jr = new JoinChannel();
		});
	}
	
	public void connectToChannel(String channelName) {
		try {
			notificationSink.subscribe(channelName);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public void addChannel(NewsChannel newsChannel) {
		JPanel newTabPanel = new JPanel(new GridLayout(0, 1));
		newTabPanel.setBackground(Color.red);
		
		channelTabs.addTab(newsChannel.getName(), newTabPanel);
	}
	
	public void addArticle(Article article, NewsChannel newsChannel) {
		int index = this.getTab(newsChannel.getName());
		if (index == -1) {
			System.out.println("This tab doesn't exist");
		} else {
			System.out.println("Adding article to tab");
			JPanel tabPanel = (JPanel) this.channelTabs.getComponentAt(this.getTab(newsChannel.getName()));
			tabPanel.add(new ArticleLink(article, this));
		}
	}
	
	public int getTab(String tabName) {
		int channelCount = this.channelTabs.getTabCount();
		for (int i = 0; i < channelCount; i++) {
			if (Objects.equals(this.channelTabs.getTitleAt(i), tabName)) {
				return i;
			}
		}
		return -1;
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
	
	private void createUIComponents() {
		// TODO: place custom component creation code here
	}
	
}