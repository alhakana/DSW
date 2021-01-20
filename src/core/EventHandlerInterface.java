package core;

import notification.Notification;

/**
 * Interface za hendlovanje poruka.
 */
public interface EventHandlerInterface {
	int generateMessage(Notification notification);
}
