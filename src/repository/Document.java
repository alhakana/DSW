package repository;

import notification.Notification;
import notification.NotificationCode;
import repository.node.RuNode;
import repository.node.RuNodeComposite;

@SuppressWarnings("serial")
public class Document extends RuNodeComposite {

	public Document(String name, RuNode parent) {
		super(name, parent);
	}

	@Override
	public void addChildren(RuNode child) {
		if (child != null && child instanceof Page) {
			Page p = (Page)child;
			if (!(getChildren().contains(child))) {
				getChildren().add(p);
				sendMessage(new Notification(NotificationCode.NEW_PAGE, p));
			}
		}
	}
	
	@Override
	public void deleteMe() {
		sendMessage(new Notification(NotificationCode.DELETE_ME, null));
	}
	
	@Override
	public void sendMessage(Notification notification) {
		notifyObservers(notification);
	}
	
}