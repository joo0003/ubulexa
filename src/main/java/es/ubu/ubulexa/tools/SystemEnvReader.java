package es.ubu.ubulexa.tools;

import es.ubu.ubulexa.utils.SystemUtils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.util.StringPool;

@PetiteBean
public class SystemEnvReader {

  public enum SystemEnvKey {
    SKILL_ID,
    BUCKET_NAME
  }

  private SystemUtils systemUtils;

  @PetiteInject
  public void setSystemUtils(SystemUtils systemUtils) {
    this.systemUtils = systemUtils;
  }

  public String skillId() {
    return systemUtils.getEnvironmentVariable(SystemEnvKey.SKILL_ID.name(), StringPool.EMPTY);
  }

  public String bucketName() {
    return systemUtils.getEnvironmentVariable(SystemEnvKey.BUCKET_NAME.name(), StringPool.EMPTY);
  }
}
