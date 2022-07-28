package com.parkit.parkingsystem;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.parkit.parkingsystem.service.InteractiveShell;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;

@ExtendWith(MockitoExtension.class)
public class InteractiveShellTest {

	private InteractiveShell interactiveShellUnderTest;

	@Mock
	private static InputReaderUtil inputReaderUtil;

	@Mock
	private ParkingService parkingService;

	@BeforeEach
	void setInteractiveShellUnderTest() {
		interactiveShellUnderTest = new InteractiveShell();
	}

	@Test
	void when_select_1_processIncomingVehicle_called_once() {

		Date inTime = new Date();
		Date outTime = new Date();
		inTime.setTime(0);
		outTime.setTime(60 * 60 * 1000);

		// GIVEN
		when(inputReaderUtil.readSelection()).thenReturn(1).thenReturn(3);

		// WHEN
		interactiveShellUnderTest.loadInterface();

		// THEN
		verify(parkingService, times(1)).processIncomingVehicle(inTime);
		verify(parkingService, times(0)).processExitingVehicle(outTime);
	}

	@Test
	void when_select_2_processExitingVehicle() {

		Date inTime = new Date();
		Date outTime = new Date();
		inTime.setTime(0);
		outTime.setTime(60 * 60 * 1000);

		// GIVEN
		when(inputReaderUtil.readSelection()).thenReturn(2).thenReturn(3);

		// WHEN
		interactiveShellUnderTest.loadInterface();

		// THEN
		verify(parkingService, times(0)).processIncomingVehicle(inTime);
		verify(parkingService, times(1)).processExitingVehicle(outTime);

	}

	@Test
	void when_unsupported_option_no_process_is_called() {

		Date inTime = new Date();
		Date outTime = new Date();
		inTime.setTime(0);
		outTime.setTime(60 * 60 * 1000);

		// GIVEN
		when(inputReaderUtil.readSelection()).thenReturn(4).thenReturn(3);

		// WHEN
		interactiveShellUnderTest.loadInterface();

		// THEN
		verify(parkingService, times(0)).processIncomingVehicle(inTime);
		verify(parkingService, times(0)).processExitingVehicle(outTime);

	}

}
