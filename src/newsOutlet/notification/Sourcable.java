package newsOutlet.notification;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Philip on 11/12/2016.
 */
public interface Sourcable extends Remote {
	
	/**
	 * Register a sink in the set of sinks
	 *
	 * @param sink sink to register
	 * @throws RemoteException
	 */
	void registerSink(Sinkable sink) throws RemoteException;
	
	/**
	 * Deregister a sink from the set of sinks
	 *
	 * @param sink sink to deregister
	 * @throws RemoteException
	 */
	void deregisterSink(Sinkable sink) throws RemoteException;
	
	/**
	 * Broadcast notification to all sinks in the set of sinks
	 *
	 * @param notification notification to broadcast
	 * @throws RemoteException
	 */
	void broadcastNotification(Notification notification) throws RemoteException;
	
	/**
	 * Get name of server
	 *
	 * @return the server name
	 * @throws RemoteException
	 */
	String getName() throws RemoteException;
}
