import java.util.Scanner;

/**
 * Created by Philip on 14/12/2016.
 */
public class RunServer {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter News Channel name:");
		String serverName = scan.nextLine();
		
		Server s = new Server(serverName);
	}
}
