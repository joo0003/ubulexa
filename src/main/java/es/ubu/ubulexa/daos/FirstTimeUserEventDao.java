package es.ubu.ubulexa.daos;

import es.ubu.ubulexa.Constants;
import es.ubu.ubulexa.tools.SystemEnvReader;
import es.ubu.ubulexa.utils.AmazonS3Utils;
import es.ubu.ubulexa.utils.Base64Utils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.apache.commons.lang3.StringUtils;

@PetiteBean
public class FirstTimeUserEventDao {

  private AmazonS3Utils amazonS3Utils;
  private SystemEnvReader systemEnvReader;
  private Base64Utils base64Utils;

  @PetiteInject
  public void setBase64Utils(Base64Utils base64Utils) {
    this.base64Utils = base64Utils;
  }

  @PetiteInject
  public void setSystemEnvReader(SystemEnvReader systemEnvReader) {
    this.systemEnvReader = systemEnvReader;
  }

  @PetiteInject
  public void setAmazonS3Utils(AmazonS3Utils amazonS3Utils) {
    this.amazonS3Utils = amazonS3Utils;
  }

  public boolean exists(String userId) {
    if (StringUtils.isBlank(userId)) {
      return false;
    }

    String encodedUserId = base64Utils.encode(userId);

    String key = Constants.SUBFOLDER_FIRST_TIME_USER_EVENTS_NAME + "/" + encodedUserId;
    return amazonS3Utils.doesObjectExist(systemEnvReader.bucketName(), key);
  }
}
