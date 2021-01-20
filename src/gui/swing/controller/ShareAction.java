package gui.swing.controller;

import java.awt.event.ActionEvent;

import gui.swing.mainframe.DocumentDialog;
import gui.swing.mainframe.MainFrame;
import repository.Document;

@SuppressWarnings("serial")
public class ShareAction extends AbstractRudokAction{
	
	public ShareAction() {
		putValue(SMALL_ICON, loadIcon("images/share.png"));
        putValue(NAME, "Share document");
        putValue(SHORT_DESCRIPTION, "Share document");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(MainFrame.getInstance().getTree().getSelectedNode() instanceof Document)
			new DocumentDialog();
	}

}
