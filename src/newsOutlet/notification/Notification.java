package newsOutlet.notification;

import newsOutlet.newsChannel.NewsChannel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Philip on 09/12/2016.
 */
public class Notification extends UnicastRemoteObject implements Notifiable {
	
	private NewsChannel newsChannel;
	
	public Notification(NewsChannel newsChannel) throws RemoteException {
		super();
		this.newsChannel = newsChannel;
	}
	
	@Override
	public NewsChannel updateNews() throws RemoteException {
		return newsChannel;
	}
	
	@Override
	public String testConnection() throws RemoteException {
		return "Test Successful";
	}
}
