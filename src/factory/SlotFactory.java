package factory;

import repository.Slot;

public abstract class SlotFactory {
	
	public Slot makeSlot() {
		Slot slot;
		slot = createSlot();
		return slot;
	}
	
	public abstract Slot createSlot();
	
}
