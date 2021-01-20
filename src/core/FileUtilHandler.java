package core;

import repository.Workspace;

public interface FileUtilHandler {
	
	void saveContext(String wsPath);
	
	void deleteTxt();
	
	Workspace readLastWs();
}
