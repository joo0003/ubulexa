package es.ubu.ubulexa.core.utils;

import java.io.InputStream;
import jodd.petite.meta.PetiteBean;
import jodd.util.ClassLoaderUtil;

@PetiteBean
public class ClassLoaderUtils {

  public InputStream getResourceAsStream(String resourceName) {
    try {
      return ClassLoaderUtil.getResourceAsStream(resourceName);
    } catch (Exception e) {
      ExceptionUtils.log(e);
    }
    return null;
  }
}
