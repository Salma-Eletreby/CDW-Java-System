package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Invoice {

	private int invoiceNo;
	private LocalDate invoiceDate;
	private boolean isMonthly;
	private double totalAmount;

	private Customer customer;
	private ArrayList<ScheduleEntry> invoiceEntries;
	private ArrayList<Invoice> extraBills;

	public Invoice(Customer customer, boolean isMonthly) {
		assignNo();
		invoiceDate = LocalDate.now();
		this.isMonthly = isMonthly;
		this.customer = customer;
		findEntries();
		calculateTotal();
	}

	public int getInvoiceNo() {
		return invoiceNo;
	}

	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}

	public boolean isMonthly() {
		return isMonthly;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public ArrayList<ScheduleEntry> getInvoiceEntries() {
		return invoiceEntries;
	}

	public ArrayList<Invoice> getExtraBills() {
		return extraBills;
	}

	private void assignNo() {
	}

	private void findEntries() {
	}

	private double calculateExcessWasteBills() {
		return 0;
	}

	private double calculateBinRentalFee() {
		return 0;
	}

	private double calculateOnRequestFee() {
		return 0;
	}

	private double calculateSubscriptionFee() {
		return 0;
	}

	private void calculateTotal() {
		totalAmount = calculateExcessWasteBills() + calculateBinRentalFee() + calculateOnRequestFee()
				+ calculateSubscriptionFee();
	}

}
