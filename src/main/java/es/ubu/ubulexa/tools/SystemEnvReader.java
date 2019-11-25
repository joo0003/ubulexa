package es.ubu.ubulexa.tools;

import es.ubu.ubulexa.utils.SystemEnvUtils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.util.StringPool;

@PetiteBean
public class SystemEnvReader {

  public enum SystemEnvKey {
    SKILL_ID,
    BUCKET_NAME,
    MOODLE_HOST_URL,
    JWT_SECRET
  }

  private SystemEnvUtils systemEnvUtils;

  @PetiteInject
  public void setSystemEnvUtils(SystemEnvUtils systemEnvUtils) {
    this.systemEnvUtils = systemEnvUtils;
  }

  public String jwtSecret() {
    return systemEnvUtils.getEnvironmentVariable(SystemEnvKey.JWT_SECRET, StringPool.EMPTY);
  }

  public String skillId() {
    return systemEnvUtils.getEnvironmentVariable(SystemEnvKey.SKILL_ID, StringPool.EMPTY);
  }

  public String moodleHostUrl() {
    return systemEnvUtils.getEnvironmentVariable(SystemEnvKey.MOODLE_HOST_URL, StringPool.EMPTY);
  }

  public String bucketName() {
    return systemEnvUtils.getEnvironmentVariable(SystemEnvKey.BUCKET_NAME, StringPool.EMPTY);
  }
}
