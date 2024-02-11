package com.example.Train_From_London_To_France.persistenceLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.example.Train_From_London_To_France.Entity.TravelDetails;

public class SaveTravelDetails {
	private static List<TravelDetails> details= new ArrayList<>();
	private boolean seatAvailable=false;
	
	public TravelDetails addTravelDetails(TravelDetails travelDetails) {
		details.add(travelDetails);
		return travelDetails;
	}

	public TravelDetails getTicketReciept(int ticketId) {
		TravelDetails reciept = new TravelDetails();
		details.forEach(travelReciept->{
			if(travelReciept.getTicketId()==ticketId) {
				reciept.setTicketId(travelReciept.getTicketId());
				reciept.setfName(travelReciept.getfName());
				reciept.setlName(travelReciept.getlName());
				reciept.setEmail(travelReciept.getEmail());
				reciept.setSeatNumber(travelReciept.getSeatNumber());
				reciept.setFrom(travelReciept.getFrom());
				reciept.setTo(travelReciept.getTo());
				reciept.setPrice_paid(travelReciept.getPrice_paid());
			}
		});
		return reciept;
	}

	public List<TravelDetails> getAllUsersAndSeatsAllocated() {
		return details;
	}

	public TravelDetails removeUser(int ticketId) {
		int i=0;
		for(TravelDetails temp: details ) {
			if(temp.getTicketId() == ticketId){
				String section=temp.getSeatNumber().substring(0,1);
				String seatNo=temp.getSeatNumber().substring(1);
				//System.out.println("Section : "+section+", Seat No.: "+seatNo);
				CheckForSeatAvailability availability=new CheckForSeatAvailability();
				availability.addSeatforDeletedUser(section, Integer.parseInt(seatNo));
				return details.remove(i);
			}
			i++;
		}
		return null;
	}

	public TravelDetails modifyUserTicket(int ticketId, String seatNo) {
		TravelDetails updatedTravelReciept=new TravelDetails();
		System.out.println("Details Size:"+details.size());
		for(int i=0;i<details.size();i++) {
			TravelDetails detailsToUpdate=details.get(i);
			if(detailsToUpdate.getTicketId()==ticketId) {
				System.out.println("i:"+i);
				updatedTravelReciept.setTicketId(detailsToUpdate.getTicketId());
				updatedTravelReciept.setfName(detailsToUpdate.getfName());
				updatedTravelReciept.setlName(detailsToUpdate.getlName());
				updatedTravelReciept.setEmail(detailsToUpdate.getEmail());
				updatedTravelReciept.setSeatNumber(seatNo);
				updatedTravelReciept.setFrom(detailsToUpdate.getFrom());
				updatedTravelReciept.setTo(detailsToUpdate.getTo());
				updatedTravelReciept.setPrice_paid(detailsToUpdate.getPrice_paid());
				CheckForSeatAvailability availability1=new CheckForSeatAvailability();
				String section=detailsToUpdate.getSeatNumber().substring(0,1);
				String seatNu=detailsToUpdate.getSeatNumber().substring(1);
				availability1.addSeatforDeletedUser(section, Integer.parseInt(seatNu));
				System.out.println("Added seat");
				details.remove(i);
				details.add(updatedTravelReciept);
				break;
			}
		}
		return updatedTravelReciept;
	}
	
	public boolean ticketAndSeatExists(int ticketId, String seatNo) {
		seatAvailable=false;
		details.forEach(travelReciept->{
			if(travelReciept.getTicketId() == ticketId) {
				CheckForSeatAvailability availability=new CheckForSeatAvailability();
				String section=seatNo.substring(0,1);
				String seatNu=seatNo.substring(1);
				seatAvailable=availability.checkSeatAvailable(section, Integer.parseInt(seatNu));
			}
		});
		return seatAvailable;
	}

	public boolean ticketExists(int ticketId) {
		seatAvailable=false;
		details.forEach(temp->{
			if(temp.getTicketId()==ticketId) {
				seatAvailable=true;
			}
		});
		return seatAvailable;
	}

	
}
