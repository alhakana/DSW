package core;

import java.io.File;

import notification.NotificationCode;
import repository.Project;
import repository.Slot;
import repository.Workspace;

public interface SerializationHandlerInterface {
	/**
	 * Cuva project na lokaciji pathname.
	 * @param project
	 * @param pathname 
	 */
	void saveProject(Project project, String path);
	
	/**
	 * File je ustvari projekat kad ga procitamo.
	 * @param file
	 */
	void importProject(File file, NotificationCode code);
	
	
	void save(Project project);
	
	/**
	 * Workspace ne cuvamo kao objekat vec kao rdkw fajl koji u sebi ima putanje do njegovih projekata.
	 * Neophodno je da sacuvamo sve projekte.
	 * @param workspace
	 * @param pathname
	 */
	void saveWorkspace(Workspace workspace, String pathname);
	
	void importWorkspace(File file);
	
	void saveSlotContext(Slot slot, String contexHTML, File file);
	
	String importSlotContext(File file);

}
