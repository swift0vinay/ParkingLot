
public class Slot {
	private SlotStatus status;
	private VehicleType type;
	private int id;

	public Slot(int id, VehicleType type) {
		this.id = id;
		this.type = type;
	}

	public SlotStatus getStatus() {
		return status;
	}

	public void setStatus(SlotStatus status) {
		this.status = status;
	}

	public int getId() {
		return this.id;
	}

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public String toString() {
		return "[ Slot-" + this.id + " ]";
	}
}
