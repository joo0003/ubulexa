package es.ubu.ubulexa.core.tools;

import es.ubu.ubulexa.core.Constants;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.util.StringUtil;

@PetiteBean
public class AppConfig {

  private ConfigPropsReader configPropsReader;

  @PetiteInject
  public void setConfigPropsReader(ConfigPropsReader configPropsReader) {
    this.configPropsReader = configPropsReader;
  }

  public String bucketName() {
    return configPropsReader.bucketName();
  }

  public String threefishSecret() {
    return configPropsReader.threefishSecret();
  }

  public String jwtSecret() {
    return configPropsReader.jwtSecret();
  }

  public String moodleHostUrl() {
    return configPropsReader.moodleHostUrl();
  }

  public String skillId() {
    return configPropsReader.skillId();
  }

  public String cheloCourse1Id() {
    return StringUtil.trimDown(Constants.CHELO_COURSE_1_ID);
  }

  public String cheloCourse2Id() {
    return StringUtil.trimDown(Constants.CHELO_COURSE_2_ID);
  }

  public String raulCourse1() {
    return StringUtil.trimDown(Constants.RAUL_COURSE_1_ID);
  }
}
