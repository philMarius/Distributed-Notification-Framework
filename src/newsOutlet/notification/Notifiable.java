package newsOutlet.notification;

import newsOutlet.newsChannel.Article;

/**
 * Created by Philip on 12/12/2016.
 */
public interface Notifiable {
	
	/**
	 * receive notification from the invoker of this method
	 * @param article to receive
	 */
	void receiveNotification(Article article);
}
