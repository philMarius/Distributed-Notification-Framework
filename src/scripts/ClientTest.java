package scripts;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Philip on 09/12/2016.
 */
public class ClientTest {
	
	public static void main(String[] args) {
		
		String host = (args.length < 1) ? null : args[0];
		
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			HelloTest stub = (HelloTest) registry.lookup("Hello");
			
			String response = stub.sayHello();
			System.out.println(response);
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}
