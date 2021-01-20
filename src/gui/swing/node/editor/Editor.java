package gui.swing.node.editor;

import javax.swing.JDialog;

import gui.swing.mainframe.MainFrame;
import gui.swing.node.view.SlotView;

@SuppressWarnings("serial")
public class Editor extends JDialog {
	
	protected SlotView slotView;	//view u kom se nalazi

	public Editor(SlotView slotView) {
		super(MainFrame.getInstance(), "", true);
		
		this.slotView = slotView;
		
		podesavanja();
	}
	
	private void podesavanja() {
		setSize(500,500);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public SlotView getSlotView() {
		return slotView;
	}
}
