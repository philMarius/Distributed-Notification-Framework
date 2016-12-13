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
	
	private NotificationSource source; //NotificationSource that handles Notification sending
	//	private Registry registry;
	private String channelName; //Name of the server news channel
	
	/**
	 * Creates new server with set name, then creates a new NotificationSource which handles the registry
	 *
	 * @param channelName Name of the server news channel
	 */
	public Server(String channelName) {
		this.channelName = channelName;
		
		try {
			source = new NotificationSource(channelName);
			System.out.println("Creating new news channel: " + channelName);
			System.out.println("Server ready");
			while (true) {
				this.addArticle();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds article to the server, queries the server administrator as to what title, author and body the article should
	 * have in turn and then sends it with the current date and the server news channel name.
	 */
	public void addArticle() {
		Scanner s = new Scanner(System.in);
		
		System.out.println("[SVR] Article Title:");
		String title = s.nextLine();
		
		System.out.println("[SVR] Article Author:");
		String author = s.nextLine();
		
		System.out.println("[SVR] Article Body:");
		String body = s.nextLine();
		
		Date now = new Date();
		
		Article newArticle = new Article(this.channelName, author, title, body, now);
		
		try {
			source.broadcastNotification(new Notification(newArticle));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Main class to start the server, queries the server administrator as to what news channel the server should be
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter News Channel name:");
		String serverName = scan.nextLine();
		
		Server s = new Server(serverName);
	}
}

