package newsOutlet.notification;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Philip on 11/12/2016.
 */
public interface Sinkable extends Remote {

	boolean subscribe(String channelName) throws RemoteException, MalformedURLException, NotBoundException;
	
	void unsubscribe(String channelName) throws RemoteException;
	
	void updateNews(Notification notification) throws RemoteException;
	
	void exit() throws RemoteException;
	
	String getName() throws RemoteException;
}
