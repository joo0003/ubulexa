package es.ubu.ubulexa.core.utils;

import jodd.exception.ExceptionUtil;

public class ExceptionUtils {

  private static String exceptionStackTraceToString(Exception e) {
    return ExceptionUtil.exceptionStackTraceToString(e);
  }

  public static void log(Exception e) {
    LoggerUtils.error(exceptionStackTraceToString(e));
  }
}
