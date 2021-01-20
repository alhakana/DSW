package gui.swing.tree.model;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import notification.Notification;
import notification.NotificationCode;
import repository.Document;
import repository.node.RuNode;
import repository.node.RuNodeComposite;

@SuppressWarnings("serial")
public class RuTreeItem extends DefaultMutableTreeNode {
	private String name;
	private RuNode nodeModel;
	
	public RuTreeItem(RuNode nodeModel) {
		this.nodeModel = nodeModel;
		this.name = nodeModel.getName();
	}
	
	public RuTreeItem(RuNode ruNode, String name) {
        this.name = name;
        this.nodeModel = ruNode;
    }
	
	@Override
	public int getIndex(TreeNode aChild) {
		return findIndexByChildren((RuTreeItem)aChild);
	}
	
	@Override
	public TreeNode getChildAt(int index) {
		return findChildByIndex(index);
	}
	
	@Override
	public int getChildCount() {
		if(nodeModel instanceof RuNodeComposite)
			return ((RuNodeComposite) nodeModel).getChildren().size();
		return 0;
	}

	@Override
	public boolean getAllowsChildren() {
		if(nodeModel instanceof RuNodeComposite)
			return true;
		return false;
	}
	
	@Override
	public boolean isLeaf() {
		if(nodeModel instanceof RuNodeComposite)
			return false;
		return true;		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public Enumeration children() {
        if(nodeModel instanceof RuNodeComposite)
            return (Enumeration) ((RuNodeComposite) nodeModel).getChildren();
        return null;
    }
	
	private TreeNode findChildByIndex(int childIndex) {
        if (nodeModel instanceof RuNodeComposite) {
            RuTreeItem toLookFor = new RuTreeItem(((RuNodeComposite) nodeModel).getChildren().get(childIndex));

            @SuppressWarnings("rawtypes")
			Iterator childrenIterator = children.iterator();
            TreeNode current;

            while (childrenIterator.hasNext()) {
                current = (TreeNode) childrenIterator.next();
                if (current.equals(toLookFor))
                    return current;
            }
        }

        return null;
    }
	
	private int findIndexByChildren(RuTreeItem node) {
		if(nodeModel instanceof RuNodeComposite)
			return ((RuNodeComposite) this.nodeModel).getChildren().indexOf(node.getNodeModel());
		
		return -1;
	}
	
	//klasicne override metode
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof RuTreeItem) {
			RuTreeItem item= (RuTreeItem)obj;
			return item.nodeModel.equals(this.nodeModel);
		}
		return false;
	}
	
	//geteri i seteri
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name.contains("(shared)")) {
			this.name = name;
			nodeModel.sendMessage(new Notification(NotificationCode.REFRESH_NAME, null));
			return;
		}
		if(nodeModel.countObservers() > 1 && nodeModel instanceof Document) {
			this.name = name + " (shared)";
			this.nodeModel.setName(name);
			return;
		}
		this.name = name;
		this.nodeModel.setName(name);
	
	}

	public RuNode getNodeModel() {
		return nodeModel;
	}
	
	@Override
	public void insert(MutableTreeNode newChild, int childIndex) {
		if (children == null) {
            children = new Vector<>();
        }
		children.insertElementAt(newChild, childIndex);
	}
}