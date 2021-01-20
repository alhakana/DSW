package gui.swing.controller;

import java.awt.event.ActionEvent;

import gui.swing.filechooser.RFileChooser;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.Project;

@SuppressWarnings("serial")
public class SaveAsProject extends AbstractRudokAction{

	public SaveAsProject() {
//		 putValue(SMALL_ICON, loadIcon("images/project.png"));
	     putValue(NAME, "SaveAs Project");
	     putValue(SHORT_DESCRIPTION, "SaveAs Project");
	
	}
	
	 	@Override
	public void actionPerformed(ActionEvent e) {
		if(MainFrame.getInstance().getTree().getSelectedNode() instanceof Project) {
			Project project = (Project)MainFrame.getInstance().getTree().getSelectedNode();
			String pathname;
			RFileChooser chooser = new RFileChooser(NotificationCode.SAVE_AS);
			if(chooser.showSaveDialog(MainFrame.getInstance().getWorkspaceView()) == RFileChooser.APPROVE_OPTION) {
				if(!chooser.getSelectedFile().exists()) {
					chooser.cancelSelection();
					MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.SAVE_AS, null));
				}else {
					pathname = chooser.getSelectedFile().getAbsolutePath() + "\\" + project.getName() + ".rdkp";
					MainFrame.getInstance().getSerializationHandler().saveProject(project, pathname);
				}
			}
		}		
	}	     
}


