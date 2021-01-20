package gui.swing.controller;

import java.awt.event.ActionEvent;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.Document;
import repository.Project;
import repository.node.RuNode;

@SuppressWarnings("serial")
public class NewDocumentAction extends AbstractRudokAction {
	
    public NewDocumentAction() {
//        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
//                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/new-document.png"));
        putValue(NAME, "New Document");
        putValue(SHORT_DESCRIPTION, "New Document");
    }

    public void actionPerformed(ActionEvent arg0) {
        if(MainFrame.getInstance().getTree().getSelectedNode() != null && MainFrame.getInstance().getTree().getSelectedNode() instanceof Project) {
        	RuNode rti= MainFrame.getInstance().getTree().getSelectedNode();
        	Document d = new Document("@Document " + rti.count++, rti);
        	MainFrame.getInstance().getTree().addDocument(d);
        } else {
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.NEW_DOCUMENT, null));
		}
    }
}
