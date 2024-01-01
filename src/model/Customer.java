package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Customer {

	private int custID;
	private String custName;
	private int[] custAddress = new int[4];
	private int contactNo;
	private boolean isSpecial;
	private boolean isActive;
	private boolean inNorthZone;
	private boolean binDelivered;
	private boolean binCollected;

	private Bin bin;
	private ArrayList<ScheduleEntry> custEntries;
	private ArrayList<Invoice> custInvoices;

	// special and regular customers are differentiated based on their bin type
	public Customer(String name, int[] address, int telephone, Bin bin) {
		assignID();
		custName = name;
		custAddress = address;
		contactNo = telephone;
		if (bin.getBinType().equals(BinType.Hazardous))
			isSpecial = true;
		else
			isSpecial = false;
		isActive = true;
		assignZone();
		binDelivered = false;
		binCollected = false;
		this.bin = bin;
	}

	public int getCustID() {
		return custID;
	}

	public String getCustName() {
		return custName;
	}

	public int[] getCustAddress() {
		return custAddress;
	}

	public int getContactNo() {
		return contactNo;
	}

	public boolean isSpecial() {
		return isSpecial;
	}

	public boolean isActive() {
		return isActive;
	}

	public boolean isInNorthZone() {
		return inNorthZone;
	}

	public boolean isBinDelivered() {
		return binDelivered;
	}

	public boolean isBinCollected() {
		return binCollected;
	}

	public Bin getBin() {
		return bin;
	}

	public ArrayList<ScheduleEntry> getCustEntries() {
		return custEntries;
	}

	public ArrayList<Invoice> getCustInvoices() {
		return custInvoices;
	}

	// assigning unique id based on total number of created customer objects
	private void assignID() {
		custID = CDW.customers.size() + 1;
	}

	// north and south zones are determined based on the zone number in the address
	private void assignZone() {
		if (custAddress[0] <= 49)
			inNorthZone = true;
		else
			inNorthZone = false;
	}

	public void deactivateCustomer() {
		isActive = false;
	}

	protected void deliverBin() {
		binDelivered = true;
		bin.deliverBin();
	}

	protected void collectBin() {
		binCollected = true;
		bin.collectBin();
	}

	// attaches a schedule entry object with the customer
	protected void attachScheduleEntry(ScheduleEntry entry) {
		if (custEntries == null)
			custEntries = new ArrayList<>();
		custEntries.add(entry);
	}

	// attached an invoice object with the customer
	protected void attachInvoices(ArrayList<Invoice> invoiceList) {
		if (custInvoices == null)
			custInvoices = new ArrayList<>();
		for (int i = 0; i < invoiceList.size(); i++)
			custInvoices.add(invoiceList.get(i));
	}

	// arraylist attributes are excluded to prevent stack overflow
	@Override
	public String toString() {
		return "Customer [custID=" + custID + ", custName=" + custName + ", custAddress=" + Arrays.toString(custAddress)
				+ ", contactNo=" + contactNo + ", isSpecial=" + isSpecial + ", isActive=" + isActive + ", inNorthZone="
				+ inNorthZone + ", binDelivered=" + binDelivered + ", binCollected=" + binCollected + ", bin=" + bin
				+ "]";
	}

}
