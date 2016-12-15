import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Philip on 11/12/2016.
 */
public interface Sourcable extends Remote {
	
	void registerSink(Sinkable sink) throws RemoteException;
	
	void deregisterSink(Sinkable sink) throws RemoteException;
	
	void broadcastNotification(Notification notification) throws RemoteException;
	
	String getName() throws RemoteException;
}
