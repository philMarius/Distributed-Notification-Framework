import java.io.Serializable;

/**
 * Created by Philip on 09/12/2016.
 */
public class Notification implements Serializable {
	
	private Object object;
	
	public Notification(Object object) {
		this.object = object;
	}
	
	public Object getObject() {
		return object;
	}
}
