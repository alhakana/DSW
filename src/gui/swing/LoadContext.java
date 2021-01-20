package gui.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import app.AppCore;
import core.ApplicationFramework;
import core.EventHandlerInterface;
import core.FileUtilHandler;
import core.Gui;
import core.Repository;
import core.SerializationHandlerInterface;
import core.SlotHandler;
import gui.swing.controller.ActionManager;
import gui.swing.filechooser.RFileChooser;
import gui.swing.mainframe.MainFrame;
import handler.EventHandlerImpl;
import handler.SerializationHandlerImpl;
import handler.SlotHandlerImpl;
import notification.Notification;
import notification.NotificationCode;
import repository.RepositoryImpl;
import repository.Workspace;
import utils.FileUtilImpl;

@SuppressWarnings("serial")
public class LoadContext extends JFrame {
	
	private JButton nButton, lButton, bButton;
	
	public LoadContext() {
		nButton = new JButton("New");
		lButton = new JButton("Last");
		bButton = new JButton("Browse");
		
		lookAndFeel();
		postavljanje();
	}
	
	private void lookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	private void postavljanje() {	
		setLayout(new FlowLayout());
		setTitle("Choose workspace");
		setSize(300,75);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		add(nButton);
		add(lButton);
		add(bButton);
		
		
		ApplicationFramework appCore = AppCore.getInstance();
		Repository repository = new RepositoryImpl();
		Gui gui = new SwingGUI(repository);
		EventHandlerInterface eventHandler = new EventHandlerImpl();
		SerializationHandlerInterface serializationHandler = new SerializationHandlerImpl();
		FileUtilHandler fileUtilHandler = new FileUtilImpl();
		SlotHandler slotHandler = new SlotHandlerImpl();
		ActionManager actionManager = new ActionManager();
		appCore.initialise(gui, repository, eventHandler, serializationHandler, fileUtilHandler, slotHandler, actionManager);
		
		actionOnButtons();
		setVisible(true);
	}
	
	private void actionOnButtons() {
		nButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				makeGUI(new Notification(NotificationCode.NEW, null));
				
			}
		});
		
		bButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RFileChooser chooser = new RFileChooser(NotificationCode.IMPORT_WORKSPACE);
				if(chooser.showOpenDialog(MainFrame.getInstance().getWorkspaceView()) == JFileChooser.APPROVE_OPTION) {
					if(!chooser.getSelectedFile().exists()) {
						chooser.cancelSelection();
						MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.IMPORT_PROJECT, null));
					}else {
						Workspace workspace = new Workspace(chooser.getSelectedFile().getName().substring(0, chooser.getSelectedFile().getName().indexOf(".")));
						workspace.setWorkspaceFile(chooser.getSelectedFile());
						dispose();
						makeGUI(new Notification(NotificationCode.BROWSE, workspace));
					}
				}else {
					dispose();
					return;
				}
				
			}
		});
		
		lButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Workspace workspace = AppCore.getInstance().getFileUtilHandler().readLastWs();
				
				if(workspace == null) {
					JOptionPane.showMessageDialog(null, "Niste sacuvali ws koji ste poslednji koristili.");
					return;
				}
				dispose();
				makeGUI(new Notification(NotificationCode.LAST, workspace));
			}
		});
	}
	
	private void makeGUI(Notification notification) {
		AppCore.getInstance().run(notification);
	}

}
