package newsOutlet.notification;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Philip on 09/12/2016.
 */
public class NotificationSource extends UnicastRemoteObject implements Sourcable {
	
	private Set<Sinkable> sinkSet;
	private String sourceName;
	
	public NotificationSource(String sourceName) throws RemoteException {
		super();
		this.sinkSet = new HashSet<>();
		this.sourceName = sourceName;
		
		int i = 0;
		String portName = "rmi://localhost/" + sourceName.toLowerCase();
		
		while (true) {
			try {
				int portToTry = 1099 + i;
				System.out.println("Attempting to register on port: " + portToTry);
				Registry registry = LocateRegistry.createRegistry(portToTry);
				Naming.rebind(portName, this);
				System.out.println("Source ready");
				break;
			} catch (RemoteException | MalformedURLException e) {
				System.out.println(e.getMessage());
				i++;
			}
		}
	}
	
	@Override
	public void registerSink(Sinkable sink) throws RemoteException {
		System.out.println("[SRC] Registering new sink");
		sinkSet.add(sink);
		System.out.println("[SRC] No. of sinks registered: " + sinkSet.size());
	}
	
	@Override
	public void deregisterSink(Sinkable sink) throws RemoteException {
		System.out.println("[SRC] Deregistering sink");
		sinkSet.remove(sink);
		System.out.println("[SRC] No. sinks registered: " + sinkSet.size());
	}
	
	@Override
	public void broadcastNotification(Notification notification) throws RemoteException {
		System.out.println("[SRC] Broadcasting notification");
		for (Sinkable s : sinkSet) {
			s.updateNews(notification);
		}
	}
	
	@Override
	public String getName() throws RemoteException {
		return sourceName;
	}
}
