package com.parkit.parkingsystem;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.util.InputReaderUtil;

public class InputReaderUtilTest {

	private final InputStream systemIn = System.in;
	private final PrintStream systemOut = System.out;

	private ByteArrayInputStream testIn;
	private ByteArrayOutputStream testOut;

	InputReaderUtil inputReaderUtilUnderTest = new InputReaderUtil();

	@BeforeEach
	public void setUpOutput() {
		testOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testOut));
	}

	private void provideInput(String data) {
		testIn = new ByteArrayInputStream(data.getBytes());
		System.setIn(testIn);
	}

	private String getOutput() {
		return testOut.toString();
	}

	@AfterEach
	public void restoreSystemInputOutput() {
		System.setIn(systemIn);
		System.setOut(systemOut);
	}

	@Test
	public void testCase1() {
		final String testString = "1";

		provideInput(testString);

		inputReaderUtilUnderTest.readSelection();

		assertThat(getOutput()).isEqualTo(testString);
	}

}
