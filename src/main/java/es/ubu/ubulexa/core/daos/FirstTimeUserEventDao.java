package es.ubu.ubulexa.core.daos;

import com.amazonaws.services.s3.model.ListObjectsV2Result;
import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.utils.Base64Utils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class FirstTimeUserEventDao extends AbstractDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(FirstTimeUserEventDao.class);

  private Base64Utils base64Utils;

  @PetiteInject
  public void setBase64Utils(Base64Utils base64Utils) {
    this.base64Utils = base64Utils;
  }

  public boolean exists(String userId) {
    if (StringUtils.isBlank(userId)) {
      return false;
    }

    String key = Constants.SUBFOLDER_FIRST_TIME_USER_EVENTS_NAME + "/" + userId;
    return amazonS3Utils().doesObjectExist(appConfig().bucketName(), key);
  }

  public Integer countAll() {
    try {
      ListObjectsV2Result result = amazonS3Utils().listObjectsV2(
          appConfig().bucketName(),
          Constants.SUBFOLDER_FIRST_TIME_USER_EVENTS_NAME
      );
      return result.getKeyCount();
    } catch (Exception e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
    }
    return null;
  }
}
