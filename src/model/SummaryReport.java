package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class SummaryReport {
	
	private int reportNo;
	private LocalDate reportDate;
	private LocalDate startDate;
	private LocalDate endDate;
	private double totalProfit;
	private int customersServiced;
	
	private ArrayList<WorkSchedule> reportSchedules;

	public SummaryReport(LocalDate startDate, LocalDate endDate) {
		assignNo();
		reportDate = LocalDate.now();
		this.startDate = startDate;
		this.endDate = endDate;
		findSchedules();
		calculateProfit();
		calculateCustomersServiced();
	}

	public int getReportNo() {
		return reportNo;
	}

	public LocalDate getReportDate() {
		return reportDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public double getTotalProfit() {
		return totalProfit;
	}

	public int getCustomersServiced() {
		return customersServiced;
	}

	public ArrayList<WorkSchedule> getReportSchedules() {
		return reportSchedules;
	}
	
	private void assignNo() {
	}
	
	private void findSchedules() {
	}
	
	private void calculateProfit() {
	}
	
	private void calculateCustomersServiced() {
	}

}
