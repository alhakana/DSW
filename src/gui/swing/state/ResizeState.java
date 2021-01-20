package gui.swing.state;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import command.AddDeviceCommand;
import gui.swing.mainframe.MainFrame;
import gui.swing.node.view.PageView;
import gui.swing.node.view.SlotView;
import notification.Notification;
import notification.NotificationCode;
import repository.Slot;

public class ResizeState extends State {
	private PageView mediator;
	private Point start;
	private Point resize;
	private ArrayList<Slot> slots;
	
	public ResizeState(PageView mediator) {
		this.mediator = mediator;
		slots = new ArrayList<Slot>();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		List<SlotView> selected = mediator.getSelectedSlots();

		if (selected.isEmpty()) {
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.SLOT_SELECT, null));
			return;
		}
		
		mediator.colorSelected();
		start = e.getPoint();
		slots.clear();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		resize = e.getPoint();
		
		List<SlotView> selected = mediator.getSelectedSlots();
		
//		if (selected.isEmpty()) {
//			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.SLOT_SELECT, null));
//			return;
//		}
		
		for(SlotView sv : selected)
			slots.add(sv.getSlotModel());
		
		MainFrame.getInstance().getSlotHandler().resize(slots, start, resize);
	}
		
	@Override
	public void mouseReleased(MouseEvent e) {
//		List<SlotView> selected = mediator.getSelectedSlots();
//		
//		if (selected.isEmpty()) {
//			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.SLOT_SELECT, null));
//			return;
//		}
		if (!slots.isEmpty()) {
			mediator.getCommandManager().addCommand(new AddDeviceCommand("resize", slots, start, resize));
			MainFrame.getInstance().getSlotHandler().changeOriginalSize(slots);
		}
		
		slots.clear();
	}

}
