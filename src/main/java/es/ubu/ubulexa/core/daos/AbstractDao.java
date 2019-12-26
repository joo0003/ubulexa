package es.ubu.ubulexa.core.daos;

import es.ubu.ubulexa.core.tools.AppConfig;
import es.ubu.ubulexa.core.utils.AmazonS3Utils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public abstract class AbstractDao {

  private AmazonS3Utils amazonS3Utils;
  private AppConfig appConfig;

  @PetiteInject
  public void setAppConfig(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  @PetiteInject
  public void setAmazonS3Utils(AmazonS3Utils amazonS3Utils) {
    this.amazonS3Utils = amazonS3Utils;
  }

  public AmazonS3Utils amazonS3Utils() {
    return amazonS3Utils;
  }

  public AppConfig appConfig() {
    return appConfig;
  }
}
