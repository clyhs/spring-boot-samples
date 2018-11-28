package org.abigfish.log4j2.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
	Logger logger = LoggerFactory.getLogger(Application.class);

	public void logApp() {
		logger.debug("log4j2 success ===== debug");
		logger.warn("log4j2 success ===== warn");
		logger.info("log4j2 success ===== info");
		logger.error(System.getProperty("user.home") + " ===== error");
	}

	public static void main(String[] args) {
		Application application = new Application();
		application.logApp();
	}
}