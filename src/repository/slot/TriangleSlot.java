package repository.slot;

import notification.ShapeType;
import repository.Slot;
import repository.node.RuNode;

@SuppressWarnings("serial")
public class TriangleSlot extends Slot {

	public TriangleSlot(String name, RuNode parent, int x, int y) {
		super(name, parent);
		this.x = x;
		this.y = y;
		xCopy = x;
		yCopy = y;
		w = 75;
		h = 75;
		wCopy = 75;
		hCopy = 50;
		type = ShapeType.TRIANGLE;
	}
	
	public TriangleSlot() {
		w = 75;
		h = 75;
		type = ShapeType.TRIANGLE;
	}
}
