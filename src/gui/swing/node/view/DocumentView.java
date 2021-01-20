package gui.swing.node.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import observer.IListener;
import repository.Document;
import repository.Page;

@SuppressWarnings("serial")
public class DocumentView extends JPanel implements IListener {
	private Document documentModel;
	private ProjectView containerParent;
	private List<PageView> pages;
	
	public DocumentView(Document documentModel, ProjectView containerParent) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(Box.createHorizontalStrut(10));
		
		pages = new ArrayList<PageView>();
		this.containerParent = containerParent;
		this.documentModel = documentModel;
		documentModel.addObserver(this);
	}
	
	@Override
	public void update(Notification notification) {
		if (notification.getNotificationCode() != null) {
			switch(notification.getNotificationCode()) {
			case NEW_PAGE:
				PageView pv = new PageView((Page) notification.getObject(), this);
				addPage(pv);
				break;
			case DELETE_ME:
				deleteMe();
				break;
			case RENAME: 
				chageName();
				break;
			case SET_FOCUS:
				focus();
				break;
			case REFRESH_NAME:
				SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());	
				break;
			default:
				break;
			}
		}
	}

	void addSharedPages(DocumentView dv) {
		for(PageView pv : dv.getPages()) {
			PageView pageView = new PageView(pv.getPageModel(), this);
			addPage(pageView);
			pageView.setManager(pv.getManager()); 	//kad napravim novi view za page moram da mu setujem state managera da bude isti kao prvom viewu
//			pageView.setCommandManager(pv.getCommandManager());
//			pageView.getCommandManager().setCommands(pv.getCommandManager().getCommands());
//			pageView.getCommandManager().setCurrentCommand(pv.getCommandManager().getCurrentCommand());
			pageView.setCommandManager(null);
			if(pv.getSlots().size() != 0) {
				for(SlotView sv : pv.getSlots()) {
					if (pv.getSelectedSlots().contains(sv))
						pageView.addSlot(sv.getSlotModel(), true);
					else pageView.addSlot(sv.getSlotModel(), false);
				}
			}
		}
	}
	
	private void addPage(PageView pv) {
		pages.add(pv);
		add(pv);
		add(Box.createHorizontalStrut(10));
		putMeInFocus();
		repaint();
		revalidate();
	}
	
	public void deleteMe() {
		pages = null;
		
//		MainFrame.getInstance().getTree().getPathRuTreeItem(1).remove(MainFrame.getInstance().getTree().getPathRuTreeItem(1).getIndex(MainFrame.getInstance().getTree().getSelectedRuTreeItem()));
		
		containerParent.deleteDocTreeItem(MainFrame.getInstance().getTree().getSelectedRuTreeItem());
		
		containerParent.getDocuments().remove(this);
		containerParent.getProjectModel().getChildren().remove(documentModel);
		containerParent.getJtp().remove(this);
		
		MainFrame.getInstance().getWorkspaceTree().setSelectionRow(0);
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());	
	}

	
	private void chageName() {
		System.out.println(documentModel.getListeners());
		if(containerParent.getDocuments() != null) {
			containerParent.getJtp().setTitleAt(containerParent.getDocuments().indexOf(this), documentModel.getName());
			notifyPages();
		}
	}

	
	private void focus() {
		if (MainFrame.getInstance().getTree().getPathRuTreeItem(1).getNodeModel().equals(containerParent.getProjectModel()))
			putMeInFocus();
	}
	
	public void putMeInFocus() {
		containerParent.putTabbInFocus(this);
		containerParent.putProjectInFocus();
	}
	
	/**
	 *  Javlja slotu da promeni njegovo ime u labeli.
	 */
	public void notifyPages() {
		for(PageView pv : pages)
			pv.labelNameChanged();		
	}
	
	public Document getDocumentModel() {
		return documentModel;
	}

	public List<PageView> getPages() {
		return pages;
	}

	public ProjectView getContainerParent() {
		return containerParent;
	}
}