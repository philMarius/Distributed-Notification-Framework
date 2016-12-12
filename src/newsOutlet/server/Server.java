package newsOutlet.server;

import newsOutlet.newsChannel.Article;
import newsOutlet.notification.Notification;
import newsOutlet.notification.NotificationSource;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Philip on 09/12/2016.
 */
public class Server {
	
//	private NewsChannel newsChannel;
	private NotificationSource source;
	private Registry registry;
	private String channelName;
	
	public Server(String channelName) {
//		this.newsChannel = new NewsChannel(channelName);
		this.channelName = channelName;
		
		try {
			source = new NotificationSource(channelName);
//			registry = LocateRegistry.createRegistry(1099);
			System.out.println("Creating new news channel: " + channelName);
			System.out.println("Server ready");
			while (true) {
				this.addArticle();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void addArticle() {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Article Title:");
		String title = s.nextLine();
		
		System.out.println("Article Author:");
		String author = s.nextLine();
		
		System.out.println("Article Body:");
		String body = s.nextLine();
		
		Date now = new Date();
		
		Article newArticle = new Article(this.channelName, author, title, body, now);
		
		try {
			source.broadcastNotification(new Notification(newArticle));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

//		newsChannel.addArticle(newArticle);
	}
	
	public static void main(String[] args) {
		String serverName = args[0];
		
		Server s = new Server(serverName);
	}
}

