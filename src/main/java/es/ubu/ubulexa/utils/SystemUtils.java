package es.ubu.ubulexa.utils;

import jodd.petite.meta.PetiteBean;
import jodd.util.StringUtil;

@PetiteBean
public class SystemUtils {

  public String getEnvironmentVariable(String name, String defaultValue) {
    return StringUtil.isBlank(System.getenv(name)) ? defaultValue : System.getenv(name);
  }
}
