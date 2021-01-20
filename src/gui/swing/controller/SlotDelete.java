package gui.swing.controller;

import java.awt.event.ActionEvent;

import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.Page;

@SuppressWarnings("serial")
public class SlotDelete extends AbstractRudokAction{

	public SlotDelete() {
		putValue(SMALL_ICON, loadIcon("images/state-delete.png"));
		putValue(NAME, "Delete Slot");
		putValue(SHORT_DESCRIPTION, "Delete Slot");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println("DeleteSlotAction");
		if (MainFrame.getInstance().getTree().getSelectedNode() instanceof Page) {
//			System.out.println("Oznacen page");
			Page page = (Page)MainFrame.getInstance().getTree().getSelectedNode();
			page.sendMessage(new Notification(NotificationCode.DELETE, null));
		} else 
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.PAGE_SELECT, null));

	}
}
