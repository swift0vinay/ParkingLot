import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Floor {
	private int id;
	private List<Slot> slots;

	public Floor(int id) {
		this.id = id;
		this.slots = new ArrayList<>();
	}

	public Slot findSlotById(int slotId) {
		return this.slots.stream().filter(s -> s.getId() == slotId).findFirst().orElse(null);
	}

	public int getId() {
		return this.id;
	}

	public int getSlotsCount() {
		return this.slots.size();
	}

	public void addSlot(VehicleType type) {
		this.slots.add(new Slot(slots.size(), type));
	}

	public int getSlotsCount(VehicleType type, SlotStatus status) {
		int count = (int) this.slots.stream().filter(s -> s.getType().equals(type) && s.getStatus() == status).count();
		return count;
	}

	public List<Slot> getSlots(VehicleType type, SlotStatus status) {
		return this.slots.stream().filter(s -> s.getType().equals(type) && s.getStatus() == status)
				.collect(Collectors.toList());
	}

	public Slot getFirstFreeSlot(VehicleType type) {
		return this.slots.stream().filter(s -> s.getType().equals(type) && s.getStatus() == SlotStatus.FREE).findFirst()
				.orElse(null);
	}
}
