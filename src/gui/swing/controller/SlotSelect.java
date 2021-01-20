package gui.swing.controller;

import java.awt.event.ActionEvent;

import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.Page;

@SuppressWarnings("serial")
public class SlotSelect extends AbstractRudokAction{

	public SlotSelect() {
		putValue(SMALL_ICON, loadIcon("images/state-select.png"));
		putValue(NAME, "Select Slot");
		putValue(SHORT_DESCRIPTION, "Select Slot");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("SelectSlotAction");
		if (MainFrame.getInstance().getTree().getSelectedNode() instanceof Page) {
			System.out.println("Oznacen page");
			Page page = (Page)MainFrame.getInstance().getTree().getSelectedNode();
			page.sendMessage(new Notification(NotificationCode.SELECT, null));
		} else 
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.PAGE_SELECT, null));

	}
}
