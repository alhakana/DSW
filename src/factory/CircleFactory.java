package factory;

import repository.Slot;
import repository.slot.CircleSlot;

public class CircleFactory extends SlotFactory{

	@Override
	public Slot createSlot() {
		return new CircleSlot();
	}

}
