package newsOutlet.notification;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Philip on 11/12/2016.
 */
public interface Sinkable extends Remote {
	
	/**
	 * Subscribe to a given source
	 *
	 * @param channelName server to subscribe to
	 * @return
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws NotBoundException
	 */
	boolean subscribe(String channelName) throws RemoteException, MalformedURLException, NotBoundException;
	
	/**
	 * Unsubscribe from a given source
	 *
	 * @param channelName server to unsubscribe from
	 * @throws RemoteException
	 */
	void unsubscribe(String channelName) throws RemoteException;
	
	/**
	 * Updates the article list in the Notifiable attached to the sink
	 *
	 * @param notification notification to add
	 * @throws RemoteException
	 */
	void updateNews(Notification notification) throws RemoteException;
	
	/**
	 * Deregisters from all sources in the set of sources
	 *
	 * @throws RemoteException
	 */
	void exit() throws RemoteException;
	
	/**
	 * Get name of the client attached
	 *
	 * @return name of the user signed in in the client
	 * @throws RemoteException
	 */
	String getName() throws RemoteException;
}
