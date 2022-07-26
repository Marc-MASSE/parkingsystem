package com.parkit.parkingsystem;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;

public class ParkingSpotDAOTest {

	private static ParkingSpotDAO parkingSpotDAO;
	private static DataBasePrepareService dataBasePrepareService;

	@BeforeAll
	private static void setUp() throws Exception {
		dataBasePrepareService = new DataBasePrepareService();
	}

	@BeforeEach
	private void setUpPerTest() throws Exception {
		dataBasePrepareService.clearDataBaseEntries();
	}

	@Test
	public void nextAvailableSlot_for_a_car_should_be_1() {
		int result;
		result = parkingSpotDAO.getNextAvailableSlot(ParkingType.CAR);
		assertThat(result).isEqualTo(1);
	}

	@Test
	public void nextAvailableSlot_for_a_bike_should_be_4() {
		int result;
		result = parkingSpotDAO.getNextAvailableSlot(ParkingType.BIKE);
		assertThat(result).isEqualTo(4);
	}

	// public void updateParkingTest

}
