package com.example.Train_From_London_To_France.Entity;

import java.util.Random;


public class TravelDetails {
	
	private static int previousId=1;
	private int ticketId;
	private String fName;
	private String lName;
	private String email;
	private String from;
	private String to;
	private String price_paid;
	private String seatNumber;
	
	public TravelDetails( String from, String to,String fName, String lName, String email, String price_paid) {
		super();
		this.ticketId = previousId++;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.from = from;
		this.to = to;
		this.price_paid = price_paid;
	}
	public TravelDetails() {
		
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getPrice_paid() {
		return price_paid;
	}
	public void setPrice_paid(String price_paid) {
		this.price_paid = price_paid;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public boolean hasNullValues() {
        return fName == null || lName == null || email == null || from == null || to == null || price_paid == null ||
         fName == "" || lName == "" || email == "" || from == "" || to == "" || price_paid == "";
	}

	@Override
	public String toString() {
		return "TravelDetails [ticketId=" + ticketId + ", fName=" + fName + ", lName=" + lName + ", email=" + email
				+ ", from=" + from + ", to=" + to + ", price_paid=" + price_paid + ", seatNumber=" + seatNumber + "]";
	}
	
	
	
}
