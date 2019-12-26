package es.ubu.ubulexa.core.utils;

import es.ubu.ubulexa.core.tools.SystemEnvReader.SystemEnvKey;
import jodd.petite.meta.PetiteBean;
import jodd.util.StringUtil;

@PetiteBean
public class SystemEnvUtils {

  public String getEnvironmentVariable(SystemEnvKey key, String defaultValue) {
    return StringUtil.isBlank(System.getenv(key.name())) ? defaultValue : System.getenv(key.name());
  }
}
