package gui.swing;

import core.Gui;
import core.Repository;
import gui.swing.mainframe.MainFrame;
import notification.Notification;

public class SwingGUI implements Gui {
	
	private MainFrame instance;
	private Repository documentRep;
	
	public SwingGUI(Repository documentRep) {
		this.documentRep = documentRep;
	}
	
	@Override
	public void start(Notification notification) {
		instance = MainFrame.getInstance();
		instance.setDocumentRep(documentRep);
		instance.initialiseGUI(notification);
		instance.setVisible(true);
	}

}
