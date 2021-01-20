package gui.swing.mainframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import gui.swing.tree.model.RuTreeItem;

/**
 * Mi ovaj dialog pozivamo samo u slucaju kad je selektovan Dokument.
 */
@SuppressWarnings("serial")
public class DocumentDialog extends JDialog {
	
	@SuppressWarnings("rawtypes")
	private JList projectList;		//smestamo projekte u ovu listu
	private JButton button;					//share button
	private JScrollPane scroll;				//u slucaju veceg broja projekata neophodan nam je ovaj skorl
	private RuTreeItem document;				//dokument koji zelimo da podalimo izmedju vise projekata
	private JPanel panel;
	private JLabel label;
	
	public DocumentDialog() {
		super(MainFrame.getInstance(),	"Share document",true);
		shareGUI();
	}
	
	private void shareGUI() {
		panel = new JPanel(new BorderLayout());
		label = new JLabel("Choose project:");
		button = new JButton("Share");
		
		logic();
		
		label.setMaximumSize(new Dimension(100,40));
		projectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scroll = new JScrollPane(projectList,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		button.addActionListener(getActionListener());
		panel.add(label,BorderLayout.NORTH);
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
		this.add(panel);
		setSize(new Dimension(150,150));
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void logic() {
		ArrayList<RuTreeItem> projekti = MainFrame.getInstance().getTree().shareList();
		document = MainFrame.getInstance().getTree().getSelectedRuTreeItem();

		projectList = new JList(projekti.toArray());
	}
	
	private ActionListener getActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RuTreeItem item = (RuTreeItem)projectList.getSelectedValue();
				if(item != null) {	//ako korisnik nista ne odabere vec samo klikne share bez ovoga ce doci do greske
					MainFrame.getInstance().getTree().addSharedDocument(document, item);
					if(!document.getName().contains("(shared)"))	//ako dokument u sebi ne sadrzi sared
						document.setName(document.getName() + " (shared)");	
				}
				dispose();
			}
		};
	}
	

}
