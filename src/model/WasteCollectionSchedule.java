package model;

import java.util.ArrayList;

public class WasteCollectionSchedule extends WorkSchedule {

	private int collectionRequirements;
	private boolean isHazardous;

	public WasteCollectionSchedule(boolean zone, boolean driverType, boolean isHazardous,
			ArrayList<ScheduleEntry> entries) {
		super(zone, driverType);
		this.isHazardous = isHazardous;
		assignTruck();
		assignEntries(entries);
	}

	public int getCollectionRequirements() {
		return collectionRequirements;
	}

	public boolean isHazardous() {
		return isHazardous;
	}

	protected void assignEntries(ArrayList<ScheduleEntry> entriesLeft) {

		ArrayList<ScheduleEntry> tempEntries = new ArrayList<>();

		for (int i = 0; i < entriesLeft.size(); i++) {
			tempEntries.add(entriesLeft.get(i));
			updateRequirements();
			if (collectionRequirements == MAX_ENTRIES)
				break;
		}
		scheduleEntries = tempEntries;
	}

	// finds and assigns a truck based on: truck availability, which zone the truck
	// services, truck type, and whether the truck is available for scheduling on
	// the schedule's date or not
	protected void assignTruck() {

		for (int i = 0; i < CDW.trucks.size(); i++)
			if (CDW.trucks.get(i).isAvailable() && CDW.trucks.get(i).isNorthTruck() == forNorthZone
					&& !CDW.tempTrucks.contains(CDW.trucks.get(i)))
				if (CDW.trucks.get(i).getBusyOnDates() == null
						|| !CDW.trucks.get(i).getBusyOnDates().contains(scheduleDate))
					if (!isHazardous && CDW.trucks.get(i).getTruckType().equals(TruckType.WasteTruck)) {
						truck = CDW.trucks.get(i);
						break;
					} else if (isHazardous && CDW.trucks.get(i).getTruckType().equals(TruckType.HazardousTruck)) {
						truck = CDW.trucks.get(i);
						break;
					}
	}

	private void updateRequirements() {
		collectionRequirements++;
	}

	// arraylist attribute 'scheduleEntries' is not excluded because it is the core
	// of the schedule
	@Override
	public String toString() {
		return "WasteCollectionSchedule [collectionRequirements=" + collectionRequirements + ", isHazardous="
				+ isHazardous + ", MAX_ENTRIES=" + MAX_ENTRIES + ", scheduleNo=" + scheduleNo + ", scheduleDate="
				+ scheduleDate + ", forNorthZone=" + forNorthZone + ", isComplete=" + isComplete + ", driver=" + driver
				+ ", truck=" + truck + ", scheduleEntries=" + scheduleEntries + "]";
	}

}
