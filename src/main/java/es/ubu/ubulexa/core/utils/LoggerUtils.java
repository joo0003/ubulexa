package es.ubu.ubulexa.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoggerUtils.class);

  public static void error(String str) {
    LOGGER.error(str);
  }

  public static void info(String str) {
    LOGGER.info(str);
  }
}
