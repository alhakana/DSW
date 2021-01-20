package gui.swing.tree.controller;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import gui.swing.tree.model.RuTreeItem;
import notification.Notification;
import notification.NotificationCode;
import repository.Document;
import repository.Page;
import repository.Project;
import repository.Slot;
import repository.node.RuNode;

public class RuTreeSelectionListener implements TreeSelectionListener {

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		check((RuTreeItem)e.getPath().getLastPathComponent());
	}
	
	private void check(RuTreeItem item) {
		RuNode model = item.getNodeModel();
		
		if (model instanceof Project) {
			Project pr = (Project)model;
			pr.sendMessage(new Notification(NotificationCode.SET_FOCUS, null));
		} else if (model instanceof Document) {
			Document doc = (Document)model;
			doc.sendMessage(new Notification(NotificationCode.SET_FOCUS, null));
		} else if (model instanceof Page) {
			Page page = (Page)model;
			page.sendMessage(new Notification(NotificationCode.SET_FOCUS, null));
		} else if (model instanceof Slot) {
			Slot slot = (Slot)model;
			slot.sendMessage(new Notification(NotificationCode.SET_FOCUS, null));
		}
	}

}
