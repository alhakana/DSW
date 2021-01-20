package observer;

import notification.Notification;

/**
 *	Menja interface Observer. 
 */
public interface IListener {
	void update(Notification notification);
}
