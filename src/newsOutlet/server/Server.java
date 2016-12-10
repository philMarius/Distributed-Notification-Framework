package newsOutlet.server;

import newsOutlet.newsChannel.NewsChannel;
import newsOutlet.notification.NotificationSource;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Philip on 09/12/2016.
 */
public class Server {
	
	private NewsChannel newsChannel;
	private NotificationSource source;
	private Registry registry;
	
	public Server(NewsChannel newsChannel) {
		this.newsChannel = newsChannel;
		source = new NotificationSource();
		
		try {
			registry = LocateRegistry.createRegistry(1099);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void addArticle() {
		
	}
}
