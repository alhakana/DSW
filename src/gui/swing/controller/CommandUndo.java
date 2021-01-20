package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import gui.swing.mainframe.MainFrame;
import repository.Page;

@SuppressWarnings("serial")
public class CommandUndo extends AbstractRudokAction {
	
	public CommandUndo() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
				KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_U);
		putValue(SMALL_ICON, loadIcon("images/command-undo.png"));
		putValue(NAME, "Undo");
		putValue(SHORT_DESCRIPTION, "Undo");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(MainFrame.getInstance().getTree().getSelectedNode() != null && 
				MainFrame.getInstance().getTree().getSelectedNode() instanceof Page) {
				Page page = (Page) MainFrame.getInstance().getTree().getSelectedNode();
				page.undo();
		}
	}

}
