package newsOutlet.server;

import newsOutlet.newsChannel.Article;
import newsOutlet.newsChannel.NewsChannel;
import newsOutlet.notification.Notification;
import newsOutlet.notification.NotificationSource;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Philip on 09/12/2016.
 */
public class Server {
	
	private NewsChannel newsChannel;
	private NotificationSource source;
	private Registry registry;
	private String name;
	
	public Server(String name) {
		this.newsChannel = new NewsChannel(name);
		this.name = name;
		
		try {
			source = new NotificationSource("bbc");
//			registry = LocateRegistry.createRegistry(1099);
			System.out.println("Server ready");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void addArticle(Article article) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Article Title:");
		String title = s.nextLine();
		
		System.out.println("Article Author:");
		String author = s.nextLine();
		
		System.out.println("Article Body:");
		String body = s.nextLine();
		
		Date now = new Date();
		
		Article newArticle = new Article(author, title, body, now);
		
		newsChannel.addArticle(newArticle);
	}
	
	public static void main(String[] args) {
		String serverName = args[0];
		
		Server s = new Server(serverName);
	}
}

