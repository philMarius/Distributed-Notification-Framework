package newsOutlet.client;

import newsOutlet.newsChannel.Article;
import newsOutlet.newsChannel.NewsChannel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionAdapter;
import java.util.Date;

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
	
	public Client() throws HeadlessException {
		super("Client Chat Window");
		
		Login l = new Login(this);
		
		addChannelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Client.this.addChannel(new NewsChannel("BBC"));
			}
		});
		addArticleToBBCButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Client.this.addArticle(new Article("Me", "Preview Title", "This is some nice body", new Date()), new NewsChannel("BBC"));
			}
		});
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
			if (this.channelTabs.getTitleAt(i) == tabName) {
				return i;
			}
		}
		return -1;
	}
	
	private void createUIComponents() {
		// TODO: place custom component creation code here
	}
	
}
