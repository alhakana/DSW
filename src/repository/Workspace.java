package repository;

import java.io.File;

import notification.Notification;
import notification.NotificationCode;
import repository.node.RuNode;
import repository.node.RuNodeComposite;

/**
 * Model za root stabla.
 */
@SuppressWarnings("serial")
public class Workspace extends RuNodeComposite {
	
	private File workspaceFile;		// putanja za workspace
	
	public Workspace(String name) {
		super(name, null);
	}

	@Override
	public void addChildren(RuNode child) {
		if (child != null && child instanceof Project) {
			Project project = (Project)child;
			if (!getChildren().contains(project)) {
				getChildren().add(project);
				sendMessage(new Notification(NotificationCode.NEW_PROJECT, project));
			}
		}
	}
	
	@Override
	public void sendMessage(Notification notification) {
		notifyObservers(notification);
	}

	public File getWorkspaceFile() {
		return workspaceFile;
	}

	public void setWorkspaceFile(File workspaceFile) {
		this.workspaceFile = workspaceFile;
	}
}