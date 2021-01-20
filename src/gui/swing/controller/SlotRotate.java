package gui.swing.controller;

import java.awt.event.ActionEvent;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.Page;

@SuppressWarnings("serial")
public class SlotRotate extends AbstractRudokAction {

	public SlotRotate() {
		putValue(SMALL_ICON, loadIcon("images/state-rotate.png"));
		putValue(NAME, "Rotate Slot");
		putValue(SHORT_DESCRIPTION, "Rotate Slot");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("SelectSlotAction");
		if (MainFrame.getInstance().getTree().getSelectedNode() instanceof Page) {
			System.out.println("Oznacen page");
			Page page = (Page)MainFrame.getInstance().getTree().getSelectedNode();
			page.sendMessage(new Notification(NotificationCode.ROTATE, null));
		} else 
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.PAGE_SELECT, null));

	}
	
}