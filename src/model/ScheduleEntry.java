package model;

import java.time.LocalDate;

public abstract class ScheduleEntry {

	private int entryNo;
	private LocalDate entryDate;
	private String serviceType;
	private boolean forNorthZone;
	private boolean isCancelled;
	private String notes;
	private boolean serviceComplete;
	private int scheduleNo;

	private Customer customer;
	
	public ScheduleEntry(Customer customer, LocalDate date, String serviceType) {
		assignNo();
		entryDate = date;
		this.serviceType = serviceType;
		this.customer = customer;
		assignZone();
		isCancelled = false;
		serviceComplete = false;
		customer.attachScheduleEntry(this);
	}

	public int getEntryNo() {
		return entryNo;
	}

	public LocalDate getEntryDate() {
		return entryDate;
	}
	
	public void setEntryDate(LocalDate date) {
		entryDate = date;
	}

	public String getServiceType() {
		return serviceType;
	}

	public boolean isForNorthZone() {
		return forNorthZone;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public String getNotes() {
		return notes;
	}

	public boolean isServiceComplete() {
		return serviceComplete;
	}

	public int getScheduleNo() {
		return scheduleNo;
	}

	public Customer getCustomer() {
		return customer;
	}

	// assigning unique id based on total number of created schedule entry objects
	private void assignNo() {
		entryNo = CDW.unassignedWasteEntries.size() + CDW.assignedWasteEntries.size() + 1;
	}

	// schedule entry zone is determined based on the customer's zone
	private void assignZone() {
		forNorthZone = customer.isInNorthZone();
	}

	// attaches the schedule entry with its assigned schedule using the schedule no.
	protected void addToSchedule(int scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public void addNotes(String notes) {
		this.notes = notes;
	}

	public void editNotes(String notes) {
		this.notes = notes;
	}

	public void deleteNotes() {
		notes = "";
	}

	public void markAsComplete() {
		serviceComplete = true;
	}

	public void cancelEntry() {
		isCancelled = true;
	}

	@Override
	public String toString() {
		return "ScheduleEntry [entryNo=" + entryNo + ", entryDate=" + entryDate + ", serviceType=" + serviceType
				+ ", forNorthZone=" + forNorthZone + ", isCancelled=" + isCancelled + ", notes=" + notes
				+ ", serviceComplete=" + serviceComplete + ", scheduleNo=" + scheduleNo + "]";
	}

}
