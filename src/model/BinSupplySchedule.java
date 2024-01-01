package model;

import java.util.ArrayList;

public class BinSupplySchedule extends WorkSchedule {

	private int binsToCollect;
	private int binsToDeliver;

	public BinSupplySchedule(boolean zone, boolean driverType, ArrayList<ScheduleEntry> entries) {
		super(zone, driverType);
		assignTruck();
		assignEntries(entries);
		updateCollection();
		updateDelivery();
	}

	public int getBinsToCollect() {
		return binsToCollect;
	}

	public int getBinsToDeliver() {
		return binsToDeliver;
	}

	protected void assignEntries(ArrayList<ScheduleEntry> entriesLeft) {
	}

	protected void assignTruck() {
	}

	private void updateCollection() {
	}

	private void updateDelivery() {
	}

}
