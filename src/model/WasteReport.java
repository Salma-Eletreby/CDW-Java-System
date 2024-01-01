package model;

import java.time.LocalDate;

public class WasteReport extends SummaryReport {

	private double totalWasteCollected;
	private double totalHazardousWaste;
	private double totalExcessWaste;

	public WasteReport(LocalDate startDate, LocalDate endDate) {
		super(startDate, endDate);
		findSchedules();
		calculateWasteCollected();
		calculateHazardousWaste();
		calculateExcessWaste();
	}

	public double getTotalWasteCollected() {
		return totalWasteCollected;
	}

	public double getTotalHazardousWaste() {
		return totalHazardousWaste;
	}

	public double getTotalExcessWaste() {
		return totalExcessWaste;
	}

	private void findSchedules() {
	}

	private void calculateWasteCollected() {
	}

	private void calculateHazardousWaste() {
	}

	private void calculateExcessWaste() {
	}

}
