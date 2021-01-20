package gui.swing.state;

import java.awt.Point;
import java.awt.event.MouseEvent;
import command.AddDeviceCommand;
import gui.swing.mainframe.MainFrame;
import gui.swing.node.view.PageView;
import gui.swing.node.view.SlotView;
import repository.Page;
import repository.Slot;

public class DeleteState extends State{
	private PageView mediator;
	private int element = -1;
	
	public DeleteState(PageView mediator) {
		this.mediator = mediator;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		mediator.getPageModel().resetColor();
		
		Point pos = e.getPoint();
		if (e.getButton() == MouseEvent.BUTTON1) {
			element = mediator.getElementAtPosition(pos);
			
			if (element != -1) {
				SlotView slotView = mediator.getSlots().get(element);
				Page page = mediator.getPageModel();
				Slot s = slotView.getSlotModel();
				
				page.addCommand(new AddDeviceCommand("delete", s));
			}
		}
		
		MainFrame.getInstance().repaint();
	}
	
}
