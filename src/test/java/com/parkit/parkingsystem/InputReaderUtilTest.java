package com.parkit.parkingsystem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.parkit.parkingsystem.util.InputReaderUtil;

@ExtendWith(MockitoExtension.class)
public class InputReaderUtilTest {

	@Mock
	Scanner scan;

	@Test
	public void readSelection_should_return_1() {
		// GIVEN
		when(scan.nextLine()).thenReturn("1");

		// WHEN
		InputReaderUtil inputReaderUtil = new InputReaderUtil();

		// THEN
		verify(scan).nextLine();
		assertThat(inputReaderUtil.readSelection()).isEqualTo("1");
	}

	@Test
	public void readVehicleRegistrationNumber_should_return_ABCDEF() throws Exception {
		// GIVEN
		when(scan.nextLine()).thenReturn("ABCDEF");

		// WHEN
		InputReaderUtil inputReaderUtil = new InputReaderUtil();

		// THEN
		verify(scan).nextLine();
		assertThat(inputReaderUtil.readVehicleRegistrationNumber()).isEqualTo("ABCDEF");
	}

}
