package com.parkit.parkingsystem;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.model.ParkingSpot;

@ExtendWith(MockitoExtension.class)
public class ParkingSpotDAOTest {

	private static ParkingSpotDAO parkingSpotDAO;
	private static ParkingSpot parkingSpot;
	private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();

	@BeforeEach
	private void setUpPerTest() throws Exception {
		parkingSpotDAO = new ParkingSpotDAO();
		parkingSpotDAO.dataBaseConfig = dataBaseTestConfig;
		parkingSpot = new ParkingSpot(1, ParkingType.BIKE, true);
	}

	@Test
	public void nextAvailableSlot_for_a_bike_should_be_4() throws SQLException {

		assertThat(parkingSpotDAO.getNextAvailableSlot(ParkingType.BIKE)).isEqualTo(4);
	}

	@Test
	public void nextAvailableSlot_for_a_car_should_be_1() throws SQLException {

		assertThat(parkingSpotDAO.getNextAvailableSlot(ParkingType.CAR)).isEqualTo(1);
	}

	@Test
	public void nextAvailableSlot_for_null_should_be_error() throws SQLException {

		assertThat(parkingSpotDAO.getNextAvailableSlot(null)).isEqualTo(-1);
	}

	@Test
	public void updateParkingTest_should_return_true() throws SQLException {

		assertThat(parkingSpotDAO.updateParking(parkingSpot)).isTrue();
	}

	@Test
	public void updateParkingTest_for_null_should_return_false() throws SQLException {

		assertThat(parkingSpotDAO.updateParking(null)).isFalse();
	}

}
