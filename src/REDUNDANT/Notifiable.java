package REDUNDANT;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Philip on 10/12/2016.
 */
public interface Notifiable extends Remote {
	
	public NewsChannel updateNews() throws RemoteException;
	
}
