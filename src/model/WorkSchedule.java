package model;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class WorkSchedule {

	protected final int MAX_ENTRIES = 10;

	protected int scheduleNo;
	protected LocalDate scheduleDate;
	protected boolean forNorthZone;
	protected boolean isComplete;
	protected boolean driverType;

	protected Driver driver;
	protected Truck truck;
	protected ArrayList<ScheduleEntry> scheduleEntries;

	public WorkSchedule(boolean zone, boolean driverType) {
		assignNo();
		scheduleDate = LocalDate.now();
		forNorthZone = zone;
		isComplete = false;
		this.driverType = driverType;
		assignDriver();
	}

	public int getScheduleNo() {
		return scheduleNo;
	}

	public LocalDate getScheduleDate() {
		return scheduleDate;
	}

	public boolean isForNorthZone() {
		return forNorthZone;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public Driver getDriver() {
		return driver;
	}

	public Truck getTruck() {
		return truck;
	}

	public ArrayList<ScheduleEntry> getScheduleEntries() {
		return scheduleEntries;
	}

	// assigning unique id based on total number of created schedule objects
	private void assignNo() {
		scheduleNo = CDW.wasteSchedules.size() + CDW.tempWasteSchedules.size() + 1;
	}

	protected abstract void assignEntries(ArrayList<ScheduleEntry> entriesLeft);

	// finds and assigns a driver based on required driver type (contract)
	private void assignDriver() {
		if (driverType) {
			assignDriver(CDW.permanentDrivers);
			if (driver == null)
				assignDriver(CDW.casualDrivers);
		} else
			assignDriver(CDW.casualDrivers);

	}

	// helper method that finds and assigns a driver based on: driver availability,
	// driver type, and whether the driver is available for scheduling on the
	// schedule's date or not
	private void assignDriver(ArrayList<Driver> drivers) {
		for (int i = 0; i < drivers.size(); i++)
			if (drivers.get(i).isAvailable() && !CDW.tempDrivers.contains(drivers.get(i)))
				if (drivers.get(i).getBusyOnDates() == null
						|| !drivers.get(i).getBusyOnDates().contains(scheduleDate)) {
					driver = drivers.get(i);
					break;
				}
	}

	protected abstract void assignTruck();

	public void markAsComplete() {
		isComplete = true;
	}

	// arraylist attribute 'scheduleEntries' is not excluded because it is the core
	// of the schedule
	@Override
	public String toString() {
		return "WorkSchedule [MAX_ENTRIES=" + MAX_ENTRIES + ", scheduleNo=" + scheduleNo + ", scheduleDate="
				+ scheduleDate + ", forNorthZone=" + forNorthZone + ", isComplete=" + isComplete + ", driver=" + driver
				+ ", truck=" + truck + ", scheduleEntries=" + scheduleEntries + "]";
	}

}
