package scripts;

import com.sun.org.apache.regexp.internal.RE;
import newsOutlet.newsChannel.NewsChannel;
import newsOutlet.notification.Notification;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Philip on 09/12/2016.
 */
public interface HelloTestInterface extends Remote {
	
	public String sayHello() throws RemoteException;
	
	public long getTime() throws RemoteException;
}
