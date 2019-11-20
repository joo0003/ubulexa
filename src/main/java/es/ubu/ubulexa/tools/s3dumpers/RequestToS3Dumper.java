package es.ubu.ubulexa.tools.s3dumpers;

import es.ubu.ubulexa.Constants;
import jodd.exception.ExceptionUtil;
import jodd.petite.meta.PetiteBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class RequestToS3Dumper extends AbstractS3Dumper {

  private static final Logger LOGGER = LoggerFactory.getLogger(RequestToS3Dumper.class);

  public void dump(String uuid, byte[] bytes) {
    try {
      String filename = createFilename(uuid);
      String key = Constants.SUBFOLDER_RAW_REQUESTS_NAME + "/" + filename;
      dumpToS3(key, bytes);
    } catch (Exception e) {
      LOGGER.error(ExceptionUtil.exceptionStackTraceToString(e));
    }
  }
}
