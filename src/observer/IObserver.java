package observer;

import notification.Notification;

/**
 * Sadrzi metode potrebne za Observable klasu.
 */
public interface IObserver {
	void addObserver(IListener listener);
//	void removeObserver(IListener listener);	po potrebi dodati
	void notifyObservers(Notification notification);
	int countObservers();
}
	