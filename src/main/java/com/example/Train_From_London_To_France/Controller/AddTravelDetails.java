package com.example.Train_From_London_To_France.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Train_From_London_To_France.Entity.TravelDetails;
import com.example.Train_From_London_To_France.persistenceLayer.CheckForSeatAvailability;
import com.example.Train_From_London_To_France.persistenceLayer.SaveTravelDetails;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/travelTicket")
public class AddTravelDetails {

	private static SaveTravelDetails saveTravelDetails=new SaveTravelDetails();
	
	@Autowired
	private CheckForSeatAvailability availability;
	
	@PostMapping("/addTravelDetails")
	ResponseEntity<?> saveUserTravelDetails(@RequestBody TravelDetails details) {
		if(details.hasNullValues()) {
			return ResponseEntity.badRequest().body("Unable to save data. Please enter valid details");
		}
		TravelDetails details2=new TravelDetails(details.getFrom(), details.getTo(), details.getfName(), details.getlName(), details.getEmail(), details.getPrice_paid());
		details2.setSeatNumber(availability.checkSeatAvailable());
		saveTravelDetails.addTravelDetails(details2);
		//System.out.println(details2.toString());
		return ResponseEntity.ok(details2);
	}
	
	@GetMapping("/getReciept/{ticketId}")
	public ResponseEntity<?> getTravelReciept(@PathVariable("ticketId") int ticketId){
		if(!saveTravelDetails.ticketExists(ticketId)) {
			return ResponseEntity.ok("Ticket Reciept doesn't exists");
		}
		return ResponseEntity.ok(saveTravelDetails.getTicketReciept(ticketId));
	}
	
	@GetMapping("/getAllUsersAndSeatsAllocated")
	public ResponseEntity<?> getAllUsersAndSeatsAllocated(){
		return ResponseEntity.ok(saveTravelDetails.getAllUsersAndSeatsAllocated());
	}
	
	@DeleteMapping("/deleteUser/{ticketId}")
	public ResponseEntity<?> removeUser(@PathVariable("ticketId") int ticketId){
		if(!saveTravelDetails.ticketExists(ticketId)) {
			return ResponseEntity.ok("Ticket doesn't exists");
		}
		return ResponseEntity.ok(saveTravelDetails.removeUser(ticketId));
	}
	
	@PutMapping("/modifySeat/{ticketId}/{seatNo}")
	public ResponseEntity<?> modifyUser(@PathVariable("ticketId") String ticketId, @PathVariable("seatNo") String seatNo){
		System.out.println("Ticket :"+ticketId+", Seat No.:"+seatNo);
		if(saveTravelDetails.ticketAndSeatExists(Integer.parseInt(ticketId), seatNo)) {
			return ResponseEntity.ok(saveTravelDetails.modifyUserTicket(Integer.parseInt(ticketId), seatNo));
		}
		return ResponseEntity.ok("Requested seat is not available");
	}
}
