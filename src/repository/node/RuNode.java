package repository.node;

import java.io.Serializable;

import notification.Notification;
import observer.IObserverImpl;

/**
 * Apstrakcija za sve cvorove.
 */
@SuppressWarnings("serial")
public abstract class RuNode extends IObserverImpl implements Serializable {
	private String name;
	private RuNode parent;
	public Integer count = 1;
	
	public RuNode(String name, RuNode parent) {
		this.name = name;
		this.parent = parent;		
	}
	
	public RuNode() {}
	
	/**
	 * Pozivace se kad treba model nesto da obvesti svoj view.
	 */
	public void sendMessage(Notification notification) {}
	
	/**
	 * Svi sem workspac-a treba da overide ovu metodu.
	 */
	public void deleteMe() {}
	
	/**
	 * Dva cvora su ista ako im je isto ime.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof RuNode) {
			RuNode node= (RuNode)obj;
			return getName().equals(node.getName());
		}
		return false;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	//geteri i seteri
	public String getName() {
		return name;
	}

	public RuNode getParent() {
		return parent;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(RuNode parent) {
		this.parent = parent;
	}
}