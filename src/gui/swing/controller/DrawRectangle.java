package gui.swing.controller;

import java.awt.event.ActionEvent;

import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.Page;

@SuppressWarnings("serial")
public class DrawRectangle extends AbstractRudokAction{

	public DrawRectangle() {
		putValue(SMALL_ICON, loadIcon("images/state-rectangle.png"));
		putValue(NAME, "Draw Rectangle");
		putValue(SHORT_DESCRIPTION, "Draw Rectangle");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println("Crta pravougaonik");
		if(MainFrame.getInstance().getTree().getSelectedNode() instanceof Page) {
//			System.out.println("Oznacen page");
			Page page = (Page)MainFrame.getInstance().getTree().getSelectedNode();
			page.sendMessage(new Notification(NotificationCode.RECTANGLE, null));
		}else 
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.PAGE_SELECT, null));

	}

}
