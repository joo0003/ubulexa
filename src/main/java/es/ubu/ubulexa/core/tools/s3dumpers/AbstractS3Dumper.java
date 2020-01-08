package es.ubu.ubulexa.core.tools.s3dumpers;

import es.ubu.ubulexa.core.tools.AppConfig;
import es.ubu.ubulexa.core.utils.AmazonS3Utils;
import es.ubu.ubulexa.core.utils.ExceptionUtils;
import es.ubu.ubulexa.core.utils.time.ClockUtils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public abstract class AbstractS3Dumper {

  private ClockUtils clockUtils;
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

  @PetiteInject
  public void setClockUtils(ClockUtils clockUtils) {
    this.clockUtils = clockUtils;
  }

  public void dump(String uuid, String keyPrefix, byte[] bytes) {
    try {
      String filename = clockUtils.clock().millis() + "_" + uuid;
      String key = keyPrefix + "/" + filename;
      amazonS3Utils.putObject(appConfig.bucketName(), key, bytes);
    } catch (Exception e) {
      ExceptionUtils.log(e);
    }
  }
}
