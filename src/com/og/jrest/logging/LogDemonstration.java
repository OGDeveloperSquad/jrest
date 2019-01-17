package com.og.jrest.logging;

import java.io.File;
import java.io.IOException;

public class LogDemonstration {

	public static void demo() throws IOException {

		/*
		 * To instantiate use ILogger and Log.getInstance() This will typically be used
		 * as a private instance variable within classes
		 */
		ILogger log = Log.newInstance();

		// There are 4 different logging levels to use at your discretion
		log.debug("Some message that will be logged");
		log.info("Another message that will be logged");
		log.error("An error message to be logged");
		log.exception(new Exception("Just pass exceptions into the method"));

		/*
		 * You can change the location of the logs for any specific level or for ALL the
		 * logging levels at once using an output stream or a file
		 */

		// Set the output for ALL logging levels to write to System.out
		log.setOutput(System.out);
		log.setOutput(new File("path/to/some/file.txt")); // NOTE: this can throw IOException

		// Or you can use either of these to set the output of specific log levels
		log.setDebugOutput(System.out);
		log.setDebugOutput(new File("another/path/to/some/file.txt")); // NOTE: this can throw IOException

		log.setInfoOutput(System.out);
		log.setInfoOutput(new File("another/path/to/some/file.txt")); // NOTE: this can throw IOException

		log.setErrorOutput(System.err);
		log.setErrorOutput(new File("another/path/to/some/file.txt")); // NOTE: this can throw IOException

		log.setExceptionOutput(System.out);
		log.setExceptionOutput(new File("another/path/to/some/file.txt")); // NOTE: this can throw IOException

		/*
		 * You can set logging outputs back to their default output state.
		 */

		// Set ALL log levels to their default state
		log.setToDefaultOutput();
		// Or set specific logs to their default state
		log.setDebugToDefaultOutput();
		log.setErrorToDefaultOutput();
		log.setExceptionToDefaultOutput();
		log.setInfoToDefaultOutput();

		/*
		 * You can also use the static logger to write to a specific file or output
		 * stream.
		 */
		Log.error("Error message", System.err);
		Log.error("Error message", new File("path/to/the/file.txt"));

		/*
		 * If you don't need to use the logger extensively for a class or you just don't
		 * need to customize it as heavily, then you can use the static logging methods
		 * to save time and efficiency.
		 */
		Log.error("Some message");
		Log.error(new Error("pass some error into this"));
		Log.exception(new Exception("pass some thrown exception into this"));
		Log.exception(new Throwable("use this as a catchall if necessary"));
		Log.info("message");
		Log.debug("another message");

	}

}
