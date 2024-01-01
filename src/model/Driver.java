package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Driver {

	private int driverID;
	private String driverName;
	private int contactNo;
	private boolean isAvailable;
	private boolean isPermanent;
	private double driverRate;

	private ArrayList<SalaryAdvice> driverSalaries;
	private ArrayList<WorkSchedule> driverWorkload;
	private ArrayList<LocalDate> busyOnDates;

	public Driver(String name, int telephone, boolean isPermanent, double rate) {
		assignID();
		driverName = name;
		contactNo = telephone;
		isAvailable = true;
		this.isPermanent = isPermanent;
		driverRate = rate;
	}

	public int getDriverID() {
		return driverID;
	}

	public String getDriverName() {
		return driverName;
	}

	public int getContactNo() {
		return contactNo;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public boolean isPermanent() {
		return isPermanent;
	}

	public double getDriverRate() {
		return driverRate;
	}

	public ArrayList<SalaryAdvice> getDriverSalaries() {
		return driverSalaries;
	}

	public ArrayList<WorkSchedule> getDriverWorkload() {
		return driverWorkload;
	}

	public ArrayList<LocalDate> getBusyOnDates() {
		return busyOnDates;
	}

	// assigning unique id based on total number of created driver objects
	private void assignID() {
		driverID = CDW.casualDrivers.size() + CDW.permanentDrivers.size() + 1;
	}

	public void makeAvailable() {
		isAvailable = true;
	}

	public void makeUnavailable() {
		isAvailable = false;
	}

	// attaches a schedule object with the driver, and adds the schedule date to the
	// list of dates the driver is unavailable/busy on
	protected void updateWorkLoad(WorkSchedule schedule) {
		if (driverWorkload == null)
			driverWorkload = new ArrayList<>();
		driverWorkload.add(schedule);
		if (busyOnDates == null)
			busyOnDates = new ArrayList<>();
		busyOnDates.add(schedule.getScheduleDate());
	}

	// attaches a salary advice object with the driver
	protected void attachSalary(SalaryAdvice advice) {
		if (driverSalaries == null)
			driverSalaries = new ArrayList<>();
		driverSalaries.add(advice);
	}

	// arraylist attributes are excluded to prevent stack overflow
	@Override
	public String toString() {
		return "Driver [driverID=" + driverID + ", driverName=" + driverName + ", contactNo=" + contactNo
				+ ", isAvailable=" + isAvailable + ", isPermanent=" + isPermanent + ", driverRate=" + driverRate + "]";
	}

}
