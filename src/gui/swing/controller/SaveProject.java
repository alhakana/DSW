package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import gui.swing.mainframe.MainFrame;
import repository.Project;

@SuppressWarnings("serial")
public class SaveProject extends AbstractRudokAction {
	
	public SaveProject() {
//		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
//		        KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/save-project.png"));
		putValue(NAME, "Save project");
		putValue(SHORT_DESCRIPTION, "Save project");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(MainFrame.getInstance().getTree().getSelectedNode() instanceof Project) {
			Project project = (Project)MainFrame.getInstance().getTree().getSelectedNode();
			File file = project.getProjectFile();
			
			//ako projekat nema putanju do fajla u sebi to znaci da nikad nije bio sacuvam pa zato pozivamo save as
			if(file == null) {
				MainFrame.getInstance().getActionManager().getSaveAsProject().actionPerformed(e);
				return;
			}
			
			MainFrame.getInstance().getSerializationHandler().save(project);
		}
	}

}