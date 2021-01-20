package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.io.File;

import gui.swing.filechooser.RFileChooser;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import notification.ShapeType;
import repository.Slot;

@SuppressWarnings("serial")
public class GraphSlotAction extends AbstractRudokAction{
	
	public GraphSlotAction() {
//		putValue(SMALL_ICON, loadIcon("images/delete.png"));
		putValue(NAME, "Graph");
		putValue(SHORT_DESCRIPTION, "Graph");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		RFileChooser chooser;
		File file;
		
		if(MainFrame.getInstance().getTree().getSelectedNode() instanceof Slot) {
			Slot slot = (Slot)MainFrame.getInstance().getTree().getSelectedNode();
			
			
			if(slot.getSadrzaj() != null) {
				System.out.println("ovaj slot vec ima kontekst u sebi");
				return;
			}
			
		
			chooser = new RFileChooser(NotificationCode.IMPORT_IMG);
			
			if(chooser.showOpenDialog(MainFrame.getInstance().getWorkspaceView()) == RFileChooser.APPROVE_OPTION) {
				if(!chooser.getSelectedFile().exists()) {
					chooser.cancelSelection();
					System.out.println("napraviti event za ovo");
				}else {
					file = chooser.getSelectedFile();
					slot.setSadrzaj(ShapeType.GRAPH);
					slot.setFile(file);
					slot.sendMessage(new Notification(NotificationCode.GRAPH, null));
				}
			}
			
		}
	}
	

}
