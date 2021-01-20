package repository.node;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public abstract class RuNodeComposite extends RuNode {
	private List<RuNode> childrens;
	
	public RuNodeComposite(String name, RuNode parent) {
		super(name, parent);
		childrens = new ArrayList<RuNode>();
	}
	
	/**
	 * RuNodeComposite class.
	 */
	public abstract void addChildren(RuNode child);
	
	/** 
	 * @param name
	 * @return RuNode sa istim imenom ili null ako ne postoji node sa tim imenom
	 */
	public RuNode getChildrenByName(String name) {
		for(RuNode node: getChildren()) {
			if (node.getName().equals(name))
				return node;
		}
		return null;
	}

	public List<RuNode> getChildren() {
		return childrens;
	}
}