package scripts;

import REDUNDANT.Notifiable;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Philip on 09/12/2016.
 */
public class ClientTest {
	
	public static void main(String[] args) {
		
		String host = (args.length < 1) ? null : args[0];
		
		try {
//			Registry registry = LocateRegistry.getRegistry(1099);
//			HelloTestInterface response = (HelloTestInterface) Naming.lookup("rmi://localhost/hello");
//
//			System.out.println("Response received");
//			System.out.println(response.sayHello());
//			System.out.println(response.getTime());
//
//			HelloTestInterface response2 = (HelloTestInterface) Naming.lookup("rmi://localhost/hello2");
//
//			System.out.println("Response2");
//			System.out.println(response2.sayHello());
//			System.out.println(response2.getTime());
//
//			Scanner s = new Scanner(System.in);
			
			Notifiable not = (Notifiable) Naming.lookup("rmi://localhost/bbc");
			
			System.out.println("Response received");
//			System.out.println(not.testConnection());
			
		} catch (RemoteException | NotBoundException | MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
