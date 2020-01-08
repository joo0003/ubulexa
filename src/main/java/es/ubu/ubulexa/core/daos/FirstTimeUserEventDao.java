package es.ubu.ubulexa.core.daos;

import com.amazonaws.services.s3.model.ListObjectsV2Result;
import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.utils.ExceptionUtils;
import jodd.petite.meta.PetiteBean;
import org.apache.commons.lang3.StringUtils;

@PetiteBean
public class FirstTimeUserEventDao extends AbstractDao {

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
      ExceptionUtils.log(e);
    }
    return null;
  }
}
