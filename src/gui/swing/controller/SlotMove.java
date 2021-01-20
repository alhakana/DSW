package gui.swing.controller;

import java.awt.event.ActionEvent;

import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.Page;

@SuppressWarnings("serial")
public class SlotMove extends AbstractRudokAction{

	public SlotMove() {
		putValue(SMALL_ICON, loadIcon("images/state-move.png"));
		putValue(NAME, "Move Slot");
		putValue(SHORT_DESCRIPTION, "Move Slot");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("MoveSlotAction");
		if (MainFrame.getInstance().getTree().getSelectedNode() instanceof Page) {
			System.out.println("Oznacen page");
			Page page = (Page)MainFrame.getInstance().getTree().getSelectedNode();
			page.sendMessage(new Notification(NotificationCode.MOVE, null));
		} else 
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.PAGE_SELECT, null));

	}
}
