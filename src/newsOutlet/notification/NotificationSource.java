package newsOutlet.notification;

import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Created by Philip on 09/12/2016.
 */
public class NotificationSource extends UnicastRemoteObject implements Sourcable {
	
	private Set<Sinkable> sinkSet; //Set of sinks
	private String sourceName; //Name of the server
	private Map<Sinkable, Queue<Notification>> sinkNotificationQueue; //Queue of notifications for each sink
	
	/**
	 * Take the source name, i.e. server name
	 *
	 * @param sourceName server name
	 * @throws RemoteException
	 */
	public NotificationSource(String sourceName) throws RemoteException {
		super();
		this.sinkSet = new HashSet<>();
		this.sourceName = sourceName;
		this.sinkNotificationQueue = new HashMap<>();
		
		int i = 0;
		String portName = "rmi://localhost/" + sourceName.toLowerCase();
		
		try {
			int portToTry = 1099 + i;
			System.out.println("Attempting to register on port: " + portToTry);
			Registry registry = LocateRegistry.createRegistry(portToTry);
			Naming.rebind(portName, this);
		} catch (RemoteException | MalformedURLException e) {
			try {
				Naming.rebind(portName, this);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Source ready");
	}
	
	/**
	 * Registers sink to the set of sinks
	 *
	 * @param sink the sink to add
	 * @throws RemoteException
	 */
	@Override
	public void registerSink(Sinkable sink) throws RemoteException {
		System.out.println("[SRC] Registering new sink");
		sinkSet.add(sink);
		System.out.println("[SRC] No. of sinks registered: " + sinkSet.size());
	}
	
	/**
	 * Removes sink from the set of registered sinks
	 *
	 * @param sink sink to remove
	 * @throws RemoteException
	 */
	@Override
	public void deregisterSink(Sinkable sink) throws RemoteException {
		System.out.println("[SRC] Deregistering sink");
		sinkSet.remove(sink);
		System.out.println("[SRC] No. sinks registered: " + sinkSet.size());
	}
	
	/**
	 * Broadcast a notification to all the sinks in the set of registered sinks. Calls the updateNews method in the sink
	 * and catches all sinks that aren't live any more and attempts to add the notification to a given queue of
	 * notifications for each sink (this doesn't work correctly)
	 *
	 * @param notification notification to send
	 * @throws RemoteException
	 */
	@Override
	public void broadcastNotification(Notification notification) throws RemoteException {
		System.out.println("[SRC] Broadcasting notification");
		for (Sinkable s : sinkSet) {
			try {
				s.updateNews(notification);
			} catch (ConnectException e) {
				System.out.println("[SRC] Client: " + s.getName() + " unable to be found, adding notification to queue");
				Queue<Notification> sinkQueue = sinkNotificationQueue.get(s);
				sinkQueue.add(notification);
				sinkNotificationQueue.put(s, sinkQueue);
			}
		}
	}
	
	/**
	 * Gets name of the server this source is attached to
	 *
	 * @return name of the server as String
	 * @throws RemoteException
	 */
	@Override
	public String getName() throws RemoteException {
		return sourceName;
	}
	
	/**
	 * Used in the functions method of the server. Lists the available, registered sinks in the set of sinks
	 *
	 * @throws RemoteException
	 */
	public void listSinks() throws RemoteException {
		System.out.println("[SRC] Sinks registered:");
		for (Sinkable s : sinkSet) {
			System.out.println("[SRC] :: " + s.getName());
		}
	}
	
	/**
	 * Used in the functions method of the server. Lists the currently registered servers on the port
	 *
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public void listRegistry() throws MalformedURLException, RemoteException {
		for (String s : Naming.list("localhost")) {
			System.out.println("[SRC] " + s);
		}
	}
}
