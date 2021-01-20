package gui.swing.tree.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;
import app.AppCore;
import gui.swing.tree.RuTree;
import gui.swing.tree.model.RuTreeItem;
import notification.Notification;
import notification.NotificationCode;
import repository.Document;
import repository.Page;
import repository.Project;
import repository.Slot;
import repository.Workspace;
import repository.node.RuNode;

public class RuTreeImpl implements RuTree {
	private RuTreeView treeView;
	private DefaultTreeModel treeModel;
	
	@Override
	public JTree generateTree(Workspace workspace) {
		RuTreeItem root = new RuTreeItem(workspace);
		treeModel = new DefaultTreeModel(root);
		treeView = new RuTreeView(treeModel);
		return treeView;
	}
	
	@Override
	public RuNode getSelectedNode() {
		if(treeView.getLastSelectedPathComponent() != null)
			return ((RuTreeItem)treeView.getLastSelectedPathComponent()).getNodeModel();
		return null;
	}
	

	
	/**
	 * Vraca RuTreeItem.
	 */
	@Override
	public RuTreeItem getSelectedRuTreeItem() {
		if(treeView.getLastSelectedPathComponent() != null)
			return (RuTreeItem)treeView.getLastSelectedPathComponent();
		return null;
	}
	
	public RuTreeItem getPathRuTreeItem(int path) {
		return (RuTreeItem) treeView.getSelectionPath().getPathComponent(path);
	}
	
	@Override
	public void addProject(Project project) {
		RuNode nodeModel = ((RuTreeItem)treeModel.getRoot()).getNodeModel();
		((RuTreeItem)treeModel.getRoot()).add(new RuTreeItem(project));
		((Workspace)nodeModel).addChildren(project);
		SwingUtilities.updateComponentTreeUI(treeView);
	}

	@Override
	public void addDocument(Document document) {
		RuNode nodeModel = ((RuTreeItem)treeView.getLastSelectedPathComponent()).getNodeModel();
		RuTreeItem item = new RuTreeItem(document);
		((RuTreeItem)treeView.getLastSelectedPathComponent()).add(item);
		((Project)nodeModel).addChildren(document);
		treeView.expandPath(treeView.getSelectionPath());
		SwingUtilities.updateComponentTreeUI(treeView);	
	}
	
	public void addSharedDocument(RuTreeItem document, RuTreeItem project) {
		project.add(document);
		((Project)project.getNodeModel()).addSharedChildren(document.getNodeModel());

		SwingUtilities.updateComponentTreeUI(treeView);	
	}

	@Override
	public void addPage(Page page) {
		RuNode nodeModel = ((RuTreeItem)treeView.getLastSelectedPathComponent()).getNodeModel();
		((RuTreeItem)treeView.getLastSelectedPathComponent()).add(new RuTreeItem(page));
		((Document)nodeModel).addChildren(page);
		treeView.expandPath(treeView.getSelectionPath());
		SwingUtilities.updateComponentTreeUI(treeView);	
	}

	@Override
	public void addSlot(Slot slot) {
		RuNode nodeModel = ((RuTreeItem)treeView.getLastSelectedPathComponent()).getNodeModel();
		((RuTreeItem)treeView.getLastSelectedPathComponent()).add(new RuTreeItem(slot));
		((Page)nodeModel).addChildren(slot);
		treeView.expandPath(treeView.getSelectionPath());
		SwingUtilities.updateComponentTreeUI(treeView);				
	}

	@Override
	public void editingAtPath() {
		treeView.startEditingAtPath(treeView.getSelectionPath());
	}
	
	/**
	 * Vraca ws ali kao rutreeitem, da bi kosristili pravi ws potrebno je da getujemo node model.
	 */
	@Override
	public RuTreeItem getWorkspace() {
		return (RuTreeItem)treeModel.getRoot();
	}
	
