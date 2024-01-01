package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class CDW {

	// containers for all involved classes in the prepare waste schedule use case
	public static Bin[] bins = { new Bin(BinType.Trash, 100), new Bin(BinType.Paper, 100),
			new Bin(BinType.Plastic, 100), new Bin(BinType.Hazardous, 100) };
	public static ArrayList<Customer> customers = new ArrayList<>();
	public static ArrayList<WasteEntry> unassignedWasteEntries = new ArrayList<>();
	public static ArrayList<WasteEntry> assignedWasteEntries = new ArrayList<>();
	public static ArrayList<WasteCollectionSchedule> wasteSchedules = new ArrayList<>();
	public static ArrayList<WasteCollectionSchedule> tempWasteSchedules = new ArrayList<>();
	public static ArrayList<Driver> permanentDrivers = new ArrayList<>();
	public static ArrayList<Driver> casualDrivers = new ArrayList<>();
	public static ArrayList<Driver> tempDrivers = new ArrayList<>();
	public static ArrayList<Truck> trucks = new ArrayList<>();
	public static ArrayList<Truck> tempTrucks = new ArrayList<>();

	// creates a new customer object and adds it to its container
	public static Customer registerCustomer(String name, int[] address, int telephone, Bin bin) {
		Customer c = new Customer(name, address, telephone, bin);
		customers.add(c);
		return c;
	}

	// creates a new waste entry object and adds it to its container
	public static WasteEntry addCustomerEntry(Customer customer, LocalDate date, String serviceType) {
		WasteEntry e = new WasteEntry(customer, date, serviceType);
		unassignedWasteEntries.add(e);
		return e;
	}

	// creates as many new waste schedule objects as needed depending on the number
	// of services for the day
	public static ArrayList<WasteCollectionSchedule> prepareWasteSchedule(boolean zone, boolean driverType,
			boolean isHazardous) {

		ArrayList<ScheduleEntry> entries = findWasteEntries(zone, isHazardous);

		if (entries == null)
			return null;

		WasteCollectionSchedule s = new WasteCollectionSchedule(zone, driverType, isHazardous, entries);
		ArrayList<WasteCollectionSchedule> schedules = new ArrayList<>();

		tempWasteSchedules.add(s);
		schedules.add(s);
		entries.removeAll(s.getScheduleEntries());
		tempDrivers.add(s.getDriver());
		tempTrucks.add(s.getTruck());

		while (!entries.isEmpty()) {
			s = new WasteCollectionSchedule(zone, false, isHazardous, entries);
			tempWasteSchedules.add(s);
			schedules.add(s);
			entries.removeAll(s.getScheduleEntries());
			tempDrivers.add(s.getDriver());
			tempTrucks.add(s.getTruck());
		}

		return schedules;
	}

	// finds all entries that need to be serviced on the day based on zone and
	// customer type (hazardous)
	private static ArrayList<ScheduleEntry> findWasteEntries(boolean zone, boolean isHazardous) {

		ArrayList<ScheduleEntry> tempEntries = new ArrayList<>();

		for (int i = 0; i < CDW.unassignedWasteEntries.size(); i++)
			if (CDW.unassignedWasteEntries.get(i).isForNorthZone() == zone
					&& CDW.unassignedWasteEntries.get(i).getEntryDate().equals(LocalDate.now())
					&& CDW.unassignedWasteEntries.get(i).getCustomer().isSpecial() == isHazardous)
				tempEntries.add(CDW.unassignedWasteEntries.get(i));

		if (tempEntries.isEmpty())
			return null;
		return tempEntries;
	}

	// adds the schedule to its container and removes it from the temporary one,
	// updates the driver and truck assignments, and marks the schedule's service
	// entries as assigned
	public static void saveSchedule(WasteCollectionSchedule schedule) {
		wasteSchedules.add(schedule);
		tempWasteSchedules.remove(schedule);
		tempDrivers.remove(schedule.getDriver());
		tempTrucks.remove(schedule.getTruck());
		schedule.getDriver().updateWorkLoad(schedule);
		schedule.getTruck().assignToSchedule(schedule);

		for (int i = 0; i < schedule.getScheduleEntries().size(); i++) {
			schedule.getScheduleEntries().get(i).addToSchedule(schedule.getScheduleNo());
			assignedWasteEntries.add((WasteEntry) schedule.getScheduleEntries().get(i));
			unassignedWasteEntries.remove((WasteEntry) schedule.getScheduleEntries().get(i));
		}
	}

	// creates a new driver object and adds it to its container
	public static Driver addDriver(String name, int telephone, boolean isPermanent, double rate) {
		Driver d = new Driver(name, telephone, isPermanent, rate);
		if (isPermanent)
			permanentDrivers.add(d);
		else
			casualDrivers.add(d);
		return d;
	}

	// creates a new truck object and adds it to its container
	public static Truck addTruck(String licenseNo, TruckType type, boolean isNorthTruck) {
		Truck t = new Truck(licenseNo, type, isNorthTruck);
		trucks.add(t);
		return t;
	}
}
