package command;

import java.util.ArrayList;

import gui.swing.mainframe.MainFrame;

public class CommandManager {
	private ArrayList<Command> commands = new ArrayList<Command>();
	private int currentCommand = 0;
	
	public void addCommand(Command command) {
		while(currentCommand < commands.size()) {
			commands.remove(currentCommand);
		}
		commands.add(command);
		doCommand();
	}
	
	public void doCommand() {
		if(currentCommand < commands.size()) {
//			System.out.println("CommandManager doCommand1");
			commands.get(currentCommand++).doCommand();
			MainFrame.getInstance().getActionManager().getCommandUndo().setEnabled(true);
		}
		if(currentCommand == commands.size()) {
//			System.out.println("CommandManager doCommand2");
			MainFrame.getInstance().getActionManager().getCommandRedo().setEnabled(false);
		}
	}
	
	public void undoCommand() {
		if(currentCommand > 0) {			
//			System.out.println("CommandManager undoCommand1");
			MainFrame.getInstance().getActionManager().getCommandRedo().setEnabled(true);
			commands.get(--currentCommand).undoCommand();
		}
		if(currentCommand == 0) {
//			System.out.println("CommandManager undoCommand2");
			MainFrame.getInstance().getActionManager().getCommandUndo().setEnabled(false);
		}
	}
	
	public ArrayList<Command> getCommands() {
		return commands;
	}
	
	public void setCommands(ArrayList<Command> commands) {
		this.commands.addAll(commands);
	}

	public int getCurrentCommand() {
		return currentCommand;
	}

	public void setCurrentCommand(int currentCommand) {
		this.currentCommand = currentCommand;
	}
	
}
