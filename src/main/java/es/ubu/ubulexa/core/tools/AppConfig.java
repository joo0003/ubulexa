package es.ubu.ubulexa.core.tools;

import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

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
}
