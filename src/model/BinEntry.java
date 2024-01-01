package model;

import java.time.LocalDate;

public class BinEntry extends ScheduleEntry {
	
	private boolean isBinDelivery;
	private Bin bin;

	public BinEntry(Customer customer, LocalDate date, String serviceType) {
		super(customer, date, serviceType);
		assignBinServiceType();
		assignBin();
	}

	public boolean isBinDelivery() {
		return isBinDelivery;
	}

	public Bin getBin() {
		return bin;
	}
	
	private void assignBinServiceType() {
	}
	
	private void assignBin() {
	}

}
