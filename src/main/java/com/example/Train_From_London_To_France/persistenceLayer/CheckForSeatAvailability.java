package com.example.Train_From_London_To_France.persistenceLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class CheckForSeatAvailability {
	private static Map<String, List<Integer>> availableSeats;
	private static int waitingCount=1;
	
	@EventListener(ApplicationReadyEvent.class)
	private void trainSeats() {
		//System.out.println("AutoRun");
		availableSeats=new HashMap<>();
		availableSeats.put("A", new ArrayList<>());
		availableSeats.put("B", new ArrayList<>());
		allocateSeats();
		
		System.out.println("Allocated  seats:");
		  for(Map.Entry<String, List<Integer>> entry: availableSeats.entrySet()) {
		  entry.getValue().forEach(seatNo ->{
		  System.out.println(entry.getKey()+" "+seatNo); }); }
		  System.out.println("-----------------------------------------------------------------------------");
		 
		 
	}

	private void allocateSeats() {
		List<Integer> seatsForSecA= availableSeats.get("A");
		List<Integer> seatsForSecB= availableSeats.get("B");
		for(int i=1; i<=5; i++) {
			seatsForSecA.add(i);
		}
		for(int i=1; i<=5; i++) {
			seatsForSecB.add(i);
		}
		
		availableSeats.put("A", seatsForSecA);
		availableSeats.put("B", seatsForSecB);
	}

	public String checkSeatAvailable() {
		String seatNo="";
		if(!availableSeats.get("A").isEmpty()) {
			List<Integer> seats=availableSeats.get("A");
			seatNo="A"+seats.remove(0);
			availableSeats.put("A", seats);
		}else if(!availableSeats.get("B").isEmpty()) {
			List<Integer> seats=availableSeats.get("B");
			seatNo = "B"+seats.remove(0);
			availableSeats.put("B", seats);
		}else{
			seatNo="WL"+waitingCount++;
		}
		return seatNo;
	}
	
	public String addSeatforDeletedUser(String section, int seatNo) {
		System.out.println("Before adding new seat : Section :"+section+", SeatNo.: "+seatNo);
		for(Map.Entry<String, List<Integer>> entry: availableSeats.entrySet()) {
			  entry.getValue().forEach(seatNu ->{
			  System.out.println(entry.getKey()+" "+seatNu); }); }
		if(section.equals("A")) {
			List<Integer> list= availableSeats.get("A");
			list.add(seatNo);
			availableSeats.put(section, list);
		}else if(section.equals("B")) {
			List<Integer> list= availableSeats.get("B");
			list.add(seatNo);
			availableSeats.put(section, list);
		}
		System.out.println("After adding seat:");
		for(Map.Entry<String, List<Integer>> entry: availableSeats.entrySet()) {
			  entry.getValue().forEach(seatNu ->{
			  System.out.println(entry.getKey()+" "+seatNu); }); }
		return "";
	}

	public boolean checkSeatAvailable(String section, int seatNu) {
		List<Integer> seats=availableSeats.get(section);
		System.out.println("Checking availability, Section:"+section+", Seat:"+seatNu);
		
		
		  for(Map.Entry<String, List<Integer>> entry: availableSeats.entrySet()) {
		  entry.getValue().forEach(seatNo ->{
		  System.out.println(entry.getKey()+" "+seatNo); }); }
		 
		int i=0;
		for(int temp : seats) {
			if(temp==seatNu) {
				seats.remove(Integer.valueOf(seatNu));
				availableSeats.put(section, seats);
				return true;
			}
		}
		return false;
	}

}
