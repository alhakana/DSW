package gui.swing.mainframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import app.AppCore;
import core.EventHandlerInterface;
import core.FileUtilHandler;
import core.Repository;
import core.SerializationHandlerInterface;
import core.SlotHandler;
import gui.swing.controller.ActionManager;
import gui.swing.node.view.WorkspaceView;
import gui.swing.tree.RuTree;
import gui.swing.tree.view.RuTreeImpl;
import handler.EventHandlerImpl;
import notification.Notification;
import notification.NotificationCode;
import repository.Workspace;

/**
 *	Glavni prozor aplikacije. Singleton klasa.
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private static MainFrame instance = null;
	
	private Repository documentRep;
	private SlotHandler slotHandler;
	private EventHandlerInterface eventHandler;
	private SerializationHandlerInterface serializationHandler;
	private FileUtilHandler fileUtilHandler;
	private ActionManager actionManager;

	private RuTree tree;
	private JTree workspaceTree;
	private WorkspaceView workspaceView;
	private JToolBar toolBar;
	private JMenuBar menuBar;
	private PaintToolbar paintToolbar;
	
	private MainFrame() {}
	
	public static MainFrame getInstance() {
		if (instance == null) 
			instance = new MainFrame();
		return instance;
	}
	
	public void initialiseGUI(Notification notification) {
		Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth, screenHeight-40);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("images/files.png")).getImage());
        setTitle("RuDok");
        
    	actionManager = AppCore.getInstance().getActionManager();

    	menuBar = new MenuBar();
		setJMenuBar(menuBar);
		
    	toolBar = new ToolBar();
		add(toolBar, BorderLayout.NORTH);

        paintToolbar = new PaintToolbar();
        add(paintToolbar, BorderLayout.EAST);
        
        lookAndFeel();
        addWindowAdapter();
        
        slotHandler = AppCore.getInstance().getSlotHandler();
        eventHandler = AppCore.getInstance().getEventHandler();
        serializationHandler =	AppCore.getInstance().getSerializationHandler();
        fileUtilHandler = AppCore.getInstance().getFileUtilHandler();

        ((EventHandlerImpl)eventHandler).addObserver(new EventHandlerView()); 
        
        initialiseWorkspaceTree(notification);
	}
	
	private void initialiseWorkspaceTree(Notification notification) {
		tree = new RuTreeImpl();
		if(notification.getNotificationCode() == NotificationCode.NEW) {
			workspaceTree = tree.generateTree(documentRep.getWorkspace());
			workspaceView = new WorkspaceView(getDocumentRep().getWorkspace());
		}else if(notification.getNotificationCode() == NotificationCode.BROWSE || notification.getNotificationCode() == NotificationCode.LAST) {
			Workspace root = (Workspace)notification.getObject();
			AppCore.getInstance().getRepository().setWorkspace(root);
			workspaceTree = tree.generateTree(root);
			workspaceView = new WorkspaceView(root);
			serializationHandler.importWorkspace(root.getWorkspaceFile());
		}
	
		treeSett();
	}
	
	private void treeSett() {
        JScrollPane scroll = new JScrollPane(workspaceTree);
        scroll.setMinimumSize(new Dimension(200, 150));
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, workspaceView);
        getContentPane().add(split, BorderLayout.CENTER);
        split.setDividerLocation(150);
        split.setOneTouchExpandable(true);
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
	
	private void addWindowAdapter() {
		addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		MainFrame frame = (MainFrame)e.getComponent();
        		int provera = eventHandler.generateMessage(new Notification(NotificationCode.EXIT_MAINFRAME, eventHandler));
        		
        		if (provera != JOptionPane.YES_OPTION)
        			frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        		else {
        			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        			if(documentRep.getWorkspace().getWorkspaceFile() != null)
        				fileUtilHandler.saveContext(documentRep.getWorkspace().getWorkspaceFile().toString());
        			else
        				fileUtilHandler.deleteTxt();
        		}
        	}
        });
	}
	
	public SerializationHandlerInterface getSerializationHandler() {
		return serializationHandler;
	}

	public ActionManager getActionManager() {
		return actionManager;
	}
	
	public void setDocumentRep(Repository documentRep) {
		this.documentRep = documentRep;
	}

	public RuTree getTree() {
		return tree;
	}

	public JTree getWorkspaceTree() {
		return workspaceTree;
	}	
	
	public WorkspaceView getWorkspaceView() {
		return workspaceView;
	}
	
	public EventHandlerInterface getEventHandler() {
		return eventHandler;
	}

	public Repository getDocumentRep() {
		return documentRep;
	}
	
	public SlotHandler getSlotHandler() {
		return slotHandler;
	}
}