package gui.swing.controller;

import java.awt.event.ActionEvent;

import gui.swing.filechooser.RFileChooser;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.Workspace;
import repository.node.RuNode;

@SuppressWarnings("serial")
public class SaveWorkspace extends AbstractRudokAction {
	
	public SaveWorkspace() {
//		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
//		        KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/save-workspace.png"));
		putValue(NAME, "Save workspace");
		putValue(SHORT_DESCRIPTION, "Save workspace");
	}
	
	//Ideja koja stoji iza ovoga je da se kreira direktorijum koji ce da se zove kao workspace u njemu ce da se sacuvaju svi
	//projekti koji se trenutno nalaze u tom workspacu i napravice se .rdkw fajl sa imenom workspace-a i u njemu
	//ce da se nalaze putanje do svi projekata koji su sacuvani
	@Override
	public void actionPerformed(ActionEvent e) {
		Workspace workspace = (Workspace)((RuNode)MainFrame.getInstance().getTree().getWorkspace().getNodeModel());
		RFileChooser chooser = new RFileChooser(NotificationCode.SAVE_WORKSPACE);
		String pathname;
		
		if(chooser.showSaveDialog(MainFrame.getInstance().getWorkspaceView()) == RFileChooser.APPROVE_OPTION) {
			if(!chooser.getSelectedFile().exists()) {
				chooser.cancelSelection();
				MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.SAVE_AS, null));
			}else {
				pathname = chooser.getSelectedFile().getAbsolutePath() + "\\" + workspace.getName();
				MainFrame.getInstance().getSerializationHandler().saveWorkspace(workspace, pathname);
			}
		}
	
	}
}