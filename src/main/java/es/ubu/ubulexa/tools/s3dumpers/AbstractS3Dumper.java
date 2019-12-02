package es.ubu.ubulexa.tools.s3dumpers;

import es.ubu.ubulexa.tools.SystemEnvReader;
import es.ubu.ubulexa.utils.AmazonS3Utils;
import es.ubu.ubulexa.utils.ClockUtils;
import es.ubu.ubulexa.utils.IOUtils;
import java.io.IOException;
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
  private SystemEnvReader systemEnvReader;
  private IOUtils ioUtils;

  @PetiteInject
  public void setIoUtils(IOUtils ioUtils) {
    this.ioUtils = ioUtils;
  }

  @PetiteInject
  public void setSystemEnvReader(SystemEnvReader systemEnvReader) {
    this.systemEnvReader = systemEnvReader;
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
      dump(key, bytes);
    } catch (Exception e) {
      LOGGER.error(ExceptionUtil.exceptionStackTraceToString(e));
    }
  }

  public void dump(String key, byte[] bytes) throws IOException {
    String content = ioUtils.toString(bytes);
    amazonS3Utils.putObject(systemEnvReader.bucketName(), key, content);
  }

  private String createFilename(String uuid) {
    return clockUtils.clock().millis() + "_" + uuid;
  }
}
