package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Truck {

	private String licenseNo;
	private TruckType truckType;
	private boolean isNorthTruck;
	private boolean isAvailable;

	private ArrayList<WorkSchedule> assignedSchedules;
	private ArrayList<LocalDate> busyOnDates;

	public Truck(String licenseNo, TruckType type, boolean isNorthTruck) {
		this.licenseNo = licenseNo;
		truckType = type;
		this.isNorthTruck = isNorthTruck;
		isAvailable = true;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public TruckType getTruckType() {
		return truckType;
	}

	public boolean isNorthTruck() {
		return isNorthTruck;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public ArrayList<WorkSchedule> getAssignedSchedules() {
		return assignedSchedules;
	}

	public ArrayList<LocalDate> getBusyOnDates() {
		return busyOnDates;
	}

	public void makeAvailable() {
		isAvailable = true;
	}

	public void makeUnavailable() {
		isAvailable = false;
	}

	// attaches a schedule object with the truck, and adds the schedule date to the
	// list of dates the truck is unavailable/busy on
	protected void assignToSchedule(WorkSchedule schedule) {
		if (assignedSchedules == null)
			assignedSchedules = new ArrayList<>();
		assignedSchedules.add(schedule);
		if (busyOnDates == null)
			busyOnDates = new ArrayList<>();
		busyOnDates.add(schedule.getScheduleDate());
	}

	// arraylist attributes are excluded to prevent stack overflow
	@Override
	public String toString() {
		return "Truck [licenseNo=" + licenseNo + ", truckType=" + truckType + ", isNorthTruck=" + isNorthTruck
				+ ", isAvailable=" + isAvailable + "]";
	}

}
