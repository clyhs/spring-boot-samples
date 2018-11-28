package org.abigfish.log4j2.log2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application2 {
  Logger logger = LoggerFactory.getLogger(Application2.class);
  public void logApp(){
    logger.debug("log4j2 success ===== debug");
    logger.warn("log4j2 success ===== warn");
    logger.info("log4j2 success ===== info");
    logger.error(System.getProperty("user.home") + " ===== error");
  }

  public static void main(String[] args) {
    Application2 application = new Application2();
    application.logApp();
  }
}