	@Override
	public ArrayList<RuTreeItem> shareList() {
		ArrayList<RuTreeItem> list = new ArrayList<RuTreeItem>();
		int num = ((RuTreeItem)treeModel.getRoot()).getChildCount();
		for(int i = 0; i < num ; i++) {
			RuTreeItem item = (RuTreeItem)treeModel.getChild(getWorkspace(), i);
			if (!((Project)item.getNodeModel()).getChildren().contains(getSelectedNode()))
				list.add(item);
			// promeniti ovaj if 
		}
		
		return list;
	}
	
	@Override
	public void importProject(Project project, NotificationCode code) {
		//dodavanje projekta u workspace
		RuNode workspace = ((RuTreeItem)treeModel.getRoot()).getNodeModel();
		List<RuNode> documentList = new ArrayList<RuNode>();
		documentList.addAll(project.getChildren());
		project.getChildren().clear();
		RuTreeItem projectTreeItem = new RuTreeItem(project);
		((RuTreeItem)treeModel.getRoot()).add(projectTreeItem);
		((Workspace)workspace).addChildren(project);
		
		
		//dodavanje dokumenta ako postoji unutar projekta
		if(documentList.size() == 0) {
			SwingUtilities.updateComponentTreeUI(treeView);			
			return;			
		}
		
		
		for(RuNode d : documentList) {
			Document document = (Document) d;
			
			//ako je serovan i ako ne iportujem samo jedan projekat onda necemo upisati serovan
			//ali ako ubacujemo u neki ws samo jedan projekat tj ne idemo na switch workspace 
			//
			if(!document.getParent().equals(project) && code == null) {
				System.out.println(document + " je serovan");
				continue;
			}else if(!document.getParent().equals(project) && code == NotificationCode.IMPORT_PROJECT)
				document.setParent(project);
			
			
			List<RuNode> pageList = new ArrayList<RuNode>();
			pageList.addAll(document.getChildren());
			document.getChildren().clear();
			RuTreeItem documentItem = new RuTreeItem(document);
			projectTreeItem.add(documentItem);
			project.addChildren(document);
			
			importPage(documentItem, document, pageList);
		}		
		
		SwingUtilities.updateComponentTreeUI(treeView);		
	}
	
	private void importPage(RuTreeItem documentItem, Document document, List<RuNode> pagesList) {
		
		if(pagesList.size() == 0)
			return;
		
		for(RuNode p : pagesList) {
			Page page = (Page)p;
			List<RuNode> slotList = new ArrayList<RuNode>();
			slotList.addAll(page.getChildren());
			page.getChildren().clear();
			
			RuTreeItem pageItem = new RuTreeItem(page);
			documentItem.add(pageItem);
			document.addChildren(page);
			importSlot(pageItem, page, slotList);
		}
	}
	
	private void importSlot(RuTreeItem pageItem, Page page, List<RuNode> slotList) {
		
		if(slotList.size() == 0)
			return;
		
		for(RuNode s : slotList) {
			Slot slot = (Slot)s;
			RuTreeItem slotItem = new RuTreeItem(slot);
			pageItem.add(slotItem);
			page.addChildren(slot);
			page.sendMessage(new Notification(NotificationCode.NEW_SLOT, slot));
			
		}
	}
	
	@Override
	public RuTreeItem changeRoot(String name) {
		Workspace noviWorkspace = new Workspace(name);
		RuTreeItem noviRoot = new RuTreeItem(noviWorkspace);
		treeModel.setRoot(noviRoot);
		AppCore.getInstance().getRepository().setWorkspace(noviWorkspace);
		return noviRoot;
	}
	


//	@Override
//	public boolean isOriginal(Page page) {
//		int num = ((RuTreeItem)treeModel.getRoot()).getChildCount();
//		for(int i = 0; i < num ; i++) {
//			RuTreeItem item = (RuTreeItem)treeModel.getChild(getWorkspace(), i);
//			Project p = (Project)item.getNodeModel();
//			if (p.get)
////			if (!((Project)item.getNodeModel()).getChildren().contains(getSelectedNode()))
//		}
//		
//		return false;
////		return page.getParent().getParent().equals(getSelectedNode().);
//		
//	}
}