package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class SalaryAdvice {
	
	private int adviceNo;
	private LocalDate adviceDate;
	private double salary;
	
	private Driver driver;
	private ArrayList<WorkSchedule> adviceSchedules;
	
	public SalaryAdvice(Driver driver) {
		assignNo();
		adviceDate = LocalDate.now();
		this.driver = driver;
		findSchedules();
		calculateSalary();
	}

	public int getAdviceNo() {
		return adviceNo;
	}

	public LocalDate getAdviceDate() {
		return adviceDate;
	}

	public double getSalary() {
		return salary;
	}

	public Driver getDriver() {
		return driver;
	}

	public ArrayList<WorkSchedule> getAdviceSchedules() {
		return adviceSchedules;
	}
	
	private void assignNo() {
	}
	
	private void findSchedules() {
	}
	
	private void calculateSalary() {
	}

}
