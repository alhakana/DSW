package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.node.RuNode;

@SuppressWarnings("serial")
public class RenameAction extends AbstractRudokAction {

	public RenameAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/rename.png"));
        putValue(NAME, "Rename");
        putValue(SHORT_DESCRIPTION, "Rename");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(MainFrame.getInstance().getTree().getSelectedNode() instanceof RuNode)
				MainFrame.getInstance().getTree().editingAtPath();
		else
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.RENAME, null));
	}

}
