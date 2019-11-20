package es.ubu.ubulexa.tools.s3dumpers;

import es.ubu.ubulexa.tools.SystemEnvReader;
import es.ubu.ubulexa.utils.AmazonS3Utils;
import es.ubu.ubulexa.utils.ClockUtils;
import es.ubu.ubulexa.utils.IOUtils;
import java.io.IOException;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public abstract class AbstractS3Dumper {

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

  protected String createFilename(String uuid) {
    return clockUtils.clock().millis() + "_" + uuid;
  }

  protected void dumpToS3(String key, byte[] bytes) throws IOException {
    String content = ioUtils.toString(bytes);
    amazonS3Utils.putObject(systemEnvReader.bucketName(), key, content);
  }
}
