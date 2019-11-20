package es.ubu.ubulexa.tools.s3dumpers;

import es.ubu.ubulexa.Constants;
import es.ubu.ubulexa.tools.SystemEnvReader;
import es.ubu.ubulexa.utils.AmazonS3Utils;
import es.ubu.ubulexa.utils.IOUtils;
import jodd.exception.ExceptionUtil;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class ResponseToS3Dumper extends AbstractS3Dumper {

  private static final Logger LOGGER = LoggerFactory.getLogger(ResponseToS3Dumper.class);

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

  public void dump(String uuid, byte[] bytes) {
    try {
      String filename = createFilename(uuid);
      String key = Constants.SUBFOLDER_RAW_RESPONSES_NAME + "/" + filename;
      dumpToS3(key, bytes);
    } catch (Exception e) {
      LOGGER.error(ExceptionUtil.exceptionStackTraceToString(e));
    }
  }
}
