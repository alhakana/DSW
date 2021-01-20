package gui.swing.tree.view;

import java.awt.Component;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import gui.swing.tree.model.RuTreeItem;
import repository.Document;
import repository.Page;
import repository.Project;
import repository.Slot;
import repository.Workspace;

@SuppressWarnings("serial")
public class RuTreeCellRenderer extends DefaultTreeCellRenderer{
	
	public RuTreeCellRenderer() {}
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		
		if (((RuTreeItem)value).getNodeModel() instanceof Workspace) {
			URL imageURL = getClass().getResource("icons/workspace.png");
			Icon icon = null;
			if (imageURL != null) 
				icon = new ImageIcon(imageURL);
			setIcon(icon);
		} else if (((RuTreeItem)value).getNodeModel() instanceof Project){
			URL imageURL = getClass().getResource("icons/small_project.png");
			Icon icon = null;
			if (imageURL != null) 
				icon = new ImageIcon(imageURL);
			setIcon(icon);
		} else if (((RuTreeItem)value).getNodeModel() instanceof Document){
			URL imageURL = getClass().getResource("icons/small_document.png");
			Icon icon = null;
			if (imageURL != null) 
				icon = new ImageIcon(imageURL);
			setIcon(icon);
		} else if (((RuTreeItem)value).getNodeModel() instanceof Page){
			URL imageURL = getClass().getResource("icons/small_page.png");
			Icon icon = null;
			if (imageURL != null) 
				icon = new ImageIcon(imageURL);
			setIcon(icon);
		} else if (((RuTreeItem)value).getNodeModel() instanceof Slot){
			URL imageURL= getClass().getResource("icons/new_slot_small.png");
			Icon icon = null;
			if (imageURL != null) 
				icon = new ImageIcon(imageURL);
			setIcon(icon);
		}
		
		return this;
	}
}
