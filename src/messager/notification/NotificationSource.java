package messager.notification;

import java.util.ArrayList;

/**
 * Created by Philip on 09/12/2016.
 */
public class NotificationSource {
	
	private ArrayList<NotificationSink> sinkList;
	
	public NotificationSource() {
		this.sinkList = new ArrayList<NotificationSink>();
	}
	
	public void sendNotification(Notification notification) {
		
	}
}
