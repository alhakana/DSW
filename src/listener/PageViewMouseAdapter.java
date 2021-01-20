package listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import gui.swing.mainframe.MainFrame;
import gui.swing.node.view.PageView;
import gui.swing.state.State;
import notification.Notification;
import notification.NotificationCode;
import repository.Page;
import repository.node.RuNode;

public class PageViewMouseAdapter extends MouseAdapter {
	private PageView pv;
	
	public PageViewMouseAdapter(PageView pv) {
		this.pv = pv;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		RuNode node = MainFrame.getInstance().getTree().getSelectedNode();
		State state = pv.getManager().getCurrectState();
		Page model = pv.getPageModel();

		if (!(node instanceof Page) || state == null) {
				MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.PAGE_SELECT, null));
		} else if (node.equals(model)) {
			Page p = (Page) MainFrame.getInstance().getTree().getSelectedNode();
			if (p.getParent().getParent().equals(model.getParent().getParent()))
				state.mousePressed(e);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		RuNode node = MainFrame.getInstance().getTree().getSelectedNode();
		State state = pv.getManager().getCurrectState();
		Page model = pv.getPageModel();

		if (!(node instanceof Page) || state == null) 
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.PAGE_SELECT, null));
		else if (node.equals(model)) 
			state.mouseReleased(e);
	}
}
