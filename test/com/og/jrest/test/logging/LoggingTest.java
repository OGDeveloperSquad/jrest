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

	/**
	 * Necessary to call at the end of all tests to reset the logging outputs.
	 * Implemented as Singleton pattern, so any changes to the Log class happen
	 * globally.
	 */
	private void resetOutputs() {
		Log.setAllToDefault();
	}

	private String getTimeStamp() {
		// FYI SimpleDateFormat is stupid and for some reason has concurrency issues.
		// Therefore the console output may be printed out of order. Deprecated as of
		// java 8 in favor of java.time (thankfully)
		return new SimpleDateFormat("** MM/dd/yyyy hh:mm:ss aa **\n\n").format(Calendar.getInstance().getTime());
	}

	@Test
	void exceptionTest_Console() {
		String textToShow = "Throwing a new test exception. Should print red with a big long indented stack trace.";
		this.announce(textToShow);
		Log.exception(new Exception(textToShow));
		this.printDivider();
		this.resetOutputs();
	}

	@Test
	void exceptionTest_File() {
		String textToShow = this.getTimeStamp() + "Throwing a new test exception";
		String path = TEST_LOG_PATH + "exceptionLogTest.txt";
		try {
			Log.setExceptionOutput(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.exception(new Exception(textToShow));
		this.resetOutputs();
	}

	@Test
	void exceptionTest_ConsoleLocal() {
		String textToShow = "LOCAL:      Throwing a new local test exception. Should print red with a big long indented stack trace.";
		this.announce(textToShow);
		Log.Local.exception(new Exception(textToShow));
		this.printDivider();
	}

	@Test
	void exceptionTest_FileLocal() {
		String textToShow = this.getTimeStamp() + "Throwing a new local test exception";
		String path = TEST_LOG_PATH + "exceptionLogLocalTest.txt";
		try {
			Log.Local.exception(new Exception(textToShow), new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void errorTest_Console() {
		String textToShow = "Testing the error output. Should be red and stuff";
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
	void errorTest_ConsoleLocal() {
		String textToShow = "LOCAL:      Testing the error local output. Should be red and stuff";
		this.announce(textToShow);
		Log.Local.error(textToShow);
		this.printDivider();
		this.resetOutputs();
	}

	@Test
	void errorTest_FileLocal() {
		String textToShow = this.getTimeStamp() + "Testing the local error output to a file. yes indeedio";
		try {
			String path = TEST_LOG_PATH + "errorLogLocalTest.txt";
			Log.Local.error(textToShow, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.resetOutputs();
	}

	@Test
	void debugTest_Console() {
		String textToShow = "Testing the debug output. Should be white and plain";
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
	void debugTest_ConsoleLocal() {
		String textToShow = "LOCAL:      Testing the debug local output. Should be red and stuff";
		this.announce(textToShow);
		Log.Local.debug(textToShow);
		this.printDivider();
		this.resetOutputs();
	}

	@Test
	void debugTest_FileLocal() {
		String textToShow = this.getTimeStamp() + "Testing the local debug output to a file. yes indeedio";
		try {
			String path = TEST_LOG_PATH + "debugLogLocalTest.txt";
			Log.Local.debug(textToShow, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.resetOutputs();
	}

	@Test
	void infoTest_Console() {
		String textToShow = "Testing the info output. Should be white and plain";
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

	@Test
	void infoTest_ConsoleLocal() {
		String textToShow = "LOCAL:      Testing the info local output. Should be red and stuff";
		this.announce(textToShow);
		Log.Local.info(textToShow);
		this.printDivider();
		this.resetOutputs();
	}

	@Test
	void infoTest_FileLocal() {
		String textToShow = this.getTimeStamp() + "Testing the local info output to a file. yes indeedio";
		try {
			String path = TEST_LOG_PATH + "infoLogLocalTest.txt";
			Log.Local.info(textToShow, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.resetOutputs();
	}

}
