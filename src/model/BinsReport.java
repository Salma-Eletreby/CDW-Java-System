package model;

import java.time.LocalDate;

public class BinsReport extends SummaryReport {
	
	private int totalBinsCollected;
	private int totalBinsDelivered;

	public BinsReport(LocalDate startDate, LocalDate endDate) {
		super(startDate, endDate);
		findSchedules();
		calculateBinsCollected();
		calculateBinsDelivered();
	}

	public int getTotalBinsCollected() {
		return totalBinsCollected;
	}

	public int getTotalBinsDelivered() {
		return totalBinsDelivered;
	}
	
	private void findSchedules() {
	}
	
	private void calculateBinsCollected() {
	}
	
	private void calculateBinsDelivered() {
	}

}
