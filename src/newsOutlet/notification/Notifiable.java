package newsOutlet.notification;

import newsOutlet.newsChannel.Article;

/**
 * Created by Philip on 12/12/2016.
 */
public interface Notifiable {
	
	void receiveNotification(Article article);
}
