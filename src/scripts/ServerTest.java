package scripts;

import newsOutlet.newsChannel.Article;
import newsOutlet.newsChannel.NewsChannel;
import newsOutlet.notification.Notification;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

/**
 * Created by Philip on 09/12/2016.
 */
public class ServerTest {
	
	public static void main(String[] args) {
		
		try {
			Registry registry = LocateRegistry.createRegistry(1099);
//			HelloTest hello = new HelloTest();
//
//			Naming.rebind("hello", hello);
//
//			HelloTest hello2 = new HelloTest();
//
//			Naming.rebind("hello2", hello2);
			
			NewsChannel bbc = new NewsChannel("BBC");
			
			Notification notification = new Notification(bbc);
			
//			Naming.rebind("bbc", notification);
			
			System.out.println("Server Ready");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
