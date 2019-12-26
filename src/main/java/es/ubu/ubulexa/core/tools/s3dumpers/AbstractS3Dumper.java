package es.ubu.ubulexa.core.tools.s3dumpers;

import es.ubu.ubulexa.core.tools.AppConfig;
import es.ubu.ubulexa.core.utils.AmazonS3Utils;
import es.ubu.ubulexa.core.utils.ClockUtils;
import jodd.exception.ExceptionUtil;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public abstract class AbstractS3Dumper {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractS3Dumper.class);

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
      String filename = createFilename(uuid);
      String key = keyPrefix + "/" + filename;
      amazonS3Utils.putObject(appConfig.bucketName(), key, bytes);
    } catch (Exception e) {
      LOGGER.error(ExceptionUtil.exceptionStackTraceToString(e));
    }
  }

  private String createFilename(String uuid) {
    return clockUtils.clock().millis() + "_" + uuid;
  }
}
