package newsOutlet.client;

import newsOutlet.newsChannel.Article;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Philip on 10/12/2016.
 */
public class ArticleLink extends JPanel {
	private JPanel articleLink;
	private JLabel authorLabel;
	private JLabel authorName;
	private JLabel dateLabel;
	private JLabel date;
	private JLabel articleTitle;
	private Article article;
	private Client client;
	
	public ArticleLink(Article article, Client client) {
		this.article = article;
		this.client = client;
		articleTitle.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	private void createUIComponents() {
		// TODO: place custom component creation code here
		articleTitle = new JLabel(article.getTitle());
		authorName = new JLabel(article.getAuthor());
		date = new JLabel(article.getDate().toString());
	}
}
