package model;

import java.time.LocalDate;

public class WasteEntry extends ScheduleEntry {

	protected final double MAX_WASTE = 25;

	private double totalWaste;
	private double excessWaste;
	
	public WasteEntry(Customer customer, LocalDate date, String serviceType) {
		super(customer, date, serviceType);
	}

	public double getTotalWaste() {
		return totalWaste;
	}

	public double getExcessWaste() {
		return excessWaste;
	}

	// allows driver to mark the total amount of waste collected, and automatically
	// calculates how much of it is in excess
	public void markWasteCollected(double totalWaste) {
		this.totalWaste = totalWaste;
		calculateExcessWaste();
	}

	// calculates the amount of excess waste based on the 'MAX_WASTE' constant
	private void calculateExcessWaste() {
		if (totalWaste > MAX_WASTE)
			excessWaste = totalWaste - MAX_WASTE;
	}

	@Override
	public String toString() {
		return "WasteEntry [MAX_WASTE=" + MAX_WASTE + ", totalWaste=" + totalWaste + ", excessWaste=" + excessWaste
				+ ", " + super.toString() + "]";
	}

}
