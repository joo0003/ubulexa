package es.ubu.ubulexa.utils;

import java.io.InputStream;
import jodd.exception.ExceptionUtil;
import jodd.petite.meta.PetiteBean;
import jodd.util.ClassLoaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class ClassLoaderUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(ClassLoaderUtils.class);

  public InputStream getResourceAsStream(String resourceName) {
    try {
      return ClassLoaderUtil.getResourceAsStream(resourceName);
    } catch (Exception e) {
      LOGGER.error(ExceptionUtil.exceptionStackTraceToString(e));
    }
    return null;
  }
}
