package es.ubu.ubulexa.core.requestinterceptors.steps;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.daos.FirstTimeUserEventDao;
import es.ubu.ubulexa.core.tools.sessionattributesetters.sessionattributegetters.FirstTimeSessionAttributeSetter;
import es.ubu.ubulexa.core.utils.AmazonS3Utils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class FirstTimeIntentRequestInterceptorStep extends AbstractRequestInterceptorStep {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(FirstTimeIntentRequestInterceptorStep.class);

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
      saveFileToS3(handlerInput);
    }
  }

  private void saveFileToS3(HandlerInput handlerInput) {
    try {
      String userId = extractUserId(handlerInput);

      String key = Constants.SUBFOLDER_FIRST_TIME_USER_EVENTS_NAME + "/" + userId;
      amazonS3Utils.putObject(appConfig().bucketName(), key, userId);
    } catch (Exception e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
    }
  }
}
