package com.example.Train_From_London_To_France;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Train_From_London_To_France.Entity.TravelDetails;
import com.example.Train_From_London_To_France.persistenceLayer.SaveTravelDetails;

@SpringBootTest
class TrainFromLondonToFranceApplicationTests {

	@Test
	void ticketService() {
		TravelDetails travelDetails=new TravelDetails("London","France","Bhartesh","Surwashi","bhartesh@gmail.com","$5");
		SaveTravelDetails saveTravelDetails= Mockito.mock(SaveTravelDetails.class);
		when(saveTravelDetails.addTravelDetails(travelDetails)).thenReturn(travelDetails);
		
		TravelDetails details=saveTravelDetails.addTravelDetails(travelDetails);
		assertEquals("London", details.getFrom());
		assertEquals("France", details.getTo());
		assertEquals("Bhartesh", details.getfName());
		assertEquals("Surwashi", details.getlName());
		assertEquals("bhartesh@gmail.com", details.getEmail());
		assertEquals("$5", details.getPrice_paid());
		System.out.println("Ticket ID:"+details.getTicketId());
	}

}
