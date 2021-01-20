package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import gui.swing.mainframe.MainFrame;
import repository.Page;

@SuppressWarnings("serial")
public class CommandRedo extends AbstractRudokAction {
	
	public CommandRedo() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/command-redo.png"));
		putValue(NAME, "Undo");
		putValue(SHORT_DESCRIPTION, "Undo");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(MainFrame.getInstance().getTree().getSelectedNode() != null && 
				MainFrame.getInstance().getTree().getSelectedNode() instanceof Page) {
				Page page = (Page) MainFrame.getInstance().getTree().getSelectedNode();
				page.redo();
		}
	}

}
