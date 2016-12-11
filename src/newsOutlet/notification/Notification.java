package newsOutlet.notification;

import newsOutlet.newsChannel.NewsChannel;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Philip on 09/12/2016.
 */
public class Notification implements Serializable {
	
	private NewsChannel newsChannel;
	
	public Notification(NewsChannel newsChannel) {
		this.newsChannel = newsChannel;
	}
	
	public NewsChannel getNewsChannel() {
		return newsChannel;
	}
}
