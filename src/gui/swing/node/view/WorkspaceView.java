package gui.swing.node.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import notification.Notification;
import notification.NotificationCode;
import observer.IListener;
import repository.Project;
import repository.Workspace;
import repository.node.RuNode;

/**
 *Workspace view je ustvari onaj veliki "panel" na desnoj strani MainFrame-a.
 */
@SuppressWarnings("serial")
public class WorkspaceView extends JDesktopPane implements IListener {
	private Workspace modelWorkspace;
	private List<ProjectView> projekti;
	private JLabel labela;
	
	public WorkspaceView(Workspace modelWorkspace) {
		projekti = new ArrayList<ProjectView>();
		this.modelWorkspace = modelWorkspace;
		modelWorkspace.addObserver(this);
		makeLabel();
	}
	
	@Override
	public void update(Notification notification) {
		if (notification.getNotificationCode() != null) {
			if (notification.getNotificationCode().equals(NotificationCode.NEW_PROJECT)) {
				ProjectView pv = new ProjectView((Project) notification.getObject(),this);
				projekti.add(pv);
				add(pv);
				try {
					pv.setSelected(true);
				} catch (PropertyVetoException e) {
					System.out.println("Linija 40 workspaceView");
					e.printStackTrace();
				}
			}else if (notification.getNotificationCode().equals(NotificationCode.LABEL))
				labela.setText(modelWorkspace.getName());
		}
	}
	
	/**
	 * Pomera na pocetak selektovani projekat.
	 * @param pw
	 */
	public void postaviFokus(ProjectView pw) {
		moveToFront(pw);
//		selectedProjectView = pw;
	}
	
	private void makeLabel() {
		labela= new JLabel(modelWorkspace.getName());
		this.add(labela);
		labela.setPreferredSize(new Dimension(300,10));
		labela.setBounds(new Rectangle(new Point(10,10),labela.getPreferredSize()));
		this.setBackground(Color.white);
	}

	public Workspace getModelWorkspace() {
		return modelWorkspace;
	}

	public List<ProjectView> getProjekti() {
		return projekti;
	}
	
	/**
	 * Parametar workspace je novi ws koji je stavljen kao root i ovde ga povezujemo sa view-om.
	 * @param workspace
	 */
	public void resetWorkspaceView(RuNode workspace) {
		
		
		if(projekti.size() != 0) {
			//prvo treba da prodjem kroz projekte i da ih zatvorim.
			for(ProjectView pw : projekti) {
				pw.setVisible(false);
			}
		}
		
		projekti.clear();
		projekti = new ArrayList<ProjectView>();
		
		modelWorkspace = (Workspace)workspace;	//povezujem root sa view-om
		modelWorkspace.addObserver(this);
		labela.setText(modelWorkspace.getName());
	}
	
//	public getSelectedProjectView()
}