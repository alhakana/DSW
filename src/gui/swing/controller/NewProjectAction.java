package gui.swing.controller;

import java.awt.event.ActionEvent;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.Project;
import repository.Workspace;
import repository.node.RuNode;

@SuppressWarnings("serial")
public class NewProjectAction extends AbstractRudokAction {

    public NewProjectAction() {
//        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
//                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/new-project.png"));
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "New Project");
    }

    public void actionPerformed(ActionEvent arg0) {
        if(MainFrame.getInstance().getTree().getSelectedNode() != null && MainFrame.getInstance().getTree().getSelectedNode() instanceof Workspace) {
	    	RuNode rn = MainFrame.getInstance().getTree().getSelectedNode();
	        Project p = new Project("@Project " + rn.count++, rn);
	        MainFrame.getInstance().getTree().addProject(p);
        }else
        	MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.NEW_PROJECT, null));
        
    }
}
