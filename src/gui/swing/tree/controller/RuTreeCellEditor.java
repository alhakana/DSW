package gui.swing.tree.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;

import gui.swing.mainframe.MainFrame;
import gui.swing.tree.model.RuTreeItem;
import listener.NameListener;
import notification.Notification;
import notification.NotificationCode;
import repository.Document;
import repository.Page;
import repository.Project;
import repository.Slot;
import repository.Workspace;
import repository.node.RuNode;
import repository.node.RuNodeComposite;


public class RuTreeCellEditor extends DefaultTreeCellEditor implements ActionListener{
	private Object clickedOn;
	private JTextField textField;
	
	public RuTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
		super(arg0, arg1);
	}
	
	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
		clickedOn = value;
		textField = new JTextField(value.toString());
		textField.addActionListener(this);
		textField.addKeyListener(new NameListener());
        return textField;
	}
	
	@Override
	public boolean isCellEditable(EventObject event) {
		if (event instanceof MouseEvent) {
			if (((MouseEvent)event).getClickCount() == 3)
				return true;
			else
				return false;
		}
		return true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!(clickedOn instanceof RuTreeItem))
			return;
		
		RuTreeItem item = (RuTreeItem)clickedOn;
		String name = e.getActionCommand().trim();
		//ako je uneo isto ime kao sto je bilo
		if (name.equals(item.getName())) {
			tree.stopEditing();
			return;
		}
		
		if (item.getNodeModel() instanceof Project) {
			if (validProjectName(name)) {
				MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.NAME_EXISTS, (Project)item.getNodeModel()));
				return;
			}
		} else if (item.getNodeModel() instanceof Document) {
			if(item.getName().contains("(shared)"))
				if(validSharedDocumetn((RuNode)item.getNodeModel(),name)) {
					MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.NAME_EXISTS, (Document)item.getNodeModel()));
					return;	
				}
			
			if (validDocumentName(name,(RuNodeComposite)item.getNodeModel().getParent())) {
				MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.NAME_EXISTS, (Document)item.getNodeModel()));
				return;	
			}
		} else if (item.getNodeModel() instanceof Page) {
			if (validPageName(name,(RuNodeComposite)item.getNodeModel().getParent())) {
				MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.NAME_EXISTS, (Page)item.getNodeModel()));
				return;	
			}
		} else if (item.getNodeModel() instanceof Slot) {
			if (validSlotName(name,(RuNodeComposite)item.getNodeModel().getParent())) {
				MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.NAME_EXISTS, (Slot)item.getNodeModel()));
				return;	
			}
		}
		
		if (name == null || name.trim().equals("")) {
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.EMPTY, null));
			return;
		}
		
		if(name.contains("@") || name.contains("(") || name.contains(")")) {
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.SPECIAL_CHA, null));
			return;
		}
		
			
		//ako je doslo do ovde znaci da je ime validno
		if (item.getNodeModel() instanceof Workspace) {
			item.setName(e.getActionCommand().trim());
			item.getNodeModel().sendMessage(new Notification(NotificationCode.LABEL, null));
		}
		if (item.getNodeModel() instanceof Project) {
			item.setName(e.getActionCommand().trim());
			item.getNodeModel().sendMessage(new Notification(NotificationCode.RENAME, null));		
		}
		if (item.getNodeModel() instanceof Document) {
			item.setName(e.getActionCommand().trim());
			item.getNodeModel().sendMessage(new Notification(NotificationCode.RENAME, null));
		}
		if (item.getNodeModel() instanceof Page) {
			item.setName(e.getActionCommand().trim());
			item.getNodeModel().sendMessage(new Notification(NotificationCode.LABEL, null));
		}
		if (item.getNodeModel() instanceof Slot)
			item.setName(e.getActionCommand().trim());
			
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
		tree.stopEditing();
	}
	
	private boolean validProjectName(String name) {
		Workspace ws = MainFrame.getInstance().getDocumentRep().getWorkspace();
		RuNode node = ws.getChildrenByName(name);
		if (node != null)
			return true;
		return false;
	}
	
	private boolean validDocumentName(String name, RuNodeComposite parent) {
		RuNode node = parent.getChildrenByName(name);
		if (node != null)
			return true;
		return false;
	}
	
	private boolean validSharedDocumetn(RuNode document,String name) {
		RuTreeItem ws = MainFrame.getInstance().getTree().getWorkspace();
		boolean shared= false, sameName = false;
		for(int i= 0; i < ws.getChildCount() ;i++) {
			RuTreeItem project = (RuTreeItem)ws.getChildAt(i);
			for(int j= 0; j < project.getChildCount() ;j++) {
				RuTreeItem doc = (RuTreeItem)project.getChildAt(j);
				if(doc.getName().contains("(shared)") && ((Document)doc.getNodeModel()).getParent().equals(document.getParent()))
					shared = true;
				
				if(name.equals(doc.getNodeModel().getName())) {
					sameName = true;
				}
			}	
			if(shared && sameName)
				return true;
			shared = false;
			sameName = false;
		}
		return false;
	}
	
	private boolean validPageName(String name, RuNodeComposite parent) {
		RuNode node = parent.getChildrenByName(name);
		if (node != null)
			return true;
		return false;
	}
	
	private boolean validSlotName(String name, RuNodeComposite parent) {
		RuNode node= parent.getChildrenByName(name);
		if (node != null)
			return true;
		return false;
	}

}
