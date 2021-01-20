package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.swing.mainframe.MainFrame;
import gui.swing.mainframe.ViewAbout;

@SuppressWarnings("serial")
public class AboutAction extends AbstractRudokAction implements ActionListener {

	public AboutAction() {
		putValue(NAME, "About");
		putValue(SHORT_DESCRIPTION, "About dialog");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		ViewAbout vd = new ViewAbout(MainFrame.getInstance(), "Creators' information", false);
		vd.setVisible(true);
	}
	
}
