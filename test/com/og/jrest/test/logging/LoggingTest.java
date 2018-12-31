package com.og.jrest.test.logging;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import com.og.jrest.logging.ILogger;
import com.og.jrest.logging.Log;

class LoggingTest {

	private static final String TEST_LOG_PATH = System.getProperty("user.dir").replace("\\", "/")
			+ "/test/com/og/jrest/test/logging/testlogs/";

	private void announce(String message, OutputStream output) {
		message = "Expected:\n" + message + "\nActual:\n";
		try {
			output.write(message.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		// Log.setAllToDefault();
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
		ILogger log = Log.newInstance();
		this.announce(textToShow, System.err);
		log.exception(new Exception(textToShow));
		this.printDivider();

		this.resetOutputs();
	}

	@Test
	void exceptionTest_File() {
		String textToShow = this.getTimeStamp() + "Throwing a new test exception";
		String path = TEST_LOG_PATH + "exceptionLogTest.txt";
		ILogger log = Log.newInstance();
		try {
			log.setExceptionOutput(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.exception(new Exception(textToShow));

		this.resetOutputs();
	}

	@Test
	void exceptionTest_ConsoleStatic() {
		String textToShow = "STATIC-LOG-TEST: Throwing a new static test exception. Should print red with a big long indented stack trace.";
		this.announce(textToShow, System.err);
		Log.exception(new Exception(textToShow));
		this.printDivider();

		this.resetOutputs();
	}

	@Test
	void exceptionTest_FileStatic() {
		String textToShow = this.getTimeStamp() + "Throwing a new static test exception";
		String path = TEST_LOG_PATH + "exceptionLogStaticTest.txt";
		Log.exception(new Exception(textToShow), new File(path));

		this.resetOutputs();
	}

	@Test
	void errorTest_Console() {
		String textToShow = "Testing the error output. Should be red and stuff";
		ILogger log = Log.newInstance();
		this.announce(textToShow, System.err);
		log.error(textToShow);
		this.printDivider();

		this.resetOutputs();
	}

	@Test
	void errorTest_File() {
		String textToShow = this.getTimeStamp() + "Testing the error output to a file. yes indeedio";
		ILogger log = Log.newInstance();
		boolean append = false;
		try {
			String path = TEST_LOG_PATH + "errorLogTest.txt";
			log.setErrorOutput(new File(path), append);
			log.error(textToShow);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.resetOutputs();
	}

	@Test
	void errorTest_ConsoleStatic() {
		String textToShow = "STATIC-LOG-TEST: Testing the error static output. Should be red and stuff";
		this.announce(textToShow, System.err);
		Log.error(textToShow);
		this.printDivider();

		this.resetOutputs();
	}

	@Test
	void errorTest_FileStatic() {
		String textToShow = this.getTimeStamp() + "Testing the static error output to a file. yes indeedio";
		String path = TEST_LOG_PATH + "errorLogStaticTest.txt";
		Log.error(textToShow, new File(path));

		this.resetOutputs();
	}

	@Test
	void debugTest_Console() {
		String textToShow = "Testing the debug output. Should be white and plain";
		ILogger log = Log.newInstance();
		this.announce(textToShow, System.out);
		log.debug(textToShow);
		this.printDivider();

		this.resetOutputs();
	}

	@Test
	void debugTest_File() {
		String textToShow = this.getTimeStamp() + "Testing the debug file output. If you can read this it worked.";
		ILogger log = Log.newInstance();
		String path = TEST_LOG_PATH + "debugLogTest.txt";
		boolean append = false;
		try {
			log.setDebugOutput(new File(path), append);
			log.debug(textToShow);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.resetOutputs();
	}

	@Test
	void debugTest_ConsoleStatic() {
		String textToShow = "STATIC-LOG-TEST: Testing the debug static output. Should be plain white text";
		this.announce(textToShow, System.out);
		Log.debug(textToShow);
		this.printDivider();

		this.resetOutputs();
	}

	@Test
	void debugTest_FileStatic() {
		String textToShow = this.getTimeStamp() + "Testing the static debug output to a file. yes indeedio";
		String path = TEST_LOG_PATH + "debugLogStaticTest.txt";
		Log.debug(textToShow, new File(path));

		this.resetOutputs();
	}

	@Test
	void infoTest_Console() {
		String textToShow = "Testing the info output. Should be white and plain";
		ILogger log = Log.newInstance();
		this.announce(textToShow, System.out);
		log.debug(textToShow);
		this.printDivider();

		this.resetOutputs();
	}

	@Test
	void infoTest_File() {
		String textToShow = this.getTimeStamp() + "Testing the info file output. If you can read this it worked.";
		ILogger log = Log.newInstance();
		boolean append = false;
		try {
			String path = TEST_LOG_PATH + "infoLogTest.txt";
			log.setInfoOutput(new File(path), append);
			log.info(textToShow);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.resetOutputs();
	}

	@Test
	void infoTest_ConsoleStatic() {
		String textToShow = "STATIC-LOG-TEST: Testing the info static output. Should be plain white text";
		this.announce(textToShow, System.out);
		Log.info(textToShow);
		this.printDivider();

		this.resetOutputs();
	}

	@Test
	void infoTest_FileStatic() {
		String textToShow = this.getTimeStamp() + "Testing the static info output to a file. yes indeedio";
		String path = TEST_LOG_PATH + "infoLogStaticTest.txt";
		Log.info(textToShow, new File(path));

		this.resetOutputs();
	}

}
