package gui.swing.controller;

import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.Slot;
import repository.Workspace;

@SuppressWarnings("serial")
public class DeleteAction extends AbstractRudokAction {

	public DeleteAction() {
	    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("DELETE"));
		putValue(SMALL_ICON, loadIcon("images/delete.png"));
		putValue(NAME, "Delete");
		putValue(SHORT_DESCRIPTION, "Delete");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (MainFrame.getInstance().getTree().getSelectedNode() != null && 
				 !(MainFrame.getInstance().getTree().getSelectedNode() instanceof Workspace) && 
				 !(MainFrame.getInstance().getTree().getSelectedNode() instanceof Slot)) {
				 MainFrame.getInstance().getTree().getSelectedNode().deleteMe();
		 } else
			 MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.DELETE_ME, null));	
	}
}
