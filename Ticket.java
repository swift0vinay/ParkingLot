
public class Ticket {

	private String parkingSlotId = "PR12345";
	private int slotId;
	private int floorId;

	public Ticket(int floorId, int slotId) {
		this.floorId = floorId;
		this.slotId = slotId;
	}

	public String getTicket() {
		return parkingSlotId + ":" + floorId + ":" + slotId;
	}

}
