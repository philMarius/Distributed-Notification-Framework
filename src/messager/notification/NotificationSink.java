package messager.notification;

import java.util.ArrayList;

/**
 * Created by Philip on 09/12/2016.
 */
public class NotificationSink {
	
	private ArrayList<NotificationSource> sourceList;
	
	public NotificationSink() {
		this.sourceList = new ArrayList<NotificationSource>();
	}
	
	public void receiveNotification() {
		
	}
}
