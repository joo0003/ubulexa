package es.ubu.ubulexa.exceptionhandlers.models;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import es.ubu.ubulexa.Constants;
import es.ubu.ubulexa.tools.SystemEnvReader;
import es.ubu.ubulexa.tools.UserIdExtractor;
import es.ubu.ubulexa.utils.AmazonS3Utils;
import es.ubu.ubulexa.utils.Base64Utils;
import java.util.Optional;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import jodd.props.Props;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PetiteBean
public class NoFirstTimeUserEventFoundExceptionHandlerModel extends AbstractExceptionHandlerModel {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(NoFirstTimeUserEventFoundExceptionHandlerModel.class);

  private static final String SPEECH_TEXT_KEY = "NoFirstTimeUserEventFoundExceptionHandlerModel.speechtext";

  private UserIdExtractor userIdExtractor;
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

  @PetiteInject
  public void setUserIdExtractor(UserIdExtractor userIdExtractor) {
    this.userIdExtractor = userIdExtractor;
  }

  public Optional<Response> handle(HandlerInput handlerInput, Throwable throwable) {
    saveFileToS3(handlerInput);

    Props dictionary = dictionaryPropsResolver.resolve(handlerInput);

    String speechText = dictionary.getValue(SPEECH_TEXT_KEY);
    return handlerInput.getResponseBuilder()
        .withSpeech(speechText)
        .build();
  }

  private void saveFileToS3(HandlerInput handlerInput) {
    try {
      String userId = userIdExtractor.extract(handlerInput);

      String encodedUserId = base64Utils.encode(userId);

      String key = Constants.SUBFOLDER_FIRST_TIME_USER_EVENTS_NAME + "/" + encodedUserId;
      amazonS3Utils.putObject(systemEnvReader.bucketName(), key, userId);
    } catch (Exception e) {
      LOGGER.error(ExceptionUtils.getStackTrace(e));
    }
  }
}