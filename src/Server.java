import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Philip on 09/12/2016.
 */
public class Server {
	
	private NotificationSource source; //NotificationSource that handles Notification sending
	//	private Registry registry;
	private String channelName; //Name of the server news channel
	
	/**
	 * Creates new server with set name, then creates a new NotificationSource which handles the registry
	 *
	 * @param channelName Name of the server news channel
	 */
	public Server(String channelName) {
		this.channelName = channelName;
		
		try {
			source = new NotificationSource(channelName);
			System.out.println("Creating new news channel: " + channelName);
			System.out.println("Server ready");
			while (true) {
				this.addArticle();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds article to the server, queries the server administrator as to what title, author and body the article should
	 * have in turn and then sends it with the current date and the server news channel name.
	 */
	public void addArticle() {
		Scanner s = new Scanner(System.in);
		char fn = '/';
		
		System.out.println("[SVR] Article Title:");
		String title = s.nextLine();
		
		if (title.charAt(0) == fn) {
			System.out.println("[SVR] Function");
			this.function(title);
		} else {
			
			System.out.println("[SVR] Article Author:");
			String author = s.nextLine();
			
			System.out.println("[SVR] Article Body:");
			String body = s.nextLine();
			
			Date now = new Date();
			
			Article newArticle = new Article(this.channelName, author, title, body, now);
			
			try {
				source.broadcastNotification(new Notification(newArticle));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Short command line arguments to test the server functionalities
	 *
	 * @param string function string
	 */
	private void function(String string) {
		System.out.println("function");
		switch (string) {
			case "/list-sinks":
				try {
					this.source.listSinks();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				break;
			case "/list-registers":
				try {
					this.source.listRegistry();
				} catch (MalformedURLException | RemoteException e) {
					e.printStackTrace();
				}
				break;
			case "/exit":
				System.out.println("[SRV] Quitting server");
				System.out.println("[SRV] Wiping stored data");
				System.out.println("[SRV] Shutting down source");
				System.exit(0);
				break;
			default:
				System.out.println("[SVR] Unrecognised command");
		}
	}
	
	/**
	 * Main class to start the server, queries the server administrator as to what news channel the server should be
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter News Channel name:");
		String serverName = scan.nextLine();
		
		Server s = new Server(serverName);
	}
}

