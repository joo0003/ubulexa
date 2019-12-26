package es.ubu.ubulexa.core.tools;

import es.ubu.ubulexa.core.utils.SystemEnvUtils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.util.StringPool;

@PetiteBean
@Deprecated
public class SystemEnvReader {

  public enum SystemEnvKey {
    SKILL_ID,
    BUCKET_NAME,
    MOODLE_HOST_URL,
    JWT_SECRET,
    THREEFISH_SECRET
  }

  private SystemEnvUtils systemEnvUtils;

  @PetiteInject
  public void setSystemEnvUtils(SystemEnvUtils systemEnvUtils) {
    this.systemEnvUtils = systemEnvUtils;
  }

  public String threefishSecret() {
    return systemEnvUtils.getEnvironmentVariable(SystemEnvKey.THREEFISH_SECRET, StringPool.EMPTY);
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
