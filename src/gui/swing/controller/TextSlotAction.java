package gui.swing.controller;

import java.awt.event.ActionEvent;

import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import notification.ShapeType;
import repository.Slot;

@SuppressWarnings("serial")
public class TextSlotAction extends AbstractRudokAction{
	
	
	public TextSlotAction() {
//		putValue(SMALL_ICON, loadIcon("images/command-redo.png"));
		putValue(NAME, "Text slot");
		putValue(SHORT_DESCRIPTION, "Text slot");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(MainFrame.getInstance().getTree().getSelectedNode() instanceof Slot) {
			Slot slot = (Slot)MainFrame.getInstance().getTree().getSelectedNode();
			
			if(slot.getSadrzaj() != null) {
				System.out.println("ovaj slot vec ima kontekst u sebi");
				return;
			}
			
			slot.setSadrzaj(ShapeType.TEXT);
			slot.sendMessage(new Notification(NotificationCode.TEXT, null));
		
		}
	}

}
