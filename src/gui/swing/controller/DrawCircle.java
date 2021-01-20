package gui.swing.controller;

import java.awt.event.ActionEvent;

import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.Page;

@SuppressWarnings("serial")
public class DrawCircle extends AbstractRudokAction{

	public DrawCircle() {
		putValue(SMALL_ICON, loadIcon("images/state-circle.png"));
		putValue(NAME, "Draw Circle");
		putValue(SHORT_DESCRIPTION, "Draw Circle");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Crta krug");
		if(MainFrame.getInstance().getTree().getSelectedNode() instanceof Page) {
			Page page = (Page)MainFrame.getInstance().getTree().getSelectedNode();
			page.sendMessage(new Notification(NotificationCode.CIRCLE, null));
		}else 
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.PAGE_SELECT, null));

	}
}
