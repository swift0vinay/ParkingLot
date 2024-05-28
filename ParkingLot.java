import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {

	private List<Floor> floors;

	private Map<String, Vehicle> ticketToVehicle;

	ParkingLot() {
		this.floors = new ArrayList<Floor>();
		ticketToVehicle = new HashMap<String, Vehicle>();
	}

	public void addFloor() {
		floors.add(new Floor(floors.size()));
		System.out.println("Added floor no. " + floors.size());
	}

	public void addSlotToFloor(int floorNumber, VehicleType type) {
		if (floorNumber - 1 >= floors.size())
			throw new IllegalArgumentException("Invalid Floor number");
		this.floors.get(floorNumber - 1).addSlot(type);
	}

	public void displayFreeSlotsCount(VehicleType type) {
		SlotStatus status = SlotStatus.FREE;
		for (int i = 0; i < floors.size(); i++) {
			System.out.println("Floor " + (i + 1) + " Free Slots for " + type + " is "
					+ floors.get(i).getSlotsCount(type, status));
		}
	}

	public void displayFreeSlots(VehicleType type) {
		SlotStatus status = SlotStatus.FREE;
		for (int i = 0; i < floors.size(); i++) {
			System.out.println("Free slots on Floor " + (i + 1) + " for " + type + " are ");
			System.out.println(floors.get(i).getSlots(type, status));
		}
	}

	public void displayOccupiedSlot(VehicleType type) {
		SlotStatus status = SlotStatus.USED;
		for (int i = 0; i < floors.size(); i++) {
			System.out.println("Free slots on Floor " + (i + 1) + " for " + type + " are ");
			System.out.println(floors.get(i).getSlots(type, status));
		}
	}

	public String park(Vehicle vehicle) throws ParkingSlotFullException {
		Ticket ticket = null;
		for (int i = 0; i < floors.size(); i++) {
			Floor floor = floors.get(i);
			int freeCount = floor.getSlotsCount(vehicle.getType(), SlotStatus.FREE);
			if (freeCount > 0) {
				Slot slot = floor.getFirstFreeSlot(vehicle.getType());
				if (slot != null) {
					slot.setStatus(SlotStatus.USED);
					ticket = new Ticket(floor.getId(), slot.getId());
					ticketToVehicle.put(ticket.getTicket(), vehicle);
					break;
				}
			}
		}
		if (ticket == null) {
			throw new ParkingSlotFullException();
		}
		return ticket.getTicket();
	}

	public void unpark(String ticket) throws InvalidTicketException {
		String ticketAr[] = ticket.split(":");
		if (ticketAr.length != 3) {
			throw new InvalidTicketException();
		}
		int floorId = Integer.parseInt(ticketAr[0]) - 1;
		if (floorId >= floors.size()) {
			throw new InvalidTicketException();
		}
		Floor floor = floors.get(floorId);
		int slotId = Integer.parseInt(ticketAr[1]) - 1;
		if (slotId >= floor.getSlotsCount()) {
			throw new InvalidTicketException();
		}
		Slot slot = floor.findSlotById(slotId);
		if (slot == null) {
			throw new InvalidTicketException();
		}
		slot.setStatus(SlotStatus.FREE);
		ticketToVehicle.remove(ticketAr);
	}

}
