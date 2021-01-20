package gui.swing.mainframe;

import javax.swing.JOptionPane;

import handler.EventHandlerImpl;
import notification.Notification;
import observer.IListener;
import repository.Document;
import repository.Page;
import repository.Project;
import repository.Slot;
import repository.node.RuNode;

public class EventHandlerView implements IListener {

	@Override
	public void update(Notification notification) {
		switch(notification.getNotificationCode()) {
		case EXIT_MAINFRAME:
			exitMainFrame((EventHandlerImpl)notification.getObject());
			break;
		case NEW_SLOT:
			newSlot();
			break;
		case NEW_PROJECT:
			newProject();
			break;
		case NEW_PAGE:
			newPage();
			break;
		case NEW_DOCUMENT:
			newDocument();
			break;
		case NAME_EXISTS:
			sameName((RuNode)notification.getObject());
			break;
		case EMPTY:
			emptyName();
			break;
		case SPECIAL_CHA:
			specialCharacter();
			break;
		case NAME_SIZE:
			nameSize();
			break;
		case DELETE_ME:
			deleteMess();
			break;
		case RENAME:
			nodeRename();
			break;
		case PAGE_SELECT:
			selectPage();
			break;
		case SLOT_SELECT:
			selectSlot();
			break;
		case SAVE_AS:
			saveAs();
			break;
		case IMPORT_PROJECT:
			importProject();
			break;
		case IMPORT_NAME:
			importProjectName();
			break;
		case SAME_DIR_NAME:
			sameDir();
			break;
		default:
			break;
		}
			
	}
	
	private void sameDir() {
		JOptionPane.showMessageDialog(null, "Directory with that workspace name already exists.");		
	}
	
	private void importProjectName() {
		JOptionPane.showMessageDialog(null, "Project with that name already exists in current workspace.");		
	}
	
	private void importProject() {
		JOptionPane.showMessageDialog(null, "Error while importing.");		
	}
	
	private void saveAs() {
		JOptionPane.showMessageDialog(null, "nznm o cemu se radi promeniti ovaj alert.");		
	}
	
	private void selectPage() {
		JOptionPane.showMessageDialog(null, "You need to select a page in the tree before drawing an element.");		
	}
	
	private void selectSlot() {
		JOptionPane.showMessageDialog(null, "You need to select slots before moving them.");		
	}
	
	private void nodeRename() {
		JOptionPane.showMessageDialog(null, "Morate da oznacite neki node da bi mogli da mu promenite ime.");
	}
	
	private void newProject() {
		JOptionPane.showMessageDialog(null, "Morate da oznacite workspace da bi napravili novi projekat.");
	}
	
	private void deleteMess() {
		JOptionPane.showMessageDialog(null, "Morate da oznacite adekvatan model u stablu u zavisnosti od onoga sta zelite da obrisete.");
	}	
	
	private void nameSize() {
		JOptionPane.showMessageDialog(null, "Ime ne sme da sadrzi vise od 15 karaktera!");		
	}
	
	private void specialCharacter() {
		JOptionPane.showMessageDialog(null, "Korisnici ne mogu da koriste specijalne karaktere kao sto su @,( i )");		
	}
	
	private void exitMainFrame(EventHandlerImpl model) {
		model.setAns(JOptionPane.showConfirmDialog(null, "Close app?", "Close", JOptionPane.YES_NO_OPTION));
	}
	
	private void newSlot() {
		JOptionPane.showMessageDialog(null, "Niste odabrali stranicu.");
	}
	
	private void newPage() {
		JOptionPane.showMessageDialog(null, "Niste odabrali dokument.");
	}
	
	private void newDocument() {
		JOptionPane.showMessageDialog(null, "Niste odabrali projekat.");
	}
	
	private void sameName(RuNode node) {
		String s = "";
		if (node instanceof Project)
			s = "projekat";
		else if (node instanceof Document)
			s = "dokument";
		else if (node instanceof Page)
			s = "stranica";
		else if (node instanceof Slot)
			s = "slot";
		
		JOptionPane.showMessageDialog(null, "Vec postoji " + s + " sa istim imenom.");
	}
	
	private void emptyName() {
		JOptionPane.showMessageDialog(null, "Potrebno je da unesete neko ime.");	
	}

}
