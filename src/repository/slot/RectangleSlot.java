package repository.slot;

import notification.ShapeType;
import repository.Slot;
import repository.node.RuNode;

@SuppressWarnings("serial")
public class RectangleSlot extends Slot {

	public RectangleSlot(String name, RuNode parent, int x, int y) {
		super(name, parent);
		this.x = x;
		this.y = y;
		xCopy = x;
		yCopy = y;
		w = 75;
		h = 50;
		wCopy = 75;
		hCopy = 50;
		type = ShapeType.RECTANGLE;
	}
	
	public RectangleSlot() {
		w = 75;
		h = 50;
		type = ShapeType.RECTANGLE;
	}
}
