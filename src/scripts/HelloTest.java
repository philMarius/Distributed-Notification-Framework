package scripts;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Philip on 10/12/2016.
 */
public class HelloTest extends UnicastRemoteObject implements HelloTestInterface {
	
	protected HelloTest() throws RemoteException {
		super();
	}
	
	@Override
	public String sayHello() throws RemoteException {
		String whoami = "unknown";
		try {
			whoami = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "Hello World from " + whoami;
	}
	
	@Override
	public long getTime() throws RemoteException {
		long time = new Date().getTime();
		return time;
	}
}
