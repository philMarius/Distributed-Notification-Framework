package scripts;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Philip on 09/12/2016.
 */
public class ServerTest implements HelloTest {
	
	@Override
	public String sayHello() throws RemoteException {
		return "Hello World!";
	}
	
	public static void main(String[] args) {
		
		try {
			ServerTest server = new ServerTest();
			HelloTest stub = (HelloTest) UnicastRemoteObject.exportObject(server, 0);
			
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("Hello", stub);
			
			System.err.println("Server ready");
			
		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
