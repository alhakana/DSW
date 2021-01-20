package gui.swing.state;


import java.awt.Point;
import java.awt.event.MouseEvent;
import command.AddDeviceCommand;
import factory.CircleFactory;
import factory.SlotFactory;
import gui.swing.mainframe.MainFrame;
import gui.swing.node.view.PageView;
import notification.Notification;
import notification.NotificationCode;
import repository.Page;
import repository.node.RuNode;
import repository.slot.CircleSlot;


public class CircleState extends State {
//	private PageView mediator;
	private SlotFactory factory;
	
	public CircleState(PageView mediator) {
//		this.mediator = mediator;
		this.factory = new CircleFactory();
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
		
		CircleSlot cs = (CircleSlot)factory.makeSlot();
		cs.setName("@Circle - " + nodeModel.count++);
		cs.coordinates((int)point.getX(), (int)point.getY());

		page.addCommand(new AddDeviceCommand("circle", cs));
	}
}
