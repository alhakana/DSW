package gui.swing.tree;

import java.util.ArrayList;

import javax.swing.JTree;

import gui.swing.tree.model.RuTreeItem;
import notification.NotificationCode;
import repository.Document;
import repository.Page;
import repository.Project;
import repository.Slot;
import repository.Workspace;
import repository.node.RuNode;

public interface RuTree {
	JTree generateTree(Workspace workspace);
	void addProject(Project project);
	void addDocument(Document document);
	void addSharedDocument(RuTreeItem document, RuTreeItem project);
	void addPage(Page page);
	void addSlot(Slot slot);
	RuNode getSelectedNode();
	void editingAtPath();
	RuTreeItem getWorkspace();
	RuTreeItem getSelectedRuTreeItem();
	
	/**
	 * Vraca selektovanu komponentu u odnosu na nivo u stablu.
	 * @param path nivo komponente koju uzimamo
	 * @return selektovana komponenta na nivou path
	 */
	RuTreeItem getPathRuTreeItem(int path);
	ArrayList<RuTreeItem> shareList();

	/**
	 * Projekat koji se salje je model i potrebno je za njega i 
	 * svako njegovo dete napraviti novi rutreeitem i view.
	 * @param project
	 */
	void importProject(Project project, NotificationCode code);
	
	/**
	 * Menja trenutni workspace i vraca novi.
	 * @param name
	 * @return
	 */
	RuTreeItem changeRoot(String name);
	

	
}
