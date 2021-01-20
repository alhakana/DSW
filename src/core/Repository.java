package core;

import repository.Workspace;

/**
 *	Za pristup modelima.
 */
public interface Repository {
	Workspace getWorkspace();
	void setWorkspace(Workspace workspace);
}
