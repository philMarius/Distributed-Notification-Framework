package newsOutlet.notification;

import newsOutlet.newsChannel.Article;

import java.io.Serializable;

/**
 * Created by Philip on 09/12/2016.
 */
public class Notification implements Serializable {
	
	private Article article;
	
	public Notification(Article article) {
		this.article = article;
	}
	
	public Article getArticle() {
		return article;
	}
}
