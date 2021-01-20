package factory;

import repository.Slot;
import repository.slot.RectangleSlot;

public class RectangleFactory extends SlotFactory{

	@Override
	public Slot createSlot() {
		return new RectangleSlot();
	}

}
