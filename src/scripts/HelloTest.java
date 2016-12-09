package scripts;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Philip on 09/12/2016.
 */
public interface HelloTest extends Remote {
	
	String sayHello() throws RemoteException;
}
