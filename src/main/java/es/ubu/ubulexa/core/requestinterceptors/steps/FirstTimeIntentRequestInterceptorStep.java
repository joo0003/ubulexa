package es.ubu.ubulexa.core.requestinterceptors.steps;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.daos.FirstTimeUserEventDao;
import es.ubu.ubulexa.core.tools.sessionattributes.FirstTimeSessionAttributeSetter;
import es.ubu.ubulexa.core.utils.AmazonS3Utils;
import es.ubu.ubulexa.core.utils.ExceptionUtils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.util.StringUtil;

@PetiteBean
public class FirstTimeIntentRequestInterceptorStep extends AbstractRequestInterceptorStep {

  private AmazonS3Utils amazonS3Utils;
  private FirstTimeUserEventDao firstTimeUserEventDao;
  private FirstTimeSessionAttributeSetter firstTimeSessionAttributeSetter;

  @PetiteInject
  public void setAmazonS3Utils(AmazonS3Utils amazonS3Utils) {
    this.amazonS3Utils = amazonS3Utils;
  }

  @PetiteInject
  public void setFirstTimeSessionAttributeSetter(
      FirstTimeSessionAttributeSetter firstTimeSessionAttributeSetter) {
    this.firstTimeSessionAttributeSetter = firstTimeSessionAttributeSetter;
  }

  @PetiteInject
  public void setFirstTimeUserEventDao(FirstTimeUserEventDao firstTimeUserEventDao) {
    this.firstTimeUserEventDao = firstTimeUserEventDao;
  }

  @Override
  public void run(HandlerInput handlerInput) {
    String userId = extractUserId(handlerInput);

    boolean b = firstTimeUserEventDao.exists(userId);

    firstTimeSessionAttributeSetter.set(handlerInput, !b);

    if (!b) {
      saveFileToS3(userId);
    }
  }

  private void saveFileToS3(String userId) {
    try {
      if (StringUtil.isNotBlank(userId)) {
        String key = Constants.SUBFOLDER_FIRST_TIME_USER_EVENTS_NAME + "/" + userId;
        amazonS3Utils.putObject(appConfig().bucketName(), key, userId);
      }
    } catch (Exception e) {
      ExceptionUtils.log(e);
    }
  }
}
