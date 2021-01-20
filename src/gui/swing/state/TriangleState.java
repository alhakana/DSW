package gui.swing.state;

import java.awt.Point;
import java.awt.event.MouseEvent;

import command.AddDeviceCommand;
import factory.SlotFactory;
import factory.TriangleFactory;
import gui.swing.mainframe.MainFrame;
import gui.swing.node.view.PageView;
import notification.Notification;
import notification.NotificationCode;
import repository.Page;
import repository.node.RuNode;
import repository.slot.TriangleSlot;

public class TriangleState extends State{
//	private PageView mediator;
	private SlotFactory factory;
	
	public TriangleState(PageView mediator) {
//		this.mediator = mediator;
		factory = new TriangleFactory();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Point point = e.getPoint();
		RuNode nodeModel = MainFrame.getInstance().getTree().getSelectedNode();
		
		if (!(nodeModel instanceof Page)) {
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.PAGE_SELECT, null));
			return;
		}
		Page page = (Page)nodeModel;
		
		TriangleSlot ts = (TriangleSlot)factory.makeSlot();
		ts.setName("@Triangle - " + nodeModel.count++);
		ts.setParent(nodeModel);
		ts.coordinates((int)point.getX(), (int)point.getY());
		
		page.addCommand(new AddDeviceCommand("circle", ts));
	}
}	