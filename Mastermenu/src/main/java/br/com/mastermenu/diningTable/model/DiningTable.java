package br.com.mastermenu.diningTable.model;

import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "dinningTable")
public class DiningTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int number;
	
	private int idHouse;
	
	private int idClient;
	
	private boolean reserved = false;

	private int peopleNumber;
	
	private String date;
	
	private String time;
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getIdHouse() {
		return idHouse;
	}

	public void setIdHouse(int idHouse) {
		this.idHouse = idHouse;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}
	
	
}
