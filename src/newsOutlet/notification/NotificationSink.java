package newsOutlet.notification;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Created by Philip on 09/12/2016.
 */
public class NotificationSink extends UnicastRemoteObject implements Sinkable {
	
	private Set<Sourcable> sourceSet;
	private String channelToGet;
	private Notifiable notifiable;
	
	public NotificationSink(Notifiable notifiable) throws RemoteException {
		super();
		this.notifiable = notifiable;
		this.sourceSet = new HashSet<>();
		this.channelToGet = "rmi://localhost/";
	}
	
	/**
	 * Updates the queue of channels that the client queries to update the news channels
	 *
	 * @param notification the notification sent by the notification source
	 */
	@Override
	public void updateNews(Notification notification) {
		System.out.println("[SNK] Update received");
		notifiable.receiveNotification(notification.getArticle());
	}
	
	@Override
	public void exit() throws RemoteException {
		System.out.println("[SNK] Unsubscribing from all sources");
		for (Sourcable s : sourceSet) {
			s.deregisterSink(this);
		}
	}
	
	/**
	 * Checks if the channel name already exists in the sourceSet and adds if it doesn't and returns false if it does
	 *
	 * @param channelName name of the source that is to be added
	 * @return boolean if it adds or doesn't
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws NotBoundException
	 */
	@Override
	public boolean subscribe(String channelName) throws RemoteException, MalformedURLException, NotBoundException {
		System.out.println("[SNK] Connecting to: " + channelName);
		
		for (Sourcable s : this.sourceSet) {
			if (s.getName().equals(channelName)) {
				System.out.println("[SNK] Channel exists already");
				return false;
			}
		}
		
		String registryName = this.channelToGet + channelName;
		Sourcable newSource = (Sourcable) Naming.lookup(registryName);
		newSource.registerSink(this);
		sourceSet.add(newSource);
		return true;
	}
	
	/**
	 * Unsubscribes from a given channel by using the name and quering it against all the notification sources stored
	 * in the source set
	 *
	 * @param channelName
	 * @throws RemoteException
	 */
	@Override
	public void unsubscribe(String channelName) throws RemoteException {
		System.out.println("[SNK] Unsubscribing from: " + channelName);
		
		ArrayList<Sourcable> removedSinks = new ArrayList<>();
		for (Sourcable s : sourceSet) {
			if (s.getName().equals(channelName)) {
				removedSinks.add(s);
				s.deregisterSink(this);
			}
		}
		
		for (Sourcable s : removedSinks) {
			sourceSet.remove(s);
		}
	}
}
