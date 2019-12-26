package es.ubu.ubulexa.skill.handlers.exceptions.models;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import es.ubu.ubulexa.core.Constants;
import es.ubu.ubulexa.core.pojos.ResponseTemplate;
import es.ubu.ubulexa.core.utils.AmazonS3Utils;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class NoFirstTimeUserEventFoundExceptionHandlerModel extends AbstractExceptionHandlerModel {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(NoFirstTimeUserEventFoundExceptionHandlerModel.class);

  private AmazonS3Utils amazonS3Utils;

  @PetiteInject
  public void setAmazonS3Utils(AmazonS3Utils amazonS3Utils) {
    this.amazonS3Utils = amazonS3Utils;
  }

  public Optional<Response> handle(HandlerInput handlerInput, Throwable throwable) {
    //saveFileToS3(handlerInput);

    ResponseTemplate responseTemplate = new ResponseTemplate();
    responseTemplate.setClazz(NoFirstTimeUserEventFoundExceptionHandlerModel.class);
    responseTemplate.setEndSession(false);

    return buildResponse(handlerInput, responseTemplate);
  }

  private void saveFileToS3(HandlerInput handlerInput) {
    try {
      String userId = extractUserId(handlerInput);

      String key = Constants.SUBFOLDER_FIRST_TIME_USER_EVENTS_NAME + "/" + userId;
      amazonS3Utils.putObject(getAppConfig().bucketName(), key, userId);
    } catch (Exception e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
    }
  }
}