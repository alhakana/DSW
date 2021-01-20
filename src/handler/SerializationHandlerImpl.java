package handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

import core.SerializationHandlerInterface;
import gui.swing.mainframe.MainFrame;
import gui.swing.tree.model.RuTreeItem;
import notification.Notification;
import notification.NotificationCode;
import repository.Document;
import repository.Page;
import repository.Project;
import repository.Slot;
import repository.Workspace;
import repository.node.RuNode;

public class SerializationHandlerImpl implements SerializationHandlerInterface {
	
	@Override
	public String importSlotContext(File file) {
		StringBuilder builder = new StringBuilder();
		try {
			FileReader fl = new FileReader(file);
			BufferedReader bf = new BufferedReader(fl);
		
			String line = "";
			while((line = bf.readLine()) != null) {
				
				builder.append(line);
				builder.append(System.lineSeparator());
			}
			bf.close();
			fl.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return builder.toString();
	}
	
	@Override
	public void saveSlotContext(Slot slot, String contexHTML, File file) {
		if(slot.getFile() != null) {
			if(slot.getFile().exists())						//Brisemo stari jer nam vise nije potreban
				slot.getFile().getAbsoluteFile().delete();
		}
		
		File slotFile = new File(file.getParent() + "\\" + slot.getName() + ".txt");
		slot.setFile(slotFile);
		
		try {
			PrintWriter contextPrint = new PrintWriter(slotFile);
			contextPrint.write(contexHTML);
			contextPrint.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public void importProject(File file, NotificationCode code) {
		//potrebno prvo proveriti da li se u workspace-u nalazi vec projekat sa istim imenom kao
		//projekat koji zelimo da importujemo.
		if(!file.exists()) {
			System.out.println("nije pronadjen fajl " + file +  " pa se nece ucitati");
			return;
		}
		Workspace ws = (Workspace)((RuTreeItem)MainFrame.getInstance().getTree().getWorkspace()).getNodeModel();
		for(RuNode project : ws.getChildren()) {
			if(file.getName().equals(project.getName() + ".rdkp")) {
				MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.IMPORT_NAME, null));
				return;
			}
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			Project p = (Project)ois.readObject();
			MainFrame.getInstance().getTree().importProject(p, code);
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Ako imamo u txt workspace-u sacuvanu putanju do nekog projekta i mi promenimo ime tom projektu i sacuvamo samo
	 * taj projekat. U tom trenutku njemu se menja putanja i potrebno je da osvezimo txt ws fajl i da mu umesto
	 * stare putanje dodamo novi. To se ovde resava
	 * @param ws
	 * @param old
	 * @param neew
	 */
	private void changeWsFile(File ws, File old , File neew) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(ws));
			StringBuffer inputBuffer = new StringBuffer();
			String line;
			
			while((line = bf.readLine()) != null) {
				if(line.equals(old.toString()))
					inputBuffer.append(neew.toString());
				else
					inputBuffer.append(line);
				inputBuffer.append(System.lineSeparator());
			}
			
			bf.close();
			
			String input = inputBuffer.toString();
			
			
			FileOutputStream fileOut = new FileOutputStream(ws);
			fileOut.write(input.getBytes());
			fileOut.close();
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	@Override
	public void importWorkspace(File file) {
		//file u sebi sadrzi putanje do projekata. Potrebno je da ih iscitamo 
		
		try {
			FileReader fl = new FileReader(file);
			BufferedReader bf = new BufferedReader(fl);
			String line = "";
			while((line = bf.readLine()) != null)
				importProject(new File(line), null);
			bf.close();
			fl.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void save(Project project) {
		//moguce je da je korisnik promenio ime projektu u odnosu na proslo ime koje se nalazi u starom file pathu
		if(!project.getProjectFile().getName().equals(project.getName() + ".rdkp")) {
			String parent = project.getProjectFile().getParent();
			File old = project.getProjectFile();
			project.getProjectFile().getAbsoluteFile().delete();
			File file = new File(parent + "\\" + project.getName() + ".rdkp");
			project.setProjectFile(file);
			if(((Workspace)((RuNode)MainFrame.getInstance().getTree().getWorkspace().getNodeModel())).getWorkspaceFile() != null)
				changeWsFile(((Workspace)((RuNode)MainFrame.getInstance().getTree().getWorkspace().getNodeModel())).getWorkspaceFile(), old, file);
		}
		
		for(RuNode d : project.getChildren() ) {
			Document document = (Document)d;
			for(RuNode p : document.getChildren()) {
				Page page = (Page)p;
				for(RuNode s : page.getChildren()) {
					Slot slot = (Slot)s;
					slot.sendMessage(new Notification(NotificationCode.SAVE_CONTEXT, project));
				}
			}
			
		}
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(project.getProjectFile()));
			oos.writeObject(project);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void saveProject(Project project, String pathname) {
		File file = new File(pathname);
		project.setProjectFile(file); 	//setujem putanju na kojoj se nalazi projekat
		for(RuNode d : project.getChildren() ) {
			Document document = (Document)d;
			for(RuNode p : document.getChildren()) {
				Page page = (Page)p;
				for(RuNode s : page.getChildren()) {
					Slot slot = (Slot)s;
					slot.sendMessage(new Notification(NotificationCode.SAVE_CONTEXT, project));
				}
			}
			
		}
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(project);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void saveWorkspace(Workspace workspace, String pathname) {
		//ako nema projekte u sebi nema smisla da cuvamo workspace jer je prazan
		if(workspace.getChildren().size() == 0)	
			return;
		
		//kreiramo folder za projekte koje cemo da sacuvamo
		File dir = new File(pathname);
		if(!dir.exists())
			dir.mkdir();	//sad je dir putanja do foldera koji sam napravio
		else {
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.SAME_DIR_NAME, null));
			return;
		}
		//ako ima projekte moramo njih da sacuvamo i da im zapamtimo putanje
		List<File> paths = new ArrayList<File>();
		for(RuNode p : workspace.getChildren()) {
			Project project = (Project)p;
			File file = new File(pathname + "\\" + project.getName() + ".rdkp");
			paths.add(file);
			project.setProjectFile(file);
			save(project);
		}
	 
		File workspaceFile = new File(pathname + "\\" + workspace.getName() + ".rdkw");
		workspace.setWorkspaceFile(workspaceFile);
		
		try {
			PrintWriter wsPrint = new PrintWriter(workspaceFile);
			for(File f : paths)
				wsPrint.println(f);
			wsPrint.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}