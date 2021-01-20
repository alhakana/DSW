package repository;

import java.io.File;

import notification.Notification;
import notification.NotificationCode;
import repository.node.RuNode;
import repository.node.RuNodeComposite;

@SuppressWarnings("serial")
public class Project extends RuNodeComposite {
	
	private File projectFile;
	public Project(String name, RuNode parent) {
		super(name, parent);
//		sendMessage(new Notification(NotificationCode.NEW_PROJECT, null));
	}

	@Override
	public void addChildren(RuNode child) {
		if (child != null && child instanceof Document) {
			Document doc = (Document)child;
			if (!(getChildren().contains(doc))) {
				getChildren().add(doc);
				sendMessage(new Notification(NotificationCode.NEW_DOCUMENT, doc));
			}
				
		}
	}
	
	public void addSharedChildren(RuNode child) {
		Document doc = (Document)child;
		getChildren().add(doc);
		sendMessage(new Notification(NotificationCode.NEW_SHARED_DOCUMENT, doc));
	}
	
	@Override
	public void deleteMe() {
		sendMessage(new Notification(NotificationCode.DELETE_ME, null));
	}
	
	@Override
	public void sendMessage(Notification notification) {
		notifyObservers(notification);
	}

	public File getProjectFile() {
		return projectFile;
	}

	public void setProjectFile(File projectFile) {
		this.projectFile = projectFile;
	}
}