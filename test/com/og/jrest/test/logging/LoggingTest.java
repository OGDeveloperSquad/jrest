package com.og.jrest.test.logging;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import com.og.jrest.logging.Log;

class LoggingTest {

	private static final String TEST_LOG_PATH = System.getProperty("user.dir").replace("\\", "/")
			+ "/test/com/og/jrest/test/logging/testlogs/";

	private void announce(String message) {
		System.out.println("Expected:\n" + message + "\nActual:");
	}

	private void printDivider() {
		System.out.println("\n\n===============================================\n\n");
	}

	private void resetOutputs() {
		Log.setAllToDefault();
	}

	private String getTimeStamp() {
		return new SimpleDateFormat("** MM/dd/yyyy hh:mm:ss aa **   ").format(Calendar.getInstance().getTime());
	}

	@Test
	void exceptionTest_Console() {
		String textToShow = this.getTimeStamp()
				+ "Throwing a new test exception. Should print red with a big long indented stack trace.";
		this.announce(textToShow);
		try {
			Log.exception(new Exception(textToShow));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.printDivider();
		this.resetOutputs();
	}

	@Test
	void exceptionTest_File() {
		String textToShow = this.getTimeStamp() + "Throwing a new test exception";
		try {
			String path = TEST_LOG_PATH + "exceptionLogTest.txt";
			Log.setExceptionOutput(new File(path));
			Log.exception(new Exception(textToShow));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.resetOutputs();
	}

	@Test
	void errorTest_Console() {
		String textToShow = this.getTimeStamp() + "Testing the error output. Should be red and stuff";
		this.announce(textToShow);
		Log.error(textToShow);
		this.printDivider();
		this.resetOutputs();
	}

	@Test
	void errorTest_File() {
		String textToShow = this.getTimeStamp() + "Testing the error output to a file. yes indeedio";
		boolean append = false;
		try {
			String path = TEST_LOG_PATH + "errorLogTest.txt";
			Log.setErrorOutput(new File(path), append);
			Log.error(textToShow);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.resetOutputs();
	}

	@Test
	void debugTest_Consose() {
		String textToShow = this.getTimeStamp() + "Testing the debug output. Should be white and plain";
		this.announce(textToShow);
		Log.debug(textToShow);
		this.printDivider();
		this.resetOutputs();
	}

	@Test
	void debugTest_File() {
		String textToShow = this.getTimeStamp() + "Testing the debug file output. If you can read this it worked.";
		boolean append = false;
		try {
			String path = TEST_LOG_PATH + "debugLogTest.txt";
			Log.setDebugOutput(new File(path), append);
			Log.debug(textToShow);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.resetOutputs();
	}

	@Test
	void infoTest_Console() {
		String textToShow = this.getTimeStamp() + "Testing the info output. Should be white and plain";
		this.announce(textToShow);
		Log.debug(textToShow);
		this.printDivider();
		this.resetOutputs();
	}

	@Test
	void infoTest_File() {
		String textToShow = this.getTimeStamp() + "Testing the info file output. If you can read this it worked.";
		boolean append = false;
		try {
			String path = TEST_LOG_PATH + "infoLogTest.txt";
			Log.setDebugOutput(new File(path), append);
			Log.debug(textToShow);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.resetOutputs();
	}

}
