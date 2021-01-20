package gui.swing.controller;

import java.awt.event.ActionEvent;

import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.Page;

@SuppressWarnings("serial")
public class DrawTriangle extends AbstractRudokAction{
	
	public DrawTriangle() {
		putValue(SMALL_ICON, loadIcon("images/state-triangle.png"));
		putValue(NAME, "Draw Triangle");
		putValue(SHORT_DESCRIPTION, "Draw Triangle");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Akcija za crtanje trougla");
		if(MainFrame.getInstance().getTree().getSelectedNode() instanceof Page) {
			Page page = (Page)MainFrame.getInstance().getTree().getSelectedNode();
			page.sendMessage(new Notification(NotificationCode.TRIANGLE, null));
		}else 
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.PAGE_SELECT, null));

	}
	

}
