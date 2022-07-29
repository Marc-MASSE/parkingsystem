package com.parkit.parkingsystem;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;

public class TicketDAOTest {

	private static TicketDAO ticketDAOTest;
	private Ticket ticket;
	private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
	private static DataBasePrepareService dataBasePrepareService;

	@BeforeAll
	private static void setUp() throws Exception {
		dataBasePrepareService = new DataBasePrepareService();
	}

	@BeforeEach
	private void SetUpPerTest() {
		ticketDAOTest = new TicketDAO();
		ticket = new Ticket();
		ticketDAOTest.dataBaseConfig = dataBaseTestConfig;
		dataBasePrepareService.clearDataBaseEntries();
	}

	@Test
	public void saveTicket_should_return_true() {

		ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
		ticket.setInTime(new Date(System.currentTimeMillis()));
		ticket.setParkingSpot(parkingSpot);
		ticket.setVehicleRegNumber("TEST");

		assertThat(ticketDAOTest.saveTicket(ticket)).isTrue();
	}

	@Test
	public void saveTicket_should_return_false() {

		assertThat(ticketDAOTest.saveTicket(null)).isFalse();
	}

	@Test
	public void getTicket_getVehicleRegNumber_should_return_TEST() {

		ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
		ticket.setInTime(new Date(System.currentTimeMillis()));
		ticket.setParkingSpot(parkingSpot);
		ticket.setVehicleRegNumber("TEST");

		ticketDAOTest.saveTicket(ticket);

		Ticket ticketTest = ticketDAOTest.getTicket("TEST");

		assertThat(ticketTest.getVehicleRegNumber()).isEqualTo("TEST");
	}

	@Test
	public void getTicket_for_TEST_should_not_exist() {

		assertThat(ticketDAOTest.getTicket("TEST")).isNull();
	}

	@Test
	public void updateTicket_should_return_true() {

		ticket.setVehicleRegNumber("TEST");
		ticket.setPrice(5);
		Date inTime = new Date(0);
		Date outTime = new Date(0);
		inTime.setTime(0);
		outTime.setTime(5 * 60 * 60 * 1000);
		ticket.setInTime(inTime);
		ticket.setOutTime(outTime);

		assertThat(ticketDAOTest.updateTicket(ticket)).isTrue();
	}

	@Test
	public void updateTicket_with_null_ticket_should_fail() {

		assertThat(ticketDAOTest.updateTicket(null)).isFalse();
	}

	@Test
	public void isAlreadyHere_should_return_true() {

		ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
		ticket.setInTime(new Date(System.currentTimeMillis()));
		ticket.setParkingSpot(parkingSpot);
		ticket.setVehicleRegNumber("TEST");

		ticketDAOTest.saveTicket(ticket);

		assertThat(ticketDAOTest.isAlreadyHere("TEST")).isTrue();
	}

	@Test
	public void isAlreadyHere_should_return_false() {

		ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
		ticket.setInTime(new Date(System.currentTimeMillis()));
		ticket.setParkingSpot(parkingSpot);
		ticket.setVehicleRegNumber("OTHER");

		ticketDAOTest.saveTicket(ticket);

		assertThat(ticketDAOTest.isAlreadyHere("TEST")).isFalse();
	}

}
