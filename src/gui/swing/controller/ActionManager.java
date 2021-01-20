package gui.swing.controller;

public class ActionManager {
    private NewProjectAction newProjectAction;
    private NewDocumentAction newDocumentAction;
    private NewPageAction newPageAction;
    
    private DeleteAction deleteAction;
    
    private ShareAction shareAction;

	private RenameAction renameAction;
    
    private AboutAction aboutAction;
    
    // Toolbar vezan za grafiku
    private DrawRectangle drawRectangle;
    private DrawCircle drawCircle;
    private DrawTriangle drawTriangle;
    private SlotSelect slotSelect;
    private SlotMove slotMove;
    private SlotResize slotResize;
    private SlotRotate slotRotate;
    private SlotDelete slotDelete;
    
    // Command
    private CommandUndo commandUndo;
    private CommandRedo commandRedo;
    
    // Open i save
    private SwitchWorkspace openWorkspace;
    private OpenProject openProject;
    private SaveWorkspace saveWorkspace;
    private SaveProject saveProject;
    private SaveAsProject saveAsProject;
    
    //grafika za slot
    private GraphSlotAction graphSlotAction;
    private ChangeImgAction changeImgAction;
    private TextSlotAction textSlotAction;
    
    public ActionManager() {
        initialiseActions();
    }

    private void initialiseActions() {
        newProjectAction = new NewProjectAction();
        newDocumentAction = new NewDocumentAction();
        newPageAction = new NewPageAction();
        
        renameAction = new RenameAction();
        
        aboutAction = new AboutAction();
    
        deleteAction = new DeleteAction();
        
        shareAction = new ShareAction();
        
        // Drugi toolbar
        drawRectangle = new DrawRectangle();
        drawCircle = new DrawCircle();
        drawTriangle = new DrawTriangle();
        slotSelect = new SlotSelect();
        slotMove = new SlotMove();
        slotResize = new SlotResize();
        slotRotate = new SlotRotate();
        slotDelete = new SlotDelete();
        
        // Command
        commandUndo = new CommandUndo();
        commandRedo = new CommandRedo();
        
        // Open i save
        openWorkspace = new SwitchWorkspace();
        openProject = new OpenProject();
        saveWorkspace = new SaveWorkspace();
        saveProject = new SaveProject();
        saveAsProject = new SaveAsProject();
        
        
        graphSlotAction = new GraphSlotAction();
        changeImgAction = new ChangeImgAction();
        textSlotAction = new TextSlotAction();
    }

	public TextSlotAction getTextSlotAction() {
		return textSlotAction;
	}

	public ChangeImgAction getChangeImgAction() {
		return changeImgAction;
	}

	public GraphSlotAction getGraphSlotAction() {
		return graphSlotAction;
	}

	public SaveAsProject getSaveAsProject() {
		return saveAsProject;
	}

	public AbstractRudokAction getDrawRectangle() {
		return drawRectangle;
	}

	public AbstractRudokAction getDrawCircle() {
		return drawCircle;
	}

	public AbstractRudokAction getDrawTriangle() {
		return drawTriangle;
	}

	public AbstractRudokAction getDeleteAction() {
		return deleteAction;
	}

	public AbstractRudokAction getNewProjectAction() {
    	return newProjectAction;
    }
    
    public AbstractRudokAction getShareAction() {
		return shareAction;
	}

	public AbstractRudokAction getNewDocumentAction() {
    	return newDocumentAction;
    }
    
    public AbstractRudokAction getNewPageAction() {
    	return newPageAction;
    }
    
	public AbstractRudokAction getRenameAction() {
		return renameAction;
	}
	
	public AbstractRudokAction getAboutAction() {
		return aboutAction;
	}
	
	public AbstractRudokAction getSlotSelect() {
		return slotSelect;
	}
	
	public AbstractRudokAction getSlotMove() {
		return slotMove;
	}
	
	public AbstractRudokAction getSlotResize() {
		return slotResize;
	}
	
	public AbstractRudokAction getSlotRotate() {
		return slotRotate;
	}
	
	public AbstractRudokAction getSlotDelete() {
		return slotDelete;
	}

	public AbstractRudokAction getCommandUndo() {
		return commandUndo;
	}
	
	public AbstractRudokAction getCommandRedo() {
		return commandRedo;
	}
    
    public AbstractRudokAction getOpenWorkspace() {
		return openWorkspace;
	}

	public AbstractRudokAction getOpenProject() {
		return openProject;
	}

	public AbstractRudokAction getSaveWorkspace() {
		return saveWorkspace;
	}

	public AbstractRudokAction getSaveProject() {
		return saveProject;
	}
	
}
