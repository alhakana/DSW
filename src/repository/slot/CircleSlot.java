package repository.slot;

import notification.ShapeType;
import repository.Slot;
import repository.node.RuNode;

@SuppressWarnings("serial")
public class CircleSlot extends Slot {
	
	public CircleSlot(String name, RuNode parent, int x, int y) {
		super(name, parent);
		this.x = x;
		this.y = y;
		xCopy = x;
		yCopy = y;
		w = 75;
		h = 75;
		wCopy = 75;
		hCopy = 50;
		type = ShapeType.CIRCLE;
	}
	
	public CircleSlot() {
		w = 75;
		h = 75;
		type = ShapeType.CIRCLE;
	}
	
	
}
