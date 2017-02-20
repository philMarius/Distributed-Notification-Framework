package newsOutlet.notification;

import java.io.Serializable;

/**
 * Created by Philip on 09/12/2016.
 */
public class Notification implements Serializable {
	
	private Object object; //Object to send
	
	public Notification(Object object) {
		this.object = object;
	}
	
	/**
	 * Getter of object
	 *
	 * @return the object being sent
	 */
	public Object getObject() {
		return object;
	}
}
